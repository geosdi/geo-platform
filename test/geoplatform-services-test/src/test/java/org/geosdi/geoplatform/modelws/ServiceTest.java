/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.modelws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.configurator.jasypt.GPPooledPBEStringEncryptorDecorator;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.TestExecutionListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.services.GeoPlatformService;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath*:applicationContext.xml"})
@TestExecutionListeners(value = {WSListenerServices.class})
public abstract class ServiceTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GeoPlatformService gpWSClient; // TODO gpService
    protected GPPooledPBEStringEncryptorDecorator gpPooledPBEStringEncryptor;
    // Roles (default)
    protected final String ROLE_ADMIN = "Admin";
    protected final String ROLE_USER = "User";
    protected final String ROLE_VIEWER = "Viewer";
    // Users
    protected final String usernameTest = "username_test_ws";
    protected GPUser userTest;
    protected long idUserTest = -1;
    // Projects
    protected GPProject projectTest;
    protected long idProjectTest = -1;
    // Folders
    protected final String nameRootFolderA = "rootFolderA";
    protected final String nameRootFolderB = "rootFolderB";
    protected GPFolder rootFolderA;
    protected GPFolder rootFolderB;
    protected long idRootFolderA = -1;
    protected long idRootFolderB = -1;
    //
    protected List<String> layerInfoKeywords;

    /**
     * The listener will inject this dependency
     */
    public void setGeoplatformServiceClient(GeoPlatformService gpWSClient,
                                            GPPooledPBEStringEncryptorDecorator theGPPooledPBEStringEncryptor) {
        this.gpWSClient = gpWSClient;
        this.gpPooledPBEStringEncryptor = theGPPooledPBEStringEncryptor;
    }

    @Before
    public void setUp() throws Exception {
        logger.trace("\n\t@@@ {}.setUp @@@", this.getClass().getSimpleName());

        // Insert User
        idUserTest = this.createAndInsertUser(usernameTest, ROLE_USER);
        userTest = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        // Insert Project
        idProjectTest = this.createAndInsertProject("project_test_ws", false, 2, new Date(System.currentTimeMillis()));
        projectTest = gpWSClient.getProjectDetail(idProjectTest);
        // Insert UserProject
        this.createAndInsertUserProject(userTest, projectTest);

        // Create root folders for the user
        idRootFolderA = this.createAndInsertFolder(nameRootFolderA, projectTest, 2, null);
        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);

        idRootFolderB = this.createAndInsertFolder(nameRootFolderB, projectTest, 1, null);
        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);

        // Set the list of keywords (for raster layer)
        layerInfoKeywords = new ArrayList<String>();
        layerInfoKeywords.add("keyword_test");
    }

    @After
    public void tearDown() {
        logger.trace("\n\t@@@ {}.tearDown @@@", this.getClass().getSimpleName());
        // Delete user
        this.deleteUser(idUserTest);
    }

    // Create and insert a User
    protected long createAndInsertUser(String username, String... roles)
            throws IllegalParameterFault {
        GPUser user = createUser(username, roles);
        logger.debug("\n*** GPUser to INSERT:\n{}\n***", user);

        long idUser = gpWSClient.insertAccount(user, false);
        logger.debug("\n*** Id ASSIGNED at the User in the DB: {} ***", idUser);
        Assert.assertTrue("Id ASSIGNED at the User in the DB", idUser > 0);
        return idUser;
    }

    protected GPUser createUser(String username, String... roles) {
        GPUser user = new GPUser();
        user.setUsername(username);
        user.setName("Complete name of " + username);
        user.setEmailAddress(username + "@test.foo");
        user.setEnabled(true);
        user.setPassword(this.gpPooledPBEStringEncryptor.encrypt("pwd_" + username));
        user.setSendEmail(true);

        if (roles.length > 0) {
            List<GPAuthority> authorities = this.createAuthorities(roles);
            user.setGPAuthorities(authorities);
        }
        return user;
    }

    private List<GPAuthority> createAuthorities(String... roles) {
        List<GPAuthority> authorities = new ArrayList<GPAuthority>();
        for (String role : roles) {
            GPAuthority authority = new GPAuthority();
            authority.setAuthority(role);

            authorities.add(authority);
        }
        return authorities;
    }

    // Delete (with assert) a User
    protected void deleteUser(long idUser) {
        try {
            boolean check = gpWSClient.deleteAccount(idUser);
            Assert.assertTrue("User with id = " + idUser + " has not been eliminated", check);
        } catch (Exception e) {
            Assert.fail("Error while deleting User with Id: " + idUser);
        }
    }

    // Delete (with assert) a Folder
    protected void deleteFolder(long idFolder) {
        try {
            boolean check = gpWSClient.deleteFolder(idFolder);
            Assert.assertTrue("Folder with id = " + idFolder + " has not been eliminated", check);
        } catch (Exception e) {
            Assert.fail("Error while deleting Folder with Id: " + idFolder);
        }
    }

    protected long createAndInsertProject(String name, boolean isShared,
                                          int numberOfElements, Date creationalDate) throws IllegalParameterFault {
        GPProject project = this.createProject(name, isShared, numberOfElements, creationalDate);
        return gpWSClient.insertProject(project);
    }

    protected long createAndInsertUserProject(GPUser user, GPProject project)
            throws IllegalParameterFault {
        GPAccountProject userProject = new GPAccountProject();
        userProject.setAccountAndProject(user, project);
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
        GPFolder folder = this.createFolder(folderName, project, position, parent);
        folder.setNumberOfDescendants(numberOfDescendants);
        return gpWSClient.insertFolder(project.getId(), folder);
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

    protected GPFolder createFolder(String folderName, GPProject project,
                                    int position, GPFolder parent) {
        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        folder.setProject(project);
        folder.setPosition(position);
        folder.setParent(parent);
        return folder;
    }

    protected long createAndInsertRasterLayer(GPFolder folder, String title, String name,
                                              String abstractText, int position, String srs, String urlServer)
            throws IllegalParameterFault {
        GPRasterLayer rasterLayer = new GPRasterLayer();
        this.createLayer(rasterLayer, folder, title, name, abstractText, position, srs, urlServer);

        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setKeywords(layerInfoKeywords);
        layerInfo.setQueryable(false);
        rasterLayer.setLayerInfo(layerInfo);

        rasterLayer.setLayerType(GPLayerType.RASTER);
        return gpWSClient.insertLayer(rasterLayer);
    }

    protected long createAndInsertVectorLayer(GPFolder folder, String title, String name,
                                              String abstractText, int position, String srs, String urlServer)
            throws IllegalParameterFault {
        GPVectorLayer vectorLayer = new GPVectorLayer();
        this.createLayer(vectorLayer, folder, title, name, abstractText, position, srs, urlServer);

        vectorLayer.setLayerType(GPLayerType.POLYGON);
        return gpWSClient.insertLayer(vectorLayer);
    }

    protected void createLayer(GPLayer layer, GPFolder folder, String title, String name,
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
