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
package org.geosdi.geoplatform.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.springframework.security.acls.domain.BasePermission;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
//@XmlRootElement(name = "GPAccountProject")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gp_account_project", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"account_id", "project_id"})})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "account_project")
public class GPAccountProject implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5848638543797948563L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GP_ACCOUNT_PROJECT_SEQ")
    @SequenceGenerator(name = "GP_ACCOUNT_PROJECT_SEQ",
            sequenceName = "GP_ACCOUNT_PROJECT_SEQ")
    private Long id;
    //
    //@XmlAnyElement(lax = true)
    @XmlElement(name = "account")
//    @XmlElementRefs(value = {
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "ACCOUNT_ID_INDEX")
    private GPAccount account;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "PROJECT_ID_INDEX")
    private GPProject project;
    //
    @Column(name = "permission_mask", nullable = false)
    private int permissionMask = BasePermission.ADMINISTRATION.getMask();
    //
    @Column(name = "default_project")
    private boolean defaultProject;
    // TODO REF Move to ProjectOption entity
    @Column(name = "base_layer", nullable = false)
    private String baseLayer = "GOOGLE_SATELLITE";

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
     * @return the account
     */
    public GPAccount getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(GPAccount account) {
        this.account = account;
    }

    /**
     * @return the project
     */
    public GPProject getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(GPProject project) {
        this.project = project;
    }

    /**
     * @param account the account to set
     * @param project the project to set
     */
    public void setAccountAndProject(GPAccount account, GPProject project) {
        this.account = account;
        this.project = project;
    }

    /**
     * @return the permissionMask
     */
    public int getPermissionMask() {
        return permissionMask;
    }

    /**
     * @param permissionMask the permissionMask to set
     */
    public void setPermissionMask(int permissionMask) {
        this.permissionMask = permissionMask;
    }

    /**
     * @return the defaultProject
     */
    public boolean isDefaultProject() {
        return defaultProject;
    }

    /**
     * @param defaultProject the defaultProject to set
     */
    public void setDefaultProject(boolean defaultProject) {
        this.defaultProject = defaultProject;
    }

    /**
     *
     * @return the baseLayer
     */
    public String getBaseLayer() {
        return baseLayer;
    }

    /**
     *
     * @param baseLayer the baseLayer to set
     */
    public void setBaseLayer(String baseLayer) {
        this.baseLayer = baseLayer;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(
                " {");
        if (account != null) {
            str.append("(id=").append(account.getId()).append(")");
        } else {
            str.append(" account=NULL");
        }
        if (project != null) {
            str.append(", project.name=").append(project.getName());
            str.append("(id=").append(project.getId()).append(")");
        } else {
            str.append(", project=NULL");
        }
        str.append(", permission=").append(permissionMask);
        str.append(", defaultProject=").append(defaultProject);
        str.append(", baseLayer=").append(baseLayer);
        return str.append("}").toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPAccountProject other = (GPAccountProject) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(
                other.id)));
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        result = (getAccount() != null ? getAccount().hashCode() : 0);
        result = 71 * result + (project != null ? project.hashCode() : 0);
        return result;
    }
}
