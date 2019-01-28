package org.geosdi.geoplatform.connector.store.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.*;
import org.geosdi.geoplatform.connector.store.datastores.GeoserverDatastoresConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverCoverageStoresConnectorStore extends GeoserverDatastoresConnectorStore implements GPGeoserverCoverageStoresConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverCoverageStoresConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GeoserverLoadCoverageStoresRequest}
     */
    @Override
    public GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest() {
        return this.server.loadCoverageStoresRequest();
    }

    /**
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    @Override
    public GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest() {
        return this.server.loadCoverageStoreRequest();
    }

    /**
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreRequest createCoverageStoreRequest() {
        return this.server.createCoverageStoreRequest();
    }

    /**
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    @Override
    public GeoserverDeleteCoverageStoreRequest deleteCoverageStoreRequest() {
        return this.server.deleteCoverageStoreRequest();
    }

    /**
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest updateCoverageStoreRequest() {
        return this.server.updateCoverageStoreRequest();
    }
}