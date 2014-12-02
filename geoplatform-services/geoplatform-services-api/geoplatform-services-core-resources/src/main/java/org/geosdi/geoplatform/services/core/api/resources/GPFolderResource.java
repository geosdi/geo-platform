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

import java.util.List;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.request.folder.InsertFolderRequest;
import org.geosdi.geoplatform.request.folder.WSAddFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.request.folder.WSDeleteFolderAndTreeModifications;
import org.geosdi.geoplatform.response.FolderDTO;
import org.geosdi.geoplatform.response.collection.TreeFolderElementsStore;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPFolderResource {

    // <editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    /**
     * Insert a Folder into a Project.
     *
     * @param insertFolderRequest
     *
     * @return the Folder ID
     *
     * @throws Exception if Project not found or if Folder is not valid
     * @deprecated only for test purpose
     * @see #saveAddedFolderAndTreeModifications(java.lang.Long, java.lang.Long,
     * org.geosdi.geoplatform.core.model.GPFolder,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Deprecated
    Long insertFolder(InsertFolderRequest insertFolderRequest) throws Exception;

    /**
     * Update a Folder.
     *
     * @param folder the Folder to update
     * @return the Folder ID
     *
     * @throws Exception if Folder not found or if Folder is not valid
     * @deprecated only for test purpose
     * @see #saveFolderProperties(java.lang.Long, java.lang.String, boolean,
     * boolean)
     */
    @Deprecated
    Long updateFolder(GPFolder folder) throws Exception;

    /**
     * Delete a Folder by ID.
     *
     * @param folderID the Folder ID
     * @return true if the Folder was deleted
     *
     * @throws Exception if Folder not found
     * @deprecated only for test purpose
     * @see #saveDeletedFolderAndTreeModifications(java.lang.Long,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Deprecated
    Boolean deleteFolder(Long folderID) throws Exception;

    /**
     * Update properties (name, checked and expanded) of a Folder.
     *
     * @param folderID the Folder ID
     * @param folderName the Folder name
     * @param checked the Folder checked
     * @param expanded the Folder expanded
     * @return the Folder ID
     *
     * @throws Exception
     */
    Long saveFolderProperties(Long folderID, String folderName, boolean checked,
            boolean expanded) throws Exception;

    /**
     * Insert a Folder, moreover manage Folder ancestors and positions on tree.
     *
     * @param sftModificationRequest
     * @return the Folder ID
     *
     * @throws Exception if Project or parent Folder not found or if the map of
     * descendants is empty for a folder sibling, or if the Folder is not valid
     */
    Long saveAddedFolderAndTreeModifications(
            WSAddFolderAndTreeModificationsRequest sftModificationRequest)
            throws Exception;

    /**
     * Delete a Folder by ID, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param sdfModificationRequest
     *
     * @return true if the Folder was deleted
     *
     * @throws Exception if Folder not found
     */
    Boolean saveDeletedFolderAndTreeModifications(
            WSDeleteFolderAndTreeModifications sdfModificationRequest)
            throws Exception;

    /**
     * Shift a Folder to a new position with a new parent Folder, and manage
     * positions on tree.
     *
     * @param sddfTreeModificationRequest
     *
     * @return true if the Folder wad shifted
     *
     * @throws Exception if Folder or parent Folder not found
     */
    Boolean saveDragAndDropFolderAndTreeModifications(
            WSDDFolderAndTreeModifications sddfTreeModificationRequest)
            throws Exception;

    /**
     * Retrieve a Folder by ID
     *
     * @param folderID the Folder ID
     * @return the Folder to retrieve
     *
     * @throws Exception if Folder not found
     */
    FolderDTO getShortFolder(Long folderID) throws Exception;

    /**
     * Retrieve a Folder by ID
     *
     * @param folderID the Folder ID
     * @return the Folder to retrieve
     *
     * @throws Exception if Folder not found
     */
    GPFolder getFolderDetail(Long folderID) throws Exception;

    /**
     * Retrieve the children Folder of a Folder by ID.
     *
     * @param folderID the Folder ID
     * @return the list of children Folder
     */
    List<FolderDTO> getChildrenFolders(Long folderID);

    /**
     * Retrieve the children elements (Folders and Layers) of a Folder.
     *
     * @param folderID the Folder ID
     * @return the tree of children elements (Folders and Layers)
     */
    TreeFolderElementsStore getChildrenElements(Long folderID);
    // </editor-fold>
}
