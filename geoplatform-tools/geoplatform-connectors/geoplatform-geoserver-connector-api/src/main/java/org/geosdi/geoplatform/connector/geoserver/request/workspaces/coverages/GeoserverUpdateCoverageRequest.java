package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverUpdateCoverageRequest extends GPJsonConnectorRequest<Boolean, GeoserverUpdateCoverageRequest> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    GeoserverUpdateCoverageRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    GeoserverUpdateCoverageRequest withCoverageStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theGPGeoserverCoverageInfo
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    GeoserverUpdateCoverageRequest withCoverageBody(GPGeoserverCoverageInfo theGPGeoserverCoverageInfo);

    /**
     * @param theCoverageName
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    GeoserverUpdateCoverageRequest withCoverageName(String theCoverageName);

    /**
     * @param theCalculate
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    GeoserverUpdateCoverageRequest withCalculate(String[] theCalculate);
}