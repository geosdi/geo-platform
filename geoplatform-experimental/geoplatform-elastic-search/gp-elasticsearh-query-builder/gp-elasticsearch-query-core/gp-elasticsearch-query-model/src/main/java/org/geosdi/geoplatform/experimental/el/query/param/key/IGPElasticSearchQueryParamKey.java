package org.geosdi.geoplatform.experimental.el.query.param.key;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GPElasticSearchQueryParamKey.class, name = "queryParamKey")})
public interface IGPElasticSearchQueryParamKey<KEY extends String> extends Serializable {

    /**
     * @return {@link String}
     */
    KEY getKey();

    /**
     * @param theKey
     */
    void setKey(KEY theKey);
}
