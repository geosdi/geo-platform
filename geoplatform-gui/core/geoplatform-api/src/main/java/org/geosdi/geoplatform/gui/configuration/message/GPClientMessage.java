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
package org.geosdi.geoplatform.gui.configuration.message;

import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPClientMessage extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 3869338219274540179L;
    //
    private Long id;
    private String sender;
    private String recipient;
    private Date creationDate;
    private String subject;
    private String text;
    private boolean read;
    private List<GPMessageCommandType> commands;
    private String commandsProperties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public List<GPMessageCommandType> getCommands() {
        return commands;
    }

    public void setCommands(List<GPMessageCommandType> commands) {
        this.commands = commands;
    }

    public String getCommandsProperties() {
        return commandsProperties;
    }

    public void setCommandsProperties(String commandsProperties) {
        this.commandsProperties = commandsProperties;
    }

    @Override
    public String toString() {
        return "GPClientMessage{"
                + "id=" + id
                + ", sender=" + sender
                + ", recipient=" + recipient
                + ", creationDate=" + creationDate
                + ", subject=" + subject
                + ", text=" + text
                + ", read=" + read
                + ", commands=" + commands
                + ", commandsProperties=" + commandsProperties + '}';
    }
}
