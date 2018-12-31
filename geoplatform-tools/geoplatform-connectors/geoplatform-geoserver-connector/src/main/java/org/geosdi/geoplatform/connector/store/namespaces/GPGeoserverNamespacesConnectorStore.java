package org.geosdi.geoplatform.connector.store.namespaces;

import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.store.workspace.GPGeoserverWorkspacesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverNamespacesConnectorStore extends GPGeoserverWorkspacesConnectorStore {

    /**
     * @return {@link GPGeoserverNamespacesRequest}
     */
    GPGeoserverNamespacesRequest createNamespacesRequest();

    /**
     * @return {@link GPGeoserverNamespaceRequest}
     */
    GPGeoserverNamespaceRequest createNamespaceRequest();
}
