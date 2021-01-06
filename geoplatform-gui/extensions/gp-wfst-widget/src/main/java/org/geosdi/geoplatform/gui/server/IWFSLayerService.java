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
package org.geosdi.geoplatform.gui.server;

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;

import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IWFSLayerService {

    /**
     * @param serverUrl
     * @param typeName
     * @return {@link LayerSchemaDTO}
     * @throws GeoPlatformException
     */
    LayerSchemaDTO describeFeatureType(String serverUrl, String typeName, Map<String, String> headerParams)
            throws GeoPlatformException;

    /**
     * @param serverUrl
     * @param typeName
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws GeoPlatformException
     */
    FeatureCollectionDTO getAllFeature(String serverUrl, String typeName, int maxFeatures,
                                       Map<String, String> headerParams) throws GeoPlatformException;

    /**
     * @param serverUrl
     * @param typeName
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws GeoPlatformException
     */
    FeatureCollectionDTO getFeaturesByQuery(String serverUrl, String typeName, int maxFeatures,
                                            QueryDTO queryDTO, Map<String, String> headerParams) throws GeoPlatformException;

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    boolean transactionUpdate(String serverURL, String typeName, String fid,
                              List<? extends AttributeDTO> attributes, Map<String, String> headerParams) throws GeoPlatformException;

    /**
     * @param serverURL
     * @param typeName
     * @param targetNamespace
     * @param attributes
     * @param headerParams
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    boolean transactionInsert(String serverURL, String typeName, String targetNamespace, List<AttributeDTO> attributes,
                              Map<String, String> headerParams) throws GeoPlatformException;

    /**
     * @param serverURL
     * @param typeName
     * @param fid
     * @param headerParams
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    boolean transactionDelete(String serverURL, String typeName, String fid, Map<String, String> headerParams)
            throws GeoPlatformException;

}
