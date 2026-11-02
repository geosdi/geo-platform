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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.LocalNamePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.MultiplicityPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Abstract class for local and global feature properties.
 * 
 * <p>Classe Java per AbstractFC_PropertyType_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractFC_PropertyType_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gfc}AbstractFC_CarrierOfCharacteristics_Type">
 *       <sequence>
 *         <element name="memberName" type="{http://www.isotc211.org/2005/gco}LocalName_PropertyType"/>
 *         <element name="definition" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="cardinality" type="{http://www.isotc211.org/2005/gco}Multiplicity_PropertyType"/>
 *         <element name="definitionReference" type="{http://www.isotc211.org/2005/gfc}FC_DefinitionReference_PropertyType" minOccurs="0"/>
 *         <element name="featureCatalogue" type="{http://www.isotc211.org/2005/gfc}FC_FeatureCatalogue_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFC_PropertyType_Type", propOrder = {
    "memberName",
    "definition",
    "cardinality",
    "definitionReference",
    "featureCatalogue"
})
@XmlSeeAlso({
    FCFeatureAttributeType.class,
    FCAssociationRoleType.class,
    FCFeatureOperationType.class
})
public abstract class AbstractFCPropertyTypeType
    extends AbstractFCCarrierOfCharacteristicsType
    implements ToString
{

    @XmlElement(required = true)
    protected LocalNamePropertyType memberName;
    protected CharacterStringPropertyType definition;
    @XmlElement(required = true)
    protected MultiplicityPropertyType cardinality;
    protected FCDefinitionReferencePropertyType definitionReference;
    protected FCFeatureCataloguePropertyType featureCatalogue;

    /**
     * Recupera il valore della proprietà memberName.
     * 
     * @return
     *     possible object is
     *     {@link LocalNamePropertyType }
     *     
     */
    public LocalNamePropertyType getMemberName() {
        return memberName;
    }

    /**
     * Imposta il valore della proprietà memberName.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalNamePropertyType }
     *     
     */
    public void setMemberName(LocalNamePropertyType value) {
        this.memberName = value;
    }

    public boolean isSetMemberName() {
        return (this.memberName!= null);
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
     * Recupera il valore della proprietà cardinality.
     * 
     * @return
     *     possible object is
     *     {@link MultiplicityPropertyType }
     *     
     */
    public MultiplicityPropertyType getCardinality() {
        return cardinality;
    }

    /**
     * Imposta il valore della proprietà cardinality.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiplicityPropertyType }
     *     
     */
    public void setCardinality(MultiplicityPropertyType value) {
        this.cardinality = value;
    }

    public boolean isSetCardinality() {
        return (this.cardinality!= null);
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
            LocalNamePropertyType theMemberName;
            theMemberName = this.getMemberName();
            strategy.appendField(locator, this, "memberName", buffer, theMemberName, this.isSetMemberName());
        }
        {
            CharacterStringPropertyType theDefinition;
            theDefinition = this.getDefinition();
            strategy.appendField(locator, this, "definition", buffer, theDefinition, this.isSetDefinition());
        }
        {
            MultiplicityPropertyType theCardinality;
            theCardinality = this.getCardinality();
            strategy.appendField(locator, this, "cardinality", buffer, theCardinality, this.isSetCardinality());
        }
        {
            FCDefinitionReferencePropertyType theDefinitionReference;
            theDefinitionReference = this.getDefinitionReference();
            strategy.appendField(locator, this, "definitionReference", buffer, theDefinitionReference, this.isSetDefinitionReference());
        }
        {
            FCFeatureCataloguePropertyType theFeatureCatalogue;
            theFeatureCatalogue = this.getFeatureCatalogue();
            strategy.appendField(locator, this, "featureCatalogue", buffer, theFeatureCatalogue, this.isSetFeatureCatalogue());
        }
        return buffer;
    }

}
