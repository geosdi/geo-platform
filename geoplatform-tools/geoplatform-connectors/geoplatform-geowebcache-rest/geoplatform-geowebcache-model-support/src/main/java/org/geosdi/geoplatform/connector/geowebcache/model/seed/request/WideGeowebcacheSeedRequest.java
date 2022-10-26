package org.geosdi.geoplatform.connector.geowebcache.model.seed.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.WideGeowebcacheSeedRequestValue;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedRequest.class)
public interface WideGeowebcacheSeedRequest extends Serializable {

    /**
     * @return {@link WideGeowebcacheSeedRequestValue}
     */
    WideGeowebcacheSeedRequestValue getSeedRequestValue();

    /**
     * @param theSeedRequestValue
     */
    void setSeedRequestValue(WideGeowebcacheSeedRequestValue theSeedRequestValue);
}
