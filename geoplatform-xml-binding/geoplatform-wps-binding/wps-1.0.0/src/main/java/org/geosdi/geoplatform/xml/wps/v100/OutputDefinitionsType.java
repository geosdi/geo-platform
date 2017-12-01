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
import java.util.ArrayList;
import java.util.List;


/**
 * Definition of a format, encoding,  schema, and unit-of-measure for an output to be returned from a process.
 * <p>
 * <p>Classe Java per OutputDefinitionsType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="OutputDefinitionsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Output" type="{http://www.opengis.net/wps/1.0.0}DocumentOutputDefinitionType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutputDefinitionsType", propOrder = {
        "output"
})
public class OutputDefinitionsType implements ToString2 {

    @XmlElement(name = "Output", required = true)
    protected List<DocumentOutputDefinitionType> output;

    /**
     * Gets the value of the output property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the output property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutput().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentOutputDefinitionType }
     */
    public List<DocumentOutputDefinitionType> getOutput() {
        if (output == null) {
            output = new ArrayList<DocumentOutputDefinitionType>();
        }
        return this.output;
    }

    public boolean isSetOutput() {
        return ((this.output != null) && (!this.output.isEmpty()));
    }

    public void unsetOutput() {
        this.output = null;
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
            List<DocumentOutputDefinitionType> theOutput;
            theOutput = (this.isSetOutput() ? this.getOutput() : null);
            strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
        }
        return buffer;
    }

    public void setOutput(List<DocumentOutputDefinitionType> value) {
        this.output = null;
        if (value != null) {
            List<DocumentOutputDefinitionType> draftl = this.getOutput();
            draftl.addAll(value);
        }
    }

}
