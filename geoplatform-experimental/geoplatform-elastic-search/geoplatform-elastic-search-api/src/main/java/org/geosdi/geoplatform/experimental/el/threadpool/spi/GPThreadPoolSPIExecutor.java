package org.geosdi.geoplatform.experimental.el.threadpool.spi;

import org.geosdi.geoplatform.threadpool.support.spi.GPThreadPoolSPIExecutorSupport;

import java.util.concurrent.ExecutorService;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolSPIExecutor extends GPThreadPoolSPIExecutorSupport<ExecutorService> {

    /**
     * @return {@link String}
     */
    default String getThreadPoolSPIExecutorName() {
        return getClass().getSimpleName();
    }
}
