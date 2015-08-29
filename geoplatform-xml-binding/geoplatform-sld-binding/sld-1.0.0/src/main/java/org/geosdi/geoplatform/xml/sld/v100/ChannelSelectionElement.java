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
 *         &lt;sequence>
 *           &lt;element ref="{http://www.opengis.net/sld}RedChannel"/>
 *           &lt;element ref="{http://www.opengis.net/sld}GreenChannel"/>
 *           &lt;element ref="{http://www.opengis.net/sld}BlueChannel"/>
 *         &lt;/sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}GrayChannel"/>
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
    "redChannel",
    "greenChannel",
    "blueChannel",
    "grayChannel"
})
@XmlRootElement(name = "ChannelSelection")
public class ChannelSelectionElement
    implements ToString
{

    @XmlElement(name = "RedChannel")
    protected SelectedChannelType redChannel;
    @XmlElement(name = "GreenChannel")
    protected SelectedChannelType greenChannel;
    @XmlElement(name = "BlueChannel")
    protected SelectedChannelType blueChannel;
    @XmlElement(name = "GrayChannel")
    protected SelectedChannelType grayChannel;

    /**
     * Recupera il valore della proprietà redChannel.
     * 
     * @return
     *     possible object is
     *     {@link SelectedChannelType }
     *     
     */
    public SelectedChannelType getRedChannel() {
        return redChannel;
    }

    /**
     * Imposta il valore della proprietà redChannel.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedChannelType }
     *     
     */
    public void setRedChannel(SelectedChannelType value) {
        this.redChannel = value;
    }

    public boolean isSetRedChannel() {
        return (this.redChannel!= null);
    }

    /**
     * Recupera il valore della proprietà greenChannel.
     * 
     * @return
     *     possible object is
     *     {@link SelectedChannelType }
     *     
     */
    public SelectedChannelType getGreenChannel() {
        return greenChannel;
    }

    /**
     * Imposta il valore della proprietà greenChannel.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedChannelType }
     *     
     */
    public void setGreenChannel(SelectedChannelType value) {
        this.greenChannel = value;
    }

    public boolean isSetGreenChannel() {
        return (this.greenChannel!= null);
    }

    /**
     * Recupera il valore della proprietà blueChannel.
     * 
     * @return
     *     possible object is
     *     {@link SelectedChannelType }
     *     
     */
    public SelectedChannelType getBlueChannel() {
        return blueChannel;
    }

    /**
     * Imposta il valore della proprietà blueChannel.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedChannelType }
     *     
     */
    public void setBlueChannel(SelectedChannelType value) {
        this.blueChannel = value;
    }

    public boolean isSetBlueChannel() {
        return (this.blueChannel!= null);
    }

    /**
     * Recupera il valore della proprietà grayChannel.
     * 
     * @return
     *     possible object is
     *     {@link SelectedChannelType }
     *     
     */
    public SelectedChannelType getGrayChannel() {
        return grayChannel;
    }

    /**
     * Imposta il valore della proprietà grayChannel.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedChannelType }
     *     
     */
    public void setGrayChannel(SelectedChannelType value) {
        this.grayChannel = value;
    }

    public boolean isSetGrayChannel() {
        return (this.grayChannel!= null);
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
            SelectedChannelType theRedChannel;
            theRedChannel = this.getRedChannel();
            strategy.appendField(locator, this, "redChannel", buffer, theRedChannel);
        }
        {
            SelectedChannelType theGreenChannel;
            theGreenChannel = this.getGreenChannel();
            strategy.appendField(locator, this, "greenChannel", buffer, theGreenChannel);
        }
        {
            SelectedChannelType theBlueChannel;
            theBlueChannel = this.getBlueChannel();
            strategy.appendField(locator, this, "blueChannel", buffer, theBlueChannel);
        }
        {
            SelectedChannelType theGrayChannel;
            theGrayChannel = this.getGrayChannel();
            strategy.appendField(locator, this, "grayChannel", buffer, theGrayChannel);
        }
        return buffer;
    }

}
