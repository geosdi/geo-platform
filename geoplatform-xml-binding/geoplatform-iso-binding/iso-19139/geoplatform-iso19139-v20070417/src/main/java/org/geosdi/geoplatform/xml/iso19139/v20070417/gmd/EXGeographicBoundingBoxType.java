/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 03:12:11 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.DecimalPropertyType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Geographic area of the entire dataset referenced to WGS 84
 * 
 * <p>Java class for EX_GeographicBoundingBox_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EX_GeographicBoundingBox_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.isotc211.org/2005/gmd}AbstractEX_GeographicExtent_Type">
 *       &lt;sequence>
 *         &lt;element name="westBoundLongitude" type="{http://www.isotc211.org/2005/gco}Decimal_PropertyType"/>
 *         &lt;element name="eastBoundLongitude" type="{http://www.isotc211.org/2005/gco}Decimal_PropertyType"/>
 *         &lt;element name="southBoundLatitude" type="{http://www.isotc211.org/2005/gco}Decimal_PropertyType"/>
 *         &lt;element name="northBoundLatitude" type="{http://www.isotc211.org/2005/gco}Decimal_PropertyType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EX_GeographicBoundingBox_Type", propOrder = {
    "westBoundLongitude",
    "eastBoundLongitude",
    "southBoundLatitude",
    "northBoundLatitude"
})
public class EXGeographicBoundingBoxType
    extends AbstractEXGeographicExtentType
    implements ToString
{

    @XmlElement(required = true)
    protected DecimalPropertyType westBoundLongitude;
    @XmlElement(required = true)
    protected DecimalPropertyType eastBoundLongitude;
    @XmlElement(required = true)
    protected DecimalPropertyType southBoundLatitude;
    @XmlElement(required = true)
    protected DecimalPropertyType northBoundLatitude;

    /**
     * Gets the value of the westBoundLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link DecimalPropertyType }
     *     
     */
    public DecimalPropertyType getWestBoundLongitude() {
        return westBoundLongitude;
    }

    /**
     * Sets the value of the westBoundLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link DecimalPropertyType }
     *     
     */
    public void setWestBoundLongitude(DecimalPropertyType value) {
        this.westBoundLongitude = value;
    }

    public boolean isSetWestBoundLongitude() {
        return (this.westBoundLongitude!= null);
    }

    /**
     * Gets the value of the eastBoundLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link DecimalPropertyType }
     *     
     */
    public DecimalPropertyType getEastBoundLongitude() {
        return eastBoundLongitude;
    }

    /**
     * Sets the value of the eastBoundLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link DecimalPropertyType }
     *     
     */
    public void setEastBoundLongitude(DecimalPropertyType value) {
        this.eastBoundLongitude = value;
    }

    public boolean isSetEastBoundLongitude() {
        return (this.eastBoundLongitude!= null);
    }

    /**
     * Gets the value of the southBoundLatitude property.
     * 
     * @return
     *     possible object is
     *     {@link DecimalPropertyType }
     *     
     */
    public DecimalPropertyType getSouthBoundLatitude() {
        return southBoundLatitude;
    }

    /**
     * Sets the value of the southBoundLatitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link DecimalPropertyType }
     *     
     */
    public void setSouthBoundLatitude(DecimalPropertyType value) {
        this.southBoundLatitude = value;
    }

    public boolean isSetSouthBoundLatitude() {
        return (this.southBoundLatitude!= null);
    }

    /**
     * Gets the value of the northBoundLatitude property.
     * 
     * @return
     *     possible object is
     *     {@link DecimalPropertyType }
     *     
     */
    public DecimalPropertyType getNorthBoundLatitude() {
        return northBoundLatitude;
    }

    /**
     * Sets the value of the northBoundLatitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link DecimalPropertyType }
     *     
     */
    public void setNorthBoundLatitude(DecimalPropertyType value) {
        this.northBoundLatitude = value;
    }

    public boolean isSetNorthBoundLatitude() {
        return (this.northBoundLatitude!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            DecimalPropertyType theWestBoundLongitude;
            theWestBoundLongitude = this.getWestBoundLongitude();
            strategy.appendField(locator, this, "westBoundLongitude", buffer, theWestBoundLongitude);
        }
        {
            DecimalPropertyType theEastBoundLongitude;
            theEastBoundLongitude = this.getEastBoundLongitude();
            strategy.appendField(locator, this, "eastBoundLongitude", buffer, theEastBoundLongitude);
        }
        {
            DecimalPropertyType theSouthBoundLatitude;
            theSouthBoundLatitude = this.getSouthBoundLatitude();
            strategy.appendField(locator, this, "southBoundLatitude", buffer, theSouthBoundLatitude);
        }
        {
            DecimalPropertyType theNorthBoundLatitude;
            theNorthBoundLatitude = this.getNorthBoundLatitude();
            strategy.appendField(locator, this, "northBoundLatitude", buffer, theNorthBoundLatitude);
        }
        return buffer;
    }

}
