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

import org.geosdi.geoplatform.support.jackson.jts.adapter.coordinate.GPJTSCoordinateAdapter;
import org.locationtech.jts.geom.*;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class JTSGeometryAdapter<LOCATIONTECH extends Geometry, VIVIDISOLUTIONS extends com.vividsolutions.jts.geom.Geometry>
        implements GPJTSGeometryAdapter {

    protected final LOCATIONTECH locationtechGeometry;
    protected final VIVIDISOLUTIONS vividisolutionsGeometry;

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSGeometryAdapter(LOCATIONTECH theLocationtechGeometry, VIVIDISOLUTIONS theVividisolutionsGeometry) {
        this.locationtechGeometry = theLocationtechGeometry;
        this.vividisolutionsGeometry = theVividisolutionsGeometry;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public int getSRID() {
        return ((this.locationtechGeometry != null) ? this.locationtechGeometry.getSRID() : this.vividisolutionsGeometry.getSRID());
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public int getNumGeometries() {
        return ((this.locationtechGeometry != null) ? this.locationtechGeometry.getNumGeometries() : this.vividisolutionsGeometry.getNumGeometries());
    }

    /**
     * @param n
     * @return {@link GPJTSGeometryAdapter}
     */
    @Override
    public GPJTSGeometryAdapter getGeometryN(int n) {
        return this;
    }

    /**
     * @return {@link GPJTSCoordinateAdapter}
     */
    @Override
    public GPJTSCoordinateAdapter getCoordinate() {
        return ((this.locationtechGeometry != null) ? GPJTSCoordinateAdapter.adapt(this.locationtechGeometry.getCoordinate())
                : GPJTSCoordinateAdapter.adapt(this.vividisolutionsGeometry.getCoordinate()));
    }

    /**
     * @return {@link GPJTSCoordinateAdapter[]}
     */
    @Override
    public GPJTSCoordinateAdapter[] getCoordinates() {
        return ((this.locationtechGeometry != null) ? GPJTSCoordinateAdapter.adapt(this.locationtechGeometry.getCoordinates())
                : GPJTSCoordinateAdapter.adapt(this.vividisolutionsGeometry.getCoordinates()));
    }

    /**
     * @param theLocationtechGeometry
     * @return {@link GPJTSGeometryAdapter}
     */
    public static GPJTSGeometryAdapter adapt(@Nonnull(when = NEVER) Geometry theLocationtechGeometry) {
        checkArgument(theLocationtechGeometry != null, "The parameter theLocationtechGeometry must not be null");
        JTSGeometryType jtsGeometryType = JTSGeometryType.forType(theLocationtechGeometry.getGeometryType());
        checkArgument(jtsGeometryType != null, "Not exists JTSGeometryType for : " + theLocationtechGeometry.getGeometryType());
        switch (jtsGeometryType) {
            case POINT:
                return JTSPointAdapter.adapt((Point) theLocationtechGeometry);
            case LINESTRING:
                return JTSLineStringAdapter.adapt((LineString) theLocationtechGeometry);
            case LINEARRING:
                return JTSLinearRingAdapter.adapt((LinearRing) theLocationtechGeometry);
            case POLYGON:
                return JTSPolygonAdapter.adapt((Polygon) theLocationtechGeometry);
            case GEOMETRYCOLLECTION:
                return JTSGeometryCollectionAdapter.adapt((GeometryCollection) theLocationtechGeometry);
            case MULTIPOINT:
                return JTSMultiPointAdapter.adapt((MultiPoint) theLocationtechGeometry);
            case MULTILINESTRING:
                return JTSMultiLinestringAdapter.adapt((MultiLineString) theLocationtechGeometry);
            case MULTIPOLYGON:
                return JTSMultiPolygonAdapter.adapt((MultiPolygon) theLocationtechGeometry);
            default:
                throw new IllegalStateException("There is no valid JTSGeometryAdapter for Geometry : " + jtsGeometryType);
        }
    }

    /**
     * @param theVividisolutionsGeometry
     * @return {@link GPJTSGeometryAdapter}
     */
    public static GPJTSGeometryAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.Geometry theVividisolutionsGeometry) {
        checkArgument(theVividisolutionsGeometry != null, "The parameter theVividisolutionsGeometry must not be null");
        JTSGeometryType jtsGeometryType = JTSGeometryType.forType(theVividisolutionsGeometry.getGeometryType());
        checkArgument(jtsGeometryType != null, "Not exists JTSGeometryType for : " + theVividisolutionsGeometry.getGeometryType());
        switch (jtsGeometryType) {
            case POINT:
                return JTSPointAdapter.adapt((com.vividsolutions.jts.geom.Point) theVividisolutionsGeometry);
            case LINESTRING:
                return JTSLineStringAdapter.adapt((com.vividsolutions.jts.geom.LineString) theVividisolutionsGeometry);
            case LINEARRING:
                return JTSLinearRingAdapter.adapt((com.vividsolutions.jts.geom.LinearRing) theVividisolutionsGeometry);
            case POLYGON:
                return JTSPolygonAdapter.adapt((com.vividsolutions.jts.geom.Polygon) theVividisolutionsGeometry);
            case GEOMETRYCOLLECTION:
                return JTSGeometryCollectionAdapter.adapt((com.vividsolutions.jts.geom.GeometryCollection) theVividisolutionsGeometry);
            case MULTIPOINT:
                return JTSMultiPointAdapter.adapt((com.vividsolutions.jts.geom.MultiPoint) theVividisolutionsGeometry);
            case MULTILINESTRING:
                return JTSMultiLinestringAdapter.adapt((com.vividsolutions.jts.geom.MultiLineString) theVividisolutionsGeometry);
            case MULTIPOLYGON:
                return JTSMultiPolygonAdapter.adapt((com.vividsolutions.jts.geom.MultiPolygon) theVividisolutionsGeometry);
            default:
                throw new IllegalStateException("There is no valid JTSGeometryAdapter for Geometry : " + jtsGeometryType);
        }
    }

    /**
     * @return {@link String}
     */
    public abstract String getGeometryType();
}