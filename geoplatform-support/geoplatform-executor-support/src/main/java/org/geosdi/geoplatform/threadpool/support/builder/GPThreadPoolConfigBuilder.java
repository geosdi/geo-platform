package org.geosdi.geoplatform.threadpool.support.builder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolConfigBuilder {

    /**
     * @param theThreadNamePrefix
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withThreadNamePrefix(String theThreadNamePrefix);

    /**
     * @param theCorePoolSize
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withCorePoolSize(Integer theCorePoolSize);

    /**
     * @param theMaxPoolSize
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withMaxPoolSize(Integer theMaxPoolSize);

    /**
     * @param theQueueCapacity
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withQueueCapacity(Integer theQueueCapacity);

    /**
     * @param theKeepAlive
     * @param theTimeUnit
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withKeepAlive(Long theKeepAlive, TimeUnit theTimeUnit);

    /**
     * @param theThreadFactory
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withThreadFactory(ThreadFactory theThreadFactory);

    /**
     * @param theIsDaemon
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withIsDaemon(Boolean theIsDaemon);

    /**
     * @param thePriority
     * @return {@link ConfigBuilder}
     */
    <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withPriority(Integer thePriority);

    /**
     * @param <ThreadConfig>
     * @return {@link ThreadConfig}
     */
    <ThreadConfig extends GPThreadPoolConfig> ThreadConfig build();

    /**
     *
     */
    interface GPThreadPoolConfig {

        /**
         * @return {@link String}
         */
        String getThreadNamePrefix();

        /**
         * @return {@link Integer}
         */
        Integer getCorePoolSize();

        /**
         * @return {@link Integer}
         */
        Integer getMaxPoolSize();

        /**
         * @return {@link BlockingQueue<Runnable>}
         */
        BlockingQueue<Runnable> getQueue();

        /**
         * @return {@link Integer}
         */
        Integer getQueueCapacity();

        /**
         * <p>Returns Keep Alive in {@link TimeUnit#SECONDS}</p>
         *
         * @return {@link Long}
         */
        Long getKeepAlive();

        /**
         * @return {@link ThreadFactory}
         */
        ThreadFactory getThreadFactory();

        /**
         * @return {@link Boolean}
         */
        Boolean isDaemon();

        /**
         * @return {@link Integer}
         */
        Integer getPriority();
    }
}
