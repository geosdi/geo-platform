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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * An element of a covariance matrix.
 * 
 * <p>Java class for CovarianceElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CovarianceElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}rowIndex"/>
 *         &lt;element ref="{http://www.opengis.net/gml}columnIndex"/>
 *         &lt;element ref="{http://www.opengis.net/gml}covariance"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CovarianceElementType", propOrder = {
    "rowIndex",
    "columnIndex",
    "covariance"
})
public class CovarianceElementType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger rowIndex;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger columnIndex;
    protected double covariance;

    public CovarianceElementType() {
    }

    /**
     * Gets the value of the rowIndex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets the value of the rowIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRowIndex(BigInteger value) {
        this.rowIndex = value;
    }

    /**
     * Gets the value of the columnIndex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getColumnIndex() {
        return columnIndex;
    }

    /**
     * Sets the value of the columnIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setColumnIndex(BigInteger value) {
        this.columnIndex = value;
    }

    /**
     * Gets the value of the covariance property.
     * 
     */
    public double getCovariance() {
        return covariance;
    }

    /**
     * Sets the value of the covariance property.
     * 
     */
    public void setCovariance(double value) {
        this.covariance = value;
    }

    @Override
    public String toString() {
        return "CovarianceElementType{ " + "rowIndex = " + rowIndex
                + ", columnIndex = " + columnIndex
                + ", covariance = " + covariance + '}';
    }
}
