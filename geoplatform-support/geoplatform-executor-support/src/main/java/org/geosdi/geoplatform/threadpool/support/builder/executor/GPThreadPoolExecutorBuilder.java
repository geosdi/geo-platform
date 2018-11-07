package org.geosdi.geoplatform.threadpool.support.builder.executor;

import org.geosdi.geoplatform.threadpool.support.builder.GPThreadPoolConfigBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolExecutorBuilder<E extends Executor, Builder extends GPThreadPoolExecutorBuilder> {

    /**
     * @param theThreadPoolConfig
     * @param <ThreadPoolConfig>
     * @return {@link GPThreadPoolExecutorBuilder}
     */
    <ThreadPoolConfig extends GPThreadPoolConfigBuilder.GPThreadPoolConfig> Builder withConfigBuilder(ThreadPoolConfig theThreadPoolConfig);

    /**
     * @return {@link ExecutorService}
     * @throws Exception
     */
    E build() throws Exception;

    abstract class GPBaseThreadPoolExecutorBuilder<E extends Executor, Builder extends GPThreadPoolExecutorBuilder>
            implements GPThreadPoolExecutorBuilder<E, Builder> {

        protected GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig;

        protected GPBaseThreadPoolExecutorBuilder() {
        }

        /**
         * @param theThreadPoolConfig
         * @return {@link GPThreadPoolExecutorBuilder}
         */
        @Override
        public <ThreadPoolConfig extends GPThreadPoolConfigBuilder.GPThreadPoolConfig> Builder withConfigBuilder(ThreadPoolConfig theThreadPoolConfig) {
            this.threadPoolConfig = theThreadPoolConfig;
            return self();
        }

        /**
         * @throws Exception
         */
        protected void checkThreadPoolConfig() throws Exception {
            checkArgument(this.threadPoolConfig != null, "The Parameter threadPoolConfig must not be null.");
        }

        /**
         * @return {@link Builder}
         */
        protected Builder self() {
            return (Builder) this;
        }
    }
}