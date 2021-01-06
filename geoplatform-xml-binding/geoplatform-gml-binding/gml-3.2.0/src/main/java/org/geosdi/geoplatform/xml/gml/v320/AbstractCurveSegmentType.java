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
//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Classe Java per AbstractCurveSegmentType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractCurveSegmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="numDerivativesAtStart" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="numDerivativesAtEnd" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="numDerivativeInterior" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCurveSegmentType")
@XmlSeeAlso({
    GeodesicStringType.class,
    LineStringSegmentType.class,
    ArcStringType.class,
    BSplineType.class,
    OffsetCurveType.class,
    ClothoidType.class,
    CubicSplineType.class,
    ArcByCenterPointType.class,
    ArcStringByBulgeType.class
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

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            BigInteger theNumDerivativesAtStart;
            theNumDerivativesAtStart = this.getNumDerivativesAtStart();
            strategy.appendField(locator, this, "numDerivativesAtStart", buffer, theNumDerivativesAtStart);
        }
        {
            BigInteger theNumDerivativesAtEnd;
            theNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
            strategy.appendField(locator, this, "numDerivativesAtEnd", buffer, theNumDerivativesAtEnd);
        }
        {
            BigInteger theNumDerivativeInterior;
            theNumDerivativeInterior = this.getNumDerivativeInterior();
            strategy.appendField(locator, this, "numDerivativeInterior", buffer, theNumDerivativeInterior);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractCurveSegmentType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numDerivativesAtStart", lhsNumDerivativesAtStart), LocatorUtils.property(thatLocator, "numDerivativesAtStart", rhsNumDerivativesAtStart), lhsNumDerivativesAtStart, rhsNumDerivativesAtStart)) {
                return false;
            }
        }
        {
            BigInteger lhsNumDerivativesAtEnd;
            lhsNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
            BigInteger rhsNumDerivativesAtEnd;
            rhsNumDerivativesAtEnd = that.getNumDerivativesAtEnd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numDerivativesAtEnd", lhsNumDerivativesAtEnd), LocatorUtils.property(thatLocator, "numDerivativesAtEnd", rhsNumDerivativesAtEnd), lhsNumDerivativesAtEnd, rhsNumDerivativesAtEnd)) {
                return false;
            }
        }
        {
            BigInteger lhsNumDerivativeInterior;
            lhsNumDerivativeInterior = this.getNumDerivativeInterior();
            BigInteger rhsNumDerivativeInterior;
            rhsNumDerivativeInterior = that.getNumDerivativeInterior();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numDerivativeInterior", lhsNumDerivativeInterior), LocatorUtils.property(thatLocator, "numDerivativeInterior", rhsNumDerivativeInterior), lhsNumDerivativeInterior, rhsNumDerivativeInterior)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            BigInteger theNumDerivativesAtStart;
            theNumDerivativesAtStart = this.getNumDerivativesAtStart();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numDerivativesAtStart", theNumDerivativesAtStart), currentHashCode, theNumDerivativesAtStart);
        }
        {
            BigInteger theNumDerivativesAtEnd;
            theNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numDerivativesAtEnd", theNumDerivativesAtEnd), currentHashCode, theNumDerivativesAtEnd);
        }
        {
            BigInteger theNumDerivativeInterior;
            theNumDerivativeInterior = this.getNumDerivativeInterior();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numDerivativeInterior", theNumDerivativeInterior), currentHashCode, theNumDerivativeInterior);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        if (target instanceof AbstractCurveSegmentType) {
            final AbstractCurveSegmentType copy = ((AbstractCurveSegmentType) target);
            if (this.isSetNumDerivativesAtStart()) {
                BigInteger sourceNumDerivativesAtStart;
                sourceNumDerivativesAtStart = this.getNumDerivativesAtStart();
                BigInteger copyNumDerivativesAtStart = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numDerivativesAtStart", sourceNumDerivativesAtStart), sourceNumDerivativesAtStart));
                copy.setNumDerivativesAtStart(copyNumDerivativesAtStart);
            } else {
                copy.numDerivativesAtStart = null;
            }
            if (this.isSetNumDerivativesAtEnd()) {
                BigInteger sourceNumDerivativesAtEnd;
                sourceNumDerivativesAtEnd = this.getNumDerivativesAtEnd();
                BigInteger copyNumDerivativesAtEnd = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numDerivativesAtEnd", sourceNumDerivativesAtEnd), sourceNumDerivativesAtEnd));
                copy.setNumDerivativesAtEnd(copyNumDerivativesAtEnd);
            } else {
                copy.numDerivativesAtEnd = null;
            }
            if (this.isSetNumDerivativeInterior()) {
                BigInteger sourceNumDerivativeInterior;
                sourceNumDerivativeInterior = this.getNumDerivativeInterior();
                BigInteger copyNumDerivativeInterior = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numDerivativeInterior", sourceNumDerivativeInterior), sourceNumDerivativeInterior));
                copy.setNumDerivativeInterior(copyNumDerivativeInterior);
            } else {
                copy.numDerivativeInterior = null;
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractCurveSegmentType) {
            final AbstractCurveSegmentType target = this;
            final AbstractCurveSegmentType leftObject = ((AbstractCurveSegmentType) left);
            final AbstractCurveSegmentType rightObject = ((AbstractCurveSegmentType) right);
            {
                BigInteger lhsNumDerivativesAtStart;
                lhsNumDerivativesAtStart = leftObject.getNumDerivativesAtStart();
                BigInteger rhsNumDerivativesAtStart;
                rhsNumDerivativesAtStart = rightObject.getNumDerivativesAtStart();
                target.setNumDerivativesAtStart(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numDerivativesAtStart", lhsNumDerivativesAtStart), LocatorUtils.property(rightLocator, "numDerivativesAtStart", rhsNumDerivativesAtStart), lhsNumDerivativesAtStart, rhsNumDerivativesAtStart)));
            }
            {
                BigInteger lhsNumDerivativesAtEnd;
                lhsNumDerivativesAtEnd = leftObject.getNumDerivativesAtEnd();
                BigInteger rhsNumDerivativesAtEnd;
                rhsNumDerivativesAtEnd = rightObject.getNumDerivativesAtEnd();
                target.setNumDerivativesAtEnd(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numDerivativesAtEnd", lhsNumDerivativesAtEnd), LocatorUtils.property(rightLocator, "numDerivativesAtEnd", rhsNumDerivativesAtEnd), lhsNumDerivativesAtEnd, rhsNumDerivativesAtEnd)));
            }
            {
                BigInteger lhsNumDerivativeInterior;
                lhsNumDerivativeInterior = leftObject.getNumDerivativeInterior();
                BigInteger rhsNumDerivativeInterior;
                rhsNumDerivativeInterior = rightObject.getNumDerivativeInterior();
                target.setNumDerivativeInterior(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numDerivativeInterior", lhsNumDerivativeInterior), LocatorUtils.property(rightLocator, "numDerivativeInterior", rhsNumDerivativeInterior), lhsNumDerivativeInterior, rhsNumDerivativeInterior)));
            }
        }
    }

    public AbstractCurveSegmentType withNumDerivativesAtStart(BigInteger value) {
        setNumDerivativesAtStart(value);
        return this;
    }

    public AbstractCurveSegmentType withNumDerivativesAtEnd(BigInteger value) {
        setNumDerivativesAtEnd(value);
        return this;
    }

    public AbstractCurveSegmentType withNumDerivativeInterior(BigInteger value) {
        setNumDerivativeInterior(value);
        return this;
    }

}
