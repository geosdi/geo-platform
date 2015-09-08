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
 *     &lt;extension base="{http://www.opengis.net/sld}SymbolizerType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}Geometry" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Label" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Font" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}LabelPlacement" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Halo" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Fill" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
    implements ToString
{

    @XmlElement(name = "Geometry")
    protected GeometryElement geometry;
    @XmlElement(name = "Label")
    protected ParameterValueType label;
    @XmlElement(name = "Font")
    protected FontElement font;
    @XmlElement(name = "LabelPlacement")
    protected LabelPlacementElement labelPlacement;
    @XmlElement(name = "Halo")
    protected HaloElement halo;
    @XmlElement(name = "Fill")
    protected FillElement fill;

    /**
     * Recupera il valore della proprietà geometry.
     * 
     * @return
     *     possible object is
     *     {@link GeometryElement }
     *     
     */
    public GeometryElement getGeometry() {
        return geometry;
    }

    /**
     * Imposta il valore della proprietà geometry.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryElement }
     *     
     */
    public void setGeometry(GeometryElement value) {
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
     *     {@link FontElement }
     *     
     */
    public FontElement getFont() {
        return font;
    }

    /**
     * Imposta il valore della proprietà font.
     * 
     * @param value
     *     allowed object is
     *     {@link FontElement }
     *     
     */
    public void setFont(FontElement value) {
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
     *     {@link LabelPlacementElement }
     *     
     */
    public LabelPlacementElement getLabelPlacement() {
        return labelPlacement;
    }

    /**
     * Imposta il valore della proprietà labelPlacement.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelPlacementElement }
     *     
     */
    public void setLabelPlacement(LabelPlacementElement value) {
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
     *     {@link HaloElement }
     *     
     */
    public HaloElement getHalo() {
        return halo;
    }

    /**
     * Imposta il valore della proprietà halo.
     * 
     * @param value
     *     allowed object is
     *     {@link HaloElement }
     *     
     */
    public void setHalo(HaloElement value) {
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
        super.appendFields(locator, buffer, strategy);
        {
            GeometryElement theGeometry;
            theGeometry = this.getGeometry();
            strategy.appendField(locator, this, "geometry", buffer, theGeometry);
        }
        {
            ParameterValueType theLabel;
            theLabel = this.getLabel();
            strategy.appendField(locator, this, "label", buffer, theLabel);
        }
        {
            FontElement theFont;
            theFont = this.getFont();
            strategy.appendField(locator, this, "font", buffer, theFont);
        }
        {
            LabelPlacementElement theLabelPlacement;
            theLabelPlacement = this.getLabelPlacement();
            strategy.appendField(locator, this, "labelPlacement", buffer, theLabelPlacement);
        }
        {
            HaloElement theHalo;
            theHalo = this.getHalo();
            strategy.appendField(locator, this, "halo", buffer, theHalo);
        }
        {
            FillElement theFill;
            theFill = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theFill);
        }
        return buffer;
    }

}
