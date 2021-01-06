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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the response type of the WPS, either raw data or XML document
 * <p>
 * <p>Classe Java per ResponseFormType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="ResponseFormType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ResponseDocument" type="{http://www.opengis.net/wps/1.0.0}ResponseDocumentType"/&gt;
 *         &lt;element name="RawDataOutput" type="{http://www.opengis.net/wps/1.0.0}OutputDefinitionType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseFormType", propOrder = {
        "responseDocument",
        "rawDataOutput"
})
public class ResponseFormType implements ToString2 {

    @XmlElement(name = "ResponseDocument")
    protected ResponseDocumentType responseDocument;
    @XmlElement(name = "RawDataOutput")
    protected OutputDefinitionType rawDataOutput;

    /**
     * Recupera il valore della proprietà responseDocument.
     *
     * @return possible object is
     * {@link ResponseDocumentType }
     */
    public ResponseDocumentType getResponseDocument() {
        return responseDocument;
    }

    /**
     * Imposta il valore della proprietà responseDocument.
     *
     * @param value allowed object is
     *              {@link ResponseDocumentType }
     */
    public void setResponseDocument(ResponseDocumentType value) {
        this.responseDocument = value;
    }

    public boolean isSetResponseDocument() {
        return (this.responseDocument != null);
    }

    /**
     * Recupera il valore della proprietà rawDataOutput.
     *
     * @return possible object is
     * {@link OutputDefinitionType }
     */
    public OutputDefinitionType getRawDataOutput() {
        return rawDataOutput;
    }

    /**
     * Imposta il valore della proprietà rawDataOutput.
     *
     * @param value allowed object is
     *              {@link OutputDefinitionType }
     */
    public void setRawDataOutput(OutputDefinitionType value) {
        this.rawDataOutput = value;
    }

    public boolean isSetRawDataOutput() {
        return (this.rawDataOutput != null);
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
            ResponseDocumentType theResponseDocument;
            theResponseDocument = this.getResponseDocument();
            strategy.appendField(locator, this, "responseDocument", buffer, theResponseDocument, this.isSetResponseDocument());
        }
        {
            OutputDefinitionType theRawDataOutput;
            theRawDataOutput = this.getRawDataOutput();
            strategy.appendField(locator, this, "rawDataOutput", buffer, theRawDataOutput, this.isSetRawDataOutput());
        }
        return buffer;
    }

}
