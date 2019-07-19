package org.geosdi.geoplatform.connector.store.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceDatastoreFeatureTypesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceFeatureTypesRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory.configured;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverFeatureTypesConnectorTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("com");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void b_loadEmptyWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("Test");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_EMPTY_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void c_loadConfiguredWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("com");
        workspaceDatastoreFeatureTypesRequest.withFeatureTypeCategory(configured);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_CONFIGURED_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void d_loadEmptyConfiguredWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("Test");
        workspaceDatastoreFeatureTypesRequest.withFeatureTypeCategory(configured);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_EMPTY_CONFIGURED_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void e_loadWorkspaceFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceFeatureTypesRequest workspaceFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceFeatureTypesRequest();
        workspaceFeatureTypesRequest.withWorkspace("sf");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_FEATURE_TYPES_RESPONSE : {}\n", workspaceFeatureTypesRequest.getResponse().toFeatureType());
    }
}