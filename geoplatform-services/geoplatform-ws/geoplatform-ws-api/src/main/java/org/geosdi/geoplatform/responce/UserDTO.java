//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.responce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.core.model.GPAuthority;

import org.geosdi.geoplatform.core.model.GPUser;

/**
 * @author giuseppe
 * 
 */
@XmlRootElement(name = "UserDTO")
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private boolean enabled;
    private boolean sendEmail;
    private String emailAddress;
    private Collection<GPAuthority> authorities;

    /**
     * Default constructor
     */
    public UserDTO() {
        super();
    }

    public UserDTO(GPUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.enabled = user.isEnabled();
        this.sendEmail = user.isSendEmail();
        this.emailAddress = user.getEmailAddress();
        this.authorities = user.getGPAuthorities();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *          the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the sendEmail
     */
    public boolean isSendEmail() {
        return sendEmail;
    }

    /**
     * @param sendEmail
     *            the sendEmail to set
     */
    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     *            the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the authorities
     */
    public Collection<GPAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities
     *          the authorities to set
     */
    public void setAuthorities(Collection<GPAuthority> authorities) {
        this.authorities = authorities;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserDTO [id=" + id
                + ", username=" + username
                + ", name=" + name
                + ", enabled=" + enabled
                + ", sendEmail=" + sendEmail
                + ", emailAddress=" + emailAddress + "]";
    }

    public static List<UserDTO> convertToUserDTOList(List<GPUser> users) {
        List<UserDTO> usersDTO = new ArrayList<UserDTO>(users.size());

        for (GPUser user : users) {
            usersDTO.add(new UserDTO(user));
        }

        return usersDTO;
    }
}
