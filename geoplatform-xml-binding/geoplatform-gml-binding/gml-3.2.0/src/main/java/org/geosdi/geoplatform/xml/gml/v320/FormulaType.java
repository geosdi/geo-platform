/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Classe Java per FormulaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FormulaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="b" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="c" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="d" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
public class FormulaType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
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

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            Double theA;
            theA = this.getA();
            strategy.appendField(locator, this, "a", buffer, theA);
        }
        {
            double theB;
            theB = this.getB();
            strategy.appendField(locator, this, "b", buffer, theB);
        }
        {
            double theC;
            theC = this.getC();
            strategy.appendField(locator, this, "c", buffer, theC);
        }
        {
            Double theD;
            theD = this.getD();
            strategy.appendField(locator, this, "d", buffer, theD);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FormulaType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "a", lhsA), LocatorUtils.property(thatLocator, "a", rhsA), lhsA, rhsA)) {
                return false;
            }
        }
        {
            double lhsB;
            lhsB = this.getB();
            double rhsB;
            rhsB = that.getB();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "b", lhsB), LocatorUtils.property(thatLocator, "b", rhsB), lhsB, rhsB)) {
                return false;
            }
        }
        {
            double lhsC;
            lhsC = this.getC();
            double rhsC;
            rhsC = that.getC();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "c", lhsC), LocatorUtils.property(thatLocator, "c", rhsC), lhsC, rhsC)) {
                return false;
            }
        }
        {
            Double lhsD;
            lhsD = this.getD();
            Double rhsD;
            rhsD = that.getD();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "d", lhsD), LocatorUtils.property(thatLocator, "d", rhsD), lhsD, rhsD)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            Double theA;
            theA = this.getA();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "a", theA), currentHashCode, theA);
        }
        {
            double theB;
            theB = this.getB();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "b", theB), currentHashCode, theB);
        }
        {
            double theC;
            theC = this.getC();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "c", theC), currentHashCode, theC);
        }
        {
            Double theD;
            theD = this.getD();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "d", theD), currentHashCode, theD);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof FormulaType) {
            final FormulaType copy = ((FormulaType) draftCopy);
            if (this.isSetA()) {
                Double sourceA;
                sourceA = this.getA();
                Double copyA = ((Double) strategy.copy(LocatorUtils.property(locator, "a", sourceA), sourceA));
                copy.setA(copyA);
            } else {
                copy.a = null;
            }
            if (this.isSetB()) {
                double sourceB;
                sourceB = this.getB();
                double copyB = strategy.copy(LocatorUtils.property(locator, "b", sourceB), sourceB);
                copy.setB(copyB);
            } else {
            }
            if (this.isSetC()) {
                double sourceC;
                sourceC = this.getC();
                double copyC = strategy.copy(LocatorUtils.property(locator, "c", sourceC), sourceC);
                copy.setC(copyC);
            } else {
            }
            if (this.isSetD()) {
                Double sourceD;
                sourceD = this.getD();
                Double copyD = ((Double) strategy.copy(LocatorUtils.property(locator, "d", sourceD), sourceD));
                copy.setD(copyD);
            } else {
                copy.d = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new FormulaType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FormulaType) {
            final FormulaType target = this;
            final FormulaType leftObject = ((FormulaType) left);
            final FormulaType rightObject = ((FormulaType) right);
            {
                Double lhsA;
                lhsA = leftObject.getA();
                Double rhsA;
                rhsA = rightObject.getA();
                target.setA(((Double) strategy.merge(LocatorUtils.property(leftLocator, "a", lhsA), LocatorUtils.property(rightLocator, "a", rhsA), lhsA, rhsA)));
            }
            {
                double lhsB;
                lhsB = leftObject.getB();
                double rhsB;
                rhsB = rightObject.getB();
                target.setB(((double) strategy.merge(LocatorUtils.property(leftLocator, "b", lhsB), LocatorUtils.property(rightLocator, "b", rhsB), lhsB, rhsB)));
            }
            {
                double lhsC;
                lhsC = leftObject.getC();
                double rhsC;
                rhsC = rightObject.getC();
                target.setC(((double) strategy.merge(LocatorUtils.property(leftLocator, "c", lhsC), LocatorUtils.property(rightLocator, "c", rhsC), lhsC, rhsC)));
            }
            {
                Double lhsD;
                lhsD = leftObject.getD();
                Double rhsD;
                rhsD = rightObject.getD();
                target.setD(((Double) strategy.merge(LocatorUtils.property(leftLocator, "d", lhsD), LocatorUtils.property(rightLocator, "d", rhsD), lhsD, rhsD)));
            }
        }
    }

    public FormulaType withA(Double value) {
        setA(value);
        return this;
    }

    public FormulaType withB(double value) {
        setB(value);
        return this;
    }

    public FormulaType withC(double value) {
        setC(value);
        return this;
    }

    public FormulaType withD(Double value) {
        setD(value);
        return this;
    }

}
