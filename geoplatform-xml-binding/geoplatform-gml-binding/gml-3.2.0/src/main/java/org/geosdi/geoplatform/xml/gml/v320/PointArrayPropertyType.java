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
//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * gml:PointArrayPropertyType is a container for an array of points. The elements are always contained inline in the array property, referencing geometry elements or arrays of geometry elements via XLinks is not supported.
 * 
 * <p>Classe Java per PointArrayPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PointArrayPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence maxOccurs="unbounded" minOccurs="0">
 *         <element ref="{http://www.opengis.net/gml}Point"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}OwnershipAttributeGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointArrayPropertyType", propOrder = {
    "point"
})
public class PointArrayPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "Point")
    protected List<PointType> point;
    @XmlAttribute(name = "owns")
    protected java.lang.Boolean owns;

    /**
     * Gets the value of the point property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the point property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointType }
     * </p>
     * 
     * 
     * @return
     *     The value of the point property.
     */
    public List<PointType> getPoint() {
        if (point == null) {
            point = new ArrayList<>();
        }
        return this.point;
    }

    public boolean isSetPoint() {
        return ((this.point!= null)&&(!this.point.isEmpty()));
    }

    public void unsetPoint() {
        this.point = null;
    }

    /**
     * Recupera il valore della proprietà owns.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isOwns() {
        if (owns == null) {
            return false;
        } else {
            return owns;
        }
    }

    /**
     * Imposta il valore della proprietà owns.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setOwns(boolean value) {
        this.owns = value;
    }

    public boolean isSetOwns() {
        return (this.owns!= null);
    }

    public void unsetOwns() {
        this.owns = null;
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            List<PointType> thePoint;
            thePoint = (this.isSetPoint()?this.getPoint():null);
            strategy.appendField(locator, this, "point", buffer, thePoint, this.isSetPoint());
        }
        {
            boolean theOwns;
            theOwns = (this.isSetOwns()?this.isOwns():false);
            strategy.appendField(locator, this, "owns", buffer, theOwns, this.isSetOwns());
        }
        return buffer;
    }

    public void setPoint(List<PointType> value) {
        this.point = null;
        if (value!= null) {
            List<PointType> draftl = this.getPoint();
            draftl.addAll(value);
        }
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PointArrayPropertyType that = ((PointArrayPropertyType) object);
        {
            List<PointType> lhsPoint;
            lhsPoint = (this.isSetPoint()?this.getPoint():null);
            List<PointType> rhsPoint;
            rhsPoint = (that.isSetPoint()?that.getPoint():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "point", lhsPoint), LocatorUtils.property(thatLocator, "point", rhsPoint), lhsPoint, rhsPoint, this.isSetPoint(), that.isSetPoint())) {
                return false;
            }
        }
        {
            boolean lhsOwns;
            lhsOwns = (this.isSetOwns()?this.isOwns():false);
            boolean rhsOwns;
            rhsOwns = (that.isSetOwns()?that.isOwns():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "owns", lhsOwns), LocatorUtils.property(thatLocator, "owns", rhsOwns), lhsOwns, rhsOwns, this.isSetOwns(), that.isSetOwns())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<PointType> thePoint;
            thePoint = (this.isSetPoint()?this.getPoint():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "point", thePoint), currentHashCode, thePoint, this.isSetPoint());
        }
        {
            boolean theOwns;
            theOwns = (this.isSetOwns()?this.isOwns():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "owns", theOwns), currentHashCode, theOwns, this.isSetOwns());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof PointArrayPropertyType) {
            final PointArrayPropertyType copy = ((PointArrayPropertyType) draftCopy);
            {
                java.lang.Boolean pointShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPoint());
                if (pointShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<PointType> sourcePoint;
                    sourcePoint = (this.isSetPoint()?this.getPoint():null);
                    @SuppressWarnings("unchecked")
                    List<PointType> copyPoint = ((List<PointType> ) strategy.copy(LocatorUtils.property(locator, "point", sourcePoint), sourcePoint, this.isSetPoint()));
                    copy.setPoint(copyPoint);
                } else {
                    if (pointShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetPoint();
                    }
                }
            }
            {
                java.lang.Boolean ownsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOwns());
                if (ownsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    boolean sourceOwns;
                    sourceOwns = (this.isSetOwns()?this.isOwns():false);
                    boolean copyOwns = strategy.copy(LocatorUtils.property(locator, "owns", sourceOwns), sourceOwns, this.isSetOwns());
                    copy.setOwns(copyOwns);
                } else {
                    if (ownsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetOwns();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new PointArrayPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof PointArrayPropertyType) {
            final PointArrayPropertyType target = this;
            final PointArrayPropertyType leftObject = ((PointArrayPropertyType) left);
            final PointArrayPropertyType rightObject = ((PointArrayPropertyType) right);
            {
                java.lang.Boolean pointShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPoint(), rightObject.isSetPoint());
                if (pointShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<PointType> lhsPoint;
                    lhsPoint = (leftObject.isSetPoint()?leftObject.getPoint():null);
                    List<PointType> rhsPoint;
                    rhsPoint = (rightObject.isSetPoint()?rightObject.getPoint():null);
                    List<PointType> mergedPoint = ((List<PointType> ) strategy.merge(LocatorUtils.property(leftLocator, "point", lhsPoint), LocatorUtils.property(rightLocator, "point", rhsPoint), lhsPoint, rhsPoint, leftObject.isSetPoint(), rightObject.isSetPoint()));
                    target.setPoint(mergedPoint);
                } else {
                    if (pointShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetPoint();
                    }
                }
            }
            {
                java.lang.Boolean ownsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOwns(), rightObject.isSetOwns());
                if (ownsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    boolean lhsOwns;
                    lhsOwns = (leftObject.isSetOwns()?leftObject.isOwns():false);
                    boolean rhsOwns;
                    rhsOwns = (rightObject.isSetOwns()?rightObject.isOwns():false);
                    boolean mergedOwns = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "owns", lhsOwns), LocatorUtils.property(rightLocator, "owns", rhsOwns), lhsOwns, rhsOwns, leftObject.isSetOwns(), rightObject.isSetOwns()));
                    target.setOwns(mergedOwns);
                } else {
                    if (ownsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetOwns();
                    }
                }
            }
        }
    }

}
