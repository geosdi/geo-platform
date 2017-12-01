//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.geosdi.geoplatform.xml.ows.v110.AcceptVersionsType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per anonymous complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AcceptVersions" type="{http://www.opengis.net/ows/1.1}AcceptVersionsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="service" use="required" type="{http://www.opengis.net/ows/1.1}ServiceType" fixed="WPS" /&gt;
 *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "acceptVersions"
})
@XmlRootElement(name = "GetCapabilities")
public class GetCapabilities implements ToString2 {

    @XmlElement(name = "AcceptVersions")
    protected AcceptVersionsType acceptVersions;
    @XmlAttribute(name = "service", required = true)
    protected String service;
    @XmlAttribute(name = "language")
    protected String language;

    /**
     * Recupera il valore della proprietà acceptVersions.
     *
     * @return possible object is
     * {@link AcceptVersionsType }
     */
    public AcceptVersionsType getAcceptVersions() {
        return acceptVersions;
    }

    /**
     * Imposta il valore della proprietà acceptVersions.
     *
     * @param value allowed object is
     *              {@link AcceptVersionsType }
     */
    public void setAcceptVersions(AcceptVersionsType value) {
        this.acceptVersions = value;
    }

    public boolean isSetAcceptVersions() {
        return (this.acceptVersions != null);
    }

    /**
     * Recupera il valore della proprietà service.
     *
     * @return possible object is
     * {@link String }
     */
    public String getService() {
        if (service == null) {
            return "WPS";
        } else {
            return service;
        }
    }

    /**
     * Imposta il valore della proprietà service.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setService(String value) {
        this.service = value;
    }

    public boolean isSetService() {
        return (this.service != null);
    }

    /**
     * Recupera il valore della proprietà language.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Imposta il valore della proprietà language.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    public boolean isSetLanguage() {
        return (this.language != null);
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
            AcceptVersionsType theAcceptVersions;
            theAcceptVersions = this.getAcceptVersions();
            strategy.appendField(locator, this, "acceptVersions", buffer, theAcceptVersions, this.isSetAcceptVersions());
        }
        {
            String theService;
            theService = this.getService();
            strategy.appendField(locator, this, "service", buffer, theService, this.isSetService());
        }
        {
            String theLanguage;
            theLanguage = this.getLanguage();
            strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
        }
        return buffer;
    }

}
