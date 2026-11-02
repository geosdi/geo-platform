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
// Generato il: 2026.11.02 alle 03:48:06 PM CET
//

package org.geosdi.geoplatform.xml.gfc;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.BooleanPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.LocalNamePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Class of real world phenomena with common properties -  - [ocl] - typeName realizes GF_FeatureType::typeName; - isAbstract realizes GF_FeatureType::isAbstract; - constrainedBy realizes GF_FeatureType::constrainedBy - [/ocl]
 * 
 * <p>Classe Java per FC_FeatureType_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FC_FeatureType_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="typeName" type="{http://www.isotc211.org/2005/gco}LocalName_PropertyType"/>
 *         <element name="definition" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="code" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="isAbstract" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="aliases" type="{http://www.isotc211.org/2005/gco}LocalName_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="inheritsFrom" type="{http://www.isotc211.org/2005/gfc}FC_InheritanceRelation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="inheritsTo" type="{http://www.isotc211.org/2005/gfc}FC_InheritanceRelation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="featureCatalogue" type="{http://www.isotc211.org/2005/gfc}FC_FeatureCatalogue_PropertyType"/>
 *         <element name="constrainedBy" type="{http://www.isotc211.org/2005/gfc}FC_Constraint_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="definitionReference" type="{http://www.isotc211.org/2005/gfc}FC_DefinitionReference_PropertyType" minOccurs="0"/>
 *         <element name="carrierOfCharacteristics" type="{http://www.isotc211.org/2005/gfc}FC_CarrierOfCharacteristics_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FC_FeatureType_Type", propOrder = {
    "typeName",
    "definition",
    "code",
    "isAbstract",
    "aliases",
    "inheritsFrom",
    "inheritsTo",
    "featureCatalogue",
    "constrainedBy",
    "definitionReference",
    "carrierOfCharacteristics"
})
@XmlSeeAlso({
    FCFeatureAssociationType.class
})
public class FCFeatureTypeType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected LocalNamePropertyType typeName;
    protected CharacterStringPropertyType definition;
    protected CharacterStringPropertyType code;
    @XmlElement(required = true)
    protected BooleanPropertyType isAbstract;
    protected List<LocalNamePropertyType> aliases;
    protected List<FCInheritanceRelationPropertyType> inheritsFrom;
    protected List<FCInheritanceRelationPropertyType> inheritsTo;
    @XmlElement(required = true)
    protected FCFeatureCataloguePropertyType featureCatalogue;
    protected List<FCConstraintPropertyType> constrainedBy;
    protected FCDefinitionReferencePropertyType definitionReference;
    protected List<FCCarrierOfCharacteristicsPropertyType> carrierOfCharacteristics;

    /**
     * Recupera il valore della proprietà typeName.
     * 
     * @return
     *     possible object is
     *     {@link LocalNamePropertyType }
     *     
     */
    public LocalNamePropertyType getTypeName() {
        return typeName;
    }

    /**
     * Imposta il valore della proprietà typeName.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalNamePropertyType }
     *     
     */
    public void setTypeName(LocalNamePropertyType value) {
        this.typeName = value;
    }

    public boolean isSetTypeName() {
        return (this.typeName!= null);
    }

    /**
     * Recupera il valore della proprietà definition.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDefinition() {
        return definition;
    }

    /**
     * Imposta il valore della proprietà definition.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDefinition(CharacterStringPropertyType value) {
        this.definition = value;
    }

    public boolean isSetDefinition() {
        return (this.definition!= null);
    }

    /**
     * Recupera il valore della proprietà code.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCode() {
        return code;
    }

    /**
     * Imposta il valore della proprietà code.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCode(CharacterStringPropertyType value) {
        this.code = value;
    }

    public boolean isSetCode() {
        return (this.code!= null);
    }

    /**
     * Recupera il valore della proprietà isAbstract.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getIsAbstract() {
        return isAbstract;
    }

    /**
     * Imposta il valore della proprietà isAbstract.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setIsAbstract(BooleanPropertyType value) {
        this.isAbstract = value;
    }

    public boolean isSetIsAbstract() {
        return (this.isAbstract!= null);
    }

    /**
     * Gets the value of the aliases property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the aliases property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAliases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalNamePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the aliases property.
     */
    public List<LocalNamePropertyType> getAliases() {
        if (aliases == null) {
            aliases = new ArrayList<>();
        }
        return this.aliases;
    }

    public boolean isSetAliases() {
        return ((this.aliases!= null)&&(!this.aliases.isEmpty()));
    }

    public void unsetAliases() {
        this.aliases = null;
    }

    /**
     * Gets the value of the inheritsFrom property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inheritsFrom property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getInheritsFrom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCInheritanceRelationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the inheritsFrom property.
     */
    public List<FCInheritanceRelationPropertyType> getInheritsFrom() {
        if (inheritsFrom == null) {
            inheritsFrom = new ArrayList<>();
        }
        return this.inheritsFrom;
    }

    public boolean isSetInheritsFrom() {
        return ((this.inheritsFrom!= null)&&(!this.inheritsFrom.isEmpty()));
    }

    public void unsetInheritsFrom() {
        this.inheritsFrom = null;
    }

    /**
     * Gets the value of the inheritsTo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inheritsTo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getInheritsTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCInheritanceRelationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the inheritsTo property.
     */
    public List<FCInheritanceRelationPropertyType> getInheritsTo() {
        if (inheritsTo == null) {
            inheritsTo = new ArrayList<>();
        }
        return this.inheritsTo;
    }

    public boolean isSetInheritsTo() {
        return ((this.inheritsTo!= null)&&(!this.inheritsTo.isEmpty()));
    }

    public void unsetInheritsTo() {
        this.inheritsTo = null;
    }

    /**
     * Recupera il valore della proprietà featureCatalogue.
     * 
     * @return
     *     possible object is
     *     {@link FCFeatureCataloguePropertyType }
     *     
     */
    public FCFeatureCataloguePropertyType getFeatureCatalogue() {
        return featureCatalogue;
    }

    /**
     * Imposta il valore della proprietà featureCatalogue.
     * 
     * @param value
     *     allowed object is
     *     {@link FCFeatureCataloguePropertyType }
     *     
     */
    public void setFeatureCatalogue(FCFeatureCataloguePropertyType value) {
        this.featureCatalogue = value;
    }

    public boolean isSetFeatureCatalogue() {
        return (this.featureCatalogue!= null);
    }

    /**
     * Gets the value of the constrainedBy property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the constrainedBy property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getConstrainedBy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCConstraintPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the constrainedBy property.
     */
    public List<FCConstraintPropertyType> getConstrainedBy() {
        if (constrainedBy == null) {
            constrainedBy = new ArrayList<>();
        }
        return this.constrainedBy;
    }

    public boolean isSetConstrainedBy() {
        return ((this.constrainedBy!= null)&&(!this.constrainedBy.isEmpty()));
    }

    public void unsetConstrainedBy() {
        this.constrainedBy = null;
    }

    /**
     * Recupera il valore della proprietà definitionReference.
     * 
     * @return
     *     possible object is
     *     {@link FCDefinitionReferencePropertyType }
     *     
     */
    public FCDefinitionReferencePropertyType getDefinitionReference() {
        return definitionReference;
    }

    /**
     * Imposta il valore della proprietà definitionReference.
     * 
     * @param value
     *     allowed object is
     *     {@link FCDefinitionReferencePropertyType }
     *     
     */
    public void setDefinitionReference(FCDefinitionReferencePropertyType value) {
        this.definitionReference = value;
    }

    public boolean isSetDefinitionReference() {
        return (this.definitionReference!= null);
    }

    /**
     * Gets the value of the carrierOfCharacteristics property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the carrierOfCharacteristics property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCarrierOfCharacteristics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCCarrierOfCharacteristicsPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the carrierOfCharacteristics property.
     */
    public List<FCCarrierOfCharacteristicsPropertyType> getCarrierOfCharacteristics() {
        if (carrierOfCharacteristics == null) {
            carrierOfCharacteristics = new ArrayList<>();
        }
        return this.carrierOfCharacteristics;
    }

    public boolean isSetCarrierOfCharacteristics() {
        return ((this.carrierOfCharacteristics!= null)&&(!this.carrierOfCharacteristics.isEmpty()));
    }

    public void unsetCarrierOfCharacteristics() {
        this.carrierOfCharacteristics = null;
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
        super.appendFields(locator, buffer, strategy);
        {
            LocalNamePropertyType theTypeName;
            theTypeName = this.getTypeName();
            strategy.appendField(locator, this, "typeName", buffer, theTypeName, this.isSetTypeName());
        }
        {
            CharacterStringPropertyType theDefinition;
            theDefinition = this.getDefinition();
            strategy.appendField(locator, this, "definition", buffer, theDefinition, this.isSetDefinition());
        }
        {
            CharacterStringPropertyType theCode;
            theCode = this.getCode();
            strategy.appendField(locator, this, "code", buffer, theCode, this.isSetCode());
        }
        {
            BooleanPropertyType theIsAbstract;
            theIsAbstract = this.getIsAbstract();
            strategy.appendField(locator, this, "isAbstract", buffer, theIsAbstract, this.isSetIsAbstract());
        }
        {
            List<LocalNamePropertyType> theAliases;
            theAliases = (this.isSetAliases()?this.getAliases():null);
            strategy.appendField(locator, this, "aliases", buffer, theAliases, this.isSetAliases());
        }
        {
            List<FCInheritanceRelationPropertyType> theInheritsFrom;
            theInheritsFrom = (this.isSetInheritsFrom()?this.getInheritsFrom():null);
            strategy.appendField(locator, this, "inheritsFrom", buffer, theInheritsFrom, this.isSetInheritsFrom());
        }
        {
            List<FCInheritanceRelationPropertyType> theInheritsTo;
            theInheritsTo = (this.isSetInheritsTo()?this.getInheritsTo():null);
            strategy.appendField(locator, this, "inheritsTo", buffer, theInheritsTo, this.isSetInheritsTo());
        }
        {
            FCFeatureCataloguePropertyType theFeatureCatalogue;
            theFeatureCatalogue = this.getFeatureCatalogue();
            strategy.appendField(locator, this, "featureCatalogue", buffer, theFeatureCatalogue, this.isSetFeatureCatalogue());
        }
        {
            List<FCConstraintPropertyType> theConstrainedBy;
            theConstrainedBy = (this.isSetConstrainedBy()?this.getConstrainedBy():null);
            strategy.appendField(locator, this, "constrainedBy", buffer, theConstrainedBy, this.isSetConstrainedBy());
        }
        {
            FCDefinitionReferencePropertyType theDefinitionReference;
            theDefinitionReference = this.getDefinitionReference();
            strategy.appendField(locator, this, "definitionReference", buffer, theDefinitionReference, this.isSetDefinitionReference());
        }
        {
            List<FCCarrierOfCharacteristicsPropertyType> theCarrierOfCharacteristics;
            theCarrierOfCharacteristics = (this.isSetCarrierOfCharacteristics()?this.getCarrierOfCharacteristics():null);
            strategy.appendField(locator, this, "carrierOfCharacteristics", buffer, theCarrierOfCharacteristics, this.isSetCarrierOfCharacteristics());
        }
        return buffer;
    }

    public void setAliases(List<LocalNamePropertyType> value) {
        this.aliases = null;
        if (value!= null) {
            List<LocalNamePropertyType> draftl = this.getAliases();
            draftl.addAll(value);
        }
    }

    public void setInheritsFrom(List<FCInheritanceRelationPropertyType> value) {
        this.inheritsFrom = null;
        if (value!= null) {
            List<FCInheritanceRelationPropertyType> draftl = this.getInheritsFrom();
            draftl.addAll(value);
        }
    }

    public void setInheritsTo(List<FCInheritanceRelationPropertyType> value) {
        this.inheritsTo = null;
        if (value!= null) {
            List<FCInheritanceRelationPropertyType> draftl = this.getInheritsTo();
            draftl.addAll(value);
        }
    }

    public void setConstrainedBy(List<FCConstraintPropertyType> value) {
        this.constrainedBy = null;
        if (value!= null) {
            List<FCConstraintPropertyType> draftl = this.getConstrainedBy();
            draftl.addAll(value);
        }
    }

    public void setCarrierOfCharacteristics(List<FCCarrierOfCharacteristicsPropertyType> value) {
        this.carrierOfCharacteristics = null;
        if (value!= null) {
            List<FCCarrierOfCharacteristicsPropertyType> draftl = this.getCarrierOfCharacteristics();
            draftl.addAll(value);
        }
    }

}
