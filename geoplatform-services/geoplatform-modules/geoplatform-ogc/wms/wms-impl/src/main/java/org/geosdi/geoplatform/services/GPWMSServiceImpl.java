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
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.delegate.GPWMSDelagate;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geosdi.geoplatform.services.response.GPLayerTypeResponse;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.ws.rs.core.Response;
import java.util.List;

public class GPWMSServiceImpl implements GPWMSService {

    @Resource(name = "wmsDelegate")
    private GPWMSDelagate wmsDelegate;

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @return {@link ServerDTO}
     * @throws ResourceNotFoundFault
     */
    @Override
    public ServerDTO getCapabilities(String serverUrl, RequestByID request, String token, String authkey) throws ResourceNotFoundFault {
        return this.wmsDelegate.getCapabilities(serverUrl, request, token, authkey);
    }

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @param headers
     * @return {@link ServerDTO}
     * @throws ResourceNotFoundFault
     */
    @Override
    public ServerDTO getCapabilitiesAuth(String serverUrl, RequestByID request, String token, String authkey, List<WMSHeaderParam> headers) throws ResourceNotFoundFault {
        return this.wmsDelegate.getCapabilitiesAuth(serverUrl, request, token, authkey, headers);
    }

    /**
     * @param serverUrl
     * @return {@link ServerDTO}
     * @throws ResourceNotFoundFault
     */
    @Override
    public ServerDTO getShortServer(String serverUrl) throws ResourceNotFoundFault {
        return this.wmsDelegate.getShortServer(serverUrl);
    }

    /**
     * @param serverURL
     * @param layerName
     * @return {@link GPLayerTypeResponse}
     * @throws Exception
     */
    @Override
    public GPLayerTypeResponse getLayerType(String serverURL, String layerName) throws Exception {
        return this.wmsDelegate.getLayerType(serverURL, layerName);
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @WebMethod(exclude = true)
    @Override
    public Response wmsGetFeatureInfo(GPWMSGetFeatureInfoRequest request) throws Exception {
        return Response.ok(this.wmsDelegate.wmsGetFeatureInfo(request)).build();
    }
}