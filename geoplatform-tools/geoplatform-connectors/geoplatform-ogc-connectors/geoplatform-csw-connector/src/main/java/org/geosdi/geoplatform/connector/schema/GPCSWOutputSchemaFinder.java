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
package org.geosdi.geoplatform.connector.schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.connector.GPCatalogConnectorStore;
import org.geosdi.geoplatform.connector.server.request.CatalogGetCapabilitiesRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.v202.CapabilitiesType;
import org.geosdi.geoplatform.xml.ows.v100.DomainType;
import org.geosdi.geoplatform.xml.ows.v100.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPCSWOutputSchemaFinder implements CSWOutputSchemaFinder {

    private static final Logger logger = LoggerFactory.getLogger(GPCSWOutputSchemaFinder.class);

    /**
     * Retrieve the best OutputSchema for Request.
     *
     * @throws java.lang.Exception
     */
    @Override
    public OutputSchema retrieveBestOutputSchemaForRequest(
            GPCatalogConnectorStore serverConnector, String requestType)
            throws Exception {
        List<String> schemas = this.retrieveRequestOutputSchemas(
                serverConnector, requestType);

        OutputSchema outputSchema = null;
        if (schemas.contains(OutputSchema.GMD.toString())) {
            outputSchema = OutputSchema.GMD;
        } else if (schemas.contains(OutputSchema.ORIGINAL.toString())) {
            outputSchema = OutputSchema.ORIGINAL;
        }

        logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@ OutputSchema: {}\n\n",
                outputSchema);
        return outputSchema;
    }

    /**
     * Retrieve for a Catalog server the OutputSchema supported from Request
     * operation. This information in present into GetCapabilities request.
     */
    private List<String> retrieveRequestOutputSchemas(
            GPCatalogConnectorStore serverConnector, String requestType)
            throws Exception {
        List<String> schemas = null;
        try {
            CatalogGetCapabilitiesRequest<CapabilitiesType> request = serverConnector.createGetCapabilitiesRequest();

            CapabilitiesType response = request.getResponse();

            List<Operation> operationList = response.getOperationsMetadata().getOperation();
            for (Operation operation : operationList) {

                if (requestType.equals(operation.getName())) {
                    List<DomainType> parameterList = operation.getParameter();
                    schemas = new ArrayList<>(parameterList.size());
                    for (DomainType parameter : parameterList) {
                        if ("outputSchema".equals(parameter.getName())) {
                            for (String outputSchemaValue : parameter.getValue()) {
                                logger.trace("\n*** outputSchema available: {}",
                                        outputSchemaValue);
                                schemas.add(outputSchemaValue.trim());
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (IOException ex) {
            logger.error("### IOException: {}", ex.getMessage());
            throw new IllegalParameterFault("Error on parse response stream");
        }

        return schemas;
    }

}
