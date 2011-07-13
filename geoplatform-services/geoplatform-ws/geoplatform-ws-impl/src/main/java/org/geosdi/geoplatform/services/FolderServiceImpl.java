//<editor-fold defaultstate="collapsed" desc="License">
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
//</editor-fold>
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.RequestByUserFolder;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 * @author giuseppe
 * 
 */
class FolderServiceImpl {

    final private static Logger logger = LoggerFactory.getLogger(FolderServiceImpl.class);
    // DAO
    private GPFolderDAO folderDao;
    private GPUserDAO userDao;
    private GPLayerDAO layerDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param folderDao
     *            the folderDao to set
     */
    public void setFolderDao(GPFolderDAO folderDao) {
        this.folderDao = folderDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * @param layerDao
     *            the layerDao to set
     */
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================    
    public long insertFolder(GPFolder folder) {
        folderDao.persist(folder);
        return folder.getId();
    }

    public long updateFolder(GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder orig = folderDao.find(folder.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Folder not found", folder.getId());
        }
        // Update all properties (except the owner)
        orig.setName(folder.getName());
        orig.setParent(folder.getParent());
        orig.setPosition(folder.getPosition());
        orig.setNumberOfDescendants(folder.getNumberOfDescendants());
        orig.setShared(folder.isShared());
        orig.setChecked(folder.isChecked());

        folderDao.merge(orig);
        return orig.getId();
    }

    public boolean deleteFolder(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = folderDao.find(request.getId());
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", request.getId());
        }

        // data on ancillary tables should be deleted by cascading
        return folderDao.remove(folder);
    }

    public long saveAddedFolderAndTreeModifications(GPFolder folder, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
//        assert ((folder.getOwner() == null && folder.getParent() != null)
//                || (folder.getOwner() != null && folder.getParent() == null)) :
//                this.getClass().getCanonicalName() + " on saveFolderAndTreeModifications - "
//                + "Illegal argument exception: folder must have or Owner or Parent NOT NULL";
//        assert ((folder.getOwner() != null && descendantsMapData.getDescendantsMap().isEmpty())
//                || (folder.getParent() != null && !descendantsMapData.getDescendantsMap().isEmpty())) :
//                "descendantsMapData must have one or more entries if the folder has a parent";

        // TODO verify problems when saving a folder with owner from interface
        GPUser user = userDao.findByUsername(folder.getOwner().getUsername());
        if (user == null) {
            throw new ResourceNotFoundFault("User " + folder.getOwner().getUsername() + " not found");
        }
        folder.setOwner(user);

        if (folder.getParent() != null) {
            GPFolder folderParent = folderDao.find(folder.getParent().getId());
            if (folderParent == null) {
                throw new ResourceNotFoundFault("Folder parent not found", folder.getParent().getId());
            }
            folder.setParent(folderParent);
        }

        int newPosition = folder.getPosition();
        int increment = 1;
        // Shift positions
        folderDao.updatePositionsLowerBound(newPosition, increment);
        layerDao.updatePositionsLowerBound(newPosition, increment);

        folderDao.persist(folder);
        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());

        return folder.getId();
    }

    public boolean saveDeletedFolderAndTreeModifications(long folderId, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPFolder folder = folderDao.find(folderId);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderId);
        }

        int oldPosition = folder.getPosition();
        int decrement = folder.getNumberOfDescendants() + 1;

        boolean result = folderDao.remove(folder);

        // Shift positions (shift must be done only after removing folder)
        folderDao.updatePositionsLowerBound(oldPosition, -decrement);
        layerDao.updatePositionsLowerBound(oldPosition, -decrement);

        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());

        return result;
    }

    public boolean saveCheckStatusFolderAndTreeModifications(long folderId, boolean isChecked)
            throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(folderId);
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", folderId);
        }

        return folderDao.persistCheckStatusFolder(folderId, isChecked);
    }

    /**
     * @param username
     * @param idFolderMoved
     * @param idNewParent: set conventionaly 0 if idFolderMoved is refer to a folder of root
     * @param newPosition
     * @param descendantsMapData
     * @return
     * @throws ResourceNotFoundFault 
     */
    public boolean saveDragAndDropFolderModifications(String username, long idFolderMoved, long idNewParent,
            int newPosition, GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundFault("User with username \"" + username + "\" not found");
        }

        GPFolder folderMoved = folderDao.find(idFolderMoved);
        if (folderMoved == null) {
            throw new ResourceNotFoundFault("Folder with not found", idFolderMoved);
        }
        //assert (folderMoved.getPosition() != newPosition) : "New Position must be NOT equal to Old Position";

        // Iff there isn't a folder of root
        if (idNewParent != 0L) {
            GPFolder folderParent = folderDao.find(idNewParent);
            if (folderParent == null) { // Drag & Drop to the root
                throw new ResourceNotFoundFault("New Parent not found", idNewParent);
            }
            logger.trace("*** Folder has a Parent");
            folderMoved.setParent(folderParent);
        } else {
            logger.trace("*** Folder has a Owner");
            folderMoved.setOwner(user);
            folderMoved.setParent(null);
        }

        int startFirstRange = 0, endFirstRange = 0;
