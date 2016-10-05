package org.geosdi.geoplatform.experimental.el.threadpool.spi.finder;

import org.geosdi.geoplatform.experimental.el.threadpool.spi.BaseThreadPoolSPIExecutor;
import org.geosdi.geoplatform.experimental.el.threadpool.spi.GPThreadPoolSPIExecutor;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutorService;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchThreadPoolExecutorFinder implements GPThreadPoolExecutorFinder {

    /**
     * @return {@link E}
     */
    @Override
    public <E extends ExecutorService> E findExecutor() {
        Iterator<GPThreadPoolSPIExecutor> it = ServiceLoader.load(GPThreadPoolSPIExecutor.class).iterator();
        GPThreadPoolSPIExecutor threadPoolSPIExecutor = (it.hasNext() ? it.next() : new BaseThreadPoolSPIExecutor());
        return (E) threadPoolSPIExecutor.createExecutor();
    }
}
