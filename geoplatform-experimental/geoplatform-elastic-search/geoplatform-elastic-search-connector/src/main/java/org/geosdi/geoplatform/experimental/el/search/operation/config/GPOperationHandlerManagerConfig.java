package org.geosdi.geoplatform.experimental.el.search.operation.config;

import org.geosdi.geoplatform.experimental.el.search.operation.responsibility.IGPOperationHandlerManager;
import org.geosdi.geoplatform.experimental.el.search.strategy.GPStrategyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Configuration
class GPOperationHandlerManagerConfig {

    private final GPStrategyRepository strategyRepository;

    /**
     * @param strategyRepository
     */
    public GPOperationHandlerManagerConfig(GPStrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    @Bean
    public IGPOperationHandlerManager operationHandlerManager() {
        return new IGPOperationHandlerManager.GPOperationHandlerManager(this.strategyRepository);
    }
}