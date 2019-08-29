package org.geosdi.geoplatform.connector.geoserver.model.settings.coverage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCoverageAccessSettings.class)
public interface IGPGeoserverCoverageAccessSettings extends Serializable {

    /**
     * @return {@link Integer}
     */
    Integer getMaxPoolSize();

    /**
     * @return {@link Integer}
     */
    Integer getCorePoolSize();

    /**
     * @return {@link Long}
     */
    Long getKeepAliveTime();

    /**
     * @return {@link String}
     */
    String getQueueType();

    /**
     * @return {@link Integer}
     */
    Integer getImageIOCacheThreshold();
}