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
 * <p>Classe Java per FormulaType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FormulaType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="a" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         <element name="b" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="c" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="d" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormulaType", propOrder = {
    "a",
    "b",
    "c",
    "d"
})
public class FormulaType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected Double a;
    protected double b;
    protected double c;
    protected Double d;

    /**
     * Recupera il valore della proprietà a.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getA() {
        return a;
    }

    /**
     * Imposta il valore della proprietà a.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setA(Double value) {
        this.a = value;
    }

    public boolean isSetA() {
        return (this.a!= null);
    }

    /**
     * Recupera il valore della proprietà b.
     * 
     */
    public double getB() {
        return b;
    }

    /**
     * Imposta il valore della proprietà b.
     * 
     */
    public void setB(double value) {
        this.b = value;
    }

    public boolean isSetB() {
        return true;
    }

    /**
     * Recupera il valore della proprietà c.
     * 
     */
    public double getC() {
        return c;
    }

    /**
     * Imposta il valore della proprietà c.
     * 
     */
    public void setC(double value) {
        this.c = value;
    }

    public boolean isSetC() {
        return true;
    }

    /**
     * Recupera il valore della proprietà d.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getD() {
        return d;
    }

    /**
     * Imposta il valore della proprietà d.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setD(Double value) {
        this.d = value;
    }

    public boolean isSetD() {
        return (this.d!= null);
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
            Double theA;
            theA = this.getA();
            strategy.appendField(locator, this, "a", buffer, theA, this.isSetA());
        }
        {
            double theB;
            theB = this.getB();
            strategy.appendField(locator, this, "b", buffer, theB, true);
        }
        {
            double theC;
            theC = this.getC();
            strategy.appendField(locator, this, "c", buffer, theC, true);
        }
        {
            Double theD;
            theD = this.getD();
            strategy.appendField(locator, this, "d", buffer, theD, this.isSetD());
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
        final FormulaType that = ((FormulaType) object);
        {
            Double lhsA;
            lhsA = this.getA();
            Double rhsA;
            rhsA = that.getA();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "a", lhsA), LocatorUtils.property(thatLocator, "a", rhsA), lhsA, rhsA, this.isSetA(), that.isSetA())) {
                return false;
            }
        }
        {
            double lhsB;
            lhsB = this.getB();
            double rhsB;
            rhsB = that.getB();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "b", lhsB), LocatorUtils.property(thatLocator, "b", rhsB), lhsB, rhsB, true, true)) {
                return false;
            }
        }
        {
            double lhsC;
            lhsC = this.getC();
            double rhsC;
            rhsC = that.getC();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "c", lhsC), LocatorUtils.property(thatLocator, "c", rhsC), lhsC, rhsC, true, true)) {
                return false;
            }
        }
        {
            Double lhsD;
            lhsD = this.getD();
            Double rhsD;
            rhsD = that.getD();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "d", lhsD), LocatorUtils.property(thatLocator, "d", rhsD), lhsD, rhsD, this.isSetD(), that.isSetD())) {
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
            Double theA;
            theA = this.getA();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "a", theA), currentHashCode, theA, this.isSetA());
        }
        {
            double theB;
            theB = this.getB();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "b", theB), currentHashCode, theB, true);
        }
        {
            double theC;
            theC = this.getC();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "c", theC), currentHashCode, theC, true);
        }
        {
            Double theD;
            theD = this.getD();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "d", theD), currentHashCode, theD, this.isSetD());
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
        if (draftCopy instanceof FormulaType) {
            final FormulaType copy = ((FormulaType) draftCopy);
            {
                java.lang.Boolean aShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetA());
                if (aShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    Double sourceA;
                    sourceA = this.getA();
                    Double copyA = ((Double) strategy.copy(LocatorUtils.property(locator, "a", sourceA), sourceA, this.isSetA()));
                    copy.setA(copyA);
                } else {
                    if (aShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.a = null;
                    }
                }
            }
            {
                java.lang.Boolean bShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, true);
                if (bShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    double sourceB;
                    sourceB = this.getB();
                    double copyB = strategy.copy(LocatorUtils.property(locator, "b", sourceB), sourceB, true);
                    copy.setB(copyB);
                } else {
                    if (bShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean cShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, true);
                if (cShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    double sourceC;
                    sourceC = this.getC();
                    double copyC = strategy.copy(LocatorUtils.property(locator, "c", sourceC), sourceC, true);
                    copy.setC(copyC);
                } else {
                    if (cShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean dShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetD());
                if (dShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    Double sourceD;
                    sourceD = this.getD();
                    Double copyD = ((Double) strategy.copy(LocatorUtils.property(locator, "d", sourceD), sourceD, this.isSetD()));
                    copy.setD(copyD);
                } else {
                    if (dShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.d = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new FormulaType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FormulaType) {
            final FormulaType target = this;
            final FormulaType leftObject = ((FormulaType) left);
            final FormulaType rightObject = ((FormulaType) right);
            {
                java.lang.Boolean aShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetA(), rightObject.isSetA());
                if (aShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    Double lhsA;
                    lhsA = leftObject.getA();
                    Double rhsA;
                    rhsA = rightObject.getA();
                    Double mergedA = ((Double) strategy.merge(LocatorUtils.property(leftLocator, "a", lhsA), LocatorUtils.property(rightLocator, "a", rhsA), lhsA, rhsA, leftObject.isSetA(), rightObject.isSetA()));
                    target.setA(mergedA);
                } else {
                    if (aShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.a = null;
                    }
                }
            }
            {
                java.lang.Boolean bShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, true, true);
                if (bShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    double lhsB;
                    lhsB = leftObject.getB();
                    double rhsB;
                    rhsB = rightObject.getB();
                    double mergedB = ((double) strategy.merge(LocatorUtils.property(leftLocator, "b", lhsB), LocatorUtils.property(rightLocator, "b", rhsB), lhsB, rhsB, true, true));
                    target.setB(mergedB);
                } else {
                    if (bShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean cShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, true, true);
                if (cShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    double lhsC;
                    lhsC = leftObject.getC();
                    double rhsC;
                    rhsC = rightObject.getC();
                    double mergedC = ((double) strategy.merge(LocatorUtils.property(leftLocator, "c", lhsC), LocatorUtils.property(rightLocator, "c", rhsC), lhsC, rhsC, true, true));
                    target.setC(mergedC);
                } else {
                    if (cShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean dShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetD(), rightObject.isSetD());
                if (dShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    Double lhsD;
                    lhsD = leftObject.getD();
                    Double rhsD;
                    rhsD = rightObject.getD();
                    Double mergedD = ((Double) strategy.merge(LocatorUtils.property(leftLocator, "d", lhsD), LocatorUtils.property(rightLocator, "d", rhsD), lhsD, rhsD, leftObject.isSetD(), rightObject.isSetD()));
                    target.setD(mergedD);
                } else {
                    if (dShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.d = null;
                    }
                }
            }
        }
    }

}
