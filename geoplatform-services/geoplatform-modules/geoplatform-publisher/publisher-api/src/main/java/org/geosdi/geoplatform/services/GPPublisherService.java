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

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayerRequest;
import org.geosdi.geoplatform.request.PublishLayersRequest;
import org.geosdi.geoplatform.request.PublishRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.geosdi.geoplatform.responce.UniqueValuesInfo;
import org.geosdi.geoplatform.services.rs.path.GPPublisherRSPathConfig;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Public interface to define the service operations mapped via REST using CXT
 * framework.
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Path(value = GPPublisherRSPathConfig.DEFAULT_PUBLISHER_RS_SERVICE_PATH)
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@WebService(name = "GPPublisherService",
        targetNamespace = "http://services.geo-platform.org/")
public interface GPPublisherService extends IGPPublisherService {

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.ANALAYZE_ZIP_EPSG_PATH)
    @Consumes(value = {MediaType.MULTIPART_FORM_DATA})
    @HttpResource(location = "/preview/analyzeZIPEPSG")
    @WebResult(name = "Result")
    @Override
    InfoPreviewStore analyzeZIPEPSG(
            @WebParam(name = "sessionID")
            @Multipart(value = "sessionID") String sessionID,
            @WebParam(name = "username")
            @Multipart(value = "userName") String userName,
            @WebParam(name = "fileName")
            @Multipart(value = "fileName",
                    type = MediaType.APPLICATION_OCTET_STREAM) File file,
            @WebParam(name = "workspace")
            @Multipart(value = "workspace", required = false) String workspace)
            throws ResourceNotFoundFault;

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.PROCESS_EPSG_RESULT_PATH)
    @HttpResource(location = "/preview/processEPSGResult")
    @WebResult(name = "Result")
    @Override
    InfoPreviewStore processEPSGResult(
            @WebParam(name = "request") ProcessEPSGResultRequest request)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.LOAD_STYLE_PATH)
    @HttpResource(location = "/preview/loadStyle")
    @WebResult(name = "Result")
    @Override
    String loadStyle(
            @WebParam(name = "layerDatasource")
            @QueryParam(value = "layerDatasource") String layerDatasource,
            @WebParam(name = "styleName")
            @QueryParam(value = "styleName") String styleName)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.DESCRIBE_FEATURE_TYPE_PATH)
    @HttpResource(location = "/preview/loadStyle")
    @WebResult(name = "Result")
    @Override
    LayerAttributeStore describeFeatureType(
            @WebParam(name = "layerName")
            @QueryParam(value = "layerName") String layerName)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.UNIQUE_VALUES_PATH)
    @HttpResource(location = "/preview/uniqueValue")
    @WebResult(name = "Result")
    @Override
    UniqueValuesInfo uniqueValues(
            @WebParam(name = "layerName")
            @QueryParam(value = "layerName") String layerName,
            @WebParam(name = "layerAttribute")
            @QueryParam(value = "layerAttribute") String layerAttribute)
            throws ResourceNotFoundFault;

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.PUBLISH_STYLE_PATH)
    @HttpResource(location = "/preview/publishStyle")
    @WebResult(name = "Result")
    @Override
    Boolean publishStyle(
            @WebParam(name = "styleToPublish")
            @QueryParam(value = "styleToPublish") String styleToPublish,
            @WebParam(name = "styleName")
            @QueryParam(value = "styleName") String styleName,
            @WebParam(name = "validate")
            @QueryParam(value = "validate") boolean validate)
            throws ResourceNotFoundFault;

    @Put
    @PUT
    @Path(value = GPPublisherRSPathConfig.UPDATE_STYLE_PATH)
    @HttpResource(location = "/preview/updateStyle")
    @WebResult(name = "Result")
    @Override
    Boolean updateStyle(
            @WebParam(name = "styleToPublish")
            @QueryParam(value = "styleToPublish") String styleToPublish,
            @WebParam(name = "styleName")
            @QueryParam(value = "styleName") String styleName,
            @WebParam(name = "validate")
            @QueryParam(value = "validate") boolean validate)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.EXISTS_STYLE_PATH)
    @HttpResource(location = "/preview/existsStyle")
    @WebResult(name = "Result")
    @Override
    public Boolean existsStyle(
            @WebParam(name = "styleName")
            @QueryParam(value = "styleName") String styleName);

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.GET_WORKSPACE_NAMES_PATH)
    @HttpResource(location = "/preview/getWorkspaces")
    @WebResult(name = "Result")
    @Override
    public List<String> getWorkspaceNames();

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.ANALYZE_TIF_IN_PREVIEW_PATH)
    @Consumes(value = {MediaType.MULTIPART_FORM_DATA})
    @HttpResource(location = "/preview/uploadZipInPreview")
    @WebResult(name = "Result")
    @Override
    InfoPreview analyzeTIFInPreview(
            @WebParam(name = "sessionID")
            @Multipart(value = "sessionID") String sessionID,
            @WebParam(name = "fileName")
            @Multipart(value = "fileName",
                    type = MediaType.APPLICATION_OCTET_STREAM) File file,
            @WebParam(name = "overwrite")
            @Multipart(value = "overwrite") Boolean overwrite,
            @WebParam(name = "workspace")
            @QueryParam(value = "workspace") String workspace)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.GET_PREVIEW_DATA_STORES_PATH)
    @HttpResource(location = "/preview/getPreviewDataStores")
    @WebResult(name = "Result")
    @Override
    InfoPreviewStore getPreviewDataStores(
            @WebParam(name = "workspace")
            @QueryParam(value = "workspace") String workspace)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPPublisherRSPathConfig.CREATE_WORKSPACE_PATH)
    @HttpResource(location = "/preview/createWorkspace")
    @WebResult(name = "Result")
    @Override
    Boolean createWorkspace(
            @WebParam(name = "workspaceName")
            @QueryParam(value = "workspaceName") String workspaceName,
            @WebParam(name = "silent")
            @QueryParam(value = "silent") boolean silent)
            throws ResourceNotFoundFault;

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.PUBLISH_PATH)
    @HttpResource(location = "/preview/publish")
    @WebResult(name = "Result")
    @Override
    Boolean publish(
            @WebParam(name = "publishRequest") PublishLayerRequest publishRequest)
            throws ResourceNotFoundFault, FileNotFoundException;

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.PUBLISH_ALL_PATH)
    @HttpResource(location = "/preview/publishAll")
    @WebResult(name = "Result")
    @Override
    Boolean publishAll(
            @WebParam(name = "publishRequest") PublishLayersRequest publishRequest)
            throws ResourceNotFoundFault, FileNotFoundException;

    @Post
    @POST
    @Path(value = GPPublisherRSPathConfig.PUBLISH_ALL_OF_PREVIEW_PATH)
    @HttpResource(location = "/preview/publishAllofPreview")
    @WebResult(name = "Result")
    @Override
    Boolean publishAllofPreview(
            @WebParam(name = "publishRequest") PublishRequest publishRequest)
            throws ResourceNotFoundFault, FileNotFoundException;
}
