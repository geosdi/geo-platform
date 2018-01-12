package org.geosdi.geoplatform.connector.geoserver.request.namespaces;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverEmptyNamespaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverNamespaces;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverNamespacesRequest extends GPGeoserverGetConnectorRequest<GPGeoserverNamespaces, GPGeoserverEmptyNamespaces> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverNamespacesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverNamespaces.class, GPGeoserverEmptyNamespaces.class);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("namespaces.json"))
                : new HttpGet(baseURI.concat("/namespaces.json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}