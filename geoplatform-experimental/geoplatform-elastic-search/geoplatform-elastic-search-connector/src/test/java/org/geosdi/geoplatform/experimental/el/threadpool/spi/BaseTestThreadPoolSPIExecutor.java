package org.geosdi.geoplatform.experimental.el.threadpool.spi;

import org.geosdi.geoplatform.threadpool.support.builder.GPThreadPoolConfigBuilder;
import org.geosdi.geoplatform.threadpool.support.builder.GPThreadPoolSupportConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BaseTestThreadPoolSPIExecutor implements GPThreadPoolSPIExecutor {

    private static final Logger logger = LoggerFactory.getLogger(BaseTestThreadPoolSPIExecutor.class);

    /**
     * @return {@link Executor}
     */
    @Override
    public ExecutorService createExecutor() {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withThreadNamePrefix("SpringTestExecutor - ")
                .withQueueCapacity(100).build();
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{} is building Executor with Config : {}\n",
                this.getThreadPoolSPIExecutorName(), threadPoolConfig);
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaxPoolSize(),
                threadPoolConfig.getKeepAlive(), TimeUnit.SECONDS, threadPoolConfig.getQueue(),
                threadPoolConfig.getThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
