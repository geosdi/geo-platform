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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per ContactInformationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ContactInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContactPersonPrimary" type="{http://www.opengis.net/context}ContactPersonPrimaryType" minOccurs="0"/>
 *         &lt;element name="ContactPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactAddress" type="{http://www.opengis.net/context}AddressType" minOccurs="0"/>
 *         &lt;element name="ContactVoiceTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactFacsimileTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactElectronicMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactInformationType", propOrder = {
    "contactPersonPrimary",
    "contactPosition",
    "contactAddress",
    "contactVoiceTelephone",
    "contactFacsimileTelephone",
    "contactElectronicMailAddress"
})
public class ContactInformationType
    implements ToString
{

    @XmlElement(name = "ContactPersonPrimary")
    protected ContactPersonPrimaryType contactPersonPrimary;
    @XmlElement(name = "ContactPosition")
    protected String contactPosition;
    @XmlElement(name = "ContactAddress")
    protected AddressType contactAddress;
    @XmlElement(name = "ContactVoiceTelephone")
    protected String contactVoiceTelephone;
    @XmlElement(name = "ContactFacsimileTelephone")
    protected String contactFacsimileTelephone;
    @XmlElement(name = "ContactElectronicMailAddress")
    protected String contactElectronicMailAddress;

    /**
     * Recupera il valore della proprietà contactPersonPrimary.
     * 
     * @return
     *     possible object is
     *     {@link ContactPersonPrimaryType }
     *     
     */
    public ContactPersonPrimaryType getContactPersonPrimary() {
        return contactPersonPrimary;
    }

    /**
     * Imposta il valore della proprietà contactPersonPrimary.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactPersonPrimaryType }
     *     
     */
    public void setContactPersonPrimary(ContactPersonPrimaryType value) {
        this.contactPersonPrimary = value;
    }

    public boolean isSetContactPersonPrimary() {
        return (this.contactPersonPrimary!= null);
    }

    /**
     * Recupera il valore della proprietà contactPosition.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPosition() {
        return contactPosition;
    }

    /**
     * Imposta il valore della proprietà contactPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPosition(String value) {
        this.contactPosition = value;
    }

    public boolean isSetContactPosition() {
        return (this.contactPosition!= null);
    }

    /**
     * Recupera il valore della proprietà contactAddress.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getContactAddress() {
        return contactAddress;
    }

    /**
     * Imposta il valore della proprietà contactAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setContactAddress(AddressType value) {
        this.contactAddress = value;
    }

    public boolean isSetContactAddress() {
        return (this.contactAddress!= null);
    }

    /**
     * Recupera il valore della proprietà contactVoiceTelephone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactVoiceTelephone() {
        return contactVoiceTelephone;
    }

    /**
     * Imposta il valore della proprietà contactVoiceTelephone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactVoiceTelephone(String value) {
        this.contactVoiceTelephone = value;
    }

    public boolean isSetContactVoiceTelephone() {
        return (this.contactVoiceTelephone!= null);
    }

    /**
     * Recupera il valore della proprietà contactFacsimileTelephone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactFacsimileTelephone() {
        return contactFacsimileTelephone;
    }

    /**
     * Imposta il valore della proprietà contactFacsimileTelephone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactFacsimileTelephone(String value) {
        this.contactFacsimileTelephone = value;
    }

    public boolean isSetContactFacsimileTelephone() {
        return (this.contactFacsimileTelephone!= null);
    }

    /**
     * Recupera il valore della proprietà contactElectronicMailAddress.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactElectronicMailAddress() {
        return contactElectronicMailAddress;
    }

    /**
     * Imposta il valore della proprietà contactElectronicMailAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactElectronicMailAddress(String value) {
        this.contactElectronicMailAddress = value;
    }

    public boolean isSetContactElectronicMailAddress() {
        return (this.contactElectronicMailAddress!= null);
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
            ContactPersonPrimaryType theContactPersonPrimary;
            theContactPersonPrimary = this.getContactPersonPrimary();
            strategy.appendField(locator, this, "contactPersonPrimary", buffer, theContactPersonPrimary);
        }
        {
            String theContactPosition;
            theContactPosition = this.getContactPosition();
            strategy.appendField(locator, this, "contactPosition", buffer, theContactPosition);
        }
        {
            AddressType theContactAddress;
            theContactAddress = this.getContactAddress();
            strategy.appendField(locator, this, "contactAddress", buffer, theContactAddress);
        }
        {
            String theContactVoiceTelephone;
            theContactVoiceTelephone = this.getContactVoiceTelephone();
            strategy.appendField(locator, this, "contactVoiceTelephone", buffer, theContactVoiceTelephone);
        }
        {
            String theContactFacsimileTelephone;
            theContactFacsimileTelephone = this.getContactFacsimileTelephone();
            strategy.appendField(locator, this, "contactFacsimileTelephone", buffer, theContactFacsimileTelephone);
        }
        {
            String theContactElectronicMailAddress;
            theContactElectronicMailAddress = this.getContactElectronicMailAddress();
            strategy.appendField(locator, this, "contactElectronicMailAddress", buffer, theContactElectronicMailAddress);
        }
        return buffer;
    }

}
