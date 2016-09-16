package org.geosdi.geoplatform.experimental.el.query.param.value;

import org.geosdi.geoplatform.experimental.el.query.param.extra.GPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.type.GPElasticSearchQueryValueType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryParamValue extends GPElasticSearchQueryExtraParamValue
        implements IGPElasticSearchQueryParamValue {

    private static final long serialVersionUID = 7790265320855430364L;
    //
    private GPElasticSearchQueryValueType valueType;

    public GPElasticSearchQueryParamValue() {
    }

    /**
     * @return {@link GPElasticSearchQueryValueType}
     */
    @Override
    public GPElasticSearchQueryValueType getValueType() {
        return this.valueType;
    }

    /**
     * @param theValueType
     */
    @Override
    public void setValueType(GPElasticSearchQueryValueType theValueType) {
        this.valueType = theValueType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " value = " + super.getValue() +
                ", valueType = " + valueType +
                '}';
    }
}
