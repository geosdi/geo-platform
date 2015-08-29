//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="color" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="opacity" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ColorMapEntry")
public class ColorMapEntryElement
    implements ToString
{

    @XmlAttribute(name = "color", required = true)
    protected String color;
    @XmlAttribute(name = "opacity")
    protected Double opacity;
    @XmlAttribute(name = "quantity")
    protected Double quantity;
    @XmlAttribute(name = "label")
    protected String label;

    /**
     * Recupera il valore della proprietà color.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColor() {
        return color;
    }

    /**
     * Imposta il valore della proprietà color.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(String value) {
        this.color = value;
    }

    public boolean isSetColor() {
        return (this.color!= null);
    }

    /**
     * Recupera il valore della proprietà opacity.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * Imposta il valore della proprietà opacity.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOpacity(double value) {
        this.opacity = value;
    }

    public boolean isSetOpacity() {
        return (this.opacity!= null);
    }

    public void unsetOpacity() {
        this.opacity = null;
    }

    /**
     * Recupera il valore della proprietà quantity.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Imposta il valore della proprietà quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantity(double value) {
        this.quantity = value;
    }

    public boolean isSetQuantity() {
        return (this.quantity!= null);
    }

    public void unsetQuantity() {
        this.quantity = null;
    }

    /**
     * Recupera il valore della proprietà label.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Imposta il valore della proprietà label.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    public boolean isSetLabel() {
        return (this.label!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theColor;
            theColor = this.getColor();
            strategy.appendField(locator, this, "color", buffer, theColor);
        }
        {
            double theOpacity;
            theOpacity = (this.isSetOpacity()?this.getOpacity(): 0.0D);
            strategy.appendField(locator, this, "opacity", buffer, theOpacity);
        }
        {
            double theQuantity;
            theQuantity = (this.isSetQuantity()?this.getQuantity(): 0.0D);
            strategy.appendField(locator, this, "quantity", buffer, theQuantity);
        }
        {
            String theLabel;
            theLabel = this.getLabel();
            strategy.appendField(locator, this, "label", buffer, theLabel);
        }
        return buffer;
    }

}
