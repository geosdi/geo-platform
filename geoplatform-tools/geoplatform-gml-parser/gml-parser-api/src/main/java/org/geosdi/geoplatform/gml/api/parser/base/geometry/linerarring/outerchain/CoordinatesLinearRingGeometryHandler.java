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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.outerchain;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.LinearRing;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.outerchain.BaseOuterChainHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CoordinatesLinearRingGeometryHandler extends BaseOuterChainHandler<LinearRing, org.locationtech.jts.geom.LinearRing, CoordinateBaseParser, LineString> {

    /**
     * @param theSRSParser
     */
    CoordinatesLinearRingGeometryHandler(@Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSRSParser) {
        super(theSRSParser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param parser
     * @return {@link org.locationtech.jts.geom.LinearRing}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LinearRing buildGeometry(GeometryFactory geometryFactory, LinearRing gmlGeometry,
            CoordinateBaseParser parser) throws ParserException {
        logger.trace("###########################Called : {}#buildGeometry", this.getClass().getSimpleName());
        if (gmlGeometry.isSetCoordinates()) {
            org.locationtech.jts.geom.LinearRing linearRing = geometryFactory.createLinearRing(parser.parseCoordinates(gmlGeometry.getCoordinates()));
            this.srsParser.parseSRS(gmlGeometry, linearRing);
            return linearRing;
        }
        throw new ParserException("There are no Ring in this Chain to parse this GML Geometry : " + gmlGeometry);
    }

    /**
     * @param gmlGeometry
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString buildGeometryAsGeoJson(LinearRing gmlGeometry, CoordinateBaseParser parser) throws ParserException {
        logger.trace("###########################Called : {}#buildGeometryAsGeoJson", this.getClass().getSimpleName());
        if (gmlGeometry.isSetCoordinates()) {
            LineString linearString = new LineString(parser.parseCoordinatesAsGeoJson(gmlGeometry.getCoordinates()));
            linearString.setCrs(this.srsParser.parseSRS(gmlGeometry));
            return linearString;
        }
        throw new ParserException("There are no Ring in this Chain to parse this GML Geometry : " + gmlGeometry);
    }
}