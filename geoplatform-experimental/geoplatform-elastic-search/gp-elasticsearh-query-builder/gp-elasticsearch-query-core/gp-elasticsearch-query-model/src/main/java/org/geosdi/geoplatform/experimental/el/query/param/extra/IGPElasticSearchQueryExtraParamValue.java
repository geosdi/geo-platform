package org.geosdi.geoplatform.experimental.el.query.param.extra;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.value.GPElasticSearchQueryParamValue;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GPElasticSearchQueryExtraParamValue.class, name = "QueryExtraParamValue"),
        @JsonSubTypes.Type(value = GPElasticSearchQueryParamValue.class, name = "QueryParamValue")})
public interface IGPElasticSearchQueryExtraParamValue<VALUE extends Object> extends Serializable {

    /**
     * @return {@link VALUE}
     */
    VALUE getValue();

    /**
     * @param theValue
     */
    void setValue(VALUE theValue);

    /**
     * @param <KEY>
     * @param <KEY_VALUE>
     * @return {@link IGPElasticSearchQueryParamKey<KEY>}
     */
    <KEY extends String, KEY_VALUE extends IGPElasticSearchQueryParamKey<KEY>> KEY_VALUE getKeyValue();

    /**
     * @param theKeyValue
     * @param <KEY>
     * @param <KEY_VALUE>
     */
    <KEY extends String, KEY_VALUE extends IGPElasticSearchQueryParamKey<KEY>> void setKeyValue(KEY_VALUE theKeyValue);

    /**
     * @return {@link String}
     */
    default String getType() {
        return "QueryExtraParamValue";
    }
}
