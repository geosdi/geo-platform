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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Reference to an input or output value that is a web accessible resource.
 * 
 * <p>Classe Java per InputReferenceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="InputReferenceType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence minOccurs="0">
 *         <element name="Header" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <choice minOccurs="0">
 *           <element name="Body" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *           <element name="BodyReference">
 *             <complexType>
 *               <complexContent>
 *                 <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   <attribute ref="{http://www.w3.org/1999/xlink}href use="required""/>
 *                 </restriction>
 *               </complexContent>
 *             </complexType>
 *           </element>
 *         </choice>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/wps/1.0.0}ComplexDataEncoding"/>
 *       <attribute ref="{http://www.w3.org/1999/xlink}href use="required""/>
 *       <attribute name="method" default="GET">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             <enumeration value="GET"/>
 *             <enumeration value="POST"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputReferenceType", propOrder = {
    "header",
    "body",
    "bodyReference"
})
public class InputReferenceType implements ToString
{

    /**
     * Extra HTTP request headers needed by the service identified in ../Reference/&#064;href.  For example, an HTTP SOAP request requires a SOAPAction header.  This permits the creation of a complete and valid POST request.
     * 
     */
    @XmlElement(name = "Header")
    protected List<InputReferenceType.Header> header;
    /**
     * The contents of this element to be used as the body of the HTTP request message to be sent to the service identified in ../Reference/&#064;href.  For example, it could be an XML encoded WFS request using HTTP POST
     * 
     */
    @XmlElement(name = "Body")
    protected Object body;
    /**
     * Reference to a remote document to be used as the body of the an HTTP POST request message to the service identified in ../Reference/&#064;href.
     * 
     */
    @XmlElement(name = "BodyReference")
    protected InputReferenceType.BodyReference bodyReference;
    /**
     * Reference to a web-accessible resource that can be used as input, or is provided by the process as output. This attribute shall contain a URL from which this input/output can be electronically retrieved.
     * 
     */
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String href;
    /**
     * Identifies the HTTP method.  Allows a choice of GET or POST.  Default is GET.
     * 
     */
    @XmlAttribute(name = "method")
    protected String method;
    /**
     * The Format of this input or requested for this output (e.g., text/xml). This element shall be omitted when the Format is indicated in the http header of the output. When included, this format shall be one published for this output or input in the Process full description.
     * 
     */
    @XmlAttribute(name = "mimeType")
    protected String mimeType;
    /**
     * The encoding of this input or requested for this output (e.g., UTF-8). This "encoding" shall be included whenever the encoding required is not the default encoding indicated in the Process full description. When included, this encoding shall be one published for this output or input in the Process full description.
     * 
     */
    @XmlAttribute(name = "encoding")
    @XmlSchemaType(name = "anyURI")
    protected String encoding;
    /**
     * PS I changed the name of this attribute to be consistent with the ProcessDescription.  The original was giving me validation troubles in XMLSpy.
     * 
     */
    @XmlAttribute(name = "schema")
    @XmlSchemaType(name = "anyURI")
    protected String schema;

    /**
     * Extra HTTP request headers needed by the service identified in ../Reference/&#064;href.  For example, an HTTP SOAP request requires a SOAPAction header.  This permits the creation of a complete and valid POST request.
     * 
     * Gets the value of the header property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the header property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getHeader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputReferenceType.Header }
     * </p>
     * 
     * 
     * @return
     *     The value of the header property.
     */
    public List<InputReferenceType.Header> getHeader() {
        if (header == null) {
            header = new ArrayList<>();
        }
        return this.header;
    }

    public boolean isSetHeader() {
        return ((this.header!= null)&&(!this.header.isEmpty()));
    }

    public void unsetHeader() {
        this.header = null;
    }

    /**
     * The contents of this element to be used as the body of the HTTP request message to be sent to the service identified in ../Reference/&#064;href.  For example, it could be an XML encoded WFS request using HTTP POST
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     * @see #getBody()
     */
    public void setBody(Object value) {
        this.body = value;
    }

