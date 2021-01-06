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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
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
 * <p>Classe Java per TimeClockType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeClockType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       &lt;sequence>
 *         &lt;element name="referenceEvent" type="{http://www.opengis.net/gml}StringOrRefType"/>
 *         &lt;element name="referenceTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="utcReference" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="dateBasis" type="{http://www.opengis.net/gml}TimeCalendarPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeClockType", propOrder = {
    "referenceEvent",
    "referenceTime",
    "utcReference",
    "dateBasis"
})
public class TimeClockType
    extends TimeReferenceSystemType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected StringOrRefType referenceEvent;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar referenceTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar utcReference;
    protected List<TimeCalendarPropertyType> dateBasis;

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
     * Recupera il valore della proprietà referenceTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReferenceTime() {
        return referenceTime;
    }

    /**
     * Imposta il valore della proprietà referenceTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReferenceTime(XMLGregorianCalendar value) {
        this.referenceTime = value;
    }

    public boolean isSetReferenceTime() {
        return (this.referenceTime!= null);
    }

    /**
     * Recupera il valore della proprietà utcReference.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUtcReference() {
        return utcReference;
    }

    /**
     * Imposta il valore della proprietà utcReference.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUtcReference(XMLGregorianCalendar value) {
        this.utcReference = value;
    }

    public boolean isSetUtcReference() {
        return (this.utcReference!= null);
    }

    /**
     * Gets the value of the dateBasis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dateBasis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDateBasis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeCalendarPropertyType }
     * 
     * 
     */
    public List<TimeCalendarPropertyType> getDateBasis() {
        if (dateBasis == null) {
            dateBasis = new ArrayList<TimeCalendarPropertyType>();
        }
        return this.dateBasis;
    }

    public boolean isSetDateBasis() {
        return ((this.dateBasis!= null)&&(!this.dateBasis.isEmpty()));
    }

    public void unsetDateBasis() {
        this.dateBasis = null;
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
            XMLGregorianCalendar theReferenceTime;
            theReferenceTime = this.getReferenceTime();
            strategy.appendField(locator, this, "referenceTime", buffer, theReferenceTime);
        }
        {
            XMLGregorianCalendar theUtcReference;
            theUtcReference = this.getUtcReference();
            strategy.appendField(locator, this, "utcReference", buffer, theUtcReference);
        }
        {
            List<TimeCalendarPropertyType> theDateBasis;
            theDateBasis = this.getDateBasis();
            strategy.appendField(locator, this, "dateBasis", buffer, theDateBasis);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeClockType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeClockType that = ((TimeClockType) object);
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
            XMLGregorianCalendar lhsReferenceTime;
            lhsReferenceTime = this.getReferenceTime();
            XMLGregorianCalendar rhsReferenceTime;
            rhsReferenceTime = that.getReferenceTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceTime", lhsReferenceTime), LocatorUtils.property(thatLocator, "referenceTime", rhsReferenceTime), lhsReferenceTime, rhsReferenceTime)) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsUtcReference;
            lhsUtcReference = this.getUtcReference();
            XMLGregorianCalendar rhsUtcReference;
            rhsUtcReference = that.getUtcReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "utcReference", lhsUtcReference), LocatorUtils.property(thatLocator, "utcReference", rhsUtcReference), lhsUtcReference, rhsUtcReference)) {
                return false;
            }
        }
        {
            List<TimeCalendarPropertyType> lhsDateBasis;
            lhsDateBasis = this.getDateBasis();
            List<TimeCalendarPropertyType> rhsDateBasis;
            rhsDateBasis = that.getDateBasis();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dateBasis", lhsDateBasis), LocatorUtils.property(thatLocator, "dateBasis", rhsDateBasis), lhsDateBasis, rhsDateBasis)) {
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
            XMLGregorianCalendar theReferenceTime;
            theReferenceTime = this.getReferenceTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceTime", theReferenceTime), currentHashCode, theReferenceTime);
        }
        {
            XMLGregorianCalendar theUtcReference;
            theUtcReference = this.getUtcReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "utcReference", theUtcReference), currentHashCode, theUtcReference);
        }
        {
            List<TimeCalendarPropertyType> theDateBasis;
            theDateBasis = this.getDateBasis();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dateBasis", theDateBasis), currentHashCode, theDateBasis);
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
        if (draftCopy instanceof TimeClockType) {
            final TimeClockType copy = ((TimeClockType) draftCopy);
            if (this.isSetReferenceEvent()) {
                StringOrRefType sourceReferenceEvent;
                sourceReferenceEvent = this.getReferenceEvent();
                StringOrRefType copyReferenceEvent = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "referenceEvent", sourceReferenceEvent), sourceReferenceEvent));
                copy.setReferenceEvent(copyReferenceEvent);
            } else {
                copy.referenceEvent = null;
            }
            if (this.isSetReferenceTime()) {
                XMLGregorianCalendar sourceReferenceTime;
                sourceReferenceTime = this.getReferenceTime();
                XMLGregorianCalendar copyReferenceTime = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "referenceTime", sourceReferenceTime), sourceReferenceTime));
                copy.setReferenceTime(copyReferenceTime);
            } else {
                copy.referenceTime = null;
            }
            if (this.isSetUtcReference()) {
                XMLGregorianCalendar sourceUtcReference;
                sourceUtcReference = this.getUtcReference();
                XMLGregorianCalendar copyUtcReference = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "utcReference", sourceUtcReference), sourceUtcReference));
                copy.setUtcReference(copyUtcReference);
            } else {
                copy.utcReference = null;
            }
            if (this.isSetDateBasis()) {
                List<TimeCalendarPropertyType> sourceDateBasis;
                sourceDateBasis = this.getDateBasis();
                @SuppressWarnings("unchecked")
                List<TimeCalendarPropertyType> copyDateBasis = ((List<TimeCalendarPropertyType> ) strategy.copy(LocatorUtils.property(locator, "dateBasis", sourceDateBasis), sourceDateBasis));
                copy.unsetDateBasis();
                List<TimeCalendarPropertyType> uniqueDateBasisl = copy.getDateBasis();
                uniqueDateBasisl.addAll(copyDateBasis);
            } else {
                copy.unsetDateBasis();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimeClockType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeClockType) {
            final TimeClockType target = this;
            final TimeClockType leftObject = ((TimeClockType) left);
            final TimeClockType rightObject = ((TimeClockType) right);
            {
                StringOrRefType lhsReferenceEvent;
                lhsReferenceEvent = leftObject.getReferenceEvent();
                StringOrRefType rhsReferenceEvent;
                rhsReferenceEvent = rightObject.getReferenceEvent();
                target.setReferenceEvent(((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "referenceEvent", lhsReferenceEvent), LocatorUtils.property(rightLocator, "referenceEvent", rhsReferenceEvent), lhsReferenceEvent, rhsReferenceEvent)));
            }
            {
                XMLGregorianCalendar lhsReferenceTime;
                lhsReferenceTime = leftObject.getReferenceTime();
                XMLGregorianCalendar rhsReferenceTime;
                rhsReferenceTime = rightObject.getReferenceTime();
                target.setReferenceTime(((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "referenceTime", lhsReferenceTime), LocatorUtils.property(rightLocator, "referenceTime", rhsReferenceTime), lhsReferenceTime, rhsReferenceTime)));
            }
            {
                XMLGregorianCalendar lhsUtcReference;
                lhsUtcReference = leftObject.getUtcReference();
                XMLGregorianCalendar rhsUtcReference;
                rhsUtcReference = rightObject.getUtcReference();
                target.setUtcReference(((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "utcReference", lhsUtcReference), LocatorUtils.property(rightLocator, "utcReference", rhsUtcReference), lhsUtcReference, rhsUtcReference)));
            }
            {
                List<TimeCalendarPropertyType> lhsDateBasis;
                lhsDateBasis = leftObject.getDateBasis();
                List<TimeCalendarPropertyType> rhsDateBasis;
                rhsDateBasis = rightObject.getDateBasis();
                target.unsetDateBasis();
                List<TimeCalendarPropertyType> uniqueDateBasisl = target.getDateBasis();
                uniqueDateBasisl.addAll(((List<TimeCalendarPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "dateBasis", lhsDateBasis), LocatorUtils.property(rightLocator, "dateBasis", rhsDateBasis), lhsDateBasis, rhsDateBasis)));
            }
        }
    }

    public void setDateBasis(List<TimeCalendarPropertyType> value) {
        List<TimeCalendarPropertyType> draftl = this.getDateBasis();
        draftl.addAll(value);
    }

    public TimeClockType withReferenceEvent(StringOrRefType value) {
        setReferenceEvent(value);
        return this;
    }

    public TimeClockType withReferenceTime(XMLGregorianCalendar value) {
        setReferenceTime(value);
        return this;
    }

    public TimeClockType withUtcReference(XMLGregorianCalendar value) {
        setUtcReference(value);
        return this;
    }

    public TimeClockType withDateBasis(TimeCalendarPropertyType... values) {
        if (values!= null) {
            for (TimeCalendarPropertyType value: values) {
                getDateBasis().add(value);
            }
        }
        return this;
    }

    public TimeClockType withDateBasis(Collection<TimeCalendarPropertyType> values) {
        if (values!= null) {
            getDateBasis().addAll(values);
        }
        return this;
    }

    public TimeClockType withDateBasis(List<TimeCalendarPropertyType> value) {
        setDateBasis(value);
        return this;
    }

    @Override
    public TimeClockType withDomainOfValidity(String value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public TimeClockType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TimeClockType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeClockType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeClockType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeClockType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeClockType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeClockType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeClockType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeClockType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeClockType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeClockType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
