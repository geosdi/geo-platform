package org.geosdi.geoplatform.wmc.jaxb.v110.comparison;

import org.geosdi.geoplatform.junit.Order;
import org.geosdi.geoplatform.junit.OrderedRunner;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.WMCJAXBContextV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.xml.wmc.v110.ViewContextType;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBContext;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(OrderedRunner.class)
public class WMCComparisonTest extends AbstractWMCComparisonTest {

    @Test
    @Order(order = 1)
    public void wmcPooledContextTest() throws Exception {
        logger.info("WMCPooledContextTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WMCJAXBContextPoolV110(JAXBContext
                                .newInstance(ViewContextType.class)))));
    }

    @Test
    @Order(order = 0)
    public void wmcSimpleContextTest() throws Exception {
        logger.info("WMCSimpleContextTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WMCJAXBContextV110(JAXBContext
                                .newInstance(ViewContextType.class)))));
    }
}
