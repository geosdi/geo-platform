package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper;

import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverEmptyResponse;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverEmptyFeatureTypeWrapper extends GPGeoserverEmptyResponse<GPGeoserverFeatureTypeWrapper> {

    /**
     * @param theEmptyFeatureType
     * @return {@link}
     */
    static GPGeoserverEmptyFeatureTypeWrapper of(@Nonnull(when = NEVER) GPGeoserverFeatureTypeWrapper theEmptyFeatureType) {
        return new GPGeoserverEmptyFeatureTypeWrapper(theEmptyFeatureType);
    }
}