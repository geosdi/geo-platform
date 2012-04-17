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
 * An engineering coordinate reference system applied to locations in images. Image coordinate reference systems are treated as a separate sub-type because a separate user community exists for images with its own terms of reference. 
 * 
 * <p>Java class for ImageCRSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImageCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractReferenceSystemType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}usesCartesianCS"/>
 *           &lt;element ref="{http://www.opengis.net/gml}usesObliqueCartesianCS"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}usesImageDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageCRSType", propOrder = {
    "usesCartesianCS",
    "usesObliqueCartesianCS",
    "usesImageDatum"
})
public class ImageCRSType extends AbstractReferenceSystemType {

    protected CartesianCSRefType usesCartesianCS;
    protected ObliqueCartesianCSRefType usesObliqueCartesianCS;
    @XmlElement(required = true)
    protected ImageDatumRefType usesImageDatum;

    public ImageCRSType() {
    }

    /**
     * Gets the value of the usesCartesianCS property.
     * 
     * @return
     *     possible object is
     *     {@link CartesianCSRefType }
     *     
     */
    public CartesianCSRefType getUsesCartesianCS() {
        return usesCartesianCS;
    }

    /**
     * Sets the value of the usesCartesianCS property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartesianCSRefType }
     *     
     */
    public void setUsesCartesianCS(CartesianCSRefType value) {
        this.usesCartesianCS = value;
    }

    /**
     * Gets the value of the usesObliqueCartesianCS property.
     * 
     * @return
     *     possible object is
     *     {@link ObliqueCartesianCSRefType }
     *     
     */
    public ObliqueCartesianCSRefType getUsesObliqueCartesianCS() {
        return usesObliqueCartesianCS;
    }

    /**
     * Sets the value of the usesObliqueCartesianCS property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObliqueCartesianCSRefType }
     *     
     */
    public void setUsesObliqueCartesianCS(ObliqueCartesianCSRefType value) {
        this.usesObliqueCartesianCS = value;
    }

    /**
     * Gets the value of the usesImageDatum property.
     * 
     * @return
     *     possible object is
     *     {@link ImageDatumRefType }
     *     
     */
    public ImageDatumRefType getUsesImageDatum() {
        return usesImageDatum;
    }

    /**
     * Sets the value of the usesImageDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageDatumRefType }
     *     
     */
    public void setUsesImageDatum(ImageDatumRefType value) {
        this.usesImageDatum = value;
    }

    @Override
    public String toString() {
        return "ImageCRSType{ " + "usesCartesianCS = " + usesCartesianCS
                + ", usesObliqueCartesianCS = " + usesObliqueCartesianCS
                + ", usesImageDatum = " + usesImageDatum + '}';
    }
}
