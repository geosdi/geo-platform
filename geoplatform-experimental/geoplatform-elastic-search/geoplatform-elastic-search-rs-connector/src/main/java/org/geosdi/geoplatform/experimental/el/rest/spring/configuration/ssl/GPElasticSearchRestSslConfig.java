package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestSslConfig {

    /**
     * @param theKeyStorePath
     * @param theKeyStorePassword
     * @return {@link GPElasticSearchRSSslConfiguration}
     */
    @Bean
    public GPElasticSearchRSSslConfiguration elasticSearchRSSslConfiguration(@Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.key_store:@null}") String theKeyStorePath,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.key_store.password:@null}") String theKeyStorePassword) {
        return new ElasticSearchRSSslConfiguration(theKeyStorePath, theKeyStorePassword);
    }
}