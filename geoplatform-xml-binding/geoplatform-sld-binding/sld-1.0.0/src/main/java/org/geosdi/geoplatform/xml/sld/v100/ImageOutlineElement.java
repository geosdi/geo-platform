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
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/sld}LineSymbolizer"/>
 *         &lt;element ref="{http://www.opengis.net/sld}PolygonSymbolizer"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lineSymbolizer",
    "polygonSymbolizer"
})
@XmlRootElement(name = "ImageOutline")
public class ImageOutlineElement
    implements ToString
{

    @XmlElement(name = "LineSymbolizer")
    protected LineSymbolizer lineSymbolizer;
    @XmlElement(name = "PolygonSymbolizer")
    protected PolygonSymbolizer polygonSymbolizer;

    /**
     * Recupera il valore della proprietà lineSymbolizer.
     * 
     * @return
     *     possible object is
     *     {@link LineSymbolizer }
     *     
     */
    public LineSymbolizer getLineSymbolizer() {
        return lineSymbolizer;
    }

    /**
     * Imposta il valore della proprietà lineSymbolizer.
     * 
     * @param value
     *     allowed object is
     *     {@link LineSymbolizer }
     *     
     */
    public void setLineSymbolizer(LineSymbolizer value) {
        this.lineSymbolizer = value;
    }

    public boolean isSetLineSymbolizer() {
        return (this.lineSymbolizer!= null);
    }

    /**
     * Recupera il valore della proprietà polygonSymbolizer.
     * 
     * @return
     *     possible object is
     *     {@link PolygonSymbolizer }
     *     
     */
    public PolygonSymbolizer getPolygonSymbolizer() {
        return polygonSymbolizer;
    }

    /**
     * Imposta il valore della proprietà polygonSymbolizer.
     * 
     * @param value
     *     allowed object is
     *     {@link PolygonSymbolizer }
     *     
     */
    public void setPolygonSymbolizer(PolygonSymbolizer value) {
        this.polygonSymbolizer = value;
    }

    public boolean isSetPolygonSymbolizer() {
        return (this.polygonSymbolizer!= null);
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
            LineSymbolizer theLineSymbolizer;
            theLineSymbolizer = this.getLineSymbolizer();
            strategy.appendField(locator, this, "lineSymbolizer", buffer, theLineSymbolizer);
        }
        {
            PolygonSymbolizer thePolygonSymbolizer;
            thePolygonSymbolizer = this.getPolygonSymbolizer();
            strategy.appendField(locator, this, "polygonSymbolizer", buffer, thePolygonSymbolizer);
        }
        return buffer;
    }

}
