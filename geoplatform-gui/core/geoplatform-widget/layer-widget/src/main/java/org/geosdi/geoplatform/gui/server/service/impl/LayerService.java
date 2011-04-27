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
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ShortFolder;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
@Service("layerService")
public class LayerService implements ILayerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GeoPlatformService geoPlatformServiceClient;

    @Override
    public List<GPFolderClientInfo> loadUserFolders(String userName) throws GeoPlatformException {
        List<GPFolderClientInfo> userFolders = new ArrayList<GPFolderClientInfo>();
//        TODO: check the right way to retrieve the user folders using the userName property
        SearchRequest userNameSearch = new SearchRequest("user_0");
        GPUser user = null;
        try {
             user = geoPlatformServiceClient.getUserByName(userNameSearch);
        } catch (ResourceNotFoundFault e) {
            GeoPlatformMessage.errorMessage("LayerService", "Unable to find user with username: " + userNameSearch.getNameLike()); 
        }
        RequestById idRequest = new RequestById(user.getId());
        List<GPFolder> folderList = geoPlatformServiceClient.getUserFolders(idRequest);
        for (GPFolder singleFolder : folderList) {
            GPFolderClientInfo folder = new GPFolderClientInfo();
            folder.setLabel(singleFolder.getName());
            folder.setzIndex(singleFolder.getPosition());
        }

        return userFolders;
    }
}
