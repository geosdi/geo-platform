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
// Generato il: 2026.11.02 alle 03:38:59 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20060504.src;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.BooleanPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.MemberNameType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.TypeNamePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per SV_Parameter_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SV_Parameter_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gco}MemberName_Type"/>
 *         <element name="direction" type="{http://www.isotc211.org/2005/srv}SV_ParameterDirection_PropertyType" minOccurs="0"/>
 *         <element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="optionality" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="repeatability" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="valueType" type="{http://www.isotc211.org/2005/gco}TypeName_PropertyType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SV_Parameter_Type", propOrder = {
    "name",
    "direction",
    "description",
    "optionality",
    "repeatability",
    "valueType"
})
public class SVParameterType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected MemberNameType name;
    protected SVParameterDirectionPropertyType direction;
    protected CharacterStringPropertyType description;
    @XmlElement(required = true)
    protected CharacterStringPropertyType optionality;
    @XmlElement(required = true)
    protected BooleanPropertyType repeatability;
    @XmlElement(required = true)
    protected TypeNamePropertyType valueType;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link MemberNameType }
     *     
     */
    public MemberNameType getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberNameType }
     *     
     */
    public void setName(MemberNameType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Recupera il valore della proprietà direction.
     * 
     * @return
     *     possible object is
     *     {@link SVParameterDirectionPropertyType }
     *     
     */
    public SVParameterDirectionPropertyType getDirection() {
        return direction;
    }

    /**
     * Imposta il valore della proprietà direction.
     * 
     * @param value
     *     allowed object is
     *     {@link SVParameterDirectionPropertyType }
     *     
     */
    public void setDirection(SVParameterDirectionPropertyType value) {
        this.direction = value;
    }

    public boolean isSetDirection() {
        return (this.direction!= null);
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
     * Recupera il valore della proprietà optionality.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getOptionality() {
        return optionality;
    }

    /**
     * Imposta il valore della proprietà optionality.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setOptionality(CharacterStringPropertyType value) {
        this.optionality = value;
    }

    public boolean isSetOptionality() {
        return (this.optionality!= null);
    }

    /**
     * Recupera il valore della proprietà repeatability.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getRepeatability() {
        return repeatability;
    }

    /**
     * Imposta il valore della proprietà repeatability.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setRepeatability(BooleanPropertyType value) {
        this.repeatability = value;
    }

    public boolean isSetRepeatability() {
        return (this.repeatability!= null);
    }

    /**
     * Recupera il valore della proprietà valueType.
     * 
     * @return
     *     possible object is
     *     {@link TypeNamePropertyType }
     *     
     */
    public TypeNamePropertyType getValueType() {
        return valueType;
    }

    /**
     * Imposta il valore della proprietà valueType.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeNamePropertyType }
     *     
     */
    public void setValueType(TypeNamePropertyType value) {
        this.valueType = value;
    }

    public boolean isSetValueType() {
        return (this.valueType!= null);
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
            MemberNameType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            SVParameterDirectionPropertyType theDirection;
            theDirection = this.getDirection();
            strategy.appendField(locator, this, "direction", buffer, theDirection, this.isSetDirection());
        }
        {
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            CharacterStringPropertyType theOptionality;
            theOptionality = this.getOptionality();
            strategy.appendField(locator, this, "optionality", buffer, theOptionality, this.isSetOptionality());
        }
        {
            BooleanPropertyType theRepeatability;
            theRepeatability = this.getRepeatability();
            strategy.appendField(locator, this, "repeatability", buffer, theRepeatability, this.isSetRepeatability());
        }
        {
            TypeNamePropertyType theValueType;
            theValueType = this.getValueType();
            strategy.appendField(locator, this, "valueType", buffer, theValueType, this.isSetValueType());
        }
        return buffer;
    }

}
