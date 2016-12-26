package org.geosdi.geoplatform.support.mail.freemarker;

import freemarker.template.Configuration;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.mail.spring.configuration.freemarker.IGPFreeMarkerConfigLocation;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-FreeMarkerConfig-Test.xml"})
@ActiveProfiles(value = "GPMailFreeMarkerSupport")
public class GPFreeMarkerConfigurationTest {

    @GeoPlatformLog
    static Logger logger;
    //
    static final String GP_MAIL_KEY = "GP_MAIL_FILE_PROP";
    //
    @Resource(name = "gpFreeMarkerConfigLocation")
    private IGPFreeMarkerConfigLocation gpFreeMarkerConfigLocation;
    @Resource(name = "gpFreeMarkerConfiguration")
    private Configuration freeMarkerConfiguration;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty(GP_MAIL_KEY, "gp-mail-test.prop");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty(GP_MAIL_KEY);
    }

    @Before
    public void setUp() {
        Assert.assertNotNull(logger);
        Assert.assertNotNull(gpFreeMarkerConfigLocation);
        Assert.assertNotNull(freeMarkerConfiguration);
    }

    @Test
    public void printFreeMarkerConfigLocationTest() throws Exception {
        logger.info("###########################GP_FREEMARKER_CONFIG_LOCATION : {}\n", this.gpFreeMarkerConfigLocation);
    }
}
