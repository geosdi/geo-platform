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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.Polygon;
import org.geosdi.geoplatform.gml.api.PolygonProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.builder.GMLBasePolygonBuilder;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.builder.PolygonBuilder;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBasePolygonParser extends AbstractGMLBaseParser<Polygon, PolygonProperty, org.locationtech.jts.geom.Polygon, org.geojson.Polygon> {

    private final GMLBaseLinearRingParser linearRingParser;
    private final PolygonBuilder polygonBuilder;

    /**
     * @param theLinearRingParser
     * @param theGeometryFactory
     * @param theSrsParser
     */
    public GMLBasePolygonParser(GMLBaseLinearRingParser theLinearRingParser, GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser) {
        super(theGeometryFactory, theSrsParser);
        this.linearRingParser = theLinearRingParser;
        this.polygonBuilder = new GMLBasePolygonBuilder(linearRingParser);
    }

    /**
     * @param gmlGeometry
     * @return {@link org.locationtech.jts.geom.Polygon}
     * @throws ParserException
     */
    @Override
    protected org.locationtech.jts.geom.Polygon canParseGeometry(Polygon gmlGeometry) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#canParseGeometry", this.getClass().getSimpleName());
        LinearRing shell = this.polygonBuilder.buildExteriorPolygon(gmlGeometry);
        LinearRing[] holes = this.polygonBuilder.buildInteriorPolygon(gmlGeometry);
        org.locationtech.jts.geom.Polygon polygon = geometryFactory.createPolygon(shell, holes);
        this.srsParser.parseSRS(gmlGeometry, polygon);
        return polygon;
    }

    /**
     * @param propertyType
     * @return {@link org.locationtech.jts.geom.Polygon}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.Polygon parseGeometry(PolygonProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The Polygon Property Type must not be null.");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#parseGeometry", this.getClass().getSimpleName());
        if (propertyType.isSetPolygon()) {
            return super.parseGeometry(propertyType.getPolygon());
        }
        throw new ParserException("There is no GML Polygon to parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.Polygon}
     * @throws ParserException
     */
    @Override
    protected org.geojson.Polygon canParseGeometryAsGeoJson(Polygon gmlGeometry) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#canParseGeometryAsGeoJson", this.getClass().getSimpleName());
        org.geojson.Polygon polygon = new org.geojson.Polygon();
        LineString shell = this.polygonBuilder.buildExteriorPolygonAsGeoJson(gmlGeometry);
        if (shell != null)
            polygon.setExteriorRing(shell.getCoordinates());
        LineString[] holes = this.polygonBuilder.buildInteriorPolygonAsGeoJson(gmlGeometry);
        if (holes != null)
            stream(holes)
                    .filter(Objects::nonNull)
                    .forEach(interiorRing -> polygon.addInteriorRing(interiorRing.getCoordinates()));
        polygon.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return polygon;
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.Polygon}
     * @throws ParserException
     */
    @Override
    public org.geojson.Polygon parseGeometryAsGeoJson(PolygonProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The Polygon Property Type must not be null.");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#parseGeometryAsGeoJson", this.getClass().getSimpleName());
        if (propertyType.isSetPolygon()) {
            return super.parseGeometryAsGeoJson(propertyType.getPolygon());
        }
        throw new ParserException("There is no GML Polygon to parse.");
    }
}