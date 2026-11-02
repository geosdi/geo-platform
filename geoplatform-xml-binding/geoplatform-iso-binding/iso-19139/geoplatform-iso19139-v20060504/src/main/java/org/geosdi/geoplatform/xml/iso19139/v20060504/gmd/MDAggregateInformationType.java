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
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Encapsulates the dataset aggregation information
 * 
 * <p>Classe Java per MD_AggregateInformation_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_AggregateInformation_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="aggregateDataSetName" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType" minOccurs="0"/>
 *         <element name="aggregateDataSetIdentifier" type="{http://www.isotc211.org/2005/gmd}MD_Identifier_PropertyType" minOccurs="0"/>
 *         <element name="associationType" type="{http://www.isotc211.org/2005/gmd}DS_AssociationTypeCode_PropertyType"/>
 *         <element name="initiativeType" type="{http://www.isotc211.org/2005/gmd}DS_InitiativeTypeCode_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_AggregateInformation_Type", propOrder = {
    "aggregateDataSetName",
    "aggregateDataSetIdentifier",
    "associationType",
    "initiativeType"
})
public class MDAggregateInformationType
    extends AbstractObjectType
    implements ToString
{

    protected CICitationPropertyType aggregateDataSetName;
    protected MDIdentifierPropertyType aggregateDataSetIdentifier;
    @XmlElement(required = true)
    protected DSAssociationTypeCodePropertyType associationType;
    protected DSInitiativeTypeCodePropertyType initiativeType;

    /**
     * Recupera il valore della proprietà aggregateDataSetName.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getAggregateDataSetName() {
        return aggregateDataSetName;
    }

    /**
     * Imposta il valore della proprietà aggregateDataSetName.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setAggregateDataSetName(CICitationPropertyType value) {
        this.aggregateDataSetName = value;
    }

    public boolean isSetAggregateDataSetName() {
        return (this.aggregateDataSetName!= null);
    }

    /**
     * Recupera il valore della proprietà aggregateDataSetIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public MDIdentifierPropertyType getAggregateDataSetIdentifier() {
        return aggregateDataSetIdentifier;
    }

    /**
     * Imposta il valore della proprietà aggregateDataSetIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public void setAggregateDataSetIdentifier(MDIdentifierPropertyType value) {
        this.aggregateDataSetIdentifier = value;
    }

    public boolean isSetAggregateDataSetIdentifier() {
        return (this.aggregateDataSetIdentifier!= null);
    }

    /**
     * Recupera il valore della proprietà associationType.
     * 
     * @return
     *     possible object is
     *     {@link DSAssociationTypeCodePropertyType }
     *     
     */
    public DSAssociationTypeCodePropertyType getAssociationType() {
        return associationType;
    }

    /**
     * Imposta il valore della proprietà associationType.
     * 
     * @param value
     *     allowed object is
     *     {@link DSAssociationTypeCodePropertyType }
     *     
     */
    public void setAssociationType(DSAssociationTypeCodePropertyType value) {
        this.associationType = value;
    }

    public boolean isSetAssociationType() {
        return (this.associationType!= null);
    }

    /**
     * Recupera il valore della proprietà initiativeType.
     * 
     * @return
     *     possible object is
     *     {@link DSInitiativeTypeCodePropertyType }
     *     
     */
    public DSInitiativeTypeCodePropertyType getInitiativeType() {
        return initiativeType;
    }

    /**
     * Imposta il valore della proprietà initiativeType.
     * 
     * @param value
     *     allowed object is
     *     {@link DSInitiativeTypeCodePropertyType }
     *     
     */
    public void setInitiativeType(DSInitiativeTypeCodePropertyType value) {
        this.initiativeType = value;
    }

    public boolean isSetInitiativeType() {
        return (this.initiativeType!= null);
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
            CICitationPropertyType theAggregateDataSetName;
            theAggregateDataSetName = this.getAggregateDataSetName();
            strategy.appendField(locator, this, "aggregateDataSetName", buffer, theAggregateDataSetName, this.isSetAggregateDataSetName());
        }
        {
            MDIdentifierPropertyType theAggregateDataSetIdentifier;
            theAggregateDataSetIdentifier = this.getAggregateDataSetIdentifier();
            strategy.appendField(locator, this, "aggregateDataSetIdentifier", buffer, theAggregateDataSetIdentifier, this.isSetAggregateDataSetIdentifier());
        }
        {
            DSAssociationTypeCodePropertyType theAssociationType;
            theAssociationType = this.getAssociationType();
            strategy.appendField(locator, this, "associationType", buffer, theAssociationType, this.isSetAssociationType());
        }
        {
            DSInitiativeTypeCodePropertyType theInitiativeType;
            theInitiativeType = this.getInitiativeType();
            strategy.appendField(locator, this, "initiativeType", buffer, theInitiativeType, this.isSetInitiativeType());
        }
        return buffer;
    }

}
