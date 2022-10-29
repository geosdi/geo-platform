package org.geosdi.geoplatform.connector.geowebcache.model.seed.operation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedOperationType.class)
public interface GPGeowebcacheSeedOperationType extends Serializable {

    /**
     * @return {@link String}
     */
    String getType();
}
