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
package org.geosdi.geoplatform.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.folder.InsertFolderRequest;
import org.geosdi.geoplatform.request.layer.InsertLayerRequest;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.model.Permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.junit.Assert.assertTrue;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class ServiceTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GeoPlatformService gpWSClient;
    // Organization
    protected GPOrganization organizationTest;
    protected static final String domainNameTest = "geosdi-test.org";
    //
    protected List<String> layerInfoKeywords;
    protected long idUserTest = -1;
    //
    protected final String serverUrlTest = "http://map.serverNameTest.foo";
    protected long idServerTest = -1;
    protected final String serverUrlGeoSDI = "http://imaa.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities";
    protected long idServerGeoSDI = -1;

    /**
     * The listener will inject this dependency.
     *
     * @param gpWSClient
     */
    public void setGeoplatformServiceClient(GeoPlatformService gpWSClient) {
        this.gpWSClient = gpWSClient;
    }

    @Before
    public void setUp() throws Exception {
        logger.trace("\n\n\t@@@@@@@ {}.setUp @@@@@@\n\n", this.getClass().getSimpleName());
        // Insert Organization
        this.setUpOrganization();
    }

    protected abstract void setUpOrganization() throws IllegalParameterFault;

    @After
    public void tearDown() {
        logger.trace("\n\t@@@ {}.tearDown @@@", this.getClass().getSimpleName());
        // Delete Organization
        this.deleteOrganization(organizationTest.getId());
    }

    /**
     * Create and insert a User.
     *
     * @param username
     * @param organization
     * @param roles
     *
     * @return {@link Long} idUser
     *
     * @throws org.geosdi.geoplatform.exception.IllegalParameterFault
     */
    protected Long createAndInsertUser(String username, GPOrganization organization, GPRole... roles) throws IllegalParameterFault {
        GPUser user = this.createUser(username, organization, roles);
        logger.debug("\n*** GPUser to INSERT:\n{}\n***", user);
        long idUser = gpWSClient.insertAccount(new InsertAccountRequest(user, FALSE));
        logger.debug("\n*** Id ASSIGNED at the User in the DB: {} ***", idUser);
        assertTrue("Id ASSIGNED at the User in the DB", idUser > 0);
        return idUser;
    }

    protected GPUser createUser(String username, GPOrganization organization, GPRole... roles) {
        GPUser user = new GPUser();
        user.setOrganization(organization);
        user.setUsername(username);
        user.setEmailAddress(username + "@" + domainNameTest);
        user.setName("Complete name of " + username);
        user.setEnabled(true);
        user.setPassword(username);
        user.setSendEmail(false);

        if (roles.length > 0) {
            List<GPAuthority> authorities = this.createAuthorities(roles);
            user.setGPAuthorities(authorities);
        }
        return user;
    }

    private List<GPAuthority> createAuthorities(GPRole... roles) {
        List<GPAuthority> authorities = new ArrayList<GPAuthority>();
        for (GPRole role : roles) {
            GPAuthority authority = new GPAuthority();
            authority.setAuthority(role.getRole());

            GPTrustedLevel trustedLevel = this.getTrustedLevelByRole(role);
            authority.setTrustedLevel(trustedLevel);

            authorities.add(authority);
        }
        return authorities;
    }

    protected GPTrustedLevel getTrustedLevelByRole(GPRole role) {
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

    /**
     * Delete (with assert) an Account.
     */
    protected void deleteAccount(long accountID) {
        try {
            boolean check = gpWSClient.deleteAccount(accountID);
            assertTrue(
                    "Account with ID = " + accountID + " has not been eliminated",
                    check);
        } catch (Exception e) {
            Assert.fail("Error while deleting Account with ID: " + accountID);
        }
    }

    /**
     * Delete (with assert) an Organization.
     */
    protected void deleteOrganization(long organizationID) {
        try {
            boolean check = gpWSClient.deleteOrganization(organizationID);
            assertTrue(
                    "Organization with ID = " + organizationID + " has not been eliminated",
                    check);
        } catch (Exception e) {
            Assert.fail(
                    "Error while deleting Organization with ID: " + organizationID);
        }
    }

    /**
     * Delete (with assert) a Folder.
     *
     * @param idFolder
     */
    protected void deleteFolder(long idFolder) {
        try {
            boolean check = gpWSClient.deleteFolder(idFolder);
            assertTrue(
                    "Folder with id = " + idFolder + " has not been eliminated",
                    check);
        } catch (Exception e) {
            Assert.fail("Error while deleting Folder with Id: " + idFolder);
        }
    }

    protected long createAndInsertProject(String name, boolean shared,
            int numberOfElements, Date creationalDate)
            throws IllegalParameterFault {
        GPProject project = this.createProject(name, shared, numberOfElements,
                creationalDate);
        return gpWSClient.insertProject(project);
    }

    protected long createAndInsertAccountProject(GPAccount account,
            GPProject project,
            Permission permission) throws IllegalParameterFault {
        GPAccountProject userProject = new GPAccountProject();
        userProject.setAccountAndProject(account, project);
        userProject.setPermissionMask(permission.getMask());
        return gpWSClient.insertAccountProject(userProject);
    }

    protected long createAndInsertFolder(String folderName, GPProject project,
            int position, GPFolder parent) throws ResourceNotFoundFault,
            IllegalParameterFault {
        GPFolder folder = this.createFolder(folderName, project, position,
                parent);
        return gpWSClient.insertFolder(new InsertFolderRequest(project.getId(),
                folder));
    }

    protected long createAndInsertFolder(String folderName, GPProject project,
            int position, GPFolder parent, int numberOfDescendants)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = this.createFolder(folderName, project, position,
                parent);
        folder.setNumberOfDescendants(numberOfDescendants);
        return gpWSClient.insertFolder(new InsertFolderRequest(project.getId(),
                folder));
    }

    protected GPProject createProject(String name, boolean shared,
            int numberOfElements, Date creationalDate) {
        GPProject project = new GPProject();
        project.setName(name);
        project.setShared(shared);
        project.setNumberOfElements(numberOfElements);
        project.setCreationDate(creationalDate);
        return project;
    }

    protected GPFolder createFolder(String folderName, GPProject project,
            int position, GPFolder parent) {
        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        folder.setProject(project);
        folder.setPosition(position);
        folder.setParent(parent);
        return folder;
    }

    protected long createAndInsertRasterLayer(GPFolder folder, String title,
            String name,
            String abstractText, int position, String srs, String urlServer)
            throws IllegalParameterFault {
        GPRasterLayer rasterLayer = new GPRasterLayer();
        this.createLayer(rasterLayer, folder, title, name, abstractText,
                position, srs, urlServer);

        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setKeywords(layerInfoKeywords);
        layerInfo.setQueryable(false);
        rasterLayer.setLayerInfo(layerInfo);

        rasterLayer.setLayerType(GPLayerType.WMS);
        return gpWSClient.insertLayer(new InsertLayerRequest(rasterLayer));
    }

    protected long createAndInsertVectorLayer(GPFolder folder, String title,
            String name,
            String abstractText, int position, String srs, String urlServer)
            throws IllegalParameterFault {
        GPVectorLayer vectorLayer = new GPVectorLayer();
        this.createLayer(vectorLayer, folder, title, name, abstractText,
                position, srs, urlServer);

        vectorLayer.setLayerType(GPLayerType.POLYGON);
        return gpWSClient.insertLayer(new InsertLayerRequest(vectorLayer));
    }

    protected void createLayer(GPLayer layer, GPFolder folder, String title,
            String name, String abstractText, int position, String srs,
            String urlServer) {
        layer.setFolder(folder);
        layer.setProject(folder.getProject());

        layer.setTitle(title);
        layer.setName(name);
        layer.setAbstractText(abstractText);
        layer.setPosition(position);
        layer.setSrs(srs);
        layer.setUrlServer(urlServer);

        GPBBox bBox = new GPBBox(10, 10, 20, 20);
        layer.setBbox(bBox);
    }

    protected void insertMassiveUsers(String suffix) throws Exception {
        for (int i = 0; i < 30; i++) {
            createAndInsertUser("user" + i + "-" + suffix, organizationTest,
                    GPRole.VIEWER);
        }
    }

    /**
     * Create and insert (with assert) a Server.
     *
     * @param serverUrl
     * @param serverType
     * @param organization
     *
     * @return serverID
     */
    protected long createAndInsertServer(String serverUrl,
            GPCapabilityType serverType, GPOrganization organization) {
        GeoPlatformServer server = this.createServer(serverUrl, serverType,
                organization);
        logger.debug("\n*** GeoPlatformServer to INSERT:\n{}\n***", server);
        long idServer = gpWSClient.insertServer(server);
        logger.debug("\n*** Id ASSIGNED at the Server in the DB: {} ***",
                idServer);
        assertTrue("Id ASSIGNED at the Server in the DB", idServer > 0);
        return idServer;
    }

    protected GeoPlatformServer createServer(String serverUrl,
            GPCapabilityType serverType, GPOrganization organization) {
        // Create field's value from Regex on Server URL
        String serverName = serverUrl.replaceAll(
                "http://(\\w+)\\.([^\\.]+)\\.(\\w+)", "$1.$2.$3");
        logger.trace("\n*** serverName: {} ***", serverName);
        String labelServer = serverName.replaceAll("(\\w+)\\.([^\\.]+)\\.(\\w+)",
                "$2");
        logger.trace("\n*** labelServer: {} ***", labelServer);
        // Create Server
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl(serverUrl);
        server.setName(serverName);
        server.setTitle(labelServer);
        server.setAbstractServer("Abstract of " + labelServer);
        server.setServerType(serverType);
        server.setOrganization(organization);
        return server;
    }

    /**
     * Delete (with assert) a Server.
     *
     * @param idServer
     */
    protected void deleteServer(long idServer) {
        try {
            boolean check = gpWSClient.deleteServer(idServer);
            assertTrue(
                    "Server with id = " + idServer + " has not been eliminated",
                    check);
        } catch (Exception e) {
            Assert.fail("Error while deleting Server with Id: " + idServer);
        }
    }

    protected Collection<GPViewport> createMassiveViewports() {
        List<GPViewport> viewports = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            viewports.add(new GPViewport("Viewport" + i + "-Rest",
                    "This is a Generic Viewport", i,
                    new GPBBox(i, i, i, i),
                    (i == 0) ? Boolean.TRUE : FALSE));
        }
        return viewports;
    }
}
