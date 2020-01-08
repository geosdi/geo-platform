package org.geosdi.geoplatform.rs.support.request.linker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPLinkerType implements IGPLinkerType {
    L("link"),
    U("unlink");

    private final String type;

    /**
     * @param theType
     */
    GPLinkerType(@Nonnull(when = NEVER) String theType) {
        checkArgument(((theType != null) && !(theType.trim().isEmpty())),"The Parameter type must not be null or an empty string.");
        this.type = theType;
    }

    /**
     * @return {@link String}
     */
    @JsonValue
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    /**
     * @param theType
     * @return {@link GPLinkerType}
     */
    @JsonCreator
    public static GPLinkerType forType(String theType) {
        Optional<GPLinkerType> optional = stream(GPLinkerType.values())
                .filter(s -> ((theType != null)) ? s.getType().equalsIgnoreCase(theType) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}