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
 * A property with the content model of gml:AbstractRingPropertyType encapsulates a ring to represent the surface boundary property of a surface.
 * 
 * <p>Classe Java per AbstractRingPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractRingPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}AbstractRing"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractRingPropertyType", propOrder = {
    "abstractRing"
})
public class AbstractRingPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * An abstraction of a ring to support surface boundaries of different complexity.
     * The AbstractRing element is the abstract head of the substituition group for all closed boundaries of a surface patch.
     * 
     */
    @XmlElementRef(name = "AbstractRing", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractRingType> abstractRing;

    /**
     * An abstraction of a ring to support surface boundaries of different complexity.
     * The AbstractRing element is the abstract head of the substituition group for all closed boundaries of a surface patch.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractRingType }{@code >}
     *     {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}
     *     {@link JAXBElement }{@code <}{@link RingType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractRingType> getAbstractRing() {
        return abstractRing;
    }

    /**
     * Imposta il valore della proprietà abstractRing.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractRingType }{@code >}
     *     {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}
     *     {@link JAXBElement }{@code <}{@link RingType }{@code >}
     *     
     * @see #getAbstractRing()
     */
    public void setAbstractRing(JAXBElement<? extends AbstractRingType> value) {
        this.abstractRing = value;
    }

    public boolean isSetAbstractRing() {
        return (this.abstractRing!= null);
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
            JAXBElement<? extends AbstractRingType> theAbstractRing;
            theAbstractRing = this.getAbstractRing();
            strategy.appendField(locator, this, "abstractRing", buffer, theAbstractRing, this.isSetAbstractRing());
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
        final AbstractRingPropertyType that = ((AbstractRingPropertyType) object);
        {
            JAXBElement<? extends AbstractRingType> lhsAbstractRing;
            lhsAbstractRing = this.getAbstractRing();
            JAXBElement<? extends AbstractRingType> rhsAbstractRing;
            rhsAbstractRing = that.getAbstractRing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractRing", lhsAbstractRing), LocatorUtils.property(thatLocator, "abstractRing", rhsAbstractRing), lhsAbstractRing, rhsAbstractRing, this.isSetAbstractRing(), that.isSetAbstractRing())) {
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
            JAXBElement<? extends AbstractRingType> theAbstractRing;
            theAbstractRing = this.getAbstractRing();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractRing", theAbstractRing), currentHashCode, theAbstractRing, this.isSetAbstractRing());
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
        if (draftCopy instanceof AbstractRingPropertyType) {
            final AbstractRingPropertyType copy = ((AbstractRingPropertyType) draftCopy);
            {
                java.lang.Boolean abstractRingShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractRing());
                if (abstractRingShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends AbstractRingType> sourceAbstractRing;
                    sourceAbstractRing = this.getAbstractRing();
                    @SuppressWarnings("unchecked")
                    JAXBElement<? extends AbstractRingType> copyAbstractRing = ((JAXBElement<? extends AbstractRingType> ) strategy.copy(LocatorUtils.property(locator, "abstractRing", sourceAbstractRing), sourceAbstractRing, this.isSetAbstractRing()));
                    copy.setAbstractRing(copyAbstractRing);
                } else {
                    if (abstractRingShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.abstractRing = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new AbstractRingPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractRingPropertyType) {
            final AbstractRingPropertyType target = this;
            final AbstractRingPropertyType leftObject = ((AbstractRingPropertyType) left);
            final AbstractRingPropertyType rightObject = ((AbstractRingPropertyType) right);
            {
                java.lang.Boolean abstractRingShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractRing(), rightObject.isSetAbstractRing());
                if (abstractRingShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends AbstractRingType> lhsAbstractRing;
                    lhsAbstractRing = leftObject.getAbstractRing();
                    JAXBElement<? extends AbstractRingType> rhsAbstractRing;
                    rhsAbstractRing = rightObject.getAbstractRing();
                    JAXBElement<? extends AbstractRingType> mergedAbstractRing = ((JAXBElement<? extends AbstractRingType> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractRing", lhsAbstractRing), LocatorUtils.property(rightLocator, "abstractRing", rhsAbstractRing), lhsAbstractRing, rhsAbstractRing, leftObject.isSetAbstractRing(), rightObject.isSetAbstractRing()));
                    target.setAbstractRing(mergedAbstractRing);
                } else {
                    if (abstractRingShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.abstractRing = null;
                    }
                }
            }
        }
    }

}
