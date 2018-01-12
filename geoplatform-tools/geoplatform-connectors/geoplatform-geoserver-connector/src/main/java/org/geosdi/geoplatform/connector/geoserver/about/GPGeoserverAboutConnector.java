package org.geosdi.geoplatform.connector.geoserver.about;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.server.GPAbstractServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.GeoserverVersion.fromString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverAboutConnector extends GPAbstractServerConnector implements IGPGeoserverAboutConnector {

    protected final GeoserverVersion version;
    protected final JacksonSupport jacksonSupport;

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    public GPGeoserverAboutConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        this(urlServer, null, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param version
     */
    public GPGeoserverAboutConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport,
            String version) {
        this(analyzesServerURL(urlServer), securityConnector, theJacksonSupport, fromString(version));
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    public GPGeoserverAboutConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, theJacksonSupport, fromString(version));
    }

    /**
     * @param server
     * @param securityConnector
     * @param theVersion
     */
    public GPGeoserverAboutConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport,
            GeoserverVersion theVersion) {
        super(analyzesServerURL(server), securityConnector);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.version = theVersion;
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    public GPGeoserverAboutConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(analyzesServerURL(server), securityConnector, pooledConnectorConfig);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.version = theVersion;
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    @Override
    public GPGeoserverAboutVersionRequest createAboutVersionRequest() {
        switch (version) {
            case V212x:
                return new GPGeoserverAboutVersionRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.12.x");
        }
    }

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    @Override
    public GPGeoserverAboutStatusRequest createAboutStatusRequest() {
        switch (version) {
            case V212x:
                return new GPGeoserverAboutStatusRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.12.x");
        }
    }

    /**
     * @return {@link GeoserverVersion}
     */
    @Override
    public GeoserverVersion getVersion() {
        return this.version;
    }

    /**
     * @param serverURL
     * @return {@link URL}
     */
    protected static URL analyzesServerURL(URL serverURL) {
        checkArgument(serverURL != null, "The Parameer serverURL must not be null.");
        if (!serverURL.getPath().contains("/geoserver/rest"))
            throw new IllegalStateException("The GeoserverConnector Part must contains path /geoserver/rest");
        return serverURL;
    }
}