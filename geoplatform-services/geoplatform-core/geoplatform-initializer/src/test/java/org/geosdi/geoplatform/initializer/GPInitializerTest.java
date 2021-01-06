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
package org.geosdi.geoplatform.initializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.acls.domain.BasePermission;

/**
 * This test is not intended to test the business logic but only the correctness
 * of the updates on DAOs.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPInitializerTest extends BaseInitializerTest {

    // User
    private String usernameUserPositionTest = "user_position_test";
    private GPUser userPositionTest;
    // Projects
    private GPProject userPositionTestProject;
    // Folders
    private GPFolder rootFolder;
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
        logger.trace("\n\t@@@ " + getClass().getSimpleName() + ".setUp @@@");
        organizationTest = new GPOrganization("geoSDI_dao_test");
        organizationDAO.persist(organizationTest);
        userPositionTest = super.insertUser(usernameUserPositionTest, organizationTest, Boolean.TRUE);

        endPosition = beginPosition + 930;

        userPositionTestProject = super.createProject("user_project_gp_test", false, 5, new Date(System.currentTimeMillis()));
        projectDAO.persist(userPositionTestProject);
        //
        super.insertBindingUserProject(userPositionTest, userPositionTestProject,
                BasePermission.ADMINISTRATION.getMask(), true);
        //
        rootFolder = super.createFolder("folder_of_" + usernameUserPositionTest, userPositionTestProject, null, beginPosition + 900); // 333930
        rootFolder.setNumberOfDescendants(13);
        rootFolder.setChecked(false);
        //
        folderDAO.persist(rootFolder);

        folderA = super.createFolder("folder_position_test_A", userPositionTestProject, rootFolder, beginPosition + 600); // 333630     
        folderB = super.createFolder("folder_position_test_B", userPositionTestProject, rootFolder, beginPosition + 300); // 333330               
        folderA.setNumberOfDescendants(3);
        folderB.setNumberOfDescendants(9);
        folderA.setChecked(false);
        folderB.setChecked(true);
        //
        folderDAO.persist(folderA, folderB);

        rasterLayer = super.createRasterLayer(folderB, userPositionTestProject, beginPosition + 30); // 333030
        vectorLayer = super.createVectorLayer(folderB, userPositionTestProject, beginPosition); // 333000
        rasterLayer.setTitle(rasterLayer.getTitle() + "_position_test");
        vectorLayer.setTitle(vectorLayer.getTitle() + "_position_test");
        rasterLayer.setChecked(false);
        vectorLayer.setChecked(true);
        //
        layerDAO.persist(rasterLayer, vectorLayer);
    }

    @After
    public void tearDown() {
        logger.trace("\n\t@@@ " + getClass().getSimpleName() + ".tearDown @@@");
        // Remove user
        accountDAO.remove(userPositionTest);
        // Remove project and his folders and layers
        projectDAO.remove(userPositionTestProject);
        // Remove organization
        organizationDAO.remove(organizationTest);
    }

    @Test
    public void testCheckDAOs() {
        Assert.assertNotNull("accountDAO is NULL", super.accountDAO);
        Assert.assertNotNull("projectDAO is NULL", super.projectDAO);
        Assert.assertNotNull("accountProjectsDAO is NULL", super.accountProjectDAO);
        Assert.assertNotNull("folderDAO is NULL", super.folderDAO);
        Assert.assertNotNull("layerDAO is NULL", super.layerDAO);
//        Assert.assertNotNull("styleDAO is NULL", super.styleDAO);
    }

    @Test
    public void testTransactionsOnLayers() {
        List<String> layerInfoKeywords = new ArrayList<String>();
        layerInfoKeywords.add("keyword_test");

        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setKeywords(layerInfoKeywords);
        layerInfo.setQueryable(false);

        String titleRasterLayer3 = "Raster Layer 3";
        String titleVectorLayer3 = "Vector Layer 3";

        try {
            // "folder_position_test_A" ---> "rasterLayer3"
            GPRasterLayer rasterLayer3 = super.createRasterLayer(folderA, userPositionTestProject, beginPosition);
            rasterLayer3.setTitle(titleRasterLayer3);

            // "folder_position_test_A" ---> "vectorLayer3"
            GPVectorLayer vectorLayer3 = super.createVectorLayer(folderA, userPositionTestProject, beginPosition);
            vectorLayer3.setTitle(titleVectorLayer3);

            // "folder_position_test_A" ---> "rasterLayer4"
            GPRasterLayer rasterLayer4 = super.createRasterLayer(folderA, userPositionTestProject, beginPosition);
            rasterLayer4.setTitle(null);

            // "folder_position_test_A" ---> "vectorLayer4"
            GPVectorLayer vectorLayer4 = super.createVectorLayer(folderA, userPositionTestProject, beginPosition);
            vectorLayer4.setTitle(null);
            //
            ArrayList<GPLayer> layersList = new ArrayList<GPLayer>();
            layersList.add(rasterLayer3);
            layersList.add(vectorLayer3);
            layersList.add(rasterLayer4);
            layersList.add(vectorLayer4);

            GPLayer[] layersArray = layersList.toArray(new GPLayer[layersList.size()]);
            layerDAO.persist(layersArray);
            Assert.fail("saveAddedLayersAndTreeModifications must throws an exception");
        } catch (Exception ex) {
            logger.trace("Exception from layerDAO because the layer's title must be NONT NULL: {}",
                    ex.getMessage());
        }

        GPLayer newRasterLayer3 = layerDAO.findByLayerName(titleRasterLayer3);
        Assert.assertNull("rasterLayer3 must be null", newRasterLayer3);

        GPLayer newVectorLayer3 = layerDAO.findByLayerName(titleVectorLayer3);
        Assert.assertNull("rectorLayer3 must be null", newVectorLayer3);
    }

    //<editor-fold defaultstate="collapsed" desc="Test of updatePositionsRange">

    /**
     * Test of updatePositionsRange method for Folders
     */
    @Test
    public void testIncreasePositionsFolders() {
        logger.trace("\n\t@@@ testIncreasePositionsFolders @@@");
        int deltaValue = 1;
        // Increase
        boolean check = folderDAO.updatePositionsRange(userPositionTestProject.getId(),
                beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Increase Position Folders NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(rootFolder.getId());
        Assert.assertEquals("Position NOT increased for \"" + rootFolder.getName() + "\"",
                userFolderUpdated.getPosition(), rootFolder.getPosition() + deltaValue);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Position NOT increased for \"" + folderA.getName() + "\"",
                folderAUpdated.getPosition(), folderA.getPosition() + deltaValue);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Position NOT increased for \"" + folderB.getName() + "\"",
                folderBUpdated.getPosition(), folderB.getPosition() + deltaValue);

        // No Increase
