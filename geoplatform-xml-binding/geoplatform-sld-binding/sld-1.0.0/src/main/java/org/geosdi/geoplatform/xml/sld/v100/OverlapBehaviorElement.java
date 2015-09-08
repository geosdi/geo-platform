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
 *         &lt;element ref="{http://www.opengis.net/sld}LATEST_ON_TOP"/>
 *         &lt;element ref="{http://www.opengis.net/sld}EARLIEST_ON_TOP"/>
 *         &lt;element ref="{http://www.opengis.net/sld}AVERAGE"/>
 *         &lt;element ref="{http://www.opengis.net/sld}RANDOM"/>
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
    "latestontop",
    "earliestontop",
    "average",
    "random"
})
@XmlRootElement(name = "OverlapBehavior")
public class OverlapBehaviorElement
    implements ToString
{

    @XmlElement(name = "LATEST_ON_TOP")
    protected LATESTONTOPElement latestontop;
    @XmlElement(name = "EARLIEST_ON_TOP")
    protected EARLIESTONTOPElement earliestontop;
    @XmlElement(name = "AVERAGE")
    protected AVERAGEElement average;
    @XmlElement(name = "RANDOM")
    protected RANDOMElement random;

    /**
     * Recupera il valore della proprietà latestontop.
     * 
     * @return
     *     possible object is
     *     {@link LATESTONTOPElement }
     *     
     */
    public LATESTONTOPElement getLATESTONTOP() {
        return latestontop;
    }

    /**
     * Imposta il valore della proprietà latestontop.
     * 
     * @param value
     *     allowed object is
     *     {@link LATESTONTOPElement }
     *     
     */
    public void setLATESTONTOP(LATESTONTOPElement value) {
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
     *     {@link EARLIESTONTOPElement }
     *     
     */
    public EARLIESTONTOPElement getEARLIESTONTOP() {
        return earliestontop;
    }

    /**
     * Imposta il valore della proprietà earliestontop.
     * 
     * @param value
     *     allowed object is
     *     {@link EARLIESTONTOPElement }
     *     
     */
    public void setEARLIESTONTOP(EARLIESTONTOPElement value) {
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
     *     {@link AVERAGEElement }
     *     
     */
    public AVERAGEElement getAVERAGE() {
        return average;
    }

    /**
     * Imposta il valore della proprietà average.
     * 
     * @param value
     *     allowed object is
     *     {@link AVERAGEElement }
     *     
     */
    public void setAVERAGE(AVERAGEElement value) {
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
     *     {@link RANDOMElement }
     *     
     */
    public RANDOMElement getRANDOM() {
        return random;
    }

    /**
     * Imposta il valore della proprietà random.
     * 
     * @param value
     *     allowed object is
     *     {@link RANDOMElement }
     *     
     */
    public void setRANDOM(RANDOMElement value) {
        this.random = value;
    }

    public boolean isSetRANDOM() {
        return (this.random!= null);
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
            LATESTONTOPElement theLATESTONTOP;
            theLATESTONTOP = this.getLATESTONTOP();
            strategy.appendField(locator, this, "latestontop", buffer, theLATESTONTOP);
        }
        {
            EARLIESTONTOPElement theEARLIESTONTOP;
            theEARLIESTONTOP = this.getEARLIESTONTOP();
            strategy.appendField(locator, this, "earliestontop", buffer, theEARLIESTONTOP);
        }
        {
            AVERAGEElement theAVERAGE;
            theAVERAGE = this.getAVERAGE();
            strategy.appendField(locator, this, "average", buffer, theAVERAGE);
        }
        {
            RANDOMElement theRANDOM;
            theRANDOM = this.getRANDOM();
            strategy.appendField(locator, this, "random", buffer, theRANDOM);
        }
        return buffer;
    }

}
