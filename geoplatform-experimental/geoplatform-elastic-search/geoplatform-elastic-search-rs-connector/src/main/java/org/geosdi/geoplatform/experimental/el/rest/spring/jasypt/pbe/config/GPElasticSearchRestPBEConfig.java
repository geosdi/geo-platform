package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.pbe.config;

import org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.pbe.properties.GPElasticSearchRestPBEProperties;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestPBEConfig {


    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRestPBEConfig.class);

    /**
     * @param elasticSearchRestPBEProperties
     * @return {@link PBEConfig}
     */
    @Bean
    public PBEConfig elasticSearchRestPBEConfig(GPElasticSearchRestPBEProperties elasticSearchRestPBEProperties) {
        logger.debug("####################################GP_ELASTICSEARCH_REST_PBE_PASSWORD : {}\n\n", elasticSearchRestPBEProperties.getPassword());
        return new SimpleStringPBEConfig() {

            {
                super.setPassword(elasticSearchRestPBEProperties.getPassword());
                super.setPoolSize(2);
                super.setAlgorithm("PBEWithMD5AndDES");
            }

        };
    }
}