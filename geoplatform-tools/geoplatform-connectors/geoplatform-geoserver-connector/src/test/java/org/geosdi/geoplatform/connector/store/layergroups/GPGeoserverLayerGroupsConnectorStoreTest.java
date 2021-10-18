package org.geosdi.geoplatform.connector.store.layergroups;

import org.geosdi.geoplatform.connector.geoserver.request.layergroups.GeoserverLoadLayerGroupsRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverLayerGroupsConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverWorkspaceToppLayersTest() throws Exception {
        GeoserverLoadLayerGroupsRequest loadLayerGroupRequest = geoserverConnectorStoreV2_18_x.loadLayerGroups();
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadLayerGroupRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_LAYER_GROUP_RESPONSE : {}\n", geoserverConnectorStoreV2_18_x
                .loadLayerGroupRequest()
                .withLayerGroupName("spearfish").getResponse());
    }

    @Test
    public void c_loadGeoserverLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_LAYER_GROUP_RESPONSE : {}\n", geoserverConnectorStoreV2_18_x
                .loadLayerGroupRequest()
                .withLayerGroupName("tiger-ny").getResponse());
    }
}