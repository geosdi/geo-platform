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
package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.POLYGON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPolygonAdapter extends JTSGeometryAdapter<Polygon, com.vividsolutions.jts.geom.Polygon> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSPolygonAdapter(Polygon theLocationtechGeometry, com.vividsolutions.jts.geom.Polygon theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return POLYGON.getType();
    }

    /**
     * @return {@link JTSLineStringAdapter}
     */
    public JTSLineStringAdapter getExteriorRing() {
        return ((this.locationtechGeometry != null)
                ? JTSLineStringAdapter.adapt(this.locationtechGeometry.getExteriorRing())
                : JTSLineStringAdapter.adapt(this.vividisolutionsGeometry.getExteriorRing()));
    }

    /**
     * @return {@link Integer}
     */
    public int getNumInteriorRing() {
        return ((this.locationtechGeometry != null)
                ? this.locationtechGeometry.getNumInteriorRing() : this.vividisolutionsGeometry.getNumInteriorRing());
    }

    /**
     * @param n
     * @return {@link JTSLineStringAdapter}
     */
    public JTSLineStringAdapter getInteriorRingN(int n) {
        return ((this.locationtechGeometry != null)
                ? JTSLineStringAdapter.adapt(this.locationtechGeometry.getInteriorRingN(n))
                : JTSLineStringAdapter.adapt(this.vividisolutionsGeometry.getInteriorRingN(n)));
    }

    /**
     * @param thePolygon
     * @return {@link JTSPolygonAdapter}
     */
    protected static JTSPolygonAdapter adapt(@Nonnull(when = When.NEVER) Polygon thePolygon) {
        checkArgument(thePolygon != null, "The Parameter polygon must not be null.");
        return new JTSPolygonAdapter(thePolygon, null);
    }

    /**
     * @param thePolygon
     * @return {@link JTSPolygonAdapter}
     */
    protected static JTSPolygonAdapter adapt(@Nonnull(when = When.NEVER) com.vividsolutions.jts.geom.Polygon thePolygon) {
        checkArgument(thePolygon != null, "The Parameter polygon must not be null.");
        return new JTSPolygonAdapter(null, thePolygon);
    }
}