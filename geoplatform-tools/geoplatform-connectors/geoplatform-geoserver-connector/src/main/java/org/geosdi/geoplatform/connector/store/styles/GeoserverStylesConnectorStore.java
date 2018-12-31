package org.geosdi.geoplatform.connector.store.styles;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStylesRequest;
import org.geosdi.geoplatform.connector.store.layers.GeoserverLayersConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverStylesConnectorStore extends GeoserverLayersConnectorStore implements GPGeoserverStylesConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverStylesConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverStylesRequest}
     */
    @Override
    public GPGeoserverStylesRequest loadStylesRequest() {
        return this.server.loadStylesRequest();
    }

    /**
     * @return {@link GPGeoserverStyleRequest}
     */
    @Override
    public GPGeoserverStyleRequest loadStyleRequest() {
        return this.server.loadStyleRequest();
    }
}