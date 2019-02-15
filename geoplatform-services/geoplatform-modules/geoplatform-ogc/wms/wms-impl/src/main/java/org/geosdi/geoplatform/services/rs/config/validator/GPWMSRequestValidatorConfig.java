package org.geosdi.geoplatform.services.rs.config.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.GPI18NMessageInterpoletor;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.validator.GPWMSRequestfValidator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPWMSRequestValidatorConfig {

    /**
     * @return {@link GPI18NValidator<GPI18NRequestValidator, String>}
     */
    @Bean
    public GPI18NValidator<GPI18NRequestValidator, String> wmsRequestValidator() {
        return new GPWMSRequestfValidator(new GPI18NMessageInterpoletor(new PlatformResourceBundleLocator("GPWMSMessages")));
    }
}