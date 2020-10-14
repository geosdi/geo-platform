package org.geosdi.geoplatform.websocket.support.spring.jasypt.crypt;

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
class GPWebSocketServerPooledPBEStringEncryptorConfig {

    /**
     * @param geoPlatformWebSocketServerPBE
     * @return {@link PooledPBEStringEncryptor}
     */
    @Bean
    public PooledPBEStringEncryptor geoPlatformWebSocketServerPooledPBEStringEncryptor(@Qualifier(value = "geoPlatformWebSocketServerPBE") PBEConfig geoPlatformWebSocketServerPBE) {
        BasePooledPBEStringEncryptorDecorator geoPlatformWebSocketServerPooledPBE = new BasePooledPBEStringEncryptorDecorator();
        geoPlatformWebSocketServerPooledPBE.setPbeConfig(geoPlatformWebSocketServerPBE);
        return geoPlatformWebSocketServerPooledPBE.pooledPBEStringEncryptor();
    }
}
