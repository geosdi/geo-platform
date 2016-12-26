package org.geosdi.geoplatform.support.mail.spring.freemarker;

import freemarker.template.TemplateExceptionHandler;
import org.geosdi.geoplatform.support.mail.spring.configuration.freemarker.IGPFreeMarkerConfigLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Profile(value = "GPMailFreeMarkerSupport")
class GPSpringFreeMarkerConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPSpringFreeMarkerConfig.class);

    @Bean(value = "gpFreeMarkerConfiguration")
    public static freemarker.template.Configuration gpFreeMarkerConfiguration(@Qualifier(value = "gpFreeMarkerConfigLocation") IGPFreeMarkerConfigLocation gpFreeMarkerConfigLocation)
            throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@GP_MAIL_SUPPORT FreeMarkerConfigLocation at --------------> {}\n",
                gpFreeMarkerConfigLocation);
        freemarker.template.Configuration freeMarkerConfiguration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_25);
        freeMarkerConfiguration.setDirectoryForTemplateLoading(gpFreeMarkerConfigLocation.getFreeMarkerConfigLocation().getFile());
        freeMarkerConfiguration.setDefaultEncoding("UTF-8");
        freeMarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        freeMarkerConfiguration.setLogTemplateExceptions(Boolean.FALSE);
        return freeMarkerConfiguration;
    }
}
