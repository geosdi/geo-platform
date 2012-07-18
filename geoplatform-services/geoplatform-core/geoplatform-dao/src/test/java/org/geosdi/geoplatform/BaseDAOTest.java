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
package org.geosdi.geoplatform;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.WKTReader;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.ows.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.geosdi.geoplatform.configurator.crypt.GPDigesterConfigutator;
import org.geosdi.geoplatform.core.dao.*;
import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.core.model.enums.GrantType;
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
import org.xml.sax.SAXException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-TEST.xml",
    "classpath:applicationContext.xml"})
public abstract class BaseDAOTest {

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
    protected GSAccountDAO gsAccountDAO;
    //
    @Autowired
    protected GSResourceDAO gsResourceDAO;
    //
    @Autowired
    protected GPDigesterConfigutator gpDigesterSHA1;
    //
    protected GPUser adminTest;
    protected GPUser userTest;
    protected GPUser viewerTest;
    protected GPUser serviceTest;
    protected GPUser gsUserTest;
    protected GPProject adminProject;
    protected GPProject userProject;
    protected GPProject viewerProject;
    protected GPProject gsUserProject;
    //
    private URL url = null;
    private final String gsAccountUserName = "gsuser";
    private final String urlWMSGetCapabilities =
            "http://imaa.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities";

