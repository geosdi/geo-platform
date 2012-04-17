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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * [complexType of] The style descriptor for features.
 * 
 * <p>Java class for FeatureStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FeatureStyleType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       &lt;sequence>
 *         &lt;element name="featureConstraint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}geometryStyle" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}topologyStyle" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}labelStyle" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="featureType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="baseType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="queryGrammar" type="{http://www.opengis.net/gml}QueryGrammarEnumeration" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureStyleType", propOrder = {
    "featureConstraint",
    "geometryStyle",
    "topologyStyle",
    "labelStyle"
})
public class FeatureStyleType extends AbstractGMLType {

    protected String featureConstraint;
    private List<GeometryStylePropertyType> geometryStyle;
    private List<TopologyStylePropertyType> topologyStyle;
    protected LabelStylePropertyType labelStyle;
    @XmlAttribute(name = "featureType")
    protected String featureType;
    @XmlAttribute(name = "baseType")
    protected String baseType;
    @XmlAttribute(name = "queryGrammar")
    protected QueryGrammarEnumeration queryGrammar;

    public FeatureStyleType() {
    }

    /**
     * Gets the value of the featureConstraint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureConstraint() {
        return featureConstraint;
    }

    /**
     * Sets the value of the featureConstraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureConstraint(String value) {
        this.featureConstraint = value;
    }

    /**
     * @param geometryStyle the geometryStyle to set
     */
    public void setGeometryStyle(List<GeometryStylePropertyType> geometryStyle) {
        this.geometryStyle = geometryStyle;
    }

    /**
     * Gets the value of the geometryStyle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geometryStyle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeometryStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeometryStylePropertyType }
     * 
     * 
     */
    public List<GeometryStylePropertyType> getGeometryStyle() {
        if (geometryStyle == null) {
            this.geometryStyle = new ArrayList<GeometryStylePropertyType>();
        }
        return this.geometryStyle;
    }

    /**
     * @param topologyStyle the topologyStyle to set
     */
    public void setTopologyStyle(List<TopologyStylePropertyType> topologyStyle) {
        this.topologyStyle = topologyStyle;
    }

    /**
     * Gets the value of the topologyStyle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topologyStyle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopologyStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopologyStylePropertyType }
     * 
     * 
     */
    public List<TopologyStylePropertyType> getTopologyStyle() {
        if (topologyStyle == null) {
            this.topologyStyle = new ArrayList<TopologyStylePropertyType>();
        }
        return this.topologyStyle;
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
     * Gets the value of the featureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureType() {
        return featureType;
    }

    /**
     * Sets the value of the featureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureType(String value) {
        this.featureType = value;
    }

    /**
     * Gets the value of the baseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseType() {
        return baseType;
    }

    /**
     * Sets the value of the baseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseType(String value) {
        this.baseType = value;
    }

    /**
     * Gets the value of the queryGrammar property.
     * 
     * @return
     *     possible object is
     *     {@link QueryGrammarEnumeration }
     *     
     */
    public QueryGrammarEnumeration getQueryGrammar() {
        return queryGrammar;
    }

    /**
     * Sets the value of the queryGrammar property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryGrammarEnumeration }
     *     
     */
    public void setQueryGrammar(QueryGrammarEnumeration value) {
        this.queryGrammar = value;
    }

    @Override
    public String toString() {
        return "FeatureStyleType{ " + "featureConstraint = " + featureConstraint
                + ", geometryStyle = " + geometryStyle
                + ", topologyStyle = " + topologyStyle
                + ", labelStyle = " + labelStyle
                + ", featureType = " + featureType + ", baseType = " + baseType
                + ", queryGrammar = " + queryGrammar + '}';
    }
}
