package org.geosdi.geoplatform.connector.store.workspace.coverages;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoverageRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoveragesRequest;
import org.geosdi.geoplatform.connector.store.workspace.GeoserverWorkspacesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverCoveragesConnectorStore extends GeoserverWorkspacesConnectorStore implements GPGeoserverCoveragesConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverCoveragesConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    @Override
    public GeoserverLoadCoveragesRequest loadWorkspaceCoveragesRequest() {
        return this.server.loadWorkspaceCoveragesRequest();
    }

    /**
     * @return {@link GeoserverLoadCoverageRequest}
     */
    @Override
    public GeoserverLoadCoverageRequest loadWorkspaceCoverageRequest() {
        return this.server.loadWorkspaceCoverageRequest();
    }
}