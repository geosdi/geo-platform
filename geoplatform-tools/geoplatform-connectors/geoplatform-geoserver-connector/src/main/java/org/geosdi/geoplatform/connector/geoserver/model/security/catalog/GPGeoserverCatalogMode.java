package org.geosdi.geoplatform.connector.geoserver.model.security.catalog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverCatalogMode implements IGPGeoserverCatalogMode {

    HIDE("HIDE"),
    MIXED("MIXED"),
    CHALLENGE("CHALLENGE");

    private final String mode;

    /**
     * @param theMode
     */
    GPGeoserverCatalogMode(@Nonnull(when = NEVER) String theMode) {
        Preconditions.checkArgument((theMode != null) && !(theMode.trim().isEmpty()), "The Parameter mode must not be null or an empty string.");
        this.mode = theMode;
    }

    /**
     * @return {@link String}
     */
    @JsonValue
    @Override
    public String getMode() {
        return this.mode;
    }

    @Override
    public String toString() {
        return this.mode;
    }

    /**
     * @param theMode
     * @return {@link IGPGeoserverCatalogMode}
     */
    @JsonCreator
    public static IGPGeoserverCatalogMode forMode(String theMode) {
        Optional<IGPGeoserverCatalogMode> optional = stream(GPGeoserverCatalogMode.values())
                .filter(v -> ((theMode != null) && !(theMode.trim().isEmpty())) ? v.getMode().equalsIgnoreCase(theMode) : FALSE)
                .map(v -> (IGPGeoserverCatalogMode) v)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}