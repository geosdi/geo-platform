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
package org.geosdi.geoplatform.initializer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.WKTReader;
import org.geosdi.geoplatform.core.acl.*;
import org.geosdi.geoplatform.core.acl.dao.*;
import org.geosdi.geoplatform.core.dao.*;
import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.core.model.enums.GrantType;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.geosdi.geoplatform.jasypt.support.GPDigesterConfigurator;
import org.geotools.data.ows.Layer;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Initializer-Test.xml",
    "classpath:applicationContext.xml"})
public abstract class BaseInitializerTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    protected GPAccountDAO accountDAO;
    //
    @Autowired
    protected GPAccountProjectDAO accountProjectDAO;
    //
    @Autowired
    protected GPProjectDAO projectDAO;
    //
    @Autowired
    protected GPFolderDAO folderDAO;
    //
    @Autowired
    protected GPLayerDAO layerDAO;
    //
//    @Autowired
//    protected GPStyleDAO styleDAO;
    //
    @Autowired
    protected GPServerDAO serverDAO;
    //
    @Autowired
    protected GPAuthorityDAO authorityDAO;
    //
    @Autowired
    protected GPOrganizationDAO organizationDAO;
    //
    @Autowired
    protected GSAccountDAO gsAccountDAO;
    //
    @Autowired
    protected GSResourceDAO gsResourceDAO;
    //
    @Autowired
    protected AclClassDAO classDAO;
    //
    @Autowired
    protected AclSidDAO sidDAO;
    //
    @Autowired
    protected AclObjectIdentityDAO objectIdentityDAO;
    //
    @Autowired
    protected AclEntryDAO entryDAO;
    //
    @Autowired
    protected GuiComponentDAO guiComponentDAO;
    //
    @Autowired
    protected GPMessageDAO messageDAO;
    //
    @Autowired
    protected GPDigesterConfigurator gpDigesterSHA1;
    //
    protected GPOrganization organizationTest;
    protected GPUser adminTest;
    protected GPUser userTest;
    protected GPUser viewerTest;
    protected GPUser serviceTest;
    protected GPUser gsUserTest;
    protected GPProject adminProject;
    protected GPProject userProject;
    protected GPProject viewerProject;
    protected GPProject gsUserProject;
    // ACL
    private static final String usernameSuperUserTestAcl = "super_user_test_acl";
    private AclClass gcClass;
    private AclSid superUser;
    private AclSid admin;
    private AclSid user;
    private AclSid viewer;
    //
    private static final String gsAccountUsername = "gsuser";

    protected void removeAll() {
//        this.removeAllStyles();
        this.removeAllLayers();
        this.removeAllFolders();
        this.removeAllAccountProject();
        this.removeAllProjects();
        this.removeAllMessages();
        this.removeAllAuthorities();
        this.removeAllGSAccounts();
        this.removeAllAccounts();
        this.removeAllACL();
        this.removeAllServers();
        this.removeAllOrganizations();
    }

    //<editor-fold defaultstate="collapsed" desc="Remove all data">
