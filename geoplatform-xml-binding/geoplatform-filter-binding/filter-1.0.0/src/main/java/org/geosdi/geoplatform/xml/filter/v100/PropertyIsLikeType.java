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
// Generato il: 2015.08.25 alle 10:56:59 PM CEST 
//


package org.geosdi.geoplatform.xml.filter.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per PropertyIsLikeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PropertyIsLikeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ogc}ComparisonOpsType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/ogc}PropertyName"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}Literal"/>
 *       &lt;/sequence>
 *       &lt;attribute name="wildCard" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="singleChar" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="escape" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyIsLikeType", propOrder = {
    "propertyName",
    "literal"
})
public class PropertyIsLikeType
    extends ComparisonOpsType
    implements ToString
{

    @XmlElement(name = "PropertyName", required = true)
    protected PropertyNameType propertyName;
    @XmlElement(name = "Literal", required = true)
    protected LiteralType literal;
    @XmlAttribute(name = "wildCard", required = true)
    protected String wildCard;
    @XmlAttribute(name = "singleChar", required = true)
    protected String singleChar;
    @XmlAttribute(name = "escape", required = true)
    protected String escape;

    /**
     * Recupera il valore della proprietà propertyName.
     * 
     * @return
     *     possible object is
     *     {@link PropertyNameType }
     *     
     */
    public PropertyNameType getPropertyName() {
        return propertyName;
    }

    /**
     * Imposta il valore della proprietà propertyName.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyNameType }
     *     
     */
    public void setPropertyName(PropertyNameType value) {
        this.propertyName = value;
    }

    public boolean isSetPropertyName() {
        return (this.propertyName!= null);
    }

    /**
     * Recupera il valore della proprietà literal.
     * 
     * @return
     *     possible object is
     *     {@link LiteralType }
     *     
     */
    public LiteralType getLiteral() {
        return literal;
    }

    /**
     * Imposta il valore della proprietà literal.
     * 
     * @param value
     *     allowed object is
     *     {@link LiteralType }
     *     
     */
    public void setLiteral(LiteralType value) {
        this.literal = value;
    }

    public boolean isSetLiteral() {
        return (this.literal!= null);
    }

    /**
     * Recupera il valore della proprietà wildCard.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWildCard() {
        return wildCard;
    }

    /**
     * Imposta il valore della proprietà wildCard.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWildCard(String value) {
        this.wildCard = value;
    }

    public boolean isSetWildCard() {
        return (this.wildCard!= null);
    }

    /**
     * Recupera il valore della proprietà singleChar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleChar() {
        return singleChar;
    }

    /**
     * Imposta il valore della proprietà singleChar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleChar(String value) {
        this.singleChar = value;
    }

    public boolean isSetSingleChar() {
        return (this.singleChar!= null);
    }

    /**
     * Recupera il valore della proprietà escape.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscape() {
        return escape;
    }

    /**
     * Imposta il valore della proprietà escape.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscape(String value) {
        this.escape = value;
    }

    public boolean isSetEscape() {
        return (this.escape!= null);
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
            PropertyNameType thePropertyName;
            thePropertyName = this.getPropertyName();
            strategy.appendField(locator, this, "propertyName", buffer, thePropertyName);
        }
        {
            LiteralType theLiteral;
            theLiteral = this.getLiteral();
            strategy.appendField(locator, this, "literal", buffer, theLiteral);
        }
        {
            String theWildCard;
            theWildCard = this.getWildCard();
            strategy.appendField(locator, this, "wildCard", buffer, theWildCard);
        }
        {
            String theSingleChar;
            theSingleChar = this.getSingleChar();
            strategy.appendField(locator, this, "singleChar", buffer, theSingleChar);
        }
        {
            String theEscape;
            theEscape = this.getEscape();
            strategy.appendField(locator, this, "escape", buffer, theEscape);
        }
        return buffer;
    }

}
