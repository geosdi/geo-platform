/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.responce;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.core.model.GPMessageCommandType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageDTO {

    private Long id;
    //
    private Long senderID;
    //
    @XmlElementWrapper(name = "recipientIDs")
    @XmlElement(name = "recipientID")
    private List<Long> recipientIDs;
    //
    private Date creationDate;
    private String text;
    private Boolean read;
    // TODO List of commands
    private GPMessageCommandType command;

    /**
     * Default constructor.
     */
    public MessageDTO() {
    }

    /**
     * Convert a Message entity into a Message DTO with only one recipient.
     *
     * @param message the Message entity to convert
     */
    public MessageDTO(GPMessage message) {
        this.id = message.getId();
        this.senderID = message.getSender().getId();
        this.recipientIDs = Arrays.asList(message.getRecipient().getId());
        this.creationDate = message.getCreationDate();
        this.text = message.getText();
        this.read = message.isRead();
        this.command = message.getCommand();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the senderID
     */
    public Long getSenderID() {
        return senderID;
    }

    /**
     * @param senderID the senderID to set
     */
    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    /**
     * @return the recipientIDs
     */
    public List<Long> getRecipientIDs() {
        return recipientIDs;
    }

    /**
     * @param recipientIDs the recipientIDs to set
     */
    public void setRecipientIDs(List<Long> recipientIDs) {
        this.recipientIDs = recipientIDs;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the read
     */
    public Boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * @return the command
     */
    public GPMessageCommandType getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(GPMessageCommandType command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "MessageDTO{" + "id=" + id
                + ", senderID=" + senderID
                + ", recipientIDs=" + recipientIDs
                + ", creationDate=" + creationDate
                + ", text=" + text
                + ", read=" + read
                + ", command=" + command + '}';
    }

    /**
     * Convert an instance of MessageDTO to {@link GPMessage}.
     */
    public static GPMessage convertToGPMessage(MessageDTO messageDTO,
            GPAccount sender, GPAccount recipient) {
        assert (sender != null) : "Sender must be NOT NULL.";
        assert (recipient != null) : "Recipiet must be NOT NULL.";

        GPMessage message = new GPMessage();
        message.setId(messageDTO.getId());
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setText(messageDTO.getText());
        message.setCreationDate(messageDTO.getCreationDate() == null
                ? new Date(System.currentTimeMillis()) : messageDTO.getCreationDate());
        message.setRead(messageDTO.isRead() == null
                ? false : messageDTO.isRead());
        message.setCommand(messageDTO.getCommand() == null
                ? GPMessageCommandType.NONE : messageDTO.getCommand());
        return message;
    }
}
