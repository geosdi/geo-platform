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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "UserDefinedSymbolization")
public class UserDefinedSymbolization implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "SupportSLD")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String supportSLD;
    @XmlAttribute(name = "UserLayer")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String userLayer;
    @XmlAttribute(name = "UserStyle")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String userStyle;
    @XmlAttribute(name = "RemoteWFS")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String remoteWFS;

    /**
     * Recupera il valore della proprietà supportSLD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportSLD() {
        if (supportSLD == null) {
            return "0";
        } else {
            return supportSLD;
        }
    }

    /**
     * Imposta il valore della proprietà supportSLD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportSLD(String value) {
        this.supportSLD = value;
    }

    /**
     * Recupera il valore della proprietà userLayer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLayer() {
        if (userLayer == null) {
            return "0";
        } else {
            return userLayer;
        }
    }

    /**
     * Imposta il valore della proprietà userLayer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLayer(String value) {
        this.userLayer = value;
    }

    /**
     * Recupera il valore della proprietà userStyle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserStyle() {
        if (userStyle == null) {
            return "0";
        } else {
            return userStyle;
        }
    }

    /**
     * Imposta il valore della proprietà userStyle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserStyle(String value) {
        this.userStyle = value;
    }

    /**
     * Recupera il valore della proprietà remoteWFS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteWFS() {
        if (remoteWFS == null) {
            return "0";
        } else {
            return remoteWFS;
        }
    }

    /**
     * Imposta il valore della proprietà remoteWFS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteWFS(String value) {
        this.remoteWFS = value;
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
            String theSupportSLD;
            theSupportSLD = this.getSupportSLD();
            strategy.appendField(locator, this, "supportSLD", buffer, theSupportSLD, (this.supportSLD!= null));
        }
        {
            String theUserLayer;
            theUserLayer = this.getUserLayer();
            strategy.appendField(locator, this, "userLayer", buffer, theUserLayer, (this.userLayer!= null));
        }
        {
            String theUserStyle;
            theUserStyle = this.getUserStyle();
            strategy.appendField(locator, this, "userStyle", buffer, theUserStyle, (this.userStyle!= null));
        }
        {
            String theRemoteWFS;
            theRemoteWFS = this.getRemoteWFS();
            strategy.appendField(locator, this, "remoteWFS", buffer, theRemoteWFS, (this.remoteWFS!= null));
        }
        return buffer;
    }

}
