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

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
@Entity
@Table(name = "acl_entry", uniqueConstraints =
@UniqueConstraint(columnNames = {"acl_object_identity", "ace_order"}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entry")
// TODO: implements AccessControlEntry?
public class AclEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACL_ENTRY_SEQ")
    @SequenceGenerator(name = "ACL_ENTRY_SEQ", sequenceName = "ACL_ENTRY_SEQ")
    private long id;
    //
    @ManyToOne(targetEntity = AclObjectIdentity.class)
    @JoinColumn(name = "acl_object_identity", nullable = false)
    private AclObjectIdentity aclObject;
    //
    @Column(name = "ace_order", nullable = false)
    private Integer aceOrder;
    //
    @ManyToOne(targetEntity = AclSid.class)
    @JoinColumn(name = "sid", nullable = false)
    private AclSid aclSid;
    //
    @Column(name = "mask", nullable = false)
    private Integer mask;
    //
    @Column(name = "granting", nullable = false)
    private boolean granting = true;
    //
    @Column(name = "audit_success", nullable = false)
    private boolean auditSuccess = true;
    //
    @Column(name = "audit_failure", nullable = false)
    private boolean auditFailure = true;

    //<editor-fold defaultstate="collapsed" desc="Constructor methods">
    public AclEntry() {
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
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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
     */
    public Integer getAceOrder() {
        return aceOrder;
    }

    /**
     * @param aceOrder the aceOrder to set
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
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @param mask the mask to set
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

    /**
     * @return the granting
     */
    public boolean isGranting() {
        return granting;
    }

    /**
     * @param granting the granting to set
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

    /*
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
