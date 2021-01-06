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
package org.geosdi.geoplatform.services;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayerRequest;
import org.geosdi.geoplatform.request.PublishLayersRequest;
import org.geosdi.geoplatform.request.PublishRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.geosdi.geoplatform.responce.UniqueValuesInfo;

import javax.jws.WebService;
import java.io.File;
import java.io.FileNotFoundException;

@WebService(
        endpointInterface = "org.geosdi.geoplatform.services.GPPublisherService")
public class GPPublisherServiceImpl extends GPPublisherBasicServiceImpl
        implements GPPublisherService {

    public GPPublisherServiceImpl(String RESTURL, String RESTUSER, String RESTPW,
            String geoportalDir) {
        super(RESTURL, RESTUSER, RESTPW, geoportalDir);
    }

    @Override
    public InfoPreviewStore analyzeZIPEPSG(String sessionID,
            String userName, File file, String workspace)
            throws ResourceNotFoundFault {
        return super.analyzeZIPEPSG(sessionID, userName, file, workspace);
    }

    @Override
    public InfoPreviewStore processEPSGResult(ProcessEPSGResultRequest request)
            throws ResourceNotFoundFault {
        return super.processEPSGResult(request);
    }

    @Override
    public String loadStyle(String layerDatasource, String styleName) throws
            ResourceNotFoundFault {
        return super.loadStyle(layerDatasource, styleName);
    }

    @Override
    public LayerAttributeStore describeFeatureType(String layerName) throws
            ResourceNotFoundFault {
        return super.describeFeatureType(layerName);
    }

    @Override
    public UniqueValuesInfo uniqueValues(String layerName, String layerAttribute) throws ResourceNotFoundFault {
        return super.uniqueValues(layerName,layerAttribute);
    }

    @Override
    public Boolean publishStyle(String styleToPublish, String styleName, 
            boolean validate) throws ResourceNotFoundFault {
        return super.publishStyle(styleToPublish, styleName, validate);
    }

    @Override
    public Boolean updateStyle(String styleToPublish, String styleName, 
            boolean validate) throws ResourceNotFoundFault {
        return super.updateStyle(styleToPublish, styleName, validate);
    }

    @Override
    public Boolean existsStyle(String styleName) {
        return super.existsStyle(styleName);
    }

    @Override
    public InfoPreview analyzeTIFInPreview(String userName, File file,
            Boolean overwrite, String workspace) throws ResourceNotFoundFault {
        return super.analyzeTIFInPreview(userName, file, overwrite, workspace);
    }

    @Override
    public InfoPreviewStore getPreviewDataStores(String workspace) throws
            ResourceNotFoundFault {
        return super.getPreviewDataStores(workspace);
    }

    @Override
    public Boolean createWorkspace(String workspaceName, boolean silent) 
            throws ResourceNotFoundFault {
        return super.createWorkspace(workspaceName, silent);
    }

    @Override
    public boolean styleIsValid(Object style) throws ResourceNotFoundFault {
        return super.styleIsValid(style);
    }

    @Override
    public Boolean publish(PublishLayerRequest publishRequest) throws
            ResourceNotFoundFault, FileNotFoundException {
        return super.publish(publishRequest);
    }

    @Override
    public Boolean publishAll(PublishLayersRequest publishRequest) throws
            ResourceNotFoundFault, FileNotFoundException {
        return super.publishAll(publishRequest);
    }

    @Override
    public Boolean publishAllofPreview(PublishRequest publishRequest) throws
            ResourceNotFoundFault, FileNotFoundException {
        return super.publishAllofPreview(publishRequest);
    }

}
