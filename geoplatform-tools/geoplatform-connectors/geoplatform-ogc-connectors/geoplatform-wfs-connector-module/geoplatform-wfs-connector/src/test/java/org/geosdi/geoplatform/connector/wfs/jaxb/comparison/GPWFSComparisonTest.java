package org.geosdi.geoplatform.connector.wfs.jaxb.comparison;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWFSComparisonTest {

    ThreadFactory WFSComparisonThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("WFSComparisonThread - " + threadID.getAndIncrement());
            thread.setDaemon(TRUE);
            return thread;
        }

    };

    enum WFSTaskType {

        DESCRIBE_FEATURE_SIMPLE,
        DESCRIBE_FEATURE_POOLED,
        DESCRIBE_FEATURE_SECURE_SIMPLE,
        DESCRIBE_FEATURE_SECURE_POOLED,
        GET_CAPABILITIES_SIMPLE,
        GET_CAPABILITIES_POOLED,
        GET_CAPABILITIES_SECURE_SIMPLE,
        GET_CAPABILITIES_SECURE_POOLED;
    }
}
