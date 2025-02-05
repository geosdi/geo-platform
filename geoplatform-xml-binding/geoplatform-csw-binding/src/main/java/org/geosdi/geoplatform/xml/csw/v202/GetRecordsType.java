/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 12:36:36 PM CEST 
//
package org.geosdi.geoplatform.xml.csw.v202;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

/**
 * 
 *          The principal means of searching the catalogue. The matching 
 *          catalogue entries may be included with the response. The client 
 *          may assign a requestId (absolute URI). A distributed search is 
 *          performed if the DistributedSearch element is present and the 
 *          catalogue is a member of a federation. Profiles may allow 
 *          alternative query expressions.
 * 
 * <p>Java class for GetRecordsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRecordsType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/cat/csw/2.0.2}RequestBaseType">
 *       &lt;sequence>
 *         &lt;element name="DistributedSearch" type="{http://www.opengis.net/cat/csw/2.0.2}DistributedSearchType" minOccurs="0"/>
 *         &lt;element name="ResponseHandler" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/cat/csw/2.0.2}AbstractQuery"/>
 *           &lt;any namespace='##other'/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/cat/csw/2.0.2}BasicRetrievalOptions"/>
 *       &lt;attribute name="requestId" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="resultType" type="{http://www.opengis.net/cat/csw/2.0.2}ResultType" default="hits" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "GetRecords")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRecordsType", propOrder = {
    "distributedSearch",
    "responseHandler",
    "abstractQuery",
    "any"
})
public class GetRecordsType extends RequestBaseType
        implements ToString {

    @XmlElement(name = "DistributedSearch")
    protected DistributedSearchType distributedSearch;
    @XmlElement(name = "ResponseHandler")
    @XmlSchemaType(name = "anyURI")
    protected List<String> responseHandler;
    @XmlElementRefs({
        @XmlElementRef(name = "AbstractQuery",
                       namespace = "http://www.opengis.net/cat/csw/2.0.2",
                       type = AbstractQueryType.class),
        @XmlElementRef(name = "QueryType",
                       namespace = "http://www.opengis.net/cat/csw/2.0.2",
                       type = QueryType.class)
    })
    protected AbstractQueryType abstractQuery;
    @XmlAnyElement(lax = true)
    protected Object any;
    @XmlAttribute(name = "requestId")
    @XmlSchemaType(name = "anyURI")
    protected String requestId;
    @XmlAttribute(name = "resultType")
    protected ResultType resultType;
    @XmlAttribute(name = "outputFormat")
    protected String outputFormat;
    @XmlAttribute(name = "outputSchema")
    @XmlSchemaType(name = "anyURI")
    protected String outputSchema;
    @XmlAttribute(name = "startPosition")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger startPosition;
    @XmlAttribute(name = "maxRecords")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxRecords;

    /**
     * Gets the value of the distributedSearch property.
     * 
     * @return
     *     possible object is
     *     {@link DistributedSearchType }
     *     
     */
    public DistributedSearchType getDistributedSearch() {
        return distributedSearch;
    }

    /**
     * Sets the value of the distributedSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributedSearchType }
     *     
     */
    public void setDistributedSearch(DistributedSearchType value) {
        this.distributedSearch = value;
    }

    public boolean isSetDistributedSearch() {
        return (this.distributedSearch != null);
    }

    /**
     * Gets the value of the responseHandler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseHandler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseHandler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getResponseHandler() {
        if (responseHandler == null) {
            responseHandler = new ArrayList<String>();
        }
        return this.responseHandler;
    }

    public boolean isSetResponseHandler() {
        return ((this.responseHandler != null) && (!this.responseHandler.isEmpty()));
    }

    public void unsetResponseHandler() {
        this.responseHandler = null;
    }

    /**
     * Gets the value of the abstractQuery property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link QueryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractQueryType }{@code >}
     *     
     */
    public AbstractQueryType getAbstractQuery() {
        return abstractQuery;
    }

    /**
     * Sets the value of the abstractQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link QueryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractQueryType }{@code >}
     *     
     */
    public void setAbstractQuery(AbstractQueryType value) {
        this.abstractQuery = value;
    }

    public boolean isSetAbstractQuery() {
        return (this.abstractQuery != null);
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

    public boolean isSetAny() {
        return (this.any != null);
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    public boolean isSetRequestId() {
        return (this.requestId != null);
    }

    /**
     * Gets the value of the resultType property.
     * 
     * @return
     *     possible object is
     *     {@link ResultType }
     *     
     */
    public ResultType getResultType() {
        if (resultType == null) {
            return ResultType.HITS;
        } else {
            return resultType;
        }
    }

    /**
     * Sets the value of the resultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *     
     */
    public void setResultType(ResultType value) {
        this.resultType = value;
    }

    public boolean isSetResultType() {
        return (this.resultType != null);
    }

    /**
     * Gets the value of the outputFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputFormat() {
        if (outputFormat == null) {
            return "application/xml";
        } else {
            return outputFormat;
        }
    }

    /**
     * Sets the value of the outputFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputFormat(String value) {
        this.outputFormat = value;
    }

    public boolean isSetOutputFormat() {
        return (this.outputFormat != null);
    }

    /**
     * Gets the value of the outputSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputSchema() {
        return outputSchema;
    }

    /**
     * Sets the value of the outputSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputSchema(String value) {
        this.outputSchema = value;
    }

    public boolean isSetOutputSchema() {
        return (this.outputSchema != null);
    }

    /**
     * Gets the value of the startPosition property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStartPosition() {
        if (startPosition == null) {
            return new BigInteger("1");
        } else {
            return startPosition;
        }
    }

    /**
     * Sets the value of the startPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStartPosition(BigInteger value) {
        this.startPosition = value;
    }

    public boolean isSetStartPosition() {
        return (this.startPosition != null);
    }

    /**
     * Gets the value of the maxRecords property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxRecords() {
        if (maxRecords == null) {
            return new BigInteger("10");
        } else {
            return maxRecords;
        }
    }

    /**
     * Sets the value of the maxRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxRecords(BigInteger value) {
        this.maxRecords = value;
    }

    public boolean isSetMaxRecords() {
        return (this.maxRecords != null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            DistributedSearchType theDistributedSearch;
            theDistributedSearch = this.getDistributedSearch();
            strategy.appendField(locator, this, "distributedSearch", buffer,
                    theDistributedSearch);
        }
        {
            List<String> theResponseHandler;
            theResponseHandler = (this.isSetResponseHandler() ? this.getResponseHandler() : null);
            strategy.appendField(locator, this, "responseHandler", buffer,
                    theResponseHandler);
        }
        {
            AbstractQueryType theAbstractQuery;
            theAbstractQuery = this.getAbstractQuery();
            strategy.appendField(locator, this, "abstractQuery", buffer,
                    theAbstractQuery);
        }
        {
            Object theAny;
            theAny = this.getAny();
            strategy.appendField(locator, this, "any", buffer, theAny);
        }
        {
            String theRequestId;
            theRequestId = this.getRequestId();
            strategy.appendField(locator, this, "requestId", buffer,
                    theRequestId);
        }
        {
            ResultType theResultType;
            theResultType = this.getResultType();
            strategy.appendField(locator, this, "resultType", buffer,
                    theResultType);
        }
        {
            String theOutputFormat;
            theOutputFormat = this.getOutputFormat();
            strategy.appendField(locator, this, "outputFormat", buffer,
                    theOutputFormat);
        }
        {
            String theOutputSchema;
            theOutputSchema = this.getOutputSchema();
            strategy.appendField(locator, this, "outputSchema", buffer,
                    theOutputSchema);
        }
        {
            BigInteger theStartPosition;
            theStartPosition = this.getStartPosition();
            strategy.appendField(locator, this, "startPosition", buffer,
                    theStartPosition);
        }
        {
            BigInteger theMaxRecords;
            theMaxRecords = this.getMaxRecords();
            strategy.appendField(locator, this, "maxRecords", buffer,
                    theMaxRecords);
        }
        return buffer;
    }

    public void setResponseHandler(List<String> value) {
        this.responseHandler = null;
        List<String> draftl = this.getResponseHandler();
        draftl.addAll(value);
    }
}
