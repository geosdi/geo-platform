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
 * Full description of a process.
 * 
 * <p>Classe Java per ProcessDescriptionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ProcessDescriptionType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/wps/1.0.0}ProcessBriefType">
 *       <sequence>
 *         <element name="DataInputs" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="Input" type="{http://www.opengis.net/wps/1.0.0}InputDescriptionType" maxOccurs="unbounded"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="ProcessOutputs">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDescriptionType" maxOccurs="unbounded"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="storeSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       <attribute name="statusSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessDescriptionType", propOrder = {
    "dataInputs",
    "processOutputs"
})
public class ProcessDescriptionType
    extends ProcessBriefType
    implements ToString
{

    /**
     * List of the inputs to this process. In almost all cases, at least one process input is required. However, no process inputs may be identified when all the inputs are predetermined fixed resources.  In this case, those resources shall be identified in the ows:Abstract element that describes the process.
     * 
     */
    @XmlElement(name = "DataInputs", namespace = "")
    protected ProcessDescriptionType.DataInputs dataInputs;
    /**
     * List of outputs which will or can result from executing the process.
     * 
     */
    @XmlElement(name = "ProcessOutputs", namespace = "", required = true)
    protected ProcessDescriptionType.ProcessOutputs processOutputs;
    /**
     * Indicates if ComplexData outputs from this process can be stored by the WPS server as web-accessible resources. If "storeSupported" is "true", the Execute operation request may include "asReference" equals "true" for any complex output, directing that the output of the process be stored so that the client can retrieve it as required. By default for this process, storage is not supported and all outputs are returned encoded in the Execute response.
     * 
     */
    @XmlAttribute(name = "storeSupported")
    protected Boolean storeSupported;
    /**
     * Indicates if ongoing status information can be provided for this process.  If "true", the Status element of the stored Execute response document shall be kept up to date.  If "false" then the Status element shall not be updated until processing is complete. By default, status information is not provided for this process.
     * 
     */
    @XmlAttribute(name = "statusSupported")
    protected Boolean statusSupported;

    /**
     * List of the inputs to this process. In almost all cases, at least one process input is required. However, no process inputs may be identified when all the inputs are predetermined fixed resources.  In this case, those resources shall be identified in the ows:Abstract element that describes the process.
     * 
     * @return
     *     possible object is
     *     {@link ProcessDescriptionType.DataInputs }
     *     
     */
    public ProcessDescriptionType.DataInputs getDataInputs() {
        return dataInputs;
    }

    /**
     * Imposta il valore della proprietà dataInputs.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessDescriptionType.DataInputs }
     *     
     * @see #getDataInputs()
     */
    public void setDataInputs(ProcessDescriptionType.DataInputs value) {
        this.dataInputs = value;
    }

    public boolean isSetDataInputs() {
        return (this.dataInputs!= null);
    }

    /**
     * List of outputs which will or can result from executing the process.
     * 
     * @return
     *     possible object is
     *     {@link ProcessDescriptionType.ProcessOutputs }
     *     
     */
    public ProcessDescriptionType.ProcessOutputs getProcessOutputs() {
        return processOutputs;
    }

    /**
     * Imposta il valore della proprietà processOutputs.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessDescriptionType.ProcessOutputs }
     *     
     * @see #getProcessOutputs()
     */
    public void setProcessOutputs(ProcessDescriptionType.ProcessOutputs value) {
        this.processOutputs = value;
    }

    public boolean isSetProcessOutputs() {
        return (this.processOutputs!= null);
    }

    /**
     * Indicates if ComplexData outputs from this process can be stored by the WPS server as web-accessible resources. If "storeSupported" is "true", the Execute operation request may include "asReference" equals "true" for any complex output, directing that the output of the process be stored so that the client can retrieve it as required. By default for this process, storage is not supported and all outputs are returned encoded in the Execute response.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isStoreSupported() {
        if (storeSupported == null) {
            return false;
        } else {
            return storeSupported;
        }
    }

    /**
     * Imposta il valore della proprietà storeSupported.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isStoreSupported()
     */
    public void setStoreSupported(boolean value) {
        this.storeSupported = value;
    }

    public boolean isSetStoreSupported() {
        return (this.storeSupported!= null);
    }

    public void unsetStoreSupported() {
        this.storeSupported = null;
    }

    /**
     * Indicates if ongoing status information can be provided for this process.  If "true", the Status element of the stored Execute response document shall be kept up to date.  If "false" then the Status element shall not be updated until processing is complete. By default, status information is not provided for this process.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isStatusSupported() {
        if (statusSupported == null) {
            return false;
        } else {
            return statusSupported;
        }
    }

    /**
     * Imposta il valore della proprietà statusSupported.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isStatusSupported()
     */
    public void setStatusSupported(boolean value) {
        this.statusSupported = value;
    }

    public boolean isSetStatusSupported() {
        return (this.statusSupported!= null);
    }

    public void unsetStatusSupported() {
        this.statusSupported = null;
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
            ProcessDescriptionType.DataInputs theDataInputs;
            theDataInputs = this.getDataInputs();
            strategy.appendField(locator, this, "dataInputs", buffer, theDataInputs, this.isSetDataInputs());
        }
        {
            ProcessDescriptionType.ProcessOutputs theProcessOutputs;
            theProcessOutputs = this.getProcessOutputs();
            strategy.appendField(locator, this, "processOutputs", buffer, theProcessOutputs, this.isSetProcessOutputs());
        }
        {
            boolean theStoreSupported;
            theStoreSupported = (this.isSetStoreSupported()?this.isStoreSupported():false);
            strategy.appendField(locator, this, "storeSupported", buffer, theStoreSupported, this.isSetStoreSupported());
        }
        {
            boolean theStatusSupported;
            theStatusSupported = (this.isSetStatusSupported()?this.isStatusSupported():false);
            strategy.appendField(locator, this, "statusSupported", buffer, theStatusSupported, this.isSetStatusSupported());
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
     *         <element name="Input" type="{http://www.opengis.net/wps/1.0.0}InputDescriptionType" maxOccurs="unbounded"/>
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
        "input"
    })
    public static class DataInputs implements ToString
    {

        /**
         * Unordered list of one or more descriptions of the inputs that can be accepted by this process, including all required and optional inputs.  Where an input is optional because a default value exists, that default value must be identified in the "ows:Abstract" element for that input, except in the case of LiteralData, where the default must be indicated in the corresponding ows:DefaultValue element. Where an input is optional because it depends on the value(s) of other inputs, this must be indicated in the ows:Abstract element for that input.
         * 
         */
        @XmlElement(name = "Input", namespace = "", required = true)
        protected List<InputDescriptionType> input;

        /**
         * Unordered list of one or more descriptions of the inputs that can be accepted by this process, including all required and optional inputs.  Where an input is optional because a default value exists, that default value must be identified in the "ows:Abstract" element for that input, except in the case of LiteralData, where the default must be indicated in the corresponding ows:DefaultValue element. Where an input is optional because it depends on the value(s) of other inputs, this must be indicated in the ows:Abstract element for that input.
         * 
         * Gets the value of the input property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore, any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the input property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getInput().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InputDescriptionType }
         * </p>
         * 
         * 
         * @return
         *     The value of the input property.
         */
        public List<InputDescriptionType> getInput() {
            if (input == null) {
                input = new ArrayList<>();
            }
            return this.input;
        }

        public boolean isSetInput() {
            return ((this.input!= null)&&(!this.input.isEmpty()));
        }

        public void unsetInput() {
            this.input = null;
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
                List<InputDescriptionType> theInput;
                theInput = (this.isSetInput()?this.getInput():null);
                strategy.appendField(locator, this, "input", buffer, theInput, this.isSetInput());
            }
            return buffer;
        }

        public void setInput(List<InputDescriptionType> value) {
            this.input = null;
            if (value!= null) {
                List<InputDescriptionType> draftl = this.getInput();
                draftl.addAll(value);
            }
        }

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
     *         <element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDescriptionType" maxOccurs="unbounded"/>
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
         * Unordered list of one or more descriptions of all the outputs that can result from executing this process. At least one output is required from each process.
         * 
         */
        @XmlElement(name = "Output", namespace = "", required = true)
        protected List<OutputDescriptionType> output;

        /**
         * Unordered list of one or more descriptions of all the outputs that can result from executing this process. At least one output is required from each process.
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
         * {@link OutputDescriptionType }
         * </p>
         * 
         * 
         * @return
         *     The value of the output property.
         */
        public List<OutputDescriptionType> getOutput() {
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
                List<OutputDescriptionType> theOutput;
                theOutput = (this.isSetOutput()?this.getOutput():null);
                strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
            }
            return buffer;
        }

        public void setOutput(List<OutputDescriptionType> value) {
            this.output = null;
            if (value!= null) {
                List<OutputDescriptionType> draftl = this.getOutput();
                draftl.addAll(value);
            }
        }

    }

}
