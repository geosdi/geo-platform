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

import java.util.Iterator;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.geosdi.geoplatform.responce.collection.LayerList;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSFolderLayerTest extends ServiceTest {

    private final String urlServer = "http://www.geosdi.org/test";
    private final String spatialReferenceSystem = "Geographic coordinate system";
    // Raster Layer 1
    private final String abstractTextRasterLayer1 = "abstractTextRasterLayer1";
    private final String nameRasterLayer1 = "rasterLayer1";
    private final String titleRasterLayer1 = "Raster Layer 1";
    private GPRasterLayer rasterLayer1 = null;
    private long idRasterLayer1 = -1;
    // Vector Layer 1
    private final String abstractTextVectorLayer1 = "abstractTextVectorLayer1";
    private final String nameVectorLayer1 = "vectorLayer1";
    private final String titleVectorLayer1 = "Vector Layer 1";
    private GPVectorLayer vectorLayer1 = null;
    private long idVectorLayer1 = -1;
    // Raster Layer 2
    private final String abstractTextRasterLayer2 = "abstractTextRasterLayer2";
    private final String nameRasterLayer2 = "rasterLayer2";
    private final String titleRasterLayer2 = "Raster Layer 2";
    private GPRasterLayer rasterLayer2 = null;
    private long idRasterLayer2 = -1;
    // Vector Layer 2
    private final String abstractTextVectorLayer2 = "abstractTextVectorLayer2";
    private final String nameVectorLayer2 = "vectorLayer2";
    private final String titleVectorLayer2 = "Vector Layer 2";
    private GPVectorLayer vectorLayer2 = null;
    private long idVectorLayer2 = -1;

    @Before
    // "position" will be set without application logic, but only to have different values
    public void setUp() throws Exception {
        super.setUp();
        logger.info("WSFolderLayerTest - SetUp --------------------------------> " + this.getClass().getName());

        userTest = geoPlatformService.getUserDetailByName(new SearchRequest(usernameTest));

        // "rootFolderA" ---> "rasterLayer1"
        idRasterLayer1 = createAndInsertRasterLayer(abstractTextRasterLayer1, rootFolderA, nameRasterLayer1, 3,
                false, spatialReferenceSystem, titleRasterLayer1, urlServer);
        rasterLayer1 = geoPlatformService.getRasterLayer(idRasterLayer1);

        // "rootFolderA" ---> "vectorLayer1"
        idVectorLayer1 = createAndInsertVectorLayer(abstractTextVectorLayer1, rootFolderA, nameVectorLayer1, 4,
                false, spatialReferenceSystem, titleVectorLayer1, urlServer);
        vectorLayer1 = geoPlatformService.getVectorLayer(idVectorLayer1);

        // "rootFolderB" ---> "rasterLayer2"
        idRasterLayer2 = createAndInsertRasterLayer(abstractTextRasterLayer2, rootFolderB, nameRasterLayer2, 5,
                false, spatialReferenceSystem, titleRasterLayer2, urlServer);
        rasterLayer2 = geoPlatformService.getRasterLayer(idRasterLayer2);

        // "rootFolderB" ---> "vectorLayer2"
        idVectorLayer2 = createAndInsertVectorLayer(abstractTextVectorLayer2, rootFolderB, nameVectorLayer2, 6,
                false, spatialReferenceSystem, titleVectorLayer2, urlServer);
        vectorLayer2 = geoPlatformService.getVectorLayer(idVectorLayer2);
    }

    @Test
    public void testGetLayer() {
        try {
            ShortLayerDTO shortRasterLayer1 = geoPlatformService.getShortLayer(idRasterLayer1);
            Assert.assertNotNull("assertNotNull shortRasterLayer1", shortRasterLayer1);
            Assert.assertEquals("assertEquals shortRasterLayer1.getName()", shortRasterLayer1.getName(), nameRasterLayer1);
            Assert.assertEquals("assertEquals shortRasterLayer1.getPosition()", shortRasterLayer1.getPosition(), 3);
            Assert.assertEquals("assertEquals shortRasterLayer1.getSrs()", shortRasterLayer1.getSrs(), spatialReferenceSystem);
            Assert.assertEquals("assertEquals shortRasterLayer1.getUrlServer()", shortRasterLayer1.getUrlServer(), urlServer);
            Assert.assertEquals("assertEquals shortRasterLayer1.getLayerType()", shortRasterLayer1.getLayerType(), GPLayerType.RASTER);

            ShortLayerDTO shortVectorLayer1 = geoPlatformService.getShortLayer(idVectorLayer1);
            Assert.assertNotNull("assertNotNull shortVectorLayer1", shortVectorLayer1);
            Assert.assertEquals("assertEquals shortVectorLayer1.getName()", shortVectorLayer1.getName(), nameVectorLayer1);
            Assert.assertEquals("assertEquals shortVectorLayer1.getPosition()", shortVectorLayer1.getPosition(), 4);
            Assert.assertEquals("assertEquals shortVectorLayer1.getSrs()", shortVectorLayer1.getSrs(), spatialReferenceSystem);
            Assert.assertEquals("assertEquals shortVectorLayer1.getUrlServer()", shortVectorLayer1.getUrlServer(), urlServer);
            Assert.assertEquals("assertEquals shortVectorLayer1.getLayerType()", shortVectorLayer1.getLayerType(), GPLayerType.POLYGON);
        } catch (ResourceNotFoundFault ex) {
            logger.debug("\n*** Layer with id \"{}\" was NOT found ***", idRasterLayer1);
        }
    }

    @Test
    public void testUpdateRasterLayer() {
        final String nameLayerUpdated = "rasterLayerUpdated";
        try {
            rasterLayer1.setFolder(rootFolderB);
            rasterLayer1.setName(nameLayerUpdated);

            geoPlatformService.updateRasterLayer(rasterLayer1);
            ShortLayerDTO layerUpdated = geoPlatformService.getShortLayer(idRasterLayer1);

            Assert.assertNotNull("assertNotNull layerUpdated", layerUpdated);
            Assert.assertEquals("assertEquals layerUpdated.getName()", layerUpdated.getName(), nameLayerUpdated);
        } catch (IllegalParameterFault ex) {
            Assert.fail("Layer has an Illegal Parameter");
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Layer with id \"" + idRasterLayer1 + "\" was NOT found");
        }

        try {
            FolderDTO folderA = geoPlatformService.getShortFolder(new RequestById(idRootFolderA));
            Assert.assertNotNull("assertNotNull folderA", folderA);
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Folder with id \"" + idRootFolderA + "\" was NOT found");
        }

        try {
            FolderDTO folderB = geoPlatformService.getShortFolder(new RequestById(idRootFolderB));
            Assert.assertNotNull("assertNotNull folderB", folderB);
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Folder with id \"" + idRootFolderB + "\" was NOT found");
        }
    }

    @Test
    public void testUpdateVectorLayer() {
        Assert.assertTrue(true);
        // TODO build testUpdateVectorLayer
    }

    @Test
    public void testDeleteLayer() {
        try {
            // Assert total number of folders stored into DB before delete            
            LayerList allLayersBeforeDelete = geoPlatformService.getLayers();
            int totalLayers = allLayersBeforeDelete.getList().size();
            Assert.assertTrue("assertEquals totalLayers", totalLayers >= 4); // SetUp() added 4 layers

            // Delete "rasterLayer1" from "rootFolderA"
            boolean erased = geoPlatformService.deleteLayer(new RequestById(idRasterLayer1));
            Assert.assertTrue("Deletion of the layer rasterLayer1", erased);

            // Get root folders for user
            FolderList folderList = geoPlatformService.getUserFoldersByUserId(idUserTest);

            // Assert on the structure of user's folders
            Assert.assertEquals("assertEquals folderList.getList().size()", folderList.getList().size(), 2);
            // Assert on the structure of "rootFolderA"
            TreeFolderElements childrenRootFolderA = geoPlatformService.getChildrenElements(idRootFolderA);
            logger.debug("\n*** childrenRootFolderA:\n{}\n***", childrenRootFolderA);
            Assert.assertNotNull("assertNotNull childrenRootFolderA", childrenRootFolderA);
            Assert.assertEquals("assertEquals childrenRootFolderA.size()", childrenRootFolderA.size(), 1);
            // Assert on layers of "rootFolderA"
            ShortLayerDTO shortVectorLayerRootFolderA = (ShortLayerDTO) childrenRootFolderA.iterator().next();
            Assert.assertEquals("assertEquals shortVectorLayerRootFolderA.getName()", shortVectorLayerRootFolderA.getName(), nameVectorLayer1);
            // Assert on the structure of "rootFolderB"
            TreeFolderElements childrenRootFolderB = geoPlatformService.getChildrenElements(idRootFolderB);
            logger.debug("\n*** childrenRootFolderB:\n{}\n***", childrenRootFolderB);
            Assert.assertNotNull("assertNotNull childrenRootFolderB", childrenRootFolderB);
            Assert.assertEquals("assertEquals childrenRootFolderB.size()", childrenRootFolderB.size(), 2);
            // Assert on layers of "rootFolderB"
            Iterator iterator = childrenRootFolderB.iterator();
            ShortLayerDTO shortRasterLayerRootFolderB = (ShortLayerDTO) iterator.next();
            Assert.assertEquals("assertEquals shortRasterLayerRootFolderB.getName()", shortRasterLayerRootFolderB.getName(), nameVectorLayer2);
            ShortLayerDTO shortVectorLayerRootFolderB = (ShortLayerDTO) iterator.next();
            Assert.assertEquals("assertEquals shortVectorLayerRootFolderB.getName()", shortVectorLayerRootFolderB.getName(), nameRasterLayer2);

            // Assert total number of layers stored into DB after delete
            LayerList allLayersAfterDelete = geoPlatformService.getLayers();
            Assert.assertEquals("assertEquals allLayersAfterDelete.getList().size()", allLayersAfterDelete.getList().size(), totalLayers - 1);
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Folder has an Illegal Parameter");
        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + idRootFolderB + "\" was NOT found");
        } catch (Exception e) {
            Assert.fail("Exception: " + e.getClass());
        }

        // Check ON DELETE CASCADE of the subforders of "rootFolderB"
        checkLayerDeleted(idRasterLayer1);
    }

    // Check if a folder was eliminated
    private void checkLayerDeleted(long idLayer) {
        try {
            ShortLayerDTO layer = geoPlatformService.getShortLayer(idLayer);
            Assert.fail("Layer with id \"" + idLayer + "\" was NOT deleted");
        } catch (Exception e) {
            logger.debug("\n*** Layer with id \"{}\" was deleted ***", idLayer);
        }
    }

    @Test
    public void testGetShortLayer() {
        try {
            ShortLayerDTO layer = geoPlatformService.getShortLayer(idVectorLayer2);
            Assert.assertNotNull("assertNotNull layer", layer);
            Assert.assertEquals("assertEquals layer.getAbstractText()", layer.getAbstractText(), abstractTextVectorLayer2);
            Assert.assertEquals("assertEquals layer.getLayerType()", layer.getLayerType(), GPLayerType.POLYGON);
            Assert.assertEquals("assertEquals layer.getName()", layer.getName(), nameVectorLayer2);
            Assert.assertEquals("assertEquals layer.getSrs()", layer.getSrs(), spatialReferenceSystem);
            Assert.assertEquals("assertEquals layer.getTitle()", layer.getTitle(), titleVectorLayer2);
            Assert.assertEquals("assertEquals layer.getUrlServer()", layer.getUrlServer(), urlServer);
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Layer with id \"" + idVectorLayer2 + "\" was NOT found");
        }
    }

    @Test
    public void testGetBBox() {
        try {
            GPBBox bbox = geoPlatformService.getBBox(idVectorLayer1);
            Assert.assertNotNull("assertNotNull bbox", bbox);
            Assert.assertEquals("assertEquals bbox.getMaxX()", bbox.getMaxX(), 20.0);
            Assert.assertEquals("assertEquals bbox.getMaxY()", bbox.getMaxY(), 20.0);
            Assert.assertEquals("assertEquals bbox.getMinX()", bbox.getMinX(), 10.0);
            Assert.assertEquals("assertEquals bbox.getMinY()", bbox.getMinY(), 10.0);
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Layer with id \"" + idVectorLayer1 + "\" was NOT found");
        }
    }

    @Test
    public void testGetLayerInfo() {
        try {
            GPLayerInfo layerInfo = geoPlatformService.getLayerInfo(idRasterLayer2);
            Assert.assertNotNull("assertNotNull layerInfo", layerInfo);
            Assert.assertEquals("assertEquals layerInfo.getKeywords()", layerInfo.getKeywords(), layerInfoKeyword);
            Assert.assertEquals("assertEquals layerInfo.isQueryable()", layerInfo.isQueryable(), false);
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Layer with id \"" + idRasterLayer2 + "\" was NOT found");
        }
    }
}
