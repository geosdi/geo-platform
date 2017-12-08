package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.server.GPWPSServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.WPSDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWPSConnectorStore extends GPConnectorStore<GPWPSServerConnector> implements WPSConnector {

    /**
     * @param serverURL
     */
    public GPWPSConnectorStore(URL serverURL) {
        this(serverURL, WPSVersion.WPS_100);
    }

    /**
     * @param serverURL
     * @param theVersion
     */
    public GPWPSConnectorStore(URL serverURL, WPSVersion theVersion) {
        this(serverURL, null, theVersion);
    }

    /**
     * @param serverURL
     * @param security
     * @param theVersion
     */
    public GPWPSConnectorStore(URL serverURL, GPSecurityConnector security, WPSVersion theVersion) {
        super(new GPWPSServerConnector(serverURL, security, theVersion));
    }

    /**
     * @param serverURL
     * @param pooledConnectorConfig
     * @param security
     * @param theVersion
     */
    public GPWPSConnectorStore(URL serverURL, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector security,
            WPSVersion theVersion) {
        super(new GPWPSServerConnector(serverURL, pooledConnectorConfig, security, theVersion));
    }

    /**
     * @return {@link WPSGetCapabilitiesRequest<T>}
     */
    @Override
    public <T> WPSGetCapabilitiesRequest<T> createGetCapabilitiesRequest() {
        return this.server.createGetCapabilitiesRequest();
    }

    /**
     * @return {@link WPSDescribeProcessRequest<T>}
     */
    @Override
    public <T> WPSDescribeProcessRequest<T> createDescribeProcessRequest() {
        return this.server.createDescribeProcessRequest();
    }

    /**
     * @return {@link WPSVersion}
     */
    @Override
    public WPSVersion getVersion() {
        return this.server.getVersion();
    }
}
