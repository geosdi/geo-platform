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
    "contactPerson",
    "contactOrganization"
})
@XmlRootElement(name = "ContactPersonPrimary")
public class ContactPersonPrimary implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ContactPerson", required = true)
    protected String contactPerson;
    @XmlElement(name = "ContactOrganization", required = true)
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
            String theContactPerson;
            theContactPerson = this.getContactPerson();
            strategy.appendField(locator, this, "contactPerson", buffer, theContactPerson, (this.contactPerson!= null));
        }
        {
            String theContactOrganization;
            theContactOrganization = this.getContactOrganization();
            strategy.appendField(locator, this, "contactOrganization", buffer, theContactOrganization, (this.contactOrganization!= null));
        }
        return buffer;
    }

}