    public boolean isSetBody() {
        return (this.body!= null);
    }

    /**
     * Reference to a remote document to be used as the body of the an HTTP POST request message to the service identified in ../Reference/&#064;href.
     * 
     * @return
     *     possible object is
     *     {@link InputReferenceType.BodyReference }
     *     
     */
    public InputReferenceType.BodyReference getBodyReference() {
        return bodyReference;
    }

    /**
     * Imposta il valore della proprietà bodyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link InputReferenceType.BodyReference }
     *     
     * @see #getBodyReference()
     */
    public void setBodyReference(InputReferenceType.BodyReference value) {
        this.bodyReference = value;
    }

    public boolean isSetBodyReference() {
        return (this.bodyReference!= null);
    }

    /**
     * Reference to a web-accessible resource that can be used as input, or is provided by the process as output. This attribute shall contain a URL from which this input/output can be electronically retrieved.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Imposta il valore della proprietà href.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getHref()
     */
    public void setHref(String value) {
        this.href = value;
    }

    public boolean isSetHref() {
        return (this.href!= null);
    }

    /**
     * Identifies the HTTP method.  Allows a choice of GET or POST.  Default is GET.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
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
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getMethod()
     */
    public void setMethod(String value) {
        this.method = value;
    }

    public boolean isSetMethod() {
        return (this.method!= null);
    }

    /**
     * The Format of this input or requested for this output (e.g., text/xml). This element shall be omitted when the Format is indicated in the http header of the output. When included, this format shall be one published for this output or input in the Process full description.
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
     * The encoding of this input or requested for this output (e.g., UTF-8). This "encoding" shall be included whenever the encoding required is not the default encoding indicated in the Process full description. When included, this encoding shall be one published for this output or input in the Process full description.
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
     * PS I changed the name of this attribute to be consistent with the ProcessDescription.  The original was giving me validation troubles in XMLSpy.
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
            List<InputReferenceType.Header> theHeader;
            theHeader = (this.isSetHeader()?this.getHeader():null);
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
        if (value!= null) {
            List<InputReferenceType.Header> draftl = this.getHeader();
            draftl.addAll(value);
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
     *       <attribute ref="{http://www.w3.org/1999/xlink}href use="required""/>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BodyReference implements ToString
    {

        /**
         * Reference to a remote document to be used as the body of the an HTTP POST request message. This attribute shall contain a URL from which this input can be electronically retrieved.
         * 
         */
        @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String href;

        /**
         * Reference to a remote document to be used as the body of the an HTTP POST request message. This attribute shall contain a URL from which this input can be electronically retrieved.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHref() {
            return href;
        }

        /**
         * Imposta il valore della proprietà href.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getHref()
         */
        public void setHref(String value) {
            this.href = value;
        }

        public boolean isSetHref() {
            return (this.href!= null);
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
                String theHref;
                theHref = this.getHref();
                strategy.appendField(locator, this, "href", buffer, theHref, this.isSetHref());
            }
            return buffer;
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
     *       <attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Header implements ToString
    {

        /**
         * Key portion of the Key-Value pair in the HTTP request header.
         * 
         */
        @XmlAttribute(name = "key", required = true)
        protected String key;
        /**
         * Value portion of the Key-Value pair in the HTTP request header.
         * 
         */
        @XmlAttribute(name = "value", required = true)
        protected String value;

        /**
         * Key portion of the Key-Value pair in the HTTP request header.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKey() {
            return key;
        }

        /**
         * Imposta il valore della proprietà key.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getKey()
         */
        public void setKey(String value) {
            this.key = value;
        }

        public boolean isSetKey() {
            return (this.key!= null);
        }

        /**
         * Value portion of the Key-Value pair in the HTTP request header.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Imposta il valore della proprietà value.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getValue()
         */
        public void setValue(String value) {
            this.value = value;
        }

        public boolean isSetValue() {
            return (this.value!= null);
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
