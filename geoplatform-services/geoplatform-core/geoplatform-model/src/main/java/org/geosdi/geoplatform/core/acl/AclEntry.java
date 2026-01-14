/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import java.io.Serializable;

/**
 * The <tt>AclEntry</tt> domain class contains entries representing grants
 * (or denials) of a permission on an object instance to a recipient.
 * The aclObject field references the domain class instance (since an instance
 * can have many granted permissions). The aclSid field references the recipient.
 * The granting field determines whether the entry grants the permission (true)
 * or denies it (false). The aceOrder field specifies the position of the entry,
 * which is important because the entries are evaluated in order and the first
 * matching entry determines whether access is allowed. The mask field holds the
 * permission. auditSuccess and auditFailure determine whether to log success
 * and/or failure events (these both default to true).
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Entity
@Table(name = "acl_entry",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"acl_object_identity", "ace_order"}),
        indexes = {
                @Index(columnList = "acl_object_identity", name = "ACL_ENTRY_OBJECT_IDENTITY_INDEX"),
                @Index(columnList = "sid", name = "ACL_ENTRY_SID_INDEX")
        })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entry")
@Getter
@Setter
@ToString
// TODO: implements AccessControlEntry?
public class AclEntry implements Serializable {

    private static final long serialVersionUID = -646975886421027258L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gp_acl_entry_generator")
    @SequenceGenerator(name = "gp_acl_entry_generator", sequenceName = "ACL_ENTRY_SEQ")
    private Long id;
    //
    @ManyToOne
    @JoinColumn(name = "acl_object_identity", nullable = false)
    private AclObjectIdentity aclObject;
    /**
     * Order wrt AclObjectIdentity.
     */
    @Column(name = "ace_order", nullable = false)
    private Integer aceOrder;
    //
    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AclSid aclSid;
    /**
     * Mask of permission type.
     */
    @Column(nullable = false)
    private Integer mask;
    /**
     * Granting of permission.
     */
    @Column(nullable = false)
    private boolean granting = false;
    //
    @Column(name = "audit_success", nullable = false)
    private boolean auditSuccess = false;
    //
    @Column(name = "audit_failure", nullable = false)
    private boolean auditFailure = false;

    //<editor-fold defaultstate="collapsed" desc="Constructor methods">
    public AclEntry() {
    }

    /**
     * @param aclObjectIdentity
     * @param aceOrder
     * @param aclSid
     * @param mask
     * @param granting
     */
    public AclEntry(AclObjectIdentity aclObjectIdentity, Integer aceOrder, AclSid aclSid,
            Integer mask, boolean granting) {
        this.aclObject = aclObjectIdentity;
        this.aceOrder = aceOrder;
        this.aclSid = aclSid;
        this.mask = mask;
        this.granting = granting;
        this.auditSuccess = true;
        this.auditFailure = true;
    }

    /**
     * @param aclObjectIdentity
     * @param aceOrder
     * @param aclSid
     * @param mask
     * @param granting
     * @param auditSuccess
     * @param auditFailure
     */
    public AclEntry(AclObjectIdentity aclObjectIdentity, Integer aceOrder, AclSid aclSid,
            Integer mask, boolean granting, boolean auditSuccess, boolean auditFailure) {
        this.aclObject = aclObjectIdentity;
        this.aceOrder = aceOrder;
        this.aclSid = aclSid;
        this.mask = mask;
        this.granting = granting;
        this.auditSuccess = auditSuccess;
        this.auditFailure = auditFailure;
    }
    //</editor-fold>
}
