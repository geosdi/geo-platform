package org.geosdi.geoplatform.experimental.el.threadpool.spi;

import java.util.concurrent.ExecutorService;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolSPIExecutor {

    /**
     * @param <E>
     * @return {@link E}
     */
    <E extends ExecutorService> E createExecutor();

    /**
     * @return {@link String}
     */
    default String getThreadPoolSPIExecutorName() {
        return getClass().getSimpleName();
    }
}
