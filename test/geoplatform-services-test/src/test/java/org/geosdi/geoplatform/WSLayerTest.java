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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.LayerList;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSLayerTest extends ServiceTest {

    private final String urlServer = "http://www.geosdi.org/test";
    private final String spatialReferenceSystem = "Geographic coordinate system";
    // Raster Layer 1
    private final String nameRaster1 = "raster_1";
    private GPRasterLayer raster1 = null;
    private long idRaster1 = -1;
    // Vector Layer 1
    private final String nameVector1 = "vector_1";
    private GPVectorLayer vector1 = null;
    private long idVector1 = -1;
    // Raster Layer 2
    private final String nameRaster2 = "raster_2";
    private GPRasterLayer raster2 = null;
    private long idRaster2 = -1;
    // Vector Layer 2
    private final String nameVector2 = "vector_2";
    private GPVectorLayer vector2 = null;
    private long idVector2 = -1;

    @Before
    // "position" will be set without application logic, but only to have different values
    public void setUp() throws Exception {
        super.setUp();

        // "rootFolderA" ---> "rasterLayer1"
        idRaster1 = createAndInsertRasterLayer("abstract_" + nameRaster1, rootFolderA, nameRaster1, 5,
                false, spatialReferenceSystem, "title" + nameRaster1, urlServer);
        raster1 = geoPlatformService.getRasterLayer(idRaster1);
        // "rootFolderA" ---> "vectorLayer1"
        idVector1 = createAndInsertVectorLayer("abstract_" + nameVector1, rootFolderA, nameVector1, 4,
                false, spatialReferenceSystem, "title_" + nameVector1, urlServer);
        vector1 = geoPlatformService.getVectorLayer(idVector1);
        //
        rootFolderA.setPosition(6);
        rootFolderA.setNumberOfDescendants(2);
        geoPlatformService.updateFolder(rootFolderA);

        // "rootFolderB" ---> "rasterLayer2"
        idRaster2 = createAndInsertRasterLayer("abstract_" + nameRaster2, rootFolderB, nameRaster2, 2,
                false, spatialReferenceSystem, "title_" + nameRaster2, urlServer);
        raster2 = geoPlatformService.getRasterLayer(idRaster2);
        // "rootFolderB" ---> "vectorLayer2"
        idVector2 = createAndInsertVectorLayer("abstract_" + nameVector2, rootFolderB, nameVector2, 1,
                false, spatialReferenceSystem, "title_" + nameVector2, urlServer);
        vector2 = geoPlatformService.getVectorLayer(idVector2);
        //
        rootFolderB.setPosition(3);
        rootFolderB.setNumberOfDescendants(2);
        geoPlatformService.updateFolder(rootFolderB);
    }

    @Test
    public void testAddLayers() {
        try {
            String nameRasterLayer3 = "Raster Layer 3";
            String nameVectorLayer3 = "Vector Layer 3";

            // "rootFolderA" ---> "rasterLayer3"
            GPRasterLayer rasterLayer3 = new GPRasterLayer();
            createLayer(rasterLayer3, "", rootFolderA, nameRasterLayer3, 6,
                    false, spatialReferenceSystem, "", urlServer);
            GPLayerInfo layerInfo = new GPLayerInfo();
            layerInfo.setKeywords(layerInfoKeywords);
            layerInfo.setQueryable(false);
            rasterLayer3.setLayerInfo(layerInfo);
            // "rootFolderA" ---> "vectorLayer3"
            GPVectorLayer vectorLayer3 = new GPVectorLayer();
            createLayer(vectorLayer3, "", rootFolderA, nameVectorLayer3, 7,
                    false, spatialReferenceSystem, "", urlServer);
            //
            ArrayList<GPLayer> arrayList = new ArrayList<GPLayer>();
            arrayList.add(rasterLayer3);
            arrayList.add(vectorLayer3);

            Map<Long, Integer> map = new HashMap<Long, Integer>();
            map.put(idRootFolderA, 4);
            GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
            descendantsMapData.setDescendantsMap(map);

            ArrayList<Long> idList = geoPlatformService.saveAddedLayersAndTreeModifications(arrayList, descendantsMapData);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("position of rootFolderA", 8, rootFolderA.getPosition());

            GPLayer newRasterLayer3 = geoPlatformService.getRasterLayer(idList.get(0));
            Assert.assertEquals("name of newRasterLayer3", nameRasterLayer3, newRasterLayer3.getName());
            Assert.assertEquals("position of newRasterLayer3", 6, newRasterLayer3.getPosition());

            GPLayer newVectorLayer3 = geoPlatformService.getVectorLayer(idList.get(1));
            Assert.assertEquals("name of newVectorLayer3", nameVectorLayer3, newVectorLayer3.getName());
            Assert.assertEquals("position of newVectorLayer3", 7, newVectorLayer3.getPosition());
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Layer has an Illegal Parameter");
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Layer with ID \"" + rnnf.getId() + "\"has a resource not found");
        }
    }

    @Test
    public void testGetLayer() {
        try {
            ShortLayerDTO shortRasterLayer1 = geoPlatformService.getShortLayer(idRaster1);
            Assert.assertNotNull("assertNotNull shortRasterLayer1", shortRasterLayer1);
            Assert.assertEquals("assertEquals shortRasterLayer1.getName()", shortRasterLayer1.getName(), nameRaster1);
            Assert.assertEquals("assertEquals shortRasterLayer1.getPosition()", shortRasterLayer1.getPosition(), 5);
            Assert.assertEquals("assertEquals shortRasterLayer1.getSrs()", shortRasterLayer1.getSrs(), spatialReferenceSystem);
            Assert.assertEquals("assertEquals shortRasterLayer1.getUrlServer()", shortRasterLayer1.getUrlServer(), urlServer);
            Assert.assertEquals("assertEquals shortRasterLayer1.getLayerType()", shortRasterLayer1.getLayerType(), GPLayerType.RASTER);

            ShortLayerDTO shortVectorLayer1 = geoPlatformService.getShortLayer(idVector1);
            Assert.assertNotNull("assertNotNull shortVectorLayer1", shortVectorLayer1);
            Assert.assertEquals("assertEquals shortVectorLayer1.getName()", shortVectorLayer1.getName(), nameVector1);
            Assert.assertEquals("assertEquals shortVectorLayer1.getPosition()", shortVectorLayer1.getPosition(), 4);
            Assert.assertEquals("assertEquals shortVectorLayer1.getSrs()", shortVectorLayer1.getSrs(), spatialReferenceSystem);
            Assert.assertEquals("assertEquals shortVectorLayer1.getUrlServer()", shortVectorLayer1.getUrlServer(), urlServer);
            Assert.assertEquals("assertEquals shortVectorLayer1.getLayerType()", shortVectorLayer1.getLayerType(), GPLayerType.POLYGON);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Layer with ID \"" + rnnf.getId() + "\" has a resource not found");
        }
    }

    @Test
    public void testUpdateRasterLayer() {
        final String nameLayerUpdated = "rasterLayerUpdated";
        try {
            raster1.setFolder(rootFolderB);
            raster1.setName(nameLayerUpdated);

            geoPlatformService.updateRasterLayer(raster1);
            ShortLayerDTO layerUpdated = geoPlatformService.getShortLayer(idRaster1);

            Assert.assertNotNull("assertNotNull layerUpdated", layerUpdated);
            Assert.assertEquals("assertEquals layerUpdated.getName()", layerUpdated.getName(), nameLayerUpdated);
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Layer has an Illegal Parameter");
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Layer with id \"" + rnnf.getId() + "\" was NOT found");
        }

        try {
            FolderDTO folderA = geoPlatformService.getShortFolder(new RequestById(idRootFolderA));
            Assert.assertNotNull("assertNotNull folderA", folderA);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Folder with id \"" + rnnf.getId() + "\" was NOT found");
        }

        try {
            FolderDTO folderB = geoPlatformService.getShortFolder(new RequestById(idRootFolderB));
            Assert.assertNotNull("assertNotNull folderB", folderB);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Folder with id \"" + rnnf.getId() + "\" was NOT found");
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
            boolean erased = geoPlatformService.deleteLayer(new RequestById(idRaster1));
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
            Assert.assertEquals("assertEquals shortVectorLayerRootFolderA.getName()", shortVectorLayerRootFolderA.getName(), nameVector1);
            // Assert on the structure of "rootFolderB"
            TreeFolderElements childrenRootFolderB = geoPlatformService.getChildrenElements(idRootFolderB);
            logger.debug("\n*** childrenRootFolderB:\n{}\n***", childrenRootFolderB);
            Assert.assertNotNull("assertNotNull childrenRootFolderB", childrenRootFolderB);
            Assert.assertEquals("assertEquals childrenRootFolderB.size()", childrenRootFolderB.size(), 2);
            // Assert on layers of "rootFolderB"
            Iterator iterator = childrenRootFolderB.iterator();
            ShortLayerDTO shortRasterLayerRootFolderB = (ShortLayerDTO) iterator.next();
            Assert.assertEquals("assertEquals shortRasterLayerRootFolderB.getName()", shortRasterLayerRootFolderB.getName(), nameRaster2);
            ShortLayerDTO shortVectorLayerRootFolderB = (ShortLayerDTO) iterator.next();
            Assert.assertEquals("assertEquals shortVectorLayerRootFolderB.getName()", shortVectorLayerRootFolderB.getName(), nameVector2);

            // Assert total number of layers stored into DB after delete
            LayerList allLayersAfterDelete = geoPlatformService.getLayers();
            Assert.assertEquals("assertEquals allLayersAfterDelete.getList().size()", allLayersAfterDelete.getList().size(), totalLayers - 1);
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Folder has an Illegal Parameter");
        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was NOT found");
        } catch (Exception e) {
            Assert.fail("Exception: " + e.getClass());
        }
        // Check ON DELETE CASCADE of the subforders of "rootFolderB"
        checkLayerDeleted(idRaster1);
    }

    @Test
    public void testSaveAndDeleteLayerAndTreeModifications() {
        GPRasterLayer layerToTest = null;
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            String nameLayerToTest = "layerToTest";
            layerToTest = new GPRasterLayer();
            super.createLayer(layerToTest, "abstract_" + nameLayerToTest, rootFolderB, nameLayerToTest,
                    3, false, spatialReferenceSystem, "title_" + nameLayerToTest, urlServer);

            GPLayerInfo layerInfo = new GPLayerInfo();
            layerInfo.setKeywords(layerInfoKeywords);
            layerInfo.setQueryable(false);
            layerToTest.setLayerInfo(layerInfo);

            // Adding new layer to user's root folder B
            map.put(idRootFolderB, 3);

            // Adding new layer to user's root folder B
            long idLayerToTest = geoPlatformService.saveAddedLayerAndTreeModifications(layerToTest, descendantsMapData);
            layerToTest.setId(idLayerToTest);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A before removing", 7, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderA.getNumberOfDescendants());

            raster1 = geoPlatformService.getRasterLayer(idRaster1);
            Assert.assertEquals("Position of raster layer 1 before removing", 6, raster1.getPosition());

            vector1 = geoPlatformService.getVectorLayer(idVector1);
            Assert.assertEquals("Position of vector layer 1 before removing", 5, vector1.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B before removing", 4, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B before removing", 3, rootFolderB.getNumberOfDescendants());

            raster2 = geoPlatformService.getRasterLayer(idRaster2);
            Assert.assertEquals("Position of raster layer 2 before removing", 2, raster2.getPosition());

            vector2 = geoPlatformService.getVectorLayer(idVector2);
            Assert.assertEquals("Position of vector layer 2 before removing", 1, vector2.getPosition());

            // Removing layer from user's root
            map.clear();
            map.put(idRootFolderB, 2);
            geoPlatformService.saveDeletedLayerAndTreeModifications(layerToTest.getId(), descendantsMapData);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after removing", 6, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderA.getNumberOfDescendants());

            raster1 = geoPlatformService.getRasterLayer(idRaster1);
            Assert.assertEquals("Position of raster layer 1 after removing", 5, raster1.getPosition());

            vector1 = geoPlatformService.getVectorLayer(idVector1);
            Assert.assertEquals("Position of vector layer 1 after removing", 4, vector1.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after removing", 3, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderB.getNumberOfDescendants());

            raster2 = geoPlatformService.getRasterLayer(idRaster2);
            Assert.assertEquals("Position of raster layer 2 after removing", 2, raster2.getPosition());

            vector2 = geoPlatformService.getVectorLayer(idVector2);
            Assert.assertEquals("Position of vector layer 2 after removing", 1, vector2.getPosition());
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Folder with id \"" + layerToTest.getId() + "\" was not found");
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Folder was not found " + rnnf.getId());
        }
    }

    @Test
    public void testDragAndDropLayerOnSameParent() {
        logger.trace("\n\t@@@ testDragAndDropLayerOnSameParent @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            // Move vector 2 before raster 2 (oldPosition < new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropLayerAndTreeModifications(
                    idVector2, idRootFolderB, 2, descendantsMapData);
            Assert.assertTrue("Drag and Drop successful", checkDD);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after drag and drop operation", 6, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after drag and drop operation", 2, rootFolderA.getNumberOfDescendants());

            raster1 = geoPlatformService.getRasterLayer(idRaster1);
            Assert.assertEquals("Position of raster layer 1 after drag and drop operation", 5, raster1.getPosition());
            Assert.assertEquals("Parent of raster layer 1 after drag and drop operation", idRootFolderA, raster1.getFolder().getId());

            vector1 = geoPlatformService.getVectorLayer(idVector1);
            Assert.assertEquals("Position of vector layer 1 after drag and drop operation", 4, vector1.getPosition());
            Assert.assertEquals("Parent of vector layer 1 after drag and drop operation", idRootFolderA, vector1.getFolder().getId());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after drag and drop operation", 3, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after drag and drop operation", 2, rootFolderB.getNumberOfDescendants());

            raster2 = geoPlatformService.getRasterLayer(idRaster2);
            Assert.assertEquals("Position of raster layer 2 after drag and drop operation", 1, raster2.getPosition());
            Assert.assertEquals("Parent of raster layer 2 after drag and drop operation", idRootFolderB, raster2.getFolder().getId());

            vector2 = geoPlatformService.getVectorLayer(idVector2);
            Assert.assertEquals("Position of vector layer 2 after drag and drop operation", 2, vector2.getPosition());
            Assert.assertEquals("Parent of vector layer 2 after drag and drop operation", idRootFolderB, vector2.getFolder().getId());

            // Move vector 2 after raster 2, in initial position (oldPosition > new Position)
            checkDD = geoPlatformService.saveDragAndDropLayerAndTreeModifications(
                    idVector2, idRootFolderB, 1, descendantsMapData);
            Assert.assertTrue("Vector 2 doesn't moved to position 1", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Folder or Layer with ID \"" + rnnf.getId() + "\" was not found");
        }
    }

    @Test
    public void testDragAndDropLayerOnDifferentFolder() {
        logger.trace("\n\t@@@ testDragAndDropLayerOnDifferentFolder @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            map.put(idRootFolderA, 3);
            map.put(idRootFolderB, 1);
            // Move vector 2 before vector 1 (oldPosition < new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropLayerAndTreeModifications(
                    idVector2, idRootFolderA, 4, descendantsMapData);
            Assert.assertTrue("Drag and Drop successful", checkDD);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after drag and drop operation", 6, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after drag and drop operation", 3, rootFolderA.getNumberOfDescendants());

            raster1 = geoPlatformService.getRasterLayer(idRaster1);
            Assert.assertEquals("Position of raster layer 1 after drag and drop operation", 5, raster1.getPosition());
            Assert.assertEquals("Parent of raster layer 1 after drag and drop operation", idRootFolderA, raster1.getFolder().getId());

            vector1 = geoPlatformService.getVectorLayer(idVector1);
            Assert.assertEquals("Position of vector layer 1 after drag and drop operation", 3, vector1.getPosition());
            Assert.assertEquals("Parent of vector layer 1 after drag and drop operation", idRootFolderA, vector1.getFolder().getId());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after drag and drop operation", 2, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after drag and drop operation", 1, rootFolderB.getNumberOfDescendants());

            raster2 = geoPlatformService.getRasterLayer(idRaster2);
            Assert.assertEquals("Position of raster layer 2 after drag and drop operation", 1, raster2.getPosition());
            Assert.assertEquals("Parent of raster layer 2 after drag and drop operation", idRootFolderB, raster2.getFolder().getId());

            vector2 = geoPlatformService.getVectorLayer(idVector2);
            Assert.assertEquals("Position of vector layer 2 after drag and drop operation", 4, vector2.getPosition());
            Assert.assertEquals("Parent of vector layer 2 after drag and drop operation", idRootFolderA, vector2.getFolder().getId());

            map.clear();
            map.put(idRootFolderA, 2);
            map.put(idRootFolderB, 2);
            // Move vector 2 after raster 2, in initial position (oldPosition > new Position)
            checkDD = geoPlatformService.saveDragAndDropLayerAndTreeModifications(
                    idVector2, idRootFolderB, 1, descendantsMapData);
            Assert.assertTrue("Vector 2 doesn't moved to position 1", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Folder or Layer with ID \"" + rnnf.getId() + "\" was not found");
        }
    }

    @Test
    public void testGetShortLayer() {
        try {
            ShortLayerDTO layer = geoPlatformService.getShortLayer(idVector2);
            Assert.assertNotNull("assertNotNull layer", layer);
            Assert.assertEquals("assertEquals layer.getLayerType()", layer.getLayerType(), GPLayerType.POLYGON);
            Assert.assertEquals("assertEquals layer.getName()", layer.getName(), nameVector2);
            Assert.assertEquals("assertEquals layer.getSrs()", layer.getSrs(), spatialReferenceSystem);
            Assert.assertEquals("assertEquals layer.getUrlServer()", layer.getUrlServer(), urlServer);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Layer with id \"" + rnnf.getId() + "\" was NOT found");
        }
    }

    @Test
    public void testGetBBox() {
        try {
            GPBBox bbox = geoPlatformService.getBBox(idVector1);
            Assert.assertNotNull("assertNotNull bbox", bbox);
            Assert.assertEquals("assertEquals bbox.getMaxX()", bbox.getMaxX(), 20.0);
            Assert.assertEquals("assertEquals bbox.getMaxY()", bbox.getMaxY(), 20.0);
            Assert.assertEquals("assertEquals bbox.getMinX()", bbox.getMinX(), 10.0);
            Assert.assertEquals("assertEquals bbox.getMinY()", bbox.getMinY(), 10.0);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Layer with id \"" + rnnf.getId() + "\" was NOT found");
        }
    }

    @Test
    public void testGetLayerInfo() {
        try {
            GPLayerInfo layerInfo = geoPlatformService.getLayerInfo(idRaster2);
            Assert.assertNotNull("assertNotNull layerInfo", layerInfo);
            Assert.assertEquals("assertEquals layerInfo.isQueryable()", false, layerInfo.isQueryable());
            List<String> keywords = layerInfo.getKeywords();
            Assert.assertNotNull("assertNotNull keywords of layerInfo", keywords);
            Assert.assertEquals("assertEquals layerInfo.getKeywords()", layerInfoKeywords.size(), keywords.size());
            for (int i = 0; i < keywords.size(); i++) {
                String key = keywords.get(i);
                Assert.assertEquals("assert keyword: index = " + i, layerInfoKeywords.get(i), key);
            }
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Layer with id \"" + rnnf.getId() + "\" was NOT found");
        }
    }

    private void checkInitialState()
            throws ResourceNotFoundFault {
        rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
        Assert.assertEquals("Position of root folder A after DD II", 6, rootFolderA.getPosition());
        Assert.assertEquals("Number of descendant of root folder A after DD II", 2, rootFolderA.getNumberOfDescendants());

        raster1 = geoPlatformService.getRasterLayer(idRaster1);
        Assert.assertEquals("Position of raster layer 1 after DD II", 5, raster1.getPosition());
        Assert.assertEquals("Parent of raster layer 1 after DD II", idRootFolderA, raster1.getFolder().getId());

        vector1 = geoPlatformService.getVectorLayer(idVector1);
        Assert.assertEquals("Position of vector layer 1 after DD II", 4, vector1.getPosition());
        Assert.assertEquals("Parent of vector layer 1 after DD II", idRootFolderA, vector1.getFolder().getId());

        rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
        Assert.assertEquals("Position of root folder B after DD II", 3, rootFolderB.getPosition());
        Assert.assertEquals("Number of descendant of root folder B after DD II", 2, rootFolderB.getNumberOfDescendants());

        raster2 = geoPlatformService.getRasterLayer(idRaster2);
        Assert.assertEquals("Position of raster layer 2 after DD II", 2, raster2.getPosition());
        Assert.assertEquals("Parent of raster layer 2 after DD II", idRootFolderB, raster2.getFolder().getId());

        vector2 = geoPlatformService.getVectorLayer(idVector2);
        Assert.assertEquals("Position of vector layer 2 after DD II", 1, vector2.getPosition());
        Assert.assertEquals("Parent of vector layer 2 after DD II", idRootFolderB, vector2.getFolder().getId());
    }

    // Check if a folder was eliminated
    private void checkLayerDeleted(long idLayer) {
        try {
            ShortLayerDTO layer = geoPlatformService.getShortLayer(idLayer);
            Assert.fail("Layer with id \"" + idLayer + "\" was NOT deleted");
        } catch (ResourceNotFoundFault rnnf) {
            logger.trace("Layer with id {} was deleted", idLayer);
        }
    }
}
