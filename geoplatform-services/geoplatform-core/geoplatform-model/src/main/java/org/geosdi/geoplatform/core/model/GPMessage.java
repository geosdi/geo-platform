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
package org.geosdi.geoplatform.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.gui.shared.GPMessageCommandType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement(name = "Message")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Message")
@Table(name = "gp_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "message")
public class GPMessage implements Serializable {

    private static final long serialVersionUID = -160635166814490037L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GP_MESSAGE_SEQ")
    @SequenceGenerator(name = "GP_MESSAGE_SEQ", sequenceName = "GP_MESSAGE_SEQ")
    private Long id;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "ACCOUNT_RECIPIENT_ID_INDEX")
    private GPAccount recipient;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "ACCOUNT_SENDER_ID_INDEX")
    private GPAccount sender;
    //
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    //
    @Column(length = 100)
    private String subject;
    //
    @Column(columnDefinition = "TEXT")
    private String text;
    //
    @Column(name = "is_read")
    private boolean isRead = false;
    //
    @Column
    private String commands;
    //
    @Column
    private String commandsProperties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GPAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(GPAccount recipient) {
        this.recipient = recipient;
    }

    public GPAccount getSender() {
        return sender;
    }

    public void setSender(GPAccount sender) {
        this.sender = sender;
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
        return isRead;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    public List<GPMessageCommandType> getCommands() {
        if (commands == null) {
            return new ArrayList<GPMessageCommandType>(0);
        }

        List<String> mcString = Arrays.asList(commands.split(";"));
        List<GPMessageCommandType> mcList = new ArrayList<GPMessageCommandType>(
                mcString.size());
        for (String string : mcString) {
            GPMessageCommandType mc = GPMessageCommandType.valueOf(string);
            mcList.add(mc);
        }
        return mcList;
    }

    public void setCommands(List<GPMessageCommandType> commands) {
        if (commands == null || commands.isEmpty()) {
            this.commands = null;
            return;
        }

        StringBuilder str = new StringBuilder();
        for (GPMessageCommandType mc : commands) {
            assert (mc != null) : "command ith must be NOT NULL.";
            str.append(mc.name()).append(";");
        }
        str.deleteCharAt(str.length() - 1);
        this.commands = str.toString();
    }

    public void addCommand(GPMessageCommandType command) {
        assert (command != null) : "command must be NOT NULL.";
        if (commands != null) {
            commands += ";" + command.name();
            return;
        }
        commands = command.name();
    }

    public String getCommandsProperties() {
        return commandsProperties;
    }

    public void setCommandsProperties(String commandsProperties) {
        this.commandsProperties = commandsProperties;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(
                " {");
        str.append(" id=").append(id);
        str.append(", recipient=").append(recipient.getNaturalID());
        str.append(", sender=").append(sender.getNaturalID());
        str.append(", creationDate=").append(creationDate);
        str.append(", subject=").append(subject);
        str.append(", text=").append(text);
        str.append(", isRead=").append(isRead);
        str.append(", commands=").append(commands);
        str.append(", commandsProperties=").append(commandsProperties);
        return str.append('}').toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPMessage other = (GPMessage) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
