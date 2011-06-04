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
package org.geosdi.geoplatform.gui.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAdd;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveRemove;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
@RemoteServiceRelativePath("LayerRemote")
public interface LayerRemote extends RemoteService {

    public static class Util {

        private static LayerRemoteAsync instance;

        public static LayerRemoteAsync getInstance() {
            if (instance == null) {
                instance = (LayerRemoteAsync) GWT.create(LayerRemote.class);
            }
            return instance;
        }
    }

    /**
     * 
     * @param userName
     * @return
     * @throws GeoPlatformException
     */
    public ArrayList<GPFolderClientInfo> loadUserFolders(String userName) throws GeoPlatformException;

    /**
     * 
     * @param folderId
     * @return
     * @throws GeoPlatformException
     */
    public ArrayList<IGPFolderElements> loadFolderElements(long folderId) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveAdd
     * @return
     * @throws GeoPlatformException
     */
    public long saveAddedFolderAndTreeModifications(MementoSaveAdd memento) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveAdd
     * @return
     * @throws GeoPlatformException
     */
    public long saveAddedLayerAndTreeModifications(MementoSaveAdd memento) throws GeoPlatformException;

    
    /**
     * 
     * @param MementoSaveRemove
     * @return
     * @throws GeoPlatformException
     */
    public boolean saveDeletedFolderAndTreeModifications(MementoSaveRemove memento) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveRemove
     * @return
     * @throws GeoPlatformException
     */
    public boolean saveDeletedLayerAndTreeModifications(MementoSaveRemove memento) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveDragDrop
     * @return
     * @throws GeoPlatformException
     */
    public boolean saveDragAndDropLayerAndTreeModifications(MementoSaveDragDrop memento) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveDragDrop
     * @return
     * @throws GeoPlatformException
     */
    public boolean saveDragAndDropFolderAndTreeModifications(MementoSaveDragDrop memento) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveCheck
     * @return
     * @throws GeoPlatformException
     */
    public boolean saveCheckStatusFolderAndTreeModifications(MementoSaveCheck memento) throws GeoPlatformException;

    /**
     * 
     * @param MementoSaveCheck
     * @return
     * @throws GeoPlatformException
     */
    public boolean saveCheckStatusLayerAndTreeModifications(MementoSaveCheck memento) throws GeoPlatformException;

    /**
     * 
     * @param folderName
     * @param position
     * @return
     * @throws GeoPlatformException
     */
    public long saveFolderForUser(String folderName, int position, int numberOfDescendants, boolean isChecked) throws GeoPlatformException;

    /**
     * 
     * @param idParentFolder
     * @param folderName
     * @param positions
     * @return
     * @throws GeoPlatformException
     */
    public long saveFolder(long idParentFolder, String folderName, int position, int numberOfDescendants, boolean isChecked) throws GeoPlatformException;

    /**
     * 
     * @param id
     * @param elementType
     * @throws GeoPlatformException
     */
    public void deleteElement(long id, TreeElement elementType) throws GeoPlatformException;
}
