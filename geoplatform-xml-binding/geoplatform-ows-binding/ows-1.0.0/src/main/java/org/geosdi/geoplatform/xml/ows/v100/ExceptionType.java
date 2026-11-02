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
// Generato il: 2026.11.02 alle 03:48:08 PM CET
//

package org.geosdi.geoplatform.xml.ows.v100;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * An Exception element describes one detected error that a server chooses to convey to the client.
 * 
 * <p>Classe Java per ExceptionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ExceptionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="ExceptionText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="exceptionCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="locator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionType", propOrder = {
    "exceptionText"
})
public class ExceptionType implements ToString
{

    /**
     * Ordered sequence of text strings that describe this specific exception or error. The contents of these strings are left open to definition by each server implementation. A server is strongly encouraged to include at least one ExceptionText value, to provide more information about the detected error than provided by the exceptionCode. When included, multiple ExceptionText values shall provide hierarchical information about one detected error, with the most significant information listed first.
     * 
     */
    @XmlElement(name = "ExceptionText")
    protected List<String> exceptionText;
    /**
     * A code representing the type of this exception, which shall be selected from a set of exceptionCode values specified for the specific service operation and server.
     * 
     */
    @XmlAttribute(name = "exceptionCode", required = true)
    protected String exceptionCode;
    /**
     * When included, this locator shall indicate to the client where an exception was encountered in servicing the client's operation request. This locator should be included whenever meaningful information can be provided by the server. The contents of this locator will depend on the specific exceptionCode and OWS service, and shall be specified in the OWS Implementation Specification.
     * 
     */
    @XmlAttribute(name = "locator")
    protected String locator;

    /**
     * Ordered sequence of text strings that describe this specific exception or error. The contents of these strings are left open to definition by each server implementation. A server is strongly encouraged to include at least one ExceptionText value, to provide more information about the detected error than provided by the exceptionCode. When included, multiple ExceptionText values shall provide hierarchical information about one detected error, with the most significant information listed first.
     * 
     * Gets the value of the exceptionText property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the exceptionText property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getExceptionText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the exceptionText property.
     */
    public List<String> getExceptionText() {
        if (exceptionText == null) {
            exceptionText = new ArrayList<>();
        }
        return this.exceptionText;
    }

    /**
     * A code representing the type of this exception, which shall be selected from a set of exceptionCode values specified for the specific service operation and server.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Imposta il valore della proprietà exceptionCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getExceptionCode()
     */
    public void setExceptionCode(String value) {
        this.exceptionCode = value;
    }

    /**
     * When included, this locator shall indicate to the client where an exception was encountered in servicing the client's operation request. This locator should be included whenever meaningful information can be provided by the server. The contents of this locator will depend on the specific exceptionCode and OWS service, and shall be specified in the OWS Implementation Specification.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocator() {
        return locator;
    }

    /**
     * Imposta il valore della proprietà locator.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getLocator()
     */
    public void setLocator(String value) {
        this.locator = value;
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
            List<String> theExceptionText;
            theExceptionText = (((this.exceptionText!= null)&&(!this.exceptionText.isEmpty()))?this.getExceptionText():null);
            strategy.appendField(locator, this, "exceptionText", buffer, theExceptionText, ((this.exceptionText!= null)&&(!this.exceptionText.isEmpty())));
        }
        {
            String theExceptionCode;
            theExceptionCode = this.getExceptionCode();
            strategy.appendField(locator, this, "exceptionCode", buffer, theExceptionCode, (this.exceptionCode!= null));
        }
        {
            String theLocator;
            theLocator = this.getLocator();
            strategy.appendField(locator, this, "locator", buffer, theLocator, (this.locator!= null));
        }
        return buffer;
    }

    public void setExceptionText(List<String> value) {
        this.exceptionText = null;
        if (value!= null) {
            List<String> draftl = this.getExceptionText();
            draftl.addAll(value);
        }
    }

}
