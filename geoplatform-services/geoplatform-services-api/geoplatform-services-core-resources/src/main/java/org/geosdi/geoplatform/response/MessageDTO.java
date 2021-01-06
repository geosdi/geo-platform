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
package org.geosdi.geoplatform.response;

import java.util.ArrayList;
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
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;

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
    private String subject;
    private String text;
    private Boolean read;
    //
    @XmlElementWrapper(name = "commandList")
    @XmlElement(name = "command")
    private List<GPMessageCommandType> commands;
    //
    private String commandsProperties;

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
        this.subject = message.getSubject();
        this.text = message.getText();
        this.read = message.isRead();

        if (message.getCommands() != null) {
            commands = new ArrayList<GPMessageCommandType>(message.getCommands().size());
            for (GPMessageCommandType command : message.getCommands()) {
                commands.add(command);
            }
        }
        this.commandsProperties = message.getCommandsProperties();
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
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
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
     * @return the commands
     */
    public List<GPMessageCommandType> getCommands() {
        return commands;
    }

    /**
     * @param commands the commands to set
     */
    public void setCommands(List<GPMessageCommandType> commands) {
        this.commands = commands;
    }

    /**
     * @param command the command to add
     */
    public void addCommands(GPMessageCommandType command) {
        assert (command != null) : "command must be NOT NULL.";
        if (this.commands == null) {
            this.commands = new ArrayList<GPMessageCommandType>();
        }
        this.commands.add(command);
    }

    /**
     * @return the commandsProperties
     */
    public String getCommandsProperties() {
        return commandsProperties;
    }

    /**
     * @param commandsProperties the commandsProperties to set
     */
    public void setCommandsProperties(String commandsProperties) {
        this.commandsProperties = commandsProperties;
    }

    @Override
    public String toString() {
        return "MessageDTO{" + "id=" + id
                + ", senderID=" + senderID
                + ", recipientIDs=" + recipientIDs
                + ", creationDate=" + creationDate
                + ", subject=" + subject
                + ", text=" + text
                + ", read=" + read
                + ", commands=" + commands
                + ", commandsProperties=" + commandsProperties + '}';
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
        message.setSubject(messageDTO.getSubject());
        message.setText(messageDTO.getText());
        message.setCreationDate(messageDTO.getCreationDate() == null
                ? new Date(System.currentTimeMillis()) : messageDTO.getCreationDate());
        message.setRead(messageDTO.isRead() == null
                ? false : messageDTO.isRead());
        if (messageDTO.getCommands() != null) {
            message.setCommands(messageDTO.getCommands());
        }
        message.setCommandsProperties(messageDTO.getCommandsProperties());
        return message;
    }
}
