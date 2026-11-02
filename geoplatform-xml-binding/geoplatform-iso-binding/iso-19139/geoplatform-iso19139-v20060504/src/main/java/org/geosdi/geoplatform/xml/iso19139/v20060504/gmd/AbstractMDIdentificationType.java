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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.src.SVServiceIdentificationType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Basic information about data
 * 
 * <p>Classe Java per AbstractMD_Identification_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractMD_Identification_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="citation" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType"/>
 *         <element name="abstract" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="purpose" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="credit" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="status" type="{http://www.isotc211.org/2005/gmd}MD_ProgressCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="pointOfContact" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="resourceMaintenance" type="{http://www.isotc211.org/2005/gmd}MD_MaintenanceInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="graphicOverview" type="{http://www.isotc211.org/2005/gmd}MD_BrowseGraphic_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="resourceFormat" type="{http://www.isotc211.org/2005/gmd}MD_Format_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="descriptiveKeywords" type="{http://www.isotc211.org/2005/gmd}MD_Keywords_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="resourceSpecificUsage" type="{http://www.isotc211.org/2005/gmd}MD_Usage_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="resourceConstraints" type="{http://www.isotc211.org/2005/gmd}MD_Constraints_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="aggregationInfo" type="{http://www.isotc211.org/2005/gmd}MD_AggregateInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMD_Identification_Type", propOrder = {
    "citation",
    "_abstract",
    "purpose",
    "credit",
    "status",
    "pointOfContact",
    "resourceMaintenance",
    "graphicOverview",
    "resourceFormat",
    "descriptiveKeywords",
    "resourceSpecificUsage",
    "resourceConstraints",
    "aggregationInfo"
})
@XmlSeeAlso({
    MDDataIdentificationType.class,
    MDServiceIdentificationType.class,
    SVServiceIdentificationType.class
})
public abstract class AbstractMDIdentificationType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CICitationPropertyType citation;
    @XmlElement(name = "abstract", required = true)
    protected CharacterStringPropertyType _abstract;
    protected CharacterStringPropertyType purpose;
    protected List<CharacterStringPropertyType> credit;
    protected List<MDProgressCodePropertyType> status;
    protected List<CIResponsiblePartyPropertyType> pointOfContact;
    protected List<MDMaintenanceInformationPropertyType> resourceMaintenance;
    protected List<MDBrowseGraphicPropertyType> graphicOverview;
    protected List<MDFormatPropertyType> resourceFormat;
    protected List<MDKeywordsPropertyType> descriptiveKeywords;
    protected List<MDUsagePropertyType> resourceSpecificUsage;
    protected List<MDConstraintsPropertyType> resourceConstraints;
    protected List<MDAggregateInformationPropertyType> aggregationInfo;

    /**
     * Recupera il valore della proprietà citation.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getCitation() {
        return citation;
    }

    /**
     * Imposta il valore della proprietà citation.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setCitation(CICitationPropertyType value) {
        this.citation = value;
    }

    public boolean isSetCitation() {
        return (this.citation!= null);
    }

    /**
     * Recupera il valore della proprietà abstract.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setAbstract(CharacterStringPropertyType value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract!= null);
    }

    /**
     * Recupera il valore della proprietà purpose.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getPurpose() {
        return purpose;
    }

    /**
     * Imposta il valore della proprietà purpose.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setPurpose(CharacterStringPropertyType value) {
        this.purpose = value;
    }

    public boolean isSetPurpose() {
        return (this.purpose!= null);
    }

    /**
     * Gets the value of the credit property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the credit property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCredit().add(newItem);
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
     *     The value of the credit property.
     */
    public List<CharacterStringPropertyType> getCredit() {
        if (credit == null) {
            credit = new ArrayList<>();
        }
        return this.credit;
    }

    public boolean isSetCredit() {
        return ((this.credit!= null)&&(!this.credit.isEmpty()));
    }

    public void unsetCredit() {
        this.credit = null;
    }

    /**
     * Gets the value of the status property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the status property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDProgressCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the status property.
     */
    public List<MDProgressCodePropertyType> getStatus() {
        if (status == null) {
            status = new ArrayList<>();
        }
        return this.status;
    }

    public boolean isSetStatus() {
        return ((this.status!= null)&&(!this.status.isEmpty()));
    }

    public void unsetStatus() {
        this.status = null;
    }

    /**
     * Gets the value of the pointOfContact property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pointOfContact property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPointOfContact().add(newItem);
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
     *     The value of the pointOfContact property.
     */
    public List<CIResponsiblePartyPropertyType> getPointOfContact() {
        if (pointOfContact == null) {
            pointOfContact = new ArrayList<>();
        }
        return this.pointOfContact;
    }

    public boolean isSetPointOfContact() {
        return ((this.pointOfContact!= null)&&(!this.pointOfContact.isEmpty()));
    }

    public void unsetPointOfContact() {
        this.pointOfContact = null;
    }

    /**
     * Gets the value of the resourceMaintenance property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the resourceMaintenance property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getResourceMaintenance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDMaintenanceInformationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the resourceMaintenance property.
     */
    public List<MDMaintenanceInformationPropertyType> getResourceMaintenance() {
        if (resourceMaintenance == null) {
            resourceMaintenance = new ArrayList<>();
        }
        return this.resourceMaintenance;
    }

    public boolean isSetResourceMaintenance() {
        return ((this.resourceMaintenance!= null)&&(!this.resourceMaintenance.isEmpty()));
    }

    public void unsetResourceMaintenance() {
        this.resourceMaintenance = null;
    }

    /**
     * Gets the value of the graphicOverview property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the graphicOverview property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getGraphicOverview().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDBrowseGraphicPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the graphicOverview property.
     */
    public List<MDBrowseGraphicPropertyType> getGraphicOverview() {
        if (graphicOverview == null) {
            graphicOverview = new ArrayList<>();
        }
        return this.graphicOverview;
    }

    public boolean isSetGraphicOverview() {
        return ((this.graphicOverview!= null)&&(!this.graphicOverview.isEmpty()));
    }

    public void unsetGraphicOverview() {
        this.graphicOverview = null;
    }

    /**
     * Gets the value of the resourceFormat property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the resourceFormat property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getResourceFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDFormatPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the resourceFormat property.
     */
    public List<MDFormatPropertyType> getResourceFormat() {
        if (resourceFormat == null) {
            resourceFormat = new ArrayList<>();
        }
        return this.resourceFormat;
    }

    public boolean isSetResourceFormat() {
        return ((this.resourceFormat!= null)&&(!this.resourceFormat.isEmpty()));
    }

    public void unsetResourceFormat() {
        this.resourceFormat = null;
    }

    /**
     * Gets the value of the descriptiveKeywords property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the descriptiveKeywords property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDescriptiveKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDKeywordsPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the descriptiveKeywords property.
     */
    public List<MDKeywordsPropertyType> getDescriptiveKeywords() {
        if (descriptiveKeywords == null) {
            descriptiveKeywords = new ArrayList<>();
        }
        return this.descriptiveKeywords;
    }

    public boolean isSetDescriptiveKeywords() {
        return ((this.descriptiveKeywords!= null)&&(!this.descriptiveKeywords.isEmpty()));
    }

    public void unsetDescriptiveKeywords() {
        this.descriptiveKeywords = null;
    }

    /**
     * Gets the value of the resourceSpecificUsage property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the resourceSpecificUsage property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getResourceSpecificUsage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDUsagePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the resourceSpecificUsage property.
     */
    public List<MDUsagePropertyType> getResourceSpecificUsage() {
        if (resourceSpecificUsage == null) {
            resourceSpecificUsage = new ArrayList<>();
        }
        return this.resourceSpecificUsage;
    }

    public boolean isSetResourceSpecificUsage() {
        return ((this.resourceSpecificUsage!= null)&&(!this.resourceSpecificUsage.isEmpty()));
    }

    public void unsetResourceSpecificUsage() {
        this.resourceSpecificUsage = null;
    }

    /**
     * Gets the value of the resourceConstraints property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the resourceConstraints property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getResourceConstraints().add(newItem);
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
     *     The value of the resourceConstraints property.
     */
    public List<MDConstraintsPropertyType> getResourceConstraints() {
        if (resourceConstraints == null) {
            resourceConstraints = new ArrayList<>();
        }
        return this.resourceConstraints;
    }

    public boolean isSetResourceConstraints() {
        return ((this.resourceConstraints!= null)&&(!this.resourceConstraints.isEmpty()));
    }

    public void unsetResourceConstraints() {
        this.resourceConstraints = null;
    }

    /**
     * Gets the value of the aggregationInfo property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the aggregationInfo property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAggregationInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDAggregateInformationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the aggregationInfo property.
     */
    public List<MDAggregateInformationPropertyType> getAggregationInfo() {
        if (aggregationInfo == null) {
            aggregationInfo = new ArrayList<>();
        }
        return this.aggregationInfo;
    }

    public boolean isSetAggregationInfo() {
        return ((this.aggregationInfo!= null)&&(!this.aggregationInfo.isEmpty()));
    }

    public void unsetAggregationInfo() {
        this.aggregationInfo = null;
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
            CICitationPropertyType theCitation;
            theCitation = this.getCitation();
            strategy.appendField(locator, this, "citation", buffer, theCitation, this.isSetCitation());
        }
        {
            CharacterStringPropertyType theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, this.isSetAbstract());
        }
        {
            CharacterStringPropertyType thePurpose;
            thePurpose = this.getPurpose();
            strategy.appendField(locator, this, "purpose", buffer, thePurpose, this.isSetPurpose());
        }
        {
            List<CharacterStringPropertyType> theCredit;
            theCredit = (this.isSetCredit()?this.getCredit():null);
            strategy.appendField(locator, this, "credit", buffer, theCredit, this.isSetCredit());
        }
        {
            List<MDProgressCodePropertyType> theStatus;
            theStatus = (this.isSetStatus()?this.getStatus():null);
            strategy.appendField(locator, this, "status", buffer, theStatus, this.isSetStatus());
        }
        {
            List<CIResponsiblePartyPropertyType> thePointOfContact;
            thePointOfContact = (this.isSetPointOfContact()?this.getPointOfContact():null);
            strategy.appendField(locator, this, "pointOfContact", buffer, thePointOfContact, this.isSetPointOfContact());
        }
        {
            List<MDMaintenanceInformationPropertyType> theResourceMaintenance;
            theResourceMaintenance = (this.isSetResourceMaintenance()?this.getResourceMaintenance():null);
            strategy.appendField(locator, this, "resourceMaintenance", buffer, theResourceMaintenance, this.isSetResourceMaintenance());
        }
        {
            List<MDBrowseGraphicPropertyType> theGraphicOverview;
            theGraphicOverview = (this.isSetGraphicOverview()?this.getGraphicOverview():null);
            strategy.appendField(locator, this, "graphicOverview", buffer, theGraphicOverview, this.isSetGraphicOverview());
        }
        {
            List<MDFormatPropertyType> theResourceFormat;
            theResourceFormat = (this.isSetResourceFormat()?this.getResourceFormat():null);
            strategy.appendField(locator, this, "resourceFormat", buffer, theResourceFormat, this.isSetResourceFormat());
        }
        {
            List<MDKeywordsPropertyType> theDescriptiveKeywords;
            theDescriptiveKeywords = (this.isSetDescriptiveKeywords()?this.getDescriptiveKeywords():null);
            strategy.appendField(locator, this, "descriptiveKeywords", buffer, theDescriptiveKeywords, this.isSetDescriptiveKeywords());
        }
        {
            List<MDUsagePropertyType> theResourceSpecificUsage;
            theResourceSpecificUsage = (this.isSetResourceSpecificUsage()?this.getResourceSpecificUsage():null);
            strategy.appendField(locator, this, "resourceSpecificUsage", buffer, theResourceSpecificUsage, this.isSetResourceSpecificUsage());
        }
        {
            List<MDConstraintsPropertyType> theResourceConstraints;
            theResourceConstraints = (this.isSetResourceConstraints()?this.getResourceConstraints():null);
            strategy.appendField(locator, this, "resourceConstraints", buffer, theResourceConstraints, this.isSetResourceConstraints());
        }
        {
            List<MDAggregateInformationPropertyType> theAggregationInfo;
            theAggregationInfo = (this.isSetAggregationInfo()?this.getAggregationInfo():null);
            strategy.appendField(locator, this, "aggregationInfo", buffer, theAggregationInfo, this.isSetAggregationInfo());
        }
        return buffer;
    }

    public void setCredit(List<CharacterStringPropertyType> value) {
        this.credit = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getCredit();
            draftl.addAll(value);
        }
    }

    public void setStatus(List<MDProgressCodePropertyType> value) {
        this.status = null;
        if (value!= null) {
            List<MDProgressCodePropertyType> draftl = this.getStatus();
            draftl.addAll(value);
        }
    }

    public void setPointOfContact(List<CIResponsiblePartyPropertyType> value) {
        this.pointOfContact = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getPointOfContact();
            draftl.addAll(value);
        }
    }

    public void setResourceMaintenance(List<MDMaintenanceInformationPropertyType> value) {
        this.resourceMaintenance = null;
        if (value!= null) {
            List<MDMaintenanceInformationPropertyType> draftl = this.getResourceMaintenance();
            draftl.addAll(value);
        }
    }

    public void setGraphicOverview(List<MDBrowseGraphicPropertyType> value) {
        this.graphicOverview = null;
        if (value!= null) {
            List<MDBrowseGraphicPropertyType> draftl = this.getGraphicOverview();
            draftl.addAll(value);
        }
    }

    public void setResourceFormat(List<MDFormatPropertyType> value) {
        this.resourceFormat = null;
        if (value!= null) {
            List<MDFormatPropertyType> draftl = this.getResourceFormat();
            draftl.addAll(value);
        }
    }

    public void setDescriptiveKeywords(List<MDKeywordsPropertyType> value) {
        this.descriptiveKeywords = null;
        if (value!= null) {
            List<MDKeywordsPropertyType> draftl = this.getDescriptiveKeywords();
            draftl.addAll(value);
        }
    }

    public void setResourceSpecificUsage(List<MDUsagePropertyType> value) {
        this.resourceSpecificUsage = null;
        if (value!= null) {
            List<MDUsagePropertyType> draftl = this.getResourceSpecificUsage();
            draftl.addAll(value);
        }
    }

    public void setResourceConstraints(List<MDConstraintsPropertyType> value) {
        this.resourceConstraints = null;
        if (value!= null) {
            List<MDConstraintsPropertyType> draftl = this.getResourceConstraints();
            draftl.addAll(value);
        }
    }

    public void setAggregationInfo(List<MDAggregateInformationPropertyType> value) {
        this.aggregationInfo = null;
        if (value!= null) {
            List<MDAggregateInformationPropertyType> draftl = this.getAggregationInfo();
            draftl.addAll(value);
        }
    }

}
