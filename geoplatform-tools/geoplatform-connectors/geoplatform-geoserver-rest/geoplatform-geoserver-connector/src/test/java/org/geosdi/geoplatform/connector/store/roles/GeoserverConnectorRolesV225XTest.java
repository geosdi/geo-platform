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
package org.geosdi.geoplatform.connector.store.roles;

import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUserBody;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV225xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.jackson.GPGeoserverUserBodyJacksonTest.toGeoserverUserBody;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(NAME_ASCENDING)
public class GeoserverConnectorRolesV225XTest extends GPBaseGeoserverConnectorStoreV225xTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorRolesV225XTest.class);

    @Test
    public void a_loadRoles() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStoreV2_25_x.loadRoles().getResponse());
    }

    @Test
    public void b_loadUserRoles() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStoreV2_25_x.loadUserRoles().withUser("admin").getResponse());
    }

    @Test
    public void c_loadGroupRoles() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStoreV2_25_x.loadGroupRoles().withGroup("test").getResponse());
    }

    @Test
    public void d_loadServiceUserRoles() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStoreV2_25_x.loadServiceUserRoles()
                .withService("default")
                .withUser("admin").getResponse());
    }

    @Test
    public void e_loadServiceGroupRoles() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStoreV2_25_x.loadServiceGroupRoles()
                .withService("default")
                .withGroup("test").getResponse());
    }

    @Test
    public void f_createRole() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStoreV2_25_x.createRole().withRole("test_role").getResponse());
    }

    @Test
    public void g_linkUserToRole() throws Exception {
        GPGeoserverUserBody createUser = toGeoserverUserBody("vsalvia", "0x,salvia,0x");
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.createUserRequest().withBody(createUser).getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.linkUserToRole().withRole("test_role").withUser("vsalvia")
                        .getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.unlinkUserToRole().withRole("test_role").withUser("vsalvia")
                        .getResponse());
    }

    @Test
    public void h_linkGroupToRole() throws Exception {
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.createGroupRequest().withGropuName("test_group").getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.linkGroupToRole().withRole("test_role").withGroup("test_group")
                        .getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.unlinkGroupToRole().withRole("test_role").withGroup("test_group")
                        .getResponse());
    }

    @Test
    public void i_createServiceRoleTest() throws Exception {
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.createServiceRole().withService("default").withRole("test_role2")
                        .getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.deleteServiceRole().withService("default").withRole("test_role2")
                        .getResponse());
    }

    @Test
    public void l_createServiceUserRoleTest() throws Exception {
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.createServiceUserRole().withService("default").withRole("test_role")
                        .withUser("vsalvia").getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.deleteServiceUserRole().withService("default").withRole("test_role")
                        .withUser("vsalvia").getResponseAsString());
    }

    @Test
    public void m_createServiceGroupRoleTest() throws Exception {
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.createServiceGroupRole().withService("default")
                        .withRole("test_role").withGroup("test_group").getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.deleteServiceGroupRole().withService("default")
                        .withRole("test_role").withGroup("test_group").getResponseAsString());
    }

    @Test
    public void z_deleteAll() throws Exception {
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.deleteRole().withRole("test_role").getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.deleteUserRequest().withUser("vsalvia").getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStoreV2_25_x.deleteGroupRequest().withGropuName("test_group").getResponse());
    }

}