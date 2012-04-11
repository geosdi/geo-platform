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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This type represents a TP_Complex capable of holding topological primitives.
 * 
 * <p>Java class for TopoComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TopoComplexType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTopologyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}maximalComplex"/>
 *         &lt;element ref="{http://www.opengis.net/gml}superComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}subComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}topoPrimitiveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}topoPrimitiveMembers" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isMaximal" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopoComplexType", propOrder = {
    "maximalComplex",
    "superComplex",
    "subComplex",
    "topoPrimitiveMember",
    "topoPrimitiveMembers"
})
public class TopoComplexType extends AbstractTopologyType {

    @XmlElement(required = true)
    protected TopoComplexMemberType maximalComplex;
    private List<TopoComplexMemberType> superComplex;
    private List<TopoComplexMemberType> subComplex;
    private List<TopoPrimitiveMemberType> topoPrimitiveMember;
    protected TopoPrimitiveArrayAssociationType topoPrimitiveMembers;
    @XmlAttribute(name = "isMaximal")
    protected Boolean isMaximal;

    public TopoComplexType() {
    }

    /**
     * Gets the value of the maximalComplex property.
     * 
     * @return
     *     possible object is
     *     {@link TopoComplexMemberType }
     *     
     */
    public TopoComplexMemberType getMaximalComplex() {
        return maximalComplex;
    }

    /**
     * Sets the value of the maximalComplex property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopoComplexMemberType }
     *     
     */
    public void setMaximalComplex(TopoComplexMemberType value) {
        this.maximalComplex = value;
    }

    /**
     * @param superComplex the superComplex to set
     */
    public void setSuperComplex(List<TopoComplexMemberType> superComplex) {
        this.superComplex = superComplex;
    }

    /**
     * @param subComplex the subComplex to set
     */
    public void setSubComplex(List<TopoComplexMemberType> subComplex) {
        this.subComplex = subComplex;
    }

    /**
     * @param topoPrimitiveMember the topoPrimitiveMember to set
     */
    public void setTopoPrimitiveMember(List<TopoPrimitiveMemberType> topoPrimitiveMember) {
        this.topoPrimitiveMember = topoPrimitiveMember;
    }

    /**
     * Gets the value of the superComplex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the superComplex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuperComplex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoComplexMemberType }
     * 
     * 
     */
    public List<TopoComplexMemberType> getSuperComplex() {
        if (superComplex == null) {
            this.superComplex = new ArrayList<TopoComplexMemberType>();
        }
        return this.superComplex;
    }

    /**
     * Gets the value of the subComplex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subComplex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubComplex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoComplexMemberType }
     * 
     * 
     */
    public List<TopoComplexMemberType> getSubComplex() {
        if (subComplex == null) {
            this.subComplex = new ArrayList<TopoComplexMemberType>();
        }
        return this.subComplex;
    }

    /**
     * Gets the value of the topoPrimitiveMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topoPrimitiveMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopoPrimitiveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoPrimitiveMemberType }
     * 
     * 
     */
    public List<TopoPrimitiveMemberType> getTopoPrimitiveMember() {
        if (topoPrimitiveMember == null) {
            this.topoPrimitiveMember = new ArrayList<TopoPrimitiveMemberType>();
        }
        return this.topoPrimitiveMember;
    }

    /**
     * Gets the value of the topoPrimitiveMembers property.
     * 
     * @return
     *     possible object is
     *     {@link TopoPrimitiveArrayAssociationType }
     *     
     */
    public TopoPrimitiveArrayAssociationType getTopoPrimitiveMembers() {
        return topoPrimitiveMembers;
    }

    /**
     * Sets the value of the topoPrimitiveMembers property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopoPrimitiveArrayAssociationType }
     *     
     */
    public void setTopoPrimitiveMembers(TopoPrimitiveArrayAssociationType value) {
        this.topoPrimitiveMembers = value;
    }

    /**
     * Gets the value of the isMaximal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsMaximal() {
        if (isMaximal == null) {
            return false;
        } else {
            return isMaximal;
        }
    }

    /**
     * Sets the value of the isMaximal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsMaximal(Boolean value) {
        this.isMaximal = value;
    }

    @Override
    public String toString() {
        return "TopoComplexType{ " + "maximalComplex = " + maximalComplex
                + ", superComplex = " + superComplex + ", subComplex = "
                + subComplex + ", topoPrimitiveMember = " + topoPrimitiveMember
                + ", topoPrimitiveMembers = " + topoPrimitiveMembers
                + ", isMaximal = " + isMaximal + '}';
    }
}
