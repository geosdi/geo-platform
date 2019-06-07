package org.geosdi.geoplatform.services.request;

import org.geojson.Feature;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class WMSFeatureStoreResponseErrorStrategy extends WMSGetFeatureInfoResponseErrorStrategy<GPWMSFeatureErrorStore> {

    WMSFeatureStoreResponseErrorStrategy() {
        super(new GPWMSFeatureErrorStore());
    }

    /**
     * @param feature
     */
    @Override
    protected void addFeatureError(Feature feature) throws Exception {
        this.value.addFeature(feature);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    protected boolean hasErrors() {
        return !this.value.getStore().isEmpty();
    }
}