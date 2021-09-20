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
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per SelectedChannelType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SelectedChannelType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}SourceChannelName"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}ContrastEnhancement" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectedChannelType", propOrder = {
    "sourceChannelName",
    "contrastEnhancement"
})
public class SelectedChannelType implements ToString2
{

    @XmlElement(name = "SourceChannelName", required = true)
    protected String sourceChannelName;
    @XmlElement(name = "ContrastEnhancement")
    protected ContrastEnhancement contrastEnhancement;

    /**
     * Recupera il valore della proprietà sourceChannelName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceChannelName() {
        return sourceChannelName;
    }

    /**
     * Imposta il valore della proprietà sourceChannelName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceChannelName(String value) {
        this.sourceChannelName = value;
    }

    public boolean isSetSourceChannelName() {
        return (this.sourceChannelName!= null);
    }

    /**
     * Recupera il valore della proprietà contrastEnhancement.
     * 
     * @return
     *     possible object is
     *     {@link ContrastEnhancement }
     *     
     */
    public ContrastEnhancement getContrastEnhancement() {
        return contrastEnhancement;
    }

    /**
     * Imposta il valore della proprietà contrastEnhancement.
     * 
     * @param value
     *     allowed object is
     *     {@link ContrastEnhancement }
     *     
     */
    public void setContrastEnhancement(ContrastEnhancement value) {
        this.contrastEnhancement = value;
    }

    public boolean isSetContrastEnhancement() {
        return (this.contrastEnhancement!= null);
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
            String theSourceChannelName;
            theSourceChannelName = this.getSourceChannelName();
            strategy.appendField(locator, this, "sourceChannelName", buffer, theSourceChannelName, this.isSetSourceChannelName());
        }
        {
            ContrastEnhancement theContrastEnhancement;
            theContrastEnhancement = this.getContrastEnhancement();
            strategy.appendField(locator, this, "contrastEnhancement", buffer, theContrastEnhancement, this.isSetContrastEnhancement());
        }
        return buffer;
    }

}
