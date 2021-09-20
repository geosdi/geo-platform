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
 *         &lt;element ref="{http://www.opengis.net/sld}Name"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}LayerFeatureConstraints" minOccurs="0"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}NamedStyle"/&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}UserStyle"/&gt;
 *         &lt;/choice&gt;
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
    "layerFeatureConstraints",
    "namedStyleOrUserStyle"
})
@XmlRootElement(name = "NamedLayer")
public class NamedLayer implements ToString2
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "LayerFeatureConstraints")
    protected LayerFeatureConstraints layerFeatureConstraints;
    @XmlElements({
        @XmlElement(name = "NamedStyle", type = NamedStyle.class),
        @XmlElement(name = "UserStyle", type = UserStyle.class)
    })
    protected List<Object> namedStyleOrUserStyle;

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
     * Recupera il valore della proprietà layerFeatureConstraints.
     * 
     * @return
     *     possible object is
     *     {@link LayerFeatureConstraints }
     *     
     */
    public LayerFeatureConstraints getLayerFeatureConstraints() {
        return layerFeatureConstraints;
    }

    /**
     * Imposta il valore della proprietà layerFeatureConstraints.
     * 
     * @param value
     *     allowed object is
     *     {@link LayerFeatureConstraints }
     *     
     */
    public void setLayerFeatureConstraints(LayerFeatureConstraints value) {
        this.layerFeatureConstraints = value;
    }

    public boolean isSetLayerFeatureConstraints() {
        return (this.layerFeatureConstraints!= null);
    }

    /**
     * Gets the value of the namedStyleOrUserStyle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namedStyleOrUserStyle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamedStyleOrUserStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedStyle }
     * {@link UserStyle }
     * 
     * 
     */
    public List<Object> getNamedStyleOrUserStyle() {
        if (namedStyleOrUserStyle == null) {
            namedStyleOrUserStyle = new ArrayList<Object>();
        }
        return this.namedStyleOrUserStyle;
    }

    public boolean isSetNamedStyleOrUserStyle() {
        return ((this.namedStyleOrUserStyle!= null)&&(!this.namedStyleOrUserStyle.isEmpty()));
    }

    public void unsetNamedStyleOrUserStyle() {
        this.namedStyleOrUserStyle = null;
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
            LayerFeatureConstraints theLayerFeatureConstraints;
            theLayerFeatureConstraints = this.getLayerFeatureConstraints();
            strategy.appendField(locator, this, "layerFeatureConstraints", buffer, theLayerFeatureConstraints, this.isSetLayerFeatureConstraints());
        }
        {
            List<Object> theNamedStyleOrUserStyle;
            theNamedStyleOrUserStyle = (this.isSetNamedStyleOrUserStyle()?this.getNamedStyleOrUserStyle():null);
            strategy.appendField(locator, this, "namedStyleOrUserStyle", buffer, theNamedStyleOrUserStyle, this.isSetNamedStyleOrUserStyle());
        }
        return buffer;
    }

    public void setNamedStyleOrUserStyle(List<Object> value) {
        this.namedStyleOrUserStyle = null;
        if (value!= null) {
            List<Object> draftl = this.getNamedStyleOrUserStyle();
            draftl.addAll(value);
        }
    }

}
