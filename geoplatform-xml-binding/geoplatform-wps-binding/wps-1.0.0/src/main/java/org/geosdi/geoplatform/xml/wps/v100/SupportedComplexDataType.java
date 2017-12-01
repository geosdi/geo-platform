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


/**
 * Formats, encodings, and schemas supported by a process input or output.
 * <p>
 * <p>Classe Java per SupportedComplexDataType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="SupportedComplexDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Default" type="{http://www.opengis.net/wps/1.0.0}ComplexDataCombinationType"/&gt;
 *         &lt;element name="Supported" type="{http://www.opengis.net/wps/1.0.0}ComplexDataCombinationsType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedComplexDataType", propOrder = {
        "_default",
        "supported"
})
@XmlSeeAlso({
        SupportedComplexDataInputType.class
})
public class SupportedComplexDataType implements ToString2 {

    @XmlElement(name = "Default", namespace = "", required = true)
    protected ComplexDataCombinationType _default;
    @XmlElement(name = "Supported", namespace = "", required = true)
    protected ComplexDataCombinationsType supported;

    /**
     * Recupera il valore della proprietà default.
     *
     * @return possible object is
     * {@link ComplexDataCombinationType }
     */
    public ComplexDataCombinationType getDefault() {
        return _default;
    }

    /**
     * Imposta il valore della proprietà default.
     *
     * @param value allowed object is
     *              {@link ComplexDataCombinationType }
     */
    public void setDefault(ComplexDataCombinationType value) {
        this._default = value;
    }

    public boolean isSetDefault() {
        return (this._default != null);
    }

    /**
     * Recupera il valore della proprietà supported.
     *
     * @return possible object is
     * {@link ComplexDataCombinationsType }
     */
    public ComplexDataCombinationsType getSupported() {
        return supported;
    }

    /**
     * Imposta il valore della proprietà supported.
     *
     * @param value allowed object is
     *              {@link ComplexDataCombinationsType }
     */
    public void setSupported(ComplexDataCombinationsType value) {
        this.supported = value;
    }

    public boolean isSetSupported() {
        return (this.supported != null);
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
        {
            ComplexDataCombinationType theDefault;
            theDefault = this.getDefault();
            strategy.appendField(locator, this, "_default", buffer, theDefault, this.isSetDefault());
        }
        {
            ComplexDataCombinationsType theSupported;
            theSupported = this.getSupported();
            strategy.appendField(locator, this, "supported", buffer, theSupported, this.isSetSupported());
        }
        return buffer;
    }

}
