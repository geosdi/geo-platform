/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveRemove;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.geosdi.geoplatform.gui.server.service.converter.DTOConverter;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
@Service("layerService")
public class LayerService implements ILayerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GeoPlatformService geoPlatformServiceClient;
    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public ArrayList<GPFolderClientInfo> loadUserFolders(String userName) throws GeoPlatformException {
        SearchRequest userNameSearch = new SearchRequest(userName);

        GPUser user = null;
        try {
            user = geoPlatformServiceClient.getUserDetailByName(userNameSearch);
        } catch (ResourceNotFoundFault e) {
            logger.error("LayerService",
                    "Unable to find user with username: " + userNameSearch.getNameLike());
            throw new GeoPlatformException(
                    "Unable to find user with username: " + userNameSearch.getNameLike());
        }

        RequestById idRequest = new RequestById(user.getId());
        List<FolderDTO> folderList = geoPlatformServiceClient.getUserFoldersByRequest(
                idRequest);

        return this.dtoConverter.convertOnlyFolder(folderList);
    }

    @Override
    public ArrayList<IGPFolderElements> loadFolderElements(long folderId) throws GeoPlatformException {
        TreeFolderElements folderElements = geoPlatformServiceClient.getChildrenElements(
                folderId);
        ArrayList<IGPFolderElements> elements = new ArrayList<IGPFolderElements>();
        try {
            folderElements.isEmpty();
            elements = this.dtoConverter.convertFolderElements(folderElements);
        } catch (Exception e) {
            logger.debug("Returning no elements: " + e);
        }
        return elements;
    }

    @Override
    public long saveFolderForUser(String folderName, int position,
            int numberOfDescendants, boolean isChecked) throws GeoPlatformException {
        GPUser user = null;
        try {
            user = geoPlatformServiceClient.getUserDetailByName(new SearchRequest(
                    "user_test_0"));
        } catch (ResourceNotFoundFault ex) {
            logger.error("LayerService",
                    "Unable to find user with username : user_test_0");
            throw new GeoPlatformException(ex);
        }

        GPFolder folder = new GPFolder();
        folder.setName(folderName);
        folder.setPosition(position);
        folder.setShared(false);
        folder.setOwner(user);
        folder.setNumberOfDescendants(numberOfDescendants);
        folder.setChecked(isChecked);

        return this.geoPlatformServiceClient.insertFolder(folder);
    }

    @Override
    public long saveFolder(long idParentFolder, String folderName, int position,
            int numberOfDescendants, boolean isChecked)
            throws GeoPlatformException {
        GPFolder gpFolder = null;

        try {
            gpFolder = geoPlatformServiceClient.getFolderDetail(new RequestById(
                    idParentFolder));
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

        return geoPlatformServiceClient.insertFolder(folder);
    }

    @Override
    public void deleteElement(long id, TreeElement elementType) throws GeoPlatformException {
        switch (elementType) {
            case FOLDER:
                deleteFolder(id);
                break;
            case LAYER:
                deleteLayer(id);
                break;
        }
    }

    /**
     * @param geoPlatformServiceClient the geoPlatformServiceClient to set
     */
    @Autowired
    public void setGeoPlatformServiceClient(
            @Qualifier("geoPlatformServiceClient") GeoPlatformService geoPlatformServiceClient) {
        this.geoPlatformServiceClient = geoPlatformServiceClient;
    }

    private void deleteFolder(long id) throws GeoPlatformException {
        try {
            this.geoPlatformServiceClient.deleteFolder(new RequestById(id));
        } catch (Exception ex) {
            logger.error("LayerService",
                    "Ubable to delete Folder with ID : " + id);
            throw new GeoPlatformException(
                    "The Folder with ID : " + id + " was deleted.");
        }
    }

    private void deleteLayer(long id) throws GeoPlatformException {
        try {
            this.geoPlatformServiceClient.deleteLayer(new RequestById(id));
        } catch (Exception ex) {
            logger.error("LayerService",
                    "Ubable to delete Layer with ID : " + id);
            throw new GeoPlatformException(
                    "The Layer with ID : " + id + " was deleted.");
        }
    }

    @Override
    public long saveAddedFolderAndTreeModifications(
            MementoSaveAddedFolder memento) throws GeoPlatformException {
        GPFolder gpFolder = this.dtoConverter.convertMementoFolder(
                memento.getAddedFolder());
        GPWebServiceMapData<Long, Integer> map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        long idSavedFolder = 0L;
        try {
            idSavedFolder = this.geoPlatformServiceClient.saveAddedFolderAndTreeModifications(
                    gpFolder, map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to save folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return idSavedFolder;
    }

    @Override
    public ArrayList<Long> saveAddedLayersAndTreeModifications(MementoSaveAddedLayers memento) throws GeoPlatformException {
        ArrayList<GPLayer> layersList = this.dtoConverter.convertMementoLayers(memento.getAddedLayers());
        GPWebServiceMapData<Long, Integer> map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        ArrayList<Long> idSavedLayers = null;
        try {
            idSavedLayers = this.geoPlatformServiceClient.saveAddedLayersAndTreeModifications("user_test_0", layersList, map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to save layers on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ex) {
            this.logger.error("Failed to save layers on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return idSavedLayers;
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(
            MementoSaveRemove memento) throws GeoPlatformException {
        GPWebServiceMapData<Long, Integer> map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveDeletedFolderAndTreeModifications(
                    memento.getIdBaseElement(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to delete folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ex) {
            this.logger.error("Failed to delete folder on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(
            MementoSaveRemove memento) throws GeoPlatformException {
        GPWebServiceMapData<Long, Integer> map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveDeletedLayerAndTreeModifications(
                    memento.getIdBaseElement(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error("Failed to delete layer on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        } catch (IllegalParameterFault ex) {
            this.logger.error("Failed to delete layer on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveDragAndDropLayerAndTreeModifications(
            MementoSaveDragDrop memento) throws GeoPlatformException {
        GPWebServiceMapData<Long, Integer> map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        try {
            result = this.geoPlatformServiceClient.saveDragAndDropLayerAndTreeModifications(
                    memento.getIdBaseElement(), memento.getIdNewParent(),
                    memento.getNewZIndex(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error(
                    "Failed to save layer drag&drop on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(
            MementoSaveDragDrop memento) throws GeoPlatformException {
        GPWebServiceMapData<Long, Integer> map = this.dtoConverter.convertDescendantMap(
                memento.getWsDescendantMap());
        boolean result = false;
        GPUser user = new GPUser();
        user.setUsername("user_test_0");
        try {
            result = this.geoPlatformServiceClient.saveDragAndDropFolderAndTreeModifications(
                    memento.getIdBaseElement(), memento.getIdNewParent(), user,
                    memento.getNewZIndex(), map);
        } catch (ResourceNotFoundFault ex) {
            this.logger.error(
                    "Failed to save folder drag&drop on LayerService: " + ex);
            throw new GeoPlatformException(ex);
        }
        return result;
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(
            MementoSaveCheck memento) throws GeoPlatformException {
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
    public boolean saveCheckStatusLayerAndTreeModifications(
            MementoSaveCheck memento) throws GeoPlatformException {
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
}
