package org.geosdi.geoplatform.experimental.el.query.param.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPElasticSearchQueryValueType {
    NUMBER("Number"), TEMPORAL("Temporal"), STRING("String");

    private final String value;

    GPElasticSearchQueryValueType(String theValue) {
        this.value = theValue;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static GPElasticSearchQueryValueType fromValue(String value) {
        for (GPElasticSearchQueryValueType surveyType : GPElasticSearchQueryValueType.values()) {
            if (surveyType.getValue().equalsIgnoreCase(value)) {
                return surveyType;
            }
        }
        return null;
    }
}
