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

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CodeListValueType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.iso19139.v20060504.src package. 
 * <p>An ObjectFactory allows you to programmatically 
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

    private static final QName _SVServiceSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ServiceSpecification");
    private static final QName _SVPlatformNeutralServiceSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_PlatformNeutralServiceSpecification");
    private static final QName _SVPlatformSpecificServiceSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_PlatformSpecificServiceSpecification");
    private static final QName _SVServiceType_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ServiceType");
    private static final QName _SVPort_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Port");
    private static final QName _SVService_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Service");
    private static final QName _SVInterface_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Interface");
    private static final QName _SVPortSpecification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_PortSpecification");
    private static final QName _SVOperation_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Operation");
    private static final QName _SVOperationModel_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationModel");
    private static final QName _SVParameter_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_Parameter");
    private static final QName _SVOperationMetadata_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationMetadata");
    private static final QName _SVServiceIdentification_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ServiceIdentification");
    private static final QName _SVOperationChain_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationChain");
    private static final QName _SVOperationChainMetadata_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_OperationChainMetadata");
    private static final QName _SVCoupledResource_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_CoupledResource");
    private static final QName _SVParameterDirection_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_ParameterDirection");
    private static final QName _DCPList_QNAME = new QName("http://www.isotc211.org/2005/srv", "DCPList");
    private static final QName _SVCouplingType_QNAME = new QName("http://www.isotc211.org/2005/srv", "SV_CouplingType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.iso19139.v20060504.src
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SVServiceSpecificationType }
     * 
     * @return
     *     the new instance of {@link SVServiceSpecificationType }
     */
    public SVServiceSpecificationType createSVServiceSpecificationType() {
        return new SVServiceSpecificationType();
    }

    /**
     * Create an instance of {@link SVPlatformNeutralServiceSpecificationType }
     * 
     * @return
     *     the new instance of {@link SVPlatformNeutralServiceSpecificationType }
     */
    public SVPlatformNeutralServiceSpecificationType createSVPlatformNeutralServiceSpecificationType() {
        return new SVPlatformNeutralServiceSpecificationType();
    }

    /**
     * Create an instance of {@link SVPlatformSpecificServiceSpecificationType }
     * 
     * @return
     *     the new instance of {@link SVPlatformSpecificServiceSpecificationType }
     */
    public SVPlatformSpecificServiceSpecificationType createSVPlatformSpecificServiceSpecificationType() {
        return new SVPlatformSpecificServiceSpecificationType();
    }

    /**
     * Create an instance of {@link SVServiceTypeType }
     * 
     * @return
     *     the new instance of {@link SVServiceTypeType }
     */
    public SVServiceTypeType createSVServiceTypeType() {
        return new SVServiceTypeType();
    }

    /**
     * Create an instance of {@link SVPortType }
     * 
     * @return
     *     the new instance of {@link SVPortType }
     */
    public SVPortType createSVPortType() {
        return new SVPortType();
    }

    /**
     * Create an instance of {@link SVServiceType }
     * 
     * @return
     *     the new instance of {@link SVServiceType }
     */
    public SVServiceType createSVServiceType() {
        return new SVServiceType();
    }

    /**
     * Create an instance of {@link SVInterfaceType }
     * 
     * @return
     *     the new instance of {@link SVInterfaceType }
     */
    public SVInterfaceType createSVInterfaceType() {
        return new SVInterfaceType();
    }

    /**
     * Create an instance of {@link SVPortSpecificationType }
     * 
     * @return
     *     the new instance of {@link SVPortSpecificationType }
     */
    public SVPortSpecificationType createSVPortSpecificationType() {
        return new SVPortSpecificationType();
    }

    /**
     * Create an instance of {@link SVOperationType }
     * 
     * @return
     *     the new instance of {@link SVOperationType }
     */
    public SVOperationType createSVOperationType() {
        return new SVOperationType();
    }

    /**
     * Create an instance of {@link SVParameterType }
     * 
     * @return
     *     the new instance of {@link SVParameterType }
     */
    public SVParameterType createSVParameterType() {
        return new SVParameterType();
    }

    /**
     * Create an instance of {@link SVOperationMetadataType }
     * 
     * @return
     *     the new instance of {@link SVOperationMetadataType }
     */
    public SVOperationMetadataType createSVOperationMetadataType() {
        return new SVOperationMetadataType();
    }

    /**
     * Create an instance of {@link SVServiceIdentificationType }
     * 
     * @return
     *     the new instance of {@link SVServiceIdentificationType }
     */
    public SVServiceIdentificationType createSVServiceIdentificationType() {
        return new SVServiceIdentificationType();
    }

    /**
     * Create an instance of {@link SVOperationChainType }
     * 
     * @return
     *     the new instance of {@link SVOperationChainType }
     */
    public SVOperationChainType createSVOperationChainType() {
        return new SVOperationChainType();
    }

    /**
     * Create an instance of {@link SVOperationChainMetadataType }
     * 
     * @return
     *     the new instance of {@link SVOperationChainMetadataType }
     */
    public SVOperationChainMetadataType createSVOperationChainMetadataType() {
        return new SVOperationChainMetadataType();
    }

    /**
     * Create an instance of {@link SVCoupledResourceType }
     * 
     * @return
     *     the new instance of {@link SVCoupledResourceType }
     */
    public SVCoupledResourceType createSVCoupledResourceType() {
        return new SVCoupledResourceType();
    }

    /**
     * Create an instance of {@link SVServiceSpecificationPropertyType }
     * 
     * @return
     *     the new instance of {@link SVServiceSpecificationPropertyType }
     */
    public SVServiceSpecificationPropertyType createSVServiceSpecificationPropertyType() {
        return new SVServiceSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVPlatformNeutralServiceSpecificationPropertyType }
     * 
     * @return
     *     the new instance of {@link SVPlatformNeutralServiceSpecificationPropertyType }
     */
    public SVPlatformNeutralServiceSpecificationPropertyType createSVPlatformNeutralServiceSpecificationPropertyType() {
        return new SVPlatformNeutralServiceSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVPlatformSpecificServiceSpecificationPropertyType }
     * 
     * @return
     *     the new instance of {@link SVPlatformSpecificServiceSpecificationPropertyType }
     */
    public SVPlatformSpecificServiceSpecificationPropertyType createSVPlatformSpecificServiceSpecificationPropertyType() {
        return new SVPlatformSpecificServiceSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVServiceTypePropertyType }
     * 
     * @return
     *     the new instance of {@link SVServiceTypePropertyType }
     */
    public SVServiceTypePropertyType createSVServiceTypePropertyType() {
        return new SVServiceTypePropertyType();
    }

    /**
     * Create an instance of {@link SVPortPropertyType }
     * 
     * @return
     *     the new instance of {@link SVPortPropertyType }
     */
    public SVPortPropertyType createSVPortPropertyType() {
        return new SVPortPropertyType();
    }

    /**
     * Create an instance of {@link SVServicePropertyType }
     * 
     * @return
     *     the new instance of {@link SVServicePropertyType }
     */
    public SVServicePropertyType createSVServicePropertyType() {
        return new SVServicePropertyType();
    }

    /**
     * Create an instance of {@link SVInterfacePropertyType }
     * 
     * @return
     *     the new instance of {@link SVInterfacePropertyType }
     */
    public SVInterfacePropertyType createSVInterfacePropertyType() {
        return new SVInterfacePropertyType();
    }

    /**
     * Create an instance of {@link SVPortSpecificationPropertyType }
     * 
     * @return
     *     the new instance of {@link SVPortSpecificationPropertyType }
     */
    public SVPortSpecificationPropertyType createSVPortSpecificationPropertyType() {
        return new SVPortSpecificationPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationPropertyType }
     * 
     * @return
     *     the new instance of {@link SVOperationPropertyType }
     */
    public SVOperationPropertyType createSVOperationPropertyType() {
        return new SVOperationPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationModelPropertyType }
     * 
     * @return
     *     the new instance of {@link SVOperationModelPropertyType }
     */
    public SVOperationModelPropertyType createSVOperationModelPropertyType() {
        return new SVOperationModelPropertyType();
    }

    /**
     * Create an instance of {@link SVParameterPropertyType }
     * 
     * @return
     *     the new instance of {@link SVParameterPropertyType }
     */
    public SVParameterPropertyType createSVParameterPropertyType() {
        return new SVParameterPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationMetadataPropertyType }
     * 
     * @return
     *     the new instance of {@link SVOperationMetadataPropertyType }
     */
    public SVOperationMetadataPropertyType createSVOperationMetadataPropertyType() {
        return new SVOperationMetadataPropertyType();
    }

    /**
     * Create an instance of {@link SVServiceIdentificationPropertyType }
     * 
     * @return
     *     the new instance of {@link SVServiceIdentificationPropertyType }
     */
    public SVServiceIdentificationPropertyType createSVServiceIdentificationPropertyType() {
        return new SVServiceIdentificationPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationChainPropertyType }
     * 
     * @return
     *     the new instance of {@link SVOperationChainPropertyType }
     */
    public SVOperationChainPropertyType createSVOperationChainPropertyType() {
        return new SVOperationChainPropertyType();
    }

    /**
     * Create an instance of {@link SVOperationChainMetadataPropertyType }
     * 
     * @return
     *     the new instance of {@link SVOperationChainMetadataPropertyType }
     */
    public SVOperationChainMetadataPropertyType createSVOperationChainMetadataPropertyType() {
        return new SVOperationChainMetadataPropertyType();
    }

    /**
     * Create an instance of {@link SVCoupledResourcePropertyType }
     * 
     * @return
     *     the new instance of {@link SVCoupledResourcePropertyType }
     */
    public SVCoupledResourcePropertyType createSVCoupledResourcePropertyType() {
        return new SVCoupledResourcePropertyType();
    }

    /**
     * Create an instance of {@link SVParameterDirectionPropertyType }
     * 
     * @return
     *     the new instance of {@link SVParameterDirectionPropertyType }
     */
    public SVParameterDirectionPropertyType createSVParameterDirectionPropertyType() {
        return new SVParameterDirectionPropertyType();
    }

    /**
     * Create an instance of {@link DCPListPropertyType }
     * 
     * @return
     *     the new instance of {@link DCPListPropertyType }
     */
    public DCPListPropertyType createDCPListPropertyType() {
        return new DCPListPropertyType();
    }

    /**
     * Create an instance of {@link SVCouplingTypePropertyType }
     * 
     * @return
     *     the new instance of {@link SVCouplingTypePropertyType }
     */
    public SVCouplingTypePropertyType createSVCouplingTypePropertyType() {
        return new SVCouplingTypePropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceSpecificationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVServiceSpecificationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ServiceSpecification")
    public JAXBElement<SVServiceSpecificationType> createSVServiceSpecification(SVServiceSpecificationType value) {
        return new JAXBElement<>(_SVServiceSpecification_QNAME, SVServiceSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPlatformNeutralServiceSpecificationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVPlatformNeutralServiceSpecificationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_PlatformNeutralServiceSpecification", substitutionHeadNamespace = "http://www.isotc211.org/2005/srv", substitutionHeadName = "SV_ServiceSpecification")
    public JAXBElement<SVPlatformNeutralServiceSpecificationType> createSVPlatformNeutralServiceSpecification(SVPlatformNeutralServiceSpecificationType value) {
        return new JAXBElement<>(_SVPlatformNeutralServiceSpecification_QNAME, SVPlatformNeutralServiceSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPlatformSpecificServiceSpecificationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVPlatformSpecificServiceSpecificationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_PlatformSpecificServiceSpecification", substitutionHeadNamespace = "http://www.isotc211.org/2005/srv", substitutionHeadName = "SV_PlatformNeutralServiceSpecification")
    public JAXBElement<SVPlatformSpecificServiceSpecificationType> createSVPlatformSpecificServiceSpecification(SVPlatformSpecificServiceSpecificationType value) {
        return new JAXBElement<>(_SVPlatformSpecificServiceSpecification_QNAME, SVPlatformSpecificServiceSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceTypeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVServiceTypeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ServiceType")
    public JAXBElement<SVServiceTypeType> createSVServiceType(SVServiceTypeType value) {
        return new JAXBElement<>(_SVServiceType_QNAME, SVServiceTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPortType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVPortType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Port")
    public JAXBElement<SVPortType> createSVPort(SVPortType value) {
        return new JAXBElement<>(_SVPort_QNAME, SVPortType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVServiceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Service")
    public JAXBElement<SVServiceType> createSVService(SVServiceType value) {
        return new JAXBElement<>(_SVService_QNAME, SVServiceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVInterfaceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVInterfaceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Interface")
    public JAXBElement<SVInterfaceType> createSVInterface(SVInterfaceType value) {
        return new JAXBElement<>(_SVInterface_QNAME, SVInterfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVPortSpecificationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVPortSpecificationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_PortSpecification")
    public JAXBElement<SVPortSpecificationType> createSVPortSpecification(SVPortSpecificationType value) {
        return new JAXBElement<>(_SVPortSpecification_QNAME, SVPortSpecificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVOperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Operation")
    public JAXBElement<SVOperationType> createSVOperation(SVOperationType value) {
        return new JAXBElement<>(_SVOperation_QNAME, SVOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationModelType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVOperationModelType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationModel", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<SVOperationModelType> createSVOperationModel(SVOperationModelType value) {
        return new JAXBElement<>(_SVOperationModel_QNAME, SVOperationModelType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVParameterType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVParameterType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_Parameter")
    public JAXBElement<SVParameterType> createSVParameter(SVParameterType value) {
        return new JAXBElement<>(_SVParameter_QNAME, SVParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationMetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVOperationMetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationMetadata")
    public JAXBElement<SVOperationMetadataType> createSVOperationMetadata(SVOperationMetadataType value) {
        return new JAXBElement<>(_SVOperationMetadata_QNAME, SVOperationMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVServiceIdentificationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVServiceIdentificationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ServiceIdentification", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmd", substitutionHeadName = "AbstractMD_Identification")
    public JAXBElement<SVServiceIdentificationType> createSVServiceIdentification(SVServiceIdentificationType value) {
        return new JAXBElement<>(_SVServiceIdentification_QNAME, SVServiceIdentificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationChainType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVOperationChainType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationChain")
    public JAXBElement<SVOperationChainType> createSVOperationChain(SVOperationChainType value) {
        return new JAXBElement<>(_SVOperationChain_QNAME, SVOperationChainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVOperationChainMetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVOperationChainMetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_OperationChainMetadata")
    public JAXBElement<SVOperationChainMetadataType> createSVOperationChainMetadata(SVOperationChainMetadataType value) {
        return new JAXBElement<>(_SVOperationChainMetadata_QNAME, SVOperationChainMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVCoupledResourceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVCoupledResourceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_CoupledResource")
    public JAXBElement<SVCoupledResourceType> createSVCoupledResource(SVCoupledResourceType value) {
        return new JAXBElement<>(_SVCoupledResource_QNAME, SVCoupledResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SVParameterDirectionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SVParameterDirectionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_ParameterDirection", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<SVParameterDirectionType> createSVParameterDirection(SVParameterDirectionType value) {
        return new JAXBElement<>(_SVParameterDirection_QNAME, SVParameterDirectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "DCPList", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<CodeListValueType> createDCPList(CodeListValueType value) {
        return new JAXBElement<>(_DCPList_QNAME, CodeListValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/srv", name = "SV_CouplingType", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<CodeListValueType> createSVCouplingType(CodeListValueType value) {
        return new JAXBElement<>(_SVCouplingType_QNAME, CodeListValueType.class, null, value);
    }

}
