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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Used to vary individual graphic parameters and attributes of the style, symbol or text.
 * 
 * <p>Java class for StyleVariationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StyleVariationType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="styleProperty" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="featurePropertyRange" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StyleVariationType", propOrder = {
    "value"
})
public class StyleVariationType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "styleProperty", required = true)
    protected String styleProperty;
    @XmlAttribute(name = "featurePropertyRange")
    protected String featurePropertyRange;

    public StyleVariationType() {
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the styleProperty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyleProperty() {
        return styleProperty;
    }

    /**
     * Sets the value of the styleProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyleProperty(String value) {
        this.styleProperty = value;
    }

    /**
     * Gets the value of the featurePropertyRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeaturePropertyRange() {
        return featurePropertyRange;
    }

    /**
     * Sets the value of the featurePropertyRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeaturePropertyRange(String value) {
        this.featurePropertyRange = value;
    }

    @Override
    public String toString() {
        return "StyleVariationType{ " + "value = " + value
                + ", styleProperty = " + styleProperty
                + ", featurePropertyRange = " + featurePropertyRange + '}';
    }
}
