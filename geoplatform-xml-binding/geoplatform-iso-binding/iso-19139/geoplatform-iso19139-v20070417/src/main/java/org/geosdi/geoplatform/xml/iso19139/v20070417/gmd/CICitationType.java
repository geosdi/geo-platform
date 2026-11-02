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

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.DatePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Standardized resource reference
 * 
 * <p>Classe Java per CI_Citation_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CI_Citation_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="title" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="alternateTitle" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="date" type="{http://www.isotc211.org/2005/gmd}CI_Date_PropertyType" maxOccurs="unbounded"/>
 *         <element name="edition" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="editionDate" type="{http://www.isotc211.org/2005/gco}Date_PropertyType" minOccurs="0"/>
 *         <element name="identifier" type="{http://www.isotc211.org/2005/gmd}MD_Identifier_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="citedResponsibleParty" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="presentationForm" type="{http://www.isotc211.org/2005/gmd}CI_PresentationFormCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="series" type="{http://www.isotc211.org/2005/gmd}CI_Series_PropertyType" minOccurs="0"/>
 *         <element name="otherCitationDetails" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="collectiveTitle" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="ISBN" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="ISSN" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CI_Citation_Type", propOrder = {
    "title",
    "alternateTitle",
    "date",
    "edition",
    "editionDate",
    "identifier",
    "citedResponsibleParty",
    "presentationForm",
    "series",
    "otherCitationDetails",
    "collectiveTitle",
    "isbn",
    "issn"
})
public class CICitationType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CharacterStringPropertyType title;
    protected List<CharacterStringPropertyType> alternateTitle;
    @XmlElement(required = true)
    protected List<CIDatePropertyType> date;
    protected CharacterStringPropertyType edition;
    protected DatePropertyType editionDate;
    protected List<MDIdentifierPropertyType> identifier;
    protected List<CIResponsiblePartyPropertyType> citedResponsibleParty;
    protected List<CIPresentationFormCodePropertyType> presentationForm;
    protected CISeriesPropertyType series;
    protected CharacterStringPropertyType otherCitationDetails;
    protected CharacterStringPropertyType collectiveTitle;
    @XmlElement(name = "ISBN")
    protected CharacterStringPropertyType isbn;
    @XmlElement(name = "ISSN")
    protected CharacterStringPropertyType issn;

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setTitle(CharacterStringPropertyType value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Gets the value of the alternateTitle property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the alternateTitle property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAlternateTitle().add(newItem);
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
     *     The value of the alternateTitle property.
     */
    public List<CharacterStringPropertyType> getAlternateTitle() {
        if (alternateTitle == null) {
            alternateTitle = new ArrayList<>();
        }
        return this.alternateTitle;
    }

    public boolean isSetAlternateTitle() {
        return ((this.alternateTitle!= null)&&(!this.alternateTitle.isEmpty()));
    }

    public void unsetAlternateTitle() {
        this.alternateTitle = null;
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
     * Recupera il valore della proprietà edition.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getEdition() {
        return edition;
    }

    /**
     * Imposta il valore della proprietà edition.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setEdition(CharacterStringPropertyType value) {
        this.edition = value;
    }

    public boolean isSetEdition() {
        return (this.edition!= null);
    }

    /**
     * Recupera il valore della proprietà editionDate.
     * 
     * @return
     *     possible object is
     *     {@link DatePropertyType }
     *     
     */
    public DatePropertyType getEditionDate() {
        return editionDate;
    }

    /**
     * Imposta il valore della proprietà editionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DatePropertyType }
     *     
     */
    public void setEditionDate(DatePropertyType value) {
        this.editionDate = value;
    }

    public boolean isSetEditionDate() {
        return (this.editionDate!= null);
    }

    /**
     * Gets the value of the identifier property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the identifier property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDIdentifierPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the identifier property.
     */
    public List<MDIdentifierPropertyType> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<>();
        }
        return this.identifier;
    }

    public boolean isSetIdentifier() {
        return ((this.identifier!= null)&&(!this.identifier.isEmpty()));
    }

    public void unsetIdentifier() {
        this.identifier = null;
    }

    /**
     * Gets the value of the citedResponsibleParty property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the citedResponsibleParty property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCitedResponsibleParty().add(newItem);
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
     *     The value of the citedResponsibleParty property.
     */
    public List<CIResponsiblePartyPropertyType> getCitedResponsibleParty() {
        if (citedResponsibleParty == null) {
            citedResponsibleParty = new ArrayList<>();
        }
        return this.citedResponsibleParty;
    }

    public boolean isSetCitedResponsibleParty() {
        return ((this.citedResponsibleParty!= null)&&(!this.citedResponsibleParty.isEmpty()));
    }

    public void unsetCitedResponsibleParty() {
        this.citedResponsibleParty = null;
    }

    /**
     * Gets the value of the presentationForm property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the presentationForm property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPresentationForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIPresentationFormCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the presentationForm property.
     */
    public List<CIPresentationFormCodePropertyType> getPresentationForm() {
        if (presentationForm == null) {
            presentationForm = new ArrayList<>();
        }
        return this.presentationForm;
    }

    public boolean isSetPresentationForm() {
        return ((this.presentationForm!= null)&&(!this.presentationForm.isEmpty()));
    }

    public void unsetPresentationForm() {
        this.presentationForm = null;
    }

    /**
     * Recupera il valore della proprietà series.
     * 
     * @return
     *     possible object is
     *     {@link CISeriesPropertyType }
     *     
     */
    public CISeriesPropertyType getSeries() {
        return series;
    }

    /**
     * Imposta il valore della proprietà series.
     * 
     * @param value
     *     allowed object is
     *     {@link CISeriesPropertyType }
     *     
     */
    public void setSeries(CISeriesPropertyType value) {
        this.series = value;
    }

    public boolean isSetSeries() {
        return (this.series!= null);
    }

    /**
     * Recupera il valore della proprietà otherCitationDetails.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getOtherCitationDetails() {
        return otherCitationDetails;
    }

    /**
     * Imposta il valore della proprietà otherCitationDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setOtherCitationDetails(CharacterStringPropertyType value) {
        this.otherCitationDetails = value;
    }

    public boolean isSetOtherCitationDetails() {
        return (this.otherCitationDetails!= null);
    }

    /**
     * Recupera il valore della proprietà collectiveTitle.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCollectiveTitle() {
        return collectiveTitle;
    }

    /**
     * Imposta il valore della proprietà collectiveTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCollectiveTitle(CharacterStringPropertyType value) {
        this.collectiveTitle = value;
    }

    public boolean isSetCollectiveTitle() {
        return (this.collectiveTitle!= null);
    }

    /**
     * Recupera il valore della proprietà isbn.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getISBN() {
        return isbn;
    }

    /**
     * Imposta il valore della proprietà isbn.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setISBN(CharacterStringPropertyType value) {
        this.isbn = value;
    }

    public boolean isSetISBN() {
        return (this.isbn!= null);
    }

    /**
     * Recupera il valore della proprietà issn.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getISSN() {
        return issn;
    }

    /**
     * Imposta il valore della proprietà issn.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setISSN(CharacterStringPropertyType value) {
        this.issn = value;
    }

    public boolean isSetISSN() {
        return (this.issn!= null);
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
            CharacterStringPropertyType theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            List<CharacterStringPropertyType> theAlternateTitle;
            theAlternateTitle = (this.isSetAlternateTitle()?this.getAlternateTitle():null);
            strategy.appendField(locator, this, "alternateTitle", buffer, theAlternateTitle, this.isSetAlternateTitle());
        }
        {
            List<CIDatePropertyType> theDate;
            theDate = (this.isSetDate()?this.getDate():null);
            strategy.appendField(locator, this, "date", buffer, theDate, this.isSetDate());
        }
        {
            CharacterStringPropertyType theEdition;
            theEdition = this.getEdition();
            strategy.appendField(locator, this, "edition", buffer, theEdition, this.isSetEdition());
        }
        {
            DatePropertyType theEditionDate;
            theEditionDate = this.getEditionDate();
            strategy.appendField(locator, this, "editionDate", buffer, theEditionDate, this.isSetEditionDate());
        }
        {
            List<MDIdentifierPropertyType> theIdentifier;
            theIdentifier = (this.isSetIdentifier()?this.getIdentifier():null);
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, this.isSetIdentifier());
        }
        {
            List<CIResponsiblePartyPropertyType> theCitedResponsibleParty;
            theCitedResponsibleParty = (this.isSetCitedResponsibleParty()?this.getCitedResponsibleParty():null);
            strategy.appendField(locator, this, "citedResponsibleParty", buffer, theCitedResponsibleParty, this.isSetCitedResponsibleParty());
        }
        {
            List<CIPresentationFormCodePropertyType> thePresentationForm;
            thePresentationForm = (this.isSetPresentationForm()?this.getPresentationForm():null);
            strategy.appendField(locator, this, "presentationForm", buffer, thePresentationForm, this.isSetPresentationForm());
        }
        {
            CISeriesPropertyType theSeries;
            theSeries = this.getSeries();
            strategy.appendField(locator, this, "series", buffer, theSeries, this.isSetSeries());
        }
        {
            CharacterStringPropertyType theOtherCitationDetails;
            theOtherCitationDetails = this.getOtherCitationDetails();
            strategy.appendField(locator, this, "otherCitationDetails", buffer, theOtherCitationDetails, this.isSetOtherCitationDetails());
        }
        {
            CharacterStringPropertyType theCollectiveTitle;
            theCollectiveTitle = this.getCollectiveTitle();
            strategy.appendField(locator, this, "collectiveTitle", buffer, theCollectiveTitle, this.isSetCollectiveTitle());
        }
        {
            CharacterStringPropertyType theISBN;
            theISBN = this.getISBN();
            strategy.appendField(locator, this, "isbn", buffer, theISBN, this.isSetISBN());
        }
        {
            CharacterStringPropertyType theISSN;
            theISSN = this.getISSN();
            strategy.appendField(locator, this, "issn", buffer, theISSN, this.isSetISSN());
        }
        return buffer;
    }

    public void setAlternateTitle(List<CharacterStringPropertyType> value) {
        this.alternateTitle = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getAlternateTitle();
            draftl.addAll(value);
        }
    }

    public void setDate(List<CIDatePropertyType> value) {
        this.date = null;
        if (value!= null) {
            List<CIDatePropertyType> draftl = this.getDate();
            draftl.addAll(value);
        }
    }

    public void setIdentifier(List<MDIdentifierPropertyType> value) {
        this.identifier = null;
        if (value!= null) {
            List<MDIdentifierPropertyType> draftl = this.getIdentifier();
            draftl.addAll(value);
        }
    }

    public void setCitedResponsibleParty(List<CIResponsiblePartyPropertyType> value) {
        this.citedResponsibleParty = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getCitedResponsibleParty();
            draftl.addAll(value);
        }
    }

    public void setPresentationForm(List<CIPresentationFormCodePropertyType> value) {
        this.presentationForm = null;
        if (value!= null) {
            List<CIPresentationFormCodePropertyType> draftl = this.getPresentationForm();
            draftl.addAll(value);
        }
    }

}
