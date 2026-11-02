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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
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
 * gml:CurveSegmentArrayPropertyType is a container for an array of curve segments.
 * 
 * <p>Classe Java per CurveSegmentArrayPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CurveSegmentArrayPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence maxOccurs="unbounded" minOccurs="0">
 *         <element ref="{http://www.opengis.net/gml}AbstractCurveSegment"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurveSegmentArrayPropertyType", propOrder = {
    "abstractCurveSegment"
})
public class CurveSegmentArrayPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractCurveSegment", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractCurveSegmentType>> abstractCurveSegment;

    /**
     * Gets the value of the abstractCurveSegment property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the abstractCurveSegment property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAbstractCurveSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractCurveSegmentType }{@code >}
     * {@link JAXBElement }{@code <}{@link ArcByBulgeType }{@code >}
     * {@link JAXBElement }{@code <}{@link ArcByCenterPointType }{@code >}
     * {@link JAXBElement }{@code <}{@link ArcStringByBulgeType }{@code >}
     * {@link JAXBElement }{@code <}{@link ArcStringType }{@code >}
     * {@link JAXBElement }{@code <}{@link ArcType }{@code >}
     * {@link JAXBElement }{@code <}{@link BSplineType }{@code >}
     * {@link JAXBElement }{@code <}{@link BezierType }{@code >}
     * {@link JAXBElement }{@code <}{@link CircleByCenterPointType }{@code >}
     * {@link JAXBElement }{@code <}{@link CircleType }{@code >}
     * {@link JAXBElement }{@code <}{@link ClothoidType }{@code >}
     * {@link JAXBElement }{@code <}{@link CubicSplineType }{@code >}
     * {@link JAXBElement }{@code <}{@link GeodesicStringType }{@code >}
     * {@link JAXBElement }{@code <}{@link GeodesicType }{@code >}
     * {@link JAXBElement }{@code <}{@link LineStringSegmentType }{@code >}
     * {@link JAXBElement }{@code <}{@link OffsetCurveType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the abstractCurveSegment property.
     */
    public List<JAXBElement<? extends AbstractCurveSegmentType>> getAbstractCurveSegment() {
        if (abstractCurveSegment == null) {
            abstractCurveSegment = new ArrayList<>();
        }
        return this.abstractCurveSegment;
    }

    public boolean isSetAbstractCurveSegment() {
        return ((this.abstractCurveSegment!= null)&&(!this.abstractCurveSegment.isEmpty()));
    }

    public void unsetAbstractCurveSegment() {
        this.abstractCurveSegment = null;
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
            List<JAXBElement<? extends AbstractCurveSegmentType>> theAbstractCurveSegment;
            theAbstractCurveSegment = (this.isSetAbstractCurveSegment()?this.getAbstractCurveSegment():null);
            strategy.appendField(locator, this, "abstractCurveSegment", buffer, theAbstractCurveSegment, this.isSetAbstractCurveSegment());
        }
        return buffer;
    }

    public void setAbstractCurveSegment(List<JAXBElement<? extends AbstractCurveSegmentType>> value) {
        this.abstractCurveSegment = null;
        if (value!= null) {
            List<JAXBElement<? extends AbstractCurveSegmentType>> draftl = this.getAbstractCurveSegment();
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
        final CurveSegmentArrayPropertyType that = ((CurveSegmentArrayPropertyType) object);
        {
            List<JAXBElement<? extends AbstractCurveSegmentType>> lhsAbstractCurveSegment;
            lhsAbstractCurveSegment = (this.isSetAbstractCurveSegment()?this.getAbstractCurveSegment():null);
            List<JAXBElement<? extends AbstractCurveSegmentType>> rhsAbstractCurveSegment;
            rhsAbstractCurveSegment = (that.isSetAbstractCurveSegment()?that.getAbstractCurveSegment():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractCurveSegment", lhsAbstractCurveSegment), LocatorUtils.property(thatLocator, "abstractCurveSegment", rhsAbstractCurveSegment), lhsAbstractCurveSegment, rhsAbstractCurveSegment, this.isSetAbstractCurveSegment(), that.isSetAbstractCurveSegment())) {
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
            List<JAXBElement<? extends AbstractCurveSegmentType>> theAbstractCurveSegment;
            theAbstractCurveSegment = (this.isSetAbstractCurveSegment()?this.getAbstractCurveSegment():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractCurveSegment", theAbstractCurveSegment), currentHashCode, theAbstractCurveSegment, this.isSetAbstractCurveSegment());
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
        if (draftCopy instanceof CurveSegmentArrayPropertyType) {
            final CurveSegmentArrayPropertyType copy = ((CurveSegmentArrayPropertyType) draftCopy);
            {
                java.lang.Boolean abstractCurveSegmentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractCurveSegment());
                if (abstractCurveSegmentShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<? extends AbstractCurveSegmentType>> sourceAbstractCurveSegment;
                    sourceAbstractCurveSegment = (this.isSetAbstractCurveSegment()?this.getAbstractCurveSegment():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<? extends AbstractCurveSegmentType>> copyAbstractCurveSegment = ((List<JAXBElement<? extends AbstractCurveSegmentType>> ) strategy.copy(LocatorUtils.property(locator, "abstractCurveSegment", sourceAbstractCurveSegment), sourceAbstractCurveSegment, this.isSetAbstractCurveSegment()));
                    copy.setAbstractCurveSegment(copyAbstractCurveSegment);
                } else {
                    if (abstractCurveSegmentShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAbstractCurveSegment();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CurveSegmentArrayPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof CurveSegmentArrayPropertyType) {
            final CurveSegmentArrayPropertyType target = this;
            final CurveSegmentArrayPropertyType leftObject = ((CurveSegmentArrayPropertyType) left);
            final CurveSegmentArrayPropertyType rightObject = ((CurveSegmentArrayPropertyType) right);
            {
                java.lang.Boolean abstractCurveSegmentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractCurveSegment(), rightObject.isSetAbstractCurveSegment());
                if (abstractCurveSegmentShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<? extends AbstractCurveSegmentType>> lhsAbstractCurveSegment;
                    lhsAbstractCurveSegment = (leftObject.isSetAbstractCurveSegment()?leftObject.getAbstractCurveSegment():null);
                    List<JAXBElement<? extends AbstractCurveSegmentType>> rhsAbstractCurveSegment;
                    rhsAbstractCurveSegment = (rightObject.isSetAbstractCurveSegment()?rightObject.getAbstractCurveSegment():null);
                    List<JAXBElement<? extends AbstractCurveSegmentType>> mergedAbstractCurveSegment = ((List<JAXBElement<? extends AbstractCurveSegmentType>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractCurveSegment", lhsAbstractCurveSegment), LocatorUtils.property(rightLocator, "abstractCurveSegment", rhsAbstractCurveSegment), lhsAbstractCurveSegment, rhsAbstractCurveSegment, leftObject.isSetAbstractCurveSegment(), rightObject.isSetAbstractCurveSegment()));
                    target.setAbstractCurveSegment(mergedAbstractCurveSegment);
                } else {
                    if (abstractCurveSegmentShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAbstractCurveSegment();
                    }
                }
            }
        }
    }

}
