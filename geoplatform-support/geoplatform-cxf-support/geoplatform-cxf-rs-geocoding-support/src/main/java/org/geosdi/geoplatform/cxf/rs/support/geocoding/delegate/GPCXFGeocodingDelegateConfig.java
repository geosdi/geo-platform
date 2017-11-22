package org.geosdi.geoplatform.cxf.rs.support.geocoding.delegate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPCXFGeocodingDelegateConfig {

    @Bean
    public IGPCXFGeocodingDelegate gpCXFGeocodingDelegate() {
        return new GPCXFGeocodingDelegate();
    }
}
