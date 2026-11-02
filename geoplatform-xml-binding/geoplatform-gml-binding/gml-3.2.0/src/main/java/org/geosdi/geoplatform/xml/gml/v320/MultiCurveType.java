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
 * <p>Classe Java per MultiCurveType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MultiCurveType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}curveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}curveMembers" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiCurveType", propOrder = {
    "curveMember",
    "curveMembers"
})
public class MultiCurveType
    extends AbstractGeometricAggregateType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<CurvePropertyType> curveMember;
    /**
     * This property element contains a list of curves. The order of the elements is significant and shall be preserved when processing the array.
     * 
     */
    protected CurveArrayPropertyType curveMembers;

    /**
     * Gets the value of the curveMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the curveMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCurveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CurvePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the curveMember property.
     */
    public List<CurvePropertyType> getCurveMember() {
        if (curveMember == null) {
            curveMember = new ArrayList<>();
        }
        return this.curveMember;
    }

    public boolean isSetCurveMember() {
        return ((this.curveMember!= null)&&(!this.curveMember.isEmpty()));
    }

    public void unsetCurveMember() {
        this.curveMember = null;
    }

    /**
     * This property element contains a list of curves. The order of the elements is significant and shall be preserved when processing the array.
     * 
     * @return
     *     possible object is
     *     {@link CurveArrayPropertyType }
     *     
     */
    public CurveArrayPropertyType getCurveMembers() {
        return curveMembers;
    }

    /**
     * Imposta il valore della proprietà curveMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveArrayPropertyType }
     *     
     * @see #getCurveMembers()
     */
    public void setCurveMembers(CurveArrayPropertyType value) {
        this.curveMembers = value;
    }

    public boolean isSetCurveMembers() {
        return (this.curveMembers!= null);
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
            List<CurvePropertyType> theCurveMember;
            theCurveMember = (this.isSetCurveMember()?this.getCurveMember():null);
            strategy.appendField(locator, this, "curveMember", buffer, theCurveMember, this.isSetCurveMember());
        }
        {
            CurveArrayPropertyType theCurveMembers;
            theCurveMembers = this.getCurveMembers();
            strategy.appendField(locator, this, "curveMembers", buffer, theCurveMembers, this.isSetCurveMembers());
        }
        return buffer;
    }

    public void setCurveMember(List<CurvePropertyType> value) {
        this.curveMember = null;
        if (value!= null) {
            List<CurvePropertyType> draftl = this.getCurveMember();
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
        final MultiCurveType that = ((MultiCurveType) object);
        {
            List<CurvePropertyType> lhsCurveMember;
            lhsCurveMember = (this.isSetCurveMember()?this.getCurveMember():null);
            List<CurvePropertyType> rhsCurveMember;
            rhsCurveMember = (that.isSetCurveMember()?that.getCurveMember():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "curveMember", lhsCurveMember), LocatorUtils.property(thatLocator, "curveMember", rhsCurveMember), lhsCurveMember, rhsCurveMember, this.isSetCurveMember(), that.isSetCurveMember())) {
                return false;
            }
        }
        {
            CurveArrayPropertyType lhsCurveMembers;
            lhsCurveMembers = this.getCurveMembers();
            CurveArrayPropertyType rhsCurveMembers;
            rhsCurveMembers = that.getCurveMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "curveMembers", lhsCurveMembers), LocatorUtils.property(thatLocator, "curveMembers", rhsCurveMembers), lhsCurveMembers, rhsCurveMembers, this.isSetCurveMembers(), that.isSetCurveMembers())) {
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
            List<CurvePropertyType> theCurveMember;
            theCurveMember = (this.isSetCurveMember()?this.getCurveMember():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "curveMember", theCurveMember), currentHashCode, theCurveMember, this.isSetCurveMember());
        }
        {
            CurveArrayPropertyType theCurveMembers;
            theCurveMembers = this.getCurveMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "curveMembers", theCurveMembers), currentHashCode, theCurveMembers, this.isSetCurveMembers());
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
        if (draftCopy instanceof MultiCurveType) {
            final MultiCurveType copy = ((MultiCurveType) draftCopy);
            {
                java.lang.Boolean curveMemberShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCurveMember());
                if (curveMemberShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<CurvePropertyType> sourceCurveMember;
                    sourceCurveMember = (this.isSetCurveMember()?this.getCurveMember():null);
                    @SuppressWarnings("unchecked")
                    List<CurvePropertyType> copyCurveMember = ((List<CurvePropertyType> ) strategy.copy(LocatorUtils.property(locator, "curveMember", sourceCurveMember), sourceCurveMember, this.isSetCurveMember()));
                    copy.setCurveMember(copyCurveMember);
                } else {
                    if (curveMemberShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetCurveMember();
                    }
                }
            }
            {
                java.lang.Boolean curveMembersShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCurveMembers());
                if (curveMembersShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CurveArrayPropertyType sourceCurveMembers;
                    sourceCurveMembers = this.getCurveMembers();
                    CurveArrayPropertyType copyCurveMembers = ((CurveArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "curveMembers", sourceCurveMembers), sourceCurveMembers, this.isSetCurveMembers()));
                    copy.setCurveMembers(copyCurveMembers);
                } else {
                    if (curveMembersShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.curveMembers = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new MultiCurveType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MultiCurveType) {
            final MultiCurveType target = this;
            final MultiCurveType leftObject = ((MultiCurveType) left);
            final MultiCurveType rightObject = ((MultiCurveType) right);
            {
                java.lang.Boolean curveMemberShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCurveMember(), rightObject.isSetCurveMember());
                if (curveMemberShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<CurvePropertyType> lhsCurveMember;
                    lhsCurveMember = (leftObject.isSetCurveMember()?leftObject.getCurveMember():null);
                    List<CurvePropertyType> rhsCurveMember;
                    rhsCurveMember = (rightObject.isSetCurveMember()?rightObject.getCurveMember():null);
                    List<CurvePropertyType> mergedCurveMember = ((List<CurvePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "curveMember", lhsCurveMember), LocatorUtils.property(rightLocator, "curveMember", rhsCurveMember), lhsCurveMember, rhsCurveMember, leftObject.isSetCurveMember(), rightObject.isSetCurveMember()));
                    target.setCurveMember(mergedCurveMember);
                } else {
                    if (curveMemberShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetCurveMember();
                    }
                }
            }
            {
                java.lang.Boolean curveMembersShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCurveMembers(), rightObject.isSetCurveMembers());
                if (curveMembersShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CurveArrayPropertyType lhsCurveMembers;
                    lhsCurveMembers = leftObject.getCurveMembers();
                    CurveArrayPropertyType rhsCurveMembers;
                    rhsCurveMembers = rightObject.getCurveMembers();
                    CurveArrayPropertyType mergedCurveMembers = ((CurveArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "curveMembers", lhsCurveMembers), LocatorUtils.property(rightLocator, "curveMembers", rhsCurveMembers), lhsCurveMembers, rhsCurveMembers, leftObject.isSetCurveMembers(), rightObject.isSetCurveMembers()));
                    target.setCurveMembers(mergedCurveMembers);
                } else {
                    if (curveMembersShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.curveMembers = null;
                    }
                }
            }
        }
    }

}
