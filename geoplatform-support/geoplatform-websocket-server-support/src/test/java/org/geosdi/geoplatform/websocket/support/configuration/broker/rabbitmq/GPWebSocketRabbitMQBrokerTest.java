package org.geosdi.geoplatform.websocket.support.configuration.broker.rabbitmq;

import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq.IGPWebSocketRabbiMQBroker;
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
@ActiveProfiles(value = {"prod"})
public class GPWebSocketRabbitMQBrokerTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketRabbitMQBrokerTest.class);
    //
    @Resource(name = "geoPlatformWebSocketRabbitMQBroker")
    private IGPWebSocketRabbiMQBroker geoPlatformWebSocketRabbitMQBroker;

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.geoPlatformWebSocketRabbitMQBroker);
    }

    @Test
    public void geoPlatformWebSocketRabbitMQBrokerConfigTest() throws Exception {
        logger.info("######################GEO_PLATFORM_WEBSOCKET_RABBITMQ_BROKER_CONFIG : {}\n", this.geoPlatformWebSocketRabbitMQBroker);
    }
}
