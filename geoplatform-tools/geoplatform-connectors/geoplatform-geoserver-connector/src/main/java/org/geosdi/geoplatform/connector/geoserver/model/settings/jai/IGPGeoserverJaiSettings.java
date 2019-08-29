package org.geosdi.geoplatform.connector.geoserver.model.settings.jai;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverJaiSettings.class)
public interface IGPGeoserverJaiSettings extends Serializable {

    /**
     * @return {@link Boolean}
     */
    boolean isAllowInterpolation();

    /**
     * @return {@link Boolean}
     */
    boolean isRecycling();

    /**
     * @return {@link Integer}
     */
    Integer getTilePriority();

    /**
     * @return {@link Integer}
     */
    Integer getTileThreads();

    /**
     * @return {@link Double}
     */
    Double getMemoryCapacity();

    /**
     * @return {@link Double}
     */
    Double getMemoryThreshold();

    /**
     * @return {@link Boolean}
     */
    boolean isImageIOCache();

    /**
     * @return {@link Boolean}
     */
    boolean isPngAcceleration();

    /**
     * @return {@link Boolean}
     */
    boolean isJpegAcceleration();

    /**
     * @return {@link Boolean}
     */
    boolean isAllowNativeMosaic();

    /**
     * @return {@link Boolean}
     */
    boolean isAllowNativeWarp();
}