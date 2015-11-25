package org.geosdi.geoplatform.connector.server.request;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class PostConnectorRequest<T> extends GPAbstractConnectorRequest<T> {

    /**
     * @param server
     */
    protected PostConnectorRequest(GPServerConnector server) {
        super(server);
    }

    @Override
    public String getResponseAsString() throws Exception {
        String content = null;

        HttpPost httpPost = this.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector
                .secure(this, httpPost);
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
        CloseableHttpResponse httpResponse = super.securityConnector
                .secure(this, httpPost);

        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            return responseEntity.getContent();

        } else {
            throw new IllegalStateException("Connector Server Error: "
                    + "Connection problem");
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
