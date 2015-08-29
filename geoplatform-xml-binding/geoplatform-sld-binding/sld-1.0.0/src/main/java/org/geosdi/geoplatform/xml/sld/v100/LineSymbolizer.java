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
 *         &lt;element ref="{http://www.opengis.net/sld}Stroke" minOccurs="0"/>
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
    "stroke"
})
public class LineSymbolizer
    extends SymbolizerType
    implements ToString
{

    @XmlElement(name = "Geometry")
    protected GeometryElement geometry;
    @XmlElement(name = "Stroke")
    protected StrokeElement stroke;

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
        super.appendFields(locator, buffer, strategy);
        {
            GeometryElement theGeometry;
            theGeometry = this.getGeometry();
            strategy.appendField(locator, this, "geometry", buffer, theGeometry);
        }
        {
            StrokeElement theStroke;
            theStroke = this.getStroke();
            strategy.appendField(locator, this, "stroke", buffer, theStroke);
        }
        return buffer;
    }

}
