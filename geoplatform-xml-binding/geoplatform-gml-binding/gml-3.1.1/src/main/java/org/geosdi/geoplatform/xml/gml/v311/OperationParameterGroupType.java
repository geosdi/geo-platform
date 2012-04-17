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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * The definition of a group of parameters used by an operation method. This complexType is expected to be used or extended for all applicable operation methods, without defining operation-method-specialized element names.  
 * 
 * <p>Java class for OperationParameterGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationParameterGroupType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}OperationParameterGroupBaseType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}groupID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}remarks" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}maximumOccurs" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}includesParameter" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationParameterGroupType", propOrder = {
    "groupID",
    "remarks",
    "maximumOccurs",
    "includesParameter"
})
public class OperationParameterGroupType
        extends OperationParameterGroupBaseType {

    private List<IdentifierType> groupID;
    protected StringOrRefType remarks;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maximumOccurs;
    @XmlElement(required = true)
    private List<AbstractGeneralOperationParameterRefType> includesParameter;

    public OperationParameterGroupType() {
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(List<IdentifierType> groupID) {
        this.groupID = groupID;
    }

    /**
     * Set of alternative identifications of this operation parameter group. The first groupID, if any, is normally the primary identification code, and any others are aliases. Gets the value of the groupID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getGroupID() {
        if (groupID == null) {
            this.groupID = new ArrayList<IdentifierType>();
        }
        return this.groupID;
    }

    /**
     * Comments on or information about this operation parameter group, including source information. 
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

    /**
     * Gets the value of the maximumOccurs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumOccurs() {
        return maximumOccurs;
    }

    /**
     * Sets the value of the maximumOccurs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumOccurs(BigInteger value) {
        this.maximumOccurs = value;
    }

    /**
     * @param includesParameter the includesParameter to set
     */
    public void setIncludesParameter(List<AbstractGeneralOperationParameterRefType> includesParameter) {
        this.includesParameter = includesParameter;
    }

    /**
     * Unordered list of associations to the set of operation parameters that are members of this group. Gets the value of the includesParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includesParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludesParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractGeneralOperationParameterRefType }
     * 
     * 
     */
    public List<AbstractGeneralOperationParameterRefType> getIncludesParameter() {
        if (includesParameter == null) {
            this.includesParameter = new ArrayList<AbstractGeneralOperationParameterRefType>();
        }
        return this.includesParameter;
    }

    @Override
    public String toString() {
        return "OperationParameterGroupType{ " + "groupID = " + groupID
                + ", remarks = " + remarks + ", maximumOccurs = " + maximumOccurs
                + ", includesParameter = " + includesParameter + '}';
    }
}
