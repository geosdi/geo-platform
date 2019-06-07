package org.geosdi.geoplatform.connector.reader.stax;

import com.fasterxml.jackson.annotation.JsonCreator;

import static org.geosdi.geoplatform.connector.reader.stax.GPGetFeatureGeoJsonStaxReader.FEATURE_NAME_KEY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSFeatureStore extends WMSFeatureStore<String> {

    private static final long serialVersionUID = 8043547109663983346L;

    GPWMSFeatureStore() {
        super(FEATURE_NAME_KEY);
    }

    /**
     * @return {@link GPWMSFeatureStore}
     */
    @JsonCreator
    protected static GPWMSFeatureStore creator() {
        return new GPWMSFeatureStore();
    }
}