package org.geosdi.geoplatform.threadpool.support.spi;

import java.util.concurrent.Executor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolSPIExecutorSupport<E extends Executor> {

    /**
     * @return {@link E}
     */
    E createExecutor();

    /**
     * @return {@link String}
     */
    String getThreadPoolSPIExecutorName();
}
