/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.server.request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.apache.http.util.EntityUtils.consume;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GPPostConnectorRequest<T, Request> extends PostConnectorRequest<T> {

    /**
     * @param server
     */
    protected GPPostConnectorRequest(GPServerConnector server) {
        super(server);
    }

    @Override
    public T getResponse() throws Exception {
        T response;
        HttpPost httpPost = super.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpPost);
        super.checkHttpResponseStatus(httpResponse.getStatusLine().getStatusCode());
        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            InputStream is = responseEntity.getContent();
            Unmarshaller unmarshaller = getUnmarshaller();
            Object content = null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, UTF_8_CHARSERT))) {
                content = unmarshaller.unmarshal(reader);
            } catch (JAXBException ex) {
                logger.error("######################JAXBException : ", ex);
            }
            if ((content == null) || !(content instanceof JAXBElement)) { // ExceptionReport
                logger.error("#############CONTENT : {} #############\n", content);
                throw new IncorrectResponseException();
            }

            response = ((JAXBElement<T>) content).getValue();
            consume(responseEntity);
        } else {
            throw new IncorrectResponseException(CONNECTION_PROBLEM_MESSAGE);
        }
        return response;
    }

    /**
     * @return {@link Request}
     * @throws Exception
     */
    protected abstract Request createRequest() throws Exception;
}