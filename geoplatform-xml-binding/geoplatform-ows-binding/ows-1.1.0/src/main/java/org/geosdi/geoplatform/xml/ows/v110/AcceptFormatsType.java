//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Prioritized sequence of zero or more GetCapabilities operation response formats desired by client, with preferred formats listed first. Each response format shall be identified by its MIME type. See AcceptFormats parameter use subclause for more information. 
 * 
 * <p>Classe Java per AcceptFormatsType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AcceptFormatsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OutputFormat" type="{http://www.opengis.net/ows/1.1}MimeType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcceptFormatsType", propOrder = {
    "outputFormat"
})
public class AcceptFormatsType implements ToString2
{

    @XmlElement(name = "OutputFormat")
    protected List<String> outputFormat;

    /**
     * Gets the value of the outputFormat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outputFormat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutputFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOutputFormat() {
        if (outputFormat == null) {
            outputFormat = new ArrayList<String>();
        }
        return this.outputFormat;
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
            List<String> theOutputFormat;
            theOutputFormat = (((this.outputFormat!= null)&&(!this.outputFormat.isEmpty()))?this.getOutputFormat():null);
            strategy.appendField(locator, this, "outputFormat", buffer, theOutputFormat, ((this.outputFormat!= null)&&(!this.outputFormat.isEmpty())));
        }
        return buffer;
    }

    public void setOutputFormat(List<String> value) {
        this.outputFormat = null;
        if (value!= null) {
            List<String> draftl = this.getOutputFormat();
            draftl.addAll(value);
        }
    }

}
