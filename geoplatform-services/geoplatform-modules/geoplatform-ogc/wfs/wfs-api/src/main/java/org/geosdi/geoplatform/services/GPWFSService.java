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

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.codehaus.jra.Get;
import org.geosdi.geoplatform.connector.wfs.response.*;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxAndQueryRequest;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxRequest;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesRequest;
import org.geosdi.geoplatform.services.rs.path.GPWFSServiceRSPathConfig;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Public interface to define the service operations mapped via REST using CXT
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
@Path(value = GPWFSServiceRSPathConfig.DEFAULT_WFS_RS_SERVICE_PATH)
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@WebService(name = "GPWFSService",
        targetNamespace = "http://services.geo-platform.org/")
public interface GPWFSService {

    /**
     * @param serverURL
     * @param typeName
     * @param headerParams
     * @return {@link LayerSchemaDTO}
     * @throws Exception
     */
    @GET
    @Path(value = GPWFSServiceRSPathConfig.DESCRIBE_FEATURE_TYPE_RS_PATH)
    @Get
    LayerSchemaDTO describeFeatureType(@QueryParam(value = "serverURL") @WebParam(name = "serverURL") String serverURL,
            @QueryParam(value = "typeName") @WebParam(name = "typeName") String typeName,
            @QueryParam(value = "headerParams") @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param headerParams
     * @return {@link FeatureDTO}
     * @throws Exception
     */
    @Get
    FeatureDTO getFeatureByFIDDirect(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "fid") String fid,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param layerSchema
     * @param fid
     * @param headerParams
     * @return {@link FeatureDTO}
     * @throws Exception
     */
    @Get
    FeatureDTO getFeatureByFID(@WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "fid") String fid,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param bBox
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Get
    FeatureCollectionDTO getFeatureByBBoxDirect(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "bBox") BBox bBox,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param layerSchema
     * @param bBox
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Get
    FeatureCollectionDTO getFeatureByBBox(@WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "bBox") BBox bBox, @WebParam(name = "headerParams") Map<String, String> headerParams)
            throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Get
    FeatureCollectionDTO getAllFeatureDirect(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "maxFeatures") int maxFeatures,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param maxFeatures
     * @param headerParams
     * @return {@link Response}
     * @throws Exception
     */
    @GET
    @Path(value = GPWFSServiceRSPathConfig.GET_GEOJSON_FEATURES_RS_PATH)
    Response getGeoJsonFeatures(@QueryParam(value = "serverURL") String serverURL,
            @QueryParam(value = "typeName") String typeName,
            @QueryParam(value = "maxFeatures") @DefaultValue(value = "30") int maxFeatures,
            @QueryParam(value = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @POST
    @Path(value = GPWFSServiceRSPathConfig.WFS_SEARCH_FEATURES_RS_PATH)
    Response searchFeatures(GPWFSSearchFeaturesRequest request) throws Exception;

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @POST
    @Path(value = GPWFSServiceRSPathConfig.WFS_SEARCH_FEATURES_BY_BBOX_RS_PATH)
    Response searchFeaturesByBbox(GPWFSSearchFeaturesByBboxRequest request) throws Exception;

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @POST
    @Path(value = GPWFSServiceRSPathConfig.WFS_SEARCH_FEATURES_BY_BBOX_AND_QUERY_RS_PATH)
    Response searchFeaturesByBboxAndQuery(GPWFSSearchFeaturesByBboxAndQueryRequest request) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Get
    FeatureCollectionDTO getFeaturesByQueryDirect(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "maxFeatures") int maxFeatures,
            @WebParam(name = "queryDTO") QueryDTO queryDTO,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param layerSchema
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Get
    FeatureCollectionDTO getFeaturesByQuery(@WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "maxFeatures") int maxFeatures, @WebParam(name = "queryDTO") QueryDTO queryDTO,
            @WebParam(name = "headerParams") Map<String, String> headerParams)
            throws Exception;

    /**
     * @param layerSchema
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Get
    FeatureCollectionDTO getAllFeature(@WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "maxFeatures") int maxFeatures,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws Exception
     */
    @Get
    boolean transactionUpdate(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "fid") String fid,
            @WebParam(name = "attributes") List<? extends AttributeDTO> attributes,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param targetNamespace
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws Exception
     */
    @Get
    boolean transactionInsert(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "targetNamespace") String targetNamespace,
            @WebParam(name = "attributes") List<AttributeDTO> attributes,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param headerParams
     * @return {@link Boolean}
     * @throws Exception
     */
    @Get
    boolean transactionDelete(@WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName, @WebParam(name = "fid") String fid,
            @WebParam(name = "headerParams") Map<String, String> headerParams) throws Exception;

}
