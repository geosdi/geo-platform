/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.store.layergroups;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.GPGeoserverLayerGroupBody;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.publishables.GPGeoserverLayerGroupPublishables;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.publishables.GPGeoserverLayerPublished;
import org.geosdi.geoplatform.connector.geoserver.request.layergroups.GeoserverLoadLayerGroupsRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV22xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.List;

import static org.geosdi.geoplatform.connector.geoserver.model.layergroups.GPGeoserverLayerGroupMode.CONTAINER;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverLayerGroupsConnectorStoreV22XTest extends GPBaseGeoserverConnectorStoreV22xTest {

    @Test
    public void a_loadGeoserverLoadLayerGroupsRequestTest() throws Exception {
        GeoserverLoadLayerGroupsRequest loadLayerGroupRequest = geoserverConnectorStoreV2_20_x.loadLayerGroups();
        logger.info("############################LOAD_LAYER_GROUPS_RESPONSE : {}\n",
                loadLayerGroupRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_LAYER_GROUP_RESPONSE : {}\n",
                geoserverConnectorStoreV2_20_x.loadLayerGroupRequest().withLayerGroupName("spearfish").getResponse());
    }

    @Test
    public void c_loadGeoserverLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_LAYER_GROUP_RESPONSE : {}\n",
                geoserverConnectorStoreV2_20_x.loadLayerGroupRequest().withLayerGroupName("LAYER_GROUP_TEST")
                        .getResponse());
    }

    @Test
    public void d_loadWorkspaceGeoserverLayerGroupsRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_LAYER_GROUP_RESPONSE : {}\n",
                geoserverConnectorStoreV2_20_x.loadWorkspaceLayerGroupsRequest().withWorkspace("sf").getResponse());
    }

    @Test
    public void e_createGeoserverLayerGroupRequestTest() throws Exception {
        logger.info("###################{}\n", geoserverConnectorStoreV2_20_x.createLayerGroupRequest()
                .withBody(this.toBody("LAYER_GROUP_TEST_NAME", "LAYER_GROUP_TEST_TITLE")).getResponse());
    }

    @Test
    public void d_deleteGeoserverLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@DELETE_LAYER_GROUP_RESPONSE : {}\n", geoserverConnectorStoreV2_20_x.deleteLayerGroupRequest()
                .withName("LAYER_GROUP_TEST_NAME")
                .getResponse());
    }

    @Test
    public void e_createGeoserverWorkspaeLayerGroupRequestTest() throws Exception {
        logger.info("###################{}\n", geoserverConnectorStoreV2_20_x.createWorkspaceLayerGroupRequest()
                .withWorkspace("tiger")
                .withBody(this.toBody("LAYER_GROUP_TEST_WORKSPACE_NAME", "LAYER_GROUP_TEST_WORKSPACE_TITLE"))
                .getResponse());
    }

    @Test
    public void f_loadGeoserverWorkspaceLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_LAYER_GROUP_RESPONSE : {}\n", geoserverConnectorStoreV2_20_x.loadWorkspaceLayerGroupsRequest()
                .withWorkspace("tiger")
                .getResponse());
    }

    @Test
    public void g_deleteGeoserverWorkspaceLayerGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@DELETE_LAYER_GROUP_RESPONSE : {}\n", geoserverConnectorStoreV2_20_x.deleteWorkspaceLayerGroupRequest()
                .withWorkspace("tiger")
                .withName("LAYER_GROUP_TEST_WORKSPACE_NAME")
                .getResponse());
    }

    /**
     * @return {@link GPGeoserverLayerGroupBody}
     */
    GPGeoserverLayerGroupBody toBody(String theName, String theTitle) {
        GPGeoserverLayerGroupBody layerGroupBody = new GPGeoserverLayerGroupBody();
        layerGroupBody.setName(theName);
        layerGroupBody.setTitle(theTitle);
        layerGroupBody.setMode(CONTAINER);
        //        layerGroupBody.setWorkspace(new GeoserverCreateWorkspaceBody("topp"));
        layerGroupBody.setBounds(new GPGeoserverLatLonBoundingBox() {
            {
                super.setMaxx(-73.907005);
                super.setMaxy(40.882078);
                super.setMinx(-74.047185);
                super.setMiny(40.679648);
                super.setCrs("EPSG:4326");
            }
        });
        GPGeoserverLayerGroupPublishables layerGroupBodyPublishables = new GPGeoserverLayerGroupPublishables();
        List<GPGeoserverLayerPublished> layers = Lists.newArrayList();
        layers.add(new GPGeoserverLayerPublished() {
            {
                super.setLayerName("tiger:giant_polygon");
            }
        });
//        layers.add(new GPGeoserverLayerPublished() {
//            {
//                super.setLayerName("topp:states");
//            }
//        });
        layers.add(new GPGeoserverLayerPublished() {
            {
                super.setLayerName("tiger:poly_landmarks");
            }
        });
        layerGroupBodyPublishables.setLayers(layers);
        layerGroupBody.setLayers(layerGroupBodyPublishables);
        return layerGroupBody;
    }
}