package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadCoverageRequest extends GPConnectorRequest<GPGeoserverCoverageInfo> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoverageRequest}
     */
    GeoserverLoadCoverageRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theCoverage
     * @return
     */
    GeoserverLoadCoverageRequest withCoverage(@Nonnull(when = NEVER) String theCoverage);

    /**
     * @param theQuietOnNotFound
     * @return {@link GeoserverLoadCoverageRequest}
     */
    GeoserverLoadCoverageRequest withQuietOnNotFound(@Nullable Boolean theQuietOnNotFound);
}