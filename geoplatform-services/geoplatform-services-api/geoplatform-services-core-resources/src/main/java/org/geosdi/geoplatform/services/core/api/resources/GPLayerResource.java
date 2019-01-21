/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.core.api.resources;

import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.request.layer.InsertLayerRequest;
import org.geosdi.geoplatform.request.layer.WSAddLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSAddLayersAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDDLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDeleteLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.collection.LongListStore;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPLayerResource {

    // <editor-fold defaultstate="collapsed" desc="Layer (Raster and Vector)">
    // ==========================================================================
    // === Layer
    // ==========================================================================
    /**
     * Insert a Layer.
     *
     * @param layerRequest
     * @return Long
     *
     * @throws Exception
     */
    @Deprecated
    Long insertLayer(InsertLayerRequest layerRequest) throws Exception;

    /**
     * Update a raster Layer.
     *
     * @param layer the Layer to update
     * @return the Layer ID
     *
     * @throws Exception if Layer not found or if the Layer is not valid
     * @deprecated only for test purpose
     * @see
     * #saveLayerProperties(org.geosdi.geoplatform.responce.RasterPropertiesDTO)
     */
    @Deprecated
    Long updateRasterLayer(GPRasterLayer layer) throws Exception;

    /**
     * Update a vector Layer.
     *
     * @param layer the Layer to update
     * @return the Layer ID
     *
     * @throws Exception if Layer not found or if the Layer is not valid
     * @deprecated only for test purpose
     */
    @Deprecated
    Long updateVectorLayer(GPVectorLayer layer) throws Exception;

    /**
     * Delete a Layer by ID.
     *
     * @param layerID the Layer ID
     * @return true if the Layer was deleted
     *
     * @throws Exception if Layer not found
     * @deprecated only for test purpose
     * @see #saveDeletedLayerAndTreeModifications(java.lang.Long,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Deprecated
    Boolean deleteLayer(Long layerID) throws Exception;

    /**
     * Insert a Layer, moreover manage Folder ancestors and positions on tree.
     *
     * @param addLayerRequest
     *
     * @return the Layer ID
     * @throws Exception if Project or parent Folder not found or if Layer is
     * not valid
     */
    Long saveAddedLayerAndTreeModifications(
            WSAddLayerAndTreeModificationsRequest addLayerRequest)
            throws Exception;

    /**
     * Insert a Layer list, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param addLayersRequest
     *
     * @return the Layer list ID
     * @throws Exception if Project or parent Folder not found or if Layer or
     * Layer list are not valid
     */
    LongListStore saveAddedLayersAndTreeModifications(
            WSAddLayersAndTreeModificationsRequest addLayersRequest)
            throws Exception;

    /**
     * Delete a Layer by ID, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param deleteLayerRequest
     *
     * @return true if the Layer was deleted
     *
     * @throws Exception if Layer not found
     */
    Boolean saveDeletedLayerAndTreeModifications(
            WSDeleteLayerAndTreeModificationsRequest deleteLayerRequest)
            throws Exception;

    /**
     * Save the check status of a Layer and manage Folder ancestors.
     *
     * @param layerID the Layer ID
     * @param checked the check status
     * @return true if the check status was saved
     *
     * @throws Exception if Layer not found
     */
    Boolean saveCheckStatusLayerAndTreeModifications(Long layerID,
            boolean checked) throws Exception;

    /**
     * Shift a Layer to a new position with a new parent Folder, and manage
     * positions on tree.
     *
     * @param ddLayerReq
     *
     * @return true if the Layer wad shifted
     * @throws Exception if Layer or parent Folder not found or if parent Folder
     * ID is null
     */
    Boolean saveDragAndDropLayerAndTreeModifications(
            WSDDLayerAndTreeModificationsRequest ddLayerReq)
            throws Exception;

    /**
     * Update properties (alias, opacity, styles, CQL filter and checked) of a
     * Layer.
     *
     * @param layerProperties the Layer properties
     * @return true if the Layer was updated
     * @throws Exception if Layer not found or if Layer is not valid
     */
    Boolean saveLayerProperties(RasterPropertiesDTO layerProperties)
            throws Exception;

    /**
     * Retrieve a raster Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     *
     * @throws Exception if raster Layer not found
     */
    GPRasterLayerResponse getRasterLayer(Long layerID) throws Exception;

    /**
     * Retrieve a vector Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     *
     * @throws Exception if vector Layer not found
     */
    GPVectorLayerResponse getVectorLayer(Long layerID) throws Exception;

    /**
     * Retrieve a Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     *
     * @throws Exception if Layer not found
     */
    ShortLayerDTO getShortLayer(Long layerID) throws Exception;

    /**
     * Retrieve a list of all Layers in a Project.
     *
     * @param projectID the Project ID
     * @return the list of Layers
     */
    ShortLayerDTOContainer getLayers(Long projectID);

    /**
     * Retrieve a list of all the first level Layers in a Project.
     *
     * @param projectID the Project ID
     * @return the list of Layers
     */
    ShortLayerDTOContainer getFirstLevelLayers(Long projectID);

    /**
     * Retrieve the Bounding Box of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Bounding Box
     *
     * @throws Exception if Layer not found
     */
    GPBBox getBBox(Long layerID) throws Exception;

    /**
     * Retrieve the info of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Layer info
     *
     * @throws Exception if Layer not found
     */
    GPLayerInfo getLayerInfo(Long layerID) throws Exception;

    /**
     * Retrieve the type of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Layer type
     *
     * @throws Exception if Layer not found
     */
    GPLayerType getLayerType(Long layerID) throws Exception;

    /**
     * Retrieve the list of all data source (server URL) of a Project.
     *
     * @param projectID the Project ID
     * @return the list of data source
     *
     * @throws Exception if Project not found
     */
    GetDataSourceResponse getLayersDataSourceByProjectID(Long projectID)
            throws Exception;
    // </editor-fold>
}
