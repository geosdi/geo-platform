package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverDeleteFeatureTypeRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    GeoserverDeleteFeatureTypeRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    GeoserverDeleteFeatureTypeRequest withStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theFeatureTypeName
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    GeoserverDeleteFeatureTypeRequest withFeatureTypeName(@Nonnull(when = NEVER) String theFeatureTypeName);

    /**
     * <p>Default value is {@link Boolean#FALSE}</p>
     *
     * @param theRecurse
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    GeoserverDeleteFeatureTypeRequest withRecurse(@Nullable Boolean theRecurse);
}