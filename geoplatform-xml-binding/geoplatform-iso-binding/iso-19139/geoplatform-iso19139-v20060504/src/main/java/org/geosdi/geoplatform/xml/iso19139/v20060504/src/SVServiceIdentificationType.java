/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Generated on: 2015.06.17 at 04:41:23 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20060504.src;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.GenericNamePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.AbstractMDIdentificationType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.EXExtentPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDConstraintsPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDDataIdentificationPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDKeywordsPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDStandardOrderProcessPropertyType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for SV_ServiceIdentification_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SV_ServiceIdentification_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.isotc211.org/2005/gmd}AbstractMD_Identification_Type">
 *       &lt;sequence>
 *         &lt;element name="serviceType" type="{http://www.isotc211.org/2005/gco}GenericName_PropertyType"/>
 *         &lt;element name="serviceTypeVersion" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accessProperties" type="{http://www.isotc211.org/2005/gmd}MD_StandardOrderProcess_PropertyType" minOccurs="0"/>
 *         &lt;element name="restrictions" type="{http://www.isotc211.org/2005/gmd}MD_Constraints_PropertyType" minOccurs="0"/>
 *         &lt;element name="keywords" type="{http://www.isotc211.org/2005/gmd}MD_Keywords_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extent" type="{http://www.isotc211.org/2005/gmd}EX_Extent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="coupledResource" type="{http://www.isotc211.org/2005/srv}SV_CoupledResource_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="couplingType" type="{http://www.isotc211.org/2005/srv}SV_CouplingType_PropertyType"/>
 *         &lt;element name="containsOperations" type="{http://www.isotc211.org/2005/srv}SV_OperationMetadata_PropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="operatesOn" type="{http://www.isotc211.org/2005/gmd}MD_DataIdentification_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SV_ServiceIdentification_Type", propOrder = {
    "serviceType",
    "serviceTypeVersion",
    "accessProperties",
    "restrictions",
    "keywords",
    "extent",
    "coupledResource",
    "couplingType",
    "containsOperations",
    "operatesOn"
})
public class SVServiceIdentificationType
    extends AbstractMDIdentificationType
    implements ToString
{

    @XmlElement(required = true)
    protected GenericNamePropertyType serviceType;
    protected List<CharacterStringPropertyType> serviceTypeVersion;
    protected MDStandardOrderProcessPropertyType accessProperties;
    protected MDConstraintsPropertyType restrictions;
    protected List<MDKeywordsPropertyType> keywords;
    protected List<EXExtentPropertyType> extent;
    protected List<SVCoupledResourcePropertyType> coupledResource;
    @XmlElement(required = true)
    protected SVCouplingTypePropertyType couplingType;
    @XmlElement(required = true)
    protected List<SVOperationMetadataPropertyType> containsOperations;
    protected List<MDDataIdentificationPropertyType> operatesOn;

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link GenericNamePropertyType }
     *     
     */
    public GenericNamePropertyType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericNamePropertyType }
     *     
     */
    public void setServiceType(GenericNamePropertyType value) {
        this.serviceType = value;
    }

    public boolean isSetServiceType() {
        return (this.serviceType!= null);
    }

    /**
     * Gets the value of the serviceTypeVersion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceTypeVersion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceTypeVersion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * 
     * 
     */
    public List<CharacterStringPropertyType> getServiceTypeVersion() {
        if (serviceTypeVersion == null) {
            serviceTypeVersion = new ArrayList<CharacterStringPropertyType>();
        }
        return this.serviceTypeVersion;
    }

    public boolean isSetServiceTypeVersion() {
        return ((this.serviceTypeVersion!= null)&&(!this.serviceTypeVersion.isEmpty()));
    }

    public void unsetServiceTypeVersion() {
        this.serviceTypeVersion = null;
    }

    /**
     * Gets the value of the accessProperties property.
     * 
     * @return
     *     possible object is
     *     {@link MDStandardOrderProcessPropertyType }
     *     
     */
    public MDStandardOrderProcessPropertyType getAccessProperties() {
        return accessProperties;
    }

    /**
     * Sets the value of the accessProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link MDStandardOrderProcessPropertyType }
     *     
     */
    public void setAccessProperties(MDStandardOrderProcessPropertyType value) {
        this.accessProperties = value;
    }

    public boolean isSetAccessProperties() {
        return (this.accessProperties!= null);
    }

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link MDConstraintsPropertyType }
     *     
     */
    public MDConstraintsPropertyType getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link MDConstraintsPropertyType }
     *     
     */
    public void setRestrictions(MDConstraintsPropertyType value) {
        this.restrictions = value;
    }

    public boolean isSetRestrictions() {
        return (this.restrictions!= null);
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDKeywordsPropertyType }
     * 
     * 
     */
    public List<MDKeywordsPropertyType> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<MDKeywordsPropertyType>();
        }
        return this.keywords;
    }

    public boolean isSetKeywords() {
        return ((this.keywords!= null)&&(!this.keywords.isEmpty()));
    }

    public void unsetKeywords() {
        this.keywords = null;
    }

    /**
     * Gets the value of the extent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXExtentPropertyType }
     * 
     * 
     */
    public List<EXExtentPropertyType> getExtent() {
        if (extent == null) {
            extent = new ArrayList<EXExtentPropertyType>();
        }
        return this.extent;
    }

    public boolean isSetExtent() {
        return ((this.extent!= null)&&(!this.extent.isEmpty()));
    }

    public void unsetExtent() {
        this.extent = null;
    }

    /**
     * Gets the value of the coupledResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coupledResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoupledResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SVCoupledResourcePropertyType }
     * 
     * 
     */
    public List<SVCoupledResourcePropertyType> getCoupledResource() {
        if (coupledResource == null) {
            coupledResource = new ArrayList<SVCoupledResourcePropertyType>();
        }
        return this.coupledResource;
    }

    public boolean isSetCoupledResource() {
        return ((this.coupledResource!= null)&&(!this.coupledResource.isEmpty()));
    }

    public void unsetCoupledResource() {
        this.coupledResource = null;
    }

    /**
     * Gets the value of the couplingType property.
     * 
     * @return
     *     possible object is
     *     {@link SVCouplingTypePropertyType }
     *     
     */
    public SVCouplingTypePropertyType getCouplingType() {
        return couplingType;
    }

    /**
     * Sets the value of the couplingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SVCouplingTypePropertyType }
     *     
     */
    public void setCouplingType(SVCouplingTypePropertyType value) {
        this.couplingType = value;
    }

    public boolean isSetCouplingType() {
        return (this.couplingType!= null);
    }

    /**
     * Gets the value of the containsOperations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the containsOperations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContainsOperations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SVOperationMetadataPropertyType }
     * 
     * 
     */
    public List<SVOperationMetadataPropertyType> getContainsOperations() {
        if (containsOperations == null) {
            containsOperations = new ArrayList<SVOperationMetadataPropertyType>();
        }
        return this.containsOperations;
    }

    public boolean isSetContainsOperations() {
        return ((this.containsOperations!= null)&&(!this.containsOperations.isEmpty()));
    }

    public void unsetContainsOperations() {
        this.containsOperations = null;
    }

    /**
     * Gets the value of the operatesOn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatesOn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatesOn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDDataIdentificationPropertyType }
     * 
     * 
     */
    public List<MDDataIdentificationPropertyType> getOperatesOn() {
        if (operatesOn == null) {
            operatesOn = new ArrayList<MDDataIdentificationPropertyType>();
        }
        return this.operatesOn;
    }

    public boolean isSetOperatesOn() {
        return ((this.operatesOn!= null)&&(!this.operatesOn.isEmpty()));
    }

    public void unsetOperatesOn() {
        this.operatesOn = null;
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
            GenericNamePropertyType theServiceType;
            theServiceType = this.getServiceType();
            strategy.appendField(locator, this, "serviceType", buffer, theServiceType);
        }
        {
            List<CharacterStringPropertyType> theServiceTypeVersion;
            theServiceTypeVersion = (this.isSetServiceTypeVersion()?this.getServiceTypeVersion():null);
            strategy.appendField(locator, this, "serviceTypeVersion", buffer, theServiceTypeVersion);
        }
        {
            MDStandardOrderProcessPropertyType theAccessProperties;
            theAccessProperties = this.getAccessProperties();
            strategy.appendField(locator, this, "accessProperties", buffer, theAccessProperties);
        }
        {
            MDConstraintsPropertyType theRestrictions;
            theRestrictions = this.getRestrictions();
            strategy.appendField(locator, this, "restrictions", buffer, theRestrictions);
        }
        {
            List<MDKeywordsPropertyType> theKeywords;
            theKeywords = (this.isSetKeywords()?this.getKeywords():null);
            strategy.appendField(locator, this, "keywords", buffer, theKeywords);
        }
        {
            List<EXExtentPropertyType> theExtent;
            theExtent = (this.isSetExtent()?this.getExtent():null);
            strategy.appendField(locator, this, "extent", buffer, theExtent);
        }
        {
            List<SVCoupledResourcePropertyType> theCoupledResource;
            theCoupledResource = (this.isSetCoupledResource()?this.getCoupledResource():null);
            strategy.appendField(locator, this, "coupledResource", buffer, theCoupledResource);
        }
        {
            SVCouplingTypePropertyType theCouplingType;
            theCouplingType = this.getCouplingType();
            strategy.appendField(locator, this, "couplingType", buffer, theCouplingType);
        }
        {
            List<SVOperationMetadataPropertyType> theContainsOperations;
            theContainsOperations = (this.isSetContainsOperations()?this.getContainsOperations():null);
            strategy.appendField(locator, this, "containsOperations", buffer, theContainsOperations);
        }
        {
            List<MDDataIdentificationPropertyType> theOperatesOn;
            theOperatesOn = (this.isSetOperatesOn()?this.getOperatesOn():null);
            strategy.appendField(locator, this, "operatesOn", buffer, theOperatesOn);
        }
        return buffer;
    }

    public void setServiceTypeVersion(List<CharacterStringPropertyType> value) {
        this.serviceTypeVersion = null;
        List<CharacterStringPropertyType> draftl = this.getServiceTypeVersion();
        draftl.addAll(value);
    }

    public void setKeywords(List<MDKeywordsPropertyType> value) {
        this.keywords = null;
        List<MDKeywordsPropertyType> draftl = this.getKeywords();
        draftl.addAll(value);
    }

    public void setExtent(List<EXExtentPropertyType> value) {
        this.extent = null;
        List<EXExtentPropertyType> draftl = this.getExtent();
        draftl.addAll(value);
    }

    public void setCoupledResource(List<SVCoupledResourcePropertyType> value) {
        this.coupledResource = null;
        List<SVCoupledResourcePropertyType> draftl = this.getCoupledResource();
        draftl.addAll(value);
    }

    public void setContainsOperations(List<SVOperationMetadataPropertyType> value) {
        this.containsOperations = null;
        List<SVOperationMetadataPropertyType> draftl = this.getContainsOperations();
        draftl.addAll(value);
    }

    public void setOperatesOn(List<MDDataIdentificationPropertyType> value) {
        this.operatesOn = null;
        List<MDDataIdentificationPropertyType> draftl = this.getOperatesOn();
        draftl.addAll(value);
    }

}
