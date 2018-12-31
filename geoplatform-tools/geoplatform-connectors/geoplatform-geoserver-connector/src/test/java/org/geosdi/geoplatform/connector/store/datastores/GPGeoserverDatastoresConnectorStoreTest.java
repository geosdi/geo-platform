package org.geosdi.geoplatform.connector.store.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverLoadWorkspacesRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverDatastoresConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverDatastoresConnectorTest() throws Exception {
        GeoserverLoadDatastoresRequest loadDatastoresRequest = geoserverConnectorStore.loadDatastoresRequest();
        loadDatastoresRequest.setWorkspaceName("topp");
        logger.info("############################LOAD_DATASTORES_RESPONSE : {}\n", loadDatastoresRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverDatastoreConnectorTest() throws Exception {
        GeoserverLoadDatastoreRequest loadDatastoreRequest = geoserverConnectorStore.loadDatastoreRequest();
        loadDatastoreRequest.setWorkspaceName("topp");
        loadDatastoreRequest.setStoreName("taz_shapes");
        logger.info("############################LOAD_DATASTORE_RESPONSE : {}\n", loadDatastoreRequest.getResponse());
    }

    @Test
    public void c_loadAllGeoserverDatastoresConnectorTest() throws Exception {
        GPGeoserverLoadWorkspacesRequest loadWorkspacesRequest = geoserverConnectorStore.loadWorkspacesRequest();
        GPGeoserverWorkspaces geoserverWorkspaces = loadWorkspacesRequest.getResponse();
        for (IGPGeoserverWorkspace geoserverWorkspace : geoserverWorkspaces.getWorkspaces()) {
            GeoserverLoadDatastoresRequest loadDatastoresRequest = geoserverConnectorStore.loadDatastoresRequest();
            loadDatastoresRequest.setWorkspaceName(geoserverWorkspace.getWorkspaceName());
            logger.info("############################LOAD_DATASTORES_RESPONSE : {}\n", loadDatastoresRequest.getResponse());
        }
    }
}