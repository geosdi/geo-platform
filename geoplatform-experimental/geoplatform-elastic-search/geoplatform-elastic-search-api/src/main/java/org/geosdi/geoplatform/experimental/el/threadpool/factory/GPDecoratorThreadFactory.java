package org.geosdi.geoplatform.experimental.el.threadpool.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDecoratorThreadFactory extends GPDefaultThreadFactory {

    private final ThreadFactory threadFactory;

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
        return thread;
    }
}
