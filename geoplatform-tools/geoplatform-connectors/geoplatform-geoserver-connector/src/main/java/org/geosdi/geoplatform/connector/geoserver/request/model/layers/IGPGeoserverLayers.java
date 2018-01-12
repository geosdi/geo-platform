package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverLayers extends Serializable {

    /**
     * @return {@link List<IGPGeoserverLayer>}
     */
    List<IGPGeoserverLayer> getLayers();
}
