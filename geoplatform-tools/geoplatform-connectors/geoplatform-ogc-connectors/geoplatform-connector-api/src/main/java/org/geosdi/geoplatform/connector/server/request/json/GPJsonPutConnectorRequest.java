package org.geosdi.geoplatform.connector.server.request.json;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPut;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPJsonPutConnectorRequest<T> extends GPBaseJsonConnectorRequest<T, HttpPut> {

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPJsonPutConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @return {@link HttpPut}
     */
    @Override
    protected HttpPut prepareHttpMethod() throws Exception {
        String uriPath = super.checkUriPath();
        HttpPut httpPut = new HttpPut(uriPath);
        httpPut.setConfig(super.prepareRequestConfig());
        HttpEntity httpEntity = this.prepareHttpEntity();
        if (httpEntity != null)
            httpPut.setEntity(httpEntity);
        return httpPut;
    }

    /**
     * @return {@link HttpEntity}
     */
    protected abstract HttpEntity prepareHttpEntity() throws Exception;
}