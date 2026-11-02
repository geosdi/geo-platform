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
 * This type is deprecated for tuples with ordinate values that are numbers.
 * CoordinatesType is a text string, intended to be used to record an array of tuples or coordinates. 
 * While it is not possible to enforce the internal structure of the string through schema validation, some optional attributes have been provided in previous versions of GML to support a description of the internal structure. These attributes are deprecated. The attributes were intended to be used as follows:
 * Decimal	symbol used for a decimal point (default=”.” a stop or period)
 * cs        	symbol used to separate components within a tuple or coordinate string (default=”,” a comma)
 * ts        	symbol used to separate tuples or coordinate strings (default=” “ a space)
 * Since it is based on the XML Schema string type, CoordinatesType may be used in the construction of tables of tuples or arrays of tuples, including ones that contain mixed text and numeric values.
 * 
 * <p>Classe Java per CoordinatesType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CoordinatesType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>string">
 *       <attribute name="decimal" type="{http://www.w3.org/2001/XMLSchema}string" default="." />
 *       <attribute name="cs" type="{http://www.w3.org/2001/XMLSchema}string" default="," />
 *       <attribute name="ts" type="{http://www.w3.org/2001/XMLSchema}string" default=" " />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinatesType", propOrder = {
    "value"
})
public class CoordinatesType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlValue
    protected String value;
    @XmlAttribute(name = "decimal")
    protected String decimal;
    @XmlAttribute(name = "cs")
    protected String cs;
    @XmlAttribute(name = "ts")
    protected String ts;

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Recupera il valore della proprietà decimal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecimal() {
        if (decimal == null) {
            return ".";
        } else {
            return decimal;
        }
    }

    /**
     * Imposta il valore della proprietà decimal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecimal(String value) {
        this.decimal = value;
    }

    public boolean isSetDecimal() {
        return (this.decimal!= null);
    }

    /**
     * Recupera il valore della proprietà cs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCs() {
        if (cs == null) {
            return ",";
        } else {
            return cs;
        }
    }

    /**
     * Imposta il valore della proprietà cs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCs(String value) {
        this.cs = value;
    }

    public boolean isSetCs() {
        return (this.cs!= null);
    }

    /**
     * Recupera il valore della proprietà ts.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTs() {
        if (ts == null) {
            return " ";
        } else {
            return ts;
        }
    }

    /**
     * Imposta il valore della proprietà ts.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTs(String value) {
        this.ts = value;
    }

    public boolean isSetTs() {
        return (this.ts!= null);
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
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theDecimal;
            theDecimal = this.getDecimal();
            strategy.appendField(locator, this, "decimal", buffer, theDecimal, this.isSetDecimal());
        }
        {
            String theCs;
            theCs = this.getCs();
            strategy.appendField(locator, this, "cs", buffer, theCs, this.isSetCs());
        }
        {
            String theTs;
            theTs = this.getTs();
            strategy.appendField(locator, this, "ts", buffer, theTs, this.isSetTs());
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
        final CoordinatesType that = ((CoordinatesType) object);
        {
            String lhsValue;
            lhsValue = this.getValue();
            String rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            String lhsDecimal;
            lhsDecimal = this.getDecimal();
            String rhsDecimal;
            rhsDecimal = that.getDecimal();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "decimal", lhsDecimal), LocatorUtils.property(thatLocator, "decimal", rhsDecimal), lhsDecimal, rhsDecimal, this.isSetDecimal(), that.isSetDecimal())) {
                return false;
            }
        }
        {
            String lhsCs;
            lhsCs = this.getCs();
            String rhsCs;
            rhsCs = that.getCs();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cs", lhsCs), LocatorUtils.property(thatLocator, "cs", rhsCs), lhsCs, rhsCs, this.isSetCs(), that.isSetCs())) {
                return false;
            }
        }
        {
            String lhsTs;
            lhsTs = this.getTs();
            String rhsTs;
            rhsTs = that.getTs();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ts", lhsTs), LocatorUtils.property(thatLocator, "ts", rhsTs), lhsTs, rhsTs, this.isSetTs(), that.isSetTs())) {
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
            String theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            String theDecimal;
            theDecimal = this.getDecimal();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "decimal", theDecimal), currentHashCode, theDecimal, this.isSetDecimal());
        }
        {
            String theCs;
            theCs = this.getCs();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cs", theCs), currentHashCode, theCs, this.isSetCs());
        }
        {
            String theTs;
            theTs = this.getTs();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ts", theTs), currentHashCode, theTs, this.isSetTs());
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
        if (draftCopy instanceof CoordinatesType) {
            final CoordinatesType copy = ((CoordinatesType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceValue;
                    sourceValue = this.getValue();
                    String copyValue = ((String) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
            {
                java.lang.Boolean decimalShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDecimal());
                if (decimalShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceDecimal;
                    sourceDecimal = this.getDecimal();
                    String copyDecimal = ((String) strategy.copy(LocatorUtils.property(locator, "decimal", sourceDecimal), sourceDecimal, this.isSetDecimal()));
                    copy.setDecimal(copyDecimal);
                } else {
                    if (decimalShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.decimal = null;
                    }
                }
            }
            {
                java.lang.Boolean csShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCs());
                if (csShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceCs;
                    sourceCs = this.getCs();
                    String copyCs = ((String) strategy.copy(LocatorUtils.property(locator, "cs", sourceCs), sourceCs, this.isSetCs()));
                    copy.setCs(copyCs);
                } else {
                    if (csShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.cs = null;
                    }
                }
            }
            {
                java.lang.Boolean tsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTs());
                if (tsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceTs;
                    sourceTs = this.getTs();
                    String copyTs = ((String) strategy.copy(LocatorUtils.property(locator, "ts", sourceTs), sourceTs, this.isSetTs()));
                    copy.setTs(copyTs);
                } else {
                    if (tsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.ts = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CoordinatesType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof CoordinatesType) {
            final CoordinatesType target = this;
            final CoordinatesType leftObject = ((CoordinatesType) left);
            final CoordinatesType rightObject = ((CoordinatesType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsValue;
                    lhsValue = leftObject.getValue();
                    String rhsValue;
                    rhsValue = rightObject.getValue();
                    String mergedValue = ((String) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
            {
                java.lang.Boolean decimalShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDecimal(), rightObject.isSetDecimal());
                if (decimalShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsDecimal;
                    lhsDecimal = leftObject.getDecimal();
                    String rhsDecimal;
                    rhsDecimal = rightObject.getDecimal();
                    String mergedDecimal = ((String) strategy.merge(LocatorUtils.property(leftLocator, "decimal", lhsDecimal), LocatorUtils.property(rightLocator, "decimal", rhsDecimal), lhsDecimal, rhsDecimal, leftObject.isSetDecimal(), rightObject.isSetDecimal()));
                    target.setDecimal(mergedDecimal);
                } else {
                    if (decimalShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.decimal = null;
                    }
                }
            }
            {
                java.lang.Boolean csShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCs(), rightObject.isSetCs());
                if (csShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsCs;
                    lhsCs = leftObject.getCs();
                    String rhsCs;
                    rhsCs = rightObject.getCs();
                    String mergedCs = ((String) strategy.merge(LocatorUtils.property(leftLocator, "cs", lhsCs), LocatorUtils.property(rightLocator, "cs", rhsCs), lhsCs, rhsCs, leftObject.isSetCs(), rightObject.isSetCs()));
                    target.setCs(mergedCs);
                } else {
                    if (csShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.cs = null;
                    }
                }
            }
            {
                java.lang.Boolean tsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTs(), rightObject.isSetTs());
                if (tsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsTs;
                    lhsTs = leftObject.getTs();
                    String rhsTs;
                    rhsTs = rightObject.getTs();
                    String mergedTs = ((String) strategy.merge(LocatorUtils.property(leftLocator, "ts", lhsTs), LocatorUtils.property(rightLocator, "ts", rhsTs), lhsTs, rhsTs, leftObject.isSetTs(), rightObject.isSetTs()));
                    target.setTs(mergedTs);
                } else {
                    if (tsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.ts = null;
                    }
                }
            }
        }
    }

}
