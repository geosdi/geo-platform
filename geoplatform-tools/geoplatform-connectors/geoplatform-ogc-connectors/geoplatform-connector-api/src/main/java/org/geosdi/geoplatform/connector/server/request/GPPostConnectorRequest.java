/**
 * geo-platform Rich webgis framework http://geo-platform.org
 * ====================================================================
 * <p/>
 * Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details. You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/
 * <p/>
 * ====================================================================
 * <p/>
 * Linking this library statically or dynamically with other modules is making a
 * combined work based on this library. Thus, the terms and conditions of the
 * GNU General Public License cover the whole combination.
 * <p/>
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules, and
 * to copy and distribute the resulting executable under terms of your choice,
 * provided that you also meet, for each linked independent module, the terms
 * and conditions of the license of that module. An independent module is a
 * module which is not derived from or based on this library. If you modify this
 * library, you may extend this exception to your version of the library, but
 * you are not obligated to do so. If you do not wish to do so, delete this
 * exception statement from your version.
 */
package org.geosdi.geoplatform.connector.server.request;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPPostConnectorRequest<T>
        extends GPAbstractConnectorRequest<T> {

    private HttpPost postMethod;

    public GPPostConnectorRequest(GPServerConnector server) {
        super(server);
    }

    private HttpPost getPostMethod() throws Exception {
        if (postMethod == null) {
            this.preparePostMethod();
        }

        return postMethod;
    }

    private void preparePostMethod() throws Exception {

        this.postMethod = new HttpPost(super.serverURI);
        this.postMethod.setConfig(super.prepareRequestConfig());

        HttpEntity httpEntity = this.preparePostEntity();
        this.postMethod.setEntity(httpEntity);

    }

    @Override
    public T getResponse() throws Exception {
        T response = null;

        HttpPost httpPost = this.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector
                .secure(this, httpPost);

        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            InputStream is = responseEntity.getContent();

            Unmarshaller unmarshaller = getUnmarshaller();
            Object content = null;
            try {
                content = unmarshaller.unmarshal(is);
            } catch (JAXBException ex) {
                logger.error("######################JAXBException : ", ex);
            }
            if ((content == null) || !(content instanceof JAXBElement)) { // ExceptionReport
                logger.error("#############CONTENT : {} #############\n", content);
                throw new IllegalStateException("Connector Server Error: "
                        + "incorrect responce");
            }

            JAXBElement<T> elementType = (JAXBElement<T>) content;
            response = elementType.getValue();

            EntityUtils.consume(responseEntity);
        } else {
            throw new IllegalStateException("Connector Server Error: "
                    + "Connection problem");
        }

        return response;
    }

    @Override
    public String getResponseAsString() throws Exception {
        String content = null;

        HttpPost httpPost = this.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(
                this,
                httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();

        if (responseEntity != null) {
            InputStream is = responseEntity.getContent();

            content = CharStreams.toString(new InputStreamReader(is,
                    Charsets.UTF_8));

            EntityUtils.consume(responseEntity);
        } else {
            throw new IllegalStateException(
                    "Connector Server Error: Connection problem");
        }

        return content;
    }

    @Override
    public InputStream getResponseAsStream() throws Exception {

        HttpPost httpPost = this.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector.secure(
                this,
                httpPost);

        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            return responseEntity.getContent();

        } else {
            throw new IllegalStateException("Connector Server Error: "
                    + "Connection problem");
        }

    }

    protected abstract HttpEntity preparePostEntity() throws Exception;

}
