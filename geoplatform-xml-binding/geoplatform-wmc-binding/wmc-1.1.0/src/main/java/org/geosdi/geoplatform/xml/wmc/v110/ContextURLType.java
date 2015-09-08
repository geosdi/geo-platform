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
 * <p>Classe Java per ContextURLType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ContextURLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OnlineResource" type="{http://www.opengis.net/context}OnlineResourceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContextURLType", propOrder = {
    "onlineResource"
})
public class ContextURLType
    implements ToString
{

    @XmlElement(name = "OnlineResource", required = true)
    protected OnlineResourceType onlineResource;

    /**
     * Recupera il valore della proprietà onlineResource.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResourceType }
     *     
     */
    public OnlineResourceType getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResourceType }
     *     
     */
    public void setOnlineResource(OnlineResourceType value) {
        this.onlineResource = value;
    }

    public boolean isSetOnlineResource() {
        return (this.onlineResource!= null);
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
            OnlineResourceType theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource);
        }
        return buffer;
    }

}
