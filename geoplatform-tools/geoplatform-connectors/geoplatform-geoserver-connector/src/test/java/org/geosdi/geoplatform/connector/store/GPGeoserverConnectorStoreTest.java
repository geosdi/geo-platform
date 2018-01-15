package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverNamespaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.IGPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStylesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverWorkspacesRequest;
import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.geosdi.geoplatform.connector.store.task.GeoserverNamespaceTask;
import org.geosdi.geoplatform.connector.store.task.GeoserverStyleTask;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverConnectorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectorStoreTest.class);
    //
    private static final String geoserverURL = "http://150.145.141.92/geoserver/rest";
    private static GPGeoserverConnectorStore geoserverConnectorStore;

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

    @Test
    public void a_aboutVersionGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutVersionRequest aboutRequest = geoserverConnectorStore.createAboutVersionRequest();
        logger.info("#####################ABOUT_VERSION_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutRequest.getResponseAsString());
    }

    @Test
    public void b_aboutStatusGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutStatusRequest aboutStatusRequest = geoserverConnectorStore.createAboutStatusRequest();
        logger.info("#####################ABOUT_STATUS_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutStatusRequest.getResponseAsString());
    }

    @Test
    public void c_workspacesGeoserverConnectorTest() throws Exception {
        GPGeoserverWorkspacesRequest workspacesRequest = geoserverConnectorStore.createWorkspacesRequest();
        logger.info("####################WORKSPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", workspacesRequest.getResponseAsString());
    }

    @Test
    public void d_namespacesGeoserverConnectorTest() throws Exception {
        GPGeoserverNamespacesRequest namespacesRequest = geoserverConnectorStore.createNamespacesRequest();
        logger.info("###################NAMESPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", namespacesRequest.getResponseAsString());
    }

    @Test
    public void e_namespaceGeoserverConnectorTest() throws Exception {
        GPGeoserverNamespaceRequest namespaceRequest = geoserverConnectorStore.createNamespaceRequest();
        namespaceRequest.setPrefix("tiger");
        logger.info("###################NAMESPACE_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", namespaceRequest.getResponseAsString());
    }

    @Test
    public void f_namespaceGeoserverConnectorMultiThreadTest() throws Exception {
        GPGeoserverNamespacesRequest namespacesRequest = geoserverConnectorStore.createNamespacesRequest();
        GPGeoserverNamespaceRequest namespaceRequest = geoserverConnectorStore.createNamespaceRequest();
        GPGeoserverNamespaces namespaces = namespacesRequest.getResponse();
        logger.info("#######################FOUND : {} namespaces.", namespaces.getNamespaces().size());
        for (IGPGeoserverNamespace namespace : namespaces.getNamespaces()) {
            new GeoserverNamespaceTask(namespaceRequest, namespace.getName()).start();
        }
        Thread.sleep(500);
    }

    @Test
    public void g_layersGeoserverConnectorTest() throws Exception {
        GPGeoserverLayersRequest layersRequest = geoserverConnectorStore.createLayersRequest();
        logger.info("##################LAYERS_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", layersRequest.getResponseAsString());
    }

    @Test
    public void h_stylesGeoserverConnectorTest() throws Exception {
        GPGeoserverStylesRequest stylesRequest = geoserverConnectorStore.createStylesRequest();
        logger.info("#################STYLES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", stylesRequest.getResponseAsString());
    }

    @Test
    public void i_styleGeoserverConnectorTest() throws Exception {
        GPGeoserverStyleRequest styleRequest = geoserverConnectorStore.createStyleRequest();
        styleRequest.setName("Frank");
        logger.info("################STYLE_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", styleRequest.getResponseAsString());
    }

    @Test
    public void l_styleGeoserverConnectorMultiThreadTest() throws Exception {
        GPGeoserverStylesRequest stylesRequest = geoserverConnectorStore.createStylesRequest();
        GPGeoserverStyleRequest styleRequest = geoserverConnectorStore.createStyleRequest();
        stylesRequest.getResponse().getStyles()
                .stream()
                .forEach(value -> new GeoserverStyleTask(styleRequest, value.getName()).start());
        Thread.sleep(700);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        geoserverConnectorStore.dispose();
    }
}