package org.geosdi.geoplatform.experimental.el.threadpool.spi;

import java.util.concurrent.Executor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolSPIExecutor {

    /**
     * @param <E>
     * @return {@link E}
     */
    <E extends Executor> E createExecutor();

    /**
     * @return {@link String}
     */
    default String getThreadPoolSPIExecutorName() {
        return getClass().getSimpleName();
    }
}
