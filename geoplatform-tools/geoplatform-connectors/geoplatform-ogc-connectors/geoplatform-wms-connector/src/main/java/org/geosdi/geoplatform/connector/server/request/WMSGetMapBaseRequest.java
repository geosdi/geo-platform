package org.geosdi.geoplatform.connector.server.request;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
public class WMSGetMapBaseRequest implements GPWMSGetMapBaseRequest {

    private static final long serialVersionUID = -8217130086281433263L;
    //
    private final GPWMSBoundingBox boundingBox;
    private final Set<String> layers;
    private final String srs;
    private final String width;
    private final String height;
    private String wmsGetMapKeyValuePair;

    /**
     * @param theBoundingBox
     * @param theLayers
     * @param theSrs
     * @param theWitdth
     * @param theHeight
     */
    public WMSGetMapBaseRequest(@Nonnull(when = NEVER) GPWMSBoundingBox theBoundingBox, @Nonnull(when = NEVER) Set<String> theLayers,
            @Nonnull(when = NEVER) String theSrs, @Nonnull(when = NEVER) String theWitdth, @Nonnull(when = NEVER) String theHeight) {
        checkArgument(theBoundingBox != null, "The Parameter boundingBox must not be null.");
        checkArgument(theLayers != null, "The Parameter layers must not be null.");
        checkArgument((theSrs != null) && !(theSrs.trim().isEmpty()), "The Parameter srs must not be null or an empty string.");
        checkArgument((theWitdth != null) && !(theWitdth.trim().isEmpty()), " The Parameter width must non be null or an empty string.");
        checkArgument((theHeight != null) && !(theHeight.trim().isEmpty()), "The Parameter height must not be null or an empty string.");
        Set<String> layersWithNonNullAndEmptyValues = theLayers.stream()
                .filter(Objects::nonNull)
                .filter(value -> !value.trim().isEmpty())
                .collect(toSet());
        checkArgument((layersWithNonNullAndEmptyValues != null) && !(layersWithNonNullAndEmptyValues.isEmpty()),
                "The Parameter layers must not contains null values or empty values.");
        this.boundingBox = theBoundingBox;
        this.layers = theLayers;
        this.srs = theSrs;
        this.width = theWitdth;
        this.height = theHeight;
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
        String layersWMSKeyValuePair = this.layers.stream()
                .filter(Objects::nonNull)
                .filter(value -> !value.trim().isEmpty())
                .collect(joining(",", "LAYERS=", ""));
        return of(layersWMSKeyValuePair, "SRS=".concat(this.srs), this.boundingBox.toWMSKeyValuePair(), "WIDTH=".concat(this.width),
                "HEIGHT=".concat(this.height))
                .collect(joining("&"));
    }
}