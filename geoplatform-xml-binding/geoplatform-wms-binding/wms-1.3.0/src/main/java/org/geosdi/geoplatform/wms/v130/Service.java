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
// Generato il: 2026.11.02 alle 02:51:45 PM CET
//

package org.geosdi.geoplatform.wms.v130;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Name">
 *           <simpleType>
 *             <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               <enumeration value="WMS"/>
 *             </restriction>
 *           </simpleType>
 *         </element>
 *         <element ref="{http://www.opengis.net/wms}Title"/>
 *         <element ref="{http://www.opengis.net/wms}Abstract" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}KeywordList" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}OnlineResource"/>
 *         <element ref="{http://www.opengis.net/wms}ContactInformation" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}Fees" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}AccessConstraints" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}LayerLimit" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}MaxWidth" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}MaxHeight" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "title",
    "_abstract",
    "keywordList",
    "onlineResource",
    "contactInformation",
    "fees",
    "accessConstraints",
    "layerLimit",
    "maxWidth",
    "maxHeight"
})
@XmlRootElement(name = "Service")
public class Service implements ToString
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    /**
     * The Title is for informative display to a human.
     * 
     */
    @XmlElement(name = "Title", required = true)
    protected String title;
    /**
     * The abstract is a longer narrative description of an object.
     * 
     */
    @XmlElement(name = "Abstract")
    protected String _abstract;
    /**
     * List of keywords or keyword phrases to help catalog searching.
     * 
     */
    @XmlElement(name = "KeywordList")
    protected KeywordList keywordList;
    /**
     * An OnlineResource is typically an HTTP URL.  The URL is placed in
     *         the xlink:href attribute, and the value "simple" is placed in the
     *         xlink:type attribute.
     * 
     */
    @XmlElement(name = "OnlineResource", required = true)
    protected OnlineResource onlineResource;
    /**
     * Information about a contact person for the service.
     * 
     */
    @XmlElement(name = "ContactInformation")
    protected ContactInformation contactInformation;
    @XmlElement(name = "Fees")
    protected String fees;
    @XmlElement(name = "AccessConstraints")
    protected String accessConstraints;
    @XmlElement(name = "LayerLimit")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger layerLimit;
    @XmlElement(name = "MaxWidth")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxWidth;
    @XmlElement(name = "MaxHeight")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxHeight;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * The Title is for informative display to a human.
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
     * @see #getTitle()
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * The abstract is a longer narrative description of an object.
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
     * @see #getAbstract()
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * List of keywords or keyword phrases to help catalog searching.
     * 
     * @return
     *     possible object is
     *     {@link KeywordList }
     *     
     */
    public KeywordList getKeywordList() {
        return keywordList;
    }

    /**
     * Imposta il valore della proprietà keywordList.
     * 
     * @param value
     *     allowed object is
     *     {@link KeywordList }
     *     
     * @see #getKeywordList()
     */
    public void setKeywordList(KeywordList value) {
        this.keywordList = value;
    }

    /**
     * An OnlineResource is typically an HTTP URL.  The URL is placed in
     *         the xlink:href attribute, and the value "simple" is placed in the
     *         xlink:type attribute.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResource }
     *     
     */
    public OnlineResource getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResource }
     *     
     * @see #getOnlineResource()
     */
    public void setOnlineResource(OnlineResource value) {
        this.onlineResource = value;
    }

    /**
     * Information about a contact person for the service.
     * 
     * @return
     *     possible object is
     *     {@link ContactInformation }
     *     
     */
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    /**
     * Imposta il valore della proprietà contactInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInformation }
     *     
     * @see #getContactInformation()
     */
    public void setContactInformation(ContactInformation value) {
        this.contactInformation = value;
    }

    /**
     * Recupera il valore della proprietà fees.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFees() {
        return fees;
    }

    /**
     * Imposta il valore della proprietà fees.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFees(String value) {
        this.fees = value;
    }

    /**
     * Recupera il valore della proprietà accessConstraints.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessConstraints() {
        return accessConstraints;
    }

    /**
     * Imposta il valore della proprietà accessConstraints.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessConstraints(String value) {
        this.accessConstraints = value;
    }

    /**
     * Recupera il valore della proprietà layerLimit.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLayerLimit() {
        return layerLimit;
    }

    /**
     * Imposta il valore della proprietà layerLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLayerLimit(BigInteger value) {
        this.layerLimit = value;
    }

    /**
     * Recupera il valore della proprietà maxWidth.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxWidth() {
        return maxWidth;
    }

    /**
     * Imposta il valore della proprietà maxWidth.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxWidth(BigInteger value) {
        this.maxWidth = value;
    }

    /**
     * Recupera il valore della proprietà maxHeight.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxHeight() {
        return maxHeight;
    }

    /**
     * Imposta il valore della proprietà maxHeight.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxHeight(BigInteger value) {
        this.maxHeight = value;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, (this.title!= null));
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, (this._abstract!= null));
        }
        {
            KeywordList theKeywordList;
            theKeywordList = this.getKeywordList();
            strategy.appendField(locator, this, "keywordList", buffer, theKeywordList, (this.keywordList!= null));
        }
        {
            OnlineResource theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource, (this.onlineResource!= null));
        }
        {
            ContactInformation theContactInformation;
            theContactInformation = this.getContactInformation();
            strategy.appendField(locator, this, "contactInformation", buffer, theContactInformation, (this.contactInformation!= null));
        }
        {
            String theFees;
            theFees = this.getFees();
            strategy.appendField(locator, this, "fees", buffer, theFees, (this.fees!= null));
        }
        {
            String theAccessConstraints;
            theAccessConstraints = this.getAccessConstraints();
            strategy.appendField(locator, this, "accessConstraints", buffer, theAccessConstraints, (this.accessConstraints!= null));
        }
        {
            BigInteger theLayerLimit;
            theLayerLimit = this.getLayerLimit();
            strategy.appendField(locator, this, "layerLimit", buffer, theLayerLimit, (this.layerLimit!= null));
        }
        {
            BigInteger theMaxWidth;
            theMaxWidth = this.getMaxWidth();
            strategy.appendField(locator, this, "maxWidth", buffer, theMaxWidth, (this.maxWidth!= null));
        }
        {
            BigInteger theMaxHeight;
            theMaxHeight = this.getMaxHeight();
            strategy.appendField(locator, this, "maxHeight", buffer, theMaxHeight, (this.maxHeight!= null));
        }
        return buffer;
    }

}
