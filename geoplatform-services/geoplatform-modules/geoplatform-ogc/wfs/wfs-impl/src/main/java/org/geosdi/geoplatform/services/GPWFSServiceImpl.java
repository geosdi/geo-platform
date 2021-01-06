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

import org.apache.cxf.binding.soap.SoapFault;
import org.geosdi.geoplatform.connector.wfs.response.*;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxAndQueryRequest;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxRequest;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesRequest;
import org.geosdi.geoplatform.support.wfs.services.DescribeFeatureService;
import org.geosdi.geoplatform.support.wfs.services.GetFeaureService;
import org.geosdi.geoplatform.support.wfs.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.forLanguageTag;

/**
 * @author Francesco Izzi <francesco.izzi@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPWFSService")
public class GPWFSServiceImpl implements GPWFSService {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSServiceImpl.class);
    //
    @Autowired
    private DescribeFeatureService gpDescribeFeatureService;
    //
    @Autowired
    private GetFeaureService gpGetFeatureService;
    //
    @Autowired
    private TransactionService gpTransactionService;
    @Resource(name = "wfsMessageSource")
    private MessageSource wfsMessageSource;
    @Resource(name = "wfsRequestValidator")
    private GPI18NValidator<GPI18NRequestValidator, String> wfsRequestValidator;

    /**
     * @param serverURL
     * @param typeName
     * @param headerParams
     * @return {@link LayerSchemaDTO}
     * @throws Exception
     */
    @Override
    public LayerSchemaDTO describeFeatureType(String serverURL, String typeName, Map<String, String> headerParams)
            throws Exception {
        try {
            return gpDescribeFeatureService.describeFeatureType(serverURL, typeName, headerParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("##################DescribeFeatureType Request Error " + "for Layer : {} ",
                    typeName + " - Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }

    }

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param headerParams
     * @return {@link FeatureDTO}
     * @throws Exception
     */
    @Override
    public FeatureDTO getFeatureByFIDDirect(String serverURL, String typeName, String fid,
            Map<String, String> headerParams) throws Exception {
        LayerSchemaDTO layerSchema = this.describeFeatureType(serverURL, typeName, headerParams);
        return this.getFeatureByFID(layerSchema, fid, headerParams);
    }

    /**
     * @param layerSchema
     * @param fid
     * @param headerParams
     * @return {@link FeatureDTO}
     * @throws Exception
     */
    @Override
    public FeatureDTO getFeatureByFID(LayerSchemaDTO layerSchema, String fid, Map<String, String> headerParams)
            throws Exception {
        try {
            return gpGetFeatureService.getFeature(layerSchema, fid, headerParams);
        } catch (Exception ex) {
            logger.error("##################GetFeatureByID Request Error " + "for Feature ID : {}",
                    fid + " - Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param bBox
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeatureByBBoxDirect(String serverURL, String typeName, BBox bBox,
            Map<String, String> headerParams) throws Exception {
        LayerSchemaDTO layerSchema = this.describeFeatureType(serverURL, typeName, headerParams);
        return this.getFeatureByBBox(layerSchema, bBox, headerParams);
    }

    /**
     * @param layerSchema
     * @param bBox
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeatureByBBox(LayerSchemaDTO layerSchema, BBox bBox, Map<String, String> headerParams)
            throws Exception {
        try {
            return gpGetFeatureService.getFeature(layerSchema, bBox, headerParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("##################GetFeatureByBBox Request error - " + "Cause : {}", ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getAllFeatureDirect(String serverURL, String typeName, int maxFeatures,
            Map<String, String> headerParams) throws Exception {
        LayerSchemaDTO layerSchema = this.describeFeatureType(serverURL, typeName, headerParams);
        return this.getAllFeature(layerSchema, maxFeatures, headerParams);
    }

    /**
     * @param serverURL
     * @param typeName
     * @param maxFeatures
     * @param headerParams
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response getGeoJsonFeatures(String serverURL, String typeName, int maxFeatures, Map<String, String> headerParams)
            throws Exception {
        try {
            return Response.ok(this.gpGetFeatureService.getFeature(serverURL, typeName, maxFeatures, headerParams)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalParameterFault(ex.getMessage());
        }
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response searchFeatures(GPWFSSearchFeaturesRequest request) throws Exception {
        if (request == null) {
            throw new IllegalParameterFault(this.wfsMessageSource.getMessage("gp_wfs_request.valid",
                    new Object[]{"GPWFSSearchFeaturesRequest"}, ENGLISH));
        }
        logger.trace("##########################Validating Request -------------------> {}\n", request);
        String message = this.wfsRequestValidator.validate(request, forLanguageTag(request.getLang()));
        if (message != null)
            throw new IllegalParameterFault(message);
        try {
            return Response.ok(this.gpGetFeatureService.searchFeatures(request.getServerURL(), request.getTypeName(),
                    request.getMaxFeatures(), request.getQueryDTO())).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalParameterFault(ex.getMessage());
        }
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response searchFeaturesByBbox(GPWFSSearchFeaturesByBboxRequest request) throws Exception {
        if (request == null) {
            throw new IllegalParameterFault(this.wfsMessageSource.getMessage("gp_wfs_request.valid",
                    new Object[]{"GPWFSSearchFeaturesByBboxRequest"}, ENGLISH));
        }
        logger.trace("##########################Validating Request -------------------> {}\n", request);
        String message = this.wfsRequestValidator.validate(request, forLanguageTag(request.getLang()));
        if (message != null)
            throw new IllegalParameterFault(message);
        try {
            LayerSchemaDTO layerSchemaDTO = this.describeFeatureType(request.getServerURL(), request.getTypeName(), null);
            return Response.ok(this.gpGetFeatureService.searchFeaturesByBbox(layerSchemaDTO, request.getBBox(), request.getMaxFeatures())).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalParameterFault(ex.getMessage());
        }
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response searchFeaturesByBboxAndQuery(GPWFSSearchFeaturesByBboxAndQueryRequest request) throws Exception {
        if (request == null) {
            throw new IllegalParameterFault(this.wfsMessageSource.getMessage("gp_wfs_request.valid",
                    new Object[]{"GPWFSSearchFeaturesByBboxAndQueryRequest"}, ENGLISH));
        }
        logger.trace("##########################Validating Request -------------------> {}\n", request);
        String message = this.wfsRequestValidator.validate(request, forLanguageTag(request.getLang()));
        if (message != null)
            throw new IllegalParameterFault(message);
        try {
            LayerSchemaDTO layerSchemaDTO = this.describeFeatureType(request.getServerURL(), request.getTypeName(), null);
            return Response.ok(this.gpGetFeatureService.searchFeaturesByBboxAndQuery(layerSchemaDTO, request.getQueryDTO(),
                    request.getBBox(), request.getMaxFeatures())).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalParameterFault(ex.getMessage());
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeaturesByQueryDirect(String serverURL, String typeName, int maxFeatures,
            QueryDTO queryDTO, Map<String, String> headerParams) throws Exception {
        LayerSchemaDTO layerSchema = this.describeFeatureType(serverURL, typeName, headerParams);
        return this.getFeaturesByQuery(layerSchema, maxFeatures, queryDTO, headerParams);
    }

    /**
     * @param layerSchema
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeaturesByQuery(LayerSchemaDTO layerSchema, int maxFeatures, QueryDTO queryDTO,
            Map<String, String> headerParams) throws Exception {
        try {
            return gpGetFeatureService.getFeature(layerSchema, maxFeatures, queryDTO, headerParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("##################GetFeatures By Query Request error - " + "Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }

    /**
     * @param layerSchema
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getAllFeature(LayerSchemaDTO layerSchema, int maxFeatures,
            Map<String, String> headerParams) throws Exception {
        try {
            return gpGetFeatureService.getFeature(layerSchema, maxFeatures, headerParams);
        } catch (Exception ex) {
            logger.error("##################GetAllFeatures Request error - " + "Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public boolean transactionUpdate(String serverURL, String typeName, String fid,
            List<? extends AttributeDTO> attributes, Map<String, String> headerParams) throws Exception {
        try {
            return gpTransactionService.transactionUpdate(serverURL, typeName, fid, attributes, headerParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("###################TranctionUpdate Request error for " + "Feature : {}",
                    typeName + " - Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param targetNamespace
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public boolean transactionInsert(String serverURL, String typeName, String targetNamespace,
            List<AttributeDTO> attributes, Map<String, String> headerParams) throws Exception {
        try {
            return gpTransactionService.transactionInsert(serverURL, typeName, targetNamespace, attributes, headerParams);
        } catch (Exception ex) {
            logger.error("###################TransactionInsert error for " + "Feature : {}",
                    typeName + " - Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param headerParams
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public boolean transactionDelete(String serverURL, String typeName, String fid,
            Map<String, String> headerParams) throws Exception {
        try {
            return gpTransactionService.transactionDelete(serverURL, typeName, fid, headerParams);
        } catch (Exception ex) {
            logger.error("###################TranctionDelete Request error for " + "Feature : {}",
                    typeName + " - FID : " + fid + " - Cause : " + ex.getMessage());
            throw new SoapFault(ex.getMessage(), SoapFault.FAULT_CODE_SERVER);
        }
    }
}
