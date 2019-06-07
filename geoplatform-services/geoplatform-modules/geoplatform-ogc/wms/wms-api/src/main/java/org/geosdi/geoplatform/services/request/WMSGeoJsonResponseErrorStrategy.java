package org.geosdi.geoplatform.services.request;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class WMSGeoJsonResponseErrorStrategy extends WMSGetFeatureInfoResponseErrorStrategy<FeatureCollection> {

    WMSGeoJsonResponseErrorStrategy() {
        super(new FeatureCollection());
    }

    /**
     * @param feature
     */
    @Override
    protected void addFeatureError(Feature feature) throws Exception {
        this.value.add(feature);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean hasErrors() {
        return !this.value.getFeatures().isEmpty();
    }
}