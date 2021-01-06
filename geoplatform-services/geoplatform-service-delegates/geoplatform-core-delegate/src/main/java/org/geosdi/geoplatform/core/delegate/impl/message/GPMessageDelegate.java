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
package org.geosdi.geoplatform.core.delegate.impl.message;

import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.dao.GPMessageDAO;
import org.geosdi.geoplatform.core.delegate.api.message.MessageDelegate;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.message.MarkMessageReadByDateRequest;
import org.geosdi.geoplatform.response.MessageDTO;
import org.geosdi.geoplatform.response.message.GetMessageResponse;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Message service delegate.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Component(value = "gpMessageDelegate")
public class GPMessageDelegate implements MessageDelegate {

    @Autowired
    private GPMessageDAO messageDao;
    @Autowired
    private GPAccountDAO accountDao;

    @Override
    public Long insertMessage(GPMessage message)
            throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkMessage(message); // TODO assert

        GPAccount recipient = this.getAccountById(message.getRecipient().getId());
        EntityCorrectness.checkAccountLog(recipient); // TODO assert
        message.setRecipient(recipient);

        GPAccount sender = this.getAccountById(message.getSender().getId());
        EntityCorrectness.checkAccountLog(sender); // TODO assert
        message.setSender(sender);

        messageDao.persist(message);

        return message.getId();
    }

    @Override
    public Boolean insertMultiMessage(MessageDTO message)
            throws ResourceNotFoundFault {
        GPAccount sender = this.getAccountById(message.getSenderID());
        EntityCorrectness.checkAccountLog(sender); // TODO assert

        int recipientNumber = message.getRecipientIDs().size();
        GPMessage[] messages = new GPMessage[recipientNumber];
        for (int i = 0; i < recipientNumber; i++) {
            Long recipientID = message.getRecipientIDs().get(i);

            // Ignore message where sender and recipiet are the same
            if (recipientID.equals(sender.getId())) {
                continue;
            }

            GPAccount recipient = this.getAccountById(recipientID);
            EntityCorrectness.checkAccountLog(sender); // TODO assert

            messages[i] = MessageDTO.convertToGPMessage(message, sender,
                    recipient);
        }
        messageDao.persist(messages);

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteMessage(Long messageID) throws ResourceNotFoundFault {
        GPMessage message = this.getMessageByID(messageID);
        EntityCorrectness.checkMessageLog(message); // TODO assert

        return messageDao.remove(message);
    }

    @Override
    public GPMessage getMessageDetail(Long messageID) throws
            ResourceNotFoundFault {
        GPMessage message = this.getMessageByID(messageID);
        EntityCorrectness.checkMessageLog(message); // TODO assert
        return message;
    }

    @Override
    public GetMessageResponse getAllMessagesByRecipient(Long recipientID)
            throws ResourceNotFoundFault {
        GPAccount recipient = this.getAccountById(recipientID);
        EntityCorrectness.checkAccountLog(recipient); // TODO assert

        List<GPMessage> allMessages = messageDao.findAllMessagesByRecipient(
                recipientID);
        EntityCorrectness.checkMessageListLog(allMessages); // TODO assert

        return new GetMessageResponse(allMessages);
    }

    @Override
    public GetMessageResponse getUnreadMessagesByRecipient(Long recipientID)
            throws ResourceNotFoundFault {
        GPAccount recipient = this.getAccountById(recipientID);
        EntityCorrectness.checkAccountLog(recipient); // TODO assert

        List<GPMessage> unreadMessages = messageDao.findUnreadMessagesByRecipient(
                recipientID);
        EntityCorrectness.checkMessageListLog(unreadMessages); // TODO assert

        return new GetMessageResponse(unreadMessages);
    }

    @Override
    public Boolean markMessageAsRead(Long messageID)
            throws ResourceNotFoundFault {
        return messageDao.markMessageAsRead(messageID);
    }

    @Override
    public Boolean markAllMessagesAsReadByRecipient(Long recipientID)
            throws ResourceNotFoundFault {
        GPAccount recipient = this.getAccountById(recipientID);
        EntityCorrectness.checkAccountLog(recipient); // TODO assert

        return messageDao.markAllMessagesAsReadByRecipient(recipientID);
    }

    @Override
    public Boolean markMessagesAsReadByDate(
            MarkMessageReadByDateRequest markMessagesAsReadByDate)
            throws ResourceNotFoundFault {
        Long recipientID = markMessagesAsReadByDate.getRecipientID();
        Date toDate = markMessagesAsReadByDate.getToDate();

        GPAccount recipient = this.getAccountById(recipientID);
        EntityCorrectness.checkAccountLog(recipient); // TODO assert

        return messageDao.markMessagesAsReadByDate(recipientID, toDate);
    }

    /**
     ***************************************************************************
     */
    private GPMessage getMessageByID(Long messageID) throws
            ResourceNotFoundFault {
        GPMessage message = messageDao.find(messageID);
        if (message == null) {
            throw new ResourceNotFoundFault("Message not found", messageID);
        }
        return message;
    }

    private GPAccount getAccountById(Long accountID) throws
            ResourceNotFoundFault {
        GPAccount account = accountDao.find(accountID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account not found", accountID);
        }
        return account;
    }

}
