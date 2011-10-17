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
import org.geosdi.geoplatform.responce.IElementDTO;
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
    private final String nameFolder2A = "folder2A";
    private final String nameFolder2B = "folder2B";
    private final String nameFolder2C = "folder2C";
    private final String nameFolder3A = "folder3A";
    private final String nameFolder3B = "folder3B";
    private final String nameFolder3C = "folder3C";
    //
    private GPFolder folder1A;
    private GPFolder folder1B;
    private GPFolder folder1C;
    private GPFolder folder2A;
    private GPFolder folder2B;
    private GPFolder folder2C;
    private GPFolder folder3A;
    private GPFolder folder3B;
    private GPFolder folder3C;
    private Long idFolder1A;
    private Long idFolder1B;
    private Long idFolder1C;
    private Long idFolder2A;
    private Long idFolder2B;
    private Long idFolder2C;
    private Long idFolder3A;
    private Long idFolder3B;
    private Long idFolder3C;
    //
    private String titleRaster = "T-raster-";
    private String nameRaster = "N-raster-";
    private String titleVector = "T-vector-";
    private String nameVector = "N-vector-";

    @Override
    public void setUp() throws Exception {
        super.setUp();

        super.createAndInsertRasterLayer(super.rootFolderA, titleRaster + super.nameRootFolderA,
                nameRaster + super.nameRootFolderA, "", 14, "", "");
        // "rootFolderA" ---> "folder1(A|B|C)"
        idFolder1A = super.createAndInsertFolder(nameFolder1A, projectTest, 13, rootFolderA, 3);
        folder1A = gpWSClient.getFolderDetail(idFolder1A);
        //
        idFolder1B = super.createAndInsertFolder(nameFolder1B, projectTest, 12, rootFolderA, 1);
        folder1B = gpWSClient.getFolderDetail(idFolder1B);
        //
        super.createAndInsertRasterLayer(folder1B, titleRaster + nameFolder1B,
                nameRaster + nameFolder1B, "", 11, "", "");
        //
        idFolder1C = super.createAndInsertFolder(nameFolder1C, projectTest, 10, rootFolderA);
        folder1C = gpWSClient.getFolderDetail(idFolder1C);
        //
        // "folder1A" ---> "folder2(A|B|C)"
        idFolder2A = super.createAndInsertFolder(nameFolder2A, projectTest, 9, folder1A, 3);
        folder2A = gpWSClient.getFolderDetail(idFolder2A);
        //
        idFolder2B = super.createAndInsertFolder(nameFolder2B, projectTest, 8, folder1A);
        folder2B = gpWSClient.getFolderDetail(idFolder2B);
        //
        idFolder2C = super.createAndInsertFolder(nameFolder2C, projectTest, 7, folder1A, 1);
        folder2C = gpWSClient.getFolderDetail(idFolder2C);
        //
        super.createAndInsertRasterLayer(folder2C, titleRaster + nameFolder2C,
                nameRaster + nameFolder2C, "", 6, "", "");
        //
        // "folder2A" ---> "folder3(A|B|C)"
        idFolder3A = super.createAndInsertFolder(nameFolder3A, projectTest, 5, folder2A, 1);
        folder3A = gpWSClient.getFolderDetail(idFolder3A);
        //
        super.createAndInsertVectorLayer(folder3A, titleVector + nameFolder3A,
                nameVector + nameFolder3A, "", 4, "", "");
        //
        idFolder3B = super.createAndInsertFolder(nameFolder3B, projectTest, 3, folder2A);
        folder3B = gpWSClient.getFolderDetail(idFolder3B);
        //
        idFolder3C = super.createAndInsertFolder(nameFolder3C, projectTest, 2, folder2A);
        folder3C = gpWSClient.getFolderDetail(idFolder3C);
        //        

        super.rootFolderA.setPosition(15);
        super.rootFolderA.setNumberOfDescendants(13);
        gpWSClient.updateFolder(rootFolderA);

        super.projectTest.setNumberOfElements(projectTest.getNumberOfElements() + 10);
        gpWSClient.updateProject(projectTest);
    }

    @Test
    public void testFixture() {
        Assert.assertNotNull("Folder1A is NULL", folder1A);
        Assert.assertEquals("ID of Folder1A is incorrect", folder1A.getId(), idFolder1A);
        Assert.assertNotNull("Folder1B is NULL", folder1B);
        Assert.assertEquals("ID of Folder1B is incorrect", folder1B.getId(), idFolder1B);
        Assert.assertNotNull("Folder1C is NULL", folder1C);
        Assert.assertEquals("ID of Folder1C is incorrect", folder1C.getId(), idFolder1C);

        Assert.assertNotNull("Folder2A is NULL", folder2A);
        Assert.assertEquals("ID of Folder2A is incorrect", folder2A.getId(), idFolder2A);
        Assert.assertNotNull("Folder2B is NULL", folder2B);
        Assert.assertEquals("ID of Folder2B is incorrect", folder2B.getId(), idFolder2B);
        Assert.assertNotNull("Folder2C is NULL", folder2C);
        Assert.assertEquals("ID of Folder2 is incorrect", folder2C.getId(), idFolder2C);

        Assert.assertNotNull("Folder3A is NULL", folder3A);
        Assert.assertEquals("ID of Folder3A is incorrect", folder3A.getId(), idFolder3A);
        Assert.assertNotNull("Folder3B is NULL", folder3B);
        Assert.assertEquals("ID of Folder3B is incorrect", folder3B.getId(), idFolder3B);
        Assert.assertNotNull("Folder3 is NULL", folder3C);
        Assert.assertEquals("ID of Folder3C is incorrect", folder3C.getId(), idFolder3C);
    }

    @Test
    public void testGetElement() throws ResourceNotFoundFault {
        ProjectDTO project = gpWSClient.getElements(super.idProjectTest);

        Assert.assertEquals(super.projectTest.getName(), project.getName());
        Assert.assertEquals(super.projectTest.getNumberOfElements(), project.getNumberOfElements());

        List<FolderDTO> rootFolders = project.getRootFolders();
        Assert.assertEquals("#root", 2, rootFolders.size());
        Assert.assertEquals("A", nameRootFolderA, rootFolders.get(0).getName());

        List<IElementDTO> childRootFolderA = rootFolders.get(0).getElementList();
        Assert.assertEquals("#A", 4, childRootFolderA.size());
        Assert.assertEquals("R-A", nameRaster + super.nameRootFolderA, childRootFolderA.get(0).getName());
        Assert.assertEquals("1A", nameFolder1A, childRootFolderA.get(1).getName());
        Assert.assertEquals("1B", nameFolder1B, childRootFolderA.get(2).getName());
        Assert.assertEquals("1C", nameFolder1C, childRootFolderA.get(3).getName());

        List<IElementDTO> childFolder1A = ((FolderDTO) childRootFolderA.get(1)).getElementList();
        Assert.assertEquals("#1A", 3, childFolder1A.size());
        Assert.assertEquals("2A", nameFolder2A, childFolder1A.get(0).getName());
        Assert.assertEquals("2B", nameFolder2B, childFolder1A.get(1).getName());
        Assert.assertEquals("2C", nameFolder2C, childFolder1A.get(2).getName());

        List<IElementDTO> childFolder2A = ((FolderDTO) childFolder1A.get(0)).getElementList();
        Assert.assertEquals("#2A", 3, childFolder2A.size());
        Assert.assertEquals("3A", nameFolder3A, childFolder2A.get(0).getName());
        Assert.assertEquals("3B", nameFolder3B, childFolder2A.get(1).getName());
        Assert.assertEquals("3C", nameFolder3C, childFolder2A.get(2).getName());

        FolderDTO f1B = (FolderDTO) childRootFolderA.get(2);
        Assert.assertEquals("#1B", 1, f1B.getElementList().size());
        Assert.assertEquals("R-1B", nameRaster + nameFolder1B, f1B.getElementList().get(0).getName());

        FolderDTO f2C = (FolderDTO) childFolder1A.get(2);
        Assert.assertEquals("#2C", 1, f2C.getElementList().size());
        Assert.assertEquals("R-2C", nameRaster + nameFolder2C, f2C.getElementList().get(0).getName());

        FolderDTO f3A = (FolderDTO) childFolder2A.get(0);
        Assert.assertEquals("#3A", 1, f3A.getElementList().size());
        Assert.assertEquals("V-3A", nameVector + nameFolder3A, f3A.getElementList().get(0).getName());
    }
}
