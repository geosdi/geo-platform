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
// Generated on: 2015.06.17 at 04:41:23 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20060504.src;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CodeListValueType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.iso19139.v20060504.src package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SVCouplingType_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_CouplingType");
    private final static QName _SVParameter_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Parameter");
    private final static QName _SVPort_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Port");
    private final static QName _SVServiceIdentification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ServiceIdentification");
    private final static QName _SVServiceSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ServiceSpecification");
    private final static QName _SVParameterDirection_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ParameterDirection");
    private final static QName _SVOperationChainMetadata_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationChainMetadata");
    private final static QName _SVServiceType_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ServiceType");
    private final static QName _SVService_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Service");
    private final static QName _SVOperationModel_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationModel");
    private final static QName _SVPortSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_PortSpecification");
    private final static QName _SVOperation_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Operation");
    private final static QName _SVOperationChain_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationChain");
    private final static QName _SVPlatformSpecificServiceSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_PlatformSpecificServiceSpecification");
    private final static QName _DCPList_QNAME = new QName("http://www.isotc211.org/2005/srv", "DCPList");
    private final static QName _SVOperationMetadata_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationMetadata");
    private final static QName _SVPlatformNeutralServiceSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_PlatformNeutralServiceSpecification");
    private final static QName _SVCoupledResource_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_CoupledResource");
    private final static QName _SVInterface_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Interface");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.iso19139.v20060504.src
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SVParameterType }
     * 
     */
    public SVParameterType createSVParameterType() {
        return new SVParameterType();
    }

    /**
     * Create an instance of {@link SVPlatformSpecificServiceSpecificationType }
     * 
     */
    public SVPlatformSpecificServiceSpecificationType createSVPlatformSpecificServiceSpecificationType() {
        return new SVPlatformSpecificServiceSpecificationType();
    }

    /**
     * Create an instance of {@link SVPlatformNeutralServiceSpecificationType }
     * 
     */
    public SVPlatformNeutralServiceSpecificationType createSVPlatformNeutralServiceSpecificationType() {
        return new SVPlatformNeutralServiceSpecificationType();
    }

    /**
     * Create an instance of {@link SVServiceSpecificationType }
     * 
     */
    public SVServiceSpecificationType createSVServiceSpecificationType() {
        return new SVServiceSpecificationType();
    }

    /**
     * Create an instance of {@link SVPortType }
     * 
     */
    public SVPortType createSVPortType() {
        return new SVPortType();
    }

    /**
     * Create an instance of {@link SVInterfaceType }
     * 
     */
    public SVInterfaceType createSVInterfaceType() {
        return new SVInterfaceType();
    }

    /**
     * Create an instance of {@link SVServiceIdentificationType }
     * 
     */
    public SVServiceIdentificationType createSVServiceIdentificationType() {
        return new SVServiceIdentificationType();
    }

    /**
     * Create an instance of {@link SVServiceType }
     * 
     */
    public SVServiceType createSVServiceType() {
        return new SVServiceType();
    }

    /**
     * Create an instance of {@link SVOperationChainMetadataType }
     * 
     */
    public SVOperationChainMetadataType createSVOperationChainMetadataType() {
        return new SVOperationChainMetadataType();
    }

    /**
     * Create an instance of {@link SVOperationChainType }
     * 
     */
    public SVOperationChainType createSVOperationChainType() {
        return new SVOperationChainType();
    }

    /**
     * Create an instance of {@link SVPortSpecificationType }
     * 
     */
    public SVPortSpecificationType createSVPortSpecificationType() {
        return new SVPortSpecificationType();
    }

    /**
     * Create an instance of {@link SVCoupledResourceType }
     * 
     */
    public SVCoupledResourceType createSVCoupledResourceType() {
        return new SVCoupledResourceType();
    }

    /**
     * Create an instance of {@link SVOperationType }
     * 
     */
    public SVOperationType createSVOperationType() {
        return new SVOperationType();
    }

    /**
     * Create an instance of {@link SVOperationMetadataType }
     * 
     */
    public SVOperationMetadataType createSVOperationMetadataType() {
        return new SVOperationMetadataType();
    }

    /**
     * Create an instance of {@link SVServiceTypeType }
     * 
     */
    public SVServiceTypeType createSVServiceTypeType() {
        return new SVServiceTypeType();
    }

    /**
     * Create an instance of {@link SVServicePropertyType }
     * 
     */
    public SVServicePropertyType createSVServicePropertyType() {
        return new SVServicePropertyType();
    }

    /**
     * Create an instance of {@link SVPlatformNeutralServiceSpecificationPropertyType }
     * 
     */
    public SVPlatformNeutralServiceSpecificationPropertyType createSVPlatformNeutralServiceSpecificationPropertyType() {
        return new SVPlatformNeutralServiceSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVInterfacePropertyType }
     * 
     */
    public SVInterfacePropertyType createSVInterfacePropertyType() {
        return new SVInterfacePropertyType();
    }

    /**
     * Create an instance of {@link SVPlatformSpecificServiceSpecificationPropertyType }
     * 
     */
    public SVPlatformSpecificServiceSpecificationPropertyType createSVPlatformSpecificServiceSpecificationPropertyType() {
        return new SVPlatformSpecificServiceSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVCouplingTypePropertyType }
     * 
     */
    public SVCouplingTypePropertyType createSVCouplingTypePropertyType() {
        return new SVCouplingTypePropertyType();
    }

    /**
     * Create an instance of {@link SVOperationChainPropertyType }
     * 
     */
    public SVOperationChainPropertyType createSVOperationChainPropertyType() {
        return new SVOperationChainPropertyType();
    }

    /**
     * Create an instance of {@link SVServiceTypePropertyType }
     * 
     */
    public SVServiceTypePropertyType createSVServiceTypePropertyType() {
        return new SVServiceTypePropertyType();
    }

    /**
     * Create an instance of {@link SVOperationMetadataPropertyType }
     * 
     */
    public SVOperationMetadataPropertyType createSVOperationMetadataPropertyType() {
        return new SVOperationMetadataPropertyType();
    }

    /**
     * Create an instance of {@link SVParameterPropertyType }
     * 
     */
    public SVParameterPropertyType createSVParameterPropertyType() {
        return new SVParameterPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationPropertyType }
     * 
     */
    public SVOperationPropertyType createSVOperationPropertyType() {
        return new SVOperationPropertyType();
    }

    /**
     * Create an instance of {@link SVParameterDirectionPropertyType }
     * 
     */
    public SVParameterDirectionPropertyType createSVParameterDirectionPropertyType() {
        return new SVParameterDirectionPropertyType();
    }

    /**
     * Create an instance of {@link SVPortPropertyType }
     * 
     */
    public SVPortPropertyType createSVPortPropertyType() {
        return new SVPortPropertyType();
    }

    /**
     * Create an instance of {@link SVPortSpecificationPropertyType }
     * 
     */
    public SVPortSpecificationPropertyType createSVPortSpecificationPropertyType() {
        return new SVPortSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVServiceIdentificationPropertyType }
     * 
     */
    public SVServiceIdentificationPropertyType createSVServiceIdentificationPropertyType() {
        return new SVServiceIdentificationPropertyType();
    }

    /**
     * Create an instance of {@link DCPListPropertyType }
     * 
     */
    public DCPListPropertyType createDCPListPropertyType() {
        return new DCPListPropertyType();
    }

    /**
     * Create an instance of {@link SVServiceSpecificationPropertyType }
     * 
     */
    public SVServiceSpecificationPropertyType createSVServiceSpecificationPropertyType() {
        return new SVServiceSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVCoupledResourcePropertyType }
     * 
     */
    public SVCoupledResourcePropertyType createSVCoupledResourcePropertyType() {
        return new SVCoupledResourcePropertyType();
    }

    /**
     * Create an instance of {@link SVOperationChainMetadataPropertyType }
     * 
     */
    public SVOperationChainMetadataPropertyType createSVOperationChainMetadataPropertyType() {
        return new SVOperationChainMetadataPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationModelPropertyType }
     * 
     */
    public SVOperationModelPropertyType createSVOperationModelPropertyType() {
        return new SVOperationModelPropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_CouplingType", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<CodeListValueType> createSVCouplingType(CodeListValueType value) {
        return new JAXBElement<CodeListValueType>(_SVCouplingType_QNAME, CodeListValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVParameterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Parameter")
    public JAXBElement<SVParameterType> createSVParameter(SVParameterType value) {
        return new JAXBElement<SVParameterType>(_SVParameter_QNAME, SVParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPortType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Port")
    public JAXBElement<SVPortType> createSVPort(SVPortType value) {
        return new JAXBElement<SVPortType>(_SVPort_QNAME, SVPortType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceIdentificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ServiceIdentification", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmd", substitutionHeadName = "AbstractMD_Identification")
    public JAXBElement<SVServiceIdentificationType> createSVServiceIdentification(SVServiceIdentificationType value) {
        return new JAXBElement<SVServiceIdentificationType>(_SVServiceIdentification_QNAME, SVServiceIdentificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceSpecificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ServiceSpecification")
    public JAXBElement<SVServiceSpecificationType> createSVServiceSpecification(SVServiceSpecificationType value) {
        return new JAXBElement<SVServiceSpecificationType>(_SVServiceSpecification_QNAME, SVServiceSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVParameterDirectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ParameterDirection", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<SVParameterDirectionType> createSVParameterDirection(SVParameterDirectionType value) {
        return new JAXBElement<SVParameterDirectionType>(_SVParameterDirection_QNAME, SVParameterDirectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationChainMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationChainMetadata")
    public JAXBElement<SVOperationChainMetadataType> createSVOperationChainMetadata(SVOperationChainMetadataType value) {
        return new JAXBElement<SVOperationChainMetadataType>(_SVOperationChainMetadata_QNAME, SVOperationChainMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ServiceType")
    public JAXBElement<SVServiceTypeType> createSVServiceType(SVServiceTypeType value) {
        return new JAXBElement<SVServiceTypeType>(_SVServiceType_QNAME, SVServiceTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Service")
    public JAXBElement<SVServiceType> createSVService(SVServiceType value) {
        return new JAXBElement<SVServiceType>(_SVService_QNAME, SVServiceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationModelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationModel", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<SVOperationModelType> createSVOperationModel(SVOperationModelType value) {
        return new JAXBElement<SVOperationModelType>(_SVOperationModel_QNAME, SVOperationModelType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPortSpecificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_PortSpecification")
    public JAXBElement<SVPortSpecificationType> createSVPortSpecification(SVPortSpecificationType value) {
        return new JAXBElement<SVPortSpecificationType>(_SVPortSpecification_QNAME, SVPortSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Operation")
    public JAXBElement<SVOperationType> createSVOperation(SVOperationType value) {
        return new JAXBElement<SVOperationType>(_SVOperation_QNAME, SVOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationChainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationChain")
    public JAXBElement<SVOperationChainType> createSVOperationChain(SVOperationChainType value) {
        return new JAXBElement<SVOperationChainType>(_SVOperationChain_QNAME, SVOperationChainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPlatformSpecificServiceSpecificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_PlatformSpecificServiceSpecification", substitutionHeadNamespace = "http://www.isotc211.org/2005/srv", substitutionHeadName = "SV_PlatformNeutralServiceSpecification")
    public JAXBElement<SVPlatformSpecificServiceSpecificationType> createSVPlatformSpecificServiceSpecification(SVPlatformSpecificServiceSpecificationType value) {
        return new JAXBElement<SVPlatformSpecificServiceSpecificationType>(_SVPlatformSpecificServiceSpecification_QNAME, SVPlatformSpecificServiceSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "DCPList", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<CodeListValueType> createDCPList(CodeListValueType value) {
        return new JAXBElement<CodeListValueType>(_DCPList_QNAME, CodeListValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationMetadata")
    public JAXBElement<SVOperationMetadataType> createSVOperationMetadata(SVOperationMetadataType value) {
        return new JAXBElement<SVOperationMetadataType>(_SVOperationMetadata_QNAME, SVOperationMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPlatformNeutralServiceSpecificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_PlatformNeutralServiceSpecification", substitutionHeadNamespace = "http://www.isotc211.org/2005/srv", substitutionHeadName = "SV_ServiceSpecification")
    public JAXBElement<SVPlatformNeutralServiceSpecificationType> createSVPlatformNeutralServiceSpecification(SVPlatformNeutralServiceSpecificationType value) {
        return new JAXBElement<SVPlatformNeutralServiceSpecificationType>(_SVPlatformNeutralServiceSpecification_QNAME, SVPlatformNeutralServiceSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVCoupledResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_CoupledResource")
    public JAXBElement<SVCoupledResourceType> createSVCoupledResource(SVCoupledResourceType value) {
        return new JAXBElement<SVCoupledResourceType>(_SVCoupledResource_QNAME, SVCoupledResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVInterfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Interface")
    public JAXBElement<SVInterfaceType> createSVInterface(SVInterfaceType value) {
        return new JAXBElement<SVInterfaceType>(_SVInterface_QNAME, SVInterfaceType.class, null, value);
    }

}
