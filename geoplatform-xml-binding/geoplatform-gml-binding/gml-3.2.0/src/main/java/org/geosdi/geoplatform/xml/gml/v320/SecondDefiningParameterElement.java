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
import jakarta.xml.bind.annotation.XmlRootElement;
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
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="inverseFlattening" type="{http://www.opengis.net/gml}MeasureType"/>
 *         <element name="semiMinorAxis" type="{http://www.opengis.net/gml}LengthType"/>
 *         <element name="isSphere">
 *           <simpleType>
 *             <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               <enumeration value="sphere"/>
 *             </restriction>
 *           </simpleType>
 *         </element>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "inverseFlattening",
    "semiMinorAxis",
    "isSphere"
})
@XmlRootElement(name = "SecondDefiningParameter")
public class SecondDefiningParameterElement implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected MeasureType inverseFlattening;
    protected LengthType semiMinorAxis;
    protected String isSphere;

    /**
     * Recupera il valore della proprietà inverseFlattening.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getInverseFlattening() {
        return inverseFlattening;
    }

    /**
     * Imposta il valore della proprietà inverseFlattening.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setInverseFlattening(MeasureType value) {
        this.inverseFlattening = value;
    }

    public boolean isSetInverseFlattening() {
        return (this.inverseFlattening!= null);
    }

    /**
     * Recupera il valore della proprietà semiMinorAxis.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getSemiMinorAxis() {
        return semiMinorAxis;
    }

    /**
     * Imposta il valore della proprietà semiMinorAxis.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setSemiMinorAxis(LengthType value) {
        this.semiMinorAxis = value;
    }

    public boolean isSetSemiMinorAxis() {
        return (this.semiMinorAxis!= null);
    }

    /**
     * Recupera il valore della proprietà isSphere.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSphere() {
        return isSphere;
    }

    /**
     * Imposta il valore della proprietà isSphere.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSphere(String value) {
        this.isSphere = value;
    }

    public boolean isSetIsSphere() {
        return (this.isSphere!= null);
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
            MeasureType theInverseFlattening;
            theInverseFlattening = this.getInverseFlattening();
            strategy.appendField(locator, this, "inverseFlattening", buffer, theInverseFlattening, this.isSetInverseFlattening());
        }
        {
            LengthType theSemiMinorAxis;
            theSemiMinorAxis = this.getSemiMinorAxis();
            strategy.appendField(locator, this, "semiMinorAxis", buffer, theSemiMinorAxis, this.isSetSemiMinorAxis());
        }
        {
            String theIsSphere;
            theIsSphere = this.getIsSphere();
            strategy.appendField(locator, this, "isSphere", buffer, theIsSphere, this.isSetIsSphere());
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
        final SecondDefiningParameterElement that = ((SecondDefiningParameterElement) object);
        {
            MeasureType lhsInverseFlattening;
            lhsInverseFlattening = this.getInverseFlattening();
            MeasureType rhsInverseFlattening;
            rhsInverseFlattening = that.getInverseFlattening();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inverseFlattening", lhsInverseFlattening), LocatorUtils.property(thatLocator, "inverseFlattening", rhsInverseFlattening), lhsInverseFlattening, rhsInverseFlattening, this.isSetInverseFlattening(), that.isSetInverseFlattening())) {
                return false;
            }
        }
        {
            LengthType lhsSemiMinorAxis;
            lhsSemiMinorAxis = this.getSemiMinorAxis();
            LengthType rhsSemiMinorAxis;
            rhsSemiMinorAxis = that.getSemiMinorAxis();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "semiMinorAxis", lhsSemiMinorAxis), LocatorUtils.property(thatLocator, "semiMinorAxis", rhsSemiMinorAxis), lhsSemiMinorAxis, rhsSemiMinorAxis, this.isSetSemiMinorAxis(), that.isSetSemiMinorAxis())) {
                return false;
            }
        }
        {
            String lhsIsSphere;
            lhsIsSphere = this.getIsSphere();
            String rhsIsSphere;
            rhsIsSphere = that.getIsSphere();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isSphere", lhsIsSphere), LocatorUtils.property(thatLocator, "isSphere", rhsIsSphere), lhsIsSphere, rhsIsSphere, this.isSetIsSphere(), that.isSetIsSphere())) {
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
            MeasureType theInverseFlattening;
            theInverseFlattening = this.getInverseFlattening();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inverseFlattening", theInverseFlattening), currentHashCode, theInverseFlattening, this.isSetInverseFlattening());
        }
        {
            LengthType theSemiMinorAxis;
            theSemiMinorAxis = this.getSemiMinorAxis();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "semiMinorAxis", theSemiMinorAxis), currentHashCode, theSemiMinorAxis, this.isSetSemiMinorAxis());
        }
        {
            String theIsSphere;
            theIsSphere = this.getIsSphere();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isSphere", theIsSphere), currentHashCode, theIsSphere, this.isSetIsSphere());
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
        if (draftCopy instanceof SecondDefiningParameterElement) {
            final SecondDefiningParameterElement copy = ((SecondDefiningParameterElement) draftCopy);
            {
                java.lang.Boolean inverseFlatteningShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetInverseFlattening());
                if (inverseFlatteningShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType sourceInverseFlattening;
                    sourceInverseFlattening = this.getInverseFlattening();
                    MeasureType copyInverseFlattening = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "inverseFlattening", sourceInverseFlattening), sourceInverseFlattening, this.isSetInverseFlattening()));
                    copy.setInverseFlattening(copyInverseFlattening);
                } else {
                    if (inverseFlatteningShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.inverseFlattening = null;
                    }
                }
            }
            {
                java.lang.Boolean semiMinorAxisShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSemiMinorAxis());
                if (semiMinorAxisShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    LengthType sourceSemiMinorAxis;
                    sourceSemiMinorAxis = this.getSemiMinorAxis();
                    LengthType copySemiMinorAxis = ((LengthType) strategy.copy(LocatorUtils.property(locator, "semiMinorAxis", sourceSemiMinorAxis), sourceSemiMinorAxis, this.isSetSemiMinorAxis()));
                    copy.setSemiMinorAxis(copySemiMinorAxis);
                } else {
                    if (semiMinorAxisShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.semiMinorAxis = null;
                    }
                }
            }
            {
                java.lang.Boolean isSphereShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIsSphere());
                if (isSphereShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceIsSphere;
                    sourceIsSphere = this.getIsSphere();
                    String copyIsSphere = ((String) strategy.copy(LocatorUtils.property(locator, "isSphere", sourceIsSphere), sourceIsSphere, this.isSetIsSphere()));
                    copy.setIsSphere(copyIsSphere);
                } else {
                    if (isSphereShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.isSphere = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new SecondDefiningParameterElement();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SecondDefiningParameterElement) {
            final SecondDefiningParameterElement target = this;
            final SecondDefiningParameterElement leftObject = ((SecondDefiningParameterElement) left);
            final SecondDefiningParameterElement rightObject = ((SecondDefiningParameterElement) right);
            {
                java.lang.Boolean inverseFlatteningShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetInverseFlattening(), rightObject.isSetInverseFlattening());
                if (inverseFlatteningShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType lhsInverseFlattening;
                    lhsInverseFlattening = leftObject.getInverseFlattening();
                    MeasureType rhsInverseFlattening;
                    rhsInverseFlattening = rightObject.getInverseFlattening();
                    MeasureType mergedInverseFlattening = ((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "inverseFlattening", lhsInverseFlattening), LocatorUtils.property(rightLocator, "inverseFlattening", rhsInverseFlattening), lhsInverseFlattening, rhsInverseFlattening, leftObject.isSetInverseFlattening(), rightObject.isSetInverseFlattening()));
                    target.setInverseFlattening(mergedInverseFlattening);
                } else {
                    if (inverseFlatteningShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.inverseFlattening = null;
                    }
                }
            }
            {
                java.lang.Boolean semiMinorAxisShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSemiMinorAxis(), rightObject.isSetSemiMinorAxis());
                if (semiMinorAxisShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    LengthType lhsSemiMinorAxis;
                    lhsSemiMinorAxis = leftObject.getSemiMinorAxis();
                    LengthType rhsSemiMinorAxis;
                    rhsSemiMinorAxis = rightObject.getSemiMinorAxis();
                    LengthType mergedSemiMinorAxis = ((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "semiMinorAxis", lhsSemiMinorAxis), LocatorUtils.property(rightLocator, "semiMinorAxis", rhsSemiMinorAxis), lhsSemiMinorAxis, rhsSemiMinorAxis, leftObject.isSetSemiMinorAxis(), rightObject.isSetSemiMinorAxis()));
                    target.setSemiMinorAxis(mergedSemiMinorAxis);
                } else {
                    if (semiMinorAxisShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.semiMinorAxis = null;
                    }
                }
            }
            {
                java.lang.Boolean isSphereShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIsSphere(), rightObject.isSetIsSphere());
                if (isSphereShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsIsSphere;
                    lhsIsSphere = leftObject.getIsSphere();
                    String rhsIsSphere;
                    rhsIsSphere = rightObject.getIsSphere();
                    String mergedIsSphere = ((String) strategy.merge(LocatorUtils.property(leftLocator, "isSphere", lhsIsSphere), LocatorUtils.property(rightLocator, "isSphere", rhsIsSphere), lhsIsSphere, rhsIsSphere, leftObject.isSetIsSphere(), rightObject.isSetIsSphere()));
                    target.setIsSphere(mergedIsSphere);
                } else {
                    if (isSphereShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.isSphere = null;
                    }
                }
            }
        }
    }

}
