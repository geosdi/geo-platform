package org.geosdi.geoplatform.connector.geoserver.model.store;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GeoserverStoreInfoType {

    COVERAGE("coverageStore"),
    FEATURE("dataStore");

    private final String type;

    GeoserverStoreInfoType(String theType) {
        this.type = theType;
    }

    /**
     * @return {@link String}
     */
    @JsonValue
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    /**
     * @param type
     * @return {@link GeoserverStoreInfoType}
     */
    @JsonCreator
    public static GeoserverStoreInfoType forType(String type) {
        Optional<GeoserverStoreInfoType> optional = stream(GeoserverStoreInfoType.values())
                .filter(v -> ((type != null) && !(type.trim().isEmpty()))
                        ? v.getType().equalsIgnoreCase(type) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}
