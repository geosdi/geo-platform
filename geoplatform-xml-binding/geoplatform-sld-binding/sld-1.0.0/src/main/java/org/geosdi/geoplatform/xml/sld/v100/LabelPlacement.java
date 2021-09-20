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
 *         &lt;element ref="{http://www.opengis.net/sld}PointPlacement"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}LinePlacement"/&gt;
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
    "pointPlacement",
    "linePlacement"
})
@XmlRootElement(name = "LabelPlacement")
public class LabelPlacement implements ToString2
{

    @XmlElement(name = "PointPlacement")
    protected PointPlacement pointPlacement;
    @XmlElement(name = "LinePlacement")
    protected LinePlacement linePlacement;

    /**
     * Recupera il valore della proprietà pointPlacement.
     * 
     * @return
     *     possible object is
     *     {@link PointPlacement }
     *     
     */
    public PointPlacement getPointPlacement() {
        return pointPlacement;
    }

    /**
     * Imposta il valore della proprietà pointPlacement.
     * 
     * @param value
     *     allowed object is
     *     {@link PointPlacement }
     *     
     */
    public void setPointPlacement(PointPlacement value) {
        this.pointPlacement = value;
    }

    public boolean isSetPointPlacement() {
        return (this.pointPlacement!= null);
    }

    /**
     * Recupera il valore della proprietà linePlacement.
     * 
     * @return
     *     possible object is
     *     {@link LinePlacement }
     *     
     */
    public LinePlacement getLinePlacement() {
        return linePlacement;
    }

    /**
     * Imposta il valore della proprietà linePlacement.
     * 
     * @param value
     *     allowed object is
     *     {@link LinePlacement }
     *     
     */
    public void setLinePlacement(LinePlacement value) {
        this.linePlacement = value;
    }

    public boolean isSetLinePlacement() {
        return (this.linePlacement!= null);
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
            PointPlacement thePointPlacement;
            thePointPlacement = this.getPointPlacement();
            strategy.appendField(locator, this, "pointPlacement", buffer, thePointPlacement, this.isSetPointPlacement());
        }
        {
            LinePlacement theLinePlacement;
            theLinePlacement = this.getLinePlacement();
            strategy.appendField(locator, this, "linePlacement", buffer, theLinePlacement, this.isSetLinePlacement());
        }
        return buffer;
    }

}
