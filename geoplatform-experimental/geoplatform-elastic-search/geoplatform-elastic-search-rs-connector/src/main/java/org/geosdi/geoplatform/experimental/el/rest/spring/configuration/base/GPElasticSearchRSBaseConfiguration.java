package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base;

import org.apache.http.HttpHost;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth.GPElasticSearchRSAuthConfiguration;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.pool.GPElasticSearchRSPoolConfiguration;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;

import static java.lang.Long.valueOf;
import static java.time.Duration.ofSeconds;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRSBaseConfiguration extends GPElasticSearchRSPoolConfiguration {

    /**
     * @return {@link HttpHost[]}
     */
    HttpHost[] getHttpHosts();

    /**
     * @return {@link Integer}
     */
    Integer getConnectionTimeout();

    /**
     * @return {@link Integer}
     */
    Integer getSocketTimeout();

    /**
     * @param <GPElasticSearchRSAuth>
     * @return {@link GPElasticSearchRSAuth}
     */
    <GPElasticSearchRSAuth extends GPElasticSearchRSAuthConfiguration> GPElasticSearchRSAuth getAuthConfig();

    /**
     * @param <GPElasticSearchRSSsl>
     * @return {@link GPElasticSearchRSSsl}
     */
    <GPElasticSearchRSSsl extends GPElasticSearchRSSslConfiguration> GPElasticSearchRSSsl getSslConfig();

    /**
     * @return {@link Integer}
     */
    default Integer defaultConnectionTimeout() {
        return valueOf(ofSeconds(2).toMillis()).intValue();
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultSocketTimeout() {
        return valueOf(ofSeconds(30).toMillis()).intValue();
    }
}