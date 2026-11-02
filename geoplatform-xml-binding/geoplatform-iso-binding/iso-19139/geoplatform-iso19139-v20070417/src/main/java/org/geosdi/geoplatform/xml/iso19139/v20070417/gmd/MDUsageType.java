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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.DateTimePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Brief description of ways in which the dataset is currently used.
 * 
 * <p>Classe Java per MD_Usage_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Usage_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="specificUsage" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="usageDateTime" type="{http://www.isotc211.org/2005/gco}DateTime_PropertyType" minOccurs="0"/>
 *         <element name="userDeterminedLimitations" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="userContactInfo" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Usage_Type", propOrder = {
    "specificUsage",
    "usageDateTime",
    "userDeterminedLimitations",
    "userContactInfo"
})
public class MDUsageType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CharacterStringPropertyType specificUsage;
    protected DateTimePropertyType usageDateTime;
    protected CharacterStringPropertyType userDeterminedLimitations;
    @XmlElement(required = true)
    protected List<CIResponsiblePartyPropertyType> userContactInfo;

    /**
     * Recupera il valore della proprietà specificUsage.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getSpecificUsage() {
        return specificUsage;
    }

    /**
     * Imposta il valore della proprietà specificUsage.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setSpecificUsage(CharacterStringPropertyType value) {
        this.specificUsage = value;
    }

    public boolean isSetSpecificUsage() {
        return (this.specificUsage!= null);
    }

    /**
     * Recupera il valore della proprietà usageDateTime.
     * 
     * @return
     *     possible object is
     *     {@link DateTimePropertyType }
     *     
     */
    public DateTimePropertyType getUsageDateTime() {
        return usageDateTime;
    }

    /**
     * Imposta il valore della proprietà usageDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimePropertyType }
     *     
     */
    public void setUsageDateTime(DateTimePropertyType value) {
        this.usageDateTime = value;
    }

    public boolean isSetUsageDateTime() {
        return (this.usageDateTime!= null);
    }

    /**
     * Recupera il valore della proprietà userDeterminedLimitations.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getUserDeterminedLimitations() {
        return userDeterminedLimitations;
    }

    /**
     * Imposta il valore della proprietà userDeterminedLimitations.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setUserDeterminedLimitations(CharacterStringPropertyType value) {
        this.userDeterminedLimitations = value;
    }

    public boolean isSetUserDeterminedLimitations() {
        return (this.userDeterminedLimitations!= null);
    }

    /**
     * Gets the value of the userContactInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the userContactInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getUserContactInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIResponsiblePartyPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the userContactInfo property.
     */
    public List<CIResponsiblePartyPropertyType> getUserContactInfo() {
        if (userContactInfo == null) {
            userContactInfo = new ArrayList<>();
        }
        return this.userContactInfo;
    }

    public boolean isSetUserContactInfo() {
        return ((this.userContactInfo!= null)&&(!this.userContactInfo.isEmpty()));
    }

    public void unsetUserContactInfo() {
        this.userContactInfo = null;
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
            CharacterStringPropertyType theSpecificUsage;
            theSpecificUsage = this.getSpecificUsage();
            strategy.appendField(locator, this, "specificUsage", buffer, theSpecificUsage, this.isSetSpecificUsage());
        }
        {
            DateTimePropertyType theUsageDateTime;
            theUsageDateTime = this.getUsageDateTime();
            strategy.appendField(locator, this, "usageDateTime", buffer, theUsageDateTime, this.isSetUsageDateTime());
        }
        {
            CharacterStringPropertyType theUserDeterminedLimitations;
            theUserDeterminedLimitations = this.getUserDeterminedLimitations();
            strategy.appendField(locator, this, "userDeterminedLimitations", buffer, theUserDeterminedLimitations, this.isSetUserDeterminedLimitations());
        }
        {
            List<CIResponsiblePartyPropertyType> theUserContactInfo;
            theUserContactInfo = (this.isSetUserContactInfo()?this.getUserContactInfo():null);
            strategy.appendField(locator, this, "userContactInfo", buffer, theUserContactInfo, this.isSetUserContactInfo());
        }
        return buffer;
    }

    public void setUserContactInfo(List<CIResponsiblePartyPropertyType> value) {
        this.userContactInfo = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getUserContactInfo();
            draftl.addAll(value);
        }
    }

}
