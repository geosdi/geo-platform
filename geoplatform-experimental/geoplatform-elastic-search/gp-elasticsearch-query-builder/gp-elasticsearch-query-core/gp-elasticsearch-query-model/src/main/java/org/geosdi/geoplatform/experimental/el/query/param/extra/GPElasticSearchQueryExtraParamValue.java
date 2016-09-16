package org.geosdi.geoplatform.experimental.el.query.param.extra;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.experimental.el.query.param.key.GPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryExtraParamValue implements IGPElasticSearchQueryExtraParamValue {

    private static final long serialVersionUID = 2177631736241219137L;
    //
    private Object value;
    @JsonDeserialize(as = GPElasticSearchQueryParamKey.class)
    private IGPElasticSearchQueryParamKey keyValue;

    public GPElasticSearchQueryExtraParamValue() {
    }

    /**
     * @return {@link Object}
     */
    @Override
    public Object getValue() {
        return this.value;
    }

    /**
     * @param theValue
     */
    @Override
    public void setValue(Object theValue) {
        this.value = theValue;
    }

    /**
     * @return {@link GPElasticSearchQueryParamKey}
     */
    @Override
    public IGPElasticSearchQueryParamKey getKeyValue() {
        return this.keyValue;
    }

    /**
     * @param theKeyValue
     */
    @Override
    public void setKeyValue(IGPElasticSearchQueryParamKey theKeyValue) {
        this.keyValue = theKeyValue;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " value = " + this.value +
                ", keyValue = " + this.keyValue +
                '}';
    }
}
