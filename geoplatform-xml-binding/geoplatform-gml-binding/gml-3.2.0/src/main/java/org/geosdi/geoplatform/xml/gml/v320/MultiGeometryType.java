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
 * <p>Classe Java per MultiGeometryType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MultiGeometryType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}geometryMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}geometryMembers" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiGeometryType", propOrder = {
    "geometryMember",
    "geometryMembers"
})
public class MultiGeometryType
    extends AbstractGeometricAggregateType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * This property element either references a geometry element via the XLink-attributes or contains the geometry element.
     * 
     */
    protected List<GeometryPropertyType> geometryMember;
    /**
     * This property element contains a list of geometry elements. The order of the elements is significant and shall be preserved when processing the array.
     * 
     */
    protected GeometryArrayPropertyType geometryMembers;

    /**
     * This property element either references a geometry element via the XLink-attributes or contains the geometry element.
     * 
     * Gets the value of the geometryMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the geometryMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getGeometryMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeometryPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the geometryMember property.
     */
    public List<GeometryPropertyType> getGeometryMember() {
        if (geometryMember == null) {
            geometryMember = new ArrayList<>();
        }
        return this.geometryMember;
    }

    public boolean isSetGeometryMember() {
        return ((this.geometryMember!= null)&&(!this.geometryMember.isEmpty()));
    }

    public void unsetGeometryMember() {
        this.geometryMember = null;
    }

    /**
     * This property element contains a list of geometry elements. The order of the elements is significant and shall be preserved when processing the array.
     * 
     * @return
     *     possible object is
     *     {@link GeometryArrayPropertyType }
     *     
     */
    public GeometryArrayPropertyType getGeometryMembers() {
        return geometryMembers;
    }

    /**
     * Imposta il valore della proprietà geometryMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryArrayPropertyType }
     *     
     * @see #getGeometryMembers()
     */
    public void setGeometryMembers(GeometryArrayPropertyType value) {
        this.geometryMembers = value;
    }

    public boolean isSetGeometryMembers() {
        return (this.geometryMembers!= null);
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
        super.appendFields(locator, buffer, strategy);
        {
            List<GeometryPropertyType> theGeometryMember;
            theGeometryMember = (this.isSetGeometryMember()?this.getGeometryMember():null);
            strategy.appendField(locator, this, "geometryMember", buffer, theGeometryMember, this.isSetGeometryMember());
        }
        {
            GeometryArrayPropertyType theGeometryMembers;
            theGeometryMembers = this.getGeometryMembers();
            strategy.appendField(locator, this, "geometryMembers", buffer, theGeometryMembers, this.isSetGeometryMembers());
        }
        return buffer;
    }

    public void setGeometryMember(List<GeometryPropertyType> value) {
        this.geometryMember = null;
        if (value!= null) {
            List<GeometryPropertyType> draftl = this.getGeometryMember();
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final MultiGeometryType that = ((MultiGeometryType) object);
        {
            List<GeometryPropertyType> lhsGeometryMember;
            lhsGeometryMember = (this.isSetGeometryMember()?this.getGeometryMember():null);
            List<GeometryPropertyType> rhsGeometryMember;
            rhsGeometryMember = (that.isSetGeometryMember()?that.getGeometryMember():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "geometryMember", lhsGeometryMember), LocatorUtils.property(thatLocator, "geometryMember", rhsGeometryMember), lhsGeometryMember, rhsGeometryMember, this.isSetGeometryMember(), that.isSetGeometryMember())) {
                return false;
            }
        }
        {
            GeometryArrayPropertyType lhsGeometryMembers;
            lhsGeometryMembers = this.getGeometryMembers();
            GeometryArrayPropertyType rhsGeometryMembers;
            rhsGeometryMembers = that.getGeometryMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "geometryMembers", lhsGeometryMembers), LocatorUtils.property(thatLocator, "geometryMembers", rhsGeometryMembers), lhsGeometryMembers, rhsGeometryMembers, this.isSetGeometryMembers(), that.isSetGeometryMembers())) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<GeometryPropertyType> theGeometryMember;
            theGeometryMember = (this.isSetGeometryMember()?this.getGeometryMember():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geometryMember", theGeometryMember), currentHashCode, theGeometryMember, this.isSetGeometryMember());
        }
        {
            GeometryArrayPropertyType theGeometryMembers;
            theGeometryMembers = this.getGeometryMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geometryMembers", theGeometryMembers), currentHashCode, theGeometryMembers, this.isSetGeometryMembers());
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof MultiGeometryType) {
            final MultiGeometryType copy = ((MultiGeometryType) draftCopy);
            {
                java.lang.Boolean geometryMemberShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGeometryMember());
                if (geometryMemberShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<GeometryPropertyType> sourceGeometryMember;
                    sourceGeometryMember = (this.isSetGeometryMember()?this.getGeometryMember():null);
                    @SuppressWarnings("unchecked")
                    List<GeometryPropertyType> copyGeometryMember = ((List<GeometryPropertyType> ) strategy.copy(LocatorUtils.property(locator, "geometryMember", sourceGeometryMember), sourceGeometryMember, this.isSetGeometryMember()));
                    copy.setGeometryMember(copyGeometryMember);
                } else {
                    if (geometryMemberShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetGeometryMember();
                    }
                }
            }
            {
                java.lang.Boolean geometryMembersShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGeometryMembers());
                if (geometryMembersShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    GeometryArrayPropertyType sourceGeometryMembers;
                    sourceGeometryMembers = this.getGeometryMembers();
                    GeometryArrayPropertyType copyGeometryMembers = ((GeometryArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "geometryMembers", sourceGeometryMembers), sourceGeometryMembers, this.isSetGeometryMembers()));
                    copy.setGeometryMembers(copyGeometryMembers);
                } else {
                    if (geometryMembersShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.geometryMembers = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new MultiGeometryType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MultiGeometryType) {
            final MultiGeometryType target = this;
            final MultiGeometryType leftObject = ((MultiGeometryType) left);
            final MultiGeometryType rightObject = ((MultiGeometryType) right);
            {
                java.lang.Boolean geometryMemberShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGeometryMember(), rightObject.isSetGeometryMember());
                if (geometryMemberShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<GeometryPropertyType> lhsGeometryMember;
                    lhsGeometryMember = (leftObject.isSetGeometryMember()?leftObject.getGeometryMember():null);
                    List<GeometryPropertyType> rhsGeometryMember;
                    rhsGeometryMember = (rightObject.isSetGeometryMember()?rightObject.getGeometryMember():null);
                    List<GeometryPropertyType> mergedGeometryMember = ((List<GeometryPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "geometryMember", lhsGeometryMember), LocatorUtils.property(rightLocator, "geometryMember", rhsGeometryMember), lhsGeometryMember, rhsGeometryMember, leftObject.isSetGeometryMember(), rightObject.isSetGeometryMember()));
                    target.setGeometryMember(mergedGeometryMember);
                } else {
                    if (geometryMemberShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetGeometryMember();
                    }
                }
            }
            {
                java.lang.Boolean geometryMembersShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGeometryMembers(), rightObject.isSetGeometryMembers());
                if (geometryMembersShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    GeometryArrayPropertyType lhsGeometryMembers;
                    lhsGeometryMembers = leftObject.getGeometryMembers();
                    GeometryArrayPropertyType rhsGeometryMembers;
                    rhsGeometryMembers = rightObject.getGeometryMembers();
                    GeometryArrayPropertyType mergedGeometryMembers = ((GeometryArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "geometryMembers", lhsGeometryMembers), LocatorUtils.property(rightLocator, "geometryMembers", rhsGeometryMembers), lhsGeometryMembers, rhsGeometryMembers, leftObject.isSetGeometryMembers(), rightObject.isSetGeometryMembers()));
                    target.setGeometryMembers(mergedGeometryMembers);
                } else {
                    if (geometryMembersShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.geometryMembers = null;
                    }
                }
            }
        }
    }

}
