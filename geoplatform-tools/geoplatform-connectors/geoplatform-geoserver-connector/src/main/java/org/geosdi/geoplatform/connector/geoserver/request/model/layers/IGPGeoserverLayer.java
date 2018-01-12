package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverLayer.class)
public interface IGPGeoserverLayer extends Serializable {

    /**
     * @return {@link String}
     */
    String getLayerName();

    /**
     * @return {@link String}
     */
    String getHref();
}
