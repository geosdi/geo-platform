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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.LinearRing;
import org.geosdi.geoplatform.gml.api.LinearRingProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.outerchain.MixedLinearRingGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.AbstractGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseLinearRingParser extends AbstractGMLBaseParser<LinearRing, LinearRingProperty, org.locationtech.jts.geom.LinearRing, org.geojson.LineString> {

    private CoordinateBaseParser coordinateParser;
    private GMLBasePointParser pointParser;
    private AbstractGeometryHandler<LinearRing, org.locationtech.jts.geom.LinearRing, GMLBasePointParser, CoordinateBaseParser, LineString> mixedLinearRingHandler;

    /**
     * @param coordinateParser
     * @param pointParser
     * @param theGeometryFactory
     * @param theSrsParser
     */
    public GMLBaseLinearRingParser(CoordinateBaseParser coordinateParser, GMLBasePointParser pointParser,
            GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser) {
        super(theGeometryFactory, theSrsParser);
        this.coordinateParser = coordinateParser;
        this.pointParser = pointParser;
        this.mixedLinearRingHandler = new MixedLinearRingGeometryHandler(theSrsParser);
    }

    /**
     * @param gmlGeometry
     * @return {@link org.locationtech.jts.geom.LinearRing}
     * @throws ParserException
     */
    @Override
    protected org.locationtech.jts.geom.LinearRing canParseGeometry(LinearRing gmlGeometry) throws ParserException {
        logger.trace("###########################Called : {}#canParseGeometry", this.getClass().getSimpleName());
        return this.mixedLinearRingHandler.buildGeometry(geometryFactory, gmlGeometry, pointParser, coordinateParser);
    }

    /**
     * @param propertyType
     * @return {@link org.locationtech.jts.geom.LinearRing}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LinearRing parseGeometry(LinearRingProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The LinearRing Property Type must not be null.");
        logger.trace("###########################Called : {}#parseGeometry", this.getClass().getSimpleName());
        if (propertyType.isSetLinearRing()) {
            return super.parseGeometry(propertyType.getLinearRing());
        }
        throw new ParserException("There is no GML LinearRing To Parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    protected LineString canParseGeometryAsGeoJson(LinearRing gmlGeometry) throws ParserException {
        logger.trace("###########################Called : {}#canParseGeometryAsGeoJson", this.getClass().getSimpleName());
        return this.mixedLinearRingHandler.buildGeometryAsGeoJson(gmlGeometry, pointParser, coordinateParser);
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.LineString}
     * @throws ParserException
     */
    @Override
    public LineString parseGeometryAsGeoJson(LinearRingProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The LinearRing Property Type must not be null.");
        logger.trace("###########################Called : {}#parseGeometryAsGeoJson", this.getClass().getSimpleName());
        if (propertyType.isSetLinearRing()) {
            return super.parseGeometryAsGeoJson(propertyType.getLinearRing());
        }
        throw new ParserException("There is no GML LinearRing To Parse.");
    }
}