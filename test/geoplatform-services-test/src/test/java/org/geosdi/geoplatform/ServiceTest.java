//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform;

import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.cxf.GeoPlatformWSClient;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class ServiceTest implements InitializingBean {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected GeoPlatformWSClient gpWSClient;
    
    protected GeoPlatformService geoPlatformService;
    
    @Autowired
    protected Server gpJettyServer;
    
    final String username = "username_test_ws";
    protected GPUser findUser = null;
    protected long idUser = -1;
    
    final String nameRootFolderA = "rootFolderA";
    protected GPFolder rootFolderA = null;
    protected long idRootFolderA = -1;
    
    final String nameRootFolderB = "rootFolderB";
    protected GPFolder rootFolderB = null;
    protected long idRootFolderB = -1;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.assertNotNull(gpJettyServer);

        gpJettyServer.start();

        geoPlatformService = gpWSClient.create();
    }

    @Before
    public void setUp() throws Exception {
        logger.info("ServiceTest - SetUp --------------------------------> " + this.getClass().getName());

        // Insert User        
        idUser = this.createAndInsertUser(username);
        findUser = geoPlatformService.getUserDetailByName(new SearchRequest(username));
        
        // Create root folders for the user
        idRootFolderA = createAndInsertFolderWithOwner(nameRootFolderA, findUser, 1, false);
        rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));

        idRootFolderB = createAndInsertFolderWithOwner(nameRootFolderB, findUser, 2, false);
        rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
    }

    @After
    public void tearDown() {
        logger.info("ServiceTest - tearDown --------------------------------> " + this.getClass().getName());
        // Delete user
        this.deleteUser(idUser);
    }
    
    @Test
    public void testRootFolders() {
        // Check root folder A
        Assert.assertNotNull(rootFolderA);
        Assert.assertEquals(rootFolderA.getName(), nameRootFolderA);
        Assert.assertEquals(rootFolderA.getPosition(), 1);
        
        // Check root folder B
        Assert.assertNotNull(rootFolderB);
        Assert.assertEquals(rootFolderB.getName(), nameRootFolderB);
        Assert.assertEquals(rootFolderB.getPosition(), 2);
    }

    // Create and insert (with assert) a User
    protected long createAndInsertUser(String username) {
        GPUser user = createUser(username);
        logger.info("\n***** GPUser to INSERT: " + user);
        long idUser = geoPlatformService.insertUser(user);
        logger.info("\n***** Id ASSIGNED at the User in the DB: " + idUser);
        Assert.assertTrue("Id ASSIGNED at the User in the DB", idUser > 0);
        return idUser;
    }

    private GPUser createUser(String name) {
        String username = name;
        GPUser user = new GPUser();
        user.setUsername(username);
        user.setEmailAddress(username + "@test.org");
        user.setEnabled(true);
        user.setPassword("test");
        user.setSendEmail(true);
        return user;
    }

    // Delete (with assert) a User
    protected void deleteUser(long idUser) {
        try {
            boolean check = geoPlatformService.deleteUser(new RequestById(idUser));
            Assert.assertTrue("User with id = " + idUser + " has not been eliminated", check);
        } catch (Exception e) {
            logger.error("\n***** Error while deleting User with Id: " + idUser);
            Assert.fail();
        }
    }

    protected long createAndInsertFolderWithOwner(String folderName, GPUser owner, int position, boolean shared) {
        GPFolder folder = createFolder(folderName, position, shared);
        folder.setOwner(owner);
        long id = geoPlatformService.insertFolder(folder);
        return id;
    }

    protected long createAndInsertFolderWithParent(String folderName, GPFolder parentFolder, int position, boolean shared) {
        GPFolder folder = createFolder(folderName, position, shared);
        folder.setParent(parentFolder);
        long id = geoPlatformService.insertFolder(folder);
        return id;
    }

    private GPFolder createFolder(String folderName, int position, boolean shared) {
        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        folder.setPosition(position);
        folder.setShared(shared);
        return folder;
    }

    protected long createAndInsertRasterLayer(String abstractFolder, GPFolder parentFolder, String name, int position, boolean shared,
                                              String srs, String title, String urlServer) {
        double minX = 10;
        double minY = 10;
        double maxX = 20;
        double maxY = 20;
        String layerInfoKeyword = "keyword";
        
        GPRasterLayer rasterLayer = new GPRasterLayer();
        rasterLayer.setAbstractText(abstractFolder);
        rasterLayer.setFolder(parentFolder);
        rasterLayer.setName(name);
        rasterLayer.setPosition(position);
        rasterLayer.setShared(shared);
        rasterLayer.setSrs(srs);
        rasterLayer.setTitle(title);
        rasterLayer.setUrlServer(urlServer);
        
        GPBBox bBox = new GPBBox(minX, minY, maxX, maxY);
        rasterLayer.setBbox(bBox);
        
        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setKeywords(layerInfoKeyword);
        layerInfo.setQueryable(false);
        rasterLayer.setLayerInfo(layerInfo);
        
        rasterLayer.setLayerType(GPLayerType.RASTER);
        long id = geoPlatformService.insertLayer(rasterLayer);
        return id;
    }
}
