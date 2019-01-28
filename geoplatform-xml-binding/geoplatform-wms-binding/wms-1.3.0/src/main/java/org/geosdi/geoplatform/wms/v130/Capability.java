//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Request"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Exception"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}_ExtendedCapabilities" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Layer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "exception",
    "extendedCapabilities",
    "layer"
})
@XmlRootElement(name = "Capability")
public class Capability implements ToString2
{

    @XmlElement(name = "Request", required = true)
    protected Request request;
    @XmlElement(name = "Exception", required = true)
    protected Exception exception;
    @XmlElement(name = "_ExtendedCapabilities")
    protected List<Object> extendedCapabilities;
    @XmlElement(name = "Layer")
    protected Layer layer;

    /**
     * Recupera il valore della proprietà request.
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Imposta il valore della proprietà request.
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setRequest(Request value) {
        this.request = value;
    }

    /**
     * Recupera il valore della proprietà exception.
     * 
     * @return
     *     possible object is
     *     {@link Exception }
     *     
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Imposta il valore della proprietà exception.
     * 
     * @param value
     *     allowed object is
     *     {@link Exception }
     *     
     */
    public void setException(Exception value) {
        this.exception = value;
    }

    /**
     * Gets the value of the extendedCapabilities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extendedCapabilities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtendedCapabilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getExtendedCapabilities() {
        if (extendedCapabilities == null) {
            extendedCapabilities = new ArrayList<Object>();
        }
        return this.extendedCapabilities;
    }

    /**
     * Recupera il valore della proprietà layer.
     * 
     * @return
     *     possible object is
     *     {@link Layer }
     *     
     */
    public Layer getLayer() {
        return layer;
    }

    /**
     * Imposta il valore della proprietà layer.
     * 
     * @param value
     *     allowed object is
     *     {@link Layer }
     *     
     */
    public void setLayer(Layer value) {
        this.layer = value;
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
            Request theRequest;
            theRequest = this.getRequest();
            strategy.appendField(locator, this, "request", buffer, theRequest, (this.request!= null));
        }
        {
            Exception theException;
            theException = this.getException();
            strategy.appendField(locator, this, "exception", buffer, theException, (this.exception!= null));
        }
        {
            List<Object> theExtendedCapabilities;
            theExtendedCapabilities = (((this.extendedCapabilities!= null)&&(!this.extendedCapabilities.isEmpty()))?this.getExtendedCapabilities():null);
            strategy.appendField(locator, this, "extendedCapabilities", buffer, theExtendedCapabilities, ((this.extendedCapabilities!= null)&&(!this.extendedCapabilities.isEmpty())));
        }
        {
            Layer theLayer;
            theLayer = this.getLayer();
            strategy.appendField(locator, this, "layer", buffer, theLayer, (this.layer!= null));
        }
        return buffer;
    }

    public void setExtendedCapabilities(List<Object> value) {
        this.extendedCapabilities = null;
        if (value!= null) {
            List<Object> draftl = this.getExtendedCapabilities();
            draftl.addAll(value);
        }
    }

}
