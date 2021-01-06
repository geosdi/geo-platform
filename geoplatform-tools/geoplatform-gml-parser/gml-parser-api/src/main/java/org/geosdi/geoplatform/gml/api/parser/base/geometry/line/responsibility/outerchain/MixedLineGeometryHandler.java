/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.line.responsibility.outerchain;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.LineString;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.responsibility.internalchain.InternalPointLineHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.AbstractGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MixedLineGeometryHandler extends AbstractGeometryHandler<LineString, org.locationtech.jts.geom.LineString, GMLBasePointParser, CoordinateBaseParser, org.geojson.LineString>
        implements MixedLineHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final AbstractGMLBaseSRSParser srsParser;
    private InternalPointLineHandler mixedPointHandler;

    /**
     * @param theSRSParser
     */
    public MixedLineGeometryHandler(@Nonnull(when = When.NEVER) AbstractGMLBaseSRSParser theSRSParser) {
        checkArgument(theSRSParser != null, "The Parameter srsParser must not be null.");
        this.srsParser = theSRSParser;
        super.setSuccessor(new DirectPositionLineGeometryHandler(theSRSParser));
        this.mixedPointHandler = new InternalPointLineHandler();
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link org.locationtech.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LineString buildGeometry(GeometryFactory geometryFactory, LineString gmlGeometry,
            GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#buildGeometry", this.getClass().getSimpleName());
        return gmlGeometry.isSetPosOrPointPropertyOrPointRep()
                ? buildLineString(geometryFactory, gmlGeometry, firstParser, secondParser) : buildGeometry(geometryFactory, gmlGeometry,
                secondParser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param parser
     * @return {@link org.locationtech.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LineString buildGeometry(GeometryFactory geometryFactory, LineString gmlGeometry, CoordinateBaseParser parser) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#buildGeometry", this.getClass().getSimpleName());
        return super.forwardBuildGeometry(geometryFactory, gmlGeometry, parser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link org.locationtech.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LineString buildLineString(GeometryFactory geometryFactory, LineString gmlGeometry,
            GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#buildLineString", this.getClass().getSimpleName());
        List<Coordinate> coordinates = new ArrayList<>();
        for (JAXBElement<?> element : gmlGeometry.getPosOrPointPropertyOrPointRep()) {
            coordinates.add(this.mixedPointHandler.buildGeometry(geometryFactory, element.getValue()).getCoordinate());
        }
        org.locationtech.jts.geom.LineString lineString = geometryFactory.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
        this.srsParser.parseSRS(gmlGeometry, lineString);
        return lineString;
    }

    /**
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.LineString buildGeometryAsGeoJson(LineString gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#buildGeometryAsGeoJson", this.getClass().getSimpleName());
        return gmlGeometry.isSetPosOrPointPropertyOrPointRep()
                ? buildLineStringAsGeoJson(gmlGeometry, firstParser, secondParser) : buildGeometryAsGeoJson(gmlGeometry, secondParser);
    }

    /**
     * @param gmlGeometry
     * @param parser
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.LineString buildGeometryAsGeoJson(LineString gmlGeometry, CoordinateBaseParser parser) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#buildGeometryAsGeoJson", this.getClass().getSimpleName());
        return super.forwardBuildGeometryAsGeoJson(gmlGeometry, parser);
    }

    /**
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.LineString buildLineStringAsGeoJson(LineString gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#buildLineStringAsGeoJson", this.getClass().getSimpleName());
        List<LngLatAlt> coordinates = new ArrayList<>();
        for (JAXBElement<?> element : gmlGeometry.getPosOrPointPropertyOrPointRep()) {
            coordinates.add(this.mixedPointHandler.buildGeometryAsGeoJson(element.getValue()).getCoordinates());
        }
        org.geojson.LineString geoJsonLineString = new org.geojson.LineString(coordinates.stream().toArray(s -> new LngLatAlt[s]));
        geoJsonLineString.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return geoJsonLineString;
    }
}