//    private void removeAllStyles() {
//        List<GPStyle> styles = styleDAO.findAll();
//        for (GPStyle style : styles) {
//            logger.trace("\n*** Style to REMOVE:\n{}\n***", style);
//            boolean removed = styleDAO.remove(style);
//            Assert.assertTrue("Old Style NOT removed", removed);
//        }
//    }
//    
    private void removeAllLayers() {
        List<GPLayer> layers = layerDAO.findAll();
        for (GPLayer layer : layers) {
            logger.trace("\n*** Layer to REMOVE:\n{}\n***", layer);
            boolean removed = layerDAO.remove(layer);
            Assert.assertTrue("Old Layer NOT removed", removed);
        }
    }

    private void removeAllFolders() {
        List<GPFolder> folders = folderDAO.findAll();
        // Folders sorted in descending order (wrt position)
        final Comparator comp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                GPFolder folder1 = (GPFolder) o1;
                GPFolder folder2 = (GPFolder) o2;
                return folder1.getPosition() - folder2.getPosition();
            }
        };
        Collections.sort(folders, comp);
        // Delete before the sub-folders
        for (GPFolder folder : folders) {
            logger.trace("\n*** Folder to REMOVE:\n{}\n***", folder);
            boolean removed = folderDAO.remove(folder);
            Assert.assertTrue("Old Folder NOT removed\n" + folder + "\n", removed);
        }
    }

    private void removeAllAccountProject() {
        List<GPAccountProject> accountProjectList = accountProjectDAO.findAll();
        for (GPAccountProject up : accountProjectList) {
            logger.trace("\n*** AccountProject to REMOVE:\n{}\n***", up);
            boolean removed = accountProjectDAO.remove(up);
            Assert.assertTrue("Old AccountProject NOT removed", removed);
        }
    }

    private void removeAllProjects() {
        List<GPProject> projects = projectDAO.findAll();
        for (GPProject project : projects) {
            logger.trace("\n*** project to REMOVE:\n{}\n***", project);
            boolean removed = projectDAO.remove(project);
            Assert.assertTrue("Old project NOT removed", removed);
        }
    }

    private void removeAllMessages() {
        List<GPMessage> messages = messageDAO.findAll();
        for (GPMessage message : messages) {
            logger.trace("\n*** message to REMOVE:\n{}\n***", message);
            boolean removed = messageDAO.remove(message);
            Assert.assertTrue("Old message NOT removed", removed);
        }
    }

    protected void removeAllAuthorities() {
        List<GPAuthority> authorities = authorityDAO.findAll();
        for (GPAuthority authority : authorities) {
            logger.trace("\n*** Authority to REMOVE:\n{}\n***", authority);
            boolean removed = authorityDAO.remove(authority);
            Assert.assertTrue("Old Authority NOT removed", removed);
        }
    }

    private void removeAllGSAccounts() {
        List<GSAccount> accountList = gsAccountDAO.findAll();
        for (GSAccount account : accountList) {
            logger.trace("\n*** GSAccount to REMOVE:\n{}\n***", account);
            boolean removed = gsAccountDAO.remove(account);
            Assert.assertTrue("Old GSAccount NOT removed", removed);
        }
    }

    private void removeAllAccounts() {
        List<GPAccount> accounts = accountDAO.findAll();
        for (GPAccount account : accounts) {
            logger.trace("\n*** Account to REMOVE:\n{}\n***", account);
            boolean removed = accountDAO.remove(account);
            Assert.assertTrue("Old Account NOT removed", removed);
        }
    }

    private void removeAllServers() {
        List<GeoPlatformServer> servers = serverDAO.findAll();
        for (GeoPlatformServer server : servers) {
            logger.debug("\n*** Server to REMOVE:\n{}\n***", server);
            boolean ret = serverDAO.remove(server);
            Assert.assertTrue("Old Server NOT removed", ret);
        }
    }

    protected void removeAllOrganizations() {
        List<GPOrganization> organizations = organizationDAO.findAll();
        for (GPOrganization organization : organizations) {
            logger.trace("\n*** Organization to REMOVE:\n{}\n***", organization);
            boolean removed = organizationDAO.remove(organization);
            Assert.assertTrue("Old Organization NOT removed", removed);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Remove all ACL data">
    private void removeAllACL() {
        this.removeAllEntries();
        this.removeAllObjectIdentities();
        this.removeAllSids();
        this.removeAllClasses();
        this.removeAllGuiComponents();
    }

    private void removeAllEntries() {
        List<AclEntry> entries = entryDAO.findAll();
        for (AclEntry e : entries) {
            logger.trace("\n*** AclEntry to REMOVE:\n{}\n***", e);
            boolean removed = entryDAO.remove(e);
            Assert.assertTrue("Old AclEntry NOT removed", removed);
        }
    }

    private void removeAllObjectIdentities() {
        List<AclObjectIdentity> objectIdentities = objectIdentityDAO.findAll();
        for (AclObjectIdentity oi : objectIdentities) {
            logger.trace("\n*** AclObjectIdentity to REMOVE:\n{}\n***", oi);
            boolean removed = objectIdentityDAO.remove(oi);
            Assert.assertTrue("Old AclObjectIdentity NOT removed", removed);
        }
    }

    private void removeAllSids() {
        List<AclSid> sids = sidDAO.findAll();
        for (AclSid s : sids) {
            logger.trace("\n*** AclSid to REMOVE:\n{}\n***", s);
            boolean removed = sidDAO.remove(s);
            Assert.assertTrue("Old AclSid NOT removed", removed);
        }
    }

    private void removeAllClasses() {
        List<AclClass> classes = classDAO.findAll();
        for (AclClass c : classes) {
            logger.trace("\n*** AclClass to REMOVE:\n{}\n***", c);
            boolean removed = classDAO.remove(c);
            Assert.assertTrue("Old AclClass NOT removed", removed);
        }
    }

    private void removeAllGuiComponents() {
        List<GuiComponent> guiComponents = guiComponentDAO.findAll();
        for (GuiComponent gc : guiComponents) {
            logger.trace("\n*** GuiComponent to REMOVE:\n{}\n***", gc);
            boolean removed = guiComponentDAO.remove(gc);
            Assert.assertTrue("Old GuiComponent NOT removed", removed);
        }
    }
    //</editor-fold>

    protected void insertData() throws ParseException {
        this.insertOrganizations();
        this.insertServers();
        this.insertAccounts();
        this.insertGuiComponents();
        this.insertProjects();
        this.insertMessages();
        this.insertFoldersAndLayers();
        this.insertGPAccessInfoTest();
    }

    //<editor-fold defaultstate="collapsed" desc="Insert data">
    private void insertOrganizations() {
        organizationTest = this.createOwnOrganization();
        organizationDAO.persist(organizationTest);
        logger.debug("\n*** Organization SAVED:\n{}\n***", organizationTest);
    }

    private void insertServers() {
        // WMS
        GeoPlatformServer server1WMS = createServer1WMS();
        GeoPlatformServer server2WMS = createServer2WMS();
        serverDAO.persist(server1WMS, server2WMS);
        logger.debug("\n*** SAVED WMS Server:\n{}\n***", server1WMS);
        logger.debug("\n*** SAVED WMS Server:\n{}\n***", server2WMS);
        // CSW
        GeoPlatformServer server1CSW = createServer1CSW();
        serverDAO.persist(server1CSW);
        logger.debug("\n*** SAVED CSW Server:\n{}\n***", server1CSW);
        //
//        this.insertDummyCSWServer();
    }

    private GeoPlatformServer createServer1WMS() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://150.145.141.124/geoserver/wms");
        server.setName("imaa.geosdi.org");
        server.setAliasName("geoSdi on IMAA");
        server.setServerType(GPCapabilityType.WMS);
        server.setOrganization(organizationTest);
        return server;
    }

    private GeoPlatformServer createServer2WMS() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://dpc.geosdi.org/geoserver/wms");
        server.setName("dpc.geosdi.org");
        server.setAliasName("DPC on geosdi");
        server.setServerType(GPCapabilityType.WMS);
        server.setOrganization(organizationTest);
        return server;
    }
