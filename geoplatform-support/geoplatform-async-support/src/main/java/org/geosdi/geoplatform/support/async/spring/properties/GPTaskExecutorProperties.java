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
package org.geosdi.geoplatform.support.async.spring.properties;

import net.jcip.annotations.Immutable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "gpTaskExecutorProp")
class GPTaskExecutorProperties implements TaskExecutorProperties {

    static final String DEFAULT_THREAD_PREFIX_NAME = "GPAsyncExecutor-";
    static final Integer DEFAULT_CORE_POOL_SIZE = 7;
    static final Integer DEFAULT_MAX_POOL_SIZE = 42;
    static final Integer DEFAULT_QUEUE_CAPACITY = 11;
    static final Boolean DEFAULT_WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = Boolean.FALSE;

    @Value(value = "gpAsyncConfigurator{gp.async.corePoolSize:@null}")
    private Integer corePoolSize;
    @Value(value = "gpAsyncConfigurator{gp.async.maxPoolSize:@null}")
    private Integer maxPoolSize;
    @Value(value = "gpAsyncConfigurator{gp.async.queueCapacity:@null}")
    private Integer queueCapacity;
    @Value(value = "gpAsyncConfigurator{gp.async.waitForTasksToCompleteOnShutdown:@null}")
    private Boolean waitForTasksToCompleteOnShutdown;
    @Value(value = "gpAsyncConfigurator{gp.async.threadNamePrefix:@null}")
    private String threadNamePrefix;

    @Override
    public Integer getCorePoolSize() {
        return this.corePoolSize = ((this.corePoolSize == null)
                ? DEFAULT_CORE_POOL_SIZE : this.corePoolSize);
    }

    @Override
    public Integer getMaxPoolSize() {
        return this.maxPoolSize = ((this.maxPoolSize == null)
                ? DEFAULT_MAX_POOL_SIZE : this.maxPoolSize);
    }

    @Override
    public Integer getQueueCapacity() {
        return this.queueCapacity = ((this.queueCapacity == null)
                ? DEFAULT_QUEUE_CAPACITY : this.queueCapacity);
    }

    /**
     * @return the waitForTasksToCompleteOnShutdown
     */
    public Boolean getWaitForTasksToCompleteOnShutdown() {
        return this.waitForTasksToCompleteOnShutdown
                = ((this.waitForTasksToCompleteOnShutdown == null)
                ? DEFAULT_WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN
                : this.waitForTasksToCompleteOnShutdown);
    }

    @Override
    public String getThreadNamePrefix() {
        return this.threadNamePrefix = ((this.threadNamePrefix == null)
                ? DEFAULT_THREAD_PREFIX_NAME : this.threadNamePrefix);
    }

    /**
     * <p></p>Sets the time limit for which threads may remain idle before
     * being terminated. The Value is in {@link TimeUnit#SECONDS}</p>
     *
     * @return {@link Integer}
     */
    @Override
    public Integer getKeepAliveTime() {
        return 30;
    }

    @Override
    public String toString() {
        return "GPBaseTaskExecutorConfig{ "
                + "corePoolSize = " + getCorePoolSize()
                + ", maxPoolSize = " + getMaxPoolSize()
                + ", queueCapacity = " + getQueueCapacity()
                + ", waitForTasksToCompleteOnShutdown = " + getWaitForTasksToCompleteOnShutdown()
                + ", threadNamePrefix = " + getThreadNamePrefix() + '}';
    }

}
