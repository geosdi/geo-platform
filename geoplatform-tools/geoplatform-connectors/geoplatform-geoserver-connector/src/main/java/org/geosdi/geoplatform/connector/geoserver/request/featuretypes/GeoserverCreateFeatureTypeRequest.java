package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.IGPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverCreateFeatureTypeRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    GeoserverCreateFeatureTypeRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    GeoserverCreateFeatureTypeRequest withStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theFeatureTypeBody
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    <FeatureTypeBody extends IGPGeoserverFeatureTypeInfo> GeoserverCreateFeatureTypeRequest withFeatureTypeBody(@Nonnull(when = NEVER) FeatureTypeBody theFeatureTypeBody);
}