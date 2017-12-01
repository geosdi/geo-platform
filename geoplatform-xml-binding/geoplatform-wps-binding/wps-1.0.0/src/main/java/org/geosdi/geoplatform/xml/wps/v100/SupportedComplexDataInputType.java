//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Classe Java per SupportedComplexDataInputType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="SupportedComplexDataInputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}SupportedComplexDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DefaultValue" type="{http://www.opengis.net/wps/1.0.0}ComplexDataType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="maximumMegabytes" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedComplexDataInputType", propOrder = {
        "defaultValue"
})
public class SupportedComplexDataInputType extends SupportedComplexDataType implements ToString2 {

    @XmlElement(name = "DefaultValue", namespace = "")
    protected ComplexDataType defaultValue;
    @XmlAttribute(name = "maximumMegabytes")
    protected BigInteger maximumMegabytes;

    /**
     * Recupera il valore della proprietà defaultValue.
     *
     * @return possible object is
     * {@link ComplexDataType }
     */
    public ComplexDataType getDefaultValue() {
        return defaultValue;
    }

    /**
     * Imposta il valore della proprietà defaultValue.
     *
     * @param value allowed object is
     *              {@link ComplexDataType }
     */
    public void setDefaultValue(ComplexDataType value) {
        this.defaultValue = value;
    }

    public boolean isSetDefaultValue() {
        return (this.defaultValue != null);
    }

    /**
     * Recupera il valore della proprietà maximumMegabytes.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getMaximumMegabytes() {
        return maximumMegabytes;
    }

    /**
     * Imposta il valore della proprietà maximumMegabytes.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setMaximumMegabytes(BigInteger value) {
        this.maximumMegabytes = value;
    }

    public boolean isSetMaximumMegabytes() {
        return (this.maximumMegabytes != null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
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
        super.appendFields(locator, buffer, strategy);
        {
            ComplexDataType theDefaultValue;
            theDefaultValue = this.getDefaultValue();
            strategy.appendField(locator, this, "defaultValue", buffer, theDefaultValue, this.isSetDefaultValue());
        }
        {
            BigInteger theMaximumMegabytes;
            theMaximumMegabytes = this.getMaximumMegabytes();
            strategy.appendField(locator, this, "maximumMegabytes", buffer, theMaximumMegabytes, this.isSetMaximumMegabytes());
        }
        return buffer;
    }

}
