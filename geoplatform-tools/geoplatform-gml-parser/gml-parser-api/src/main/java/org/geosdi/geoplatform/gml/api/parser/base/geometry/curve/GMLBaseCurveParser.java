/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.curve;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.geosdi.geoplatform.gml.api.AbstractCurveSegment;
import org.geosdi.geoplatform.gml.api.Curve;
import org.geosdi.geoplatform.gml.api.CurveSegmentArrayProperty;
import org.geosdi.geoplatform.gml.api.LineStringSegment;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.responsibility.AbstractCurveHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.responsibility.CoordCurveHandler;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseCurveParser {

    private CoordinateBaseParser coordinateParser = GMLBaseParametersRepo.getDefaultCoordinateBaseParser();
    private AbstractCurveHandler coordCurveHandler = new CoordCurveHandler();
    private final GeometryFactory geometryFactory;
    private final AbstractGMLBaseSRSParser srsParser;

    /**
     * @param theGeometryFactory
     * @param theSrsParser
     */
    public GMLBaseCurveParser(GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser) {
        this.geometryFactory = theGeometryFactory;
        this.srsParser = theSrsParser;
    }

    /**
     * @param gmlGeometry
     * @return {@link Collection<LineString>}
     * @throws ParserException
     */
    public Collection<LineString> parseGeometry(Curve gmlGeometry) throws ParserException {
        List<LineString> lines = new ArrayList<>();
        if (gmlGeometry.isSetSegments()) {
            CurveSegmentArrayProperty curveArrySegment = gmlGeometry.getSegments();
            for (AbstractCurveSegment curveSegment : curveArrySegment.getAbstractCurveSegment()) {
                if (curveSegment instanceof LineStringSegment) {
                    LineStringSegment lineStringSegment = (LineStringSegment) curveSegment;
                    lines.add(coordCurveHandler.parseGeometry(geometryFactory,
                            lineStringSegment, coordinateParser));
                } else {
                    throw new IllegalArgumentException("In CurveType only LineStringSegment are allowed.");
                }
            }
        }
        return lines;
    }
}
