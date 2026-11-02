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
 * A property with the content model of gml:LinearRingPropertyType encapsulates a linear ring to represent a component of a surface boundary.
 * 
 * <p>Classe Java per LinearRingPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LinearRingPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}LinearRing"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinearRingPropertyType", propOrder = {
    "linearRing"
})
public class LinearRingPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A LinearRing is defined by four or more coordinate tuples, with linear interpolation between them; the first and last coordinates shall be coincident. The number of direct positions in the list shall be at least four.
     * 
     */
    @XmlElement(name = "LinearRing", required = true)
    protected LinearRingType linearRing;

    /**
     * A LinearRing is defined by four or more coordinate tuples, with linear interpolation between them; the first and last coordinates shall be coincident. The number of direct positions in the list shall be at least four.
     * 
     * @return
     *     possible object is
     *     {@link LinearRingType }
     *     
     */
    public LinearRingType getLinearRing() {
        return linearRing;
    }

    /**
     * Imposta il valore della proprietà linearRing.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearRingType }
     *     
     * @see #getLinearRing()
     */
    public void setLinearRing(LinearRingType value) {
        this.linearRing = value;
    }

    public boolean isSetLinearRing() {
        return (this.linearRing!= null);
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
            LinearRingType theLinearRing;
            theLinearRing = this.getLinearRing();
            strategy.appendField(locator, this, "linearRing", buffer, theLinearRing, this.isSetLinearRing());
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
        final LinearRingPropertyType that = ((LinearRingPropertyType) object);
        {
            LinearRingType lhsLinearRing;
            lhsLinearRing = this.getLinearRing();
            LinearRingType rhsLinearRing;
            rhsLinearRing = that.getLinearRing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "linearRing", lhsLinearRing), LocatorUtils.property(thatLocator, "linearRing", rhsLinearRing), lhsLinearRing, rhsLinearRing, this.isSetLinearRing(), that.isSetLinearRing())) {
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
            LinearRingType theLinearRing;
            theLinearRing = this.getLinearRing();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "linearRing", theLinearRing), currentHashCode, theLinearRing, this.isSetLinearRing());
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
        if (draftCopy instanceof LinearRingPropertyType) {
            final LinearRingPropertyType copy = ((LinearRingPropertyType) draftCopy);
            {
                java.lang.Boolean linearRingShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLinearRing());
                if (linearRingShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    LinearRingType sourceLinearRing;
                    sourceLinearRing = this.getLinearRing();
                    LinearRingType copyLinearRing = ((LinearRingType) strategy.copy(LocatorUtils.property(locator, "linearRing", sourceLinearRing), sourceLinearRing, this.isSetLinearRing()));
                    copy.setLinearRing(copyLinearRing);
                } else {
                    if (linearRingShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.linearRing = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new LinearRingPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof LinearRingPropertyType) {
            final LinearRingPropertyType target = this;
            final LinearRingPropertyType leftObject = ((LinearRingPropertyType) left);
            final LinearRingPropertyType rightObject = ((LinearRingPropertyType) right);
            {
                java.lang.Boolean linearRingShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLinearRing(), rightObject.isSetLinearRing());
                if (linearRingShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    LinearRingType lhsLinearRing;
                    lhsLinearRing = leftObject.getLinearRing();
                    LinearRingType rhsLinearRing;
                    rhsLinearRing = rightObject.getLinearRing();
                    LinearRingType mergedLinearRing = ((LinearRingType) strategy.merge(LocatorUtils.property(leftLocator, "linearRing", lhsLinearRing), LocatorUtils.property(rightLocator, "linearRing", rhsLinearRing), lhsLinearRing, rhsLinearRing, leftObject.isSetLinearRing(), rightObject.isSetLinearRing()));
                    target.setLinearRing(mergedLinearRing);
                } else {
                    if (linearRingShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.linearRing = null;
                    }
                }
            }
        }
    }

}
