package org.geosdi.geoplatform.jaxb.comparison.task.factory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextBuilderComparisonThreadFactory implements ThreadFactory {

    private final AtomicInteger threadID = new AtomicInteger(0);

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
        thread.setName("GPJAXBContextBuilderComparisonThread - " + threadID.getAndIncrement());
        thread.setDaemon(TRUE);
        return thread;
    }
}
