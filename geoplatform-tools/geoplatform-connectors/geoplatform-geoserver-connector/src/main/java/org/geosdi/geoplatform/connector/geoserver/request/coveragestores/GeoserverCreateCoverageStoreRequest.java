package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.IGPGeoserverCoverageStoreBody;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverCreateCoverageStoreRequest extends GPConnectorRequest<String> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    GeoserverCreateCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theBody
     * @param <CoverageStoreBody>
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    <CoverageStoreBody extends IGPGeoserverCoverageStoreBody> GeoserverCreateCoverageStoreRequest withBody(@Nonnull(when = NEVER) CoverageStoreBody theBody);
}