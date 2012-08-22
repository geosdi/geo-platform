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
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Search;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author giuseppe
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
class FolderServiceImpl {
    
    final private static Logger logger = LoggerFactory.getLogger(FolderServiceImpl.class);
    // DAO
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
    private GPProjectDAO projectDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param folderDao the folderDao to set
     */
    public void setFolderDao(GPFolderDAO folderDao) {
        this.folderDao = folderDao;
    }

    /**
     * @param layerDao the layerDao to set
     */
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
    }

    /**
     * @param projectDao the projectDao to set
     */
    public void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    @Deprecated
    public Long insertFolder(Long projectId, GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        
        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }
        EntityCorrectness.checkProjectLog(project); // TODO assert

        folder.setProject(project);
        EntityCorrectness.checkFolder(folder); // TODO assert

        folderDao.persist(folder);
        return folder.getId();
    }
    
    @Deprecated
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

        folderDao.merge(orig);
        return orig.getId();
    }
    
    @Deprecated
    public boolean deleteFolder(Long folderID) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        return folderDao.remove(folder);
    }
    
    public Long saveFolderProperties(Long folderID, String name, boolean checked,
            boolean expanded)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        if (name == null || name.trim().length() == 0) {
            throw new IllegalParameterFault("Folder \"name\" cannot be null or empty");
        }
        
        folder.setName(name);
        folder.setChecked(checked);
        folder.setExpanded(expanded);
        
        folderDao.merge(folder);
        return folder.getId();
    }
    
    public Long saveAddedFolderAndTreeModifications(Long projectId, Long parentId,
            GPFolder folder, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }
        EntityCorrectness.checkProjectLog(project); // TODO assert
        folder.setProject(project);
        
        if (parentId != null) {
            if (descendantsMapData.getDescendantsMap().isEmpty()) { // TODO assert
                throw new IllegalParameterFault("descendantsMapData must have one or more entries if the folder has a parent");
            }
            
            GPFolder parentFolder = folderDao.find(parentId);
            if (parentFolder == null) {
                throw new ResourceNotFoundFault("Folder parent not found", parentId);
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
        
        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, increment);
        
        return folder.getId();
    }
    
    public boolean saveDeletedFolderAndTreeModifications(Long folderID, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        int oldPosition = folder.getPosition();
        int decrement = -(folder.getNumberOfDescendants() + 1);
        
        boolean result = folderDao.remove(folder);
        
        GPProject project = folder.getProject();
        // Shift positions (shift must be done only after removing folder)
        folderDao.updatePositionsLowerBound(project.getId(), oldPosition, decrement);
        layerDao.updatePositionsLowerBound(project.getId(), oldPosition, decrement);
        
        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, decrement);
        
        return result;
    }
    
    public boolean saveCheckStatusFolderAndTreeModifications(Long folderID, boolean checked)
            throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        return folderDao.persistCheckStatusFolder(folderID, checked);
    }

    /**
     * @param idFolderMoved
     * @param idNewParent: set conventionally 0 if idFolderMoved is refer to a
     * folder of root
     * @param newPosition
     * @param descendantsMapData
     * @return
     * @throws ResourceNotFoundFault
     */
    public boolean saveDragAndDropFolderModifications(Long idFolderMoved, Long idNewParent,
            int newPosition, GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        GPFolder folderMoved = folderDao.find(idFolderMoved);
        if (folderMoved == null) {
            throw new ResourceNotFoundFault("Folder not found", idFolderMoved);
        }
//        assert (folderMoved.getPosition() != newPosition) : "New Position must be NOT equal to Old Position";
        EntityCorrectness.checkFolderLog(folderMoved); // TODO assert

        if (idNewParent != null) {
            logger.trace("*** Folder will have a Parent");
            GPFolder folderParent = folderDao.find(idNewParent);
            if (folderParent == null) {
                throw new ResourceNotFoundFault("New Parent not found", idNewParent);
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
        
        Search search = new Search();
        search.addFilterGreaterOrEqual("position", endFirstRange).
                addFilterLessOrEqual("position", startFirstRange);
        search.addFilterEqual("project.id", folderMoved.getProject().getId());
        List<GPFolder> matchingFoldersFirstRange = folderDao.search(search);
        List<GPLayer> matchingLayersFirstRange = layerDao.search(search);
        
        search.clear();
        search.addFilterGreaterOrEqual("position", endSecondRange).
                addFilterLessOrEqual("position", startSecondRange);
        search.addFilterEqual("project.id", folderMoved.getProject().getId());
        List<GPFolder> matchingFoldersSecondRange = folderDao.search(search);
        List<GPLayer> matchingLayersSecondRange = layerDao.search(search);
        
        logger.trace("Range: " + startFirstRange + " - " + endFirstRange + " - "
                + startSecondRange + " - " + endSecondRange + " - ");
//        System.out.println("### matchingFoldersFirstRange.size(): " + matchingFoldersFirstRange.size());
//        System.out.println("### matchingLayersFirstRange.size(): " + matchingLayersFirstRange.size());
        int moveValue = matchingFoldersFirstRange.size() + matchingLayersFirstRange.size();

//        System.out.println("### startFirstRange: " + startFirstRange);
//        System.out.println("### endFirstRange: " + endFirstRange);
//        System.out.println("### startSecondRange: " + startSecondRange);
//        System.out.println("### endSecondRange: " + endSecondRange);
//        System.out.println("### shiftValue: " + shiftValue);
//        System.out.println("### moveValue: " + moveValue);

        if (folderMoved.getPosition() < newPosition) {// Drag & Drop to top
            this.executeFoldersModifications(matchingFoldersFirstRange, -shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange, -shiftValue);
            this.executeFoldersModifications(matchingFoldersSecondRange, moveValue);
            this.executeLayersModifications(matchingLayersSecondRange, moveValue);
        } else if (folderMoved.getPosition() > newPosition) {// Drag & Drop to bottom
            this.executeFoldersModifications(matchingFoldersFirstRange, shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange, shiftValue);
            this.executeFoldersModifications(matchingFoldersSecondRange, -moveValue);
            this.executeLayersModifications(matchingLayersSecondRange, -moveValue);
        }
        
        folderDao.merge(matchingFoldersFirstRange.toArray(new GPFolder[matchingFoldersFirstRange.size()]));
        folderDao.merge(matchingFoldersSecondRange.toArray(new GPFolder[matchingFoldersSecondRange.size()]));
        layerDao.merge(matchingLayersFirstRange.toArray(new GPLayer[matchingLayersFirstRange.size()]));
        layerDao.merge(matchingLayersSecondRange.toArray(new GPLayer[matchingLayersSecondRange.size()]));
        folderMoved.setPosition(newPosition);
        folderDao.merge(folderMoved);
        
        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        
        return true;
    }
    
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
    
    public FolderDTO getShortFolder(Long folderID) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        FolderDTO folderDTO = new FolderDTO(folder);
        return folderDTO;
    }
    
    public GPFolder getFolderDetail(Long folderID) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderID);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderID);
        }
        EntityCorrectness.checkFolderLog(folder); // TODO assert

        return folder;
    }
    
    public List<FolderDTO> searchFolders(PaginatedSearchRequest searchRequest) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.setMaxResults(searchRequest.getNum());
        searchCriteria.setPage(searchRequest.getPage());
        searchCriteria.addSortAsc("name");
        
        String like = searchRequest.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("name", like);
        }
        
        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return FolderDTO.convertToFolderDTOList(foundFolder);
    }
    
    public List<FolderDTO> getFolders() {
        List<GPFolder> found = folderDao.findAll();
        return FolderDTO.convertToFolderDTOList(found);
    }
    
    public long getFoldersCount(SearchRequest searchRequest) {
        Search searchCriteria = new Search(GPFolder.class);
        if (searchRequest != null && searchRequest.getNameLike() != null) {
            searchCriteria.addFilterILike("name", searchRequest.getNameLike());
        }
        
        return folderDao.count(searchCriteria);
    }
    
    public List<FolderDTO> getChildrenFoldersByRequest(RequestByID request) {
        Search searchCriteria = new Search(GPFolder.class);
        
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addSortAsc("name");
        searchCriteria.addFilterEqual("parent.id", request.getId());
        
        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return FolderDTO.convertToFolderDTOList(foundFolder);
    }
    
    public List<FolderDTO> getChildrenFolders(Long folderID) {
        Search searchCriteria = new Search(GPFolder.class);
        
        searchCriteria.addSortAsc("name");
        searchCriteria.addFilterEqual("parent.id", folderID);
        
        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return FolderDTO.convertToFolderDTOList(foundFolder);
    }
    
    public TreeFolderElements getChildrenElements(Long folderID) {
        TreeFolderElements tree = new TreeFolderElements();
        
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addFilterEqual("parent.id", folderID);
        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        tree.addFolderCollection(FolderDTO.convertToFolderDTOList(foundFolder));
        
        searchCriteria = new Search(GPLayer.class);
        searchCriteria.addFilterEqual("folder.id", folderID);
        List<GPLayer> foundLayer = layerDao.search(searchCriteria);
        tree.addLayerCollection(foundLayer);
        
        return tree;
    }
    //</editor-fold>

    private void updateNumberOfElements(GPProject project, int delta) {
        project.deltaToNumberOfElements(delta);
        projectDao.merge(project);
    }
}
