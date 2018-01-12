package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import org.geosdi.geoplatform.connector.geoserver.request.model.GPGeoserverEmptyResponse;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverEmptyLayers implements GPGeoserverEmptyResponse<GPGeoserverLayers> {

    /**
     * @return {@link GPGeoserverLayers}
     */
    @Override
    public GPGeoserverLayers toModel() {
        return new GPGeoserverLayers();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return toModel().toString();
    }
}