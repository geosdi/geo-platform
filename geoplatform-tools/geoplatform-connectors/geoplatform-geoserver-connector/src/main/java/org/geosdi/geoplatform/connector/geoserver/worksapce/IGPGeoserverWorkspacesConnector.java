package org.geosdi.geoplatform.connector.geoserver.worksapce;

import org.geosdi.geoplatform.connector.geoserver.about.IGPGeoserverAboutConnector;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverWorkspacesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverWorkspacesConnector extends IGPGeoserverAboutConnector {

    /**
     * @return {@link GPGeoserverWorkspacesRequest}
     */
    GPGeoserverWorkspacesRequest createWorkspacesRequest();
}