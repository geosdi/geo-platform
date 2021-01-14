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
package org.geosdi.geoplatform.connector.server.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKeyValuePair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static lombok.AccessLevel.NONE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(exclude = {"wmsGetMapKeyValuePair"})
@Immutable
public class WMSGetMapBaseRequest implements GPWMSGetMapBaseRequest {

    private static final long serialVersionUID = -8217130086281433263L;
    //
    private final GPWMSBoundingBox boundingBox;
    private final Collection<String> layers;
    private final String srs;
    private final String width;
    private final String height;
    private final Collection<GPWMSRequestKeyValuePair> extraParams;
    @Getter(NONE)
    private String wmsGetMapKeyValuePair;

    /**
     * @param theBoundingBox
     * @param theLayers
     * @param theSrs
     * @param theWitdth
     * @param theHeight
     */
    public WMSGetMapBaseRequest(@Nonnull(when = NEVER) GPWMSBoundingBox theBoundingBox, @Nonnull(when = NEVER) Collection<String> theLayers,
            @Nonnull(when = NEVER) String theSrs, @Nonnull(when = NEVER) String theWitdth, @Nonnull(when = NEVER) String theHeight) {
        this(theBoundingBox, theLayers, theSrs, theWitdth, theHeight, Collections.EMPTY_LIST);
    }

    /**
     * @param theBoundingBox
     * @param theLayers
     * @param theSrs
     * @param theWitdth
     * @param theHeight
     * @param theExtraParams
     */
    @JsonCreator
    public WMSGetMapBaseRequest(@JsonProperty(value = "boundingBox") @Nonnull(when = NEVER) GPWMSBoundingBox theBoundingBox,
            @JsonProperty(value = "layers") @Nonnull(when = NEVER) Collection<String> theLayers, @JsonProperty(value = "srs") @Nonnull(when = NEVER) String theSrs,
            @JsonProperty(value = "width") @Nonnull(when = NEVER) String theWitdth, @JsonProperty(value = "height") @Nonnull(when = NEVER) String theHeight,
            @JsonProperty(value = "extraParams") @Nullable Collection<GPWMSRequestKeyValuePair> theExtraParams) {
        checkArgument(theBoundingBox != null, "The Parameter boundingBox must not be null.");
        checkArgument((theLayers != null) && !(theLayers.isEmpty()), "The Parameter layers must not be null or an empty list.");
        checkArgument((theSrs != null) && !(theSrs.trim().isEmpty()), "The Parameter srs must not be null or an empty string.");
        checkArgument((theWitdth != null) && !(theWitdth.trim().isEmpty()), " The Parameter width must non be null or an empty string.");
        checkArgument((theHeight != null) && !(theHeight.trim().isEmpty()), "The Parameter height must not be null or an empty string.");
        List<String> layersWithNonNullAndEmptyValues = theLayers.stream()
                .filter(Objects::nonNull)
                .filter(v -> !(v.trim().isEmpty()))
                .distinct()
                .collect(toList());
        checkArgument(!(layersWithNonNullAndEmptyValues.isEmpty()), "The Parameter layers must not contains null or empty values.");
        this.boundingBox = theBoundingBox;
        this.layers = layersWithNonNullAndEmptyValues;
        this.srs = theSrs;
        this.width = theWitdth;
        this.height = theHeight;
        this.extraParams = theExtraParams;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toWMSKeyValuePair() {
        return this.wmsGetMapKeyValuePair = ((this.wmsGetMapKeyValuePair != null) ? this.wmsGetMapKeyValuePair : this.toInternalWMSKeyValuePair());
    }

    /**
     * @return {@link String}
     */
    String toInternalWMSKeyValuePair() {
        return of(this.layers.stream()
                        .filter(Objects::nonNull)
                        .filter(value -> !value.trim().isEmpty())
                        .collect(joining(",", "LAYERS=", "")), of("SRS", this.srs).collect(joining("=")),
                this.boundingBox.toWMSKeyValuePair(), of("WIDTH", this.width).collect(joining("=")), of("HEIGHT", this.height).collect(joining("=")))
                .collect(joining("&"));
    }
}