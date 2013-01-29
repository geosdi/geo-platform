/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client;

import com.calclab.emite.core.client.bosh.XmppBoshConnection;
import com.calclab.emite.core.client.conn.ConnectionSettings;
import com.calclab.emite.core.client.events.MessageEvent;
import com.calclab.emite.core.client.events.MessageHandler;
import com.calclab.emite.core.client.events.PresenceEvent;
import com.calclab.emite.core.client.events.PresenceHandler;
import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.GWT;
import java.util.Collection;
import org.geosdi.geoplatform.gui.client.model.XMPPSessionGinjector;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.puregwt.xmpp.XMPPEventRepository;
import org.geosdi.geoplatform.gui.puregwt.xmpp.XMPPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.xmpp.event.AbstractXMPPEvent;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPXMPPClient {

    public void userXMPPLogin(String username, String password, String hostXmppServer) {
        System.out.println("Executing xmpp code for: " + hostXmppServer);
        final XMPPSessionGinjector ginjector = GWT.create(XMPPSessionGinjector.class);
        final XmppSession sessionXmpp = ginjector.getXmppSession();

        final XmppBoshConnection connection = ginjector.getXmppBoshConnection();
        connection.setSettings(new ConnectionSettings("http-bind", hostXmppServer));

        XmppURI xmppURI = XmppURI.uri(username + '@' + hostXmppServer);
        sessionXmpp.login(xmppURI, password);

        // Usefull to debug the code
        sessionXmpp.addSessionStateChangedHandler(true, new StateChangedHandler() {
            @Override
            public void onStateChanged(StateChangedEvent event) {
                if (event.is(SessionStates.loggedIn)) {
                    System.out.println("We are now online");
                } else if (event.is(SessionStates.disconnected)) {
                    System.out.println("We are now offline");
                } else {
                    System.out.println("Current state: " + event.getState());
                }
            }
        });

        /*
         * We show (log) every incoming presence stanzas
         */
        sessionXmpp.addPresenceReceivedHandler(new PresenceHandler() {
            @Override
            public void onPresence(PresenceEvent event) {
                Presence presence = event.getPresence();
                System.out.println("Presence received from " + presence.getFrom() + ": " + presence.toString());
                GeoPlatformMessage.infoMessage("XMPP Connection", "Presence received from " + presence.getFrom() + ": " + presence.toString());
                Registry.register(GlobalRegistryEnum.EMITE_RESOURCE.getValue(), sessionXmpp.getCurrentUserURI().getResource());
            }
        });

        /*
         * We show every incoming message in the GWT log console
         */
        sessionXmpp.addMessageReceivedHandler(new MessageHandler() {
            @Override
            public void onMessage(MessageEvent event) {
                Message message = event.getMessage();
//                message.getAttribute(null);
                System.out.println("Message received: " + message.getSubject());

                XmppRoster roster = ginjector.getRoster();

                Collection<RosterItem> items = roster.getItems();
                System.out.println("**** ROSTER ALL ITEMS: " + items.size());
                for (RosterItem rosterItem : items) {
//                    System.out.println("*** " + rosterItem.toString() + " [" + rosterItem.getName() + "]");
                    if (rosterItem.isAvailable()) {
                        System.out.println("### AVAILABLE ### " + rosterItem.getJID() + " [" + rosterItem.getName() + "]");
                    }
                }

                if (message.getSubject() != null && message.getBody() != null) {
                    AbstractXMPPEvent xmppEvent = XMPPEventRepository.getXMPPEventForSubject(message.getSubject());
                    if (xmppEvent != null) {
                        xmppEvent.setMessageBody(message.getBody());
                        XMPPHandlerManager.fireEvent(xmppEvent);
                        System.out.println("Message fired");
                    }
                }

                String subject = message.getSubject() == null ? "NEW Message" : message.getSubject();
                GeoPlatformMessage.infoMessage(subject, message.getBody());
            }
        });

        final ChatManager chatManager = ginjector.getChatManager();

//        xmppURI = XmppURI.uri("service@" + hostXmppServer);
        final Chat chat = chatManager.open(xmppURI);
//        chat.addMessageReceivedHandler(new MessageHandler() {
//            @Override
//            public void onMessage(final MessageEvent event) {
//                System.out.println("CHAT Message received: " + event.getMessage().getBody());
//            }
//        });

    }
}
