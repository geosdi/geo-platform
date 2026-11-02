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
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.DatePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.ObjectReferencePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about the metadata
 * 
 * <p>Classe Java per MD_Metadata_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Metadata_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="fileIdentifier" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="language" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="characterSet" type="{http://www.isotc211.org/2005/gmd}MD_CharacterSetCode_PropertyType" minOccurs="0"/>
 *         <element name="parentIdentifier" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="hierarchyLevel" type="{http://www.isotc211.org/2005/gmd}MD_ScopeCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="hierarchyLevelName" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="contact" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded"/>
 *         <element name="dateStamp" type="{http://www.isotc211.org/2005/gco}Date_PropertyType"/>
 *         <element name="metadataStandardName" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="metadataStandardVersion" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="dataSetURI" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="locale" type="{http://www.isotc211.org/2005/gmd}PT_Locale_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="spatialRepresentationInfo" type="{http://www.isotc211.org/2005/gmd}MD_SpatialRepresentation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="referenceSystemInfo" type="{http://www.isotc211.org/2005/gmd}MD_ReferenceSystem_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="metadataExtensionInfo" type="{http://www.isotc211.org/2005/gmd}MD_MetadataExtensionInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="identificationInfo" type="{http://www.isotc211.org/2005/gmd}MD_Identification_PropertyType" maxOccurs="unbounded"/>
 *         <element name="contentInfo" type="{http://www.isotc211.org/2005/gmd}MD_ContentInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="distributionInfo" type="{http://www.isotc211.org/2005/gmd}MD_Distribution_PropertyType" minOccurs="0"/>
 *         <element name="dataQualityInfo" type="{http://www.isotc211.org/2005/gmd}DQ_DataQuality_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="portrayalCatalogueInfo" type="{http://www.isotc211.org/2005/gmd}MD_PortrayalCatalogueReference_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="metadataConstraints" type="{http://www.isotc211.org/2005/gmd}MD_Constraints_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="applicationSchemaInfo" type="{http://www.isotc211.org/2005/gmd}MD_ApplicationSchemaInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="metadataMaintenance" type="{http://www.isotc211.org/2005/gmd}MD_MaintenanceInformation_PropertyType" minOccurs="0"/>
 *         <element name="series" type="{http://www.isotc211.org/2005/gmd}DS_Aggregate_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="describes" type="{http://www.isotc211.org/2005/gmd}DS_DataSet_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="propertyType" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="featureType" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="featureAttribute" type="{http://www.isotc211.org/2005/gco}ObjectReference_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Metadata_Type", propOrder = {
    "fileIdentifier",
    "language",
    "characterSet",
    "parentIdentifier",
    "hierarchyLevel",
    "hierarchyLevelName",
    "contact",
    "dateStamp",
    "metadataStandardName",
    "metadataStandardVersion",
    "dataSetURI",
    "locale",
    "spatialRepresentationInfo",
    "referenceSystemInfo",
    "metadataExtensionInfo",
    "identificationInfo",
    "contentInfo",
    "distributionInfo",
    "dataQualityInfo",
    "portrayalCatalogueInfo",
    "metadataConstraints",
    "applicationSchemaInfo",
    "metadataMaintenance",
    "series",
    "describes",
    "propertyType",
    "featureType",
    "featureAttribute"
})
public class MDMetadataType
    extends AbstractObjectType
    implements ToString
{

    protected CharacterStringPropertyType fileIdentifier;
    protected CharacterStringPropertyType language;
    protected MDCharacterSetCodePropertyType characterSet;
    protected CharacterStringPropertyType parentIdentifier;
    protected List<MDScopeCodePropertyType> hierarchyLevel;
    protected List<CharacterStringPropertyType> hierarchyLevelName;
    @XmlElement(required = true)
    protected List<CIResponsiblePartyPropertyType> contact;
    @XmlElement(required = true)
    protected DatePropertyType dateStamp;
    protected CharacterStringPropertyType metadataStandardName;
    protected CharacterStringPropertyType metadataStandardVersion;
    protected CharacterStringPropertyType dataSetURI;
    protected List<PTLocalePropertyType> locale;
    protected List<MDSpatialRepresentationPropertyType> spatialRepresentationInfo;
    protected List<MDReferenceSystemPropertyType> referenceSystemInfo;
    protected List<MDMetadataExtensionInformationPropertyType> metadataExtensionInfo;
    @XmlElement(required = true)
    protected List<MDIdentificationPropertyType> identificationInfo;
    protected List<MDContentInformationPropertyType> contentInfo;
    protected MDDistributionPropertyType distributionInfo;
    protected List<DQDataQualityPropertyType> dataQualityInfo;
    protected List<MDPortrayalCatalogueReferencePropertyType> portrayalCatalogueInfo;
    protected List<MDConstraintsPropertyType> metadataConstraints;
    protected List<MDApplicationSchemaInformationPropertyType> applicationSchemaInfo;
    protected MDMaintenanceInformationPropertyType metadataMaintenance;
    protected List<DSAggregatePropertyType> series;
    protected List<DSDataSetPropertyType> describes;
    protected List<ObjectReferencePropertyType> propertyType;
    protected List<ObjectReferencePropertyType> featureType;
    protected List<ObjectReferencePropertyType> featureAttribute;

    /**
     * Recupera il valore della proprietà fileIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getFileIdentifier() {
        return fileIdentifier;
    }

    /**
     * Imposta il valore della proprietà fileIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setFileIdentifier(CharacterStringPropertyType value) {
        this.fileIdentifier = value;
    }

    public boolean isSetFileIdentifier() {
        return (this.fileIdentifier!= null);
    }

    /**
     * Recupera il valore della proprietà language.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getLanguage() {
        return language;
    }

    /**
     * Imposta il valore della proprietà language.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setLanguage(CharacterStringPropertyType value) {
        this.language = value;
    }

    public boolean isSetLanguage() {
        return (this.language!= null);
    }

    /**
     * Recupera il valore della proprietà characterSet.
     * 
     * @return
     *     possible object is
     *     {@link MDCharacterSetCodePropertyType }
     *     
     */
    public MDCharacterSetCodePropertyType getCharacterSet() {
        return characterSet;
    }

    /**
     * Imposta il valore della proprietà characterSet.
     * 
     * @param value
     *     allowed object is
     *     {@link MDCharacterSetCodePropertyType }
     *     
     */
    public void setCharacterSet(MDCharacterSetCodePropertyType value) {
        this.characterSet = value;
    }

    public boolean isSetCharacterSet() {
        return (this.characterSet!= null);
    }

    /**
     * Recupera il valore della proprietà parentIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getParentIdentifier() {
        return parentIdentifier;
    }

    /**
     * Imposta il valore della proprietà parentIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setParentIdentifier(CharacterStringPropertyType value) {
        this.parentIdentifier = value;
    }

    public boolean isSetParentIdentifier() {
        return (this.parentIdentifier!= null);
    }

    /**
     * Gets the value of the hierarchyLevel property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the hierarchyLevel property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getHierarchyLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDScopeCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the hierarchyLevel property.
     */
    public List<MDScopeCodePropertyType> getHierarchyLevel() {
        if (hierarchyLevel == null) {
            hierarchyLevel = new ArrayList<>();
        }
        return this.hierarchyLevel;
    }

    public boolean isSetHierarchyLevel() {
        return ((this.hierarchyLevel!= null)&&(!this.hierarchyLevel.isEmpty()));
    }

    public void unsetHierarchyLevel() {
        this.hierarchyLevel = null;
    }

    /**
     * Gets the value of the hierarchyLevelName property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the hierarchyLevelName property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getHierarchyLevelName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the hierarchyLevelName property.
     */
    public List<CharacterStringPropertyType> getHierarchyLevelName() {
        if (hierarchyLevelName == null) {
            hierarchyLevelName = new ArrayList<>();
        }
        return this.hierarchyLevelName;
    }

    public boolean isSetHierarchyLevelName() {
        return ((this.hierarchyLevelName!= null)&&(!this.hierarchyLevelName.isEmpty()));
    }

    public void unsetHierarchyLevelName() {
        this.hierarchyLevelName = null;
    }

    /**
     * Gets the value of the contact property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the contact property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getContact().add(newItem);
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
     *     The value of the contact property.
     */
    public List<CIResponsiblePartyPropertyType> getContact() {
        if (contact == null) {
            contact = new ArrayList<>();
        }
        return this.contact;
    }

    public boolean isSetContact() {
        return ((this.contact!= null)&&(!this.contact.isEmpty()));
    }

    public void unsetContact() {
        this.contact = null;
    }

    /**
     * Recupera il valore della proprietà dateStamp.
     * 
     * @return
     *     possible object is
     *     {@link DatePropertyType }
     *     
     */
    public DatePropertyType getDateStamp() {
        return dateStamp;
    }

    /**
     * Imposta il valore della proprietà dateStamp.
     * 
     * @param value
     *     allowed object is
     *     {@link DatePropertyType }
     *     
     */
    public void setDateStamp(DatePropertyType value) {
        this.dateStamp = value;
    }

    public boolean isSetDateStamp() {
        return (this.dateStamp!= null);
    }

    /**
     * Recupera il valore della proprietà metadataStandardName.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getMetadataStandardName() {
        return metadataStandardName;
    }

    /**
     * Imposta il valore della proprietà metadataStandardName.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setMetadataStandardName(CharacterStringPropertyType value) {
        this.metadataStandardName = value;
    }

    public boolean isSetMetadataStandardName() {
        return (this.metadataStandardName!= null);
    }

    /**
     * Recupera il valore della proprietà metadataStandardVersion.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getMetadataStandardVersion() {
        return metadataStandardVersion;
    }

    /**
     * Imposta il valore della proprietà metadataStandardVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setMetadataStandardVersion(CharacterStringPropertyType value) {
        this.metadataStandardVersion = value;
    }

    public boolean isSetMetadataStandardVersion() {
        return (this.metadataStandardVersion!= null);
    }

    /**
     * Recupera il valore della proprietà dataSetURI.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDataSetURI() {
        return dataSetURI;
    }

    /**
     * Imposta il valore della proprietà dataSetURI.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDataSetURI(CharacterStringPropertyType value) {
        this.dataSetURI = value;
    }

    public boolean isSetDataSetURI() {
        return (this.dataSetURI!= null);
    }

    /**
     * Gets the value of the locale property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the locale property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLocale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PTLocalePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the locale property.
     */
    public List<PTLocalePropertyType> getLocale() {
        if (locale == null) {
            locale = new ArrayList<>();
        }
        return this.locale;
    }

    public boolean isSetLocale() {
        return ((this.locale!= null)&&(!this.locale.isEmpty()));
    }

    public void unsetLocale() {
        this.locale = null;
    }

    /**
     * Gets the value of the spatialRepresentationInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the spatialRepresentationInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSpatialRepresentationInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDSpatialRepresentationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the spatialRepresentationInfo property.
     */
    public List<MDSpatialRepresentationPropertyType> getSpatialRepresentationInfo() {
        if (spatialRepresentationInfo == null) {
            spatialRepresentationInfo = new ArrayList<>();
        }
        return this.spatialRepresentationInfo;
    }

    public boolean isSetSpatialRepresentationInfo() {
        return ((this.spatialRepresentationInfo!= null)&&(!this.spatialRepresentationInfo.isEmpty()));
    }

    public void unsetSpatialRepresentationInfo() {
        this.spatialRepresentationInfo = null;
    }

    /**
     * Gets the value of the referenceSystemInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the referenceSystemInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getReferenceSystemInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDReferenceSystemPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the referenceSystemInfo property.
     */
    public List<MDReferenceSystemPropertyType> getReferenceSystemInfo() {
        if (referenceSystemInfo == null) {
            referenceSystemInfo = new ArrayList<>();
        }
        return this.referenceSystemInfo;
    }

    public boolean isSetReferenceSystemInfo() {
        return ((this.referenceSystemInfo!= null)&&(!this.referenceSystemInfo.isEmpty()));
    }

    public void unsetReferenceSystemInfo() {
        this.referenceSystemInfo = null;
    }

    /**
     * Gets the value of the metadataExtensionInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the metadataExtensionInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMetadataExtensionInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDMetadataExtensionInformationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the metadataExtensionInfo property.
     */
    public List<MDMetadataExtensionInformationPropertyType> getMetadataExtensionInfo() {
        if (metadataExtensionInfo == null) {
            metadataExtensionInfo = new ArrayList<>();
        }
        return this.metadataExtensionInfo;
    }

    public boolean isSetMetadataExtensionInfo() {
        return ((this.metadataExtensionInfo!= null)&&(!this.metadataExtensionInfo.isEmpty()));
    }

    public void unsetMetadataExtensionInfo() {
        this.metadataExtensionInfo = null;
    }

    /**
     * Gets the value of the identificationInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the identificationInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIdentificationInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDIdentificationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the identificationInfo property.
     */
    public List<MDIdentificationPropertyType> getIdentificationInfo() {
        if (identificationInfo == null) {
            identificationInfo = new ArrayList<>();
        }
        return this.identificationInfo;
    }

    public boolean isSetIdentificationInfo() {
        return ((this.identificationInfo!= null)&&(!this.identificationInfo.isEmpty()));
    }

    public void unsetIdentificationInfo() {
        this.identificationInfo = null;
    }

    /**
     * Gets the value of the contentInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the contentInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getContentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDContentInformationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the contentInfo property.
     */
    public List<MDContentInformationPropertyType> getContentInfo() {
        if (contentInfo == null) {
            contentInfo = new ArrayList<>();
        }
        return this.contentInfo;
    }

    public boolean isSetContentInfo() {
        return ((this.contentInfo!= null)&&(!this.contentInfo.isEmpty()));
    }

    public void unsetContentInfo() {
        this.contentInfo = null;
    }

    /**
     * Recupera il valore della proprietà distributionInfo.
     * 
     * @return
     *     possible object is
     *     {@link MDDistributionPropertyType }
     *     
     */
    public MDDistributionPropertyType getDistributionInfo() {
        return distributionInfo;
    }

    /**
     * Imposta il valore della proprietà distributionInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link MDDistributionPropertyType }
     *     
     */
    public void setDistributionInfo(MDDistributionPropertyType value) {
        this.distributionInfo = value;
    }

    public boolean isSetDistributionInfo() {
        return (this.distributionInfo!= null);
    }

    /**
     * Gets the value of the dataQualityInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dataQualityInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDataQualityInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DQDataQualityPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the dataQualityInfo property.
     */
    public List<DQDataQualityPropertyType> getDataQualityInfo() {
        if (dataQualityInfo == null) {
            dataQualityInfo = new ArrayList<>();
        }
        return this.dataQualityInfo;
    }

    public boolean isSetDataQualityInfo() {
        return ((this.dataQualityInfo!= null)&&(!this.dataQualityInfo.isEmpty()));
    }

    public void unsetDataQualityInfo() {
        this.dataQualityInfo = null;
    }

    /**
     * Gets the value of the portrayalCatalogueInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the portrayalCatalogueInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPortrayalCatalogueInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDPortrayalCatalogueReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the portrayalCatalogueInfo property.
     */
    public List<MDPortrayalCatalogueReferencePropertyType> getPortrayalCatalogueInfo() {
        if (portrayalCatalogueInfo == null) {
            portrayalCatalogueInfo = new ArrayList<>();
        }
        return this.portrayalCatalogueInfo;
    }

    public boolean isSetPortrayalCatalogueInfo() {
        return ((this.portrayalCatalogueInfo!= null)&&(!this.portrayalCatalogueInfo.isEmpty()));
    }

    public void unsetPortrayalCatalogueInfo() {
        this.portrayalCatalogueInfo = null;
    }

    /**
     * Gets the value of the metadataConstraints property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the metadataConstraints property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMetadataConstraints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDConstraintsPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the metadataConstraints property.
     */
    public List<MDConstraintsPropertyType> getMetadataConstraints() {
        if (metadataConstraints == null) {
            metadataConstraints = new ArrayList<>();
        }
        return this.metadataConstraints;
    }

    public boolean isSetMetadataConstraints() {
        return ((this.metadataConstraints!= null)&&(!this.metadataConstraints.isEmpty()));
    }

    public void unsetMetadataConstraints() {
        this.metadataConstraints = null;
    }

    /**
     * Gets the value of the applicationSchemaInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the applicationSchemaInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getApplicationSchemaInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDApplicationSchemaInformationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the applicationSchemaInfo property.
     */
    public List<MDApplicationSchemaInformationPropertyType> getApplicationSchemaInfo() {
        if (applicationSchemaInfo == null) {
            applicationSchemaInfo = new ArrayList<>();
        }
        return this.applicationSchemaInfo;
    }

    public boolean isSetApplicationSchemaInfo() {
        return ((this.applicationSchemaInfo!= null)&&(!this.applicationSchemaInfo.isEmpty()));
    }

    public void unsetApplicationSchemaInfo() {
        this.applicationSchemaInfo = null;
    }

    /**
     * Recupera il valore della proprietà metadataMaintenance.
     * 
     * @return
     *     possible object is
     *     {@link MDMaintenanceInformationPropertyType }
     *     
     */
    public MDMaintenanceInformationPropertyType getMetadataMaintenance() {
        return metadataMaintenance;
    }

    /**
     * Imposta il valore della proprietà metadataMaintenance.
     * 
     * @param value
     *     allowed object is
     *     {@link MDMaintenanceInformationPropertyType }
     *     
     */
    public void setMetadataMaintenance(MDMaintenanceInformationPropertyType value) {
        this.metadataMaintenance = value;
    }

    public boolean isSetMetadataMaintenance() {
        return (this.metadataMaintenance!= null);
    }

    /**
     * Gets the value of the series property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the series property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSeries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DSAggregatePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the series property.
     */
    public List<DSAggregatePropertyType> getSeries() {
        if (series == null) {
            series = new ArrayList<>();
        }
        return this.series;
    }

    public boolean isSetSeries() {
        return ((this.series!= null)&&(!this.series.isEmpty()));
    }

    public void unsetSeries() {
        this.series = null;
    }

    /**
     * Gets the value of the describes property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the describes property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDescribes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DSDataSetPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the describes property.
     */
    public List<DSDataSetPropertyType> getDescribes() {
        if (describes == null) {
            describes = new ArrayList<>();
        }
        return this.describes;
    }

    public boolean isSetDescribes() {
        return ((this.describes!= null)&&(!this.describes.isEmpty()));
    }

    public void unsetDescribes() {
        this.describes = null;
    }

    /**
     * Gets the value of the propertyType property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the propertyType property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPropertyType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the propertyType property.
     */
    public List<ObjectReferencePropertyType> getPropertyType() {
        if (propertyType == null) {
            propertyType = new ArrayList<>();
        }
        return this.propertyType;
    }

    public boolean isSetPropertyType() {
        return ((this.propertyType!= null)&&(!this.propertyType.isEmpty()));
    }

    public void unsetPropertyType() {
        this.propertyType = null;
    }

    /**
     * Gets the value of the featureType property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureType property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureType property.
     */
    public List<ObjectReferencePropertyType> getFeatureType() {
        if (featureType == null) {
            featureType = new ArrayList<>();
        }
        return this.featureType;
    }

    public boolean isSetFeatureType() {
        return ((this.featureType!= null)&&(!this.featureType.isEmpty()));
    }

    public void unsetFeatureType() {
        this.featureType = null;
    }

    /**
     * Gets the value of the featureAttribute property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureAttribute property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectReferencePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureAttribute property.
     */
    public List<ObjectReferencePropertyType> getFeatureAttribute() {
        if (featureAttribute == null) {
            featureAttribute = new ArrayList<>();
        }
        return this.featureAttribute;
    }

    public boolean isSetFeatureAttribute() {
        return ((this.featureAttribute!= null)&&(!this.featureAttribute.isEmpty()));
    }

    public void unsetFeatureAttribute() {
        this.featureAttribute = null;
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
            CharacterStringPropertyType theFileIdentifier;
            theFileIdentifier = this.getFileIdentifier();
            strategy.appendField(locator, this, "fileIdentifier", buffer, theFileIdentifier, this.isSetFileIdentifier());
        }
        {
            CharacterStringPropertyType theLanguage;
            theLanguage = this.getLanguage();
            strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
        }
        {
            MDCharacterSetCodePropertyType theCharacterSet;
            theCharacterSet = this.getCharacterSet();
            strategy.appendField(locator, this, "characterSet", buffer, theCharacterSet, this.isSetCharacterSet());
        }
        {
            CharacterStringPropertyType theParentIdentifier;
            theParentIdentifier = this.getParentIdentifier();
            strategy.appendField(locator, this, "parentIdentifier", buffer, theParentIdentifier, this.isSetParentIdentifier());
        }
        {
            List<MDScopeCodePropertyType> theHierarchyLevel;
            theHierarchyLevel = (this.isSetHierarchyLevel()?this.getHierarchyLevel():null);
            strategy.appendField(locator, this, "hierarchyLevel", buffer, theHierarchyLevel, this.isSetHierarchyLevel());
        }
        {
            List<CharacterStringPropertyType> theHierarchyLevelName;
            theHierarchyLevelName = (this.isSetHierarchyLevelName()?this.getHierarchyLevelName():null);
            strategy.appendField(locator, this, "hierarchyLevelName", buffer, theHierarchyLevelName, this.isSetHierarchyLevelName());
        }
        {
            List<CIResponsiblePartyPropertyType> theContact;
            theContact = (this.isSetContact()?this.getContact():null);
            strategy.appendField(locator, this, "contact", buffer, theContact, this.isSetContact());
        }
        {
            DatePropertyType theDateStamp;
            theDateStamp = this.getDateStamp();
            strategy.appendField(locator, this, "dateStamp", buffer, theDateStamp, this.isSetDateStamp());
        }
        {
            CharacterStringPropertyType theMetadataStandardName;
            theMetadataStandardName = this.getMetadataStandardName();
            strategy.appendField(locator, this, "metadataStandardName", buffer, theMetadataStandardName, this.isSetMetadataStandardName());
        }
        {
            CharacterStringPropertyType theMetadataStandardVersion;
            theMetadataStandardVersion = this.getMetadataStandardVersion();
            strategy.appendField(locator, this, "metadataStandardVersion", buffer, theMetadataStandardVersion, this.isSetMetadataStandardVersion());
        }
        {
            CharacterStringPropertyType theDataSetURI;
            theDataSetURI = this.getDataSetURI();
            strategy.appendField(locator, this, "dataSetURI", buffer, theDataSetURI, this.isSetDataSetURI());
        }
        {
            List<PTLocalePropertyType> theLocale;
            theLocale = (this.isSetLocale()?this.getLocale():null);
            strategy.appendField(locator, this, "locale", buffer, theLocale, this.isSetLocale());
        }
        {
            List<MDSpatialRepresentationPropertyType> theSpatialRepresentationInfo;
            theSpatialRepresentationInfo = (this.isSetSpatialRepresentationInfo()?this.getSpatialRepresentationInfo():null);
            strategy.appendField(locator, this, "spatialRepresentationInfo", buffer, theSpatialRepresentationInfo, this.isSetSpatialRepresentationInfo());
        }
        {
            List<MDReferenceSystemPropertyType> theReferenceSystemInfo;
            theReferenceSystemInfo = (this.isSetReferenceSystemInfo()?this.getReferenceSystemInfo():null);
            strategy.appendField(locator, this, "referenceSystemInfo", buffer, theReferenceSystemInfo, this.isSetReferenceSystemInfo());
        }
        {
            List<MDMetadataExtensionInformationPropertyType> theMetadataExtensionInfo;
            theMetadataExtensionInfo = (this.isSetMetadataExtensionInfo()?this.getMetadataExtensionInfo():null);
            strategy.appendField(locator, this, "metadataExtensionInfo", buffer, theMetadataExtensionInfo, this.isSetMetadataExtensionInfo());
        }
        {
            List<MDIdentificationPropertyType> theIdentificationInfo;
            theIdentificationInfo = (this.isSetIdentificationInfo()?this.getIdentificationInfo():null);
            strategy.appendField(locator, this, "identificationInfo", buffer, theIdentificationInfo, this.isSetIdentificationInfo());
        }
        {
            List<MDContentInformationPropertyType> theContentInfo;
            theContentInfo = (this.isSetContentInfo()?this.getContentInfo():null);
            strategy.appendField(locator, this, "contentInfo", buffer, theContentInfo, this.isSetContentInfo());
        }
        {
            MDDistributionPropertyType theDistributionInfo;
            theDistributionInfo = this.getDistributionInfo();
            strategy.appendField(locator, this, "distributionInfo", buffer, theDistributionInfo, this.isSetDistributionInfo());
        }
        {
            List<DQDataQualityPropertyType> theDataQualityInfo;
            theDataQualityInfo = (this.isSetDataQualityInfo()?this.getDataQualityInfo():null);
            strategy.appendField(locator, this, "dataQualityInfo", buffer, theDataQualityInfo, this.isSetDataQualityInfo());
        }
        {
            List<MDPortrayalCatalogueReferencePropertyType> thePortrayalCatalogueInfo;
            thePortrayalCatalogueInfo = (this.isSetPortrayalCatalogueInfo()?this.getPortrayalCatalogueInfo():null);
            strategy.appendField(locator, this, "portrayalCatalogueInfo", buffer, thePortrayalCatalogueInfo, this.isSetPortrayalCatalogueInfo());
        }
        {
            List<MDConstraintsPropertyType> theMetadataConstraints;
            theMetadataConstraints = (this.isSetMetadataConstraints()?this.getMetadataConstraints():null);
            strategy.appendField(locator, this, "metadataConstraints", buffer, theMetadataConstraints, this.isSetMetadataConstraints());
        }
        {
            List<MDApplicationSchemaInformationPropertyType> theApplicationSchemaInfo;
            theApplicationSchemaInfo = (this.isSetApplicationSchemaInfo()?this.getApplicationSchemaInfo():null);
            strategy.appendField(locator, this, "applicationSchemaInfo", buffer, theApplicationSchemaInfo, this.isSetApplicationSchemaInfo());
        }
        {
            MDMaintenanceInformationPropertyType theMetadataMaintenance;
            theMetadataMaintenance = this.getMetadataMaintenance();
            strategy.appendField(locator, this, "metadataMaintenance", buffer, theMetadataMaintenance, this.isSetMetadataMaintenance());
        }
        {
            List<DSAggregatePropertyType> theSeries;
            theSeries = (this.isSetSeries()?this.getSeries():null);
            strategy.appendField(locator, this, "series", buffer, theSeries, this.isSetSeries());
        }
        {
            List<DSDataSetPropertyType> theDescribes;
            theDescribes = (this.isSetDescribes()?this.getDescribes():null);
            strategy.appendField(locator, this, "describes", buffer, theDescribes, this.isSetDescribes());
        }
        {
            List<ObjectReferencePropertyType> thePropertyType;
            thePropertyType = (this.isSetPropertyType()?this.getPropertyType():null);
            strategy.appendField(locator, this, "propertyType", buffer, thePropertyType, this.isSetPropertyType());
        }
        {
            List<ObjectReferencePropertyType> theFeatureType;
            theFeatureType = (this.isSetFeatureType()?this.getFeatureType():null);
            strategy.appendField(locator, this, "featureType", buffer, theFeatureType, this.isSetFeatureType());
        }
        {
            List<ObjectReferencePropertyType> theFeatureAttribute;
            theFeatureAttribute = (this.isSetFeatureAttribute()?this.getFeatureAttribute():null);
            strategy.appendField(locator, this, "featureAttribute", buffer, theFeatureAttribute, this.isSetFeatureAttribute());
        }
        return buffer;
    }

    public void setHierarchyLevel(List<MDScopeCodePropertyType> value) {
        this.hierarchyLevel = null;
        if (value!= null) {
            List<MDScopeCodePropertyType> draftl = this.getHierarchyLevel();
            draftl.addAll(value);
        }
    }

    public void setHierarchyLevelName(List<CharacterStringPropertyType> value) {
        this.hierarchyLevelName = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getHierarchyLevelName();
            draftl.addAll(value);
        }
    }

    public void setContact(List<CIResponsiblePartyPropertyType> value) {
        this.contact = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getContact();
            draftl.addAll(value);
        }
    }

    public void setLocale(List<PTLocalePropertyType> value) {
        this.locale = null;
        if (value!= null) {
            List<PTLocalePropertyType> draftl = this.getLocale();
            draftl.addAll(value);
        }
    }

    public void setSpatialRepresentationInfo(List<MDSpatialRepresentationPropertyType> value) {
        this.spatialRepresentationInfo = null;
        if (value!= null) {
            List<MDSpatialRepresentationPropertyType> draftl = this.getSpatialRepresentationInfo();
            draftl.addAll(value);
        }
    }

    public void setReferenceSystemInfo(List<MDReferenceSystemPropertyType> value) {
        this.referenceSystemInfo = null;
        if (value!= null) {
            List<MDReferenceSystemPropertyType> draftl = this.getReferenceSystemInfo();
            draftl.addAll(value);
        }
    }

    public void setMetadataExtensionInfo(List<MDMetadataExtensionInformationPropertyType> value) {
        this.metadataExtensionInfo = null;
        if (value!= null) {
            List<MDMetadataExtensionInformationPropertyType> draftl = this.getMetadataExtensionInfo();
            draftl.addAll(value);
        }
    }

    public void setIdentificationInfo(List<MDIdentificationPropertyType> value) {
        this.identificationInfo = null;
        if (value!= null) {
            List<MDIdentificationPropertyType> draftl = this.getIdentificationInfo();
            draftl.addAll(value);
        }
    }

    public void setContentInfo(List<MDContentInformationPropertyType> value) {
        this.contentInfo = null;
        if (value!= null) {
            List<MDContentInformationPropertyType> draftl = this.getContentInfo();
            draftl.addAll(value);
        }
    }

    public void setDataQualityInfo(List<DQDataQualityPropertyType> value) {
        this.dataQualityInfo = null;
        if (value!= null) {
            List<DQDataQualityPropertyType> draftl = this.getDataQualityInfo();
            draftl.addAll(value);
        }
    }

    public void setPortrayalCatalogueInfo(List<MDPortrayalCatalogueReferencePropertyType> value) {
        this.portrayalCatalogueInfo = null;
        if (value!= null) {
            List<MDPortrayalCatalogueReferencePropertyType> draftl = this.getPortrayalCatalogueInfo();
            draftl.addAll(value);
        }
    }

    public void setMetadataConstraints(List<MDConstraintsPropertyType> value) {
        this.metadataConstraints = null;
        if (value!= null) {
            List<MDConstraintsPropertyType> draftl = this.getMetadataConstraints();
            draftl.addAll(value);
        }
    }

    public void setApplicationSchemaInfo(List<MDApplicationSchemaInformationPropertyType> value) {
        this.applicationSchemaInfo = null;
        if (value!= null) {
            List<MDApplicationSchemaInformationPropertyType> draftl = this.getApplicationSchemaInfo();
            draftl.addAll(value);
        }
    }

    public void setSeries(List<DSAggregatePropertyType> value) {
        this.series = null;
        if (value!= null) {
            List<DSAggregatePropertyType> draftl = this.getSeries();
            draftl.addAll(value);
        }
    }

    public void setDescribes(List<DSDataSetPropertyType> value) {
        this.describes = null;
        if (value!= null) {
            List<DSDataSetPropertyType> draftl = this.getDescribes();
            draftl.addAll(value);
        }
    }

    public void setPropertyType(List<ObjectReferencePropertyType> value) {
        this.propertyType = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getPropertyType();
            draftl.addAll(value);
        }
    }

    public void setFeatureType(List<ObjectReferencePropertyType> value) {
        this.featureType = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getFeatureType();
            draftl.addAll(value);
        }
    }

    public void setFeatureAttribute(List<ObjectReferencePropertyType> value) {
        this.featureAttribute = null;
        if (value!= null) {
            List<ObjectReferencePropertyType> draftl = this.getFeatureAttribute();
            draftl.addAll(value);
        }
    }

}
