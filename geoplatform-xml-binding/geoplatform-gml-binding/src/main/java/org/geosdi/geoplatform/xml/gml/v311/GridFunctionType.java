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
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines how values in the domain are mapped to the range set. The start point and the sequencing rule are specified here.
 * 
 * <p>Java class for GridFunctionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GridFunctionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sequenceRule" type="{http://www.opengis.net/gml}SequenceRuleType" minOccurs="0"/>
 *         &lt;element name="startPoint" type="{http://www.opengis.net/gml}integerList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GridFunctionType", propOrder = {
    "sequenceRule",
    "startPoint"
})
@XmlSeeAlso({
    IndexMapType.class
})
public class GridFunctionType {

    protected SequenceRuleType sequenceRule;
    @XmlList
    private List<BigInteger> startPoint;

    public GridFunctionType() {
    }

    /**
     * Gets the value of the sequenceRule property.
     * 
     * @return
     *     possible object is
     *     {@link SequenceRuleType }
     *     
     */
    public SequenceRuleType getSequenceRule() {
        return sequenceRule;
    }

    /**
     * Sets the value of the sequenceRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceRuleType }
     *     
     */
    public void setSequenceRule(SequenceRuleType value) {
        this.sequenceRule = value;
    }

    /**
     * @param startPoint the startPoint to set
     */
    public void setStartPoint(List<BigInteger> startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * Gets the value of the startPoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the startPoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStartPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getStartPoint() {
        if (startPoint == null) {
            this.startPoint = new ArrayList<BigInteger>();
        }
        return this.startPoint;
    }

    @Override
    public String toString() {
        return "GridFunctionType{ " + "sequenceRule = " + sequenceRule
                + ", startPoint = " + startPoint + '}';
    }
}
