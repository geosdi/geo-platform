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
    "title",
    "onlineResource",
    "logoURL"
})
@XmlRootElement(name = "Attribution")
public class Attribution implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "OnlineResource")
    protected OnlineResource onlineResource;
    @XmlElement(name = "LogoURL")
    protected LogoURL logoURL;

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

    /**
     * Recupera il valore della proprietà onlineResource.
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
     */
    public void setOnlineResource(OnlineResource value) {
        this.onlineResource = value;
    }

    /**
     * Recupera il valore della proprietà logoURL.
     * 
     * @return
     *     possible object is
     *     {@link LogoURL }
     *     
     */
    public LogoURL getLogoURL() {
        return logoURL;
    }

    /**
     * Imposta il valore della proprietà logoURL.
     * 
     * @param value
     *     allowed object is
     *     {@link LogoURL }
     *     
     */
    public void setLogoURL(LogoURL value) {
        this.logoURL = value;
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
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, (this.title!= null));
        }
        {
            OnlineResource theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource, (this.onlineResource!= null));
        }
        {
            LogoURL theLogoURL;
            theLogoURL = this.getLogoURL();
            strategy.appendField(locator, this, "logoURL", buffer, theLogoURL, (this.logoURL!= null));
        }
        return buffer;
    }

}
