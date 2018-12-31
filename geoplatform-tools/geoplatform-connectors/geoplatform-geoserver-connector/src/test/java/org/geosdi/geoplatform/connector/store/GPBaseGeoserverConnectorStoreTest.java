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
public abstract class GPBaseGeoserverConnectorStoreTest {

    protected static final Logger logger = LoggerFactory.getLogger(GPBaseGeoserverConnectorStoreTest.class);
    //
    private static final String geoserverURL = "http://150.145.141.92/geoserver/rest";
    protected static GPGeoserverConnectorStore geoserverConnectorStore;

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        geoserverConnectorStore = geoserverConnectorBuilder()
                .withServerUrl(new URL(geoserverURL))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(150)
                        .withDefaultMaxPerRoute(80)
                        .withMaxRedirect(20)
                        .build())
                .withClientSecurity(new BasicPreemptiveSecurityConnector("admin", "geoserver"))
                .build();
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception {
        geoserverConnectorStore.dispose();
    }
}
