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
package org.geosdi.geoplatform.model.rest;

import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.rs.GPRestExceptionMessage;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.request.layer.WSAddLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSAddLayersAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDDLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDeleteLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.response.FolderDTO;
import org.geosdi.geoplatform.response.ProjectDTO;
import org.geosdi.geoplatform.response.ShortLayerDTO;
import org.geosdi.geoplatform.response.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.response.collection.TreeFolderElements;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import java.util.*;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class RSLayerTest extends BasicRestServiceTest {
    
    private static final String urlServer = "http://www.geosdi.org/test_rs";
    private static final String newUrlServer = "http://www.geosdi.org/newtest_rs";
    private static final String spatialReferenceSystem = "Geographic coordinate system";
    // Raster Layer 1
    private static final String titleRaster1 = "raster_1_rs";
    private GPRasterLayer raster1 = null;
    private long idRaster1 = -1;
    // Vector Layer 1
    private static final String titleVector1 = "vector_1_rs";
    private GPVectorLayer vector1 = null;
    private long idVector1 = -1;
    // Raster Layer 2
    private static final String titleRaster2 = "raster_2_rs";
    private GPRasterLayer raster2 = null;
    private long idRaster2 = -1;
    // Vector Layer 2
    private static final String titleVector2 = "vector_2_rs";
    private GPVectorLayer vector2 = null;
    private long idVector2 = -1;
    // Raster Layer 3
    private static final String titleRaster3 = "raster_3_rs";
    // Vector Layer 3
    private static final String titleVector3 = "vector_3_rs";
    
    @Override
    public void setUp() throws Exception {
        super.setUp();

        // "rootFolderA" ---> "rasterLayer1"
        idRaster1 = createAndInsertRasterLayer(rootFolderA, titleRaster1,
                "name_" + titleRaster1,
                "abstract_" + titleRaster1, 5, spatialReferenceSystem, urlServer);
        raster1 = gpWSClient.getRasterLayer(idRaster1).getRasterLayer();
        // "rootFolderA" ---> "vectorLayer1"
        idVector1 = createAndInsertVectorLayer(rootFolderA, titleVector1,
                "name_" + titleVector1,
                "abstract_" + titleVector1, 4, spatialReferenceSystem, urlServer);
        vector1 = gpWSClient.getVectorLayer(idVector1).getVectorLayer();
        //
        rootFolderA.setPosition(6);
        rootFolderA.setNumberOfDescendants(2);
        gpWSClient.updateFolder(rootFolderA);

        // "rootFolderB" ---> "rasterLayer2"
        idRaster2 = createAndInsertRasterLayer(rootFolderB, titleRaster2,
                "name_" + titleRaster2,
                "abstract_" + titleRaster2, 2, spatialReferenceSystem, urlServer);
        raster2 = gpWSClient.getRasterLayer(idRaster2).getRasterLayer();
        // "rootFolderB" ---> "vectorLayer2"
        idVector2 = createAndInsertVectorLayer(rootFolderB, titleVector2,
                "name_" + titleVector2,
                "abstract_" + titleVector2, 1, spatialReferenceSystem, urlServer);
        vector2 = gpWSClient.getVectorLayer(idVector2).getVectorLayer();
        //
        rootFolderB.setPosition(3);
        rootFolderB.setNumberOfDescendants(2);
        gpWSClient.updateFolder(rootFolderB);
        
        super.projectTest.setNumberOfElements(
                projectTest.getNumberOfElements() + 4);
        gpWSClient.updateProject(projectTest);
    }
    
    @Test
    public void testAddLayersRest() throws IllegalParameterFault,
            ResourceNotFoundFault {
        List<Long> idList = this.addLayer3Rest();
        
        this.checkStateRest(new int[]{8, 5, 4, 3, 2, 1}, new int[]{4, 2},
                "after add layers");
        
        GPLayer newRasterLayer3 = gpWSClient.getRasterLayer(idList.get(0)).getRasterLayer();
        Assert.assertEquals("title of newRasterLayer3", titleRaster3,
                newRasterLayer3.getTitle());
        Assert.assertEquals("position of newRasterLayer3", 7,
                newRasterLayer3.getPosition());
        
        GPLayer newVectorLayer3 = gpWSClient.getVectorLayer(idList.get(1)).getVectorLayer();
        Assert.assertEquals("title of newVectorLayer3", titleVector3,
                newVectorLayer3.getTitle());
        Assert.assertEquals("position of newVectorLayer3", 6,
                newVectorLayer3.getPosition());
    }
    
    @Test
    public void testGetLayerRest() throws ResourceNotFoundFault {
        ShortLayerDTO shortRasterLayer1 = gpWSClient.getShortLayer(idRaster1);
        Assert.assertNotNull("assertNotNull shortRasterLayer1",
                shortRasterLayer1);
        Assert.assertEquals("assertEquals shortRasterLayer1.getTitle()",
                titleRaster1, shortRasterLayer1.getTitle());
        Assert.assertEquals("assertEquals shortRasterLayer1.getName()",
                "name_" + titleRaster1, shortRasterLayer1.getName());
        Assert.assertEquals("assertEquals shortRasterLayer1.getPosition()", 5,
                shortRasterLayer1.getPosition().intValue());
        Assert.assertEquals("assertEquals shortRasterLayer1.getSrs()",
                spatialReferenceSystem, shortRasterLayer1.getSrs());
        Assert.assertEquals("assertEquals shortRasterLayer1.getUrlServer()",
                urlServer, shortRasterLayer1.getUrlServer());
        Assert.assertEquals("assertEquals shortRasterLayer1.getLayerType()",
                GPLayerType.WMS, shortRasterLayer1.getLayerType());
        
        ShortLayerDTO shortVectorLayer1 = gpWSClient.getShortLayer(idVector1);
        Assert.assertNotNull("assertNotNull shortVectorLayer1",
                shortVectorLayer1);
        Assert.assertEquals("assertEquals shortVectorLayer1.getTitle()",
                titleVector1, shortVectorLayer1.getTitle());
        Assert.assertEquals("assertEquals shortVectorLayer1.getName()",
                "name_" + titleVector1, shortVectorLayer1.getName());
        Assert.assertEquals("assertEquals shortVectorLayer1.getPosition()", 4,
                shortVectorLayer1.getPosition().intValue());
        Assert.assertEquals("assertEquals shortVectorLayer1.getSrs()",
                spatialReferenceSystem, shortVectorLayer1.getSrs());
        Assert.assertEquals("assertEquals shortVectorLayer1.getUrlServer()",
                urlServer, shortVectorLayer1.getUrlServer());
        Assert.assertEquals("assertEquals shortVectorLayer1.getLayerType()",
                GPLayerType.POLYGON, shortVectorLayer1.getLayerType());
    }
    
    @Test
    public void testUpdateRasterLayerRest()
            throws IllegalParameterFault, ResourceNotFoundFault {
        final String titleLayerUpdated = "rasterLayerUpdated_rs";
        
        raster1.setTitle(titleLayerUpdated);
        
        gpWSClient.updateRasterLayer(raster1);
        ShortLayerDTO layerUpdated = gpWSClient.getShortLayer(idRaster1);
        
        Assert.assertNotNull("assertNotNull layerUpdated", layerUpdated);
        Assert.assertEquals("assertEquals layerUpdated.getTitle()",
                titleLayerUpdated, layerUpdated.getTitle());
    }
    
    @Test
    public void testDeleteLayerRest() throws ResourceNotFoundFault {
        // Assert total number of folders stored into DB before delete            
        List<ShortLayerDTO> allLayersBeforeDelete = gpWSClient.getLayers(
                idProjectTest).getLayers();
        int totalLayers = allLayersBeforeDelete.size();
        Assert.assertTrue("assertEquals totalLayers", totalLayers == 4); // SetUp() added 4 layers

        // Delete "rasterLayer1" from "rootFolderA"
        boolean erased = gpWSClient.deleteLayer(idRaster1);
        Assert.assertTrue("Deletion of the layer rasterLayer1", erased);
        
        ProjectDTO projectWithRootFolders = gpWSClient.getProjectWithRootFolders(
                idProjectTest, super.idUserTest);
        Assert.assertNotNull("projectWithRootFolders null",
                projectWithRootFolders);

        // Get root folders for project
        List<FolderDTO> folderList = projectWithRootFolders.getRootFolders();

        // Assert on the structure of project's folders
        Assert.assertEquals("assertEquals folderList.getList().size()",
                folderList.size(), 2);
        // Assert on the structure of "rootFolderA"
        TreeFolderElements childrenRootFolderA = gpWSClient.getChildrenElements(
                idRootFolderA).getFolderElements();
        logger.trace("\n*** childrenRootFolderA:\n{}\n***", childrenRootFolderA);
        Assert.assertNotNull("assertNotNull childrenRootFolderA",
                childrenRootFolderA);
        Assert.assertEquals("assertEquals childrenRootFolderA.size()",
                childrenRootFolderA.size(), 1);
        // Assert on layers of "rootFolderA"
        ShortLayerDTO shortVectorLayerRootFolderA = (ShortLayerDTO) childrenRootFolderA.iterator().next();
        Assert.assertEquals(
                "assertEquals shortVectorLayerRootFolderA.getTitle()",
                shortVectorLayerRootFolderA.getTitle(), titleVector1);
        // Assert on the structure of "rootFolderB"
        TreeFolderElements childrenRootFolderB = gpWSClient.getChildrenElements(
                idRootFolderB).getFolderElements();
        logger.trace("\n*** childrenRootFolderB:\n{}\n***", childrenRootFolderB);
        Assert.assertNotNull("assertNotNull childrenRootFolderB",
                childrenRootFolderB);
        Assert.assertEquals("assertEquals childrenRootFolderB.size()",
                childrenRootFolderB.size(), 2);
        // Assert on layers of "rootFolderB"
        Iterator iterator = childrenRootFolderB.iterator();
        ShortLayerDTO shortRasterLayerRootFolderB = (ShortLayerDTO) iterator.next();
        Assert.assertEquals(
                "assertEquals shortRasterLayerRootFolderB.getTitle()",
                shortRasterLayerRootFolderB.getTitle(), titleRaster2);
        ShortLayerDTO shortVectorLayerRootFolderB = (ShortLayerDTO) iterator.next();
        Assert.assertEquals(
                "assertEquals shortVectorLayerRootFolderB.getTitle()",
                shortVectorLayerRootFolderB.getTitle(), titleVector2);

        // Assert total number of layers stored into DB after delete
        List<ShortLayerDTO> allLayersAfterDelete = gpWSClient.getLayers(
                idProjectTest).getLayers();
        Assert.assertEquals("assertEquals allLayersAfterDelete.getList().size()",
                allLayersAfterDelete.size(), totalLayers - 1);

        // Check ON DELETE CASCADE of the subforders of "rootFolderB"
        this.checkLayerDeletedRest(idRaster1);
    }
    
    @Test
    public void testSaveAndDeleteLayerAndTreeModifications()
            throws IllegalParameterFault, ResourceNotFoundFault {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        
        int totalElementsOfProject = gpWSClient.getNumberOfElementsProject(
                idProjectTest);
        Assert.assertEquals("Initial totalElementsOfProject",
                6, totalElementsOfProject);  // SetUp() added 2 folders + 4 layers

        String titleLayerToTest = "layerToTest";
        GPRasterLayer layerToTest = new GPRasterLayer();
        super.createLayer(layerToTest, rootFolderB, titleLayerToTest,
                "name_" + titleLayerToTest,
                "abstract_" + titleLayerToTest, 3, spatialReferenceSystem,
                urlServer);
        
        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setKeywords(layerInfoKeywords);
        layerInfo.setQueryable(false);
        layerToTest.setLayerInfo(layerInfo);

        // Adding new layer to user's root folder B
        map.put(idRootFolderB, rootFolderB.getNumberOfDescendants() + 1);

        // Adding new layer to user's root folder B
        long idLayerToTest = gpWSClient.saveAddedLayerAndTreeModifications(
                new WSAddLayerAndTreeModificationsRequest(projectTest.getId(),
                        rootFolderB.getId(), layerToTest, descendantsMapData));
        
        GPBBox bbox = gpWSClient.getBBox(idLayerToTest);
        logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@@ LAYER_BBOX : {}@@@@@@@@"
                + "@@@@@@@\n", bbox);
        
        GPLayerInfo li = gpWSClient.getLayerInfo(idLayerToTest);
        logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@ LAYER_INFO : {}@@@@@@@@@"
                + "@@@@@@@\n", li);
        
        GPLayerType layerType = gpWSClient.getLayerType(idLayerToTest);
        logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@ LAYER_TYPE : {}@@@@@@@@@"
                + "@@@@@@@\n", layerType);
        
        Assert.assertEquals("totalElementsOfProject after added",
                totalElementsOfProject + 1,
                gpWSClient.getNumberOfElementsProject(idProjectTest).intValue());
        
        this.checkStateRest(new int[]{7, 6, 5, 4, 2, 1}, new int[]{2, 3},
                "before removing");

        // Removing layer from user's root
        map.clear();
        map.put(idRootFolderB, 2);
        gpWSClient.saveDeletedLayerAndTreeModifications(
                new WSDeleteLayerAndTreeModificationsRequest(idLayerToTest,
                        descendantsMapData));
        
        Assert.assertEquals("totalElementsOfProject after deleted",
                totalElementsOfProject, gpWSClient.getNumberOfElementsProject(
                        idProjectTest).intValue());
        
        this.checkInitialStateRest("after removing");
    }
    
    @Test
    public void testDragAndDropLayerOnSameParentRest()
            throws IllegalParameterFault, ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropLayerOnSameParentRest @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        // Move vector 2 before raster 2 (oldPosition < new Position)
        boolean checkDD = gpWSClient.saveDragAndDropLayerAndTreeModifications(
                new WSDDLayerAndTreeModificationsRequest(idVector2,
                        idRootFolderB, 2, descendantsMapData));
        Assert.assertTrue("Drag and Drop successful", checkDD);
        
        this.checkStateRest(new int[]{6, 5, 4, 3, 1, 2}, new int[]{2, 2},
                "after DD I on same parent");

        // Move vector 2 after raster 2, in initial position (oldPosition > new Position)
        checkDD = gpWSClient.saveDragAndDropLayerAndTreeModifications(
                new WSDDLayerAndTreeModificationsRequest(idVector2,
                        idRootFolderB, 1, descendantsMapData));
        Assert.assertTrue("Vector 2 doesn't moved to position 1", checkDD);
        
        this.checkInitialStateRest("after DD II on same parent");
    }
    
    @Test
    public void testDragAndDropLayerOnDifferentFolderRest()
            throws IllegalParameterFault, ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropLayerOnDifferentFolderRest @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        
        map.put(idRootFolderA, 3);
        map.put(idRootFolderB, 1);
        // Move vector 2 before vector 1 (oldPosition < new Position)
        boolean checkDD = gpWSClient.saveDragAndDropLayerAndTreeModifications(
                new WSDDLayerAndTreeModificationsRequest(idVector2,
                        idRootFolderA, 4, descendantsMapData));
        Assert.assertTrue("Drag and Drop successful", checkDD);
        
        this.checkStateRest(new int[]{6, 5, 3, 2, 1, 4}, new int[]{3, 1},
                "after DD I on different parent");
        Assert.assertEquals(
                "Parent of vector layer 2 after DD I on different parent",
                idRootFolderA, vector2.getFolder().getId().longValue());
        
        map.clear();
        map.put(idRootFolderA, 2);
        map.put(idRootFolderB, 2);
        // Move vector 2 after raster 2, in initial position (oldPosition > new Position)
        checkDD = gpWSClient.saveDragAndDropLayerAndTreeModifications(
                new WSDDLayerAndTreeModificationsRequest(idVector2,
                        idRootFolderB, 1, descendantsMapData));
        Assert.assertTrue("Vector 2 doesn't moved to position 1", checkDD);
        
        this.checkInitialStateRest("after DD II on different parent");
    }
    
    @Test
    public void testTransactionOnAddLayerRest() throws IllegalParameterFault,
            ResourceNotFoundFault {
        logger.trace("\n\t@@@ testTransactionOnAddLayerRest @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        map.put(idRootFolderA, 3);
        try {
            GPRasterLayer raster = new GPRasterLayer();
            super.createLayer(raster, rootFolderA, null, "", "",
                    5, spatialReferenceSystem, urlServer); // Title must be NOT NULL
            gpWSClient.saveAddedLayerAndTreeModifications(
                    new WSAddLayerAndTreeModificationsRequest(
                            projectTest.getId(),
                            rootFolderA.getId(), raster, descendantsMapData));
            Assert.fail("Add layer must fail because title value is null");
        } catch (Exception e) {
            this.checkInitialStateRest("transaction test");
        }
    }
    
    @Test
    public void testTransactionOnRemoveAndAddLayerRest()
            throws IllegalParameterFault, ResourceNotFoundFault {
        logger.trace("\n\t@@@ testTransactionOnRemoveAndAddLayerRest @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        map.put(idRootFolderA, 3);
        try {
            // Delete "rasterLayer1" from "rootFolderA"
            boolean erased = gpWSClient.deleteLayer(idRaster1);
            Assert.assertTrue("Deletion of the layer rasterLayer1", erased);
            
            GPRasterLayer raster = new GPRasterLayer();
            super.createLayer(raster, rootFolderA, null, "", "",
                    5, spatialReferenceSystem, urlServer); // Title must be NOT NULL
            gpWSClient.saveAddedLayerAndTreeModifications(
                    new WSAddLayerAndTreeModificationsRequest(
                            projectTest.getId(),
                            rootFolderA.getId(), raster, descendantsMapData));
            Assert.fail("Add layer must fail because title value is null");
        } catch (ClientErrorException ex) {
            GPRestExceptionMessage exMess = ex.getResponse().readEntity(
                    GPRestExceptionMessage.class);
            logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", exMess);
            try {
                raster1 = gpWSClient.getRasterLayer(idRaster1).getRasterLayer();
                Assert.fail("rasterLayer1 must not exist");
            } catch (NotFoundException rnf) {
                GPRestExceptionMessage rnfMess = rnf.getResponse().readEntity(
                        GPRestExceptionMessage.class);
                logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", rnfMess);
            }
        }
    }
    
    @Test
    public void testGetShortLayerRest() throws ResourceNotFoundFault {
        ShortLayerDTO layer = gpWSClient.getShortLayer(idVector2);
        Assert.assertNotNull("assertNotNull layer", layer);
        Assert.assertEquals("assertEquals layer.getLayerType()",
                layer.getLayerType(), GPLayerType.POLYGON);
        Assert.assertEquals("assertEquals layer.getName()", layer.getName(),
                vector2.getName());
        Assert.assertEquals("assertEquals layer.getSrs()", layer.getSrs(),
                spatialReferenceSystem);
        Assert.assertEquals("assertEquals layer.getUrlServer()",
                layer.getUrlServer(), urlServer);
    }
    
    @Test
    public void testGetBBoxRest() throws ResourceNotFoundFault {
        GPBBox bbox = gpWSClient.getBBox(idVector1);
        Assert.assertNotNull("assertNotNull bbox", bbox);
        Assert.assertEquals("assertEquals bbox.getMaxX()", 0, Double.compare(
                bbox.getMaxX(), 20.0));
        Assert.assertEquals("assertEquals bbox.getMaxY()", 0, Double.compare(
                bbox.getMaxY(), 20.0));
        Assert.assertEquals("assertEquals bbox.getMinX()", 0, Double.compare(
                bbox.getMinX(), 10.0));
        Assert.assertEquals("assertEquals bbox.getMinY()", 0, Double.compare(
                bbox.getMinY(), 10.0));
    }
    
    @Test
    public void testCorrectnessOnAddLayersRest() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testCorrectnessOnAddLayers @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        
        ArrayList<GPLayer> arrayList = new ArrayList<GPLayer>();
        try {
            List<Long> longList = gpWSClient.saveAddedLayersAndTreeModifications(
                    new WSAddLayersAndTreeModificationsRequest(
                            projectTest.getId(), rootFolderA.getId(), arrayList,
                            descendantsMapData)).getElements();
            Assert.fail("Test must fail because list of layers is empty");
        } catch (Exception ex) {
            this.checkInitialStateRest("correctess on AddLayers");
        }
    }
    
    @Test
    public void testGetLayerInfoRest() throws ResourceNotFoundFault {
        GPLayerInfo layerInfo = gpWSClient.getLayerInfo(idRaster2);
        Assert.assertNotNull("assertNotNull layerInfo", layerInfo);
        Assert.assertEquals("assertEquals layerInfo.isQueryable()", false,
                layerInfo.isQueryable());
        List<String> keywords = layerInfo.getKeywords();
        Assert.assertNotNull("assertNotNull keywords of layerInfo", keywords);
        Assert.assertEquals("assertEquals layerInfo.getKeywords()",
                layerInfoKeywords.size(), keywords.size());
        for (int i = 0; i < keywords.size(); i++) {
            String key = keywords.get(i);
            Assert.assertEquals("assert keyword: index = " + i,
                    layerInfoKeywords.get(i), key);
        }
    }
    
    @Test
    public void testGetLayersDataSourceByProjectRest()
            throws IllegalParameterFault, ResourceNotFoundFault {
        this.addLayer3Rest();
        
        List<String> list = gpWSClient.getLayersDataSourceByProjectID(
                idProjectTest).getDataSources();
        
        Assert.assertEquals("Number of elements of server's url", 2, list.size());
        Assert.assertTrue("List does not contain 'http://www.geosdi.org/test'",
                list.contains(urlServer));
        Assert.assertTrue(
                "List does not contain 'http://www.geosdi.org/newtest'",
                list.contains(newUrlServer));
    }
    
    @Test
    public void saveCheckStatusLayerAndTreeModificationsRest() throws Exception {
        Assert.assertTrue(gpWSClient.saveCheckStatusLayerAndTreeModifications(
                idRaster1, true));
    }
    
    private void checkInitialStateRest(String info)
            throws ResourceNotFoundFault {
        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);
        Assert.assertEquals("Position of root folder A - " + info, 6,
                rootFolderA.getPosition());
        Assert.assertNull("Parent of root folder A - " + info,
                rootFolderA.getParent());
        Assert.assertEquals("Number of descendant of root folder A - " + info, 2,
                rootFolderA.getNumberOfDescendants());
        
        raster1 = gpWSClient.getRasterLayer(idRaster1).getRasterLayer();
        Assert.assertEquals("Position of raster layer 1 - " + info, 5,
                raster1.getPosition());
        Assert.assertEquals("Parent of raster layer 1 - " + info, idRootFolderA,
                raster1.getFolder().getId().longValue());
        
        vector1 = gpWSClient.getVectorLayer(idVector1).getVectorLayer();
        Assert.assertEquals("Position of vector layer 1 - " + info, 4,
                vector1.getPosition());
        Assert.assertEquals("Parent of vector layer 1 - " + info, idRootFolderA,
                vector1.getFolder().getId().longValue());
        
        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);
        Assert.assertEquals("Position of root folder B - " + info, 3,
                rootFolderB.getPosition());
        Assert.assertNull("Parent of root folder B - " + info,
                rootFolderB.getParent());
        Assert.assertEquals("Number of descendant of root folder B - " + info, 2,
                rootFolderB.getNumberOfDescendants());
        
        raster2 = gpWSClient.getRasterLayer(idRaster2).getRasterLayer();
        Assert.assertEquals("Position of raster layer 2 - " + info, 2,
                raster2.getPosition());
        Assert.assertEquals("Parent of raster layer 2 - " + info, idRootFolderB,
                raster2.getFolder().getId().longValue());
        
        vector2 = gpWSClient.getVectorLayer(idVector2).getVectorLayer();
        Assert.assertEquals("Position of vector layer 2 - " + info, 1,
                vector2.getPosition());
        Assert.assertEquals("Parent of vector layer 2 - " + info, idRootFolderB,
                vector2.getFolder().getId().longValue());
    }
    
    private ArrayList<Long> addLayer3Rest() throws IllegalParameterFault,
            ResourceNotFoundFault {
        // "rootFolderA" ---> "rasterLayer3"
        GPRasterLayer rasterLayer3 = new GPRasterLayer();
        super.createLayer(rasterLayer3, rootFolderA, titleRaster3, "", "",
                7, spatialReferenceSystem, newUrlServer);
        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setKeywords(layerInfoKeywords);
        layerInfo.setQueryable(false);
        rasterLayer3.setLayerInfo(layerInfo);
        // "rootFolderA" ---> "vectorLayer3"
        GPVectorLayer vectorLayer3 = new GPVectorLayer();
        vectorLayer3.setLayerType(GPLayerType.POINT);
        super.createLayer(vectorLayer3, rootFolderA, titleVector3, "", "",
                6, spatialReferenceSystem, newUrlServer);
        //
        ArrayList<GPLayer> arrayList = new ArrayList<GPLayer>();
        arrayList.add(rasterLayer3);
        arrayList.add(vectorLayer3);
        
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(idRootFolderA, rootFolderA.getNumberOfDescendants() + 2);
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        
        return (ArrayList<Long>) gpWSClient.saveAddedLayersAndTreeModifications(
                new WSAddLayersAndTreeModificationsRequest(projectTest.getId(),
                        rootFolderA.getId(), arrayList, descendantsMapData))
                .getElements();
    }
    
    private void checkStateRest(int[] positions, int[] numberOfDescendants,
            String info)
            throws ResourceNotFoundFault {
        Assert.assertEquals("Array positions must have exactly 6 elements", 6,
                positions.length);
        Assert.assertEquals(
                "Array numberOfDescendants must have exactly 2 elements", 2,
                numberOfDescendants.length);
        
        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);
        Assert.assertEquals("Position of root folder A - " + info, positions[0],
                rootFolderA.getPosition());
        Assert.assertNull("Parent of root folder A - " + info,
                rootFolderA.getParent());
        Assert.assertEquals("Number of descendant of root folder A - " + info,
                numberOfDescendants[0], rootFolderA.getNumberOfDescendants());
        
        raster1 = gpWSClient.getRasterLayer(idRaster1).getRasterLayer();
        Assert.assertEquals("Position of raster layer 1 - " + info, positions[1],
                raster1.getPosition());
        
        vector1 = gpWSClient.getVectorLayer(idVector1).getVectorLayer();
        Assert.assertEquals("Position of vector layer 1 - " + info, positions[2],
                vector1.getPosition());
        
        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);
        Assert.assertEquals("Position of root folder B - " + info, positions[3],
                rootFolderB.getPosition());
        Assert.assertNull("Parent of root folder B - " + info,
                rootFolderB.getParent());
        Assert.assertEquals("Number of descendant of root folder B - " + info,
                numberOfDescendants[1], rootFolderB.getNumberOfDescendants());
        
        raster2 = gpWSClient.getRasterLayer(idRaster2).getRasterLayer();
        Assert.assertEquals("Position of raster layer 2 - " + info, positions[4],
                raster2.getPosition());
        
        vector2 = gpWSClient.getVectorLayer(idVector2).getVectorLayer();
        Assert.assertEquals("Position of vector layer 2 - " + info, positions[5],
                vector2.getPosition());
    }

    /**
     * Check if a layer was eliminated.
     */
    private void checkLayerDeletedRest(long idLayer) {
        try {
            gpWSClient.getShortLayer(idLayer);
            Assert.fail("Layer with id \"" + idLayer + "\" was NOT deleted");
        } catch (Exception ex) {
            logger.trace("Layer with id {} was deleted", idLayer);
        }
    }
    
}
