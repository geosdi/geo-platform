package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStore;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadCoverageStoreRequest extends GPConnectorRequest<GPGeoserverCoverageStore> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    GeoserverLoadCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore The name of the store to be retrieved.
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    GeoserverLoadCoverageStoreRequest withStore(@Nonnull(when = NEVER) String theStore);
}