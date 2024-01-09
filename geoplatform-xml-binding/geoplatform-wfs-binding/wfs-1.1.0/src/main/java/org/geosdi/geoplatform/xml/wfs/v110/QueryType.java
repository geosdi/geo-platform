/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Generated on: 2012.10.26 at 03:16:42 PM CEST 
//


package org.geosdi.geoplatform.xml.wfs.v110;

import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.FunctionType;
import org.geosdi.geoplatform.xml.filter.v110.SortByType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 *             The Query element is of type QueryType.
 *          
 * 
 * <p>Java class for QueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.opengis.net/wfs}PropertyName"/>
 *           &lt;element ref="{http://www.opengis.net/wfs}XlinkPropertyName"/>
 *           &lt;element ref="{http://www.opengis.net/ogc}Function"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/ogc}Filter" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}SortBy" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="handle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="typeName" use="required" type="{http://www.opengis.net/wfs}TypeNameListType" />
 *       &lt;attribute name="featureVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="srsName" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "QueryType")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryType", propOrder = {
    "propertyNameOrXlinkPropertyNameOrFunction",
    "filter",
    "sortBy"
})
public class QueryType
    implements ToString
{

    @XmlElements({
        @XmlElement(name = "PropertyName", type = String.class),
        @XmlElement(name = "XlinkPropertyName", type = XlinkPropertyName.class),
        @XmlElement(name = "Function", namespace = "http://www.opengis.net/ogc", type = FunctionType.class)
    })
    protected List<Object> propertyNameOrXlinkPropertyNameOrFunction;
    @XmlElement(name = "Filter", namespace = "http://www.opengis.net/ogc")
    protected FilterType filter;
    @XmlElement(name = "SortBy", namespace = "http://www.opengis.net/ogc")
    protected SortByType sortBy;
    @XmlAttribute(name = "handle")
    protected String handle;
    @XmlAttribute(name = "typeName", required = true)
    protected List<QName> typeName;
    @XmlAttribute(name = "featureVersion")
    protected String featureVersion;
    @XmlAttribute(name = "srsName")
    @XmlSchemaType(name = "anyURI")
    protected String srsName;

    /**
     * Gets the value of the propertyNameOrXlinkPropertyNameOrFunction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyNameOrXlinkPropertyNameOrFunction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyNameOrXlinkPropertyNameOrFunction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link XlinkPropertyName }
     * {@link FunctionType }
     * 
     * 
     */
    public List<Object> getPropertyNameOrXlinkPropertyNameOrFunction() {
        if (propertyNameOrXlinkPropertyNameOrFunction == null) {
            propertyNameOrXlinkPropertyNameOrFunction = new ArrayList<Object>();
        }
        return this.propertyNameOrXlinkPropertyNameOrFunction;
    }

    /**
     * 
     *                 The Filter element is used to define spatial and/or non-spatial
     *                 constraints on query.  Spatial constrains use GML3 to specify
     *                 the constraining geometry.  A full description of the Filter
     *                 element can be found in the Filter Encoding Implementation
     *                 Specification.
     *              
     * 
     * @return
     *     possible object is
     *     {@link FilterType }
     *     
     */
    public FilterType getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *     
     */
    public void setFilter(FilterType value) {
        this.filter = value;
    }

    /**
     * 
     *                 The SortBy element is used specify property names whose
     *                 values should be used to order (upon presentation) the
     *                 set of feature instances that satisfy the query.
     *              
     * 
     * @return
     *     possible object is
     *     {@link SortByType }
     *     
     */
    public SortByType getSortBy() {
        return sortBy;
    }

    /**
     * Sets the value of the sortBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortByType }
     *     
     */
    public void setSortBy(SortByType value) {
        this.sortBy = value;
    }

    /**
     * Gets the value of the handle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Sets the value of the handle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandle(String value) {
        this.handle = value;
    }

    /**
     * Gets the value of the typeName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the typeName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTypeName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getTypeName() {
        if (typeName == null) {
            typeName = new ArrayList<QName>();
        }
        return this.typeName;
    }

    /**
     * Gets the value of the featureVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureVersion() {
        return featureVersion;
    }

    /**
     * Sets the value of the featureVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureVersion(String value) {
        this.featureVersion = value;
    }

    /**
     * Gets the value of the srsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrsName() {
        return srsName;
    }

    /**
     * Sets the value of the srsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrsName(String value) {
        this.srsName = value;
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
        {
            List<Object> thePropertyNameOrXlinkPropertyNameOrFunction;
            thePropertyNameOrXlinkPropertyNameOrFunction = (((this.propertyNameOrXlinkPropertyNameOrFunction!= null)&&(!this.propertyNameOrXlinkPropertyNameOrFunction.isEmpty()))?this.getPropertyNameOrXlinkPropertyNameOrFunction():null);
            strategy.appendField(locator, this, "propertyNameOrXlinkPropertyNameOrFunction", buffer, thePropertyNameOrXlinkPropertyNameOrFunction);
        }
        {
            FilterType theFilter;
            theFilter = this.getFilter();
            strategy.appendField(locator, this, "filter", buffer, theFilter);
        }
        {
            SortByType theSortBy;
            theSortBy = this.getSortBy();
            strategy.appendField(locator, this, "sortBy", buffer, theSortBy);
        }
        {
            String theHandle;
            theHandle = this.getHandle();
            strategy.appendField(locator, this, "handle", buffer, theHandle);
        }
        {
            List<QName> theTypeName;
            theTypeName = (((this.typeName!= null)&&(!this.typeName.isEmpty()))?this.getTypeName():null);
            strategy.appendField(locator, this, "typeName", buffer, theTypeName);
        }
        {
            String theFeatureVersion;
            theFeatureVersion = this.getFeatureVersion();
            strategy.appendField(locator, this, "featureVersion", buffer, theFeatureVersion);
        }
        {
            String theSrsName;
            theSrsName = this.getSrsName();
            strategy.appendField(locator, this, "srsName", buffer, theSrsName);
        }
        return buffer;
    }

    public void setPropertyNameOrXlinkPropertyNameOrFunction(List<Object> value) {
        this.propertyNameOrXlinkPropertyNameOrFunction = null;
        List<Object> draftl = this.getPropertyNameOrXlinkPropertyNameOrFunction();
        draftl.addAll(value);
    }

    public void setTypeName(List<QName> value) {
        this.typeName = null;
        List<QName> draftl = this.getTypeName();
        draftl.addAll(value);
    }

}
