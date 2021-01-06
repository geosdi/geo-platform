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
 * <p>Classe Java per ResponseDocumentType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="ResponseDocumentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Output" type="{http://www.opengis.net/wps/1.0.0}DocumentOutputDefinitionType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="storeExecuteResponse" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="lineage" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseDocumentType", propOrder = {
        "output"
})
public class ResponseDocumentType implements ToString2 {

    @XmlElement(name = "Output", required = true)
    protected List<DocumentOutputDefinitionType> output;
    @XmlAttribute(name = "storeExecuteResponse")
    protected Boolean storeExecuteResponse;
    @XmlAttribute(name = "lineage")
    protected Boolean lineage;
    @XmlAttribute(name = "status")
    protected Boolean status;

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
     * {@link DocumentOutputDefinitionType }
     */
    public List<DocumentOutputDefinitionType> getOutput() {
        if (output == null) {
            output = new ArrayList<DocumentOutputDefinitionType>();
        }
        return this.output;
    }

    public boolean isSetOutput() {
        return ((this.output != null) && (!this.output.isEmpty()));
    }

    public void unsetOutput() {
        this.output = null;
    }

    /**
     * Recupera il valore della proprietà storeExecuteResponse.
     *
     * @return possible object is
     * {@link Boolean }
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
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setStoreExecuteResponse(boolean value) {
        this.storeExecuteResponse = value;
    }

    public boolean isSetStoreExecuteResponse() {
        return (this.storeExecuteResponse != null);
    }

    public void unsetStoreExecuteResponse() {
        this.storeExecuteResponse = null;
    }

    /**
     * Recupera il valore della proprietà lineage.
     *
     * @return possible object is
     * {@link Boolean }
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
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setLineage(boolean value) {
        this.lineage = value;
    }

    public boolean isSetLineage() {
        return (this.lineage != null);
    }

    public void unsetLineage() {
        this.lineage = null;
    }

    /**
     * Recupera il valore della proprietà status.
     *
     * @return possible object is
     * {@link Boolean }
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
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    public boolean isSetStatus() {
        return (this.status != null);
    }

    public void unsetStatus() {
        this.status = null;
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
            List<DocumentOutputDefinitionType> theOutput;
            theOutput = (this.isSetOutput() ? this.getOutput() : null);
            strategy.appendField(locator, this, "output", buffer, theOutput, this.isSetOutput());
        }
        {
            boolean theStoreExecuteResponse;
            theStoreExecuteResponse = (this.isSetStoreExecuteResponse() ? this.isStoreExecuteResponse() : false);
            strategy.appendField(locator, this, "storeExecuteResponse", buffer, theStoreExecuteResponse, this.isSetStoreExecuteResponse());
        }
        {
            boolean theLineage;
            theLineage = (this.isSetLineage() ? this.isLineage() : false);
            strategy.appendField(locator, this, "lineage", buffer, theLineage, this.isSetLineage());
        }
        {
            boolean theStatus;
            theStatus = (this.isSetStatus() ? this.isStatus() : false);
            strategy.appendField(locator, this, "status", buffer, theStatus, this.isSetStatus());
        }
        return buffer;
    }

    public void setOutput(List<DocumentOutputDefinitionType> value) {
        this.output = null;
        if (value != null) {
            List<DocumentOutputDefinitionType> draftl = this.getOutput();
            draftl.addAll(value);
        }
    }

}