//
//    private GeoPlatformServer createServer3WMS() {
//        GeoPlatformServer server = new GeoPlatformServer();
//        server.setServerUrl("https://earthbuilder.google.com/13496919088645259843-03170733828027579281-4/wms/?request=GetCapabilities");
//        server.setName("earthbuilder.google.com");
//        server.setAliasName("EARTHBUILDER");
//        server.setServerType(GPCapabilityType.WMS);
//        server.setOrganization(organizationTest);
//        return server;
//    }
//

    private GeoPlatformServer createServer1CSW() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://catalog.geosdi.org/geonetwork/srv/en/csw");
        server.setName("csw.geosdi.org");
        server.setAliasName("CSW on geosdi");
        server.setServerType(GPCapabilityType.CSW);
        server.setOrganization(organizationTest);
        return server;
    }
//
//    private void insertDummyCSWServer() {
//        for (int i = 10; i <= 99; i++) {
//            GeoPlatformServer server = new GeoPlatformServer();
//            server.setTitle("Title_" + i);
//            server.setAliasName("Z_Alias_" + i);
//            server.setServerUrl("http://csw-test/" + i);
//            server.setServerType(GPCapabilityType.CSW);
//            server.setOrganization(organizationTest);
//            serverDAO.persist(server);
//        }
//    }
//

    private void insertAccounts() {
        // GUI test
        this.adminTest = this.insertUser("admin", organizationTest, Boolean.TRUE, GPRole.ADMIN);
        this.userTest = this.insertUser("user", organizationTest, Boolean.TRUE, GPRole.USER);
        this.viewerTest = this.insertUser("viewer", organizationTest, Boolean.TRUE, GPRole.VIEWER);
        this.serviceTest = this.insertUser("service", organizationTest, Boolean.TRUE, GPRole.ADMIN);
        this.gsUserTest = this.insertUser(gsAccountUsername, organizationTest, Boolean.TRUE, GPRole.ADMIN);
        // ACL
        this.insertUser(usernameSuperUserTestAcl, organizationTest, Boolean.TRUE, GPRole.ADMIN, GPRole.USER);
        this.insertUser("admin_acl_test", organizationTest, Boolean.TRUE, GPRole.ADMIN);
        this.insertUser("user_acl_test", organizationTest, Boolean.TRUE, GPRole.USER);
        this.insertUser("user_not_enabled", organizationTest, Boolean.FALSE, GPRole.USER);
    }

    private void insertProjects() {
        this.adminProject = this.createProject("admin_project", false, 0,
                new Date(System.currentTimeMillis()));
        this.userProject = this.createProject("user_project", false, 0,
                new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5)));
        this.viewerProject = this.createProject("viewer_project", false, 0,
                new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1)));
        this.gsUserProject = this.createProject("gp_user_project", false, 0,
                new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(3)));
        projectDAO.persist(adminProject, userProject, viewerProject, gsUserProject);
        //
        this.insertBindingUserProject(adminTest, adminProject,
                BasePermission.ADMINISTRATION.getMask(), true);
        this.insertBindingUserProject(userTest, adminProject,
                BasePermission.READ.getMask(), false);
        this.insertBindingUserProject(userTest, userProject,
                BasePermission.ADMINISTRATION.getMask(), true);
        this.insertBindingUserProject(viewerTest, viewerProject,
                BasePermission.ADMINISTRATION.getMask(), true);
        this.insertBindingUserProject(gsUserTest, gsUserProject,
                BasePermission.ADMINISTRATION.getMask(), true);
        //
        accountDAO.merge(adminTest, userTest, viewerTest, gsUserTest);
    }

    private void insertMessages() {
        GPMessage message = new GPMessage();
        message.setRecipient(userTest);
        message.setSender(adminTest);
        message.setCreationDate(new Date(System.currentTimeMillis()));
        message.setRead(false);
        message.setSubject("Project Shared");
        message.setText("\"" + adminTest.getName() + "\" shared with you the \""
                + adminProject.getName() + "\" project.");
        message.addCommand(GPMessageCommandType.OPEN_PROJECT);

        messageDAO.persist(message);
    }

    private void insertFoldersAndLayers() {
        // Projects of admin
        for (int i = 1; i <= 41; i++) {
            GPProject projectIth = this.createProject("project_admin_k_" + i, false,
                    i, new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(i)));
            projectDAO.persist(projectIth);
            this.insertBindingUserProject(adminTest, projectIth,
                    BasePermission.ADMINISTRATION.getMask(), false);
        }

        // Project of user -> root folder: "server layer"
        // TODO Uncomment for exception: java.lang.Object; cannot be cast to org.geotools.data.ows.Capabilities
