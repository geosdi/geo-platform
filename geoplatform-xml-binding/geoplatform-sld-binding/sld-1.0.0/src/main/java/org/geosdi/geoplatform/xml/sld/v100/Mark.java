//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

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
 *         &lt;element ref="{http://www.opengis.net/sld}WellKnownName" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Fill" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Stroke" minOccurs="0"/&gt;
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
    "wellKnownName",
    "fill",
    "stroke"
})
@XmlRootElement(name = "Mark")
public class Mark implements ToString2
{

    @XmlElement(name = "WellKnownName")
    protected String wellKnownName;
    @XmlElement(name = "Fill")
    protected Fill fill;
    @XmlElement(name = "Stroke")
    protected Stroke stroke;

    /**
     * Recupera il valore della proprietà wellKnownName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWellKnownName() {
        return wellKnownName;
    }

    /**
     * Imposta il valore della proprietà wellKnownName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWellKnownName(String value) {
        this.wellKnownName = value;
    }

    public boolean isSetWellKnownName() {
        return (this.wellKnownName!= null);
    }

    /**
     * Recupera il valore della proprietà fill.
     * 
     * @return
     *     possible object is
     *     {@link Fill }
     *     
     */
    public Fill getFill() {
        return fill;
    }

    /**
     * Imposta il valore della proprietà fill.
     * 
     * @param value
     *     allowed object is
     *     {@link Fill }
     *     
     */
    public void setFill(Fill value) {
        this.fill = value;
    }

    public boolean isSetFill() {
        return (this.fill!= null);
    }

    /**
     * Recupera il valore della proprietà stroke.
     * 
     * @return
     *     possible object is
     *     {@link Stroke }
     *     
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * Imposta il valore della proprietà stroke.
     * 
     * @param value
     *     allowed object is
     *     {@link Stroke }
     *     
     */
    public void setStroke(Stroke value) {
        this.stroke = value;
    }

    public boolean isSetStroke() {
        return (this.stroke!= null);
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
            String theWellKnownName;
            theWellKnownName = this.getWellKnownName();
            strategy.appendField(locator, this, "wellKnownName", buffer, theWellKnownName, this.isSetWellKnownName());
        }
        {
            Fill theFill;
            theFill = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theFill, this.isSetFill());
        }
        {
            Stroke theStroke;
            theStroke = this.getStroke();
            strategy.appendField(locator, this, "stroke", buffer, theStroke, this.isSetStroke());
        }
        return buffer;
    }

}
