/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.ObjectReferencePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Description of the class of information covered by the information
 * 
 * <p>Classe Java per MD_ScopeDescription_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_ScopeDescription_Type">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="attributes" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         <element name="features" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         <element name="featureInstances" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         <element name="attributeInstances" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded"/>
 *         <element name="dataset" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="other" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
public class MDScopeDescriptionType implements ToString
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the attributes property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the attributes property.
     */
    public List<ObjectReferencePropertyType> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the features property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the features property.
     */
    public List<ObjectReferencePropertyType> getFeatures() {
        if (features == null) {
            features = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureInstances property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureInstances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureInstances property.
     */
    public List<ObjectReferencePropertyType> getFeatureInstances() {
        if (featureInstances == null) {
            featureInstances = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the attributeInstances property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAttributeInstances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the attributeInstances property.
     */
    public List<ObjectReferencePropertyType> getAttributeInstances() {
        if (attributeInstances == null) {
            attributeInstances = new ArrayList<>();
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
     * Recupera il valore della proprietà dataset.
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
     * Imposta il valore della proprietà dataset.
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
     * Recupera il valore della proprietà other.
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
     * Imposta il valore della proprietà other.
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

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            List<ObjectReferencePropertyType> theAttributes;
            theAttributes = (this.isSetAttributes()?this.getAttributes():null);
            strategy.appendField(locator, this, "attributes", buffer, theAttributes, this.isSetAttributes());
        }
        {
            List<ObjectReferencePropertyType> theFeatures;
            theFeatures = (this.isSetFeatures()?this.getFeatures():null);
            strategy.appendField(locator, this, "features", buffer, theFeatures, this.isSetFeatures());
        }
        {
            List<ObjectReferencePropertyType> theFeatureInstances;
            theFeatureInstances = (this.isSetFeatureInstances()?this.getFeatureInstances():null);
            strategy.appendField(locator, this, "featureInstances", buffer, theFeatureInstances, this.isSetFeatureInstances());
        }
        {
            List<ObjectReferencePropertyType> theAttributeInstances;
            theAttributeInstances = (this.isSetAttributeInstances()?this.getAttributeInstances():null);
            strategy.appendField(locator, this, "attributeInstances", buffer, theAttributeInstances, this.isSetAttributeInstances());
        }
        {
            CharacterStringPropertyType theDataset;
            theDataset = this.getDataset();
            strategy.appendField(locator, this, "dataset", buffer, theDataset, this.isSetDataset());
        }
        {
            CharacterStringPropertyType theOther;
            theOther = this.getOther();
            strategy.appendField(locator, this, "other", buffer, theOther, this.isSetOther());
        }
        return buffer;
    }

    public void setAttributes(List<ObjectReferencePropertyType> value) {
        this.attributes = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getAttributes();
            draftl.addAll(value);
        }
    }

    public void setFeatures(List<ObjectReferencePropertyType> value) {
        this.features = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getFeatures();
            draftl.addAll(value);
        }
    }

    public void setFeatureInstances(List<ObjectReferencePropertyType> value) {
        this.featureInstances = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getFeatureInstances();
            draftl.addAll(value);
        }
    }

    public void setAttributeInstances(List<ObjectReferencePropertyType> value) {
        this.attributeInstances = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getAttributeInstances();
            draftl.addAll(value);
        }
    }

}
