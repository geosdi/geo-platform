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
 * <p>Classe Java per OrientableCurveType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="OrientableCurveType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCurveType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}baseCurve"/>
 *       </sequence>
 *       <attribute name="orientation" type="{http://www.opengis.net/gml}SignType" default="+" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrientableCurveType", propOrder = {
    "baseCurve"
})
public class OrientableCurveType
    extends AbstractCurveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The property baseCurve references or contains the base curve, i.e. it either references the base curve via the XLink-attributes or contains the curve element. A curve element is any element which is substitutable for AbstractCurve. The base curve has positive orientation.
     * 
     */
    @XmlElement(required = true)
    protected CurvePropertyType baseCurve;
    @XmlAttribute(name = "orientation")
    protected SignType orientation;

    /**
     * The property baseCurve references or contains the base curve, i.e. it either references the base curve via the XLink-attributes or contains the curve element. A curve element is any element which is substitutable for AbstractCurve. The base curve has positive orientation.
     * 
     * @return
     *     possible object is
     *     {@link CurvePropertyType }
     *     
     */
    public CurvePropertyType getBaseCurve() {
        return baseCurve;
    }

    /**
     * Imposta il valore della proprietà baseCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link CurvePropertyType }
     *     
     * @see #getBaseCurve()
     */
    public void setBaseCurve(CurvePropertyType value) {
        this.baseCurve = value;
    }

    public boolean isSetBaseCurve() {
        return (this.baseCurve!= null);
    }

    /**
     * Recupera il valore della proprietà orientation.
     * 
     * @return
     *     possible object is
     *     {@link SignType }
     *     
     */
    public SignType getOrientation() {
        if (orientation == null) {
            return SignType.VALUE_2;
        } else {
            return orientation;
        }
    }

    /**
     * Imposta il valore della proprietà orientation.
     * 
     * @param value
     *     allowed object is
     *     {@link SignType }
     *     
     */
    public void setOrientation(SignType value) {
        this.orientation = value;
    }

    public boolean isSetOrientation() {
        return (this.orientation!= null);
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
            CurvePropertyType theBaseCurve;
            theBaseCurve = this.getBaseCurve();
            strategy.appendField(locator, this, "baseCurve", buffer, theBaseCurve, this.isSetBaseCurve());
        }
        {
            SignType theOrientation;
            theOrientation = this.getOrientation();
            strategy.appendField(locator, this, "orientation", buffer, theOrientation, this.isSetOrientation());
        }
        return buffer;
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
        final OrientableCurveType that = ((OrientableCurveType) object);
        {
            CurvePropertyType lhsBaseCurve;
            lhsBaseCurve = this.getBaseCurve();
            CurvePropertyType rhsBaseCurve;
            rhsBaseCurve = that.getBaseCurve();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseCurve", lhsBaseCurve), LocatorUtils.property(thatLocator, "baseCurve", rhsBaseCurve), lhsBaseCurve, rhsBaseCurve, this.isSetBaseCurve(), that.isSetBaseCurve())) {
                return false;
            }
        }
        {
            SignType lhsOrientation;
            lhsOrientation = this.getOrientation();
            SignType rhsOrientation;
            rhsOrientation = that.getOrientation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orientation", lhsOrientation), LocatorUtils.property(thatLocator, "orientation", rhsOrientation), lhsOrientation, rhsOrientation, this.isSetOrientation(), that.isSetOrientation())) {
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
            CurvePropertyType theBaseCurve;
            theBaseCurve = this.getBaseCurve();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baseCurve", theBaseCurve), currentHashCode, theBaseCurve, this.isSetBaseCurve());
        }
        {
            SignType theOrientation;
            theOrientation = this.getOrientation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "orientation", theOrientation), currentHashCode, theOrientation, this.isSetOrientation());
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
        if (draftCopy instanceof OrientableCurveType) {
            final OrientableCurveType copy = ((OrientableCurveType) draftCopy);
            {
                java.lang.Boolean baseCurveShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBaseCurve());
                if (baseCurveShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CurvePropertyType sourceBaseCurve;
                    sourceBaseCurve = this.getBaseCurve();
                    CurvePropertyType copyBaseCurve = ((CurvePropertyType) strategy.copy(LocatorUtils.property(locator, "baseCurve", sourceBaseCurve), sourceBaseCurve, this.isSetBaseCurve()));
                    copy.setBaseCurve(copyBaseCurve);
                } else {
                    if (baseCurveShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.baseCurve = null;
                    }
                }
            }
            {
                java.lang.Boolean orientationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOrientation());
                if (orientationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    SignType sourceOrientation;
                    sourceOrientation = this.getOrientation();
                    SignType copyOrientation = ((SignType) strategy.copy(LocatorUtils.property(locator, "orientation", sourceOrientation), sourceOrientation, this.isSetOrientation()));
                    copy.setOrientation(copyOrientation);
                } else {
                    if (orientationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.orientation = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new OrientableCurveType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof OrientableCurveType) {
            final OrientableCurveType target = this;
            final OrientableCurveType leftObject = ((OrientableCurveType) left);
            final OrientableCurveType rightObject = ((OrientableCurveType) right);
            {
                java.lang.Boolean baseCurveShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBaseCurve(), rightObject.isSetBaseCurve());
                if (baseCurveShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CurvePropertyType lhsBaseCurve;
                    lhsBaseCurve = leftObject.getBaseCurve();
                    CurvePropertyType rhsBaseCurve;
                    rhsBaseCurve = rightObject.getBaseCurve();
                    CurvePropertyType mergedBaseCurve = ((CurvePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "baseCurve", lhsBaseCurve), LocatorUtils.property(rightLocator, "baseCurve", rhsBaseCurve), lhsBaseCurve, rhsBaseCurve, leftObject.isSetBaseCurve(), rightObject.isSetBaseCurve()));
                    target.setBaseCurve(mergedBaseCurve);
                } else {
                    if (baseCurveShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.baseCurve = null;
                    }
                }
            }
            {
                java.lang.Boolean orientationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOrientation(), rightObject.isSetOrientation());
                if (orientationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    SignType lhsOrientation;
                    lhsOrientation = leftObject.getOrientation();
                    SignType rhsOrientation;
                    rhsOrientation = rightObject.getOrientation();
                    SignType mergedOrientation = ((SignType) strategy.merge(LocatorUtils.property(leftLocator, "orientation", lhsOrientation), LocatorUtils.property(rightLocator, "orientation", rhsOrientation), lhsOrientation, rhsOrientation, leftObject.isSetOrientation(), rightObject.isSetOrientation()));
                    target.setOrientation(mergedOrientation);
                } else {
                    if (orientationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.orientation = null;
                    }
                }
            }
        }
    }

}
