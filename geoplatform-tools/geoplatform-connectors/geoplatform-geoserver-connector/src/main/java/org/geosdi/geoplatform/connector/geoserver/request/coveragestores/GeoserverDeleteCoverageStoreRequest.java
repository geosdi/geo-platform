package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverPurgeParam;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverDeleteCoverageStoreRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theWorkspace The name of the worskpace containing the coverage stores.
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    GeoserverDeleteCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theCoverageStore The name of the store to be retrieved.
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    GeoserverDeleteCoverageStoreRequest withCoverageStore(@Nonnull(when = NEVER) String theCoverageStore);

    /**
     * <p>
     * The purge parameter specifies if and how the underlying raster data source is deleted.
     * Allowable values for this parameter are {@link GPGeoserverPurgeParam#NONE}, {@link GPGeoserverPurgeParam#METADATA},
     * {@link GPGeoserverPurgeParam#ALL}. When set to {@link GPGeoserverPurgeParam#NONE}
     * data and auxiliary files are preserved. When set to {@link GPGeoserverPurgeParam#METADATA} delete only auxiliary files and metadata.
     * It’s recommended when data files (such as granules) should not be deleted from disk.
     * Finally, when set to {@link GPGeoserverPurgeParam#ALL} both data and auxiliary files are removed.
     * </p>
     *
     * @param thePurge
     * @param <Purge>
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    <Purge extends GPGeoserverPurgeParam> GeoserverDeleteCoverageStoreRequest withPurge(Purge thePurge);

    /**
     * <p>
     * The recurse controls recursive deletion. When set to true all resources contained in the store are also removed.
     * The default value is {@link Boolean#FALSE}.
     * </p>
     *
     * @param theRecurse
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    GeoserverDeleteCoverageStoreRequest withRecurse(@Nullable Boolean theRecurse);
}