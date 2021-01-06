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
package org.geosdi.geoplatform.gui.client.service;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface LayerRemoteAsync {

    void loadDefaultProject(AsyncCallback<GPClientProject> callback);

    void shareProjectToUsers(long idSharedProject,
            List<Long> accountIDsProject,
            AsyncCallback<Boolean> callback);

    void getOrganizationUsersToShareProject(long projectId,
            AsyncCallback<ArrayList<GPSimpleUser>> callback);

    void getOrganizationUsers(AsyncCallback<ArrayList<GPSimpleUser>> callback);

    void getAccountsFromSharedProject(long idSharedProject,
            AsyncCallback<ArrayList<GPSimpleUser>> callback);

    void loadDefaultProjectElements(AsyncCallback<GPClientProject> callback);

//    void loadProject(long projectId,
//            AsyncCallback<ArrayList<GPFolderClientInfo>> callback);

    void loadFolderElements(Long folderID,
            AsyncCallback<ArrayList<IGPFolderElements>> callback);

    void saveAddedFolderAndTreeModifications(MementoSaveAddedFolder memento,
            AsyncCallback<Long> callback);

    void saveDeletedFolderAndTreeModifications(MementoSaveRemove memento,
            AsyncCallback<Boolean> callback);

    void saveDeletedLayerAndTreeModifications(MementoSaveRemove memento,
            AsyncCallback<Boolean> callback);

    void saveDragAndDropFolderAndTreeModifications(MementoSaveDragDrop memento,
            AsyncCallback<Boolean> callback);

    void saveDragAndDropLayerAndTreeModifications(MementoSaveDragDrop memento,
            AsyncCallback<Boolean> callback);

    void saveCheckStatusLayerAndTreeModifications(MementoSaveCheck memento,
            AsyncCallback<Boolean> callback);

    void saveCheckStatusFolderAndTreeModifications(MementoSaveCheck memento,
            AsyncCallback<Boolean> callback);

    void saveLayerProperties(MementoLayerOriginalProperties memento,
            AsyncCallback<Boolean> callback);

    void saveFolderForUser(String folderName,
            int position,
            int numberOfDescendants,
            boolean isChecked,
            AsyncCallback<Long> callback);

    void saveFolder(Long idParentFolder,
            String folderName,
            int position,
            int numberOfDescendants,
            boolean isChecked,
            AsyncCallback<Long> callback);

    void deleteElement(Long id,
            TreeElement elementType,
            AsyncCallback<?> callback);

    void checkWmsGetMapUrl(String url,
            AsyncCallback<Boolean> callback);

    void checkKmlUrl(String url,
            AsyncCallback<Boolean> callback);

    void searchProjects(PagingLoadConfig config,
            String searchText,
            String imageURL,
            AsyncCallback<PagingLoadResult<GPClientProject>> callback)
            throws GeoPlatformException;

    void setDefaultProject(Long projectID,
            AsyncCallback<?> callback)
            throws GeoPlatformException;

    void saveProject(GPClientProject project,
            AsyncCallback<Long> callback)
            throws GeoPlatformException;

    void updateProject(GPClientProject project,
            AsyncCallback<?> callback)
            throws GeoPlatformException;

    void deleteProject(Long projectID,
            AsyncCallback<?> callback) throws GeoPlatformException;

    void setLayerRefreshTime(String emiteResource,
            String layerUUID,
            int secondToRefresh,
            AsyncCallback<?> callback) throws GeoPlatformException;

    void getLayerDimension(String layerName,
            AsyncCallback<String> callback) throws GeoPlatformException;

    void describeFeatureType(String layerName, AsyncCallback<List<GPLayerAttributes>> callback);
}
