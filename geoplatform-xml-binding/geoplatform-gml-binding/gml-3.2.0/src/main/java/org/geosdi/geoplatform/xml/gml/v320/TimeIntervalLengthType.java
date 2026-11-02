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

import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
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
 * <p>Classe Java per TimeIntervalLengthType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeIntervalLengthType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>decimal">
 *       <attribute name="unit" use="required" type="{http://www.opengis.net/gml}TimeUnitType" />
 *       <attribute name="radix" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       <attribute name="factor" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeIntervalLengthType", propOrder = {
    "value"
})
public class TimeIntervalLengthType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(name = "unit", required = true)
    protected String unit;
    @XmlAttribute(name = "radix")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger radix;
    @XmlAttribute(name = "factor")
    protected BigInteger factor;

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Recupera il valore della proprietà unit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Imposta il valore della proprietà unit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    public boolean isSetUnit() {
        return (this.unit!= null);
    }

    /**
     * Recupera il valore della proprietà radix.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRadix() {
        return radix;
    }

    /**
     * Imposta il valore della proprietà radix.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRadix(BigInteger value) {
        this.radix = value;
    }

    public boolean isSetRadix() {
        return (this.radix!= null);
    }

    /**
     * Recupera il valore della proprietà factor.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFactor() {
        return factor;
    }

    /**
     * Imposta il valore della proprietà factor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFactor(BigInteger value) {
        this.factor = value;
    }

    public boolean isSetFactor() {
        return (this.factor!= null);
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
            BigDecimal theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theUnit;
            theUnit = this.getUnit();
            strategy.appendField(locator, this, "unit", buffer, theUnit, this.isSetUnit());
        }
        {
            BigInteger theRadix;
            theRadix = this.getRadix();
            strategy.appendField(locator, this, "radix", buffer, theRadix, this.isSetRadix());
        }
        {
            BigInteger theFactor;
            theFactor = this.getFactor();
            strategy.appendField(locator, this, "factor", buffer, theFactor, this.isSetFactor());
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
        final TimeIntervalLengthType that = ((TimeIntervalLengthType) object);
        {
            BigDecimal lhsValue;
            lhsValue = this.getValue();
            BigDecimal rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            String lhsUnit;
            lhsUnit = this.getUnit();
            String rhsUnit;
            rhsUnit = that.getUnit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "unit", lhsUnit), LocatorUtils.property(thatLocator, "unit", rhsUnit), lhsUnit, rhsUnit, this.isSetUnit(), that.isSetUnit())) {
                return false;
            }
        }
        {
            BigInteger lhsRadix;
            lhsRadix = this.getRadix();
            BigInteger rhsRadix;
            rhsRadix = that.getRadix();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "radix", lhsRadix), LocatorUtils.property(thatLocator, "radix", rhsRadix), lhsRadix, rhsRadix, this.isSetRadix(), that.isSetRadix())) {
                return false;
            }
        }
        {
            BigInteger lhsFactor;
            lhsFactor = this.getFactor();
            BigInteger rhsFactor;
            rhsFactor = that.getFactor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "factor", lhsFactor), LocatorUtils.property(thatLocator, "factor", rhsFactor), lhsFactor, rhsFactor, this.isSetFactor(), that.isSetFactor())) {
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
            BigDecimal theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            String theUnit;
            theUnit = this.getUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unit", theUnit), currentHashCode, theUnit, this.isSetUnit());
        }
        {
            BigInteger theRadix;
            theRadix = this.getRadix();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "radix", theRadix), currentHashCode, theRadix, this.isSetRadix());
        }
        {
            BigInteger theFactor;
            theFactor = this.getFactor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "factor", theFactor), currentHashCode, theFactor, this.isSetFactor());
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
        if (draftCopy instanceof TimeIntervalLengthType) {
            final TimeIntervalLengthType copy = ((TimeIntervalLengthType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigDecimal sourceValue;
                    sourceValue = this.getValue();
                    BigDecimal copyValue = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
            {
                java.lang.Boolean unitShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUnit());
                if (unitShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceUnit;
                    sourceUnit = this.getUnit();
                    String copyUnit = ((String) strategy.copy(LocatorUtils.property(locator, "unit", sourceUnit), sourceUnit, this.isSetUnit()));
                    copy.setUnit(copyUnit);
                } else {
                    if (unitShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unit = null;
                    }
                }
            }
            {
                java.lang.Boolean radixShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRadix());
                if (radixShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceRadix;
                    sourceRadix = this.getRadix();
                    BigInteger copyRadix = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "radix", sourceRadix), sourceRadix, this.isSetRadix()));
                    copy.setRadix(copyRadix);
                } else {
                    if (radixShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.radix = null;
                    }
                }
            }
            {
                java.lang.Boolean factorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFactor());
                if (factorShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceFactor;
                    sourceFactor = this.getFactor();
                    BigInteger copyFactor = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "factor", sourceFactor), sourceFactor, this.isSetFactor()));
                    copy.setFactor(copyFactor);
                } else {
                    if (factorShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.factor = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeIntervalLengthType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof TimeIntervalLengthType) {
            final TimeIntervalLengthType target = this;
            final TimeIntervalLengthType leftObject = ((TimeIntervalLengthType) left);
            final TimeIntervalLengthType rightObject = ((TimeIntervalLengthType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigDecimal lhsValue;
                    lhsValue = leftObject.getValue();
                    BigDecimal rhsValue;
                    rhsValue = rightObject.getValue();
                    BigDecimal mergedValue = ((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
            {
                java.lang.Boolean unitShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUnit(), rightObject.isSetUnit());
                if (unitShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsUnit;
                    lhsUnit = leftObject.getUnit();
                    String rhsUnit;
                    rhsUnit = rightObject.getUnit();
                    String mergedUnit = ((String) strategy.merge(LocatorUtils.property(leftLocator, "unit", lhsUnit), LocatorUtils.property(rightLocator, "unit", rhsUnit), lhsUnit, rhsUnit, leftObject.isSetUnit(), rightObject.isSetUnit()));
                    target.setUnit(mergedUnit);
                } else {
                    if (unitShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unit = null;
                    }
                }
            }
            {
                java.lang.Boolean radixShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRadix(), rightObject.isSetRadix());
                if (radixShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsRadix;
                    lhsRadix = leftObject.getRadix();
                    BigInteger rhsRadix;
                    rhsRadix = rightObject.getRadix();
                    BigInteger mergedRadix = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "radix", lhsRadix), LocatorUtils.property(rightLocator, "radix", rhsRadix), lhsRadix, rhsRadix, leftObject.isSetRadix(), rightObject.isSetRadix()));
                    target.setRadix(mergedRadix);
                } else {
                    if (radixShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.radix = null;
                    }
                }
            }
            {
                java.lang.Boolean factorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFactor(), rightObject.isSetFactor());
                if (factorShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsFactor;
                    lhsFactor = leftObject.getFactor();
                    BigInteger rhsFactor;
                    rhsFactor = rightObject.getFactor();
                    BigInteger mergedFactor = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "factor", lhsFactor), LocatorUtils.property(rightLocator, "factor", rhsFactor), lhsFactor, rhsFactor, leftObject.isSetFactor(), rightObject.isSetFactor()));
                    target.setFactor(mergedFactor);
                } else {
                    if (factorShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.factor = null;
                    }
                }
            }
        }
    }

}
