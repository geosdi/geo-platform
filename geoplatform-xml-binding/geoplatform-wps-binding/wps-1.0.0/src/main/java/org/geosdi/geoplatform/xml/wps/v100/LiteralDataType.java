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
 * One simple literal value (such as an integer or real number) that is embedded in the Execute operation request or response.
 * <p>
 * String containing the Literal value (e.g., "49").
 * <p>
 * <p>Classe Java per LiteralDataType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="LiteralDataType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="dataType" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="uom" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiteralDataType", propOrder = {
        "value"
})
public class LiteralDataType implements ToString2 {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "dataType")
    @XmlSchemaType(name = "anyURI")
    protected String dataType;
    @XmlAttribute(name = "uom")
    @XmlSchemaType(name = "anyURI")
    protected String uom;

    /**
     * Recupera il valore della proprietà value.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value != null);
    }

    /**
     * Recupera il valore della proprietà dataType.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Imposta il valore della proprietà dataType.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    public boolean isSetDataType() {
        return (this.dataType != null);
    }

    /**
     * Recupera il valore della proprietà uom.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUom() {
        return uom;
    }

    /**
     * Imposta il valore della proprietà uom.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUom(String value) {
        this.uom = value;
    }

    public boolean isSetUom() {
        return (this.uom != null);
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
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theDataType;
            theDataType = this.getDataType();
            strategy.appendField(locator, this, "dataType", buffer, theDataType, this.isSetDataType());
        }
        {
            String theUom;
            theUom = this.getUom();
            strategy.appendField(locator, this, "uom", buffer, theUom, this.isSetUom());
        }
        return buffer;
    }

}
