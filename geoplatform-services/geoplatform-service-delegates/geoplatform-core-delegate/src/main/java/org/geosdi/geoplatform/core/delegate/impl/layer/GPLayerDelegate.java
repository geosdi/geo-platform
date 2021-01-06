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
package org.geosdi.geoplatform.core.delegate.impl.layer;

import com.google.common.collect.Lists;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.delegate.api.layer.LayerDelegate;
import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.geosdi.geoplatform.request.layer.*;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.response.collection.LongListStore;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Layer service delegate.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Component(value = "gpLayerDelegate")
public class GPLayerDelegate implements LayerDelegate {

    @Autowired
    private GPProjectDAO projectDao;
    @Autowired
    private GPFolderDAO folderDao;
    @Autowired
    private GPLayerDAO layerDao;

    @Override
    public Long insertLayer(InsertLayerRequest layerRequest) throws
            IllegalParameterFault {
        if (layerRequest == null) {
            throw new IllegalParameterFault(
                    "The InsertLayerRequest must not be "
                            + "null.");
        }

        GPLayer layer = layerRequest.getLayer();
        EntityCorrectness.checkLayer(layer); // TODO assert

        layerDao.persist(layer);
        return layer.getId();
    }

    @Override
    public Long updateRasterLayer(GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkLayer(layer); // TODO assert

        GPRasterLayer orig = (GPRasterLayer) this.getLayerDetail(layer.getId());

        orig.setLayerInfo(layer.getLayerInfo());
        this.updateLayer(orig, layer);

        layerDao.merge(orig);
        return orig.getId();
    }

    @Override
    public Long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkLayer(layer); // TODO assert

        GPVectorLayer orig = (GPVectorLayer) this.getLayerDetail(layer.getId());

        orig.setGeometry(layer.getGeometry());
        this.updateLayer(orig, layer);

