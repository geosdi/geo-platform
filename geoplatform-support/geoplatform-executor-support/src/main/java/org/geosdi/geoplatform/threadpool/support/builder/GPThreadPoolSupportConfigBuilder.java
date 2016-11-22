package org.geosdi.geoplatform.threadpool.support.builder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPThreadPoolSupportConfigBuilder extends GPDefaultThreadPoolConfigBuilder {

    private GPThreadPoolSupportConfigBuilder() {
        super(defaultThreadNamePrefix(), defaultCorePoolSize(), defaultMaxPoolSize(), defaultQueueCapacity(),
                defaultQueue(), defaultKeepAlive(), defaultThreadFactory(), defaultIsDaemon(), defaultPriority());
    }

    /**
     * @return {@link GPThreadPoolSupportConfigBuilder}
     */
    public static <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder threadPoolConfigBuilder() {
        return (ConfigBuilder) new GPThreadPoolSupportConfigBuilder();
    }

    /**
     * @param theThreadNamePrefix
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withThreadNamePrefix(String theThreadNamePrefix) {
        this.threadNamePrefix = ((theThreadNamePrefix != null) && !(theThreadNamePrefix.isEmpty())) ? theThreadNamePrefix : defaultThreadNamePrefix();
        return self();
    }

    /**
     * @param theCorePoolSize
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withCorePoolSize(Integer theCorePoolSize) {
        this.corePoolSize = ((theCorePoolSize != null) && (theCorePoolSize > 0)) ? theCorePoolSize : defaultCorePoolSize();
        return self();
    }

    /**
     * @param theMaxPoolSize
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withMaxPoolSize(Integer theMaxPoolSize) {
        this.maxPoolSize = ((theMaxPoolSize != null) && (theMaxPoolSize > 0)) ? theMaxPoolSize : defaultMaxPoolSize();
        return self();
    }


    /**
     * @param theQueueCapacity
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withQueueCapacity(Integer theQueueCapacity) {
        this.queueCapacity = theQueueCapacity;
        return self();
    }

    /**
     * @param theKeepAlive
     * @param theTimeUnit
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withKeepAlive(Long theKeepAlive,
            TimeUnit theTimeUnit) {
        this.keepAlive = ((theKeepAlive != null) && (theKeepAlive > 0)) ?
                TimeUnit.SECONDS.convert(theKeepAlive, theTimeUnit) :
                ((theKeepAlive != null) && (theKeepAlive < 0)) ? TimeUnit.SECONDS.convert(Long.MAX_VALUE, TimeUnit.MILLISECONDS) :
                        defaultKeepAlive();
        return self();
    }

    /**
     * @param theThreadFactory
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withThreadFactory(ThreadFactory theThreadFactory) {
        this.threadFactory = (theThreadFactory != null) ? theThreadFactory : defaultThreadFactory();
        return self();
    }

    /**
     * @param theIsDaemon
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withIsDaemon(Boolean theIsDaemon) {
        this.isDaemon = (theIsDaemon != null) ? theIsDaemon : defaultIsDaemon();
        return self();
    }

    /**
     * @param thePriority
     * @return {@link ConfigBuilder}
     */
    @Override
    public <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder withPriority(Integer thePriority) {
        this.priority = (thePriority != null) ? thePriority : defaultPriority();
        return self();
    }
}
