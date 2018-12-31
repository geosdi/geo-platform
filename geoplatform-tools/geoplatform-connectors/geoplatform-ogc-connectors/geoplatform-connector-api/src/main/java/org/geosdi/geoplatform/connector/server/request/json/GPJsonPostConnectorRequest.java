package org.geosdi.geoplatform.connector.server.request.json;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPJsonPostConnectorRequest<T> extends GPBaseJsonConnectorRequest<T, HttpPost> {

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPJsonPostConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @return {@link HttpPost}
     */
    @Override
    protected HttpPost prepareHttpMethod() throws Exception {
        String uriPath = super.checkUriPath();
        HttpPost httpPost = new HttpPost(uriPath);
        httpPost.setConfig(super.prepareRequestConfig());
        HttpEntity httpEntity = this.prepareHttpEntity();
        if (httpEntity != null)
            httpPost.setEntity(httpEntity);
        return httpPost;
    }

    /**
     * @return {@link HttpEntity}
     */
    protected abstract HttpEntity prepareHttpEntity() throws Exception;
}