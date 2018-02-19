package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Configuration
class GPStrategyOperationRepositoryConfig {

    @Bean(initMethod = "registerStrategies")
    public GPStrategyOperationRepository strategyOperationRepository() {
        return new GPStrategyOperationRepository();
    }
}