package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectorStoreTest.class);
    //
    private static final String geoserverURL = "http://150.145.141.92/geoserver/rest";
    private static GPGeoserverConnectorStore geoserverConnectorStore;

    @BeforeClass
    public static void beforeClasse() throws Exception {
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

    @Test
    public void aboutGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutVersionRequest aboutRequest = geoserverConnectorStore.createAboutRequest();
        logger.info("#####################ABOUT_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutRequest.getResponseAsString());
    }
}
