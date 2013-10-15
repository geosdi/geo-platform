/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.services.feature;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.wfs.responce.AttributeDTO;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Service("gpTransactionService")
public class GPTransactionService extends AbstractFeatureService
        implements TransactionService {

    @Override
    public boolean transactionUpdate(String serverURL, String typeName,
            String fid, List<? extends AttributeDTO> attributes) throws Exception {
        assert (serverURL != null);
        assert (typeName != null);
        assert (fid != null);
        assert (attributes != null && attributes.size() > 0);

        logger.debug("\n*** WFS Transaction UPDATE for layer '{}' ***", typeName);
        if (!typeName.contains(":")) {
            throw new IllegalParameterFault(
                    "typeName must contain the char \":\"");
        }

        serverURL = serverURL.replace("wms", "wfs");
        if (!wfsConfigurator.matchDefaultDataSource(serverURL)) {
            throw new ResourceNotFoundFault(
                    "Edit Mode cannot be applied to the server with url "
                    + wfsConfigurator.getDefaultWFSDataSource());
        }

        try {
            GPWFSConnectorStore serverConnector = super.createWFSConnector(
                    serverURL);
            WFSTransactionRequest<TransactionResponseType> request =
                    serverConnector.createTransactionRequest();

            request.setOperation(TransactionOperation.UPDATE);
            request.setTypeName(new QName(typeName));
            request.setFID(fid);
            request.setAttributes(attributes);

            TransactionResponseType response = request.getResponse();
            if (response.getTransactionSummary().getTotalUpdated().intValue() == 1) {
                return true;
            }

        } catch (ServerInternalFault ex) {
            logger.error("\n### ServerInternalFault: {} ###", ex.getMessage());
        } catch (IOException ex) {
            logger.error("\n### IOException: {} ###", ex.getMessage());
            throw new ResourceNotFoundFault(
                    "Error to execute the WFS Transacion Update for the layer "
                    + typeName + " - fid: " + fid);
        }

        return false;
    }

    @Override
    public boolean transactionInsert(String serverURL, String typeName,
            String targetNamespace, List<AttributeDTO> attributes)
            throws Exception {
        assert (serverURL != null);
        assert (typeName != null);
        assert (targetNamespace != null);
        assert (attributes != null && attributes.size() > 0);

        logger.debug("\n\n*** WFS Transaction INSERT for layer '{}' ***",
                typeName);
        if (!typeName.contains(":")) {
            throw new IllegalParameterFault(
                    "typeName must contain the char \":\"");
        }

        serverURL = serverURL.replace("wms", "wfs");
        if (!wfsConfigurator.matchDefaultDataSource(serverURL)) {
            throw new ResourceNotFoundFault(
                    "Edit Mode cannot be applied to the server with url "
                    + wfsConfigurator.getDefaultWFSDataSource());
        }

        try {
            GPWFSConnectorStore serverConnector = super.createWFSConnector(
                    serverURL);
            WFSTransactionRequest<TransactionResponseType> request =
                    serverConnector.createTransactionRequest();

            StringTokenizer st = new StringTokenizer(typeName, ":");
            String wk = st.nextToken();

            QName qName = new QName(targetNamespace, typeName, wk);

            request.setOperation(TransactionOperation.INSERT);
            request.setTypeName(qName);
            request.setAttributes(attributes);

            TransactionResponseType response = request.getResponse();
            if (response.getTransactionSummary().getTotalInserted().intValue() == 1) {
                return true;
            }

        } catch (ServerInternalFault ex) {
            logger.error("\n### ServerInternalFault: {} ###", ex.getMessage());
        } catch (IOException ex) {
            logger.error("\n### IOException: {} ###", ex.getMessage());
            throw new ResourceNotFoundFault(
                    "Error to execute the WFS Transacion Insert for the layer "
                    + typeName);
        }

        return false;
    }

}
