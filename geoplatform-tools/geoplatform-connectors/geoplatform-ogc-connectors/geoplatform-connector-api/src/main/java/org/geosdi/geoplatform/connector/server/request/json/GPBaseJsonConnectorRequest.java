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
package org.geosdi.geoplatform.connector.server.request.json;

import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;
import org.geosdi.geoplatform.connector.server.request.GPAbstractConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.*;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.util.EntityUtils.consume;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseJsonConnectorRequest<T, H extends HttpUriRequest> extends GPAbstractConnectorRequest<T> {

    protected final JacksonSupport jacksonSupport;
    protected final Class<T> classe;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPBaseJsonConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector);
        checkArgument((this.jacksonSupport = theJacksonSupport) != null, "The Parameter JacksonSupport must not be null.");
        checkArgument((this.classe = forClass()) != null, "The Parameter classe must not be null.");
    }

    /**
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T getResponse() throws Exception {
        HttpUriRequest httpMethod = this.prepareHttpMethod();
        httpMethod.addHeader("Content-Type", "application/json");
        logger.debug("#############################Executing -------------> {}\n", httpMethod.getURI().toString());
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        logger.debug("###############################STATUS_CODE : {} for Request : {}\n", statusCode, this.getClass().getSimpleName());
        super.checkHttpResponseStatus(statusCode);
        HttpEntity responseEntity = httpResponse.getEntity();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent(), UTF_8_CHARSERT))) {
            return readInternal(reader);
        } catch (Exception ex) {
            throw new IncorrectResponseException(ex);
        } finally {
            consume(responseEntity);
            httpResponse.close();
        }
    }

    /**
     * <p>
     * Method to generate Response AS a {@link String} value.
     * </p>
     *
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String getResponseAsString() throws Exception {
        HttpUriRequest httpMethod = this.prepareHttpMethod();
        httpMethod.addHeader("Content-Type", "application/json");
        logger.debug("#############################Executing -------------> {}\n", httpMethod.getURI().toString());
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        logger.debug("###############################STATUS_CODE : {} for Request : {}\n", statusCode, this.getClass().getSimpleName());
        super.checkHttpResponseStatus(statusCode);
        HttpEntity responseEntity = httpResponse.getEntity();
        try {
            if (responseEntity != null) {
                return CharStreams.toString(new InputStreamReader(responseEntity.getContent(), UTF_8));
            } else {
                throw new IncorrectResponseException(CONNECTION_PROBLEM_MESSAGE);
            }
        } finally {
            consume(responseEntity);
            httpResponse.close();
        }
    }

    /**
     * <p>
     * Method to generate Response AS a {@link InputStream} Stream. Remember to
     * close the Stream</p>
     *
     * @return {@link InputStream} stream
     * @throws Exception
     */
    @Override
    public InputStream getResponseAsStream() throws Exception {
        HttpUriRequest httpMethod = this.prepareHttpMethod();
        httpMethod.addHeader("Content-Type", "application/json");
        logger.debug("#############################Executing -------------> {}\n", httpMethod.getURI().toString());
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        logger.debug("###############################STATUS_CODE : {} for Request : {}\n", statusCode, this.getClass().getSimpleName());
        super.checkHttpResponseStatus(statusCode);
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
     * @return
     * @throws Exception
     */
    protected String checkUriPath() throws Exception {
        String uriPath = createUriPath();
        checkArgument((uriPath != null) && !(uriPath.trim().isEmpty()), "The Parameter uriPath for " + getClass().getSimpleName() + " must not be null or an Empty String.");
        return uriPath;
    }

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    protected T readInternal(BufferedReader reader) throws Exception {
        return this.jacksonSupport.getDefaultMapper().readValue(reader, this.classe);
    }

    /**
     * @return {@link String}
     */
    protected abstract String createUriPath() throws Exception;

    /**
     * @return {@link H}
     */
    protected abstract H prepareHttpMethod() throws Exception;

    /**
     * @return {@link Class<T>}
     */
    protected abstract Class<T> forClass();
}