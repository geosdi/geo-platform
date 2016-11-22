package org.geosdi.geoplatform.threadpool.support.spi.finder;

import java.util.concurrent.Executor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolExecutorFinderSupport<E extends Executor> {

    /**
     * @return {@link E}
     */
    E findExecutor();
}
