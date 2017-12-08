package org.geosdi.geoplatform.connector.server;

import org.geosdi.geoplatform.connector.WPSVersion;
import org.geosdi.geoplatform.connector.WPSVersionException;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.WPSDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.v100.WPSDescribeProcessRequestV100;
import org.geosdi.geoplatform.connector.server.request.v100.WPSGetCapabilitiesRequestV100;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import java.net.URL;

import static org.geosdi.geoplatform.connector.WPSVersion.toWPSVersion;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWPSServerConnector extends GPAbstractServerConnector implements GPWPSConnector {

    private final WPSVersion version;

    /**
     * <p>
     * Create an Instance of {@link GPWPSServerConnector} with the server URL
     * and the specific version.</p>
     *
     * @param urlServer the String that represent WPS_100 server URL
     * @param version   the value of WPS_100 version. Must be 1.0.0
     */
    public GPWPSServerConnector(String urlServer, String version) {
        this(urlServer, null, version);
    }

    /**
     * <p>
     * Create an instance of {@link GPWPSServerConnector} with the server URL,
     * {@link GPSecurityConnector} for security and version.</p>
     *
     * @param urlServer         the String that represent WPS_100 server URL
     * @param securityConnector {@link GPSecurityConnector}
     * @param version           the value of WPS_100 version. Must be 1.0.0
     */
    public GPWPSServerConnector(String urlServer, GPSecurityConnector securityConnector, String version) {
        this(analyzesServerURL(urlServer), securityConnector, toWPSVersion(version));
    }

    /**
     * @param theUrl
     * @param theSecurityConnector
     */
    public GPWPSServerConnector(URL theUrl, GPSecurityConnector theSecurityConnector, WPSVersion version) {
        super(theUrl, theSecurityConnector);
        this.version = version;
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    public GPWPSServerConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, toWPSVersion(version));
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    public GPWPSServerConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, WPSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig);
        this.version = theVersion;
    }

    /**
     * @return {@link WPSGetCapabilitiesRequest}
     */
    @Override
    public WPSGetCapabilitiesRequest createGetCapabilitiesRequest() {
        switch (this.version) {
            case WPS_100:
                return new WPSGetCapabilitiesRequestV100(this);
            default:
                throw new WPSVersionException("The Version for WPS must be 1.0.0");
        }
    }

    /**
     * @return {@link WPSDescribeProcessRequest}
     */
    @Override
    public WPSDescribeProcessRequest createDescribeProcessRequest() {
        switch (this.version) {
            case WPS_100:
                return new WPSDescribeProcessRequestV100(this);
            default:
                throw new WPSVersionException("The Version for WPS must be 1.0.0");
        }
    }

    /**
     * @return {@link WPSVersion}
     */
    @Override
    public WPSVersion getVersion() {
        return this.version;
    }
}
