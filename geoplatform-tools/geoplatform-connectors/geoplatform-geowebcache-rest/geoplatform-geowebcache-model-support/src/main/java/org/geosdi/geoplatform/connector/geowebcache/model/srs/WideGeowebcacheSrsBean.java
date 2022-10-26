package org.geosdi.geoplatform.connector.geowebcache.model.srs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSrsBean.class)
public interface WideGeowebcacheSrsBean extends Serializable {

    /**
     * @return {@link Integer}
     */
    Integer getNumber();

    /**
     * @param theNumber
     */
    void setNumber(Integer theNumber);
}
