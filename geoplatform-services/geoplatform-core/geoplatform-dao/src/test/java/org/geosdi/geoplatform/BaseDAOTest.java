//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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

import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.Utility;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.ows.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.dao.GPUserProjectsDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUserProjects;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class BaseDAOTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    protected GPUserDAO userDAO;
    //
    @Autowired
    protected GPUserProjectsDAO userProjectsDAO;
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
    protected final String usernameAdminTest = "admin_test_0";
    protected final String usernameUserTest = "user_test_0";
    protected GPUser adminTest = null;
    protected GPUser userTest = null;
    protected GPUser sigvTest = null;
    protected GPProject adminProject = null;
    protected GPProject userProject = null;
    // ACL
    protected final String usernameSuperUserTest = "super_user_test_acl";
    //
    private URL url = null;
    private final String urlWMSGetCapabilities =
            "http://imaa.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities";

    //<editor-fold defaultstate="collapsed" desc="Remove all data">
    protected void removeAll() {
//        removeAllStyles();
        removeAllLayers();
        removeAllFolders();
        removeAllUserProjects();
        removeAllProjects();
        removeAllAuthorities();
        removeAllUsers();
    }

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
        Comparator comp = new Comparator() {

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

    private void removeAllUserProjects() {
        List<GPUserProjects> userProjects = userProjectsDAO.findAll();
        for (GPUserProjects up : userProjects) {
            logger.trace("\n*** UserProject to REMOVE:\n{}\n***", up);
            boolean removed = userProjectsDAO.remove(up);
            Assert.assertTrue("Old UserProjects NOT removed", removed);
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

    protected void removeAllAuthorities() {
        List<GPAuthority> authorities = authorityDAO.findAll();
        for (GPAuthority authority : authorities) {
            logger.trace("\n*** Authority to REMOVE:\n{}\n***", authority);
            boolean removed = authorityDAO.remove(authority);
            Assert.assertTrue("Old Authority NOT removed", removed);
        }
    }

    protected void removeAllUsers() {
        List<GPUser> users = userDAO.findAll();
        for (GPUser user : users) {
            logger.trace("\n*** User to REMOVE:\n{}\n***", user);
            boolean removed = userDAO.remove(user);
            Assert.assertTrue("Old User NOT removed", removed);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Insert data">
    protected void insertData() throws ParseException {
        this.adminTest = this.insertUser(usernameAdminTest, GPRole.ADMIN);
        this.userTest = this.insertUser(usernameUserTest, GPRole.USER);
        // ACL Data
        this.insertUser(usernameSuperUserTest, GPRole.ADMIN, GPRole.USER);
        this.insertUser("admin_acl_test", GPRole.ADMIN);
        this.insertUser("user_acl_test", GPRole.USER);
        // GUI test
        this.insertUser("admin", GPRole.ADMIN);
        this.insertUser("user", GPRole.USER);
        this.insertUser("viewer", GPRole.VIEWER);
        // Manage Users
        this.insertUser("admin_pag_1", GPRole.ADMIN);
        this.insertUser("admin_pag_2", GPRole.ADMIN);
        this.insertUser("admin_pag_3", GPRole.ADMIN);
        this.insertUser("admin_pag_4", GPRole.ADMIN);
        this.insertUser("admin_pag_5", GPRole.ADMIN);
        this.insertUser("admin_pag_6", GPRole.ADMIN);
        this.insertUser("admin_pag_7", GPRole.ADMIN);
        this.insertUser("user_pag_1", GPRole.USER);
        this.insertUser("user_pag_2", GPRole.USER);
        this.insertUser("user_pag_3", GPRole.USER);
        this.insertUser("user_pag_4", GPRole.USER);
        this.insertUser("user_pag_5", GPRole.USER);
        this.insertUser("user_pag_6", GPRole.USER);
        this.insertUser("user_pag_7", GPRole.USER);
        this.insertUser("user_pag_8", GPRole.USER);
        this.insertUser("user_pag_9", GPRole.USER);
        this.insertUser("user_pag_10", GPRole.USER);
        this.insertUser("user_pag_11", GPRole.USER);
        this.insertUser("viewer_pag_1", GPRole.VIEWER);
        this.insertUser("viewer_pag_2", GPRole.VIEWER);
        this.insertUser("viewer_pag_3", GPRole.VIEWER);
        this.insertUser("viewer_pag_4", GPRole.VIEWER);
        this.insertUser("viewer_pag_5", GPRole.VIEWER);
        this.insertUser("viewer_pag_6", GPRole.VIEWER);
        this.insertUser("viewer_pag_7", GPRole.VIEWER);
        this.insertUser("viewer_pag_8", GPRole.VIEWER);
        this.insertUser("viewer_pag_9", GPRole.VIEWER);
        this.insertUser("viewer_pag_10", GPRole.VIEWER);
        this.insertUser("viewer_pag_11", GPRole.VIEWER);
        this.insertUser("viewer_pag_12", GPRole.VIEWER);
        this.insertUser("viewer_pag_13", GPRole.VIEWER);
        this.insertUser("viewer_pag_14", GPRole.VIEWER);
        this.insertUser("viewer_pag_15", GPRole.VIEWER);
        this.insertUser("viewer_pag_16", GPRole.VIEWER);
        this.insertUser("viewer_pag_17", GPRole.VIEWER);
        // SIGV User
        this.sigvTest = this.insertUser("SIGV", GPRole.ADMIN);
    }

    protected GPUser insertUser(String name, GPRole... roles) {
        GPUser user = createUser(name);
        userDAO.persist(user);
        logger.debug("\n*** User SAVED:\n{}\n***", user);

        if (roles.length > 0) {
            List<GPAuthority> authorities = this.createAuthorities(user, roles);
            user.setGPAuthorities(authorities);

            for (GPAuthority authority : authorities) {
                authorityDAO.persist(authority);
                logger.debug("\n*** Authority SAVED:\n{}\n***", authority);
            }
        }

        return user;
    }

    private List<GPAuthority> createAuthorities(GPUser user, GPRole... roles) {
        List<GPAuthority> authorities = new ArrayList<GPAuthority>();
        for (GPRole role : roles) {
            authorities.add(new GPAuthority(user, role.toString()));
        }
        return authorities;
    }

    private GPUser createUser(String username) {
        GPUser user = new GPUser();
        user.setUsername(username);
        user.setName("Complete name of " + username);
        user.setEmailAddress(username + "@test.org");
        user.setEnabled(true);
        if (username.contains("_")) {
            user.setPassword(Utility.md5hash("pwd_" + username));
        } else { // User for GUI test
            user.setPassword(Utility.md5hash(username));
        }
        user.setSendEmail(true);
        return user;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Insert folders">
    protected void insertFolders() throws ParseException {
        List<Layer> layerList = this.loadLayersFromServer();
        this.insertFoldersAndProject(layerList);
    }

    private void insertFoldersAndProject(List<Layer> layerList) {
        this.adminProject = this.createProject("admin_project", true, 6,
                new Date(System.currentTimeMillis()));
        this.userProject = this.createProject("user_project", false, layerList.size(),
                new Date(System.currentTimeMillis() + 300 * 1000));
        //
        projectDAO.persist(adminProject, userProject);
        //
        this.insertBindingUserProject(adminTest, adminProject, BasePermission.ADMINISTRATION.getMask());
        this.insertBindingUserProject(userTest, adminProject, BasePermission.READ.getMask());
        this.insertBindingUserProject(userTest, userProject, BasePermission.ADMINISTRATION.getMask());

        // Project of admin -> root folders: "only folders, layers"
        GPFolder onlyFolders = this.createFolder("only folders", adminProject, null, 6);
        // "only folders" ---> "empty subfolder A"
        GPFolder emptySubFolderA = this.createFolder("empty subfolder A", adminProject, onlyFolders, 5);
        // "only folders" ---> "empty subfolder B"
        GPFolder emptySubFolderB = this.createFolder("empty subfolder B", adminProject, onlyFolders, 4);
        // "layers"
        GPFolder layerFolder = this.createFolder("layers", adminProject, null, 3);
        // "layers" ---> _rasterLayer_ ---> Styles
        GPRasterLayer rasterLayer = this.createRasterLayer(layerFolder, adminProject, 2);
//        GPStyle rasterLayerStyle1 = this.createStyle("style 1", rasterLayer);
//        GPStyle rasterLayerStyle2 = this.createStyle("style 2", rasterLayer);
        // ---> "layers" --> _vectorLayer_
        GPVectorLayer vectorLayer = this.createVectorLayer(layerFolder, adminProject, 1);
        //
        onlyFolders.setNumberOfDescendants(2);
        layerFolder.setNumberOfDescendants(2);
        folderDAO.persist(onlyFolders, emptySubFolderA, emptySubFolderB, layerFolder);
        layerDAO.persist(rasterLayer, vectorLayer);
//        styleDAO.persist(rasterLayerStyle1, rasterLayerStyle2);

        // Project of user -> root folder: "server layer"
        GPFolder folderServerLayer = this.createFolder("server layer", userProject, null, layerList.size() + 1);
        folderServerLayer.setNumberOfDescendants(layerList.size());
        List<GPRasterLayer> layers = this.loadRasterLayer(layerList, folderServerLayer, userProject, layerList.size());
        folderDAO.persist(folderServerLayer);
        layerDAO.persist(layers.toArray(new GPRasterLayer[]{}));
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
            int permissionMask) {
        GPUserProjects userProjects = new GPUserProjects();
        userProjects.setUserAndProject(user, project);
        userProjects.setPermissionMask(permissionMask);
        userProjectsDAO.persist(userProjects);
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
        raster.setLayerType(GPLayerType.RASTER);
        // GPLayerInfo
        GPLayerInfo info = new GPLayerInfo();
        List<String> keywords = new ArrayList<String>();
        keywords.add("IGM");
        info.setKeywords(keywords);
        info.setQueryable(true);
        raster.setLayerInfo(info);
        // Styles
        List<String> styles = new ArrayList<String>();
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

    // Load imaa Layers
    private List<Layer> loadLayersFromServer() {
        try {
            url = new URL(urlWMSGetCapabilities);
        } catch (MalformedURLException e) {
            logger.error("Error:" + e);
        }

        WebMapServer wms = null;
        List<Layer> layers = null;
        try {
            wms = new WebMapServer(url);

            WMSCapabilities capabilities = wms.getCapabilities();

            layers = capabilities.getLayerList();
        } catch (IOException e) {
            //There was an error communicating with the server
            //For example, the server is down
        } catch (ServiceException e) {
            //The server returned a ServiceException (unusual in this case)
        } catch (SAXException e) {
            //Unable to parse the response from the server
            //For example, the capabilities it returned was not valid
        }

        return layers;
    }

    private List<GPRasterLayer> loadRasterLayer(List<Layer> layers,
            GPFolder folder, GPProject project, int position) {
        List<GPRasterLayer> rasterLayers = null;
        rasterLayers = new ArrayList<GPRasterLayer>(layers.size());

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
            raster.setLayerType(GPLayerType.RASTER);
            raster.setUrlServer("http://imaa.geosdi.org/geoserver/wms");
            if (i < 5) {
                raster.setChecked(true);
            }
            rasterLayers.add(raster);
        }

        return rasterLayers;
    }
//</editor-fold>
}