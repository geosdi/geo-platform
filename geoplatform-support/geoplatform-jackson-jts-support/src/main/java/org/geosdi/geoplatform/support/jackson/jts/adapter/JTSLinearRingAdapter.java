package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.LinearRing;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.LINEARRING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSLinearRingAdapter extends AbstractJTSLineStringAdapter<LinearRing, com.vividsolutions.jts.geom.LinearRing> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSLinearRingAdapter(LinearRing theLocationtechGeometry, com.vividsolutions.jts.geom.LinearRing theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return LINEARRING.getType();
    }

    /**
     * @param theLinearRing
     * @return {@link JTSLinearRingAdapter}
     */
    protected static JTSLinearRingAdapter adapt(@Nonnull(when = NEVER) LinearRing theLinearRing) {
        checkArgument(theLinearRing != null, "The Parameter linearRing must not be null.");
        return new JTSLinearRingAdapter(theLinearRing, null);
    }

    /**
     * @param theLinearRing
     * @return {@link JTSLinearRingAdapter}
     */
    protected static JTSLinearRingAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.LinearRing theLinearRing) {
        checkArgument(theLinearRing != null, "The Parameter linearRing must not be null.");
        return new JTSLinearRingAdapter(null, theLinearRing);
    }
}