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

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractCurveSegmentType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractCurveSegmentType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="numDerivativesAtStart" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       <attribute name="numDerivativesAtEnd" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       <attribute name="numDerivativeInterior" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCurveSegmentType")
@XmlSeeAlso({
    LineStringSegmentType.class,
    ArcStringType.class,
    ArcStringByBulgeType.class,
    ArcByCenterPointType.class,
    CubicSplineType.class,
    BSplineType.class,
    OffsetCurveType.class,
    ClothoidType.class,
    GeodesicStringType.class
})
public abstract class AbstractCurveSegmentType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlAttribute(name = "numDerivativesAtStart")
    protected BigInteger numDerivativesAtStart;
    @XmlAttribute(name = "numDerivativesAtEnd")
    protected BigInteger numDerivativesAtEnd;
    @XmlAttribute(name = "numDerivativeInterior")
    protected BigInteger numDerivativeInterior;

    /**
     * Recupera il valore della proprietà numDerivativesAtStart.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumDerivativesAtStart() {
        if (numDerivativesAtStart == null) {
            return new BigInteger("0");
        } else {
            return numDerivativesAtStart;
        }
    }

    /**
     * Imposta il valore della proprietà numDerivativesAtStart.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumDerivativesAtStart(BigInteger value) {
        this.numDerivativesAtStart = value;
    }

    public boolean isSetNumDerivativesAtStart() {
        return (this.numDerivativesAtStart!= null);
    }

    /**
     * Recupera il valore della proprietà numDerivativesAtEnd.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumDerivativesAtEnd() {
        if (numDerivativesAtEnd == null) {
            return new BigInteger("0");
        } else {
            return numDerivativesAtEnd;
        }
    }

    /**
     * Imposta il valore della proprietà numDerivativesAtEnd.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumDerivativesAtEnd(BigInteger value) {
        this.numDerivativesAtEnd = value;
    }

    public boolean isSetNumDerivativesAtEnd() {
        return (this.numDerivativesAtEnd!= null);
    }

    /**
     * Recupera il valore della proprietà numDerivativeInterior.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumDerivativeInterior() {
        if (numDerivativeInterior == null) {
            return new BigInteger("0");
        } else {
            return numDerivativeInterior;
        }
    }

    /**
     * Imposta il valore della proprietà numDerivativeInterior.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumDerivativeInterior(BigInteger value) {
        this.numDerivativeInterior = value;
    }

    public boolean isSetNumDerivativeInterior() {
        return (this.numDerivativeInterior!= null);
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
            BigInteger theNumDerivativesAtStart;
            theNumDerivativesAtStart = this.getNumDerivativesAtStart();
            strategy.appendField(locator, this, "numDerivativesAtStart", buffer, theNumDerivativesAtStart, this.isSetNumDerivativesAtStart());
        }
        {
            BigInteger theNumDerivativesAtEnd;
            theNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
            strategy.appendField(locator, this, "numDerivativesAtEnd", buffer, theNumDerivativesAtEnd, this.isSetNumDerivativesAtEnd());
        }
        {
            BigInteger theNumDerivativeInterior;
            theNumDerivativeInterior = this.getNumDerivativeInterior();
            strategy.appendField(locator, this, "numDerivativeInterior", buffer, theNumDerivativeInterior, this.isSetNumDerivativeInterior());
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
        final AbstractCurveSegmentType that = ((AbstractCurveSegmentType) object);
        {
            BigInteger lhsNumDerivativesAtStart;
            lhsNumDerivativesAtStart = this.getNumDerivativesAtStart();
            BigInteger rhsNumDerivativesAtStart;
            rhsNumDerivativesAtStart = that.getNumDerivativesAtStart();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numDerivativesAtStart", lhsNumDerivativesAtStart), LocatorUtils.property(thatLocator, "numDerivativesAtStart", rhsNumDerivativesAtStart), lhsNumDerivativesAtStart, rhsNumDerivativesAtStart, this.isSetNumDerivativesAtStart(), that.isSetNumDerivativesAtStart())) {
                return false;
            }
        }
        {
            BigInteger lhsNumDerivativesAtEnd;
            lhsNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
            BigInteger rhsNumDerivativesAtEnd;
            rhsNumDerivativesAtEnd = that.getNumDerivativesAtEnd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numDerivativesAtEnd", lhsNumDerivativesAtEnd), LocatorUtils.property(thatLocator, "numDerivativesAtEnd", rhsNumDerivativesAtEnd), lhsNumDerivativesAtEnd, rhsNumDerivativesAtEnd, this.isSetNumDerivativesAtEnd(), that.isSetNumDerivativesAtEnd())) {
                return false;
            }
        }
        {
            BigInteger lhsNumDerivativeInterior;
            lhsNumDerivativeInterior = this.getNumDerivativeInterior();
            BigInteger rhsNumDerivativeInterior;
            rhsNumDerivativeInterior = that.getNumDerivativeInterior();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numDerivativeInterior", lhsNumDerivativeInterior), LocatorUtils.property(thatLocator, "numDerivativeInterior", rhsNumDerivativeInterior), lhsNumDerivativeInterior, rhsNumDerivativeInterior, this.isSetNumDerivativeInterior(), that.isSetNumDerivativeInterior())) {
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
            BigInteger theNumDerivativesAtStart;
            theNumDerivativesAtStart = this.getNumDerivativesAtStart();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numDerivativesAtStart", theNumDerivativesAtStart), currentHashCode, theNumDerivativesAtStart, this.isSetNumDerivativesAtStart());
        }
        {
            BigInteger theNumDerivativesAtEnd;
            theNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numDerivativesAtEnd", theNumDerivativesAtEnd), currentHashCode, theNumDerivativesAtEnd, this.isSetNumDerivativesAtEnd());
        }
        {
            BigInteger theNumDerivativeInterior;
            theNumDerivativeInterior = this.getNumDerivativeInterior();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numDerivativeInterior", theNumDerivativeInterior), currentHashCode, theNumDerivativeInterior, this.isSetNumDerivativeInterior());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        if (target instanceof AbstractCurveSegmentType) {
            final AbstractCurveSegmentType copy = ((AbstractCurveSegmentType) target);
            {
                java.lang.Boolean numDerivativesAtStartShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNumDerivativesAtStart());
                if (numDerivativesAtStartShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceNumDerivativesAtStart;
                    sourceNumDerivativesAtStart = this.getNumDerivativesAtStart();
                    BigInteger copyNumDerivativesAtStart = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numDerivativesAtStart", sourceNumDerivativesAtStart), sourceNumDerivativesAtStart, this.isSetNumDerivativesAtStart()));
                    copy.setNumDerivativesAtStart(copyNumDerivativesAtStart);
                } else {
                    if (numDerivativesAtStartShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.numDerivativesAtStart = null;
                    }
                }
            }
            {
                java.lang.Boolean numDerivativesAtEndShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNumDerivativesAtEnd());
                if (numDerivativesAtEndShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceNumDerivativesAtEnd;
                    sourceNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
                    BigInteger copyNumDerivativesAtEnd = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numDerivativesAtEnd", sourceNumDerivativesAtEnd), sourceNumDerivativesAtEnd, this.isSetNumDerivativesAtEnd()));
                    copy.setNumDerivativesAtEnd(copyNumDerivativesAtEnd);
                } else {
                    if (numDerivativesAtEndShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.numDerivativesAtEnd = null;
                    }
                }
            }
            {
                java.lang.Boolean numDerivativeInteriorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNumDerivativeInterior());
                if (numDerivativeInteriorShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceNumDerivativeInterior;
                    sourceNumDerivativeInterior = this.getNumDerivativeInterior();
                    BigInteger copyNumDerivativeInterior = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numDerivativeInterior", sourceNumDerivativeInterior), sourceNumDerivativeInterior, this.isSetNumDerivativeInterior()));
                    copy.setNumDerivativeInterior(copyNumDerivativeInterior);
                } else {
                    if (numDerivativeInteriorShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.numDerivativeInterior = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractCurveSegmentType) {
            final AbstractCurveSegmentType target = this;
            final AbstractCurveSegmentType leftObject = ((AbstractCurveSegmentType) left);
            final AbstractCurveSegmentType rightObject = ((AbstractCurveSegmentType) right);
            {
                java.lang.Boolean numDerivativesAtStartShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNumDerivativesAtStart(), rightObject.isSetNumDerivativesAtStart());
                if (numDerivativesAtStartShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsNumDerivativesAtStart;
                    lhsNumDerivativesAtStart = leftObject.getNumDerivativesAtStart();
                    BigInteger rhsNumDerivativesAtStart;
                    rhsNumDerivativesAtStart = rightObject.getNumDerivativesAtStart();
                    BigInteger mergedNumDerivativesAtStart = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numDerivativesAtStart", lhsNumDerivativesAtStart), LocatorUtils.property(rightLocator, "numDerivativesAtStart", rhsNumDerivativesAtStart), lhsNumDerivativesAtStart, rhsNumDerivativesAtStart, leftObject.isSetNumDerivativesAtStart(), rightObject.isSetNumDerivativesAtStart()));
                    target.setNumDerivativesAtStart(mergedNumDerivativesAtStart);
                } else {
                    if (numDerivativesAtStartShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.numDerivativesAtStart = null;
                    }
                }
            }
            {
                java.lang.Boolean numDerivativesAtEndShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNumDerivativesAtEnd(), rightObject.isSetNumDerivativesAtEnd());
                if (numDerivativesAtEndShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsNumDerivativesAtEnd;
                    lhsNumDerivativesAtEnd = leftObject.getNumDerivativesAtEnd();
                    BigInteger rhsNumDerivativesAtEnd;
                    rhsNumDerivativesAtEnd = rightObject.getNumDerivativesAtEnd();
                    BigInteger mergedNumDerivativesAtEnd = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numDerivativesAtEnd", lhsNumDerivativesAtEnd), LocatorUtils.property(rightLocator, "numDerivativesAtEnd", rhsNumDerivativesAtEnd), lhsNumDerivativesAtEnd, rhsNumDerivativesAtEnd, leftObject.isSetNumDerivativesAtEnd(), rightObject.isSetNumDerivativesAtEnd()));
                    target.setNumDerivativesAtEnd(mergedNumDerivativesAtEnd);
                } else {
                    if (numDerivativesAtEndShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.numDerivativesAtEnd = null;
                    }
                }
            }
            {
                java.lang.Boolean numDerivativeInteriorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNumDerivativeInterior(), rightObject.isSetNumDerivativeInterior());
                if (numDerivativeInteriorShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsNumDerivativeInterior;
                    lhsNumDerivativeInterior = leftObject.getNumDerivativeInterior();
                    BigInteger rhsNumDerivativeInterior;
                    rhsNumDerivativeInterior = rightObject.getNumDerivativeInterior();
                    BigInteger mergedNumDerivativeInterior = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numDerivativeInterior", lhsNumDerivativeInterior), LocatorUtils.property(rightLocator, "numDerivativeInterior", rhsNumDerivativeInterior), lhsNumDerivativeInterior, rhsNumDerivativeInterior, leftObject.isSetNumDerivativeInterior(), rightObject.isSetNumDerivativeInterior()));
                    target.setNumDerivativeInterior(mergedNumDerivativeInterior);
                } else {
                    if (numDerivativeInteriorShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.numDerivativeInterior = null;
                    }
                }
            }
        }
    }

}
