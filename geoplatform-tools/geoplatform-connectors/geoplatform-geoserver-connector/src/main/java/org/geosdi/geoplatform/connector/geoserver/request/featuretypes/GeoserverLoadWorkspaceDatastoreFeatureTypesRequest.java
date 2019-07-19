package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadWorkspaceDatastoreFeatureTypesRequest extends GeoserverLoadFeatureTypesRequest<GeoserverLoadWorkspaceDatastoreFeatureTypesRequest> {

    /**
     * @param theStore the name of the Datastore
     * @return {@link GeoserverLoadWorkspaceDatastoreFeatureTypesRequest}
     */
    GeoserverLoadWorkspaceDatastoreFeatureTypesRequest withStore(@Nonnull(when = NEVER) String theStore);
}