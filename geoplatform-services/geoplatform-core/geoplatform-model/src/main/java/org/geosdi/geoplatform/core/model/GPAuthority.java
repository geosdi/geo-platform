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
package org.geosdi.geoplatform.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI Group
 * 
 */
@XmlRootElement(name = "Authority")
@Entity(name = "Authority")
@Table(name = "gp_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "authority")
public class GPAuthority implements GrantedAuthority, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5005299814060260152L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_AUTHORITY_SEQ")
    @SequenceGenerator(name = "GP_AUTHORITY_SEQ", sequenceName = "GP_AUTHORITY_SEQ")
    private long id;
    //
    @Column(name = "username", nullable = false)
    private String username;
    //
    @Column(name = "authority", nullable = false)
    private String authority;
    //
    @ManyToOne(targetEntity = GPUser.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPUser gpUser;

    public GPAuthority() {
    }

    public GPAuthority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public GPAuthority(GPUser gpUser, String authority) {
        this.gpUser = gpUser;
        this.username = gpUser.getUsername();
        this.authority = authority;
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

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return authority;
    }

    /**
     * @param authority
     *            the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 
     * @return gpUser
     */
    public GPUser getGpUser() {
        return gpUser;
    }

    /**
     * @param gpUser
     *            the gpUser to set
     */
    public void setGpUsers(GPUser gpUser) {
        this.gpUser = gpUser;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPAuthority {");
        str.append("id=").append(id);
        str.append(", username=").append(username);
        str.append(", authority=").append(authority);
        str.append(", gpUsers=").append(gpUser).append("}");
        return str.toString();
    }
}
