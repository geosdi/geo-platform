package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.geoserver.request.model.workspace.GPGeoserverEmptyWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverWorkspacesRequest extends GPGeoserverGetConnectorRequest<GPGeoserverWorkspaces, GPGeoserverEmptyWorkspaces> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverWorkspacesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverWorkspaces.class, GPGeoserverEmptyWorkspaces.class);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("workspaces.json"))
                : new HttpGet(baseURI.concat("/workspaces.json"))));
    }
}