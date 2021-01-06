/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.threadpool.support.builder;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.threadpool.support.factory.GPDecoratorThreadFactory;
import org.geosdi.geoplatform.threadpool.support.factory.GPDefaultThreadFactory;

import java.util.Queue;
import java.util.concurrent.*;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPDefaultThreadPoolConfigBuilder implements GPThreadPoolConfigBuilder {

    protected String threadNamePrefix;
    protected Integer corePoolSize;
    protected Integer maxPoolSize;
    protected Integer queueCapacity;
    protected BlockingQueue<Runnable> queue;
    protected Long keepAlive;
    protected ThreadFactory threadFactory;
    protected Boolean isDaemon;
    protected Integer priority;

    /**
     * @param theThreadNamePrefix
     * @param theCorePoolSize
     * @param theMaxPoolSize
     * @param theQueueCapacity
     * @param theQueue
     * @param theKeepAlive
     * @param theThreadFactory
     * @param theisDaemon
     * @param thePriority
     */
    protected GPDefaultThreadPoolConfigBuilder(String theThreadNamePrefix, Integer theCorePoolSize,
            Integer theMaxPoolSize, Integer theQueueCapacity, BlockingQueue<Runnable> theQueue, Long theKeepAlive,
            ThreadFactory theThreadFactory, Boolean theisDaemon, Integer thePriority) {
        this.threadNamePrefix = theThreadNamePrefix;
        this.corePoolSize = theCorePoolSize;
        this.maxPoolSize = theMaxPoolSize;
        this.queueCapacity = theQueueCapacity;
        this.queue = theQueue;
        this.keepAlive = theKeepAlive;
        this.threadFactory = theThreadFactory;
        this.isDaemon = theisDaemon;
        this.priority = thePriority;
    }

    /**
     * @return {@link String}
     */
    protected static String defaultThreadNamePrefix() {
        return "GPTaskExecutorSupport - ";
    }

    /**
     * @return {@link Integer}
     */
    protected static Integer defaultCorePoolSize() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * @return {@link Integer}
     */
    protected static Integer defaultMaxPoolSize() {
        return (defaultCorePoolSize() * 50);
    }

    /**
     * @return {@link Integer}
     */
    protected static Integer defaultQueueCapacity() {
        return 4167;
    }

    /**
     * @return {@link GPThreadPoolConfigBuilder}
     */
    protected static BlockingQueue<Runnable> defaultQueue() {
        return new SynchronousQueue<Runnable>();
    }

    /**
     * @return {@link Long}
     */
    protected static Long defaultKeepAlive() {
        return TimeUnit.SECONDS.convert(30000l, TimeUnit.MILLISECONDS);
    }

    /**
     * @return {@link ThreadFactory}
     */
    protected static ThreadFactory defaultThreadFactory() {
        return new GPDefaultThreadFactory(defaultThreadNamePrefix(), defaultIsDaemon(), defaultPriority());
    }

    /**
     * @return {@link Boolean}
     */
    protected static Boolean defaultIsDaemon() {
        return FALSE;
    }

    /**
     * @return {@link Integer}
     */
    protected static Integer defaultPriority() {
        return Thread.NORM_PRIORITY;
    }

    /**
     * @param queueCapacity
     * @return {@link BlockingDeque<Runnable>}
     */
    protected static BlockingDeque<Runnable> defaultQueueWithCapacity(Integer queueCapacity) {
        return new LinkedBlockingDeque<>(((queueCapacity != null) && (queueCapacity > 0)) ? queueCapacity : defaultQueueCapacity());
    }

    /**
     * @return {@link GPThreadPoolConfigBuilder}
     */
    protected <ConfigBuilder extends GPThreadPoolConfigBuilder> ConfigBuilder self() {
        return (ConfigBuilder) this;
    }

    /**
     * @return {@link GPThreadPoolConfig}
     */
    @Override
    public GPThreadPoolConfig build() {
        return new GPDefaultThreadPoolConfig(this.threadNamePrefix, this.corePoolSize, this.maxPoolSize, this.queueCapacity,
                (this.queueCapacity != null) && (!(this.queueCapacity.equals(defaultQueueCapacity())) && (this.queueCapacity > 0)) ?
                        defaultQueueWithCapacity(this.queueCapacity) : defaultQueue(), this.keepAlive,
                new GPDecoratorThreadFactory(this.threadNamePrefix, this.isDaemon, this.priority, this.threadFactory),
                this.isDaemon, this.priority);
    }

    /**
     *
     */
    @Immutable
    protected static class GPDefaultThreadPoolConfig implements GPThreadPoolConfigBuilder.GPThreadPoolConfig {

        private final String threadNamePrefix;
        private final Integer corePoolSize;
        private final Integer maxPoolSize;
        private final Integer queueCapacity;
        private final BlockingQueue<Runnable> queue;
        private final Long keepAlive;
        private final ThreadFactory threadFactory;
        private final Boolean isDaemon;
        private final Integer priority;

        public GPDefaultThreadPoolConfig(String theThreadNamePrefix, Integer theCorePoolSize,
                Integer theMaxPoolSize, Integer theQueueCapacity, BlockingQueue<Runnable> theQueue, Long theKeepAlive,
                ThreadFactory theThreadFactory, Boolean theIsDaemon, Integer thePriority) {
            this.threadNamePrefix = theThreadNamePrefix;
            this.corePoolSize = theCorePoolSize;
            this.maxPoolSize = theMaxPoolSize;
            this.queueCapacity = theQueueCapacity;
            this.queue = theQueue;
            this.keepAlive = theKeepAlive;
            this.threadFactory = theThreadFactory;
            this.isDaemon = theIsDaemon;
            this.priority = thePriority;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getThreadNamePrefix() {
            return this.threadNamePrefix;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getCorePoolSize() {
            return this.corePoolSize;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getMaxPoolSize() {
            return this.maxPoolSize;
        }

        /**
         * @return {@link Queue<Runnable>}
         */
        @Override
        public BlockingQueue<Runnable> getQueue() {
            return this.queue;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getQueueCapacity() {
            return this.queueCapacity;
        }

        /**
         * <p>Returns Keep Alive in {@link TimeUnit#SECONDS}</p>
         *
         * @return {@link Long}
         */
        @Override
        public Long getKeepAlive() {
            return this.keepAlive;
        }

        /**
         * @return {@link ThreadFactory}
         */
        @Override
        public ThreadFactory getThreadFactory() {
            return this.threadFactory;
        }

        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean isDaemon() {
            return this.isDaemon;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getPriority() {
            return this.priority;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    "  threadNamePrefix = '" + threadNamePrefix + '\'' +
                    ", corePoolSize = " + corePoolSize +
                    ", maxPoolSize = " + maxPoolSize +
                    ", queueCapacity = " + queueCapacity +
                    ", queue = " + queue +
                    ", keepAlive = " + keepAlive +
                    ", threadFactory = " + threadFactory +
                    ", isDaemon = " + isDaemon +
                    ", priority = " + priority +
                    '}';
        }
    }
}
