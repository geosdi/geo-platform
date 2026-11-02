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
 * <p>Classe Java per MultiPointType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MultiPointType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}pointMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}pointMembers" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiPointType", propOrder = {
    "pointMember",
    "pointMembers"
})
public class MultiPointType
    extends AbstractGeometricAggregateType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * This property element either references a Point via the XLink-attributes or contains the Point element.
     * 
     */
    protected List<PointPropertyType> pointMember;
    /**
     * This property element contains a list of points. The order of the elements is significant and shall be preserved when processing the array.
     * 
     */
    protected PointArrayPropertyType pointMembers;

    /**
     * This property element either references a Point via the XLink-attributes or contains the Point element.
     * 
     * Gets the value of the pointMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pointMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPointMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the pointMember property.
     */
    public List<PointPropertyType> getPointMember() {
        if (pointMember == null) {
            pointMember = new ArrayList<>();
        }
        return this.pointMember;
    }

    public boolean isSetPointMember() {
        return ((this.pointMember!= null)&&(!this.pointMember.isEmpty()));
    }

    public void unsetPointMember() {
        this.pointMember = null;
    }

    /**
     * This property element contains a list of points. The order of the elements is significant and shall be preserved when processing the array.
     * 
     * @return
     *     possible object is
     *     {@link PointArrayPropertyType }
     *     
     */
    public PointArrayPropertyType getPointMembers() {
        return pointMembers;
    }

    /**
     * Imposta il valore della proprietà pointMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link PointArrayPropertyType }
     *     
     * @see #getPointMembers()
     */
    public void setPointMembers(PointArrayPropertyType value) {
        this.pointMembers = value;
    }

    public boolean isSetPointMembers() {
        return (this.pointMembers!= null);
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
            List<PointPropertyType> thePointMember;
            thePointMember = (this.isSetPointMember()?this.getPointMember():null);
            strategy.appendField(locator, this, "pointMember", buffer, thePointMember, this.isSetPointMember());
        }
        {
            PointArrayPropertyType thePointMembers;
            thePointMembers = this.getPointMembers();
            strategy.appendField(locator, this, "pointMembers", buffer, thePointMembers, this.isSetPointMembers());
        }
        return buffer;
    }

    public void setPointMember(List<PointPropertyType> value) {
        this.pointMember = null;
        if (value!= null) {
            List<PointPropertyType> draftl = this.getPointMember();
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
        final MultiPointType that = ((MultiPointType) object);
        {
            List<PointPropertyType> lhsPointMember;
            lhsPointMember = (this.isSetPointMember()?this.getPointMember():null);
            List<PointPropertyType> rhsPointMember;
            rhsPointMember = (that.isSetPointMember()?that.getPointMember():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointMember", lhsPointMember), LocatorUtils.property(thatLocator, "pointMember", rhsPointMember), lhsPointMember, rhsPointMember, this.isSetPointMember(), that.isSetPointMember())) {
                return false;
            }
        }
        {
            PointArrayPropertyType lhsPointMembers;
            lhsPointMembers = this.getPointMembers();
            PointArrayPropertyType rhsPointMembers;
            rhsPointMembers = that.getPointMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointMembers", lhsPointMembers), LocatorUtils.property(thatLocator, "pointMembers", rhsPointMembers), lhsPointMembers, rhsPointMembers, this.isSetPointMembers(), that.isSetPointMembers())) {
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
            List<PointPropertyType> thePointMember;
            thePointMember = (this.isSetPointMember()?this.getPointMember():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointMember", thePointMember), currentHashCode, thePointMember, this.isSetPointMember());
        }
        {
            PointArrayPropertyType thePointMembers;
            thePointMembers = this.getPointMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointMembers", thePointMembers), currentHashCode, thePointMembers, this.isSetPointMembers());
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
        if (draftCopy instanceof MultiPointType) {
            final MultiPointType copy = ((MultiPointType) draftCopy);
            {
                java.lang.Boolean pointMemberShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPointMember());
                if (pointMemberShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<PointPropertyType> sourcePointMember;
                    sourcePointMember = (this.isSetPointMember()?this.getPointMember():null);
                    @SuppressWarnings("unchecked")
                    List<PointPropertyType> copyPointMember = ((List<PointPropertyType> ) strategy.copy(LocatorUtils.property(locator, "pointMember", sourcePointMember), sourcePointMember, this.isSetPointMember()));
                    copy.setPointMember(copyPointMember);
                } else {
                    if (pointMemberShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetPointMember();
                    }
                }
            }
            {
                java.lang.Boolean pointMembersShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPointMembers());
                if (pointMembersShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    PointArrayPropertyType sourcePointMembers;
                    sourcePointMembers = this.getPointMembers();
                    PointArrayPropertyType copyPointMembers = ((PointArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "pointMembers", sourcePointMembers), sourcePointMembers, this.isSetPointMembers()));
                    copy.setPointMembers(copyPointMembers);
                } else {
                    if (pointMembersShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.pointMembers = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new MultiPointType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MultiPointType) {
            final MultiPointType target = this;
            final MultiPointType leftObject = ((MultiPointType) left);
            final MultiPointType rightObject = ((MultiPointType) right);
            {
                java.lang.Boolean pointMemberShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPointMember(), rightObject.isSetPointMember());
                if (pointMemberShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<PointPropertyType> lhsPointMember;
                    lhsPointMember = (leftObject.isSetPointMember()?leftObject.getPointMember():null);
                    List<PointPropertyType> rhsPointMember;
                    rhsPointMember = (rightObject.isSetPointMember()?rightObject.getPointMember():null);
                    List<PointPropertyType> mergedPointMember = ((List<PointPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "pointMember", lhsPointMember), LocatorUtils.property(rightLocator, "pointMember", rhsPointMember), lhsPointMember, rhsPointMember, leftObject.isSetPointMember(), rightObject.isSetPointMember()));
                    target.setPointMember(mergedPointMember);
                } else {
                    if (pointMemberShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetPointMember();
                    }
                }
            }
            {
                java.lang.Boolean pointMembersShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPointMembers(), rightObject.isSetPointMembers());
                if (pointMembersShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    PointArrayPropertyType lhsPointMembers;
                    lhsPointMembers = leftObject.getPointMembers();
                    PointArrayPropertyType rhsPointMembers;
                    rhsPointMembers = rightObject.getPointMembers();
                    PointArrayPropertyType mergedPointMembers = ((PointArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "pointMembers", lhsPointMembers), LocatorUtils.property(rightLocator, "pointMembers", rhsPointMembers), lhsPointMembers, rhsPointMembers, leftObject.isSetPointMembers(), rightObject.isSetPointMembers()));
                    target.setPointMembers(mergedPointMembers);
                } else {
                    if (pointMembersShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.pointMembers = null;
                    }
                }
            }
        }
    }

}
