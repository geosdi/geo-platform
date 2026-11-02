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
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * gml:MeasureType supports recording an amount encoded as a value of XML Schema double, together with a units of measure indicated by an attribute uom, short for “units Of measure”. The value of the uom attribute identifies a reference system for the amount, usually a ratio or interval scale.
 * 
 * <p>Classe Java per MeasureType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MeasureType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>double">
 *       <attribute name="uom" use="required" type="{http://www.opengis.net/gml}UomIdentifier" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasureType", propOrder = {
    "value"
})
@XmlSeeAlso({
    AngleType.class,
    Quantity.class,
    LengthType.class,
    ScaleType.class,
    TimeType.class,
    GridLengthType.class,
    AreaType.class,
    VolumeType.class,
    SpeedType.class
})
public class MeasureType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlValue
    protected double value;
    @XmlAttribute(name = "uom", required = true)
    protected String uom;

    /**
     * Recupera il valore della proprietà value.
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return true;
    }

    /**
     * Recupera il valore della proprietà uom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUom() {
        return uom;
    }

    /**
     * Imposta il valore della proprietà uom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUom(String value) {
        this.uom = value;
    }

    public boolean isSetUom() {
        return (this.uom!= null);
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
            double theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, true);
        }
        {
            String theUom;
            theUom = this.getUom();
            strategy.appendField(locator, this, "uom", buffer, theUom, this.isSetUom());
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
        final MeasureType that = ((MeasureType) object);
        {
            double lhsValue;
            lhsValue = this.getValue();
            double rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, true, true)) {
                return false;
            }
        }
        {
            String lhsUom;
            lhsUom = this.getUom();
            String rhsUom;
            rhsUom = that.getUom();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "uom", lhsUom), LocatorUtils.property(thatLocator, "uom", rhsUom), lhsUom, rhsUom, this.isSetUom(), that.isSetUom())) {
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
            double theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, true);
        }
        {
            String theUom;
            theUom = this.getUom();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "uom", theUom), currentHashCode, theUom, this.isSetUom());
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
        if (draftCopy instanceof MeasureType) {
            final MeasureType copy = ((MeasureType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, true);
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    double sourceValue;
                    sourceValue = this.getValue();
                    double copyValue = strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, true);
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean uomShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUom());
                if (uomShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceUom;
                    sourceUom = this.getUom();
                    String copyUom = ((String) strategy.copy(LocatorUtils.property(locator, "uom", sourceUom), sourceUom, this.isSetUom()));
                    copy.setUom(copyUom);
                } else {
                    if (uomShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.uom = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new MeasureType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof MeasureType) {
            final MeasureType target = this;
            final MeasureType leftObject = ((MeasureType) left);
            final MeasureType rightObject = ((MeasureType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, true, true);
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    double lhsValue;
                    lhsValue = leftObject.getValue();
                    double rhsValue;
                    rhsValue = rightObject.getValue();
                    double mergedValue = ((double) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, true, true));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean uomShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUom(), rightObject.isSetUom());
                if (uomShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsUom;
                    lhsUom = leftObject.getUom();
                    String rhsUom;
                    rhsUom = rightObject.getUom();
                    String mergedUom = ((String) strategy.merge(LocatorUtils.property(leftLocator, "uom", lhsUom), LocatorUtils.property(rightLocator, "uom", rhsUom), lhsUom, rhsUom, leftObject.isSetUom(), rightObject.isSetUom()));
                    target.setUom(mergedUom);
                } else {
                    if (uomShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.uom = null;
                    }
                }
            }
        }
    }

}
