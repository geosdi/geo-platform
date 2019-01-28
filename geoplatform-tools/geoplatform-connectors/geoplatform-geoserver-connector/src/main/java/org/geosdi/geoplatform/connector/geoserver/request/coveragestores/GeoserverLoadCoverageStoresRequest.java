package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStores;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadCoverageStoresRequest extends GPConnectorRequest<GPGeoserverCoverageStores> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoverageStoresRequest}
     */
    GeoserverLoadCoverageStoresRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);
}