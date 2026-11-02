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
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.BooleanPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * FC_InheritanceRelation realizes GF_InheritanceRelation.  - [ocl] - FC_InheritanceRelation always assumes that its GF_InheritanceRelation::uniqueInstance is TRUE. - [/ocl]
 * 
 * <p>Classe Java per FC_InheritanceRelation_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FC_InheritanceRelation_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="uniqueInstance" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="subtype" type="{http://www.isotc211.org/2005/gfc}FC_FeatureType_PropertyType"/>
 *         <element name="supertype" type="{http://www.isotc211.org/2005/gfc}FC_FeatureType_PropertyType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FC_InheritanceRelation_Type", propOrder = {
    "name",
    "description",
    "uniqueInstance",
    "subtype",
    "supertype"
})
public class FCInheritanceRelationType
    extends AbstractObjectType
    implements ToString
{

    protected CharacterStringPropertyType name;
    @XmlElement(required = true)
    protected CharacterStringPropertyType description;
    @XmlElement(required = true)
    protected BooleanPropertyType uniqueInstance;
    @XmlElement(required = true)
    protected FCFeatureTypePropertyType subtype;
    @XmlElement(required = true)
    protected FCFeatureTypePropertyType supertype;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setName(CharacterStringPropertyType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Recupera il valore della proprietà description.
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
     * Imposta il valore della proprietà description.
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
     * Recupera il valore della proprietà uniqueInstance.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getUniqueInstance() {
        return uniqueInstance;
    }

    /**
     * Imposta il valore della proprietà uniqueInstance.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setUniqueInstance(BooleanPropertyType value) {
        this.uniqueInstance = value;
    }

    public boolean isSetUniqueInstance() {
        return (this.uniqueInstance!= null);
    }

    /**
     * Recupera il valore della proprietà subtype.
     * 
     * @return
     *     possible object is
     *     {@link FCFeatureTypePropertyType }
     *     
     */
    public FCFeatureTypePropertyType getSubtype() {
        return subtype;
    }

    /**
     * Imposta il valore della proprietà subtype.
     * 
     * @param value
     *     allowed object is
     *     {@link FCFeatureTypePropertyType }
     *     
     */
    public void setSubtype(FCFeatureTypePropertyType value) {
        this.subtype = value;
    }

    public boolean isSetSubtype() {
        return (this.subtype!= null);
    }

    /**
     * Recupera il valore della proprietà supertype.
     * 
     * @return
     *     possible object is
     *     {@link FCFeatureTypePropertyType }
     *     
     */
    public FCFeatureTypePropertyType getSupertype() {
        return supertype;
    }

    /**
     * Imposta il valore della proprietà supertype.
     * 
     * @param value
     *     allowed object is
     *     {@link FCFeatureTypePropertyType }
     *     
     */
    public void setSupertype(FCFeatureTypePropertyType value) {
        this.supertype = value;
    }

    public boolean isSetSupertype() {
        return (this.supertype!= null);
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
            CharacterStringPropertyType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            BooleanPropertyType theUniqueInstance;
            theUniqueInstance = this.getUniqueInstance();
            strategy.appendField(locator, this, "uniqueInstance", buffer, theUniqueInstance, this.isSetUniqueInstance());
        }
        {
            FCFeatureTypePropertyType theSubtype;
            theSubtype = this.getSubtype();
            strategy.appendField(locator, this, "subtype", buffer, theSubtype, this.isSetSubtype());
        }
        {
            FCFeatureTypePropertyType theSupertype;
            theSupertype = this.getSupertype();
            strategy.appendField(locator, this, "supertype", buffer, theSupertype, this.isSetSupertype());
        }
        return buffer;
    }

}
