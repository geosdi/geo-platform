package org.geosdi.geoplatform.connector.geoserver.namespaces;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.worksapce.GPGeoserverWorkspacesConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverNamespacesConnector extends GPGeoserverWorkspacesConnector implements IGPGeoserverNamespacesConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverNamespacesConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverNamespacesConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverNamespacesConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverNamespacesConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverNamespacesConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GPGeoserverNamespacesRequest}
     */
    @Override
    public GPGeoserverNamespacesRequest createNamespacesRequest() {
        switch (version) {
            case V212x:
                return new GPGeoserverNamespacesRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.12.x");
        }
    }

    /**
     * @return {@link GPGeoserverNamespaceRequest}
     */
    @Override
    public GPGeoserverNamespaceRequest createNamespaceRequest() {
        switch (version) {
            case V212x:
                return new GPGeoserverNamespaceRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.12.x");
        }
    }
}