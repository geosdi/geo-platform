package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseGeoserverConnectorStoreV215xTest {

    protected static final Logger logger = LoggerFactory.getLogger(GPBaseGeoserverConnectorStoreV215xTest.class);
    //
    private static final String geoserverURLV2_15_5 = "http://150.145.141.180/geoserver/rest";
    protected static GPGeoserverConnectorStore geoserverConnectorStoreV2_15_5;

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        geoserverConnectorStoreV2_15_5 = geoserverConnectorBuilder()
                .withServerUrl(new URL(geoserverURLV2_15_5))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(80)
                        .withDefaultMaxPerRoute(40)
                        .withMaxRedirect(10)
                        .build())
                .withClientSecurity(new BasicPreemptiveSecurityConnector("admin", "geoservertest"))
                .build();
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception {
        geoserverConnectorStoreV2_15_5.dispose();
    }
}