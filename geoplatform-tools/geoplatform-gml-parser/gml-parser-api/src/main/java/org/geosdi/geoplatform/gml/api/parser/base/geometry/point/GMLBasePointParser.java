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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.point;

import org.geosdi.geoplatform.gml.api.Point;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.responsibility.BasePointGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.responsibility.DirectPositionGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBasePointParser extends AbstractGMLBaseParser<Point, PointProperty, org.locationtech.jts.geom.Point, org.geojson.Point> {

    private final CoordinateBaseParser coordinateParser;
    private final BasePointGeometryHandler directPos = new DirectPositionGeometryHandler();

    /**
     * @param theGeometryFactory
     * @param theSrsParser
     * @param theCoordinateBaseParser
     */
    public GMLBasePointParser(GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser,
            CoordinateBaseParser theCoordinateBaseParser) {
        super(theGeometryFactory, theSrsParser);
        checkNotNull(theSrsParser, "The GML Coordinate Parser must not be null.");
        this.coordinateParser = theCoordinateBaseParser;
    }

    /**
     * @param gmlGeometry
     * @return {@link org.locationtech.jts.geom.Point}
     * @throws ParserException
     */
    @Override
    protected org.locationtech.jts.geom.Point canParseGeometry(Point gmlGeometry) throws ParserException {
        return directPos.buildGeometry(geometryFactory, gmlGeometry, coordinateParser);
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.Point}
     * @throws ParserException
     */
    @Override
    protected org.geojson.Point canParseGeometryAsGeoJson(Point gmlGeometry) throws ParserException {
        return directPos.buildGeometryAsGeoJson(gmlGeometry, coordinateParser);
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.Point}
     * @throws ParserException
     */
    @Override
    public org.geojson.Point parseGeometryAsGeoJson(PointProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The Property Type must not be null.");
        if (propertyType.isSetPoint()) {
            return super.parseGeometryAsGeoJson(propertyType.getPoint());
        }
        throw new ParserException("There is no GML Point To Parse.");
    }

    /**
     * @param propertyType
     * @return @link com.vividsolutions.jts.geom.Point}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.Point parseGeometry(PointProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The Property Type must not be null.");
        if (propertyType.isSetPoint()) {
            return super.parseGeometry(propertyType.getPoint());
        }
        throw new ParserException("There is no GML Point To Parse.");
    }
}