package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.datastores.IGPGeoserverDatastoresConnector;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverCoverageStoresConnector extends IGPGeoserverDatastoresConnector {

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