//        List<Layer> layerList = this.loadLayersFromServer();
//        GPFolder folderServerLayer = this.createFolder("server layer", userProject, null, layerList.size() + 1);
//        folderServerLayer.setNumberOfDescendants(layerList.size());
//        List<GPRasterLayer> layers = this.loadRasterLayer(layerList, folderServerLayer, userProject, layerList.size());
//        folderDAO.persist(folderServerLayer);
//        layerDAO.persist(layers.toArray(new GPRasterLayer[layers.size()]));
//        //
//        userProject.setNumberOfElements(layerList.size());
//        projectDAO.merge(userProject);

        // Project of viewer -> root folders: "only folders, layers"
        GPFolder onlyFolders = this.createFolder("only folders", viewerProject, null, 6);
        // "only folders" ---> "empty subfolder A"
        GPFolder emptySubFolderA = this.createFolder("empty subfolder A", viewerProject, onlyFolders, 5);
        // "only folders" ---> "empty subfolder B"
        GPFolder emptySubFolderB = this.createFolder("empty subfolder B", viewerProject, onlyFolders, 4);
        // "layers"
        GPFolder layerFolder = this.createFolder("layers", viewerProject, null, 3);
        // "layers" ---> _rasterLayer_ ---> Styles
        GPRasterLayer rasterLayer = this.createRasterLayer(layerFolder, viewerProject, 2);
