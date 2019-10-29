package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.pbe.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestPBEPropertiesConfig {

    /**
     * @return {@link GPElasticSearchRestPBEProperties}
     */
    @Bean
    public GPElasticSearchRestPBEProperties elasticSearchRestPBEProperties() {
        return new GPElasticSearchRestPBEProperties("GP_ELASTICSEARCH_REST_PBE_KEY");
    }
}