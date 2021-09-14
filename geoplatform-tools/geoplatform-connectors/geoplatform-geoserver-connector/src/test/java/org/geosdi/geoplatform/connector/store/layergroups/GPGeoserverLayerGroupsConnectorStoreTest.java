package org.geosdi.geoplatform.connector.store.layergroups;

import org.geosdi.geoplatform.connector.geoserver.request.layergroups.GeoserverLayerGroupsRequest;
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
        GeoserverLayerGroupsRequest loadLayerGroupRequest = geoserverConnectorStoreV2_18_x.loadLayerGroups();
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadLayerGroupRequest.getResponse());
    }

}
