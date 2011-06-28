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
package org.geosdi.geoplatform.gui.server.gwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.geosdi.geoplatform.gui.server.service.impl.LayerService;
import org.geosdi.geoplatform.gui.spring.GeoPlatformContextUtil;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
public class LayerRemoteImpl extends RemoteServiceServlet implements LayerRemote {
    
    private static final long serialVersionUID = -2177973379191108728L;

    private ILayerService layerService = (ILayerService) GeoPlatformContextUtil.getInstance().getBean(
                LayerService.class);

    @Override
    public ArrayList<GPFolderClientInfo> loadUserFolders() throws GeoPlatformException {
        return this.layerService.loadUserFolders(super.getThreadLocalRequest());
    }

    @Override
    public ArrayList<IGPFolderElements> loadFolderElements(long folderId) throws GeoPlatformException {
        return this.layerService.loadFolderElements(folderId, super.getThreadLocalRequest());
    }

    @Override
    public long saveFolderForUser(String folderName, int position, int numberOfDescendants, boolean isChecked) throws GeoPlatformException {
        return this.layerService.saveFolderForUser(folderName, position, numberOfDescendants, 
                isChecked, super.getThreadLocalRequest());
    }

    @Override
    public long saveFolder(long idParentFolder, String folderName, int position, int numberOfDescendants, boolean isChecked) throws GeoPlatformException {
        return this.layerService.saveFolder(idParentFolder, folderName,
                position, numberOfDescendants, isChecked, super.getThreadLocalRequest());
    }

    @Override
    public void deleteElement(long id, TreeElement elementType) throws GeoPlatformException {
        this.layerService.deleteElement(id, elementType, super.getThreadLocalRequest());
    }

    @Override
    public long saveAddedFolderAndTreeModifications(MementoSaveAddedFolder memento) throws GeoPlatformException {
        return this.layerService.saveAddedFolderAndTreeModifications(memento, super.getThreadLocalRequest());
    }
    
    @Override
    public ArrayList<Long> saveAddedLayersAndTreeModifications(MementoSaveAddedLayers memento) throws GeoPlatformException {
        return this.layerService.saveAddedLayersAndTreeModifications(memento, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(MementoSaveRemove memento) throws GeoPlatformException {
        return this.layerService.saveDeletedFolderAndTreeModifications(memento, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(MementoSaveRemove memento) throws GeoPlatformException {
        return this.layerService.saveDeletedLayerAndTreeModifications(memento, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDragAndDropLayerAndTreeModifications(MementoSaveDragDrop memento) throws GeoPlatformException {
        return this.layerService.saveDragAndDropLayerAndTreeModifications(memento, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(MementoSaveDragDrop memento) throws GeoPlatformException {
        return this.layerService.saveDragAndDropFolderAndTreeModifications(memento, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(MementoSaveCheck memento) throws GeoPlatformException {
        return this.layerService.saveCheckStatusFolderAndTreeModifications(memento, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveCheckStatusLayerAndTreeModifications(MementoSaveCheck memento) throws GeoPlatformException {
        return this.layerService.saveCheckStatusLayerAndTreeModifications(memento, super.getThreadLocalRequest());
    }
}
