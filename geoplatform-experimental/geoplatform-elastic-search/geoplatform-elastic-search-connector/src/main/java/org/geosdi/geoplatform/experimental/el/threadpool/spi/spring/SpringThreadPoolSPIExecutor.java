package org.geosdi.geoplatform.experimental.el.threadpool.spi.spring;

import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPElasticSearchThreadPoolConfigBuilder;
import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPThreadPoolConfigBuilder;
import org.geosdi.geoplatform.experimental.el.threadpool.spi.GPThreadPoolSPIExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SpringThreadPoolSPIExecutor implements GPThreadPoolSPIExecutor {

    protected static final Logger logger = LoggerFactory.getLogger(SpringThreadPoolSPIExecutor.class);

    /**
     * @return {@link Executor}
     */
    @Override
    public Executor createExecutor() {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().build();
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{} is building Executor with Config : {}\n",
                this.getThreadPoolSPIExecutorName(), threadPoolConfig);
        return new ThreadPoolTaskExecutor() {

            {
                super.setThreadNamePrefix(threadPoolConfig.getThreadNamePrefix());
                super.setCorePoolSize(threadPoolConfig.getCorePoolSize());
                super.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
                super.setQueueCapacity(threadPoolConfig.getQueueCapacity());
                super.setThreadFactory(threadPoolConfig.getThreadFactory());
                super.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
                super.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            }
        };
    }
}
