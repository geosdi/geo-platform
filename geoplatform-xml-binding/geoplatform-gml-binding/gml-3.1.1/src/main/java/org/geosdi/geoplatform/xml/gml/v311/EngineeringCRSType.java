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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A contextually local coordinate reference system; which can be divided into two broad categories:
 * - earth-fixed systems applied to engineering activities on or near the surface of the earth;
 * - CRSs on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.
 * For further information, see OGC Abstract Specification Topic 2. 
 * 
 * <p>Java class for EngineeringCRSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EngineeringCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractReferenceSystemType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}usesCS"/>
 *         &lt;element ref="{http://www.opengis.net/gml}usesEngineeringDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EngineeringCRSType", propOrder = {
    "usesCS",
    "usesEngineeringDatum"
})
public class EngineeringCRSType extends AbstractReferenceSystemType {

    @XmlElement(required = true)
    protected CoordinateSystemRefType usesCS;
    @XmlElement(required = true)
    protected EngineeringDatumRefType usesEngineeringDatum;

    public EngineeringCRSType() {
    }

    /**
     * Gets the value of the usesCS property.
     * 
     * @return
     *     possible object is
     *     {@link CoordinateSystemRefType }
     *     
     */
    public CoordinateSystemRefType getUsesCS() {
        return usesCS;
    }

    /**
     * Sets the value of the usesCS property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinateSystemRefType }
     *     
     */
    public void setUsesCS(CoordinateSystemRefType value) {
        this.usesCS = value;
    }

    /**
     * Gets the value of the usesEngineeringDatum property.
     * 
     * @return
     *     possible object is
     *     {@link EngineeringDatumRefType }
     *     
     */
    public EngineeringDatumRefType getUsesEngineeringDatum() {
        return usesEngineeringDatum;
    }

    /**
     * Sets the value of the usesEngineeringDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link EngineeringDatumRefType }
     *     
     */
    public void setUsesEngineeringDatum(EngineeringDatumRefType value) {
        this.usesEngineeringDatum = value;
    }

    @Override
    public String toString() {
        return "EngineeringCRSType{ " + "usesCS = " + usesCS
                + ", usesEngineeringDatum = " + usesEngineeringDatum + '}';
    }
}
