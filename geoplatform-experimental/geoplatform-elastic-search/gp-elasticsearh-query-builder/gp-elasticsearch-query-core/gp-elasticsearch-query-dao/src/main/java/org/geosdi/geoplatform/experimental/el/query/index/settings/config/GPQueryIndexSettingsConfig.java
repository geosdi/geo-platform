package org.geosdi.geoplatform.experimental.el.query.index.settings.config;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.index.settings.spi.finder.GPQueryIndexSettingsFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPQueryIndexSettingsConfig {

    @Bean
    public <IS extends GPIndexSettings> IS gpQueryIndexSettings() {
        return new GPQueryIndexSettingsFinder().findQueryIndexSettings();
    }
}
