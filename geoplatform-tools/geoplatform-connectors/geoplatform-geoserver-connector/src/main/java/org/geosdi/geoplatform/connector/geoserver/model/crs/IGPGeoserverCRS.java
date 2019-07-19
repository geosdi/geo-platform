package org.geosdi.geoplatform.connector.geoserver.model.crs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCRS.class)
public interface IGPGeoserverCRS extends Serializable {

    /**
     * @return {@link String}
     */
    String getValue();
    /**
     * @return {@link String}
     */
    String getType();
}