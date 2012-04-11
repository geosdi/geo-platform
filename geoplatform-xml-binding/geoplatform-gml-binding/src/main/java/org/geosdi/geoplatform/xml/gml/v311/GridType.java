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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * An unrectified grid, which is a network composed of two or more sets of equally spaced parallel lines in which the members of each set intersect the members of the other sets at right angles.
 * 
 * <p>Java class for GridType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GridType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometryType">
 *       &lt;sequence>
 *         &lt;element name="limits" type="{http://www.opengis.net/gml}GridLimitsType"/>
 *         &lt;element name="axisName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dimension" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GridType", propOrder = {
    "limits",
    "axisName"
})
@XmlSeeAlso({
    RectifiedGridType.class
})
public class GridType extends AbstractGeometryType {

    @XmlElement(required = true)
    protected GridLimitsType limits;
    @XmlElement(required = true)
    private List<String> axisName;
    @XmlAttribute(name = "dimension", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger dimension;

    /**
     * Gets the value of the limits property.
     * 
     * @return
     *     possible object is
     *     {@link GridLimitsType }
     *     
     */
    public GridLimitsType getLimits() {
        return limits;
    }

    /**
     * Sets the value of the limits property.
     * 
     * @param value
     *     allowed object is
     *     {@link GridLimitsType }
     *     
     */
    public void setLimits(GridLimitsType value) {
        this.limits = value;
    }

    /**
     * @param axisName the axisName to set
     */
    public void setAxisName(List<String> axisName) {
        this.axisName = axisName;
    }

    /**
     * Gets the value of the axisName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the axisName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAxisName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAxisName() {
        if (axisName == null) {
            this.axisName = new ArrayList<String>();
        }
        return this.axisName;
    }

    /**
     * Gets the value of the dimension property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDimension() {
        return dimension;
    }

    /**
     * Sets the value of the dimension property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDimension(BigInteger value) {
        this.dimension = value;
    }

    @Override
    public String toString() {
        return "GridType{ " + "limits = " + limits + ", axisName = " + axisName
                + ", dimension = " + dimension + '}';
    }
}
