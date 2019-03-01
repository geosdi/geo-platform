package org.geosdi.geoplatform.services.rs.config.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.GPI18NMessageInterpoletor;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.validator.GPWFSRequestfValidator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPWFSRequestValidatorConfig {

    /**
     * @return {@link GPI18NValidator<GPI18NRequestValidator, String>}
     */
    @Bean
    public GPI18NValidator<GPI18NRequestValidator, String> wfsRequestValidator() {
        return new GPWFSRequestfValidator(new GPI18NMessageInterpoletor(new PlatformResourceBundleLocator("GPWFSMessages")));
    }
}