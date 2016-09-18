package org.geosdi.geoplatform.experimental.el.query.mediator.config;

import org.geosdi.geoplatform.experimental.el.query.mediator.GPBaseElasticSearchQueryMediator;
import org.geosdi.geoplatform.experimental.el.query.mediator.GPElasticSearchQueryMediator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchQueryMediatorConfig {

    @Bean
    public GPElasticSearchQueryMediator elasticSearchQueryMediator() {
        return new GPBaseElasticSearchQueryMediator();
    }
}
