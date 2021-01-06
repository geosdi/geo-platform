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

import java.util.concurrent.ThreadFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDecoratorThreadFactory extends GPDefaultThreadFactory implements Thread.UncaughtExceptionHandler {

    private final ThreadFactory threadFactory;

    /**
     * @param theThreadFactory
     */
    public GPDecoratorThreadFactory(ThreadFactory theThreadFactory) {
       this.threadFactory = theThreadFactory;
    }

    /**
     * @param theThreadNamePrefix
     * @param theIsDaemon
     * @param thePriority
     * @param threadFactory
     */
    public GPDecoratorThreadFactory(String theThreadNamePrefix, Boolean theIsDaemon, Integer thePriority,
            ThreadFactory threadFactory) {
        super(theThreadNamePrefix, theIsDaemon, thePriority);
        this.threadFactory = threadFactory;
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
        Thread thread = this.threadFactory.newThread(r);
        thread.setDaemon(this.isDaemon());
        thread.setName(this.getThreadNamePrefix() + this.nextThreadID());
        thread.setPriority(this.getPriority());
        thread.setUncaughtExceptionHandler(this);
        return thread;
    }

    /**
     * Method invoked when the given thread terminates due to the
     * given uncaught exception.
     * <p>Any exception thrown by this method will be ignored by the
     * Java Virtual Machine.
     *
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace(System.err);
        System.err.flush();
    }
}