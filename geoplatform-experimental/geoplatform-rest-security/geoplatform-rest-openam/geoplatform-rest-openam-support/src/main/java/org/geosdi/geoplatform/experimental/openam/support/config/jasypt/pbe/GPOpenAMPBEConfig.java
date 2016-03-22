package org.geosdi.geoplatform.experimental.openam.support.config.jasypt.pbe;

import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPOpenAMPBEConfig {

    @Bean
    public PBEConfig openAMPBEConfig() {
        return new SimpleStringPBEConfig() {

            {
                super.setPassword("$-geosdi,0x");
                super.setPoolSize(2);
                super.setAlgorithm("PBEWithMD5AndDES");
            }
        };
    }
}
