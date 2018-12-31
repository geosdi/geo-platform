package org.geosdi.geoplatform.connector.store.datastores;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.connector.store.styles.GeoserverStylesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverDatastoresConnectorStore extends GeoserverStylesConnectorStore implements GPGeoserverDatastoresConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverDatastoresConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GeoserverLoadDatastoresRequest}
     */
    @Override
    public GeoserverLoadDatastoresRequest loadDatastoresRequest() {
        return this.server.loadDatastoresRequest();
    }

    /**
     * @return {@link GeoserverLoadDatastoreRequest}
     */
    @Override
    public GeoserverLoadDatastoreRequest loadDatastoreRequest() {
        return this.server.loadDatastoreRequest();
    }
}