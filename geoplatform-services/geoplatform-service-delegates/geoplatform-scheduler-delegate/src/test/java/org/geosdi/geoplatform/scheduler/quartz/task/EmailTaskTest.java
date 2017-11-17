package org.geosdi.geoplatform.scheduler.quartz.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml"})
public class EmailTaskTest {

    private static final Logger logger = LoggerFactory.getLogger(EmailTaskTest.class);
    //
    @Resource(name = "emailTask")
    private EmailTask emailTask;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.emailTask);
    }

    @Test
    public void printEmailTaskTest() throws Exception {
        logger.info("#############################EMAIL_TASK : {}\n", this.emailTask);
    }
}
