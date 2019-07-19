package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.GPGeoserverFeatureTypeWrapper;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadFeatureTypesRequest<Request extends GeoserverLoadFeatureTypesRequest> extends GPConnectorRequest<GPGeoserverFeatureTypeWrapper> {

    /**
     * @param theWorkspace the name of the Workspace
     * @return {@link Request}
     */
    Request withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theFeatureTypeCategory
     * @return {@link Request}
     */
    Request withFeatureTypeCategory(@Nullable GPGeoserverFeatureTypeCategory theFeatureTypeCategory);
}