//        System.out.println("### folderMoved.getPosition(): " + folderMoved.getPosition());
//        System.out.println("### newPosition: " + newPosition);
        if (folderMoved.getPosition() < newPosition) {// Drag & Drop to top
//            System.out.println("### Drag & Drop to top");
            startFirstRange = newPosition;
            endFirstRange = folderMoved.getPosition() + 1;
        } else if (folderMoved.getPosition() > newPosition) {// Drag & Drop to bottom
//            System.out.println("### Drag & Drop to bottom");
            startFirstRange = folderMoved.getPosition() - folderMoved.getNumberOfDescendants() - 1;
            endFirstRange = newPosition - folderMoved.getNumberOfDescendants();
        }
        int startSecondRange = folderMoved.getPosition();
        int endSecondRange = folderMoved.getPosition() - folderMoved.getNumberOfDescendants();
        int shiftValue = folderMoved.getNumberOfDescendants() + 1;

        Search search = new Search();
        search.addFilterGreaterOrEqual("position", endFirstRange).
                addFilterLessOrEqual("position", startFirstRange);
        search.addFilterEqual("owner.id", user.getId());
        List<GPFolder> matchingFoldersFirstRange = folderDao.search(search);
        search.removeFiltersOnProperty("owner.id");
        search.addFilterEqual("ownerId", user.getId());
        List<GPLayer> matchingLayersFirstRange = layerDao.search(search);
        search.clear();
        search.addFilterGreaterOrEqual("position", endSecondRange).
                addFilterLessOrEqual("position", startSecondRange);
        search.addFilterEqual("owner.id", user.getId());
        List<GPFolder> matchingFoldersSecondRange = folderDao.search(search);
        search.removeFiltersOnProperty("owner.id");
        search.addFilterEqual("ownerId", user.getId());
        List<GPLayer> matchingLayersSecondRange = layerDao.search(search);
        System.out.println("Range: " + startFirstRange + " - " + endFirstRange + " - "
                + startSecondRange + " - " + endSecondRange + " - ");
//        System.out.println("### matchingFoldersFirstRange.size(): " + matchingFoldersFirstRange.size());
//        System.out.println("### matchingLayersFirstRange.size(): " + matchingLayersFirstRange.size());
        System.out.println((matchingLayersFirstRange.isEmpty() ? "lista vuota" : matchingLayersFirstRange.get(0)));
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
        for (GPLayer gPLayer : elements) {
            gPLayer.setPosition(gPLayer.getPosition() + value);
            System.out.println("New position assignet to: " + gPLayer.getName() + " posiz: " + gPLayer.getPosition());
        }
    }

    private void executeFoldersModifications(List<GPFolder> elements, int value) {
        for (GPFolder gPFolder : elements) {
            gPFolder.setPosition(gPFolder.getPosition() + value);
            System.out.println("New position assignet to: " + gPFolder.getName() + " posiz: " + gPFolder.getPosition());
        }
    }

    public FolderDTO getShortFolder(RequestById request) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(request.getId());
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", request.getId());
        }

        FolderDTO folderDTO = new FolderDTO(folder);
        return folderDTO;
    }

    public GPFolder getFolderDetail(RequestById request) throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(request.getId());
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", request.getId());
        }

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
        return convertToFolderList(foundFolder);
    }

    public List<FolderDTO> getFolders() {
        List<GPFolder> found = folderDao.findAll();
        return convertToFolderList(found);
    }

    public long getFoldersCount(SearchRequest searchRequest) {
        Search searchCriteria = new Search(GPFolder.class);
        if (searchRequest != null && searchRequest.getNameLike() != null) {
            searchCriteria.addFilterILike("name", searchRequest.getNameLike());
        }

        return folderDao.count(searchCriteria);
    }

    public List<FolderDTO> getChildrenFolders(long folderId, int num, int page) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.setMaxResults(num);
        searchCriteria.setPage(page);
        searchCriteria.addSortAsc("name");

        Filter parent = Filter.equal("parent.id", folderId);
        searchCriteria.addFilter(parent);

        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return convertToFolderList(foundFolder);
    }

    public List<FolderDTO> getChildrenFolders(long folderId) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addSortAsc("name");

        Filter parent = Filter.equal("parent.id", folderId);
        searchCriteria.addFilter(parent);

        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return convertToFolderList(foundFolder);
    }

    public TreeFolderElements getChildrenElements(long folderId) {
        TreeFolderElements tree = new TreeFolderElements();

        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addSortAsc("name");
        Filter parent = Filter.equal("parent.id", folderId);
        searchCriteria.addFilter(parent);
        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        tree.addFolderCollection(convertToFolderList(foundFolder));

        searchCriteria = new Search(GPLayer.class);
        searchCriteria.addSortAsc("name");
        Filter folder = Filter.equal("folder.id", folderId);
        searchCriteria.addFilter(folder);
        List<GPLayer> foundLayer = layerDao.search(searchCriteria);
        tree.addLayerCollection(foundLayer);

        return tree;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / User">
    // ==========================================================================
    // === Folder / User
    // ==========================================================================
    public void setFolderShared(RequestById request)
            throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(request.getId());
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found", request.getId());
        }

        folder.setShared(true);
