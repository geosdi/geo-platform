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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
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
 * The method for identifying a temporal position is specific to each temporal reference system.  gml:TimePositionType supports the description of temporal position according to the subtypes described in ISO 19108.
 * Values based on calendars and clocks use lexical formats that are based on ISO 8601, as described in XML Schema Part 2:2001. A decimal value may be used with coordinate systems such as GPS time or UNIX time. A URI may be used to provide a reference to some era in an ordinal reference system . 
 * In common with many of the components modelled as data types in the ISO 19100 series of International Standards, the corresponding GML component has simple content. However, the content model gml:TimePositionType is defined in several steps.
 * Three XML attributes appear on gml:TimePositionType:
 * A time value shall be associated with a temporal reference system through the frame attribute that provides a URI reference that identifies a description of the reference system. Following ISO 19108, the Gregorian calendar with UTC is the default reference system, but others may also be used. Components for describing temporal reference systems are described in 14.4, but it is not required that the reference system be described in this, as the reference may refer to anything that may be indentified with a URI.  
 * For time values using a calendar containing more than one era, the (optional) calendarEraName attribute provides the name of the calendar era.  
 * Inexact temporal positions may be expressed using the optional indeterminatePosition attribute.  This takes a value from an enumeration.
 * 
 * <p>Classe Java per TimePositionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimePositionType">
 *   <simpleContent>
 *     <extension base="<http://www.opengis.net/gml>TimePositionUnion">
 *       <attribute name="frame" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="#ISO-8601" />
 *       <attribute name="calendarEraName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="indeterminatePosition" type="{http://www.opengis.net/gml}TimeIndeterminateValueType" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimePositionType", propOrder = {
    "value"
})
public class TimePositionType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The simple type gml:TimePositionUnion is a union of XML Schema simple types which instantiate the subtypes for temporal position described in ISO 19108.
     *  An ordinal era may be referenced via URI.  A decimal value may be used to indicate the distance from the scale origin .  time is used for a position that recurs daily (see ISO 19108:2002 5.4.4.2).
     *  Finally, calendar and clock forms that support the representation of time in systems based on years, months, days, hours, minutes and seconds, in a notation following ISO 8601, are assembled by gml:CalDate
     * 
     */
    @XmlValue
    protected List<String> value;
    @XmlAttribute(name = "frame")
    @XmlSchemaType(name = "anyURI")
    protected String frame;
    @XmlAttribute(name = "calendarEraName")
    protected String calendarEraName;
    @XmlAttribute(name = "indeterminatePosition")
    protected TimeIndeterminateValueType indeterminatePosition;

    /**
     * The simple type gml:TimePositionUnion is a union of XML Schema simple types which instantiate the subtypes for temporal position described in ISO 19108.
     *  An ordinal era may be referenced via URI.  A decimal value may be used to indicate the distance from the scale origin .  time is used for a position that recurs daily (see ISO 19108:2002 5.4.4.2).
     *  Finally, calendar and clock forms that support the representation of time in systems based on years, months, days, hours, minutes and seconds, in a notation following ISO 8601, are assembled by gml:CalDate
     * 
     * Gets the value of the value property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the value property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the value property.
     */
    public List<String> getValue() {
        if (value == null) {
            value = new ArrayList<>();
        }
        return this.value;
    }

    public boolean isSetValue() {
        return ((this.value!= null)&&(!this.value.isEmpty()));
    }

    public void unsetValue() {
        this.value = null;
    }

    /**
     * Recupera il valore della proprietà frame.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrame() {
        if (frame == null) {
            return "#ISO-8601";
        } else {
            return frame;
        }
    }

    /**
     * Imposta il valore della proprietà frame.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrame(String value) {
        this.frame = value;
    }

    public boolean isSetFrame() {
        return (this.frame!= null);
    }

    /**
     * Recupera il valore della proprietà calendarEraName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarEraName() {
        return calendarEraName;
    }

    /**
     * Imposta il valore della proprietà calendarEraName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarEraName(String value) {
        this.calendarEraName = value;
    }

    public boolean isSetCalendarEraName() {
        return (this.calendarEraName!= null);
    }

    /**
     * Recupera il valore della proprietà indeterminatePosition.
     * 
     * @return
     *     possible object is
     *     {@link TimeIndeterminateValueType }
     *     
     */
    public TimeIndeterminateValueType getIndeterminatePosition() {
        return indeterminatePosition;
    }

    /**
     * Imposta il valore della proprietà indeterminatePosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIndeterminateValueType }
     *     
     */
    public void setIndeterminatePosition(TimeIndeterminateValueType value) {
        this.indeterminatePosition = value;
    }

    public boolean isSetIndeterminatePosition() {
        return (this.indeterminatePosition!= null);
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
            List<String> theValue;
            theValue = (this.isSetValue()?this.getValue():null);
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theFrame;
            theFrame = this.getFrame();
            strategy.appendField(locator, this, "frame", buffer, theFrame, this.isSetFrame());
        }
        {
            String theCalendarEraName;
            theCalendarEraName = this.getCalendarEraName();
            strategy.appendField(locator, this, "calendarEraName", buffer, theCalendarEraName, this.isSetCalendarEraName());
        }
        {
            TimeIndeterminateValueType theIndeterminatePosition;
            theIndeterminatePosition = this.getIndeterminatePosition();
            strategy.appendField(locator, this, "indeterminatePosition", buffer, theIndeterminatePosition, this.isSetIndeterminatePosition());
        }
        return buffer;
    }

    public void setValue(List<String> value) {
        this.value = null;
        if (value!= null) {
            List<String> draftl = this.getValue();
            draftl.addAll(value);
        }
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TimePositionType that = ((TimePositionType) object);
        {
            List<String> lhsValue;
            lhsValue = (this.isSetValue()?this.getValue():null);
            List<String> rhsValue;
            rhsValue = (that.isSetValue()?that.getValue():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            String lhsFrame;
            lhsFrame = this.getFrame();
            String rhsFrame;
            rhsFrame = that.getFrame();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "frame", lhsFrame), LocatorUtils.property(thatLocator, "frame", rhsFrame), lhsFrame, rhsFrame, this.isSetFrame(), that.isSetFrame())) {
                return false;
            }
        }
        {
            String lhsCalendarEraName;
            lhsCalendarEraName = this.getCalendarEraName();
            String rhsCalendarEraName;
            rhsCalendarEraName = that.getCalendarEraName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "calendarEraName", lhsCalendarEraName), LocatorUtils.property(thatLocator, "calendarEraName", rhsCalendarEraName), lhsCalendarEraName, rhsCalendarEraName, this.isSetCalendarEraName(), that.isSetCalendarEraName())) {
                return false;
            }
        }
        {
            TimeIndeterminateValueType lhsIndeterminatePosition;
            lhsIndeterminatePosition = this.getIndeterminatePosition();
            TimeIndeterminateValueType rhsIndeterminatePosition;
            rhsIndeterminatePosition = that.getIndeterminatePosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "indeterminatePosition", lhsIndeterminatePosition), LocatorUtils.property(thatLocator, "indeterminatePosition", rhsIndeterminatePosition), lhsIndeterminatePosition, rhsIndeterminatePosition, this.isSetIndeterminatePosition(), that.isSetIndeterminatePosition())) {
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
            List<String> theValue;
            theValue = (this.isSetValue()?this.getValue():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            String theFrame;
            theFrame = this.getFrame();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "frame", theFrame), currentHashCode, theFrame, this.isSetFrame());
        }
        {
            String theCalendarEraName;
            theCalendarEraName = this.getCalendarEraName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "calendarEraName", theCalendarEraName), currentHashCode, theCalendarEraName, this.isSetCalendarEraName());
        }
        {
            TimeIndeterminateValueType theIndeterminatePosition;
            theIndeterminatePosition = this.getIndeterminatePosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "indeterminatePosition", theIndeterminatePosition), currentHashCode, theIndeterminatePosition, this.isSetIndeterminatePosition());
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
        if (draftCopy instanceof TimePositionType) {
            final TimePositionType copy = ((TimePositionType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceValue;
                    sourceValue = (this.isSetValue()?this.getValue():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyValue = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetValue();
                    }
                }
            }
            {
                java.lang.Boolean frameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFrame());
                if (frameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceFrame;
                    sourceFrame = this.getFrame();
                    String copyFrame = ((String) strategy.copy(LocatorUtils.property(locator, "frame", sourceFrame), sourceFrame, this.isSetFrame()));
                    copy.setFrame(copyFrame);
                } else {
                    if (frameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.frame = null;
                    }
                }
            }
            {
                java.lang.Boolean calendarEraNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCalendarEraName());
                if (calendarEraNameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceCalendarEraName;
                    sourceCalendarEraName = this.getCalendarEraName();
                    String copyCalendarEraName = ((String) strategy.copy(LocatorUtils.property(locator, "calendarEraName", sourceCalendarEraName), sourceCalendarEraName, this.isSetCalendarEraName()));
                    copy.setCalendarEraName(copyCalendarEraName);
                } else {
                    if (calendarEraNameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.calendarEraName = null;
                    }
                }
            }
            {
                java.lang.Boolean indeterminatePositionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIndeterminatePosition());
                if (indeterminatePositionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeIndeterminateValueType sourceIndeterminatePosition;
                    sourceIndeterminatePosition = this.getIndeterminatePosition();
                    TimeIndeterminateValueType copyIndeterminatePosition = ((TimeIndeterminateValueType) strategy.copy(LocatorUtils.property(locator, "indeterminatePosition", sourceIndeterminatePosition), sourceIndeterminatePosition, this.isSetIndeterminatePosition()));
                    copy.setIndeterminatePosition(copyIndeterminatePosition);
                } else {
                    if (indeterminatePositionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.indeterminatePosition = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimePositionType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof TimePositionType) {
            final TimePositionType target = this;
            final TimePositionType leftObject = ((TimePositionType) left);
            final TimePositionType rightObject = ((TimePositionType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsValue;
                    lhsValue = (leftObject.isSetValue()?leftObject.getValue():null);
                    List<String> rhsValue;
                    rhsValue = (rightObject.isSetValue()?rightObject.getValue():null);
                    List<String> mergedValue = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetValue();
                    }
                }
            }
            {
                java.lang.Boolean frameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFrame(), rightObject.isSetFrame());
                if (frameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsFrame;
                    lhsFrame = leftObject.getFrame();
                    String rhsFrame;
                    rhsFrame = rightObject.getFrame();
                    String mergedFrame = ((String) strategy.merge(LocatorUtils.property(leftLocator, "frame", lhsFrame), LocatorUtils.property(rightLocator, "frame", rhsFrame), lhsFrame, rhsFrame, leftObject.isSetFrame(), rightObject.isSetFrame()));
                    target.setFrame(mergedFrame);
                } else {
                    if (frameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.frame = null;
                    }
                }
            }
            {
                java.lang.Boolean calendarEraNameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCalendarEraName(), rightObject.isSetCalendarEraName());
                if (calendarEraNameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsCalendarEraName;
                    lhsCalendarEraName = leftObject.getCalendarEraName();
                    String rhsCalendarEraName;
                    rhsCalendarEraName = rightObject.getCalendarEraName();
                    String mergedCalendarEraName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "calendarEraName", lhsCalendarEraName), LocatorUtils.property(rightLocator, "calendarEraName", rhsCalendarEraName), lhsCalendarEraName, rhsCalendarEraName, leftObject.isSetCalendarEraName(), rightObject.isSetCalendarEraName()));
                    target.setCalendarEraName(mergedCalendarEraName);
                } else {
                    if (calendarEraNameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.calendarEraName = null;
                    }
                }
            }
            {
                java.lang.Boolean indeterminatePositionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIndeterminatePosition(), rightObject.isSetIndeterminatePosition());
                if (indeterminatePositionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeIndeterminateValueType lhsIndeterminatePosition;
                    lhsIndeterminatePosition = leftObject.getIndeterminatePosition();
                    TimeIndeterminateValueType rhsIndeterminatePosition;
                    rhsIndeterminatePosition = rightObject.getIndeterminatePosition();
                    TimeIndeterminateValueType mergedIndeterminatePosition = ((TimeIndeterminateValueType) strategy.merge(LocatorUtils.property(leftLocator, "indeterminatePosition", lhsIndeterminatePosition), LocatorUtils.property(rightLocator, "indeterminatePosition", rhsIndeterminatePosition), lhsIndeterminatePosition, rhsIndeterminatePosition, leftObject.isSetIndeterminatePosition(), rightObject.isSetIndeterminatePosition()));
                    target.setIndeterminatePosition(mergedIndeterminatePosition);
                } else {
                    if (indeterminatePositionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.indeterminatePosition = null;
                    }
                }
            }
        }
    }

}
