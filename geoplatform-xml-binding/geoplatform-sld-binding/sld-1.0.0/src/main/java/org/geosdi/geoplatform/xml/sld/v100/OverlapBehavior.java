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
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}LATEST_ON_TOP"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}EARLIEST_ON_TOP"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}AVERAGE"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}RANDOM"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "latestontop",
    "earliestontop",
    "average",
    "random"
})
@XmlRootElement(name = "OverlapBehavior")
public class OverlapBehavior implements ToString2
{

    @XmlElement(name = "LATEST_ON_TOP")
    protected LATESTONTOP latestontop;
    @XmlElement(name = "EARLIEST_ON_TOP")
    protected EARLIESTONTOP earliestontop;
    @XmlElement(name = "AVERAGE")
    protected AVERAGE average;
    @XmlElement(name = "RANDOM")
    protected RANDOM random;

    /**
     * Recupera il valore della proprietà latestontop.
     * 
     * @return
     *     possible object is
     *     {@link LATESTONTOP }
     *     
     */
    public LATESTONTOP getLATESTONTOP() {
        return latestontop;
    }

    /**
     * Imposta il valore della proprietà latestontop.
     * 
     * @param value
     *     allowed object is
     *     {@link LATESTONTOP }
     *     
     */
    public void setLATESTONTOP(LATESTONTOP value) {
        this.latestontop = value;
    }

    public boolean isSetLATESTONTOP() {
        return (this.latestontop!= null);
    }

    /**
     * Recupera il valore della proprietà earliestontop.
     * 
     * @return
     *     possible object is
     *     {@link EARLIESTONTOP }
     *     
     */
    public EARLIESTONTOP getEARLIESTONTOP() {
        return earliestontop;
    }

    /**
     * Imposta il valore della proprietà earliestontop.
     * 
     * @param value
     *     allowed object is
     *     {@link EARLIESTONTOP }
     *     
     */
    public void setEARLIESTONTOP(EARLIESTONTOP value) {
        this.earliestontop = value;
    }

    public boolean isSetEARLIESTONTOP() {
        return (this.earliestontop!= null);
    }

    /**
     * Recupera il valore della proprietà average.
     * 
     * @return
     *     possible object is
     *     {@link AVERAGE }
     *     
     */
    public AVERAGE getAVERAGE() {
        return average;
    }

    /**
     * Imposta il valore della proprietà average.
     * 
     * @param value
     *     allowed object is
     *     {@link AVERAGE }
     *     
     */
    public void setAVERAGE(AVERAGE value) {
        this.average = value;
    }

    public boolean isSetAVERAGE() {
        return (this.average!= null);
    }

    /**
     * Recupera il valore della proprietà random.
     * 
     * @return
     *     possible object is
     *     {@link RANDOM }
     *     
     */
    public RANDOM getRANDOM() {
        return random;
    }

    /**
     * Imposta il valore della proprietà random.
     * 
     * @param value
     *     allowed object is
     *     {@link RANDOM }
     *     
     */
    public void setRANDOM(RANDOM value) {
        this.random = value;
    }

    public boolean isSetRANDOM() {
        return (this.random!= null);
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
            LATESTONTOP theLATESTONTOP;
            theLATESTONTOP = this.getLATESTONTOP();
            strategy.appendField(locator, this, "latestontop", buffer, theLATESTONTOP, this.isSetLATESTONTOP());
        }
        {
            EARLIESTONTOP theEARLIESTONTOP;
            theEARLIESTONTOP = this.getEARLIESTONTOP();
            strategy.appendField(locator, this, "earliestontop", buffer, theEARLIESTONTOP, this.isSetEARLIESTONTOP());
        }
        {
            AVERAGE theAVERAGE;
            theAVERAGE = this.getAVERAGE();
            strategy.appendField(locator, this, "average", buffer, theAVERAGE, this.isSetAVERAGE());
        }
        {
            RANDOM theRANDOM;
            theRANDOM = this.getRANDOM();
            strategy.appendField(locator, this, "random", buffer, theRANDOM, this.isSetRANDOM());
        }
        return buffer;
    }

}
