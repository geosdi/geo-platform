//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "OnlineResource")
public class OnlineResource implements Serializable, ToString2 {

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "xmlns:xlink")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlnsXlink;
    @XmlAttribute(name = "xlink:type")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xlinkType;
    @XmlAttribute(name = "xlink:href", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xlinkHref;

    /**
     * Recupera il valore della proprietà xmlnsXlink.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXmlnsXlink() {
        if (xmlnsXlink == null) {
            return "http://www.w3.org/1999/xlink";
        } else {
            return xmlnsXlink;
        }
    }

    /**
     * Imposta il valore della proprietà xmlnsXlink.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXmlnsXlink(String value) {
        this.xmlnsXlink = value;
    }

    /**
     * Recupera il valore della proprietà xlinkType.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXlinkType() {
        if (xlinkType == null) {
            return "simple";
        } else {
            return xlinkType;
        }
    }

    /**
     * Imposta il valore della proprietà xlinkType.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXlinkType(String value) {
        this.xlinkType = value;
    }

    /**
     * Recupera il valore della proprietà xlinkHref.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXlinkHref() {
        return xlinkHref;
    }

    /**
     * Imposta il valore della proprietà xlinkHref.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXlinkHref(String value) {
        this.xlinkHref = value;
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
            String theXmlnsXlink;
            theXmlnsXlink = this.getXmlnsXlink();
            strategy.appendField(locator, this, "xmlnsXlink", buffer, theXmlnsXlink, (this.xmlnsXlink != null));
        }
        {
            String theXlinkType;
            theXlinkType = this.getXlinkType();
            strategy.appendField(locator, this, "xlinkType", buffer, theXlinkType, (this.xlinkType != null));
        }
        {
            String theXlinkHref;
            theXlinkHref = this.getXlinkHref();
            strategy.appendField(locator, this, "xlinkHref", buffer, theXlinkHref, (this.xlinkHref != null));
        }
        return buffer;
    }

}
