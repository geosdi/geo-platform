package org.geosdi.geoplatform.connector.geoserver.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.coveragestores.IGPGeoserverCoverageStoresConnector;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceDatastoreFeatureTypesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceFeatureTypesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverFeatureTypesConnector extends IGPGeoserverCoverageStoresConnector {

    /**
     * @return {@link GeoserverLoadWorkspaceFeatureTypesRequest}
     */
    GeoserverLoadWorkspaceFeatureTypesRequest loadWorkspaceFeatureTypesRequest();

    /**
     * @return {@link GeoserverLoadWorkspaceDatastoreFeatureTypesRequest}
     */
    GeoserverLoadWorkspaceDatastoreFeatureTypesRequest loadWorkspaceDatastoreFeatureTypesRequest();
}