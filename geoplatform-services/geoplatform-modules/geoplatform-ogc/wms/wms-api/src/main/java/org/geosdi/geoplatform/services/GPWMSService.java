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

import org.apache.cxf.annotations.FastInfoset;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geosdi.geoplatform.services.response.GPLayerTypeResponse;
import org.geosdi.geoplatform.services.rs.path.GPServiceRSPathConfig;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Public interface to define the service operations mapped via REST using CXF
 * framework.
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 */
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowOrigins = {
                "*"
        },
        allowCredentials = true,
        maxAge = 1,
        allowHeaders = {
                "authorization", "content-type", "X-HTTP-Method-Override"
        },
        exposeHeaders = {
                "authorization", "content-type", "X-HTTP-Method-Override"
        }
)
@Path(value = GPServiceRSPathConfig.DEFAULT_RS_SERVICE_PATH)
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@FastInfoset(serializerMinAttributeValueSize = 200, serializerMaxAttributeValueSize = 400,
        serializerMinCharacterContentChunkSize = 100, serializerAttributeValueMapMemoryLimit = 200,
        serializerMaxCharacterContentChunkSize = 300, serializerCharacterContentChunkMapMemoryLimit = 200)
@WebService(name = "GPWMSService",
        targetNamespace = "http://services.geo-platform.org/")
public interface GPWMSService {

    // <editor-fold defaultstate="collapsed" desc="WMS">
    // ==========================================================================
    // === WMS
    // ==========================================================================
    @GET
    @Path(value = GPServiceRSPathConfig.GET_WMS_CAPABILITIES)
    @Get
    @HttpResource(location = "/wms/capabilities/{id}")
    @WebResult(name = "Capabilities")
    ServerDTO getCapabilities(@QueryParam(value = "serverUrl")
    @WebParam(name = "serverUrl") String serverUrl, @QueryParam(value = "") @WebParam(name = "request") RequestByID request,
            @QueryParam(value = "token") @WebParam(name = "token") String token,
            @QueryParam(value = "authkey") @WebParam(name = "authkey") String authkey) throws ResourceNotFoundFault;

    @GET
    @Path(value = GPServiceRSPathConfig.GET_WMS_CAPABILITIES)
    @Get
    @HttpResource(location = "/wms/capabilities/{id}")
    @WebResult(name = "Capabilities")
    ServerDTO getCapabilitiesAuth(@QueryParam(value = "serverUrl")
    @WebParam(name = "serverUrl") String serverUrl, @QueryParam(value = "") @WebParam(name = "request") RequestByID request,
            @QueryParam(value = "token") @WebParam(name = "token") String token,
            @QueryParam(value = "authkey") @WebParam(name = "authkey") String authkey,
            @QueryParam(value = "headers") @WebParam(name = "headers") List<WMSHeaderParam> headers) throws ResourceNotFoundFault;

    @GET
    @Path(value = GPServiceRSPathConfig.GET_WMS_SERVER_BY_URL)
    @Get
    @HttpResource(location = "/server/{serverUrl}")
    @WebResult(name = "Servers")
    ServerDTO getShortServer(@QueryParam(value = "serverUrl") @WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    /**
     * @param serverURL
     * @param layerName
     * @return {@link GPLayerTypeResponse}
     * @throws Exception
     */
    @GET
    @Path(value = GPServiceRSPathConfig.GET_LAYER_TYPE)
    @Get
    GPLayerTypeResponse getLayerType(@QueryParam(value = "serverURL") @WebParam(name = "serverURL") String serverURL,
            @QueryParam(value = "layerName") @WebParam(name = "layerName") String layerName) throws Exception;

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @POST
    @Path(value = GPServiceRSPathConfig.WMS_GET_FEATURE_INFO_PATH)
    Response wmsGetFeatureInfo(GPWMSGetFeatureInfoRequest request) throws Exception;
    // </editor-fold>
}