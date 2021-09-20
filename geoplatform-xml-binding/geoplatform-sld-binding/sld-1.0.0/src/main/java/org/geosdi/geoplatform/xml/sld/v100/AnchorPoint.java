//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://www.opengis.net/sld}AnchorPointX"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}AnchorPointY"/&gt;
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
    "anchorPointX",
    "anchorPointY"
})
@XmlRootElement(name = "AnchorPoint")
public class AnchorPoint implements ToString2
{

    @XmlElement(name = "AnchorPointX", required = true)
    protected ParameterValueType anchorPointX;
    @XmlElement(name = "AnchorPointY", required = true)
    protected ParameterValueType anchorPointY;

    /**
     * Recupera il valore della proprietà anchorPointX.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getAnchorPointX() {
        return anchorPointX;
    }

    /**
     * Imposta il valore della proprietà anchorPointX.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setAnchorPointX(ParameterValueType value) {
        this.anchorPointX = value;
    }

    public boolean isSetAnchorPointX() {
        return (this.anchorPointX!= null);
    }

    /**
     * Recupera il valore della proprietà anchorPointY.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getAnchorPointY() {
        return anchorPointY;
    }

    /**
     * Imposta il valore della proprietà anchorPointY.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setAnchorPointY(ParameterValueType value) {
        this.anchorPointY = value;
    }

    public boolean isSetAnchorPointY() {
        return (this.anchorPointY!= null);
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
            ParameterValueType theAnchorPointX;
            theAnchorPointX = this.getAnchorPointX();
            strategy.appendField(locator, this, "anchorPointX", buffer, theAnchorPointX, this.isSetAnchorPointX());
        }
        {
            ParameterValueType theAnchorPointY;
            theAnchorPointY = this.getAnchorPointY();
            strategy.appendField(locator, this, "anchorPointY", buffer, theAnchorPointY, this.isSetAnchorPointY());
        }
        return buffer;
    }

}
