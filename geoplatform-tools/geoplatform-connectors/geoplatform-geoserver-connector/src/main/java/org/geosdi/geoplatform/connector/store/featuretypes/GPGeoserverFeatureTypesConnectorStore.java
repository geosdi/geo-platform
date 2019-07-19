package org.geosdi.geoplatform.connector.store.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceDatastoreFeatureTypesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceFeatureTypesRequest;
import org.geosdi.geoplatform.connector.store.coveragestores.GPGeoserverCoverageStoresConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverFeatureTypesConnectorStore extends GPGeoserverCoverageStoresConnectorStore {

    /**
     * @return {@link GeoserverLoadWorkspaceFeatureTypesRequest}
     */
    GeoserverLoadWorkspaceFeatureTypesRequest loadWorkspaceFeatureTypesRequest();

    /**
     * @return {@link GeoserverLoadWorkspaceDatastoreFeatureTypesRequest}
     */
    GeoserverLoadWorkspaceDatastoreFeatureTypesRequest loadWorkspaceDatastoreFeatureTypesRequest();
}