//        folder.setOwner(null);
        folderDao.merge(folder);
    }

    public boolean setFolderOwner(RequestByUserFolder request, boolean force)
            throws ResourceNotFoundFault {
        GPFolder folder = folderDao.find(request.getFolderId());
        if (folder == null) {
            throw new ResourceNotFoundFault("Folder not found",
                    request.getFolderId());
        }

        GPUser user = userDao.find(request.getUserId());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found",
                    request.getUserId());
        }

        // TODO: implement the logic described in this method's javadoc

        folder.setShared(false);
        folder.setOwner(user);
        folderDao.merge(folder);

        return true;
    }

    public List<FolderDTO> getUserFoldersByRequest(RequestById request) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addSortAsc("position");

        searchCriteria.addFilterEqual("owner.id", request.getId());
        searchCriteria.addFilterNull("parent.id");

        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return convertToFolderList(foundFolder);
    }

    public List<FolderDTO> getUserFoldersByUserId(long userId) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addSortAsc("position");

        searchCriteria.addFilterEqual("owner.id", userId);
        searchCriteria.addFilterNull("parent.id");

        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
        return convertToFolderList(foundFolder);
    }

    public List<FolderDTO> getAllUserFolders(long userId, int num, int page) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.setMaxResults(num);
        searchCriteria.setPage(page);
        searchCriteria.addSortAsc("name");

        Filter owner = Filter.equal("owner.id", userId);
        Filter shared = Filter.equal("shared", true);
        searchCriteria.addFilterOr(owner, shared);
        searchCriteria.addFilterNull("parent.id");

        List<GPFolder> foundFolder = folderDao.search(searchCriteria);

        return convertToFolderList(foundFolder);
    }

    public List<FolderDTO> getAllUserFoldersByUserId(long userId) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addSortAsc("name");

        Filter owner = Filter.equal("owner.id", userId);
        Filter shared = Filter.equal("shared", true);
        searchCriteria.addFilterOr(owner, shared);
        searchCriteria.addFilterNull("parent.id");

        List<GPFolder> foundFolder = folderDao.search(searchCriteria);

        return convertToFolderList(foundFolder);
    }

    public long getUserFoldersCount(RequestById request) {
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addFilterEqual("owner.id", request.getId());
        searchCriteria.addFilterNull("parent.id");
        return folderDao.count(searchCriteria);
    }

    public int getAllUserFoldersCount(long userId) {
        Search searchCriteria = new Search(GPFolder.class);

        Filter owner = Filter.equal("owner.id", userId);
        Filter shared = Filter.equal("shared", true);
        searchCriteria.addFilterOr(owner, shared);
        searchCriteria.addFilterNull("parent.id");

        return folderDao.count(searchCriteria);
    }
    //</editor-fold>

    private List<FolderDTO> convertToFolderList(List<GPFolder> folderList) {
        List<FolderDTO> foldersDTO = new ArrayList<FolderDTO>(folderList.size());
        for (GPFolder folder : folderList) {
            FolderDTO folderDTO = new FolderDTO(folder);
            foldersDTO.add(folderDTO);
        }

        Collections.sort(foldersDTO);

        return foldersDTO;
    }
}
