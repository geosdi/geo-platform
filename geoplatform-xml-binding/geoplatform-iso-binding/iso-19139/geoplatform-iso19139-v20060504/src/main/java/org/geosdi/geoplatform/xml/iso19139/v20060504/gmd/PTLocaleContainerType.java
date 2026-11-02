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
// Generato il: 2026.11.02 alle 03:38:59 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20060504.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per PT_LocaleContainer_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PT_LocaleContainer_Type">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="locale" type="{http://www.isotc211.org/2005/gmd}PT_Locale_PropertyType"/>
 *         <element name="date" type="{http://www.isotc211.org/2005/gmd}CI_Date_PropertyType" maxOccurs="unbounded"/>
 *         <element name="responsibleParty" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded"/>
 *         <element name="localisedString" type="{http://www.isotc211.org/2005/gmd}LocalisedCharacterString_PropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PT_LocaleContainer_Type", propOrder = {
    "description",
    "locale",
    "date",
    "responsibleParty",
    "localisedString"
})
public class PTLocaleContainerType implements ToString
{

    @XmlElement(required = true)
    protected CharacterStringPropertyType description;
    @XmlElement(required = true)
    protected PTLocalePropertyType locale;
    @XmlElement(required = true)
    protected List<CIDatePropertyType> date;
    @XmlElement(required = true)
    protected List<CIResponsiblePartyPropertyType> responsibleParty;
    @XmlElement(required = true)
    protected List<LocalisedCharacterStringPropertyType> localisedString;

    /**
     * Recupera il valore della proprietà description.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDescription(CharacterStringPropertyType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Recupera il valore della proprietà locale.
     * 
     * @return
     *     possible object is
     *     {@link PTLocalePropertyType }
     *     
     */
    public PTLocalePropertyType getLocale() {
        return locale;
    }

    /**
     * Imposta il valore della proprietà locale.
     * 
     * @param value
     *     allowed object is
     *     {@link PTLocalePropertyType }
     *     
     */
    public void setLocale(PTLocalePropertyType value) {
        this.locale = value;
    }

    public boolean isSetLocale() {
        return (this.locale!= null);
    }

    /**
     * Gets the value of the date property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the date property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIDatePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the date property.
     */
    public List<CIDatePropertyType> getDate() {
        if (date == null) {
            date = new ArrayList<>();
        }
        return this.date;
    }

    public boolean isSetDate() {
        return ((this.date!= null)&&(!this.date.isEmpty()));
    }

    public void unsetDate() {
        this.date = null;
    }

    /**
     * Gets the value of the responsibleParty property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the responsibleParty property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getResponsibleParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIResponsiblePartyPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the responsibleParty property.
     */
    public List<CIResponsiblePartyPropertyType> getResponsibleParty() {
        if (responsibleParty == null) {
            responsibleParty = new ArrayList<>();
        }
        return this.responsibleParty;
    }

    public boolean isSetResponsibleParty() {
        return ((this.responsibleParty!= null)&&(!this.responsibleParty.isEmpty()));
    }

    public void unsetResponsibleParty() {
        this.responsibleParty = null;
    }

    /**
     * Gets the value of the localisedString property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the localisedString property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLocalisedString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalisedCharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the localisedString property.
     */
    public List<LocalisedCharacterStringPropertyType> getLocalisedString() {
        if (localisedString == null) {
            localisedString = new ArrayList<>();
        }
        return this.localisedString;
    }

    public boolean isSetLocalisedString() {
        return ((this.localisedString!= null)&&(!this.localisedString.isEmpty()));
    }

    public void unsetLocalisedString() {
        this.localisedString = null;
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            PTLocalePropertyType theLocale;
            theLocale = this.getLocale();
            strategy.appendField(locator, this, "locale", buffer, theLocale, this.isSetLocale());
        }
        {
            List<CIDatePropertyType> theDate;
            theDate = (this.isSetDate()?this.getDate():null);
            strategy.appendField(locator, this, "date", buffer, theDate, this.isSetDate());
        }
        {
            List<CIResponsiblePartyPropertyType> theResponsibleParty;
            theResponsibleParty = (this.isSetResponsibleParty()?this.getResponsibleParty():null);
            strategy.appendField(locator, this, "responsibleParty", buffer, theResponsibleParty, this.isSetResponsibleParty());
        }
        {
            List<LocalisedCharacterStringPropertyType> theLocalisedString;
            theLocalisedString = (this.isSetLocalisedString()?this.getLocalisedString():null);
            strategy.appendField(locator, this, "localisedString", buffer, theLocalisedString, this.isSetLocalisedString());
        }
        return buffer;
    }

    public void setDate(List<CIDatePropertyType> value) {
        this.date = null;
        if (value!= null) {
            List<CIDatePropertyType> draftl = this.getDate();
            draftl.addAll(value);
        }
    }

    public void setResponsibleParty(List<CIResponsiblePartyPropertyType> value) {
        this.responsibleParty = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getResponsibleParty();
            draftl.addAll(value);
        }
    }

    public void setLocalisedString(List<LocalisedCharacterStringPropertyType> value) {
        this.localisedString = null;
        if (value!= null) {
            List<LocalisedCharacterStringPropertyType> draftl = this.getLocalisedString();
            draftl.addAll(value);
        }
    }

}
