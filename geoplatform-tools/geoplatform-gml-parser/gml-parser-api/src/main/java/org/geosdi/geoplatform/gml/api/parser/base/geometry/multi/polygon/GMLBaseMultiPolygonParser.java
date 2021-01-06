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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.polygon;

import org.geosdi.geoplatform.gml.api.MultiPolygon;
import org.geosdi.geoplatform.gml.api.MultiPolygonProperty;
import org.geosdi.geoplatform.gml.api.PolygonProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiPolygonParser extends AbstractGMLBaseParser<MultiPolygon, MultiPolygonProperty, org.locationtech.jts.geom.MultiPolygon, org.geojson.MultiPolygon> {

    private final GMLBasePolygonParser polygonParser;

    /**
     * @param theGeometryFactory
     * @param theSrsParser
     * @param thePolygonParser
     */
    public GMLBaseMultiPolygonParser(@Nonnull(when = NEVER) GeometryFactory theGeometryFactory, @Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSrsParser,
            @Nonnull(when = NEVER) GMLBasePolygonParser thePolygonParser) {
        super(theGeometryFactory, theSrsParser);
        checkArgument(thePolygonParser != null, "The Parameter polygonParser must not be null.");
        this.polygonParser = thePolygonParser;
    }

    /**
     * @param gmlGeometry
     * @return {@link org.locationtech.jts.geom.MultiPolygon}
     * @throws ParserException
     */
    @Override
    protected org.locationtech.jts.geom.MultiPolygon canParseGeometry(MultiPolygon gmlGeometry) throws ParserException {
        checkArgument(gmlGeometry.isSetPolygonMember(), "The Polygon Member Property must not be null.");
        List<Polygon> polygons = new ArrayList<>(gmlGeometry.getPolygonMember().size());
        for (PolygonProperty polygonProperty : gmlGeometry.getPolygonMember()) {
            if ((gmlGeometry.isSetSrsDimension()) && (polygonProperty.isSetPolygon())
                    && !(polygonProperty.getPolygon().isSetSrsDimension()))
                polygonProperty.getPolygon().setSrsDimension(gmlGeometry.getSrsDimension());
            polygons.add(polygonParser.parseGeometry(polygonProperty));
        }
        org.locationtech.jts.geom.MultiPolygon multiPolygon = this.geometryFactory.createMultiPolygon(polygons.toArray(new Polygon[polygons.size()]));
        this.srsParser.parseSRS(gmlGeometry, multiPolygon);
        return multiPolygon;
    }

    /**
     * @param propertyType
     * @return {@link org.locationtech.jts.geom.MultiPolygon}
     * }
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.MultiPolygon parseGeometry(MultiPolygonProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The MultiPolygonProperty Type must not be null.");
        if (propertyType.isSetMultiPolygon()) {
            return super.parseGeometry(propertyType.getMultiPolygon());
        }
        throw new ParserException("There is no GML MultiPolygon to Parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.MultiPolygon}
     * @throws ParserException
     */
    @Override
    protected org.geojson.MultiPolygon canParseGeometryAsGeoJson(MultiPolygon gmlGeometry) throws ParserException {
        checkArgument(gmlGeometry.isSetPolygonMember(), "The Polygon Member Property must not be null.");
        org.geojson.MultiPolygon multiPolygon = new org.geojson.MultiPolygon();
        for (PolygonProperty polygonProperty : gmlGeometry.getPolygonMember()) {
            if ((gmlGeometry.isSetSrsDimension()) && (polygonProperty.isSetPolygon())
                    && !(polygonProperty.getPolygon().isSetSrsDimension()))
                polygonProperty.getPolygon().setSrsDimension(gmlGeometry.getSrsDimension());
            multiPolygon.add(polygonParser.parseGeometryAsGeoJson(polygonProperty));
        }
        multiPolygon.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return multiPolygon;
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.MultiPolygon}
     * @throws ParserException
     */
    @Override
    public org.geojson.MultiPolygon parseGeometryAsGeoJson(MultiPolygonProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The MultiPolygonProperty Type must not be null.");
        if (propertyType.isSetMultiPolygon()) {
            return super.parseGeometryAsGeoJson(propertyType.getMultiPolygon());
        }
        throw new ParserException("There is no GML MultiPolygon to Parse.");
    }
}