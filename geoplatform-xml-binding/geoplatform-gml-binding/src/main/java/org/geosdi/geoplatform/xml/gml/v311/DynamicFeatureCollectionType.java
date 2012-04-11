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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * A dynamic feature collection may possess a history and/or a timestamp.
 * 
 * <p>Java class for DynamicFeatureCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DynamicFeatureCollectionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}FeatureCollectionType">
 *       &lt;group ref="{http://www.opengis.net/gml}dynamicProperties"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicFeatureCollectionType", propOrder = {
    "validTime",
    "history",
    "dataSource"
})
public class DynamicFeatureCollectionType extends FeatureCollectionType {

    protected TimePrimitivePropertyType validTime;
    @XmlElementRef(name = "history", namespace = "http://www.opengis.net/gml",
                   type = JAXBElement.class)
    protected JAXBElement<? extends HistoryPropertyType> history;
    protected StringOrRefType dataSource;

    public DynamicFeatureCollectionType() {
    }

    /**
     * Gets the value of the validTime property.
     * 
     * @return
     *     possible object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public TimePrimitivePropertyType getValidTime() {
        return validTime;
    }

    /**
     * Sets the value of the validTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public void setValidTime(TimePrimitivePropertyType value) {
        this.validTime = value;
    }

    /**
     * Gets the value of the history property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TrackType }{@code >}
     *     
     */
    public JAXBElement<? extends HistoryPropertyType> getHistory() {
        return history;
    }

    /**
     * Sets the value of the history property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TrackType }{@code >}
     *     
     */
    public void setHistory(JAXBElement<? extends HistoryPropertyType> value) {
        this.history = ((JAXBElement<? extends HistoryPropertyType>) value);
    }

    /**
     * Gets the value of the dataSource property.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDataSource() {
        return dataSource;
    }

    /**
     * Sets the value of the dataSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setDataSource(StringOrRefType value) {
        this.dataSource = value;
    }

    @Override
    public String toString() {
        return "DynamicFeatureCollectionType{ " + "validTime = " + validTime
                + ", history = " + history + ", dataSource = " + dataSource + '}';
    }
}
