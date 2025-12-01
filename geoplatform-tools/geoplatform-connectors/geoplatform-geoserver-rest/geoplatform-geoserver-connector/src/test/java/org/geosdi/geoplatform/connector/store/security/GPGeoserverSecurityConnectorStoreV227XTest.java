/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store.security;

import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalog;
import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUserBody;
import org.geosdi.geoplatform.connector.geoserver.request.security.GeoserverGetMasterPasswordRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GeoserverGetCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GeoserverUpdateCatalogRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV227xTest;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import static org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalogMode.HIDE;
import static org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalogMode.MIXED;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverUserBodyJacksonTest.toGeoserverUserBody;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverSecurityConnectorStoreV227XTest extends GPBaseGeoserverConnectorStoreV227xTest {

    @Test
    public void a_loadGeoserverMasterPasswordTest() throws Exception {
        GeoserverGetMasterPasswordRequest geoserverGetMasterPasswordRequest = geoserverConnectorStoreV2_27_x.loadMasterPasswordRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_LOAD_MASTER_PASSWORD_RESPONSE : {}\n", geoserverGetMasterPasswordRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverCatalogRequestTest() throws Exception {
        GeoserverGetCatalogRequest geoserverGetCatalogRequest = geoserverConnectorStoreV2_27_x.loadCatalogRequest();
        GPGeoserverCatalog geoserverCatalog = geoserverGetCatalogRequest.getResponse();
        assertTrue(geoserverCatalog.getCatalogMode() == HIDE);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_LOAD_CATALOG_RESPONSE : {}\n", geoserverCatalog);
    }

    @Test
    public void c_updateGeoserverCatalogRequestTest() throws Exception {
        GeoserverUpdateCatalogRequest updateCatalogRequest = geoserverConnectorStoreV2_27_x.updateCatalogRequest();
        updateCatalogRequest.withCatalogMode(MIXED);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_CATALOG_RESPONSE : {}\n", updateCatalogRequest.getResponse());
        GeoserverGetCatalogRequest geoserverGetCatalogRequest = geoserverConnectorStoreV2_27_x.loadCatalogRequest();
        GPGeoserverCatalog geoserverCatalog = geoserverGetCatalogRequest.getResponse();
        assertTrue(geoserverCatalog.getCatalogMode() == MIXED);
        updateCatalogRequest.withCatalogMode(HIDE);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_CATALOG_RESPONSE : {}\n", updateCatalogRequest.getResponse());
    }

    @Test
    public void d_loadGeoserverUsersRequest() throws Exception {
        logger.info("#####################GP_GEOSERVER_USERS_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.loadUsersRequest().getResponse());
    }

    @Test
    public void e_createGeoserverUserRequestTest() throws Exception {
        GPGeoserverUserBody createUser = toGeoserverUserBody("glascaleia", "0x,77peppino77?!0x");
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CREATE_USER_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.createUserRequest()
                .withBody(createUser).getResponse());
    }

    @Ignore
    @Test
    public void f_loadGeoserverUserRequestTest() throws Exception {
        logger.info("#####################GP_GEOSERVER_LOAD_USER_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.loadUserRequest()
                .withUser("glascaleia").getResponse());
    }

    @Test
    public void g_deleteGeoserverUserRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_DELETE_USER_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.deleteUserRequest()
                .withUser("glascaleia").getResponse());
    }

    @Test
    public void h_loadGeoserverGroupsRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_LOAD_GROUPS_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.loadGroupsRequest()
                .getResponse());
    }

    @Test
    public void i_createGeoserverGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CREATE_GROUPS_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.createGroupRequest()
                .withGropuName("group_test").getResponse());
    }

    @Test
    public void l_deleteGeoserverGroupRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_DELETE_GROUP_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x.deleteGroupRequest()
                .withGropuName("group_test").getResponse());
    }

    @Test
    public void m_loadGeoserverLayerRulesRequestTest() throws Exception {
        logger.info("####################GP_GEOSERVER_LOAD_LAYER_RULES_RESPONSE : {}\n",
                geoserverConnectorStoreV2_27_x.loadAclLayers().getResponse());
    }
}