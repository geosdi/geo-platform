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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 *     <extension base="{http://www.opengis.net/wps/1.0.0}ResponseBaseType">
 *       <sequence>
 *         <element name="Process" type="{http://www.opengis.net/wps/1.0.0}ProcessBriefType"/>
 *         <element name="Status" type="{http://www.opengis.net/wps/1.0.0}StatusType"/>
 *         <element name="DataInputs" type="{http://www.opengis.net/wps/1.0.0}DataInputsType" minOccurs="0"/>
 *         <element name="OutputDefinitions" type="{http://www.opengis.net/wps/1.0.0}OutputDefinitionsType" minOccurs="0"/>
 *         <element name="ProcessOutputs" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDataType" maxOccurs="unbounded"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="serviceInstance" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       <attribute name="statusLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "process",
    "status",
    "dataInputs",
    "outputDefinitions",
    "processOutputs"
})
@XmlRootElement(name = "ExecuteResponse")
public class ExecuteResponse
    extends ResponseBaseType
    implements ToString
{

    /**
     * Process description from the ProcessOfferings section of the GetCapabilities response.
     * 
     */
    @XmlElement(name = "Process", required = true)
    protected ProcessBriefType process;
    /**
     * Execution status of this process.
     * 
     */
    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    /**
     * Inputs that were provided as part of the execute request. This element shall be omitted unless the lineage attribute of the execute request is set to "true".
     * 
     */
    @XmlElement(name = "DataInputs")
    protected DataInputsType dataInputs;
    /**
     * Complete list of Output data types that were requested as part of the Execute request. This element shall be omitted unless the lineage attribute of the execute request is set to "true".
     * 
     */
    @XmlElement(name = "OutputDefinitions")
    protected OutputDefinitionsType outputDefinitions;
    /**
     * List of values of the Process output parameters. Normally there would be at least one output when the process has completed successfully. If the process has not finished executing, the implementer can choose to include whatever final results are ready at the time the Execute response is provided. If the reference locations of outputs are known in advance, these URLs may be provided before they are populated.
     * 
     */
    @XmlElement(name = "ProcessOutputs")
    protected ExecuteResponse.ProcessOutputs processOutputs;
    /**
     * This attribute shall contain the GetCapabilities URL of the WPS service which was invoked
     * 
     */
    @XmlAttribute(name = "serviceInstance", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String serviceInstance;
    /**
     * The URL referencing the location from which the ExecuteResponse can be retrieved. If "status" is "true" in the Execute request, the ExecuteResponse should also be found here as soon as the process returns the initial response to the client. It should persist at this location as long as the outputs are accessible from the server. The outputs may be stored for as long as the implementer of the server decides. If the process takes a long time, this URL can be repopulated on an ongoing basis in order to keep the client updated on progress. Before the process has succeeded, the ExecuteResponse contains information about the status of the process, including whether or not processing has started, and the percentage completed. It may also optionally contain the inputs and any ProcessStartedType interim results. When the process has succeeded, the ExecuteResponse found at this URL shall contain the output values or references to them.
     * 
     */
    @XmlAttribute(name = "statusLocation")
    @XmlSchemaType(name = "anyURI")
    protected String statusLocation;

    /**
     * Process description from the ProcessOfferings section of the GetCapabilities response.
     * 
     * @return
     *     possible object is
     *     {@link ProcessBriefType }
     *     
     */
    public ProcessBriefType getProcess() {
        return process;
    }

    /**
     * Imposta il valore della proprietà process.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessBriefType }
     *     
     * @see #getProcess()
     */
    public void setProcess(ProcessBriefType value) {
        this.process = value;
    }

    public boolean isSetProcess() {
        return (this.process!= null);
    }

    /**
     * Execution status of this process.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Imposta il valore della proprietà status.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     * @see #getStatus()
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    public boolean isSetStatus() {
        return (this.status!= null);
    }

    /**
     * Inputs that were provided as part of the execute request. This element shall be omitted unless the lineage attribute of the execute request is set to "true".
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
     * Complete list of Output data types that were requested as part of the Execute request. This element shall be omitted unless the lineage attribute of the execute request is set to "true".
     * 
     * @return
     *     possible object is
     *     {@link OutputDefinitionsType }
     *     
     */
    public OutputDefinitionsType getOutputDefinitions() {
        return outputDefinitions;
    }

    /**
     * Imposta il valore della proprietà outputDefinitions.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputDefinitionsType }
     *     
     * @see #getOutputDefinitions()
     */
    public void setOutputDefinitions(OutputDefinitionsType value) {
        this.outputDefinitions = value;
    }

    public boolean isSetOutputDefinitions() {
        return (this.outputDefinitions!= null);
    }

    /**
     * List of values of the Process output parameters. Normally there would be at least one output when the process has completed successfully. If the process has not finished executing, the implementer can choose to include whatever final results are ready at the time the Execute response is provided. If the reference locations of outputs are known in advance, these URLs may be provided before they are populated.
     * 
     * @return
     *     possible object is
     *     {@link ExecuteResponse.ProcessOutputs }
     *     
     */
    public ExecuteResponse.ProcessOutputs getProcessOutputs() {
        return processOutputs;
    }

    /**
     * Imposta il valore della proprietà processOutputs.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecuteResponse.ProcessOutputs }
     *     
     * @see #getProcessOutputs()
     */
    public void setProcessOutputs(ExecuteResponse.ProcessOutputs value) {
        this.processOutputs = value;
    }

    public boolean isSetProcessOutputs() {
        return (this.processOutputs!= null);
    }

    /**
     * This attribute shall contain the GetCapabilities URL of the WPS service which was invoked
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceInstance() {
        return serviceInstance;
    }

    /**
     * Imposta il valore della proprietà serviceInstance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getServiceInstance()
     */
    public void setServiceInstance(String value) {
        this.serviceInstance = value;
    }

    public boolean isSetServiceInstance() {
        return (this.serviceInstance!= null);
    }

    /**
     * The URL referencing the location from which the ExecuteResponse can be retrieved. If "status" is "true" in the Execute request, the ExecuteResponse should also be found here as soon as the process returns the initial response to the client. It should persist at this location as long as the outputs are accessible from the server. The outputs may be stored for as long as the implementer of the server decides. If the process takes a long time, this URL can be repopulated on an ongoing basis in order to keep the client updated on progress. Before the process has succeeded, the ExecuteResponse contains information about the status of the process, including whether or not processing has started, and the percentage completed. It may also optionally contain the inputs and any ProcessStartedType interim results. When the process has succeeded, the ExecuteResponse found at this URL shall contain the output values or references to them.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusLocation() {
        return statusLocation;
    }

    /**
     * Imposta il valore della proprietà statusLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getStatusLocation()
     */
    public void setStatusLocation(String value) {
        this.statusLocation = value;
    }

    public boolean isSetStatusLocation() {
        return (this.statusLocation!= null);
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
            ProcessBriefType theProcess;
            theProcess = this.getProcess();
            strategy.appendField(locator, this, "process", buffer, theProcess, this.isSetProcess());
        }
        {
            StatusType theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus, this.isSetStatus());
        }
        {
            DataInputsType theDataInputs;
            theDataInputs = this.getDataInputs();
            strategy.appendField(locator, this, "dataInputs", buffer, theDataInputs, this.isSetDataInputs());
        }
        {
            OutputDefinitionsType theOutputDefinitions;
            theOutputDefinitions = this.getOutputDefinitions();
            strategy.appendField(locator, this, "outputDefinitions", buffer, theOutputDefinitions, this.isSetOutputDefinitions());
        }
        {
            ExecuteResponse.ProcessOutputs theProcessOutputs;
            theProcessOutputs = this.getProcessOutputs();
            strategy.appendField(locator, this, "processOutputs", buffer, theProcessOutputs, this.isSetProcessOutputs());
        }
        {
            String theServiceInstance;
            theServiceInstance = this.getServiceInstance();
            strategy.appendField(locator, this, "serviceInstance", buffer, theServiceInstance, this.isSetServiceInstance());
        }
        {
            String theStatusLocation;
            theStatusLocation = this.getStatusLocation();
            strategy.appendField(locator, this, "statusLocation", buffer, theStatusLocation, this.isSetStatusLocation());
        }
        return buffer;
    }


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
     *         <element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDataType" maxOccurs="unbounded"/>
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
        "output"
    })
    public static class ProcessOutputs implements ToString
    {

        /**
         * Unordered list of values of all the outputs produced by this process. It is not necessary to include an output until the Status is ProcessSucceeded.
         * 
         */
        @XmlElement(name = "Output", required = true)
        protected List<OutputDataType> output;

        /**
         * Unordered list of values of all the outputs produced by this process. It is not necessary to include an output until the Status is ProcessSucceeded.
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
         * {@link OutputDataType }
         * </p>
         * 
         * 
         * @return
         *     The value of the output property.
         */
        public List<OutputDataType> getOutput() {
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
                List<OutputDataType> theOutput;
                theOutput = (this.isSetOutput()?this.getOutput():null);
                strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
            }
            return buffer;
        }

        public void setOutput(List<OutputDataType> value) {
            this.output = null;
            if (value!= null) {
                List<OutputDataType> draftl = this.getOutput();
                draftl.addAll(value);
            }
        }

    }

}
