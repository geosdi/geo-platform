package org.geosdi.geoplatform.experimental.el.threadpool.spi;

import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPElasticSearchThreadPoolConfigBuilder;
import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPThreadPoolConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BaseThreadPoolSPIExecutor implements GPThreadPoolSPIExecutor {

    private static final Logger logger = LoggerFactory.getLogger(BaseThreadPoolSPIExecutor.class);

    /**
     * @return {@link E}
     * @throws Exception
     */
    @Override
    public <E extends Executor> E createExecutor()  {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().build();
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{} is building Executor with Config : {}\n",
                this.getThreadPoolSPIExecutorName(), threadPoolConfig);
        return (E) new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaxPoolSize(),
                threadPoolConfig.getKeepAlive(), TimeUnit.MINUTES, threadPoolConfig.getQueue(),
                threadPoolConfig.getThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
