package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@ComponentScan(value = {"org.geosdi.geoplatform.experimental.el.rest.spring.jasypt",
        "org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth",
        "org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl",
        "org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base"})
class GPElasticSearchRSBaseConfigTest {
}