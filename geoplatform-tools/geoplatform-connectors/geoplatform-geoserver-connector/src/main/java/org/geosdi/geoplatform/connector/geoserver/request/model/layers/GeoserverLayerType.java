package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GeoserverLayerType {
    Raster("RASTER"),
    Vector("VECTOR");

    private final String type;

    GeoserverLayerType(String theType) {
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
     * @return {@link GeoserverLayerType}
     */
    @JsonCreator
    public static GeoserverLayerType forType(String type) {
        Optional<GeoserverLayerType> optional = Arrays
                .stream(GeoserverLayerType.values())
                .filter(v -> ((type != null) && !(type.trim().isEmpty()))
                        ? v.getType().equalsIgnoreCase(type) : Boolean.FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(Optional.empty()))) ? optional.get() : null;
    }
}