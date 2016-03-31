package org.geosdi.geoplatform.experimental.openam.support.config.connector;

import org.geosdi.geoplatform.experimental.openam.api.connector.GPOpenAMConnector;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class OpenAMConnectorConfig {

    @Bean
    @Autowired
    public GPOpenAMConnector openAMConnector(@Qualifier(value = "openAMConnectorSettings") GPConnectorSettings openAMConnectorSettings) {
        return new OpenAMConnector(openAMConnectorSettings);
    }
}
