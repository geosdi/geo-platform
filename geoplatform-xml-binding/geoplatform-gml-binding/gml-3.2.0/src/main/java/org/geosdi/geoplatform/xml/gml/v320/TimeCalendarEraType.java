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
 * <p>Classe Java per TimeCalendarEraType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeCalendarEraType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}DefinitionType">
 *       <sequence>
 *         <element name="referenceEvent" type="{http://www.opengis.net/gml}StringOrRefType"/>
 *         <element name="referenceDate" type="{http://www.opengis.net/gml}CalDate"/>
 *         <element name="julianReference" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         <element name="epochOfUse" type="{http://www.opengis.net/gml}TimePeriodPropertyType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
            String theReferenceDate;
            theReferenceDate = this.getReferenceDate();
            strategy.appendField(locator, this, "referenceDate", buffer, theReferenceDate, this.isSetReferenceDate());
        }
        {
            BigDecimal theJulianReference;
            theJulianReference = this.getJulianReference();
            strategy.appendField(locator, this, "julianReference", buffer, theJulianReference, this.isSetJulianReference());
        }
        {
            TimePeriodPropertyType theEpochOfUse;
            theEpochOfUse = this.getEpochOfUse();
            strategy.appendField(locator, this, "epochOfUse", buffer, theEpochOfUse, this.isSetEpochOfUse());
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
        final TimeCalendarEraType that = ((TimeCalendarEraType) object);
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
            String lhsReferenceDate;
            lhsReferenceDate = this.getReferenceDate();
            String rhsReferenceDate;
            rhsReferenceDate = that.getReferenceDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceDate", lhsReferenceDate), LocatorUtils.property(thatLocator, "referenceDate", rhsReferenceDate), lhsReferenceDate, rhsReferenceDate, this.isSetReferenceDate(), that.isSetReferenceDate())) {
                return false;
            }
        }
        {
            BigDecimal lhsJulianReference;
            lhsJulianReference = this.getJulianReference();
            BigDecimal rhsJulianReference;
            rhsJulianReference = that.getJulianReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "julianReference", lhsJulianReference), LocatorUtils.property(thatLocator, "julianReference", rhsJulianReference), lhsJulianReference, rhsJulianReference, this.isSetJulianReference(), that.isSetJulianReference())) {
                return false;
            }
        }
        {
            TimePeriodPropertyType lhsEpochOfUse;
            lhsEpochOfUse = this.getEpochOfUse();
            TimePeriodPropertyType rhsEpochOfUse;
            rhsEpochOfUse = that.getEpochOfUse();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "epochOfUse", lhsEpochOfUse), LocatorUtils.property(thatLocator, "epochOfUse", rhsEpochOfUse), lhsEpochOfUse, rhsEpochOfUse, this.isSetEpochOfUse(), that.isSetEpochOfUse())) {
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
            String theReferenceDate;
            theReferenceDate = this.getReferenceDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceDate", theReferenceDate), currentHashCode, theReferenceDate, this.isSetReferenceDate());
        }
        {
            BigDecimal theJulianReference;
            theJulianReference = this.getJulianReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "julianReference", theJulianReference), currentHashCode, theJulianReference, this.isSetJulianReference());
        }
        {
            TimePeriodPropertyType theEpochOfUse;
            theEpochOfUse = this.getEpochOfUse();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "epochOfUse", theEpochOfUse), currentHashCode, theEpochOfUse, this.isSetEpochOfUse());
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
        if (draftCopy instanceof TimeCalendarEraType) {
            final TimeCalendarEraType copy = ((TimeCalendarEraType) draftCopy);
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
                java.lang.Boolean referenceDateShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetReferenceDate());
                if (referenceDateShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceReferenceDate;
                    sourceReferenceDate = this.getReferenceDate();
                    String copyReferenceDate = ((String) strategy.copy(LocatorUtils.property(locator, "referenceDate", sourceReferenceDate), sourceReferenceDate, this.isSetReferenceDate()));
                    copy.setReferenceDate(copyReferenceDate);
                } else {
                    if (referenceDateShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.referenceDate = null;
                    }
                }
            }
            {
                java.lang.Boolean julianReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetJulianReference());
                if (julianReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigDecimal sourceJulianReference;
                    sourceJulianReference = this.getJulianReference();
                    BigDecimal copyJulianReference = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "julianReference", sourceJulianReference), sourceJulianReference, this.isSetJulianReference()));
                    copy.setJulianReference(copyJulianReference);
                } else {
                    if (julianReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.julianReference = null;
                    }
                }
            }
            {
                java.lang.Boolean epochOfUseShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEpochOfUse());
                if (epochOfUseShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePeriodPropertyType sourceEpochOfUse;
                    sourceEpochOfUse = this.getEpochOfUse();
                    TimePeriodPropertyType copyEpochOfUse = ((TimePeriodPropertyType) strategy.copy(LocatorUtils.property(locator, "epochOfUse", sourceEpochOfUse), sourceEpochOfUse, this.isSetEpochOfUse()));
                    copy.setEpochOfUse(copyEpochOfUse);
                } else {
                    if (epochOfUseShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.epochOfUse = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeCalendarEraType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeCalendarEraType) {
            final TimeCalendarEraType target = this;
            final TimeCalendarEraType leftObject = ((TimeCalendarEraType) left);
            final TimeCalendarEraType rightObject = ((TimeCalendarEraType) right);
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
                java.lang.Boolean referenceDateShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetReferenceDate(), rightObject.isSetReferenceDate());
                if (referenceDateShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsReferenceDate;
                    lhsReferenceDate = leftObject.getReferenceDate();
                    String rhsReferenceDate;
                    rhsReferenceDate = rightObject.getReferenceDate();
                    String mergedReferenceDate = ((String) strategy.merge(LocatorUtils.property(leftLocator, "referenceDate", lhsReferenceDate), LocatorUtils.property(rightLocator, "referenceDate", rhsReferenceDate), lhsReferenceDate, rhsReferenceDate, leftObject.isSetReferenceDate(), rightObject.isSetReferenceDate()));
                    target.setReferenceDate(mergedReferenceDate);
                } else {
                    if (referenceDateShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.referenceDate = null;
                    }
                }
            }
            {
                java.lang.Boolean julianReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetJulianReference(), rightObject.isSetJulianReference());
                if (julianReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigDecimal lhsJulianReference;
                    lhsJulianReference = leftObject.getJulianReference();
                    BigDecimal rhsJulianReference;
                    rhsJulianReference = rightObject.getJulianReference();
                    BigDecimal mergedJulianReference = ((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "julianReference", lhsJulianReference), LocatorUtils.property(rightLocator, "julianReference", rhsJulianReference), lhsJulianReference, rhsJulianReference, leftObject.isSetJulianReference(), rightObject.isSetJulianReference()));
                    target.setJulianReference(mergedJulianReference);
                } else {
                    if (julianReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.julianReference = null;
                    }
                }
            }
            {
                java.lang.Boolean epochOfUseShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEpochOfUse(), rightObject.isSetEpochOfUse());
                if (epochOfUseShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePeriodPropertyType lhsEpochOfUse;
                    lhsEpochOfUse = leftObject.getEpochOfUse();
                    TimePeriodPropertyType rhsEpochOfUse;
                    rhsEpochOfUse = rightObject.getEpochOfUse();
                    TimePeriodPropertyType mergedEpochOfUse = ((TimePeriodPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "epochOfUse", lhsEpochOfUse), LocatorUtils.property(rightLocator, "epochOfUse", rhsEpochOfUse), lhsEpochOfUse, rhsEpochOfUse, leftObject.isSetEpochOfUse(), rightObject.isSetEpochOfUse()));
                    target.setEpochOfUse(mergedEpochOfUse);
                } else {
                    if (epochOfUseShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.epochOfUse = null;
                    }
                }
            }
        }
    }

}
