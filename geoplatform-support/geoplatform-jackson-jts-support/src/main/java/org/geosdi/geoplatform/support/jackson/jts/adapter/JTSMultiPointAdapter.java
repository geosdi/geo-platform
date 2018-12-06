package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.MULTIPOINT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPointAdapter extends JTSBaseGeometryCollectionAdapter<MultiPoint, com.vividsolutions.jts.geom.MultiPoint> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSMultiPointAdapter(MultiPoint theLocationtechGeometry, com.vividsolutions.jts.geom.MultiPoint theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return MULTIPOINT.getType();
    }

    /**
     * @param n
     * @return {@link GPJTSGeometryAdapter}
     */
    @Override
    public GPJTSGeometryAdapter getGeometryN(int n) {
        return ((this.locationtechGeometry != null) ? JTSPointAdapter.adapt((Point) this.locationtechGeometry.getGeometryN(n))
                : JTSPointAdapter.adapt((com.vividsolutions.jts.geom.Point) this.vividisolutionsGeometry.getGeometryN(n)));
    }

    /**
     * @param theMultiPoint
     * @return {@link JTSMultiPointAdapter}
     */
    protected static JTSMultiPointAdapter adapt(@Nonnull(when = NEVER) MultiPoint theMultiPoint) {
        checkArgument(theMultiPoint != null, "The Parameter multiPoint must not be null.");
        return new JTSMultiPointAdapter(theMultiPoint, null);
    }

    /**
     * @param theMultiPoint
     * @return {@link JTSMultiPointAdapter}
     */
    protected static JTSMultiPointAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.MultiPoint theMultiPoint) {
        checkArgument(theMultiPoint != null, "The Parameter multiPoint must not be null.");
        return new JTSMultiPointAdapter(null, theMultiPoint);
    }
}