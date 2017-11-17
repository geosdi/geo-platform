package org.geosdi.geoplatform.core.dao;

import org.geosdi.geoplatform.core.acl.AclEntry;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles(value = {"jpa"})
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
    public void findAllAccountsTest() throws Exception {
        List<GPAccount> accounts = this.accountDAO.findAll();
        logger.info("############################FOUNDS {} - Accounts.", accounts.size());
    }

    @Test
    public void findByOrganizationNameTest() throws Exception {
        List<GPAccount> accounts = this.accountDAO.findByOrganization("geoSDI");
        logger.info("###########################FOUNDS : {} - Accounts.", accounts.size());
    }

    @Test
    public void findAccountByUserNameTest() throws Exception {
        logger.info("################################FOUND : {}\n", this.accountDAO.findByUsername("admin"));
    }

    @Test
    public void findAccountByEmailTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FOUND : {}\n", this.accountDAO.findByEmail("admin@geosdi.org"));
    }

    @Test
    public void countAccountsTest() throws Exception {
        logger.info("###############################FOUNDS : {}\n", this.accountDAO.countAccounts("%user%"));
    }

    @Test
    public void findLayersByProjectIDTest() throws Exception {
        List<String> dataSources = this.layerDAO.findDistinctDataSourceByProjectId(1l);
        logger.info("###############################LAYERS_DATA_SOURCES : {}\n", dataSources);
    }

    @Test
    public void findLayerByNameTest() throws Exception {
        GPLayer layer = this.layerDAO.findByLayerName("test");
        Assert.assertNull(layer);
        logger.info("{}\n", this.layerDAO.persistCheckStatusLayer(1l, Boolean.TRUE));
    }

    @Test
    public void findShortByAccountNaturalIDTest() throws Exception {
        logger.info("###########################{}\n", this.authorityDAO.findShortByAccountNaturalID("admin"));
    }

    @Test
    public void countAccoutProjectTest() throws Exception {
        logger.info("########################ACCOUNT_PROJECTS_FOUND : {}\n", this.accountProjectDAO.count(2l,
                "%user%"));
    }

    @Test
    public void searchPagebleUsersByOrganizationTest() throws Exception {
        logger.info("{}\n", this.accountDAO.searchPagebleUsersByOrganization(0, 4, "geoSDI",
                52l, null).size());
        logger.info("{}\n", this.accountDAO.searchPagebleUsersByOrganization(2, 4, "geoSDI",
                52l, null).size());
    }

    @Test
    public void countAccountByOrganizationTest() throws Exception {
        logger.info("{}\n", this.accountDAO.countUsers("geoSDI", null));
    }

    @Test
    public void searchRootFoldersTest() throws Exception {
        logger.info("#########################ROOT_FOLDERS : {}\n", this.folderDAO.searchSubFoders(54l));
    }

    @Test
    public void findByObjectIdentityTest() throws Exception {
        List<AclEntry> entries = this.entryDAO.findByObjectIdentity(104l);
        for (AclEntry entry : entries) {
            logger.info("{}\n", entry.getAceOrder());
        }
    }

    @Test
    public void searchCSWServersTest() throws Exception {
        logger.info("{}\n", this.serverDAO.searchPagebleServers(0, 10, "geoSDI_ws_test",
                GPCapabilityType.CSW, "%test%"));
    }
}
