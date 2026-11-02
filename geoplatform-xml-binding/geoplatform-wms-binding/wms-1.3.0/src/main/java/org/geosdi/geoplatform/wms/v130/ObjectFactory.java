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
// Generato il: 2026.11.02 alle 02:51:45 PM CET
//

package org.geosdi.geoplatform.wms.v130;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.wms.v130 package. 
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

    private static final QName _Title_QNAME = new QName("http://www.opengis.net/wms", "Title");
    private static final QName _Abstract_QNAME = new QName("http://www.opengis.net/wms", "Abstract");
    private static final QName _ContactPerson_QNAME = new QName("http://www.opengis.net/wms", "ContactPerson");
    private static final QName _ContactOrganization_QNAME = new QName("http://www.opengis.net/wms", "ContactOrganization");
    private static final QName _ContactPosition_QNAME = new QName("http://www.opengis.net/wms", "ContactPosition");
    private static final QName _AddressType_QNAME = new QName("http://www.opengis.net/wms", "AddressType");
    private static final QName _Address_QNAME = new QName("http://www.opengis.net/wms", "Address");
    private static final QName _City_QNAME = new QName("http://www.opengis.net/wms", "City");
    private static final QName _StateOrProvince_QNAME = new QName("http://www.opengis.net/wms", "StateOrProvince");
    private static final QName _PostCode_QNAME = new QName("http://www.opengis.net/wms", "PostCode");
    private static final QName _Country_QNAME = new QName("http://www.opengis.net/wms", "Country");
    private static final QName _ContactVoiceTelephone_QNAME = new QName("http://www.opengis.net/wms", "ContactVoiceTelephone");
    private static final QName _ContactFacsimileTelephone_QNAME = new QName("http://www.opengis.net/wms", "ContactFacsimileTelephone");
    private static final QName _ContactElectronicMailAddress_QNAME = new QName("http://www.opengis.net/wms", "ContactElectronicMailAddress");
    private static final QName _Fees_QNAME = new QName("http://www.opengis.net/wms", "Fees");
    private static final QName _AccessConstraints_QNAME = new QName("http://www.opengis.net/wms", "AccessConstraints");
    private static final QName _LayerLimit_QNAME = new QName("http://www.opengis.net/wms", "LayerLimit");
    private static final QName _MaxWidth_QNAME = new QName("http://www.opengis.net/wms", "MaxWidth");
    private static final QName _MaxHeight_QNAME = new QName("http://www.opengis.net/wms", "MaxHeight");
    private static final QName _GetCapabilities_QNAME = new QName("http://www.opengis.net/wms", "GetCapabilities");
    private static final QName _GetMap_QNAME = new QName("http://www.opengis.net/wms", "GetMap");
    private static final QName _GetFeatureInfo_QNAME = new QName("http://www.opengis.net/wms", "GetFeatureInfo");
    private static final QName _ExtendedOperation_QNAME = new QName("http://www.opengis.net/wms", "_ExtendedOperation");
    private static final QName _Format_QNAME = new QName("http://www.opengis.net/wms", "Format");
    private static final QName _ExtendedCapabilities_QNAME = new QName("http://www.opengis.net/wms", "_ExtendedCapabilities");
    private static final QName _Name_QNAME = new QName("http://www.opengis.net/wms", "Name");
    private static final QName _CRS_QNAME = new QName("http://www.opengis.net/wms", "CRS");
    private static final QName _MinScaleDenominator_QNAME = new QName("http://www.opengis.net/wms", "MinScaleDenominator");
    private static final QName _MaxScaleDenominator_QNAME = new QName("http://www.opengis.net/wms", "MaxScaleDenominator");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.wms.v130
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WMSCapabilities }
     * 
     * @return
     *     the new instance of {@link WMSCapabilities }
     */
    public WMSCapabilities createWMSCapabilities() {
        return new WMSCapabilities();
    }

    /**
     * Create an instance of {@link Service }
     * 
     * @return
     *     the new instance of {@link Service }
     */
    public Service createService() {
        return new Service();
    }

    /**
     * Create an instance of {@link KeywordList }
     * 
     * @return
     *     the new instance of {@link KeywordList }
     */
    public KeywordList createKeywordList() {
        return new KeywordList();
    }

    /**
     * Create an instance of {@link Keyword }
     * 
     * @return
     *     the new instance of {@link Keyword }
     */
    public Keyword createKeyword() {
        return new Keyword();
    }

    /**
     * Create an instance of {@link OnlineResource }
     * 
     * @return
     *     the new instance of {@link OnlineResource }
     */
    public OnlineResource createOnlineResource() {
        return new OnlineResource();
    }

    /**
     * Create an instance of {@link ContactInformation }
     * 
     * @return
     *     the new instance of {@link ContactInformation }
     */
    public ContactInformation createContactInformation() {
        return new ContactInformation();
    }

    /**
     * Create an instance of {@link ContactPersonPrimary }
     * 
     * @return
     *     the new instance of {@link ContactPersonPrimary }
     */
    public ContactPersonPrimary createContactPersonPrimary() {
        return new ContactPersonPrimary();
    }

    /**
     * Create an instance of {@link ContactAddress }
     * 
     * @return
     *     the new instance of {@link ContactAddress }
     */
    public ContactAddress createContactAddress() {
        return new ContactAddress();
    }

    /**
     * Create an instance of {@link Capability }
     * 
     * @return
     *     the new instance of {@link Capability }
     */
    public Capability createCapability() {
        return new Capability();
    }

    /**
     * Create an instance of {@link Request }
     * 
     * @return
     *     the new instance of {@link Request }
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link OperationType }
     * 
     * @return
     *     the new instance of {@link OperationType }
     */
    public OperationType createOperationType() {
        return new OperationType();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     * @return
     *     the new instance of {@link Exception }
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Layer }
     * 
     * @return
     *     the new instance of {@link Layer }
     */
    public Layer createLayer() {
        return new Layer();
    }

    /**
     * Create an instance of {@link EXGeographicBoundingBox }
     * 
     * @return
     *     the new instance of {@link EXGeographicBoundingBox }
     */
    public EXGeographicBoundingBox createEXGeographicBoundingBox() {
        return new EXGeographicBoundingBox();
    }

    /**
     * Create an instance of {@link BoundingBox }
     * 
     * @return
     *     the new instance of {@link BoundingBox }
     */
    public BoundingBox createBoundingBox() {
        return new BoundingBox();
    }

    /**
     * Create an instance of {@link Dimension }
     * 
     * @return
     *     the new instance of {@link Dimension }
     */
    public Dimension createDimension() {
        return new Dimension();
    }

    /**
     * Create an instance of {@link Attribution }
     * 
     * @return
     *     the new instance of {@link Attribution }
     */
    public Attribution createAttribution() {
        return new Attribution();
    }

    /**
     * Create an instance of {@link LogoURL }
     * 
     * @return
     *     the new instance of {@link LogoURL }
     */
    public LogoURL createLogoURL() {
        return new LogoURL();
    }

    /**
     * Create an instance of {@link AuthorityURL }
     * 
     * @return
     *     the new instance of {@link AuthorityURL }
     */
    public AuthorityURL createAuthorityURL() {
        return new AuthorityURL();
    }

    /**
     * Create an instance of {@link Identifier }
     * 
     * @return
     *     the new instance of {@link Identifier }
     */
    public Identifier createIdentifier() {
        return new Identifier();
    }

    /**
     * Create an instance of {@link MetadataURL }
     * 
     * @return
     *     the new instance of {@link MetadataURL }
     */
    public MetadataURL createMetadataURL() {
        return new MetadataURL();
    }

    /**
     * Create an instance of {@link DataURL }
     * 
     * @return
     *     the new instance of {@link DataURL }
     */
    public DataURL createDataURL() {
        return new DataURL();
    }

    /**
     * Create an instance of {@link FeatureListURL }
     * 
     * @return
     *     the new instance of {@link FeatureListURL }
     */
    public FeatureListURL createFeatureListURL() {
        return new FeatureListURL();
    }

    /**
     * Create an instance of {@link Style }
     * 
     * @return
     *     the new instance of {@link Style }
     */
    public Style createStyle() {
        return new Style();
    }

    /**
     * Create an instance of {@link LegendURL }
     * 
     * @return
     *     the new instance of {@link LegendURL }
     */
    public LegendURL createLegendURL() {
        return new LegendURL();
    }

    /**
     * Create an instance of {@link StyleSheetURL }
     * 
     * @return
     *     the new instance of {@link StyleSheetURL }
     */
    public StyleSheetURL createStyleSheetURL() {
        return new StyleSheetURL();
    }

    /**
     * Create an instance of {@link StyleURL }
     * 
     * @return
     *     the new instance of {@link StyleURL }
     */
    public StyleURL createStyleURL() {
        return new StyleURL();
    }

    /**
     * Create an instance of {@link DCPType }
     * 
     * @return
     *     the new instance of {@link DCPType }
     */
    public DCPType createDCPType() {
        return new DCPType();
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
     * Create an instance of {@link Get }
     * 
     * @return
     *     the new instance of {@link Get }
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link Post }
     * 
     * @return
     *     the new instance of {@link Post }
     */
    public Post createPost() {
        return new Post();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Title")
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Abstract")
    public JAXBElement<String> createAbstract(String value) {
        return new JAXBElement<>(_Abstract_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "ContactPerson")
    public JAXBElement<String> createContactPerson(String value) {
        return new JAXBElement<>(_ContactPerson_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "ContactOrganization")
    public JAXBElement<String> createContactOrganization(String value) {
        return new JAXBElement<>(_ContactOrganization_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "ContactPosition")
    public JAXBElement<String> createContactPosition(String value) {
        return new JAXBElement<>(_ContactPosition_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "AddressType")
    public JAXBElement<String> createAddressType(String value) {
        return new JAXBElement<>(_AddressType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Address")
    public JAXBElement<String> createAddress(String value) {
        return new JAXBElement<>(_Address_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "City")
    public JAXBElement<String> createCity(String value) {
        return new JAXBElement<>(_City_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "StateOrProvince")
    public JAXBElement<String> createStateOrProvince(String value) {
        return new JAXBElement<>(_StateOrProvince_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "PostCode")
    public JAXBElement<String> createPostCode(String value) {
        return new JAXBElement<>(_PostCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Country")
    public JAXBElement<String> createCountry(String value) {
        return new JAXBElement<>(_Country_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "ContactVoiceTelephone")
    public JAXBElement<String> createContactVoiceTelephone(String value) {
        return new JAXBElement<>(_ContactVoiceTelephone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "ContactFacsimileTelephone")
    public JAXBElement<String> createContactFacsimileTelephone(String value) {
        return new JAXBElement<>(_ContactFacsimileTelephone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "ContactElectronicMailAddress")
    public JAXBElement<String> createContactElectronicMailAddress(String value) {
        return new JAXBElement<>(_ContactElectronicMailAddress_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Fees")
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
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "AccessConstraints")
    public JAXBElement<String> createAccessConstraints(String value) {
        return new JAXBElement<>(_AccessConstraints_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "LayerLimit")
    public JAXBElement<BigInteger> createLayerLimit(BigInteger value) {
        return new JAXBElement<>(_LayerLimit_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "MaxWidth")
    public JAXBElement<BigInteger> createMaxWidth(BigInteger value) {
        return new JAXBElement<>(_MaxWidth_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "MaxHeight")
    public JAXBElement<BigInteger> createMaxHeight(BigInteger value) {
        return new JAXBElement<>(_MaxHeight_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "GetCapabilities")
    public JAXBElement<OperationType> createGetCapabilities(OperationType value) {
        return new JAXBElement<>(_GetCapabilities_QNAME, OperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "GetMap")
    public JAXBElement<OperationType> createGetMap(OperationType value) {
        return new JAXBElement<>(_GetMap_QNAME, OperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "GetFeatureInfo")
    public JAXBElement<OperationType> createGetFeatureInfo(OperationType value) {
        return new JAXBElement<>(_GetFeatureInfo_QNAME, OperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "_ExtendedOperation")
    public JAXBElement<OperationType> createExtendedOperation(OperationType value) {
        return new JAXBElement<>(_ExtendedOperation_QNAME, OperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Format")
    public JAXBElement<String> createFormat(String value) {
        return new JAXBElement<>(_Format_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "_ExtendedCapabilities")
    public JAXBElement<Object> createExtendedCapabilities(Object value) {
        return new JAXBElement<>(_ExtendedCapabilities_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "CRS")
    public JAXBElement<String> createCRS(String value) {
        return new JAXBElement<>(_CRS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "MinScaleDenominator")
    public JAXBElement<Double> createMinScaleDenominator(Double value) {
        return new JAXBElement<>(_MinScaleDenominator_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/wms", name = "MaxScaleDenominator")
    public JAXBElement<Double> createMaxScaleDenominator(Double value) {
        return new JAXBElement<>(_MaxScaleDenominator_QNAME, Double.class, null, value);
    }

}
