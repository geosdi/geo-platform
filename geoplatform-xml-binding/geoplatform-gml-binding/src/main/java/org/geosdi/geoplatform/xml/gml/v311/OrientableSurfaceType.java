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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * OrientableSurface consists of a surface and an orientation. If the orientation is "+", then the OrientableSurface is identical to the baseSurface. If the orientation is "-", then the OrientableSurface is a reference to a Surface with an up-normal that reverses the direction for this OrientableSurface, the sense of "the top of the surface".
 * 
 * <p>Java class for OrientableSurfaceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrientableSurfaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractSurfaceType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}baseSurface"/>
 *       &lt;/sequence>
 *       &lt;attribute name="orientation" type="{http://www.opengis.net/gml}SignType" default="+" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrientableSurfaceType", propOrder = {
    "baseSurface"
})
public class OrientableSurfaceType extends AbstractSurfaceType {

    @XmlElement(required = true)
    protected SurfacePropertyType baseSurface;
    @XmlAttribute(name = "orientation")
    protected String orientation;

    public OrientableSurfaceType() {
    }

    /**
     * References or contains the base surface (positive orientation).
     * 
     * @return
     *     possible object is
     *     {@link SurfacePropertyType }
     *     
     */
    public SurfacePropertyType getBaseSurface() {
        return baseSurface;
    }

    /**
     * Sets the value of the baseSurface property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurfacePropertyType }
     *     
     */
    public void setBaseSurface(SurfacePropertyType value) {
        this.baseSurface = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrientation() {
        if (orientation == null) {
            return "+";
        } else {
            return orientation;
        }
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrientation(String value) {
        this.orientation = value;
    }

    @Override
    public String toString() {
        return "OrientableSurfaceType{ " + "baseSurface = "
                + baseSurface + ", orientation = " + orientation + '}';
    }
}
