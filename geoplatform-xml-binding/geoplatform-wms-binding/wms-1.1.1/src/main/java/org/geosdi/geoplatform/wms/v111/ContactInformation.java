//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "contactPersonPrimary",
    "contactPosition",
    "contactAddress",
    "contactVoiceTelephone",
    "contactFacsimileTelephone",
    "contactElectronicMailAddress"
})
@XmlRootElement(name = "ContactInformation")
public class ContactInformation implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ContactPersonPrimary")
    protected ContactPersonPrimary contactPersonPrimary;
    @XmlElement(name = "ContactPosition")
    protected String contactPosition;
    @XmlElement(name = "ContactAddress")
    protected ContactAddress contactAddress;
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
     *     {@link ContactPersonPrimary }
     *     
     */
    public ContactPersonPrimary getContactPersonPrimary() {
        return contactPersonPrimary;
    }

    /**
     * Imposta il valore della proprietà contactPersonPrimary.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactPersonPrimary }
     *     
     */
    public void setContactPersonPrimary(ContactPersonPrimary value) {
        this.contactPersonPrimary = value;
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

    /**
     * Recupera il valore della proprietà contactAddress.
     * 
     * @return
     *     possible object is
     *     {@link ContactAddress }
     *     
     */
    public ContactAddress getContactAddress() {
        return contactAddress;
    }

    /**
     * Imposta il valore della proprietà contactAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactAddress }
     *     
     */
    public void setContactAddress(ContactAddress value) {
        this.contactAddress = value;
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

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            ContactPersonPrimary theContactPersonPrimary;
            theContactPersonPrimary = this.getContactPersonPrimary();
            strategy.appendField(locator, this, "contactPersonPrimary", buffer, theContactPersonPrimary, (this.contactPersonPrimary!= null));
        }
        {
            String theContactPosition;
            theContactPosition = this.getContactPosition();
            strategy.appendField(locator, this, "contactPosition", buffer, theContactPosition, (this.contactPosition!= null));
        }
        {
            ContactAddress theContactAddress;
            theContactAddress = this.getContactAddress();
            strategy.appendField(locator, this, "contactAddress", buffer, theContactAddress, (this.contactAddress!= null));
        }
        {
            String theContactVoiceTelephone;
            theContactVoiceTelephone = this.getContactVoiceTelephone();
            strategy.appendField(locator, this, "contactVoiceTelephone", buffer, theContactVoiceTelephone, (this.contactVoiceTelephone!= null));
        }
        {
            String theContactFacsimileTelephone;
            theContactFacsimileTelephone = this.getContactFacsimileTelephone();
            strategy.appendField(locator, this, "contactFacsimileTelephone", buffer, theContactFacsimileTelephone, (this.contactFacsimileTelephone!= null));
        }
        {
            String theContactElectronicMailAddress;
            theContactElectronicMailAddress = this.getContactElectronicMailAddress();
            strategy.appendField(locator, this, "contactElectronicMailAddress", buffer, theContactElectronicMailAddress, (this.contactElectronicMailAddress!= null));
        }
        return buffer;
    }

}
