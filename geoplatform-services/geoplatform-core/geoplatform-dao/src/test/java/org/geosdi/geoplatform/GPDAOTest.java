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

import java.util.HashMap;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPUser;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test is not intended test the business logic but only the correctness
 * of the updates on DAO
 * 
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class GPDAOTest extends BaseDAOTest {

    // User
    private String nameUser = "user_position_test";
    private GPUser userPositionTest = null;
    // Folders
    private GPFolder userFolder;
    private GPFolder folderA;
    private GPFolder folderB;
    // Layers
    private GPRasterLayer rasterLayer;
    private GPVectorLayer vectorLayer;
    // Position of the last leaf in preorder visit (in business tree, equal 0)
    private int beginPosition = 333000;
    // Position of the firt child of the root (in business tree, equal total_number_of_element)
    private int endPosition = -1; // > beginPosition

    @Before
    public void setUp() {
        logger.info("\n\t@@@ " + getClass().getSimpleName() + ".setUp @@@");
        userPositionTest = super.insertUser(nameUser);

        endPosition = beginPosition + 930;
        userFolder = super.createUserFolder("folder_of_" + nameUser, beginPosition + 900, userPositionTest); // 333930
        userFolder.setNumberOfDescendants(13);
        userFolder.setChecked(false);
        //
        folderDAO.persist(userFolder);

        folderA = super.createEmptyFolder("folder_position_test_A", beginPosition + 600, userFolder); // 333630        
        folderB = super.createEmptyFolder("folder_position_test_B", beginPosition + 300, userFolder); // 333330
        folderA.setNumberOfDescendants(3);
        folderB.setNumberOfDescendants(9);
        folderA.setChecked(false);
        folderB.setChecked(true);
        //
        folderDAO.persist(folderA, folderB);

        rasterLayer = super.createRasterLayer1(beginPosition + 30, folderB); // 333030
        vectorLayer = super.createVectorLayer1(beginPosition, folderB); // 333000
        rasterLayer.setName(rasterLayer.getName() + "_position_test");
        vectorLayer.setName(vectorLayer.getName() + "_position_test");
        rasterLayer.setChecked(false);
        vectorLayer.setChecked(true);
        //
        layerDAO.persist(rasterLayer, vectorLayer);
    }

    @After
    public void tearDown() {
        logger.info("\n\t@@@ " + getClass().getSimpleName() + ".tearDown @@@");
        // Remove user and his folders and layers
        userDAO.remove(userPositionTest);
    }

    /**
     * Test of updatePositionsRange method for Folders
     */
    @Test
    public void testIncreasePositionsFolders() {
        logger.trace("\n\t@@@ testIncreasePositionsFolders @@@");
        int deltaValue = 1;
        boolean check = folderDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Increase Position NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(userFolder.getId());
        Assert.assertEquals("Position NOT increased for \"" + userFolder.getName() + "\"",
                userFolderUpdated.getPosition(), userFolder.getPosition() + deltaValue);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Position NOT increased for \"" + folderA.getName() + "\"",
                folderAUpdated.getPosition(), folderA.getPosition() + deltaValue);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Position NOT increased for \"" + folderB.getName() + "\"",
                folderBUpdated.getPosition(), folderB.getPosition() + deltaValue);
    }

    @Test
    public void testDecreasePositionsFolders() {
        logger.trace("\n\t@@@ testDecreasePositionsFolders @@@");
        int deltaValue = -1;
        boolean check = folderDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(userFolder.getId());
        Assert.assertEquals("Position NOT decreased for \"" + userFolder.getName() + "\"",
                userFolderUpdated.getPosition(), userFolder.getPosition() + deltaValue);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Position NOT decreased for \"" + folderA.getName() + "\"",
                folderAUpdated.getPosition(), folderA.getPosition() + deltaValue);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Position NOT decreased for \"" + folderB.getName() + "\"",
                folderBUpdated.getPosition(), folderB.getPosition() + deltaValue);
    }

    /**
     * Test of updatePositionsRange method for Layers
     */
    @Test
    public void testIncreasePositionsLayers() {
        logger.trace("\n\t@@@ testIncreasePositionsLayers @@@");
        int deltaValue = 1;
        boolean check = layerDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Increase Position NOT done", check);

        GPLayer rasterLayerUpdated = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Position NOT increased for \"" + rasterLayer.getName() + "\"",
                rasterLayerUpdated.getPosition(), rasterLayer.getPosition() + deltaValue);

        GPLayer vectorLayerUpdated = layerDAO.find(vectorLayer.getId());
        Assert.assertEquals("Position NOT increased for \"" + vectorLayer.getName() + "\"",
                vectorLayerUpdated.getPosition(), vectorLayer.getPosition() + deltaValue);
    }

    @Test
    public void testDecreasePositionsLayers() {
        logger.trace("\n\t@@@ testDecreasePositionsLayers @@@");
        int deltaValue = -1;
        boolean check = layerDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPLayer rasterLayerUpdated = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Position NOT decreased for \"" + rasterLayer.getName() + "\"",
                rasterLayerUpdated.getPosition(), rasterLayer.getPosition() + deltaValue);

        GPLayer vectorLayerUpdated = layerDAO.find(vectorLayer.getId());
        Assert.assertEquals("Position NOT decreased for \"" + vectorLayer.getName() + "\"",
                vectorLayerUpdated.getPosition(), vectorLayer.getPosition() + deltaValue);
    }

    /**
     * Test of updatePositionsLowerBound method for Foders
     */
    @Test
    public void testShiftPositionsFolders() {
        logger.trace("\n\t@@@ testShiftPositionsFolders @@@");
        int deltaValue = 33;
        boolean check = folderDAO.updatePositionsLowerBound(beginPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(userFolder.getId());
        Assert.assertEquals("Position NOT decreased for \"" + userFolder.getName() + "\"",
                userFolderUpdated.getPosition(), userFolder.getPosition() + deltaValue);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Position NOT decreased for \"" + folderA.getName() + "\"",
                folderAUpdated.getPosition(), folderA.getPosition() + deltaValue);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Position NOT decreased for \"" + folderB.getName() + "\"",
                folderBUpdated.getPosition(), folderB.getPosition() + deltaValue);
    }

    /**
     * Test of updatePositionsLowerBound method for Layers
     */
    @Test
    public void testShiftPositionsLayers() {
        logger.trace("\n\t@@@ testShiftPositionsLayers @@@");
        int deltaValue = 99;
        boolean check = layerDAO.updatePositionsLowerBound(beginPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPLayer rasterLayerUpdated = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Position NOT decreased for \"" + rasterLayer.getName() + "\"",
                rasterLayerUpdated.getPosition(), rasterLayer.getPosition() + deltaValue);

        GPLayer vectorLayerUpdated = layerDAO.find(vectorLayer.getId());
        Assert.assertEquals("Position NOT decreased for \"" + vectorLayer.getName() + "\"",
                vectorLayerUpdated.getPosition(), vectorLayer.getPosition() + deltaValue);
    }

    /**
     * Test of updateAncestorsDescendants method for Folders
     */
    @Test
    public void testUpdateAncestorsDescendants() {
        logger.trace("\n\t@@@ testUpdateAncestorsDescendants @@@");

        Map<Long, Integer> descendantsMap = new HashMap<Long, Integer>();
        descendantsMap.put(userFolder.getId(), 37);
        descendantsMap.put(folderB.getId(), 31);

        boolean check = folderDAO.updateAncestorsDescendants(descendantsMap);
        Assert.assertTrue("Update Ancestors Descendants NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(userFolder.getId());
        Assert.assertEquals("Ancestors Descendants NOT updated for \"" + userFolder.getName() + "\"",
                userFolderUpdated.getNumberOfDescendants(),
                Integer.parseInt(descendantsMap.get(userFolderUpdated.getId()).toString()));

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Ancestors Descendants NOT updated for \"" + folderB.getName() + "\"",
                folderBUpdated.getNumberOfDescendants(),
                Integer.parseInt(descendantsMap.get(folderBUpdated.getId()).toString()));

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Ancestors Descendants NOT updated for \"" + folderA.getName() + "\"",
                folderAUpdated.getNumberOfDescendants(), folderA.getNumberOfDescendants());
    }

    /**
     * Test of saveCheckStatusFolder method for Folders
     */
    @Test
    public void testSaveCheckStatusFolder() {
        logger.trace("\n\t@@@ testSaveCheckStatusFolder @@@");
        boolean beginIsChecked = folderA.isChecked(); // false

        // Not Swith: false to false
        boolean checkSave = folderDAO.saveCheckStatusFolder(folderA.getId(),
                beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Not Swith: false to false)", checkSave);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked NOT updated (Not Swith: false to false)",
                folderAUpdated.isChecked(), beginIsChecked);

        // Switch: false to true
        checkSave = folderDAO.saveCheckStatusFolder(folderA.getId(),
                !beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Switch: false to true)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked NOT updated for (Not Swith: false to true)",
                folderAUpdated.isChecked(), !beginIsChecked);

        // Not Swith: true to true
        checkSave = folderDAO.saveCheckStatusFolder(folderA.getId(),
                !beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Not Swith: true to true)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked NOT updated for (Not Swith: true to true)",
                folderAUpdated.isChecked(), !beginIsChecked);

        // Swith: true to false
        checkSave = folderDAO.saveCheckStatusFolder(folderA.getId(),
                beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Swith: true to false)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked NOT updated for (Swith: true to false)",
                folderAUpdated.isChecked(), beginIsChecked);

        // ID Folder NOT correct
        checkSave = folderDAO.saveCheckStatusFolder(1234567890, false);
        Assert.assertFalse("Save Check Status Folder NOT done (ID Folder NOT correct)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked NOT updated for (ID Folder NOT correct)",
                folderAUpdated.isChecked(), beginIsChecked);
    }
}
