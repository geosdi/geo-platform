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
package org.geosdi.geoplatform.core.dao;

import jakarta.annotation.Resource;
import org.geosdi.geoplatform.core.acl.AclEntry;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.geosdi.geoplatform.core.model.GPCapabilityType.CSW;
import static org.junit.Assert.assertNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles(value = {"jpa"})
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GeoPlatformCoreDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoPlatformCoreDaoTest.class);
    //
    @Resource(name = "accountDAO")
    private GPAccountDAO accountDAO;
    @Resource(name = "layerDAO")
    private GPLayerDAO layerDAO;
    @Resource(name = "authorityDAO")
    private GPAuthorityDAO authorityDAO;
    @Resource(name = "accountProjectDAO")
    private GPAccountProjectDAO accountProjectDAO;
    @Resource(name = "folderDAO")
    private GPFolderDAO folderDAO;
    @Resource(name = "entryDAO")
    private AclEntryDAO entryDAO;
    @Resource(name = "serverDAO")
    private GPServerDAO serverDAO;

    @Before
    public void setUp() throws Exception {
        assertNotNull("The Parameter accountDAO must not be null.", this.accountDAO);
        assertNotNull("The Parameter layerDAO must not be null", this.layerDAO);
        assertNotNull("The Parameter authorityDAO must not be null", this.authorityDAO);
        assertNotNull("The Parameter accountProjectDAO must not be null", this.accountProjectDAO);
        assertNotNull("The Parameter folderDAO must not be null", this.folderDAO);
        assertNotNull("The Parameter entryDAO must not be null", this.entryDAO);
        assertNotNull("The Parameter serverDAO must not be null", this.serverDAO);
    }

    @Test
    public void a_findAllAccountsTest() throws Exception {
        List<GPAccount> accounts = this.accountDAO.findAll();
        logger.info("############################FOUNDS {} - Accounts.", accounts.size());
    }

    @Test
    public void b_findByOrganizationNameTest() throws Exception {
        List<GPAccount> accounts = this.accountDAO.findByOrganization("geoSDI");
        logger.info("###########################FOUNDS : {} - Accounts.", accounts.size());
    }

    @Test
    public void c_findAccountByUserNameTest() throws Exception {
        logger.info("################################FOUND : {}\n", this.accountDAO.findByUsername("admin"));

    }

    @Test
    public void d_findAccountByEmailTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FOUND : {}\n", this.accountDAO.findByEmail("admin@geosdi.org"));
    }

    @Test
    public void e_countAccountsTest() throws Exception {
        logger.info("###############################FOUNDS : {}\n", this.accountDAO.countAccounts("%user%"));
    }

    @Test
    public void f_findLayersByProjectIDTest() throws Exception {
        List<String> dataSources = this.layerDAO.findDistinctDataSourceByProjectId(1l);
        logger.info("###############################LAYERS_DATA_SOURCES : {}\n", dataSources);
    }

    @Test
    public void g_findLayerByNameTest() throws Exception {
        GPLayer layer = this.layerDAO.findByLayerName("test");
        Assert.assertNull(layer);
        logger.info("{}\n", this.layerDAO.persistCheckStatusLayer(1l, Boolean.TRUE));
    }

    @Test
    public void h_findShortByAccountNaturalIDTest() throws Exception {
        logger.info("###########################{}\n", this.authorityDAO.findShortByAccountNaturalID("admin"));
    }

    @Test
    public void i_countAccoutProjectTest() throws Exception {
        logger.info("########################ACCOUNT_PROJECTS_FOUND : {}\n", this.accountProjectDAO.count(2l,
                "%user%"));
    }

    @Test
    public void l_searchPagebleUsersByOrganizationTest() throws Exception {
        logger.info("{}\n", this.accountDAO.searchPagebleUsersByOrganization(0, 4, "geoSDI",
                52l, null).size());
        logger.info("{}\n", this.accountDAO.searchPagebleUsersByOrganization(2, 4, "geoSDI",
                52l, null).size());
    }

    @Test
    public void m_countAccountByOrganizationTest() throws Exception {
        logger.info("{}\n", this.accountDAO.countUsers("geoSDI", null));
    }

    @Test
    public void n_searchRootFoldersTest() throws Exception {
        logger.info("#########################ROOT_FOLDERS : {}\n", this.folderDAO.searchSubFoders(54l));
    }

    @Test
    public void o_findByObjectIdentityTest() throws Exception {
        List<AclEntry> entries = this.entryDAO.findByObjectIdentity(104l);
        for (AclEntry entry : entries) {
            logger.info("{}\n", entry.getAceOrder());
        }
    }

    @Test
    public void p_searchCSWServersTest() throws Exception {
        logger.info("{}\n", this.serverDAO.searchPagebleServers(0, 10, "geoSDI_ws_test", CSW, "%test%"));
    }
}
