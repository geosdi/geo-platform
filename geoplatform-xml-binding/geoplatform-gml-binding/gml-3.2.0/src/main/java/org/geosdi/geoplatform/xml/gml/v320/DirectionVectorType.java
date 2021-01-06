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
 * Direction vectors are specified by providing components of a vector.
 * 
 * <p>Classe Java per DirectionVectorType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DirectionVectorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/gml}vector"/>
 *         &lt;sequence>
 *           &lt;element name="horizontalAngle" type="{http://www.opengis.net/gml}AngleType"/>
 *           &lt;element name="verticalAngle" type="{http://www.opengis.net/gml}AngleType"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectionVectorType", propOrder = {
    "vector",
    "horizontalAngle",
    "verticalAngle"
})
public class DirectionVectorType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
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
            VectorType theVector;
            theVector = this.getVector();
            strategy.appendField(locator, this, "vector", buffer, theVector);
        }
        {
            AngleType theHorizontalAngle;
            theHorizontalAngle = this.getHorizontalAngle();
            strategy.appendField(locator, this, "horizontalAngle", buffer, theHorizontalAngle);
        }
        {
            AngleType theVerticalAngle;
            theVerticalAngle = this.getVerticalAngle();
            strategy.appendField(locator, this, "verticalAngle", buffer, theVerticalAngle);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DirectionVectorType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "vector", lhsVector), LocatorUtils.property(thatLocator, "vector", rhsVector), lhsVector, rhsVector)) {
                return false;
            }
        }
        {
            AngleType lhsHorizontalAngle;
            lhsHorizontalAngle = this.getHorizontalAngle();
            AngleType rhsHorizontalAngle;
            rhsHorizontalAngle = that.getHorizontalAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "horizontalAngle", lhsHorizontalAngle), LocatorUtils.property(thatLocator, "horizontalAngle", rhsHorizontalAngle), lhsHorizontalAngle, rhsHorizontalAngle)) {
                return false;
            }
        }
        {
            AngleType lhsVerticalAngle;
            lhsVerticalAngle = this.getVerticalAngle();
            AngleType rhsVerticalAngle;
            rhsVerticalAngle = that.getVerticalAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalAngle", lhsVerticalAngle), LocatorUtils.property(thatLocator, "verticalAngle", rhsVerticalAngle), lhsVerticalAngle, rhsVerticalAngle)) {
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
            VectorType theVector;
            theVector = this.getVector();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "vector", theVector), currentHashCode, theVector);
        }
        {
            AngleType theHorizontalAngle;
            theHorizontalAngle = this.getHorizontalAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "horizontalAngle", theHorizontalAngle), currentHashCode, theHorizontalAngle);
        }
        {
            AngleType theVerticalAngle;
            theVerticalAngle = this.getVerticalAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalAngle", theVerticalAngle), currentHashCode, theVerticalAngle);
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
        if (draftCopy instanceof DirectionVectorType) {
            final DirectionVectorType copy = ((DirectionVectorType) draftCopy);
            if (this.isSetVector()) {
                VectorType sourceVector;
                sourceVector = this.getVector();
                VectorType copyVector = ((VectorType) strategy.copy(LocatorUtils.property(locator, "vector", sourceVector), sourceVector));
                copy.setVector(copyVector);
            } else {
                copy.vector = null;
            }
            if (this.isSetHorizontalAngle()) {
                AngleType sourceHorizontalAngle;
                sourceHorizontalAngle = this.getHorizontalAngle();
                AngleType copyHorizontalAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "horizontalAngle", sourceHorizontalAngle), sourceHorizontalAngle));
                copy.setHorizontalAngle(copyHorizontalAngle);
            } else {
                copy.horizontalAngle = null;
            }
            if (this.isSetVerticalAngle()) {
                AngleType sourceVerticalAngle;
                sourceVerticalAngle = this.getVerticalAngle();
                AngleType copyVerticalAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "verticalAngle", sourceVerticalAngle), sourceVerticalAngle));
                copy.setVerticalAngle(copyVerticalAngle);
            } else {
                copy.verticalAngle = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DirectionVectorType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DirectionVectorType) {
            final DirectionVectorType target = this;
            final DirectionVectorType leftObject = ((DirectionVectorType) left);
            final DirectionVectorType rightObject = ((DirectionVectorType) right);
            {
                VectorType lhsVector;
                lhsVector = leftObject.getVector();
                VectorType rhsVector;
                rhsVector = rightObject.getVector();
                target.setVector(((VectorType) strategy.merge(LocatorUtils.property(leftLocator, "vector", lhsVector), LocatorUtils.property(rightLocator, "vector", rhsVector), lhsVector, rhsVector)));
            }
            {
                AngleType lhsHorizontalAngle;
                lhsHorizontalAngle = leftObject.getHorizontalAngle();
                AngleType rhsHorizontalAngle;
                rhsHorizontalAngle = rightObject.getHorizontalAngle();
                target.setHorizontalAngle(((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "horizontalAngle", lhsHorizontalAngle), LocatorUtils.property(rightLocator, "horizontalAngle", rhsHorizontalAngle), lhsHorizontalAngle, rhsHorizontalAngle)));
            }
            {
                AngleType lhsVerticalAngle;
                lhsVerticalAngle = leftObject.getVerticalAngle();
                AngleType rhsVerticalAngle;
                rhsVerticalAngle = rightObject.getVerticalAngle();
                target.setVerticalAngle(((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "verticalAngle", lhsVerticalAngle), LocatorUtils.property(rightLocator, "verticalAngle", rhsVerticalAngle), lhsVerticalAngle, rhsVerticalAngle)));
            }
        }
    }

    public DirectionVectorType withVector(VectorType value) {
        setVector(value);
        return this;
    }

    public DirectionVectorType withHorizontalAngle(AngleType value) {
        setHorizontalAngle(value);
        return this;
    }

    public DirectionVectorType withVerticalAngle(AngleType value) {
        setVerticalAngle(value);
        return this;
    }

}
