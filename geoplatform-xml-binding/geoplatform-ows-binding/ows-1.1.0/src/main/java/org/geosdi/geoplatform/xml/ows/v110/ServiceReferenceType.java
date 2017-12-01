//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Complete reference to a remote resource that needs to be retrieved from an OWS using an XML-encoded operation request. This element shall be used, within an InputData or Manifest element that is used for input data, when that input data needs to be retrieved from another web service using a XML-encoded OWS operation request. This element shall not be used for local payload input data or for requesting the resource from a web server using HTTP Get. 
 * 
 * <p>Classe Java per ServiceReferenceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ServiceReferenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}ReferenceType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="RequestMessage" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="RequestMessageReference" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceReferenceType", propOrder = {
    "requestMessage",
    "requestMessageReference"
})
public class ServiceReferenceType
    extends ReferenceType
    implements ToString2
{

    @XmlElement(name = "RequestMessage")
    protected Object requestMessage;
    @XmlElement(name = "RequestMessageReference")
    @XmlSchemaType(name = "anyURI")
    protected String requestMessageReference;

    /**
     * Recupera il valore della proprietà requestMessage.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRequestMessage() {
        return requestMessage;
    }

    /**
     * Imposta il valore della proprietà requestMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRequestMessage(Object value) {
        this.requestMessage = value;
    }

    /**
     * Recupera il valore della proprietà requestMessageReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestMessageReference() {
        return requestMessageReference;
    }

    /**
     * Imposta il valore della proprietà requestMessageReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestMessageReference(String value) {
        this.requestMessageReference = value;
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
            Object theRequestMessage;
            theRequestMessage = this.getRequestMessage();
            strategy.appendField(locator, this, "requestMessage", buffer, theRequestMessage, (this.requestMessage!= null));
        }
        {
            String theRequestMessageReference;
            theRequestMessageReference = this.getRequestMessageReference();
            strategy.appendField(locator, this, "requestMessageReference", buffer, theRequestMessageReference, (this.requestMessageReference!= null));
        }
        return buffer;
    }

}
