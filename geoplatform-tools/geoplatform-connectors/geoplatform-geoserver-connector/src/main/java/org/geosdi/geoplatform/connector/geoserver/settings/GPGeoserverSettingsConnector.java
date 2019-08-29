package org.geosdi.geoplatform.connector.geoserver.settings;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadContactSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverUpdateGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GeoserverUpdateGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.security.GPGeoserverSecurityConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverSettingsConnector extends GPGeoserverSecurityConnector implements IGPGeoserverSettingsConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverSettingsConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverSettingsConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverSettingsConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverSettingsConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverSettingsConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GPGeoserverLoadGlobalSettingsRequest}
     */
    @Override
    public GPGeoserverLoadGlobalSettingsRequest loadGeoserverGlobalSettingRequest() {
        switch (version) {
            case V215x:
                return new GPGeoserverLoadGlobalSettingsRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GeoserverUpdateGlobalSettingsRequest}
     */
    @Override
    public GeoserverUpdateGlobalSettingsRequest updateGlobalSettingsRequest() {
        switch (version) {
            case V215x:
                return new GPGeoserverUpdateGlobalSettingsRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GPGeoserverLoadContactSettingsRequest}
     */
    @Override
    public GPGeoserverLoadContactSettingsRequest loadGeoserverContactSettingsRequest() {
        switch (version) {
            case V215x:
                return new GPGeoserverLoadContactSettingsRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }
}