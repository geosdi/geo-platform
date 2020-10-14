package org.geosdi.geoplatform.websocket.support.crypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-WebSocketServer-Crypt-Test.xml"})
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWebSocketServerPooledPBEStringEncryptorTest {

    private static Logger logger = LoggerFactory.getLogger(GPWebSocketServerPooledPBEStringEncryptorTest.class);
    //
    @Resource(name = "geoPlatformWebSocketServerPooledPBEStringEncryptor")
    private PooledPBEStringEncryptor geoPlatformWebSocketServerPooledPBEStringEncryptor;

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.geoPlatformWebSocketServerPooledPBEStringEncryptor);
    }

    @Test
    public void a_encryptedRabbitMQRelayHostNameTest() throws Exception {
        String rabbitMQRelayHost = "150.145.141.202";
        String encryptedRabbitMQRelayHost = this.geoPlatformWebSocketServerPooledPBEStringEncryptor.encrypt(rabbitMQRelayHost);
        assertTrue("GeoPlatform RabbitMQ Relay Host / Encrypted Host doesn't match",
                this.geoPlatformWebSocketServerPooledPBEStringEncryptor.decrypt(encryptedRabbitMQRelayHost).equals(rabbitMQRelayHost));
        logger.debug("@@@@@@@@@@@@@@GEO_PLATFORM_WEBSOCKET_SERVER_RELAY_HOST_RABBITMQ : {}\n\n", encryptedRabbitMQRelayHost);
    }

    @Test
    public void b_encryptedRabbitMQRelayPortTest() throws Exception {
        String rabbitMQRelayPort = "5672";
        String encryptedRabbitMQRelayPort = this.geoPlatformWebSocketServerPooledPBEStringEncryptor.encrypt(rabbitMQRelayPort);
        assertTrue("GeoPlatform RabbitMQ Relay Port / Encrypted Port doesn't match",
                this.geoPlatformWebSocketServerPooledPBEStringEncryptor.decrypt(encryptedRabbitMQRelayPort).equals(rabbitMQRelayPort));
        logger.debug("@@@@@@@@@@@@@@GEO_PLATFORM_WEBSOCKET_SERVER_RELAY_PORT_RABBITMQ : {}\n\n", encryptedRabbitMQRelayPort);
    }

    @Test
    public void c_encryptedRabbitMQUserNameTest() throws Exception {
        String rabbitMQUserName = "admin";
        String encryptedRabbitMQUserName = this.geoPlatformWebSocketServerPooledPBEStringEncryptor.encrypt(rabbitMQUserName);
        assertTrue("GeoPlatform RabbitMQ UserName / Encrypted User doesn't match",
                this.geoPlatformWebSocketServerPooledPBEStringEncryptor.decrypt(encryptedRabbitMQUserName).equals(rabbitMQUserName));
        logger.debug("@@@@@@@@@@@@@@GEO_PLATFORM_RABBITMQ_ENCRYPTED_USERNAME : {}\n\n", encryptedRabbitMQUserName);
    }

    @Test
    public void d_encryptedRabbitMQPasswordTest() throws Exception {
        String rabbitMQPassword = "$$geosdi!!";
        String encryptedRabbitMQPassword = this.geoPlatformWebSocketServerPooledPBEStringEncryptor.encrypt(rabbitMQPassword);
        assertTrue("GeoPlatform RabbitMQ Password / Encrypted Password doesn't match",
                this.geoPlatformWebSocketServerPooledPBEStringEncryptor.decrypt(encryptedRabbitMQPassword).equals(rabbitMQPassword));
        logger.debug("@@@@@@@@@@@@@@GEO_PLATFORM_RABBITMQ_ENCRYPTED_PASSWORD : {}\n\n", encryptedRabbitMQPassword);
    }
}