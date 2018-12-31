package org.geosdi.geoplatform.connector.server.request.json;

import org.apache.http.client.methods.HttpDelete;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPJsonDeleteConnectorRequest<T> extends GPBaseJsonConnectorRequest<T, HttpDelete> {

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPJsonDeleteConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @return {@link HttpDelete}
     */
    @Override
    protected HttpDelete prepareHttpMethod() throws Exception {
        String uriPath = super.checkUriPath();
        HttpDelete httpDelete = new HttpDelete(uriPath);
        httpDelete.setConfig(super.prepareRequestConfig());
        return httpDelete;
    }
}