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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
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
@XmlRootElement(name = "LatLonBoundingBox")
public class LatLonBoundingBox implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "minx", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minx;
    @XmlAttribute(name = "miny", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String miny;
    @XmlAttribute(name = "maxx", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxx;
    @XmlAttribute(name = "maxy", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxy;

    /**
     * Recupera il valore della proprietà minx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinx() {
        return minx;
    }

    /**
     * Imposta il valore della proprietà minx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinx(String value) {
        this.minx = value;
    }

    /**
     * Recupera il valore della proprietà miny.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiny() {
        return miny;
    }

    /**
     * Imposta il valore della proprietà miny.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiny(String value) {
        this.miny = value;
    }

    /**
     * Recupera il valore della proprietà maxx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxx() {
        return maxx;
    }

    /**
     * Imposta il valore della proprietà maxx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxx(String value) {
        this.maxx = value;
    }

    /**
     * Recupera il valore della proprietà maxy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxy() {
        return maxy;
    }

    /**
     * Imposta il valore della proprietà maxy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxy(String value) {
        this.maxy = value;
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
            String theMinx;
            theMinx = this.getMinx();
            strategy.appendField(locator, this, "minx", buffer, theMinx, (this.minx!= null));
        }
        {
            String theMiny;
            theMiny = this.getMiny();
            strategy.appendField(locator, this, "miny", buffer, theMiny, (this.miny!= null));
        }
        {
            String theMaxx;
            theMaxx = this.getMaxx();
            strategy.appendField(locator, this, "maxx", buffer, theMaxx, (this.maxx!= null));
        }
        {
            String theMaxy;
            theMaxy = this.getMaxy();
            strategy.appendField(locator, this, "maxy", buffer, theMaxy, (this.maxy!= null));
        }
        return buffer;
    }

}
