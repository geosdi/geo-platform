/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.service.impl;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.common.collect.Lists;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoFolderOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.server.service.converter.DTOLayerConverter;
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;
import org.geosdi.geoplatform.gui.shared.XMPPSubjectEnum;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.MessageDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.RasterPropertiesDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.geosdi.geoplatform.responce.collection.XmppAttributesMap;
import org.geosdi.geoplatform.services.GPTrackingService;
import org.geosdi.geoplatform.services.GPWFSService;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("layerService")
public class LayerService implements ILayerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GeoPlatformService geoPlatformServiceClient;
    //
    private GPTrackingService geoPlatformTrackingClient;
    //
    private GPWFSService geoPlatformFeatureClient;
    //
    @Autowired
    private DTOLayerConverter dtoConverter;
    //
    @Autowired
    private SessionUtility sessionUtility;
    @Autowired
    private GeoServerRESTReader geoserverRestReader;

    /**
     * @param geoPlatformServiceClient the geoPlatformServiceClient to set
     */
    @Autowired
    public void setGeoPlatformServiceClient(
            @Qualifier("geoPlatformServiceClient") GeoPlatformService geoPlatformServiceClient) {
        this.geoPlatformServiceClient = geoPlatformServiceClient;
    }

    /**
     *
     * @param geoPlatformTrackingClient the geoPlatformTrackingClient to set
     */
    @Autowired
    public void setGeoPlatformTrackingClient(
            @Qualifier("geoPlatformTrackingClient") GPTrackingService geoPlatformTrackingClient) {
        this.geoPlatformTrackingClient = geoPlatformTrackingClient;
    }

    /**
     *
     * @param geoPlatformFeatureClient the geoPlatformFeatureClient to set
     */
    @Autowired
    public void setGeoPlatformWFSClient(
            @Qualifier("geoPlatformWFSClient") GPWFSService geoPlatformFeatureClient) {
        this.geoPlatformFeatureClient = geoPlatformFeatureClient;
    }

    @Override
    public GPClientProject loadDefaultProjectElements(HttpServletRequest httpServletRequest) throws GeoPlatformException {
        Long projectId = null;
        GPAccount account;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            projectId = this.sessionUtility.getDefaultProject(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
//            System.out.println("Session timeout on loadDefaultProjectElements");
            throw new GeoPlatformException(timeout);
        }
        ProjectDTO projectDTO = null;
        try {
            GPProject project = this.geoPlatformServiceClient.getProjectDetail(projectId);
            if (account.isLoadExpandedFolders() || project.isShared()) {
                projectDTO = this.geoPlatformServiceClient.getProjectWithExpandedFolders(projectId, account.getId());
            } else {
                projectDTO = geoPlatformServiceClient.
                        getProjectWithRootFolders(projectId, account.getId());
            }
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Returning no elements: " + rnf);
        }
        return this.dtoConverter.convertToGPClientProject(projectDTO);
    }

    @Override
    public ArrayList<GPFolderClientInfo> loadProject(long projectId, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPAccount account;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }

        List<FolderDTO> folderList = null;
        try {
            ProjectDTO project;
            if (account.isLoadExpandedFolders()) {
                project = geoPlatformServiceClient.getProjectWithExpandedFolders(projectId, account.getId());
            } else {
                project = geoPlatformServiceClient.
                        getProjectWithRootFolders(projectId, account.getId());
            }
            folderList = project.getRootFolders();
        } catch (ResourceNotFoundFault rnf) {
            logger.debug("Returning no elements: " + rnf);
        }
        return this.dtoConverter.convertOnlyFolders(folderList);
    }

    @Override
    public ArrayList<IGPFolderElements> loadFolderElements(Long folderID, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        TreeFolderElements folderElements = geoPlatformServiceClient.getChildrenElements(folderID);
        ArrayList<IGPFolderElements> elementsToReturn = Lists.newArrayListWithCapacity(0);
        try {
            elementsToReturn = this.dtoConverter.convertFolderElements(folderElements);
        } catch (Exception e) {
            logger.debug("Returning no elements: " + e);
        }
        return elementsToReturn;
    }

    @Override
    public Long saveFolderForUser(String folderName, int position,
            int numberOfDescendants, boolean isChecked, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        folder.setPosition(position);
        folder.setShared(false);
        Long projectId;
        try {
            projectId = this.sessionUtility.getDefaultProject(httpServletRequest);
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        folder.setNumberOfDescendants(numberOfDescendants);
        folder.setChecked(isChecked);
        Long savedFolderId = null;
        try {
            savedFolderId = this.geoPlatformServiceClient.insertFolder(projectId, folder);
        } catch (IllegalParameterFault ilg) {
            logger.error(
                    "Error on LayerService: " + ilg);
            throw new GeoPlatformException("Parameter incorrect on saveFoldeForUser");
        } catch (ResourceNotFoundFault rnff) {
            logger.error("Error on LayerService: " + rnff);
            throw new GeoPlatformException(rnff);
        }
        return savedFolderId;
    }

    @Override
    public Long saveFolder(Long idParentFolder, String folderName, int position,
            int numberOfDescendants, boolean isChecked, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        GPFolder gpFolder = null;
        try {
            gpFolder = geoPlatformServiceClient.getFolderDetail(idParentFolder);
        } catch (Exception e) {
            logger.error("LayerService",
                    "Ubable to load Folder with ID : " + idParentFolder);
            throw new GeoPlatformException(
                    "The Folder with ID : " + idParentFolder + " was deleted.");
        }

        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        folder.setPosition(position);
        folder.setShared(false);
        folder.setParent(gpFolder);
        folder.setNumberOfDescendants(numberOfDescendants);
        folder.setChecked(isChecked);
        Long projectId;

//        folder.setProject(project);

        Long savedFolderId = null;
        try {
            projectId = this.sessionUtility.getDefaultProject(httpServletRequest);
            savedFolderId = this.geoPlatformServiceClient.insertFolder(projectId, folder);
        } catch (IllegalParameterFault ilg) {
            logger.error(
                    "Error on LayerService: " + ilg);
            throw new GeoPlatformException("Parameter incorrect on saveFolde");
        } catch (ResourceNotFoundFault rnff) {
            logger.error(
                    "Error on LayerService: " + rnff);
            throw new GeoPlatformException(rnff);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        return savedFolderId;
    }

    @Override
    public void deleteElement(Long id, TreeElement elementType, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        switch (elementType) {
            case FOLDER:
                this.deleteFolder(id);
                break;
            case LAYER:
                this.deleteLayer(id);
                break;
        }
    }

    @Override
    public Long saveAddedFolderAndTreeModifications(
            MementoSaveAddedFolder memento, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        GPFolder gpFolder = this.dtoConverter.convertMementoFolder(memento.getAddedFolder());

        GPWebServiceMapData map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        Long idSavedFolder = null;
        try {
            Long projectId = this.sessionUtility.getDefaultProject(httpServletRequest);
            idSavedFolder = this.geoPlatformServiceClient.saveAddedFolderAndTreeModifications(
                    projectId, memento.getAddedFolder().getIdParent(), gpFolder, map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to save folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ilg) {
            logger.error(
                    "Error on LayerService: " + ilg);
            throw new GeoPlatformException("Parameter incorrect on saveAddedFolderAndTreeModifications");
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        return idSavedFolder;
    }

    @Override
    public ArrayList<Long> saveAddedLayersAndTreeModifications(MementoSaveAddedLayers memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        List<GPLayer> layersList = this.dtoConverter.convertMementoLayers(memento.getAddedLayers());
        GPWebServiceMapData map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());

        ArrayList<Long> idSavedLayers = null;
        try {
            Long projectId = this.sessionUtility.getDefaultProject(httpServletRequest);
            Long parentFolderId = layersList.get(0).getFolder().getId();
            idSavedLayers = this.geoPlatformServiceClient.saveAddedLayersAndTreeModifications(
                    projectId, parentFolderId, layersList, map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to save layers on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ex) {
            this.logger.error("Failed to save layers on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        return idSavedLayers;
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(MementoSaveRemove memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        GPWebServiceMapData map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveDeletedFolderAndTreeModifications(
                    memento.getIdBaseElement(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to delete folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(MementoSaveRemove memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        GPWebServiceMapData map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveDeletedLayerAndTreeModifications(
                    memento.getIdBaseElement(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to delete layer on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveDragAndDropLayerAndTreeModifications(
            MementoSaveDragDrop memento, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPWebServiceMapData map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        try {
            result = this.geoPlatformServiceClient.saveDragAndDropLayerAndTreeModifications(
                    memento.getIdBaseElement(), memento.getIdNewParent(), memento.getNewZIndex(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error(
                    "Failed to save layer drag&drop on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ilg) {
            logger.error(
                    "Error on LayerService: " + ilg);
            throw new GeoPlatformException("Parameter incorrect on saveDragAndDropLayerAndTreeModifications");
        }
        return result;
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(
            MementoSaveDragDrop memento, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPWebServiceMapData map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        try {
            result = this.geoPlatformServiceClient.saveDragAndDropFolderAndTreeModifications(
                    memento.getIdBaseElement(), memento.getIdNewParent(), memento.getNewZIndex(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error(
                    "Failed to save folder drag&drop on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    @Deprecated
    public boolean saveCheckStatusFolderAndTreeModifications(MementoSaveCheck memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveCheckStatusFolderAndTreeModifications(
                    memento.getIdBaseElement(), memento.isChecked());
        } catch (ResourceNotFoundFault ex) {
            this.logger.error(
                    "Failed to save checked folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    @Deprecated
    public boolean saveCheckStatusLayerAndTreeModifications(MementoSaveCheck memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveCheckStatusLayerAndTreeModifications(
                    memento.getIdBaseElement(), memento.isChecked());
        } catch (ResourceNotFoundFault ex) {
            this.logger.error(
                    "Failed to save checked layer on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveLayerProperties(MementoLayerOriginalProperties memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        boolean result = false;
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        RasterPropertiesDTO dto = this.dtoConverter.convertMementoProperties(memento);
        try {
            result = geoPlatformServiceClient.saveLayerProperties(dto);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to save layers on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ex) {
            this.logger.error("Failed to save layers on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveFolderProperties(MementoFolderOriginalProperties memento,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        try {
            geoPlatformServiceClient.saveFolderProperties(memento.getIdBaseElement(),
                    memento.getName(), memento.isChecked(), memento.isExpanded());
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to save folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ex) {
            this.logger.error("Failed to save folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return true;
    }

    @Override
    public boolean checkWmsGetMapUrl(String urlString) throws GeoPlatformException {
        try {
            URL url = new URL(urlString);
            URLConnection myURLConnection = url.openConnection();
            myURLConnection.connect();
            String contentType = myURLConnection.getContentType();
            if (!contentType.contains("xml")) {
                return true;
            }
        } catch (IOException ex) {
            logger.info("Error on executing Check url: " + ex);
            throw new GeoPlatformException("Error on executing ParseURLServlet.");
        }
        return false;
    }

    // TODO Check
    @Override
    public boolean checkKmlUrl(String urlString) throws GeoPlatformException {
        try {
            URL url = new URL(urlString);
            URLConnection myURLConnection = url.openConnection();
            myURLConnection.connect();
//            String contentType = myURLConnection.getContentType();
//            if (!contentType.contains("xml")) {
//                return true;
//            }
        } catch (IOException ex) {
            logger.info("Error on executing Check url: " + ex);
            throw new GeoPlatformException("Error on executing ParseURLServlet.");
        }
        return false;
    }

    @Deprecated
    private void deleteFolder(Long id) throws GeoPlatformException {
        try {
            this.geoPlatformServiceClient.deleteFolder(id);
        } catch (Exception ex) {
            logger.error("LayerService",
                    "Ubable to delete Folder with ID : " + id);
            throw new GeoPlatformException(
                    "The Folder with ID : " + id + " was deleted.");
        }
    }

    @Deprecated
    private void deleteLayer(Long id) throws GeoPlatformException {
        try {
            this.geoPlatformServiceClient.deleteLayer(id);
        } catch (Exception ex) {
            logger.error("LayerService",
                    "Ubable to delete Layer with ID : " + id);
            throw new GeoPlatformException(
                    "The Layer with ID : " + id + " was deleted.");
        }
    }

    @Override
    public PagingLoadResult<GPClientProject> searchProjects(PagingLoadConfig config,
            String searchText, String imageURL, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GPAccount account = null;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }

        int start = config.getOffset();
        SearchRequest srq = new SearchRequest(searchText);
        try {
            Long projectsCount = this.geoPlatformServiceClient.getAccountProjectsCount(account.getId(), srq);

            int page = start == 0 ? start : start / config.getLimit();

            PaginatedSearchRequest psr = new PaginatedSearchRequest(searchText,
                    config.getLimit(), page);

            List<ProjectDTO> projectsDTO = this.geoPlatformServiceClient.searchAccountProjects(account.getId(), psr);

            if (projectsDTO == null) {
                throw new GeoPlatformException("There are no results");
            }

            ArrayList<GPClientProject> clientProjects = new ArrayList<GPClientProject>();

            for (ProjectDTO projectDTO : projectsDTO) {
                GPClientProject clientProject = this.dtoConverter.convertToGPCLientProject(projectDTO, imageURL);
                clientProjects.add(clientProject);
            }

            return new BasePagingLoadResult<GPClientProject>(clientProjects,
                    config.getOffset(), projectsCount.intValue());

        } catch (ResourceNotFoundFault ex) {
            logger.error("An Error Occured : " + ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    @Override
    public void setDefaultProject(Long projectID, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            GPProject updatedProjecd = this.geoPlatformServiceClient.updateDefaultProject(account.getId(), projectID);
            this.sessionUtility.storeLoggedAccountAndDefaultProject(account,
                    updatedProjecd.getId(), httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault ex) {
            logger.error("An Error Occured : " + ex.getMessage());
            throw new GeoPlatformException(ex);
        }
    }

    @Override
    public Long saveProject(GPClientProject project,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {

        Long projectId = null;
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            projectId = this.geoPlatformServiceClient.saveProject(account.getNaturalID(),
                    this.dtoConverter.convertToGProject(project), project.isDefaultProject());

            this.sessionUtility.storeLoggedAccountAndDefaultProject(account,
                    projectId, httpServletRequest);

        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);

        } catch (ResourceNotFoundFault rnf) {
            this.logger.error("Failed to save project on SecurityService: {}", rnf);
            throw new GeoPlatformException(rnf);

        } catch (IllegalParameterFault ilg) {
            logger.error("Error on SecurityService: {}", ilg);
            throw new GeoPlatformException("Parameter incorrect on saveProject");
        }

        return projectId;
    }

    @Override
    public void updateProject(GPClientProject project,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            AccountProjectPropertiesDTO dto = this.dtoConverter.convertToAccountProjectPropertiesDTO(account.getId(),
                    project);
            if (this.geoPlatformServiceClient.saveAccountProjectProperties(dto)) {
                this.sessionUtility.storeLoggedAccountAndDefaultProject(account,
                        project.getId(), httpServletRequest);
            }
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Failed to update project on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        } catch (IllegalParameterFault ilg) {
            logger.error(
                    "Error on SecurityService: " + ilg);
            throw new GeoPlatformException("Parameter incorrect on saveProject");
        } catch (GPSessionTimeout timeout) {
            System.out.println("Session timeout");
            throw new GeoPlatformException(timeout);
        }

    }

    @Override
    public void deleteProject(Long projectID, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {

            this.geoPlatformServiceClient.deleteProject(projectID);
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Failed to Delete project on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        }
    }

    @Override
    public void setLayerRefreshTime(String emiteResource, String layerUUID, int secondToRefresh, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            if (account instanceof GPUser) {
                String username = ((GPUser) account).getUsername();
                if (secondToRefresh > 0) {
                    logger.debug("Request to subscribe layer refresh for: " + username + " - " + layerUUID);
                    httpServletRequest.getSession().setMaxInactiveInterval(-1);
                    this.geoPlatformTrackingClient.subscribeLayerNotification(username,
                            emiteResource, layerUUID, secondToRefresh);
                } else if (account instanceof GPUser) {
                    logger.debug("Request to UNsubscribe layer refresh for: " + username + " - " + layerUUID);
                    this.geoPlatformTrackingClient.unscribeLayerNotification(username, layerUUID);
                }
            }
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
    }

    @Override
    public ArrayList<GPSimpleUser> getOrganizationUsers(HttpServletRequest httpServletRequest) throws GeoPlatformException {
        ArrayList<GPSimpleUser> simpleUserList = null;
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            List<ShortAccountDTO> accounts = this.geoPlatformServiceClient.getAccounts(account.getOrganization().getName());
            simpleUserList = Lists.newArrayList(this.dtoConverter.convertToGPSimpleUser(accounts));
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Failed to load Organization Users on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        }
        return simpleUserList;
    }

    @Override
    public ArrayList<GPSimpleUser> getOrganizationUsersToShareProject(long projectId, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        ArrayList<GPSimpleUser> simpleUserList = null;
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
            List<ShortAccountDTO> accounts = this.geoPlatformServiceClient.getAccountsToShareByProjectID(projectId);
            simpleUserList = Lists.newArrayList(this.dtoConverter.convertToGPSimpleUser(accounts));
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Failed to load Organization Users on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        }
        return simpleUserList;
    }

    @Override
    public ArrayList<GPSimpleUser> getAccountsFromSharedProject(long idSharedProject, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        ArrayList<GPSimpleUser> simpleUserList = null;
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
            List<ShortAccountDTO> accounts = this.geoPlatformServiceClient.getAccountsByProjectID(idSharedProject);
            simpleUserList = Lists.newArrayList(this.dtoConverter.convertToGPSimpleUser(accounts));
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Failed to load Accounts for Shared Project with id: " + idSharedProject + "on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        }
        return simpleUserList;
    }

    @Override
    public boolean shareProjectToUsers(long idSharedProject, List<Long> accountIDsProject, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        boolean result = false;
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            result = this.geoPlatformServiceClient.updateAccountsProjectSharing(idSharedProject, accountIDsProject);
            if (result) {
                MessageDTO message = new MessageDTO();
                message.setCommands(Lists.newArrayList(GPMessageCommandType.OPEN_PROJECT));
                message.setCommandsProperties("" + idSharedProject);
                message.setCreationDate(new Date());
                message.setSenderID(account.getId());
                message.setSubject("Project Shared");
                String sharerName;
                if (account instanceof GPUser) {
                    GPUser user = (GPUser) account;
                    sharerName = user.getName();
                } else {
                    sharerName = account.getNaturalID();
                }
                GPProject project = this.geoPlatformServiceClient.getProjectDetail(idSharedProject);
                message.setText(sharerName + " shared with you the " + project.getName() + " project. Do you want to open it?");
                message.setRecipientIDs(accountIDsProject);
                this.geoPlatformServiceClient.insertMultiMessage(message);
            }
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault rnf) {
            logger.error("Failed to save Shared project to Accounts for Shared Project with id: " + idSharedProject + "on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        }
        return result;
    }

    @Override
    public GPClientProject loadDefaultProject(HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        ProjectDTO projectDTO = null;
        try {
            GPAccount account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            projectDTO = this.geoPlatformServiceClient.getDefaultProjectDTO(account.getId());
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault ex) {
            logger.error("An Error Occured : " + ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
        return this.dtoConverter.convertToGPClientProject(projectDTO);
    }

    @Override
    public void sendSharedProjectNotification(Long projectId, XMPPSubjectEnum subject,
            String text, Map<String, String> attributesMap,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
            logger.debug("Request to send shared project message for projectID: "
                    + projectId + " - with subject: " + subject);
            this.geoPlatformTrackingClient.sendSharedProjectNotification(projectId,
                    subject, text, new XmppAttributesMap(attributesMap));
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (ResourceNotFoundFault rnff) {
            logger.error("An Error Occured on sendSharedProjectNotification: " + rnff);
            throw new GeoPlatformException(rnff);
        }
    }

    @Override
    public String getLayerDimension(String layerName, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
            return this.geoserverRestReader.getDimensions(layerName);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        } catch (MalformedURLException ex) {
            throw new GeoPlatformException(ex);
        }
    }
}
