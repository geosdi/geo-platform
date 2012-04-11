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
 * A prime meridian defines the origin from which longitude values are determined.
 * 
 * <p>Java class for PrimeMeridianType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrimeMeridianType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}PrimeMeridianBaseType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}meridianID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}remarks" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}greenwichLongitude"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrimeMeridianType", propOrder = {
    "meridianID",
    "remarks",
    "greenwichLongitude"
})
public class PrimeMeridianType extends PrimeMeridianBaseType {

    private List<IdentifierType> meridianID;
    protected StringOrRefType remarks;
    @XmlElement(required = true)
    protected AngleChoiceType greenwichLongitude;

    public PrimeMeridianType() {
    }

    /**
     * @param meridianID the meridianID to set
     */
    public void setMeridianID(List<IdentifierType> meridianID) {
        this.meridianID = meridianID;
    }

    /**
     * Set of alternative identifications of this prime meridian. The first meridianID, if any, is normally the primary identification code, and any others are aliases. Gets the value of the meridianID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meridianID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeridianID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getMeridianID() {
        if (meridianID == null) {
            this.meridianID = new ArrayList<IdentifierType>();
        }
        return this.meridianID;
    }

    /**
     * Comments on or information about this prime meridian, including source information. 
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
     * Gets the value of the greenwichLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link AngleChoiceType }
     *     
     */
    public AngleChoiceType getGreenwichLongitude() {
        return greenwichLongitude;
    }

    /**
     * Sets the value of the greenwichLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleChoiceType }
     *     
     */
    public void setGreenwichLongitude(AngleChoiceType value) {
        this.greenwichLongitude = value;
    }

    @Override
    public String toString() {
        return "PrimeMeridianType{ " + "meridianID = " + meridianID
                + ", remarks = " + remarks
                + ", greenwichLongitude = " + greenwichLongitude + '}';
    }
}
