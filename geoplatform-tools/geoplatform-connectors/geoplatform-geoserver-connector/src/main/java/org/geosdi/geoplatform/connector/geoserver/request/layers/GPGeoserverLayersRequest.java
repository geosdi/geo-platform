package org.geosdi.geoplatform.connector.geoserver.request.layers;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GPGeoserverEmptyLayers;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GPGeoserverLayers;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverLayersRequest extends GPGeoserverGetConnectorRequest<GPGeoserverLayers, GPGeoserverEmptyLayers> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLayersRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverLayers.class, GPGeoserverEmptyLayers.class);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("layers.json"))
                : new HttpGet(baseURI.concat("/layers.json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}