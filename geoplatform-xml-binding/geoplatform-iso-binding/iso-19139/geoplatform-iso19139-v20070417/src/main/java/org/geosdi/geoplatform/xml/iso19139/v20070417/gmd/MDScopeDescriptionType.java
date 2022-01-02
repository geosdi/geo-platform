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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.ObjectReferencePropertyType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Description of the class of information covered by the information
 * 
 * <p>Java class for MD_ScopeDescription_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MD_ScopeDescription_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="attributes" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="features" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="featureInstances" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="attributeInstances" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="dataset" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         &lt;element name="other" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_ScopeDescription_Type", propOrder = {
    "attributes",
    "features",
    "featureInstances",
    "attributeInstances",
    "dataset",
    "other"
})
public class MDScopeDescriptionType
    implements ToString
{

    protected List<ObjectReferencePropertyType> attributes;
    protected List<ObjectReferencePropertyType> features;
    protected List<ObjectReferencePropertyType> featureInstances;
    protected List<ObjectReferencePropertyType> attributeInstances;
    protected CharacterStringPropertyType dataset;
    protected CharacterStringPropertyType other;

    /**
     * Gets the value of the attributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * 
     * 
     */
    public List<ObjectReferencePropertyType> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<ObjectReferencePropertyType>();
        }
        return this.attributes;
    }

    public boolean isSetAttributes() {
        return ((this.attributes!= null)&&(!this.attributes.isEmpty()));
    }

    public void unsetAttributes() {
        this.attributes = null;
    }

    /**
     * Gets the value of the features property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the features property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * 
     * 
     */
    public List<ObjectReferencePropertyType> getFeatures() {
        if (features == null) {
            features = new ArrayList<ObjectReferencePropertyType>();
        }
        return this.features;
    }

    public boolean isSetFeatures() {
        return ((this.features!= null)&&(!this.features.isEmpty()));
    }

    public void unsetFeatures() {
        this.features = null;
    }

    /**
     * Gets the value of the featureInstances property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureInstances property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureInstances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * 
     * 
     */
    public List<ObjectReferencePropertyType> getFeatureInstances() {
        if (featureInstances == null) {
            featureInstances = new ArrayList<ObjectReferencePropertyType>();
        }
        return this.featureInstances;
    }

    public boolean isSetFeatureInstances() {
        return ((this.featureInstances!= null)&&(!this.featureInstances.isEmpty()));
    }

    public void unsetFeatureInstances() {
        this.featureInstances = null;
    }

    /**
     * Gets the value of the attributeInstances property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeInstances property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeInstances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * 
     * 
     */
    public List<ObjectReferencePropertyType> getAttributeInstances() {
        if (attributeInstances == null) {
            attributeInstances = new ArrayList<ObjectReferencePropertyType>();
        }
        return this.attributeInstances;
    }

    public boolean isSetAttributeInstances() {
        return ((this.attributeInstances!= null)&&(!this.attributeInstances.isEmpty()));
    }

    public void unsetAttributeInstances() {
        this.attributeInstances = null;
    }

    /**
     * Gets the value of the dataset property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDataset() {
        return dataset;
    }

    /**
     * Sets the value of the dataset property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDataset(CharacterStringPropertyType value) {
        this.dataset = value;
    }

    public boolean isSetDataset() {
        return (this.dataset!= null);
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setOther(CharacterStringPropertyType value) {
        this.other = value;
    }

    public boolean isSetOther() {
        return (this.other!= null);
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
        {
            List<ObjectReferencePropertyType> theAttributes;
            theAttributes = (this.isSetAttributes()?this.getAttributes():null);
            strategy.appendField(locator, this, "attributes", buffer, theAttributes);
        }
        {
            List<ObjectReferencePropertyType> theFeatures;
            theFeatures = (this.isSetFeatures()?this.getFeatures():null);
            strategy.appendField(locator, this, "features", buffer, theFeatures);
        }
        {
            List<ObjectReferencePropertyType> theFeatureInstances;
            theFeatureInstances = (this.isSetFeatureInstances()?this.getFeatureInstances():null);
            strategy.appendField(locator, this, "featureInstances", buffer, theFeatureInstances);
        }
        {
            List<ObjectReferencePropertyType> theAttributeInstances;
            theAttributeInstances = (this.isSetAttributeInstances()?this.getAttributeInstances():null);
            strategy.appendField(locator, this, "attributeInstances", buffer, theAttributeInstances);
        }
        {
            CharacterStringPropertyType theDataset;
            theDataset = this.getDataset();
            strategy.appendField(locator, this, "dataset", buffer, theDataset);
        }
        {
            CharacterStringPropertyType theOther;
            theOther = this.getOther();
            strategy.appendField(locator, this, "other", buffer, theOther);
        }
        return buffer;
    }

    public void setAttributes(List<ObjectReferencePropertyType> value) {
        this.attributes = null;
        List<ObjectReferencePropertyType> draftl = this.getAttributes();
        draftl.addAll(value);
    }

    public void setFeatures(List<ObjectReferencePropertyType> value) {
        this.features = null;
        List<ObjectReferencePropertyType> draftl = this.getFeatures();
        draftl.addAll(value);
    }

    public void setFeatureInstances(List<ObjectReferencePropertyType> value) {
        this.featureInstances = null;
        List<ObjectReferencePropertyType> draftl = this.getFeatureInstances();
        draftl.addAll(value);
    }

    public void setAttributeInstances(List<ObjectReferencePropertyType> value) {
        this.attributeInstances = null;
        List<ObjectReferencePropertyType> draftl = this.getAttributeInstances();
        draftl.addAll(value);
    }

}
