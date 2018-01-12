package org.geosdi.geoplatform.connector.geoserver.namespaces;

import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.worksapce.IGPGeoserverWorkspacesConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverNamespacesConnector extends IGPGeoserverWorkspacesConnector {

    /**
     * @return {@link GPGeoserverNamespacesRequest}
     */
    GPGeoserverNamespacesRequest createNamespacesRequest();

    /**
     * @return {@link GPGeoserverNamespaceRequest}
     */
    GPGeoserverNamespaceRequest createNamespaceRequest();
}