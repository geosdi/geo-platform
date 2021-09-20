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
 *     &lt;extension base="{http://www.opengis.net/sld}SymbolizerType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Geometry" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Label" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Font" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}LabelPlacement" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Halo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Fill" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "geometry",
    "label",
    "font",
    "labelPlacement",
    "halo",
    "fill"
})
public class TextSymbolizer
    extends SymbolizerType
    implements ToString2
{

    @XmlElement(name = "Geometry")
    protected Geometry geometry;
    @XmlElement(name = "Label")
    protected ParameterValueType label;
    @XmlElement(name = "Font")
    protected Font font;
    @XmlElement(name = "LabelPlacement")
    protected LabelPlacement labelPlacement;
    @XmlElement(name = "Halo")
    protected Halo halo;
    @XmlElement(name = "Fill")
    protected Fill fill;

    /**
     * Recupera il valore della proprietà geometry.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Imposta il valore della proprietà geometry.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setGeometry(Geometry value) {
        this.geometry = value;
    }

    public boolean isSetGeometry() {
        return (this.geometry!= null);
    }

    /**
     * Recupera il valore della proprietà label.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getLabel() {
        return label;
    }

    /**
     * Imposta il valore della proprietà label.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setLabel(ParameterValueType value) {
        this.label = value;
    }

    public boolean isSetLabel() {
        return (this.label!= null);
    }

    /**
     * Recupera il valore della proprietà font.
     * 
     * @return
     *     possible object is
     *     {@link Font }
     *     
     */
    public Font getFont() {
        return font;
    }

    /**
     * Imposta il valore della proprietà font.
     * 
     * @param value
     *     allowed object is
     *     {@link Font }
     *     
     */
    public void setFont(Font value) {
        this.font = value;
    }

    public boolean isSetFont() {
        return (this.font!= null);
    }

    /**
     * Recupera il valore della proprietà labelPlacement.
     * 
     * @return
     *     possible object is
     *     {@link LabelPlacement }
     *     
     */
    public LabelPlacement getLabelPlacement() {
        return labelPlacement;
    }

    /**
     * Imposta il valore della proprietà labelPlacement.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelPlacement }
     *     
     */
    public void setLabelPlacement(LabelPlacement value) {
        this.labelPlacement = value;
    }

    public boolean isSetLabelPlacement() {
        return (this.labelPlacement!= null);
    }

    /**
     * Recupera il valore della proprietà halo.
     * 
     * @return
     *     possible object is
     *     {@link Halo }
     *     
     */
    public Halo getHalo() {
        return halo;
    }

    /**
     * Imposta il valore della proprietà halo.
     * 
     * @param value
     *     allowed object is
     *     {@link Halo }
     *     
     */
    public void setHalo(Halo value) {
        this.halo = value;
    }

    public boolean isSetHalo() {
        return (this.halo!= null);
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
        super.appendFields(locator, buffer, strategy);
        {
            Geometry theGeometry;
            theGeometry = this.getGeometry();
            strategy.appendField(locator, this, "geometry", buffer, theGeometry, this.isSetGeometry());
        }
        {
            ParameterValueType theLabel;
            theLabel = this.getLabel();
            strategy.appendField(locator, this, "label", buffer, theLabel, this.isSetLabel());
        }
        {
            Font theFont;
            theFont = this.getFont();
            strategy.appendField(locator, this, "font", buffer, theFont, this.isSetFont());
        }
        {
            LabelPlacement theLabelPlacement;
            theLabelPlacement = this.getLabelPlacement();
            strategy.appendField(locator, this, "labelPlacement", buffer, theLabelPlacement, this.isSetLabelPlacement());
        }
        {
            Halo theHalo;
            theHalo = this.getHalo();
            strategy.appendField(locator, this, "halo", buffer, theHalo, this.isSetHalo());
        }
        {
            Fill theFill;
            theFill = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theFill, this.isSetFill());
        }
        return buffer;
    }

}
