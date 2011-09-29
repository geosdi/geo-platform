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

import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.ShortRasterPropertiesDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
class LayerServiceImpl {

    final private static Logger logger = LoggerFactory.getLogger(LayerServiceImpl.class);
    // DAO
    private GPUserDAO userDao;
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
//    private GPStyleDAO styleDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param userDao 
     *            the userDao to set
     */
    public GPUserDAO getUserDao() {
        return userDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * @param folderDao 
     *            the folderDao to set
     */
    public void setFolderDao(GPFolderDAO folderDao) {
        this.folderDao = folderDao;
    }

    /**
     * @param layerDao
     *            the layerDao to set
     */
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
    }

//    /**
//     * @param styleDao
//     *            the styleDao to set
//     */
//    public void setStyleDao(GPStyleDAO styleDao) {
//        this.styleDao = styleDao;
//    }
    //</editor-fold>
//    public List<StyleDTO> getLayerStyles(long layerId) {
//        Search searchCriteria = new Search(GPStyle.class);
//        searchCriteria.addSortAsc("name");
//
//        Filter layer = Filter.equal("layer.id", layerId);
//        searchCriteria.addFilter(layer);
//
//        List<GPStyle> foundStyle = styleDao.search(searchCriteria);
//        return convertToStyleList(foundStyle);
//    }
    public long insertLayer(GPLayer layer) {
        layerDao.persist(layer);
        return layer.getId();
    }

    public long updateRasterLayer(GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPRasterLayer orig = (GPRasterLayer) layerDao.find(layer.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Layer not found", layer.getId());
        }

        orig.setLayerInfo(layer.getLayerInfo());
        this.updateLayer(orig, layer);

        layerDao.merge(orig);
        return orig.getId();
    }

    public long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPVectorLayer orig = (GPVectorLayer) layerDao.find(layer.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Layer not found", layer.getId());
        }

        orig.setGeometry(layer.getGeometry());
        this.updateLayer(orig, layer);