//        GPStyle rasterLayerStyle1 = this.createStyle("style 1", rasterLayer);
//        GPStyle rasterLayerStyle2 = this.createStyle("style 2", rasterLayer);
        // ---> "layers" --> _vectorLayer_
        GPVectorLayer vectorLayer = this.createVectorLayer(layerFolder, viewerProject, 1);
        //
        onlyFolders.setNumberOfDescendants(2);
        layerFolder.setNumberOfDescendants(2);
        folderDAO.persist(onlyFolders, emptySubFolderA, emptySubFolderB, layerFolder);
        layerDAO.persist(rasterLayer, vectorLayer);
//        styleDAO.persist(rasterLayerStyle1, rasterLayerStyle2);
        //
        viewerProject.setNumberOfElements(6);
        projectDAO.merge(viewerProject);
    }

    private void cloneProject(){


/*        GPProject gpProject = projectDAO.findByProjectName("project_admin_k_1");
        GPProject gpProjectCloned = GPProjectBinder.getGProjectBinder().withFrom(gpProject).bind();
        projectDAO.persist(gpProjectCloned);
        this.insertBindingUserProject(adminTest, gpProjectCloned,
                BasePermission.ADMINISTRATION.getMask(), false);*/

    }

    private void insertGPAccessInfoTest() {
        GSAccount gsAccount = this.generateGSAccount(gsAccountUsername);
        GSResource resource = this.generateResource(gsAccount);
        gsUserTest.setGsAccount(gsAccount);
        this.gsAccountDAO.persist(gsAccount);
        this.gsResourceDAO.persist(resource);
        accountDAO.merge(gsUserTest);
    }

    private GSAccount generateGSAccount(String username) {
        GSAccount account = new GSAccount();
        account.setGsuser(username);
        account.setAuthkey(UUID.randomUUID().toString());
        return account;
    }

    private GSResource generateResource(GSAccount account) {
        GSResource resource = new GSResource();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        WKTReader reader = new WKTReader(geometryFactory);
        MultiPolygon multiPolygon = null;
        try {
            //"District of Columbia" restrictions
            multiPolygon = (MultiPolygon) reader.read(
                    "MULTIPOLYGON(((-77.008232 38.966557,-76.911209 38.889988,-77.045448 38.78812,-77.035248 38.813915,-77.045189 38.829365,-77.040405 38.838413,-77.039078 38.862431,-77.067886 38.886101,-77.078949 38.9156,-77.122627 38.93206,-77.042389 38.993431,-77.008232 38.966557)))");
        } catch (com.vividsolutions.jts.io.ParseException ex) {
            logger.error("Error to generate multipolygon: " + ex);
        }
        resource.setArea(multiPolygon);
        resource.setGrant(GrantType.ALLOW);
        resource.setWorkspace("topp");
        resource.setLayerName("states");
        resource.setAttributes("STATE_NAME,MALE,FEMALE");
        //simple cql filter accourding to Multipolygon restriction
        resource.setCqlFilterRead("STATE_NAME='Virginia'");
        resource.setDefaultStyle("polygon");
        resource.setGsAccount(account);
        return resource;
    }

    private GPOrganization createOwnOrganization() {
        GPOrganization organization = new GPOrganization("geoSDI");
        organization.setDescription("geoSDI realizza e distribuisce i migliori sistemi software geospaziali web based utilizzando un approccio open source.");
        organization.setUrl("http://www.geosdi.org");
        organization.setTelephone("+39.0971.427305");
        organization.setAddress("C.da S. Loja Tito Scalo (PZ) - Basilicata 85050 Italy");
        return organization;
    }

    protected GPUser insertUser(String username, GPOrganization organization, boolean enabled, GPRole... roles) {
        GPUser newUser = this.createUser(username, organization, enabled);
        accountDAO.persist(newUser);
        logger.debug("\n*** User SAVED:\n{}\n***", newUser);

        if (roles.length > 0) {
            List<GPAuthority> authorities = this.createAuthorities(newUser, roles);
            newUser.setGPAuthorities(authorities);

            for (GPAuthority authority : authorities) {
                authorityDAO.persist(authority);
                logger.debug("\n*** Authority SAVED:\n{}\n***", authority);
            }
        }

        return newUser;
    }

    protected GPApplication insertApplication(String appId) {
        GPApplication application = this.createApplication(appId);
        accountDAO.persist(application);
        logger.debug("\n*** Application SAVED:\n{}\n***", application);

        return application;
    }

    private List<GPAuthority> createAuthorities(GPAccount account, GPRole... roles) {
        List<GPAuthority> authorities = Lists.<GPAuthority>newArrayList();
        for (GPRole role : roles) {
            GPTrustedLevel trustedLevel = this.getTrustedLevelByRole(role);
            authorities.add(new GPAuthority(account, trustedLevel, role.getRole()));
        }
        return authorities;
    }

    private GPTrustedLevel getTrustedLevelByRole(GPRole role) {
        switch (role) {
            case ADMIN:
                return GPTrustedLevel.FULL;
            case USER:
                return GPTrustedLevel.RESTRICT;
            case VIEWER:
                return GPTrustedLevel.NONE;
            default:
                return GPTrustedLevel.NONE;
        }
    }

    private GPUser createUser(String username, GPOrganization organization, boolean enabled) {
        GPUser newUser = new GPUser();
        newUser.setOrganization(organization);
        newUser.setUsername(username);
        if (username.contains("_")) {
            newUser.setPassword(this.gpDigesterSHA1.digest("pwd_" + username));
        } else { // User for GUI test
            newUser.setPassword(this.gpDigesterSHA1.digest(username));
        }
        newUser.setName("Complete name of " + username);
        newUser.setEmailAddress(username + "@geosdi.org");
        newUser.setEnabled(enabled);
        newUser.setSendEmail(true);
        return newUser;
    }

    private GPApplication createApplication(String appID) {
        GPApplication application = new GPApplication();
        application.setAppID(appID);
        application.setOrganization(organizationTest);
        application.setEnabled(true);
        return application;
    }

    protected GPFolder createFolder(String name, GPProject project,
            GPFolder parent, int position) {
        GPFolder folder = new GPFolder();
        folder.setName(name);
        folder.setProject(project);
        folder.setParent(parent);
        folder.setPosition(position);
        return folder;
    }

    protected GPProject createProject(String name, boolean isShared,
            int numberOfElements, Date creationalDate) {
        GPProject project = new GPProject();
        project.setName(name);
        project.setShared(isShared);
        project.setNumberOfElements(numberOfElements);
        project.setCreationDate(creationalDate);
        return project;
    }

    protected void insertBindingUserProject(GPUser user, GPProject project,
            int permissionMask, boolean defaultProject) {
        GPAccountProject userProjects = new GPAccountProject();
        userProjects.setAccountAndProject(user, project);
        userProjects.setPermissionMask(permissionMask);
        userProjects.setDefaultProject(defaultProject);
        accountProjectDAO.persist(userProjects);
    }

    protected GPRasterLayer createRasterLayer(GPFolder folder, GPProject project,
            int position) {
        String name = "deagostini_ita_250mila";
        // GPRasterLayer
        GPRasterLayer raster = new GPRasterLayer();
        raster.setTitle("StratiDiBase:" + name);
        raster.setName(name);
        raster.setAbstractText("abstract:" + name);
        raster.setFolder(folder);
        raster.setProject(project);
        raster.setPosition(position);
        raster.setSrs("EPSG:4326");
        raster.setUrlServer("http://dpc.geosdi.org/geoserver/wms");
        raster.setBbox(new GPBBox(6.342, 35.095, 19.003, 47.316));
        raster.setLayerType(GPLayerType.WMS);
        // GPLayerInfo
        GPLayerInfo info = new GPLayerInfo();
        List<String> keywords = Lists.<String>newArrayList();
        keywords.add("IGM");
        info.setKeywords(keywords);
        info.setQueryable(true);
        raster.setLayerInfo(info);
        // Styles
        List<String> styles = Lists.<String>newArrayList();
        styles.add("Default Style");
        styles.add("Style k");
        raster.setStyles(styles);
        return raster;
    }

