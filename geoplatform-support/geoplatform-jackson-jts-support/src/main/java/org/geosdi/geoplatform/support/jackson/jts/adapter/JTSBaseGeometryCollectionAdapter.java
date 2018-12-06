package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.*;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.GEOMETRYCOLLECTION;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class JTSBaseGeometryCollectionAdapter<LOCATIONTECH extends GeometryCollection, VIVIDISOLUTIONS extends com.vividsolutions.jts.geom.GeometryCollection>
        extends JTSGeometryAdapter<LOCATIONTECH, VIVIDISOLUTIONS> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSBaseGeometryCollectionAdapter(LOCATIONTECH theLocationtechGeometry, VIVIDISOLUTIONS theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return GEOMETRYCOLLECTION.getType();
    }

    /**
     * @param n
     * @return {@link GPJTSGeometryAdapter}
     */
    @Override
    public GPJTSGeometryAdapter getGeometryN(int n) {
        return ((this.locationtechGeometry != null) ? this.internalAdapt(this.locationtechGeometry.getGeometryN(n))
                : this.internalAdapt(this.vividisolutionsGeometry.getGeometryN(n)));
    }

    /**
     * @param theLocationtechGeometry
     * @return {@link GPJTSGeometryAdapter}
     */
    protected GPJTSGeometryAdapter internalAdapt(@Nonnull(when = NEVER) Geometry theLocationtechGeometry) {
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
    protected GPJTSGeometryAdapter internalAdapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.Geometry theVividisolutionsGeometry) {
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
}