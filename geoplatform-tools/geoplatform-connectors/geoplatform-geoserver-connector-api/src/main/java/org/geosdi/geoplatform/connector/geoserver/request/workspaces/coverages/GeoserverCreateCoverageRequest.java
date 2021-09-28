package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPStoreType;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverCreateCoverageRequest extends GPJsonConnectorRequest<Boolean, GeoserverCreateCoverageRequest> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageRequest}
     */
    GeoserverCreateCoverageRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverCreateCoverageRequest}
     */
    GeoserverCreateCoverageRequest withCoverageStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theMethod
     * @return {@link GeoserverCreateCoverageRequest}
     */
    GeoserverCreateCoverageRequest withMethod(@Nonnull(when = NEVER) GPStoreType theMethod);

    /**
     * @param theFormat
     * @return {@link GeoserverCreateCoverageRequest}
     */
    GeoserverCreateCoverageRequest withFormat(@Nonnull(when = NEVER) GPFormatExtension theFormat);

    /**
     * @param theGPGeoserverCoverageInfo
     * @return {@link GeoserverCreateCoverageRequest}
     */
    GeoserverCreateCoverageRequest withCoverageBody(GPGeoserverCoverageInfo theGPGeoserverCoverageInfo);
}