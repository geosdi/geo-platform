package org.geosdi.geoplatform.connector.store.namespaces;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.store.workspace.GeoserverWorkspacesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverNamespacesConnectorStore extends GeoserverWorkspacesConnectorStore implements GPGeoserverNamespacesConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverNamespacesConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverNamespacesRequest}
     */
    @Override
    public GPGeoserverNamespacesRequest createNamespacesRequest() {
        return this.server.createNamespacesRequest();
    }

    /**
     * @return {@link GPGeoserverNamespaceRequest}
     */
    @Override
    public GPGeoserverNamespaceRequest createNamespaceRequest() {
        return this.server.createNamespaceRequest();
    }
}