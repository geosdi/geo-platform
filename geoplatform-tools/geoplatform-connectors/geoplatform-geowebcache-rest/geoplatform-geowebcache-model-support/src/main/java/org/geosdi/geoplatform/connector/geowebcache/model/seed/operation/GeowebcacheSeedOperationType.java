package org.geosdi.geoplatform.connector.geowebcache.model.seed.operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GeowebcacheSeedOperationType implements WideGeowebcacheSeedOperationType {

    RESEED("reseed"),
    TRUNCATE("truncate");

    private final String type;

    /**
     * @param theType
     */
    GeowebcacheSeedOperationType(String theType) {
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
     * @param type
     * @return {@link WideGeowebcacheSeedOperationType}
     */
    @JsonCreator
    public static WideGeowebcacheSeedOperationType forType(String type) {
        Optional<WideGeowebcacheSeedOperationType> optional = Arrays
                .stream(GeowebcacheSeedOperationType.values())
                .filter(v -> ((type != null) && !(type.trim().isEmpty()))
                        ? v.getType().equalsIgnoreCase(type) : FALSE)
                .map(value -> (WideGeowebcacheSeedOperationType) value)
                .findFirst();
        return ((optional != null) && !(optional.equals(Optional.empty()))) ? optional.get() : null;
    }
}