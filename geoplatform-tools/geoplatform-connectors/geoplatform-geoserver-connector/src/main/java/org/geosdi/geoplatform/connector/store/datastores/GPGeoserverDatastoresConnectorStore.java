package org.geosdi.geoplatform.connector.store.datastores;

import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.store.styles.GPGeoserverStylesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverDatastoresConnectorStore extends GPGeoserverStylesConnectorStore {

    /**
     * @return {@link GeoserverLoadDatastoresRequest}
     */
    GeoserverLoadDatastoresRequest loadDatastoresRequest();

    /**
     * @return {@link GeoserverLoadDatastoreRequest}
     */
    GeoserverLoadDatastoreRequest loadDatastoreRequest();
}