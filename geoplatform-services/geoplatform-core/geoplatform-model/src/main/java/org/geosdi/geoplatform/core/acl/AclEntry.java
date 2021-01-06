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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@UniqueConstraint(columnNames = {"acl_object_identity", "ace_order"}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entry")
// TODO: implements AccessControlEntry?
public class AclEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACL_ENTRY_SEQ")
    @SequenceGenerator(name = "ACL_ENTRY_SEQ", sequenceName = "ACL_ENTRY_SEQ")
    private Long id;
    //
    @ManyToOne
    @JoinColumn(name = "acl_object_identity", nullable = false)
    @Index(name = "ACL_ENTRY_OBJECT_IDENTITY_INDEX")
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
    @Index(name = "ACL_ENTRY_SID_INDEX")
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
     * @return the aclObject
     */
    public AclObjectIdentity getAclObject() {
        return aclObject;
    }

    /**
     * @param aclObject the aclObject to set
     */
    public void setAclObject(AclObjectIdentity aclObject) {
        this.aclObject = aclObject;
    }

    /**
     * @return the aceOrder
     * 
     * @see #aceOrder
     */
    public Integer getAceOrder() {
        return aceOrder;
    }

    /**
     * @param aceOrder the aceOrder to set
     * 
     * @see #aceOrder
     */
    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    /**
     * @return the aclSid
     */
    public AclSid getAclSid() {
        return aclSid;
    }

    /**
     * @param aclSid the aclSid to set
     */
    public void setAclSid(AclSid aclSid) {
        this.aclSid = aclSid;
    }

    /**
     * @return the mask
     * 
     * @see #mask
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @param mask the mask to set
     * 
     * @see #mask
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

    /**
     * @return the granting
     * 
     * @see #granting
     */
    public boolean isGranting() {
        return granting;
    }

    /**
     * @param granting the granting to set
     * 
     * @see #granting
     */
    public void setGranting(boolean granting) {
        this.granting = granting;
    }

    /**
     * @return the auditSuccess
     */
    public boolean isAuditSuccess() {
        return auditSuccess;
    }

    /**
     * @param auditSuccess the auditSuccess to set
     */
    public void setAuditSuccess(boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    /**
     * @return the auditFailure
     */
    public boolean isAuditFailure() {
        return auditFailure;
    }

    /**
     * @param auditFailure the auditFailure to set
     */
    public void setAuditFailure(boolean auditFailure) {
        this.auditFailure = auditFailure;
    }
    //</editor-fold>

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AclEntry {");
        str.append("id=").append(id);
        str.append(", aclObject.id=").append(
                aclObject != null ? aclObject.getId() : "NULL");
        str.append(", aceOrder=").append(aceOrder);
        str.append(", aclSid.id=").append(
                aclSid != null ? aclSid.getId() : "NULL");
        str.append(", mask=").append(mask);
        str.append(", granting=").append(granting);
        str.append(", auditSuccess=").append(auditSuccess);
        str.append(", auditFailure=").append(auditFailure).append('}');
        return str.toString();
    }
}
