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

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

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
                SECONDS.convert(theKeepAlive, theTimeUnit) :
                ((theKeepAlive != null) && (theKeepAlive < 0)) ? SECONDS.convert(60000l, MILLISECONDS) :
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
