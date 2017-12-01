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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per anonymous complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}ResponseBaseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProcessDescription" type="{http://www.opengis.net/wps/1.0.0}ProcessDescriptionType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "processDescription"
})
@XmlRootElement(name = "ProcessDescriptions")
public class ProcessDescriptions extends ResponseBaseType implements ToString2 {

    @XmlElement(name = "ProcessDescription", namespace = "", required = true)
    protected List<ProcessDescriptionType> processDescription;

    /**
     * Gets the value of the processDescription property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processDescription property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessDescription().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessDescriptionType }
     */
    public List<ProcessDescriptionType> getProcessDescription() {
        if (processDescription == null) {
            processDescription = new ArrayList<ProcessDescriptionType>();
        }
        return this.processDescription;
    }

    public boolean isSetProcessDescription() {
        return ((this.processDescription != null) && (!this.processDescription.isEmpty()));
    }

    public void unsetProcessDescription() {
        this.processDescription = null;
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
            List<ProcessDescriptionType> theProcessDescription;
            theProcessDescription = (this.isSetProcessDescription() ? this.getProcessDescription() : null);
            strategy.appendField(locator, this, "processDescription", buffer, theProcessDescription, this.isSetProcessDescription());
        }
        return buffer;
    }

    public void setProcessDescription(List<ProcessDescriptionType> value) {
        this.processDescription = null;
        if (value != null) {
            List<ProcessDescriptionType> draftl = this.getProcessDescription();
            draftl.addAll(value);
        }
    }

}
