/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.model.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.request.layer.WSAddLayersAndTreeModificationsRequest;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.junit.Assert;
import org.junit.Test;

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
        raster1 = gpWSClient.getRasterLayer(idRaster1);
        // "rootFolderA" ---> "vectorLayer1"
        idVector1 = createAndInsertVectorLayer(rootFolderA, titleVector1,
                "name_" + titleVector1,
                "abstract_" + titleVector1, 4, spatialReferenceSystem, urlServer);
        vector1 = gpWSClient.getVectorLayer(idVector1);
        //
        rootFolderA.setPosition(6);
        rootFolderA.setNumberOfDescendants(2);
        gpWSClient.updateFolder(rootFolderA);

        // "rootFolderB" ---> "rasterLayer2"
        idRaster2 = createAndInsertRasterLayer(rootFolderB, titleRaster2,
                "name_" + titleRaster2,
                "abstract_" + titleRaster2, 2, spatialReferenceSystem, urlServer);
        raster2 = gpWSClient.getRasterLayer(idRaster2);
        // "rootFolderB" ---> "vectorLayer2"
        idVector2 = createAndInsertVectorLayer(rootFolderB, titleVector2,
                "name_" + titleVector2,
                "abstract_" + titleVector2, 1, spatialReferenceSystem, urlServer);
        vector2 = gpWSClient.getVectorLayer(idVector2);
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

        GPLayer newRasterLayer3 = gpWSClient.getRasterLayer(idList.get(0));
        Assert.assertEquals("title of newRasterLayer3", titleRaster3,
                newRasterLayer3.getTitle());
        Assert.assertEquals("position of newRasterLayer3", 7,
                newRasterLayer3.getPosition());

        GPLayer newVectorLayer3 = gpWSClient.getVectorLayer(idList.get(1));
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

        raster1 = gpWSClient.getRasterLayer(idRaster1);
        Assert.assertEquals("Position of raster layer 1 - " + info, positions[1],
                raster1.getPosition());

        vector1 = gpWSClient.getVectorLayer(idVector1);
        Assert.assertEquals("Position of vector layer 1 - " + info, positions[2],
                vector1.getPosition());

        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);
        Assert.assertEquals("Position of root folder B - " + info, positions[3],
                rootFolderB.getPosition());
        Assert.assertNull("Parent of root folder B - " + info,
                rootFolderB.getParent());
        Assert.assertEquals("Number of descendant of root folder B - " + info,
                numberOfDescendants[1], rootFolderB.getNumberOfDescendants());

        raster2 = gpWSClient.getRasterLayer(idRaster2);
        Assert.assertEquals("Position of raster layer 2 - " + info, positions[4],
                raster2.getPosition());

        vector2 = gpWSClient.getVectorLayer(idVector2);
        Assert.assertEquals("Position of vector layer 2 - " + info, positions[5],
                vector2.getPosition());
    }

}
