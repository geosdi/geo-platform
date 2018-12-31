package org.geosdi.geoplatform.connector.store.layers;

import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.store.namespaces.GPGeoserverNamespacesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverLayersConnectorStore extends GPGeoserverNamespacesConnectorStore {

    /**
     * @return {@link GPGeoserverLayersRequest}
     */
    GPGeoserverLayersRequest loadLayersRequest();

    /**
     * @return {@link GPGeoserverLayerRequest}
     */
    GPGeoserverLayerRequest loadLayerRequest();
}
