package org.geosdi.geoplatform.connector.geoserver.datastores;

import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.geoserver.styles.IGPGeoserverStylesConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverDatastoresConnector extends IGPGeoserverStylesConnector {

    /**
     * @return {@link GeoserverLoadDatastoresRequest}
     */
    GeoserverLoadDatastoresRequest loadDatastoresRequest();

    /**
     * @return {@link GeoserverLoadDatastoreRequest}
     */
    GeoserverLoadDatastoreRequest loadDatastoreRequest();
}