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

package org.geosdi.geoplatform.xml.wps.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.ows.v110.CodeType;
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
 *     <extension base="{http://www.opengis.net/wps/1.0.0}RequestBaseType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows/1.1}Identifier"/>
 *         <element name="DataInputs" type="{http://www.opengis.net/wps/1.0.0}DataInputsType" minOccurs="0"/>
 *         <element name="ResponseForm" type="{http://www.opengis.net/wps/1.0.0}ResponseFormType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "identifier",
    "dataInputs",
    "responseForm"
})
@XmlRootElement(name = "Execute")
public class Execute
    extends RequestBaseType
    implements ToString
{

    /**
     * Identifier of the Process to be executed. This Process identifier shall be as listed in the ProcessOfferings section of the WPS Capabilities document.
     * 
     */
    @XmlElement(name = "Identifier", namespace = "http://www.opengis.net/ows/1.1", required = true)
    protected CodeType identifier;
    /**
     * List of input (or parameter) values provided to the process, including each of the Inputs needed to execute the process. It is possible to have no inputs provided only when all the inputs are predetermined fixed resources. In all other cases, at least one input is required.
     * 
     */
    @XmlElement(name = "DataInputs")
    protected DataInputsType dataInputs;
    /**
     * Defines the response type of the WPS, either raw data or XML document.  If absent, the response shall be a response document which includes all outputs encoded in the response.
     * 
     */
    @XmlElement(name = "ResponseForm")
    protected ResponseFormType responseForm;

    /**
     * Identifier of the Process to be executed. This Process identifier shall be as listed in the ProcessOfferings section of the WPS Capabilities document.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getIdentifier() {
        return identifier;
    }

    /**
     * Imposta il valore della proprietà identifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getIdentifier()
     */
    public void setIdentifier(CodeType value) {
        this.identifier = value;
    }

    public boolean isSetIdentifier() {
        return (this.identifier!= null);
    }

    /**
     * List of input (or parameter) values provided to the process, including each of the Inputs needed to execute the process. It is possible to have no inputs provided only when all the inputs are predetermined fixed resources. In all other cases, at least one input is required.
     * 
     * @return
     *     possible object is
     *     {@link DataInputsType }
     *     
     */
    public DataInputsType getDataInputs() {
        return dataInputs;
    }

    /**
     * Imposta il valore della proprietà dataInputs.
     * 
     * @param value
     *     allowed object is
     *     {@link DataInputsType }
     *     
     * @see #getDataInputs()
     */
    public void setDataInputs(DataInputsType value) {
        this.dataInputs = value;
    }

    public boolean isSetDataInputs() {
        return (this.dataInputs!= null);
    }

    /**
     * Defines the response type of the WPS, either raw data or XML document.  If absent, the response shall be a response document which includes all outputs encoded in the response.
     * 
     * @return
     *     possible object is
     *     {@link ResponseFormType }
     *     
     */
    public ResponseFormType getResponseForm() {
        return responseForm;
    }

    /**
     * Imposta il valore della proprietà responseForm.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseFormType }
     *     
     * @see #getResponseForm()
     */
    public void setResponseForm(ResponseFormType value) {
        this.responseForm = value;
    }

    public boolean isSetResponseForm() {
        return (this.responseForm!= null);
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
            CodeType theIdentifier;
            theIdentifier = this.getIdentifier();
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, this.isSetIdentifier());
        }
        {
            DataInputsType theDataInputs;
            theDataInputs = this.getDataInputs();
            strategy.appendField(locator, this, "dataInputs", buffer, theDataInputs, this.isSetDataInputs());
        }
        {
            ResponseFormType theResponseForm;
            theResponseForm = this.getResponseForm();
            strategy.appendField(locator, this, "responseForm", buffer, theResponseForm, this.isSetResponseForm());
        }
        return buffer;
    }

}
