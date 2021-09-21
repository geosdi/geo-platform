//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{http://www.opengis.net/sld}Name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Title" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Abstract" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}IsDefault" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}FeatureTypeStyle" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserStyle", propOrder = {
    "name",
    "title",
    "_abstract",
    "isDefault",
    "featureTypeStyle"
})
@XmlRootElement(name = "UserStyle")
public class UserStyle implements ToString2
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "IsDefault")
    protected Boolean isDefault;
    @XmlElement(name = "FeatureTypeStyle", required = true)
    protected List<FeatureTypeStyle> featureTypeStyle;

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

    public boolean isSetName() {
        return (this.name!= null);
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

    public boolean isSetTitle() {
        return (this.title!= null);
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

    public boolean isSetAbstract() {
        return (this._abstract!= null);
    }

    /**
     * Recupera il valore della proprietà isDefault.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDefault() {
        return isDefault;
    }

    /**
     * Imposta il valore della proprietà isDefault.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDefault(Boolean value) {
        this.isDefault = value;
    }

    public boolean isSetIsDefault() {
        return (this.isDefault!= null);
    }

    /**
     * Gets the value of the featureTypeStyle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureTypeStyle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureTypeStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeatureTypeStyle }
     * 
     * 
     */
    public List<FeatureTypeStyle> getFeatureTypeStyle() {
        if (featureTypeStyle == null) {
            featureTypeStyle = new ArrayList<FeatureTypeStyle>();
        }
        return this.featureTypeStyle;
    }

    public boolean isSetFeatureTypeStyle() {
        return ((this.featureTypeStyle!= null)&&(!this.featureTypeStyle.isEmpty()));
    }

    public void unsetFeatureTypeStyle() {
        this.featureTypeStyle = null;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, this.isSetAbstract());
        }
        {
            Boolean theIsDefault;
            theIsDefault = this.isIsDefault();
            strategy.appendField(locator, this, "isDefault", buffer, theIsDefault, this.isSetIsDefault());
        }
        {
            List<FeatureTypeStyle> theFeatureTypeStyle;
            theFeatureTypeStyle = (this.isSetFeatureTypeStyle()?this.getFeatureTypeStyle():null);
            strategy.appendField(locator, this, "featureTypeStyle", buffer, theFeatureTypeStyle, this.isSetFeatureTypeStyle());
        }
        return buffer;
    }

    public void setFeatureTypeStyle(List<FeatureTypeStyle> value) {
        this.featureTypeStyle = null;
        if (value!= null) {
            List<FeatureTypeStyle> draftl = this.getFeatureTypeStyle();
            draftl.addAll(value);
        }
    }

}
