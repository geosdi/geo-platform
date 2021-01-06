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
package org.geosdi.geoplatform.support.xmpp;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.xmpp.loader.GPXMPPLoader;
import org.geosdi.geoplatform.support.xmpp.spring.connection.manager.XMPPConnectionManager;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jivesoftware.smack.packet.Message;
import org.junit.*;
import org.junit.runner.RunWith;
import org.jxmpp.jid.impl.JidCreate;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPXMPPLoader.class},
        loader = AnnotationConfigContextLoader.class)
public class SendPacketTest {

    @GeoPlatformLog
    static Logger logger;
    //
    static final String GP_XMPP_KEY = "GP_XMPP_FILE_PROP";
    static final String RECEIVER = "JnFAHibv/ZxmDtQGXHMghKpQLxSOiGjYcAfhDNRNUh8=";
    static final String MESSAGE = " is building geo-platform.";
    //
    @Resource(name = "gpXMPPConnectionManager")
    private XMPPConnectionManager gpXMPPConnectionManager;
    @Resource(name = "gpXmppPooledPBEStringEncryptor")
    private PooledPBEStringEncryptor gpXmppPooledPBEStringEncryptor;
    private Message message;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty(GP_XMPP_KEY, "gp-xmpp-test.prop");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty(GP_XMPP_KEY);
    }

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(gpXMPPConnectionManager);
        this.message = new Message(JidCreate.entityBareFrom(this.gpXmppPooledPBEStringEncryptor.
                decrypt(RECEIVER)), Message.Type.chat);
        this.message.setSubject("Example XMPP Message");
        String user = System.getProperty("user.name");
        this.message.setBody((user != null) ? user + MESSAGE : "Someone"
                + MESSAGE);
    }

    @Test
    public void sendPacket() throws Exception {
        this.gpXMPPConnectionManager.login();
        this.gpXMPPConnectionManager.sendStanza(message);

        Thread.sleep(3000);
    }

}
