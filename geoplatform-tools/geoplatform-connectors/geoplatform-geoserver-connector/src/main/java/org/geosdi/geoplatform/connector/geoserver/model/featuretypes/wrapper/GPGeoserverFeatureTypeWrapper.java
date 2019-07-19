package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverFeatureTypeWrapper<V> implements IGPGeoserverFeatureTypeWrapper<V> {

    private final V featureType;

    /**
     * @param theFeatureType
     */
    GPGeoserverFeatureTypeWrapper(@Nonnull(when = NEVER) V theFeatureType) {
        checkArgument(theFeatureType != null, "The Parameter featureType must not be null.");
        this.featureType = theFeatureType;
    }

    /**
     * @return {@link V}
     */
    @Override
    public V toFeatureType() {
        return this.featureType;
    }
}