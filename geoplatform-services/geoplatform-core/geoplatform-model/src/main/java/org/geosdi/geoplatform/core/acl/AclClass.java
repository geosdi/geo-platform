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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

/**
 * The <tt>AclClass</tt> domain class contains entries for the names of each
 * application domain class that has associated permissions.
 * 
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Entity
@Table(name = "acl_class")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "class")
public class AclClass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACL_CLASS_SEQ")
    @SequenceGenerator(name = "ACL_CLASS_SEQ", sequenceName = "ACL_CLASS_SEQ")
    private Long id;
    /**
     * Fully qualified name of a class (package.className) of the secure domain object.
     */
    @Column(name = "clazz", unique = true, nullable = false, length = 500)
    @Index(name = "ACL_CLASS_INDEX")
    private String clazz;

    //<editor-fold defaultstate="collapsed" desc="Constructor methods">
    public AclClass() {
    }

    /**
     * 
     * @param clazz
     * 
     * @see #clazz
     */
    public AclClass(String clazz) {
        this.clazz = clazz;
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
     * @return the clazz
     * 
     * @see #clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     * 
     * @see #clazz
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    //</editor-fold>

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AclClass {");
        str.append("id=").append(id);
        str.append(", clazz=").append(clazz).append('}');
        return str.toString();
    }
}
