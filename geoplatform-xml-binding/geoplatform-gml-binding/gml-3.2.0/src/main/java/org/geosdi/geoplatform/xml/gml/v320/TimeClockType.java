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
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per TimeClockType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeClockType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       <sequence>
 *         <element name="referenceEvent" type="{http://www.opengis.net/gml}StringOrRefType"/>
 *         <element name="referenceTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         <element name="utcReference" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         <element name="dateBasis" type="{http://www.opengis.net/gml}TimeCalendarPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dateBasis property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDateBasis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeCalendarPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the dateBasis property.
     */
    public List<TimeCalendarPropertyType> getDateBasis() {
        if (dateBasis == null) {
            dateBasis = new ArrayList<>();
        }
        return this.dateBasis;
    }

    public boolean isSetDateBasis() {
        return ((this.dateBasis!= null)&&(!this.dateBasis.isEmpty()));
    }

    public void unsetDateBasis() {
        this.dateBasis = null;
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
            StringOrRefType theReferenceEvent;
            theReferenceEvent = this.getReferenceEvent();
            strategy.appendField(locator, this, "referenceEvent", buffer, theReferenceEvent, this.isSetReferenceEvent());
        }
        {
            XMLGregorianCalendar theReferenceTime;
            theReferenceTime = this.getReferenceTime();
            strategy.appendField(locator, this, "referenceTime", buffer, theReferenceTime, this.isSetReferenceTime());
        }
        {
            XMLGregorianCalendar theUtcReference;
            theUtcReference = this.getUtcReference();
            strategy.appendField(locator, this, "utcReference", buffer, theUtcReference, this.isSetUtcReference());
        }
        {
            List<TimeCalendarPropertyType> theDateBasis;
            theDateBasis = (this.isSetDateBasis()?this.getDateBasis():null);
            strategy.appendField(locator, this, "dateBasis", buffer, theDateBasis, this.isSetDateBasis());
        }
        return buffer;
    }

    public void setDateBasis(List<TimeCalendarPropertyType> value) {
        this.dateBasis = null;
        if (value!= null) {
            List<TimeCalendarPropertyType> draftl = this.getDateBasis();
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeClockType that = ((TimeClockType) object);
        {
            StringOrRefType lhsReferenceEvent;
            lhsReferenceEvent = this.getReferenceEvent();
            StringOrRefType rhsReferenceEvent;
            rhsReferenceEvent = that.getReferenceEvent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceEvent", lhsReferenceEvent), LocatorUtils.property(thatLocator, "referenceEvent", rhsReferenceEvent), lhsReferenceEvent, rhsReferenceEvent, this.isSetReferenceEvent(), that.isSetReferenceEvent())) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsReferenceTime;
            lhsReferenceTime = this.getReferenceTime();
            XMLGregorianCalendar rhsReferenceTime;
            rhsReferenceTime = that.getReferenceTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceTime", lhsReferenceTime), LocatorUtils.property(thatLocator, "referenceTime", rhsReferenceTime), lhsReferenceTime, rhsReferenceTime, this.isSetReferenceTime(), that.isSetReferenceTime())) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsUtcReference;
            lhsUtcReference = this.getUtcReference();
            XMLGregorianCalendar rhsUtcReference;
            rhsUtcReference = that.getUtcReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "utcReference", lhsUtcReference), LocatorUtils.property(thatLocator, "utcReference", rhsUtcReference), lhsUtcReference, rhsUtcReference, this.isSetUtcReference(), that.isSetUtcReference())) {
                return false;
            }
        }
        {
            List<TimeCalendarPropertyType> lhsDateBasis;
            lhsDateBasis = (this.isSetDateBasis()?this.getDateBasis():null);
            List<TimeCalendarPropertyType> rhsDateBasis;
            rhsDateBasis = (that.isSetDateBasis()?that.getDateBasis():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dateBasis", lhsDateBasis), LocatorUtils.property(thatLocator, "dateBasis", rhsDateBasis), lhsDateBasis, rhsDateBasis, this.isSetDateBasis(), that.isSetDateBasis())) {
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
            StringOrRefType theReferenceEvent;
            theReferenceEvent = this.getReferenceEvent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceEvent", theReferenceEvent), currentHashCode, theReferenceEvent, this.isSetReferenceEvent());
        }
        {
            XMLGregorianCalendar theReferenceTime;
            theReferenceTime = this.getReferenceTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceTime", theReferenceTime), currentHashCode, theReferenceTime, this.isSetReferenceTime());
        }
        {
            XMLGregorianCalendar theUtcReference;
            theUtcReference = this.getUtcReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "utcReference", theUtcReference), currentHashCode, theUtcReference, this.isSetUtcReference());
        }
        {
            List<TimeCalendarPropertyType> theDateBasis;
            theDateBasis = (this.isSetDateBasis()?this.getDateBasis():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dateBasis", theDateBasis), currentHashCode, theDateBasis, this.isSetDateBasis());
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
        if (draftCopy instanceof TimeClockType) {
            final TimeClockType copy = ((TimeClockType) draftCopy);
            {
                java.lang.Boolean referenceEventShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetReferenceEvent());
                if (referenceEventShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType sourceReferenceEvent;
                    sourceReferenceEvent = this.getReferenceEvent();
                    StringOrRefType copyReferenceEvent = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "referenceEvent", sourceReferenceEvent), sourceReferenceEvent, this.isSetReferenceEvent()));
                    copy.setReferenceEvent(copyReferenceEvent);
                } else {
                    if (referenceEventShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.referenceEvent = null;
                    }
                }
            }
            {
                java.lang.Boolean referenceTimeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetReferenceTime());
                if (referenceTimeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    XMLGregorianCalendar sourceReferenceTime;
                    sourceReferenceTime = this.getReferenceTime();
                    XMLGregorianCalendar copyReferenceTime = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "referenceTime", sourceReferenceTime), sourceReferenceTime, this.isSetReferenceTime()));
                    copy.setReferenceTime(copyReferenceTime);
                } else {
                    if (referenceTimeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.referenceTime = null;
                    }
                }
            }
            {
                java.lang.Boolean utcReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUtcReference());
                if (utcReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    XMLGregorianCalendar sourceUtcReference;
                    sourceUtcReference = this.getUtcReference();
                    XMLGregorianCalendar copyUtcReference = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "utcReference", sourceUtcReference), sourceUtcReference, this.isSetUtcReference()));
                    copy.setUtcReference(copyUtcReference);
                } else {
                    if (utcReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.utcReference = null;
                    }
                }
            }
            {
                java.lang.Boolean dateBasisShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDateBasis());
                if (dateBasisShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeCalendarPropertyType> sourceDateBasis;
                    sourceDateBasis = (this.isSetDateBasis()?this.getDateBasis():null);
                    @SuppressWarnings("unchecked")
                    List<TimeCalendarPropertyType> copyDateBasis = ((List<TimeCalendarPropertyType> ) strategy.copy(LocatorUtils.property(locator, "dateBasis", sourceDateBasis), sourceDateBasis, this.isSetDateBasis()));
                    copy.setDateBasis(copyDateBasis);
                } else {
                    if (dateBasisShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDateBasis();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeClockType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeClockType) {
            final TimeClockType target = this;
            final TimeClockType leftObject = ((TimeClockType) left);
            final TimeClockType rightObject = ((TimeClockType) right);
            {
                java.lang.Boolean referenceEventShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetReferenceEvent(), rightObject.isSetReferenceEvent());
                if (referenceEventShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType lhsReferenceEvent;
                    lhsReferenceEvent = leftObject.getReferenceEvent();
                    StringOrRefType rhsReferenceEvent;
                    rhsReferenceEvent = rightObject.getReferenceEvent();
                    StringOrRefType mergedReferenceEvent = ((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "referenceEvent", lhsReferenceEvent), LocatorUtils.property(rightLocator, "referenceEvent", rhsReferenceEvent), lhsReferenceEvent, rhsReferenceEvent, leftObject.isSetReferenceEvent(), rightObject.isSetReferenceEvent()));
                    target.setReferenceEvent(mergedReferenceEvent);
                } else {
                    if (referenceEventShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.referenceEvent = null;
                    }
                }
            }
            {
                java.lang.Boolean referenceTimeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetReferenceTime(), rightObject.isSetReferenceTime());
                if (referenceTimeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    XMLGregorianCalendar lhsReferenceTime;
                    lhsReferenceTime = leftObject.getReferenceTime();
                    XMLGregorianCalendar rhsReferenceTime;
                    rhsReferenceTime = rightObject.getReferenceTime();
                    XMLGregorianCalendar mergedReferenceTime = ((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "referenceTime", lhsReferenceTime), LocatorUtils.property(rightLocator, "referenceTime", rhsReferenceTime), lhsReferenceTime, rhsReferenceTime, leftObject.isSetReferenceTime(), rightObject.isSetReferenceTime()));
                    target.setReferenceTime(mergedReferenceTime);
                } else {
                    if (referenceTimeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.referenceTime = null;
                    }
                }
            }
            {
                java.lang.Boolean utcReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUtcReference(), rightObject.isSetUtcReference());
                if (utcReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    XMLGregorianCalendar lhsUtcReference;
                    lhsUtcReference = leftObject.getUtcReference();
                    XMLGregorianCalendar rhsUtcReference;
                    rhsUtcReference = rightObject.getUtcReference();
                    XMLGregorianCalendar mergedUtcReference = ((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "utcReference", lhsUtcReference), LocatorUtils.property(rightLocator, "utcReference", rhsUtcReference), lhsUtcReference, rhsUtcReference, leftObject.isSetUtcReference(), rightObject.isSetUtcReference()));
                    target.setUtcReference(mergedUtcReference);
                } else {
                    if (utcReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.utcReference = null;
                    }
                }
            }
            {
                java.lang.Boolean dateBasisShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDateBasis(), rightObject.isSetDateBasis());
                if (dateBasisShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeCalendarPropertyType> lhsDateBasis;
                    lhsDateBasis = (leftObject.isSetDateBasis()?leftObject.getDateBasis():null);
                    List<TimeCalendarPropertyType> rhsDateBasis;
                    rhsDateBasis = (rightObject.isSetDateBasis()?rightObject.getDateBasis():null);
                    List<TimeCalendarPropertyType> mergedDateBasis = ((List<TimeCalendarPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "dateBasis", lhsDateBasis), LocatorUtils.property(rightLocator, "dateBasis", rhsDateBasis), lhsDateBasis, rhsDateBasis, leftObject.isSetDateBasis(), rightObject.isSetDateBasis()));
                    target.setDateBasis(mergedDateBasis);
                } else {
                    if (dateBasisShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDateBasis();
                    }
                }
            }
        }
    }

}
