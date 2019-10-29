package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base;

import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth.GPElasticSearchRSAuthConfiguration;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRSBaseConfig {

    /**
     * @param theHttpHost
     * @param theConnectionTimeout
     * @param theSocketTimeout
     * @param theMaxTotalConnections
     * @param theDefaultMaxPerRoute
     * @param theMaxRedirects
     * @param theElasticSearchRestAuthConfiguration
     * @param theElasticSearchRSSslConfiguration
     * @return {@link GPElasticSearchRSBaseConfiguration}
     */
    @Bean
    public GPElasticSearchRSBaseConfiguration elasticSearchRSBaseConfiguration(
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.hosts:@null}") String theHttpHost,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.connection.timeout:@null}") Integer theConnectionTimeout,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.socket.timeout:@null}") Integer theSocketTimeout,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.max_total_connections:@null}") Integer theMaxTotalConnections,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.default_max_per_route:@null}") Integer theDefaultMaxPerRoute,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.max_redirects:@null}") Integer theMaxRedirects,
            @Qualifier(value = "elasticSearchRestAuthConfiguration") GPElasticSearchRSAuthConfiguration theElasticSearchRestAuthConfiguration,
            @Qualifier(value = "elasticSearchRSSslConfiguration") GPElasticSearchRSSslConfiguration theElasticSearchRSSslConfiguration) {
        return new ElasticSearchRSBaseConfiguration(theHttpHost, theConnectionTimeout, theSocketTimeout, theMaxTotalConnections, theDefaultMaxPerRoute, theMaxRedirects, theElasticSearchRestAuthConfiguration, theElasticSearchRSSslConfiguration);
    }
}