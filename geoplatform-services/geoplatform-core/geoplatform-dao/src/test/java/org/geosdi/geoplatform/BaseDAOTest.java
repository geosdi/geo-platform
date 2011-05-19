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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPStyleDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPStyle;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.ows.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
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
    @Autowired
    protected GPUserDAO userDAO;
    @Autowired
    protected GPFolderDAO folderDAO;
    @Autowired
    protected GPLayerDAO layerDAO;
    @Autowired
    protected GPStyleDAO styleDAO;
    @Autowired
    protected GPServerDAO serverDAO;
    @Autowired
    protected GPAuthorityDAO authorityDAO;

    @Before
    public void setUp() {
        logger.info("----------------------- Running "
                + getClass().getSimpleName());
    }

    // This test is performed only after all test's subclasses were performed
    @Test
    public void testCheckDAOs() {
        Assert.assertNotNull(userDAO);
        Assert.assertNotNull(folderDAO);
        Assert.assertNotNull(layerDAO);
        Assert.assertNotNull(styleDAO);
        Assert.assertNotNull(serverDAO);
        Assert.assertNotNull(authorityDAO);
    }

    //<editor-fold defaultstate="collapsed" desc="Remove all data">
    protected void removeAll() {
        removeAllStyle();
        removeAllLayer();
        removeAllFolder();
        removeAllAuthority();
        removeAllUser();
        removeAllServer();
    }

    private void removeAllAuthority() {
        List<GPAuthority> autorities = authorityDAO.findAll();
        for (GPAuthority autority : autorities) {
            logger.info("Removing " + autority);
            boolean ret = authorityDAO.remove(autority);
            Assert.assertTrue("Old Authority not removed", ret);
        }
    }

    private void removeAllFolder() {
        List<GPFolder> folders = folderDAO.findAll();
        for (GPFolder folder : folders) {
            logger.info("Removing " + folder);
            folderDAO.remove(folder);
        }
    }

    private void removeAllLayer() {
        List<GPLayer> layers = layerDAO.findAll();
        for (GPLayer layer : layers) {
            logger.info("Removing " + layer);
            layerDAO.remove(layer);
        }
    }

    private void removeAllStyle() {
        List<GPStyle> styles = styleDAO.findAll();
        for (GPStyle style : styles) {
            logger.info("Removing " + style);
            boolean ret = styleDAO.remove(style);
            Assert.assertTrue("Old Style not removed", ret);
        }
    }

    private void removeAllUser() {
        List<GPUser> users = userDAO.findAll();
        for (GPUser user : users) {
            logger.info("Removing " + user);
            boolean ret = userDAO.remove(user);
            Assert.assertTrue("Old User not removed", ret);
        }
    }

    private void removeAllServer() {
        List<GeoPlatformServer> servers = serverDAO.findAll();
        for (GeoPlatformServer server : servers) {
            logger.info("Removing " + server);
            boolean ret = serverDAO.remove(server);
            Assert.assertTrue("Old User not removed", ret);
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Insert data">
    protected void insertData() throws ParseException {
        insertUser();
    }

    private void insertUser() {
        GPUser user = createUser("user_0");
        userDAO.persist(user);
        logger.info("Save user: " + user);

        List<GPAuthority> authorities = new ArrayList<GPAuthority>();
        GPAuthority authority = new GPAuthority(user.getUsername(), "ROLE_ADMIN");
        authorities.add(authority);
        user.setGpAuthorities(authorities);
        authorityDAO.persist(authority);
        logger.info("Save Authority for: " + authority);
    }

    protected GPUser createUser(String name) {
        String username = name;
        GPUser user = new GPUser();
        user.setUsername(username);
        user.setEmailAddress(username + "@test");
        user.setEnabled(true);
        user.setPassword("test");
        user.setSendEmail(true);
        return user;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Insert folders">    
    protected void insertMockLayer() throws ParseException {
    }

    protected void insertFolders() throws ParseException {
        int position = 0;
        insertFolderUser(position);
    }

    private void insertFolderUser(int position) {
        GPUser user = userDAO.findByUsername("user_0");

        // "only folders"
        GPFolder onlyFolders = new GPFolder();
        onlyFolders.setName("only folders");
        onlyFolders.setOwner(user);
        onlyFolders.setPosition(++position);
        onlyFolders.setParent(null);
        
        // "only folders" ---> "empty subfolder A"
        GPFolder emptySubFolderA = new GPFolder();
        emptySubFolderA.setName("empty subfolder A");
        emptySubFolderA.setPosition(++position);
        emptySubFolderA.setParent(onlyFolders);
        
        // "only folders" ---> "empty subfolder B"
        GPFolder emptySubFolderB = new GPFolder();
        emptySubFolderB.setName("empty subfolder B");
        emptySubFolderB.setPosition(++position);
        emptySubFolderB.setParent(onlyFolders);
        
        folderDAO.persist(onlyFolders, emptySubFolderA, emptySubFolderB);
        
        // "my raster"
        GPFolder folderRaster = new GPFolder();
        folderRaster.setName("my raster");
        folderRaster.setOwner(user);
        folderRaster.setPosition(++position);
        folderRaster.setParent(null);

        // GPRasterLayer
        GPRasterLayer rasterLayer1 = new GPRasterLayer();
        rasterLayer1.setName("StratiDiBase:deagostini_ita_250mila");
        rasterLayer1.setPosition(++position);
        rasterLayer1.setAbstractText("deagostini_ita_250mila");
        rasterLayer1.setTitle("deagostini");
        rasterLayer1.setSrs("EPSG:4326");
        rasterLayer1.setUrlServer("http://dpc.geosdi.org/geoserver/wms");
        rasterLayer1.setBbox(new GPBBox(6.342, 35.095, 19.003, 47.316));
        rasterLayer1.setLayerType(GPLayerType.RASTER);

        // GPLayerInfo
        GPLayerInfo info = new GPLayerInfo();
        info.setKeywords("IGM");
        info.setQueryable(true);
        rasterLayer1.setLayerInfo(info);

        // GPStyle #1
        GPStyle style1 = createStyleDTO("style 1");
        style1.setLayer(rasterLayer1);
        // GPStyle #2
        GPStyle style2 = createStyleDTO("style 2");
        style2.setLayer(rasterLayer1);

        rasterLayer1.setFolder(folderRaster);

        folderDAO.persist(folderRaster);
        layerDAO.persist(rasterLayer1);
        styleDAO.persist(style1, style2);

        //load sitpdc layer
        URL url = null;
        try {
            url = new URL(
                    "http://dpc.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities");
        } catch (MalformedURLException e) {
            System.out.println("ERRORE:" + e);
        }

        WebMapServer wms = null;
        try {
            wms = new WebMapServer(url);

            WMSCapabilities capabilities = wms.getCapabilities();

            List<Layer> layers = capabilities.getLayerList();

            for (int i = 1; i < layers.size(); i++) {
                logger.debug("LAYER" + layers.get(i));
                GPRasterLayer raster = new GPRasterLayer();
                raster.setName(layers.get(i).getName());
                raster.setAbstractText(layers.get(i).get_abstract());
                raster.setSrs(layers.get(i).getSrs().toString());
                raster.setBbox(new GPBBox(
                        layers.get(i).getLatLonBoundingBox().getMinX(), layers.get(
                        i).getLatLonBoundingBox().getMinY(),
                        layers.get(i).getLatLonBoundingBox().getMaxX(), layers.get(
                        i).getLatLonBoundingBox().getMaxY()));
                GPLayerInfo infoLayer = new GPLayerInfo();
                infoLayer.setKeywords(layers.get(i).getKeywords() != null ? layers.get(
                        i).getKeywords().toString() : "");
                infoLayer.setQueryable(true);
                raster.setLayerInfo(infoLayer);
                raster.setFolder(folderRaster);
                raster.setLayerType(GPLayerType.RASTER);
                raster.setPosition(++position);
                raster.setUrlServer("http://dpc.geosdi.org/geoserver/wms");
                layerDAO.persist(raster);
            }

            GPFolder folderIGM = new GPFolder();
            folderIGM.setName("IGM");
            folderIGM.setPosition(++position);
            folderIGM.setParent(folderRaster);
            folderDAO.persist(folderIGM);

            GPVectorLayer vectorLayer1 = new GPVectorLayer();
            vectorLayer1.setName("Name of vectorLayer");
            vectorLayer1.setPosition(++position);
            vectorLayer1.setAbstractText("AbstractText of vectorLayer");
            vectorLayer1.setTitle("Title of vectorLayer");
            vectorLayer1.setSrs("EPSG:4326");
            vectorLayer1.setUrlServer("http://dpc.geosdi.org/geoserver/wms");
            vectorLayer1.setBbox(new GPBBox(1.1, 2.2, 3.3, 3.3));
            vectorLayer1.setLayerType(GPLayerType.MULTIPOLYGON);
            vectorLayer1.setFolder(folderIGM);
            layerDAO.persist(vectorLayer1);            

        } catch (IOException e) {
            //There was an error communicating with the server
            //For example, the server is down
        } catch (ServiceException e) {
            //The server returned a ServiceException (unusual in this case)
        } catch (SAXException e) {
            //Unable to parse the response from the server
            //For example, the capabilities it returned was not valid
        }

    }

    protected GPStyle createStyleDTO(String name) {
        GPStyle style = new GPStyle();
        style.setName(name);
        style.setTitle("The " + name);
        style.setAbstractText("Abstract for " + name);
        style.setLegendURL("http://www.geosdi.org/" + name.replaceAll("[ ]+",
                "-"));
        return style;
    }
    //</editor-fold>
}
