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
package org.geosdi.geoplatform.support.wfs.services;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDescribeFeatureService extends AbstractFeatureService implements DescribeFeatureService {

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
        checkArgument((serverURL != null) && !(serverURL.trim().isEmpty()), "The Parameter serverURL must not be null or an empty string.");
        checkArgument((typeName != null) && !(typeName.trim().isEmpty()), "The Parameter typeName must not be null or an empty string.");
        logger.debug("\n*** WFS DescribeFeatureType for layer {} ***", typeName);
        serverURL = serverURL.replace("ows", "wfs").replace("wms", "wfs");
        if (!typeName.contains(":")) {
            throw new IllegalArgumentException("typeName must contain the char \":\"");
        }
//        if (!this.wfsConfigurator.matchDefaultDataSource(serverURL)) {
//            throw new IllegalStateException("Edit Mode cannot be applied to "
//                    + "the server with url : " + serverURL);
//        }
        LayerSchemaDTO layerSchema;
        try {
            GPWFSConnectorStore serverConnector = ((headerParams != null) && (headerParams.size() > 0)) ?
                    super.createWFSConnector(serverURL, headerParams) : super.createWFSConnector(serverURL);
            WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
            QName qName = new QName(typeName);
            request.setTypeName(Arrays.asList(qName));
            Schema response = request.getResponse();
            String name = typeName.substring(typeName.indexOf(":") + 1);
            layerSchema = GP_FEATURE_SCHEMA_READER.getFeature(response, name);
            if (layerSchema == null) {
                logger.error("\n### The layer \"{}\" isn't a feature ###", typeName);
            } else {
                layerSchema.setScope(serverURL);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error to execute the WFS DescribeFeatureType for the layer " + typeName);
        }
        return layerSchema;
    }
}
