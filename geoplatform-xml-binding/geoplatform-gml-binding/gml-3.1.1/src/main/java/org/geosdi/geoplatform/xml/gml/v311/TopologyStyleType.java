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

/**
 * [complexType of] The style descriptor for topologies of a feature. Describes individual topology elements styles.
 * 
 * <p>Java class for TopologyStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TopologyStyleType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}BaseStyleDescriptorType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}symbol"/>
 *           &lt;element name="style" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}labelStyle" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="topologyProperty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="topologyType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopologyStyleType", propOrder = {
    "symbol",
    "style",
    "labelStyle"
})
public class TopologyStyleType extends BaseStyleDescriptorType {

    protected SymbolType symbol;
    protected String style;
    protected LabelStylePropertyType labelStyle;
    @XmlAttribute(name = "topologyProperty")
    protected String topologyProperty;
    @XmlAttribute(name = "topologyType")
    protected String topologyType;

    public TopologyStyleType() {
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link SymbolType }
     *     
     */
    public SymbolType getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link SymbolType }
     *     
     */
    public void setSymbol(SymbolType value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    /**
     * Gets the value of the labelStyle property.
     * 
     * @return
     *     possible object is
     *     {@link LabelStylePropertyType }
     *     
     */
    public LabelStylePropertyType getLabelStyle() {
        return labelStyle;
    }

    /**
     * Sets the value of the labelStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelStylePropertyType }
     *     
     */
    public void setLabelStyle(LabelStylePropertyType value) {
        this.labelStyle = value;
    }

    /**
     * Gets the value of the topologyProperty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopologyProperty() {
        return topologyProperty;
    }

    /**
     * Sets the value of the topologyProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopologyProperty(String value) {
        this.topologyProperty = value;
    }

    /**
     * Gets the value of the topologyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopologyType() {
        return topologyType;
    }

    /**
     * Sets the value of the topologyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopologyType(String value) {
        this.topologyType = value;
    }

    @Override
    public String toString() {
        return "TopologyStyleType{ " + "symbol = " + symbol
                + ", style = " + style + ", labelStyle = "
                + labelStyle + ", topologyProperty = " + topologyProperty
                + ", topologyType = " + topologyType + '}';
    }
}
