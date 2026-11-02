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

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
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
 * <p>Classe Java per ClothoidType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ClothoidType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       <sequence>
 *         <element name="refLocation">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element ref="{http://www.opengis.net/gml}AffinePlacement"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="scaleFactor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         <element name="startParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="endParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       </sequence>
 *       <attribute name="interpolation" type="{http://www.opengis.net/gml}CurveInterpolationType" fixed="clothoid" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
    @XmlAttribute(name = "interpolation")
    public static final CurveInterpolationType INTERPOLATION = CurveInterpolationType.CLOTHOID;

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
            ClothoidType.RefLocation theRefLocation;
            theRefLocation = this.getRefLocation();
            strategy.appendField(locator, this, "refLocation", buffer, theRefLocation, this.isSetRefLocation());
        }
        {
            BigDecimal theScaleFactor;
            theScaleFactor = this.getScaleFactor();
            strategy.appendField(locator, this, "scaleFactor", buffer, theScaleFactor, this.isSetScaleFactor());
        }
        {
            double theStartParameter;
            theStartParameter = this.getStartParameter();
            strategy.appendField(locator, this, "startParameter", buffer, theStartParameter, true);
        }
        {
            double theEndParameter;
            theEndParameter = this.getEndParameter();
            strategy.appendField(locator, this, "endParameter", buffer, theEndParameter, true);
        }
        {
            CurveInterpolationType theINTERPOLATION;
            theINTERPOLATION = ClothoidType.INTERPOLATION;
            strategy.appendField(locator, this, "interpolation", buffer, theINTERPOLATION, true);
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
        final ClothoidType that = ((ClothoidType) object);
        {
            ClothoidType.RefLocation lhsRefLocation;
            lhsRefLocation = this.getRefLocation();
            ClothoidType.RefLocation rhsRefLocation;
            rhsRefLocation = that.getRefLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refLocation", lhsRefLocation), LocatorUtils.property(thatLocator, "refLocation", rhsRefLocation), lhsRefLocation, rhsRefLocation, this.isSetRefLocation(), that.isSetRefLocation())) {
                return false;
            }
        }
        {
            BigDecimal lhsScaleFactor;
            lhsScaleFactor = this.getScaleFactor();
            BigDecimal rhsScaleFactor;
            rhsScaleFactor = that.getScaleFactor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "scaleFactor", lhsScaleFactor), LocatorUtils.property(thatLocator, "scaleFactor", rhsScaleFactor), lhsScaleFactor, rhsScaleFactor, this.isSetScaleFactor(), that.isSetScaleFactor())) {
                return false;
            }
        }
        {
            double lhsStartParameter;
            lhsStartParameter = this.getStartParameter();
            double rhsStartParameter;
            rhsStartParameter = that.getStartParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startParameter", lhsStartParameter), LocatorUtils.property(thatLocator, "startParameter", rhsStartParameter), lhsStartParameter, rhsStartParameter, true, true)) {
                return false;
            }
        }
        {
            double lhsEndParameter;
            lhsEndParameter = this.getEndParameter();
            double rhsEndParameter;
            rhsEndParameter = that.getEndParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endParameter", lhsEndParameter), LocatorUtils.property(thatLocator, "endParameter", rhsEndParameter), lhsEndParameter, rhsEndParameter, true, true)) {
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
            ClothoidType.RefLocation theRefLocation;
            theRefLocation = this.getRefLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refLocation", theRefLocation), currentHashCode, theRefLocation, this.isSetRefLocation());
        }
        {
            BigDecimal theScaleFactor;
            theScaleFactor = this.getScaleFactor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scaleFactor", theScaleFactor), currentHashCode, theScaleFactor, this.isSetScaleFactor());
        }
        {
            double theStartParameter;
            theStartParameter = this.getStartParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startParameter", theStartParameter), currentHashCode, theStartParameter, true);
        }
        {
            double theEndParameter;
            theEndParameter = this.getEndParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endParameter", theEndParameter), currentHashCode, theEndParameter, true);
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
        if (draftCopy instanceof ClothoidType) {
            final ClothoidType copy = ((ClothoidType) draftCopy);
            {
                java.lang.Boolean refLocationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRefLocation());
                if (refLocationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ClothoidType.RefLocation sourceRefLocation;
                    sourceRefLocation = this.getRefLocation();
                    ClothoidType.RefLocation copyRefLocation = ((ClothoidType.RefLocation) strategy.copy(LocatorUtils.property(locator, "refLocation", sourceRefLocation), sourceRefLocation, this.isSetRefLocation()));
                    copy.setRefLocation(copyRefLocation);
                } else {
                    if (refLocationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.refLocation = null;
                    }
                }
            }
            {
                java.lang.Boolean scaleFactorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetScaleFactor());
                if (scaleFactorShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigDecimal sourceScaleFactor;
                    sourceScaleFactor = this.getScaleFactor();
                    BigDecimal copyScaleFactor = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "scaleFactor", sourceScaleFactor), sourceScaleFactor, this.isSetScaleFactor()));
                    copy.setScaleFactor(copyScaleFactor);
                } else {
                    if (scaleFactorShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.scaleFactor = null;
                    }
                }
            }
            {
                java.lang.Boolean startParameterShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, true);
                if (startParameterShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    double sourceStartParameter;
                    sourceStartParameter = this.getStartParameter();
                    double copyStartParameter = strategy.copy(LocatorUtils.property(locator, "startParameter", sourceStartParameter), sourceStartParameter, true);
                    copy.setStartParameter(copyStartParameter);
                } else {
                    if (startParameterShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean endParameterShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, true);
                if (endParameterShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    double sourceEndParameter;
                    sourceEndParameter = this.getEndParameter();
                    double copyEndParameter = strategy.copy(LocatorUtils.property(locator, "endParameter", sourceEndParameter), sourceEndParameter, true);
                    copy.setEndParameter(copyEndParameter);
                } else {
                    if (endParameterShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ClothoidType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ClothoidType) {
            final ClothoidType target = this;
            final ClothoidType leftObject = ((ClothoidType) left);
            final ClothoidType rightObject = ((ClothoidType) right);
            {
                java.lang.Boolean refLocationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRefLocation(), rightObject.isSetRefLocation());
                if (refLocationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ClothoidType.RefLocation lhsRefLocation;
                    lhsRefLocation = leftObject.getRefLocation();
                    ClothoidType.RefLocation rhsRefLocation;
                    rhsRefLocation = rightObject.getRefLocation();
                    ClothoidType.RefLocation mergedRefLocation = ((ClothoidType.RefLocation) strategy.merge(LocatorUtils.property(leftLocator, "refLocation", lhsRefLocation), LocatorUtils.property(rightLocator, "refLocation", rhsRefLocation), lhsRefLocation, rhsRefLocation, leftObject.isSetRefLocation(), rightObject.isSetRefLocation()));
                    target.setRefLocation(mergedRefLocation);
                } else {
                    if (refLocationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.refLocation = null;
                    }
                }
            }
            {
                java.lang.Boolean scaleFactorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetScaleFactor(), rightObject.isSetScaleFactor());
                if (scaleFactorShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigDecimal lhsScaleFactor;
                    lhsScaleFactor = leftObject.getScaleFactor();
                    BigDecimal rhsScaleFactor;
                    rhsScaleFactor = rightObject.getScaleFactor();
                    BigDecimal mergedScaleFactor = ((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "scaleFactor", lhsScaleFactor), LocatorUtils.property(rightLocator, "scaleFactor", rhsScaleFactor), lhsScaleFactor, rhsScaleFactor, leftObject.isSetScaleFactor(), rightObject.isSetScaleFactor()));
                    target.setScaleFactor(mergedScaleFactor);
                } else {
                    if (scaleFactorShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.scaleFactor = null;
                    }
                }
            }
            {
                java.lang.Boolean startParameterShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, true, true);
                if (startParameterShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    double lhsStartParameter;
                    lhsStartParameter = leftObject.getStartParameter();
                    double rhsStartParameter;
                    rhsStartParameter = rightObject.getStartParameter();
                    double mergedStartParameter = ((double) strategy.merge(LocatorUtils.property(leftLocator, "startParameter", lhsStartParameter), LocatorUtils.property(rightLocator, "startParameter", rhsStartParameter), lhsStartParameter, rhsStartParameter, true, true));
                    target.setStartParameter(mergedStartParameter);
                } else {
                    if (startParameterShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
            {
                java.lang.Boolean endParameterShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, true, true);
                if (endParameterShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    double lhsEndParameter;
                    lhsEndParameter = leftObject.getEndParameter();
                    double rhsEndParameter;
                    rhsEndParameter = rightObject.getEndParameter();
                    double mergedEndParameter = ((double) strategy.merge(LocatorUtils.property(leftLocator, "endParameter", lhsEndParameter), LocatorUtils.property(rightLocator, "endParameter", rhsEndParameter), lhsEndParameter, rhsEndParameter, true, true));
                    target.setEndParameter(mergedEndParameter);
                } else {
                    if (endParameterShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                    }
                }
            }
        }
    }


    /**
     * <p>Classe Java per anonymous complex type.</p>
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element ref="{http://www.opengis.net/gml}AffinePlacement"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "affinePlacement"
    })
    public static class RefLocation implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        /**
         * location, refDirection, inDimension and outDimension have the same meaning as specified in ISO 19107:2003, 6.4.21.
         * 
         */
        @XmlElement(name = "AffinePlacement", required = true)
        protected AffinePlacementType affinePlacement;

        /**
         * location, refDirection, inDimension and outDimension have the same meaning as specified in ISO 19107:2003, 6.4.21.
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
         * @see #getAffinePlacement()
         */
        public void setAffinePlacement(AffinePlacementType value) {
            this.affinePlacement = value;
        }

        public boolean isSetAffinePlacement() {
            return (this.affinePlacement!= null);
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
                AffinePlacementType theAffinePlacement;
                theAffinePlacement = this.getAffinePlacement();
                strategy.appendField(locator, this, "affinePlacement", buffer, theAffinePlacement, this.isSetAffinePlacement());
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
            final ClothoidType.RefLocation that = ((ClothoidType.RefLocation) object);
            {
                AffinePlacementType lhsAffinePlacement;
                lhsAffinePlacement = this.getAffinePlacement();
                AffinePlacementType rhsAffinePlacement;
                rhsAffinePlacement = that.getAffinePlacement();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "affinePlacement", lhsAffinePlacement), LocatorUtils.property(thatLocator, "affinePlacement", rhsAffinePlacement), lhsAffinePlacement, rhsAffinePlacement, this.isSetAffinePlacement(), that.isSetAffinePlacement())) {
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
                AffinePlacementType theAffinePlacement;
                theAffinePlacement = this.getAffinePlacement();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "affinePlacement", theAffinePlacement), currentHashCode, theAffinePlacement, this.isSetAffinePlacement());
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
            if (draftCopy instanceof ClothoidType.RefLocation) {
                final ClothoidType.RefLocation copy = ((ClothoidType.RefLocation) draftCopy);
                {
                    java.lang.Boolean affinePlacementShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAffinePlacement());
                    if (affinePlacementShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                        AffinePlacementType sourceAffinePlacement;
                        sourceAffinePlacement = this.getAffinePlacement();
                        AffinePlacementType copyAffinePlacement = ((AffinePlacementType) strategy.copy(LocatorUtils.property(locator, "affinePlacement", sourceAffinePlacement), sourceAffinePlacement, this.isSetAffinePlacement()));
                        copy.setAffinePlacement(copyAffinePlacement);
                    } else {
                        if (affinePlacementShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                            copy.affinePlacement = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        @Override
        public Object createNewInstance() {
            return new ClothoidType.RefLocation();
        }

        @Override
        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
            mergeFrom(null, null, left, right, strategy);
        }

        @Override
        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof ClothoidType.RefLocation) {
                final ClothoidType.RefLocation target = this;
                final ClothoidType.RefLocation leftObject = ((ClothoidType.RefLocation) left);
                final ClothoidType.RefLocation rightObject = ((ClothoidType.RefLocation) right);
                {
                    java.lang.Boolean affinePlacementShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAffinePlacement(), rightObject.isSetAffinePlacement());
                    if (affinePlacementShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                        AffinePlacementType lhsAffinePlacement;
                        lhsAffinePlacement = leftObject.getAffinePlacement();
                        AffinePlacementType rhsAffinePlacement;
                        rhsAffinePlacement = rightObject.getAffinePlacement();
                        AffinePlacementType mergedAffinePlacement = ((AffinePlacementType) strategy.merge(LocatorUtils.property(leftLocator, "affinePlacement", lhsAffinePlacement), LocatorUtils.property(rightLocator, "affinePlacement", rhsAffinePlacement), lhsAffinePlacement, rhsAffinePlacement, leftObject.isSetAffinePlacement(), rightObject.isSetAffinePlacement()));
                        target.setAffinePlacement(mergedAffinePlacement);
                    } else {
                        if (affinePlacementShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                            target.affinePlacement = null;
                        }
                    }
                }
            }
        }

    }

}
