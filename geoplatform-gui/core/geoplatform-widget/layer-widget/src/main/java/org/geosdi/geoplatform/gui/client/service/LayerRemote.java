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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtect;
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
@RemoteServiceRelativePath("LayerRemote")
@XsrfProtect
public interface LayerRemote extends RemoteService {

    public static class Util {

        private static final LayerRemoteAsync instance = (LayerRemoteAsync) GWT.create(
                LayerRemote.class);

        public static LayerRemoteAsync getInstance() {
            return instance;
        }
    }

    @Deprecated
    GPClientProject loadDefaultProject() throws GeoPlatformException;

    @Deprecated
    boolean shareProjectToUsers(long idSharedProject,
            List<Long> accountIDsProject) throws GeoPlatformException;

    @Deprecated
    ArrayList<GPSimpleUser> getOrganizationUsersToShareProject(long projectId)
            throws GeoPlatformException;

    ArrayList<GPSimpleUser> getOrganizationUsers() throws GeoPlatformException;

    ArrayList<GPSimpleUser> getAccountsFromSharedProject(long idSharedProject)
            throws GeoPlatformException;

    GPClientProject loadDefaultProjectElements() throws GeoPlatformException;

//    ArrayList<GPFolderClientInfo> loadProject(long projectId) throws GeoPlatformException;

    /**
     *
     * @param folderID
     * @return
     * @throws GeoPlatformException
     */
    ArrayList<IGPFolderElements> loadFolderElements(Long folderID) throws GeoPlatformException;

    /**
     *
     * @param MementoSaveAdd
     * @return
     * @throws GeoPlatformException
     */
    Long saveAddedFolderAndTreeModifications(MementoSaveAddedFolder memento)
            throws GeoPlatformException;

    /**
     *
     * @param MementoSaveRemove
     * @return
     * @throws GeoPlatformException
     */
    boolean saveDeletedFolderAndTreeModifications(MementoSaveRemove memento)
            throws GeoPlatformException;

    /**
     *
     * @param MementoSaveRemove
     * @return
     * @throws GeoPlatformException
     */
    boolean saveDeletedLayerAndTreeModifications(MementoSaveRemove memento)
            throws GeoPlatformException;

    /**
     *
     * @param MementoSaveDragDrop
     * @return
     * @throws GeoPlatformException
     */
    boolean saveDragAndDropLayerAndTreeModifications(MementoSaveDragDrop memento)
            throws GeoPlatformException;

    /**
     *
     * @param MementoSaveDragDrop
     * @return
     * @throws GeoPlatformException
     */
    boolean saveDragAndDropFolderAndTreeModifications(
            MementoSaveDragDrop memento) throws GeoPlatformException;

    /**
     *
     * @param MementoSaveCheck
     * @return
     * @throws GeoPlatformException
     */
    boolean saveCheckStatusFolderAndTreeModifications(MementoSaveCheck memento)
            throws GeoPlatformException;

    /**
     *
     * @param MementoSaveCheck
     * @return
     * @throws GeoPlatformException
     */
    boolean saveCheckStatusLayerAndTreeModifications(MementoSaveCheck memento)
            throws GeoPlatformException;

    /**
     *
     * @param MementoLayerOriginalProperties
     * @return
     * @throws GeoPlatformException
     */
    boolean saveLayerProperties(MementoLayerOriginalProperties memento) throws GeoPlatformException;

    /**
     *
     * @param folderName
     * @param position
     * @return
     * @throws GeoPlatformException
     */
    Long saveFolderForUser(String folderName,
            int position,
            int numberOfDescendants,
            boolean isChecked) throws GeoPlatformException;

    /**
     *
     * @param idParentFolder
     * @param folderName
     * @param positions
     * @return
     * @throws GeoPlatformException
     */
    Long saveFolder(Long idParentFolder,
            String folderName,
            int position,
            int numberOfDescendants,
            boolean isChecked) throws GeoPlatformException;

    /**
     *
     * @param id
     * @param elementType
     * @throws GeoPlatformException
     */
    void deleteElement(Long id,
            TreeElement elementType) throws GeoPlatformException;

    /**
     *
     * @param url
     * @return
     * @throws GeoPlatformException
     */
    boolean checkWmsGetMapUrl(String url) throws GeoPlatformException;

    /**
     *
     * @param url
     * @return
     * @throws GeoPlatformException
     */
    boolean checkKmlUrl(String url) throws GeoPlatformException;

    /**
     *
     * @param config
     * @param searchText
     * @return PagingLoadResult<GPClientProject>
     *
     * @throws GeoPlatformException
     */
    PagingLoadResult<GPClientProject> searchProjects(PagingLoadConfig config,
            String searchText,
            String imageURL) throws GeoPlatformException;

    /**
     *
     * @param projectID
     * @throws GeoPlatformException
     */
    void setDefaultProject(Long projectID) throws GeoPlatformException;

    /**
     *
     * @param project
     * @return projectID
     * @throws GeoPlatformException
     */
    Long saveProject(GPClientProject project) throws GeoPlatformException;

    /**
     *
     * @param project
     * @throws GeoPlatformException
     */
    void updateProject(GPClientProject project) throws GeoPlatformException;

    /**
     *
     * @param projectID
     * @throws GeoPlatformException
     */
    void deleteProject(Long projectID) throws GeoPlatformException;

    /**
     *
     * @param username
     * @param layerUUID
     * @param secondToRefresh
     * @throws GeoPlatformException
     */
    void setLayerRefreshTime(String emiteResource,
            String layerUUID,
            int secondToRefresh) throws GeoPlatformException;

    String getLayerDimension(String layerName) throws GeoPlatformException;

//    String checkCQLExpression(String CQLExpression) throws GeoPlatformException;
    List<GPLayerAttributes> describeFeatureType(String layerName) throws GeoPlatformException;
}
