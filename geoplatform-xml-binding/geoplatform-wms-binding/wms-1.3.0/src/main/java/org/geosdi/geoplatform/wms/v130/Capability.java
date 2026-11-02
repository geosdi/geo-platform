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
// Generato il: 2026.11.02 alle 02:51:45 PM CET
//

package org.geosdi.geoplatform.wms.v130;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/wms}Request"/>
 *         <element ref="{http://www.opengis.net/wms}Exception"/>
 *         <element ref="{http://www.opengis.net/wms}_ExtendedCapabilities" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}Layer" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
public class Capability implements ToString
{

    /**
     * Available WMS Operations are listed in a Request element.
     * 
     */
    @XmlElement(name = "Request", required = true)
    protected Request request;
    /**
     * An Exception element indicates which error-reporting formats are
     *         supported.
     * 
     */
    @XmlElement(name = "Exception", required = true)
    protected Exception exception;
    /**
     * Individual service providers may use this element to report extended
     *         capabilities.
     * 
     */
    @XmlElement(name = "_ExtendedCapabilities")
    protected List<Object> extendedCapabilities;
    /**
     * Nested list of zero or more map Layers offered by this server.
     * 
     */
    @XmlElement(name = "Layer")
    protected Layer layer;

    /**
     * Available WMS Operations are listed in a Request element.
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
     * @see #getRequest()
     */
    public void setRequest(Request value) {
        this.request = value;
    }

    /**
     * An Exception element indicates which error-reporting formats are
     *         supported.
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
     * @see #getException()
     */
    public void setException(Exception value) {
        this.exception = value;
    }

    /**
     * Individual service providers may use this element to report extended
     *         capabilities.
     * 
     * Gets the value of the extendedCapabilities property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the extendedCapabilities property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getExtendedCapabilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * </p>
     * 
     * 
     * @return
     *     The value of the extendedCapabilities property.
     */
    public List<Object> getExtendedCapabilities() {
        if (extendedCapabilities == null) {
            extendedCapabilities = new ArrayList<>();
        }
        return this.extendedCapabilities;
    }

    /**
     * Nested list of zero or more map Layers offered by this server.
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
     * @see #getLayer()
     */
    public void setLayer(Layer value) {
        this.layer = value;
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
