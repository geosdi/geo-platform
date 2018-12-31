package org.geosdi.geoplatform.connector.store.workspace;

import org.geosdi.geoplatform.connector.geoserver.request.workspaces.*;
import org.geosdi.geoplatform.connector.store.about.GPGeoserverAboutConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverWorkspacesConnectorStore extends GPGeoserverAboutConnectorStore {

    /**
     * @return {@link GPGeoserverLoadWorkspacesRequest}
     */
    GPGeoserverLoadWorkspacesRequest loadWorkspacesRequest();

    /**
     * @return {@link GPGeoserverLoadWorkspaceRequest}
     */
    GPGeoserverLoadWorkspaceRequest loadWorkspaceRequest();

    /**
     * @return {@link GeoserverCreateWorkspaceRequest}
     */
    GeoserverCreateWorkspaceRequest createWorkspaceRequest();

    /**
     * @return {@link GeoserverDeleteWorkspaceRequest}
     */
    GeoserverDeleteWorkspaceRequest deleteWorkspaceRequest();

    /**
     * @return {@link GeoserverUpdateWorkspaceRequest}
     */
    GeoserverUpdateWorkspaceRequest updateWorkspaceRequest();
}