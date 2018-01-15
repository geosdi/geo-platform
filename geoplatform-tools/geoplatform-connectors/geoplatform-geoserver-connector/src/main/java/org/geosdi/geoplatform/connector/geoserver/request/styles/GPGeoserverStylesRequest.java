package org.geosdi.geoplatform.connector.geoserver.request.styles;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverEmptyStyles;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverStyles;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverStylesRequest extends GPGeoserverGetConnectorRequest<GPGeoserverStyles, GPGeoserverEmptyStyles> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverStylesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverStyles.class, GPGeoserverEmptyStyles.class);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("styles.json"))
                : new HttpGet(baseURI.concat("/styles.json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}