package org.geosdi.geoplatform.websocket.support.configuration.broker.mem;

import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.GPWebSocketStompBroker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-WebSocketBroker-Test.xml"})
@ActiveProfiles(value = {"dev"})
public class GPWebSocketMemBrokerTest {

    private static Logger logger = LoggerFactory.getLogger(GPWebSocketMemBrokerTest.class);
    //
    @Resource(name = "geoPlatformWebSocketMemBroker")
    private GPWebSocketStompBroker geoPlatformWebSocketMemBroker;

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.geoPlatformWebSocketMemBroker);
    }

    @Test
    public void geoPlatformWebSocketMemBrokerConfigTest() throws Exception {
        logger.info("###########################GEO_PLATFORM_WEBSOCKET_MEM_BROKER_CONFIG : {}\n", this.geoPlatformWebSocketMemBroker);
    }
}