/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.model.rest;

import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;
import org.geosdi.geoplatform.request.message.MarkMessageReadByDateRequest;
import org.geosdi.geoplatform.response.MessageDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.*;

import static org.geosdi.geoplatform.gui.shared.GPMessageCommandType.NONE;
import static org.geosdi.geoplatform.gui.shared.GPRole.USER;
import static org.geosdi.geoplatform.gui.shared.GPRole.VIEWER;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class RSMessageTest extends BasicRestServiceTest {

    private Long firstRecipientID;
    private Long latterRecipientID;
    private GPAccount firstRecipient;
//    private GPAccount latterRecipient;
    private GPMessage message;

    @Override
    public void setUp() throws Exception {
        // Insert Organization
        this.setUpOrganization();
        // Insert Users
        this.userTest = this.createAndInsertUser(usernameTest, organizationTest, USER);

        firstRecipient = this.createAndInsertUser("first_recipient_RS", organizationTest, USER);
        firstRecipientID = firstRecipient.getId();

        latterRecipientID = this.createAndInsertUser("latter_recipient_RS", organizationTest, VIEWER).getId();
//        latterRecipient = gpWSClient.getUserDetail(latterRecipientID);

        // Create message
        message = new GPMessage();
        message.setRecipient(firstRecipient);
        message.setSender(userTest);
        message.setCreationDate(new Date(System.currentTimeMillis()));
        message.setRead(false);
        message.setSubject("Foo subject REST");
        message.setText("Foo message REST.");
        message.addCommand(NONE);
    }

    @Test
    public void a_testInsertMessageRest() throws Exception {
        // Insert message
        Long messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
        // Test
        GPMessage messageDetail = gpWSClient.getMessageDetail(messageID);
        assertNotNull(messageDetail);
        assertNotNull(messageDetail.getSender());
        assertEquals(message.getSender().getId(), messageDetail.getSender().getId());
        assertNotNull(messageDetail.getRecipient());
        assertEquals(message.getRecipient().getId(), messageDetail.getRecipient().getId());
        assertEquals(message.getSubject(), messageDetail.getSubject());
        assertEquals(message.getText(), messageDetail.getText());
        assertEquals(message.getCommands(), messageDetail.getCommands());
        assertFalse(messageDetail.isRead());
    }

    @Test
    public void b_testInsertMessageMultiCommandRest() throws Exception {
        // Insert message
        message.addCommand(GPMessageCommandType.OPEN_PROJECT);
        Long messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
        // Test
        GPMessage messageDetail = gpWSClient.getMessageDetail(messageID);
        assertNotNull(messageDetail);
        List<GPMessageCommandType> commands = messageDetail.getCommands();
        assertNotNull(commands);
        assertEquals(2, commands.size());
        assertTrue(commands.contains(NONE));
        assertTrue(commands.contains(GPMessageCommandType.OPEN_PROJECT));
    }

    @Test
    public void c_testInsertMultiMessageRest() throws Exception {
        // Insert messages
        MessageDTO messageDTO = new MessageDTO(message);
        messageDTO.setRecipientIDs(Arrays.asList(firstRecipientID, latterRecipientID));
        Boolean result = gpWSClient.insertMultiMessage(messageDTO);
        assertTrue(result);
        // Test first
        List<GPMessage> firstAllMessages = gpWSClient.getAllMessagesByRecipient(firstRecipientID).getMessages();
        assertNotNull(firstAllMessages);
        assertEquals(1, firstAllMessages.size());
        GPMessage firstMessage = firstAllMessages.get(0);
        assertNotNull(firstMessage);
        assertEquals(firstRecipientID, firstMessage.getRecipient().getId());
        // Test latter
        List<GPMessage> latterAllMessages = gpWSClient.getAllMessagesByRecipient(latterRecipientID).getMessages();
        assertNotNull(latterAllMessages);
        assertEquals(1, latterAllMessages.size());
        GPMessage latterMessage = latterAllMessages.get(0);
        assertNotNull(latterMessage);
        assertEquals(latterRecipientID, latterMessage.getRecipient().getId());
    }

    @Test
    public void d_testDeleteMessageRest() throws Exception {
        // Insert message
        Long messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
        // Test
        Boolean result = gpWSClient.deleteMessage(messageID);
        assertTrue(result);
    }

    @Test
    public void e_testMarkMessageAsReadRest() throws Exception {
        // Insert message
        Long messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
        // Test
        Boolean result = gpWSClient.markMessageAsRead(messageID);
        assertTrue(result);
        GPMessage messageDetail = gpWSClient.getMessageDetail(messageID);
        assertNotNull(messageDetail);
        assertTrue(messageDetail.isRead());
    }

    @Test
    public void f_testRetrieveMessagesRest() throws Exception {
        this.insertMessagesSortedRest();
        // Test all messages
        List<GPMessage> allMessages = gpWSClient.getAllMessagesByRecipient(firstRecipientID).getMessages();
        assertNotNull(allMessages);
        assertEquals(3, allMessages.size());
        assertNotNull(allMessages.get(0));
        assertNotNull(allMessages.get(1));
        assertNotNull(allMessages.get(2));
        assertNotNull(allMessages.get(0).getCreationDate());
        assertNotNull(allMessages.get(1).getCreationDate());
        assertNotNull(allMessages.get(2).getCreationDate());
        assertTrue(allMessages.get(0).getCreationDate().after(allMessages.get(1).getCreationDate()));
        assertTrue(allMessages.get(1).getCreationDate().after(allMessages.get(2).getCreationDate()));
        // Test unread messages
        List<GPMessage> unreadMessages = gpWSClient.getUnreadMessagesByRecipient(firstRecipientID).getMessages();
        assertNotNull(unreadMessages);
        assertEquals(2, unreadMessages.size());
        assertNotNull(unreadMessages.get(0));
        assertNotNull(unreadMessages.get(1));
        assertNotNull(unreadMessages.get(0).getCreationDate());
        assertNotNull(unreadMessages.get(1).getCreationDate());
        assertTrue(allMessages.get(0).getCreationDate().after(allMessages.get(1).getCreationDate()));
    }

    @Test
    public void g_testMarkAllMessageAsReadRest() throws Exception {
        this.insertMessagesSortedRest();
        // Test unread messages intial
        List<GPMessage> unreadMessages = gpWSClient.getUnreadMessagesByRecipient(firstRecipientID).getMessages();
        assertNotNull(unreadMessages);
        assertEquals(2, unreadMessages.size());
        // Test mark messages as read
        Boolean result = gpWSClient.markAllMessagesAsReadByRecipient(firstRecipientID);
        assertTrue(result);
        // Test unread messages final
        unreadMessages = gpWSClient.getUnreadMessagesByRecipient(firstRecipientID).getMessages();
        assertEquals(0, unreadMessages.size());
    }

    @Test
    public void h_testMarkPreviousMessagesAsReadRest() throws Exception {
        this.insertMessagesSortedRest();
        // Test unread messages intial
        List<GPMessage> unreadMessages = gpWSClient.getUnreadMessagesByRecipient(firstRecipientID).getMessages();
        assertNotNull(unreadMessages);
        assertEquals(2, unreadMessages.size());
        // Test mark messages as read
        Date toDate = new GregorianCalendar(2012, Calendar.JANUARY, 12 - 1).getTime();
        Boolean result = gpWSClient.markMessagesAsReadByDate(new MarkMessageReadByDateRequest(firstRecipientID, toDate));
        assertTrue(result);
        // Test unread messages final
        unreadMessages = gpWSClient.getUnreadMessagesByRecipient(firstRecipientID).getMessages();
        assertNotNull(unreadMessages);
        assertEquals(1, unreadMessages.size());
    }

    /*
     ***************************************************************************
     */
    private void insertMessagesSortedRest() throws Exception {
        // Insert message 1
        message.setCreationDate(new GregorianCalendar(2012, Calendar.JANUARY, 1).getTime());
        Long messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
        // Insert message 2
        message.setCreationDate(new GregorianCalendar(2012, Calendar.JANUARY, 12).getTime());
        message.setText("Another message to read.");
        messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
        // Insert message 3
        message.setCreationDate(new GregorianCalendar(2012, Calendar.JANUARY, 23).getTime());
        message.setText("Message read.");
        message.setRead(true);
        messageID = gpWSClient.insertMessage(message);
        assertNotNull(messageID);
    }
}