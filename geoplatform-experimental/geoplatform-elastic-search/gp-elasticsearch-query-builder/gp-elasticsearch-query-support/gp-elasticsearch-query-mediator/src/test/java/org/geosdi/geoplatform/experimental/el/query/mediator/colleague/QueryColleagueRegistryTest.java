package org.geosdi.geoplatform.experimental.el.query.mediator.colleague;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Query-Colleague-Test.xml"})
public class QueryColleagueRegistryTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "elasticSearchQueryColleagueRegistry")
    private Map<GPBaseIndexCreator.GPIndexSettings, GPElasticSearchQueryColleague> queryColleagueRegistry;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.queryColleagueRegistry);
    }

    @Test
    public void elasticSearchQueryColleagueRegistry() throws Exception {
        Assert.assertTrue(this.queryColleagueRegistry.size() == 2);
        logger.info("##############################ELASTICSEARCH_QUERY_COLLEAGUE_REGISTRY : {}\n",
                this.queryColleagueRegistry);
    }
}
