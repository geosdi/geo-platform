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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.builder;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.AbstractRing;
import org.geosdi.geoplatform.gml.api.AbstractRingProperty;
import org.geosdi.geoplatform.gml.api.Polygon;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.LinearRing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBasePolygonBuilder implements PolygonBuilder {

    private static final Logger logger = LoggerFactory.getLogger(GMLBasePolygonBuilder.class);
    //
    private final GMLBaseLinearRingParser linearRingParser;

    /**
     * @param theLinearRingParser
     */
    public GMLBasePolygonBuilder(GMLBaseLinearRingParser theLinearRingParser) {
        this.linearRingParser = theLinearRingParser;
    }

    /**
     * @param polygon
     * @return {@link LinearRing}
     * @throws ParserException
     */
    @Override
    public LinearRing buildExteriorPolygon(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#buildExteriorPolygon", this.getClass().getSimpleName());
        return polygon.isSetExterior() ? canBuildExteriorPolygon(polygon) : null;
    }

    /**
     * @param polygon
     * @return {@link LinearRing[]}
     * @throws ParserException
     */
    @Override
    public LinearRing[] buildInteriorPolygon(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#buildInteriorPolygon", this.getClass().getSimpleName());
        return polygon.isSetInterior() ? canBuildInteriorPolygon(polygon) : null;
    }

    /**
     * @param polygon
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString buildExteriorPolygonAsGeoJson(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#buildExteriorPolygonAsGeoJson", this.getClass().getSimpleName());
        return polygon.isSetExterior() ? canBuildExteriorPolygonAsGeoJson(polygon) : null;
    }

    /**
     * @param polygon
     * @return {@link LineString[]}
     * @throws ParserException
     */
    @Override
    public LineString[] buildInteriorPolygonAsGeoJson(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#buildInteriorPolygonAsGeoJson", this.getClass().getSimpleName());
        return polygon.isSetInterior() ? canBuildInteriorPolygonAsGeoJson(polygon) : null;
    }

    /**
     * @param polygon
     * @return {@link LinearRing}
     * @throws ParserException
     */
    protected LinearRing canBuildExteriorPolygon(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#canBuildExteriorPolygon", this.getClass().getSimpleName());
        AbstractRingProperty ringProperty = polygon.getExteriorValue();
        AbstractRing ring = ringProperty.getAbstractRing();
        if ((polygon.isSetSrsDimension()) && !(ring.isSetSrsDimension()))
            ring.setSrsDimension(polygon.getSrsDimension());
        return ((ring != null) && (ring instanceof org.geosdi.geoplatform.gml.api.LinearRing))
                ? linearRingParser.parseGeometry((org.geosdi.geoplatform.gml.api.LinearRing) ring) : null;
    }

    /**
     * @param polygon
     * @return {@link LinearRing[]}
     * @throws ParserException
     */
    protected LinearRing[] canBuildInteriorPolygon(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#canBuildInteriorPolygon", this.getClass().getSimpleName());
        List<LinearRing> interiorElements = new ArrayList<>(polygon.getInteriorValues().size());
        for (AbstractRingProperty ringProperty : polygon.getInteriorValues()) {
            AbstractRing ring = ringProperty.getAbstractRing();
            if ((polygon.isSetSrsDimension()) && !(ring.isSetSrsDimension()))
                ring.setSrsDimension(polygon.getSrsDimension());
            if ((ring != null) && (ring instanceof LinearRing)) {
                interiorElements.add(this.linearRingParser.parseGeometry((org.geosdi.geoplatform.gml.api.LinearRing) ring));
            }
        }
        return interiorElements.toArray(new LinearRing[interiorElements.size()]);
    }

    /**
     * @param polygon
     * @return {@link LineString}
     * @throws ParserException
     */
    protected LineString canBuildExteriorPolygonAsGeoJson(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#canBuildExteriorPolygonAsGeoJson", this.getClass().getSimpleName());
        AbstractRingProperty ringProperty = polygon.getExteriorValue();
        AbstractRing ring = ringProperty.getAbstractRing();
        if ((polygon.isSetSrsDimension()) && !(ring.isSetSrsDimension()))
            ring.setSrsDimension(polygon.getSrsDimension());
        return ((ring != null) && (ring instanceof org.geosdi.geoplatform.gml.api.LinearRing))
                ? linearRingParser.parseGeometryAsGeoJson((org.geosdi.geoplatform.gml.api.LinearRing) ring) : null;
    }

    /**
     * @param polygon
     * @return {@link LineString[]}
     * @throws ParserException
     */
    protected LineString[] canBuildInteriorPolygonAsGeoJson(Polygon polygon) throws ParserException {
        logger.trace("###########################Called : {}#canBuildInteriorPolygonAsGeoJson", this.getClass().getSimpleName());
        List<LineString> interiorElements = new ArrayList<>(polygon.getInteriorValues().size());
        logger.trace("#####################INTERIOR_RING_VALUES : {}\n", polygon.getInteriorValues().size());
        for (AbstractRingProperty ringProperty : polygon.getInteriorValues()) {
            AbstractRing ring = ringProperty.getAbstractRing();
            if ((polygon.isSetSrsDimension()) && !(ring.isSetSrsDimension()))
                ring.setSrsDimension(polygon.getSrsDimension());
            logger.trace("######################RING_IS_NULL : {} - isLinearRing : {}", (ring != null), (ring instanceof org.geosdi.geoplatform.gml.api.LinearRing));
            if ((ring != null) && (ring instanceof org.geosdi.geoplatform.gml.api.LinearRing)) {
                interiorElements.add(this.linearRingParser.parseGeometryAsGeoJson((org.geosdi.geoplatform.gml.api.LinearRing) ring));
            }
        }
        return interiorElements.stream().toArray(s -> new LineString[s]);
    }
}