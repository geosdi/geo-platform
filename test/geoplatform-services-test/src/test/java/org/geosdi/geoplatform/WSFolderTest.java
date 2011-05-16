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
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSFolderTest extends ServiceTest {

    // Folder
    private final String nameFolder1 = "folder1";
    private GPFolder folder1 = null;
    private long idFolder1 = -1;
    private final String nameFolder2 = "folder2";
    private GPFolder folder2 = null;
    private long idFolder2 = -1;
    private final String nameFolder3 = "folder3";
    private GPFolder folder3 = null;
    private long idFolder3 = -1;
    private final String nameFolder4 = "folder4";
    private GPFolder folder4 = null;
    private long idFolder4 = -1;
    private final String nameFolder5 = "folder5";
    private GPFolder folder5 = null;
    private long idFolder5 = -1;

    @Before
    // "position" will be set without application logic, but only to have different values
    public void setUp() throws Exception {
        super.setUp();
        logger.info("WSFolderLayerTest - SetUp --------------------------------> " + this.getClass().getName());

        findUser = geoPlatformService.getUserDetailByName(new SearchRequest(username));

        // "rootFolderA" ---> "folder1"
        idFolder1 = createAndInsertFolderWithParent(nameFolder1, rootFolderA, 3, false);
        folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));
        // "rootFolderA" ---> "folder2"
        idFolder2 = createAndInsertFolderWithParent(nameFolder2, rootFolderA, 4, false);
        folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));

        // "rootFolderB" ---> "folder3"
        idFolder3 = createAndInsertFolderWithParent(nameFolder3, rootFolderB, 5, false);
        folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));
        // "rootFolderB" ---> "folder4"
        idFolder4 = createAndInsertFolderWithParent(nameFolder4, rootFolderB, 6, false);
        folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));
        // "rootFolderB" ---> "folder5"
        idFolder5 = createAndInsertFolderWithParent(nameFolder5, rootFolderB, 7, false);
        folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
    }

    @Test
    public void testGetFolder() {
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
            Assert.fail("Folder has an illegal parameter");
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("User \"" + username + "\" not found");
        }
    }

    @Test
    public void testDeleteFolder() {
        FolderDTO foderToCheck = null;
        try {
            // Assert total number of folders stored into DB before delete
            FolderList allFoldersBeforeDelete = geoPlatformService.getFolders();
            Assert.assertEquals("Assert number of folders stored into DB before delete",allFoldersBeforeDelete.getList().size(), 12);
            
            // Delete "rootFolderB" and in cascade "folder3" & "folder4" & "folder5"
            geoPlatformService.deleteFolder(new RequestById(idRootFolderB));

            // "rootFolderA" ---> "folder1" & "folder2"
            FolderList folderList = geoPlatformService.getUserFoldersByUserId(idUser);

            // Assert on the structure of user's folders
            Assert.assertEquals(folderList.getList().size(), 1);

            // Assert on "rootFolderA"
            foderToCheck = folderList.getList().iterator().next();
            logger.info("\n***** foderToCheck:\n" + foderToCheck + "\n*****");
            Assert.assertEquals(foderToCheck.getId(), idRootFolderA);
            Assert.assertEquals(foderToCheck.getName(), nameRootFolderA);
            // Assert on the structure of the subfolders of "rootFolderA"
            TreeFolderElements childrenRootFolderA = geoPlatformService.getChildrenElements(idRootFolderA);
            logger.info("\n************************ childrenRootFolderA:\n" + childrenRootFolderA + "\n*****");
            Assert.assertNotNull(childrenRootFolderA);
            Assert.assertEquals(childrenRootFolderA.size(), 2);
            // Iterator for scan folder in descending order
            Iterator iterator = childrenRootFolderA.iterator();
            // Assert on "folder2"
            FolderDTO folderDTOToCheck = (FolderDTO) iterator.next();
            logger.info("\n***** folderDTOToCheck:\n" + folderDTOToCheck + "\n*****");
            Assert.assertEquals(folderDTOToCheck.getId(), idFolder2);
            Assert.assertEquals(folderDTOToCheck.getName(), nameFolder2);
            Assert.assertTrue(folderDTOToCheck.isEmpty());
            // Assert on "folder1"
            folderDTOToCheck = (FolderDTO) iterator.next();
            logger.info("\n***** folderDTOToCheck:\n" + folderDTOToCheck + "\n*****");
            Assert.assertEquals(folderDTOToCheck.getId(), idFolder1);
            Assert.assertEquals(folderDTOToCheck.getName(), nameFolder1);
            Assert.assertTrue(folderDTOToCheck.isEmpty());

            // Assert on "rootFolderB" (deleted)
            TreeFolderElements childrenRootFolderB = geoPlatformService.getChildrenElements(idRootFolderB);
            Assert.assertNull(childrenRootFolderB);

            // Assert total number of folders stored into DB after delete
            FolderList allFoldersAfterDelete = geoPlatformService.getFolders();
            Assert.assertEquals("Assert number of folders stored into DB after delete",allFoldersAfterDelete.getList().size(), 8);
        } catch (IllegalParameterFault ipf) {
            Assert.fail("Folder has an illegal parameter");
        } catch (ResourceNotFoundFault rnff) {
            Assert.fail("Folder with id \"" + idRootFolderB + "\" not found");
        } catch (Exception e) {
            Assert.fail("Exception: " + e.getClass());
        }

        // Check ON DELETE CASCADE of the subforders of "rootFolderB"
        checkFolderDeleted(idFolder3);
        checkFolderDeleted(idFolder4);
        checkFolderDeleted(idFolder5);
    }

    // Check if a folder was eliminated
    private void checkFolderDeleted(long idFolder) {
        try {
            RequestById request = new RequestById(idFolder);
            GPFolder subFolderOfRootFolder = geoPlatformService.getFolderDetail(request);
            Assert.fail("Folder with id \"" + idFolder + "\" was NOT deleted in cascade");
        } catch (Exception e) {
            logger.info("\n***** Folder with id \"" + idFolder + "\" was deleted in cascade");
        }
    }
}
