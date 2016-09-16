package org.geosdi.geoplatform.experimental.el.query.model.adapter;

import org.geosdi.geoplatform.experimental.el.query.param.extra.IGPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.response.collection.GenericMapAdapter;
import org.geosdi.geoplatform.response.collection.GenericMapType;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryExtraParametersAdapter extends GenericMapAdapter<IGPElasticSearchQueryParamKey,
        IGPElasticSearchQueryExtraParamValue> {

    @Override
    public Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> unmarshal(GenericMapType<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> v) throws Exception {
        return super.unmarshal(v);
    }

    @Override
    public GenericMapType<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> marshal(Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> v) throws Exception {
        return super.marshal(v);
    }
}
