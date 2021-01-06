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

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per ClothoidType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ClothoidType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       &lt;sequence>
 *         &lt;element name="refLocation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.opengis.net/gml}AffinePlacement"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="scaleFactor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="startParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="endParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *       &lt;attribute name="interpolation" type="{http://www.opengis.net/gml}CurveInterpolationType" fixed="clothoid" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClothoidType", propOrder = {
    "refLocation",
    "scaleFactor",
    "startParameter",
    "endParameter"
})
public class ClothoidType
    extends AbstractCurveSegmentType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected ClothoidType.RefLocation refLocation;
    @XmlElement(required = true)
    protected BigDecimal scaleFactor;
    protected double startParameter;
    protected double endParameter;
    /**
     * 
     * 
     */
    @XmlAttribute(name = "interpolation")
    public final static CurveInterpolationType INTERPOLATION = CurveInterpolationType.CLOTHOID;

    /**
     * Recupera il valore della proprietà refLocation.
     * 
     * @return
     *     possible object is
     *     {@link ClothoidType.RefLocation }
     *     
     */
    public ClothoidType.RefLocation getRefLocation() {
        return refLocation;
    }

    /**
     * Imposta il valore della proprietà refLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link ClothoidType.RefLocation }
     *     
     */
    public void setRefLocation(ClothoidType.RefLocation value) {
        this.refLocation = value;
    }

    public boolean isSetRefLocation() {
        return (this.refLocation!= null);
    }

    /**
     * Recupera il valore della proprietà scaleFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Imposta il valore della proprietà scaleFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setScaleFactor(BigDecimal value) {
        this.scaleFactor = value;
    }

    public boolean isSetScaleFactor() {
        return (this.scaleFactor!= null);
    }

    /**
     * Recupera il valore della proprietà startParameter.
     * 
     */
    public double getStartParameter() {
        return startParameter;
    }

    /**
     * Imposta il valore della proprietà startParameter.
     * 
     */
    public void setStartParameter(double value) {
        this.startParameter = value;
    }

    public boolean isSetStartParameter() {
        return true;
    }

    /**
     * Recupera il valore della proprietà endParameter.
     * 
     */
    public double getEndParameter() {
        return endParameter;
    }

    /**
     * Imposta il valore della proprietà endParameter.
     * 
     */
    public void setEndParameter(double value) {
        this.endParameter = value;
    }

    public boolean isSetEndParameter() {
        return true;
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
        super.appendFields(locator, buffer, strategy);
        {
            ClothoidType.RefLocation theRefLocation;
            theRefLocation = this.getRefLocation();
            strategy.appendField(locator, this, "refLocation", buffer, theRefLocation);
        }
        {
            BigDecimal theScaleFactor;
            theScaleFactor = this.getScaleFactor();
            strategy.appendField(locator, this, "scaleFactor", buffer, theScaleFactor);
        }
        {
            double theStartParameter;
            theStartParameter = this.getStartParameter();
            strategy.appendField(locator, this, "startParameter", buffer, theStartParameter);
        }
        {
            double theEndParameter;
            theEndParameter = this.getEndParameter();
            strategy.appendField(locator, this, "endParameter", buffer, theEndParameter);
        }
        {
            CurveInterpolationType theINTERPOLATION;
            theINTERPOLATION = ClothoidType.INTERPOLATION;
            strategy.appendField(locator, this, "interpolation", buffer, theINTERPOLATION);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ClothoidType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ClothoidType that = ((ClothoidType) object);
        {
            ClothoidType.RefLocation lhsRefLocation;
            lhsRefLocation = this.getRefLocation();
            ClothoidType.RefLocation rhsRefLocation;
            rhsRefLocation = that.getRefLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refLocation", lhsRefLocation), LocatorUtils.property(thatLocator, "refLocation", rhsRefLocation), lhsRefLocation, rhsRefLocation)) {
                return false;
            }
        }
        {
            BigDecimal lhsScaleFactor;
            lhsScaleFactor = this.getScaleFactor();
            BigDecimal rhsScaleFactor;
            rhsScaleFactor = that.getScaleFactor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "scaleFactor", lhsScaleFactor), LocatorUtils.property(thatLocator, "scaleFactor", rhsScaleFactor), lhsScaleFactor, rhsScaleFactor)) {
                return false;
            }
        }
        {
            double lhsStartParameter;
            lhsStartParameter = this.getStartParameter();
            double rhsStartParameter;
            rhsStartParameter = that.getStartParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startParameter", lhsStartParameter), LocatorUtils.property(thatLocator, "startParameter", rhsStartParameter), lhsStartParameter, rhsStartParameter)) {
                return false;
            }
        }
        {
            double lhsEndParameter;
            lhsEndParameter = this.getEndParameter();
            double rhsEndParameter;
            rhsEndParameter = that.getEndParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endParameter", lhsEndParameter), LocatorUtils.property(thatLocator, "endParameter", rhsEndParameter), lhsEndParameter, rhsEndParameter)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            ClothoidType.RefLocation theRefLocation;
            theRefLocation = this.getRefLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refLocation", theRefLocation), currentHashCode, theRefLocation);
        }
        {
            BigDecimal theScaleFactor;
            theScaleFactor = this.getScaleFactor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scaleFactor", theScaleFactor), currentHashCode, theScaleFactor);
        }
        {
            double theStartParameter;
            theStartParameter = this.getStartParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startParameter", theStartParameter), currentHashCode, theStartParameter);
        }
        {
            double theEndParameter;
            theEndParameter = this.getEndParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endParameter", theEndParameter), currentHashCode, theEndParameter);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof ClothoidType) {
            final ClothoidType copy = ((ClothoidType) draftCopy);
            if (this.isSetRefLocation()) {
                ClothoidType.RefLocation sourceRefLocation;
                sourceRefLocation = this.getRefLocation();
                ClothoidType.RefLocation copyRefLocation = ((ClothoidType.RefLocation) strategy.copy(LocatorUtils.property(locator, "refLocation", sourceRefLocation), sourceRefLocation));
                copy.setRefLocation(copyRefLocation);
            } else {
                copy.refLocation = null;
            }
            if (this.isSetScaleFactor()) {
                BigDecimal sourceScaleFactor;
                sourceScaleFactor = this.getScaleFactor();
                BigDecimal copyScaleFactor = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "scaleFactor", sourceScaleFactor), sourceScaleFactor));
                copy.setScaleFactor(copyScaleFactor);
            } else {
                copy.scaleFactor = null;
            }
            if (this.isSetStartParameter()) {
                double sourceStartParameter;
                sourceStartParameter = this.getStartParameter();
                double copyStartParameter = strategy.copy(LocatorUtils.property(locator, "startParameter", sourceStartParameter), sourceStartParameter);
                copy.setStartParameter(copyStartParameter);
            } else {
            }
            if (this.isSetEndParameter()) {
                double sourceEndParameter;
                sourceEndParameter = this.getEndParameter();
                double copyEndParameter = strategy.copy(LocatorUtils.property(locator, "endParameter", sourceEndParameter), sourceEndParameter);
                copy.setEndParameter(copyEndParameter);
            } else {
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ClothoidType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ClothoidType) {
            final ClothoidType target = this;
            final ClothoidType leftObject = ((ClothoidType) left);
            final ClothoidType rightObject = ((ClothoidType) right);
            {
                ClothoidType.RefLocation lhsRefLocation;
                lhsRefLocation = leftObject.getRefLocation();
                ClothoidType.RefLocation rhsRefLocation;
                rhsRefLocation = rightObject.getRefLocation();
                target.setRefLocation(((ClothoidType.RefLocation) strategy.merge(LocatorUtils.property(leftLocator, "refLocation", lhsRefLocation), LocatorUtils.property(rightLocator, "refLocation", rhsRefLocation), lhsRefLocation, rhsRefLocation)));
            }
            {
                BigDecimal lhsScaleFactor;
                lhsScaleFactor = leftObject.getScaleFactor();
                BigDecimal rhsScaleFactor;
                rhsScaleFactor = rightObject.getScaleFactor();
                target.setScaleFactor(((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "scaleFactor", lhsScaleFactor), LocatorUtils.property(rightLocator, "scaleFactor", rhsScaleFactor), lhsScaleFactor, rhsScaleFactor)));
            }
            {
                double lhsStartParameter;
                lhsStartParameter = leftObject.getStartParameter();
                double rhsStartParameter;
                rhsStartParameter = rightObject.getStartParameter();
                target.setStartParameter(((double) strategy.merge(LocatorUtils.property(leftLocator, "startParameter", lhsStartParameter), LocatorUtils.property(rightLocator, "startParameter", rhsStartParameter), lhsStartParameter, rhsStartParameter)));
            }
            {
                double lhsEndParameter;
                lhsEndParameter = leftObject.getEndParameter();
                double rhsEndParameter;
                rhsEndParameter = rightObject.getEndParameter();
                target.setEndParameter(((double) strategy.merge(LocatorUtils.property(leftLocator, "endParameter", lhsEndParameter), LocatorUtils.property(rightLocator, "endParameter", rhsEndParameter), lhsEndParameter, rhsEndParameter)));
            }
        }
    }

    public ClothoidType withRefLocation(ClothoidType.RefLocation value) {
        setRefLocation(value);
        return this;
    }

    public ClothoidType withScaleFactor(BigDecimal value) {
        setScaleFactor(value);
        return this;
    }

    public ClothoidType withStartParameter(double value) {
        setStartParameter(value);
        return this;
    }

    public ClothoidType withEndParameter(double value) {
        setEndParameter(value);
        return this;
    }

    @Override
    public ClothoidType withNumDerivativesAtStart(BigInteger value) {
        setNumDerivativesAtStart(value);
        return this;
    }

    @Override
    public ClothoidType withNumDerivativesAtEnd(BigInteger value) {
        setNumDerivativesAtEnd(value);
        return this;
    }

    @Override
    public ClothoidType withNumDerivativeInterior(BigInteger value) {
        setNumDerivativeInterior(value);
        return this;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://www.opengis.net/gml}AffinePlacement"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "affinePlacement"
    })
    public static class RefLocation
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        @XmlElement(name = "AffinePlacement", required = true)
        protected AffinePlacementType affinePlacement;

        /**
         * Recupera il valore della proprietà affinePlacement.
         * 
         * @return
         *     possible object is
         *     {@link AffinePlacementType }
         *     
         */
        public AffinePlacementType getAffinePlacement() {
            return affinePlacement;
        }

        /**
         * Imposta il valore della proprietà affinePlacement.
         * 
         * @param value
         *     allowed object is
         *     {@link AffinePlacementType }
         *     
         */
        public void setAffinePlacement(AffinePlacementType value) {
            this.affinePlacement = value;
        }

        public boolean isSetAffinePlacement() {
            return (this.affinePlacement!= null);
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
                AffinePlacementType theAffinePlacement;
                theAffinePlacement = this.getAffinePlacement();
                strategy.appendField(locator, this, "affinePlacement", buffer, theAffinePlacement);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ClothoidType.RefLocation)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ClothoidType.RefLocation that = ((ClothoidType.RefLocation) object);
            {
                AffinePlacementType lhsAffinePlacement;
                lhsAffinePlacement = this.getAffinePlacement();
                AffinePlacementType rhsAffinePlacement;
                rhsAffinePlacement = that.getAffinePlacement();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "affinePlacement", lhsAffinePlacement), LocatorUtils.property(thatLocator, "affinePlacement", rhsAffinePlacement), lhsAffinePlacement, rhsAffinePlacement)) {
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
                AffinePlacementType theAffinePlacement;
                theAffinePlacement = this.getAffinePlacement();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "affinePlacement", theAffinePlacement), currentHashCode, theAffinePlacement);
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
            if (draftCopy instanceof ClothoidType.RefLocation) {
                final ClothoidType.RefLocation copy = ((ClothoidType.RefLocation) draftCopy);
                if (this.isSetAffinePlacement()) {
                    AffinePlacementType sourceAffinePlacement;
                    sourceAffinePlacement = this.getAffinePlacement();
                    AffinePlacementType copyAffinePlacement = ((AffinePlacementType) strategy.copy(LocatorUtils.property(locator, "affinePlacement", sourceAffinePlacement), sourceAffinePlacement));
                    copy.setAffinePlacement(copyAffinePlacement);
                } else {
                    copy.affinePlacement = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new ClothoidType.RefLocation();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof ClothoidType.RefLocation) {
                final ClothoidType.RefLocation target = this;
                final ClothoidType.RefLocation leftObject = ((ClothoidType.RefLocation) left);
                final ClothoidType.RefLocation rightObject = ((ClothoidType.RefLocation) right);
                {
                    AffinePlacementType lhsAffinePlacement;
                    lhsAffinePlacement = leftObject.getAffinePlacement();
                    AffinePlacementType rhsAffinePlacement;
                    rhsAffinePlacement = rightObject.getAffinePlacement();
                    target.setAffinePlacement(((AffinePlacementType) strategy.merge(LocatorUtils.property(leftLocator, "affinePlacement", lhsAffinePlacement), LocatorUtils.property(rightLocator, "affinePlacement", rhsAffinePlacement), lhsAffinePlacement, rhsAffinePlacement)));
                }
            }
        }

        public ClothoidType.RefLocation withAffinePlacement(AffinePlacementType value) {
            setAffinePlacement(value);
            return this;
        }

    }

}
