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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.GenericNamePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.AbstractMDIdentificationType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.EXExtentPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDConstraintsPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDDataIdentificationPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDKeywordsPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gmd.MDStandardOrderProcessPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per SV_ServiceIdentification_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SV_ServiceIdentification_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmd}AbstractMD_Identification_Type">
 *       <sequence>
 *         <element name="serviceType" type="{http://www.isotc211.org/2005/gco}GenericName_PropertyType"/>
 *         <element name="serviceTypeVersion" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="accessProperties" type="{http://www.isotc211.org/2005/gmd}MD_StandardOrderProcess_PropertyType" minOccurs="0"/>
 *         <element name="restrictions" type="{http://www.isotc211.org/2005/gmd}MD_Constraints_PropertyType" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.isotc211.org/2005/gmd}MD_Keywords_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="extent" type="{http://www.isotc211.org/2005/gmd}EX_Extent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="coupledResource" type="{http://www.isotc211.org/2005/srv}SV_CoupledResource_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="couplingType" type="{http://www.isotc211.org/2005/srv}SV_CouplingType_PropertyType"/>
 *         <element name="containsOperations" type="{http://www.isotc211.org/2005/srv}SV_OperationMetadata_PropertyType" maxOccurs="unbounded"/>
 *         <element name="operatesOn" type="{http://www.isotc211.org/2005/gmd}MD_DataIdentification_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
     * Recupera il valore della proprietà serviceType.
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
     * Imposta il valore della proprietà serviceType.
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the serviceTypeVersion property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getServiceTypeVersion().add(newItem);
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
     *     The value of the serviceTypeVersion property.
     */
    public List<CharacterStringPropertyType> getServiceTypeVersion() {
        if (serviceTypeVersion == null) {
            serviceTypeVersion = new ArrayList<>();
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
     * Recupera il valore della proprietà accessProperties.
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
     * Imposta il valore della proprietà accessProperties.
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
     * Recupera il valore della proprietà restrictions.
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
     * Imposta il valore della proprietà restrictions.
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the keywords property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getKeywords().add(newItem);
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
     *     The value of the keywords property.
     */
    public List<MDKeywordsPropertyType> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the extent property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXExtentPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the extent property.
     */
    public List<EXExtentPropertyType> getExtent() {
        if (extent == null) {
            extent = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the coupledResource property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCoupledResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SVCoupledResourcePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the coupledResource property.
     */
    public List<SVCoupledResourcePropertyType> getCoupledResource() {
        if (coupledResource == null) {
            coupledResource = new ArrayList<>();
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
     * Recupera il valore della proprietà couplingType.
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
     * Imposta il valore della proprietà couplingType.
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the containsOperations property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getContainsOperations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SVOperationMetadataPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the containsOperations property.
     */
    public List<SVOperationMetadataPropertyType> getContainsOperations() {
        if (containsOperations == null) {
            containsOperations = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the operatesOn property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getOperatesOn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDDataIdentificationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the operatesOn property.
     */
    public List<MDDataIdentificationPropertyType> getOperatesOn() {
        if (operatesOn == null) {
            operatesOn = new ArrayList<>();
        }
        return this.operatesOn;
    }

    public boolean isSetOperatesOn() {
        return ((this.operatesOn!= null)&&(!this.operatesOn.isEmpty()));
    }

    public void unsetOperatesOn() {
        this.operatesOn = null;
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
            GenericNamePropertyType theServiceType;
            theServiceType = this.getServiceType();
            strategy.appendField(locator, this, "serviceType", buffer, theServiceType, this.isSetServiceType());
        }
        {
            List<CharacterStringPropertyType> theServiceTypeVersion;
            theServiceTypeVersion = (this.isSetServiceTypeVersion()?this.getServiceTypeVersion():null);
            strategy.appendField(locator, this, "serviceTypeVersion", buffer, theServiceTypeVersion, this.isSetServiceTypeVersion());
        }
        {
            MDStandardOrderProcessPropertyType theAccessProperties;
            theAccessProperties = this.getAccessProperties();
            strategy.appendField(locator, this, "accessProperties", buffer, theAccessProperties, this.isSetAccessProperties());
        }
        {
            MDConstraintsPropertyType theRestrictions;
            theRestrictions = this.getRestrictions();
            strategy.appendField(locator, this, "restrictions", buffer, theRestrictions, this.isSetRestrictions());
        }
        {
            List<MDKeywordsPropertyType> theKeywords;
            theKeywords = (this.isSetKeywords()?this.getKeywords():null);
            strategy.appendField(locator, this, "keywords", buffer, theKeywords, this.isSetKeywords());
        }
        {
            List<EXExtentPropertyType> theExtent;
            theExtent = (this.isSetExtent()?this.getExtent():null);
            strategy.appendField(locator, this, "extent", buffer, theExtent, this.isSetExtent());
        }
        {
            List<SVCoupledResourcePropertyType> theCoupledResource;
            theCoupledResource = (this.isSetCoupledResource()?this.getCoupledResource():null);
            strategy.appendField(locator, this, "coupledResource", buffer, theCoupledResource, this.isSetCoupledResource());
        }
        {
            SVCouplingTypePropertyType theCouplingType;
            theCouplingType = this.getCouplingType();
            strategy.appendField(locator, this, "couplingType", buffer, theCouplingType, this.isSetCouplingType());
        }
        {
            List<SVOperationMetadataPropertyType> theContainsOperations;
            theContainsOperations = (this.isSetContainsOperations()?this.getContainsOperations():null);
            strategy.appendField(locator, this, "containsOperations", buffer, theContainsOperations, this.isSetContainsOperations());
        }
        {
            List<MDDataIdentificationPropertyType> theOperatesOn;
            theOperatesOn = (this.isSetOperatesOn()?this.getOperatesOn():null);
            strategy.appendField(locator, this, "operatesOn", buffer, theOperatesOn, this.isSetOperatesOn());
        }
        return buffer;
    }

    public void setServiceTypeVersion(List<CharacterStringPropertyType> value) {
        this.serviceTypeVersion = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getServiceTypeVersion();
            draftl.addAll(value);
        }
    }

    public void setKeywords(List<MDKeywordsPropertyType> value) {
        this.keywords = null;
        if (value!= null) {
            List<MDKeywordsPropertyType> draftl = this.getKeywords();
            draftl.addAll(value);
        }
    }

    public void setExtent(List<EXExtentPropertyType> value) {
        this.extent = null;
        if (value!= null) {
            List<EXExtentPropertyType> draftl = this.getExtent();
            draftl.addAll(value);
        }
    }

    public void setCoupledResource(List<SVCoupledResourcePropertyType> value) {
        this.coupledResource = null;
        if (value!= null) {
            List<SVCoupledResourcePropertyType> draftl = this.getCoupledResource();
            draftl.addAll(value);
        }
    }

    public void setContainsOperations(List<SVOperationMetadataPropertyType> value) {
        this.containsOperations = null;
        if (value!= null) {
            List<SVOperationMetadataPropertyType> draftl = this.getContainsOperations();
            draftl.addAll(value);
        }
    }

    public void setOperatesOn(List<MDDataIdentificationPropertyType> value) {
        this.operatesOn = null;
        if (value!= null) {
            List<MDDataIdentificationPropertyType> draftl = this.getOperatesOn();
            draftl.addAll(value);
        }
    }

}
