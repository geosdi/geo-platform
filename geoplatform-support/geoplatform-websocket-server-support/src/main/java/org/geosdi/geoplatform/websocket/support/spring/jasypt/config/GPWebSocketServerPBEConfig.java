package org.geosdi.geoplatform.websocket.support.spring.jasypt.config;

import org.geosdi.geoplatform.websocket.support.spring.configuration.jasypt.pbe.GPWebSocketServerPBEProperties;
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
class GPWebSocketServerPBEConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketServerPBEConfig.class);

    /**
     * @param geoPlatformWebSocketServerPBEProperties
     * @return {@link PBEConfig}
     */
    @Bean
    public PBEConfig geoPlatformWebSocketServerPBE(GPWebSocketServerPBEProperties geoPlatformWebSocketServerPBEProperties) {
        logger.debug("####################################GEO_PLATFORM_WEBSOCKET_SERVER_PBE_PASSWORD : {}\n\n",
                geoPlatformWebSocketServerPBEProperties.getPassword());
        return new SimpleStringPBEConfig() {

            {
                super.setPassword(geoPlatformWebSocketServerPBEProperties.getPassword());
                super.setPoolSize(2);
                super.setAlgorithm("PBEWithMD5AndDES");
            }

        };
    }
}