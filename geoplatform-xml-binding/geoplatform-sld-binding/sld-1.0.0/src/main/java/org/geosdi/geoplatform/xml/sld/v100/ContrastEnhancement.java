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
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}Normalize"/&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}Histogram"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}GammaValue" minOccurs="0"/&gt;
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
    "normalize",
    "histogram",
    "gammaValue"
})
@XmlRootElement(name = "ContrastEnhancement")
public class ContrastEnhancement implements ToString2
{

    @XmlElement(name = "Normalize")
    protected Normalize normalize;
    @XmlElement(name = "Histogram")
    protected Histogram histogram;
    @XmlElement(name = "GammaValue")
    protected Double gammaValue;

    /**
     * Recupera il valore della proprietà normalize.
     * 
     * @return
     *     possible object is
     *     {@link Normalize }
     *     
     */
    public Normalize getNormalize() {
        return normalize;
    }

    /**
     * Imposta il valore della proprietà normalize.
     * 
     * @param value
     *     allowed object is
     *     {@link Normalize }
     *     
     */
    public void setNormalize(Normalize value) {
        this.normalize = value;
    }

    public boolean isSetNormalize() {
        return (this.normalize!= null);
    }

    /**
     * Recupera il valore della proprietà histogram.
     * 
     * @return
     *     possible object is
     *     {@link Histogram }
     *     
     */
    public Histogram getHistogram() {
        return histogram;
    }

    /**
     * Imposta il valore della proprietà histogram.
     * 
     * @param value
     *     allowed object is
     *     {@link Histogram }
     *     
     */
    public void setHistogram(Histogram value) {
        this.histogram = value;
    }

    public boolean isSetHistogram() {
        return (this.histogram!= null);
    }

    /**
     * Recupera il valore della proprietà gammaValue.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGammaValue() {
        return gammaValue;
    }

    /**
     * Imposta il valore della proprietà gammaValue.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGammaValue(Double value) {
        this.gammaValue = value;
    }

    public boolean isSetGammaValue() {
        return (this.gammaValue!= null);
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
            Normalize theNormalize;
            theNormalize = this.getNormalize();
            strategy.appendField(locator, this, "normalize", buffer, theNormalize, this.isSetNormalize());
        }
        {
            Histogram theHistogram;
            theHistogram = this.getHistogram();
            strategy.appendField(locator, this, "histogram", buffer, theHistogram, this.isSetHistogram());
        }
        {
            Double theGammaValue;
            theGammaValue = this.getGammaValue();
            strategy.appendField(locator, this, "gammaValue", buffer, theGammaValue, this.isSetGammaValue());
        }
        return buffer;
    }

}
