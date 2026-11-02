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
// Generato il: 2026.11.02 alle 03:48:08 PM CET
//

package org.geosdi.geoplatform.xml.ows.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Identification of, and means of communication with, person responsible for the server. At least one of IndividualName, OrganisationName, or PositionName shall be included.
 * 
 * <p>Classe Java per ResponsiblePartyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ResponsiblePartyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows}IndividualName" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}OrganisationName" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}PositionName" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}ContactInfo" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}Role"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponsiblePartyType", propOrder = {
    "individualName",
    "organisationName",
    "positionName",
    "contactInfo",
    "role"
})
public class ResponsiblePartyType implements ToString
{

    /**
     * Name of the responsible person: surname, given name, title separated by a delimiter.
     * 
     */
    @XmlElement(name = "IndividualName")
    protected String individualName;
    /**
     * Name of the responsible organization.
     * 
     */
    @XmlElement(name = "OrganisationName")
    protected String organisationName;
    /**
     * Role or position of the responsible person.
     * 
     */
    @XmlElement(name = "PositionName")
    protected String positionName;
    /**
     * Address of the responsible party.
     * 
     */
    @XmlElement(name = "ContactInfo")
    protected ContactType contactInfo;
    /**
     * Function performed by the responsible party. Possible values of this Role shall include the values and the meanings listed in Subclause B.5.5 of ISO 19115:2003.
     * 
     */
    @XmlElement(name = "Role", required = true)
    protected CodeType role;

    /**
     * Name of the responsible person: surname, given name, title separated by a delimiter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualName() {
        return individualName;
    }

    /**
     * Imposta il valore della proprietà individualName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getIndividualName()
     */
    public void setIndividualName(String value) {
        this.individualName = value;
    }

    /**
     * Name of the responsible organization.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationName() {
        return organisationName;
    }

    /**
     * Imposta il valore della proprietà organisationName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getOrganisationName()
     */
    public void setOrganisationName(String value) {
        this.organisationName = value;
    }

    /**
     * Role or position of the responsible person.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * Imposta il valore della proprietà positionName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPositionName()
     */
    public void setPositionName(String value) {
        this.positionName = value;
    }

    /**
     * Address of the responsible party.
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getContactInfo() {
        return contactInfo;
    }

    /**
     * Imposta il valore della proprietà contactInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     * @see #getContactInfo()
     */
    public void setContactInfo(ContactType value) {
        this.contactInfo = value;
    }

    /**
     * Function performed by the responsible party. Possible values of this Role shall include the values and the meanings listed in Subclause B.5.5 of ISO 19115:2003.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getRole() {
        return role;
    }

    /**
     * Imposta il valore della proprietà role.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getRole()
     */
    public void setRole(CodeType value) {
        this.role = value;
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
            String theIndividualName;
            theIndividualName = this.getIndividualName();
            strategy.appendField(locator, this, "individualName", buffer, theIndividualName, (this.individualName!= null));
        }
        {
            String theOrganisationName;
            theOrganisationName = this.getOrganisationName();
            strategy.appendField(locator, this, "organisationName", buffer, theOrganisationName, (this.organisationName!= null));
        }
        {
            String thePositionName;
            thePositionName = this.getPositionName();
            strategy.appendField(locator, this, "positionName", buffer, thePositionName, (this.positionName!= null));
        }
        {
            ContactType theContactInfo;
            theContactInfo = this.getContactInfo();
            strategy.appendField(locator, this, "contactInfo", buffer, theContactInfo, (this.contactInfo!= null));
        }
        {
            CodeType theRole;
            theRole = this.getRole();
            strategy.appendField(locator, this, "role", buffer, theRole, (this.role!= null));
        }
        return buffer;
    }

}
