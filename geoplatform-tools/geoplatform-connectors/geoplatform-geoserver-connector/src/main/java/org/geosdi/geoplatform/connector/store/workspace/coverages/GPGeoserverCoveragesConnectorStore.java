package org.geosdi.geoplatform.connector.store.workspace.coverages;

import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoverageRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoveragesRequest;
import org.geosdi.geoplatform.connector.store.workspace.GPGeoserverWorkspacesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverCoveragesConnectorStore extends GPGeoserverWorkspacesConnectorStore {

    /**
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    GeoserverLoadCoveragesRequest loadWorkspaceCoveragesRequest();

    /**
     * @return {@link GeoserverLoadCoverageRequest}
     */
    GeoserverLoadCoverageRequest loadWorkspaceCoverageRequest();
}