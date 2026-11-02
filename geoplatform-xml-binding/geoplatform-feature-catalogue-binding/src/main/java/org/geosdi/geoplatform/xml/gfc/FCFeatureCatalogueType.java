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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CIResponsiblePartyPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmx.AbstractCTCatalogueType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * A feature catalogue contains the definition of some number feature types with other information necessary for their definitions.
 * 
 * <p>Classe Java per FC_FeatureCatalogue_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FC_FeatureCatalogue_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmx}AbstractCT_Catalogue_Type">
 *       <sequence>
 *         <element name="producer" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType"/>
 *         <element name="functionalLanguage" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="featureType" type="{http://www.isotc211.org/2005/gfc}FC_FeatureType_PropertyType" maxOccurs="unbounded"/>
 *         <element name="definitionSource" type="{http://www.isotc211.org/2005/gfc}FC_DefinitionSource_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="globalProperty" type="{http://www.isotc211.org/2005/gfc}FC_PropertyType_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="inheritanceRelation" type="{http://www.isotc211.org/2005/gfc}FC_InheritanceRelation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FC_FeatureCatalogue_Type", propOrder = {
    "producer",
    "functionalLanguage",
    "featureType",
    "definitionSource",
    "globalProperty",
    "inheritanceRelation"
})
@XmlRootElement(name = "FC_FeatureCatalogue")
public class FCFeatureCatalogueType
    extends AbstractCTCatalogueType
    implements ToString
{

    @XmlElement(required = true)
    protected CIResponsiblePartyPropertyType producer;
    protected CharacterStringPropertyType functionalLanguage;
    @XmlElement(required = true)
    protected List<FCFeatureTypePropertyType> featureType;
    protected List<FCDefinitionSourcePropertyType> definitionSource;
    protected List<FCPropertyTypePropertyType> globalProperty;
    protected List<FCInheritanceRelationPropertyType> inheritanceRelation;

    /**
     * Recupera il valore della proprietà producer.
     * 
     * @return
     *     possible object is
     *     {@link CIResponsiblePartyPropertyType }
     *     
     */
    public CIResponsiblePartyPropertyType getProducer() {
        return producer;
    }

    /**
     * Imposta il valore della proprietà producer.
     * 
     * @param value
     *     allowed object is
     *     {@link CIResponsiblePartyPropertyType }
     *     
     */
    public void setProducer(CIResponsiblePartyPropertyType value) {
        this.producer = value;
    }

    public boolean isSetProducer() {
        return (this.producer!= null);
    }

    /**
     * Recupera il valore della proprietà functionalLanguage.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getFunctionalLanguage() {
        return functionalLanguage;
    }

    /**
     * Imposta il valore della proprietà functionalLanguage.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setFunctionalLanguage(CharacterStringPropertyType value) {
        this.functionalLanguage = value;
    }

    public boolean isSetFunctionalLanguage() {
        return (this.functionalLanguage!= null);
    }

    /**
     * Gets the value of the featureType property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureType property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCFeatureTypePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureType property.
     */
    public List<FCFeatureTypePropertyType> getFeatureType() {
        if (featureType == null) {
            featureType = new ArrayList<>();
        }
        return this.featureType;
    }

    public boolean isSetFeatureType() {
        return ((this.featureType!= null)&&(!this.featureType.isEmpty()));
    }

    public void unsetFeatureType() {
        this.featureType = null;
    }

    /**
     * Gets the value of the definitionSource property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the definitionSource property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDefinitionSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCDefinitionSourcePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the definitionSource property.
     */
    public List<FCDefinitionSourcePropertyType> getDefinitionSource() {
        if (definitionSource == null) {
            definitionSource = new ArrayList<>();
        }
        return this.definitionSource;
    }

    public boolean isSetDefinitionSource() {
        return ((this.definitionSource!= null)&&(!this.definitionSource.isEmpty()));
    }

    public void unsetDefinitionSource() {
        this.definitionSource = null;
    }

    /**
     * Gets the value of the globalProperty property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the globalProperty property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getGlobalProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCPropertyTypePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the globalProperty property.
     */
    public List<FCPropertyTypePropertyType> getGlobalProperty() {
        if (globalProperty == null) {
            globalProperty = new ArrayList<>();
        }
        return this.globalProperty;
    }

    public boolean isSetGlobalProperty() {
        return ((this.globalProperty!= null)&&(!this.globalProperty.isEmpty()));
    }

    public void unsetGlobalProperty() {
        this.globalProperty = null;
    }

    /**
     * Gets the value of the inheritanceRelation property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inheritanceRelation property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getInheritanceRelation().add(newItem);
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
     *     The value of the inheritanceRelation property.
     */
    public List<FCInheritanceRelationPropertyType> getInheritanceRelation() {
        if (inheritanceRelation == null) {
            inheritanceRelation = new ArrayList<>();
        }
        return this.inheritanceRelation;
    }

    public boolean isSetInheritanceRelation() {
        return ((this.inheritanceRelation!= null)&&(!this.inheritanceRelation.isEmpty()));
    }

    public void unsetInheritanceRelation() {
        this.inheritanceRelation = null;
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
            CIResponsiblePartyPropertyType theProducer;
            theProducer = this.getProducer();
            strategy.appendField(locator, this, "producer", buffer, theProducer, this.isSetProducer());
        }
        {
            CharacterStringPropertyType theFunctionalLanguage;
            theFunctionalLanguage = this.getFunctionalLanguage();
            strategy.appendField(locator, this, "functionalLanguage", buffer, theFunctionalLanguage, this.isSetFunctionalLanguage());
        }
        {
            List<FCFeatureTypePropertyType> theFeatureType;
            theFeatureType = (this.isSetFeatureType()?this.getFeatureType():null);
            strategy.appendField(locator, this, "featureType", buffer, theFeatureType, this.isSetFeatureType());
        }
        {
            List<FCDefinitionSourcePropertyType> theDefinitionSource;
            theDefinitionSource = (this.isSetDefinitionSource()?this.getDefinitionSource():null);
            strategy.appendField(locator, this, "definitionSource", buffer, theDefinitionSource, this.isSetDefinitionSource());
        }
        {
            List<FCPropertyTypePropertyType> theGlobalProperty;
            theGlobalProperty = (this.isSetGlobalProperty()?this.getGlobalProperty():null);
            strategy.appendField(locator, this, "globalProperty", buffer, theGlobalProperty, this.isSetGlobalProperty());
        }
        {
            List<FCInheritanceRelationPropertyType> theInheritanceRelation;
            theInheritanceRelation = (this.isSetInheritanceRelation()?this.getInheritanceRelation():null);
            strategy.appendField(locator, this, "inheritanceRelation", buffer, theInheritanceRelation, this.isSetInheritanceRelation());
        }
        return buffer;
    }

    public void setFeatureType(List<FCFeatureTypePropertyType> value) {
        this.featureType = null;
        if (value!= null) {
            List<FCFeatureTypePropertyType> draftl = this.getFeatureType();
            draftl.addAll(value);
        }
    }

    public void setDefinitionSource(List<FCDefinitionSourcePropertyType> value) {
        this.definitionSource = null;
        if (value!= null) {
            List<FCDefinitionSourcePropertyType> draftl = this.getDefinitionSource();
            draftl.addAll(value);
        }
    }

    public void setGlobalProperty(List<FCPropertyTypePropertyType> value) {
        this.globalProperty = null;
        if (value!= null) {
            List<FCPropertyTypePropertyType> draftl = this.getGlobalProperty();
            draftl.addAll(value);
        }
    }

    public void setInheritanceRelation(List<FCInheritanceRelationPropertyType> value) {
        this.inheritanceRelation = null;
        if (value!= null) {
            List<FCInheritanceRelationPropertyType> draftl = this.getInheritanceRelation();
            draftl.addAll(value);
        }
    }

}
