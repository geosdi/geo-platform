/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Generated on: 2015.06.17 at 04:41:23 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20060504.gmd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Information about spatial, vertical, and temporal extent
 * 
 * <p>Java class for EX_Extent_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EX_Extent_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         &lt;element name="geographicElement" type="{http://www.isotc211.org/2005/gmd}EX_GeographicExtent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="temporalElement" type="{http://www.isotc211.org/2005/gmd}EX_TemporalExtent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="verticalElement" type="{http://www.isotc211.org/2005/gmd}EX_VerticalExtent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EX_Extent_Type", propOrder = {
    "description",
    "geographicElement",
    "temporalElement",
    "verticalElement"
})
public class EXExtentType
    extends AbstractObjectType
    implements ToString
{

    protected CharacterStringPropertyType description;
    protected List<EXGeographicExtentPropertyType> geographicElement;
    protected List<EXTemporalExtentPropertyType> temporalElement;
    protected List<EXVerticalExtentPropertyType> verticalElement;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDescription(CharacterStringPropertyType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the geographicElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geographicElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeographicElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXGeographicExtentPropertyType }
     * 
     * 
     */
    public List<EXGeographicExtentPropertyType> getGeographicElement() {
        if (geographicElement == null) {
            geographicElement = new ArrayList<EXGeographicExtentPropertyType>();
        }
        return this.geographicElement;
    }

    public boolean isSetGeographicElement() {
        return ((this.geographicElement!= null)&&(!this.geographicElement.isEmpty()));
    }

    public void unsetGeographicElement() {
        this.geographicElement = null;
    }

    /**
     * Gets the value of the temporalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the temporalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemporalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXTemporalExtentPropertyType }
     * 
     * 
     */
    public List<EXTemporalExtentPropertyType> getTemporalElement() {
        if (temporalElement == null) {
            temporalElement = new ArrayList<EXTemporalExtentPropertyType>();
        }
        return this.temporalElement;
    }

    public boolean isSetTemporalElement() {
        return ((this.temporalElement!= null)&&(!this.temporalElement.isEmpty()));
    }

    public void unsetTemporalElement() {
        this.temporalElement = null;
    }

    /**
     * Gets the value of the verticalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verticalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerticalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXVerticalExtentPropertyType }
     * 
     * 
     */
    public List<EXVerticalExtentPropertyType> getVerticalElement() {
        if (verticalElement == null) {
            verticalElement = new ArrayList<EXVerticalExtentPropertyType>();
        }
        return this.verticalElement;
    }

    public boolean isSetVerticalElement() {
        return ((this.verticalElement!= null)&&(!this.verticalElement.isEmpty()));
    }

    public void unsetVerticalElement() {
        this.verticalElement = null;
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            List<EXGeographicExtentPropertyType> theGeographicElement;
            theGeographicElement = (this.isSetGeographicElement()?this.getGeographicElement():null);
            strategy.appendField(locator, this, "geographicElement", buffer, theGeographicElement);
        }
        {
            List<EXTemporalExtentPropertyType> theTemporalElement;
            theTemporalElement = (this.isSetTemporalElement()?this.getTemporalElement():null);
            strategy.appendField(locator, this, "temporalElement", buffer, theTemporalElement);
        }
        {
            List<EXVerticalExtentPropertyType> theVerticalElement;
            theVerticalElement = (this.isSetVerticalElement()?this.getVerticalElement():null);
            strategy.appendField(locator, this, "verticalElement", buffer, theVerticalElement);
        }
        return buffer;
    }

    public void setGeographicElement(List<EXGeographicExtentPropertyType> value) {
        this.geographicElement = null;
        List<EXGeographicExtentPropertyType> draftl = this.getGeographicElement();
        draftl.addAll(value);
    }

    public void setTemporalElement(List<EXTemporalExtentPropertyType> value) {
        this.temporalElement = null;
        List<EXTemporalExtentPropertyType> draftl = this.getTemporalElement();
        draftl.addAll(value);
    }

    public void setVerticalElement(List<EXVerticalExtentPropertyType> value) {
        this.verticalElement = null;
        List<EXVerticalExtentPropertyType> draftl = this.getVerticalElement();
        draftl.addAll(value);
    }

}
