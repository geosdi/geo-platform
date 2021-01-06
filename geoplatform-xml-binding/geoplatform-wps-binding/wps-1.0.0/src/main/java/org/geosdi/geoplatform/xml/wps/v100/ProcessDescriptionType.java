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
 * Full description of a process.
 * <p>
 * <p>Classe Java per ProcessDescriptionType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="ProcessDescriptionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}ProcessBriefType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DataInputs" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Input" type="{http://www.opengis.net/wps/1.0.0}InputDescriptionType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProcessOutputs"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDescriptionType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="storeSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="statusSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessDescriptionType", propOrder = {
        "dataInputs",
        "processOutputs"
})
public class ProcessDescriptionType extends ProcessBriefType implements ToString2 {

    @XmlElement(name = "DataInputs", namespace = "")
    protected ProcessDescriptionType.DataInputs dataInputs;
    @XmlElement(name = "ProcessOutputs", namespace = "", required = true)
    protected ProcessDescriptionType.ProcessOutputs processOutputs;
    @XmlAttribute(name = "storeSupported")
    protected Boolean storeSupported;
    @XmlAttribute(name = "statusSupported")
    protected Boolean statusSupported;

    /**
     * Recupera il valore della proprietà dataInputs.
     *
     * @return possible object is
     * {@link ProcessDescriptionType.DataInputs }
     */
    public ProcessDescriptionType.DataInputs getDataInputs() {
        return dataInputs;
    }

    /**
     * Imposta il valore della proprietà dataInputs.
     *
     * @param value allowed object is
     *              {@link ProcessDescriptionType.DataInputs }
     */
    public void setDataInputs(ProcessDescriptionType.DataInputs value) {
        this.dataInputs = value;
    }

    public boolean isSetDataInputs() {
        return (this.dataInputs != null);
    }

    /**
     * Recupera il valore della proprietà processOutputs.
     *
     * @return possible object is
     * {@link ProcessDescriptionType.ProcessOutputs }
     */
    public ProcessDescriptionType.ProcessOutputs getProcessOutputs() {
        return processOutputs;
    }

    /**
     * Imposta il valore della proprietà processOutputs.
     *
     * @param value allowed object is
     *              {@link ProcessDescriptionType.ProcessOutputs }
     */
    public void setProcessOutputs(ProcessDescriptionType.ProcessOutputs value) {
        this.processOutputs = value;
    }

    public boolean isSetProcessOutputs() {
        return (this.processOutputs != null);
    }

    /**
     * Recupera il valore della proprietà storeSupported.
     *
     * @return possible object is
     * {@link Boolean }
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
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setStoreSupported(boolean value) {
        this.storeSupported = value;
    }

    public boolean isSetStoreSupported() {
        return (this.storeSupported != null);
    }

    public void unsetStoreSupported() {
        this.storeSupported = null;
    }

    /**
     * Recupera il valore della proprietà statusSupported.
     *
     * @return possible object is
     * {@link Boolean }
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
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setStatusSupported(boolean value) {
        this.statusSupported = value;
    }

    public boolean isSetStatusSupported() {
        return (this.statusSupported != null);
    }

    public void unsetStatusSupported() {
        this.statusSupported = null;
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
            theStoreSupported = (this.isSetStoreSupported() ? this.isStoreSupported() : false);
            strategy.appendField(locator, this, "storeSupported", buffer, theStoreSupported, this.isSetStoreSupported());
        }
        {
            boolean theStatusSupported;
            theStatusSupported = (this.isSetStatusSupported() ? this.isStatusSupported() : false);
            strategy.appendField(locator, this, "statusSupported", buffer, theStatusSupported, this.isSetStatusSupported());
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
     *         &lt;element name="Input" type="{http://www.opengis.net/wps/1.0.0}InputDescriptionType" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "input"
    })
    public static class DataInputs implements ToString2 {

        @XmlElement(name = "Input", namespace = "", required = true)
        protected List<InputDescriptionType> input;

        /**
         * Gets the value of the input property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the input property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInput().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InputDescriptionType }
         */
        public List<InputDescriptionType> getInput() {
            if (input == null) {
                input = new ArrayList<InputDescriptionType>();
            }
            return this.input;
        }

        public boolean isSetInput() {
            return ((this.input != null) && (!this.input.isEmpty()));
        }

        public void unsetInput() {
            this.input = null;
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
                List<InputDescriptionType> theInput;
                theInput = (this.isSetInput() ? this.getInput() : null);
                strategy.appendField(locator, this, "input", buffer, theInput, this.isSetInput());
            }
            return buffer;
        }

        public void setInput(List<InputDescriptionType> value) {
            this.input = null;
            if (value != null) {
                List<InputDescriptionType> draftl = this.getInput();
                draftl.addAll(value);
            }
        }

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
     *         &lt;element name="Output" type="{http://www.opengis.net/wps/1.0.0}OutputDescriptionType" maxOccurs="unbounded"/&gt;
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

        @XmlElement(name = "Output", namespace = "", required = true)
        protected List<OutputDescriptionType> output;

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
         * {@link OutputDescriptionType }
         */
        public List<OutputDescriptionType> getOutput() {
            if (output == null) {
                output = new ArrayList<OutputDescriptionType>();
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
                List<OutputDescriptionType> theOutput;
                theOutput = (this.isSetOutput() ? this.getOutput() : null);
                strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
            }
            return buffer;
        }

        public void setOutput(List<OutputDescriptionType> value) {
            this.output = null;
            if (value != null) {
                List<OutputDescriptionType> draftl = this.getOutput();
                draftl.addAll(value);
            }
        }

    }

}
