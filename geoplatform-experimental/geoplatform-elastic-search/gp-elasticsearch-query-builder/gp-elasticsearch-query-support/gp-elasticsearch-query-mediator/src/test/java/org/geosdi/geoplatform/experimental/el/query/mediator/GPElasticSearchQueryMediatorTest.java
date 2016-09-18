package org.geosdi.geoplatform.experimental.el.query.mediator;

import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Query-Mediator-Test.xml"})
public class GPElasticSearchQueryMediatorTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "elasticSearchQueryMediator")
    private GPElasticSearchQueryMediator queryMediator;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.queryMediator);
    }

    @Test
    public void printAllGPElasticSearchQueryColleagueTest() throws Exception {
        logger.info("###########################QUERY_COLLEAGUES : {}\n",
                this.queryMediator.getAllQueryColleagues());
    }

    @Test
    public void executeQuerySimpleColleagueTest() throws Exception {
        String executionResult = this.queryMediator.executeQueryColleague(new GPBaseIndexSettings("QueryColleagueSimpleIndex",
                "QueryColleagueSimpleType"), "DO IT SIMPLE");
        logger.info("###########################QUERY_COLLEAGUE_SIMPLE executing -------------> : {}\n",
                executionResult);
    }

    @Test
    public void executeQueryBaseColleagueTest() throws Exception {
        String executionResult = this.queryMediator.executeQueryColleague(new GPBaseIndexSettings("QueryColleagueBaseIndex",
                "QueryColleagueBaseType"), "DO IT BASE");
        logger.info("###########################QUERY_COLLEAGUE_BASE executing -------------> : {}\n",
                executionResult);
    }
}
