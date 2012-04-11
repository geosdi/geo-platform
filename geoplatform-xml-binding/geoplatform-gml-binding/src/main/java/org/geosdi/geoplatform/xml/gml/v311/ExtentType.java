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
 * Information about the spatial, vertical, and/or temporal extent of a reference system object. Constraints: At least one of the elements "description", "boundingBox", "boundingPolygon", "verticalExtent", and temporalExtent" must be included, but more that one can be included when appropriate. Furthermore, more than one "boundingBox", "boundingPolygon", "verticalExtent", and/or temporalExtent" element can be included, with more than one meaning the union of the individual domains.
 * 
 * <p>Java class for ExtentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}description" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}boundingBox" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{http://www.opengis.net/gml}boundingPolygon" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}verticalExtent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}temporalExtent" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtentType", propOrder = {
    "description",
    "boundingBox",
    "boundingPolygon",
    "verticalExtent",
    "temporalExtent"
})
public class ExtentType {

    protected StringOrRefType description;
    private List<EnvelopeType> boundingBox;
    private List<PolygonType> boundingPolygon;
    private List<EnvelopeType> verticalExtent;
    private List<TimePeriodType> temporalExtent;

    public ExtentType() {
    }

    /**
     * Description of spatial and/or temporal extent of this object.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setDescription(StringOrRefType value) {
        this.description = value;
    }

    /**
     * @param boundingBox the boundingBox to set
     */
    public void setBoundingBox(List<EnvelopeType> boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * Unordered list of bounding boxes (or envelopes) whose union describes the spatial domain of this object.Gets the value of the boundingBox property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boundingBox property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoundingBox().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnvelopeType }
     * 
     * 
     */
    public List<EnvelopeType> getBoundingBox() {
        if (boundingBox == null) {
            this.boundingBox = new ArrayList<EnvelopeType>();
        }
        return this.boundingBox;
    }

    /**
     * @param boundingPolygon the boundingPolygon to set
     */
    public void setBoundingPolygon(List<PolygonType> boundingPolygon) {
        this.boundingPolygon = boundingPolygon;
    }

    /**
     * Unordered list of bounding polygons whose union describes the spatial domain of this object.Gets the value of the boundingPolygon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boundingPolygon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoundingPolygon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolygonType }
     * 
     * 
     */
    public List<PolygonType> getBoundingPolygon() {
        if (boundingPolygon == null) {
            this.boundingPolygon = new ArrayList<PolygonType>();
        }
        return this.boundingPolygon;
    }

    /**
     * @param verticalExtent the verticalExtent to set
     */
    public void setVerticalExtent(List<EnvelopeType> verticalExtent) {
        this.verticalExtent = verticalExtent;
    }

    /**
     * Unordered list of vertical intervals whose union describes the spatial domain of this object.Gets the value of the verticalExtent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verticalExtent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerticalExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnvelopeType }
     * 
     * 
     */
    public List<EnvelopeType> getVerticalExtent() {
        if (verticalExtent == null) {
            this.verticalExtent = new ArrayList<EnvelopeType>();
        }
        return this.verticalExtent;
    }

    /**
     * @param temporalExtent the temporalExtent to set
     */
    public void setTemporalExtent(List<TimePeriodType> temporalExtent) {
        this.temporalExtent = temporalExtent;
    }

    /**
     * Unordered list of time periods whose union describes the spatial domain of this object.Gets the value of the temporalExtent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the temporalExtent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemporalExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimePeriodType }
     * 
     * 
     */
    public List<TimePeriodType> getTemporalExtent() {
        if (temporalExtent == null) {
            this.temporalExtent = new ArrayList<TimePeriodType>();
        }
        return this.temporalExtent;
    }

    @Override
    public String toString() {
        return "ExtentType{ " + "description = " + description
                + ", boundingBox = " + boundingBox + ", boundingPolygon = "
                + boundingPolygon + ", verticalExtent = " + verticalExtent
                + ", temporalExtent = " + temporalExtent + '}';
    }
}
