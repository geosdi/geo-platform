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
 * References an externally defined finite set of values and ranges for this input.
 * <p>
 * <p>Classe Java per ValuesReferenceType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="ValuesReferenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute ref="{http://www.opengis.net/ows/1.1}reference"/&gt;
 *       &lt;attribute name="valuesForm" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValuesReferenceType")
public class ValuesReferenceType implements ToString2 {

    @XmlAttribute(name = "reference", namespace = "http://www.opengis.net/ows/1.1")
    @XmlSchemaType(name = "anyURI")
    protected String reference;
    @XmlAttribute(name = "valuesForm")
    @XmlSchemaType(name = "anyURI")
    protected String valuesForm;

    /**
     * Recupera il valore della proprietà reference.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReference() {
        return reference;
    }

    /**
     * Imposta il valore della proprietà reference.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReference(String value) {
        this.reference = value;
    }

    public boolean isSetReference() {
        return (this.reference != null);
    }

    /**
     * Recupera il valore della proprietà valuesForm.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValuesForm() {
        return valuesForm;
    }

    /**
     * Imposta il valore della proprietà valuesForm.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValuesForm(String value) {
        this.valuesForm = value;
    }

    public boolean isSetValuesForm() {
        return (this.valuesForm != null);
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
            String theReference;
            theReference = this.getReference();
            strategy.appendField(locator, this, "reference", buffer, theReference, this.isSetReference());
        }
        {
            String theValuesForm;
            theValuesForm = this.getValuesForm();
            strategy.appendField(locator, this, "valuesForm", buffer, theValuesForm, this.isSetValuesForm());
        }
        return buffer;
    }

}
