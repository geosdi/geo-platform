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
 * <p>Classe Java per ContactPersonPrimaryType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ContactPersonPrimaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContactPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactOrganization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactPersonPrimaryType", propOrder = {
    "contactPerson",
    "contactOrganization"
})
public class ContactPersonPrimaryType
    implements ToString
{

    @XmlElement(name = "ContactPerson")
    protected String contactPerson;
    @XmlElement(name = "ContactOrganization")
    protected String contactOrganization;

    /**
     * Recupera il valore della proprietà contactPerson.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * Imposta il valore della proprietà contactPerson.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

    public boolean isSetContactPerson() {
        return (this.contactPerson!= null);
    }

    /**
     * Recupera il valore della proprietà contactOrganization.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactOrganization() {
        return contactOrganization;
    }

    /**
     * Imposta il valore della proprietà contactOrganization.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactOrganization(String value) {
        this.contactOrganization = value;
    }

    public boolean isSetContactOrganization() {
        return (this.contactOrganization!= null);
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
            String theContactPerson;
            theContactPerson = this.getContactPerson();
            strategy.appendField(locator, this, "contactPerson", buffer, theContactPerson);
        }
        {
            String theContactOrganization;
            theContactOrganization = this.getContactOrganization();
            strategy.appendField(locator, this, "contactOrganization", buffer, theContactOrganization);
        }
        return buffer;
    }

}
