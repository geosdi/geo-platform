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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Aggregate value built from other Values using the Composite pattern. It contains zero or an arbitrary number of valueComponent elements, and zero or one valueComponents elements.  It may be used for strongly coupled aggregates (vectors, tensors) or for arbitrary collections of values.
 * 
 * <p>Java class for CompositeValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompositeValueType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}valueComponent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}valueComponents" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeValueType", propOrder = {
    "valueComponent",
    "valueComponents"
})
@XmlSeeAlso({
    ValueArrayType.class
})
public class CompositeValueType extends AbstractGMLType {

    private List<ValuePropertyType> valueComponent;
    protected ValueArrayPropertyType valueComponents;

    public CompositeValueType() {
    }

    /**
     * @param valueComponent the valueComponent to set
     */
    public void setValueComponent(List<ValuePropertyType> valueComponent) {
        this.valueComponent = valueComponent;
    }

    /**
     * Gets the value of the valueComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuePropertyType }
     * 
     * 
     */
    public List<ValuePropertyType> getValueComponent() {
        if (valueComponent == null) {
            this.valueComponent = new ArrayList<ValuePropertyType>();
        }
        return this.valueComponent;
    }

    /**
     * Gets the value of the valueComponents property.
     * 
     * @return
     *     possible object is
     *     {@link ValueArrayPropertyType }
     *     
     */
    public ValueArrayPropertyType getValueComponents() {
        return valueComponents;
    }

    /**
     * Sets the value of the valueComponents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueArrayPropertyType }
     *     
     */
    public void setValueComponents(ValueArrayPropertyType value) {
        this.valueComponents = value;
    }

    @Override
    public String toString() {
        return "CompositeValueType{ " + "valueComponent = " + valueComponent 
                + ", valueComponents = " + valueComponents + '}';
    }
}
