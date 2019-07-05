package org.geosdi.geoplatform.support.google.spring.jasypt.crypt;

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
class GPGoogleServicePooledPBEStringEncryptorConfig {

    /**
     * @param googleServicePBEConfig
     * @return {@link PooledPBEStringEncryptor}
     */
    @Bean
    public PooledPBEStringEncryptor googleServicePooledPBEStringEncryptor(@Qualifier(value = "googleServicePBEConfig") PBEConfig googleServicePBEConfig) {
        BasePooledPBEStringEncryptorDecorator googleServicePooledPBE = new BasePooledPBEStringEncryptorDecorator();
        googleServicePooledPBE.setPbeConfig(googleServicePBEConfig);
        return googleServicePooledPBE.pooledPBEStringEncryptor();
    }
}