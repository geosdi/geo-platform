/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.responsibility;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.ArcString;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.LineStringSegment;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DirectPositionListCurveHandler extends AbstractCurveHandler {

    private final GMLBasePointParser pointParser = GMLBaseParametersRepo.getDefaultPointParser();

    DirectPositionListCurveHandler() {
    }

    /**
     * @param geometryFactory
     * @param lineStringSegment
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString parseGeometry(GeometryFactory geometryFactory, LineStringSegment lineStringSegment, CoordinateBaseParser parser) throws ParserException {
        return geometryFactory.createLineString(parser.parseCoordinates(lineStringSegment.getPos()));
    }

    /**
     * @param lineStringSegment
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.LineString parseGeometryAsGeoJson(LineStringSegment lineStringSegment, CoordinateBaseParser parser) throws ParserException {
        return new org.geojson.LineString(parser.parseCoordinatesAsGeoJson(lineStringSegment.getPos()));
    }

    /**
     * @param geometryFactory
     * @param arcString
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString parseGeometry(GeometryFactory geometryFactory, ArcString arcString, CoordinateBaseParser parser) throws ParserException {
        return arcString.isSetPosOrPointPropertyOrPointRep() ? geometryFactory.createLineString(toJTSCoordinates(arcString, parser)) : null;
    }

    /**
     * @param arcString
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.LineString parseGeometryAsGeoJson(ArcString arcString, CoordinateBaseParser parser) throws ParserException {
        return arcString.isSetPosOrPointPropertyOrPointRep() ? new org.geojson.LineString(toGeoJsonCoordinates(arcString, parser)) : null;
    }

    /**
     * @param arcString
     * @param parser
     * @return {@link Coordinate[]}
     * @throws ParserException
     */
    private Coordinate[] toJTSCoordinates(ArcString arcString, CoordinateBaseParser parser) throws ParserException {
        checkArgument((arcString.getPosOrPointPropertyOrPointRep().size() == 3), "The Parameter posOrPointPropertyOrPointRep must contains 3 elements");
        List<Coordinate> coordinates = newArrayList();
        for (JAXBElement jaxbElement : arcString.getPosOrPointPropertyOrPointRep()) {
            if (jaxbElement.getValue() instanceof PointProperty) {
                coordinates.add(pointParser.parseGeometry((PointProperty) jaxbElement.getValue()).getCoordinate());
            } else if (jaxbElement.getValue() instanceof DirectPosition) {
                coordinates.add(parser.parseCoordinate((DirectPosition) jaxbElement.getValue()));
            } else {
                logger.warn("###############Class not parsed correctly : {}\n", jaxbElement.getValue().getClass().getSimpleName());
            }
        }
        return coordinates.toArray(new Coordinate[coordinates.size()]);
    }

    /**
     * @param arcString
     * @param parser
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    private LngLatAlt[] toGeoJsonCoordinates(ArcString arcString, CoordinateBaseParser parser) throws ParserException {
        checkArgument((arcString.getPosOrPointPropertyOrPointRep().size() == 3), "The Parameter posOrPointPropertyOrPointRep must contains 3 elements");
        List<LngLatAlt> coordinates = newArrayList();
        for (JAXBElement jaxbElement : arcString.getPosOrPointPropertyOrPointRep()) {
            if (jaxbElement.getValue() instanceof PointProperty) {
                coordinates.add(pointParser.parseGeometryAsGeoJson((PointProperty) jaxbElement.getValue()).getCoordinates());
            } else if (jaxbElement.getValue() instanceof DirectPosition) {
                coordinates.add(parser.parseCoordinateAsGeoJson((DirectPosition) jaxbElement.getValue()));
            } else {
                logger.warn("###############Class not parsed correctly : {}\n", jaxbElement.getValue().getClass().getSimpleName());
            }
        }
        return coordinates.toArray(new LngLatAlt[coordinates.size()]);
    }
}