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
// Generated on: 2012.04.18 at 03:12:11 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Identification of, and means of communication with, person(s) and organisations associated with the dataset
 * 
 * <p>Java class for CI_ResponsibleParty_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CI_ResponsibleParty_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       &lt;sequence>
 *         &lt;element name="individualName" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         &lt;element name="organisationName" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         &lt;element name="positionName" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         &lt;element name="contactInfo" type="{http://www.isotc211.org/2005/gmd}CI_Contact_PropertyType" minOccurs="0"/>
 *         &lt;element name="role" type="{http://www.isotc211.org/2005/gmd}CI_RoleCode_PropertyType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CI_ResponsibleParty_Type", propOrder = {
    "individualName",
    "organisationName",
    "positionName",
    "contactInfo",
    "role"
})
public class CIResponsiblePartyType
    extends AbstractObjectType
    implements ToString
{

    protected CharacterStringPropertyType individualName;
    protected CharacterStringPropertyType organisationName;
    protected CharacterStringPropertyType positionName;
    protected CIContactPropertyType contactInfo;
    @XmlElement(required = true)
    protected CIRoleCodePropertyType role;

    /**
     * Gets the value of the individualName property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getIndividualName() {
        return individualName;
    }

    /**
     * Sets the value of the individualName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setIndividualName(CharacterStringPropertyType value) {
        this.individualName = value;
    }

    public boolean isSetIndividualName() {
        return (this.individualName!= null);
    }

    /**
     * Gets the value of the organisationName property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getOrganisationName() {
        return organisationName;
    }

    /**
     * Sets the value of the organisationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setOrganisationName(CharacterStringPropertyType value) {
        this.organisationName = value;
    }

    public boolean isSetOrganisationName() {
        return (this.organisationName!= null);
    }

    /**
     * Gets the value of the positionName property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getPositionName() {
        return positionName;
    }

    /**
     * Sets the value of the positionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setPositionName(CharacterStringPropertyType value) {
        this.positionName = value;
    }

    public boolean isSetPositionName() {
        return (this.positionName!= null);
    }

    /**
     * Gets the value of the contactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CIContactPropertyType }
     *     
     */
    public CIContactPropertyType getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the value of the contactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIContactPropertyType }
     *     
     */
    public void setContactInfo(CIContactPropertyType value) {
        this.contactInfo = value;
    }

    public boolean isSetContactInfo() {
        return (this.contactInfo!= null);
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link CIRoleCodePropertyType }
     *     
     */
    public CIRoleCodePropertyType getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIRoleCodePropertyType }
     *     
     */
    public void setRole(CIRoleCodePropertyType value) {
        this.role = value;
    }

    public boolean isSetRole() {
        return (this.role!= null);
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
            CharacterStringPropertyType theIndividualName;
            theIndividualName = this.getIndividualName();
            strategy.appendField(locator, this, "individualName", buffer, theIndividualName);
        }
        {
            CharacterStringPropertyType theOrganisationName;
            theOrganisationName = this.getOrganisationName();
            strategy.appendField(locator, this, "organisationName", buffer, theOrganisationName);
        }
        {
            CharacterStringPropertyType thePositionName;
            thePositionName = this.getPositionName();
            strategy.appendField(locator, this, "positionName", buffer, thePositionName);
        }
        {
            CIContactPropertyType theContactInfo;
            theContactInfo = this.getContactInfo();
            strategy.appendField(locator, this, "contactInfo", buffer, theContactInfo);
        }
        {
            CIRoleCodePropertyType theRole;
            theRole = this.getRole();
            strategy.appendField(locator, this, "role", buffer, theRole);
        }
        return buffer;
    }

}
