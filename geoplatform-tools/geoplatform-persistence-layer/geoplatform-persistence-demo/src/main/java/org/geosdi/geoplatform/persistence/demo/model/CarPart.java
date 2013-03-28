/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.persistence.demo.model;

import java.io.Serializable;
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
import org.hibernate.annotations.Index;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Entity
@Table(name = "CAR_PARTS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"partname", "carplate"})})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "car_parts")
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
    @Index(name = "ROLEPROPS_ROLENAME_INDEX")
    private Car car;
    //
    @NaturalId
    @Column(name = "partname", columnDefinition = "VARCHAR(2048)")
    @Index(name = "ROLEPROPS_PROPVALUE_INDEX")
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
