package org.geosdi.geoplatform.experimental.el.query.task.function;

import org.geosdi.geoplatform.experimental.el.query.param.key.GPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.type.GPElasticSearchQueryValueType;
import org.geosdi.geoplatform.experimental.el.query.param.value.GPElasticSearchQueryParamValue;

import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryParamValueFunction implements Function<Integer, GPElasticSearchQueryParamValue> {

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public GPElasticSearchQueryParamValue apply(Integer integer) {
        return new GPElasticSearchQueryParamValue() {

            {
                super.setKeyValue(new GPElasticSearchQueryParamKey("KEY@" + integer));
                super.setValue(integer);
                super.setValueType(GPElasticSearchQueryValueType.NUMBER);
            }
        };
    }
}
