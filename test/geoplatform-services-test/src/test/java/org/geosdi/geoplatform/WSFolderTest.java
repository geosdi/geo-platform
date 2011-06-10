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
import java.util.Iterator;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
// TODO TEST DD on same position
// TODO TEST DD with many subelements
public class WSFolderTest extends ServiceTest {

    private final String nameFolder1 = "folder1";
    private final String nameFolder2 = "folder2";
    private final String nameFolder3 = "folder3";
    private final String nameFolder4 = "folder4";
    private final String nameFolder5 = "folder5";
    private GPFolder folder1 = null;
    private GPFolder folder2 = null;
    private GPFolder folder3 = null;
    private GPFolder folder4 = null;
    private GPFolder folder5 = null;
    private long idFolder1 = -1;
    private long idFolder2 = -1;
    private long idFolder3 = -1;
    private long idFolder4 = -1;
    private long idFolder5 = -1;

    @Before
    // "position" will be set without application logic, but only to have different values
    public void setUp() throws Exception {
        super.setUp();

        // "rootFolderA" ---> "folder1"
        idFolder1 = createAndInsertFolderWithParent(nameFolder1, rootFolderA, 6, false);
        folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
        // "rootFolderA" ---> "folder2"
        idFolder2 = createAndInsertFolderWithParent(nameFolder2, rootFolderA, 5, false);
        folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
        //
        rootFolderA.setPosition(7);
        rootFolderA.setNumberOfDescendants(2);
        geoPlatformService.updateFolder(rootFolderA);

        // "rootFolderB" ---> "folder3"
        idFolder3 = createAndInsertFolderWithParent(nameFolder3, rootFolderB, 3, false);
        folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
        // "rootFolderB" ---> "folder4"
        idFolder4 = createAndInsertFolderWithParent(nameFolder4, rootFolderB, 2, false);
        folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
        // "rootFolderB" ---> "folder5"
        idFolder5 = createAndInsertFolderWithParent(nameFolder5, rootFolderB, 1, false);
        folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
        //
        rootFolderB.setPosition(4);
        rootFolderB.setNumberOfDescendants(3);
        geoPlatformService.updateFolder(rootFolderB);
    }

    @Test
    public void testGetShortFolder() {
        try {
            FolderDTO folderA = geoPlatformService.getShortFolder(new RequestById(idRootFolderA));
            Assert.assertNotNull("assertNotNull folderA", folderA);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Unable to find folder with id \"" + rnnf.getId());
        }

