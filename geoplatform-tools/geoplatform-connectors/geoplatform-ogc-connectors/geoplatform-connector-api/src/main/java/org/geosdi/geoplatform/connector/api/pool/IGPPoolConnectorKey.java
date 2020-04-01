package org.geosdi.geoplatform.connector.api.pool;

import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.httpclient.proxy.HttpClientProxyConfiguration;

import java.io.Serializable;
import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPPoolConnectorKey extends Serializable {

    /**
     * @return {@link URL}
     */
    URL getServerUrl();

    /**
     * @return {@link GPPooledConnectorConfig}
     */
    GPPooledConnectorConfig getPooledConnectorConfig();

    /**
     * @return {@link}
     */
    GPSecurityConnector getSecurityConnector();

    /**
     * @return {@link HttpClientProxyConfiguration}
     */
    HttpClientProxyConfiguration getProxyConfiguration();

    /**
     * @return {@link String}
     */
    String getVersion();
}