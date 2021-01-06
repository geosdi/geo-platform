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
package org.geosdi.geoplatform.gui.server.gwt;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.geosdi.geoplatform.gui.server.spring.xsrf.GPAutoInjectingXsrfTokenServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LayerRemoteImpl extends GPAutoInjectingXsrfTokenServiceServlet
        implements LayerRemote {

    private static final long serialVersionUID = -2177973379191108728L;
    //
    @Autowired
    private ILayerService layerService;

    /**
     * @deprecated Using command
     * @return
     * @throws GeoPlatformException
     * @deprecated
     */
    @Deprecated
    @Override
    public GPClientProject loadDefaultProjectElements() throws GeoPlatformException {
        return this.layerService.loadDefaultProjectElements(
                super.getThreadLocalRequest());
    }

//    /**
//     * @deprecated Using command
//     * @param projectId
//     * @return
//     * @throws GeoPlatformException
//     */
//    @Deprecated
//    @Override
//    public ArrayList<GPFolderClientInfo> loadProject(long projectId) throws GeoPlatformException {
//        return this.layerService.loadProject(projectId,
//                super.getThreadLocalRequest());
//    }

    /**
     * @deprecated Using command
     * @param folderID
     * @return
     * @throws GeoPlatformException
     */
    @Deprecated
    @Override
    public ArrayList<IGPFolderElements> loadFolderElements(Long folderID)
            throws GeoPlatformException {
        return this.layerService.loadFolderElements(folderID,
                super.getThreadLocalRequest());
    }

    @Override
    public Long saveFolderForUser(String folderName,
            int position,
            int numberOfDescendants,
            boolean isChecked) throws GeoPlatformException {
        return this.layerService.saveFolderForUser(folderName, position,
                numberOfDescendants,
                isChecked,
                super.getThreadLocalRequest());
    }

    @Override
    public Long saveFolder(Long idParentFolder,
            String folderName,
            int position,
            int numberOfDescendants,
            boolean isChecked) throws GeoPlatformException {
        return this.layerService.saveFolder(idParentFolder, folderName,
                position, numberOfDescendants,
                isChecked,
                super.getThreadLocalRequest());
    }

    @Override
    public void deleteElement(Long id,
            TreeElement elementType)
            throws GeoPlatformException {
        this.layerService.deleteElement(id, elementType,
                super.getThreadLocalRequest());
    }

    @Override
    public Long saveAddedFolderAndTreeModifications(
            MementoSaveAddedFolder memento)
            throws GeoPlatformException {
        return this.layerService.saveAddedFolderAndTreeModifications(memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(
            MementoSaveRemove memento)
            throws GeoPlatformException {
        return this.layerService.saveDeletedFolderAndTreeModifications(memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(
            MementoSaveRemove memento)
            throws GeoPlatformException {
        return this.layerService.saveDeletedLayerAndTreeModifications(memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDragAndDropLayerAndTreeModifications(
            MementoSaveDragDrop memento)
            throws GeoPlatformException {
        return this.layerService.saveDragAndDropLayerAndTreeModifications(
                memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(
            MementoSaveDragDrop memento)
            throws GeoPlatformException {
        return this.layerService.saveDragAndDropFolderAndTreeModifications(
                memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(
            MementoSaveCheck memento)
            throws GeoPlatformException {
        return this.layerService.saveCheckStatusFolderAndTreeModifications(
                memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean saveCheckStatusLayerAndTreeModifications(
            MementoSaveCheck memento)
            throws GeoPlatformException {
        return this.layerService.saveCheckStatusLayerAndTreeModifications(
                memento,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean checkWmsGetMapUrl(String url) throws GeoPlatformException {
        return this.layerService.checkWmsGetMapUrl(url);
    }

    @Override
    public boolean checkKmlUrl(String url) throws GeoPlatformException {
        return this.layerService.checkKmlUrl(url);
    }

    @Override
    public boolean saveLayerProperties(MementoLayerOriginalProperties memento)
            throws GeoPlatformException {
        return this.layerService.saveLayerProperties(memento,
                super.getThreadLocalRequest());
    }

    /**
     * @deprecated Using command
     * @param config
     * @param searchText
     * @param imageURL
     * @return
     * @throws GeoPlatformException
     */
    @Deprecated
    @Override
    public PagingLoadResult<GPClientProject> searchProjects(
            PagingLoadConfig config,
            String searchText,
            String imageURL) throws GeoPlatformException {
        return this.layerService.searchProjects(config, searchText, imageURL,
                super.getThreadLocalRequest());
    }

    @Override
    public void setDefaultProject(Long projectID) throws GeoPlatformException {
        this.layerService.setDefaultProject(projectID,
                super.getThreadLocalRequest());
    }

    @Override
    public Long saveProject(GPClientProject project) throws GeoPlatformException {
        return this.layerService.saveProject(project,
                super.getThreadLocalRequest());
    }

    @Override
    public void updateProject(GPClientProject project) throws GeoPlatformException {
        this.layerService.updateProject(project, super.getThreadLocalRequest());
    }

    @Override
    public void deleteProject(Long projectID) throws GeoPlatformException {
        this.layerService.deleteProject(projectID, super.getThreadLocalRequest());
    }

    @Override
    public void setLayerRefreshTime(String emiteResource,
            String layerUUID,
            int secondToRefresh) throws GeoPlatformException {
        this.layerService.setLayerRefreshTime(emiteResource, layerUUID,
                secondToRefresh,
                super.getThreadLocalRequest());
    }

    @Override
    public ArrayList<GPSimpleUser> getOrganizationUsers() throws GeoPlatformException {
        return this.layerService.getOrganizationUsers(
                super.getThreadLocalRequest());
    }

    @Override
    public ArrayList<GPSimpleUser> getOrganizationUsersToShareProject(
            long projectId) throws GeoPlatformException {
        return this.layerService.getOrganizationUsersToShareProject(projectId,
                super.getThreadLocalRequest());
    }

    @Override
    public ArrayList<GPSimpleUser> getAccountsFromSharedProject(
            long idSharedProject) throws GeoPlatformException {
        return this.layerService.getAccountsFromSharedProject(idSharedProject,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean shareProjectToUsers(long idSharedProject,
            List<Long> accountIDsProject) throws GeoPlatformException {
        return this.layerService.shareProjectToUsers(idSharedProject,
                accountIDsProject,
                super.getThreadLocalRequest());
    }

    @Override
    public GPClientProject loadDefaultProject() throws GeoPlatformException {
        return this.layerService.loadDefaultProject(
                super.getThreadLocalRequest());
    }

    /**
     * @deprecated Using CAS command
     * @param layerName
     * @return
     * @throws GeoPlatformException
     */
    @Deprecated
    @Override
    public String getLayerDimension(String layerName) throws GeoPlatformException {
        return this.layerService.getLayerDimension(layerName, super.getThreadLocalRequest());
    }

    /**
     * @deprecated Using CAS command
     * @param layerName
     * @return
     * @throws GeoPlatformException
     */
    @Deprecated
    @Override
    public List<GPLayerAttributes> describeFeatureType(String layerName) throws GeoPlatformException {
        return this.layerService.describeFeatureType(layerName);
    }
}