//        check = folderDAO.updatePositionsRange(Integer.MAX_VALUE - 1, Integer.MAX_VALUE, deltaValue);
//        Assert.assertFalse("Increase Position Folders should NOT be done", check);
    }

    @Test
    public void testDecreasePositionsFolders() {
        logger.trace("\n\t@@@ testDecreasePositionsFolders @@@");
        int deltaValue = -1;
        // Decrease
        boolean check = folderDAO.updatePositionsRange(userPositionTestProject.getId(),
                beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Decrease Position Folders NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(rootFolder.getId());
        Assert.assertEquals("Position NOT decreased for \"" + rootFolder.getName() + "\"",
                userFolderUpdated.getPosition(), rootFolder.getPosition() + deltaValue);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Position NOT decreased for \"" + folderA.getName() + "\"",
                folderAUpdated.getPosition(), folderA.getPosition() + deltaValue);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Position NOT decreased for \"" + folderB.getName() + "\"",
                folderBUpdated.getPosition(), folderB.getPosition() + deltaValue);

        // No Decrease
//        check = folderDAO.updatePositionsRange(Integer.MAX_VALUE - 1, Integer.MAX_VALUE, deltaValue);
//        Assert.assertFalse("Decrease Position Folders should NOT be done", check);
    }

    /**
     * Test of updatePositionsRange method for Layers
     */
    @Test
    public void testIncreasePositionsLayers() {
        logger.trace("\n\t@@@ testIncreasePositionsLayers @@@");
        int deltaValue = 1;
        // Increase
        boolean check = layerDAO.updatePositionsRange(userPositionTestProject.getId(),
                beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Increase Position Layers NOT done", check);

        GPLayer rasterLayerUpdated = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Position NOT increased for \"" + rasterLayer.getName() + "\"",
                rasterLayerUpdated.getPosition(), rasterLayer.getPosition() + deltaValue);

        GPLayer vectorLayerUpdated = layerDAO.find(vectorLayer.getId());
        Assert.assertEquals("Position NOT increased for \"" + vectorLayer.getName() + "\"",
                vectorLayerUpdated.getPosition(), vectorLayer.getPosition() + deltaValue);

        // No Increase
//        check = layerDAO.updatePositionsRange(Integer.MAX_VALUE - 1, Integer.MAX_VALUE, deltaValue);
//        Assert.assertFalse("Increase Position Layers should NOT be done", check);
    }

    @Test
    public void testDecreasePositionsLayers() {
        logger.trace("\n\t@@@ testDecreasePositionsLayers @@@");
        int deltaValue = -1;
        // Decrease
        boolean check = layerDAO.updatePositionsRange(userPositionTestProject.getId(),
                beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Decrease Position Layers NOT done", check);

        GPLayer rasterLayerUpdated = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Position NOT decreased for \"" + rasterLayer.getName() + "\"",
                rasterLayerUpdated.getPosition(), rasterLayer.getPosition() + deltaValue);

        GPLayer vectorLayerUpdated = layerDAO.find(vectorLayer.getId());
        Assert.assertEquals("Position NOT decreased for \"" + vectorLayer.getName() + "\"",
                vectorLayerUpdated.getPosition(), vectorLayer.getPosition() + deltaValue);

        // No Decrease
//        check = layerDAO.updatePositionsRange(Integer.MAX_VALUE - 1, Integer.MAX_VALUE, deltaValue);
//        Assert.assertFalse("Decrease Position Layers should NOT be done", check);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of updatePositionsLowerBound">

    /**
     * Test of updatePositionsLowerBound method for Foders
     */
    @Test
    public void testShiftPositionsFolders() {
        logger.trace("\n\t@@@ testShiftPositionsFolders @@@");
        int deltaValue = 33;
        // Shift
        boolean check = folderDAO.updatePositionsLowerBound(userPositionTestProject.getId(),
                beginPosition, deltaValue);
        Assert.assertTrue("Shift Position Folders NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(rootFolder.getId());
        Assert.assertEquals("Shift Position NOT done for \"" + rootFolder.getName() + "\"",
                userFolderUpdated.getPosition(), rootFolder.getPosition() + deltaValue);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Shift Position NOT done for \"" + folderA.getName() + "\"",
                folderAUpdated.getPosition(), folderA.getPosition() + deltaValue);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Shift Position NOT done for \"" + folderB.getName() + "\"",
                folderBUpdated.getPosition(), folderB.getPosition() + deltaValue);

        // No Shift
//        check = folderDAO.updatePositionsLowerBound(Integer.MAX_VALUE, deltaValue);
//        Assert.assertFalse("Shift Position Folders should NOT be done", check);
    }

    /**
     * Test of updatePositionsLowerBound method for Layers
     */
    @Test
    public void testShiftPositionsLayers() {
        logger.trace("\n\t@@@ testShiftPositionsLayers @@@");
        int deltaValue = 99;
        // Shift
        boolean check = layerDAO.updatePositionsLowerBound(userPositionTestProject.getId(),
                beginPosition, deltaValue);
        Assert.assertTrue("Shift Position Layers NOT done", check);

        GPLayer rasterLayerUpdated = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Shift Position NOT done for \"" + rasterLayer.getName() + "\"",
                rasterLayerUpdated.getPosition(), rasterLayer.getPosition() + deltaValue);

        GPLayer vectorLayerUpdated = layerDAO.find(vectorLayer.getId());
        Assert.assertEquals("Shift Position NOT done for \"" + vectorLayer.getName() + "\"",
                vectorLayerUpdated.getPosition(), vectorLayer.getPosition() + deltaValue);

        // No Shift
