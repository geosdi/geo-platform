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
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.composite.TreeElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.geosdi.geoplatform.gui.server.service.impl.LayerService;
import org.geosdi.geoplatform.gui.spring.GeoPlatformContextUtil;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
public class LayerRemoteImpl extends RemoteServiceServlet implements LayerRemote {

    //TODO: Insert correct serialVersionUID
    private static final long serialVersionUID = 8244727800484212092L;

    private ILayerService layerService;

    public LayerRemoteImpl() {
        this.layerService = (ILayerService) GeoPlatformContextUtil.getInstance().getBean(
                LayerService.class);
    }

    @Override
    public List<FolderTreeNode> loadUserFolders(String userName) throws GeoPlatformException {
        return this.layerService.loadUserFolders(userName);
    }
    
    @Override
    public List<GPBeanTreeModel> loadFolderElements(long folderId) throws GeoPlatformException {
        return this.layerService.loadFolderElements(folderId);
    }

    @Override
    public long saveFolderForUser(String folderName, int position) throws GeoPlatformException {
        return this.layerService.saveFolderForUser(folderName, position);
    }

    @Override
    public long saveFolder(long idParentFolder, String folderName, int position) throws GeoPlatformException {
        return this.layerService.saveFolder(idParentFolder, folderName,
                position);
    }

    @Override
    public void deleteElement(long id, TreeElement elementType) throws GeoPlatformException {
        this.layerService.deleteElement(id, elementType);
    }

}
