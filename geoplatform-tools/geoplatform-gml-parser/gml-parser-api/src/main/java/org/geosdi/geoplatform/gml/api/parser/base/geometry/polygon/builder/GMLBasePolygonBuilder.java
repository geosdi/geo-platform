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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.builder;

import com.vividsolutions.jts.geom.LinearRing;
import org.geosdi.geoplatform.gml.api.AbstractRing;
import org.geosdi.geoplatform.gml.api.AbstractRingProperty;
import org.geosdi.geoplatform.gml.api.Polygon;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBasePolygonBuilder implements PolygonBuilder {

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
        return polygon.isSetExterior() ? canBuildExteriorPolygon(polygon) : null;
    }

    /**
     * @param polygon
     * @return {@link LinearRing[]}
     * @throws ParserException
     */
    @Override
    public LinearRing[] buildInteriorPolygon(Polygon polygon) throws ParserException {
        return polygon.isSetInterior() ? canBuildInteriorPolygon(polygon) : null;
    }

    /**
     * @param polygon
     * @return {@link LinearRing}
     * @throws ParserException
     */
    protected LinearRing canBuildExteriorPolygon(Polygon polygon) throws ParserException {
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
}