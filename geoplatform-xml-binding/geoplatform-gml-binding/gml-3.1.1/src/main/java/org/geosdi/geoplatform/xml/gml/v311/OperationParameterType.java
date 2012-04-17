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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The definition of a parameter used by an operation method. Most parameter values are numeric, but other types of parameter values are possible. This complexType is expected to be used or extended for all operation methods, without defining operation-method-specialized element names.  
 * 
 * <p>Java class for OperationParameterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationParameterType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}OperationParameterBaseType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}parameterID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}remarks" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationParameterType", propOrder = {
    "parameterID",
    "remarks"
})
public class OperationParameterType
        extends OperationParameterBaseType {

    private List<IdentifierType> parameterID;
    protected StringOrRefType remarks;

    public OperationParameterType() {
    }

    /**
     * @param parameterID the parameterID to set
     */
    public void setParameterID(List<IdentifierType> parameterID) {
        this.parameterID = parameterID;
    }

    /**
     * Set of alternative identifications of this operation parameter. The first parameterID, if any, is normally the primary identification code, and any others are aliases. Gets the value of the parameterID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getParameterID() {
        if (parameterID == null) {
            this.parameterID = new ArrayList<IdentifierType>();
        }
        return this.parameterID;
    }

    /**
     * Comments on or information about this operation parameter, including source information. 
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setRemarks(StringOrRefType value) {
        this.remarks = value;
    }

    @Override
    public String toString() {
        return "OperationParameterType{ " + "parameterID = " + parameterID
                + ", remarks = " + remarks + '}';
    }
}
