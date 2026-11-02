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
 * <p>Classe Java per DegreesType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DegreesType">
 *   <simpleContent>
 *     <extension base="<http://www.opengis.net/gml>DegreeValueType">
 *       <attribute name="direction">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             <enumeration value="N"/>
 *             <enumeration value="E"/>
 *             <enumeration value="S"/>
 *             <enumeration value="W"/>
 *             <enumeration value="+"/>
 *             <enumeration value="-"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreesType", propOrder = {
    "value"
})
public class DegreesType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlValue
    protected int value;
    @XmlAttribute(name = "direction")
    protected String direction;

    /**
     * Recupera il valore della proprietà value.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return true;
    }

    /**
     * Recupera il valore della proprietà direction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Imposta il valore della proprietà direction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    public boolean isSetDirection() {
        return (this.direction!= null);
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
            int theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, true);
        }
        {
            String theDirection;
            theDirection = this.getDirection();
            strategy.appendField(locator, this, "direction", buffer, theDirection, this.isSetDirection());
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
        final DegreesType that = ((DegreesType) object);
        {
            int lhsValue;
            lhsValue = this.getValue();
            int rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, true, true)) {
                return false;
            }
        }
        {
            String lhsDirection;
            lhsDirection = this.getDirection();
            String rhsDirection;
            rhsDirection = that.getDirection();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "direction", lhsDirection), LocatorUtils.property(thatLocator, "direction", rhsDirection), lhsDirection, rhsDirection, this.isSetDirection(), that.isSetDirection())) {
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
            int theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, true);
        }
        {
            String theDirection;
            theDirection = this.getDirection();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "direction", theDirection), currentHashCode, theDirection, this.isSetDirection());
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
        if (draftCopy instanceof DegreesType) {
            final DegreesType copy = ((DegreesType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, true);
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    int sourceValue;
                    sourceValue = this.getValue();
                    int copyValue = strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, true);
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean directionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDirection());
                if (directionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceDirection;
                    sourceDirection = this.getDirection();
                    String copyDirection = ((String) strategy.copy(LocatorUtils.property(locator, "direction", sourceDirection), sourceDirection, this.isSetDirection()));
                    copy.setDirection(copyDirection);
                } else {
                    if (directionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.direction = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new DegreesType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DegreesType) {
            final DegreesType target = this;
            final DegreesType leftObject = ((DegreesType) left);
            final DegreesType rightObject = ((DegreesType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, true, true);
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    int lhsValue;
                    lhsValue = leftObject.getValue();
                    int rhsValue;
                    rhsValue = rightObject.getValue();
                    int mergedValue = ((int) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, true, true));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean directionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDirection(), rightObject.isSetDirection());
                if (directionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsDirection;
                    lhsDirection = leftObject.getDirection();
                    String rhsDirection;
                    rhsDirection = rightObject.getDirection();
                    String mergedDirection = ((String) strategy.merge(LocatorUtils.property(leftLocator, "direction", lhsDirection), LocatorUtils.property(rightLocator, "direction", rhsDirection), lhsDirection, rhsDirection, leftObject.isSetDirection(), rightObject.isSetDirection()));
                    target.setDirection(mergedDirection);
                } else {
                    if (directionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.direction = null;
                    }
                }
            }
        }
    }

}
