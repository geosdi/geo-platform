/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per anonymous complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}ResponseBaseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Process" type="{http://www.opengis.net/wps/1.0.0}ProcessBriefType"/&gt;
 *         &lt;element name="Status" type="{http://www.opengis.net/wps/1.0.0}StatusType"/&gt;
 *         &lt;element name="DataInputs" type="{http://www.opengis.net/wps/1.0.0}DataInputsType" minOccurs="0"/&gt;
 *         &lt;element name="OutputDefinitions" type="{http://www.opengis.net/wps/1.0.0}OutputDefinitionsType" minOccurs="0"/&gt;
 *         &lt;element name="ProcessOutputs" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDataType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="serviceInstance" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="statusLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
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
public class ExecuteResponse extends ResponseBaseType implements ToString2 {

    @XmlElement(name = "Process", required = true)
    protected ProcessBriefType process;
    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    @XmlElement(name = "DataInputs")
    protected DataInputsType dataInputs;
    @XmlElement(name = "OutputDefinitions")
    protected OutputDefinitionsType outputDefinitions;
    @XmlElement(name = "ProcessOutputs")
    protected ExecuteResponse.ProcessOutputs processOutputs;
    @XmlAttribute(name = "serviceInstance", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String serviceInstance;
    @XmlAttribute(name = "statusLocation")
    @XmlSchemaType(name = "anyURI")
    protected String statusLocation;

    /**
     * Recupera il valore della proprietà process.
     *
     * @return possible object is
     * {@link ProcessBriefType }
     */
    public ProcessBriefType getProcess() {
        return process;
    }

    /**
     * Imposta il valore della proprietà process.
     *
     * @param value allowed object is
     *              {@link ProcessBriefType }
     */
    public void setProcess(ProcessBriefType value) {
        this.process = value;
    }

    public boolean isSetProcess() {
        return (this.process != null);
    }

    /**
     * Recupera il valore della proprietà status.
     *
     * @return possible object is
     * {@link StatusType }
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Imposta il valore della proprietà status.
     *
     * @param value allowed object is
     *              {@link StatusType }
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    public boolean isSetStatus() {
        return (this.status != null);
    }

    /**
     * Recupera il valore della proprietà dataInputs.
     *
     * @return possible object is
     * {@link DataInputsType }
     */
    public DataInputsType getDataInputs() {
        return dataInputs;
    }

    /**
     * Imposta il valore della proprietà dataInputs.
     *
     * @param value allowed object is
     *              {@link DataInputsType }
     */
    public void setDataInputs(DataInputsType value) {
        this.dataInputs = value;
    }

    public boolean isSetDataInputs() {
        return (this.dataInputs != null);
    }

    /**
     * Recupera il valore della proprietà outputDefinitions.
     *
     * @return possible object is
     * {@link OutputDefinitionsType }
     */
    public OutputDefinitionsType getOutputDefinitions() {
        return outputDefinitions;
    }

    /**
     * Imposta il valore della proprietà outputDefinitions.
     *
     * @param value allowed object is
     *              {@link OutputDefinitionsType }
     */
    public void setOutputDefinitions(OutputDefinitionsType value) {
        this.outputDefinitions = value;
    }

    public boolean isSetOutputDefinitions() {
        return (this.outputDefinitions != null);
    }

    /**
     * Recupera il valore della proprietà processOutputs.
     *
     * @return possible object is
     * {@link ExecuteResponse.ProcessOutputs }
     */
    public ExecuteResponse.ProcessOutputs getProcessOutputs() {
        return processOutputs;
    }

    /**
     * Imposta il valore della proprietà processOutputs.
     *
     * @param value allowed object is
     *              {@link ExecuteResponse.ProcessOutputs }
     */
    public void setProcessOutputs(ExecuteResponse.ProcessOutputs value) {
        this.processOutputs = value;
    }

    public boolean isSetProcessOutputs() {
        return (this.processOutputs != null);
    }

    /**
     * Recupera il valore della proprietà serviceInstance.
     *
     * @return possible object is
     * {@link String }
     */
    public String getServiceInstance() {
        return serviceInstance;
    }

    /**
     * Imposta il valore della proprietà serviceInstance.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setServiceInstance(String value) {
        this.serviceInstance = value;
    }

    public boolean isSetServiceInstance() {
        return (this.serviceInstance != null);
    }

    /**
     * Recupera il valore della proprietà statusLocation.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStatusLocation() {
        return statusLocation;
    }

    /**
     * Imposta il valore della proprietà statusLocation.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStatusLocation(String value) {
        this.statusLocation = value;
    }

    public boolean isSetStatusLocation() {
        return (this.statusLocation != null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
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
     * <p>Classe Java per anonymous complex type.
     * <p>
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * <p>
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDataType" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "output"
    })
    public static class ProcessOutputs implements ToString2 {

        @XmlElement(name = "Output", required = true)
        protected List<OutputDataType> output;

        /**
         * Gets the value of the output property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the output property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOutput().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OutputDataType }
         */
        public List<OutputDataType> getOutput() {
            if (output == null) {
                output = new ArrayList<OutputDataType>();
            }
            return this.output;
        }

        public boolean isSetOutput() {
            return ((this.output != null) && (!this.output.isEmpty()));
        }

        public void unsetOutput() {
            this.output = null;
        }

        public String toString() {
            final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
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
                List<OutputDataType> theOutput;
                theOutput = (this.isSetOutput() ? this.getOutput() : null);
                strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
            }
            return buffer;
        }

        public void setOutput(List<OutputDataType> value) {
            this.output = null;
            if (value != null) {
                List<OutputDataType> draftl = this.getOutput();
                draftl.addAll(value);
            }
        }

    }

}
