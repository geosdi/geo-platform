/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import jakarta.persistence.*;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import java.io.Serializable;

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
        @UniqueConstraint(columnNames = {"principal", "sid", "organization_id"}),
        indexes = {
                @Index(columnList = "sid", name = "ACL_SID_INDEX")
        })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sid")
@Getter
@Setter
@ToString
// TODO: implements Sid? extends PrincipalSid, GrantedAuthoritySid?
public class AclSid implements Serializable {

    private static final long serialVersionUID = -7679673575853604494L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gp_acl_sid_generator")
    @GenericGenerator(name = "gp_acl_sid_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ACL_SID_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    private Long id;
    /**
     * Standard security concept which represents only an authenticated entity.
     * <p>
     * If Id refers to a principal name (username) will be true.
     * If refers to a GrantedAuthority (role name) will be false.
     */
    @Column(nullable = false)
    private boolean principal = true;
    /**
     * SID stands for "Secure Identity".
     */
    @Column(nullable = false)
    private String sid;
    //
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPOrganization organization;

    //<editor-fold defaultstate="collapsed" desc="Constructor methods">
    public AclSid() {
    }

    /**
     * @param principal
     * @param sid
     * @see #principal
     * @see #sid
     */
    public AclSid(boolean principal, String sid) {
        this.principal = principal;
        this.sid = sid;
    }

    /**
     * @param principal
     * @param sid
     * @param organization
     * @see #principal
     * @see #sid
     */
    public AclSid(boolean principal, String sid, GPOrganization organization) {
        this.principal = principal;
        this.sid = sid;
        this.organization = organization;
    }
    //</editor-fold>

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