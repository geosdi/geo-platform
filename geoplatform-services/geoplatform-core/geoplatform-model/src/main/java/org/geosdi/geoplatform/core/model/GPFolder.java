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

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author Francesco Izzi - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
//@XmlRootElement(name = "Folder")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Folder")
@Table(name = "gp_folder")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "folder")
public class GPFolder implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5826659681483678835L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_FOLDER_SEQ")
    @SequenceGenerator(name = "GP_FOLDER_SEQ", sequenceName = "GP_FOLDER_SEQ")
    private Long id;
    //
    @Column(nullable = false)
    @Index(name = "FOLDER_NAME_INDEX")
    private String name;
    //
    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPFolder parent;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPProject project;
    //
    @Column(name = "number_of_descendants")
    private int numberOfDescendants = 0;
    //
    @Column
    private int position = -1;
    //    
    @Column
    private boolean checked;
    //
    @Column
    private boolean shared;
    //
    @Column
    private boolean expanded;

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

    /**
     * @return the parent
     */
    public GPFolder getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(GPFolder parent) {
        this.parent = parent;
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
     * @return the numberOfDescendants
     */
    public int getNumberOfDescendants() {
        return numberOfDescendants;
    }

    /**
     * @param numberOfDescendants the numberOfDescendants to set
     */
    public void setNumberOfDescendants(int numberOfDescendants) {
        this.numberOfDescendants = numberOfDescendants;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the shared
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * @param shared the shared to set
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * @return the expanded
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * @param expanded the expanded to set
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("id=").append(id);
        str.append(", name=").append(name);
        if (parent != null) {
            str.append(", parent.name=").append(parent.getName());
            str.append("(id=").append(parent.getId()).append(")");
        } else {
            str.append(", parent=NULL (this is a root folder)");
        }
        if (project != null) {
            str.append(", project.name=").append(project.getName());
            str.append("(id=").append(project.getId()).append(")");
        } else {
            str.append(", project=NULL");
        }
        str.append(", numberOfDescendants=").append(numberOfDescendants);
        str.append(", position=").append(position);
        str.append(", checked=").append(checked);
        str.append(", shared=").append(shared);
        str.append(", expanded=").append(expanded);
        str.append(", level=").append(getLevel());
        return str.append("}").toString();
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
        final GPFolder other = (GPFolder) obj;
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
        int hash = 13;
        hash = 77 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}
