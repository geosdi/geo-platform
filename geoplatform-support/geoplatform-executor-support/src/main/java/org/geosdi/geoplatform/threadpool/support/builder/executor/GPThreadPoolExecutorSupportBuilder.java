package org.geosdi.geoplatform.threadpool.support.builder.executor;

import org.geosdi.geoplatform.threadpool.support.builder.executor.GPThreadPoolExecutorBuilder.GPBaseThreadPoolExecutorBuilder;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPThreadPoolExecutorSupportBuilder extends GPBaseThreadPoolExecutorBuilder<ThreadPoolExecutor, GPThreadPoolExecutorSupportBuilder> {

    private GPThreadPoolExecutorSupportBuilder() {
    }

    /**
     * @return {@link GPThreadPoolExecutorSupportBuilder}
     */
    public static GPThreadPoolExecutorSupportBuilder builder() {
        return new GPThreadPoolExecutorSupportBuilder();
    }

    /**
     * @return {@link ThreadPoolExecutor}
     * @throws Exception
     */
    @Override
    public ThreadPoolExecutor build() throws Exception {
        super.checkThreadPoolConfig();
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaxPoolSize(),
                threadPoolConfig.getKeepAlive(), TimeUnit.SECONDS, threadPoolConfig.getQueue(), threadPoolConfig.getThreadFactory());
    }
}
