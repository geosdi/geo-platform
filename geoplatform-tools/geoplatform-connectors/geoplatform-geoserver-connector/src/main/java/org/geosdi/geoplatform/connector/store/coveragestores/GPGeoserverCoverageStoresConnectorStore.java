package org.geosdi.geoplatform.connector.store.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.*;
import org.geosdi.geoplatform.connector.store.datastores.GPGeoserverDatastoresConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverCoverageStoresConnectorStore extends GPGeoserverDatastoresConnectorStore {

    /**
     * @return {@link GeoserverLoadCoverageStoresRequest}
     */
    GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest();

    /**
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest();

    /**
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    GeoserverCreateCoverageStoreRequest createCoverageStoreRequest();

    /**
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    GeoserverDeleteCoverageStoreRequest deleteCoverageStoreRequest();

    /**
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    GeoserverUpdateCoverageStoreRequest updateCoverageStoreRequest();
}