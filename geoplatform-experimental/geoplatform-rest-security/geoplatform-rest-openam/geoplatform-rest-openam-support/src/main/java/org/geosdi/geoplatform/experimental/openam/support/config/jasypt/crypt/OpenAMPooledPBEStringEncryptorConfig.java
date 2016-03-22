package org.geosdi.geoplatform.experimental.openam.support.config.jasypt.crypt;

import org.geosdi.geoplatform.jasypt.support.BasePooledPBEStringEncryptorDecorator;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class OpenAMPooledPBEStringEncryptorConfig {

    @Bean(name = "openAMPooledPBEStringEncryptor")
    @Autowired
    public PooledPBEStringEncryptor openAMPooledPBEStringEncryptor(@Qualifier(value = "openAMPBEConfig") PBEConfig openAMPBEConfig) {
        BasePooledPBEStringEncryptorDecorator bpbe = new BasePooledPBEStringEncryptorDecorator();
        bpbe.setPbeConfig(openAMPBEConfig);

        return bpbe.pooledPBEStringEncryptor();
    }
}
