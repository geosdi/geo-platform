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
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmx;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.DatePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDCharacterSetCodePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.PTLocalePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per AbstractCT_Catalogue_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractCT_Catalogue_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="scope" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded"/>
 *         <element name="fieldOfApplication" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="versionNumber" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="versionDate" type="{http://www.isotc211.org/2005/gco}Date_PropertyType"/>
 *         <element name="language" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="characterSet" type="{http://www.isotc211.org/2005/gmd}MD_CharacterSetCode_PropertyType" minOccurs="0"/>
 *         <element name="locale" type="{http://www.isotc211.org/2005/gmd}PT_Locale_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="subCatalogue" type="{http://www.isotc211.org/2005/gmx}CT_Catalogue_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCT_Catalogue_Type", propOrder = {
    "name",
    "scope",
    "fieldOfApplication",
    "versionNumber",
    "versionDate",
    "language",
    "characterSet",
    "locale",
    "subCatalogue"
})
@XmlSeeAlso({
    CTCodelistCatalogueType.class,
    CTCrsCatalogueType.class,
    CTUomCatalogueType.class
})
public abstract class AbstractCTCatalogueType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CharacterStringPropertyType name;
    @XmlElement(required = true)
    protected List<CharacterStringPropertyType> scope;
    protected List<CharacterStringPropertyType> fieldOfApplication;
    @XmlElement(required = true)
    protected CharacterStringPropertyType versionNumber;
    @XmlElement(required = true)
    protected DatePropertyType versionDate;
    protected CharacterStringPropertyType language;
    protected MDCharacterSetCodePropertyType characterSet;
    protected List<PTLocalePropertyType> locale;
    protected List<CTCataloguePropertyType> subCatalogue;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setName(CharacterStringPropertyType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the scope property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the scope property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getScope().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the scope property.
     */
    public List<CharacterStringPropertyType> getScope() {
        if (scope == null) {
            scope = new ArrayList<>();
        }
        return this.scope;
    }

    public boolean isSetScope() {
        return ((this.scope!= null)&&(!this.scope.isEmpty()));
    }

    public void unsetScope() {
        this.scope = null;
    }

    /**
     * Gets the value of the fieldOfApplication property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the fieldOfApplication property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFieldOfApplication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the fieldOfApplication property.
     */
    public List<CharacterStringPropertyType> getFieldOfApplication() {
        if (fieldOfApplication == null) {
            fieldOfApplication = new ArrayList<>();
        }
        return this.fieldOfApplication;
    }

    public boolean isSetFieldOfApplication() {
        return ((this.fieldOfApplication!= null)&&(!this.fieldOfApplication.isEmpty()));
    }

    public void unsetFieldOfApplication() {
        this.fieldOfApplication = null;
    }

    /**
     * Recupera il valore della proprietà versionNumber.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getVersionNumber() {
        return versionNumber;
    }

    /**
     * Imposta il valore della proprietà versionNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setVersionNumber(CharacterStringPropertyType value) {
        this.versionNumber = value;
    }

    public boolean isSetVersionNumber() {
        return (this.versionNumber!= null);
    }

    /**
     * Recupera il valore della proprietà versionDate.
     * 
     * @return
     *     possible object is
     *     {@link DatePropertyType }
     *     
     */
    public DatePropertyType getVersionDate() {
        return versionDate;
    }

    /**
     * Imposta il valore della proprietà versionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DatePropertyType }
     *     
     */
    public void setVersionDate(DatePropertyType value) {
        this.versionDate = value;
    }

    public boolean isSetVersionDate() {
        return (this.versionDate!= null);
    }

    /**
     * Recupera il valore della proprietà language.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getLanguage() {
        return language;
    }

    /**
     * Imposta il valore della proprietà language.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setLanguage(CharacterStringPropertyType value) {
        this.language = value;
    }

    public boolean isSetLanguage() {
        return (this.language!= null);
    }

    /**
     * Recupera il valore della proprietà characterSet.
     * 
     * @return
     *     possible object is
     *     {@link MDCharacterSetCodePropertyType }
     *     
     */
    public MDCharacterSetCodePropertyType getCharacterSet() {
        return characterSet;
    }

    /**
     * Imposta il valore della proprietà characterSet.
     * 
     * @param value
     *     allowed object is
     *     {@link MDCharacterSetCodePropertyType }
     *     
     */
    public void setCharacterSet(MDCharacterSetCodePropertyType value) {
        this.characterSet = value;
    }

    public boolean isSetCharacterSet() {
        return (this.characterSet!= null);
    }

    /**
     * Gets the value of the locale property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the locale property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLocale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PTLocalePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the locale property.
     */
    public List<PTLocalePropertyType> getLocale() {
        if (locale == null) {
            locale = new ArrayList<>();
        }
        return this.locale;
    }

    public boolean isSetLocale() {
        return ((this.locale!= null)&&(!this.locale.isEmpty()));
    }

    public void unsetLocale() {
        this.locale = null;
    }

    /**
     * Gets the value of the subCatalogue property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the subCatalogue property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSubCatalogue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTCataloguePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the subCatalogue property.
     */
    public List<CTCataloguePropertyType> getSubCatalogue() {
        if (subCatalogue == null) {
            subCatalogue = new ArrayList<>();
        }
        return this.subCatalogue;
    }

    public boolean isSetSubCatalogue() {
        return ((this.subCatalogue!= null)&&(!this.subCatalogue.isEmpty()));
    }

    public void unsetSubCatalogue() {
        this.subCatalogue = null;
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
            CharacterStringPropertyType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            List<CharacterStringPropertyType> theScope;
            theScope = (this.isSetScope()?this.getScope():null);
            strategy.appendField(locator, this, "scope", buffer, theScope, this.isSetScope());
        }
        {
            List<CharacterStringPropertyType> theFieldOfApplication;
            theFieldOfApplication = (this.isSetFieldOfApplication()?this.getFieldOfApplication():null);
            strategy.appendField(locator, this, "fieldOfApplication", buffer, theFieldOfApplication, this.isSetFieldOfApplication());
        }
        {
            CharacterStringPropertyType theVersionNumber;
            theVersionNumber = this.getVersionNumber();
            strategy.appendField(locator, this, "versionNumber", buffer, theVersionNumber, this.isSetVersionNumber());
        }
        {
            DatePropertyType theVersionDate;
            theVersionDate = this.getVersionDate();
            strategy.appendField(locator, this, "versionDate", buffer, theVersionDate, this.isSetVersionDate());
        }
        {
            CharacterStringPropertyType theLanguage;
            theLanguage = this.getLanguage();
            strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
        }
        {
            MDCharacterSetCodePropertyType theCharacterSet;
            theCharacterSet = this.getCharacterSet();
            strategy.appendField(locator, this, "characterSet", buffer, theCharacterSet, this.isSetCharacterSet());
        }
        {
            List<PTLocalePropertyType> theLocale;
            theLocale = (this.isSetLocale()?this.getLocale():null);
            strategy.appendField(locator, this, "locale", buffer, theLocale, this.isSetLocale());
        }
        {
            List<CTCataloguePropertyType> theSubCatalogue;
            theSubCatalogue = (this.isSetSubCatalogue()?this.getSubCatalogue():null);
            strategy.appendField(locator, this, "subCatalogue", buffer, theSubCatalogue, this.isSetSubCatalogue());
        }
        return buffer;
    }

    public void setScope(List<CharacterStringPropertyType> value) {
        this.scope = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getScope();
            draftl.addAll(value);
        }
    }

    public void setFieldOfApplication(List<CharacterStringPropertyType> value) {
        this.fieldOfApplication = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getFieldOfApplication();
            draftl.addAll(value);
        }
    }

    public void setLocale(List<PTLocalePropertyType> value) {
        this.locale = null;
        if (value!= null) {
            List<PTLocalePropertyType> draftl = this.getLocale();
            draftl.addAll(value);
        }
    }

    public void setSubCatalogue(List<CTCataloguePropertyType> value) {
        this.subCatalogue = null;
        if (value!= null) {
            List<CTCataloguePropertyType> draftl = this.getSubCatalogue();
            draftl.addAll(value);
        }
    }

}
