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
import javax.xml.bind.annotation.XmlRootElement;
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
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="inverseFlattening" type="{http://www.opengis.net/gml}MeasureType"/>
 *         &lt;element name="semiMinorAxis" type="{http://www.opengis.net/gml}LengthType"/>
 *         &lt;element name="isSphere">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="sphere"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
public class SecondDefiningParameterElement
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
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
            MeasureType theInverseFlattening;
            theInverseFlattening = this.getInverseFlattening();
            strategy.appendField(locator, this, "inverseFlattening", buffer, theInverseFlattening);
        }
        {
            LengthType theSemiMinorAxis;
            theSemiMinorAxis = this.getSemiMinorAxis();
            strategy.appendField(locator, this, "semiMinorAxis", buffer, theSemiMinorAxis);
        }
        {
            String theIsSphere;
            theIsSphere = this.getIsSphere();
            strategy.appendField(locator, this, "isSphere", buffer, theIsSphere);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SecondDefiningParameterElement)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inverseFlattening", lhsInverseFlattening), LocatorUtils.property(thatLocator, "inverseFlattening", rhsInverseFlattening), lhsInverseFlattening, rhsInverseFlattening)) {
                return false;
            }
        }
        {
            LengthType lhsSemiMinorAxis;
            lhsSemiMinorAxis = this.getSemiMinorAxis();
            LengthType rhsSemiMinorAxis;
            rhsSemiMinorAxis = that.getSemiMinorAxis();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "semiMinorAxis", lhsSemiMinorAxis), LocatorUtils.property(thatLocator, "semiMinorAxis", rhsSemiMinorAxis), lhsSemiMinorAxis, rhsSemiMinorAxis)) {
                return false;
            }
        }
        {
            String lhsIsSphere;
            lhsIsSphere = this.getIsSphere();
            String rhsIsSphere;
            rhsIsSphere = that.getIsSphere();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isSphere", lhsIsSphere), LocatorUtils.property(thatLocator, "isSphere", rhsIsSphere), lhsIsSphere, rhsIsSphere)) {
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
            MeasureType theInverseFlattening;
            theInverseFlattening = this.getInverseFlattening();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inverseFlattening", theInverseFlattening), currentHashCode, theInverseFlattening);
        }
        {
            LengthType theSemiMinorAxis;
            theSemiMinorAxis = this.getSemiMinorAxis();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "semiMinorAxis", theSemiMinorAxis), currentHashCode, theSemiMinorAxis);
        }
        {
            String theIsSphere;
            theIsSphere = this.getIsSphere();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isSphere", theIsSphere), currentHashCode, theIsSphere);
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
        if (draftCopy instanceof SecondDefiningParameterElement) {
            final SecondDefiningParameterElement copy = ((SecondDefiningParameterElement) draftCopy);
            if (this.isSetInverseFlattening()) {
                MeasureType sourceInverseFlattening;
                sourceInverseFlattening = this.getInverseFlattening();
                MeasureType copyInverseFlattening = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "inverseFlattening", sourceInverseFlattening), sourceInverseFlattening));
                copy.setInverseFlattening(copyInverseFlattening);
            } else {
                copy.inverseFlattening = null;
            }
            if (this.isSetSemiMinorAxis()) {
                LengthType sourceSemiMinorAxis;
                sourceSemiMinorAxis = this.getSemiMinorAxis();
                LengthType copySemiMinorAxis = ((LengthType) strategy.copy(LocatorUtils.property(locator, "semiMinorAxis", sourceSemiMinorAxis), sourceSemiMinorAxis));
                copy.setSemiMinorAxis(copySemiMinorAxis);
            } else {
                copy.semiMinorAxis = null;
            }
            if (this.isSetIsSphere()) {
                String sourceIsSphere;
                sourceIsSphere = this.getIsSphere();
                String copyIsSphere = ((String) strategy.copy(LocatorUtils.property(locator, "isSphere", sourceIsSphere), sourceIsSphere));
                copy.setIsSphere(copyIsSphere);
            } else {
                copy.isSphere = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SecondDefiningParameterElement();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SecondDefiningParameterElement) {
            final SecondDefiningParameterElement target = this;
            final SecondDefiningParameterElement leftObject = ((SecondDefiningParameterElement) left);
            final SecondDefiningParameterElement rightObject = ((SecondDefiningParameterElement) right);
            {
                MeasureType lhsInverseFlattening;
                lhsInverseFlattening = leftObject.getInverseFlattening();
                MeasureType rhsInverseFlattening;
                rhsInverseFlattening = rightObject.getInverseFlattening();
                target.setInverseFlattening(((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "inverseFlattening", lhsInverseFlattening), LocatorUtils.property(rightLocator, "inverseFlattening", rhsInverseFlattening), lhsInverseFlattening, rhsInverseFlattening)));
            }
            {
                LengthType lhsSemiMinorAxis;
                lhsSemiMinorAxis = leftObject.getSemiMinorAxis();
                LengthType rhsSemiMinorAxis;
                rhsSemiMinorAxis = rightObject.getSemiMinorAxis();
                target.setSemiMinorAxis(((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "semiMinorAxis", lhsSemiMinorAxis), LocatorUtils.property(rightLocator, "semiMinorAxis", rhsSemiMinorAxis), lhsSemiMinorAxis, rhsSemiMinorAxis)));
            }
            {
                String lhsIsSphere;
                lhsIsSphere = leftObject.getIsSphere();
                String rhsIsSphere;
                rhsIsSphere = rightObject.getIsSphere();
                target.setIsSphere(((String) strategy.merge(LocatorUtils.property(leftLocator, "isSphere", lhsIsSphere), LocatorUtils.property(rightLocator, "isSphere", rhsIsSphere), lhsIsSphere, rhsIsSphere)));
            }
        }
    }

    public SecondDefiningParameterElement withInverseFlattening(MeasureType value) {
        setInverseFlattening(value);
        return this;
    }

    public SecondDefiningParameterElement withSemiMinorAxis(LengthType value) {
        setSemiMinorAxis(value);
        return this;
    }

    public SecondDefiningParameterElement withIsSphere(String value) {
        setIsSphere(value);
        return this;
    }

}