        layerDao.merge(orig);
        return orig.getId();
    }

    public boolean deleteLayer(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPLayer layer = layerDao.find(request.getId());
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", request.getId());
        }

        // data on ancillary tables should be deleted by cascading
        return layerDao.remove(layer);
    }

    public long saveAddedLayerAndTreeModifications(String username,
            GPLayer layer, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser owner = userDao.findByUsername(username);
        if (owner == null) {
            throw new ResourceNotFoundFault("Owner with username \"" + username + "\" not found");
        }
        layer.setOwnerId(owner.getId());

        GPFolder parent = layer.getFolder();
        if (parent == null) {
            throw new IllegalParameterFault("Parent of layer with id " + layer.getId() + " not found");
        }

        long idParent = parent.getId();
        GPFolder parentFromDB = folderDao.find(idParent);
        if (parentFromDB == null) {
            throw new ResourceNotFoundFault("Parent of layer not found", idParent);
        }

        int newPosition = layer.getPosition();
        int increment = 1;
        // Shift positions
        layerDao.updatePositionsLowerBound(newPosition, increment);
        folderDao.updatePositionsLowerBound(newPosition, increment);

        layerDao.persist(layer);

        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        return layer.getId();
    }

    public ArrayList<Long> saveAddedLayersAndTreeModifications(String username,
            List<GPLayer> layers, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser owner = userDao.findByUsername(username);
        if (owner == null) {
            throw new ResourceNotFoundFault("Owner with username \"" + username + "\" not found");
        }

        GPLayer[] layersArray = layers.toArray(new GPLayer[layers.size()]);
        for (GPLayer gpLayer : layersArray) {
            gpLayer.setOwnerId(owner.getId());
        }

        GPFolder parent = null;
        GPFolder parentFromDB = null;
        parent = layersArray[0].getFolder();
        if (parent == null) {
            throw new IllegalParameterFault("Parent of layer with id " + layersArray[0].getId() + " not found");
        }

        long idParent = parent.getId();
        parentFromDB = folderDao.find(idParent);
        if (parentFromDB == null) {
            throw new ResourceNotFoundFault("Parent of layer not found", idParent);
        }
        ArrayList<Long> arrayList = new ArrayList<Long>(layers.size());
        int newPosition = layers.get(layers.size() - 1).getPosition();
        int increment = layers.size();
        // Shift positions
        layerDao.updatePositionsLowerBound(newPosition, increment);
        folderDao.updatePositionsLowerBound(newPosition, increment);

        layerDao.persist(layersArray);

        for (int i = 0; i < layersArray.length; i++) {
            arrayList.add(layersArray[i].getId());
        }

        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        return arrayList;
    }

    public boolean saveDeletedLayerAndTreeModifications(long id, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPLayer layer = layerDao.find(id);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", id);
        }

        int oldPosition = layer.getPosition();
        boolean result = layerDao.remove(layer);

        int decrement = 1;
        // Shift positions
        layerDao.updatePositionsLowerBound(oldPosition, -decrement);
        folderDao.updatePositionsLowerBound(oldPosition, -decrement);

        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());

        return result;
    }

    public boolean saveCheckStatusLayerAndTreeModifications(long layerId, boolean checked)
            throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }
        assert (layer.getFolder() != null) : "Layer must be stored into a folder";

        boolean checkSave = layerDao.persistCheckStatusLayer(layerId, checked);

        // Iff checked is true and the check status was modified, all the ancestor folders must be checked
        if (checked && checkSave) {
            Long[] layerAncestors = this.getIdsFolderAndAncestors(layer.getFolder());
            return folderDao.persistCheckStatusFolders(true, layerAncestors);
        }

        return checkSave;
    }

    /**
     * For Drag and Drop, fix the check of new ancestors for a layer checked
     * 
     * The old and new folders (parent) will be extraxted from DB
     */
    public boolean fixCheckStatusLayerAndTreeModifications(long layerId,
            long oldFolderId, long newFolderId) throws ResourceNotFoundFault {
        // Retrieve the layer
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }
        assert (layer.isChecked()) : "For Fix the check, the layer must be checked";

        // Retrieve the folders parent
        GPFolder oldFolder = folderDao.find(oldFolderId);
        if (oldFolder == null) {
            throw new ResourceNotFoundFault("Old Folder not found", oldFolderId);
        }
        GPFolder newFolder = folderDao.find(newFolderId);
        if (newFolder == null) {
            throw new ResourceNotFoundFault("New Folder not found", newFolderId);
        }

        // Test if the Check was valid (all the old ancestor must be checked)
        GPFolder[] oldAncestors = this.getFolderAndAncestors(oldFolder);
        if (this.isAllFoldersChecked(oldAncestors)) {
            Long[] idNewAncestors = this.getIdsFolderAndAncestors(newFolder);
            return folderDao.persistCheckStatusFolders(true, idNewAncestors);
        }

        return true;
    }

    public boolean saveDragAndDropLayerModifications(String username, long idLayerMoved,
            long idNewParent, int newPosition, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundFault("User with username \"" + username + "\" not found");
        }

        GPLayer layerMoved = layerDao.find(idLayerMoved);
        if (layerMoved == null) {
            throw new ResourceNotFoundFault("Layer with id " + idLayerMoved + " not found");
        }
        //assert (folderMoved.getPosition() != newPosition) : "New Position must be NOT equal to Old Position";

        if (idNewParent == 0L) {
            throw new ResourceNotFoundFault("Folder parent with id " + idNewParent + " not found");
        }

        GPFolder folderParent = folderDao.find(idNewParent);
        if (folderParent == null) {
            throw new ResourceNotFoundFault("The new parent does not exists into DB.", idNewParent);
        }
        layerMoved.setFolder(folderParent);

        int startFirstRange = 0, endFirstRange = 0;
        if (layerMoved.getPosition() < newPosition) {// Drag & Drop to top
            startFirstRange = newPosition;
            endFirstRange = layerMoved.getPosition() + 1;
        } else if (layerMoved.getPosition() > newPosition) {// Drag & Drop to bottom
            startFirstRange = layerMoved.getPosition() - 1;
            endFirstRange = newPosition;
        }
        int shiftValue = 1;

        Search search = new Search();
        search.addFilterGreaterOrEqual("position", endFirstRange).
                addFilterLessOrEqual("position", startFirstRange);
        search.addFilterEqual("owner.id", user.getId());
        List<GPFolder> matchingFoldersFirstRange = folderDao.search(search);
        search.removeFiltersOnProperty("owner.id");
        search.addFilterEqual("ownerId", user.getId());
        List<GPLayer> matchingLayersFirstRange = layerDao.search(search);

        if (layerMoved.getPosition() < newPosition) {// Drag & Drop to top
            this.executeFoldersModifications(matchingFoldersFirstRange, -shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange, -shiftValue);
        } else if (layerMoved.getPosition() > newPosition) {// Drag & Drop to bottom
            this.executeFoldersModifications(matchingFoldersFirstRange, shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange, shiftValue);
        }

        folderDao.merge(matchingFoldersFirstRange.toArray(new GPFolder[matchingFoldersFirstRange.size()]));
        layerDao.merge(matchingLayersFirstRange.toArray(new GPLayer[matchingLayersFirstRange.size()]));
        layerMoved.setPosition(newPosition);
        layerDao.merge(layerMoved);

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

    public boolean saveLayerProperties(String username, ShortRasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser owner = userDao.findByUsername(username);
        if (owner == null) {
            throw new ResourceNotFoundFault("Owner with username \"" + username + "\" not found");
        }
        long layerId = layerProperties.getId();
        if (layerId == -1) {
            throw new IllegalParameterFault("Layer ID must be setted");
        }

        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        layer.setAlias(layerProperties.getAlias());
        if (layer instanceof GPRasterLayer) {
            try {
                GPRasterLayer raster = (GPRasterLayer) layer;
                raster.setOpacity(layerProperties.getOpacity());
                raster.setStyles(layerProperties.getStyleList());
            } catch (IllegalArgumentException iae) {
                throw new IllegalParameterFault(iae.getMessage());
            }
        }

        layerDao.merge(layer);

        boolean checkSave = layerDao.persistCheckStatusLayer(layerId, layerProperties.isChecked());

        // Iff checked is true and the check status was modified, all the ancestor folders must be checked
        if (layerProperties.isChecked() && checkSave) {
            Long[] layerAncestors = this.getIdsFolderAndAncestors(layer.getFolder());
            return folderDao.persistCheckStatusFolders(true, layerAncestors);
        }

        return true;
    }

    public GPRasterLayer getRasterLayer(long layerId) throws ResourceNotFoundFault {
        GPRasterLayer layer = (GPRasterLayer) layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer;
    }

    public GPVectorLayer getVectorLayer(long layerId) throws ResourceNotFoundFault {
        GPVectorLayer layer = (GPVectorLayer) layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer;
    }

    public ShortLayerDTO getShortLayer(long layerId) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return new ShortLayerDTO(layer);
    }

    public List<ShortLayerDTO> getLayers() {
        List<GPLayer> found = layerDao.findAll();
        return convertToLayerList(found);
    }

    public GPBBox getBBox(long layerId) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer.getBbox();
    }

    public GPLayerInfo getLayerInfo(long layerId) throws ResourceNotFoundFault {
        GPRasterLayer layer = (GPRasterLayer) layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer.getLayerInfo();
    }

