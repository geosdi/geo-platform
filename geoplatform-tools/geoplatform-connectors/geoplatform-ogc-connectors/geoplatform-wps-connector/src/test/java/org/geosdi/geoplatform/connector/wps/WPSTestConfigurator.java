package org.geosdi.geoplatform.connector.wps;

import org.geosdi.geoplatform.connector.GPWPSConnectorStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.WPSConnectorBuilder.newWPSConnectorBuilder;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSTestConfigurator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static final String wpsURL = "http://150.145.141.92/geoserver/wps";
    private static final String wpsHttpsURL = "https://vvf-toscana.geosdi.org/geoserver/wps";

    static {
        try {
            wpsServerConnector = newWPSConnectorBuilder()
                    .withServerUrl(new URL(wpsURL))
                    .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                            .withMaxTotalConnections(40)
                            .withDefaultMaxPerRoute(20)
                            .withMaxRedirect(10)
                            .build())
                    .withVersion("1.0.0")
                    .build();
            wpsHttpsServerConnector = newWPSConnectorBuilder()
                    .withServerUrl(new URL(wpsHttpsURL))
                    .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                            .withMaxTotalConnections(20)
                            .withDefaultMaxPerRoute(10)
                            .withMaxRedirect(5)
                            .build())
                    .withVersion("1.0.0")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected static GPWPSConnectorStore wpsServerConnector;
    protected static GPWPSConnectorStore wpsHttpsServerConnector;
}
