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

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per ServerType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ServerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OnlineResource" type="{http://www.opengis.net/context}OnlineResourceType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="service" use="required" type="{http://www.opengis.net/context}serviceType" /&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerType", propOrder = {"onlineResource"})
@XmlRootElement(name = "Server")
public class ServerType implements ToString2 {

    @XmlElement(name = "OnlineResource", required = true)
    protected OnlineResourceType onlineResource;
    @XmlAttribute(name = "service", required = true)
    protected ServiceType service;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "title")
    protected String title;

    /**
     * Recupera il valore della proprietà onlineResource.
     *
     * @return possible object is
     * {@link OnlineResourceType }
     */
    public OnlineResourceType getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     *
     * @param value allowed object is
     * {@link OnlineResourceType }
     */
    public void setOnlineResource(OnlineResourceType value) {
        this.onlineResource = value;
    }

    public boolean isSetOnlineResource() {
        return (this.onlineResource != null);
    }

    /**
     * Recupera il valore della proprietà service.
     *
     * @return possible object is
     * {@link ServiceType }
     */
    public ServiceType getService() {
        return service;
    }

    /**
     * Imposta il valore della proprietà service.
     *
     * @param value allowed object is
     * {@link ServiceType }
     */
    public void setService(ServiceType value) {
        this.service = value;
    }

    public boolean isSetService() {
        return (this.service != null);
    }

    /**
     * Recupera il valore della proprietà version.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    public boolean isSetVersion() {
        return (this.version != null);
    }

    /**
     * Recupera il valore della proprietà title.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title != null);
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
            OnlineResourceType theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource,
                    this.isSetOnlineResource());
        }
        {
            ServiceType theService;
            theService = this.getService();
            strategy.appendField(locator, this, "service", buffer, theService, this.isSetService());
        }
        {
            String theVersion;
            theVersion = this.getVersion();
            strategy.appendField(locator, this, "version", buffer, theVersion, this.isSetVersion());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        return buffer;
    }

}
