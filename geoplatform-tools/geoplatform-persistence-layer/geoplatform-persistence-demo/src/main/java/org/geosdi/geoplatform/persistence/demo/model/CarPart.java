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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.persistence.demo.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Entity
@Table(indexes = {
    @Index(name = "ROLEPROPS_PROPVALUE_INDEX", columnList = "partName")},
        name = "CAR_PARTS", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"partname", "carplate"})})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
        region = "carPartCacheRegion")
public class CarPart implements Serializable {

    private static final long serialVersionUID = 2061845368648914687L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "CAR_PARTS_SEQ")
    @SequenceGenerator(name = "CAR_PARTS_SEQ", sequenceName = "CAR_PARTS_SEQ")
    private Long id;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "carplate", referencedColumnName = "plate",
            nullable = false)
    private Car car;
    //
    @NaturalId
    @Column(name = "partname", columnDefinition = "VARCHAR(2048)")
    private String partName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.car != null ? this.car.hashCode() : 0);
        hash = 97 * hash + (this.partName != null ? this.partName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarPart other = (CarPart) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.car != other.car && (this.car == null || !this.car.equals(
                other.car))) {
            return false;
        }
        if ((this.partName == null) ? (other.partName != null)
                : !this.partName.equals(other.partName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarPart{" + "id=" + id + ", car=" + car + ", partName=" + partName + '}';
    }

}
