/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.core.api.resources;

import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.message.MarkMessageReadByDateRequest;
import org.geosdi.geoplatform.response.MessageDTO;
import org.geosdi.geoplatform.response.message.GetMessageResponse;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPMessageResource {

    // <editor-fold defaultstate="collapsed" desc="Message">
    // ==========================================================================
    // === Message
    // ==========================================================================
    /**
     * Insert a Message.
     *
     * @param message the Message to insert
     * @return the Message ID
     * @throws Exception if the Account recipient or sender not found or if
     * Message is not valid
     */
    Long insertMessage(GPMessage message) throws Exception;

    /**
     * Insert a same Message for each Account recipient. Ignore message where
     * sender and recipient are the same.
     *
     * @param messageDTO the Message to insert for each Account recipient
     * @return true if the Messages was added
     *
     * @throws Exception if Account sender or one Account recipient not found
     */
    Boolean insertMultiMessage(MessageDTO messageDTO) throws Exception;

    /**
     * Delete a Message by ID.
     *
     * @param messageID the Message ID
     * @return true if the Message was deleted
     *
     * @throws Exception if Message not found
     */
    Boolean deleteMessage(Long messageID) throws Exception;

    /**
     * Retrieve a Message by ID.
     *
     * @param messageID the Message ID
     * @return the Message to retrieve
     *
     * @throws Exception if Message not found
     */
    GPMessage getMessageDetail(Long messageID) throws Exception;

    /**
     * Retrieve all Messages of an Account recipient, sorted in descending order
     * by creation date.
     *
     * @param recipientID the Account recipient ID
     * @return list of all Messages
     *
     * @throws Exception if Account recipient not found
     */
    GetMessageResponse getAllMessagesByRecipient(Long recipientID) throws
            Exception;

    /**
     * Retrieve unread Messages of an Account recipient, sorted in descending
     * order by creation date.
     *
     * @param recipientID the Account recipient ID
     * @return list of unread Messages
     *
     * @throws Exception if Account recipient not found
     */
    GetMessageResponse getUnreadMessagesByRecipient(Long recipientID)
            throws Exception;

    /**
     * Mark a Message as read.
     *
     * @param messageID the Message ID
     * @return true if the Message was marked
     *
     * @throws Exception if Message not found
     */
    Boolean markMessageAsRead(Long messageID) throws Exception;

    /**
     * Mark all Messages of an Account recipient as read.
     *
     * @param recipientID the Account recipient ID
     * @return true if the Messages was marked
     *
     * @throws Exception if Account recipient not found
     */
    Boolean markAllMessagesAsReadByRecipient(Long recipientID)
            throws Exception;

    /**
     * Mark all unread Messages, until a date, of an Account recipient as read.
     *
     * @param markMessageAsReadByDateReq
     * @return true if the Messages was marked
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    Boolean markMessagesAsReadByDate(
            MarkMessageReadByDateRequest markMessageAsReadByDateReq)
            throws Exception;
    // </editor-fold>
}
