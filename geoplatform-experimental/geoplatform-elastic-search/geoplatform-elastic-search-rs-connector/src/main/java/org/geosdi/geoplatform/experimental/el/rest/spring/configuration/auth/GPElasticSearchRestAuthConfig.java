package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestAuthConfig {

    /**
     * @param theUserName
     * @param thePassword
     * @return {@link GPElasticSearchRSAuthConfiguration}
     */
    @Bean
    public GPElasticSearchRSAuthConfiguration elasticSearchRestAuthConfiguration(@Value("elasticSearchRestConfigurator{gp.elasticsearch.rest.username:@null}") String theUserName,
            @Value("elasticSearchRestConfigurator{gp.elasticsearch.rest.password:@null}") String thePassword) {
        return new ElasticSearchRSAuthConfiguration(theUserName, thePassword);
    }
}