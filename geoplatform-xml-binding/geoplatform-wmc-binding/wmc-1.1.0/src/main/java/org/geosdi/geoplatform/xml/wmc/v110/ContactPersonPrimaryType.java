//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ContactPersonPrimaryType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ContactPersonPrimaryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContactPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ContactOrganization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactPersonPrimaryType", propOrder = {"contactPerson", "contactOrganization"})
public class ContactPersonPrimaryType implements ToString2 {

    @XmlElement(name = "ContactPerson")
    protected String contactPerson;
    @XmlElement(name = "ContactOrganization")
    protected String contactOrganization;

    /**
     * Recupera il valore della proprietà contactPerson.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * Imposta il valore della proprietà contactPerson.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

    public boolean isSetContactPerson() {
        return (this.contactPerson != null);
    }

    /**
     * Recupera il valore della proprietà contactOrganization.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContactOrganization() {
        return contactOrganization;
    }

    /**
     * Imposta il valore della proprietà contactOrganization.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setContactOrganization(String value) {
        this.contactOrganization = value;
    }

    public boolean isSetContactOrganization() {
        return (this.contactOrganization != null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
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
            String theContactPerson;
            theContactPerson = this.getContactPerson();
            strategy.appendField(locator, this, "contactPerson", buffer, theContactPerson, this.isSetContactPerson());
        }
        {
            String theContactOrganization;
            theContactOrganization = this.getContactOrganization();
            strategy.appendField(locator, this, "contactOrganization", buffer, theContactOrganization,
                    this.isSetContactOrganization());
        }
        return buffer;
    }

}
