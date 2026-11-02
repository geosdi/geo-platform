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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import javax.xml.datatype.Duration;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per TimePeriodType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimePeriodType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractTimeGeometricPrimitiveType">
 *       <sequence>
 *         <choice>
 *           <element name="beginPosition" type="{http://www.opengis.net/gml/3.2}TimePositionType"/>
 *           <element name="begin" type="{http://www.opengis.net/gml/3.2}TimeInstantPropertyType"/>
 *         </choice>
 *         <choice>
 *           <element name="endPosition" type="{http://www.opengis.net/gml/3.2}TimePositionType"/>
 *           <element name="end" type="{http://www.opengis.net/gml/3.2}TimeInstantPropertyType"/>
 *         </choice>
 *         <group ref="{http://www.opengis.net/gml/3.2}timeLength" minOccurs="0"/>
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
    implements ToString
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

}
