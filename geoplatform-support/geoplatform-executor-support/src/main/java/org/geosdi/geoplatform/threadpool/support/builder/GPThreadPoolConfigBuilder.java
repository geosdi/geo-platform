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
