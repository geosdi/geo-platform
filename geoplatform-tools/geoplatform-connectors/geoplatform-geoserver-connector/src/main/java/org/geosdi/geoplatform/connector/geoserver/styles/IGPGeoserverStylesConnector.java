package org.geosdi.geoplatform.connector.geoserver.styles;

import org.geosdi.geoplatform.connector.geoserver.layers.IGPGeoserverLayersConnector;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStylesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverStylesConnector extends IGPGeoserverLayersConnector {

    /**
     * @return {@link GPGeoserverStylesRequest}
     */
    GPGeoserverStylesRequest createStylesRequest();

    /**
     * @return {@link GPGeoserverStyleRequest}
     */
    GPGeoserverStyleRequest createStyleRequest();
}