//    public Point getGeometry(long layerId) throws ResourceNotFoundFault {
//        GPVectorLayer layer = (GPVectorLayer)layerDao.find(layerId);
//        if (layer == null) {
//            throw new ResourceNotFoundFault("Layer not found", layerId);
//        }
//
//        return layer.getGeometry();
//    }
    public GPLayerType getLayerType(long layerId) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer.getLayerType();
    }

    public ArrayList<String> getLayersDataSourceByOwner(String userName) throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(userName);
        if (user == null) {
            throw new ResourceNotFoundFault("User with username \"" + userName + "\" not found");
        }

        return layerDao.findDistinctDataSourceByUserId(user.getId());
    }

    /**
     * Updates all common fiels of raster and vector layers (GPLayer) 
     */
    private void updateLayer(GPLayer layerToUpdate, GPLayer layer) {
        layerToUpdate.setAbstractText(layer.getAbstractText());
        layerToUpdate.setBbox(layer.getBbox());
        layerToUpdate.setFolder(layer.getFolder());
        layerToUpdate.setLayerType(layer.getLayerType());
        layerToUpdate.setName(layer.getName());
        layerToUpdate.setPosition(layer.getPosition());
        layerToUpdate.setShared(layer.isShared());
        layerToUpdate.setSrs(layer.getSrs());
        layerToUpdate.setTitle(layer.getTitle());
        layerToUpdate.setUrlServer(layer.getUrlServer());
    }

//    private List<StyleDTO> convertToStyleList(List<GPStyle> foundStyle) {
//        List<StyleDTO> stylesDTO = new ArrayList<StyleDTO>(foundStyle.size());
//
//        for (GPStyle style : foundStyle) {
//            stylesDTO.add(new StyleDTO(style));
//        }
//
//        return stylesDTO;
//    }
    private List<ShortLayerDTO> convertToLayerList(List<GPLayer> layerList) {
        List<ShortLayerDTO> layersDTO = new ArrayList<ShortLayerDTO>(layerList.size());

        for (GPLayer layer : layerList) {
            layersDTO.add(new ShortLayerDTO(layer));
        }

        return layersDTO;
    }

    /**
     * @return Folder argument and his ancestor folders
     */
    private GPFolder[] getFolderAndAncestors(GPFolder folderChild)
            throws ResourceNotFoundFault {
        Long[] idFolderAndAncestors = this.getIdsFolderAndAncestors(folderChild);
        GPFolder[] folderAndAncestors = folderDao.find(idFolderAndAncestors);
        if (folderAndAncestors.length == 0) {
            throw new ResourceNotFoundFault("Ancestors Folders of Layer not found");
        }
        return folderAndAncestors;
    }

    /**
     * @return IDs of folder argument and his ancestor folders
     */
    private Long[] getIdsFolderAndAncestors(GPFolder folder) {
        List<Long> ancestors = new ArrayList<Long>();
        ancestors.add(folder.getId());

        GPFolder ancestorIth = folder.getParent();
        while (ancestorIth != null) {
            ancestors.add(ancestorIth.getId());
            ancestorIth = ancestorIth.getParent();
        }
        return ancestors.toArray(new Long[ancestors.size()]);
    }

    /**
     * @return True if all folders are checked
     */
    private boolean isAllFoldersChecked(GPFolder... folders)
            throws ResourceNotFoundFault {
        for (GPFolder folder : folders) {
            if (!folder.isChecked()) {
                return false;
            }
        }
        return true;
    }
}
