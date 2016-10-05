package org.geosdi.geoplatform.experimental.el.threadpool.finder;

import org.geosdi.geoplatform.experimental.el.threadpool.spi.finder.GPElasticSearchThreadPoolExecutorFinder;
import org.geosdi.geoplatform.experimental.el.threadpool.spi.finder.GPThreadPoolExecutorFinder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPThreadPoolExecutorFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPThreadPoolExecutorFinderTest.class);
    //
    private final GPThreadPoolExecutorFinder threadPoolExecutorFinder = new GPElasticSearchThreadPoolExecutorFinder();

    @Test
    public void threadPoolExecutorFindTest() throws Exception {
        ExecutorService executor = this.threadPoolExecutorFinder.findExecutor();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GPThreadPoolExecutorFinderTest.findExecutor : {}\n",
                executor);
    }
}
