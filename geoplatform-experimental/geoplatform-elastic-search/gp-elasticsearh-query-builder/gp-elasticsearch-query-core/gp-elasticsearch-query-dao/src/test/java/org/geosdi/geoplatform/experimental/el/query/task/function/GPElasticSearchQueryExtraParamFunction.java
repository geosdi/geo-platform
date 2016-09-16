package org.geosdi.geoplatform.experimental.el.query.task.function;

import org.geosdi.geoplatform.experimental.el.query.param.extra.GPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.key.GPElasticSearchQueryParamKey;

import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryExtraParamFunction implements Function<Integer,
        GPElasticSearchQueryExtraParamValue> {

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public GPElasticSearchQueryExtraParamValue apply(Integer integer) {
        return new GPElasticSearchQueryExtraParamValue() {

            {
                super.setKeyValue(new GPElasticSearchQueryParamKey("KEY_EXTRA@" + integer));
                super.setValue(integer);
            }
        };
    }
}
