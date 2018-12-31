package org.geosdi.geoplatform.connector.store.styles;

import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStylesRequest;
import org.geosdi.geoplatform.connector.store.layers.GPGeoserverLayersConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverStylesConnectorStore extends GPGeoserverLayersConnectorStore {

    /**
     * @return {@link GPGeoserverStylesRequest}
     */
    GPGeoserverStylesRequest loadStylesRequest();

    /**
     * @return {@link GPGeoserverStyleRequest}
     */
    GPGeoserverStyleRequest loadStyleRequest();
}
