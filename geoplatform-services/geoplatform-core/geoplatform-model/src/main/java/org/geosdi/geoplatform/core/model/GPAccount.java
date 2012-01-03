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
package org.geosdi.geoplatform.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
@XmlTransient
@XmlSeeAlso(value = {GPUser.class, GPApplication.class})
@Entity(name = "Account")
@Table(name = "gp_account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "GP_ACCOUNT_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class GPAccount implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5273811518496727023L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_ACCOUNT_SEQ")
    @SequenceGenerator(name = "GP_ACCOUNT_SEQ", sequenceName = "GP_ACCOUNT_SEQ")
    private Long id;
    //
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled = false;
    //
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date(System.currentTimeMillis());
    //    
    @Column(name = "account_temporary")
    private boolean accountTemporary = false;
    //
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;
    //
    @Transient
    private Boolean accountNonLocked;
    //
    @Transient
    private Boolean credentialsNonExpired;
    //
    @Transient
    private List<GPAuthority> authorities;
    //
    // NOTE: Hibernate with this list remove "on delete cascade" on FK of gp_account_project(account_id)
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
//    private List<GPAccountProject> accountProjects = new LinkedList<GPAccountProject>();
    //
    @Column(name = "defaultProject_id")
    private Long defaultProjectID;

    public abstract String getStringID();

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
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *          the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the accountTemporary
     */
    public boolean isAccountTemporary() {
        return accountTemporary;
    }

    /**
     * @param accountTemporary the accountTemporary to set
     */
    public void setAccountTemporary(boolean accountTemporary) {
        this.accountTemporary = accountTemporary;
    }

    /**
     * @return the accountNonExpired
     */
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * @param accountNonExpired
     *          the accountNonExpired to set
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new HashSet<GrantedAuthority>();
        for (GrantedAuthority ga : authorities) {
            auth.add(ga);
        }
        return auth;
    }

    /**
     * @return the gpAuthorities
     */
    public List<GPAuthority> getGPAuthorities() {
        return authorities;
    }

    /**
     * @param authorities
     *          the authorities to set
     */
    public void setGPAuthorities(List<GPAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * @return the defaultProjectID
     */
    public Long getDefaultProjectID() {
        return defaultProjectID;
    }

    /**
     * @param defaultProjectID the defaultProjectID to set
     */
    public void setDefaultProjectID(Long defaultProjectID) {
        this.defaultProjectID = defaultProjectID;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName());
        str.append(" id=").append(id);
        str.append(", enabled=").append(enabled);
        str.append(", creationDate=").append(creationDate);
        str.append(", accountTemporary=").append(accountTemporary);
        str.append(", accountNonExpired=").append(accountNonExpired);
//        str.append(", accountNonLocked=").append(accountNonLocked);
//        str.append(", credentialsNonExpired=").append(credentialsNonExpired);
        if (authorities != null) {
            str.append(", authorities.size=").append(authorities.size());
        } else {
            str.append(", authorities=NULL");
        }
        str.append(", defaultProjectID=").append(defaultProjectID);
        return str.toString();
    }

    /*
     * (non-Javadoc)
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
        final GPAccount other = (GPAccount) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
