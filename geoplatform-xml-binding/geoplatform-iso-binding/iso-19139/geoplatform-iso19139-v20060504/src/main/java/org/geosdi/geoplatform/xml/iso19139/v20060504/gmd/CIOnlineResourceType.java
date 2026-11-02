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

package org.geosdi.geoplatform.xml.iso19139.v20060504.gmd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about online sources from which the dataset, specification, or community profile name and extended metadata elements can be obtained.
 * 
 * <p>Classe Java per CI_OnlineResource_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CI_OnlineResource_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="linkage" type="{http://www.isotc211.org/2005/gmd}URL_PropertyType"/>
 *         <element name="protocol" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="applicationProfile" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="name" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="function" type="{http://www.isotc211.org/2005/gmd}CI_OnLineFunctionCode_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CI_OnlineResource_Type", propOrder = {
    "linkage",
    "protocol",
    "applicationProfile",
    "name",
    "description",
    "function"
})
public class CIOnlineResourceType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected URLPropertyType linkage;
    protected CharacterStringPropertyType protocol;
    protected CharacterStringPropertyType applicationProfile;
    protected CharacterStringPropertyType name;
    protected CharacterStringPropertyType description;
    protected CIOnLineFunctionCodePropertyType function;

    /**
     * Recupera il valore della proprietà linkage.
     * 
     * @return
     *     possible object is
     *     {@link URLPropertyType }
     *     
     */
    public URLPropertyType getLinkage() {
        return linkage;
    }

    /**
     * Imposta il valore della proprietà linkage.
     * 
     * @param value
     *     allowed object is
     *     {@link URLPropertyType }
     *     
     */
    public void setLinkage(URLPropertyType value) {
        this.linkage = value;
    }

    public boolean isSetLinkage() {
        return (this.linkage!= null);
    }

    /**
     * Recupera il valore della proprietà protocol.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getProtocol() {
        return protocol;
    }

    /**
     * Imposta il valore della proprietà protocol.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setProtocol(CharacterStringPropertyType value) {
        this.protocol = value;
    }

    public boolean isSetProtocol() {
        return (this.protocol!= null);
    }

    /**
     * Recupera il valore della proprietà applicationProfile.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getApplicationProfile() {
        return applicationProfile;
    }

    /**
     * Imposta il valore della proprietà applicationProfile.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setApplicationProfile(CharacterStringPropertyType value) {
        this.applicationProfile = value;
    }

    public boolean isSetApplicationProfile() {
        return (this.applicationProfile!= null);
    }

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
     * Recupera il valore della proprietà function.
     * 
     * @return
     *     possible object is
     *     {@link CIOnLineFunctionCodePropertyType }
     *     
     */
    public CIOnLineFunctionCodePropertyType getFunction() {
        return function;
    }

    /**
     * Imposta il valore della proprietà function.
     * 
     * @param value
     *     allowed object is
     *     {@link CIOnLineFunctionCodePropertyType }
     *     
     */
    public void setFunction(CIOnLineFunctionCodePropertyType value) {
        this.function = value;
    }

    public boolean isSetFunction() {
        return (this.function!= null);
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
            URLPropertyType theLinkage;
            theLinkage = this.getLinkage();
            strategy.appendField(locator, this, "linkage", buffer, theLinkage, this.isSetLinkage());
        }
        {
            CharacterStringPropertyType theProtocol;
            theProtocol = this.getProtocol();
            strategy.appendField(locator, this, "protocol", buffer, theProtocol, this.isSetProtocol());
        }
        {
            CharacterStringPropertyType theApplicationProfile;
            theApplicationProfile = this.getApplicationProfile();
            strategy.appendField(locator, this, "applicationProfile", buffer, theApplicationProfile, this.isSetApplicationProfile());
        }
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
            CIOnLineFunctionCodePropertyType theFunction;
            theFunction = this.getFunction();
            strategy.appendField(locator, this, "function", buffer, theFunction, this.isSetFunction());
        }
        return buffer;
    }

}
