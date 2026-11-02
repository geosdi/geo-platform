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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per CoordinateSystemAxisType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CoordinateSystemAxisType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}axisAbbrev"/>
 *         <element ref="{http://www.opengis.net/gml}axisDirection"/>
 *         <element ref="{http://www.opengis.net/gml}minimumValue" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}maximumValue" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}rangeMeaning" minOccurs="0"/>
 *       </sequence>
 *       <attribute ref="{http://www.opengis.net/gml}uom use="required""/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinateSystemAxisType", propOrder = {
    "axisAbbrev",
    "axisDirection",
    "minimumValue",
    "maximumValue",
    "rangeMeaning"
})
public class CoordinateSystemAxisType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:axisAbbrev is the abbreviation used for this coordinate system axis; this abbreviation is also used to identify the coordinates in the coordinate tuple. The codeSpace attribute may reference a source of more information on a set of standardized abbreviations, or on this abbreviation.
     * 
     */
    @XmlElement(required = true)
    protected CodeType axisAbbrev;
    /**
     * gml:axisDirection is the direction of this coordinate system axis (or in the case of Cartesian projected coordinates, the direction of this coordinate system axis at the origin).
     * Within any set of coordinate system axes, only one of each pair of terms may be used. For earth-fixed CRSs, this direction is often approximate and intended to provide a human interpretable meaning to the axis. When a geodetic datum is used, the precise directions of the axes may therefore vary slightly from this approximate direction.
     * The codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     */
    @XmlElement(required = true)
    protected CodeWithAuthorityType axisDirection;
    /**
     * The gml:minimumValue and gml:maximumValue properties allow the specification of minimum and maximum value normally allowed for this axis, in the unit of measure for the axis. For a continuous angular axis such as longitude, the values wrap-around at this value. Also, values beyond this minimum/maximum can be used for specified purposes, such as in a bounding box. A value of minus infinity shall be allowed for the gml:minimumValue element, a value of plus infiniy for the gml:maximumValue element. If these elements are omitted, the value is unspecified.
     * 
     */
    protected Double minimumValue;
    /**
     * The gml:minimumValue and gml:maximumValue properties allow the specification of minimum and maximum value normally allowed for this axis, in the unit of measure for the axis. For a continuous angular axis such as longitude, the values wrap-around at this value. Also, values beyond this minimum/maximum can be used for specified purposes, such as in a bounding box. A value of minus infinity shall be allowed for the gml:minimumValue element, a value of plus infiniy for the gml:maximumValue element. If these elements are omitted, the value is unspecified.
     * 
     */
    protected Double maximumValue;
    /**
     * gml:rangeMeaning describes the meaning of axis value range specified by gml:minimumValue and gml:maximumValue. This element shall be omitted when both gml:minimumValue and gml:maximumValue are omitted. This element should be included when gml:minimumValue and/or gml:maximumValue are included. If this element is omitted when the gml:minimumValue and/or gml:maximumValue are included, the meaning is unspecified. The codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     */
    protected CodeWithAuthorityType rangeMeaning;
    /**
     * The uom attribute provides an identifier of the unit of measure used for this coordinate system axis. The value of this coordinate in a coordinate tuple shall be recorded using this unit of measure, whenever those coordinates use a coordinate reference system that uses a coordinate system that uses this axis.
     * 
     */
    @XmlAttribute(name = "uom", namespace = "http://www.opengis.net/gml", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uom;

    /**
     * gml:axisAbbrev is the abbreviation used for this coordinate system axis; this abbreviation is also used to identify the coordinates in the coordinate tuple. The codeSpace attribute may reference a source of more information on a set of standardized abbreviations, or on this abbreviation.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getAxisAbbrev() {
        return axisAbbrev;
    }

    /**
     * Imposta il valore della proprietà axisAbbrev.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getAxisAbbrev()
     */
    public void setAxisAbbrev(CodeType value) {
        this.axisAbbrev = value;
    }

    public boolean isSetAxisAbbrev() {
        return (this.axisAbbrev!= null);
    }

    /**
     * gml:axisDirection is the direction of this coordinate system axis (or in the case of Cartesian projected coordinates, the direction of this coordinate system axis at the origin).
     * Within any set of coordinate system axes, only one of each pair of terms may be used. For earth-fixed CRSs, this direction is often approximate and intended to provide a human interpretable meaning to the axis. When a geodetic datum is used, the precise directions of the axes may therefore vary slightly from this approximate direction.
     * The codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     * @return
     *     possible object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public CodeWithAuthorityType getAxisDirection() {
        return axisDirection;
    }

    /**
     * Imposta il valore della proprietà axisDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeWithAuthorityType }
     *     
     * @see #getAxisDirection()
     */
    public void setAxisDirection(CodeWithAuthorityType value) {
        this.axisDirection = value;
    }

    public boolean isSetAxisDirection() {
        return (this.axisDirection!= null);
    }

    /**
     * The gml:minimumValue and gml:maximumValue properties allow the specification of minimum and maximum value normally allowed for this axis, in the unit of measure for the axis. For a continuous angular axis such as longitude, the values wrap-around at this value. Also, values beyond this minimum/maximum can be used for specified purposes, such as in a bounding box. A value of minus infinity shall be allowed for the gml:minimumValue element, a value of plus infiniy for the gml:maximumValue element. If these elements are omitted, the value is unspecified.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimumValue() {
        return minimumValue;
    }

    /**
     * Imposta il valore della proprietà minimumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     * @see #getMinimumValue()
     */
    public void setMinimumValue(Double value) {
        this.minimumValue = value;
    }

    public boolean isSetMinimumValue() {
        return (this.minimumValue!= null);
    }

    /**
     * The gml:minimumValue and gml:maximumValue properties allow the specification of minimum and maximum value normally allowed for this axis, in the unit of measure for the axis. For a continuous angular axis such as longitude, the values wrap-around at this value. Also, values beyond this minimum/maximum can be used for specified purposes, such as in a bounding box. A value of minus infinity shall be allowed for the gml:minimumValue element, a value of plus infiniy for the gml:maximumValue element. If these elements are omitted, the value is unspecified.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumValue() {
        return maximumValue;
    }

    /**
     * Imposta il valore della proprietà maximumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     * @see #getMaximumValue()
     */
    public void setMaximumValue(Double value) {
        this.maximumValue = value;
    }

    public boolean isSetMaximumValue() {
        return (this.maximumValue!= null);
    }

    /**
     * gml:rangeMeaning describes the meaning of axis value range specified by gml:minimumValue and gml:maximumValue. This element shall be omitted when both gml:minimumValue and gml:maximumValue are omitted. This element should be included when gml:minimumValue and/or gml:maximumValue are included. If this element is omitted when the gml:minimumValue and/or gml:maximumValue are included, the meaning is unspecified. The codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     * @return
     *     possible object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public CodeWithAuthorityType getRangeMeaning() {
        return rangeMeaning;
    }

    /**
     * Imposta il valore della proprietà rangeMeaning.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeWithAuthorityType }
     *     
     * @see #getRangeMeaning()
     */
    public void setRangeMeaning(CodeWithAuthorityType value) {
        this.rangeMeaning = value;
    }

    public boolean isSetRangeMeaning() {
        return (this.rangeMeaning!= null);
    }

    /**
     * The uom attribute provides an identifier of the unit of measure used for this coordinate system axis. The value of this coordinate in a coordinate tuple shall be recorded using this unit of measure, whenever those coordinates use a coordinate reference system that uses a coordinate system that uses this axis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUom() {
        return uom;
    }

    /**
     * Imposta il valore della proprietà uom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUom()
     */
    public void setUom(String value) {
        this.uom = value;
    }

    public boolean isSetUom() {
        return (this.uom!= null);
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
            CodeType theAxisAbbrev;
            theAxisAbbrev = this.getAxisAbbrev();
            strategy.appendField(locator, this, "axisAbbrev", buffer, theAxisAbbrev, this.isSetAxisAbbrev());
        }
        {
            CodeWithAuthorityType theAxisDirection;
            theAxisDirection = this.getAxisDirection();
            strategy.appendField(locator, this, "axisDirection", buffer, theAxisDirection, this.isSetAxisDirection());
        }
        {
            Double theMinimumValue;
            theMinimumValue = this.getMinimumValue();
            strategy.appendField(locator, this, "minimumValue", buffer, theMinimumValue, this.isSetMinimumValue());
        }
        {
            Double theMaximumValue;
            theMaximumValue = this.getMaximumValue();
            strategy.appendField(locator, this, "maximumValue", buffer, theMaximumValue, this.isSetMaximumValue());
        }
        {
            CodeWithAuthorityType theRangeMeaning;
            theRangeMeaning = this.getRangeMeaning();
            strategy.appendField(locator, this, "rangeMeaning", buffer, theRangeMeaning, this.isSetRangeMeaning());
        }
        {
            String theUom;
            theUom = this.getUom();
            strategy.appendField(locator, this, "uom", buffer, theUom, this.isSetUom());
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
        final CoordinateSystemAxisType that = ((CoordinateSystemAxisType) object);
        {
            CodeType lhsAxisAbbrev;
            lhsAxisAbbrev = this.getAxisAbbrev();
            CodeType rhsAxisAbbrev;
            rhsAxisAbbrev = that.getAxisAbbrev();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axisAbbrev", lhsAxisAbbrev), LocatorUtils.property(thatLocator, "axisAbbrev", rhsAxisAbbrev), lhsAxisAbbrev, rhsAxisAbbrev, this.isSetAxisAbbrev(), that.isSetAxisAbbrev())) {
                return false;
            }
        }
        {
            CodeWithAuthorityType lhsAxisDirection;
            lhsAxisDirection = this.getAxisDirection();
            CodeWithAuthorityType rhsAxisDirection;
            rhsAxisDirection = that.getAxisDirection();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axisDirection", lhsAxisDirection), LocatorUtils.property(thatLocator, "axisDirection", rhsAxisDirection), lhsAxisDirection, rhsAxisDirection, this.isSetAxisDirection(), that.isSetAxisDirection())) {
                return false;
            }
        }
        {
            Double lhsMinimumValue;
            lhsMinimumValue = this.getMinimumValue();
            Double rhsMinimumValue;
            rhsMinimumValue = that.getMinimumValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumValue", lhsMinimumValue), LocatorUtils.property(thatLocator, "minimumValue", rhsMinimumValue), lhsMinimumValue, rhsMinimumValue, this.isSetMinimumValue(), that.isSetMinimumValue())) {
                return false;
            }
        }
        {
            Double lhsMaximumValue;
            lhsMaximumValue = this.getMaximumValue();
            Double rhsMaximumValue;
            rhsMaximumValue = that.getMaximumValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximumValue", lhsMaximumValue), LocatorUtils.property(thatLocator, "maximumValue", rhsMaximumValue), lhsMaximumValue, rhsMaximumValue, this.isSetMaximumValue(), that.isSetMaximumValue())) {
                return false;
            }
        }
        {
            CodeWithAuthorityType lhsRangeMeaning;
            lhsRangeMeaning = this.getRangeMeaning();
            CodeWithAuthorityType rhsRangeMeaning;
            rhsRangeMeaning = that.getRangeMeaning();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rangeMeaning", lhsRangeMeaning), LocatorUtils.property(thatLocator, "rangeMeaning", rhsRangeMeaning), lhsRangeMeaning, rhsRangeMeaning, this.isSetRangeMeaning(), that.isSetRangeMeaning())) {
                return false;
            }
        }
        {
            String lhsUom;
            lhsUom = this.getUom();
            String rhsUom;
            rhsUom = that.getUom();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "uom", lhsUom), LocatorUtils.property(thatLocator, "uom", rhsUom), lhsUom, rhsUom, this.isSetUom(), that.isSetUom())) {
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
            CodeType theAxisAbbrev;
            theAxisAbbrev = this.getAxisAbbrev();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axisAbbrev", theAxisAbbrev), currentHashCode, theAxisAbbrev, this.isSetAxisAbbrev());
        }
        {
            CodeWithAuthorityType theAxisDirection;
            theAxisDirection = this.getAxisDirection();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axisDirection", theAxisDirection), currentHashCode, theAxisDirection, this.isSetAxisDirection());
        }
        {
            Double theMinimumValue;
            theMinimumValue = this.getMinimumValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minimumValue", theMinimumValue), currentHashCode, theMinimumValue, this.isSetMinimumValue());
        }
        {
            Double theMaximumValue;
            theMaximumValue = this.getMaximumValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maximumValue", theMaximumValue), currentHashCode, theMaximumValue, this.isSetMaximumValue());
        }
        {
            CodeWithAuthorityType theRangeMeaning;
            theRangeMeaning = this.getRangeMeaning();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rangeMeaning", theRangeMeaning), currentHashCode, theRangeMeaning, this.isSetRangeMeaning());
        }
        {
            String theUom;
            theUom = this.getUom();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "uom", theUom), currentHashCode, theUom, this.isSetUom());
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
        if (draftCopy instanceof CoordinateSystemAxisType) {
            final CoordinateSystemAxisType copy = ((CoordinateSystemAxisType) draftCopy);
            {
                java.lang.Boolean axisAbbrevShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAxisAbbrev());
                if (axisAbbrevShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeType sourceAxisAbbrev;
                    sourceAxisAbbrev = this.getAxisAbbrev();
                    CodeType copyAxisAbbrev = ((CodeType) strategy.copy(LocatorUtils.property(locator, "axisAbbrev", sourceAxisAbbrev), sourceAxisAbbrev, this.isSetAxisAbbrev()));
                    copy.setAxisAbbrev(copyAxisAbbrev);
                } else {
                    if (axisAbbrevShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.axisAbbrev = null;
                    }
                }
            }
            {
                java.lang.Boolean axisDirectionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAxisDirection());
                if (axisDirectionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType sourceAxisDirection;
                    sourceAxisDirection = this.getAxisDirection();
                    CodeWithAuthorityType copyAxisDirection = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "axisDirection", sourceAxisDirection), sourceAxisDirection, this.isSetAxisDirection()));
                    copy.setAxisDirection(copyAxisDirection);
                } else {
                    if (axisDirectionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.axisDirection = null;
                    }
                }
            }
            {
                java.lang.Boolean minimumValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMinimumValue());
                if (minimumValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    Double sourceMinimumValue;
                    sourceMinimumValue = this.getMinimumValue();
                    Double copyMinimumValue = ((Double) strategy.copy(LocatorUtils.property(locator, "minimumValue", sourceMinimumValue), sourceMinimumValue, this.isSetMinimumValue()));
                    copy.setMinimumValue(copyMinimumValue);
                } else {
                    if (minimumValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.minimumValue = null;
                    }
                }
            }
            {
                java.lang.Boolean maximumValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMaximumValue());
                if (maximumValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    Double sourceMaximumValue;
                    sourceMaximumValue = this.getMaximumValue();
                    Double copyMaximumValue = ((Double) strategy.copy(LocatorUtils.property(locator, "maximumValue", sourceMaximumValue), sourceMaximumValue, this.isSetMaximumValue()));
                    copy.setMaximumValue(copyMaximumValue);
                } else {
                    if (maximumValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.maximumValue = null;
                    }
                }
            }
            {
                java.lang.Boolean rangeMeaningShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRangeMeaning());
                if (rangeMeaningShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType sourceRangeMeaning;
                    sourceRangeMeaning = this.getRangeMeaning();
                    CodeWithAuthorityType copyRangeMeaning = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "rangeMeaning", sourceRangeMeaning), sourceRangeMeaning, this.isSetRangeMeaning()));
                    copy.setRangeMeaning(copyRangeMeaning);
                } else {
                    if (rangeMeaningShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.rangeMeaning = null;
                    }
                }
            }
            {
                java.lang.Boolean uomShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUom());
                if (uomShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceUom;
                    sourceUom = this.getUom();
                    String copyUom = ((String) strategy.copy(LocatorUtils.property(locator, "uom", sourceUom), sourceUom, this.isSetUom()));
                    copy.setUom(copyUom);
                } else {
                    if (uomShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.uom = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CoordinateSystemAxisType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof CoordinateSystemAxisType) {
            final CoordinateSystemAxisType target = this;
            final CoordinateSystemAxisType leftObject = ((CoordinateSystemAxisType) left);
            final CoordinateSystemAxisType rightObject = ((CoordinateSystemAxisType) right);
            {
                java.lang.Boolean axisAbbrevShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAxisAbbrev(), rightObject.isSetAxisAbbrev());
                if (axisAbbrevShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeType lhsAxisAbbrev;
                    lhsAxisAbbrev = leftObject.getAxisAbbrev();
                    CodeType rhsAxisAbbrev;
                    rhsAxisAbbrev = rightObject.getAxisAbbrev();
                    CodeType mergedAxisAbbrev = ((CodeType) strategy.merge(LocatorUtils.property(leftLocator, "axisAbbrev", lhsAxisAbbrev), LocatorUtils.property(rightLocator, "axisAbbrev", rhsAxisAbbrev), lhsAxisAbbrev, rhsAxisAbbrev, leftObject.isSetAxisAbbrev(), rightObject.isSetAxisAbbrev()));
                    target.setAxisAbbrev(mergedAxisAbbrev);
                } else {
                    if (axisAbbrevShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.axisAbbrev = null;
                    }
                }
            }
            {
                java.lang.Boolean axisDirectionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAxisDirection(), rightObject.isSetAxisDirection());
                if (axisDirectionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType lhsAxisDirection;
                    lhsAxisDirection = leftObject.getAxisDirection();
                    CodeWithAuthorityType rhsAxisDirection;
                    rhsAxisDirection = rightObject.getAxisDirection();
                    CodeWithAuthorityType mergedAxisDirection = ((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "axisDirection", lhsAxisDirection), LocatorUtils.property(rightLocator, "axisDirection", rhsAxisDirection), lhsAxisDirection, rhsAxisDirection, leftObject.isSetAxisDirection(), rightObject.isSetAxisDirection()));
                    target.setAxisDirection(mergedAxisDirection);
                } else {
                    if (axisDirectionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.axisDirection = null;
                    }
                }
            }
            {
                java.lang.Boolean minimumValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMinimumValue(), rightObject.isSetMinimumValue());
                if (minimumValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    Double lhsMinimumValue;
                    lhsMinimumValue = leftObject.getMinimumValue();
                    Double rhsMinimumValue;
                    rhsMinimumValue = rightObject.getMinimumValue();
                    Double mergedMinimumValue = ((Double) strategy.merge(LocatorUtils.property(leftLocator, "minimumValue", lhsMinimumValue), LocatorUtils.property(rightLocator, "minimumValue", rhsMinimumValue), lhsMinimumValue, rhsMinimumValue, leftObject.isSetMinimumValue(), rightObject.isSetMinimumValue()));
                    target.setMinimumValue(mergedMinimumValue);
                } else {
                    if (minimumValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.minimumValue = null;
                    }
                }
            }
            {
                java.lang.Boolean maximumValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMaximumValue(), rightObject.isSetMaximumValue());
                if (maximumValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    Double lhsMaximumValue;
                    lhsMaximumValue = leftObject.getMaximumValue();
                    Double rhsMaximumValue;
                    rhsMaximumValue = rightObject.getMaximumValue();
                    Double mergedMaximumValue = ((Double) strategy.merge(LocatorUtils.property(leftLocator, "maximumValue", lhsMaximumValue), LocatorUtils.property(rightLocator, "maximumValue", rhsMaximumValue), lhsMaximumValue, rhsMaximumValue, leftObject.isSetMaximumValue(), rightObject.isSetMaximumValue()));
                    target.setMaximumValue(mergedMaximumValue);
                } else {
                    if (maximumValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.maximumValue = null;
                    }
                }
            }
            {
                java.lang.Boolean rangeMeaningShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRangeMeaning(), rightObject.isSetRangeMeaning());
                if (rangeMeaningShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType lhsRangeMeaning;
                    lhsRangeMeaning = leftObject.getRangeMeaning();
                    CodeWithAuthorityType rhsRangeMeaning;
                    rhsRangeMeaning = rightObject.getRangeMeaning();
                    CodeWithAuthorityType mergedRangeMeaning = ((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "rangeMeaning", lhsRangeMeaning), LocatorUtils.property(rightLocator, "rangeMeaning", rhsRangeMeaning), lhsRangeMeaning, rhsRangeMeaning, leftObject.isSetRangeMeaning(), rightObject.isSetRangeMeaning()));
                    target.setRangeMeaning(mergedRangeMeaning);
                } else {
                    if (rangeMeaningShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.rangeMeaning = null;
                    }
                }
            }
            {
                java.lang.Boolean uomShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUom(), rightObject.isSetUom());
                if (uomShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsUom;
                    lhsUom = leftObject.getUom();
                    String rhsUom;
                    rhsUom = rightObject.getUom();
                    String mergedUom = ((String) strategy.merge(LocatorUtils.property(leftLocator, "uom", lhsUom), LocatorUtils.property(rightLocator, "uom", rhsUom), lhsUom, rhsUom, leftObject.isSetUom(), rightObject.isSetUom()));
                    target.setUom(mergedUom);
                } else {
                    if (uomShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.uom = null;
                    }
                }
            }
        }
    }

}
