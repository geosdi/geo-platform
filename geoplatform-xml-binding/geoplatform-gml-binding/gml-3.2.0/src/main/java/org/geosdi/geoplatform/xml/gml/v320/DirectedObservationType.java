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
 * <p>Classe Java per DirectedObservationType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DirectedObservationType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}ObservationType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}direction"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectedObservationType", propOrder = {
    "direction"
})
@XmlSeeAlso({
    DirectedObservationAtDistanceType.class
})
public class DirectedObservationType
    extends ObservationType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The property gml:direction is intended as a pre-defined property expressing a direction to be assigned to features defined in a GML application schema.
     * 
     */
    @XmlElement(required = true)
    protected DirectionPropertyType direction;

    /**
     * The property gml:direction is intended as a pre-defined property expressing a direction to be assigned to features defined in a GML application schema.
     * 
     * @return
     *     possible object is
     *     {@link DirectionPropertyType }
     *     
     */
    public DirectionPropertyType getDirection() {
        return direction;
    }

    /**
     * Imposta il valore della proprietà direction.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectionPropertyType }
     *     
     * @see #getDirection()
     */
    public void setDirection(DirectionPropertyType value) {
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
        super.appendFields(locator, buffer, strategy);
        {
            DirectionPropertyType theDirection;
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DirectedObservationType that = ((DirectedObservationType) object);
        {
            DirectionPropertyType lhsDirection;
            lhsDirection = this.getDirection();
            DirectionPropertyType rhsDirection;
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            DirectionPropertyType theDirection;
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof DirectedObservationType) {
            final DirectedObservationType copy = ((DirectedObservationType) draftCopy);
            {
                java.lang.Boolean directionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDirection());
                if (directionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectionPropertyType sourceDirection;
                    sourceDirection = this.getDirection();
                    DirectionPropertyType copyDirection = ((DirectionPropertyType) strategy.copy(LocatorUtils.property(locator, "direction", sourceDirection), sourceDirection, this.isSetDirection()));
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
        return new DirectedObservationType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DirectedObservationType) {
            final DirectedObservationType target = this;
            final DirectedObservationType leftObject = ((DirectedObservationType) left);
            final DirectedObservationType rightObject = ((DirectedObservationType) right);
            {
                java.lang.Boolean directionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDirection(), rightObject.isSetDirection());
                if (directionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectionPropertyType lhsDirection;
                    lhsDirection = leftObject.getDirection();
                    DirectionPropertyType rhsDirection;
                    rhsDirection = rightObject.getDirection();
                    DirectionPropertyType mergedDirection = ((DirectionPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "direction", lhsDirection), LocatorUtils.property(rightLocator, "direction", rhsDirection), lhsDirection, rhsDirection, leftObject.isSetDirection(), rightObject.isSetDirection()));
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
