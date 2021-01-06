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
package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverNamespaces;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.IGPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverLoadWorkspace;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLoadLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStylesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.*;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.store.task.GeoserverLayerTask;
import org.geosdi.geoplatform.connector.store.task.GeoserverNamespaceTask;
import org.geosdi.geoplatform.connector.store.task.GeoserverStyleTask;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverConnectorStoreV217XTest extends GPBaseGeoserverConnectorStoreV217xTest {

    @Test
    public void a_aboutVersionGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutVersionRequest aboutRequest = geoserverConnectorStoreV2_17_x.createAboutVersionRequest();
        logger.info("#####################ABOUT_VERSION_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutRequest.getResponseAsString());
    }

    @Test
    public void b_aboutStatusGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutStatusRequest aboutStatusRequest = geoserverConnectorStoreV2_17_x.createAboutStatusRequest();
        logger.info("#####################ABOUT_STATUS_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutStatusRequest.getResponseAsString());
    }

    @Test
    public void c_workspacesGeoserverConnectorTest() throws Exception {
        GPGeoserverLoadWorkspacesRequest workspacesRequest = geoserverConnectorStoreV2_17_x.loadWorkspacesRequest();
        logger.info("####################WORKSPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", workspacesRequest.getResponse());
    }

    @Test
    public void d_namespacesGeoserverConnectorTest() throws Exception {
        GPGeoserverNamespacesRequest namespacesRequest = geoserverConnectorStoreV2_17_x.createNamespacesRequest();
        logger.info("###################NAMESPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", namespacesRequest.getResponseAsString());
    }

    @Test
    public void e_namespaceGeoserverConnectorTest() throws Exception {
        GPGeoserverNamespaceRequest namespaceRequest = geoserverConnectorStoreV2_17_x.createNamespaceRequest();
        namespaceRequest.setPrefix("tiger");
        logger.info("###################NAMESPACE_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", namespaceRequest.getResponseAsString());
    }

    @Test
    public void f_namespaceGeoserverConnectorMultiThreadTest() throws Exception {
        GPGeoserverNamespacesRequest namespacesRequest = geoserverConnectorStoreV2_17_x.createNamespacesRequest();
        GPGeoserverNamespaceRequest namespaceRequest = geoserverConnectorStoreV2_17_x.createNamespaceRequest();
        GPGeoserverNamespaces namespaces = namespacesRequest.getResponse();
        logger.info("#######################FOUND : {} namespaces.", namespaces.getNamespaces().size());
        for (IGPGeoserverNamespace namespace : namespaces.getNamespaces()) {
            new GeoserverNamespaceTask(namespaceRequest, namespace.getName()).start();
        }
        sleep(1000);
    }

    @Test
    public void g_layersGeoserverConnectorTest() throws Exception {
        GPGeoserverLayersRequest layersRequest = geoserverConnectorStoreV2_17_x.loadLayersRequest();
        logger.info("##################LAYERS_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", layersRequest.getResponseAsString());
    }

    @Test
    public void h_stylesGeoserverConnectorTest() throws Exception {
        GPGeoserverStylesRequest stylesRequest = geoserverConnectorStoreV2_17_x.loadStylesRequest();
        logger.info("#################STYLES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", stylesRequest.getResponseAsString());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void i_styleGeoserverConnectorTest() throws Exception {
        GeoserverStyleRequest styleRequest = geoserverConnectorStoreV2_17_x.loadStyleRequest();
        styleRequest.withStyleName("Frank");
        logger.info("################STYLE_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", styleRequest.getResponseAsString());
    }

    @Test
    public void l_styleGeoserverConnectorMultiThreadTest() throws Exception {
        GPGeoserverStylesRequest stylesRequest = geoserverConnectorStoreV2_17_x.loadStylesRequest();
        GeoserverStyleRequest styleRequest = geoserverConnectorStoreV2_17_x.loadStyleRequest();
        stylesRequest.getResponse().getStyles()
                .stream()
                .forEach(value -> new GeoserverStyleTask(styleRequest, value.getName()).start());
        sleep(1000);
    }

    @Test
    public void m_layerVectorGeoserverConnectorTest() throws Exception {
        GPGeoserverLoadLayerRequest layerRequest = geoserverConnectorStoreV2_17_x.loadLayerRequest();
        layerRequest.withName("giant_polygon");
        logger.info("##############VECTOR_LAYER_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", layerRequest.getResponseAsString());
    }

    @Test
    public void n_layerRasterGeoserverConnectorTest() throws Exception {
        GPGeoserverLoadLayerRequest layerRequest = geoserverConnectorStoreV2_17_x.loadLayerRequest();
        layerRequest.withName("Arc_Sample");
        logger.info("############RASTER_LAYER_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", layerRequest.getResponseAsString());
    }

    @Test
    public void o_layerGeoserverConnectorMultiThreadTest() throws Exception {
        GPGeoserverLayersRequest layersRequest = geoserverConnectorStoreV2_17_x.loadLayersRequest();
        GPGeoserverLoadLayerRequest layerRequest = geoserverConnectorStoreV2_17_x.loadLayerRequest();
        layersRequest.getResponse().getLayers()
                .stream()
                .forEach(value -> new GeoserverLayerTask(layerRequest, value.getLayerName()).start());
        sleep(1000);
    }

    @Test
    public void p_createWorkspaceGeoserverConnectorTest() throws Exception {
        GeoserverCreateWorkspaceRequest createWorkspaceRequest = geoserverConnectorStoreV2_17_x.createWorkspaceRequest();
        logger.info("############CREATE_WORKSPACE_RESPONSE : {}", createWorkspaceRequest
                .withWorkspaceBody(new GeoserverCreateWorkspaceBody("workspace_test"))
                .getResponseAsString());
    }

    @Test
    public void q_loadWorkspaceGeoserverConnectorTest() throws Exception {
        GPGeoserverLoadWorkspaceRequest loadWorkspaceRequest = geoserverConnectorStoreV2_17_x.loadWorkspaceRequest();
        loadWorkspaceRequest.setWorkspaceName("workspace_test");
        GPGeoserverLoadWorkspace loadWorkspace = loadWorkspaceRequest.getResponse();
        logger.info("#############################LOAD_WORKSPACE_RESPONSE : {}\n", loadWorkspace);
    }

    @Test
    public void r_updateWorkspaceGeoserverConnectorTest() throws Exception {
        GeoserverUpdateWorkspaceRequest updateWorkspaceRequest = geoserverConnectorStoreV2_17_x.updateWorkspaceRequest();
        updateWorkspaceRequest.setWorkspaceName("workspace_test");
        updateWorkspaceRequest.setWorkspaceBody(new GeoserverCreateWorkspaceBody("workspace_test_1"));
        logger.info("##########################UPDATE_WORKSPACE_RESPONSE : {}\n", updateWorkspaceRequest.getResponseAsString());
    }

    @Test
    public void s_deleteWorkspaceGeoserverConnectorTest() throws Exception {
        GeoserverDeleteWorkspaceRequest deleteWorkspaceRequest = geoserverConnectorStoreV2_17_x.deleteWorkspaceRequest();
        deleteWorkspaceRequest.withWorkspaceName("workspace_test_1");
        deleteWorkspaceRequest.withRecurse(TRUE);
        logger.info("###########################DELETE_WORKSPACE_RESPONSE : {}\n", deleteWorkspaceRequest.getResponse());
    }
}