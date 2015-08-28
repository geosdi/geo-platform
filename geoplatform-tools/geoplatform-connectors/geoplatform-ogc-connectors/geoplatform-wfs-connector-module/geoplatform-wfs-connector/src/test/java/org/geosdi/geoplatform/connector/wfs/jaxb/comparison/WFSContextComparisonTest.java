package org.geosdi.geoplatform.connector.wfs.jaxb.comparison;

import org.geosdi.geoplatform.connector.jaxb.context.WFSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.context.pool.WFSJAXBContextPool;
import org.geosdi.geoplatform.junit.Order;
import org.geosdi.geoplatform.junit.OrderedRunner;
import org.geosdi.geoplatform.xml.wfs.v110.WFSCapabilitiesType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBContext;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(OrderedRunner.class)
public class WFSContextComparisonTest extends AbstractWFSComparisonTest {

    @Test
    @Order(order = 1)
    public void wfsPooledDescribeFeatureTest() throws Exception {
        logger.info("WFSPooledDescribeFeatureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_POOLED)));
    }

    @Test
    @Order(order = 0)
    public void wfsSimpleDescribeFeatureTest() throws Exception {
        logger.info("WFSSimpleDescribeFeatureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_SIMPLE)));
    }


    @Test
    @Order(order = 2)
    public void wfsPooledDescribeFeatureSecureTest() throws Exception {
        logger.info("WFSPooledDescribeFeatureSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_SECURE_POOLED)));
    }

    @Test
    @Order(order = 3)
    public void wfsSimpleDescribeFeatureSecureTest() throws Exception {
        logger.info("WFSSimpleDescribeFeatureSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_SECURE_SIMPLE)));
    }

    @Test
    @Order(order = 4)
    public void wfsPooledGetCapabilitiesTest() throws Exception {
        logger.info("WFSPooledGetCapabilitiesTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_POOLED)));
    }

    @Test
    @Order(order = 5)
    public void wfsSimpleGetCapabilitiesTest() throws Exception {
        logger.info("WFSSimpleGetCapabilitiesTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_SIMPLE)));
    }

    @Test
    @Order(order = 6)
    public void wfsPooledGetCapabilitiesSecureTest() throws Exception {
        logger.info("WFSPooledGetCapabilitiesSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_SECURE_POOLED)));
    }

    @Test
    @Order(order = 7)
    public void wfsSimpleGetCapabilitiesSecureTest() throws Exception {
        logger.info("WFSSimpleGetCapabilitiesSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext
                                .newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_SECURE_SIMPLE)));
    }
}