    //<editor-fold defaultstate="collapsed" desc="Remove all data">
    protected void removeAll() {
//        removeAllStyles();
//        removeAllLayers();
//        removeAllFolders();
//        removeAllAccountProject();
        removeAllProjects();
//        removeAllAuthorities();
        removeAllAccounts();
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

    protected void removeAllAuthorities() {
        List<GPAuthority> authorities = authorityDAO.findAll();
        for (GPAuthority authority : authorities) {
            logger.trace("\n*** Authority to REMOVE:\n{}\n***", authority);
            boolean removed = authorityDAO.remove(authority);
            Assert.assertTrue("Old Authority NOT removed", removed);
        }
    }

    protected void removeAllAccounts() {
        List<GPAccount> accounts = accountDAO.findAll();
        for (GPAccount account : accounts) {
            logger.trace("\n*** Account to REMOVE:\n{}\n***", account);
            boolean removed = accountDAO.remove(account);
            Assert.assertTrue("Old Account NOT removed", removed);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Insert data">
    protected void insertData() throws ParseException {
        this.insertAccount();
        this.insertProject();
        this.insertFoldersAndLayers();
        this.insertGPAccessInfoTest();
    }

    private void insertGPAccessInfoTest() {
        this.removeAllGSAccounts();
        GSAccount gsAccount = this.generateGSAccount(this.gsAccountUserName);
        GSResource resource = this.generateResource(gsAccount);
        gsUserTest.setDefaultProjectID(gsUserProject.getId());
        gsUserTest.setGsAccount(gsAccount);
        this.gsAccountDAO.persist(gsAccount);
        this.gsResourceDAO.persist(resource);
        accountDAO.merge(gsUserTest);
    }

    private GSAccount generateGSAccount(String userName) {
        GSAccount account = new GSAccount();
        account.setGsuser(userName);
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

    private void removeAllGSAccounts() {
        List<GSAccount> accountList = gsAccountDAO.findAll();
        for (GSAccount account : accountList) {
            logger.trace("\n*** GSAccount to REMOVE:\n{}\n***", account);
            boolean removed = gsAccountDAO.remove(account);
            Assert.assertTrue("Old GSAccount NOT removed", removed);
        }
    }

    private void insertAccount() {
        // GUI test
        this.adminTest = this.insertUser("admin", GPRole.ADMIN);
        this.userTest = this.insertUser("user", GPRole.USER);
        this.viewerTest = this.insertUser("viewer", GPRole.VIEWER);
        this.serviceTest = this.insertUser("service", GPRole.ADMIN);
        this.gsUserTest = this.insertUser(this.gsAccountUserName, GPRole.ADMIN);
        //
        this.insertApplication("SIGV");
    }

    private void insertProject() {
        this.adminProject = this.createProject("admin_project", true, 0,
                new Date(System.currentTimeMillis()));
        this.userProject = this.createProject("user_project", false, 0,
                new Date(System.currentTimeMillis() + 300 * 1000));
        this.viewerProject = this.createProject("viewer_project", false, 0,
                new Date(System.currentTimeMillis() + 1700 * 1000));
        this.gsUserProject = this.createProject("gp_user_project", true, 0,
                new Date(System.currentTimeMillis()));
        projectDAO.persist(adminProject, userProject, viewerProject, gsUserProject);
        //
        this.insertBindingUserProject(adminTest, adminProject, BasePermission.ADMINISTRATION.getMask());
        this.insertBindingUserProject(userTest, adminProject, BasePermission.READ.getMask());
        this.insertBindingUserProject(userTest, userProject, BasePermission.ADMINISTRATION.getMask());
        this.insertBindingUserProject(viewerTest, viewerProject, BasePermission.READ.getMask());
        this.insertBindingUserProject(gsUserTest, gsUserProject, BasePermission.ADMINISTRATION.getMask());
        //
        adminTest.setDefaultProjectID(adminProject.getId());
        userTest.setDefaultProjectID(userProject.getId());
        viewerTest.setDefaultProjectID(viewerProject.getId());
        gsUserTest.setDefaultProjectID(gsUserProject.getId());
        accountDAO.merge(adminTest, userTest, viewerTest, gsUserTest);
    }

    private void insertFoldersAndLayers() {
        // Projects of admin
        for (int i = 1; i <= 41; i++) {
            GPProject projectIth = this.createProject("project_admin_k_" + i, false,
                    i, new Date(System.currentTimeMillis() + i * 333));
            projectDAO.persist(projectIth);
            this.insertBindingUserProject(this.adminTest, projectIth,
                    BasePermission.ADMINISTRATION.getMask());
        }

        // Project of user -> root folder: "server layer"
        List<Layer> layerList = this.loadLayersFromServer();
        GPFolder folderServerLayer = this.createFolder("server layer", userProject, null, layerList.size() + 1);
        folderServerLayer.setNumberOfDescendants(layerList.size());
        List<GPRasterLayer> layers = this.loadRasterLayer(layerList, folderServerLayer, userProject, layerList.size());
        folderDAO.persist(folderServerLayer);
        layerDAO.persist(layers.toArray(new GPRasterLayer[]{}));
        //
        userProject.setNumberOfElements(layerList.size());
        projectDAO.merge(userProject);

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

    protected GPUser insertUser(String name, GPRole... roles) {
        GPUser user = this.createUser(name);
        accountDAO.persist(user);
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

    protected GPApplication insertApplication(String appId) {
        GPApplication application = this.createApplication(appId);
        accountDAO.persist(application);
        logger.debug("\n*** Application SAVED:\n{}\n***", application);

        return application;
    }

    private List<GPAuthority> createAuthorities(GPAccount account, GPRole... roles) {
        List<GPAuthority> authorities = new ArrayList<GPAuthority>();
        for (GPRole role : roles) {
            authorities.add(new GPAuthority(account, role.toString()));
        }
        return authorities;
    }

    private GPUser createUser(String username) {
        GPUser user = new GPUser();
        user.setUsername(username);
        user.setName("Complete name of " + username);
        user.setEmailAddress(username + "@test.foo");
        user.setEnabled(true);
        if (username.contains("_")) {
            user.setPassword(this.gpDigesterSHA1.digest("pwd_" + username));
        } else { // User for GUI test
            user.setPassword(this.gpDigesterSHA1.digest(username));
        }
        user.setSendEmail(true);
        return user;
    }

    private GPApplication createApplication(String appID) {
        GPApplication application = new GPApplication();
        application.setAppID(appID);
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
            int permissionMask) {
        GPAccountProject userProjects = new GPAccountProject();
        userProjects.setAccountAndProject(user, project);
        userProjects.setPermissionMask(permissionMask);
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

        List<Layer> layers = null;
        try {
            WebMapServer wms = new WebMapServer(url);

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
        List<GPRasterLayer> rasterLayers = new ArrayList<GPRasterLayer>(layers.size());

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

    /**
     * Default roles for ACLs purpose
     */
    protected enum GPRole {

        ADMIN("Admin"),
        USER("User"),
        VIEWER("Viewer");
        //
        private String role;

        private GPRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return role;
        }
    }
}
