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
 * <p>Classe Java per OffsetCurveType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="OffsetCurveType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       <sequence>
 *         <element name="offsetBase" type="{http://www.opengis.net/gml}CurvePropertyType"/>
 *         <element name="distance" type="{http://www.opengis.net/gml}LengthType"/>
 *         <element name="refDirection" type="{http://www.opengis.net/gml}VectorType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OffsetCurveType", propOrder = {
    "offsetBase",
    "distance",
    "refDirection"
})
public class OffsetCurveType
    extends AbstractCurveSegmentType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected CurvePropertyType offsetBase;
    @XmlElement(required = true)
    protected LengthType distance;
    protected VectorType refDirection;

    /**
     * Recupera il valore della proprietà offsetBase.
     * 
     * @return
     *     possible object is
     *     {@link CurvePropertyType }
     *     
     */
    public CurvePropertyType getOffsetBase() {
        return offsetBase;
    }

    /**
     * Imposta il valore della proprietà offsetBase.
     * 
     * @param value
     *     allowed object is
     *     {@link CurvePropertyType }
     *     
     */
    public void setOffsetBase(CurvePropertyType value) {
        this.offsetBase = value;
    }

    public boolean isSetOffsetBase() {
        return (this.offsetBase!= null);
    }

    /**
     * Recupera il valore della proprietà distance.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getDistance() {
        return distance;
    }

    /**
     * Imposta il valore della proprietà distance.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setDistance(LengthType value) {
        this.distance = value;
    }

    public boolean isSetDistance() {
        return (this.distance!= null);
    }

    /**
     * Recupera il valore della proprietà refDirection.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getRefDirection() {
        return refDirection;
    }

    /**
     * Imposta il valore della proprietà refDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setRefDirection(VectorType value) {
        this.refDirection = value;
    }

    public boolean isSetRefDirection() {
        return (this.refDirection!= null);
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
            CurvePropertyType theOffsetBase;
            theOffsetBase = this.getOffsetBase();
            strategy.appendField(locator, this, "offsetBase", buffer, theOffsetBase, this.isSetOffsetBase());
        }
        {
            LengthType theDistance;
            theDistance = this.getDistance();
            strategy.appendField(locator, this, "distance", buffer, theDistance, this.isSetDistance());
        }
        {
            VectorType theRefDirection;
            theRefDirection = this.getRefDirection();
            strategy.appendField(locator, this, "refDirection", buffer, theRefDirection, this.isSetRefDirection());
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
        final OffsetCurveType that = ((OffsetCurveType) object);
        {
            CurvePropertyType lhsOffsetBase;
            lhsOffsetBase = this.getOffsetBase();
            CurvePropertyType rhsOffsetBase;
            rhsOffsetBase = that.getOffsetBase();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offsetBase", lhsOffsetBase), LocatorUtils.property(thatLocator, "offsetBase", rhsOffsetBase), lhsOffsetBase, rhsOffsetBase, this.isSetOffsetBase(), that.isSetOffsetBase())) {
                return false;
            }
        }
        {
            LengthType lhsDistance;
            lhsDistance = this.getDistance();
            LengthType rhsDistance;
            rhsDistance = that.getDistance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "distance", lhsDistance), LocatorUtils.property(thatLocator, "distance", rhsDistance), lhsDistance, rhsDistance, this.isSetDistance(), that.isSetDistance())) {
                return false;
            }
        }
        {
            VectorType lhsRefDirection;
            lhsRefDirection = this.getRefDirection();
            VectorType rhsRefDirection;
            rhsRefDirection = that.getRefDirection();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refDirection", lhsRefDirection), LocatorUtils.property(thatLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection, this.isSetRefDirection(), that.isSetRefDirection())) {
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
            CurvePropertyType theOffsetBase;
            theOffsetBase = this.getOffsetBase();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "offsetBase", theOffsetBase), currentHashCode, theOffsetBase, this.isSetOffsetBase());
        }
        {
            LengthType theDistance;
            theDistance = this.getDistance();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "distance", theDistance), currentHashCode, theDistance, this.isSetDistance());
        }
        {
            VectorType theRefDirection;
            theRefDirection = this.getRefDirection();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refDirection", theRefDirection), currentHashCode, theRefDirection, this.isSetRefDirection());
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
        if (draftCopy instanceof OffsetCurveType) {
            final OffsetCurveType copy = ((OffsetCurveType) draftCopy);
            {
                java.lang.Boolean offsetBaseShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOffsetBase());
                if (offsetBaseShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CurvePropertyType sourceOffsetBase;
                    sourceOffsetBase = this.getOffsetBase();
                    CurvePropertyType copyOffsetBase = ((CurvePropertyType) strategy.copy(LocatorUtils.property(locator, "offsetBase", sourceOffsetBase), sourceOffsetBase, this.isSetOffsetBase()));
                    copy.setOffsetBase(copyOffsetBase);
                } else {
                    if (offsetBaseShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.offsetBase = null;
                    }
                }
            }
            {
                java.lang.Boolean distanceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDistance());
                if (distanceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    LengthType sourceDistance;
                    sourceDistance = this.getDistance();
                    LengthType copyDistance = ((LengthType) strategy.copy(LocatorUtils.property(locator, "distance", sourceDistance), sourceDistance, this.isSetDistance()));
                    copy.setDistance(copyDistance);
                } else {
                    if (distanceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.distance = null;
                    }
                }
            }
            {
                java.lang.Boolean refDirectionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRefDirection());
                if (refDirectionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    VectorType sourceRefDirection;
                    sourceRefDirection = this.getRefDirection();
                    VectorType copyRefDirection = ((VectorType) strategy.copy(LocatorUtils.property(locator, "refDirection", sourceRefDirection), sourceRefDirection, this.isSetRefDirection()));
                    copy.setRefDirection(copyRefDirection);
                } else {
                    if (refDirectionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.refDirection = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new OffsetCurveType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof OffsetCurveType) {
            final OffsetCurveType target = this;
            final OffsetCurveType leftObject = ((OffsetCurveType) left);
            final OffsetCurveType rightObject = ((OffsetCurveType) right);
            {
                java.lang.Boolean offsetBaseShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOffsetBase(), rightObject.isSetOffsetBase());
                if (offsetBaseShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CurvePropertyType lhsOffsetBase;
                    lhsOffsetBase = leftObject.getOffsetBase();
                    CurvePropertyType rhsOffsetBase;
                    rhsOffsetBase = rightObject.getOffsetBase();
                    CurvePropertyType mergedOffsetBase = ((CurvePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "offsetBase", lhsOffsetBase), LocatorUtils.property(rightLocator, "offsetBase", rhsOffsetBase), lhsOffsetBase, rhsOffsetBase, leftObject.isSetOffsetBase(), rightObject.isSetOffsetBase()));
                    target.setOffsetBase(mergedOffsetBase);
                } else {
                    if (offsetBaseShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.offsetBase = null;
                    }
                }
            }
            {
                java.lang.Boolean distanceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDistance(), rightObject.isSetDistance());
                if (distanceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    LengthType lhsDistance;
                    lhsDistance = leftObject.getDistance();
                    LengthType rhsDistance;
                    rhsDistance = rightObject.getDistance();
                    LengthType mergedDistance = ((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "distance", lhsDistance), LocatorUtils.property(rightLocator, "distance", rhsDistance), lhsDistance, rhsDistance, leftObject.isSetDistance(), rightObject.isSetDistance()));
                    target.setDistance(mergedDistance);
                } else {
                    if (distanceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.distance = null;
                    }
                }
            }
            {
                java.lang.Boolean refDirectionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRefDirection(), rightObject.isSetRefDirection());
                if (refDirectionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    VectorType lhsRefDirection;
                    lhsRefDirection = leftObject.getRefDirection();
                    VectorType rhsRefDirection;
                    rhsRefDirection = rightObject.getRefDirection();
                    VectorType mergedRefDirection = ((VectorType) strategy.merge(LocatorUtils.property(leftLocator, "refDirection", lhsRefDirection), LocatorUtils.property(rightLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection, leftObject.isSetRefDirection(), rightObject.isSetRefDirection()));
                    target.setRefDirection(mergedRefDirection);
                } else {
                    if (refDirectionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.refDirection = null;
                    }
                }
            }
        }
    }

}