//        check = layerDAO.updatePositionsLowerBound(Integer.MAX_VALUE, deltaValue);
//        Assert.assertFalse("Shift Position Layers should NOT be done", check);
    }
    //</editor-fold>

    /**
     * Test of updateAncestorsDescendants method for Folders
     */
    @Test
    public void testUpdateAncestorsDescendants() {
        logger.trace("\n\t@@@ testUpdateAncestorsDescendants @@@");

        Map<Long, Integer> descendantsMap = new HashMap<Long, Integer>();
        descendantsMap.put(rootFolder.getId(), 37);
        descendantsMap.put(folderB.getId(), 31);

        // Update
        boolean check = folderDAO.updateAncestorsDescendants(descendantsMap);
        Assert.assertTrue("Update Ancestors Descendants NOT done", check);

        GPFolder userFolderUpdated = folderDAO.find(rootFolder.getId());
        Assert.assertEquals("Ancestors Descendants NOT updated for \"" + rootFolder.getName() + "\"",
                userFolderUpdated.getNumberOfDescendants(),
                Integer.parseInt(descendantsMap.get(userFolderUpdated.getId()).toString()));

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("Ancestors Descendants NOT updated for \"" + folderB.getName() + "\"",
                folderBUpdated.getNumberOfDescendants(),
                Integer.parseInt(descendantsMap.get(folderBUpdated.getId()).toString()));

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Ancestors Descendants NOT updated for \"" + folderA.getName() + "\"",
                folderAUpdated.getNumberOfDescendants(), folderA.getNumberOfDescendants());

        // No Update
