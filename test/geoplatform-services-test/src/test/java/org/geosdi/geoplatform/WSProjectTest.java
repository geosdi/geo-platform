/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
package org.geosdi.geoplatform;

import java.util.List;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.junit.Test;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class WSProjectTest extends ServiceTest {

    private final String nameFolder1A = "folder1A";
    private final String nameFolder1B = "folder1B";
    private final String nameFolder1C = "folder1C";
    //
    private final String nameFolder2A = "folder2A";
    private final String nameFolder2B = "folder2B";
    private final String nameFolder2C = "folder2C";
    //
    private final String nameFolder3A = "folder3A";
    private final String nameFolder3B = "folder3B";
    private final String nameFolder3C = "folder3C";
    //
    private GPFolder folder1A = null;
    private GPFolder folder1B = null;
    private GPFolder folder1C = null;
    private GPFolder folder2A = null;
    private GPFolder folder2B = null;
    private GPFolder folder2C = null;
    private GPFolder folder3A = null;
    private GPFolder folder3B = null;
    private GPFolder folder3C = null;
    private long idFolder1A = -1;
    private long idFolder1B = -1;
    private long idFolder1C = -1;
    private long idFolder2A = -1;
    private long idFolder2B = -1;
    private long idFolder2C = -1;
    private long idFolder3A = -1;
    private long idFolder3B = -1;
    private long idFolder3C = -1;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // "rootFolderA" ---> "folder1(A|B|C)"
        idFolder1A = super.createAndInsertFolder(nameFolder1A, projectTest, 10, rootFolderA, 3);
        folder1A = gpWSClient.getFolderDetail(idFolder1A);
        //
        idFolder1B = super.createAndInsertFolder(nameFolder1B, projectTest, 9, rootFolderA);
        folder1B = gpWSClient.getFolderDetail(idFolder1B);
        //
        idFolder1C = super.createAndInsertFolder(nameFolder1C, projectTest, 8, rootFolderA);
        folder1C = gpWSClient.getFolderDetail(idFolder1C);
        //
        // "folder1A" ---> "folder2(A|B|C)"
        idFolder2A = super.createAndInsertFolder(nameFolder2A, projectTest, 7, folder1A, 3);
        folder2A = gpWSClient.getFolderDetail(idFolder2A);
        //
        idFolder2B = super.createAndInsertFolder(nameFolder2B, projectTest, 6, folder1A);
        folder2B = gpWSClient.getFolderDetail(idFolder2B);
        //
        idFolder2C = super.createAndInsertFolder(nameFolder2C, projectTest, 5, folder1A);
        folder2C = gpWSClient.getFolderDetail(idFolder2C);
        //
        // "folder2A" ---> "folder3(A|B|C)"
        idFolder3A = super.createAndInsertFolder(nameFolder3A, projectTest, 4, folder2A);
        folder3A = gpWSClient.getFolderDetail(idFolder3A);
        //
        idFolder3B = super.createAndInsertFolder(nameFolder3B, projectTest, 3, folder2A);
        folder3B = gpWSClient.getFolderDetail(idFolder3B);
        //
        idFolder3C = super.createAndInsertFolder(nameFolder3C, projectTest, 2, folder2A);
        folder3C = gpWSClient.getFolderDetail(idFolder3C);
        //
        super.rootFolderA.setPosition(11);
        super.rootFolderA.setNumberOfDescendants(9);
        gpWSClient.updateFolder(rootFolderA);

        super.projectTest.setNumberOfElements(projectTest.getNumberOfElements() + 5);
        gpWSClient.updateProject(projectTest);
    }

    @Test
    public void testFixture() {
        Assert.assertNotNull("Folder1A is NULL", folder1A);
        Assert.assertEquals("ID of Folder1A is incorrect", folder1A.getId().longValue(), idFolder1A);
        Assert.assertNotNull("Folder1B is NULL", folder1B);
        Assert.assertEquals("ID of Folder1B is incorrect", folder1B.getId().longValue(), idFolder1B);
        Assert.assertNotNull("Folder1C is NULL", folder1C);
        Assert.assertEquals("ID of Folder1C is incorrect", folder1C.getId().longValue(), idFolder1C);

        Assert.assertNotNull("Folder2A is NULL", folder2A);
        Assert.assertEquals("ID of Folder2A is incorrect", folder2A.getId().longValue(), idFolder2A);
        Assert.assertNotNull("Folder2B is NULL", folder2B);
        Assert.assertEquals("ID of Folder2B is incorrect", folder2B.getId().longValue(), idFolder2B);
        Assert.assertNotNull("Folder2C is NULL", folder2C);
        Assert.assertEquals("ID of Folder2 is incorrect", folder2C.getId().longValue(), idFolder2C);

        Assert.assertNotNull("Folder3A is NULL", folder3A);
        Assert.assertEquals("ID of Folder3A is incorrect", folder3A.getId().longValue(), idFolder3A);
        Assert.assertNotNull("Folder3B is NULL", folder3B);
        Assert.assertEquals("ID of Folder3B is incorrect", folder3B.getId().longValue(), idFolder3B);
        Assert.assertNotNull("Folder3 is NULL", folder3C);
        Assert.assertEquals("ID of Folder3C is incorrect", folder3C.getId().longValue(), idFolder3C);
    }

    @Test
    public void testGetElement() throws ResourceNotFoundFault {
        ProjectDTO project = gpWSClient.getElements(super.idProjectTest);

        Assert.assertEquals(super.projectTest.getName(), project.getName());
        Assert.assertEquals(super.projectTest.getNumberOfElements(), project.getNumberOfElements());
        
        List<FolderDTO> rootFolders = project.getRootFolders();
        Assert.assertEquals("#root", 2, rootFolders.size());
        Assert.assertEquals("A", nameRootFolderA, rootFolders.get(0).getName());
        
        List<FolderDTO> childFolder1A = (List<FolderDTO>) rootFolders.get(0).getElementList();
        Assert.assertEquals("#1A", 3, childFolder1A.size());
        Assert.assertEquals("1A", nameFolder1A, childFolder1A.get(0).getName());
        Assert.assertEquals("1B", nameFolder1B, childFolder1A.get(1).getName());
        Assert.assertEquals("1C", nameFolder1C, childFolder1A.get(2).getName());
        
        List<FolderDTO> childFolder2A = (List<FolderDTO>) childFolder1A.get(0).getElementList();
        Assert.assertEquals("#2A", 3, childFolder2A.size());
        Assert.assertEquals("2A", nameFolder2A, childFolder2A.get(0).getName());
        Assert.assertEquals("2B", nameFolder2B, childFolder2A.get(1).getName());
        Assert.assertEquals("2C", nameFolder2C, childFolder2A.get(2).getName());
        
        List<FolderDTO> childFolder3A = (List<FolderDTO>) childFolder2A.get(0).getElementList();
        Assert.assertEquals("#3A", 3, childFolder3A.size());
        Assert.assertEquals("3A", nameFolder3A, childFolder3A.get(0).getName());
        Assert.assertEquals("3B", nameFolder3B, childFolder3A.get(1).getName());
        Assert.assertEquals("3C", nameFolder3C, childFolder3A.get(2).getName());
    }
}
