package org.geosdi.geoplatform.connector.server.request;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GetConnectorRequest<T> extends GPBaseConnectorRequest<T, HttpGet> {

    /**
     * @param theServerConnector
     */
    protected GetConnectorRequest(@Nonnull(when = When.NEVER) GPServerConnector theServerConnector) {
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