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
    "http"
})
@XmlRootElement(name = "DCPType")
public class DCPType implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "HTTP", required = true)
    protected HTTP http;

    /**
     * Recupera il valore della proprietà http.
     * 
     * @return
     *     possible object is
     *     {@link HTTP }
     *     
     */
    public HTTP getHTTP() {
        return http;
    }

    /**
     * Imposta il valore della proprietà http.
     * 
     * @param value
     *     allowed object is
     *     {@link HTTP }
     *     
     */
    public void setHTTP(HTTP value) {
        this.http = value;
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
            HTTP theHTTP;
            theHTTP = this.getHTTP();
            strategy.appendField(locator, this, "http", buffer, theHTTP, (this.http!= null));
        }
        return buffer;
    }

}
