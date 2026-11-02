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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * A combination of format, encoding, and/or schema supported by a process input or output.
 * 
 * <p>Classe Java per ComplexDataDescriptionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ComplexDataDescriptionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="MimeType" type="{http://www.opengis.net/ows/1.1}MimeType"/>
 *         <element name="Encoding" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="Schema" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexDataDescriptionType", propOrder = {
    "mimeType",
    "encoding",
    "schema"
})
public class ComplexDataDescriptionType implements ToString
{

    /**
     * Mime type supported for this input or output (e.g., text/xml).
     * 
     */
    @XmlElement(name = "MimeType", namespace = "", required = true)
    protected String mimeType;
    /**
     * Reference to an encoding supported for this input or output (e.g., UTF-8).  This element shall be omitted if Encoding does not apply to this Input/Output.
     * 
     */
    @XmlElement(name = "Encoding", namespace = "")
    @XmlSchemaType(name = "anyURI")
    protected String encoding;
    /**
     * Reference to a definition of XML elements or types supported for this Input/Output (e.g., GML 2.1 Application Schema). Each of these XML elements or types shall be defined in a separate XML Schema Document. This parameter shall be included when this input/output is XML encoded using an XML schema. When included, the input/output shall validate against the referenced XML Schema. This element shall be omitted if Schema does not apply to this Input/Output. Note: If the Input/Output uses a profile of a larger schema, the server administrator should provide that schema profile for validation purposes.
     * 
     */
    @XmlElement(name = "Schema", namespace = "")
    @XmlSchemaType(name = "anyURI")
    protected String schema;

    /**
     * Mime type supported for this input or output (e.g., text/xml).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Imposta il valore della proprietà mimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getMimeType()
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    public boolean isSetMimeType() {
        return (this.mimeType!= null);
    }

    /**
     * Reference to an encoding supported for this input or output (e.g., UTF-8).  This element shall be omitted if Encoding does not apply to this Input/Output.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Imposta il valore della proprietà encoding.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getEncoding()
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

    public boolean isSetEncoding() {
        return (this.encoding!= null);
    }

    /**
     * Reference to a definition of XML elements or types supported for this Input/Output (e.g., GML 2.1 Application Schema). Each of these XML elements or types shall be defined in a separate XML Schema Document. This parameter shall be included when this input/output is XML encoded using an XML schema. When included, the input/output shall validate against the referenced XML Schema. This element shall be omitted if Schema does not apply to this Input/Output. Note: If the Input/Output uses a profile of a larger schema, the server administrator should provide that schema profile for validation purposes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Imposta il valore della proprietà schema.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getSchema()
     */
    public void setSchema(String value) {
        this.schema = value;
    }

    public boolean isSetSchema() {
        return (this.schema!= null);
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
            String theMimeType;
            theMimeType = this.getMimeType();
            strategy.appendField(locator, this, "mimeType", buffer, theMimeType, this.isSetMimeType());
        }
        {
            String theEncoding;
            theEncoding = this.getEncoding();
            strategy.appendField(locator, this, "encoding", buffer, theEncoding, this.isSetEncoding());
        }
        {
            String theSchema;
            theSchema = this.getSchema();
            strategy.appendField(locator, this, "schema", buffer, theSchema, this.isSetSchema());
        }
        return buffer;
    }

}
