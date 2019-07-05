package org.geosdi.geoplatform.support.google.spring.jasypt.config;

import org.geosdi.geoplatform.jasypt.support.env.GPPBESystemEnvProperties;
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
class GPGoogleServicePBEConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPGoogleServicePBEConfig.class);

    /**
     * @param googleServicePBEProperties
     * @return {@link PBEConfig}
     */
    @Bean
    public PBEConfig googleServicePBEConfig(GPPBESystemEnvProperties googleServicePBEProperties) {
        logger.debug("####################################GOOGLE_SERVICE_PBE_PASSWORD : {}\n\n", googleServicePBEProperties.getPassword());
        return new SimpleStringPBEConfig() {

            {
                super.setPassword(googleServicePBEProperties.getPassword());
                super.setPoolSize(2);
                super.setAlgorithm("PBEWithMD5AndDES");
            }

        };
    }
}