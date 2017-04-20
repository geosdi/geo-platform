package org.geosdi.geoplatform.cas.aop;

import org.geosdi.geoplatform.services.IGPPublisherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@ActiveProfiles(value = {"gs_cas"})
public class GeoPlatformCasAopTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoPlatformCasAopTest.class);
    //
    @Resource(name = "casPublisherService")
    private IGPPublisherService casPublisherService;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull("The CasPublisherService must not be null", this.casPublisherService);
    }

    @Test(expected = IllegalStateException.class)
    public void casAopExistsStyleTest() throws Exception {
        logger.info("####################################EXISTS_STYLE_RESULT : {}\n",
                this.casPublisherService.existsStyle("polygon"));
    }

    @Test(expected = IllegalStateException.class)
    public void casAopUpdateStyleTest() throws Exception {
        logger.info("############################UPDATE_STYLE_RESULT : {}\n",
                this.casPublisherService.updateStyle("PIPPO", "pippo", false));
    }
}
