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

import com.vividsolutions.jts.geom.Geometry;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@XmlRootElement(name = "VectorLayer")
@Entity(name = "VectorLayer")
@Table(name = "gp_vector_layer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "vector_layer")
public class GPVectorLayer extends GPLayer {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3309979650712821228L;
    //
    @Type(type = "org.hibernatespatial.GeometryUserType")
    @Column(nullable = true)
    private Geometry geometry;
    //
    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPFolder folder;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "VECTOR_PROJECT_ID_INDEX")
    private GPProject project;

    /**
     * @return the geometry
     */
    @XmlTransient
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @param geometry
     *            the geometry to set
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return the folder
     */
    @Override
    public GPFolder getFolder() {
        return folder;
    }

    /**
     * @param folder
     *            the folder to set
     */
    @Override
    public void setFolder(GPFolder folder) {
        this.folder = folder;
    }

    /**
     * @return the project
     */
    @Override
    public GPProject getProject() {
        return project;
    }

    /**
     * @param project
     *            the project to set
     */
    @Override
    public void setProject(GPProject project) {
        this.project = project;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append(super.toString());
        str.append(", geometry=").append(geometry);
        return str.append("}").toString();
    }
}
