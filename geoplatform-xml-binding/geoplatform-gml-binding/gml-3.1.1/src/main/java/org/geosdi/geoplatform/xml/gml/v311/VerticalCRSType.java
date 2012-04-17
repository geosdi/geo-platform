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
 * A 1D coordinate reference system used for recording heights or depths. Vertical CRSs make use of the direction of gravity to define the concept of height or depth, but the relationship with gravity may not be straightforward. By implication, ellipsoidal heights (h) cannot be captured in a vertical coordinate reference system. Ellipsoidal heights cannot exist independently, but only as an inseparable part of a 3D coordinate tuple defined in a geographic 3D coordinate reference system. 
 * 
 * <p>Java class for VerticalCRSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerticalCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractReferenceSystemType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}usesVerticalCS"/>
 *         &lt;element ref="{http://www.opengis.net/gml}usesVerticalDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalCRSType", propOrder = {
    "usesVerticalCS",
    "usesVerticalDatum"
})
public class VerticalCRSType
        extends AbstractReferenceSystemType {

    @XmlElement(required = true)
    protected VerticalCSRefType usesVerticalCS;
    @XmlElement(required = true)
    protected VerticalDatumRefType usesVerticalDatum;

    public VerticalCRSType() {
    }

    /**
     * Gets the value of the usesVerticalCS property.
     * 
     * @return
     *     possible object is
     *     {@link VerticalCSRefType }
     *     
     */
    public VerticalCSRefType getUsesVerticalCS() {
        return usesVerticalCS;
    }

    /**
     * Sets the value of the usesVerticalCS property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalCSRefType }
     *     
     */
    public void setUsesVerticalCS(VerticalCSRefType value) {
        this.usesVerticalCS = value;
    }

    /**
     * Gets the value of the usesVerticalDatum property.
     * 
     * @return
     *     possible object is
     *     {@link VerticalDatumRefType }
     *     
     */
    public VerticalDatumRefType getUsesVerticalDatum() {
        return usesVerticalDatum;
    }

    /**
     * Sets the value of the usesVerticalDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalDatumRefType }
     *     
     */
    public void setUsesVerticalDatum(VerticalDatumRefType value) {
        this.usesVerticalDatum = value;
    }

    @Override
    public String toString() {
        return "VerticalCRSType{ " + "usesVerticalCS = " + usesVerticalCS
                + ", usesVerticalDatum = " + usesVerticalDatum + '}';
    }
}
