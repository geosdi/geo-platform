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
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.BooleanPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * A role of the association FC_AssociationRole::relation.  - [ocl] - roleName = FC_Member::memberName; - FC_PropertyType::cardinality realizes GF_AssociationRole::cardinality - [/ocl]
 * 
 * <p>Classe Java per FC_AssociationRole_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FC_AssociationRole_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gfc}AbstractFC_PropertyType_Type">
 *       <sequence>
 *         <element name="type" type="{http://www.isotc211.org/2005/gfc}FC_RoleType_PropertyType"/>
 *         <element name="isOrdered" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="isNavigable" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="relation" type="{http://www.isotc211.org/2005/gfc}FC_FeatureAssociation_PropertyType"/>
 *         <element name="rolePlayer" type="{http://www.isotc211.org/2005/gfc}FC_FeatureType_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FC_AssociationRole_Type", propOrder = {
    "type",
    "isOrdered",
    "isNavigable",
    "relation",
    "rolePlayer"
})
public class FCAssociationRoleType
    extends AbstractFCPropertyTypeType
    implements ToString
{

    @XmlElement(required = true)
    protected FCRoleTypePropertyType type;
    @XmlElement(required = true)
    protected BooleanPropertyType isOrdered;
    @XmlElement(required = true)
    protected BooleanPropertyType isNavigable;
    @XmlElement(required = true)
    protected FCFeatureAssociationPropertyType relation;
    protected FCFeatureTypePropertyType rolePlayer;

    /**
     * Recupera il valore della proprietà type.
     * 
     * @return
     *     possible object is
     *     {@link FCRoleTypePropertyType }
     *     
     */
    public FCRoleTypePropertyType getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     * @param value
     *     allowed object is
     *     {@link FCRoleTypePropertyType }
     *     
     */
    public void setType(FCRoleTypePropertyType value) {
        this.type = value;
    }

    public boolean isSetType() {
        return (this.type!= null);
    }

    /**
     * Recupera il valore della proprietà isOrdered.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getIsOrdered() {
        return isOrdered;
    }

    /**
     * Imposta il valore della proprietà isOrdered.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setIsOrdered(BooleanPropertyType value) {
        this.isOrdered = value;
    }

    public boolean isSetIsOrdered() {
        return (this.isOrdered!= null);
    }

    /**
     * Recupera il valore della proprietà isNavigable.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getIsNavigable() {
        return isNavigable;
    }

    /**
     * Imposta il valore della proprietà isNavigable.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setIsNavigable(BooleanPropertyType value) {
        this.isNavigable = value;
    }

    public boolean isSetIsNavigable() {
        return (this.isNavigable!= null);
    }

    /**
     * Recupera il valore della proprietà relation.
     * 
     * @return
     *     possible object is
     *     {@link FCFeatureAssociationPropertyType }
     *     
     */
    public FCFeatureAssociationPropertyType getRelation() {
        return relation;
    }

    /**
     * Imposta il valore della proprietà relation.
     * 
     * @param value
     *     allowed object is
     *     {@link FCFeatureAssociationPropertyType }
     *     
     */
    public void setRelation(FCFeatureAssociationPropertyType value) {
        this.relation = value;
    }

    public boolean isSetRelation() {
        return (this.relation!= null);
    }

    /**
     * Recupera il valore della proprietà rolePlayer.
     * 
     * @return
     *     possible object is
     *     {@link FCFeatureTypePropertyType }
     *     
     */
    public FCFeatureTypePropertyType getRolePlayer() {
        return rolePlayer;
    }

    /**
     * Imposta il valore della proprietà rolePlayer.
     * 
     * @param value
     *     allowed object is
     *     {@link FCFeatureTypePropertyType }
     *     
     */
    public void setRolePlayer(FCFeatureTypePropertyType value) {
        this.rolePlayer = value;
    }

    public boolean isSetRolePlayer() {
        return (this.rolePlayer!= null);
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
            FCRoleTypePropertyType theType;
            theType = this.getType();
            strategy.appendField(locator, this, "type", buffer, theType, this.isSetType());
        }
        {
            BooleanPropertyType theIsOrdered;
            theIsOrdered = this.getIsOrdered();
            strategy.appendField(locator, this, "isOrdered", buffer, theIsOrdered, this.isSetIsOrdered());
        }
        {
            BooleanPropertyType theIsNavigable;
            theIsNavigable = this.getIsNavigable();
            strategy.appendField(locator, this, "isNavigable", buffer, theIsNavigable, this.isSetIsNavigable());
        }
        {
            FCFeatureAssociationPropertyType theRelation;
            theRelation = this.getRelation();
            strategy.appendField(locator, this, "relation", buffer, theRelation, this.isSetRelation());
        }
        {
            FCFeatureTypePropertyType theRolePlayer;
            theRolePlayer = this.getRolePlayer();
            strategy.appendField(locator, this, "rolePlayer", buffer, theRolePlayer, this.isSetRolePlayer());
        }
        return buffer;
    }

}
