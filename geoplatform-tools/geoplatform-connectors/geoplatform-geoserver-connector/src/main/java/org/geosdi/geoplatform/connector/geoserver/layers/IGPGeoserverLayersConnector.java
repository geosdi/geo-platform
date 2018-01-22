package org.geosdi.geoplatform.connector.geoserver.layers;

import org.geosdi.geoplatform.connector.geoserver.namespaces.IGPGeoserverNamespacesConnector;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverLayersConnector extends IGPGeoserverNamespacesConnector {

    /**
     * @return {@link GPGeoserverLayersRequest}
     */
    GPGeoserverLayersRequest createLayersRequest();

    /**
     * @return {@link GPGeoserverLayerRequest}
     */
    GPGeoserverLayerRequest createLayerRequest();
}
