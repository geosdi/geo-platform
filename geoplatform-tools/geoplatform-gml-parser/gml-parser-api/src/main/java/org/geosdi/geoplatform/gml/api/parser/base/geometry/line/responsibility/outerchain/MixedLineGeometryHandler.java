/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geosdi.geoplatform.gml.api.LineString;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.responsibility.internalchain.InternalPointLineHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.AbstractGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MixedLineGeometryHandler extends AbstractGeometryHandler<LineString, com.vividsolutions.jts.geom.LineString, GMLBasePointParser, CoordinateBaseParser>
        implements MixedLineHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private InternalPointLineHandler mixedPointHandler;

    public MixedLineGeometryHandler() {
        super.setSuccessor(new DirectPositionLineGeometryHandler());
        this.mixedPointHandler = new InternalPointLineHandler();
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link com.vividsolutions.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public com.vividsolutions.jts.geom.LineString buildGeometry(GeometryFactory geometryFactory, LineString gmlGeometry,
            GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        return gmlGeometry.isSetPosOrPointPropertyOrPointRep()
                ? buildLineString(geometryFactory, gmlGeometry, firstParser,
                secondParser) : buildGeometry(geometryFactory, gmlGeometry,
                secondParser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param parser
     * @return {@link com.vividsolutions.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public com.vividsolutions.jts.geom.LineString buildGeometry(GeometryFactory geometryFactory,
            LineString gmlGeometry, CoordinateBaseParser parser) throws ParserException {
        return super.forwardBuildGeometry(geometryFactory, gmlGeometry, parser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link com.vividsolutions.jts.geom.LineString}
     * @throws ParserException
     */
    @Override
    public com.vividsolutions.jts.geom.LineString buildLineString(GeometryFactory geometryFactory,
            LineString gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser)
            throws ParserException {
        List<Coordinate> coordinates = new ArrayList<>();
        for (JAXBElement<?> element : gmlGeometry.getPosOrPointPropertyOrPointRep()) {
            coordinates.add(this.mixedPointHandler.buildGeometry(geometryFactory, element.getValue()).getCoordinate());
        }
        return geometryFactory.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
    }
}
