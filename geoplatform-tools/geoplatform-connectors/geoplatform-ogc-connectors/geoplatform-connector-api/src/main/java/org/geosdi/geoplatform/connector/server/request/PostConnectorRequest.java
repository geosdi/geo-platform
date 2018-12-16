/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static org.apache.http.util.EntityUtils.consume;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class PostConnectorRequest<T> extends GPAbstractConnectorRequest<T> implements GPJAXBConnectorRequest<T> {

    /**
     * @param server
     */
    protected PostConnectorRequest(GPServerConnector server) {
        super(server);
    }

    @Override
    public String getResponseAsString() throws Exception {
        HttpPost httpPost = this.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpPost);
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

    @Override
    public InputStream getResponseAsStream() throws Exception {
        HttpPost httpPost = this.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpPost);
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
     * @return {@link HttpPost}
     * @throws Exception
     */
    protected HttpPost getPostMethod() throws Exception {
        HttpPost postMethod = new HttpPost(super.serverURI);
        postMethod.setConfig(super.prepareRequestConfig());
        HttpEntity httpEntity = this.preparePostEntity();
        postMethod.setEntity(httpEntity);
        return postMethod;
    }

    /**
     * @return {@link HttpEntity}
     * @throws Exception
     */
    protected abstract HttpEntity preparePostEntity() throws Exception;
}