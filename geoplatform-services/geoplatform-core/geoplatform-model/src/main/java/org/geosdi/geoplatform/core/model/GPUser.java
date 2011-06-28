//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.core.model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author giuseppe
 * 
 */
@XmlRootElement(name = "User")
@Entity(name = "User")
@Table(name = "gp_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
public class GPUser implements Serializable, UserDetails {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1354980934257649175L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_USER_SEQ")
    @SequenceGenerator(name = "GP_USER_SEQ", sequenceName = "GP_USER_SEQ")
    private long id;
    //
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;
    /**
     * since memberService integration
     */
    @Column(name = "user_password")
    private String password;
    //
    @Column(name = "email_address", nullable = false)
    private String emailAddress;
    /**
     * since memberService integration
     */
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled = false;
    //
    @Column(name = "send_email", nullable = false)
    private boolean sendEmail = false;
    //
    @Transient
    private Boolean accountNonExpired;
    //
    @Transient
    private Boolean accountNonLocked;
    //
    @Transient
    private Boolean credentialsNonExpired;
    //
    @Transient
    private Collection<GPAuthority> gpAuthorities;

    /**
     * Default constructor
     */
    public GPUser() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    @Override
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
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        System.out.println("Wawawa " + password);
        this.password = Utility.md5hash(password);
        System.out.println("Wawawa hashed " + this.password);
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
     * @return the enabled
     */
    @Override
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

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new HashSet<GrantedAuthority>();
        for (GrantedAuthority ga : gpAuthorities) {
            auth.add(ga);
        }
        return auth;
    }

    /**
     * @return the gpAuthorities
     */
    public Collection<GPAuthority> getGpAuthorities() {
        return gpAuthorities;
    }

    /**
     * @param gpAuthorities
     *          the gpAuthorities to set
     */
    public void setGpAuthorities(List<GPAuthority> gpAuthorities) {
        this.gpAuthorities = gpAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPUser {");
        str.append("id=").append(id);
        str.append(", username=").append(username);
        str.append(", password=").append(password);
        str.append(", emailAddress=").append(emailAddress);
        str.append(", enabled=").append(enabled);
        str.append(", sendEmail=").append(sendEmail);
        str.append(", accountNonExpired=").append(accountNonExpired);
        str.append(", accountNonLocked=").append(accountNonLocked);
        str.append(", credentialsNonExpired=").append(credentialsNonExpired).append('}');
        return str.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPUser other = (GPUser) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    public boolean verify(String password) throws NoSuchAlgorithmException {
        String hashPasswordSpeicifed = Utility.md5hash(password);
        if (this.password.equals(hashPasswordSpeicifed)) {
            return true;
        } else {
            return false;
        }
    }
}
