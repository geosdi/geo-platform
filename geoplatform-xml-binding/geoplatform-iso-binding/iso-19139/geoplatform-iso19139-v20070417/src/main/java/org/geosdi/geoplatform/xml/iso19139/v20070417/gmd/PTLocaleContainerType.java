/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 03:12:11 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for PT_LocaleContainer_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PT_LocaleContainer_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         &lt;element name="locale" type="{http://www.isotc211.org/2005/gmd}PT_Locale_PropertyType"/>
 *         &lt;element name="date" type="{http://www.isotc211.org/2005/gmd}CI_Date_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="responsibleParty" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="localisedString" type="{http://www.isotc211.org/2005/gmd}LocalisedCharacterString_PropertyType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
public class PTLocaleContainerType
    implements ToString
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
     * Gets the value of the description property.
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
     * Sets the value of the description property.
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
     * Gets the value of the locale property.
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
     * Sets the value of the locale property.
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the date property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIDatePropertyType }
     * 
     * 
     */
    public List<CIDatePropertyType> getDate() {
        if (date == null) {
            date = new ArrayList<CIDatePropertyType>();
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responsibleParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponsibleParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIResponsiblePartyPropertyType }
     * 
     * 
     */
    public List<CIResponsiblePartyPropertyType> getResponsibleParty() {
        if (responsibleParty == null) {
            responsibleParty = new ArrayList<CIResponsiblePartyPropertyType>();
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localisedString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalisedString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalisedCharacterStringPropertyType }
     * 
     * 
     */
    public List<LocalisedCharacterStringPropertyType> getLocalisedString() {
        if (localisedString == null) {
            localisedString = new ArrayList<LocalisedCharacterStringPropertyType>();
        }
        return this.localisedString;
    }

    public boolean isSetLocalisedString() {
        return ((this.localisedString!= null)&&(!this.localisedString.isEmpty()));
    }

    public void unsetLocalisedString() {
        this.localisedString = null;
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
        {
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            PTLocalePropertyType theLocale;
            theLocale = this.getLocale();
            strategy.appendField(locator, this, "locale", buffer, theLocale);
        }
        {
            List<CIDatePropertyType> theDate;
            theDate = (this.isSetDate()?this.getDate():null);
            strategy.appendField(locator, this, "date", buffer, theDate);
        }
        {
            List<CIResponsiblePartyPropertyType> theResponsibleParty;
            theResponsibleParty = (this.isSetResponsibleParty()?this.getResponsibleParty():null);
            strategy.appendField(locator, this, "responsibleParty", buffer, theResponsibleParty);
        }
        {
            List<LocalisedCharacterStringPropertyType> theLocalisedString;
            theLocalisedString = (this.isSetLocalisedString()?this.getLocalisedString():null);
            strategy.appendField(locator, this, "localisedString", buffer, theLocalisedString);
        }
        return buffer;
    }

    public void setDate(List<CIDatePropertyType> value) {
        this.date = null;
        List<CIDatePropertyType> draftl = this.getDate();
        draftl.addAll(value);
    }

    public void setResponsibleParty(List<CIResponsiblePartyPropertyType> value) {
        this.responsibleParty = null;
        List<CIResponsiblePartyPropertyType> draftl = this.getResponsibleParty();
        draftl.addAll(value);
    }

    public void setLocalisedString(List<LocalisedCharacterStringPropertyType> value) {
        this.localisedString = null;
        List<LocalisedCharacterStringPropertyType> draftl = this.getLocalisedString();
        draftl.addAll(value);
    }

}
