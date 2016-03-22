package org.geosdi.geoplatform.experimental.openam.jasypt.pbe.primary;

import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPOpenAMPBEPrimaryConfig {

    @Bean
    @Primary
    public PBEConfig openAMPBEConfig() {
        return new SimpleStringPBEConfig() {

            {
                super.setPassword("test#####");
                super.setPoolSize(4);
                super.setAlgorithm("SHA-1");
            }
        };
    }
}
