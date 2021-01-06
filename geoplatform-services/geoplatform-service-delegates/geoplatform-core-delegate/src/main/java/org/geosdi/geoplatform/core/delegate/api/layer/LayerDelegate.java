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
package org.geosdi.geoplatform.core.delegate.api.layer;

import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.request.layer.*;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.collection.LongListStore;
import org.geosdi.geoplatform.services.core.api.resources.GPLayerResource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface LayerDelegate extends GPLayerResource {

    @Override
    Long insertLayer(InsertLayerRequest layerRequest) throws
            IllegalParameterFault;

    @Override
    Long updateRasterLayer(GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Boolean deleteLayer(Long layerID) throws ResourceNotFoundFault;

    @Override
    Long saveAddedLayerAndTreeModifications(
            WSAddLayerAndTreeModificationsRequest addLayerRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    LongListStore saveAddedLayersAndTreeModifications(WSAddLayersAndTreeModificationsRequest addLayersRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Boolean saveDeletedLayerAndTreeModifications(
            WSDeleteLayerAndTreeModificationsRequest deleteLayerRequest)
            throws ResourceNotFoundFault;

    @Override
    Boolean saveCheckStatusLayerAndTreeModifications(Long layerID,
            boolean checked) throws ResourceNotFoundFault;

    boolean fixCheckStatusLayerAndTreeModifications(Long layerID,
            Long oldFolderID, Long newFolderID) throws ResourceNotFoundFault;

    @Override
    Boolean saveDragAndDropLayerAndTreeModifications(
            WSDDLayerAndTreeModificationsRequest ddLayerReq)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Boolean saveLayerProperties(RasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    GPLayer getLayerDetail(Long layerID) throws ResourceNotFoundFault;

    @Override
    GPRasterLayerResponse getRasterLayer(Long layerID) throws
            ResourceNotFoundFault;

    @Override
    GPVectorLayerResponse getVectorLayer(Long layerID) throws
            ResourceNotFoundFault;

    @Override
    ShortLayerDTO getShortLayer(Long layerID) throws
            ResourceNotFoundFault;

    @Override
    ShortLayerDTOContainer getLayers(Long projectID);

    @Override
    GPBBox getBBox(Long layerID) throws ResourceNotFoundFault;

    @Override
    GPLayerInfo getLayerInfo(Long layerID) throws ResourceNotFoundFault;

    @Override
    GPLayerType getLayerType(Long layerID) throws ResourceNotFoundFault;

    @Override
    GetDataSourceResponse getLayersDataSourceByProjectID(Long projectID)
            throws ResourceNotFoundFault;

}
