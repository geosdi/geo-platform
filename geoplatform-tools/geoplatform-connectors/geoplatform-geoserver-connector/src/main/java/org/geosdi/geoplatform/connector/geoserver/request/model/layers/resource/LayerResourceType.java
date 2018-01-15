package org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Optional.empty;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum LayerResourceType {
    Coverage("coverage"),
    Feature("featureType");

    private final String type;

    LayerResourceType(String theType) {
        this.type = theType;
    }

    /**
     * @return {@link String}
     */
    @JsonValue
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     * @return {@link LayerResourceType}
     */
    @JsonCreator
    public static LayerResourceType forType(String type) {
        Optional<LayerResourceType> optional = Arrays
                .stream(LayerResourceType.values())
                .filter(v -> ((type != null) && !(type.trim().isEmpty()))
                        ? v.getType().equalsIgnoreCase(type) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}