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
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.ows.v110;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.ows.v110 package. 
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

    private static final QName _Metadata_QNAME = new QName("http://www.opengis.net/ows/1.1", "Metadata");
    private static final QName _AbstractMetaData_QNAME = new QName("http://www.opengis.net/ows/1.1", "AbstractMetaData");
    private static final QName _BoundingBox_QNAME = new QName("http://www.opengis.net/ows/1.1", "BoundingBox");
    private static final QName _WGS84BoundingBox_QNAME = new QName("http://www.opengis.net/ows/1.1", "WGS84BoundingBox");
    private static final QName _Title_QNAME = new QName("http://www.opengis.net/ows/1.1", "Title");
    private static final QName _Abstract_QNAME = new QName("http://www.opengis.net/ows/1.1", "Abstract");
    private static final QName _Keywords_QNAME = new QName("http://www.opengis.net/ows/1.1", "Keywords");
    private static final QName _PointOfContact_QNAME = new QName("http://www.opengis.net/ows/1.1", "PointOfContact");
    private static final QName _IndividualName_QNAME = new QName("http://www.opengis.net/ows/1.1", "IndividualName");
    private static final QName _OrganisationName_QNAME = new QName("http://www.opengis.net/ows/1.1", "OrganisationName");
    private static final QName _PositionName_QNAME = new QName("http://www.opengis.net/ows/1.1", "PositionName");
    private static final QName _Role_QNAME = new QName("http://www.opengis.net/ows/1.1", "Role");
    private static final QName _ContactInfo_QNAME = new QName("http://www.opengis.net/ows/1.1", "ContactInfo");
    private static final QName _Identifier_QNAME = new QName("http://www.opengis.net/ows/1.1", "Identifier");
    private static final QName _OutputFormat_QNAME = new QName("http://www.opengis.net/ows/1.1", "OutputFormat");
    private static final QName _AvailableCRS_QNAME = new QName("http://www.opengis.net/ows/1.1", "AvailableCRS");
    private static final QName _SupportedCRS_QNAME = new QName("http://www.opengis.net/ows/1.1", "SupportedCRS");
    private static final QName _AccessConstraints_QNAME = new QName("http://www.opengis.net/ows/1.1", "AccessConstraints");
    private static final QName _Fees_QNAME = new QName("http://www.opengis.net/ows/1.1", "Fees");
    private static final QName _Language_QNAME = new QName("http://www.opengis.net/ows/1.1", "Language");
    private static final QName _Value_QNAME = new QName("http://www.opengis.net/ows/1.1", "Value");
    private static final QName _Range_QNAME = new QName("http://www.opengis.net/ows/1.1", "Range");
    private static final QName _DefaultValue_QNAME = new QName("http://www.opengis.net/ows/1.1", "DefaultValue");
    private static final QName _MinimumValue_QNAME = new QName("http://www.opengis.net/ows/1.1", "MinimumValue");
    private static final QName _MaximumValue_QNAME = new QName("http://www.opengis.net/ows/1.1", "MaximumValue");
    private static final QName _Spacing_QNAME = new QName("http://www.opengis.net/ows/1.1", "Spacing");
    private static final QName _Meaning_QNAME = new QName("http://www.opengis.net/ows/1.1", "Meaning");
    private static final QName _DataType_QNAME = new QName("http://www.opengis.net/ows/1.1", "DataType");
    private static final QName _ReferenceSystem_QNAME = new QName("http://www.opengis.net/ows/1.1", "ReferenceSystem");
    private static final QName _UOM_QNAME = new QName("http://www.opengis.net/ows/1.1", "UOM");
    private static final QName _ExtendedCapabilities_QNAME = new QName("http://www.opengis.net/ows/1.1", "ExtendedCapabilities");
    private static final QName _GetCapabilities_QNAME = new QName("http://www.opengis.net/ows/1.1", "GetCapabilities");
    private static final QName _Resource_QNAME = new QName("http://www.opengis.net/ows/1.1", "Resource");
    private static final QName _GetResourceByID_QNAME = new QName("http://www.opengis.net/ows/1.1", "GetResourceByID");
    private static final QName _Exception_QNAME = new QName("http://www.opengis.net/ows/1.1", "Exception");
    private static final QName _OtherSource_QNAME = new QName("http://www.opengis.net/ows/1.1", "OtherSource");
    private static final QName _DatasetDescriptionSummary_QNAME = new QName("http://www.opengis.net/ows/1.1", "DatasetDescriptionSummary");
    private static final QName _DatasetDescriptionSummaryExtension_QNAME = new QName("http://www.opengis.net/ows/1.1", "DatasetDescriptionSummaryExtension");
    private static final QName _OtherSourceExtension_QNAME = new QName("http://www.opengis.net/ows/1.1", "OtherSourceExtension");
    private static final QName _AbstractReferenceBase_QNAME = new QName("http://www.opengis.net/ows/1.1", "AbstractReferenceBase");
    private static final QName _Reference_QNAME = new QName("http://www.opengis.net/ows/1.1", "Reference");
    private static final QName _ReferenceGroup_QNAME = new QName("http://www.opengis.net/ows/1.1", "ReferenceGroup");
    private static final QName _Manifest_QNAME = new QName("http://www.opengis.net/ows/1.1", "Manifest");
    private static final QName _OperationResponse_QNAME = new QName("http://www.opengis.net/ows/1.1", "OperationResponse");
    private static final QName _InputData_QNAME = new QName("http://www.opengis.net/ows/1.1", "InputData");
    private static final QName _ServiceReference_QNAME = new QName("http://www.opengis.net/ows/1.1", "ServiceReference");
    private static final QName _HTTPGet_QNAME = new QName("http://www.opengis.net/ows/1.1", "Get");
    private static final QName _HTTPPost_QNAME = new QName("http://www.opengis.net/ows/1.1", "Post");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.ows.v110
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MetadataType }
     * 
     * @return
     *     the new instance of {@link MetadataType }
     */
    public MetadataType createMetadataType() {
        return new MetadataType();
    }

    /**
     * Create an instance of {@link BoundingBoxType }
     * 
     * @return
     *     the new instance of {@link BoundingBoxType }
     */
    public BoundingBoxType createBoundingBoxType() {
        return new BoundingBoxType();
    }

    /**
     * Create an instance of {@link WGS84BoundingBoxType }
     * 
     * @return
     *     the new instance of {@link WGS84BoundingBoxType }
     */
    public WGS84BoundingBoxType createWGS84BoundingBoxType() {
        return new WGS84BoundingBoxType();
    }

    /**
     * Create an instance of {@link LanguageStringType }
     * 
     * @return
     *     the new instance of {@link LanguageStringType }
     */
    public LanguageStringType createLanguageStringType() {
        return new LanguageStringType();
    }

    /**
     * Create an instance of {@link KeywordsType }
     * 
     * @return
     *     the new instance of {@link KeywordsType }
     */
    public KeywordsType createKeywordsType() {
        return new KeywordsType();
    }

    /**
     * Create an instance of {@link ResponsiblePartyType }
     * 
     * @return
     *     the new instance of {@link ResponsiblePartyType }
     */
    public ResponsiblePartyType createResponsiblePartyType() {
        return new ResponsiblePartyType();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     * @return
     *     the new instance of {@link CodeType }
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link ContactType }
     * 
     * @return
     *     the new instance of {@link ContactType }
     */
    public ContactType createContactType() {
        return new ContactType();
    }

    /**
     * Create an instance of {@link ServiceIdentification }
     * 
     * @return
     *     the new instance of {@link ServiceIdentification }
     */
    public ServiceIdentification createServiceIdentification() {
        return new ServiceIdentification();
    }

    /**
     * Create an instance of {@link DescriptionType }
     * 
     * @return
     *     the new instance of {@link DescriptionType }
     */
    public DescriptionType createDescriptionType() {
        return new DescriptionType();
    }

    /**
     * Create an instance of {@link ServiceProvider }
     * 
     * @return
     *     the new instance of {@link ServiceProvider }
     */
    public ServiceProvider createServiceProvider() {
        return new ServiceProvider();
    }

    /**
     * Create an instance of {@link OnlineResourceType }
     * 
     * @return
     *     the new instance of {@link OnlineResourceType }
     */
    public OnlineResourceType createOnlineResourceType() {
        return new OnlineResourceType();
    }

    /**
     * Create an instance of {@link ResponsiblePartySubsetType }
     * 
     * @return
     *     the new instance of {@link ResponsiblePartySubsetType }
     */
    public ResponsiblePartySubsetType createResponsiblePartySubsetType() {
        return new ResponsiblePartySubsetType();
    }

    /**
     * Create an instance of {@link AnyValue }
     * 
     * @return
     *     the new instance of {@link AnyValue }
     */
    public AnyValue createAnyValue() {
        return new AnyValue();
    }

    /**
     * Create an instance of {@link NoValues }
     * 
     * @return
     *     the new instance of {@link NoValues }
     */
    public NoValues createNoValues() {
        return new NoValues();
    }

    /**
     * Create an instance of {@link ValuesReference }
     * 
     * @return
     *     the new instance of {@link ValuesReference }
     */
    public ValuesReference createValuesReference() {
        return new ValuesReference();
    }

    /**
     * Create an instance of {@link AllowedValues }
     * 
     * @return
     *     the new instance of {@link AllowedValues }
     */
    public AllowedValues createAllowedValues() {
        return new AllowedValues();
    }

    /**
     * Create an instance of {@link ValueType }
     * 
     * @return
     *     the new instance of {@link ValueType }
     */
    public ValueType createValueType() {
        return new ValueType();
    }

    /**
     * Create an instance of {@link RangeType }
     * 
     * @return
     *     the new instance of {@link RangeType }
     */
    public RangeType createRangeType() {
        return new RangeType();
    }

    /**
     * Create an instance of {@link DomainMetadataType }
     * 
     * @return
     *     the new instance of {@link DomainMetadataType }
     */
    public DomainMetadataType createDomainMetadataType() {
        return new DomainMetadataType();
    }

    /**
     * Create an instance of {@link OperationsMetadata }
     * 
     * @return
     *     the new instance of {@link OperationsMetadata }
     */
    public OperationsMetadata createOperationsMetadata() {
        return new OperationsMetadata();
    }

    /**
     * Create an instance of {@link Operation }
     * 
     * @return
     *     the new instance of {@link Operation }
     */
    public Operation createOperation() {
        return new Operation();
    }

    /**
     * Create an instance of {@link DCP }
     * 
     * @return
     *     the new instance of {@link DCP }
     */
    public DCP createDCP() {
        return new DCP();
    }

    /**
     * Create an instance of {@link HTTP }
     * 
     * @return
     *     the new instance of {@link HTTP }
     */
    public HTTP createHTTP() {
        return new HTTP();
    }

    /**
     * Create an instance of {@link RequestMethodType }
     * 
     * @return
     *     the new instance of {@link RequestMethodType }
     */
    public RequestMethodType createRequestMethodType() {
        return new RequestMethodType();
    }

    /**
     * Create an instance of {@link DomainType }
     * 
     * @return
     *     the new instance of {@link DomainType }
     */
    public DomainType createDomainType() {
        return new DomainType();
    }

    /**
     * Create an instance of {@link GetCapabilitiesType }
     * 
     * @return
     *     the new instance of {@link GetCapabilitiesType }
     */
    public GetCapabilitiesType createGetCapabilitiesType() {
        return new GetCapabilitiesType();
    }

    /**
     * Create an instance of {@link GetResourceByIdType }
     * 
     * @return
     *     the new instance of {@link GetResourceByIdType }
     */
    public GetResourceByIdType createGetResourceByIdType() {
        return new GetResourceByIdType();
    }

    /**
     * Create an instance of {@link ExceptionReport }
     * 
     * @return
     *     the new instance of {@link ExceptionReport }
     */
    public ExceptionReport createExceptionReport() {
        return new ExceptionReport();
    }

    /**
     * Create an instance of {@link ExceptionType }
     * 
     * @return
     *     the new instance of {@link ExceptionType }
     */
    public ExceptionType createExceptionType() {
        return new ExceptionType();
    }

    /**
     * Create an instance of {@link DatasetDescriptionSummaryBaseType }
     * 
     * @return
     *     the new instance of {@link DatasetDescriptionSummaryBaseType }
     */
    public DatasetDescriptionSummaryBaseType createDatasetDescriptionSummaryBaseType() {
        return new DatasetDescriptionSummaryBaseType();
    }

    /**
     * Create an instance of {@link AbstractReferenceBaseType }
     * 
     * @return
     *     the new instance of {@link AbstractReferenceBaseType }
     */
    public AbstractReferenceBaseType createAbstractReferenceBaseType() {
        return new AbstractReferenceBaseType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     * @return
     *     the new instance of {@link ReferenceType }
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link ReferenceGroupType }
     * 
     * @return
     *     the new instance of {@link ReferenceGroupType }
     */
    public ReferenceGroupType createReferenceGroupType() {
        return new ReferenceGroupType();
    }

    /**
     * Create an instance of {@link ManifestType }
     * 
     * @return
     *     the new instance of {@link ManifestType }
     */
    public ManifestType createManifestType() {
        return new ManifestType();
    }

    /**
     * Create an instance of {@link ServiceReferenceType }
     * 
     * @return
     *     the new instance of {@link ServiceReferenceType }
     */
    public ServiceReferenceType createServiceReferenceType() {
        return new ServiceReferenceType();
    }

    /**
     * Create an instance of {@link TelephoneType }
     * 
     * @return
     *     the new instance of {@link TelephoneType }
     */
    public TelephoneType createTelephoneType() {
        return new TelephoneType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     * @return
     *     the new instance of {@link AddressType }
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link BasicIdentificationType }
     * 
     * @return
     *     the new instance of {@link BasicIdentificationType }
     */
    public BasicIdentificationType createBasicIdentificationType() {
        return new BasicIdentificationType();
    }

    /**
     * Create an instance of {@link IdentificationType }
     * 
     * @return
     *     the new instance of {@link IdentificationType }
     */
    public IdentificationType createIdentificationType() {
        return new IdentificationType();
    }

    /**
     * Create an instance of {@link UnNamedDomainType }
     * 
     * @return
     *     the new instance of {@link UnNamedDomainType }
     */
    public UnNamedDomainType createUnNamedDomainType() {
        return new UnNamedDomainType();
    }

    /**
     * Create an instance of {@link CapabilitiesBaseType }
     * 
     * @return
     *     the new instance of {@link CapabilitiesBaseType }
     */
    public CapabilitiesBaseType createCapabilitiesBaseType() {
        return new CapabilitiesBaseType();
    }

    /**
     * Create an instance of {@link AcceptVersionsType }
     * 
     * @return
     *     the new instance of {@link AcceptVersionsType }
     */
    public AcceptVersionsType createAcceptVersionsType() {
        return new AcceptVersionsType();
    }

    /**
     * Create an instance of {@link SectionsType }
     * 
     * @return
     *     the new instance of {@link SectionsType }
     */
    public SectionsType createSectionsType() {
        return new SectionsType();
    }

    /**
     * Create an instance of {@link AcceptFormatsType }
     * 
     * @return
     *     the new instance of {@link AcceptFormatsType }
     */
    public AcceptFormatsType createAcceptFormatsType() {
        return new AcceptFormatsType();
    }

    /**
     * Create an instance of {@link ContentsBaseType }
     * 
     * @return
     *     the new instance of {@link ContentsBaseType }
     */
    public ContentsBaseType createContentsBaseType() {
        return new ContentsBaseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Metadata")
    public JAXBElement<MetadataType> createMetadata(MetadataType value) {
        return new JAXBElement<>(_Metadata_QNAME, MetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "AbstractMetaData")
    public JAXBElement<Object> createAbstractMetaData(Object value) {
        return new JAXBElement<>(_AbstractMetaData_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoundingBoxType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoundingBoxType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "BoundingBox")
    public JAXBElement<BoundingBoxType> createBoundingBox(BoundingBoxType value) {
        return new JAXBElement<>(_BoundingBox_QNAME, BoundingBoxType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WGS84BoundingBoxType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WGS84BoundingBoxType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "WGS84BoundingBox", substitutionHeadNamespace = "http://www.opengis.net/ows/1.1", substitutionHeadName = "BoundingBox")
    public JAXBElement<WGS84BoundingBoxType> createWGS84BoundingBox(WGS84BoundingBoxType value) {
        return new JAXBElement<>(_WGS84BoundingBox_QNAME, WGS84BoundingBoxType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageStringType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LanguageStringType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Title")
    public JAXBElement<LanguageStringType> createTitle(LanguageStringType value) {
        return new JAXBElement<>(_Title_QNAME, LanguageStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageStringType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LanguageStringType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Abstract")
    public JAXBElement<LanguageStringType> createAbstract(LanguageStringType value) {
        return new JAXBElement<>(_Abstract_QNAME, LanguageStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeywordsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link KeywordsType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Keywords")
    public JAXBElement<KeywordsType> createKeywords(KeywordsType value) {
        return new JAXBElement<>(_Keywords_QNAME, KeywordsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponsiblePartyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponsiblePartyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "PointOfContact")
    public JAXBElement<ResponsiblePartyType> createPointOfContact(ResponsiblePartyType value) {
        return new JAXBElement<>(_PointOfContact_QNAME, ResponsiblePartyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "IndividualName")
    public JAXBElement<String> createIndividualName(String value) {
        return new JAXBElement<>(_IndividualName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "OrganisationName")
    public JAXBElement<String> createOrganisationName(String value) {
        return new JAXBElement<>(_OrganisationName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "PositionName")
    public JAXBElement<String> createPositionName(String value) {
        return new JAXBElement<>(_PositionName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Role")
    public JAXBElement<CodeType> createRole(CodeType value) {
        return new JAXBElement<>(_Role_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ContactType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "ContactInfo")
    public JAXBElement<ContactType> createContactInfo(ContactType value) {
        return new JAXBElement<>(_ContactInfo_QNAME, ContactType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Identifier")
    public JAXBElement<CodeType> createIdentifier(CodeType value) {
        return new JAXBElement<>(_Identifier_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "OutputFormat")
    public JAXBElement<String> createOutputFormat(String value) {
        return new JAXBElement<>(_OutputFormat_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "AvailableCRS")
    public JAXBElement<String> createAvailableCRS(String value) {
        return new JAXBElement<>(_AvailableCRS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "SupportedCRS", substitutionHeadNamespace = "http://www.opengis.net/ows/1.1", substitutionHeadName = "AvailableCRS")
    public JAXBElement<String> createSupportedCRS(String value) {
        return new JAXBElement<>(_SupportedCRS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "AccessConstraints")
    public JAXBElement<String> createAccessConstraints(String value) {
        return new JAXBElement<>(_AccessConstraints_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Fees")
    public JAXBElement<String> createFees(String value) {
        return new JAXBElement<>(_Fees_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLanguage(String value) {
        return new JAXBElement<>(_Language_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Value")
    public JAXBElement<ValueType> createValue(ValueType value) {
        return new JAXBElement<>(_Value_QNAME, ValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RangeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RangeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Range")
    public JAXBElement<RangeType> createRange(RangeType value) {
        return new JAXBElement<>(_Range_QNAME, RangeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "DefaultValue")
    public JAXBElement<ValueType> createDefaultValue(ValueType value) {
        return new JAXBElement<>(_DefaultValue_QNAME, ValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "MinimumValue")
    public JAXBElement<ValueType> createMinimumValue(ValueType value) {
        return new JAXBElement<>(_MinimumValue_QNAME, ValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "MaximumValue")
    public JAXBElement<ValueType> createMaximumValue(ValueType value) {
        return new JAXBElement<>(_MaximumValue_QNAME, ValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Spacing")
    public JAXBElement<ValueType> createSpacing(ValueType value) {
        return new JAXBElement<>(_Spacing_QNAME, ValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Meaning")
    public JAXBElement<DomainMetadataType> createMeaning(DomainMetadataType value) {
        return new JAXBElement<>(_Meaning_QNAME, DomainMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "DataType")
    public JAXBElement<DomainMetadataType> createDataType(DomainMetadataType value) {
        return new JAXBElement<>(_DataType_QNAME, DomainMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "ReferenceSystem")
    public JAXBElement<DomainMetadataType> createReferenceSystem(DomainMetadataType value) {
        return new JAXBElement<>(_ReferenceSystem_QNAME, DomainMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DomainMetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "UOM")
    public JAXBElement<DomainMetadataType> createUOM(DomainMetadataType value) {
        return new JAXBElement<>(_UOM_QNAME, DomainMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "ExtendedCapabilities")
    public JAXBElement<Object> createExtendedCapabilities(Object value) {
        return new JAXBElement<>(_ExtendedCapabilities_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCapabilitiesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCapabilitiesType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "GetCapabilities")
    public JAXBElement<GetCapabilitiesType> createGetCapabilities(GetCapabilitiesType value) {
        return new JAXBElement<>(_GetCapabilities_QNAME, GetCapabilitiesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Resource")
    public JAXBElement<Object> createResource(Object value) {
        return new JAXBElement<>(_Resource_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResourceByIdType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetResourceByIdType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "GetResourceByID")
    public JAXBElement<GetResourceByIdType> createGetResourceByID(GetResourceByIdType value) {
        return new JAXBElement<>(_GetResourceByID_QNAME, GetResourceByIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExceptionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Exception")
    public JAXBElement<ExceptionType> createException(ExceptionType value) {
        return new JAXBElement<>(_Exception_QNAME, ExceptionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "OtherSource")
    public JAXBElement<MetadataType> createOtherSource(MetadataType value) {
        return new JAXBElement<>(_OtherSource_QNAME, MetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatasetDescriptionSummaryBaseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatasetDescriptionSummaryBaseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "DatasetDescriptionSummary")
    public JAXBElement<DatasetDescriptionSummaryBaseType> createDatasetDescriptionSummary(DatasetDescriptionSummaryBaseType value) {
        return new JAXBElement<>(_DatasetDescriptionSummary_QNAME, DatasetDescriptionSummaryBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatasetDescriptionSummaryBaseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatasetDescriptionSummaryBaseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "DatasetDescriptionSummaryExtension", substitutionHeadNamespace = "http://www.opengis.net/ows/1.1", substitutionHeadName = "DatasetDescriptionSummary")
    public JAXBElement<DatasetDescriptionSummaryBaseType> createDatasetDescriptionSummaryExtension(DatasetDescriptionSummaryBaseType value) {
        return new JAXBElement<>(_DatasetDescriptionSummaryExtension_QNAME, DatasetDescriptionSummaryBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "OtherSourceExtension", substitutionHeadNamespace = "http://www.opengis.net/ows/1.1", substitutionHeadName = "OtherSource")
    public JAXBElement<MetadataType> createOtherSourceExtension(MetadataType value) {
        return new JAXBElement<>(_OtherSourceExtension_QNAME, MetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractReferenceBaseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractReferenceBaseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "AbstractReferenceBase")
    public JAXBElement<AbstractReferenceBaseType> createAbstractReferenceBase(AbstractReferenceBaseType value) {
        return new JAXBElement<>(_AbstractReferenceBase_QNAME, AbstractReferenceBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Reference", substitutionHeadNamespace = "http://www.opengis.net/ows/1.1", substitutionHeadName = "AbstractReferenceBase")
    public JAXBElement<ReferenceType> createReference(ReferenceType value) {
        return new JAXBElement<>(_Reference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceGroupType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReferenceGroupType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "ReferenceGroup")
    public JAXBElement<ReferenceGroupType> createReferenceGroup(ReferenceGroupType value) {
        return new JAXBElement<>(_ReferenceGroup_QNAME, ReferenceGroupType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Manifest")
    public JAXBElement<ManifestType> createManifest(ManifestType value) {
        return new JAXBElement<>(_Manifest_QNAME, ManifestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "OperationResponse")
    public JAXBElement<ManifestType> createOperationResponse(ManifestType value) {
        return new JAXBElement<>(_OperationResponse_QNAME, ManifestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "InputData")
    public JAXBElement<ManifestType> createInputData(ManifestType value) {
        return new JAXBElement<>(_InputData_QNAME, ManifestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceReferenceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ServiceReferenceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "ServiceReference", substitutionHeadNamespace = "http://www.opengis.net/ows/1.1", substitutionHeadName = "Reference")
    public JAXBElement<ServiceReferenceType> createServiceReference(ServiceReferenceType value) {
        return new JAXBElement<>(_ServiceReference_QNAME, ServiceReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestMethodType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestMethodType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Get", scope = HTTP.class)
    public JAXBElement<RequestMethodType> createHTTPGet(RequestMethodType value) {
        return new JAXBElement<>(_HTTPGet_QNAME, RequestMethodType.class, HTTP.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestMethodType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestMethodType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ows/1.1", name = "Post", scope = HTTP.class)
    public JAXBElement<RequestMethodType> createHTTPPost(RequestMethodType value) {
        return new JAXBElement<>(_HTTPPost_QNAME, RequestMethodType.class, HTTP.class, value);
    }

}
