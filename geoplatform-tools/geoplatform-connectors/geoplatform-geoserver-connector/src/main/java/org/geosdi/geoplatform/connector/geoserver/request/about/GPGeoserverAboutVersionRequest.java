package org.geosdi.geoplatform.connector.geoserver.request.about;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.model.about.version.GPGeoserverAboutVersion;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverAboutVersionRequest extends GPJsonGetConnectorRequest<GPGeoserverAboutVersion> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverAboutVersionRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverAboutVersion.class);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("about/version.json"))
                : new HttpGet(baseURI.concat("/about/version.json"))));
    }
}