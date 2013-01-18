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
package org.geosdi.geoplatform.connector.server.request.v110;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.xml.bind.Marshaller;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.AbstractTransactionRequest;
import org.geosdi.geoplatform.connector.server.request.ITransactionOperationStrategy;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.GPTransactionMediator;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.FeatureStreamWriter;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSTransactionRequestV110 extends AbstractTransactionRequest<TransactionResponseType> {

    public WFSTransactionRequestV110(GPServerConnector server) {
        super(server);
    }

    @Override
    protected HttpEntity preparePostEntity()
            throws IllegalParameterFault, Exception, UnsupportedEncodingException {
        if (operation != TransactionOperation.INSERT) {
            return super.preparePostEntity();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FeatureStreamWriter streamWriter = new FeatureStreamWriter();
        streamWriter.write(this, outputStream);

        String feature = outputStream.toString("UTF-8");
        logger.debug("\n*** Feature to INSERT ***\n{}\n\n", feature);

        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<wfs:Transaction service=\"WFS\" version=\"1.1.0\" "
                + "xmlns:ogc=\"http://www.opengis.net/ogc\" "
                + "xmlns:wfs=\"http://www.opengis.net/wfs\" "
                + "xmlns:ows=\"http://www.opengis.net/ows\" "
                + "xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:gml=\"http://www.opengis.net/gml\" "
                + "xmlns:" + typeName.getPrefix() + "=\"" + typeName.getNamespaceURI() + "\" "
                + "xmlns:ns6=\"http://www.w3.org/1999/xlink\" "
                + "xmlns:ns7=\"http://www.w3.org/2001/SMIL20/\" "
                + "xmlns:ns8=\"http://www.w3.org/2001/SMIL20/Language\">\n"
                + "<wfs:Insert idgen=\"GenerateNew\" inputFormat=\"text/xml; subtype=gml/3.1.1\">\n"
                + feature
                + "</wfs:Insert>\n"
                + "</wfs:Transaction>";
        logger.trace("\n*** Request TRANSACTION INSERT ***\n{}\n\n", request);

        return new StringEntity(request, ContentType.APPLICATION_XML);
    }

    @Override
    protected Object createRequest() throws IllegalParameterFault {
        if (operation == null) {
            throw new IllegalArgumentException(
                    "Transaction Operation must not be null.");
        }

        ITransactionOperationStrategy operationStrategy = GPTransactionMediator.getStrategy(
                operation);
        Object elementType = operationStrategy.getOperation(this);

        TransactionType request = new TransactionType();
        request.setInsertOrUpdateOrDelete(Arrays.asList(elementType));

        return request;
    }

    @Override
    public String showRequestAsString() throws Exception {
        return operation == TransactionOperation.INSERT ? showRequestWithStax()
               : super.showRequestAsString();
    }

    protected final String showRequestWithStax() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FeatureStreamWriter streamWriter = new FeatureStreamWriter();
        streamWriter.write(this, outputStream);

        return outputStream.toString("UTF-8");
    }
}
