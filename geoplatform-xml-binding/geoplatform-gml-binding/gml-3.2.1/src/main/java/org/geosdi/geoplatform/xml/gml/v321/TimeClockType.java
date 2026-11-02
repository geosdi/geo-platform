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

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per TimeClockType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeClockType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}TimeReferenceSystemType">
 *       <sequence>
 *         <element name="referenceEvent" type="{http://www.opengis.net/gml/3.2}StringOrRefType"/>
 *         <element name="referenceTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         <element name="utcReference" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         <element name="dateBasis" type="{http://www.opengis.net/gml/3.2}TimeCalendarPropertyType" maxOccurs="unbounded" minOccurs="0"/>
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
    implements ToString
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

}
