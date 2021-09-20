//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *         &lt;element ref="{http://www.opengis.net/sld}Name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Title" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Abstract" minOccurs="0"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}NamedLayer"/&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}UserLayer"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.0.0" /&gt;
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
    "namedLayerOrUserLayer"
})
@XmlRootElement(name = "StyledLayerDescriptor")
public class StyledLayerDescriptor implements ToString2
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElements({
        @XmlElement(name = "NamedLayer", type = NamedLayer.class),
        @XmlElement(name = "UserLayer", type = UserLayer.class)
    })
    protected List<Object> namedLayerOrUserLayer;
    /**
     * 
     * 
     */
    @XmlAttribute(name = "version", required = true)
    public final static String VERSION = "1.0.0";

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
     * Gets the value of the namedLayerOrUserLayer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namedLayerOrUserLayer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamedLayerOrUserLayer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedLayer }
     * {@link UserLayer }
     * 
     * 
     */
    public List<Object> getNamedLayerOrUserLayer() {
        if (namedLayerOrUserLayer == null) {
            namedLayerOrUserLayer = new ArrayList<Object>();
        }
        return this.namedLayerOrUserLayer;
    }

    public boolean isSetNamedLayerOrUserLayer() {
        return ((this.namedLayerOrUserLayer!= null)&&(!this.namedLayerOrUserLayer.isEmpty()));
    }

    public void unsetNamedLayerOrUserLayer() {
        this.namedLayerOrUserLayer = null;
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
            List<Object> theNamedLayerOrUserLayer;
            theNamedLayerOrUserLayer = (this.isSetNamedLayerOrUserLayer()?this.getNamedLayerOrUserLayer():null);
            strategy.appendField(locator, this, "namedLayerOrUserLayer", buffer, theNamedLayerOrUserLayer, this.isSetNamedLayerOrUserLayer());
        }
        {
            String theVERSION;
            theVERSION = StyledLayerDescriptor.VERSION;
            strategy.appendField(locator, this, "version", buffer, theVERSION, true);
        }
        return buffer;
    }

    public void setNamedLayerOrUserLayer(List<Object> value) {
        this.namedLayerOrUserLayer = null;
        if (value!= null) {
            List<Object> draftl = this.getNamedLayerOrUserLayer();
            draftl.addAll(value);
        }
    }

}
