package org.geosdi.geoplatform.threadpool.support.builder.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPThreadPoolTaskExecutorBuilder extends GPThreadPoolExecutorBuilder.GPBaseThreadPoolExecutorBuilder<ThreadPoolTaskExecutor, GPThreadPoolTaskExecutorBuilder> {

    protected GPThreadPoolTaskExecutorBuilder() {
    }

    /**
     * @return {@link GPThreadPoolTaskExecutorBuilder}
     */
    public static GPThreadPoolTaskExecutorBuilder builder() {
        return new GPThreadPoolTaskExecutorBuilder();
    }

    /**
     * @return {@link ThreadPoolTaskExecutor}
     * @throws Exception
     */
    @Override
    public ThreadPoolTaskExecutor build() throws Exception {
        super.checkThreadPoolConfig();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        threadPoolTaskExecutor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolConfig.getKeepAlive().intValue());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(TRUE);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new CallerRunsPolicy());
        threadPoolTaskExecutor.setThreadFactory(threadPoolConfig.getThreadFactory());
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolConfig.getThreadNamePrefix());
        return threadPoolTaskExecutor;
    }
}