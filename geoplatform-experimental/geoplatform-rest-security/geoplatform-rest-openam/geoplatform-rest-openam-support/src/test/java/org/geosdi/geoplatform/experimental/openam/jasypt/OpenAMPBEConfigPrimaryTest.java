package org.geosdi.geoplatform.experimental.openam.jasypt;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-OpenAM-PBE-Primary-Test.xml"})
public class OpenAMPBEConfigPrimaryTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Autowired
    private PBEConfig openAMPBEConfig;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.openAMPBEConfig);
    }

    @Test
    public void openAMPBEDefaultConfigTest() {
        logger.info(":::::::::::::::::::::::::PASSWORD USED : {}\n", this.openAMPBEConfig.getPassword());
        logger.info(":::::::::::::::::::::::::POOL_SIZE USED : {}\n", this.openAMPBEConfig.getPoolSize());
        logger.info(":::::::::::::::::::::::::ALGORITHM USED : {}\n", this.openAMPBEConfig.getAlgorithm());
    }
}
