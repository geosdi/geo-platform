/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.model.soap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class ServiceTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GeoPlatformService gpWSClient;
    // Organization
    protected static final String organizationNameTest = "geoSDI_ws_test";
    protected GPOrganization organizationTest;
    // Users
    protected static final String domainNameTest = "geosdi-test.org";
    protected static final String usernameTest = "user_test_ws";
    protected static final String passwordTest = usernameTest;
    protected static final String emailTest = usernameTest + "@" + domainNameTest;
    protected GPUser userTest;
    protected long idUserTest = -1;
    // Projects
    protected GPProject projectTest;
    protected long idProjectTest = -1;
    // Folders
    protected static final String nameRootFolderA = "rootFolderA";
    protected static final String nameRootFolderB = "rootFolderB";
    protected GPFolder rootFolderA;
    protected GPFolder rootFolderB;
    protected long idRootFolderA = -1;
    protected long idRootFolderB = -1;
    //
    protected List<String> layerInfoKeywords;

    /**
     * The listener will inject this dependency.
     */
    public void setGeoplatformServiceClient(GeoPlatformService gpWSClient) {
        this.gpWSClient = gpWSClient;
    }

    @Before
    public void setUp() throws Exception {
        logger.trace("\n\t@@@ {}.setUp @@@", this.getClass().getSimpleName());

        // Insert Organization
        this.setUpOrganization();

        // Insert User
        idUserTest = this.createAndInsertUser(usernameTest, organizationTest, GPRole.USER);
        userTest = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        // Insert Project
        idProjectTest = this.createAndInsertProject("project_test_ws", false, 2,
                                                    new Date(System.currentTimeMillis()));
        projectTest = gpWSClient.getProjectDetail(idProjectTest);
        // Insert the Account as the owner of Project
        this.createAndInsertAccountProject(userTest, projectTest, BasePermission.ADMINISTRATION);

        // Create root folders for the user
        idRootFolderA = this.createAndInsertFolder(nameRootFolderA, projectTest,
                                                   2, null);
        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);

        idRootFolderB = this.createAndInsertFolder(nameRootFolderB, projectTest,
                                                   1, null);
        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);

        // Set the list of keywords (for raster layer)
        layerInfoKeywords = new ArrayList<String>();
        layerInfoKeywords.add("keyword_test");
    }

    protected void setUpOrganization() throws IllegalParameterFault {
        organizationTest = new GPOrganization(organizationNameTest);
        organizationTest.setId(gpWSClient.insertOrganization(organizationTest));
    }

    @After
    public void tearDown() {
        logger.trace("\n\t@@@ {}.tearDown @@@", this.getClass().getSimpleName());
        // Delete Organization
        this.deleteOrganization(organizationTest.getId());
    }

    /**
     * Create and insert a User.
     */
    protected long createAndInsertUser(String username,
            GPOrganization organization, GPRole... roles)
            throws IllegalParameterFault {
        GPUser user = this.createUser(username, organization, roles);
        logger.debug("\n*** GPUser to INSERT:\n{}\n***", user);

        long idUser = gpWSClient.insertAccount(user, false);
        logger.debug("\n*** Id ASSIGNED at the User in the DB: {} ***", idUser);
        Assert.assertTrue("Id ASSIGNED at the User in the DB", idUser > 0);
        return idUser;
    }

    protected GPUser createUser(String username, GPOrganization organization,
            GPRole... roles) {
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
            Assert.assertTrue("Account with ID = " + accountID + " has not been eliminated", check);
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
            Assert.assertTrue("Organization with ID = " + organizationID + " has not been eliminated", check);
        } catch (Exception e) {
            Assert.fail(
                    "Error while deleting Organization with ID: " + organizationID);
        }
    }

    /**
     * Delete (with assert) a Folder.
     */
    protected void deleteFolder(long idFolder) {
        try {
            boolean check = gpWSClient.deleteFolder(idFolder);
            Assert.assertTrue("Folder with id = " + idFolder + " has not been eliminated", check);
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

    protected long createAndInsertAccountProject(GPAccount account, GPProject project,
            Permission permission) throws IllegalParameterFault {
        GPAccountProject userProject = new GPAccountProject();
        userProject.setAccountAndProject(account, project);
        userProject.setPermissionMask(permission.getMask());
        return gpWSClient.insertAccountProject(userProject);
    }

    protected long createAndInsertFolder(String folderName, GPProject project,
            int position, GPFolder parent) throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = this.createFolder(folderName, project, position, parent);
        return gpWSClient.insertFolder(project.getId(), folder);
    }

    protected long createAndInsertFolder(String folderName, GPProject project,
            int position, GPFolder parent, int numberOfDescendants)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = this.createFolder(folderName, project, position,
                                            parent);
        folder.setNumberOfDescendants(numberOfDescendants);
        return gpWSClient.insertFolder(project.getId(), folder);
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
        return gpWSClient.insertLayer(rasterLayer);
    }

    protected long createAndInsertVectorLayer(GPFolder folder, String title,
            String name,
            String abstractText, int position, String srs, String urlServer)
            throws IllegalParameterFault {
        GPVectorLayer vectorLayer = new GPVectorLayer();
        this.createLayer(vectorLayer, folder, title, name, abstractText,
                         position, srs, urlServer);

        vectorLayer.setLayerType(GPLayerType.POLYGON);
        return gpWSClient.insertLayer(vectorLayer);
    }

    protected void createLayer(GPLayer layer, GPFolder folder, String title,
            String name,
            String abstractText, int position, String srs, String urlServer) {
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
}
