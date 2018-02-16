package org.geosdi.geoplatform.experimental.el.search.strategy.config;

import org.geosdi.geoplatform.experimental.el.search.strategy.GPStrategyOperationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Configuration
public class GPStrategyOperationRepositoryConfig {

    @Bean
    public GPStrategyOperationRepository gpStrategyOperationRepository() {
        return new GPStrategyOperationRepository();
    }

}
