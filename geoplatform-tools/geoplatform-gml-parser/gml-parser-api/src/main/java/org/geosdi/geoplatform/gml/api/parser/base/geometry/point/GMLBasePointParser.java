/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gml.api.parser.base.geometry.point;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geosdi.geoplatform.gml.api.Point;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.responsibility.BasePointGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.responsibility.DirectPositionGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBasePointParser extends AbstractGMLBaseParser<Point, PointProperty, com.vividsolutions.jts.geom.Point> {

    private final CoordinateBaseParser coordinateParser;
    private final BasePointGeometryHandler directPos = new DirectPositionGeometryHandler();

    public GMLBasePointParser(GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser,
            CoordinateBaseParser theCoordinateBaseParser) {
        super(theGeometryFactory, theSrsParser);
        Preconditions.checkNotNull(theSrsParser, "The GML Coordinate Parser must not be null.");
        this.coordinateParser = theCoordinateBaseParser;
    }

    @Override
    protected com.vividsolutions.jts.geom.Point canParseGeometry(Point gmlGeometry)
            throws ParserException {
        return directPos.buildGeometry(geometryFactory, gmlGeometry, coordinateParser);
    }

    @Override
    public com.vividsolutions.jts.geom.Point parseGeometry(PointProperty propertyType)
            throws ParserException {
        Preconditions.checkNotNull(propertyType, "The Property Type must not be null.");
        if (propertyType.isSetPoint()) {
            return super.parseGeometry(propertyType.getPoint());
        }
        throw new ParserException("There is no GML Point To Parse.");
    }
}
