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
package org.geosdi.geoplatform.gui.server;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.model.projects.GPShortClientProject;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;
import org.geosdi.geoplatform.request.project.CloneProjectRequest;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface ILayerService extends InitializingBean {

    GPClientProject loadDefaultProject(HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean shareProjectToUsers(long idSharedProject, List<Long> accountIDsProject, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    ArrayList<GPSimpleUser> getOrganizationUsersToShareProject(long projectId, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    ArrayList<GPSimpleUser> getOrganizationUsers(HttpServletRequest httpServletRequest) throws GeoPlatformException;

    ArrayList<GPSimpleUser> getAccountsFromSharedProject(long idSharedProject, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    ArrayList<GPFolderClientInfo> loadProject(long projectId, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    GPClientProject loadDefaultProjectElements(HttpServletRequest httpServletRequest) throws GeoPlatformException;

    ArrayList<IGPFolderElements> loadFolderElements(Long folderID, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    Long saveAddedFolderAndTreeModifications(MementoSaveAddedFolder memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveDeletedFolderAndTreeModifications(MementoSaveRemove memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveDeletedLayerAndTreeModifications(MementoSaveRemove memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveDragAndDropLayerAndTreeModifications(MementoSaveDragDrop memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveDragAndDropFolderAndTreeModifications(MementoSaveDragDrop memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveCheckStatusFolderAndTreeModifications(MementoSaveCheck memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveCheckStatusLayerAndTreeModifications(MementoSaveCheck memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean saveLayerProperties(MementoLayerOriginalProperties memento, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    Long saveFolderForUser(String folderName, int position, int numberOfDescendants, boolean isChecked, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    Long saveFolder(Long idParentFolder, String folderName, int position, int numberOfDescendants, boolean isChecked, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    void deleteElement(Long id, TreeElement elementType, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    boolean checkWmsGetMapUrl(String url) throws GeoPlatformException;

    boolean checkKmlUrl(String url) throws GeoPlatformException;

    BasePagingLoadResult<GPClientProject> searchProjects(PagingLoadConfig config, String searchText, String imageURL, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    void setDefaultProject(Long projectID, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    Long saveProject(GPClientProject project, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    void updateProject(GPClientProject project, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    void deleteProject(Long projectID, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    void setLayerRefreshTime(String emiteResource, String layerUUID, int secondToRefresh, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    String getLayerDimension(String layerName, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    List<GPLayerAttributes> describeFeatureType(String layerName) throws GeoPlatformException;

    Long cloneProject(CloneProjectRequest request) throws GeoPlatformException;

    GPShortClientProject loadRootElements(Long projectID, HttpServletRequest httpServletRequest) throws GeoPlatformException;
}