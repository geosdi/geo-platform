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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * The <tt>AclClass</tt> domain class contains entries for the names of each
 * application domain class that has associated permissions.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Entity
@Table(name = "acl_class", indexes = {
        @Index(columnList = "clazz", name = "ACL_CLASS_INDEX")
})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "class")
@Getter
@Setter
@ToString
public class AclClass implements Serializable {

    private static final long serialVersionUID = -587659899652273094L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gp_acl_class_generator")
    @SequenceGenerator(name = "gp_acl_class_generator", sequenceName = "ACL_CLASS_SEQ")
    private Long id;
    /**
     * Fully qualified name of a class (package.className) of the secure domain object.
     */
    @Column(name = "clazz", unique = true, nullable = false, length = 500)
    private String clazz;

    //<editor-fold defaultstate="collapsed" desc="Constructor methods">
    public AclClass() {
    }

    /**
     * @param clazz
     * @see #clazz
     */
    public AclClass(String clazz) {
        this.clazz = clazz;
    }
    //</editor-fold>
}