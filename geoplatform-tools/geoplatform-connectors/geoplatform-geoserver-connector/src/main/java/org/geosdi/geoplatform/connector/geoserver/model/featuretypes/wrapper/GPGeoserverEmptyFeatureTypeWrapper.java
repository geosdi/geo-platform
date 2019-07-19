package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverEmptyFeatureTypeWrapper implements IGPGeoserverEmptyFeatureTypeWrapper {

    private final GPGeoserverFeatureTypeWrapper emptyFeatureType;

    /**
     * @param theEmptyFeatureType
     */
    GPGeoserverEmptyFeatureTypeWrapper(@Nonnull(when = NEVER) GPGeoserverFeatureTypeWrapper theEmptyFeatureType) {
        checkArgument(theEmptyFeatureType != null, "The Parameter emptyFeatureType must not be null.");
        this.emptyFeatureType = theEmptyFeatureType;
    }

    /**
     * @return {@link GPGeoserverFeatureTypeWrapper}
     */
    @Override
    public GPGeoserverFeatureTypeWrapper toModel() {
        return this.emptyFeatureType;
    }
}