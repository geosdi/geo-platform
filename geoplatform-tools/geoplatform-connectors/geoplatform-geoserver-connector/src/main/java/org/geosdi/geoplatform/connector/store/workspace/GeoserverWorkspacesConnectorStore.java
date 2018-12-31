package org.geosdi.geoplatform.connector.store.workspace;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.*;
import org.geosdi.geoplatform.connector.store.about.GeoserverAboutConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverWorkspacesConnectorStore extends GeoserverAboutConnectorStore implements GPGeoserverWorkspacesConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverWorkspacesConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverLoadWorkspacesRequest}
     */
    @Override
    public GPGeoserverLoadWorkspacesRequest loadWorkspacesRequest() {
        return this.server.loadWorkspacesRequest();
    }

    /**
     * @return {@link GPGeoserverLoadWorkspaceRequest}
     */
    @Override
    public GPGeoserverLoadWorkspaceRequest loadWorkspaceRequest() {
        return this.server.loadWorkspaceRequest();
    }

    /**
     * @return {@link GeoserverCreateWorkspaceRequest}
     */
    @Override
    public GeoserverCreateWorkspaceRequest createWorkspaceRequest() {
        return this.server.createWorkspaceRequest();
    }

    /**
     * @return {@link GeoserverDeleteWorkspaceRequest}
     */
    @Override
    public GeoserverDeleteWorkspaceRequest deleteWorkspaceRequest() {
        return this.server.deleteWorkspaceRequest();
    }

    /**
     * @return {@link GeoserverUpdateWorkspaceRequest}
     */
    @Override
    public GeoserverUpdateWorkspaceRequest updateWorkspaceRequest() {
        return this.server.updateWorkspaceRequest();
    }
}