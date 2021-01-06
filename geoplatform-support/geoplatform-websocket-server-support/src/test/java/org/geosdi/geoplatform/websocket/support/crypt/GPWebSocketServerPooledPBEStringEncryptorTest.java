/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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