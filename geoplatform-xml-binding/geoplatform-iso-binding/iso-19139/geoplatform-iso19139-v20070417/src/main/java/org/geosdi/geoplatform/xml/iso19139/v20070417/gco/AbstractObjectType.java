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

package org.geosdi.geoplatform.xml.iso19139.v20070417.gco;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractDQElementType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractDQResultType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractDSAggregateType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractEXGeographicExtentType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractMDContentInformationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractMDIdentificationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractMDSpatialRepresentationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractRSReferenceSystemType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CIAddressType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CICitationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CIContactType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CIDateType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CIOnlineResourceType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CIResponsiblePartyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CISeriesType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CITelephoneType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.DQDataQualityType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.DQScopeType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.DSAssociationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.DSDataSetType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.EXExtentType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.EXTemporalExtentType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.EXVerticalExtentType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.LILineageType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.LIProcessStepType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.LISourceType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDAggregateInformationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDApplicationSchemaInformationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDBrowseGraphicType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDConstraintsType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDDigitalTransferOptionsType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDDimensionType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDDistributionType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDDistributorType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDExtendedElementInformationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDFormatType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDGeometricObjectsType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDIdentifierType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDKeywordsType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDMaintenanceInformationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDMediumType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDMetadataExtensionInformationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDMetadataType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDPortrayalCatalogueReferenceType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDRangeDimensionType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDReferenceSystemType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDRepresentativeFractionType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDStandardOrderProcessType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDUsageType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.PTFreeTextType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.PTLocaleType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmx.AbstractCTCatalogueType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmx.AbstractMXFileType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per AbstractObject_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractObject_Type">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *       </sequence>
 *       <attGroup ref="{http://www.isotc211.org/2005/gco}ObjectIdentification"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractObject_Type")
@XmlSeeAlso({
    TypeNameType.class,
    MemberNameType.class,
    MultiplicityType.class,
    MultiplicityRangeType.class,
    EXVerticalExtentType.class,
    EXExtentType.class,
    EXTemporalExtentType.class,
    AbstractEXGeographicExtentType.class,
    MDIdentifierType.class,
    MDReferenceSystemType.class,
    AbstractRSReferenceSystemType.class,
    CIResponsiblePartyType.class,
    CICitationType.class,
    CIAddressType.class,
    CIOnlineResourceType.class,
    CIContactType.class,
    CITelephoneType.class,
    CIDateType.class,
    CISeriesType.class,
    MDDimensionType.class,
    AbstractMDSpatialRepresentationType.class,
    MDGeometricObjectsType.class,
    MDExtendedElementInformationType.class,
    MDMetadataExtensionInformationType.class,
    AbstractMDContentInformationType.class,
    MDRangeDimensionType.class,
    MDApplicationSchemaInformationType.class,
    MDPortrayalCatalogueReferenceType.class,
    MDConstraintsType.class,
    MDMediumType.class,
    MDDigitalTransferOptionsType.class,
    MDStandardOrderProcessType.class,
    MDDistributorType.class,
    MDDistributionType.class,
    MDFormatType.class,
    MDMaintenanceInformationType.class,
    MDBrowseGraphicType.class,
    AbstractMDIdentificationType.class,
    MDRepresentativeFractionType.class,
    MDUsageType.class,
    MDKeywordsType.class,
    DSAssociationType.class,
    MDAggregateInformationType.class,
    LIProcessStepType.class,
    LISourceType.class,
    LILineageType.class,
    AbstractDQResultType.class,
    AbstractDQElementType.class,
    DQDataQualityType.class,
    DQScopeType.class,
    PTFreeTextType.class,
    PTLocaleType.class,
    MDMetadataType.class,
    AbstractCTCatalogueType.class,
    AbstractDSAggregateType.class,
    DSDataSetType.class,
    AbstractMXFileType.class
})
public abstract class AbstractObjectType implements ToString
{

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "uuid")
    protected String uuid;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
    }

    /**
     * Recupera il valore della proprietà uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Imposta il valore della proprietà uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    public boolean isSetUuid() {
        return (this.uuid!= null);
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
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId, this.isSetId());
        }
        {
            String theUuid;
            theUuid = this.getUuid();
            strategy.appendField(locator, this, "uuid", buffer, theUuid, this.isSetUuid());
        }
        return buffer;
    }

}
