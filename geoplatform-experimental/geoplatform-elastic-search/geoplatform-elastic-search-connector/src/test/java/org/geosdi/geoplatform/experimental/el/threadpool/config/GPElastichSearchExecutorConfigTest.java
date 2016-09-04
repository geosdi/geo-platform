package org.geosdi.geoplatform.experimental.el.threadpool.config;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Executor-Test.xml"})
public class GPElastichSearchExecutorConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "elasticSearchExecutor")
    private Executor elasticSearchExecutor;

    @After
    public void setUp() throws Exception {
        Assert.assertNotNull(this.elasticSearchExecutor);
        Assert.assertTrue(this.elasticSearchExecutor instanceof ThreadPoolTaskExecutor);
    }

    @Test
    public void elasticSearchExecutorTest() {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GP_ELASTIC_SEARCH_EXECUTOR : {}\n", this.elasticSearchExecutor);
    }

    @Test
    public void printThreadPoolKeepAliveTest() {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@KEEP_ALIVE_SECONDS : {}\n",
                ((ThreadPoolTaskExecutor) this.elasticSearchExecutor).getKeepAliveSeconds());
    }
}
