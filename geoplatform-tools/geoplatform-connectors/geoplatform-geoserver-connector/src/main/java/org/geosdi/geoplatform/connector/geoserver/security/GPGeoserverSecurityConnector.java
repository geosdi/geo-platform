package org.geosdi.geoplatform.connector.geoserver.security;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.featuretypes.GPGeoserverFeatureTypesConnector;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverGetMasterPasswordRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverUpdateCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GPGeoserverGetCatalogRequest;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverSecurityConnector extends GPGeoserverFeatureTypesConnector implements IGPGeoserverSecurityConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverSecurityConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverSecurityConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverSecurityConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverSecurityConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverSecurityConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GPGeoserverGetMasterPasswordRequest}
     */
    @Override
    public GPGeoserverGetMasterPasswordRequest loadMasterPasswordRequest() {
        switch (version) {
            case V215x:
                return new GPGeoserverGetMasterPasswordRequest(this, this.emptyJacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GPGeoserverGetCatalogRequest}
     */
    @Override
    public GPGeoserverGetCatalogRequest loadCatalogRequest() {
        switch (version) {
            case V215x:
                return new GPGeoserverGetCatalogRequest(this, this.emptyJacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GPGeoserverUpdateCatalogRequest}
     */
    @Override
    public GPGeoserverUpdateCatalogRequest updateCatalogRequest() {
        switch (version) {
            case V215x:
                return new GPGeoserverUpdateCatalogRequest(this, this.emptyJacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }
}