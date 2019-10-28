package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.crypt;

import org.geosdi.geoplatform.jasypt.support.BasePooledPBEStringEncryptorDecorator;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestPooledPBEStringEncryptorConfig {

    /**
     * @param elasticSearchRestPBEConfig
     * @return {@link PooledPBEStringEncryptor}
     */
    @Bean
    public PooledPBEStringEncryptor elasticSearchRestPooledPBEStringEncryptor(@Qualifier(value = "elasticSearchRestPBEConfig") PBEConfig elasticSearchRestPBEConfig) {
        return new BasePooledPBEStringEncryptorDecorator() {
            {
                super.setPbeConfig(elasticSearchRestPBEConfig);
            }
        }.pooledPBEStringEncryptor();
    }
}