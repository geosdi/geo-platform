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
package org.geosdi.geoplatform.connector.server.request;

import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.util.EntityUtils.consume;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseConnectorRequest<T, H extends HttpUriRequest> extends GPAbstractConnectorRequest<T> implements GPJAXBConnectorRequest<T> {

    /**
     * @param theServerConnector
     */
    protected GPBaseConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector);
    }

    /**
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T getResponse() throws Exception {
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        CloseableHttpResponse httpResponse = this.securityConnector.secure(this, httpUriRequest);
        super.checkHttpResponseStatus(httpResponse.getStatusLine().getStatusCode());
        HttpEntity responseEntity = httpResponse.getEntity();
        try {
            if (responseEntity != null) {
                return this.readInternal(responseEntity.getContent());
            } else {
                throw new IncorrectResponseException(CONNECTION_PROBLEM_MESSAGE);
            }
        } finally {
            consume(responseEntity);
            httpResponse.close();
        }
    }

    /**
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String getResponseAsString() throws Exception {
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpUriRequest);
        super.checkHttpResponseStatus(httpResponse.getStatusLine().getStatusCode());
        HttpEntity responseEntity = httpResponse.getEntity();
        try {
            if (responseEntity != null) {
                InputStream is = responseEntity.getContent();
                return CharStreams.toString(new InputStreamReader(is, UTF_8));
            } else {
                throw new IncorrectResponseException(CONNECTION_PROBLEM_MESSAGE);
            }
        } finally {
            consume(responseEntity);
            httpResponse.close();
        }
    }

    /**
     * @return {@link InputStream}
     * @throws Exception
     */
    @Override
    public InputStream getResponseAsStream() throws Exception {
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpUriRequest);
        super.checkHttpResponseStatus(httpResponse.getStatusLine().getStatusCode());
        HttpEntity responseEntity = httpResponse.getEntity();
        try {
            if (responseEntity != null) {
                InputStream inputStream = responseEntity.getContent();
                return new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
            } else {
                throw new IncorrectResponseException(CONNECTION_PROBLEM_MESSAGE);
            }
        } finally {
            consume(responseEntity);
            httpResponse.close();
        }
    }

    /**
     * @param inputStream
     * @return {@link T}
     * @throws Exception
     */
    protected T readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        Unmarshaller unmarshaller = getUnmarshaller();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, UTF_8_CHARSERT))) {
            Object content = unmarshaller.unmarshal(reader);
            if ((content == null)) { // ExceptionReport
                logger.error("#############CONTENT : {} #############\n", content);
                throw new IncorrectResponseException();
            }
            return ((content instanceof JAXBElement) ? ((JAXBElement<T>) content).getValue() : (T) content);
        } catch (JAXBException ex) {
            ex.printStackTrace();
            logger.error("######################JAXBException : {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link H}
     */
    protected abstract H prepareHttpMethod() throws Exception;
}