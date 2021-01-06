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
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per TimeCalendarEraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeCalendarEraType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}DefinitionType">
 *       &lt;sequence>
 *         &lt;element name="referenceEvent" type="{http://www.opengis.net/gml}StringOrRefType"/>
 *         &lt;element name="referenceDate" type="{http://www.opengis.net/gml}CalDate"/>
 *         &lt;element name="julianReference" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="epochOfUse" type="{http://www.opengis.net/gml}TimePeriodPropertyType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeCalendarEraType", propOrder = {
    "referenceEvent",
    "referenceDate",
    "julianReference",
    "epochOfUse"
})
public class TimeCalendarEraType
    extends DefinitionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected StringOrRefType referenceEvent;
    @XmlElement(required = true)
    protected String referenceDate;
    @XmlElement(required = true)
    protected BigDecimal julianReference;
    @XmlElement(required = true)
    protected TimePeriodPropertyType epochOfUse;

    /**
     * Recupera il valore della proprietà referenceEvent.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getReferenceEvent() {
        return referenceEvent;
    }

    /**
     * Imposta il valore della proprietà referenceEvent.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setReferenceEvent(StringOrRefType value) {
        this.referenceEvent = value;
    }

    public boolean isSetReferenceEvent() {
        return (this.referenceEvent!= null);
    }

    /**
     * Recupera il valore della proprietà referenceDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceDate() {
        return referenceDate;
    }

    /**
     * Imposta il valore della proprietà referenceDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceDate(String value) {
        this.referenceDate = value;
    }

    public boolean isSetReferenceDate() {
        return (this.referenceDate!= null);
    }

    /**
     * Recupera il valore della proprietà julianReference.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getJulianReference() {
        return julianReference;
    }

    /**
     * Imposta il valore della proprietà julianReference.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setJulianReference(BigDecimal value) {
        this.julianReference = value;
    }

    public boolean isSetJulianReference() {
        return (this.julianReference!= null);
    }

    /**
     * Recupera il valore della proprietà epochOfUse.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodPropertyType }
     *     
     */
    public TimePeriodPropertyType getEpochOfUse() {
        return epochOfUse;
    }

    /**
     * Imposta il valore della proprietà epochOfUse.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodPropertyType }
     *     
     */
    public void setEpochOfUse(TimePeriodPropertyType value) {
        this.epochOfUse = value;
    }

    public boolean isSetEpochOfUse() {
        return (this.epochOfUse!= null);
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
            StringOrRefType theReferenceEvent;
            theReferenceEvent = this.getReferenceEvent();
            strategy.appendField(locator, this, "referenceEvent", buffer, theReferenceEvent);
        }
        {
            String theReferenceDate;
            theReferenceDate = this.getReferenceDate();
            strategy.appendField(locator, this, "referenceDate", buffer, theReferenceDate);
        }
        {
            BigDecimal theJulianReference;
            theJulianReference = this.getJulianReference();
            strategy.appendField(locator, this, "julianReference", buffer, theJulianReference);
        }
        {
            TimePeriodPropertyType theEpochOfUse;
            theEpochOfUse = this.getEpochOfUse();
            strategy.appendField(locator, this, "epochOfUse", buffer, theEpochOfUse);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeCalendarEraType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeCalendarEraType that = ((TimeCalendarEraType) object);
        {
            StringOrRefType lhsReferenceEvent;
            lhsReferenceEvent = this.getReferenceEvent();
            StringOrRefType rhsReferenceEvent;
            rhsReferenceEvent = that.getReferenceEvent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceEvent", lhsReferenceEvent), LocatorUtils.property(thatLocator, "referenceEvent", rhsReferenceEvent), lhsReferenceEvent, rhsReferenceEvent)) {
                return false;
            }
        }
        {
            String lhsReferenceDate;
            lhsReferenceDate = this.getReferenceDate();
            String rhsReferenceDate;
            rhsReferenceDate = that.getReferenceDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceDate", lhsReferenceDate), LocatorUtils.property(thatLocator, "referenceDate", rhsReferenceDate), lhsReferenceDate, rhsReferenceDate)) {
                return false;
            }
        }
        {
            BigDecimal lhsJulianReference;
            lhsJulianReference = this.getJulianReference();
            BigDecimal rhsJulianReference;
            rhsJulianReference = that.getJulianReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "julianReference", lhsJulianReference), LocatorUtils.property(thatLocator, "julianReference", rhsJulianReference), lhsJulianReference, rhsJulianReference)) {
                return false;
            }
        }
        {
            TimePeriodPropertyType lhsEpochOfUse;
            lhsEpochOfUse = this.getEpochOfUse();
            TimePeriodPropertyType rhsEpochOfUse;
            rhsEpochOfUse = that.getEpochOfUse();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "epochOfUse", lhsEpochOfUse), LocatorUtils.property(thatLocator, "epochOfUse", rhsEpochOfUse), lhsEpochOfUse, rhsEpochOfUse)) {
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
            StringOrRefType theReferenceEvent;
            theReferenceEvent = this.getReferenceEvent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceEvent", theReferenceEvent), currentHashCode, theReferenceEvent);
        }
        {
            String theReferenceDate;
            theReferenceDate = this.getReferenceDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceDate", theReferenceDate), currentHashCode, theReferenceDate);
        }
        {
            BigDecimal theJulianReference;
            theJulianReference = this.getJulianReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "julianReference", theJulianReference), currentHashCode, theJulianReference);
        }
        {
            TimePeriodPropertyType theEpochOfUse;
            theEpochOfUse = this.getEpochOfUse();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "epochOfUse", theEpochOfUse), currentHashCode, theEpochOfUse);
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
        if (draftCopy instanceof TimeCalendarEraType) {
            final TimeCalendarEraType copy = ((TimeCalendarEraType) draftCopy);
            if (this.isSetReferenceEvent()) {
                StringOrRefType sourceReferenceEvent;
                sourceReferenceEvent = this.getReferenceEvent();
                StringOrRefType copyReferenceEvent = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "referenceEvent", sourceReferenceEvent), sourceReferenceEvent));
                copy.setReferenceEvent(copyReferenceEvent);
            } else {
                copy.referenceEvent = null;
            }
            if (this.isSetReferenceDate()) {
                String sourceReferenceDate;
                sourceReferenceDate = this.getReferenceDate();
                String copyReferenceDate = ((String) strategy.copy(LocatorUtils.property(locator, "referenceDate", sourceReferenceDate), sourceReferenceDate));
                copy.setReferenceDate(copyReferenceDate);
            } else {
                copy.referenceDate = null;
            }
            if (this.isSetJulianReference()) {
                BigDecimal sourceJulianReference;
                sourceJulianReference = this.getJulianReference();
                BigDecimal copyJulianReference = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "julianReference", sourceJulianReference), sourceJulianReference));
                copy.setJulianReference(copyJulianReference);
            } else {
                copy.julianReference = null;
            }
            if (this.isSetEpochOfUse()) {
                TimePeriodPropertyType sourceEpochOfUse;
                sourceEpochOfUse = this.getEpochOfUse();
                TimePeriodPropertyType copyEpochOfUse = ((TimePeriodPropertyType) strategy.copy(LocatorUtils.property(locator, "epochOfUse", sourceEpochOfUse), sourceEpochOfUse));
                copy.setEpochOfUse(copyEpochOfUse);
            } else {
                copy.epochOfUse = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimeCalendarEraType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeCalendarEraType) {
            final TimeCalendarEraType target = this;
            final TimeCalendarEraType leftObject = ((TimeCalendarEraType) left);
            final TimeCalendarEraType rightObject = ((TimeCalendarEraType) right);
            {
                StringOrRefType lhsReferenceEvent;
                lhsReferenceEvent = leftObject.getReferenceEvent();
                StringOrRefType rhsReferenceEvent;
                rhsReferenceEvent = rightObject.getReferenceEvent();
                target.setReferenceEvent(((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "referenceEvent", lhsReferenceEvent), LocatorUtils.property(rightLocator, "referenceEvent", rhsReferenceEvent), lhsReferenceEvent, rhsReferenceEvent)));
            }
            {
                String lhsReferenceDate;
                lhsReferenceDate = leftObject.getReferenceDate();
                String rhsReferenceDate;
                rhsReferenceDate = rightObject.getReferenceDate();
                target.setReferenceDate(((String) strategy.merge(LocatorUtils.property(leftLocator, "referenceDate", lhsReferenceDate), LocatorUtils.property(rightLocator, "referenceDate", rhsReferenceDate), lhsReferenceDate, rhsReferenceDate)));
            }
            {
                BigDecimal lhsJulianReference;
                lhsJulianReference = leftObject.getJulianReference();
                BigDecimal rhsJulianReference;
                rhsJulianReference = rightObject.getJulianReference();
                target.setJulianReference(((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "julianReference", lhsJulianReference), LocatorUtils.property(rightLocator, "julianReference", rhsJulianReference), lhsJulianReference, rhsJulianReference)));
            }
            {
                TimePeriodPropertyType lhsEpochOfUse;
                lhsEpochOfUse = leftObject.getEpochOfUse();
                TimePeriodPropertyType rhsEpochOfUse;
                rhsEpochOfUse = rightObject.getEpochOfUse();
                target.setEpochOfUse(((TimePeriodPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "epochOfUse", lhsEpochOfUse), LocatorUtils.property(rightLocator, "epochOfUse", rhsEpochOfUse), lhsEpochOfUse, rhsEpochOfUse)));
            }
        }
    }

    public TimeCalendarEraType withReferenceEvent(StringOrRefType value) {
        setReferenceEvent(value);
        return this;
    }

    public TimeCalendarEraType withReferenceDate(String value) {
        setReferenceDate(value);
        return this;
    }

    public TimeCalendarEraType withJulianReference(BigDecimal value) {
        setJulianReference(value);
        return this;
    }

    public TimeCalendarEraType withEpochOfUse(TimePeriodPropertyType value) {
        setEpochOfUse(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeCalendarEraType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeCalendarEraType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeCalendarEraType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeCalendarEraType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeCalendarEraType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
