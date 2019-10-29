package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@ComponentScan(value = {"org.geosdi.geoplatform.experimental.el.rest.spring.jasypt",
        "org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth"})
class GPElasticSearchRestAuthConfigTest {
}