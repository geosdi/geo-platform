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
package org.geosdi.geoplatform.core.delegate.impl.folder;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.delegate.api.folder.FolderDelegate;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.request.folder.InsertFolderRequest;
import org.geosdi.geoplatform.request.folder.WSAddFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.request.folder.WSDeleteFolderAndTreeModifications;
import org.geosdi.geoplatform.response.FolderDTO;
import org.geosdi.geoplatform.response.collection.ChildrenFolderStore;
import org.geosdi.geoplatform.response.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.response.collection.TreeFolderElementsStore;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.geosdi.geoplatform.response.FolderDTO.convertToFolderDTOList;

/**
 * Folder service delegate.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "gpFolderDelegate")
public class GPFolderDelegate implements FolderDelegate {

    private static final Logger logger = LoggerFactory.getLogger(
            GPFolderDelegate.class);
    //
    @Autowired
    private GPFolderDAO folderDao;
    @Autowired
    private GPLayerDAO layerDao;
    @Autowired
    private GPProjectDAO projectDao;

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // =========================================================================
    // === Folder
    // =========================================================================
    @Override
    public Long insertFolder(InsertFolderRequest insertFolderRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        Long projectID = insertFolderRequest.getProjectID();
        GPFolder folder = insertFolderRequest.getFolder();

        if (projectID == null) {
            throw new IllegalParameterFault("The projectID must not be null.");
        }

        if (folder == null) {
            throw new IllegalParameterFault(
                    "The GPFolder passed in Request must "
                            + "not be null.");
        }

        GPProject project = projectDao.find(projectID);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectID);
        }
        EntityCorrectness.checkProjectLog(project); // TODO assert

        folder.setProject(project);
        EntityCorrectness.checkFolder(folder); // TODO assert

        folderDao.persist(folder);
        return folder.getId();
    }

    @Override
    public Long updateFolder(GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder orig = folderDao.find(folder.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Folder not found", folder.getId());
        }
        EntityCorrectness.checkFolderLog(orig); // TODO assert

        // Update all properties (except the project)
        orig.setName(folder.getName());
        orig.setPosition(folder.getPosition());
        orig.setNumberOfDescendants(folder.getNumberOfDescendants());
        orig.setChecked(folder.isChecked());
        orig.setExpanded(folder.isExpanded());
        orig.setShared(folder.isShared());
        orig.setParent(folder.getParent());
        EntityCorrectness.checkFolder(orig); // TODO assert
        folderDao.update(orig);
        return orig.getId();
    }

    @Override
    public Boolean deleteFolder(Long folderID) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        return folderDao.removeById(folder.getId());
    }

    @Override
    public Long saveFolderProperties(Long folderID, String name,
            boolean checked, boolean expanded)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        if (name == null || name.trim().length() == 0) {
            throw new IllegalParameterFault(
                    "Folder \"name\" cannot be null or empty");
        }
        folder.setName(name);
        folder.setChecked(checked);
        folder.setExpanded(expanded);
        folderDao.update(folder);
        return folder.getId();
    }

    @Override
    public Long saveAddedFolderAndTreeModifications(
            WSAddFolderAndTreeModificationsRequest sftModificationRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        Long projectId = sftModificationRequest.getProjectID();
        Long parentId = sftModificationRequest.getParentID();
        GPFolder folder = sftModificationRequest.getFolder();
        GPWebServiceMapData descendantsMapData = sftModificationRequest.getDescendantsMapData();

        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SERVER SIDE @@@@@@@@@@@@@@ {}",
                descendantsMapData);

        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }
        EntityCorrectness.checkProjectLog(project); // TODO assert
        folder.setProject(project);

        if (parentId != null) {
            if (descendantsMapData.getDescendantsMap().isEmpty()) { // TODO assert
                throw new IllegalParameterFault(
                        "descendantsMapData must have one or more entries if the folder has a parent");
            }

            GPFolder parentFolder = folderDao.find(parentId);
            if (parentFolder == null) {
                throw new ResourceNotFoundFault("Folder parent not found",
                        parentId);
            }
            EntityCorrectness.checkFolderLog(parentFolder); // TODO assert
            folder.setParent(parentFolder);
        }
        EntityCorrectness.checkFolder(folder); // TODO assert

        int newPosition = folder.getPosition();
        int increment = 1;
        // Shift positions
        folderDao.updatePositionsLowerBound(projectId, newPosition, increment);
        layerDao.updatePositionsLowerBound(projectId, newPosition, increment);

        folderDao.persist(folder);

        folderDao.updateAncestorsDescendants(
                descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, increment);

        return folder.getId();
    }

    @Override
    public Boolean saveDeletedFolderAndTreeModifications(
            WSDeleteFolderAndTreeModifications sdfModificationRequest)
            throws ResourceNotFoundFault {
        Long folderID = sdfModificationRequest.getFolderID();
        GPWebServiceMapData descendantsMapData = sdfModificationRequest.getDescendantsMapData();

        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        int oldPosition = folder.getPosition();
        int decrement = -(folder.getNumberOfDescendants() + 1);

        boolean result = folderDao.removeById(folder.getId());

        GPProject project = folder.getProject();
        // Shift positions (shift must be done only after removing folder)
        folderDao.updatePositionsLowerBound(project.getId(), oldPosition,
                decrement);
        layerDao.updatePositionsLowerBound(project.getId(), oldPosition,
                decrement);

        folderDao.updateAncestorsDescendants(
                descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, decrement);

        return result;
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(Long folderID,
            boolean checked)
            throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        return folderDao.persistCheckStatusFolder(folderID, checked);
    }

    @Override
    public Boolean saveDragAndDropFolderAndTreeModifications(
            WSDDFolderAndTreeModifications sddfTreeModificationRequest)
            throws ResourceNotFoundFault {
        Long folderMovedID = sddfTreeModificationRequest.getFolderMovedID();
        Long newParentID = sddfTreeModificationRequest.getNewParentID();
        int newPosition = sddfTreeModificationRequest.getNewPosition();
        GPWebServiceMapData descendantsMapData = sddfTreeModificationRequest.getDescendantsMapData();

        GPFolder folderMoved = folderDao.find(folderMovedID);
        if (folderMoved == null) {
            throw new ResourceNotFoundFault("Folder not found", folderMovedID);
        }
//        assert (folderMoved.getPosition() != newPosition) : "New Position must be NOT equal to Old Position";
        EntityCorrectness.checkFolderLog(folderMoved); // TODO assert

        if (newParentID != null) {
            logger.trace("*** Folder will have a Parent");
            GPFolder folderParent = folderDao.find(newParentID);
            if (folderParent == null) {
                throw new ResourceNotFoundFault("New Parent not found",
                        newParentID);
            }
            EntityCorrectness.checkFolderLog(folderParent); // TODO assert
            folderMoved.setParent(folderParent);
        } else {
            logger.trace("*** Folder will be a root folder");
            folderMoved.setParent(null);
        }

        int startFirstRange = 0, endFirstRange = 0;
        if (folderMoved.getPosition() < newPosition) {
            logger.trace("*** Drag & Drop to top");
            startFirstRange = newPosition;
            endFirstRange = folderMoved.getPosition() + 1;
        } else if (folderMoved.getPosition() > newPosition) {
            logger.trace("*** Drag & Drop to bottom");
            startFirstRange = folderMoved.getPosition() - folderMoved.getNumberOfDescendants() - 1;
            endFirstRange = newPosition - folderMoved.getNumberOfDescendants();
        }
        int startSecondRange = folderMoved.getPosition();
        int endSecondRange = folderMoved.getPosition() - folderMoved.getNumberOfDescendants();
        int shiftValue = folderMoved.getNumberOfDescendants() + 1;

        List<GPFolder> matchingFoldersFirstRange = folderDao.findByPositionAndProjectID(folderMoved.getProject().getId(),
                startFirstRange, endFirstRange);
        List<GPLayer> matchingLayersFirstRange = layerDao.findByPositionAndProjectID(folderMoved.getProject().getId(),
                startFirstRange, endFirstRange);

        List<GPFolder> matchingFoldersSecondRange = folderDao.findByPositionAndProjectID(folderMoved.getProject().getId(),
                startSecondRange, endSecondRange);
        List<GPLayer> matchingLayersSecondRange = layerDao.findByPositionAndProjectID(folderMoved.getProject().getId(),
                startSecondRange, endSecondRange);

        logger.trace("Range: " + startFirstRange + " - " + endFirstRange + " - "
                + startSecondRange + " - " + endSecondRange + " - ");

        int moveValue = matchingFoldersFirstRange.size() + matchingLayersFirstRange.size();

        if (folderMoved.getPosition() < newPosition) {// Drag & Drop to top
            this.executeFoldersModifications(matchingFoldersFirstRange,
                    -shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange,
                    -shiftValue);
            this.executeFoldersModifications(matchingFoldersSecondRange,
                    moveValue);
            this.executeLayersModifications(matchingLayersSecondRange, moveValue);
        } else if (folderMoved.getPosition() > newPosition) {// Drag & Drop to bottom
            this.executeFoldersModifications(matchingFoldersFirstRange,
                    shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange, shiftValue);
            this.executeFoldersModifications(matchingFoldersSecondRange,
                    -moveValue);
            this.executeLayersModifications(matchingLayersSecondRange,
                    -moveValue);
        }
        folderDao.update(matchingFoldersFirstRange);
        folderDao.update(matchingFoldersSecondRange);
        layerDao.update(matchingLayersFirstRange);
        layerDao.update(matchingLayersSecondRange);
        folderMoved.setPosition(newPosition);
        folderDao.update(folderMoved);
        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        return Boolean.TRUE;
    }

    @Override
    public FolderDTO getShortFolder(Long folderID) throws ResourceNotFoundFault {
        return new FolderDTO(this.getFolderDetail(folderID));
    }

    @Override
    public GPFolder getFolderDetail(Long folderID) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert
        return folder;
    }

    @Override
    public List<FolderDTO> searchFolders(PaginatedSearchRequest searchRequest) {
        List<GPFolder> foundFolder = folderDao.searchPagebleFolders(searchRequest.getPage(), searchRequest.getNum(),
                searchRequest.getNameLike());
        return convertToFolderDTOList(foundFolder);
    }

    @Override
    public List<FolderDTO> getFolders() {
        List<GPFolder> found = folderDao.findAll();
        return convertToFolderDTOList(found);
    }

    @Override
    public long getFoldersCount(SearchRequest searchRequest) {
        return folderDao.countFolders(searchRequest.getNameLike()).longValue();
    }

    @Override
    public List<FolderDTO> getChildrenFoldersByRequest(RequestByID request) {
        List<GPFolder> foundFolder = folderDao.searchPagebleFoldersByParentID(request.getPage(), request.getNum(),
                request.getId());
        return convertToFolderDTOList(foundFolder);
    }

    @Override
    public ChildrenFolderStore getChildrenFolders(Long folderID) {
        List<GPFolder> foundFolder = folderDao.searchFoldersByParentID(folderID);
        return new ChildrenFolderStore(convertToFolderDTOList(
                foundFolder));
    }

    @Override
    public TreeFolderElementsStore getChildrenElements(Long folderID) {
        TreeFolderElementsStore tree = new TreeFolderElementsStore();
        List<GPFolder> foundFolder = folderDao.searchFoldersByParentID(folderID);
        tree.addFolderCollection(convertToFolderDTOList(foundFolder));
        List<GPLayer> foundLayer = layerDao.searchLayersByFolderID(folderID);
        tree.addLayerCollection(foundLayer);
        return tree;
    }
    //</editor-fold>

    /**
     * **************************************************************************
     */
    private void executeLayersModifications(List<GPLayer> elements, int value) {
        for (GPLayer layer : elements) {
            layer.setPosition(layer.getPosition() + value);
        }
    }

    private void executeFoldersModifications(List<GPFolder> elements, int value) {
        for (GPFolder folder : elements) {
            EntityCorrectness.checkFolderLog(folder); // TODO assert
            folder.setPosition(folder.getPosition() + value);
        }
    }

    private void updateNumberOfElements(GPProject project, int delta) {
        project.deltaToNumberOfElements(delta);
        projectDao.update(project);
    }
}
