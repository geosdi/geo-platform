/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "exception",
    "vendorSpecificCapabilities",
    "userDefinedSymbolization",
    "layer"
})
@XmlRootElement(name = "Capability")
public class Capability implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Request", required = true)
    protected Request request;
    @XmlElement(name = "Exception", required = true)
    protected Exception exception;
    @XmlElement(name = "VendorSpecificCapabilities")
    protected VendorSpecificCapabilities vendorSpecificCapabilities;
    @XmlElement(name = "UserDefinedSymbolization")
    protected UserDefinedSymbolization userDefinedSymbolization;
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
     * Recupera il valore della proprietà vendorSpecificCapabilities.
     * 
     * @return
     *     possible object is
     *     {@link VendorSpecificCapabilities }
     *     
     */
    public VendorSpecificCapabilities getVendorSpecificCapabilities() {
        return vendorSpecificCapabilities;
    }

    /**
     * Imposta il valore della proprietà vendorSpecificCapabilities.
     * 
     * @param value
     *     allowed object is
     *     {@link VendorSpecificCapabilities }
     *     
     */
    public void setVendorSpecificCapabilities(VendorSpecificCapabilities value) {
        this.vendorSpecificCapabilities = value;
    }

    /**
     * Recupera il valore della proprietà userDefinedSymbolization.
     * 
     * @return
     *     possible object is
     *     {@link UserDefinedSymbolization }
     *     
     */
    public UserDefinedSymbolization getUserDefinedSymbolization() {
        return userDefinedSymbolization;
    }

    /**
     * Imposta il valore della proprietà userDefinedSymbolization.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDefinedSymbolization }
     *     
     */
    public void setUserDefinedSymbolization(UserDefinedSymbolization value) {
        this.userDefinedSymbolization = value;
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
            VendorSpecificCapabilities theVendorSpecificCapabilities;
            theVendorSpecificCapabilities = this.getVendorSpecificCapabilities();
            strategy.appendField(locator, this, "vendorSpecificCapabilities", buffer, theVendorSpecificCapabilities, (this.vendorSpecificCapabilities!= null));
        }
        {
            UserDefinedSymbolization theUserDefinedSymbolization;
            theUserDefinedSymbolization = this.getUserDefinedSymbolization();
            strategy.appendField(locator, this, "userDefinedSymbolization", buffer, theUserDefinedSymbolization, (this.userDefinedSymbolization!= null));
        }
        {
            Layer theLayer;
            theLayer = this.getLayer();
            strategy.appendField(locator, this, "layer", buffer, theLayer, (this.layer!= null));
        }
        return buffer;
    }

}
