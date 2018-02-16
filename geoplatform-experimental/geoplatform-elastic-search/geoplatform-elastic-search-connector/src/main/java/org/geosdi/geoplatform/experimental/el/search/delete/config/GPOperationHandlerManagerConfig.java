package org.geosdi.geoplatform.experimental.el.search.delete.config;

import org.geosdi.geoplatform.experimental.el.search.delete.responsibility.IGPOperationHandlerManager;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPStrategyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Configuration
class GPOperationHandlerManagerConfig {

    private final IGPStrategyRepository strategyRepository;

    public GPOperationHandlerManagerConfig(IGPStrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    @Bean
    public IGPOperationHandlerManager gpOperationHandlerManager() {
        return new IGPOperationHandlerManager.GPOperationHandlerManager(this.strategyRepository);
    }
}
