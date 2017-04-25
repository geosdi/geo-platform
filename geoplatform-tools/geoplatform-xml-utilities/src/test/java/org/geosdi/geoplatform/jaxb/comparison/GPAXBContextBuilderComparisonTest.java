package org.geosdi.geoplatform.jaxb.comparison;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

import static org.geosdi.geoplatform.jaxb.comparison.task.GPJAXBContextBuilderTaskType.POOLED;
import static org.geosdi.geoplatform.jaxb.comparison.task.GPJAXBContextBuilderTaskType.SIMPLE;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPAXBContextBuilderComparisonTest extends AbstractAXBContextBuilderComparisonTest {

    @Test
    public void a_gpJAXBContextBuilderSimpleTest() throws Exception {
        logger.info("GPJAXBContextBuilderSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(SIMPLE)));
    }

    @Test
    public void b_gpJAXBContextBuilderPooledTest() throws Exception {
        logger.info("GPJAXBContextBuilderPooledTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(POOLED)));
    }
}
