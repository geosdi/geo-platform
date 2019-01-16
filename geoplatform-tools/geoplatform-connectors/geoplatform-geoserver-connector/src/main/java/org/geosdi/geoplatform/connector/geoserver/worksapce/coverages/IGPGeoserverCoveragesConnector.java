package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages;

import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoverageRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoveragesRequest;
import org.geosdi.geoplatform.connector.geoserver.worksapce.IGPGeoserverWorkspacesConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverCoveragesConnector extends IGPGeoserverWorkspacesConnector {

    /**
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    GeoserverLoadCoveragesRequest loadWorkspaceCoveragesRequest();

    /**
     * @return {@@link GeoserverLoadCoverageRequest}
     */
    GeoserverLoadCoverageRequest loadWorkspaceCoverageRequest();
}