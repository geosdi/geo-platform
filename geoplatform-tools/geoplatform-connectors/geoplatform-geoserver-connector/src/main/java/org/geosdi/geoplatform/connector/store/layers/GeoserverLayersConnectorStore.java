package org.geosdi.geoplatform.connector.store.layers;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.store.namespaces.GeoserverNamespacesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverLayersConnectorStore extends GeoserverNamespacesConnectorStore implements GPGeoserverLayersConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverLayersConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverLayersRequest}
     */
    @Override
    public GPGeoserverLayersRequest loadLayersRequest() {
        return this.server.createLayersRequest();
    }

    /**
     * @return {@link GPGeoserverLayerRequest}
     */
    @Override
    public GPGeoserverLayerRequest loadLayerRequest() {
        return this.server.createLayerRequest();
    }
}