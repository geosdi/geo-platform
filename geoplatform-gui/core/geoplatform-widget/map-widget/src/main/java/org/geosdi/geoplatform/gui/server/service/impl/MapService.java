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
package org.geosdi.geoplatform.gui.server.service.impl;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.server.service.IMapService;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.viewport.ManageViewportRequest;
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
@Service("mapService")
public class MapService implements IMapService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private SessionUtility sessionUtility;
    private GeoPlatformService geoPlatformServiceClient;

//    @Override
//    public String layerAuthenticate(String userName, String password, String url) throws GeoPlatformException {
//        HTTPUtils hTTPUtils = new HTTPUtils(userName, password);
//        String responce = null;
//        try {
//            logger.info("Layer: " + url);
//            responce = hTTPUtils.get("http://localhost:8989/geoserver");
//            logger.info("Auth Responce: " + responce);
//        } catch (MalformedURLException mfe) {
//            System.out.println("Url tazzo: " + mfe);
//        }
//        return responce;
//    }
    @Override
    public void saveBaseLayer(String baseLayer,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPAccount account;
        Long projectID;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            projectID = this.sessionUtility.getDefaultProject(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        try {
            GPAccountProject accountProject = this.geoPlatformServiceClient.
                    getAccountProjectByAccountAndProjectIDs(account.getId(),
                            projectID);
            accountProject.setBaseLayer(baseLayer);
            this.geoPlatformServiceClient.updateAccountProject(accountProject);
        } catch (ResourceNotFoundFault rnff) {
            logger.error("Error on MapService: " + rnff);
            throw new GeoPlatformException(rnff);
        } catch (IllegalParameterFault ipf) {
            logger.error("Error on MapService: " + ipf);
            throw new GeoPlatformException(ipf);
        }
    }

    @Override
    public List<GPClientViewport> loadViewportElements(
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPAccount account;
        Long projectID;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            projectID = this.sessionUtility.getDefaultProject(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }

        Collection<GPViewport> viewportCollectionElements = null;
        try {
            GPAccountProject accountProject = this.geoPlatformServiceClient
                    .getAccountProjectByAccountAndProjectIDs(account.getId(),
                            projectID);
            viewportCollectionElements = this.geoPlatformServiceClient.getAccountProjectViewports(
                    accountProject.getId()).getViewports();
        } catch (ResourceNotFoundFault rnff) {
            logger.error("Error on MapService: " + rnff);
            throw new GeoPlatformException(rnff);
        }
        return this.convertServerViewportToDTO(viewportCollectionElements);
    }

    @Override
    public void replaceViewportList(List<GPClientViewport> viewportList,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPAccount account;
        Long projectID;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
            projectID = this.sessionUtility.getDefaultProject(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        try {
            GPAccountProject accountProject = this.geoPlatformServiceClient.
                    getAccountProjectByAccountAndProjectIDs(account.getId(),
                            projectID);
            this.geoPlatformServiceClient.replaceViewportList(
                    new ManageViewportRequest(accountProject.getId(),
                            convertClientViewportToDTO(viewportList)));
        } catch (ResourceNotFoundFault | IllegalParameterFault rnff) {
            logger.error("Error on MapService: " + rnff);
            throw new GeoPlatformException(rnff);
        }
    }

    private List<GPClientViewport> convertServerViewportToDTO(
            Collection<GPViewport> viewportCollection) {
        List<GPClientViewport> clientViewportList = Lists.<GPClientViewport>newArrayList();
        GPClientViewport clientViewport;
        GPBBox serverBBOX;
        BBoxClientInfo clientBBOX;
        if (viewportCollection != null) {
            for (GPViewport viewport : viewportCollection) {
                serverBBOX = viewport.getBbox();
                clientBBOX = new BBoxClientInfo(serverBBOX.getMinX(),
                        serverBBOX.getMinY(),
                        serverBBOX.getMaxX(), serverBBOX.getMaxY());
                clientViewport = new GPClientViewport(viewport.getName(),
                        viewport.getDescription(), clientBBOX,
                        viewport.getZoomLevel(), viewport.isIsDefault());
                clientViewportList.add(clientViewport);
            }
        }
        return clientViewportList;
    }

    private ArrayList<GPViewport> convertClientViewportToDTO(
            List<GPClientViewport> viewportList) {
        ArrayList<GPViewport> serverViewportList = Lists.<GPViewport>newArrayList();
        GPViewport serverViewport;
        GPBBox serverBBOX;
        BBoxClientInfo clientBBOX;
        if (viewportList != null) {
            for (GPClientViewport viewport : viewportList) {
                clientBBOX = viewport.getBbox();
                serverBBOX = new GPBBox(clientBBOX.getLowerLeftX(),
                        clientBBOX.getLowerLeftY(),
                        clientBBOX.getUpperRightX(), clientBBOX.getUpperRightY());
                serverViewport = new GPViewport(viewport.getName(),
                        viewport.getDescription(),
                        viewport.getZoomLevel(), serverBBOX,
                        viewport.isDefault());
                serverViewportList.add(serverViewport);
            }
        }
        return serverViewportList;
    }

    /**
     * @param geoPlatformServiceClient the geoPlatformServiceClient to set
     */
    @Autowired
    public void setGeoPlatformServiceClient(
            @Qualifier("geoPlatformServiceClient") GeoPlatformService geoPlatformServiceClient) {
        this.geoPlatformServiceClient = geoPlatformServiceClient;
    }
}
