package org.geosdi.geoplatform.experimental.el.query.index;

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

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Index-Settings-Test.xml"})
public class GPQueryIndexSettingsConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "gpQueryIndexSettings")
    private GPBaseIndexCreator.GPIndexSettings indexSettings;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.indexSettings);
    }

    @Test
    public void gpElasticSearchIndexSettingsTest() {
        Assert.assertTrue(this.indexSettings.getIndexName().equals("GP_ELASTICSEARCH_QUERY_INDEX_NAME_TEST"));
        Assert.assertTrue(this.indexSettings.getIndexType().equals("GP_ELASTICSEARCH_QUERY_INDEX_TYPE_TEST"));
        logger.info("###############################GP_ELASTICSEARCH_QUERY_INDEX_TYPE_TEST : {}\n",
                this.indexSettings);
    }
}
