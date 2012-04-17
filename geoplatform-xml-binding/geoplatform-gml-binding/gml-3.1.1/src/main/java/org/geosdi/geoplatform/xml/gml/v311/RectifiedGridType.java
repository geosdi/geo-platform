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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A rectified grid has an origin and vectors that define its post locations.
 * 
 * <p>Java class for RectifiedGridType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RectifiedGridType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}GridType">
 *       &lt;sequence>
 *         &lt;element name="origin" type="{http://www.opengis.net/gml}PointPropertyType"/>
 *         &lt;element name="offsetVector" type="{http://www.opengis.net/gml}VectorType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RectifiedGridType", propOrder = {
    "origin",
    "offsetVector"
})
public class RectifiedGridType extends GridType {

    @XmlElement(required = true)
    protected PointPropertyType origin;
    @XmlElement(required = true)
    private List<VectorType> offsetVector;

    public RectifiedGridType() {
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link PointPropertyType }
     *     
     */
    public PointPropertyType getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointPropertyType }
     *     
     */
    public void setOrigin(PointPropertyType value) {
        this.origin = value;
    }

    /**
     * @param offsetVector the offsetVector to set
     */
    public void setOffsetVector(List<VectorType> offsetVector) {
        this.offsetVector = offsetVector;
    }

    /**
     * Gets the value of the offsetVector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offsetVector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOffsetVector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VectorType }
     * 
     * 
     */
    public List<VectorType> getOffsetVector() {
        if (offsetVector == null) {
            this.offsetVector = new ArrayList<VectorType>();
        }
        return this.offsetVector;
    }

    @Override
    public String toString() {
        return "RectifiedGridType{ " + "origin = " + origin
                + ", offsetVector = " + offsetVector + '}';
    }
}
