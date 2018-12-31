package org.geosdi.geoplatform.connector.geoserver.datastores;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GPGeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GPGeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.geoserver.styles.GPGeoserverStylesConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverDatastoresConnector extends GPGeoserverStylesConnector implements IGPGeoserverDatastoresConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverDatastoresConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverDatastoresConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverDatastoresConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverDatastoresConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverDatastoresConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GeoserverLoadDatastoresRequest}
     */
    @Override
    public GeoserverLoadDatastoresRequest loadDatastoresRequest() {
        switch (version) {
            case V214x:
                return new GPGeoserverLoadDatastoresRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.14.x");
        }
    }

    /**
     * @return {@link GeoserverLoadDatastoreRequest}
     */
    @Override
    public GeoserverLoadDatastoreRequest loadDatastoreRequest() {
        switch (version) {
            case V214x:
                return new GPGeoserverLoadDatastoreRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.14.x");
        }
    }
}