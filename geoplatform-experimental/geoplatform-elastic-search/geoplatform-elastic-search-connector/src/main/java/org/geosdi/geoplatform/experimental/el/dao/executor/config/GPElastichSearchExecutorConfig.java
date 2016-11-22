package org.geosdi.geoplatform.experimental.el.dao.executor.config;

import org.geosdi.geoplatform.experimental.el.threadpool.spi.finder.GPElasticSearchThreadPoolExecutorFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElastichSearchExecutorConfig {

    @Bean
    public static Executor elasticSearchExecutor() {
        return new GPElasticSearchThreadPoolExecutorFinder().findExecutor();
    }
}
