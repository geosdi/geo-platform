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

import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSFolderLayerTest extends ServiceTest {

    private String username = "user_folder_test";
    private GPUser findUser = null;

    @Test
    public void testGetFolder() {
        GPUser user_test = super.createUser(username);
        geoPlatformService.insertUser(user_test);

        try {
            findUser = geoPlatformService.getUserDetailByName(new SearchRequest(username));

            int position = 0;
            createAndInsertFolder("folderA", findUser, null, ++position, false);
            FolderList findFolderA = geoPlatformService.getUserFoldersByUserId(findUser.getId());
            Assert.assertNotNull(findFolderA);
            Assert.assertEquals(findFolderA.getList().size(), 1);
            Assert.assertEquals(findFolderA.getList().iterator().next().getName(), "folderA");
            Assert.assertEquals(findFolderA.getList().iterator().next().getPosition(), 1);

//            GPFolder folderB1 = createAndInsertFolder("folderB1", null, , ++position, false);
//            GPFolder folderC = createAndInsertFolder("folderC", null, folderB1, ++position, false);
//            GPFolder folderB2 = createAndInsertFolder("folderB2", null, folderA, ++position, false);
//            GPFolder folderD = createAndInsertFolder("folderD", null, folderB2, ++position, false);
//            GPFolder folderE = createAndInsertFolder("folderE", null, folderB2, ++position, false);
//
//            FolderList rootChildrens = geoPlatformService.getChildrenFoldersByFolderId(findFolderA.getList().iterator().next().getId());
//            Assert.assertNotNull(rootChildrens);
//            Assert.assertEquals(rootChildrens.getList().size(), 2);
//
//            FolderDTO folderDTOCheckB1 = rootChildrens.getList().iterator().next();
//            Assert.assertEquals(folderDTOCheckB1.getName(), "folderB1");
//            Assert.assertEquals(folderDTOCheckB1.getPosition(), 2);
//
//            FolderDTO folderDTOCheckB2 = rootChildrens.getList().iterator().next();
//            Assert.assertEquals(folderDTOCheckB2.getName(), "folderB2");
//            Assert.assertEquals(folderDTOCheckB2.getPosition(), 4);            
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("User \"" + username + "\" not found");
        }
    }

//    @Test
//    public void testGetLayer() {
//    }
//
//    @Test
//    public void testGetFolderAndLayer() {
//    }
    
    @After
    public void tearDown() {
        try {
            boolean check = geoPlatformService.deleteUser(new RequestById(findUser.getId()));
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("User \"" + findUser.getUsername() + "\" not found");
        } catch (IllegalParameterFault ex) {
            Assert.fail("Unable to delete user \"" + findUser.getUsername() + "\"");
        }
    }

    private void createAndInsertFolder(String folderName, GPUser owner, GPFolder parentFolder, int position, boolean shared) {
        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        if (owner != null) {
            folder.setOwner(owner);
        } else {
            folder.setParent(parentFolder);
        }
        folder.setPosition(position);
        folder.setShared(shared);
        geoPlatformService.insertFolder(folder);
    }
}
