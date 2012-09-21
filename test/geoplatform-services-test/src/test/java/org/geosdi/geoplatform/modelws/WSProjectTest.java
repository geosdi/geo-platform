/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.modelws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.IElementDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.RasterLayerDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.VectorLayerDTO;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.acls.domain.BasePermission;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WSProjectTest extends ServiceTest {

    Map<String, Object> fixture = new HashMap<String, Object>();
    // Folder
    private static final String nameFolder1A = "folder1A";
    private static final String nameFolder1B = "folder1B";
    private static final String nameFolder1C = "folder1C";
    private static final String nameFolder2A = "folder2A";
    private static final String nameFolder2B = "folder2B";
    private static final String nameFolder2C = "folder2C";
    private static final String nameFolder3A = "folder3A";
    private static final String nameFolder3B = "folder3B";
    private static final String nameFolder3C = "folder3C";
    private GPFolder folder1A;
    private GPFolder folder1B;
    private GPFolder folder1C;
    private GPFolder folder2A;
    private GPFolder folder2B;
    private GPFolder folder2C;
    private GPFolder folder3A;
    private GPFolder folder3B;
    private GPFolder folder3C;
    // Layer
    private String titleRaster = "T-raster-";
    private String nameRaster = "N-raster-";
    private String titleVector = "T-vector-";
    private String nameVector = "N-vector-";
    GPRasterLayer rasterRootFolderA;
    GPRasterLayer rasterFolder1B;
    GPRasterLayer rasterFolder2C;
    GPVectorLayer vectorFolder3A;
    GPVectorLayer vectorRootFolderB;

    /**
     * Tree structure to test
     *
     * A -|
     *    + raster-A
     *    + 1A ---------|
     *                  + 2A -------|
     *                              + 3A -------|
     *                                          + vector-3A
     *                              + 3B
     *                              + 3C
     *                  + 2B
     *                  + 2C -------|
     *                              + raster-2C
     *
     *    + 1B ---------|
     *                  + raster-1B
     *    + 1C
     * B -|
     *    + vector-B
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();

        Long idRasterRootFolderA = super.createAndInsertRasterLayer(super.rootFolderA, titleRaster + nameRootFolderA,
                                                                    nameRaster + nameRootFolderA, "", 15, "", "");
        rasterRootFolderA = gpWSClient.getRasterLayer(idRasterRootFolderA);
        this.fixture.put(rasterRootFolderA.getName(), rasterRootFolderA);
        // "rootFolderA" ---> "folder1(A|B|C)"
        Long idFolder1A = super.createAndInsertFolder(nameFolder1A, projectTest, 14, rootFolderA, 8);
        folder1A = gpWSClient.getFolderDetail(idFolder1A);
        this.fixture.put(folder1A.getName(), folder1A);
        //
        Long idFolder1B = super.createAndInsertFolder(nameFolder1B, projectTest, 5, rootFolderA, 1);
        folder1B = gpWSClient.getFolderDetail(idFolder1B);
        this.fixture.put(folder1B.getName(), folder1B);
        //
        Long idRasterFolder1B = super.createAndInsertRasterLayer(folder1B, titleRaster + nameFolder1B,
                                                                 nameRaster + nameFolder1B, "", 4, "", "");
        rasterFolder1B = gpWSClient.getRasterLayer(idRasterFolder1B);
        this.fixture.put(rasterFolder1B.getName(), rasterFolder1B);
        //
        Long idFolder1C = super.createAndInsertFolder(nameFolder1C, projectTest, 3, rootFolderA);
        folder1C = gpWSClient.getFolderDetail(idFolder1C);
        this.fixture.put(folder1C.getName(), folder1C);
        //
        // "folder1A" ---> "folder2(A|B|C)"
        Long idFolder2A = super.createAndInsertFolder(nameFolder2A, projectTest, 13, folder1A, 4);
        folder2A = gpWSClient.getFolderDetail(idFolder2A);
        this.fixture.put(folder2A.getName(), folder2A);
        //
        Long idFolder2B = super.createAndInsertFolder(nameFolder2B, projectTest, 8, folder1A);
        folder2B = gpWSClient.getFolderDetail(idFolder2B);
        this.fixture.put(folder2B.getName(), folder2B);
        //
        Long idFolder2C = super.createAndInsertFolder(nameFolder2C, projectTest, 7, folder1A, 1);
        folder2C = gpWSClient.getFolderDetail(idFolder2C);
        this.fixture.put(folder2C.getName(), folder2C);
        //
        Long idRasterFolder2C = super.createAndInsertRasterLayer(folder2C, titleRaster + nameFolder2C,
                                                                 nameRaster + nameFolder2C, "", 6, "", "");
        rasterFolder2C = gpWSClient.getRasterLayer(idRasterFolder2C);
        this.fixture.put(rasterFolder2C.getName(), rasterFolder2C);
        //
        // "folder2A" ---> "folder3(A|B|C)"
        Long idFolder3A = super.createAndInsertFolder(nameFolder3A, projectTest, 12, folder2A, 1);
        folder3A = gpWSClient.getFolderDetail(idFolder3A);
        this.fixture.put(folder3A.getName(), folder3A);
        //
        Long idVectorFolder3A = super.createAndInsertVectorLayer(folder3A, titleVector + nameFolder3A,
                                                                 nameVector + nameFolder3A, "", 11, "", "");
        vectorFolder3A = gpWSClient.getVectorLayer(idVectorFolder3A);
        this.fixture.put(vectorFolder3A.getName(), vectorFolder3A);
        //
        Long idFolder3B = super.createAndInsertFolder(nameFolder3B, projectTest, 10, folder2A);
        folder3B = gpWSClient.getFolderDetail(idFolder3B);
        this.fixture.put(folder3B.getName(), folder3B);
        //
        Long idFolder3C = super.createAndInsertFolder(nameFolder3C, projectTest, 9, folder2A);
        folder3C = gpWSClient.getFolderDetail(idFolder3C);
        this.fixture.put(folder3C.getName(), folder3C);
        //        
        super.rootFolderA.setNumberOfDescendants(13);
        super.rootFolderA.setPosition(16);
        gpWSClient.updateFolder(super.rootFolderA);


        Long idVectorRootFolderB = super.createAndInsertVectorLayer(super.rootFolderB, titleVector + nameRootFolderB,
                                                                    nameVector + nameRootFolderB, "", 1, "", "");
        vectorRootFolderB = gpWSClient.getVectorLayer(idVectorRootFolderB);
        this.fixture.put(vectorRootFolderB.getName(), vectorRootFolderB);
        //
        super.rootFolderB.setNumberOfDescendants(1);
        super.rootFolderB.setPosition(2);
        gpWSClient.updateFolder(super.rootFolderB);

        super.projectTest.setNumberOfElements(projectTest.getNumberOfElements()
                + super.rootFolderA.getNumberOfDescendants() + super.rootFolderB.getNumberOfDescendants());
        gpWSClient.updateProject(projectTest);
    }

    @Test
    public void testFixtureNotNull() {
        for (Map.Entry<String, Object> entry : fixture.entrySet()) {
            Assert.assertNotNull(entry.getKey() + " is NULL", entry.getValue());
        }
    }

    @Test
    public void testExportProject() throws ResourceNotFoundFault {
        ProjectDTO project = gpWSClient.exportProject(super.idProjectTest);

        Assert.assertEquals("project name", super.projectTest.getName(), project.getName());
        Assert.assertEquals("project elements", super.projectTest.getNumberOfElements(), project.getNumberOfElements().intValue());

        List<FolderDTO> rootFolders = project.getRootFolders();
        Assert.assertEquals("#root", 2, rootFolders.size());
        Assert.assertEquals("A", nameRootFolderA, rootFolders.get(0).getName());
        Assert.assertEquals("B", nameRootFolderB, rootFolders.get(1).getName());

        List<IElementDTO> childRootFolderA = rootFolders.get(0).getElementList();
        Assert.assertEquals("#A", 4, childRootFolderA.size());
        Assert.assertEquals("R-A", nameRaster + nameRootFolderA, childRootFolderA.get(0).getName());
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

        List<IElementDTO> childRootFolderB = rootFolders.get(1).getElementList();
        Assert.assertEquals("#B", 1, childRootFolderB.size());
        Assert.assertEquals("V-B", nameVector + nameRootFolderB, childRootFolderB.get(0).getName());
    }

    @Test
    public void testOnlyFirstLevelFolder() throws ResourceNotFoundFault {
        gpWSClient.deleteFolder(super.idRootFolderA);

        ProjectDTO project = gpWSClient.exportProject(super.idProjectTest);
        Assert.assertEquals("project name", super.projectTest.getName(), project.getName());

        List<FolderDTO> rootFolders = project.getRootFolders();
        Assert.assertEquals("#root", 1, rootFolders.size());
        Assert.assertEquals("B", nameRootFolderB, rootFolders.get(0).getName());

        List<IElementDTO> childRootFolderB = rootFolders.get(0).getElementList();
        Assert.assertEquals("#B", 1, childRootFolderB.size());
        Assert.assertEquals("V-B", nameVector + nameRootFolderB, childRootFolderB.get(0).getName());
    }

    @Test
    public void testImportProject() throws IllegalParameterFault, ResourceNotFoundFault {
        // Create ProjectDTO to import
        ProjectDTO projectDTO = new ProjectDTO(super.projectTest);

        List<GPFolder> rootFolders = Arrays.asList(super.rootFolderA, super.rootFolderB);
        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(rootFolders);
        projectDTO.setRootFolders(rootFoldersDTO);

        FolderDTO rootFolderADTO = rootFoldersDTO.get(0);
        rootFolderADTO.addLayer(new RasterLayerDTO(rasterRootFolderA));
        List<FolderDTO> childRootFolderA = FolderDTO.convertToFolderDTOList(Arrays.asList(folder1A, folder1B, folder1C));
        rootFolderADTO.addFolders(childRootFolderA);

        List<FolderDTO> childFolder1A = FolderDTO.convertToFolderDTOList(Arrays.asList(folder2A, folder2B, folder2C));
        childRootFolderA.get(0).addFolders(childFolder1A);
        childRootFolderA.get(1).addLayer(new RasterLayerDTO(rasterFolder1B));

        List<FolderDTO> childFolder2A = FolderDTO.convertToFolderDTOList(Arrays.asList(folder3A, folder3B, folder3C));
        childFolder1A.get(0).addFolders(childFolder2A);
        childFolder1A.get(2).addLayer(new RasterLayerDTO(rasterFolder2C));

        childFolder2A.get(0).addLayer(new VectorLayerDTO(vectorFolder3A));

        FolderDTO rootFolderBDTO = rootFoldersDTO.get(1);
        rootFolderBDTO.addLayer(new VectorLayerDTO(vectorRootFolderB));

        projectDTO.setId(null); // Entity passed must not containd an ID, otherwise Hibernate throws PersistentObjectException
        // Import ProjectDTO
        Long projectID = gpWSClient.importProject(projectDTO, super.idUserTest);
        // Check imported Project
        Assert.assertTrue("Check importProject", projectID > 0);
        logger.debug("*** ID project imported: {} ***", projectID);

        GPProject projectAdded = gpWSClient.getProjectDetail(projectID);
        Assert.assertEquals("project name", super.projectTest.getName(), projectAdded.getName());
        Assert.assertEquals("project elements", super.projectTest.getNumberOfElements(), projectAdded.getNumberOfElements());

        rootFoldersDTO = gpWSClient.getRootFoldersByProjectID(projectID);
        Assert.assertNotNull("rootFolders null", rootFoldersDTO);
        Assert.assertEquals("#root", 2, rootFoldersDTO.size());
        rootFolderADTO = rootFoldersDTO.get(0);
        rootFolderBDTO = rootFoldersDTO.get(1);
        this.assertFolder("A", super.rootFolderA, rootFolderADTO);
        this.assertFolder("B", super.rootFolderB, rootFolderBDTO);

        TreeFolderElements elemRootFolderA = gpWSClient.getChildrenElements(rootFolderADTO.getId());
        Assert.assertNotNull("elem-A null", elemRootFolderA);
        Assert.assertEquals("#A", 4, elemRootFolderA.size());
        IElementDTO rasterRootFolderADTO = elemRootFolderA.pollFirst();
        FolderDTO folder1ADTO = (FolderDTO) elemRootFolderA.pollFirst();
        FolderDTO folder1BDTO = (FolderDTO) elemRootFolderA.pollFirst();
        FolderDTO folder1CDTO = (FolderDTO) elemRootFolderA.pollFirst();
        this.assertLayer("R-A", rasterRootFolderA, rasterRootFolderADTO);
        this.assertFolder("1A", folder1A, folder1ADTO);
        this.assertFolder("1B", folder1B, folder1BDTO);
        this.assertFolder("1C", folder1C, folder1CDTO);

        TreeFolderElements elemFolder1A = gpWSClient.getChildrenElements(folder1ADTO.getId());
        Assert.assertNotNull("elem-1A null", elemFolder1A);
        Assert.assertEquals("#1A", 3, elemFolder1A.size());
        FolderDTO folder2ADTO = (FolderDTO) elemFolder1A.pollFirst();
        FolderDTO folder2BDTO = (FolderDTO) elemFolder1A.pollFirst();
        FolderDTO folder2CDTO = (FolderDTO) elemFolder1A.pollFirst();
        this.assertFolder("2A", folder2A, folder2ADTO);
        this.assertFolder("2B", folder2B, folder2BDTO);
        this.assertFolder("2C", folder2C, folder2CDTO);

        TreeFolderElements elemFolder2A = gpWSClient.getChildrenElements(folder2ADTO.getId());
        Assert.assertNotNull("elem-2A null", elemFolder2A);
        Assert.assertEquals("#2A", 3, elemFolder2A.size());
        FolderDTO folder3ADTO = (FolderDTO) elemFolder2A.pollFirst();
        FolderDTO folder3BDTO = (FolderDTO) elemFolder2A.pollFirst();
        FolderDTO folder3CDTO = (FolderDTO) elemFolder2A.pollFirst();
        this.assertFolder("3A", folder3A, folder3ADTO);
        this.assertFolder("3B", folder3B, folder3BDTO);
        this.assertFolder("3C", folder3C, folder3CDTO);

        TreeFolderElements elemFolder3A = gpWSClient.getChildrenElements(folder3ADTO.getId());
        Assert.assertNotNull("elem-3A null", elemFolder3A);
        Assert.assertEquals("#3A", 1, elemFolder3A.size());
        IElementDTO vectorFolder3ADTO = elemFolder3A.pollFirst();
        this.assertLayer("V-3A", vectorFolder3A, vectorFolder3ADTO);

        TreeFolderElements elemFolder2C = gpWSClient.getChildrenElements(folder2CDTO.getId());
        Assert.assertNotNull("elem-2C null", elemFolder2C);
        Assert.assertEquals("#2C", 1, elemFolder2C.size());
        IElementDTO rasterFolder2CDTO = elemFolder2C.pollFirst();
        this.assertLayer("R-2C", rasterFolder2C, rasterFolder2CDTO);

        TreeFolderElements elemFolder1B = gpWSClient.getChildrenElements(folder1BDTO.getId());
        Assert.assertNotNull("elem-1B null", elemFolder1B);
        Assert.assertEquals("#1B", 1, elemFolder1B.size());
        IElementDTO rasterFolder1BDTO = elemFolder1B.pollFirst();
        this.assertLayer("R-1B", rasterFolder1B, rasterFolder1BDTO);

        TreeFolderElements elemRootFolderB = gpWSClient.getChildrenElements(rootFolderBDTO.getId());
        Assert.assertNotNull("elem-B null", elemRootFolderB);
        Assert.assertEquals("#B", 1, elemRootFolderB.size());
        IElementDTO vectorRootFolderBDTO = elemRootFolderB.pollFirst();
        this.assertLayer("V-B", vectorRootFolderB, vectorRootFolderBDTO);
    }

    private void assertLayer(String msg, GPLayer layer, IElementDTO layerToCheck) {
        Assert.assertEquals(msg, layer.getName(), layerToCheck.getName());
        Assert.assertEquals("Position-" + msg, layer.getPosition(), layerToCheck.getPosition().intValue());
    }

    private void assertFolder(String msg, GPFolder folder, FolderDTO folderToCheck) {
        Assert.assertEquals(msg, folder.getName(), folderToCheck.getName());
        Assert.assertEquals("Position-" + msg, folder.getPosition(), folderToCheck.getPosition().intValue());
        Assert.assertEquals("Descendats-" + msg, folder.getNumberOfDescendants(), folderToCheck.getNumberOfDescendants().intValue());
    }

    @Test
    public void testExpandedElementsProject() throws Exception {
        super.rootFolderA.setExpanded(true);
        super.rootFolderB.setExpanded(true);
        folder1A.setExpanded(true);
        folder2C.setExpanded(true);

        gpWSClient.updateFolder(super.rootFolderA);
        gpWSClient.updateFolder(super.rootFolderB);
        gpWSClient.updateFolder(folder1A);
        gpWSClient.updateFolder(folder2C);

        ProjectDTO project = gpWSClient.getExpandedElementsByProjectID(super.idProjectTest);

        Assert.assertEquals("project name", super.projectTest.getName(), project.getName());
        Assert.assertEquals("project elements", super.projectTest.getNumberOfElements(), project.getNumberOfElements().intValue());

        List<FolderDTO> rootFolders = project.getRootFolders();
        Assert.assertEquals("#root", 2, rootFolders.size());
        Assert.assertEquals("A", nameRootFolderA, rootFolders.get(0).getName());
        Assert.assertEquals("B", nameRootFolderB, rootFolders.get(1).getName());

        List<IElementDTO> childRootFolderA = rootFolders.get(0).getElementList();
        Assert.assertEquals("#A", 4, childRootFolderA.size());
        Assert.assertEquals("R-A", nameRaster + nameRootFolderA, childRootFolderA.get(0).getName());
        Assert.assertEquals("1A", nameFolder1A, childRootFolderA.get(1).getName());
        Assert.assertEquals("1B", nameFolder1B, childRootFolderA.get(2).getName());
        Assert.assertEquals("1C", nameFolder1C, childRootFolderA.get(3).getName());

        List<IElementDTO> childFolder1A = ((FolderDTO) childRootFolderA.get(1)).getElementList();
        Assert.assertEquals("#1A", 3, childFolder1A.size());
        Assert.assertEquals("2A", nameFolder2A, childFolder1A.get(0).getName());
        Assert.assertEquals("2B", nameFolder2B, childFolder1A.get(1).getName());
        Assert.assertEquals("2C", nameFolder2C, childFolder1A.get(2).getName());

        List<IElementDTO> childFolder2A = ((FolderDTO) childFolder1A.get(0)).getElementList();
        Assert.assertEquals("#2A", 0, childFolder2A.size()); // NO elements

        FolderDTO f1B = (FolderDTO) childRootFolderA.get(2);
        Assert.assertEquals("#1B", 0, f1B.getElementList().size()); // NO elements

        FolderDTO f2C = (FolderDTO) childFolder1A.get(2);
        Assert.assertEquals("#2C", 1, f2C.getElementList().size());
        Assert.assertEquals("R-2C", nameRaster + nameFolder2C, f2C.getElementList().get(0).getName());

        List<IElementDTO> childRootFolderB = rootFolders.get(1).getElementList();
        Assert.assertEquals("#B", 1, childRootFolderB.size());
        Assert.assertEquals("V-B", nameVector + nameRootFolderB, childRootFolderB.get(0).getName());
    }

    @Test
    public void testAccountsBySharedProjectID() throws Exception {
        // Set shared the Project test
        projectTest.setShared(true);
        projectTest.setName("shared_project_test_ws");
        gpWSClient.updateProject(projectTest);

        // Initial test
        List<ShortAccountDTO> accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(1, accountsToShare.size());
        Assert.assertEquals(idUserTest, accountsToShare.get(0).getId().longValue());

        // Insert Users to which the Project is shared
        Long firstUserID = this.createAndInsertUser("first_to_share_project", organizationTest, GPRole.USER);
        Long latterUserID = this.createAndInsertUser("latter_to_share_project", organizationTest, GPRole.VIEWER);

        GPUser firstUser = gpWSClient.getUserDetail(firstUserID);
        GPUser latterUser = gpWSClient.getUserDetail(latterUserID);

        // Insert the Users as viewers of Project
        this.createAndInsertAccountProject(firstUser, projectTest, BasePermission.READ);
        this.createAndInsertAccountProject(latterUser, projectTest, BasePermission.READ);

        // Final test
        accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(3, accountsToShare.size());
    }

    @Test
    public void testAccountsToShareByProjectID() throws Exception {
        // Set shared the Project test
        projectTest.setShared(true);
        projectTest.setName("shared_project_to_share_test_ws");
        gpWSClient.updateProject(projectTest);

        // Initial test
        List<ShortAccountDTO> accountsToShare = gpWSClient.getAccountsToShareByProjectID(idProjectTest);
        Assert.assertNull(accountsToShare);

        // Insert a User to which the Project is shared as viewer
        Long newUserID = this.createAndInsertUser("user_to_share_project", organizationTest, GPRole.USER);
        GPUser newUser = gpWSClient.getUserDetail(newUserID);
        this.createAndInsertAccountProject(newUser, projectTest, BasePermission.READ);

        // Insert Users to which it possible to share the Project
        this.createAndInsertUser("first_possible_to_share_project", organizationTest, GPRole.USER);
        this.createAndInsertUser("latter_possible_to_share_project", organizationTest, GPRole.VIEWER);

        // Final test
        accountsToShare = gpWSClient.getAccountsToShareByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(2, accountsToShare.size());
    }

    @Test
    public void testProjectOwner() throws Exception {
        // Set shared the Project test
        projectTest.setShared(true);
        projectTest.setName("shared_project_owner_test_ws");
        gpWSClient.updateProject(projectTest);

        // Insert a User to which the Project is shared as viewer
        Long newOwnerID = this.createAndInsertUser("user_to_share_project", organizationTest, GPRole.USER);
        GPUser newOwner = gpWSClient.getUserDetail(newOwnerID);
        this.createAndInsertAccountProject(newOwner, projectTest, BasePermission.READ);

        // Initial test
        GPAccount owner = gpWSClient.getProjectOwner(idProjectTest);
        Assert.assertNotNull(owner);
        Assert.assertEquals(userTest, owner);

        // Change the Account owner
        RequestByAccountProjectIDs request = new RequestByAccountProjectIDs(newOwnerID, idProjectTest);
        boolean result = gpWSClient.setProjectOwner(request);
        Assert.assertTrue(result);

        // Final test
        owner = gpWSClient.getProjectOwner(idProjectTest);
        Assert.assertNotNull(owner);
        Assert.assertEquals(newOwnerID, owner.getId());
    }

    @Test
    public void testProjectNewOwner() throws Exception {
        // Initial test
        GPAccount owner = gpWSClient.getProjectOwner(idProjectTest);
        Assert.assertNotNull(owner);
        Assert.assertEquals(userTest, owner);

        // Change the Account owner
        Long newOwnerID = this.createAndInsertUser("new_owner", organizationTest, GPRole.ADMIN);

        RequestByAccountProjectIDs request = new RequestByAccountProjectIDs(newOwnerID, idProjectTest);
        boolean result = gpWSClient.setProjectOwner(request);
        Assert.assertTrue(result);

        // Final test
        owner = gpWSClient.getProjectOwner(idProjectTest);
        Assert.assertNotNull(owner);
        Assert.assertEquals(newOwnerID, owner.getId());
    }

    @Test
    public void testUpdateAccountsProjectSharingCreate() throws Exception {
        // Initial test
        GPProject project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertFalse(project.isShared());

        List<ShortAccountDTO> accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(1, accountsToShare.size());
        Assert.assertEquals(idUserTest, accountsToShare.get(0).getId().longValue());

        // Insert User to which the Project will be share
        Long newUserID = this.createAndInsertUser("user_to_share_project", organizationTest, GPRole.USER);

        // Test add user for sharing
        boolean result = gpWSClient.updateAccountsProjectSharing(idProjectTest, Arrays.asList(newUserID));
        Assert.assertTrue(result);

        project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertTrue(project.isShared());

        accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(2, accountsToShare.size());
        boolean check = false;
        for (ShortAccountDTO accountDTO : accountsToShare) {
            if (newUserID.equals(accountDTO.getId())) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    @Test
    public void testUpdateAccountsProjectSharingRemoveAll() throws Exception {
        // Insert a User to which the Project is shared as viewer
        Long newUserID = this.createAndInsertUser("user_to_share_project", organizationTest, GPRole.USER);
        GPUser newUser = gpWSClient.getUserDetail(newUserID);
        this.createAndInsertAccountProject(newUser, projectTest, BasePermission.READ);

        // Set the Project as share
        projectTest.setShared(true);
        gpWSClient.updateProject(projectTest);

        // Initial test
        GPProject project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertTrue(project.isShared());

        List<ShortAccountDTO> accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(2, accountsToShare.size());
        Assert.assertEquals(2, accountsToShare.size());
        boolean check = false;
        for (ShortAccountDTO accountDTO : accountsToShare) {
            if (newUserID.equals(accountDTO.getId())) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);

        // Test delete user for sharing
        boolean result = gpWSClient.updateAccountsProjectSharing(idProjectTest, new ArrayList<Long>(0));
        Assert.assertTrue(result);

        project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertFalse(project.isShared());

        accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(1, accountsToShare.size());
        Assert.assertEquals(idUserTest, accountsToShare.get(0).getId().longValue());
    }

    @Test
    public void testUpdateAccountsProjectSharingManage() throws Exception {
        // Insert a User to which the Project is shared as viewer
        Long firstUserID = this.createAndInsertUser("first_to_share_project", organizationTest, GPRole.USER);
        Long latterUserID = this.createAndInsertUser("latter_to_share_project", organizationTest, GPRole.VIEWER);
        GPUser newUser = gpWSClient.getUserDetail(firstUserID);
        this.createAndInsertAccountProject(newUser, projectTest, BasePermission.READ);

        // Set the Project as share
        projectTest.setShared(true);
        gpWSClient.updateProject(projectTest);

        // Initial test
        GPProject project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertTrue(project.isShared());

        List<ShortAccountDTO> accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(2, accountsToShare.size());
        Assert.assertEquals(2, accountsToShare.size());
        boolean checkFirst = false;
        for (ShortAccountDTO accountDTO : accountsToShare) {
            if (firstUserID.equals(accountDTO.getId())) {
                checkFirst = true;
                break;
            }
        }
        Assert.assertTrue(checkFirst);

        // Test add latter user for sharing
        boolean result = gpWSClient.updateAccountsProjectSharing(idProjectTest,
                                                                 Arrays.asList(firstUserID, latterUserID));
        Assert.assertTrue(result);

        project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertTrue(project.isShared());

        accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(3, accountsToShare.size());
        checkFirst = false;
        boolean checkLatter = false;
        for (ShortAccountDTO accountDTO : accountsToShare) {
            if (firstUserID.equals(accountDTO.getId())) {
                checkFirst = true;
            }
            if (latterUserID.equals(accountDTO.getId())) {
                checkLatter = true;
            }
        }
        Assert.assertTrue(checkFirst);
        Assert.assertTrue(checkLatter);

        // Test delete first user for sharing
        result = gpWSClient.updateAccountsProjectSharing(idProjectTest,
                                                         Arrays.asList(latterUserID));
        Assert.assertTrue(result);

        project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertTrue(project.isShared());

        accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(2, accountsToShare.size());
        checkLatter = false;
        for (ShortAccountDTO accountDTO : accountsToShare) {
            if (latterUserID.equals(accountDTO.getId())) {
                checkLatter = true;
                break;
            }
        }
        Assert.assertTrue(checkLatter);
    }

    @Test
    public void testUpdateAccountsProjectSharingOwner() throws Exception {
        // Initial test
        GPProject project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertFalse(project.isShared());

        List<ShortAccountDTO> accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(1, accountsToShare.size());
        Assert.assertEquals(idUserTest, accountsToShare.get(0).getId().longValue());

        // Test pass owner
        boolean result = gpWSClient.updateAccountsProjectSharing(idProjectTest, Arrays.asList(idUserTest));
        Assert.assertTrue(result);

        project = gpWSClient.getProjectDetail(idProjectTest);
        Assert.assertFalse(project.isShared());

        accountsToShare = gpWSClient.getAccountsByProjectID(idProjectTest);
        Assert.assertNotNull(accountsToShare);
        Assert.assertEquals(1, accountsToShare.size());
        Assert.assertEquals(idUserTest, accountsToShare.get(0).getId().longValue());
    }
}
