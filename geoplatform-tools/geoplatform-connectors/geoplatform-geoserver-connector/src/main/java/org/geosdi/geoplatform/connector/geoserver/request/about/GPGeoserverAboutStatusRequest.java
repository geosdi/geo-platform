package org.geosdi.geoplatform.connector.geoserver.request.about;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.model.about.status.GPGeoserverAboutStatus;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverAboutStatusRequest extends GPJsonGetConnectorRequest<GPGeoserverAboutStatus> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverAboutStatusRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverAboutStatus.class);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("about/status.json"))
                : new HttpGet(baseURI.concat("/about/status.json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}