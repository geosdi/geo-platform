package org.geosdi.geoplatform.connector.store.layers;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadWorkspaceLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadWorkspaceLayersRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverLoadWorkspacesRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.geosdi.geoplatform.connector.store.task.GeoserverWorkspaceLayersTask;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverLayersConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverWorkspaceToppLayersTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("topp");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverWorkspaceCiteTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest
                .withWorkspaceName("cite").getResponse());
    }

    @Test
    public void c_loadGeoserverWorkspaceTigerTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("tiger");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void d_loadGeoserverWorkspaceNurcTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("nurc");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void e_loadGeoserverWorkspaceSdeTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("sde");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void f_loadGeoserverWorkspaceItGeosolutionsTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("it.geosolutions");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void g_loadGeoserverWorkspaceSfTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("sf");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void h_loadGeoserverWorkspaceLayerTest() throws Exception {
        GeoserverLoadWorkspaceLayerRequest loadWorkspaceLayerRequest = geoserverConnectorStore.loadWorkspaceLayerRequest();
        loadWorkspaceLayerRequest.withWorkspaceName("tiger").withLayerName("poi");
        logger.info("######################LOAD_WORKSPACE_LAYER_RESPONSE : {}\n", loadWorkspaceLayerRequest.getResponse());
    }

    @Test
    public void h_loadGeoserverWorkspaceLayersTest() throws Exception {
        GPGeoserverLoadWorkspacesRequest workspacesRequest = geoserverConnectorStore.loadWorkspacesRequest();
        GPGeoserverWorkspaces geoserverWorkspaces = workspacesRequest.getResponse();
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStore.loadWorkspaceLayersRequest();
        for (IGPGeoserverWorkspace geoserverWorkspace : geoserverWorkspaces.getWorkspaces()) {
            new GeoserverWorkspaceLayersTask(loadWorkspaceLayersRequest, geoserverWorkspace.getWorkspaceName()).start();
        }
        sleep(1000);
    }
}