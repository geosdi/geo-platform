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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.slf4j.Logger;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.EPSGLayerData;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.server.service.IPublisherService;
import org.geosdi.geoplatform.gui.server.utility.PublisherFileUtils;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;
import org.geosdi.geoplatform.gui.utility.GPReloadURLException;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayersRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.services.GPPublisherService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("publisherService")
public class PublisherService implements IPublisherService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GPPublisherService geoPlatformPublishClient;
    //
    @Autowired
    private SessionUtility sessionUtility;

    @Override
    public String processEPSGResult(HttpServletRequest httpServletRequest,
            List<EPSGLayerData> previewLayerList, String workspace) throws GeoPlatformException {
        GPAccount account;
        try {
            account = sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        List<InfoPreview> resultList = null;
        try {
            resultList = geoPlatformPublishClient.processEPSGResult(
                    new ProcessEPSGResultRequest(account.getNaturalID(),
                            this.trasformPreviewLayerList(
                                    previewLayerList), workspace)).getInfoPreviews();
        } catch (ResourceNotFoundFault ex) {
            logger.error("Error on publish shape: " + ex.getMessage());
            throw new GeoPlatformException(
                    "Error on publish shape: " + ex.getMessage());
        }
        return PublisherFileUtils.generateJSONObjects(resultList);
    }

    private ArrayList<InfoPreview> trasformPreviewLayerList(
            List<EPSGLayerData> previewLayerList) {
        ArrayList<InfoPreview> infoPreviewList = Lists.<InfoPreview>newArrayList();
        InfoPreview infoPreview;
        for (EPSGLayerData previewLayer : previewLayerList) {
            infoPreview = new InfoPreview(null, null,
                    previewLayer.getFeatureName(), 0d, 0d, 0d, 0d,
                    previewLayer.getEpsgCode(), previewLayer.getStyleName(),
                    previewLayer.isIsShape(), previewLayer.getAlreadyExists());
            if (previewLayer.getPublishAction() != null) {
                infoPreview.setLayerPublishAction(LayerPublishAction.valueOf(
                        previewLayer.getPublishAction()));
                infoPreview.setNewName(previewLayer.getNewName());
                infoPreview.setFileName(previewLayer.getFileName());
            }
            infoPreviewList.add(infoPreview);
            logger.info("Layer preview transformed: " + infoPreview.toString());
        }
        return infoPreviewList;
    }

    @Override
    public String publishLayerPreview(HttpServletRequest httpServletRequest,
            List<String> layerList, String workspace) throws
            GeoPlatformException {
        try {
            sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        String result = null;
        try {
            geoPlatformPublishClient.publishAll(new PublishLayersRequest(
                    httpServletRequest.getSession().getId(), workspace,
                    "dataTest", layerList));

        } catch (ResourceNotFoundFault ex) {
            logger.error("Error on publish shape: " + ex.getMessage());
            throw new GeoPlatformException("Error on publish shape.");
        } catch (FileNotFoundException ex) {
            logger.error("Error on publish shape: " + ex);
            throw new GeoPlatformException("Error on publish shape.");
        } catch (IOException e) {
            logger.error("Error on reloading cluster: " + e);
            throw new GeoPlatformException(new GPReloadURLException(
                    "Error on reloading cluster."));
        }
        return result;
    }

    @Override
    public boolean createWorkspace(String workspaceName, boolean silent,
            HttpServletRequest httpServletRequest) throws GeoPlatformException {
        try {
            sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        boolean result = false;
        try {
            result = geoPlatformPublishClient.createWorkspace(workspaceName, silent);
        } catch (ResourceNotFoundFault ex) {
            logger.error("Error on creating workspace: " + ex);
            throw new GeoPlatformException(ex.getMessage());
        }
        return result;
    }

    @Override
    public void kmlPreview(HttpServletRequest httpServletRequest, String url)
            throws GeoPlatformException {
        // TODO
    }

    /**
     *
     * @param geoPlatformPublishClient
     */
    @Autowired
    public void setGeoPlatformPublishClient(
            @Qualifier("geoPlatformPublishClient") GPPublisherService geoPlatformPublishClient) {
        this.geoPlatformPublishClient = geoPlatformPublishClient;
    }
}
