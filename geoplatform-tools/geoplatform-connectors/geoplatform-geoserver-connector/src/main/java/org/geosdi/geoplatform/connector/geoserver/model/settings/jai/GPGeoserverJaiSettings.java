package org.geosdi.geoplatform.connector.geoserver.model.settings.jai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPGeoserverJaiSettings implements IGPGeoserverJaiSettings {

    private static final long serialVersionUID = -6327902512604827045L;
    //
    private final boolean allowInterpolation;
    private final boolean recycling;
    private final Integer tilePriority;
    private final Integer tileThreads;
    private final Double memoryCapacity;
    private final Double memoryThreshold;
    private final boolean imageIOCache;
    private final boolean pngAcceleration;
    private final boolean jpegAcceleration;
    private final boolean allowNativeMosaic;
    private final boolean allowNativeWarp;

    /**
     * @param theAllowInterpolation
     * @param theRecycling
     * @param theTilePriority
     * @param theTileThreads
     * @param theMemoryCapacity
     * @param theMemoryThreshold
     * @param theImageIOCache
     * @param thePngAcceleration
     * @param theJpegAcceleration
     * @param theAllowNativeMosaic
     * @param theAllowNativeWarp
     */
    @JsonCreator
    public GPGeoserverJaiSettings(@JsonProperty(value = "allowInterpolation") boolean theAllowInterpolation, @JsonProperty(value = "recycling") boolean theRecycling,
            @JsonProperty(value = "tilePriority") Integer theTilePriority, @JsonProperty(value = "tileThreads") Integer theTileThreads,
            @JsonProperty(value = "memoryCapacity") Double theMemoryCapacity, @JsonProperty(value = "memoryThreshold") Double theMemoryThreshold,
            @JsonProperty(value = "imageIOCache") boolean theImageIOCache, @JsonProperty(value = "pngAcceleration") boolean thePngAcceleration,
            @JsonProperty(value = "jpegAcceleration") boolean theJpegAcceleration, @JsonProperty(value = "allowNativeMosaic") boolean theAllowNativeMosaic,
            @JsonProperty(value = "allowNativeWarp") boolean theAllowNativeWarp) {
        this.allowInterpolation = theAllowInterpolation;
        this.recycling = theRecycling;
        this.tilePriority = theTilePriority;
        this.tileThreads = theTileThreads;
        this.memoryCapacity = theMemoryCapacity;
        this.memoryThreshold = theMemoryThreshold;
        this.imageIOCache = theImageIOCache;
        this.pngAcceleration = thePngAcceleration;
        this.jpegAcceleration = theJpegAcceleration;
        this.allowNativeMosaic = theAllowNativeMosaic;
        this.allowNativeWarp = theAllowNativeWarp;
    }
}