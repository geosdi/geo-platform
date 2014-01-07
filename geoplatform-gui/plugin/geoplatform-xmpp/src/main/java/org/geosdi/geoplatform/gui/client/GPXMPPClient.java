/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client;

import com.calclab.emite.core.XmppURI;
import com.calclab.emite.core.conn.ConnectionSettings;
import com.calclab.emite.core.conn.XmppConnection;
import com.calclab.emite.core.events.MessageReceivedEvent;
import com.calclab.emite.core.events.PresenceReceivedEvent;
import com.calclab.emite.core.events.SessionStatusChangedEvent;
import com.calclab.emite.core.sasl.Credentials;
import com.calclab.emite.core.session.XmppSession;
import com.calclab.emite.core.stanzas.Message;
import com.calclab.emite.core.stanzas.Presence;
import com.calclab.emite.im.chat.PairChat;
import com.calclab.emite.im.chat.PairChatManager;
import com.calclab.emite.im.roster.RosterItem;
import com.calclab.emite.im.roster.XmppRoster;
import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.GWT;
import java.util.Collection;
import java.util.logging.Logger;
import org.geosdi.geoplatform.gui.client.config.XMPPSessionGinjector;
import org.geosdi.geoplatform.gui.client.i18n.XMPPModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.XMPPModuleMessages;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.puregwt.xmpp.XMPPEventRepository;
import org.geosdi.geoplatform.gui.puregwt.xmpp.XMPPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.xmpp.event.AbstractXMPPEvent;

/**
 * TODO : Re - Implement ME
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPXMPPClient implements SessionStatusChangedEvent.Handler,
        MessageReceivedEvent.Handler, PresenceReceivedEvent.Handler {

    static {
        ginjector = GWT.create(XMPPSessionGinjector.class);
    }

    static final Logger logger = Logger.getLogger(GPXMPPClient.class.getName());
    static final XMPPSessionGinjector ginjector;
    //
    private final XmppSession sessionXmpp = ginjector.getXmppSession();

    public void userXMPPLogin(String username, String password,
            String hostXmppServer) {
        logger.info("Executing xmpp code for: " + hostXmppServer);
        final XMPPSessionGinjector ginjector = GWT.create(XMPPSessionGinjector.class);
        final XmppSession sessionXmpp = ginjector.getXmppSession();

        final XmppConnection connection = ginjector.getXmppConnection();
        connection.setSettings(new ConnectionSettings(hostXmppServer));

        XmppURI xmppURI = XmppURI.uri(username + '@' + hostXmppServer);

        sessionXmpp.login(new Credentials(xmppURI, password));

        sessionXmpp.addSessionStatusChangedHandler(this, true);
        sessionXmpp.addPresenceReceivedHandler(this);
        sessionXmpp.addMessageReceivedHandler(this);

        final PairChatManager chatManager = ginjector.getPainChatManager();

        final PairChat chat = chatManager.openChat(xmppURI);
    }

    // Usefull to debug the code
    @Override
    public void onSessionStatusChanged(SessionStatusChangedEvent event) {
        switch (event.getStatus()) {
            case loggedIn:
                logger.info(XMPPModuleConstants.INSTANCE.GPXMPPClient_xmppConnectionInfoText()
                        + "\n " + XMPPModuleConstants.INSTANCE.GPXMPPClient_statusOnlineText());
                logger.info("We are now online");
                break;
            case disconnected:
                logger.info(XMPPModuleConstants.INSTANCE.GPXMPPClient_xmppConnectionInfoText()
                        + "\n " + XMPPModuleConstants.INSTANCE.GPXMPPClient_statusOffLineText());
                logger.info("We are now offline");
                break;
            default:
                logger.info(XMPPModuleConstants.INSTANCE.GPXMPPClient_xmppConnectionInfoText()
                        + "\n " + XMPPModuleMessages.INSTANCE.xmppCurrentStatusMessage(event.getStatus().name()));
                logger.info("Current state: " + event.getStatus());
        }
    }

    /*
     * We show every incoming message in the GWT log console
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        logger.info("Message received: " + message.getSubject());

        XmppRoster roster = ginjector.getRoster();

        Collection<RosterItem> items = roster.getItems();
        logger.info("**** ROSTER ALL ITEMS: " + items.size());
        for (RosterItem rosterItem : items) {
            if (rosterItem.isAvailable()) {
                logger.info("### AVAILABLE ### "
                        + rosterItem.getJID()
                        + " [" + rosterItem.getName() + "]");
            }
        }

        if (message.getSubject() != null && message.getBody() != null) {
            AbstractXMPPEvent xmppEvent = XMPPEventRepository.getXMPPEventForSubject(message.getSubject());
            if (xmppEvent != null) {
                xmppEvent.setMessageBody(message.getBody());
                XMPPHandlerManager.fireEvent(xmppEvent);
                logger.fine("Message fired");
            }
        }

        String subject = message.getSubject() == null
                ? XMPPModuleConstants.INSTANCE.GPXMPPClient_newMessageText()
                : message.getSubject();
        logger.info(subject + "\n " + message.getBody());
    }

    /*
     * We show (log) every incoming presence stanzas
     */
    @Override
    public void onPresenceReceived(PresenceReceivedEvent event) {
        Presence presence = event.getPresence();
        logger.info("Presence received from " + presence.getFrom() + ": " + presence.toString());
        logger.info(XMPPModuleConstants.INSTANCE.GPXMPPClient_xmppConnectionInfoText()
                + "\n "
                + XMPPModuleMessages.INSTANCE.presenceReceivedFromMessage(
                        presence.getXML().getAttribute("from"),
                        presence.toString()));
        Registry.register(GlobalRegistryEnum.EMITE_RESOURCE.getValue(),
                sessionXmpp.getCurrentUserURI().getResource());
    }

}
