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
package org.geosdi.geoplatform.support.xmpp.spring.connection.manager;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.xmpp.configuration.auth.XMPPAuth;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.parts.Resourcepart;
import org.slf4j.Logger;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPXMPPConnectionManager implements XMPPConnectionManager {

    @GeoPlatformLog
    static Logger logger;
    //
    private final XMPPTCPConnectionConfiguration connectionConfig;
    private final XMPPAuth gpSpringXMPPAuth;
    private volatile XMPPTCPConnection connection;

    /**
     * @param theConnectionConfig
     * @param theGPSpringXMPPAuth
     */
    public GPXMPPConnectionManager(XMPPTCPConnectionConfiguration theConnectionConfig, XMPPAuth theGPSpringXMPPAuth) {
        checkArgument(theConnectionConfig != null, "The Parameter connectionConfig must not be null.");
        checkArgument(theGPSpringXMPPAuth != null, "The Parameter gpSpringXMPPAuth must not be null.");
        this.connectionConfig = theConnectionConfig;
        this.gpSpringXMPPAuth = theGPSpringXMPPAuth;
    }

    @Override
    public void disconnect() throws SmackException.NotConnectedException {
        this.connection.disconnect();
    }

    @Override
    public void login() throws Exception {
        this.connection.login(this.gpSpringXMPPAuth.getXMPPUserName(),
                this.gpSpringXMPPAuth.getXMPPPassword());
    }

    @Override
    public void login(String resource) throws Exception {
        this.connection.login(this.gpSpringXMPPAuth.getXMPPUserName(),
                this.gpSpringXMPPAuth.getXMPPPassword(), Resourcepart.from(resource));
    }

    @Override
    public void sendStanza(Stanza stanza) throws Exception {
        this.connection.sendStanza(stanza);
    }

    @Override
    public XMPPConnection getConnection() {
        return this.connection;
    }

    @Override
    public ChatManager createChatManager() {
        return ChatManager.getInstanceFor(connection);
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Destroy {}\n\n",
                getClass().getSimpleName());
    }

    @Override
    public final void initXMPPConnectionManager() throws Exception {
        if (this.connection == null) {
            this.connection = new XMPPTCPConnection(connectionConfig);
            this.connection.connect();
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {} Initialization.\n\n", getClass().getSimpleName());
        }
    }
}