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
 * <p>Classe Java per ResponseDocumentType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ResponseDocumentType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Output" type="{http://www.opengis.net/wps/1.0.0}DocumentOutputDefinitionType" maxOccurs="unbounded"/>
 *       </sequence>
 *       <attribute name="storeExecuteResponse" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       <attribute name="lineage" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       <attribute name="status" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseDocumentType", propOrder = {
    "output"
})
public class ResponseDocumentType implements ToString
{

    /**
     * Unordered list of definitions of the outputs (or parameters) requested from the process. These outputs are not normally identified, unless the client is specifically requesting a limited subset of outputs, and/or is requesting output formats and/or schemas and/or encodings different from the defaults and selected from the alternatives identified in the process description, or wishes to customize the descriptive information about the output.
     * 
     */
    @XmlElement(name = "Output", required = true)
    protected List<DocumentOutputDefinitionType> output;
    /**
     * Specifies if the Execute operation response shall be returned quickly with status information, or not returned until process execution is complete. This parameter shall not be included unless the corresponding "statusSupported" parameter is included and is "true" in the ProcessDescription for this process.
     * 
     */
    @XmlAttribute(name = "storeExecuteResponse")
    protected Boolean storeExecuteResponse;
    /**
     * Indicates if the Execute operation response shall include the DataInputs and OutputDefinitions elements.  If lineage is "true" the server shall include in the execute response a complete copy of the DataInputs and OutputDefinition elements as received in the execute request.  If lineage is "false" then these elements shall be omitted from the response.
     * 
     */
    @XmlAttribute(name = "lineage")
    protected Boolean lineage;
    /**
     * Indicates if the stored execute response document shall be updated to provide ongoing reports on the status of execution.  If status is "true" and storeExecuteResponse is "true" (and the server has indicated that both storeSupported and statusSupported are "true")  then the Status element of the execute response document stored at executeResponseLocation is kept up to date by the process.  While the execute response contains ProcessAccepted, ProcessStarted, or ProcessPaused, updates shall be made to the executeResponse document until either the process completes successfully (in which case ProcessSucceeded is populated), or the process fails (in which case ProcessFailed is populated).  If status is "false" then the Status element shall not be updated until the process either completes successfully or fails.  If status="true" and storeExecuteResponse is "false" then the service shall raise an exception.
     * 
     */
    @XmlAttribute(name = "status")
    protected Boolean status;

    /**
     * Unordered list of definitions of the outputs (or parameters) requested from the process. These outputs are not normally identified, unless the client is specifically requesting a limited subset of outputs, and/or is requesting output formats and/or schemas and/or encodings different from the defaults and selected from the alternatives identified in the process description, or wishes to customize the descriptive information about the output.
     * 
     * Gets the value of the output property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the output property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getOutput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentOutputDefinitionType }
     * </p>
     * 
     * 
     * @return
     *     The value of the output property.
     */
    public List<DocumentOutputDefinitionType> getOutput() {
        if (output == null) {
            output = new ArrayList<>();
        }
        return this.output;
    }

    public boolean isSetOutput() {
        return ((this.output!= null)&&(!this.output.isEmpty()));
    }

    public void unsetOutput() {
        this.output = null;
    }

    /**
     * Specifies if the Execute operation response shall be returned quickly with status information, or not returned until process execution is complete. This parameter shall not be included unless the corresponding "statusSupported" parameter is included and is "true" in the ProcessDescription for this process.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isStoreExecuteResponse() {
        if (storeExecuteResponse == null) {
            return false;
        } else {
            return storeExecuteResponse;
        }
    }

    /**
     * Imposta il valore della proprietà storeExecuteResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isStoreExecuteResponse()
     */
    public void setStoreExecuteResponse(boolean value) {
        this.storeExecuteResponse = value;
    }

    public boolean isSetStoreExecuteResponse() {
        return (this.storeExecuteResponse!= null);
    }

    public void unsetStoreExecuteResponse() {
        this.storeExecuteResponse = null;
    }

    /**
     * Indicates if the Execute operation response shall include the DataInputs and OutputDefinitions elements.  If lineage is "true" the server shall include in the execute response a complete copy of the DataInputs and OutputDefinition elements as received in the execute request.  If lineage is "false" then these elements shall be omitted from the response.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isLineage() {
        if (lineage == null) {
            return false;
        } else {
            return lineage;
        }
    }

    /**
     * Imposta il valore della proprietà lineage.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isLineage()
     */
    public void setLineage(boolean value) {
        this.lineage = value;
    }

    public boolean isSetLineage() {
        return (this.lineage!= null);
    }

    public void unsetLineage() {
        this.lineage = null;
    }

    /**
     * Indicates if the stored execute response document shall be updated to provide ongoing reports on the status of execution.  If status is "true" and storeExecuteResponse is "true" (and the server has indicated that both storeSupported and statusSupported are "true")  then the Status element of the execute response document stored at executeResponseLocation is kept up to date by the process.  While the execute response contains ProcessAccepted, ProcessStarted, or ProcessPaused, updates shall be made to the executeResponse document until either the process completes successfully (in which case ProcessSucceeded is populated), or the process fails (in which case ProcessFailed is populated).  If status is "false" then the Status element shall not be updated until the process either completes successfully or fails.  If status="true" and storeExecuteResponse is "false" then the service shall raise an exception.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isStatus() {
        if (status == null) {
            return false;
        } else {
            return status;
        }
    }

    /**
     * Imposta il valore della proprietà status.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isStatus()
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    public boolean isSetStatus() {
        return (this.status!= null);
    }

    public void unsetStatus() {
        this.status = null;
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
            List<DocumentOutputDefinitionType> theOutput;
            theOutput = (this.isSetOutput()?this.getOutput():null);
            strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
        }
        {
            boolean theStoreExecuteResponse;
            theStoreExecuteResponse = (this.isSetStoreExecuteResponse()?this.isStoreExecuteResponse():false);
            strategy.appendField(locator, this, "storeExecuteResponse", buffer, theStoreExecuteResponse, this.isSetStoreExecuteResponse());
        }
        {
            boolean theLineage;
            theLineage = (this.isSetLineage()?this.isLineage():false);
            strategy.appendField(locator, this, "lineage", buffer, theLineage, this.isSetLineage());
        }
        {
            boolean theStatus;
            theStatus = (this.isSetStatus()?this.isStatus():false);
            strategy.appendField(locator, this, "status", buffer, theStatus, this.isSetStatus());
        }
        return buffer;
    }

    public void setOutput(List<DocumentOutputDefinitionType> value) {
        this.output = null;
        if (value!= null) {
            List<DocumentOutputDefinitionType> draftl = this.getOutput();
            draftl.addAll(value);
        }
    }

}
