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
// Generato il: 2026.11.02 alle 03:48:06 PM CET
//

package org.geosdi.geoplatform.xml.gfc;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CodeListValueType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.gfc package. 
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

    private static final QName _FCListedValue_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_ListedValue");
    private static final QName _FCFeatureAssociation_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_FeatureAssociation");
    private static final QName _FCFeatureType_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_FeatureType");
    private static final QName _FCFeatureCatalogue_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_FeatureCatalogue");
    private static final QName _FCFeatureAttribute_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_FeatureAttribute");
    private static final QName _AbstractFCPropertyType_QNAME = new QName("http://www.isotc211.org/2005/gfc", "AbstractFC_PropertyType");
    private static final QName _AbstractFCCarrierOfCharacteristics_QNAME = new QName("http://www.isotc211.org/2005/gfc", "AbstractFC_CarrierOfCharacteristics");
    private static final QName _FCAssociationRole_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_AssociationRole");
    private static final QName _FCInheritanceRelation_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_InheritanceRelation");
    private static final QName _FCFeatureOperation_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_FeatureOperation");
    private static final QName _FCConstraint_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_Constraint");
    private static final QName _FCBinding_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_Binding");
    private static final QName _FCDefinitionSource_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_DefinitionSource");
    private static final QName _FCDefinitionReference_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_DefinitionReference");
    private static final QName _FCBoundFeatureAttribute_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_BoundFeatureAttribute");
    private static final QName _FCBoundAssociationRole_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_BoundAssociationRole");
    private static final QName _FCRoleType_QNAME = new QName("http://www.isotc211.org/2005/gfc", "FC_RoleType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.gfc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FCListedValueType }
     * 
     * @return
     *     the new instance of {@link FCListedValueType }
     */
    public FCListedValueType createFCListedValueType() {
        return new FCListedValueType();
    }

    /**
     * Create an instance of {@link FCFeatureAssociationType }
     * 
     * @return
     *     the new instance of {@link FCFeatureAssociationType }
     */
    public FCFeatureAssociationType createFCFeatureAssociationType() {
        return new FCFeatureAssociationType();
    }

    /**
     * Create an instance of {@link FCFeatureTypeType }
     * 
     * @return
     *     the new instance of {@link FCFeatureTypeType }
     */
    public FCFeatureTypeType createFCFeatureTypeType() {
        return new FCFeatureTypeType();
    }

    /**
     * Create an instance of {@link FCFeatureCatalogueType }
     * 
     * @return
     *     the new instance of {@link FCFeatureCatalogueType }
     */
    public FCFeatureCatalogueType createFCFeatureCatalogueType() {
        return new FCFeatureCatalogueType();
    }

    /**
     * Create an instance of {@link FCFeatureAttributeType }
     * 
     * @return
     *     the new instance of {@link FCFeatureAttributeType }
     */
    public FCFeatureAttributeType createFCFeatureAttributeType() {
        return new FCFeatureAttributeType();
    }

    /**
     * Create an instance of {@link FCAssociationRoleType }
     * 
     * @return
     *     the new instance of {@link FCAssociationRoleType }
     */
    public FCAssociationRoleType createFCAssociationRoleType() {
        return new FCAssociationRoleType();
    }

    /**
     * Create an instance of {@link FCInheritanceRelationType }
     * 
     * @return
     *     the new instance of {@link FCInheritanceRelationType }
     */
    public FCInheritanceRelationType createFCInheritanceRelationType() {
        return new FCInheritanceRelationType();
    }

    /**
     * Create an instance of {@link FCFeatureOperationType }
     * 
     * @return
     *     the new instance of {@link FCFeatureOperationType }
     */
    public FCFeatureOperationType createFCFeatureOperationType() {
        return new FCFeatureOperationType();
    }

    /**
     * Create an instance of {@link FCConstraintType }
     * 
     * @return
     *     the new instance of {@link FCConstraintType }
     */
    public FCConstraintType createFCConstraintType() {
        return new FCConstraintType();
    }

    /**
     * Create an instance of {@link FCBindingType }
     * 
     * @return
     *     the new instance of {@link FCBindingType }
     */
    public FCBindingType createFCBindingType() {
        return new FCBindingType();
    }

    /**
     * Create an instance of {@link FCDefinitionSourceType }
     * 
     * @return
     *     the new instance of {@link FCDefinitionSourceType }
     */
    public FCDefinitionSourceType createFCDefinitionSourceType() {
        return new FCDefinitionSourceType();
    }

    /**
     * Create an instance of {@link FCDefinitionReferenceType }
     * 
     * @return
     *     the new instance of {@link FCDefinitionReferenceType }
     */
    public FCDefinitionReferenceType createFCDefinitionReferenceType() {
        return new FCDefinitionReferenceType();
    }

    /**
     * Create an instance of {@link FCBoundFeatureAttributeType }
     * 
     * @return
     *     the new instance of {@link FCBoundFeatureAttributeType }
     */
    public FCBoundFeatureAttributeType createFCBoundFeatureAttributeType() {
        return new FCBoundFeatureAttributeType();
    }

    /**
     * Create an instance of {@link FCBoundAssociationRoleType }
     * 
     * @return
     *     the new instance of {@link FCBoundAssociationRoleType }
     */
    public FCBoundAssociationRoleType createFCBoundAssociationRoleType() {
        return new FCBoundAssociationRoleType();
    }

    /**
     * Create an instance of {@link FCListedValuePropertyType }
     * 
     * @return
     *     the new instance of {@link FCListedValuePropertyType }
     */
    public FCListedValuePropertyType createFCListedValuePropertyType() {
        return new FCListedValuePropertyType();
    }

    /**
     * Create an instance of {@link FCFeatureAssociationPropertyType }
     * 
     * @return
     *     the new instance of {@link FCFeatureAssociationPropertyType }
     */
    public FCFeatureAssociationPropertyType createFCFeatureAssociationPropertyType() {
        return new FCFeatureAssociationPropertyType();
    }

    /**
     * Create an instance of {@link FCFeatureCataloguePropertyType }
     * 
     * @return
     *     the new instance of {@link FCFeatureCataloguePropertyType }
     */
    public FCFeatureCataloguePropertyType createFCFeatureCataloguePropertyType() {
        return new FCFeatureCataloguePropertyType();
    }

    /**
     * Create an instance of {@link FCFeatureAttributePropertyType }
     * 
     * @return
     *     the new instance of {@link FCFeatureAttributePropertyType }
     */
    public FCFeatureAttributePropertyType createFCFeatureAttributePropertyType() {
        return new FCFeatureAttributePropertyType();
    }

    /**
     * Create an instance of {@link FCAssociationRolePropertyType }
     * 
     * @return
     *     the new instance of {@link FCAssociationRolePropertyType }
     */
    public FCAssociationRolePropertyType createFCAssociationRolePropertyType() {
        return new FCAssociationRolePropertyType();
    }

    /**
     * Create an instance of {@link FCFeatureTypePropertyType }
     * 
     * @return
     *     the new instance of {@link FCFeatureTypePropertyType }
     */
    public FCFeatureTypePropertyType createFCFeatureTypePropertyType() {
        return new FCFeatureTypePropertyType();
    }

    /**
     * Create an instance of {@link FCInheritanceRelationPropertyType }
     * 
     * @return
     *     the new instance of {@link FCInheritanceRelationPropertyType }
     */
    public FCInheritanceRelationPropertyType createFCInheritanceRelationPropertyType() {
        return new FCInheritanceRelationPropertyType();
    }

    /**
     * Create an instance of {@link FCFeatureOperationPropertyType }
     * 
     * @return
     *     the new instance of {@link FCFeatureOperationPropertyType }
     */
    public FCFeatureOperationPropertyType createFCFeatureOperationPropertyType() {
        return new FCFeatureOperationPropertyType();
    }

    /**
     * Create an instance of {@link FCPropertyTypePropertyType }
     * 
     * @return
     *     the new instance of {@link FCPropertyTypePropertyType }
     */
    public FCPropertyTypePropertyType createFCPropertyTypePropertyType() {
        return new FCPropertyTypePropertyType();
    }

    /**
     * Create an instance of {@link FCConstraintPropertyType }
     * 
     * @return
     *     the new instance of {@link FCConstraintPropertyType }
     */
    public FCConstraintPropertyType createFCConstraintPropertyType() {
        return new FCConstraintPropertyType();
    }

    /**
     * Create an instance of {@link FCBindingPropertyType }
     * 
     * @return
     *     the new instance of {@link FCBindingPropertyType }
     */
    public FCBindingPropertyType createFCBindingPropertyType() {
        return new FCBindingPropertyType();
    }

    /**
     * Create an instance of {@link FCDefinitionSourcePropertyType }
     * 
     * @return
     *     the new instance of {@link FCDefinitionSourcePropertyType }
     */
    public FCDefinitionSourcePropertyType createFCDefinitionSourcePropertyType() {
        return new FCDefinitionSourcePropertyType();
    }

    /**
     * Create an instance of {@link FCDefinitionReferencePropertyType }
     * 
     * @return
     *     the new instance of {@link FCDefinitionReferencePropertyType }
     */
    public FCDefinitionReferencePropertyType createFCDefinitionReferencePropertyType() {
        return new FCDefinitionReferencePropertyType();
    }

    /**
     * Create an instance of {@link FCBoundFeatureAttributePropertyType }
     * 
     * @return
     *     the new instance of {@link FCBoundFeatureAttributePropertyType }
     */
    public FCBoundFeatureAttributePropertyType createFCBoundFeatureAttributePropertyType() {
        return new FCBoundFeatureAttributePropertyType();
    }

    /**
     * Create an instance of {@link FCCarrierOfCharacteristicsPropertyType }
     * 
     * @return
     *     the new instance of {@link FCCarrierOfCharacteristicsPropertyType }
     */
    public FCCarrierOfCharacteristicsPropertyType createFCCarrierOfCharacteristicsPropertyType() {
        return new FCCarrierOfCharacteristicsPropertyType();
    }

    /**
     * Create an instance of {@link FCBoundAssociationRolePropertyType }
     * 
     * @return
     *     the new instance of {@link FCBoundAssociationRolePropertyType }
     */
    public FCBoundAssociationRolePropertyType createFCBoundAssociationRolePropertyType() {
        return new FCBoundAssociationRolePropertyType();
    }

    /**
     * Create an instance of {@link FCRoleTypePropertyType }
     * 
     * @return
     *     the new instance of {@link FCRoleTypePropertyType }
     */
    public FCRoleTypePropertyType createFCRoleTypePropertyType() {
        return new FCRoleTypePropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCListedValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCListedValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_ListedValue")
    public JAXBElement<FCListedValueType> createFCListedValue(FCListedValueType value) {
        return new JAXBElement<>(_FCListedValue_QNAME, FCListedValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCFeatureAssociationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCFeatureAssociationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_FeatureAssociation", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "FC_FeatureType")
    public JAXBElement<FCFeatureAssociationType> createFCFeatureAssociation(FCFeatureAssociationType value) {
        return new JAXBElement<>(_FCFeatureAssociation_QNAME, FCFeatureAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCFeatureTypeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCFeatureTypeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_FeatureType")
    public JAXBElement<FCFeatureTypeType> createFCFeatureType(FCFeatureTypeType value) {
        return new JAXBElement<>(_FCFeatureType_QNAME, FCFeatureTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCFeatureCatalogueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCFeatureCatalogueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_FeatureCatalogue", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "AbstractCT_Catalogue")
    public JAXBElement<FCFeatureCatalogueType> createFCFeatureCatalogue(FCFeatureCatalogueType value) {
        return new JAXBElement<>(_FCFeatureCatalogue_QNAME, FCFeatureCatalogueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCFeatureAttributeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCFeatureAttributeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_FeatureAttribute", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "AbstractFC_PropertyType")
    public JAXBElement<FCFeatureAttributeType> createFCFeatureAttribute(FCFeatureAttributeType value) {
        return new JAXBElement<>(_FCFeatureAttribute_QNAME, FCFeatureAttributeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFCPropertyTypeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractFCPropertyTypeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "AbstractFC_PropertyType", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "AbstractFC_CarrierOfCharacteristics")
    public JAXBElement<AbstractFCPropertyTypeType> createAbstractFCPropertyType(AbstractFCPropertyTypeType value) {
        return new JAXBElement<>(_AbstractFCPropertyType_QNAME, AbstractFCPropertyTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFCCarrierOfCharacteristicsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractFCCarrierOfCharacteristicsType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "AbstractFC_CarrierOfCharacteristics")
    public JAXBElement<AbstractFCCarrierOfCharacteristicsType> createAbstractFCCarrierOfCharacteristics(AbstractFCCarrierOfCharacteristicsType value) {
        return new JAXBElement<>(_AbstractFCCarrierOfCharacteristics_QNAME, AbstractFCCarrierOfCharacteristicsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCAssociationRoleType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCAssociationRoleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_AssociationRole", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "AbstractFC_PropertyType")
    public JAXBElement<FCAssociationRoleType> createFCAssociationRole(FCAssociationRoleType value) {
        return new JAXBElement<>(_FCAssociationRole_QNAME, FCAssociationRoleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCInheritanceRelationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCInheritanceRelationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_InheritanceRelation")
    public JAXBElement<FCInheritanceRelationType> createFCInheritanceRelation(FCInheritanceRelationType value) {
        return new JAXBElement<>(_FCInheritanceRelation_QNAME, FCInheritanceRelationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCFeatureOperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCFeatureOperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_FeatureOperation", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "AbstractFC_PropertyType")
    public JAXBElement<FCFeatureOperationType> createFCFeatureOperation(FCFeatureOperationType value) {
        return new JAXBElement<>(_FCFeatureOperation_QNAME, FCFeatureOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCConstraintType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCConstraintType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_Constraint")
    public JAXBElement<FCConstraintType> createFCConstraint(FCConstraintType value) {
        return new JAXBElement<>(_FCConstraint_QNAME, FCConstraintType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCBindingType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCBindingType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_Binding", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "AbstractFC_CarrierOfCharacteristics")
    public JAXBElement<FCBindingType> createFCBinding(FCBindingType value) {
        return new JAXBElement<>(_FCBinding_QNAME, FCBindingType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCDefinitionSourceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCDefinitionSourceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_DefinitionSource")
    public JAXBElement<FCDefinitionSourceType> createFCDefinitionSource(FCDefinitionSourceType value) {
        return new JAXBElement<>(_FCDefinitionSource_QNAME, FCDefinitionSourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCDefinitionReferenceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCDefinitionReferenceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_DefinitionReference")
    public JAXBElement<FCDefinitionReferenceType> createFCDefinitionReference(FCDefinitionReferenceType value) {
        return new JAXBElement<>(_FCDefinitionReference_QNAME, FCDefinitionReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCBoundFeatureAttributeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCBoundFeatureAttributeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_BoundFeatureAttribute", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "FC_Binding")
    public JAXBElement<FCBoundFeatureAttributeType> createFCBoundFeatureAttribute(FCBoundFeatureAttributeType value) {
        return new JAXBElement<>(_FCBoundFeatureAttribute_QNAME, FCBoundFeatureAttributeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FCBoundAssociationRoleType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FCBoundAssociationRoleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_BoundAssociationRole", substitutionHeadNamespace = "http://www.isotc211.org/2005/gfc", substitutionHeadName = "FC_Binding")
    public JAXBElement<FCBoundAssociationRoleType> createFCBoundAssociationRole(FCBoundAssociationRoleType value) {
        return new JAXBElement<>(_FCBoundAssociationRole_QNAME, FCBoundAssociationRoleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gfc", name = "FC_RoleType", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<CodeListValueType> createFCRoleType(CodeListValueType value) {
        return new JAXBElement<>(_FCRoleType_QNAME, CodeListValueType.class, null, value);
    }

}
