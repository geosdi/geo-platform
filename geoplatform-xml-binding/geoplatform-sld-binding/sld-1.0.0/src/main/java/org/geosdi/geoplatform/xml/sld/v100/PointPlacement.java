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
 *         &lt;element ref="{http://www.opengis.net/sld}AnchorPoint" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Displacement" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Rotation" minOccurs="0"/&gt;
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
    "anchorPoint",
    "displacement",
    "rotation"
})
@XmlRootElement(name = "PointPlacement")
public class PointPlacement implements ToString2
{

    @XmlElement(name = "AnchorPoint")
    protected AnchorPoint anchorPoint;
    @XmlElement(name = "Displacement")
    protected Displacement displacement;
    @XmlElement(name = "Rotation")
    protected ParameterValueType rotation;

    /**
     * Recupera il valore della proprietà anchorPoint.
     * 
     * @return
     *     possible object is
     *     {@link AnchorPoint }
     *     
     */
    public AnchorPoint getAnchorPoint() {
        return anchorPoint;
    }

    /**
     * Imposta il valore della proprietà anchorPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link AnchorPoint }
     *     
     */
    public void setAnchorPoint(AnchorPoint value) {
        this.anchorPoint = value;
    }

    public boolean isSetAnchorPoint() {
        return (this.anchorPoint!= null);
    }

    /**
     * Recupera il valore della proprietà displacement.
     * 
     * @return
     *     possible object is
     *     {@link Displacement }
     *     
     */
    public Displacement getDisplacement() {
        return displacement;
    }

    /**
     * Imposta il valore della proprietà displacement.
     * 
     * @param value
     *     allowed object is
     *     {@link Displacement }
     *     
     */
    public void setDisplacement(Displacement value) {
        this.displacement = value;
    }

    public boolean isSetDisplacement() {
        return (this.displacement!= null);
    }

    /**
     * Recupera il valore della proprietà rotation.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getRotation() {
        return rotation;
    }

    /**
     * Imposta il valore della proprietà rotation.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setRotation(ParameterValueType value) {
        this.rotation = value;
    }

    public boolean isSetRotation() {
        return (this.rotation!= null);
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
            AnchorPoint theAnchorPoint;
            theAnchorPoint = this.getAnchorPoint();
            strategy.appendField(locator, this, "anchorPoint", buffer, theAnchorPoint, this.isSetAnchorPoint());
        }
        {
            Displacement theDisplacement;
            theDisplacement = this.getDisplacement();
            strategy.appendField(locator, this, "displacement", buffer, theDisplacement, this.isSetDisplacement());
        }
        {
            ParameterValueType theRotation;
            theRotation = this.getRotation();
            strategy.appendField(locator, this, "rotation", buffer, theRotation, this.isSetRotation());
        }
        return buffer;
    }

}
