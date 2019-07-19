package org.geosdi.geoplatform.connector.geoserver.model.crs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
public class GPGeoserverCRS implements IGPGeoserverCRS {

    private static final long serialVersionUID = -2174733277323184810L;
    //
    private final String value;
    private final String type;

    /**
     * @param theValue
     * @param theType
     */
    @JsonCreator
    public GPGeoserverCRS(@Nonnull(when = NEVER) @JsonProperty(value = "$") String theValue, @Nonnull(when = NEVER) @JsonProperty(value = "@class") String theType) {
        checkArgument(((theValue != null) && !(theValue.trim().isEmpty())), "The Parameter value must not be null or an empty string.");
        checkArgument(((theType != null) && !(theType.trim().isEmpty())), "The Parameter type must not be null or an empty string.");
        this.value = theValue;
        this.type = theType;
    }
}