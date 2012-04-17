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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RangeSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RangeSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/gml}ValueArray" maxOccurs="unbounded"/>
 *         &lt;group ref="{http://www.opengis.net/gml}ScalarValueList" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/gml}DataBlock"/>
 *         &lt;element ref="{http://www.opengis.net/gml}File"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeSetType", propOrder = {
    "valueArray",
    "scalarValueList",
    "dataBlock",
    "file"
})
public class RangeSetType {

    @XmlElement(name = "ValueArray")
    private List<ValueArrayType> valueArray;
    @XmlElementRefs({
        @XmlElementRef(name = "CategoryList",
                       namespace = "http://www.opengis.net/gml",
                       type = JAXBElement.class),
        @XmlElementRef(name = "CountList",
                       namespace = "http://www.opengis.net/gml",
                       type = JAXBElement.class),
        @XmlElementRef(name = "BooleanList",
                       namespace = "http://www.opengis.net/gml",
                       type = JAXBElement.class),
        @XmlElementRef(name = "QuantityList",
                       namespace = "http://www.opengis.net/gml",
                       type = JAXBElement.class)
    })
    private List<JAXBElement<?>> scalarValueList;
    @XmlElement(name = "DataBlock")
    protected DataBlockType dataBlock;
    @XmlElement(name = "File")
    protected FileType file;

    public RangeSetType() {
    }

    /**
     * each member _Value holds a tuple or "row" from the equivalent table Gets the value of the valueArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueArrayType }
     * 
     * 
     */
    public List<ValueArrayType> getValueArray() {
        if (valueArray == null) {
            this.valueArray = new ArrayList<ValueArrayType>();
        }
        return this.valueArray;
    }

    /**
     * @param valueArray the valueArray to set
     */
    public void setValueArray(List<ValueArrayType> valueArray) {
        this.valueArray = valueArray;
    }

    /**
     * @param scalarValueList the scalarValueList to set
     */
    public void setScalarValueList(List<JAXBElement<?>> scalarValueList) {
        this.scalarValueList = scalarValueList;
    }

    /**
     * each list holds the complete set of one scalar component from the values - i.e. a "column" from the equivalent table Gets the value of the scalarValueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scalarValueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScalarValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CodeOrNullListType }{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * {@link JAXBElement }{@code <}{@link MeasureOrNullListType }{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getScalarValueList() {
        if (scalarValueList == null) {
            this.scalarValueList = new ArrayList<JAXBElement<?>>();
        }
        return this.scalarValueList;
    }

    /**
     * Its tuple list holds the values as space-separated tuples each of which contains comma-separated components, and the tuple structure is specified using the rangeParameters property.
     * 
     * @return
     *     possible object is
     *     {@link DataBlockType }
     *     
     */
    public DataBlockType getDataBlock() {
        return dataBlock;
    }

    /**
     * Sets the value of the dataBlock property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataBlockType }
     *     
     */
    public void setDataBlock(DataBlockType value) {
        this.dataBlock = value;
    }

    /**
     * a reference to an external source for the data, together with a description of how that external source is structured
     * 
     * @return
     *     possible object is
     *     {@link FileType }
     *     
     */
    public FileType getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileType }
     *     
     */
    public void setFile(FileType value) {
        this.file = value;
    }

    @Override
    public String toString() {
        return "RangeSetType{ " + "valueArray = " + valueArray
                + ", scalarValueList = " + scalarValueList
                + ", dataBlock = " + dataBlock + ", file = " + file + '}';
    }
}
