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
package org.geosdi.geoplatform.model.soap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.folder.WSAddFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.folder.WSDeleteFolderAndTreeModifications;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.response.FolderDTO;
import org.geosdi.geoplatform.response.ProjectDTO;
import org.geosdi.geoplatform.response.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.response.collection.TreeFolderElements;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WSFolderTest extends BaseSoapServiceTest {

    private static final String nameFolder1 = "folder1";
    private static final String nameFolder2 = "folder2";
    private static final String nameFolder3 = "folder3";
    private static final String nameFolder4 = "folder4";
    private static final String nameFolder5 = "folder5";
    private GPFolder folder1;
    private GPFolder folder2;
    private GPFolder folder3;
    private GPFolder folder4;
    private GPFolder folder5;
    private long idFolder1 = -1;
    private long idFolder2 = -1;
    private long idFolder3 = -1;
    private long idFolder4 = -1;
    private long idFolder5 = -1;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // "rootFolderA" ---> "folder1"
        idFolder1 = super.createAndInsertFolder(nameFolder1, projectTest, 6,
                rootFolderA);
        folder1 = gpWSClient.getFolderDetail(idFolder1);
        // "rootFolderA" ---> "folder2"
        idFolder2 = super.createAndInsertFolder(nameFolder2, projectTest, 5,
                rootFolderA);
        folder2 = gpWSClient.getFolderDetail(idFolder2);
        //
        super.rootFolderA.setPosition(7);
        super.rootFolderA.setNumberOfDescendants(2);
        gpWSClient.updateFolder(rootFolderA);

        // "rootFolderB" ---> "folder3"
        idFolder3 = super.createAndInsertFolder(nameFolder3, projectTest, 3,
                rootFolderB);
        folder3 = gpWSClient.getFolderDetail(idFolder3);
        // "rootFolderB" ---> "folder4"
        idFolder4 = super.createAndInsertFolder(nameFolder4, projectTest, 2,
                rootFolderB);
        folder4 = gpWSClient.getFolderDetail(idFolder4);
        // "rootFolderB" ---> "folder5"
        idFolder5 = super.createAndInsertFolder(nameFolder5, projectTest, 1,
                rootFolderB);
        folder5 = gpWSClient.getFolderDetail(idFolder5);
        //
        super.rootFolderB.setPosition(4);
        super.rootFolderB.setNumberOfDescendants(3);
        gpWSClient.updateFolder(rootFolderB);

        super.projectTest.setNumberOfElements(
                projectTest.getNumberOfElements() + 5);
        gpWSClient.updateProject(projectTest);
    }

    @Test
    public void testFoldersTest() {
        Assert.assertNotNull("Folder 1 is NULL", folder1);
        Assert.assertEquals("ID of Folder 1 is incorrect",
                folder1.getId().longValue(), idFolder1);

        Assert.assertNotNull("Folder 2 is NULL", folder2);
        Assert.assertEquals("ID of Folder 2 is incorrect",
                folder2.getId().longValue(), idFolder2);

        Assert.assertNotNull("Folder 3 is NULL", folder3);
        Assert.assertEquals("ID of Folder 3 is incorrect",
                folder3.getId().longValue(), idFolder3);

        Assert.assertNotNull("Folder 4 is NULL", folder4);
        Assert.assertEquals("ID of Folder 4 is incorrect",
                folder4.getId().longValue(), idFolder4);

        Assert.assertNotNull("Folder 5 is NULL", folder5);
        Assert.assertEquals("ID of Folder 5 is incorrect",
                folder5.getId().longValue(), idFolder5);
    }

    @Test
    public void testGetShortFolder() throws ResourceNotFoundFault {
        FolderDTO folderA = gpWSClient.getShortFolder(idRootFolderA);
        Assert.assertNotNull("assertNotNull Folder A", folderA);

        FolderDTO folderB = gpWSClient.getShortFolder(idRootFolderB);
        Assert.assertNotNull("assertNotNull Folder B", folderB);
    }

    @Test
    public void testUpdateFolder()
            throws IllegalParameterFault, ResourceNotFoundFault {
        final String nameFolderUpdated = "folderUpdated";

        folder5.setParent(rootFolderA);
        folder5.setName(nameFolderUpdated);

        gpWSClient.updateFolder(folder5);
        GPFolder folderUpdated = gpWSClient.getFolderDetail(idFolder5);

        Assert.assertNotNull("FolderUpdated is NULL", folderUpdated);
        Assert.assertEquals("Folder name of FolderUpdated NOT match",
                nameFolderUpdated, folderUpdated.getName());
        Assert.assertEquals("Parent ID of FolderUpdated NOT match",
                idRootFolderA, folderUpdated.getParent().getId().longValue());
    }

    @Test
    public void testDeleteFolder() throws ResourceNotFoundFault {
        // Assert number of folders of ProjectTest before delete
        int totalFolders = gpWSClient.getNumberOfElementsProject(idProjectTest);
        Assert.assertEquals(
                "Number of all folders of ProjectTest before deleted",
                7, totalFolders); // SetUp() added 2+5 folders
        //
        ProjectDTO projectWithRootFolders = gpWSClient.getProjectWithRootFolders(
                idProjectTest, super.idUserTest);
        Assert.assertNotNull("projectWithRootFolders null",
                projectWithRootFolders);

        List<FolderDTO> rootFolderList = projectWithRootFolders.getRootFolders();
        Assert.assertNotNull("List of root folders is null", rootFolderList);
        Assert.assertEquals(
                "Number of root folders of ProjectTest before deleted",
                2, rootFolderList.size());

        // Delete "rootFolderB" and in cascade "folder3" & "folder4" & "folder5"
        gpWSClient.deleteFolder(idRootFolderB);

        // "rootFolderA" ---> "folder1" & "folder2"
        projectWithRootFolders = gpWSClient.getProjectWithRootFolders(
                idProjectTest, super.idUserTest);
        Assert.assertNotNull("projectWithRootFolders null",
                projectWithRootFolders);

        rootFolderList = projectWithRootFolders.getRootFolders();
        Assert.assertNotNull("List of mod root folders is null", rootFolderList);
        Assert.assertEquals(
                "Number of root folders of ProjectTest after deleted",
                1, rootFolderList.size());

        // Assert on the structure of project's folders
        // Assert on "rootFolderA"
        FolderDTO folderToCheck = rootFolderList.iterator().next();
        logger.trace("\n*** folderToCheck:\n{}\n***", folderToCheck);
        Assert.assertEquals("Check ID of rootFolderA", rootFolderA.getId(),
                folderToCheck.getId());
        // Assert on the structure of the subfolders of "rootFolderA"
        TreeFolderElements childrenRootFolderA = gpWSClient.getChildrenElements(
                idRootFolderA).getFolderElements();
        logger.trace("\n*** childrenRootFolderA:\n{}\n***", childrenRootFolderA);
        Assert.assertNotNull("Check childrenRootFolderA not null",
                childrenRootFolderA);
        Assert.assertEquals("Check size of childrenRootFolderA", 2,
                childrenRootFolderA.size());
        // Iterator for scan folder in descending order
        Iterator childsIterator = childrenRootFolderA.iterator();
        // Assert on "folder1"
        FolderDTO folderDTOToCheck = (FolderDTO) childsIterator.next();
        logger.trace("\n*** folder_1_DTOToCheck:\n{}\n***", folderDTOToCheck);
        Assert.assertEquals("Check ID of folder 1", folder1.getId(),
                folderDTOToCheck.getId());
        // Assert on "folder2"
        folderDTOToCheck = (FolderDTO) childsIterator.next();
        logger.trace("\n*** folder_2_DTOToCheck:\n{}\n***", folderDTOToCheck);
        Assert.assertEquals("Check ID of folder 2", folder2.getId(),
                folderDTOToCheck.getId());

        // Assert on "rootFolderB" (deleted)
        TreeFolderElements childrenRootFolderB = gpWSClient.getChildrenElements(
                idRootFolderB).getFolderElements();
        Assert.assertEquals("Check childrenRootFolderB must be Empty",
                Boolean.TRUE, childrenRootFolderB.isEmpty());

        // Check ON DELETE CASCADE of the subforders of "rootFolderB"
        this.checkFolderDeleted(idFolder3);
        this.checkFolderDeleted(idFolder4);
        this.checkFolderDeleted(idFolder5);
    }

    @Test
    public void testSaveAndDeleteFolderAndTreeModifications()
            throws IllegalParameterFault, ResourceNotFoundFault {
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map); // Set an empty map

        int totalElementsOfProject = gpWSClient.getNumberOfElementsProject(
                idProjectTest);
        Assert.assertEquals("Initial totalElementsOfProject",
                7, totalElementsOfProject);  // SetUp() added 2+5 folders

        String nameFolderToTest = "folderToTest";
        GPFolder folderToTest = super.createFolder(nameFolderToTest, projectTest,
                1, null);

        // Adding new folder to project's root            
        long idFolderToTest = gpWSClient.saveAddedFolderAndTreeModifications(new WSAddFolderAndTreeModificationsRequest(
                        projectTest.getId(),
                        null, folderToTest, descendantsMapData));

        Assert.assertEquals("totalElementsOfProject after added",
                totalElementsOfProject + 1,
                gpWSClient.getNumberOfElementsProject(idProjectTest).intValue());

        this.checkState(new int[]{8, 7, 6, 5, 4, 3, 2}, new int[]{2, 3},
                "before removing");

        // Removing folder from user's root
        boolean checkDelete = gpWSClient.saveDeletedFolderAndTreeModifications(new WSDeleteFolderAndTreeModifications(idFolderToTest,
                        descendantsMapData));
        Assert.assertTrue("Delete NOT done for \"" + nameFolderToTest + "\"",
                checkDelete);

        Assert.assertEquals("totalElementsOfProject after deleted",
                totalElementsOfProject, gpWSClient.getNumberOfElementsProject(
                        idProjectTest).intValue());

        this.checkInitialState("after removing");
    }

    @Test
    public void testSaveAndDeleteSubfolderAndTreeModifications()
            throws IllegalParameterFault, ResourceNotFoundFault {
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        int totalElementsOfProject = gpWSClient.getNumberOfElementsProject(
                idProjectTest);
        Assert.assertEquals("Initial totalElementsOfProject",
                7, totalElementsOfProject);  // SetUp() added 2+5 folders

        List<FolderDTO> childrenFolders = gpWSClient.getChildrenFolders(
                idRootFolderB).getChildren();
        Assert.assertEquals(
                "Before adding new folder - Number of subfolders of root folder B ",
                3, childrenFolders.size());

        String nameFolderToTest = "folderToTest";
        GPFolder folderToTest = super.createFolder(nameFolderToTest, projectTest,
                3, rootFolderB);

        // Adding new folder to user's root folder B
        map.put(idRootFolderB, 4);
        long idFolderToTest = gpWSClient.saveAddedFolderAndTreeModifications(new WSAddFolderAndTreeModificationsRequest(
                        projectTest.getId(),
                        rootFolderB.getId(), folderToTest,
                        descendantsMapData));

        Assert.assertEquals("totalElementsOfProject after added",
                totalElementsOfProject + 1,
                gpWSClient.getNumberOfElementsProject(idProjectTest).intValue());

        this.checkState(new int[]{8, 7, 6, 5, 4, 2, 1}, new int[]{2, 4},
                "before removing");

        // Removing folder from user's root folder B
        map.clear();
        map.put(idRootFolderB, 3);
        boolean checkDelete = gpWSClient.saveDeletedFolderAndTreeModifications(new WSDeleteFolderAndTreeModifications(idFolderToTest,
                        descendantsMapData));
        Assert.assertTrue("Delete NOT done for \"" + nameFolderToTest + "\"",
                checkDelete);

        Assert.assertEquals("totalElementsOfProject after deleted",
                totalElementsOfProject, gpWSClient.getNumberOfElementsProject(
                        idProjectTest).intValue());

        this.checkInitialState("after removing");
    }

    @Test
    public void testDragAndDropOnSameParent() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropOnSameParent @@@");
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        // Move folder 5 between folder 3 and folder 4 (oldPosition < new Position)
        boolean checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(idFolder5,
                        super.idRootFolderB, 2, descendantsMapData));
        Assert.assertTrue("Folder 5 doesn't moved to position 2", checkDD);

        this.checkState(new int[]{7, 6, 5, 4, 3, 1, 2}, new int[]{2, 3},
                "after DD I on same parent");

        // Move folder 5 after folder 4, in initial position (oldPosition > new Position)
        checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(idFolder5,
                        super.idRootFolderB, 1, descendantsMapData));
        Assert.assertTrue("Folder 5 doesn't moved to position 1", checkDD);

        this.checkInitialState("after DD II on same parent");
    }

    @Test
    public void testDragAndDropOnDifferentParent() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropOnDifferentParent @@@");
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        map.put(super.idRootFolderA, 3);
        map.put(super.idRootFolderB, 2);
        // Move folder 4 between folder 1 and folder 2 (oldPosition < new Position)
        boolean checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(idFolder4,
                        super.idRootFolderA, 5, descendantsMapData));
        Assert.assertTrue("Folder 4 doesn't moved to position 5", checkDD);

        this.checkState(new int[]{7, 6, 4, 3, 2, 5, 1}, new int[]{3, 2},
                "after DD I on different parent");

        // Move folder 4 after folder 3, in initial position (oldPosition > new Position)
        map.clear();
        map.put(super.idRootFolderA, 2);
        map.put(super.idRootFolderB, 3);
        checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(idFolder4,
                        super.idRootFolderB, 2, descendantsMapData));
        Assert.assertTrue("Folder 4 doesn't moved to position 2", checkDD);

        this.checkInitialState("after DD II on different parent");
    }

    @Test
    public void testDragAndDropOnRootParent() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropOnRootParent @@@");
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        // Move folder B before folder A (oldPosition < new Position)
        boolean checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(
                        super.idRootFolderB, null, 7, descendantsMapData));
        Assert.assertTrue("Folder B doesn't moved to position 7", checkDD);

        this.checkState(new int[]{3, 2, 1, 7, 6, 5, 4}, new int[]{2, 3},
                "after DD I on root parent");

        // Move folder B after folder A, in initial position (oldPosition > new Position)
        checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(
                        super.idRootFolderB, null, 4, descendantsMapData));
        Assert.assertTrue("Folder 4 doesn't moved to position 4", checkDD);

        this.checkInitialState("after DD II on root parent");
    }

    @Test
    public void testDragAndDropFromRootToTop() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropFromRootToTop @@@");
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        map.put(idRootFolderA, 6);
        // Move Folder B after Folder 1 (oldPosition < new Position)
        boolean checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(
                        super.idRootFolderB, super.idRootFolderA, 6,
                        descendantsMapData));
        Assert.assertTrue("Folder B doesn't moved to position 6", checkDD);

        this.checkState(new int[]{7, 2, 1, 6, 5, 4, 3}, new int[]{6, 3},
                "after DD I from root to top");
        Assert.assertNull("Parent of root folder A after DD I from root to top",
                rootFolderA.getParent());
        Assert.assertNotNull(
                "Parent of root folder B after DD I from root to top",
                rootFolderB.getParent());
        Assert.assertEquals(
                "Parent of root folder B after DD I from root to top",
                idRootFolderA, rootFolderB.getParent().getId().longValue());

        map.clear();
        map.put(idRootFolderA, 2);
        // Move folder B in initial position (oldPosition > new Position)
        checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(
                        super.idRootFolderB, null, 4, descendantsMapData));
        Assert.assertTrue("Folder B doesn't moved to position 4", checkDD);

        this.checkInitialState("after DD II from root to top");
    }

    @Test
    public void testDragAndDropFromRootToBottom() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testDragAndDropFromRootToBottom @@@");
        Map<Long, Integer> map = new HashMap<>();
        GPWebServiceMapData descendantsMapData = new GPWebServiceMapData();
        descendantsMapData.setDescendantsMap(map);

        map.put(idRootFolderB, 6);
        // Move Folder A after Folder 3 (oldPosition > new Position)
        boolean checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(
                        super.idRootFolderA, super.idRootFolderB, 5,
                        descendantsMapData));
        Assert.assertTrue("Folder A doesn't moved to position 5", checkDD);

        this.checkState(new int[]{5, 4, 3, 7, 6, 2, 1}, new int[]{2, 6},
                "after DD I from root to bottom");
        Assert.assertNotNull(
                "Parent of root folder A after DD I from root to bottom",
                rootFolderA.getParent());
        Assert.assertEquals(
                "Parent of root folder A after DD I from root to bottom",
                idRootFolderB, rootFolderA.getParent().getId().longValue());
        Assert.assertNull(
                "Parent of root folder B after DD I from root to bottom",
                rootFolderB.getParent());

        map.clear();
        map.put(idRootFolderB, 3);
        // Move folder A in initial position (oldPosition < new Position)
        checkDD = gpWSClient.saveDragAndDropFolderAndTreeModifications(new WSDDFolderAndTreeModifications(
                        super.idRootFolderA, null, 7, descendantsMapData));
        Assert.assertTrue("Folder B doesn't moved to position 7", checkDD);

        this.checkInitialState("after DD II from root to bottom");
    }

    private void checkInitialState(String info) throws ResourceNotFoundFault {
        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);
        Assert.assertEquals("Position of root folder A - " + info, 7,
                rootFolderA.getPosition());
        Assert.assertNull("Parent of root folder A - " + info,
                rootFolderA.getParent());
        Assert.assertEquals("Number of descendant of root folder A - " + info, 2,
                rootFolderA.getNumberOfDescendants());

        folder1 = gpWSClient.getFolderDetail(idFolder1);
        Assert.assertEquals("Position of folder 1 - " + info, 6,
                folder1.getPosition());

        folder2 = gpWSClient.getFolderDetail(idFolder2);
        Assert.assertEquals("Position of folder 2 - " + info, 5,
                folder2.getPosition());

        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);
        Assert.assertEquals("Position of root folder B - " + info, 4,
                rootFolderB.getPosition());
        Assert.assertNull("Parent of root folder B - " + info,
                rootFolderB.getParent());
        Assert.assertEquals("Number of descendant of root folder B - " + info, 3,
                rootFolderB.getNumberOfDescendants());

        folder3 = gpWSClient.getFolderDetail(idFolder3);
        Assert.assertEquals("Position of folder 3 - " + info, 3,
                folder3.getPosition());

        folder4 = gpWSClient.getFolderDetail(idFolder4);
        Assert.assertEquals("Position of folder 4 - " + info, 2,
                folder4.getPosition());

        folder5 = gpWSClient.getFolderDetail(idFolder5);
        Assert.assertEquals("Position of folder 5 - " + info, 1,
                folder5.getPosition());
    }

    private void checkState(int[] positions, int[] numberOfDescendants,
            String info)
            throws ResourceNotFoundFault {
        Assert.assertEquals("Array positions must have exactly 7 elements", 7,
                positions.length);
        Assert.assertEquals(
                "Array numberOfDescendants must have exactly 2 elements", 2,
                numberOfDescendants.length);

        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);
        Assert.assertEquals("Position of root folder A - " + info, positions[0],
                rootFolderA.getPosition());
        Assert.assertEquals("Number of descendant of root folder A - " + info,
                numberOfDescendants[0], rootFolderA.getNumberOfDescendants());

        folder1 = gpWSClient.getFolderDetail(idFolder1);
        Assert.assertEquals("Position of folder 1 - " + info, positions[1],
                folder1.getPosition());

        folder2 = gpWSClient.getFolderDetail(idFolder2);
        Assert.assertEquals("Position of folder 2 - " + info, positions[2],
                folder2.getPosition());

        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);
        Assert.assertEquals("Position of root folder B - " + info, positions[3],
                rootFolderB.getPosition());
        Assert.assertEquals("Number of descendant of root folder B - " + info,
                numberOfDescendants[1], rootFolderB.getNumberOfDescendants());

        folder3 = gpWSClient.getFolderDetail(idFolder3);
        Assert.assertEquals("Position of folder 3 - " + info, positions[4],
                folder3.getPosition());

        folder4 = gpWSClient.getFolderDetail(idFolder4);
        Assert.assertEquals("Position of folder 4 - " + info, positions[5],
                folder4.getPosition());

        folder5 = gpWSClient.getFolderDetail(idFolder5);
        Assert.assertEquals("Position of folder 5 - " + info, positions[6],
                folder5.getPosition());
    }

    /**
     * Check if a folder was eliminated.
     */
    private void checkFolderDeleted(long idFolder) {
        try {
            gpWSClient.getFolderDetail(idFolder);
            Assert.fail(
                    "Folder with id \"" + idFolder + "\" was NOT deleted in cascade");
        } catch (Exception e) {
            logger.debug(
                    "\n*** Folder with id \"{}\" was deleted in cascade ***",
                    idFolder);
        }
    }
}
