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
package org.geosdi.geoplatform.core.acl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The <tt>AclSid</tt> (SID is an acronym for "Security Identity") domain class
 * contains entries for the names of grant recipients (principal).
 * These are principal name (usernames, where principal is true) or
 * GrantedAuthority (role name, where principal is false). When granting
 * permissions to a role, any user with that role receives that permission.
 * 
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Entity
@Table(name = "acl_sid",
       uniqueConstraints =
@UniqueConstraint(columnNames = {"principal", "sid", "organization_id"}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sid")
// TODO: implements Sid? extends PrincipalSid, GrantedAuthoritySid?
public class AclSid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACL_SID_SEQ")
    @SequenceGenerator(name = "ACL_SID_SEQ", sequenceName = "ACL_SID_SEQ")
    private Long id;
    /**
     * Standard security concept which represents only an authenticated entity. 
     * 
     * If Id refers to a principal name (username) will be true.
     * If refers to a GrantedAuthority (role name) will be false.
     */
    @Column(nullable = false)
    private boolean principal = true;
    /**
     * SID stands for "Secure Identity".
     */
    @Column(nullable = false)
    @Index(name = "ACL_SID_INDEX")
    private String sid;
    //
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPOrganization organization;

    //<editor-fold defaultstate="collapsed" desc="Constructor methods">
    public AclSid() {
    }

    /**
     * 
     * @param principal
     * @param sid
     * 
     * @see #principal
     * @see #sid
     */
    public AclSid(boolean principal, String sid) {
        this.principal = principal;
        this.sid = sid;
    }

    /**
     * 
     * @param principal
     * @param sid
     * @param organization
     * 
     * @see #principal
     * @see #sid
     */
    public AclSid(boolean principal, String sid, GPOrganization organization) {
        this.principal = principal;
        this.sid = sid;
        this.organization = organization;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setter methods">
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
     * @return the principal
     * 
     * @see #principal
     */
    public boolean isPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     * 
     * @see #principal
     */
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    /**
     * @return the sid
     * 
     * @see #sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     * 
     * @see #sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the organization
     */
    public GPOrganization getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(GPOrganization organization) {
        this.organization = organization;
    }
    //</editor-fold>

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AclSid {");
        str.append("id=").append(id);
        str.append(", principal=").append(principal);
        str.append(", sid=").append(sid);
        str.append(", organization=").append(organization);
        return str.append('}').toString();
    }

    /**
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
        final AclSid other = (AclSid) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return sid.hashCode();
    }
}
