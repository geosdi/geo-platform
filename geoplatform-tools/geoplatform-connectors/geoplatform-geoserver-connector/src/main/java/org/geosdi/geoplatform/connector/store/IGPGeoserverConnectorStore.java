package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverWorkspacesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverConnectorStore {

    /**
     * @return {@link GeoserverVersion}
     */
    GeoserverVersion getVersion();

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    GPGeoserverAboutVersionRequest createAboutVersionRequest();

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    GPGeoserverAboutStatusRequest createAboutStatusRequest();

    /**
     * @return {@link GPGeoserverWorkspacesRequest}
     */
    GPGeoserverWorkspacesRequest createWorkspacesRequest();

    /**
     * @return {@link GPGeoserverNamespacesRequest}
     */
    GPGeoserverNamespacesRequest createNamespacesRequest();

    /**
     * @return {@link GPGeoserverNamespaceRequest}
     */
    GPGeoserverNamespaceRequest createNamespaceRequest();

    /**
     * @return {@link GPGeoserverLayersRequest}
     */
    GPGeoserverLayersRequest createLayersRequest();
}