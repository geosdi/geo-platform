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
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpUriRequest);
        super.checkHttpResponseStatus(httpResponse.getStatusLine().getStatusCode());
        HttpEntity responseEntity = httpResponse.getEntity();
        try {
            if (responseEntity != null) {
                InputStream is = responseEntity.getContent();
                Unmarshaller unmarshaller = getUnmarshaller();
                Object content;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, UTF_8_CHARSERT))) {
                    content = unmarshaller.unmarshal(reader);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                    logger.error("######################JAXBException : {}", ex);
                    throw new IllegalStateException(ex);
                }
                if ((content == null)) { // ExceptionReport
                    logger.error("#############CONTENT : {} #############\n", content);
                    throw new IncorrectResponseException();
                }
                return ((content instanceof JAXBElement) ? ((JAXBElement<T>) content).getValue() : (T) content);
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
     * @return {@link H}
     */
    protected abstract H prepareHttpMethod() throws Exception;
}