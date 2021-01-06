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
package org.geosdi.geoplatform.model.rest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.request.message.MarkMessageReadByDateRequest;
import org.geosdi.geoplatform.response.MessageDTO;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
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
        idUserTest = this.createAndInsertUser(usernameTest, organizationTest,
                GPRole.USER);
        userTest = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));

        firstRecipientID = this.createAndInsertUser("first_recipient_RS",
                organizationTest, GPRole.USER);
        firstRecipient = gpWSClient.getUserDetail(firstRecipientID);

        latterRecipientID = this.createAndInsertUser("latter_recipient_RS",
                organizationTest, GPRole.VIEWER);
//        latterRecipient = gpWSClient.getUserDetail(latterRecipientID);

        // Create message
        message = new GPMessage();
        message.setRecipient(firstRecipient);
        message.setSender(userTest);
        message.setCreationDate(new Date(System.currentTimeMillis()));
        message.setRead(false);
        message.setSubject("Foo subject REST");
        message.setText("Foo message REST.");
        message.addCommand(GPMessageCommandType.NONE);
    }

    @Test
    public void testInsertMessageRest() throws Exception {
        // Insert message
        Long messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);

        // Test
        GPMessage messageDetail = gpWSClient.getMessageDetail(messageID);
        Assert.assertNotNull(messageDetail);
        Assert.assertNotNull(messageDetail.getSender());
        Assert.assertEquals(message.getSender().getId(),
                messageDetail.getSender().getId());
        Assert.assertNotNull(messageDetail.getRecipient());
        Assert.assertEquals(message.getRecipient().getId(),
                messageDetail.getRecipient().getId());
        Assert.assertEquals(message.getSubject(), messageDetail.getSubject());
        Assert.assertEquals(message.getText(), messageDetail.getText());
        Assert.assertEquals(message.getCommands(), messageDetail.getCommands());
        Assert.assertFalse(messageDetail.isRead());
    }

    @Test
    public void testInsertMessageMultiCommandRest() throws Exception {
        // Insert message
        message.addCommand(GPMessageCommandType.OPEN_PROJECT);
        Long messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);

        // Test
        GPMessage messageDetail = gpWSClient.getMessageDetail(messageID);
        Assert.assertNotNull(messageDetail);
        List<GPMessageCommandType> commands = messageDetail.getCommands();
        Assert.assertNotNull(commands);
        Assert.assertEquals(2, commands.size());
        Assert.assertTrue(commands.contains(GPMessageCommandType.NONE));
        Assert.assertTrue(commands.contains(GPMessageCommandType.OPEN_PROJECT));
    }

    @Test
    public void testInsertMultiMessageRest() throws Exception {
        // Insert messages
        MessageDTO messageDTO = new MessageDTO(message);
        messageDTO.setRecipientIDs(Arrays.asList(firstRecipientID,
                latterRecipientID));
        Boolean result = gpWSClient.insertMultiMessage(messageDTO);
        Assert.assertTrue(result);

        // Test first
        List<GPMessage> firstAllMessages = gpWSClient.getAllMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertNotNull(firstAllMessages);
        Assert.assertEquals(1, firstAllMessages.size());
        GPMessage firstMessage = firstAllMessages.get(0);
        Assert.assertNotNull(firstMessage);
        Assert.assertEquals(firstRecipientID,
                firstMessage.getRecipient().getId());

        // Test latter
        List<GPMessage> latterAllMessages = gpWSClient.getAllMessagesByRecipient(
                latterRecipientID).getMessages();
        Assert.assertNotNull(latterAllMessages);
        Assert.assertEquals(1, latterAllMessages.size());
        GPMessage latterMessage = latterAllMessages.get(0);
        Assert.assertNotNull(latterMessage);
        Assert.assertEquals(latterRecipientID,
                latterMessage.getRecipient().getId());
    }

    @Test
    public void testDeleteMessageRest() throws Exception {
        // Insert message
        Long messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);

        // Test
        Boolean result = gpWSClient.deleteMessage(messageID);
        Assert.assertTrue(result);
    }

    @Test
    public void testMarkMessageAsReadRest() throws Exception {
        // Insert message
        Long messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);

        // Test
        Boolean result = gpWSClient.markMessageAsRead(messageID);
        Assert.assertTrue(result);

        GPMessage messageDetail = gpWSClient.getMessageDetail(messageID);
        Assert.assertNotNull(messageDetail);
        Assert.assertTrue(messageDetail.isRead());
    }

    @Test
    public void testRetrieveMessagesRest() throws Exception {
        this.insertMessagesSortedRest();

        // Test all messages
        List<GPMessage> allMessages = gpWSClient.getAllMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertNotNull(allMessages);
        Assert.assertEquals(3, allMessages.size());
        Assert.assertNotNull(allMessages.get(0));
        Assert.assertNotNull(allMessages.get(1));
        Assert.assertNotNull(allMessages.get(2));
        Assert.assertNotNull(allMessages.get(0).getCreationDate());
        Assert.assertNotNull(allMessages.get(1).getCreationDate());
        Assert.assertNotNull(allMessages.get(2).getCreationDate());
        Assert.assertTrue(allMessages.get(0).getCreationDate().after(
                allMessages.get(1).getCreationDate()));
        Assert.assertTrue(allMessages.get(1).getCreationDate().after(
                allMessages.get(2).getCreationDate()));

        // Test unread messages
        List<GPMessage> unreadMessages = gpWSClient.getUnreadMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertNotNull(unreadMessages);
        Assert.assertEquals(2, unreadMessages.size());
        Assert.assertNotNull(unreadMessages.get(0));
        Assert.assertNotNull(unreadMessages.get(1));
        Assert.assertNotNull(unreadMessages.get(0).getCreationDate());
        Assert.assertNotNull(unreadMessages.get(1).getCreationDate());
        Assert.assertTrue(allMessages.get(0).getCreationDate().after(
                allMessages.get(1).getCreationDate()));
    }

    @Test
    public void testMarkAllMessageAsReadRest() throws Exception {
        this.insertMessagesSortedRest();

        // Test unread messages intial
        List<GPMessage> unreadMessages = gpWSClient.getUnreadMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertNotNull(unreadMessages);
        Assert.assertEquals(2, unreadMessages.size());

        // Test mark messages as read
        Boolean result = gpWSClient.markAllMessagesAsReadByRecipient(
                firstRecipientID);
        Assert.assertTrue(result);

        // Test unread messages final
        unreadMessages = gpWSClient.getUnreadMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertEquals(0, unreadMessages.size());
    }

    @Test
    public void testMarkPreviousMessagesAsReadRest() throws Exception {
        this.insertMessagesSortedRest();

        // Test unread messages intial
        List<GPMessage> unreadMessages = gpWSClient.getUnreadMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertNotNull(unreadMessages);
        Assert.assertEquals(2, unreadMessages.size());

        // Test mark messages as read
        Date toDate = new GregorianCalendar(2012, Calendar.JANUARY, 12 - 1).getTime();
        Boolean result = gpWSClient.markMessagesAsReadByDate(
                new MarkMessageReadByDateRequest(firstRecipientID,
                        toDate));
        Assert.assertTrue(result);

        // Test unread messages final
        unreadMessages = gpWSClient.getUnreadMessagesByRecipient(
                firstRecipientID).getMessages();
        Assert.assertNotNull(unreadMessages);
        Assert.assertEquals(1, unreadMessages.size());
    }

    /*
     ***************************************************************************
     */
    private void insertMessagesSortedRest() throws Exception {
        // Insert message 1
        message.setCreationDate(
                new GregorianCalendar(2012, Calendar.JANUARY, 1).getTime());
        Long messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);
        // Insert message 2
        message.setCreationDate(
                new GregorianCalendar(2012, Calendar.JANUARY, 12).getTime());
        message.setText("Another message to read.");
        messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);
        // Insert message 3
        message.setCreationDate(
                new GregorianCalendar(2012, Calendar.JANUARY, 23).getTime());
        message.setText("Message read.");
        message.setRead(true);
        messageID = gpWSClient.insertMessage(message);
        Assert.assertNotNull(messageID);
    }

}
