/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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