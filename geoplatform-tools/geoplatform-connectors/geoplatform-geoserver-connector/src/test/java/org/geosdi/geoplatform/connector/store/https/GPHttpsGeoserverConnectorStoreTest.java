package org.geosdi.geoplatform.connector.store.https;

import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverWorkspacesRequest;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPHttpsGeoserverConnectorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPHttpsGeoserverConnectorStoreTest.class);
    //
    private static final String geoserverURL = "https://vvf-toscana.geosdi.org/geoserver/rest";
    private static GPGeoserverConnectorStore httpsGeoserverConnectorStore;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        httpsGeoserverConnectorStore = geoserverConnectorBuilder()
                .withServerUrl(new URL(geoserverURL))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(40)
                        .withDefaultMaxPerRoute(10)
                        .withMaxRedirect(2)
                        .build())
                .build();
    }

    @Test(expected = UnauthorizedException.class)
    public void a_aboutHttpsVersionGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutVersionRequest aboutRequest = httpsGeoserverConnectorStore.createAboutVersionRequest();
        logger.info("#####################ABOUT_VERSION_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutRequest.getResponseAsString());
    }

    @Test(expected = UnauthorizedException.class)
    public void b_aboutHttpsStatusGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutStatusRequest aboutStatusRequest = httpsGeoserverConnectorStore.createAboutStatusRequest();
        logger.info("#####################ABOUT_STATUS_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutStatusRequest.getResponseAsString());
    }

    @Test(expected = UnauthorizedException.class)
    public void c_workspacesHttpsGeoserverConnectorTest() throws Exception {
        GPGeoserverWorkspacesRequest workspacesRequest = httpsGeoserverConnectorStore.createWorkspacesRequest();
        logger.info("####################WORKSPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", workspacesRequest.getResponse());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.clearProperty("jsse.enableSNIExtension");
        httpsGeoserverConnectorStore.dispose();
    }
}