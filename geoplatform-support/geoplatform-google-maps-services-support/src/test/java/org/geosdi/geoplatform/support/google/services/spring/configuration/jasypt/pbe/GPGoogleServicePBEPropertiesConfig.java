package org.geosdi.geoplatform.support.google.services.spring.configuration.jasypt.pbe;

import org.geosdi.geoplatform.jasypt.support.env.GPPBESystemEnvProperties;
import org.geosdi.geoplatform.support.google.spring.configuration.jasypt.pbe.GPGoogleServicePBEProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPGoogleServicePBEPropertiesConfig {

    /**
     * @return {@link GPPBESystemEnvProperties}
     */
    @Bean
    public GPPBESystemEnvProperties gpGoogleServicePBEProperties() {
        return new GPGoogleServicePBEProperties("GP_GOOGLE_SERVICE_PBE_KEY");
    }
}