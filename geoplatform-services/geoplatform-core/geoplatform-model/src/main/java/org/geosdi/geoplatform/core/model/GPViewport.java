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

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
//@XmlRootElement(name = "Viewport")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Viewport")
@Table(name = "gp_viewport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "viewport")
public class GPViewport implements Serializable {

    private static final long serialVersionUID = 8911979056279744318L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_VIEWPORT_SEQ")
    @SequenceGenerator(name = "GP_VIEWPORT_SEQ", sequenceName = "GP_VIEWPORT_SEQ")
    private Long id;
    //
    @Column(nullable = false)
    @Index(name = "VIEWPORT_NAME_INDEX")
    private String name;
    //
    @Column
    private String description;
    //  
    @Column(name = "zoom_level", nullable = false)
    private double zoomLevel;
    //  
    @Column(name = "is_default")
    private boolean isDefault = false;
    //
    @Embedded
    private GPBBox bbox;
    //
    @ManyToOne(optional = true)
    @JoinColumn(name = "account_project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "ACCOUNT_PROJECT_ID_INDEX")
    private GPAccountProject accountProject;

    public GPViewport() {
    }

    public GPViewport(String name, String description, double zoomLevel, GPBBox bbox,
            boolean isDefault) {
        this.name = name;
        this.description = description;
        this.zoomLevel = zoomLevel;
        this.bbox = bbox;
        this.isDefault = isDefault;
    }

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the bbox
     */
    public GPBBox getBbox() {
        return bbox;
    }

    /**
     * @param bbox the bbox to set
     */
    public void setBbox(GPBBox bbox) {
        this.bbox = bbox;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public GPAccountProject getAccountProject() {
        return accountProject;
    }

    public void setAccountProject(GPAccountProject accountProject) {
        this.accountProject = accountProject;
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(double zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    @Override
    public String toString() {
        return "GPViewport{" + "id=" + id
                + ", name=" + name
                + ", description=" + description
                + ", zoomLevel=" + zoomLevel
                + ", isDefault=" + isDefault
                + ", bbox=" + bbox
                + ", accountProject=" + accountProject + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
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
        final GPViewport other = (GPViewport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
