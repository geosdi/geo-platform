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

import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
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
 * <p>Classe Java per TimePeriodType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimePeriodType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTimeGeometricPrimitiveType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="beginPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *           &lt;element name="begin" type="{http://www.opengis.net/gml}TimeInstantPropertyType"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="endPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *           &lt;element name="end" type="{http://www.opengis.net/gml}TimeInstantPropertyType"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.opengis.net/gml}timeLength" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
    protected Duration duration;
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
     * Recupera il valore della proprietà duration.
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
     */
    public void setDuration(Duration value) {
        this.duration = value;
    }

    public boolean isSetDuration() {
        return (this.duration!= null);
    }

    /**
     * Recupera il valore della proprietà timeInterval.
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
     */
    public void setTimeInterval(TimeIntervalLengthType value) {
        this.timeInterval = value;
    }

    public boolean isSetTimeInterval() {
        return (this.timeInterval!= null);
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
            TimePositionType theBeginPosition;
            theBeginPosition = this.getBeginPosition();
            strategy.appendField(locator, this, "beginPosition", buffer, theBeginPosition);
        }
        {
            TimeInstantPropertyType theBegin;
            theBegin = this.getBegin();
            strategy.appendField(locator, this, "begin", buffer, theBegin);
        }
        {
            TimePositionType theEndPosition;
            theEndPosition = this.getEndPosition();
            strategy.appendField(locator, this, "endPosition", buffer, theEndPosition);
        }
        {
            TimeInstantPropertyType theEnd;
            theEnd = this.getEnd();
            strategy.appendField(locator, this, "end", buffer, theEnd);
        }
        {
            Duration theDuration;
            theDuration = this.getDuration();
            strategy.appendField(locator, this, "duration", buffer, theDuration);
        }
        {
            TimeIntervalLengthType theTimeInterval;
            theTimeInterval = this.getTimeInterval();
            strategy.appendField(locator, this, "timeInterval", buffer, theTimeInterval);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimePeriodType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "beginPosition", lhsBeginPosition), LocatorUtils.property(thatLocator, "beginPosition", rhsBeginPosition), lhsBeginPosition, rhsBeginPosition)) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsBegin;
            lhsBegin = this.getBegin();
            TimeInstantPropertyType rhsBegin;
            rhsBegin = that.getBegin();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "begin", lhsBegin), LocatorUtils.property(thatLocator, "begin", rhsBegin), lhsBegin, rhsBegin)) {
                return false;
            }
        }
        {
            TimePositionType lhsEndPosition;
            lhsEndPosition = this.getEndPosition();
            TimePositionType rhsEndPosition;
            rhsEndPosition = that.getEndPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endPosition", lhsEndPosition), LocatorUtils.property(thatLocator, "endPosition", rhsEndPosition), lhsEndPosition, rhsEndPosition)) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsEnd;
            lhsEnd = this.getEnd();
            TimeInstantPropertyType rhsEnd;
            rhsEnd = that.getEnd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "end", lhsEnd), LocatorUtils.property(thatLocator, "end", rhsEnd), lhsEnd, rhsEnd)) {
                return false;
            }
        }
        {
            Duration lhsDuration;
            lhsDuration = this.getDuration();
            Duration rhsDuration;
            rhsDuration = that.getDuration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "duration", lhsDuration), LocatorUtils.property(thatLocator, "duration", rhsDuration), lhsDuration, rhsDuration)) {
                return false;
            }
        }
        {
            TimeIntervalLengthType lhsTimeInterval;
            lhsTimeInterval = this.getTimeInterval();
            TimeIntervalLengthType rhsTimeInterval;
            rhsTimeInterval = that.getTimeInterval();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeInterval", lhsTimeInterval), LocatorUtils.property(thatLocator, "timeInterval", rhsTimeInterval), lhsTimeInterval, rhsTimeInterval)) {
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
            TimePositionType theBeginPosition;
            theBeginPosition = this.getBeginPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "beginPosition", theBeginPosition), currentHashCode, theBeginPosition);
        }
        {
            TimeInstantPropertyType theBegin;
            theBegin = this.getBegin();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "begin", theBegin), currentHashCode, theBegin);
        }
        {
            TimePositionType theEndPosition;
            theEndPosition = this.getEndPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endPosition", theEndPosition), currentHashCode, theEndPosition);
        }
        {
            TimeInstantPropertyType theEnd;
            theEnd = this.getEnd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "end", theEnd), currentHashCode, theEnd);
        }
        {
            Duration theDuration;
            theDuration = this.getDuration();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "duration", theDuration), currentHashCode, theDuration);
        }
        {
            TimeIntervalLengthType theTimeInterval;
            theTimeInterval = this.getTimeInterval();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeInterval", theTimeInterval), currentHashCode, theTimeInterval);
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
        if (draftCopy instanceof TimePeriodType) {
            final TimePeriodType copy = ((TimePeriodType) draftCopy);
            if (this.isSetBeginPosition()) {
                TimePositionType sourceBeginPosition;
                sourceBeginPosition = this.getBeginPosition();
                TimePositionType copyBeginPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "beginPosition", sourceBeginPosition), sourceBeginPosition));
                copy.setBeginPosition(copyBeginPosition);
            } else {
                copy.beginPosition = null;
            }
            if (this.isSetBegin()) {
                TimeInstantPropertyType sourceBegin;
                sourceBegin = this.getBegin();
                TimeInstantPropertyType copyBegin = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "begin", sourceBegin), sourceBegin));
                copy.setBegin(copyBegin);
            } else {
                copy.begin = null;
            }
            if (this.isSetEndPosition()) {
                TimePositionType sourceEndPosition;
                sourceEndPosition = this.getEndPosition();
                TimePositionType copyEndPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "endPosition", sourceEndPosition), sourceEndPosition));
                copy.setEndPosition(copyEndPosition);
            } else {
                copy.endPosition = null;
            }
            if (this.isSetEnd()) {
                TimeInstantPropertyType sourceEnd;
                sourceEnd = this.getEnd();
                TimeInstantPropertyType copyEnd = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "end", sourceEnd), sourceEnd));
                copy.setEnd(copyEnd);
            } else {
                copy.end = null;
            }
            if (this.isSetDuration()) {
                Duration sourceDuration;
                sourceDuration = this.getDuration();
                Duration copyDuration = ((Duration) strategy.copy(LocatorUtils.property(locator, "duration", sourceDuration), sourceDuration));
                copy.setDuration(copyDuration);
            } else {
                copy.duration = null;
            }
            if (this.isSetTimeInterval()) {
                TimeIntervalLengthType sourceTimeInterval;
                sourceTimeInterval = this.getTimeInterval();
                TimeIntervalLengthType copyTimeInterval = ((TimeIntervalLengthType) strategy.copy(LocatorUtils.property(locator, "timeInterval", sourceTimeInterval), sourceTimeInterval));
                copy.setTimeInterval(copyTimeInterval);
            } else {
                copy.timeInterval = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimePeriodType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimePeriodType) {
            final TimePeriodType target = this;
            final TimePeriodType leftObject = ((TimePeriodType) left);
            final TimePeriodType rightObject = ((TimePeriodType) right);
            {
                TimePositionType lhsBeginPosition;
                lhsBeginPosition = leftObject.getBeginPosition();
                TimePositionType rhsBeginPosition;
                rhsBeginPosition = rightObject.getBeginPosition();
                target.setBeginPosition(((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "beginPosition", lhsBeginPosition), LocatorUtils.property(rightLocator, "beginPosition", rhsBeginPosition), lhsBeginPosition, rhsBeginPosition)));
            }
            {
                TimeInstantPropertyType lhsBegin;
                lhsBegin = leftObject.getBegin();
                TimeInstantPropertyType rhsBegin;
                rhsBegin = rightObject.getBegin();
                target.setBegin(((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "begin", lhsBegin), LocatorUtils.property(rightLocator, "begin", rhsBegin), lhsBegin, rhsBegin)));
            }
            {
                TimePositionType lhsEndPosition;
                lhsEndPosition = leftObject.getEndPosition();
                TimePositionType rhsEndPosition;
                rhsEndPosition = rightObject.getEndPosition();
                target.setEndPosition(((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "endPosition", lhsEndPosition), LocatorUtils.property(rightLocator, "endPosition", rhsEndPosition), lhsEndPosition, rhsEndPosition)));
            }
            {
                TimeInstantPropertyType lhsEnd;
                lhsEnd = leftObject.getEnd();
                TimeInstantPropertyType rhsEnd;
                rhsEnd = rightObject.getEnd();
                target.setEnd(((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "end", lhsEnd), LocatorUtils.property(rightLocator, "end", rhsEnd), lhsEnd, rhsEnd)));
            }
            {
                Duration lhsDuration;
                lhsDuration = leftObject.getDuration();
                Duration rhsDuration;
                rhsDuration = rightObject.getDuration();
                target.setDuration(((Duration) strategy.merge(LocatorUtils.property(leftLocator, "duration", lhsDuration), LocatorUtils.property(rightLocator, "duration", rhsDuration), lhsDuration, rhsDuration)));
            }
            {
                TimeIntervalLengthType lhsTimeInterval;
                lhsTimeInterval = leftObject.getTimeInterval();
                TimeIntervalLengthType rhsTimeInterval;
                rhsTimeInterval = rightObject.getTimeInterval();
                target.setTimeInterval(((TimeIntervalLengthType) strategy.merge(LocatorUtils.property(leftLocator, "timeInterval", lhsTimeInterval), LocatorUtils.property(rightLocator, "timeInterval", rhsTimeInterval), lhsTimeInterval, rhsTimeInterval)));
            }
        }
    }

    public TimePeriodType withBeginPosition(TimePositionType value) {
        setBeginPosition(value);
        return this;
    }

    public TimePeriodType withBegin(TimeInstantPropertyType value) {
        setBegin(value);
        return this;
    }

    public TimePeriodType withEndPosition(TimePositionType value) {
        setEndPosition(value);
        return this;
    }

    public TimePeriodType withEnd(TimeInstantPropertyType value) {
        setEnd(value);
        return this;
    }

    public TimePeriodType withDuration(Duration value) {
        setDuration(value);
        return this;
    }

    public TimePeriodType withTimeInterval(TimeIntervalLengthType value) {
        setTimeInterval(value);
        return this;
    }

    @Override
    public TimePeriodType withFrame(String value) {
        setFrame(value);
        return this;
    }

    @Override
    public TimePeriodType withRelatedTime(RelatedTimeType... values) {
        if (values!= null) {
            for (RelatedTimeType value: values) {
                getRelatedTime().add(value);
            }
        }
        return this;
    }

    @Override
    public TimePeriodType withRelatedTime(Collection<RelatedTimeType> values) {
        if (values!= null) {
            getRelatedTime().addAll(values);
        }
        return this;
    }

    @Override
    public TimePeriodType withRelatedTime(List<RelatedTimeType> value) {
        setRelatedTime(value);
        return this;
    }

    @Override
    public TimePeriodType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimePeriodType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimePeriodType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimePeriodType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimePeriodType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimePeriodType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimePeriodType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimePeriodType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimePeriodType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimePeriodType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
