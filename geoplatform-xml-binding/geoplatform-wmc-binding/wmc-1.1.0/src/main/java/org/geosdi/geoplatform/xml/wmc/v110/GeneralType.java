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
// Generato il: 2015.08.25 alle 11:22:41 PM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per GeneralType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GeneralType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Window" type="{http://www.opengis.net/context}WindowType" minOccurs="0"/>
 *         &lt;element name="BoundingBox" type="{http://www.opengis.net/context}BoundingBoxType"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="KeywordList" type="{http://www.opengis.net/context}KeywordListType" minOccurs="0"/>
 *         &lt;element name="Abstract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogoURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/>
 *         &lt;element name="DescriptionURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/>
 *         &lt;element name="ContactInformation" type="{http://www.opengis.net/context}ContactInformationType" minOccurs="0"/>
 *         &lt;element name="Extension" type="{http://www.opengis.net/context}ExtensionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "General")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneralType", propOrder = {
    "window",
    "boundingBox",
    "title",
    "keywordList",
    "_abstract",
    "logoURL",
    "descriptionURL",
    "contactInformation",
    "extension"
})
public class GeneralType
    implements ToString
{

    @XmlElement(name = "Window")
    protected WindowType window;
    @XmlElement(name = "BoundingBox", required = true)
    protected BoundingBoxType boundingBox;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "KeywordList")
    protected KeywordListType keywordList;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "LogoURL")
    protected URLType logoURL;
    @XmlElement(name = "DescriptionURL")
    protected URLType descriptionURL;
    @XmlElement(name = "ContactInformation")
    protected ContactInformationType contactInformation;
    @XmlElement(name = "Extension")
    protected ExtensionType extension;

    /**
     * Recupera il valore della proprietà window.
     * 
     * @return
     *     possible object is
     *     {@link WindowType }
     *     
     */
    public WindowType getWindow() {
        return window;
    }

    /**
     * Imposta il valore della proprietà window.
     * 
     * @param value
     *     allowed object is
     *     {@link WindowType }
     *     
     */
    public void setWindow(WindowType value) {
        this.window = value;
    }

    public boolean isSetWindow() {
        return (this.window!= null);
    }

    /**
     * Recupera il valore della proprietà boundingBox.
     * 
     * @return
     *     possible object is
     *     {@link BoundingBoxType }
     *     
     */
    public BoundingBoxType getBoundingBox() {
        return boundingBox;
    }

    /**
     * Imposta il valore della proprietà boundingBox.
     * 
     * @param value
     *     allowed object is
     *     {@link BoundingBoxType }
     *     
     */
    public void setBoundingBox(BoundingBoxType value) {
        this.boundingBox = value;
    }

    public boolean isSetBoundingBox() {
        return (this.boundingBox!= null);
    }

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Recupera il valore della proprietà keywordList.
     * 
     * @return
     *     possible object is
     *     {@link KeywordListType }
     *     
     */
    public KeywordListType getKeywordList() {
        return keywordList;
    }

    /**
     * Imposta il valore della proprietà keywordList.
     * 
     * @param value
     *     allowed object is
     *     {@link KeywordListType }
     *     
     */
    public void setKeywordList(KeywordListType value) {
        this.keywordList = value;
    }

    public boolean isSetKeywordList() {
        return (this.keywordList!= null);
    }

    /**
     * Recupera il valore della proprietà abstract.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract!= null);
    }

    /**
     * Recupera il valore della proprietà logoURL.
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getLogoURL() {
        return logoURL;
    }

    /**
     * Imposta il valore della proprietà logoURL.
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setLogoURL(URLType value) {
        this.logoURL = value;
    }

    public boolean isSetLogoURL() {
        return (this.logoURL!= null);
    }

    /**
     * Recupera il valore della proprietà descriptionURL.
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getDescriptionURL() {
        return descriptionURL;
    }

    /**
     * Imposta il valore della proprietà descriptionURL.
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setDescriptionURL(URLType value) {
        this.descriptionURL = value;
    }

    public boolean isSetDescriptionURL() {
        return (this.descriptionURL!= null);
    }

    /**
     * Recupera il valore della proprietà contactInformation.
     * 
     * @return
     *     possible object is
     *     {@link ContactInformationType }
     *     
     */
    public ContactInformationType getContactInformation() {
        return contactInformation;
    }

    /**
     * Imposta il valore della proprietà contactInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInformationType }
     *     
     */
    public void setContactInformation(ContactInformationType value) {
        this.contactInformation = value;
    }

    public boolean isSetContactInformation() {
        return (this.contactInformation!= null);
    }

    /**
     * Recupera il valore della proprietà extension.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Imposta il valore della proprietà extension.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

    public boolean isSetExtension() {
        return (this.extension!= null);
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
            WindowType theWindow;
            theWindow = this.getWindow();
            strategy.appendField(locator, this, "window", buffer, theWindow);
        }
        {
            BoundingBoxType theBoundingBox;
            theBoundingBox = this.getBoundingBox();
            strategy.appendField(locator, this, "boundingBox", buffer, theBoundingBox);
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle);
        }
        {
            KeywordListType theKeywordList;
            theKeywordList = this.getKeywordList();
            strategy.appendField(locator, this, "keywordList", buffer, theKeywordList);
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract);
        }
        {
            URLType theLogoURL;
            theLogoURL = this.getLogoURL();
            strategy.appendField(locator, this, "logoURL", buffer, theLogoURL);
        }
        {
            URLType theDescriptionURL;
            theDescriptionURL = this.getDescriptionURL();
            strategy.appendField(locator, this, "descriptionURL", buffer, theDescriptionURL);
        }
        {
            ContactInformationType theContactInformation;
            theContactInformation = this.getContactInformation();
            strategy.appendField(locator, this, "contactInformation", buffer, theContactInformation);
        }
        {
            ExtensionType theExtension;
            theExtension = this.getExtension();
            strategy.appendField(locator, this, "extension", buffer, theExtension);
        }
        return buffer;
    }

}
