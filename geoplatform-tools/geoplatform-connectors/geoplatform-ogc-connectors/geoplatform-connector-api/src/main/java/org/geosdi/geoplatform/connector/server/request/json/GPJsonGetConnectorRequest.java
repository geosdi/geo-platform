package org.geosdi.geoplatform.connector.server.request.json;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPJsonGetConnectorRequest<T> extends GPBaseJsonConnectorRequest<T, HttpGet> {

    /**
     * @param server
     * @param theJacksonSupport
     * @param theClasse
     */
    public GPJsonGetConnectorRequest(GPServerConnector server, JacksonSupport theJacksonSupport,
            Class<T> theClasse) {
        super(server, theJacksonSupport, theClasse);
    }
}