//    private GPStyle createStyle(String name, GPRasterLayer layer) {
//        GPStyle style = new GPStyle();
//        style.setName(name);
//        style.setTitle("The " + name);
//        style.setAbstractText("Abstract for " + name);
//        style.setLegendURL("http://www.geosdi.org/"
//                + name.replaceAll("[ ]+", "-"));
//        style.setLayer(layer);
//        return style;
//    }
//    
    protected GPVectorLayer createVectorLayer(GPFolder folder, GPProject project, int position) {
        GPVectorLayer vector = new GPVectorLayer();
        vector.setName("Name of vectorLayer");
        vector.setTitle("Title of vectorLayer");
        vector.setFolder(folder);
        vector.setProject(project);
        vector.setPosition(position);
        vector.setAbstractText("AbstractText of vectorLayer");
        vector.setSrs("EPSG:4326");
        vector.setUrlServer("http://imaa.geosdi.org/geoserver/wms");
        vector.setBbox(new GPBBox(1.1, 2.2, 3.3, 3.3));
        vector.setLayerType(GPLayerType.MULTIPOLYGON);
        vector.setChecked(true);
        return vector;
    }

    private List<GPRasterLayer> loadRasterLayer(List<Layer> layers,
            GPFolder folder, GPProject project, int position) {
        List<GPRasterLayer> rasterLayers = Lists.<GPRasterLayer>newArrayListWithCapacity(layers.size());

        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);

            GPRasterLayer raster = new GPRasterLayer();
            raster.setName(layer.getName());
            raster.setTitle(layer.getTitle());
            raster.setAbstractText(layer.get_abstract());
            raster.setSrs(layer.getSrs().toString());
            raster.setBbox(new GPBBox(
                    layer.getLatLonBoundingBox().getMinX(),
                    layer.getLatLonBoundingBox().getMinY(),
                    layer.getLatLonBoundingBox().getMaxX(),
                    layer.getLatLonBoundingBox().getMaxY()));

            GPLayerInfo infoLayer = new GPLayerInfo();
            infoLayer.setQueryable(layer.isQueryable());
            if (layer.getKeywords() != null) {
                List<String> keywordList = Arrays.asList(layer.getKeywords());
                if (keywordList.size() > 0) {
                    infoLayer.setKeywords(keywordList);
                }
            }
            raster.setLayerInfo(infoLayer);

            raster.setFolder(folder);
            raster.setProject(project);
            raster.setPosition(position--);
            raster.setLayerType(GPLayerType.WMS);
            raster.setUrlServer("http://imaa.geosdi.org/geoserver/wms");
            if (i < 5) {
                raster.setChecked(true);
            }
            rasterLayers.add(raster);
        }

        return rasterLayers;
    }

    private void insertGuiComponents() {
        // Unique class of Object Identities
        this.gcClass = new AclClass(GuiComponent.class.getName());
        //
        logger.debug("\n*** AclClass to INSERT:\n{}\n***", gcClass);
        classDAO.persist(gcClass);

        this.createSids();

        Map<String, GuiComponent> gcMap = this.createGuiComponents();

        Map<String, AclObjectIdentity> objIdMap = this.createObjectIdentities(gcMap);

        this.createEntries(objIdMap);
    }

    private void createSids() {
        // Owner of all Object Identities
        this.superUser = new AclSid(true, usernameSuperUserTestAcl);
        // Users of interest
        this.admin = new AclSid(false, GPRole.ADMIN.getRole(), organizationTest);
        this.user = new AclSid(false, GPRole.USER.getRole(), organizationTest);
        this.viewer = new AclSid(false, GPRole.VIEWER.getRole(), organizationTest);
        //
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", superUser);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", admin);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", user);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", viewer);
        //
        sidDAO.persist(superUser, admin, user, viewer);
    }

    private Map<String, GuiComponent> createGuiComponents() {
        Map<String, GuiComponent> gcMap = Maps.<String, GuiComponent>newHashMap();

        for (String ID : GuiComponentIDs.LIST_ALL) {
            gcMap.put(ID, new GuiComponent(ID));
        }

        guiComponentDAO.persist(gcMap.values().toArray(new GuiComponent[gcMap.size()]));

        return gcMap;
    }

    private Map<String, AclObjectIdentity> createObjectIdentities(
            Map<String, GuiComponent> gcMap) {
        Map<String, AclObjectIdentity> objIdMap = Maps.<String, AclObjectIdentity>newHashMap();

        for (String componentID : GuiComponentIDs.LIST_ALL) {
            Long id = gcMap.get(componentID).getId();
            // SuperUser is the owner of all Object Identities
            objIdMap.put(componentID, new AclObjectIdentity(gcClass, id, superUser));
        }

        objectIdentityDAO.persist(objIdMap.values().toArray(new AclObjectIdentity[objIdMap.size()]));

        return objIdMap;
    }

    private void createEntries(Map<String, AclObjectIdentity> objIdMap) {
        // ACE
        int enable = GeoPlatformPermission.ENABLE.getMask();
        //
        Map<String, AclEntry> entriesMap = Maps.<String, AclEntry>newHashMap();
        // Admin
        for (String componentID : GuiComponentIDs.LIST_ALL) {
            entriesMap.put(GPRole.ADMIN + componentID,
                    new AclEntry(objIdMap.get(componentID), 1, admin, enable, true));
        }
        // User
        for (Map.Entry<String, Boolean> e : GuiComponentIDs.MAP_USER.entrySet()) {
            if (e.getValue() != null) {
                entriesMap.put(GPRole.USER + e.getKey(),
                        new AclEntry(objIdMap.get(e.getKey()), 2, user, enable, e.getValue()));
            }
        }
        // Viewer
        for (Map.Entry<String, Boolean> e : GuiComponentIDs.MAP_VIEWER.entrySet()) {
            if (e.getValue() != null) {
                // Ace Order is 3 because the entries of admin and user should be added before
                entriesMap.put(GPRole.VIEWER + e.getKey(),
                        new AclEntry(objIdMap.get(e.getKey()), 3, viewer, enable, e.getValue()));
            }
        }
        //
        entryDAO.persist(entriesMap.values().toArray(new AclEntry[entriesMap.size()]));
    }
    //</editor-fold>
}