/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.ows.v110;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Complete reference to a remote resource that needs to be retrieved from an OWS using an XML-encoded operation request. This element shall be used, within an InputData or Manifest element that is used for input data, when that input data needs to be retrieved from another web service using a XML-encoded OWS operation request. This element shall not be used for local payload input data or for requesting the resource from a web server using HTTP Get.
 * 
 * <p>Classe Java per ServiceReferenceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ServiceReferenceType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/ows/1.1}ReferenceType">
 *       <choice>
 *         <element name="RequestMessage" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         <element name="RequestMessageReference" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       </choice>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
    implements ToString
{

    /**
     * The XML-encoded operation request message to be sent to request this input data from another web server using HTTP Post.
     * 
     */
    @XmlElement(name = "RequestMessage")
    protected Object requestMessage;
    /**
     * Reference to the XML-encoded operation request message to be sent to request this input data from another web server using HTTP Post. The referenced message shall be attached to the same message (using the cid scheme), or be accessible using a URL.
     * 
     */
    @XmlElement(name = "RequestMessageReference")
    @XmlSchemaType(name = "anyURI")
    protected String requestMessageReference;

    /**
     * The XML-encoded operation request message to be sent to request this input data from another web server using HTTP Post.
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
     * @see #getRequestMessage()
     */
    public void setRequestMessage(Object value) {
        this.requestMessage = value;
    }

    /**
     * Reference to the XML-encoded operation request message to be sent to request this input data from another web server using HTTP Post. The referenced message shall be attached to the same message (using the cid scheme), or be accessible using a URL.
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
     * @see #getRequestMessageReference()
     */
    public void setRequestMessageReference(String value) {
        this.requestMessageReference = value;
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
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
