package org.geosdi.geoplatform.experimental.el.rest.client;

import org.elasticsearch.client.RestHighLevelClient;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.geosdi.geoplatform.experimental.el.rest.api.info.GPElasticSearchRestInfo.of;
import static org.junit.Assert.assertNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-ElasticSearchRestHighLevelClient-Test.xml"})
public class GPElasticSearchRestHighLeveClientConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "elasticSearchRestHighLevelClient")
    private RestHighLevelClient elasticSearchRestHighLevelClient;

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.elasticSearchRestHighLevelClient);
    }

    @Test
    public void printElasticSearchRestHighLevelClientTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GP_ELASTICSEARCG_REST_HIGH_LEVEL_CLIENT : {}\n", this.elasticSearchRestHighLevelClient);
        logger.info("#############################{}\n", of(this.elasticSearchRestHighLevelClient.info(DEFAULT)));
    }
}