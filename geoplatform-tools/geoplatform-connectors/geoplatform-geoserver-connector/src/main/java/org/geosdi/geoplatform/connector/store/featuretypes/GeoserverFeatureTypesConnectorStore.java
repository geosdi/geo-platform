package org.geosdi.geoplatform.connector.store.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverCreateFeatureTypeRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverDeleteFeatureTypeRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceDatastoreFeatureTypesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceFeatureTypesRequest;
import org.geosdi.geoplatform.connector.store.coveragestores.GeoserverCoverageStoresConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverFeatureTypesConnectorStore extends GeoserverCoverageStoresConnectorStore implements GPGeoserverFeatureTypesConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverFeatureTypesConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GeoserverLoadWorkspaceFeatureTypesRequest}
     */
    @Override
    public GeoserverLoadWorkspaceFeatureTypesRequest loadWorkspaceFeatureTypesRequest() {
        return this.server.loadWorkspaceFeatureTypesRequest();
    }

    /**
     * @return {@link GeoserverLoadWorkspaceDatastoreFeatureTypesRequest}
     */
    @Override
    public GeoserverLoadWorkspaceDatastoreFeatureTypesRequest loadWorkspaceDatastoreFeatureTypesRequest() {
        return this.server.loadWorkspaceDatastoreFeatureTypesRequest();
    }

    /**
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    @Override
    public GeoserverCreateFeatureTypeRequest createFeatureTypeRequest() {
        return this.server.createFeatureTypeRequest();
    }

    /**
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    @Override
    public GeoserverDeleteFeatureTypeRequest deleteFeatureTypeRequest() {
        return this.server.deleteFeatureTypeRequest();
    }
}