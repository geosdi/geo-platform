package org.geosdi.geoplatform.i18n.spring;

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
public class GPWFSMessageSourceTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSMessageSourceTest.class);
    //
    @Resource(name = "wfsMessageSource")
    private MessageSource wfsMessageSource;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull("GPWFSMessageSource must not be null.", this.wfsMessageSource);
    }

    @Test
    public void a_printI18NWFSRequestValidMessageEnTest() {
        logger.info("::::::::::::::::::::::::::::::PRINT_EN_MESSAGE : {}\n", this.wfsMessageSource
                .getMessage("gp_wfs_request.valid", new Object[]{"MESSAGE_TEST"}, Locale.ENGLISH));
    }

    @Test
    public void b_printI18NNWFSRequestValidMessageItTest() {
        logger.info("::::::::::::::::::::::::::::::PRINT_IT_MESSAGE : {}\n", this.wfsMessageSource
                .getMessage("gp_wfs_request.valid", new Object[]{"Messaggio Test"}, Locale.ITALIAN));
    }
}