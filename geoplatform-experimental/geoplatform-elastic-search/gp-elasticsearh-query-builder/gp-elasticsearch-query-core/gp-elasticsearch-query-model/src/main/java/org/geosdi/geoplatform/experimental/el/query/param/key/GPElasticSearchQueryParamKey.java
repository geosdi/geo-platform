package org.geosdi.geoplatform.experimental.el.query.param.key;

import java.util.Objects;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryParamKey implements IGPElasticSearchQueryParamKey {

    private static final long serialVersionUID = -8318187951909738818L;
    //
    private String key;

    public GPElasticSearchQueryParamKey() {
    }

    public GPElasticSearchQueryParamKey(String theKey) {
        this.key = theKey;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * @param theKey
     */
    @Override
    public void setKey(String theKey) {
        this.key = theKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GPElasticSearchQueryParamKey)) return false;
        GPElasticSearchQueryParamKey that = (GPElasticSearchQueryParamKey) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " key = '" + key + '\'' +
                '}';
    }
}
