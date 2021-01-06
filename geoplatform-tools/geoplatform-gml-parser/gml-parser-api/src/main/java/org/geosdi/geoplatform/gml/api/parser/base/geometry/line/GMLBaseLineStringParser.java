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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.line;

import org.geosdi.geoplatform.gml.api.LineString;
import org.geosdi.geoplatform.gml.api.LineStringProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.responsibility.outerchain.MixedLineGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.AbstractGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseLineStringParser extends AbstractGMLBaseParser<LineString, LineStringProperty, org.locationtech.jts.geom.LineString, org.geojson.LineString> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private CoordinateBaseParser coordinateParser;
    private GMLBasePointParser pointParser;
    private AbstractGeometryHandler<LineString, org.locationtech.jts.geom.LineString, GMLBasePointParser, CoordinateBaseParser, org.geojson.LineString> mixedLineHandler;

    /**
     * @param theGeometryFactory
     * @param theSrsParser
     * @param coordinateParser
     * @param thePointParser
     */
    public GMLBaseLineStringParser(GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser,
            CoordinateBaseParser coordinateParser, GMLBasePointParser thePointParser) {
        super(theGeometryFactory, theSrsParser);
        this.coordinateParser = coordinateParser;
        this.pointParser = thePointParser;
        this.mixedLineHandler = new MixedLineGeometryHandler(theSrsParser);
    }

    /**
     * @param gmlGeometry
     * @return {@link org.locationtech.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    protected org.locationtech.jts.geom.LineString canParseGeometry(LineString gmlGeometry) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#canParseGeometry", this.getClass().getSimpleName());
        return this.mixedLineHandler.buildGeometry(geometryFactory, gmlGeometry, pointParser, coordinateParser);
    }

    /**
     * @param propertyType
     * @return {@link org.locationtech.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LineString parseGeometry(LineStringProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The LineString Property Type must not be null.");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#parseGeometry", this.getClass().getSimpleName());
        if (propertyType.isSetLineString()) {
            return super.parseGeometry(propertyType.getLineString());
        }
        throw new ParserException("There is no GML LineString To Parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    protected org.geojson.LineString canParseGeometryAsGeoJson(LineString gmlGeometry) throws ParserException {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#canParseGeometryAsGeoJson", this.getClass().getSimpleName());
        return this.mixedLineHandler.buildGeometryAsGeoJson(gmlGeometry, pointParser, coordinateParser);
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.LineString parseGeometryAsGeoJson(LineStringProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The LineString Property Type must not be null.");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@Called : {}#parseGeometryAsGeoJson", this.getClass().getSimpleName());
        if (propertyType.isSetLineString()) {
            return super.parseGeometryAsGeoJson(propertyType.getLineString());
        }
        throw new ParserException("There is no GML LineString To Parse.");
    }
}