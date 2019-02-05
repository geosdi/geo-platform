package org.geosdi.geoplatform.connector.server.request;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GetConnectorRequest<T> extends GPBaseConnectorRequest<T, HttpGet> {

    /**
     * @param theServerConnector
     */
    protected GetConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector);
    }

    /**
     * @return
     * @throws Exception
     */
    protected String checkUriPath() throws Exception {
        String uriPath = this.createUriPath();
        checkArgument((uriPath != null) && !(uriPath.trim().isEmpty()),
                "The Parameter uriPath for " + getClass().getSimpleName() + " must not be null or an Empty String.");
        return uriPath;
    }

    /**
     * @return {@link String}
     */
    protected abstract String createUriPath() throws Exception;
}