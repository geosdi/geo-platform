package org.geosdi.geoplatform.experimental.el.threadpool.spi.finder;

import org.geosdi.geoplatform.threadpool.support.spi.finder.GPThreadPoolExecutorFinderSupport;

import java.util.concurrent.ExecutorService;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadPoolExecutorFinder extends GPThreadPoolExecutorFinderSupport<ExecutorService> {

    /**
     * @return {@link ExecutorService}
     */
    ExecutorService findExecutor();
}
