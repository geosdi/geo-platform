package org.geosdi.geoplatform.connector.geoserver.model.connection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverConnectionParam.class)
public interface IGPGeoserverConnectionParam extends Serializable {

    /**
     * @return {@link String}
     */
    String getKey();

    /**
     * @return {@link String}
     */
    String getValue();
}