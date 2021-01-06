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

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.IWFSLayerService;
import org.geosdi.geoplatform.services.GPWFSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Service(value = "wfsLayerService")
public class WFSLayerService implements IWFSLayerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GPWFSService geoPlatformWFSClient;

    /**
     * @param serverUrl
     * @param typeName
     * @param headerParams
     * @return {@link LayerSchemaDTO}
     * @throws GeoPlatformException
     */
    @Override
    public LayerSchemaDTO describeFeatureType(String serverUrl, String typeName, Map<String, String> headerParams)
            throws GeoPlatformException {
        try {
            return geoPlatformWFSClient.describeFeatureType(serverUrl, typeName, headerParams);
        } catch (Exception ex) {
            logger.error("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " +
                    "WFSLayerService#describeFeatureType Error {} @@@@@@@@@@@@@", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param serverUrl
     * @param typeName
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws GeoPlatformException
     */
    @Override
    public FeatureCollectionDTO getAllFeature(String serverUrl, String typeName, int maxFeatures, Map<String, String> headerParams) throws GeoPlatformException {
        logger.info("#####################################EXECUTING : getAllFeature");
        try {
            return geoPlatformWFSClient.getAllFeatureDirect(serverUrl, typeName, maxFeatures, headerParams);
        } catch (Exception ex) {
            logger.warn("\n\n@@@@@@@@@@@@@@@@@@@ WFSLayerService#getAllFeature " + "Error {} @@@@@@@@@@@@@",
                    ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    @Override
    public boolean transactionUpdate(String serverURL, String typeName, String fid,
                                     List<? extends AttributeDTO> attributes, Map<String, String> headerParams) throws GeoPlatformException {
        try {
            return this.geoPlatformWFSClient.transactionUpdate(serverURL, typeName, fid, attributes, headerParams);
        } catch (Exception ex) {
            logger.warn("\n\n@@@@ WFSLayerService Transaction Update Error {} " + "@@@@@@@@@@@@@", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param headerParams
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    @Override
    public boolean transactionDelete(String serverURL, String typeName, String fid, Map<String, String> headerParams)
            throws GeoPlatformException {
        try {
            return this.geoPlatformWFSClient.transactionDelete(serverURL, typeName, fid, headerParams);
        } catch (Exception ex) {
            logger.warn("\n\n@@@@ WFSLayerService Transaction Delete Error {} " + "@@@@@@@@@@@@@", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param serverURL
     * @param typeName
     * @param targetNamespace
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    @Override
    public boolean transactionInsert(String serverURL, String typeName, String targetNamespace,
                                     List<AttributeDTO> attributes, Map<String, String> headerParams) throws GeoPlatformException {
        try {
            return this.geoPlatformWFSClient.transactionInsert(serverURL, typeName, targetNamespace, attributes, headerParams);
        } catch (Exception ex) {
            logger.warn("\n\n@@@@ WFSLayerService Transaction Insert Error {} " + "@@@@@@@@@@@@@", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param serverUrl
     * @param typeName
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws GeoPlatformException
     */
    @Override
    public FeatureCollectionDTO getFeaturesByQuery(String serverUrl, String typeName, int maxFeatures,
                                                   QueryDTO queryDTO, Map<String, String> headerParams) throws GeoPlatformException {
        logger.info("#####################################EXECUTING : getFeaturesByQuery");
        try {
            return this.geoPlatformWFSClient.getFeaturesByQueryDirect(serverUrl, typeName, maxFeatures, queryDTO, headerParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("\n\n@@@@ WFSLayerService Get Feature By Query Error {} " + "@@@@@@@@@@@@@", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param geoPlatformWFSClient the geoPlatformWFSClient to set
     */
    @Autowired
    public void setGeoPlatformWFSClient(@Qualifier(value = "geoPlatformWFSClient") GPWFSService geoPlatformWFSClient) {
        this.geoPlatformWFSClient = geoPlatformWFSClient;
    }
}
