package org.geosdi.geoplatform.experimental.openam.support.config.cookie;

import org.geosdi.geoplatform.experimental.openam.api.connector.GPOpenAMConnector;
import org.geosdi.geoplatform.experimental.openam.api.connector.cookie.IOpenAMCookieInfo;
import org.geosdi.geoplatform.experimental.openam.support.connector.settings.cookie.OpenAMCookieInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class OpenAMCookieConfig {

    @Bean
    @Required
    public static IOpenAMCookieInfo openAMCookieInfo(@Qualifier(value = "openAMConnector") GPOpenAMConnector openAMConnector)
            throws Exception {
        IOpenAMCookieInfo openAMCookieInfo = new OpenAMCookieInfo(openAMConnector.serverInfo().getCookieName());
        openAMConnector.registerOpenAMCookieInfo(openAMCookieInfo);
        return openAMCookieInfo;
    }
}
