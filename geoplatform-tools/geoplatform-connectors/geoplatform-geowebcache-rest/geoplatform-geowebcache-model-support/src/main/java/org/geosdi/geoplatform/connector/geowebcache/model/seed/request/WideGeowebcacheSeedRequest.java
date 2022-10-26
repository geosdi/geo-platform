package org.geosdi.geoplatform.connector.geowebcache.model.seed.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.IGeowebcacheSeedRequestValue;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedRequest.class)
public interface WideGeowebcacheSeedRequest extends Serializable {

    /**
     * @return {@link IGeowebcacheSeedRequestValue}
     */
    IGeowebcacheSeedRequestValue getSeedRequestValue();

    /**
     * @param theSeedRequestValue
     */
    void setSeedRequestValue(IGeowebcacheSeedRequestValue theSeedRequestValue);
}
