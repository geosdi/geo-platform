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
package org.geosdi.geoplatform.connector.store.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverCreateDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverUpdateDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverLoadWorkspacesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GeoserverCreateWorkspaceRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV217xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.IGPPostgisDatastoreBodyBuilder.GPPostgisDatastoreBodyBuilder.postgisDatastoreBodyBuilder;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverDatastoresConnectorStoreV217XTest extends GPBaseGeoserverConnectorStoreV217xTest {

    @Test
    public void a_loadGeoserverDatastoresConnectorTest() throws Exception {
        GeoserverLoadDatastoresRequest loadDatastoresRequest = geoserverConnectorStoreV2_17_x.loadDatastoresRequest();
        loadDatastoresRequest.setWorkspaceName("topp");
        logger.info("############################LOAD_DATASTORES_RESPONSE : {}\n", loadDatastoresRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverDatastoreConnectorTest() throws Exception {
        GeoserverLoadDatastoreRequest loadDatastoreRequest = geoserverConnectorStoreV2_17_x.loadDatastoreRequest();
        loadDatastoreRequest.withWorkspaceName("topp");
        loadDatastoreRequest.withStoreName("taz_shapes");
        logger.info("############################LOAD_DATASTORE_RESPONSE : {}\n", loadDatastoreRequest.getResponse());
    }

    @Test
    public void c_loadAllGeoserverDatastoresConnectorTest() throws Exception {
        GPGeoserverLoadWorkspacesRequest loadWorkspacesRequest = geoserverConnectorStoreV2_17_x.loadWorkspacesRequest();
        GPGeoserverWorkspaces geoserverWorkspaces = loadWorkspacesRequest.getResponse();
        for (IGPGeoserverWorkspace geoserverWorkspace : geoserverWorkspaces.getWorkspaces()) {
            GeoserverLoadDatastoresRequest loadDatastoresRequest = geoserverConnectorStoreV2_17_x.loadDatastoresRequest();
            loadDatastoresRequest.setWorkspaceName(geoserverWorkspace.getWorkspaceName());
            logger.info("############################LOAD_DATASTORES_RESPONSE : {}\n", loadDatastoresRequest.getResponse());
        }
    }

    @Test
    public void d_createGeoserverDatastoreConnectorTest() throws Exception {
        GeoserverCreateWorkspaceRequest createWorkspaceRequest = geoserverConnectorStoreV2_17_x.createWorkspaceRequest();
        createWorkspaceRequest.withWorkspaceBody(new GeoserverCreateWorkspaceBody("workspace_test_5"));
        logger.info("############CREATE_WORKSPACE_RESPONSE : {}", createWorkspaceRequest.getResponseAsString());
        GeoserverCreateDatastoreRequest createDatastoreRequest = geoserverConnectorStoreV2_17_x.createDatastoreRequest();
        createDatastoreRequest.withWorkspaceName("workspace_test_5")
                .withDatastoreBody(postgisDatastoreBodyBuilder().withName("datastore_test")
                        .withHost("localhost")
                        .withPort(5432)
                        .withDatabase("gp")
                        .withUser("postgres")
                        .withPassword("0x,postgres,0x")
                        .build());
        logger.info("####################CREATE_DATASTORE_RESPONSE : \n{}\n", createDatastoreRequest.getResponse());
        logger.info("####################CREATE_DATASTORE_RESPONSE : \n{}\n", createDatastoreRequest
                .withDatastoreBody(postgisDatastoreBodyBuilder().withName("datastore_test_1")
                        .withHost("localhost")
                        .withPort(5432)
                        .withDatabase("gp")
                        .withUser("postgres")
                        .withPassword("0x,postgres,0x")
                        .build()).getResponse());
        assertTrue(geoserverConnectorStoreV2_17_x.deleteDatastoreRequest().withWorkspaceName("workspace_test_5").withDatastoreName("datastore_test").getResponse());
        assertTrue(geoserverConnectorStoreV2_17_x.deleteDatastoreRequest().withWorkspaceName("workspace_test_5").withDatastoreName("datastore_test_1").getResponse());
        assertTrue(geoserverConnectorStoreV2_17_x.deleteWorkspaceRequest().withWorkspaceName("workspace_test_5").getResponse());
    }

    @Test
    public void e_updateGeoserverDatastoreConnectorTest() throws Exception {
        GeoserverCreateWorkspaceRequest createWorkspaceRequest = geoserverConnectorStoreV2_17_x.createWorkspaceRequest();
        createWorkspaceRequest.withWorkspaceBody(new GeoserverCreateWorkspaceBody("workspace_test_6"));
        logger.info("############CREATE_WORKSPACE_RESPONSE : {}", createWorkspaceRequest.getResponseAsString());
        GeoserverCreateDatastoreRequest createDatastoreRequest = geoserverConnectorStoreV2_17_x.createDatastoreRequest();
        createDatastoreRequest.withWorkspaceName("workspace_test_6")
                .withDatastoreBody(postgisDatastoreBodyBuilder().withName("datastore_test_2")
                        .withHost("localhost")
                        .withPort(5432)
                        .withDatabase("gp")
                        .withUser("postgres")
                        .withPassword("0x,postgres,0x").build());
        logger.info("####################CREATE_DATASTORE_RESPONSE : \n{}\n", createDatastoreRequest.getResponse());
        GeoserverUpdateDatastoreRequest updateDatastoreRequest = geoserverConnectorStoreV2_17_x.updateDatastoreRequest();
        updateDatastoreRequest.withWorkspaceName("workspace_test_6").withStoreName("datastore_test_2")
                .withDatastoreBody(postgisDatastoreBodyBuilder().withName("datastore_test_2")
                        .withDescription("DESCRIPTION_TEST").withEnabled(Boolean.FALSE)
                        .withHost("localhost")
                        .withPort(2700)
                        .withDatabase("test")
                        .withUser("postgres")
                        .withPassword("admin")
                        .build());
        logger.info("############################UPDATE_DATASTORE_RESPONSE : {}\n", updateDatastoreRequest.getResponse());
        GeoserverLoadDatastoreRequest loadDatastoreRequest = geoserverConnectorStoreV2_17_x.loadDatastoreRequest();
        logger.info("################################DATASTORE_UPDATED : {}\n", loadDatastoreRequest
                .withWorkspaceName("workspace_test_6").withStoreName("datastore_test_2").getResponse());
        assertTrue(geoserverConnectorStoreV2_17_x.deleteWorkspaceRequest().withWorkspaceName("workspace_test_6").withRecurse(TRUE).getResponse());
    }
}