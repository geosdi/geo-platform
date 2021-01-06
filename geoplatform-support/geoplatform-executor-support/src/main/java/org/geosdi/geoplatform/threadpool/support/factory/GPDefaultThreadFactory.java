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
package org.geosdi.geoplatform.threadpool.support.factory;

import javax.annotation.Nullable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.FALSE;
import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.NORM_PRIORITY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDefaultThreadFactory implements GPThreadFactorySupport {

    private final AtomicInteger threadID = new AtomicInteger(0);
    private final String threadNamePrefix;
    private final Boolean isDaemon;
    private final Integer priority;

    public GPDefaultThreadFactory() {
        this(null, null, null);
    }

    /**
     * @param theThreadNamePrefix
     * @param theIsDaemon
     * @param thePriority
     */
    public GPDefaultThreadFactory(@Nullable String theThreadNamePrefix, @Nullable Boolean theIsDaemon, @Nullable Integer thePriority) {
        this.threadNamePrefix = ((theThreadNamePrefix != null) && !(theThreadNamePrefix.trim().isEmpty()))
                ? theThreadNamePrefix : "GPTaskExecutor#";
        this.isDaemon = (theIsDaemon != null) ? theIsDaemon : FALSE;
        this.priority = ((thePriority != null) && ((thePriority <= MAX_PRIORITY)
                && (thePriority >= Thread.MIN_PRIORITY))) ? thePriority : NORM_PRIORITY;
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = Executors.privilegedThreadFactory().newThread(r);
        thread.setName(this.threadNamePrefix + nextThreadID());
        thread.setPriority(this.priority);
        thread.setDaemon(this.isDaemon);
        return thread;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getThreadNamePrefix() {
        return this.threadNamePrefix;
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

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer nextThreadID() {
        return (this.threadID.compareAndSet(1000, 0) ? this.threadID.get() : this.threadID.getAndIncrement());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " threadNamePrefix = '" + threadNamePrefix + '\'' +
                ", isDaemon = " + isDaemon +
                ", priority = " + priority +
                '}';
    }
}