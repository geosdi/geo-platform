package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedResponse.class)
public interface GPGeowebcacheResponse extends Serializable {

    /**
     * @return {@link List<List<Long>>}
     */
    List<List<Long>> getStatusTaskValues();

    /**
     * @return {@link Boolean}
     */
    @JsonIgnore
    default Boolean isTerminated() {
        return (this.getStatusTaskValues().isEmpty());
    }

    /**
     * @param theStatusTaskValues
     * @return {@link GPGeowebcacheResponse}
     * @throws Exception
     */
    static GPGeowebcacheResponse of(@Nonnull(when = NEVER) List<List<Long>> theStatusTaskValues) throws Exception {
        return new GeowebcacheSeedResponse(theStatusTaskValues);
    }
}