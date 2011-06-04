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
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPStyleDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPStyle;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.StyleDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.LayerList;
import org.geosdi.geoplatform.responce.collection.StyleList;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
class LayerServiceImpl {

    final private static Logger LOGGER = Logger.getLogger(LayerServiceImpl.class);
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
    private GPStyleDAO styleDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
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

    /**
     * @param styleDao
     *            the styleDao to set
     */
    public void setStyleDao(GPStyleDAO styleDao) {
        this.styleDao = styleDao;
    }
    //</editor-fold>

    StyleList getLayerStyles(long layerId) {
        Search searchCriteria = new Search(GPStyle.class);
        searchCriteria.addSortAsc("name");

        Filter layer = Filter.equal("layer.id", layerId);
        searchCriteria.addFilter(layer);

        List<GPStyle> foundStyle = styleDao.search(searchCriteria);
        StyleList list = convertToStyleList(foundStyle);
        return list;
    }

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

//        orig.setParent(layer.getParent());
        orig.setAbstractText(layer.getAbstractText());
        orig.setBbox(layer.getBbox());
        orig.setFolder(layer.getFolder());
        orig.setLayerInfo(layer.getLayerInfo());
        orig.setLayerType(layer.getLayerType());
        orig.setName(layer.getName());
        orig.setPosition(layer.getPosition());
        orig.setShared(layer.isShared());
        orig.setSrs(layer.getSrs());
        orig.setTitle(layer.getTitle());
        orig.setUrlServer(layer.getUrlServer());

        layerDao.merge(orig);
        return orig.getId();
    }

    public long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPVectorLayer orig = (GPVectorLayer) layerDao.find(layer.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Layer not found", layer.getId());
        }

//        orig.setParent(layer.getParent());
        orig.setAbstractText(layer.getAbstractText());
        orig.setBbox(layer.getBbox());
        orig.setFolder(layer.getFolder());
        orig.setGeometry(layer.getGeometry());
        orig.setLayerType(layer.getLayerType());
        orig.setName(layer.getName());
        orig.setPosition(layer.getPosition());
        orig.setShared(layer.isShared());
        orig.setSrs(layer.getSrs());
        orig.setTitle(layer.getTitle());
        orig.setUrlServer(layer.getUrlServer());

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

    // Add @Transaction ?
    public long saveAddedLayerAndTreeModification(GPLayer layer, GPWebServiceMapData descendantsMapData) {
        int newPosition = layer.getPosition();
        int increment = 1;
        // Shift positions
        layerDao.updatePositionsLowerBound(newPosition, increment);
        folderDao.updatePositionsLowerBound(newPosition, increment);

        layerDao.persist(layer);

        folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());

        return layer.getId();
    }

    // Add @Transaction ?
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


    public boolean saveCheckStatusLayerAndTreeModifications(long layerId, boolean isChecked)
            throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }
        assert (layer.getFolder() != null) : "Layer must be stored into a folder";

        boolean checkSave = layerDao.persistCheckStatusLayer(layerId, isChecked);

        // Iff isChecked is true, all the ancestor folders must be checked
        if (isChecked && checkSave) {
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

    // Add @Transaction ?
    public boolean saveDragAndDropLayerModifications(long idLayerMoved, long idNewParent, int newPosition,
            GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        GPLayer layerMoved = layerDao.find(idLayerMoved);
        if (layerMoved == null) {
            throw new ResourceNotFoundFault("Layer with id " + idLayerMoved + " not found");
        }
        assert(layerMoved.getFolder() != null) : "Layer specified must be stored into a folder";
        
//        GPFolder oldFolder = layerMoved.getFolder();
//        if (layerMoved.getPosition() == newPosition) {
//            if(oldFolder.getId() == idNewParent){
//                return false;
//            }
//            // Fix parent and descendants assotiation
//        }
        
        GPFolder newFolder = folderDao.find(idNewParent);
        if (newFolder == null) {
            throw new ResourceNotFoundFault("Folder with id " + idNewParent + " not found");
        }

        int beginPosition = -1, endPosition = -1, delta = 0;
        int oldPosition = layerMoved.getPosition();
        if (oldPosition < newPosition) {
            // Drag & Drop to up
            beginPosition = oldPosition + 1;
            endPosition = newPosition;
            delta = -1;
        } else if (newPosition < oldPosition) {
            // Drag & Drop to down
            beginPosition = newPosition;
            endPosition = oldPosition - 1;
            delta = 1;
        }

        boolean resultUpdateOfLayers = true, resultUpdateOfFolders = true;
        if(delta != 0){
            resultUpdateOfLayers = layerDao.updatePositionsRange(beginPosition, endPosition, delta);
            resultUpdateOfFolders = folderDao.updatePositionsRange(beginPosition, endPosition, delta);
        }
        assert(resultUpdateOfLayers) : "Errors occured when updating position of layers";
        assert(resultUpdateOfFolders) : "Errors occured when updating position of folders";

        layerMoved.setFolder(newFolder);
        layerMoved.setPosition(newPosition);
        layerDao.merge(layerMoved);
        
        boolean resultUpdateAncestorsDescendants = folderDao.updateAncestorsDescendants(descendantsMapData.getDescendantsMap());
        assert(resultUpdateAncestorsDescendants) : "Errors occured when updating ancestors and descendants on tree";
        
        return resultUpdateOfLayers && resultUpdateOfFolders && resultUpdateAncestorsDescendants;
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

    public LayerList getLayers() {
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

    // TODO Move to StyleList?
    // as constructor: StyleList list = new StyleList(List<GPStyle>);    
    private StyleList convertToStyleList(List<GPStyle> foundStyle) {
        List<StyleDTO> stylesDTO = new ArrayList<StyleDTO>(foundStyle.size());
        for (GPStyle style : foundStyle) {
            stylesDTO.add(new StyleDTO(style));
        }

        StyleList styles = new StyleList();
        styles.setList(stylesDTO);
        return styles;
    }

    // TODO Move to LayerList?
    // as constructor: LayerList list = new LayerList(List<GPLayer>);
    private LayerList convertToLayerList(List<GPLayer> layerList) {
        List<ShortLayerDTO> layersDTO = new ArrayList<ShortLayerDTO>(layerList.size());
        for (GPLayer layer : layerList) {
            layersDTO.add(new ShortLayerDTO(layer));
        }

        LayerList layers = new LayerList();
        layers.setList(layersDTO);
        return layers;
    }

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
     * @return List of his and ancestor folders ID
     */
    private Long[] getIdsFolderAndAncestors(GPFolder folder) {
        assert (folder != null) : "Folder in getFolderAndAncestorsId must be NOT NULL";
        assert ((folder.getOwner() == null && folder.getParent() != null)
                || (folder.getOwner() != null && folder.getParent() == null)) :
                "getFolderAndAncestorsId - Illegal Argument Exception: folder must have or Owner or Parent NOT NULL";
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