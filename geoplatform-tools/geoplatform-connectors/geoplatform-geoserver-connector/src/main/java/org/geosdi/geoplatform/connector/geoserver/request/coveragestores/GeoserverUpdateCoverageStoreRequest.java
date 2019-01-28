package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.IGPGeoserverCoverageStoreBody;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverUpdateCoverageStoreRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theWorkspace The name of the worskpace containing the coverage stores.
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    GeoserverUpdateCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore The name of the store to be retrieved.
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    GeoserverUpdateCoverageStoreRequest withStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theBody The coverage store body information to upload. For a PUT, only values which should be changed need to be included.
     * @param <CoverageStoreBody>
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    <CoverageStoreBody extends IGPGeoserverCoverageStoreBody> GeoserverUpdateCoverageStoreRequest withBody(@Nonnull(when = NEVER) CoverageStoreBody theBody);
}