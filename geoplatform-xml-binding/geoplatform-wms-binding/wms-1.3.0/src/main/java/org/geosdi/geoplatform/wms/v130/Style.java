//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import java.util.ArrayList;
import java.util.List;
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
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Name"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Title"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Abstract" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}LegendURL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}StyleSheetURL" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}StyleURL" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "title",
    "_abstract",
    "legendURL",
    "styleSheetURL",
    "styleURL"
})
@XmlRootElement(name = "Style")
public class Style implements ToString2
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "LegendURL")
    protected List<LegendURL> legendURL;
    @XmlElement(name = "StyleSheetURL")
    protected StyleSheetURL styleSheetURL;
    @XmlElement(name = "StyleURL")
    protected StyleURL styleURL;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

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
     * Recupera il valore della proprietà abstract.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the legendURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the legendURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLegendURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LegendURL }
     * 
     * 
     */
    public List<LegendURL> getLegendURL() {
        if (legendURL == null) {
            legendURL = new ArrayList<LegendURL>();
        }
        return this.legendURL;
    }

    /**
     * Recupera il valore della proprietà styleSheetURL.
     * 
     * @return
     *     possible object is
     *     {@link StyleSheetURL }
     *     
     */
    public StyleSheetURL getStyleSheetURL() {
        return styleSheetURL;
    }

    /**
     * Imposta il valore della proprietà styleSheetURL.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleSheetURL }
     *     
     */
    public void setStyleSheetURL(StyleSheetURL value) {
        this.styleSheetURL = value;
    }

    /**
     * Recupera il valore della proprietà styleURL.
     * 
     * @return
     *     possible object is
     *     {@link StyleURL }
     *     
     */
    public StyleURL getStyleURL() {
        return styleURL;
    }

    /**
     * Imposta il valore della proprietà styleURL.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleURL }
     *     
     */
    public void setStyleURL(StyleURL value) {
        this.styleURL = value;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, (this.title!= null));
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, (this._abstract!= null));
        }
        {
            List<LegendURL> theLegendURL;
            theLegendURL = (((this.legendURL!= null)&&(!this.legendURL.isEmpty()))?this.getLegendURL():null);
            strategy.appendField(locator, this, "legendURL", buffer, theLegendURL, ((this.legendURL!= null)&&(!this.legendURL.isEmpty())));
        }
        {
            StyleSheetURL theStyleSheetURL;
            theStyleSheetURL = this.getStyleSheetURL();
            strategy.appendField(locator, this, "styleSheetURL", buffer, theStyleSheetURL, (this.styleSheetURL!= null));
        }
        {
            StyleURL theStyleURL;
            theStyleURL = this.getStyleURL();
            strategy.appendField(locator, this, "styleURL", buffer, theStyleURL, (this.styleURL!= null));
        }
        return buffer;
    }

    public void setLegendURL(List<LegendURL> value) {
        this.legendURL = null;
        if (value!= null) {
            List<LegendURL> draftl = this.getLegendURL();
            draftl.addAll(value);
        }
    }

}
