package org.geosdi.geoplatform.experimental.el.query.param.value;

import org.geosdi.geoplatform.experimental.el.query.param.extra.IGPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.type.GPElasticSearchQueryValueType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryParamValue<VALUE extends Object> extends IGPElasticSearchQueryExtraParamValue<VALUE> {

    /**
     * @return {@link GPElasticSearchQueryValueType}
     */
    GPElasticSearchQueryValueType getValueType();

    /**
     * @param theValueType
     */
    void setValueType(GPElasticSearchQueryValueType theValueType);

    /**
     * @return {@link String}
     */
    @Override
    default String getType() {
        return "QueryParamValue";
    }
}