        try {
            FolderDTO folderB = geoPlatformService.getShortFolder(new RequestById(idRootFolderB));
            Assert.assertNotNull("assertNotNull folderB", folderB);
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Unable to find folder with id \"" + rnnf.getId());
        }

        Assert.assertNotNull(folder1);
        Assert.assertEquals(folder1.getId(), idFolder1);
        Assert.assertEquals(folder1.getName(), nameFolder1);

        Assert.assertNotNull(folder2);
        Assert.assertEquals(folder2.getId(), idFolder2);
        Assert.assertEquals(folder2.getName(), nameFolder2);

        Assert.assertNotNull(folder3);
        Assert.assertEquals(folder3.getId(), idFolder3);
        Assert.assertEquals(folder3.getName(), nameFolder3);

        Assert.assertNotNull(folder4);
        Assert.assertEquals(folder4.getId(), idFolder4);
        Assert.assertEquals(folder4.getName(), nameFolder4);

        Assert.assertNotNull(folder5);
        Assert.assertEquals(folder5.getId(), idFolder5);
        Assert.assertEquals(folder5.getName(), nameFolder5);
    }

    @Test
    public void testUpdateFolder() {
        final String nameFolderUpdated = "folderUpdated";
        try {
            folder5.setParent(rootFolderA);
            folder5.setName(nameFolderUpdated);

            geoPlatformService.updateFolder(folder5);
            GPFolder folderUpdated = geoPlatformService.getFolderDetail(new RequestById(idFolder5));

            Assert.assertNotNull(folderUpdated);
            Assert.assertEquals(folderUpdated.getName(), nameFolderUpdated);
            Assert.assertEquals(folderUpdated.getParent().getId(), idRootFolderA);
        } catch (IllegalParameterFault ex) {
            Assert.fail("Folder has an Illegal Parameter");
        } catch (ResourceNotFoundFault rnnf) {
            Assert.fail("Folder with id \"" + rnnf.getId() + "\" not found");
        }
    }

    @Test
    public void testDeleteFolder() {
        FolderDTO folderToCheck = null;
        try {
            // Assert total number of folders stored into DB before delete
            FolderList allFoldersBeforeDelete = geoPlatformService.getFolders();
            int totalFolders = allFoldersBeforeDelete.getList().size();
            Assert.assertTrue("Assert number of folders stored into DB before delete",
                    totalFolders >= 7); // SetUp() added 2+5 folders

            // Delete "rootFolderB" and in cascade "folder3" & "folder4" & "folder5"
            geoPlatformService.deleteFolder(new RequestById(idRootFolderB));

            // "rootFolderA" ---> "folder1" & "folder2"
            FolderList folderList = geoPlatformService.getUserFoldersByUserId(idUserTest);

            // Assert on the structure of user's folders
            Assert.assertEquals(folderList.getList().size(), 1);

            // Assert on "rootFolderA"
            folderToCheck = folderList.getList().iterator().next();
            logger.debug("\n*** folderToCheck:\n{}\n***", folderToCheck);
            Assert.assertEquals("Check id on rootFolderA", folderToCheck.getId(), idRootFolderA);
            Assert.assertEquals("Check name on rootFolderA", folderToCheck.getName(), nameRootFolderA);
            // Assert on the structure of the subfolders of "rootFolderA"
            TreeFolderElements childrenRootFolderA = geoPlatformService.getChildrenElements(idRootFolderA);
            logger.debug("\n*** childrenRootFolderA:\n{}\n***", childrenRootFolderA);
            Assert.assertNotNull("Check childrenRootFolderA not null", childrenRootFolderA);
            Assert.assertEquals("Check size of childrenRootFolderA", childrenRootFolderA.size(), 2);
            // Iterator for scan folder in descending order
            Iterator iterator = childrenRootFolderA.iterator();
            // Assert on "folder1"
            FolderDTO folderDTOToCheck = (FolderDTO) iterator.next();
            logger.debug("\n*** folderDTOToCheck:\n{}\n***", folderDTOToCheck);
            Assert.assertEquals("Check id of folder 1", folderDTOToCheck.getId(), idFolder1);
            Assert.assertEquals("Check name of folder 1", folderDTOToCheck.getName(), nameFolder1);
            // Assert on "folder2"
            folderDTOToCheck = (FolderDTO) iterator.next();
            logger.debug("\n*** folderDTOToCheck:\n{}\n***", folderDTOToCheck);
            Assert.assertEquals("Check id of folder 2", folderDTOToCheck.getId(), idFolder2);
            Assert.assertEquals("Check name of folder 2", folderDTOToCheck.getName(), nameFolder2);

            // Assert on "rootFolderB" (deleted)
            TreeFolderElements childrenRootFolderB = geoPlatformService.getChildrenElements(idRootFolderB);
            Assert.assertNull("Check childrenRootFolderB null", childrenRootFolderB);

            // Assert total number of folders stored into DB after delete
            FolderList allFoldersAfterDelete = geoPlatformService.getFolders();
            Assert.assertTrue("Assert number of folders stored into DB after delete",
                    allFoldersAfterDelete.getList().size() == totalFolders - 4); // Has been deleted 4 folders
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Folder has an illegal parameter");
        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" not found");
        } catch (Exception e) {
            Assert.fail("Exception: " + e.getClass());
        }

        // Check ON DELETE CASCADE of the subforders of "rootFolderB"
        checkFolderDeleted(idFolder3);
        checkFolderDeleted(idFolder4);
        checkFolderDeleted(idFolder5);
    }

    @Test
    public void testSaveAndDeleteFolderAndTreeModifications() {
        GPFolder folderToTest = null;
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map); // Set an empty map
        try {
            FolderList childrenFolders = geoPlatformService.getChildrenFoldersByFolderId(idRootFolderB);
            Assert.assertEquals("Before adding new folder - Number of subfolders of root folder B ", 3, childrenFolders.getList().size());

            String nameFolderToTest = "folderToTest";
            folderToTest = super.createFolder(nameFolderToTest, 1, false);
            folderToTest.setOwner(userTest);

            // Adding new folder to user's root            
            long idFolderToTest = geoPlatformService.saveAddedFolderAndTreeModifications(folderToTest, descendantsMapData);
            folderToTest.setId(idFolderToTest);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A before removing", 8, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 before removing", 7, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 before removing", 6, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B before removing", 5, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B before removing", 3, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 before removing", 4, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 before removing", 3, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 before removing", 2, folder5.getPosition());

            // Removing folder from user's root
            boolean checkDelete = geoPlatformService.saveDeletedFolderAndTreeModifications(folderToTest.getId(), descendantsMapData);
            Assert.assertTrue("Delete NOT done for \"" + folderToTest.getName() + "\"", checkDelete);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after removing", 7, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after removing", 6, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after removing", 5, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after removing", 4, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B before removing", 3, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after removing", 3, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after removing", 2, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after removing", 1, folder5.getPosition());
        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        } catch (IllegalParameterFault ex) {
            Assert.fail("Folder with id \"" + folderToTest.getId() + "\" was not found");
        }
    }

    @Test
    public void testSaveAndDeleteSubfolderAndTreeModifications() {
        GPFolder folderToTest = null;
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            FolderList childrenFolders = geoPlatformService.getChildrenFoldersByFolderId(idRootFolderB);
            Assert.assertEquals("Before adding new folder - Number of subfolders of root folder B ", 3, childrenFolders.getList().size());

            String nameFolderToTest = "folderToTest";
            folderToTest = super.createFolder(nameFolderToTest, 3, false);
            folderToTest.setParent(rootFolderB);

            // Adding new folder to user's root folder B
            map.put(idRootFolderB, 4);
            long idFolderToTest = geoPlatformService.saveAddedFolderAndTreeModifications(folderToTest, descendantsMapData);
            folderToTest.setId(idFolderToTest);

            childrenFolders = geoPlatformService.getChildrenFoldersByFolderId(idRootFolderB);
            Assert.assertEquals("After adding new folder - Number of subfolders of root folder B ", 4, childrenFolders.getList().size());

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A before removing", 8, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 before removing", 7, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 before removing", 6, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B before removing", 5, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B before removing", 4, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 before removing", 4, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 before removing", 2, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 before removing", 1, folder5.getPosition());

            // Removing folder from user's root folder B
            map.clear();
            map.put(idRootFolderB, 3);
            boolean checkDelete = geoPlatformService.saveDeletedFolderAndTreeModifications(folderToTest.getId(), descendantsMapData);
            Assert.assertTrue("Delete NOT done for \"" + folderToTest.getName() + "\"", checkDelete);

            childrenFolders = geoPlatformService.getChildrenFoldersByFolderId(idRootFolderB);
            Assert.assertEquals("After removing new folder - Number of subfolders of root folder B ", 3, childrenFolders.getList().size());

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after removing", 7, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A before removing", 2, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after removing", 6, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after removing", 5, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after removing", 4, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B before removing", 3, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after removing", 3, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after removing", 2, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after removing", 1, folder5.getPosition());
        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        } catch (IllegalParameterFault ex) {
            Assert.fail("Folder with id \"" + folderToTest.getId() + "\" was not found");
        }
    }

    @Test
    public void testDragAndDropOnSameParent() {
        logger.trace("\n\t@@@ testDragAndDropOnSameParent @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            // Move folder 5 between folder 3 and folder 4 (oldPosition < new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    idFolder5, super.idRootFolderB, super.userTest, 2, descendantsMapData);
            Assert.assertTrue("Folder 5 doesn't moved to position 2", checkDD);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after DD I", 7, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after DD I", 2, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after DD I", 6, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after DD I", 5, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after DD I", 4, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after DD I", 3, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after DD I", 3, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after DD I", 1, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after DD I", 2, folder5.getPosition());

            // Move folder 5 after folder 4, in initial position (oldPosition > new Position)
            checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    idFolder5, super.idRootFolderB, super.userTest, 1, descendantsMapData);
            Assert.assertTrue("Folder 5 doesn't moved to position 1", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        }
    }

    @Test
    public void testDragAndDropOnDifferentParent() {
        logger.trace("\n\t@@@ testDragAndDropOnDifferentParent @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            map.put(super.idRootFolderA, 3);
            map.put(super.idRootFolderB, 2);
            // Move folder 4 between folder 1 and folder 2 (oldPosition < new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    idFolder4, super.idRootFolderA, super.userTest, 5, descendantsMapData);
            Assert.assertTrue("Folder 4 doesn't moved to position 5", checkDD);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after DD I", 7, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after DD I", 3, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after DD I", 6, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after DD I", 4, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after DD I", 3, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after DD I", 2, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after DD I", 2, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after DD I", 5, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after DD I", 1, folder5.getPosition());

            // Move folder 4 after folder 3, in initial position (oldPosition > new Position)
            map.clear();
            map.put(super.idRootFolderA, 2);
            map.put(super.idRootFolderB, 3);
            checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    idFolder4, super.idRootFolderB, super.userTest, 2, descendantsMapData);
            Assert.assertTrue("Folder 4 doesn't moved to position 2", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        }
    }

    @Test
    public void testDragAndDropOnRootParent() {
        logger.trace("\n\t@@@ testDragAndDropOnRootParent @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            // Move folder B before folder A (oldPosition < new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    super.idRootFolderB, 0, super.userTest, 7, descendantsMapData);
            Assert.assertTrue("Folder B doesn't moved to position 7", checkDD);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after DD I", 3, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after DD I", 2, rootFolderA.getNumberOfDescendants());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after DD I", 2, folder1.getPosition());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after DD I", 1, folder2.getPosition());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B  after DD I", 7, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after DD I", 3, rootFolderB.getNumberOfDescendants());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after DD I", 6, folder3.getPosition());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after DD I", 5, folder4.getPosition());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after DD I", 4, folder5.getPosition());

            // Move folder B after folder A, in initial position (oldPosition > new Position)
            checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    super.idRootFolderB, 0, super.userTest, 1, descendantsMapData);
            Assert.assertTrue("Folder 4 doesn't moved to position 1", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        }
    }

    @Test
    public void testDragAndDropFromRootToTop() {
        logger.trace("\n\t@@@ testDragAndDropFromRootToTop @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            map.put(idRootFolderA, 6);
            // Move Folder B after Folder 1 (oldPosition < new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    super.idRootFolderB, super.idRootFolderA, null, 6, descendantsMapData);
            Assert.assertTrue("Drag and Drop successful", checkDD);

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after drag and drop operation", 7, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after drag and drop operation", 6, rootFolderA.getNumberOfDescendants());
            Assert.assertEquals("Owner of root folder A after drag and drop operation", userTest, rootFolderA.getOwner());
            Assert.assertNull("Parent of root folder A after drag and drop operation", rootFolderA.getParent());

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after drag and drop operation", 6, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after drag and drop operation", 3, rootFolderB.getNumberOfDescendants());
            Assert.assertEquals("Parent of root folder B after drag and drop operation", idRootFolderA, rootFolderB.getParent().getId());
            Assert.assertNull("Owner of root folder B after drag and drop operation", rootFolderB.getOwner());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after drag and drop operation", 5, folder3.getPosition());
            Assert.assertEquals("Parent of folder 3 after drag and drop operation", idRootFolderB, folder3.getParent().getId());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after drag and drop operation", 4, folder4.getPosition());
            Assert.assertEquals("Parent of folder 4 after drag and drop operation", idRootFolderB, folder4.getParent().getId());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after drag and drop operation", 3, folder5.getPosition());
            Assert.assertEquals("Parent of folder 5 after drag and drop operation", idRootFolderB, folder5.getParent().getId());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after drag and drop operation", 2, folder1.getPosition());
            Assert.assertEquals("Parent of folder 1 after drag and drop operation", idRootFolderA, folder1.getParent().getId());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after drag and drop operation", 1, folder2.getPosition());
            Assert.assertEquals("Parent of folder 2 after drag and drop operation", idRootFolderA, folder2.getParent().getId());

            map.clear();
            map.put(idRootFolderA, 2);
            // Move folder B in initial position (oldPosition > new Position)
            checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    super.idRootFolderB, 0, super.userTest, 1, descendantsMapData);
            Assert.assertTrue("Folder B doesn't moved to position 1", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        }
    }

    @Test
    public void testDragAndDropFromRootToBottom() {
        logger.trace("\n\t@@@ testDragAndDropFromRootToBottom @@@");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);
        try {
            map.put(idRootFolderB, 6);
            // Move Folder A after Folder 3 (oldPosition > new Position)
            boolean checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    super.idRootFolderA, super.idRootFolderB, null, 4, descendantsMapData);
            Assert.assertTrue("Drag and Drop successful", checkDD);

            rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
            Assert.assertEquals("Position of root folder B after drag and drop operation", 7, rootFolderB.getPosition());
            Assert.assertEquals("Number of descendant of root folder B after drag and drop operation", 6, rootFolderB.getNumberOfDescendants());
            Assert.assertEquals("Owner of root folder B after drag and drop operation", userTest, rootFolderB.getOwner());
            Assert.assertNull("Parent of root folder B after drag and drop operation", rootFolderB.getParent());

            rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
            Assert.assertEquals("Position of root folder A after drag and drop operation", 6, rootFolderA.getPosition());
            Assert.assertEquals("Number of descendant of root folder A after drag and drop operation", 2, rootFolderA.getNumberOfDescendants());
            Assert.assertEquals("Parent of root folder A after drag and drop operation", idRootFolderB, rootFolderA.getParent().getId());
            Assert.assertNull("Owner of root folder A after drag and drop operation", rootFolderA.getOwner());

            folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
            Assert.assertEquals("Position of folder 1 after drag and drop operation", 5, folder1.getPosition());
            Assert.assertEquals("Parent of folder 1 after drag and drop operation", idRootFolderA, folder1.getParent().getId());

            folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
            Assert.assertEquals("Position of folder 2 after drag and drop operation", 4, folder2.getPosition());
            Assert.assertEquals("Parent of folder 2 after drag and drop operation", idRootFolderA, folder2.getParent().getId());

            folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
            Assert.assertEquals("Position of folder 3 after drag and drop operation", 3, folder3.getPosition());
            Assert.assertEquals("Parent of folder 3 after drag and drop operation", idRootFolderB, folder3.getParent().getId());

            folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
            Assert.assertEquals("Position of folder 4 after drag and drop operation", 2, folder4.getPosition());
            Assert.assertEquals("Parent of folder 4 after drag and drop operation", idRootFolderB, folder4.getParent().getId());

            folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
            Assert.assertEquals("Position of folder 5 after drag and drop operation", 1, folder5.getPosition());
            Assert.assertEquals("Parent of folder 5 after drag and drop operation", idRootFolderB, folder5.getParent().getId());

            map.clear();
            map.put(idRootFolderB, 3);
            // Move folder A in initial position (oldPosition < new Position)
            checkDD = geoPlatformService.saveDragAndDropFolderAndTreeModifications(
                    super.idRootFolderA, 0, super.userTest, 7, descendantsMapData);
            Assert.assertTrue("Folder B doesn't moved to position 7", checkDD);

            this.checkInitialState();

        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + rnff.getId() + "\" was not found");
        }
    }

    private void checkInitialState()
            throws ResourceNotFoundFault {

        rootFolderA = geoPlatformService.getFolderDetail(new RequestById(idRootFolderA));
        Assert.assertEquals("Position of root folder A after DD II", 7, rootFolderA.getPosition());
        Assert.assertEquals("Number of descendant of root folder A after DD II", 2, rootFolderA.getNumberOfDescendants());

        folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
        Assert.assertEquals("Position of folder 1 after DD II", 6, folder1.getPosition());

        folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
        Assert.assertEquals("Position of folder 2 after DD II", 5, folder2.getPosition());

        rootFolderB = geoPlatformService.getFolderDetail(new RequestById(idRootFolderB));
        Assert.assertEquals("Position of root folder B after DD II", 4, rootFolderB.getPosition());
        Assert.assertEquals("Number of descendant of root folder B after DD II", 3, rootFolderB.getNumberOfDescendants());

        folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
        Assert.assertEquals("Position of folder 3 after DD II", 3, folder3.getPosition());

        folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
        Assert.assertEquals("Position of folder 4 after DD II", 2, folder4.getPosition());

        folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
        Assert.assertEquals("Position of folder 5 after DD II", 1, folder5.getPosition());
    }

    // Check if a folder was eliminated
    private void checkFolderDeleted(long idFolder) {
        try {
            RequestById request = new RequestById(idFolder);
            GPFolder subFolderOfRootFolder = geoPlatformService.getFolderDetail(request);
            Assert.fail("Folder with id \"" + idFolder + "\" was NOT deleted in cascade");
        } catch (Exception e) {
            logger.debug("\n*** Folder with id \"{}\" was deleted in cascade ***", idFolder);
        }
    }
}
