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
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("topp");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverWorkspaceCiteTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest
                .withWorkspaceName("cite").getResponse());
    }

    @Test
    public void c_loadGeoserverWorkspaceTigerTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("tiger");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void d_loadGeoserverWorkspaceNurcTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("nurc");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void e_loadGeoserverWorkspaceSdeTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("sde");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void f_loadGeoserverWorkspaceItGeosolutionsTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("it.geosolutions");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void g_loadGeoserverWorkspaceSfTest() throws Exception {
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        loadWorkspaceLayersRequest.withWorkspaceName("sf");
        logger.info("############################LOAD_WORKSPACE_LAYERS_RESPONSE : {}\n", loadWorkspaceLayersRequest.getResponse());
    }

    @Test
    public void h_loadGeoserverWorkspaceLayerTest() throws Exception {
        GeoserverLoadWorkspaceLayerRequest loadWorkspaceLayerRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayerRequest();
        loadWorkspaceLayerRequest.withWorkspaceName("tiger").withLayerName("poi");
        logger.info("######################LOAD_WORKSPACE_LAYER_RESPONSE : {}\n", loadWorkspaceLayerRequest.getResponse());
    }

    @Test
    public void h_loadGeoserverWorkspaceLayersTest() throws Exception {
        GPGeoserverLoadWorkspacesRequest workspacesRequest = geoserverConnectorStoreV2_18_x.loadWorkspacesRequest();
        GPGeoserverWorkspaces geoserverWorkspaces = workspacesRequest.getResponse();
        GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest = geoserverConnectorStoreV2_18_x.loadWorkspaceLayersRequest();
        for (IGPGeoserverWorkspace geoserverWorkspace : geoserverWorkspaces.getWorkspaces()) {
            new GeoserverWorkspaceLayersTask(loadWorkspaceLayersRequest, geoserverWorkspace.getWorkspaceName()).start();
        }
        sleep(700);
    }
}