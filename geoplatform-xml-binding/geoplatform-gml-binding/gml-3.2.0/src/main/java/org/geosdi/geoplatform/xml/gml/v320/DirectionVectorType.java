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
 * Direction vectors are specified by providing components of a vector.
 * 
 * <p>Classe Java per DirectionVectorType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DirectionVectorType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element ref="{http://www.opengis.net/gml}vector"/>
 *         <sequence>
 *           <element name="horizontalAngle" type="{http://www.opengis.net/gml}AngleType"/>
 *           <element name="verticalAngle" type="{http://www.opengis.net/gml}AngleType"/>
 *         </sequence>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectionVectorType", propOrder = {
    "vector",
    "horizontalAngle",
    "verticalAngle"
})
public class DirectionVectorType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected VectorType vector;
    protected AngleType horizontalAngle;
    protected AngleType verticalAngle;

    /**
     * Recupera il valore della proprietà vector.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getVector() {
        return vector;
    }

    /**
     * Imposta il valore della proprietà vector.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setVector(VectorType value) {
        this.vector = value;
    }

    public boolean isSetVector() {
        return (this.vector!= null);
    }

    /**
     * Recupera il valore della proprietà horizontalAngle.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getHorizontalAngle() {
        return horizontalAngle;
    }

    /**
     * Imposta il valore della proprietà horizontalAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     */
    public void setHorizontalAngle(AngleType value) {
        this.horizontalAngle = value;
    }

    public boolean isSetHorizontalAngle() {
        return (this.horizontalAngle!= null);
    }

    /**
     * Recupera il valore della proprietà verticalAngle.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getVerticalAngle() {
        return verticalAngle;
    }

    /**
     * Imposta il valore della proprietà verticalAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     */
    public void setVerticalAngle(AngleType value) {
        this.verticalAngle = value;
    }

    public boolean isSetVerticalAngle() {
        return (this.verticalAngle!= null);
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
            VectorType theVector;
            theVector = this.getVector();
            strategy.appendField(locator, this, "vector", buffer, theVector, this.isSetVector());
        }
        {
            AngleType theHorizontalAngle;
            theHorizontalAngle = this.getHorizontalAngle();
            strategy.appendField(locator, this, "horizontalAngle", buffer, theHorizontalAngle, this.isSetHorizontalAngle());
        }
        {
            AngleType theVerticalAngle;
            theVerticalAngle = this.getVerticalAngle();
            strategy.appendField(locator, this, "verticalAngle", buffer, theVerticalAngle, this.isSetVerticalAngle());
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
        final DirectionVectorType that = ((DirectionVectorType) object);
        {
            VectorType lhsVector;
            lhsVector = this.getVector();
            VectorType rhsVector;
            rhsVector = that.getVector();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "vector", lhsVector), LocatorUtils.property(thatLocator, "vector", rhsVector), lhsVector, rhsVector, this.isSetVector(), that.isSetVector())) {
                return false;
            }
        }
        {
            AngleType lhsHorizontalAngle;
            lhsHorizontalAngle = this.getHorizontalAngle();
            AngleType rhsHorizontalAngle;
            rhsHorizontalAngle = that.getHorizontalAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "horizontalAngle", lhsHorizontalAngle), LocatorUtils.property(thatLocator, "horizontalAngle", rhsHorizontalAngle), lhsHorizontalAngle, rhsHorizontalAngle, this.isSetHorizontalAngle(), that.isSetHorizontalAngle())) {
                return false;
            }
        }
        {
            AngleType lhsVerticalAngle;
            lhsVerticalAngle = this.getVerticalAngle();
            AngleType rhsVerticalAngle;
            rhsVerticalAngle = that.getVerticalAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalAngle", lhsVerticalAngle), LocatorUtils.property(thatLocator, "verticalAngle", rhsVerticalAngle), lhsVerticalAngle, rhsVerticalAngle, this.isSetVerticalAngle(), that.isSetVerticalAngle())) {
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
            VectorType theVector;
            theVector = this.getVector();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "vector", theVector), currentHashCode, theVector, this.isSetVector());
        }
        {
            AngleType theHorizontalAngle;
            theHorizontalAngle = this.getHorizontalAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "horizontalAngle", theHorizontalAngle), currentHashCode, theHorizontalAngle, this.isSetHorizontalAngle());
        }
        {
            AngleType theVerticalAngle;
            theVerticalAngle = this.getVerticalAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalAngle", theVerticalAngle), currentHashCode, theVerticalAngle, this.isSetVerticalAngle());
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
        if (draftCopy instanceof DirectionVectorType) {
            final DirectionVectorType copy = ((DirectionVectorType) draftCopy);
            {
                java.lang.Boolean vectorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetVector());
                if (vectorShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    VectorType sourceVector;
                    sourceVector = this.getVector();
                    VectorType copyVector = ((VectorType) strategy.copy(LocatorUtils.property(locator, "vector", sourceVector), sourceVector, this.isSetVector()));
                    copy.setVector(copyVector);
                } else {
                    if (vectorShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.vector = null;
                    }
                }
            }
            {
                java.lang.Boolean horizontalAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetHorizontalAngle());
                if (horizontalAngleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AngleType sourceHorizontalAngle;
                    sourceHorizontalAngle = this.getHorizontalAngle();
                    AngleType copyHorizontalAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "horizontalAngle", sourceHorizontalAngle), sourceHorizontalAngle, this.isSetHorizontalAngle()));
                    copy.setHorizontalAngle(copyHorizontalAngle);
                } else {
                    if (horizontalAngleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.horizontalAngle = null;
                    }
                }
            }
            {
                java.lang.Boolean verticalAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetVerticalAngle());
                if (verticalAngleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AngleType sourceVerticalAngle;
                    sourceVerticalAngle = this.getVerticalAngle();
                    AngleType copyVerticalAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "verticalAngle", sourceVerticalAngle), sourceVerticalAngle, this.isSetVerticalAngle()));
                    copy.setVerticalAngle(copyVerticalAngle);
                } else {
                    if (verticalAngleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.verticalAngle = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new DirectionVectorType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DirectionVectorType) {
            final DirectionVectorType target = this;
            final DirectionVectorType leftObject = ((DirectionVectorType) left);
            final DirectionVectorType rightObject = ((DirectionVectorType) right);
            {
                java.lang.Boolean vectorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetVector(), rightObject.isSetVector());
                if (vectorShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    VectorType lhsVector;
                    lhsVector = leftObject.getVector();
                    VectorType rhsVector;
                    rhsVector = rightObject.getVector();
                    VectorType mergedVector = ((VectorType) strategy.merge(LocatorUtils.property(leftLocator, "vector", lhsVector), LocatorUtils.property(rightLocator, "vector", rhsVector), lhsVector, rhsVector, leftObject.isSetVector(), rightObject.isSetVector()));
                    target.setVector(mergedVector);
                } else {
                    if (vectorShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.vector = null;
                    }
                }
            }
            {
                java.lang.Boolean horizontalAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetHorizontalAngle(), rightObject.isSetHorizontalAngle());
                if (horizontalAngleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AngleType lhsHorizontalAngle;
                    lhsHorizontalAngle = leftObject.getHorizontalAngle();
                    AngleType rhsHorizontalAngle;
                    rhsHorizontalAngle = rightObject.getHorizontalAngle();
                    AngleType mergedHorizontalAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "horizontalAngle", lhsHorizontalAngle), LocatorUtils.property(rightLocator, "horizontalAngle", rhsHorizontalAngle), lhsHorizontalAngle, rhsHorizontalAngle, leftObject.isSetHorizontalAngle(), rightObject.isSetHorizontalAngle()));
                    target.setHorizontalAngle(mergedHorizontalAngle);
                } else {
                    if (horizontalAngleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.horizontalAngle = null;
                    }
                }
            }
            {
                java.lang.Boolean verticalAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetVerticalAngle(), rightObject.isSetVerticalAngle());
                if (verticalAngleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AngleType lhsVerticalAngle;
                    lhsVerticalAngle = leftObject.getVerticalAngle();
                    AngleType rhsVerticalAngle;
                    rhsVerticalAngle = rightObject.getVerticalAngle();
                    AngleType mergedVerticalAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "verticalAngle", lhsVerticalAngle), LocatorUtils.property(rightLocator, "verticalAngle", rhsVerticalAngle), lhsVerticalAngle, rhsVerticalAngle, leftObject.isSetVerticalAngle(), rightObject.isSetVerticalAngle()));
                    target.setVerticalAngle(mergedVerticalAngle);
                } else {
                    if (verticalAngleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.verticalAngle = null;
                    }
                }
            }
        }
    }

}
