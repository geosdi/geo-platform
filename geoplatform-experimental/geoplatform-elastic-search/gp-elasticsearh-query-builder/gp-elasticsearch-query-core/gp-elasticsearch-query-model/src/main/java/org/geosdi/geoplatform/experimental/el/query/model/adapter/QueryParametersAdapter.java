package org.geosdi.geoplatform.experimental.el.query.model.adapter;

import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.value.IGPElasticSearchQueryParamValue;
import org.geosdi.geoplatform.response.collection.GenericMapAdapter;
import org.geosdi.geoplatform.response.collection.GenericMapType;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryParametersAdapter extends GenericMapAdapter<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> {

    @Override
    public Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> unmarshal(GenericMapType<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> v) throws Exception {
        return super.unmarshal(v);
    }

    @Override
    public GenericMapType<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> marshal(Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> v) throws Exception {
        return super.marshal(v);
    }
}
