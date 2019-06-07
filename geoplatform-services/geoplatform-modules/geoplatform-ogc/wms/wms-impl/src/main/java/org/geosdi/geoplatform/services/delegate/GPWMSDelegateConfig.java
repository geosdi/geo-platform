package org.geosdi.geoplatform.services.delegate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPWMSDelegateConfig {

    /**
     * @return {@link GPWMSDelagate}
     */
    @Bean
    public GPWMSDelagate wmsDelegate() {
        return new GPWMSDelegateImpl();
    }
}