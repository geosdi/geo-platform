package org.geosdi.geoplatform.experimental.el.threadpool.spi;

import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPElasticSearchThreadPoolConfigBuilder;
import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPThreadPoolConfigBuilder;
import org.geosdi.geoplatform.experimental.el.threadpool.spi.spring.SpringThreadPoolSPIExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SpringTestThreadPoolSPIExecutor extends SpringThreadPoolSPIExecutor {

    /**
     * @return {@link Executor}
     */
    @Override
    public Executor createExecutor() {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withThreadNamePrefix("SpringTestExecutor - ")
                .withQueueCapacity(100).build();
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
