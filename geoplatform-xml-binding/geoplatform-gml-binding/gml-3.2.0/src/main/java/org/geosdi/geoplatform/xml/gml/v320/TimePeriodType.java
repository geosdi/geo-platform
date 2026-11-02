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

import javax.xml.datatype.Duration;
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
 * <p>Classe Java per TimePeriodType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimePeriodType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTimeGeometricPrimitiveType">
 *       <sequence>
 *         <choice>
 *           <element name="beginPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *           <element name="begin" type="{http://www.opengis.net/gml}TimeInstantPropertyType"/>
 *         </choice>
 *         <choice>
 *           <element name="endPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *           <element name="end" type="{http://www.opengis.net/gml}TimeInstantPropertyType"/>
 *         </choice>
 *         <group ref="{http://www.opengis.net/gml}timeLength" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimePeriodType", propOrder = {
    "beginPosition",
    "begin",
    "endPosition",
    "end",
    "duration",
    "timeInterval"
})
public class TimePeriodType
    extends AbstractTimeGeometricPrimitiveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected TimePositionType beginPosition;
    protected TimeInstantPropertyType begin;
    protected TimePositionType endPosition;
    protected TimeInstantPropertyType end;
    /**
     * gml:duration conforms to the ISO 8601 syntax for temporal length as implemented by the XML Schema duration type.
     * 
     */
    protected Duration duration;
    /**
     * gml:timeInterval conforms to ISO 11404 which is based on floating point values for temporal length.
     * ISO 11404 syntax specifies the use of a positiveInteger together with appropriate values for radix and factor. The resolution of the time interval is to one radix ^(-factor) of the specified time unit.
     * The value of the unit is either selected from the units for time intervals from ISO 31-1:1992, or is another suitable unit.  The encoding is defined for GML in gml:TimeUnitType. The second component of this union type provides a method for indicating time units other than the six standard units given in the enumeration.
     * 
     */
    protected TimeIntervalLengthType timeInterval;

    /**
     * Recupera il valore della proprietà beginPosition.
     * 
     * @return
     *     possible object is
     *     {@link TimePositionType }
     *     
     */
    public TimePositionType getBeginPosition() {
        return beginPosition;
    }

    /**
     * Imposta il valore della proprietà beginPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePositionType }
     *     
     */
    public void setBeginPosition(TimePositionType value) {
        this.beginPosition = value;
    }

    public boolean isSetBeginPosition() {
        return (this.beginPosition!= null);
    }

    /**
     * Recupera il valore della proprietà begin.
     * 
     * @return
     *     possible object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public TimeInstantPropertyType getBegin() {
        return begin;
    }

    /**
     * Imposta il valore della proprietà begin.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public void setBegin(TimeInstantPropertyType value) {
        this.begin = value;
    }

    public boolean isSetBegin() {
        return (this.begin!= null);
    }

    /**
     * Recupera il valore della proprietà endPosition.
     * 
     * @return
     *     possible object is
     *     {@link TimePositionType }
     *     
     */
    public TimePositionType getEndPosition() {
        return endPosition;
    }

    /**
     * Imposta il valore della proprietà endPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePositionType }
     *     
     */
    public void setEndPosition(TimePositionType value) {
        this.endPosition = value;
    }

    public boolean isSetEndPosition() {
        return (this.endPosition!= null);
    }

    /**
     * Recupera il valore della proprietà end.
     * 
     * @return
     *     possible object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public TimeInstantPropertyType getEnd() {
        return end;
    }

    /**
     * Imposta il valore della proprietà end.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public void setEnd(TimeInstantPropertyType value) {
        this.end = value;
    }

    public boolean isSetEnd() {
        return (this.end!= null);
    }

    /**
     * gml:duration conforms to the ISO 8601 syntax for temporal length as implemented by the XML Schema duration type.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Imposta il valore della proprietà duration.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     * @see #getDuration()
     */
    public void setDuration(Duration value) {
        this.duration = value;
    }

    public boolean isSetDuration() {
        return (this.duration!= null);
    }

    /**
     * gml:timeInterval conforms to ISO 11404 which is based on floating point values for temporal length.
     * ISO 11404 syntax specifies the use of a positiveInteger together with appropriate values for radix and factor. The resolution of the time interval is to one radix ^(-factor) of the specified time unit.
     * The value of the unit is either selected from the units for time intervals from ISO 31-1:1992, or is another suitable unit.  The encoding is defined for GML in gml:TimeUnitType. The second component of this union type provides a method for indicating time units other than the six standard units given in the enumeration.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntervalLengthType }
     *     
     */
    public TimeIntervalLengthType getTimeInterval() {
        return timeInterval;
    }

    /**
     * Imposta il valore della proprietà timeInterval.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntervalLengthType }
     *     
     * @see #getTimeInterval()
     */
    public void setTimeInterval(TimeIntervalLengthType value) {
        this.timeInterval = value;
    }

    public boolean isSetTimeInterval() {
        return (this.timeInterval!= null);
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
            TimePositionType theBeginPosition;
            theBeginPosition = this.getBeginPosition();
            strategy.appendField(locator, this, "beginPosition", buffer, theBeginPosition, this.isSetBeginPosition());
        }
        {
            TimeInstantPropertyType theBegin;
            theBegin = this.getBegin();
            strategy.appendField(locator, this, "begin", buffer, theBegin, this.isSetBegin());
        }
        {
            TimePositionType theEndPosition;
            theEndPosition = this.getEndPosition();
            strategy.appendField(locator, this, "endPosition", buffer, theEndPosition, this.isSetEndPosition());
        }
        {
            TimeInstantPropertyType theEnd;
            theEnd = this.getEnd();
            strategy.appendField(locator, this, "end", buffer, theEnd, this.isSetEnd());
        }
        {
            Duration theDuration;
            theDuration = this.getDuration();
            strategy.appendField(locator, this, "duration", buffer, theDuration, this.isSetDuration());
        }
        {
            TimeIntervalLengthType theTimeInterval;
            theTimeInterval = this.getTimeInterval();
            strategy.appendField(locator, this, "timeInterval", buffer, theTimeInterval, this.isSetTimeInterval());
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
        final TimePeriodType that = ((TimePeriodType) object);
        {
            TimePositionType lhsBeginPosition;
            lhsBeginPosition = this.getBeginPosition();
            TimePositionType rhsBeginPosition;
            rhsBeginPosition = that.getBeginPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "beginPosition", lhsBeginPosition), LocatorUtils.property(thatLocator, "beginPosition", rhsBeginPosition), lhsBeginPosition, rhsBeginPosition, this.isSetBeginPosition(), that.isSetBeginPosition())) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsBegin;
            lhsBegin = this.getBegin();
            TimeInstantPropertyType rhsBegin;
            rhsBegin = that.getBegin();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "begin", lhsBegin), LocatorUtils.property(thatLocator, "begin", rhsBegin), lhsBegin, rhsBegin, this.isSetBegin(), that.isSetBegin())) {
                return false;
            }
        }
        {
            TimePositionType lhsEndPosition;
            lhsEndPosition = this.getEndPosition();
            TimePositionType rhsEndPosition;
            rhsEndPosition = that.getEndPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endPosition", lhsEndPosition), LocatorUtils.property(thatLocator, "endPosition", rhsEndPosition), lhsEndPosition, rhsEndPosition, this.isSetEndPosition(), that.isSetEndPosition())) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsEnd;
            lhsEnd = this.getEnd();
            TimeInstantPropertyType rhsEnd;
            rhsEnd = that.getEnd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "end", lhsEnd), LocatorUtils.property(thatLocator, "end", rhsEnd), lhsEnd, rhsEnd, this.isSetEnd(), that.isSetEnd())) {
                return false;
            }
        }
        {
            Duration lhsDuration;
            lhsDuration = this.getDuration();
            Duration rhsDuration;
            rhsDuration = that.getDuration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "duration", lhsDuration), LocatorUtils.property(thatLocator, "duration", rhsDuration), lhsDuration, rhsDuration, this.isSetDuration(), that.isSetDuration())) {
                return false;
            }
        }
        {
            TimeIntervalLengthType lhsTimeInterval;
            lhsTimeInterval = this.getTimeInterval();
            TimeIntervalLengthType rhsTimeInterval;
            rhsTimeInterval = that.getTimeInterval();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeInterval", lhsTimeInterval), LocatorUtils.property(thatLocator, "timeInterval", rhsTimeInterval), lhsTimeInterval, rhsTimeInterval, this.isSetTimeInterval(), that.isSetTimeInterval())) {
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
            TimePositionType theBeginPosition;
            theBeginPosition = this.getBeginPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "beginPosition", theBeginPosition), currentHashCode, theBeginPosition, this.isSetBeginPosition());
        }
        {
            TimeInstantPropertyType theBegin;
            theBegin = this.getBegin();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "begin", theBegin), currentHashCode, theBegin, this.isSetBegin());
        }
        {
            TimePositionType theEndPosition;
            theEndPosition = this.getEndPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endPosition", theEndPosition), currentHashCode, theEndPosition, this.isSetEndPosition());
        }
        {
            TimeInstantPropertyType theEnd;
            theEnd = this.getEnd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "end", theEnd), currentHashCode, theEnd, this.isSetEnd());
        }
        {
            Duration theDuration;
            theDuration = this.getDuration();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "duration", theDuration), currentHashCode, theDuration, this.isSetDuration());
        }
        {
            TimeIntervalLengthType theTimeInterval;
            theTimeInterval = this.getTimeInterval();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeInterval", theTimeInterval), currentHashCode, theTimeInterval, this.isSetTimeInterval());
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
        if (draftCopy instanceof TimePeriodType) {
            final TimePeriodType copy = ((TimePeriodType) draftCopy);
            {
                java.lang.Boolean beginPositionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBeginPosition());
                if (beginPositionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType sourceBeginPosition;
                    sourceBeginPosition = this.getBeginPosition();
                    TimePositionType copyBeginPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "beginPosition", sourceBeginPosition), sourceBeginPosition, this.isSetBeginPosition()));
                    copy.setBeginPosition(copyBeginPosition);
                } else {
                    if (beginPositionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.beginPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean beginShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBegin());
                if (beginShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType sourceBegin;
                    sourceBegin = this.getBegin();
                    TimeInstantPropertyType copyBegin = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "begin", sourceBegin), sourceBegin, this.isSetBegin()));
                    copy.setBegin(copyBegin);
                } else {
                    if (beginShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.begin = null;
                    }
                }
            }
            {
                java.lang.Boolean endPositionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEndPosition());
                if (endPositionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType sourceEndPosition;
                    sourceEndPosition = this.getEndPosition();
                    TimePositionType copyEndPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "endPosition", sourceEndPosition), sourceEndPosition, this.isSetEndPosition()));
                    copy.setEndPosition(copyEndPosition);
                } else {
                    if (endPositionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.endPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean endShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEnd());
                if (endShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType sourceEnd;
                    sourceEnd = this.getEnd();
                    TimeInstantPropertyType copyEnd = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "end", sourceEnd), sourceEnd, this.isSetEnd()));
                    copy.setEnd(copyEnd);
                } else {
                    if (endShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.end = null;
                    }
                }
            }
            {
                java.lang.Boolean durationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDuration());
                if (durationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    Duration sourceDuration;
                    sourceDuration = this.getDuration();
                    Duration copyDuration = ((Duration) strategy.copy(LocatorUtils.property(locator, "duration", sourceDuration), sourceDuration, this.isSetDuration()));
                    copy.setDuration(copyDuration);
                } else {
                    if (durationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.duration = null;
                    }
                }
            }
            {
                java.lang.Boolean timeIntervalShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTimeInterval());
                if (timeIntervalShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeIntervalLengthType sourceTimeInterval;
                    sourceTimeInterval = this.getTimeInterval();
                    TimeIntervalLengthType copyTimeInterval = ((TimeIntervalLengthType) strategy.copy(LocatorUtils.property(locator, "timeInterval", sourceTimeInterval), sourceTimeInterval, this.isSetTimeInterval()));
                    copy.setTimeInterval(copyTimeInterval);
                } else {
                    if (timeIntervalShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.timeInterval = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimePeriodType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimePeriodType) {
            final TimePeriodType target = this;
            final TimePeriodType leftObject = ((TimePeriodType) left);
            final TimePeriodType rightObject = ((TimePeriodType) right);
            {
                java.lang.Boolean beginPositionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBeginPosition(), rightObject.isSetBeginPosition());
                if (beginPositionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType lhsBeginPosition;
                    lhsBeginPosition = leftObject.getBeginPosition();
                    TimePositionType rhsBeginPosition;
                    rhsBeginPosition = rightObject.getBeginPosition();
                    TimePositionType mergedBeginPosition = ((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "beginPosition", lhsBeginPosition), LocatorUtils.property(rightLocator, "beginPosition", rhsBeginPosition), lhsBeginPosition, rhsBeginPosition, leftObject.isSetBeginPosition(), rightObject.isSetBeginPosition()));
                    target.setBeginPosition(mergedBeginPosition);
                } else {
                    if (beginPositionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.beginPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean beginShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBegin(), rightObject.isSetBegin());
                if (beginShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType lhsBegin;
                    lhsBegin = leftObject.getBegin();
                    TimeInstantPropertyType rhsBegin;
                    rhsBegin = rightObject.getBegin();
                    TimeInstantPropertyType mergedBegin = ((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "begin", lhsBegin), LocatorUtils.property(rightLocator, "begin", rhsBegin), lhsBegin, rhsBegin, leftObject.isSetBegin(), rightObject.isSetBegin()));
                    target.setBegin(mergedBegin);
                } else {
                    if (beginShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.begin = null;
                    }
                }
            }
            {
                java.lang.Boolean endPositionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEndPosition(), rightObject.isSetEndPosition());
                if (endPositionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType lhsEndPosition;
                    lhsEndPosition = leftObject.getEndPosition();
                    TimePositionType rhsEndPosition;
                    rhsEndPosition = rightObject.getEndPosition();
                    TimePositionType mergedEndPosition = ((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "endPosition", lhsEndPosition), LocatorUtils.property(rightLocator, "endPosition", rhsEndPosition), lhsEndPosition, rhsEndPosition, leftObject.isSetEndPosition(), rightObject.isSetEndPosition()));
                    target.setEndPosition(mergedEndPosition);
                } else {
                    if (endPositionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.endPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean endShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEnd(), rightObject.isSetEnd());
                if (endShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType lhsEnd;
                    lhsEnd = leftObject.getEnd();
                    TimeInstantPropertyType rhsEnd;
                    rhsEnd = rightObject.getEnd();
                    TimeInstantPropertyType mergedEnd = ((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "end", lhsEnd), LocatorUtils.property(rightLocator, "end", rhsEnd), lhsEnd, rhsEnd, leftObject.isSetEnd(), rightObject.isSetEnd()));
                    target.setEnd(mergedEnd);
                } else {
                    if (endShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.end = null;
                    }
                }
            }
            {
                java.lang.Boolean durationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDuration(), rightObject.isSetDuration());
                if (durationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    Duration lhsDuration;
                    lhsDuration = leftObject.getDuration();
                    Duration rhsDuration;
                    rhsDuration = rightObject.getDuration();
                    Duration mergedDuration = ((Duration) strategy.merge(LocatorUtils.property(leftLocator, "duration", lhsDuration), LocatorUtils.property(rightLocator, "duration", rhsDuration), lhsDuration, rhsDuration, leftObject.isSetDuration(), rightObject.isSetDuration()));
                    target.setDuration(mergedDuration);
                } else {
                    if (durationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.duration = null;
                    }
                }
            }
            {
                java.lang.Boolean timeIntervalShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTimeInterval(), rightObject.isSetTimeInterval());
                if (timeIntervalShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeIntervalLengthType lhsTimeInterval;
                    lhsTimeInterval = leftObject.getTimeInterval();
                    TimeIntervalLengthType rhsTimeInterval;
                    rhsTimeInterval = rightObject.getTimeInterval();
                    TimeIntervalLengthType mergedTimeInterval = ((TimeIntervalLengthType) strategy.merge(LocatorUtils.property(leftLocator, "timeInterval", lhsTimeInterval), LocatorUtils.property(rightLocator, "timeInterval", rhsTimeInterval), lhsTimeInterval, rhsTimeInterval, leftObject.isSetTimeInterval(), rightObject.isSetTimeInterval()));
                    target.setTimeInterval(mergedTimeInterval);
                } else {
                    if (timeIntervalShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.timeInterval = null;
                    }
                }
            }
        }
    }

}
