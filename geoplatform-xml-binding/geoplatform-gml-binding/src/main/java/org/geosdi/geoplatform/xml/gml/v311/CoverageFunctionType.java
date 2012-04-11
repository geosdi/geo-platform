/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.xml.gml.v311;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * The function or rule which defines the map from members of the domainSet to the range.  
 *       More functions will be added to this list
 * 
 * <p>Java class for CoverageFunctionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoverageFunctionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/gml}MappingRule"/>
 *         &lt;element ref="{http://www.opengis.net/gml}GridFunction"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoverageFunctionType", propOrder = {
    "mappingRule",
    "gridFunction"
})
public class CoverageFunctionType {

    @XmlElement(name = "MappingRule")
    protected StringOrRefType mappingRule;
    @XmlElementRef(name = "GridFunction",
                   namespace = "http://www.opengis.net/gml",
                   type = JAXBElement.class)
    protected JAXBElement<? extends GridFunctionType> gridFunction;

    public CoverageFunctionType() {
    }

    /**
     * Gets the value of the mappingRule property.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getMappingRule() {
        return mappingRule;
    }

    /**
     * Sets the value of the mappingRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setMappingRule(StringOrRefType value) {
        this.mappingRule = value;
    }

    /**
     * Gets the value of the gridFunction property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link IndexMapType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GridFunctionType }{@code >}
     *     
     */
    public JAXBElement<? extends GridFunctionType> getGridFunction() {
        return gridFunction;
    }

    /**
     * Sets the value of the gridFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link IndexMapType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GridFunctionType }{@code >}
     *     
     */
    public void setGridFunction(JAXBElement<? extends GridFunctionType> value) {
        this.gridFunction = ((JAXBElement<? extends GridFunctionType>) value);
    }

    @Override
    public String toString() {
        return "CoverageFunctionType{ " + "mappingRule = " + mappingRule
                + ", gridFunction = " + gridFunction + '}';
    }
}
