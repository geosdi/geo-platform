package org.geosdi.geoplatform.wms.i18N.spring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Locale;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-i18N-Test.xml"})
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSMessageSourceTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSMessageSourceTest.class);
    //
    @Resource(name = "wmsMessageSource")
    private MessageSource wmsMessageSource;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull("GPWMSMessageSource must not be null.", this.wmsMessageSource);
    }

    @Test
    public void a_printI18NWMSRequestValidMessageEnTest() {
        logger.info("::::::::::::::::::::::::::::::PRINT_EN_MESSAGE : {}\n", this.wmsMessageSource
                .getMessage("gp_wms_request.valid", new Object[]{"MESSAGE_TEST"}, Locale.ENGLISH));
    }

    @Test
    public void b_printI18NNWMSRequestValidMessageItTest() {
        logger.info("::::::::::::::::::::::::::::::PRINT_IT_MESSAGE : {}\n", this.wmsMessageSource
                .getMessage("gp_wms_request.valid", new Object[]{"Messaggio Test"}, Locale.ITALIAN));
    }
}