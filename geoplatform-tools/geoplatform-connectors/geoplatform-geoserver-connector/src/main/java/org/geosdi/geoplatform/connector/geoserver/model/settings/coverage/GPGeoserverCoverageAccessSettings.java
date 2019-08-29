package org.geosdi.geoplatform.connector.geoserver.model.settings.coverage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
public class GPGeoserverCoverageAccessSettings implements IGPGeoserverCoverageAccessSettings {

    private static final long serialVersionUID = -8912380047398796481L;
    //
    private final Integer maxPoolSize;
    private final Integer corePoolSize;
    private final Long keepAliveTime;
    private final String queueType;
    private final Integer imageIOCacheThreshold;

    /**
     * @param theMaxPoolSize
     * @param theCorePoolSize
     * @param theKeepAliveTime
     * @param theQueueType
     * @param theImageIOCacheThreshold
     */
    @JsonCreator
    public GPGeoserverCoverageAccessSettings(@JsonProperty(value = "maxPoolSize") Integer theMaxPoolSize, @JsonProperty(value = "corePoolSize") Integer theCorePoolSize,
            @JsonProperty(value = "keepAliveTime") Long theKeepAliveTime, @JsonProperty(value = "queueType") String theQueueType,
            @JsonProperty(value = "imageIOCacheThreshold") Integer theImageIOCacheThreshold) {
        this.maxPoolSize = theMaxPoolSize;
        this.corePoolSize = theCorePoolSize;
        this.keepAliveTime = theKeepAliveTime;
        this.queueType = theQueueType;
        this.imageIOCacheThreshold = theImageIOCacheThreshold;
    }
}