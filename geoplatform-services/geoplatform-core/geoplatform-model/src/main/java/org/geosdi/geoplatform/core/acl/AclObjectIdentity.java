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
 * The <tt>AclObjectIdentity</tt> domain class contains entries representing
 * individual domain class instances (OIDs).
 * It has a field for the instance id (objectId) and domain class (aclClass)
 * that uniquely identify the instance. In addition there are optional nullable
 * fields for the parent OID (parentAclObject) and owner (aclSid). There's also
 * a flag (inheriting) to indicate whether ACL entries can inherit from a parent ACL.
 * 
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Entity
@Table(name = "acl_object_identity",
       uniqueConstraints =
@UniqueConstraint(columnNames = {"object_id_class", "object_id_identity"}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "object_identity")
// TODO: implements Acl? extends AclImpl?
public class AclObjectIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACL_OBJECT_IDENTITY_SEQ")
    @SequenceGenerator(name = "ACL_OBJECT_IDENTITY_SEQ", sequenceName = "ACL_OBJECT_IDENTITY_SEQ")
    private Long id;
    //
    @ManyToOne
    @JoinColumn(name = "object_id_class", nullable = false)
    @Index(name = "ACL_OBJECT_IDENTITY_CLASS_INDEX")
    private AclClass aclClass;
    //
    @Column(name = "object_id_identity", nullable = false)
    @Index(name = "ACL_OBJECT_IDENTITY_ID_INDEX")
    private Long objectId;
    //
    @ManyToOne
    @JoinColumn(name = "parent_object")
    private AclObjectIdentity parentAclObject;
    //
    @ManyToOne
    @JoinColumn(name = "owner_sid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AclSid aclSid;
    //
    @Column(name = "entries_inheriting", nullable = false)
    private boolean inheriting = false;

    //<editor-fold defaultstate="collapsed" desc="Contructor Methods">
    public AclObjectIdentity() {
    }

    /**
     * Constructor that doesn't handle inheritance
     * 
     * @param aclClass
     * @param objectId
     * @param aclSid
     */
    public AclObjectIdentity(AclClass aclClass, Long objectId, AclSid aclSid) {
        this.aclClass = aclClass;
        this.objectId = objectId;
        this.aclSid = aclSid;
    }

    /**
     * Constructor that handle inheritance
     * 
     * @param aclClass
     * @param objectId
     * @param aclSid
     * @param parentAclObject 
     */
    public AclObjectIdentity(AclClass aclClass, Long objectId, AclSid aclSid,
                             AclObjectIdentity parentAclObject) {
        this.aclClass = aclClass;
        this.objectId = objectId;
        this.aclSid = aclSid;
        this.inheriting = true;
        this.parentAclObject = parentAclObject;
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
     * @return the aclClass
     */
    public AclClass getAclClass() {
        return aclClass;
    }

    /**
     * @param aclClass the aclClass to set
     */
    public void setAclClass(AclClass aclClass) {
        this.aclClass = aclClass;
    }

    /**
     * @return the objectId
     */
    public Long getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the parentAclObject
     */
    public AclObjectIdentity getParentAclObject() {
        return parentAclObject;
    }

    /**
     * @param parentAclObject the parentAclObject to set
     */
    public void setParentAclObject(AclObjectIdentity parentAclObject) {
        this.parentAclObject = parentAclObject;
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
     * @return the inheriting
     */
    public boolean isInheriting() {
        return inheriting;
    }

    /**
     * @param inheriting the inheriting to set
     */
    public void setInheriting(boolean inheriting) {
        this.inheriting = inheriting;
    }
    //</editor-fold>

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AclObjectIdentity {");
        str.append("id=").append(id);
        str.append(", aclClass.id=").append(
                aclClass != null ? aclClass.getId() : "NULL");
        str.append(", objectId=").append(objectId);
        str.append(", parentAclObject.id=").append(
                parentAclObject != null ? parentAclObject.getId() : "NULL");
        str.append(", aclSid.id=").append(
                aclSid != null ? aclSid.getId() : "NULL");
        str.append(", inheriting=").append(inheriting).append('}');
        return str.toString();
    }
}