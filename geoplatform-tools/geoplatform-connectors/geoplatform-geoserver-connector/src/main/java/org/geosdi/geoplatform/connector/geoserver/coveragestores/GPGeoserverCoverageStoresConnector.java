package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.datastores.GPGeoserverDatastoresConnector;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.*;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverCoverageStoresConnector extends GPGeoserverDatastoresConnector implements IGPGeoserverCoverageStoresConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverCoverageStoresConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverCoverageStoresConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverCoverageStoresConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverCoverageStoresConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverCoverageStoresConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GeoserverLoadCoverageStoresRequest}
     */
    @Override
    public GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest() {
        switch (version) {
            case V215x:
            case V216x:
                return new GPGeoserverLoadCoverageStoresRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    @Override
    public GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest() {
        switch (version) {
            case V215x:
            case V216x:
                return new GPGeoserverLoadCoverageStoreRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreRequest createCoverageStoreRequest() {
        switch (version) {
            case V215x:
            case V216x:
                return new GPGeoserverCreateCoverageStoreRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    @Override
    public GeoserverDeleteCoverageStoreRequest deleteCoverageStoreRequest() {
        switch (version) {
            case V215x:
            case V216x:
                return new GPGeoserverDeleteCoverageStoreRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest updateCoverageStoreRequest() {
        switch (version) {
            case V215x:
            case V216x:
                return new GPGeoserverUpdateCoverageStoreRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }
}