//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}WellKnownName" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Fill" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Stroke" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
public class MarkElement implements ToString
{

    @XmlElement(name = "WellKnownName")
    protected String wellKnownName;
    @XmlElement(name = "Fill")
    protected FillElement fill;
    @XmlElement(name = "Stroke")
    protected StrokeElement stroke;

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
     *     {@link FillElement }
     *     
     */
    public FillElement getFill() {
        return fill;
    }

    /**
     * Imposta il valore della proprietà fill.
     * 
     * @param value
     *     allowed object is
     *     {@link FillElement }
     *     
     */
    public void setFill(FillElement value) {
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
     *     {@link StrokeElement }
     *     
     */
    public StrokeElement getStroke() {
        return stroke;
    }

    /**
     * Imposta il valore della proprietà stroke.
     * 
     * @param value
     *     allowed object is
     *     {@link StrokeElement }
     *     
     */
    public void setStroke(StrokeElement value) {
        this.stroke = value;
    }

    public boolean isSetStroke() {
        return (this.stroke!= null);
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
            String theWellKnownName;
            theWellKnownName = this.getWellKnownName();
            strategy.appendField(locator, this, "wellKnownName", buffer, theWellKnownName);
        }
        {
            FillElement theFill;
            theFill = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theFill);
        }
        {
            StrokeElement theStroke;
            theStroke = this.getStroke();
            strategy.appendField(locator, this, "stroke", buffer, theStroke);
        }
        return buffer;
    }

}
