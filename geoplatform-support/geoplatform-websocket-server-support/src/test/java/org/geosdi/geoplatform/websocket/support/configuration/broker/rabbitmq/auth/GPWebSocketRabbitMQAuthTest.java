package org.geosdi.geoplatform.websocket.support.configuration.broker.rabbitmq.auth;

import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq.auth.IGPWebSocketRabbitMQAuth;
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
@ContextConfiguration(locations = {"classpath:applicationContext-RabbitMQAuth-Test.xml"})
@ActiveProfiles(value = {"prod"})
public class GPWebSocketRabbitMQAuthTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketRabbitMQAuthTest.class);
    //
    @Resource(name = "geoPlatformWebSocketRabbitMQAuth")
    private IGPWebSocketRabbitMQAuth geoPlatformWebSocketRabbitMQAuth;

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.geoPlatformWebSocketRabbitMQAuth);
    }

    @Test
    public void wideWebSocketRabbitMQAuthConfigTest() throws Exception {
        logger.info("##############################GEO_PLATFORM_WEBSOCKET_RABBITMQ_AUTH_CONFIG : {}\n", this.geoPlatformWebSocketRabbitMQAuth);
    }
}
