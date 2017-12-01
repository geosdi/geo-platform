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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Description of the status of process execution.
 * <p>
 * <p>Classe Java per StatusType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="StatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ProcessAccepted" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ProcessStarted" type="{http://www.opengis.net/wps/1.0.0}ProcessStartedType"/&gt;
 *         &lt;element name="ProcessPaused" type="{http://www.opengis.net/wps/1.0.0}ProcessStartedType"/&gt;
 *         &lt;element name="ProcessSucceeded" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ProcessFailed" type="{http://www.opengis.net/wps/1.0.0}ProcessFailedType"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="creationTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", propOrder = {
        "processAccepted",
        "processStarted",
        "processPaused",
        "processSucceeded",
        "processFailed"
})
public class StatusType implements ToString2 {

    @XmlElement(name = "ProcessAccepted")
    protected String processAccepted;
    @XmlElement(name = "ProcessStarted")
    protected ProcessStartedType processStarted;
    @XmlElement(name = "ProcessPaused")
    protected ProcessStartedType processPaused;
    @XmlElement(name = "ProcessSucceeded")
    protected String processSucceeded;
    @XmlElement(name = "ProcessFailed")
    protected ProcessFailedType processFailed;
    @XmlAttribute(name = "creationTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;

    /**
     * Recupera il valore della proprietà processAccepted.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProcessAccepted() {
        return processAccepted;
    }

    /**
     * Imposta il valore della proprietà processAccepted.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProcessAccepted(String value) {
        this.processAccepted = value;
    }

    public boolean isSetProcessAccepted() {
        return (this.processAccepted != null);
    }

    /**
     * Recupera il valore della proprietà processStarted.
     *
     * @return possible object is
     * {@link ProcessStartedType }
     */
    public ProcessStartedType getProcessStarted() {
        return processStarted;
    }

    /**
     * Imposta il valore della proprietà processStarted.
     *
     * @param value allowed object is
     *              {@link ProcessStartedType }
     */
    public void setProcessStarted(ProcessStartedType value) {
        this.processStarted = value;
    }

    public boolean isSetProcessStarted() {
        return (this.processStarted != null);
    }

    /**
     * Recupera il valore della proprietà processPaused.
     *
     * @return possible object is
     * {@link ProcessStartedType }
     */
    public ProcessStartedType getProcessPaused() {
        return processPaused;
    }

    /**
     * Imposta il valore della proprietà processPaused.
     *
     * @param value allowed object is
     *              {@link ProcessStartedType }
     */
    public void setProcessPaused(ProcessStartedType value) {
        this.processPaused = value;
    }

    public boolean isSetProcessPaused() {
        return (this.processPaused != null);
    }

    /**
     * Recupera il valore della proprietà processSucceeded.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProcessSucceeded() {
        return processSucceeded;
    }

    /**
     * Imposta il valore della proprietà processSucceeded.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProcessSucceeded(String value) {
        this.processSucceeded = value;
    }

    public boolean isSetProcessSucceeded() {
        return (this.processSucceeded != null);
    }

    /**
     * Recupera il valore della proprietà processFailed.
     *
     * @return possible object is
     * {@link ProcessFailedType }
     */
    public ProcessFailedType getProcessFailed() {
        return processFailed;
    }

    /**
     * Imposta il valore della proprietà processFailed.
     *
     * @param value allowed object is
     *              {@link ProcessFailedType }
     */
    public void setProcessFailed(ProcessFailedType value) {
        this.processFailed = value;
    }

    public boolean isSetProcessFailed() {
        return (this.processFailed != null);
    }

    /**
     * Recupera il valore della proprietà creationTime.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCreationTime() {
        return creationTime;
    }

    /**
     * Imposta il valore della proprietà creationTime.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCreationTime(XMLGregorianCalendar value) {
        this.creationTime = value;
    }

    public boolean isSetCreationTime() {
        return (this.creationTime != null);
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
            String theProcessAccepted;
            theProcessAccepted = this.getProcessAccepted();
            strategy.appendField(locator, this, "processAccepted", buffer, theProcessAccepted, this.isSetProcessAccepted());
        }
        {
            ProcessStartedType theProcessStarted;
            theProcessStarted = this.getProcessStarted();
            strategy.appendField(locator, this, "processStarted", buffer, theProcessStarted, this.isSetProcessStarted());
        }
        {
            ProcessStartedType theProcessPaused;
            theProcessPaused = this.getProcessPaused();
            strategy.appendField(locator, this, "processPaused", buffer, theProcessPaused, this.isSetProcessPaused());
        }
        {
            String theProcessSucceeded;
            theProcessSucceeded = this.getProcessSucceeded();
            strategy.appendField(locator, this, "processSucceeded", buffer, theProcessSucceeded, this.isSetProcessSucceeded());
        }
        {
            ProcessFailedType theProcessFailed;
            theProcessFailed = this.getProcessFailed();
            strategy.appendField(locator, this, "processFailed", buffer, theProcessFailed, this.isSetProcessFailed());
        }
        {
            XMLGregorianCalendar theCreationTime;
            theCreationTime = this.getCreationTime();
            strategy.appendField(locator, this, "creationTime", buffer, theCreationTime, this.isSetCreationTime());
        }
        return buffer;
    }

}
