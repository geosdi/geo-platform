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
package org.geosdi.geoplatform.core.delegate.api.folder;

import java.util.List;
import org.geosdi.geoplatform.core.model.GPFolder;
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
import org.geosdi.geoplatform.response.collection.TreeFolderElementsStore;
import org.geosdi.geoplatform.services.core.api.resources.GPFolderResource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface FolderDelegate extends GPFolderResource {

    @Override
    Long insertFolder(InsertFolderRequest insertFolderRequest) throws
            ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Long updateFolder(GPFolder folder) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Override
    Boolean deleteFolder(Long folderID) throws ResourceNotFoundFault;

    @Override
    Long saveFolderProperties(Long folderID, String name, boolean checked,
            boolean expanded) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Override
    Long saveAddedFolderAndTreeModifications(
            WSAddFolderAndTreeModificationsRequest sftModificationRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Boolean saveDeletedFolderAndTreeModifications(
            WSDeleteFolderAndTreeModifications sdfModificationRequest)
            throws ResourceNotFoundFault;

    boolean saveCheckStatusFolderAndTreeModifications(Long folderID,
            boolean checked) throws ResourceNotFoundFault;

    @Override
    Boolean saveDragAndDropFolderAndTreeModifications(
            WSDDFolderAndTreeModifications sddfTreeModificationRequest)
            throws ResourceNotFoundFault;

    @Override
    FolderDTO getShortFolder(Long folderID) throws ResourceNotFoundFault;

    @Override
    GPFolder getFolderDetail(Long folderID) throws ResourceNotFoundFault;

    List<FolderDTO> searchFolders(PaginatedSearchRequest searchRequest);

    List<FolderDTO> getFolders();

    long getFoldersCount(SearchRequest searchRequest);

    List<FolderDTO> getChildrenFoldersByRequest(RequestByID request);

    @Override
    ChildrenFolderStore getChildrenFolders(Long folderID);
    
    @Override
    TreeFolderElementsStore getChildrenElements(Long folderID);

}
