package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.MULTIPOLYGON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPolygonAdapter extends JTSBaseGeometryCollectionAdapter<MultiPolygon, com.vividsolutions.jts.geom.MultiPolygon> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSMultiPolygonAdapter(MultiPolygon theLocationtechGeometry, com.vividsolutions.jts.geom.MultiPolygon theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @param n
     * @return {@link GPJTSGeometryAdapter}
     */
    @Override
    public GPJTSGeometryAdapter getGeometryN(int n) {
        return ((this.locationtechGeometry != null) ? JTSPolygonAdapter.adapt((Polygon) this.locationtechGeometry.getGeometryN(n))
                : JTSPolygonAdapter.adapt((com.vividsolutions.jts.geom.Polygon) this.vividisolutionsGeometry.getGeometryN(n)));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return MULTIPOLYGON.getType();
    }

    /**
     * @param theMultiPolygon
     * @return {@link JTSMultiPolygonAdapter}
     */
    protected static JTSMultiPolygonAdapter adapt(@Nonnull(when = NEVER) MultiPolygon theMultiPolygon) {
        checkArgument(theMultiPolygon != null, "The Parameter multiPolygon must not be null.");
        return new JTSMultiPolygonAdapter(theMultiPolygon, null);
    }

    /**
     * @param theMultiPolygon
     * @return {@link JTSMultiPolygonAdapter}
     */
    protected static JTSMultiPolygonAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.MultiPolygon theMultiPolygon) {
        checkArgument(theMultiPolygon != null, "The Parameter multiPolygon must not be null.");
        return new JTSMultiPolygonAdapter(null, theMultiPolygon);
    }
}