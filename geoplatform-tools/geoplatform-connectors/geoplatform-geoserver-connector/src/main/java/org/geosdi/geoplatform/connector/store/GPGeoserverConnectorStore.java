package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverWorkspacesRequest;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorStore extends GPConnectorStore<GPGeoserverConnector> implements IGPGeoserverConnectorStore {

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverConnectorStore(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport,
            GeoserverVersion theVersion) {
        this(server, null, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverConnectorStore(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(new GPGeoserverConnector(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion));
    }

    /**
     * @return {@link GeoserverVersion}
     */
    @Override
    public GeoserverVersion getVersion() {
        return this.server.getVersion();
    }

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    @Override
    public GPGeoserverAboutVersionRequest createAboutVersionRequest() {
        return this.server.createAboutVersionRequest();
    }

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    @Override
    public GPGeoserverAboutStatusRequest createAboutStatusRequest() {
        return this.server.createAboutStatusRequest();
    }

    /**
     * @return {@link GPGeoserverWorkspacesRequest}
     */
    @Override
    public GPGeoserverWorkspacesRequest createWorkspacesRequest() {
        return this.server.createWorkspacesRequest();
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

    /**
     * @return {@link GPGeoserverLayersRequest}
     */
    @Override
    public GPGeoserverLayersRequest createLayersRequest() {
        return this.server.createLayersRequest();
    }
}