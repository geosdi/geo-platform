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
 *         &lt;element ref="{http://www.opengis.net/sld}AnchorPointX"/>
 *         &lt;element ref="{http://www.opengis.net/sld}AnchorPointY"/>
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
    "anchorPointX",
    "anchorPointY"
})
@XmlRootElement(name = "AnchorPoint")
public class AnchorPointElement
    implements ToString
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
            ParameterValueType theAnchorPointX;
            theAnchorPointX = this.getAnchorPointX();
            strategy.appendField(locator, this, "anchorPointX", buffer, theAnchorPointX);
        }
        {
            ParameterValueType theAnchorPointY;
            theAnchorPointY = this.getAnchorPointY();
            strategy.appendField(locator, this, "anchorPointY", buffer, theAnchorPointY);
        }
        return buffer;
    }

}