//        descendantsMap.clear();
//        check = folderDAO.updateAncestorsDescendants(descendantsMap);
//        Assert.assertFalse("Update Ancestors Descendants should NOT be done (empty map)", check);
//
//        descendantsMap.put(Long.MAX_VALUE, 3);
//        check = folderDAO.updateAncestorsDescendants(descendantsMap);
//        Assert.assertFalse("Update Ancestors Descendants should NOT be done (nothing to update)", check);
    }

    //<editor-fold defaultstate="collapsed" desc="Test of persistCheckStatus">

    /**
     * Test of persistCheckStatusFolder method for Folders
     */
    @Test
    public void testPersistCheckStatusFolder() {
        logger.trace("\n\t@@@ testPersistCheckStatusFolder @@@");
        boolean beginIsChecked = folderA.isChecked(); // false

        // No Swith: false to false
        boolean checkSave = folderDAO.persistCheckStatusFolder(folderA.getId(),
                beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Not Swith: false to false)", checkSave);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked Folder NOT updated (Not Swith: false to false)",
                folderAUpdated.isChecked(), beginIsChecked);

        // Switch: false to true
        checkSave = folderDAO.persistCheckStatusFolder(folderA.getId(),
                !beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Switch: false to true)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked Folder NOT updated for (Not Swith: false to true)",
                folderAUpdated.isChecked(), !beginIsChecked);

        // No Swith: true to true
        checkSave = folderDAO.persistCheckStatusFolder(folderA.getId(),
                !beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Not Swith: true to true)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked Folder NOT updated for (Not Swith: true to true)",
                folderAUpdated.isChecked(), !beginIsChecked);

        // Swith: true to false
        checkSave = folderDAO.persistCheckStatusFolder(folderA.getId(),
                beginIsChecked);
        Assert.assertTrue("Save Check Status Folder NOT done (Swith: true to false)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked Folder NOT updated for (Swith: true to false)",
                folderAUpdated.isChecked(), beginIsChecked);

        // ID Folder NOT correct
        checkSave = folderDAO.persistCheckStatusFolder(Long.MAX_VALUE, false);
        Assert.assertFalse("Save Check Status Folder NOT done (ID Folder NOT correct)", checkSave);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("Checked Folder NOT updated for (ID Folder NOT correct)",
                folderAUpdated.isChecked(), beginIsChecked);
    }

    /**
     * Test of persistCheckStatusFolderS method for Folders
     */
    @Test
    public void testPersistCheckStatusFolderS() {
        logger.trace("\n\t@@@ testPersistCheckStatusFolderS @@@");

        Long[] ids = new Long[]{rootFolder.getId(), folderA.getId(), folderB.getId()};

        // Set all folders checked
        boolean checkSave = folderDAO.persistCheckStatusFolders(true, ids);
        Assert.assertTrue("Save Check Status Folder to true NOT done", checkSave);

        GPFolder userFolderUpdated = folderDAO.find(rootFolder.getId());
        Assert.assertEquals("NOT checked Folder \"" + rootFolder.getName() + "\"",
                userFolderUpdated.isChecked(), true);

        GPFolder folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("NOT checked Folder \"" + folderA.getName() + "\"",
                folderAUpdated.isChecked(), true);

        GPFolder folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("NOT checked Folder \"" + folderB.getName() + "\"",
                folderBUpdated.isChecked(), true);

        // Set all folders unchecked
        checkSave = folderDAO.persistCheckStatusFolders(false, ids);
        Assert.assertTrue("Save Check Status Folder to false NOT done", checkSave);

        userFolderUpdated = folderDAO.find(rootFolder.getId());
        Assert.assertEquals("NOT unchecked Folder \"" + rootFolder.getName() + "\"",
                userFolderUpdated.isChecked(), false);

        folderAUpdated = folderDAO.find(folderA.getId());
        Assert.assertEquals("NOT unchecked Folder \"" + folderA.getName() + "\"",
                folderAUpdated.isChecked(), false);

        folderBUpdated = folderDAO.find(folderB.getId());
        Assert.assertEquals("NOT unchecked Folder \"" + folderB.getName() + "\"",
                folderBUpdated.isChecked(), false);
    }

    /**
     * Test of persistCheckStatusLayer method for Layers
     */
    @Test
    public void testPersistCheckStatusLayer() {
        logger.trace("\n\t@@@ testPersistCheckStatusLayer @@@");
        boolean beginIsChecked = rasterLayer.isChecked(); // false

        // No Swith: false to false
        boolean checkSave = layerDAO.persistCheckStatusLayer(rasterLayer.getId(),
                beginIsChecked);
        Assert.assertTrue("Save Check Status Layer NOT done (Not Swith: false to false)", checkSave);

        GPLayer raster = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Checked Layer NOT updated (Not Swith: false to false)",
                raster.isChecked(), beginIsChecked);

        // Switch: false to true
        checkSave = layerDAO.persistCheckStatusLayer(rasterLayer.getId(),
                !beginIsChecked);
        Assert.assertTrue("Save Check Status Layer NOT done (Switch: false to true)", checkSave);

        raster = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Checked Layer NOT updated for (Not Swith: false to true)",
                raster.isChecked(), !beginIsChecked);

        // No Swith: true to true
        checkSave = layerDAO.persistCheckStatusLayer(rasterLayer.getId(),
                !beginIsChecked);
        Assert.assertTrue("Save Check Status Layer NOT done (Not Swith: true to true)", checkSave);

        raster = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Checked Layer NOT updated for (Not Swith: true to true)",
                raster.isChecked(), !beginIsChecked);

        // Swith: true to false
        checkSave = layerDAO.persistCheckStatusLayer(rasterLayer.getId(),
                beginIsChecked);
        Assert.assertTrue("Save Check Status Layer NOT done (Swith: true to false)", checkSave);

        raster = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Checked Layer NOT updated for (Swith: true to false)",
                raster.isChecked(), beginIsChecked);

        // ID Folder NOT correct
        checkSave = layerDAO.persistCheckStatusLayer(Long.MAX_VALUE, false);
        Assert.assertFalse("Save Check Status Layer NOT done (ID Folder NOT correct)", checkSave);

        raster = layerDAO.find(rasterLayer.getId());
        Assert.assertEquals("Checked Layer NOT updated for (ID Folder NOT correct)",
                raster.isChecked(), beginIsChecked);
    }
    //</editor-fold>
}
