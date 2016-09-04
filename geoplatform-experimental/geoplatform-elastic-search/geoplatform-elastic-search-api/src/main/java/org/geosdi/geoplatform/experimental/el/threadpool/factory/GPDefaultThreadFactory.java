package org.geosdi.geoplatform.experimental.el.threadpool.factory;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.MAX_PRIORITY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDefaultThreadFactory implements GPElasticSearchThreadFactory {

    private final AtomicInteger threadID = new AtomicInteger(0);
    private final String threadNamePrefix;
    private final Boolean isDaemon;
    private final Integer priority;

    public GPDefaultThreadFactory(String theThreadNamePrefix, Boolean theIsDaemon, Integer thePriority) {
        this.threadNamePrefix = ((theThreadNamePrefix != null) && !(theThreadNamePrefix.isEmpty())) ?
                theThreadNamePrefix : "GPElasticSearchTaskExecutor - ";
        this.isDaemon = (theIsDaemon != null) ? theIsDaemon : Boolean.FALSE;
        this.priority = ((thePriority != null) && ((thePriority <= MAX_PRIORITY)
                && (thePriority >= Thread.MIN_PRIORITY))) ? thePriority : Thread.NORM_PRIORITY;
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
        thread.setName(this.threadNamePrefix + this.threadID.getAndIncrement());
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
        return this.threadID.getAndIncrement();
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
