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
 * Reference to an input or output value that is a web accessible resource.
 * <p>
 * <p>Classe Java per InputReferenceType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="InputReferenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="Header" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="Body" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *           &lt;element name="BodyReference"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;attribute ref="{http://www.w3.org/1999/xlink}href use="required""/&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attGroup ref="{http://www.opengis.net/wps/1.0.0}ComplexDataEncoding"/&gt;
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href use="required""/&gt;
 *       &lt;attribute name="method" default="GET"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="GET"/&gt;
 *             &lt;enumeration value="POST"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputReferenceType", propOrder = {
        "header",
        "body",
        "bodyReference"
})
public class InputReferenceType implements ToString2 {

    @XmlElement(name = "Header")
    protected List<InputReferenceType.Header> header;
    @XmlElement(name = "Body")
    protected Object body;
    @XmlElement(name = "BodyReference")
    protected InputReferenceType.BodyReference bodyReference;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String href;
    @XmlAttribute(name = "method")
    protected String method;
    @XmlAttribute(name = "mimeType")
    protected String mimeType;
    @XmlAttribute(name = "encoding")
    @XmlSchemaType(name = "anyURI")
    protected String encoding;
    @XmlAttribute(name = "schema")
    @XmlSchemaType(name = "anyURI")
    protected String schema;

    /**
     * Gets the value of the header property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the header property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeader().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputReferenceType.Header }
     */
    public List<InputReferenceType.Header> getHeader() {
        if (header == null) {
            header = new ArrayList<InputReferenceType.Header>();
        }
        return this.header;
    }

    public boolean isSetHeader() {
        return ((this.header != null) && (!this.header.isEmpty()));
    }

    public void unsetHeader() {
        this.header = null;
    }

    /**
     * Recupera il valore della proprietà body.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setBody(Object value) {
        this.body = value;
    }

    public boolean isSetBody() {
        return (this.body != null);
    }

    /**
     * Recupera il valore della proprietà bodyReference.
     *
     * @return possible object is
     * {@link InputReferenceType.BodyReference }
     */
    public InputReferenceType.BodyReference getBodyReference() {
        return bodyReference;
    }

    /**
     * Imposta il valore della proprietà bodyReference.
     *
     * @param value allowed object is
     *              {@link InputReferenceType.BodyReference }
     */
    public void setBodyReference(InputReferenceType.BodyReference value) {
        this.bodyReference = value;
    }

    public boolean isSetBodyReference() {
        return (this.bodyReference != null);
    }

    /**
     * Reference to a web-accessible resource that can be used as input, or is provided by the process as output. This attribute shall contain a URL from which this input/output can be electronically retrieved.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHref() {
        return href;
    }

    /**
     * Imposta il valore della proprietà href.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHref(String value) {
        this.href = value;
    }

    public boolean isSetHref() {
        return (this.href != null);
    }

    /**
     * Recupera il valore della proprietà method.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMethod() {
        if (method == null) {
            return "GET";
        } else {
            return method;
        }
    }

    /**
     * Imposta il valore della proprietà method.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMethod(String value) {
        this.method = value;
    }

    public boolean isSetMethod() {
        return (this.method != null);
    }

    /**
     * Recupera il valore della proprietà mimeType.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Imposta il valore della proprietà mimeType.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    public boolean isSetMimeType() {
        return (this.mimeType != null);
    }

    /**
     * Recupera il valore della proprietà encoding.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Imposta il valore della proprietà encoding.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

    public boolean isSetEncoding() {
        return (this.encoding != null);
    }

    /**
     * Recupera il valore della proprietà schema.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Imposta il valore della proprietà schema.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSchema(String value) {
        this.schema = value;
    }

    public boolean isSetSchema() {
        return (this.schema != null);
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
            List<InputReferenceType.Header> theHeader;
            theHeader = (this.isSetHeader() ? this.getHeader() : null);
            strategy.appendField(locator, this, "header", buffer, theHeader, this.isSetHeader());
        }
        {
            Object theBody;
            theBody = this.getBody();
            strategy.appendField(locator, this, "body", buffer, theBody, this.isSetBody());
        }
        {
            InputReferenceType.BodyReference theBodyReference;
            theBodyReference = this.getBodyReference();
            strategy.appendField(locator, this, "bodyReference", buffer, theBodyReference, this.isSetBodyReference());
        }
        {
            String theHref;
            theHref = this.getHref();
            strategy.appendField(locator, this, "href", buffer, theHref, this.isSetHref());
        }
        {
            String theMethod;
            theMethod = this.getMethod();
            strategy.appendField(locator, this, "method", buffer, theMethod, this.isSetMethod());
        }
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

    public void setHeader(List<InputReferenceType.Header> value) {
        this.header = null;
        if (value != null) {
            List<InputReferenceType.Header> draftl = this.getHeader();
            draftl.addAll(value);
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
     *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href use="required""/&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BodyReference implements ToString2 {

        @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String href;

        /**
         * Reference to a remote document to be used as the body of the an HTTP POST request message. This attribute shall contain a URL from which this input can be electronically retrieved.
         *
         * @return possible object is
         * {@link String }
         */
        public String getHref() {
            return href;
        }

        /**
         * Imposta il valore della proprietà href.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setHref(String value) {
            this.href = value;
        }

        public boolean isSetHref() {
            return (this.href != null);
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
                String theHref;
                theHref = this.getHref();
                strategy.appendField(locator, this, "href", buffer, theHref, this.isSetHref());
            }
            return buffer;
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
     *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Header implements ToString2 {

        @XmlAttribute(name = "key", required = true)
        protected String key;
        @XmlAttribute(name = "value", required = true)
        protected String value;

        /**
         * Recupera il valore della proprietà key.
         *
         * @return possible object is
         * {@link String }
         */
        public String getKey() {
            return key;
        }

        /**
         * Imposta il valore della proprietà key.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setKey(String value) {
            this.key = value;
        }

        public boolean isSetKey() {
            return (this.key != null);
        }

        /**
         * Recupera il valore della proprietà value.
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Imposta il valore della proprietà value.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
        }

        public boolean isSetValue() {
            return (this.value != null);
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
                String theKey;
                theKey = this.getKey();
                strategy.appendField(locator, this, "key", buffer, theKey, this.isSetKey());
            }
            {
                String theValue;
                theValue = this.getValue();
                strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
            }
            return buffer;
        }

    }

}
