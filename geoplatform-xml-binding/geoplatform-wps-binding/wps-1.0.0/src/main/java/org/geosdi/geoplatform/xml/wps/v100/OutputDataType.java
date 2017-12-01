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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Value of one output from a process.
 * <p>
 * In this use, the DescriptionType shall describe this process output.
 * <p>
 * <p>Classe Java per OutputDataType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="OutputDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}DescriptionType"&gt;
 *       &lt;group ref="{http://www.opengis.net/wps/1.0.0}OutputDataFormChoice"/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutputDataType", propOrder = {
        "reference",
        "data"
})
public class OutputDataType extends DescriptionType implements ToString2 {

    @XmlElement(name = "Reference")
    protected OutputReferenceType reference;
    @XmlElement(name = "Data")
    protected DataType data;

    /**
     * Recupera il valore della proprietà reference.
     *
     * @return possible object is
     * {@link OutputReferenceType }
     */
    public OutputReferenceType getReference() {
        return reference;
    }

    /**
     * Imposta il valore della proprietà reference.
     *
     * @param value allowed object is
     *              {@link OutputReferenceType }
     */
    public void setReference(OutputReferenceType value) {
        this.reference = value;
    }

    public boolean isSetReference() {
        return (this.reference != null);
    }

    /**
     * Recupera il valore della proprietà data.
     *
     * @return possible object is
     * {@link DataType }
     */
    public DataType getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     *
     * @param value allowed object is
     *              {@link DataType }
     */
    public void setData(DataType value) {
        this.data = value;
    }

    public boolean isSetData() {
        return (this.data != null);
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
            OutputReferenceType theReference;
            theReference = this.getReference();
            strategy.appendField(locator, this, "reference", buffer, theReference, this.isSetReference());
        }
        {
            DataType theData;
            theData = this.getData();
            strategy.appendField(locator, this, "data", buffer, theData, this.isSetData());
        }
        return buffer;
    }

}