        layerDao.merge(orig);
        return orig.getId();
    }

    @Override
    public Boolean deleteLayer(Long layerID) throws ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);

        // data on ancillary tables should be deleted by cascading
        return layerDao.remove(layer);
    }

    @Override
    public Long saveAddedLayerAndTreeModifications(
            WSAddLayerAndTreeModificationsRequest addLayerRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (addLayerRequest == null) {
            throw new IllegalParameterFault(
                    "The WSAddLayerAndTreeModificationsRequest "
                            + "must not be null.");
        }
        Long projectID = addLayerRequest.getProjectID();
        Long parentID = addLayerRequest.getParentFolderID();
        GPLayer layer = addLayerRequest.getLayer();
        GPWebServiceMapData descendantsMapData = addLayerRequest.getDescendantsMapData();

        GPProject project = projectDao.find(projectID);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectID);
        }
        EntityCorrectness.checkProjectLog(project); // TODO assert
        layer.setProject(project);

        GPFolder parent = folderDao.find(parentID);
        if (parent == null) {
            throw new ResourceNotFoundFault("Parent folder not found", parentID);
        }
        EntityCorrectness.checkFolderLog(parent); // TODO assert
        layer.setFolder(parent);

        EntityCorrectness.checkLayer(layer); // TODO assert

        int newPosition = layer.getPosition();
        int increment = 1;
        // Shift positions
        layerDao.updatePositionsLowerBound(projectID, newPosition, increment);
        folderDao.updatePositionsLowerBound(projectID, newPosition, increment);

        layerDao.persist(layer);

        folderDao.updateAncestorsDescendants(
                descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, increment);

        return layer.getId();
    }

    @Override
    public LongListStore saveAddedLayersAndTreeModifications(
            WSAddLayersAndTreeModificationsRequest addLayersRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        Long projectID = addLayersRequest.getProjectID();
        Long parentID = addLayersRequest.getParentFolderID();
        List<GPLayer> layers = addLayersRequest.getLayers();
        GPWebServiceMapData descendantsMapData = addLayersRequest.getDescendantsMapData();
        // Project
        GPProject project = projectDao.find(projectID);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectID);
        }
        EntityCorrectness.checkProjectLog(project); // TODO assert

        // Folder Parent
        GPFolder parent = folderDao.find(parentID);
        if (parent == null) {
            throw new ResourceNotFoundFault("Parent folder not found", parentID);
        }
        EntityCorrectness.checkFolderLog(parent); // TODO assert

        if (layers == null || layers.isEmpty()) {
            throw new IllegalParameterFault("List of layers is null or empty");
        }
        for (GPLayer layer : layers) {
            layer.setProject(project);
            layer.setFolder(parent);
            EntityCorrectness.checkLayer(layer); // TODO assert
        }

        int newPosition = layers.get(layers.size() - 1).getPosition();
        int increment = layers.size();
        // Shift positions
        layerDao.updatePositionsLowerBound(projectID, newPosition, increment);
        folderDao.updatePositionsLowerBound(projectID, newPosition, increment);

        layerDao.persist(layers.toArray(new GPLayer[layers.size()]));

        ArrayList<Long> IDsList = new ArrayList<Long>(layers.size());
        for (GPLayer layer : layers) {
            IDsList.add(layer.getId());
        }

        folderDao.updateAncestorsDescendants(
                descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, increment);

        return new LongListStore(IDsList);
    }

    @Override
    public Boolean saveDeletedLayerAndTreeModifications(
            WSDeleteLayerAndTreeModificationsRequest deleteLayerRequest)
            throws ResourceNotFoundFault {
        if (deleteLayerRequest == null) {
            throw new IllegalArgumentException(
                    "The WSDeleteLayerAndTreeModificationsRequest "
                            + "must not be null.");
        }
        Long layerID = deleteLayerRequest.getLayerID();
        GPWebServiceMapData descendantsMapData = deleteLayerRequest.getDescendantsMapData();

        GPLayer layer = this.getLayerDetail(layerID);

        int oldPosition = layer.getPosition();
        boolean result = layerDao.remove(layer);

        int decrement = -1;
        GPProject project = layer.getProject();
        // Shift positions
        layerDao.updatePositionsLowerBound(project.getId(), oldPosition,
                decrement);
        folderDao.updatePositionsLowerBound(project.getId(), oldPosition,
                decrement);

        folderDao.updateAncestorsDescendants(
                descendantsMapData.getDescendantsMap());
        this.updateNumberOfElements(project, decrement);

        return result;
    }

    @Override
    public Boolean saveCheckStatusLayerAndTreeModifications(Long layerID,
            boolean checked) throws ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);

        boolean checkSave = layerDao.persistCheckStatusLayer(layerID, checked);

        // Iff checked is true, all the ancestor folders must be checked
        if (checked && checkSave) {
            Long[] layerAncestors = this.getAncestorIDs(layer.getFolder());
            return folderDao.persistCheckStatusFolders(true, layerAncestors);
        }

        return checkSave;
    }

    @Override
    public boolean fixCheckStatusLayerAndTreeModifications(Long layerID,
            Long oldFolderID, Long newFolderID) throws ResourceNotFoundFault {
        this.getLayerDetail(layerID);

        // Retrieve the folders parent
        GPFolder oldFolder = folderDao.find(oldFolderID);
        if (oldFolder == null) {
            throw new ResourceNotFoundFault("Old Folder not found", oldFolderID);
        }
        EntityCorrectness.checkFolderLog(oldFolder); // TODO assert

        GPFolder newFolder = folderDao.find(newFolderID);
        if (newFolder == null) {
            throw new ResourceNotFoundFault("New Folder not found", newFolderID);
        }
        EntityCorrectness.checkFolderLog(newFolder); // TODO assert

        // Test if the Check was valid (all the old ancestor must be checked)
        GPFolder[] oldAncestors = this.getAncestors(oldFolder);
        if (this.isAllFoldersChecked(oldAncestors)) {
            Long[] idNewAncestors = this.getAncestorIDs(newFolder);
            return folderDao.persistCheckStatusFolders(true, idNewAncestors);
        }

        return true;
    }

    @Override
    public Boolean saveDragAndDropLayerAndTreeModifications(
            WSDDLayerAndTreeModificationsRequest ddLayerReq)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (ddLayerReq == null) {
            throw new IllegalParameterFault(
                    "The WSDDLayerAndTreeModificationsRequest "
                            + "must not be null.");
        }
        Long layerMovedID = ddLayerReq.getLayerMovedID();
        Long newParentID = ddLayerReq.getNewParentID();
        int newPosition = ddLayerReq.getNewPosition();
        GPWebServiceMapData descendantsMapData = ddLayerReq.getDescendantsMapData();

        GPLayer layerMoved = this.getLayerDetail(layerMovedID);

        if (newParentID == null) {
            throw new IllegalParameterFault("New folder parent NOT declared");
        }

        GPFolder folderParent = folderDao.find(newParentID);
        if (folderParent == null) {
            throw new ResourceNotFoundFault("The new parent does not exists",
                    newParentID);
        }
        EntityCorrectness.checkFolderLog(folderParent); // TODO assert
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
        search.addFilterEqual("project.id", layerMoved.getProject().getId());
        List<GPFolder> matchingFoldersFirstRange = folderDao.search(search);
        List<GPLayer> matchingLayersFirstRange = layerDao.search(search);

        if (layerMoved.getPosition() < newPosition) {// Drag & Drop to top
            this.executeFoldersModifications(matchingFoldersFirstRange,
                    -shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange,
                    -shiftValue);
        } else if (layerMoved.getPosition() > newPosition) {// Drag & Drop to bottom
            this.executeFoldersModifications(matchingFoldersFirstRange,
                    shiftValue);
            this.executeLayersModifications(matchingLayersFirstRange, shiftValue);
        }

        folderDao.merge(matchingFoldersFirstRange.toArray(
                new GPFolder[matchingFoldersFirstRange.size()]));
        layerDao.merge(matchingLayersFirstRange.toArray(
                new GPLayer[matchingLayersFirstRange.size()]));

        layerMoved.setPosition(newPosition);
        layerDao.merge(layerMoved);

        folderDao.updateAncestorsDescendants(
                descendantsMapData.getDescendantsMap());

        return true;
    }

    @Override
    public Boolean saveLayerProperties(RasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        Long layerID = layerProperties.getId();

        GPLayer layer = this.getLayerDetail(layerID);

        layer.setAlias(layerProperties.getAlias());
        if (layer instanceof GPRasterLayer) {
            try {
                GPRasterLayer raster = (GPRasterLayer) layer;
                raster.setOpacity(layerProperties.getOpacity());
                raster.setSingleTileRequest(
                        layerProperties.isSingleTileRequest());
                raster.setMaxScale(layerProperties.getMaxScale());
                raster.setMinScale(layerProperties.getMinScale());
                raster.setStyles(layerProperties.getStyleList());
                raster.setCqlFilter(layerProperties.getCqlFilter());
                raster.setTimeFilter(layerProperties.getTimeFilter());
            } catch (IllegalArgumentException iae) {
                throw new IllegalParameterFault(iae.getMessage());
            }
        }

        layerDao.merge(layer);

        boolean checkSave = layerDao.persistCheckStatusLayer(layerID,
                layerProperties.isChecked());

        // Iff checked is true and the check status was modified, all the ancestor folders must be checked
        if (layerProperties.isChecked() && checkSave) {
            Long[] layerAncestors = this.getAncestorIDs(layer.getFolder());
            return folderDao.persistCheckStatusFolders(true, layerAncestors);
        }

        return true;
    }

    @Override
    public GPLayer getLayerDetail(Long layerID) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerID);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerID);
        }
        EntityCorrectness.checkLayerLog(layer); // TODO assert

        return layer;
    }

    @Override
    public GPRasterLayerResponse getRasterLayer(Long layerID) throws
            ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);
        GPRasterLayer raster = this.rasterLayer(layer);
        return new GPRasterLayerResponse(raster);
    }

    @Override
    public GPVectorLayerResponse getVectorLayer(Long layerID) throws
            ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);
        GPVectorLayer vector = this.vectorLayer(layer);
        return new GPVectorLayerResponse(vector);
    }

    @Override
    public ShortLayerDTO getShortLayer(Long layerID) throws
            ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);
        ShortLayerDTO layerDTO = new ShortLayerDTO(layer);
        return layerDTO;
    }

    @Override
    public ShortLayerDTOContainer getLayers(Long projectID) {
        Search searchCriteria = new Search(GPLayer.class);

        searchCriteria.addSortAsc("title");
        searchCriteria.addFilterEqual("project.id", projectID);

        List<GPLayer> foundLayer = layerDao.search(searchCriteria);

        EntityCorrectness.checkLayerCompleteListLog(foundLayer); // TODO assert

        return new ShortLayerDTOContainer(ShortLayerDTO
                .convertToShortLayerDTOList(foundLayer));
    }

    @Override
    public ShortLayerDTOContainer getFirstLevelLayers(Long projectID) {
        Search searchCriteria = new Search(GPLayer.class);
        searchCriteria.addSortDesc("position");
//            Filter.equal("folder.id", folder.getId());
        Filter filterEqualFolderID = new Filter();
        filterEqualFolderID.setProperty("folder.id");
        filterEqualFolderID.setOperator(Filter.OP_EQUAL);
        searchCriteria.addFilter(filterEqualFolderID);

        List<GPFolder> rootFolders = folderDao.searchRootFolders(projectID);
        List<GPLayer> firstLevelLayers = Lists.<GPLayer>newArrayList();
        for (GPFolder folder : GPSharedUtils.safeList(rootFolders)) {
            filterEqualFolderID.setValue(folder.getId());
            firstLevelLayers.addAll(layerDao.search(searchCriteria));
        }

        EntityCorrectness.checkLayerCompleteListLog(firstLevelLayers); // TODO assert

        return new ShortLayerDTOContainer(ShortLayerDTO
                .convertToShortLayerDTOList(firstLevelLayers));
    }

    @Override
    public GPBBox getBBox(Long layerID) throws ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);
        GPBBox bBox = layer.getBbox();
        return bBox;
    }

    @Override
    public GPLayerInfo getLayerInfo(Long layerID) throws ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);
        GPRasterLayer raster = this.rasterLayer(layer);
        GPLayerInfo layerInfo = raster.getLayerInfo();
        return layerInfo;
    }

    @Override
    public GPLayerType getLayerType(Long layerID) throws
            ResourceNotFoundFault {
        GPLayer layer = this.getLayerDetail(layerID);
        GPLayerType layerType = layer.getLayerType();
        return layerType;
    }

    @Override
    public GetDataSourceResponse getLayersDataSourceByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        GPProject project = projectDao.find(projectID);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectID);
        }

        return new GetDataSourceResponse(layerDao
                .findDistinctDataSourceByProjectId(projectID));
    }

    /**
     ***************************************************************************
     */
    private GPRasterLayer rasterLayer(GPLayer layer) throws
            ResourceNotFoundFault {
        if (!(layer instanceof GPRasterLayer)) {
            throw new ResourceNotFoundFault("Layer is not a raster");
        }
        return (GPRasterLayer) layer;
    }

    private GPVectorLayer vectorLayer(GPLayer layer) throws
            ResourceNotFoundFault {
        if (!(layer instanceof GPVectorLayer)) {
            throw new ResourceNotFoundFault("Layer is not a vector");
        }
        return (GPVectorLayer) layer;
    }

    private void executeLayersModifications(List<GPLayer> elements, int value) {
        for (GPLayer layer : elements) {
            EntityCorrectness.checkLayerLog(layer); // TODO assert
            layer.setPosition(layer.getPosition() + value);
        }
    }

    private void executeFoldersModifications(List<GPFolder> elements, int value) {
        for (GPFolder folder : elements) {
            EntityCorrectness.checkFolderLog(folder); // TODO assert
            folder.setPosition(folder.getPosition() + value);
        }
    }

    /**
     * Updates all common fields of raster and vector layers (GPLayer)
     */
    private void updateLayer(GPLayer layerToUpdate, GPLayer layer) {
        layerToUpdate.setProject(layer.getProject());
        layerToUpdate.setAbstractText(layer.getAbstractText());
        layerToUpdate.setBbox(layer.getBbox());
        layerToUpdate.setLayerType(layer.getLayerType());
        layerToUpdate.setName(layer.getName());
        layerToUpdate.setPosition(layer.getPosition());
        layerToUpdate.setShared(layer.isShared());
        layerToUpdate.setSrs(layer.getSrs());
        layerToUpdate.setTitle(layer.getTitle());
        layerToUpdate.setUrlServer(layer.getUrlServer());
        layerToUpdate.setSingleTileRequest(layer.isSingleTileRequest());
    }

    /**
     * @return folder argument and his ancestor folders
     */
    private GPFolder[] getAncestors(GPFolder folder)
            throws ResourceNotFoundFault {
        Long[] idFolderAndAncestors = this.getAncestorIDs(folder);
        GPFolder[] folderAndAncestors = folderDao.find(idFolderAndAncestors);
        if (folderAndAncestors.length == 0) {
            throw new ResourceNotFoundFault(
                    "Ancestors Folders of Layer not found");
        }
        return folderAndAncestors;
    }

    /**
     * @return IDs of parent folder argument and his ancestor folders
     */
    private Long[] getAncestorIDs(GPFolder parent) {
        List<Long> ancestors = new ArrayList<Long>();
        ancestors.add(parent.getId());

        GPFolder ancestorIth = parent.getParent();
        while (ancestorIth != null) {
            ancestors.add(ancestorIth.getId());
            ancestorIth = ancestorIth.getParent();
        }
        return ancestors.toArray(new Long[ancestors.size()]);
    }

    /**
     * @return true if all folders are checked
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

    private void updateNumberOfElements(GPProject project, int delta) {
        project.deltaToNumberOfElements(delta);
        projectDao.merge(project);
    }

}
