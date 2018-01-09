package org.geosdi.geoplatform.connector.server.request.json;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPAbstractConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.http.util.EntityUtils.consume;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseJsonConnectorRequest<T> extends GPAbstractConnectorRequest<T> {

    protected final JacksonSupport jacksonSupport;
    protected final Class<T> classe;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     * @param theClasse
     */
    public GPBaseJsonConnectorRequest(GPServerConnector theServerConnector, JacksonSupport theJacksonSupport,
            Class<T> theClasse) {
        super(theServerConnector);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport must not be null.");
        checkArgument(theClasse != null, "The Parameter classe must not be null.");
        this.jacksonSupport = theJacksonSupport;
        this.classe = theClasse;
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

        if (httpResponse.getStatusLine().getStatusCode() == 401) {
            throw new IllegalStateException("Unauthorized : This request requires HTTP authentication.");
        }

        HttpEntity responseEntity = httpResponse.getEntity();
        try {
            if (responseEntity != null) {
                InputStream is = responseEntity.getContent();
                InputSource inputSource = new InputSource(new InputStreamReader(is, UTF_8_CHARSERT));
                inputSource.setEncoding(UTF_8_CHARSERT.name());
                try {
                    return this.jacksonSupport.getDefaultMapper().readValue(inputSource.getCharacterStream(), this.classe);
                } catch (Exception ex) {
                    throw new IllegalStateException(INCORRECT_RESPONSE_MESSAGE);
                }
            } else {
                throw new IllegalStateException(CONNECTION_PROBLEM_MESSAGE);
            }
        } finally {
            consume(responseEntity);
        }
    }

    /**
     * <p>
     * Method to generate Response AS a {@link String} string.
     * </p>
     *
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String getResponseAsString() throws Exception {
        return this.jacksonSupport.getDefaultMapper().writeValueAsString(getResponse());
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
        CloseableHttpResponse httpResponse = super.securityConnector.secure(this, httpMethod);
        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            return responseEntity.getContent();
        } else {
            throw new IllegalStateException(CONNECTION_PROBLEM_MESSAGE);
        }
    }

    /**
     * @param <H>
     * @return {@link H}
     */
    protected abstract <H extends HttpUriRequest> H prepareHttpMethod();
}