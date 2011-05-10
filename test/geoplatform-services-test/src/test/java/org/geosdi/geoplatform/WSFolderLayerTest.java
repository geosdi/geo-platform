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
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSFolderLayerTest extends ServiceTest {

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
    
    private final String urlServer = "http://www.geosdi.org/test";
    private final String abstractFolderRasterLayer1 = "abstractFolderRasterLayer1";
    private final String nameRasterLayer1 = "rasterLayer1";
    private final String spatialReferenceSystemRasterLayer1 = "Geographic coordinate system";
    private final String titleRasterLayer1 = "Raster Layer 1";
    private GPRasterLayer rasterLayer1 = null;
    private long idRasterLayer1 = -1;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        logger.info("WSFolderLayerTest - SetUp --------------------------------> " + this.getClass().getName());

        findUser = geoPlatformService.getUserDetailByName(new SearchRequest(username));

        idFolder1 = createAndInsertFolderWithParent(nameFolder1, rootFolderA, -1, false);
        folder1 = geoPlatformService.getFolderDetail(new RequestById(idFolder1));

        idFolder2 = createAndInsertFolderWithParent(nameFolder2, rootFolderA, -1, false);
        folder2 = geoPlatformService.getFolderDetail(new RequestById(idFolder2));
        
        idFolder3 = createAndInsertFolderWithParent(nameFolder3, rootFolderB, -1, false);
        folder3 = geoPlatformService.getFolderDetail(new RequestById(idFolder3));

        idFolder4 = createAndInsertFolderWithParent(nameFolder4, rootFolderB, -1, false);
        folder4 = geoPlatformService.getFolderDetail(new RequestById(idFolder4));

        idFolder5 = createAndInsertFolderWithParent(nameFolder5, rootFolderB, -1, false);
        folder5 = geoPlatformService.getFolderDetail(new RequestById(idFolder5));
        
//        idRasterLayer1 = createAndInsertRasterLayer(abstractFolderRasterLayer1, rootFolderA, nameRasterLayer1, -1, false, spatialReferenceSystemRasterLayer1,
//                                                    titleRasterLayer1, urlServer);
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
    public void testDeleteFolderB() {
        try {
            geoPlatformService.deleteFolder(new RequestById(idRootFolderB));
            
            FolderList folderList = geoPlatformService.getUserFoldersByUserId(idUser);
            
            Assert.assertEquals(folderList.getList().size(), 1);
            Assert.assertEquals(folderList.getList().iterator().next().getName(), nameRootFolderA);
        } catch (IllegalParameterFault ex) {
            Assert.fail("Folder has an illegal parameter");
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Folder with id \"" + idRootFolderB + "\" not found");
        }
    }

    @Test
    public void testDeleteChildrenFolder() {
        try {
            folder4.setParent(folder3);
            folder5.setParent(folder3);
            geoPlatformService.updateFolder(folder4);
            geoPlatformService.updateFolder(folder5);
            
            geoPlatformService.deleteFolder(new RequestById(idFolder3));
            
            FolderList folderList = geoPlatformService.getUserFoldersByUserId(idUser);
            
            Assert.assertEquals(folderList.getList().size(), 2);
            
            Iterator<FolderDTO> iterator = folderList.getList().iterator();
            Assert.assertEquals(iterator.next().getName(), nameRootFolderB);
            Assert.assertEquals(iterator.next().getName(), nameRootFolderA);
            
            TreeFolderElements childrenRootFolderA = geoPlatformService.getChildrenElements(idRootFolderA);
            Assert.assertNotNull(childrenRootFolderA);
            Assert.assertEquals(childrenRootFolderA.size(), 1);
            
            TreeFolderElements childrenRootFolderB = geoPlatformService.getChildrenElements(idRootFolderB);
            Assert.assertNull(childrenRootFolderB);
        } catch (IllegalParameterFault ex) {
            Assert.fail("Folder has an illegal parameter");
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Folder with id \"" + idFolder4 + "\" not found");
        }
    }
    
    @Test
    public void testGetLayer() {
        Assert.assertTrue(true);
    }

//    @Test
//    public void testGetFolderAndLayer() {
//    }
}
