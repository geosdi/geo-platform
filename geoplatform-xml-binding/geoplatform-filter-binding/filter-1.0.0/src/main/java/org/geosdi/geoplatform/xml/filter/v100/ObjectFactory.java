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
// Generato il: 2026.11.02 alle 02:49:38 PM CET
//

package org.geosdi.geoplatform.xml.filter.v100;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.filter.v100 package. 
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

    private static final QName _Add_QNAME = new QName("http://www.opengis.net/ogc", "Add");
    private static final QName _Expression_QNAME = new QName("http://www.opengis.net/ogc", "expression");
    private static final QName _Sub_QNAME = new QName("http://www.opengis.net/ogc", "Sub");
    private static final QName _Mul_QNAME = new QName("http://www.opengis.net/ogc", "Mul");
    private static final QName _Div_QNAME = new QName("http://www.opengis.net/ogc", "Div");
    private static final QName _PropertyName_QNAME = new QName("http://www.opengis.net/ogc", "PropertyName");
    private static final QName _Function_QNAME = new QName("http://www.opengis.net/ogc", "Function");
    private static final QName _Literal_QNAME = new QName("http://www.opengis.net/ogc", "Literal");
    private static final QName _FeatureId_QNAME = new QName("http://www.opengis.net/ogc", "FeatureId");
    private static final QName _Filter_QNAME = new QName("http://www.opengis.net/ogc", "Filter");
    private static final QName _ComparisonOps_QNAME = new QName("http://www.opengis.net/ogc", "comparisonOps");
    private static final QName _PropertyIsEqualTo_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsEqualTo");
    private static final QName _PropertyIsNotEqualTo_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsNotEqualTo");
    private static final QName _PropertyIsLessThan_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsLessThan");
    private static final QName _PropertyIsGreaterThan_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsGreaterThan");
    private static final QName _PropertyIsLessThanOrEqualTo_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsLessThanOrEqualTo");
    private static final QName _PropertyIsGreaterThanOrEqualTo_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsGreaterThanOrEqualTo");
    private static final QName _PropertyIsLike_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsLike");
    private static final QName _PropertyIsNull_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsNull");
    private static final QName _PropertyIsBetween_QNAME = new QName("http://www.opengis.net/ogc", "PropertyIsBetween");
    private static final QName _SpatialOps_QNAME = new QName("http://www.opengis.net/ogc", "spatialOps");
    private static final QName _Equals_QNAME = new QName("http://www.opengis.net/ogc", "Equals");
    private static final QName _Disjoint_QNAME = new QName("http://www.opengis.net/ogc", "Disjoint");
    private static final QName _Touches_QNAME = new QName("http://www.opengis.net/ogc", "Touches");
    private static final QName _Within_QNAME = new QName("http://www.opengis.net/ogc", "Within");
    private static final QName _Overlaps_QNAME = new QName("http://www.opengis.net/ogc", "Overlaps");
    private static final QName _Crosses_QNAME = new QName("http://www.opengis.net/ogc", "Crosses");
    private static final QName _Intersects_QNAME = new QName("http://www.opengis.net/ogc", "Intersects");
    private static final QName _Contains_QNAME = new QName("http://www.opengis.net/ogc", "Contains");
    private static final QName _DWithin_QNAME = new QName("http://www.opengis.net/ogc", "DWithin");
    private static final QName _Beyond_QNAME = new QName("http://www.opengis.net/ogc", "Beyond");
    private static final QName _BBOX_QNAME = new QName("http://www.opengis.net/ogc", "BBOX");
    private static final QName _LogicOps_QNAME = new QName("http://www.opengis.net/ogc", "logicOps");
    private static final QName _And_QNAME = new QName("http://www.opengis.net/ogc", "And");
    private static final QName _Or_QNAME = new QName("http://www.opengis.net/ogc", "Or");
    private static final QName _Not_QNAME = new QName("http://www.opengis.net/ogc", "Not");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.filter.v100
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BinaryOperatorType }
     * 
     * @return
     *     the new instance of {@link BinaryOperatorType }
     */
    public BinaryOperatorType createBinaryOperatorType() {
        return new BinaryOperatorType();
    }

    /**
     * Create an instance of {@link PropertyNameType }
     * 
     * @return
     *     the new instance of {@link PropertyNameType }
     */
    public PropertyNameType createPropertyNameType() {
        return new PropertyNameType();
    }

    /**
     * Create an instance of {@link FunctionType }
     * 
     * @return
     *     the new instance of {@link FunctionType }
     */
    public FunctionType createFunctionType() {
        return new FunctionType();
    }

    /**
     * Create an instance of {@link LiteralType }
     * 
     * @return
     *     the new instance of {@link LiteralType }
     */
    public LiteralType createLiteralType() {
        return new LiteralType();
    }

    /**
     * Create an instance of {@link FeatureIdType }
     * 
     * @return
     *     the new instance of {@link FeatureIdType }
     */
    public FeatureIdType createFeatureIdType() {
        return new FeatureIdType();
    }

    /**
     * Create an instance of {@link FilterType }
     * 
     * @return
     *     the new instance of {@link FilterType }
     */
    public FilterType createFilterType() {
        return new FilterType();
    }

    /**
     * Create an instance of {@link BinaryComparisonOpType }
     * 
     * @return
     *     the new instance of {@link BinaryComparisonOpType }
     */
    public BinaryComparisonOpType createBinaryComparisonOpType() {
        return new BinaryComparisonOpType();
    }

    /**
     * Create an instance of {@link PropertyIsLikeType }
     * 
     * @return
     *     the new instance of {@link PropertyIsLikeType }
     */
    public PropertyIsLikeType createPropertyIsLikeType() {
        return new PropertyIsLikeType();
    }

    /**
     * Create an instance of {@link PropertyIsNullType }
     * 
     * @return
     *     the new instance of {@link PropertyIsNullType }
     */
    public PropertyIsNullType createPropertyIsNullType() {
        return new PropertyIsNullType();
    }

    /**
     * Create an instance of {@link PropertyIsBetweenType }
     * 
     * @return
     *     the new instance of {@link PropertyIsBetweenType }
     */
    public PropertyIsBetweenType createPropertyIsBetweenType() {
        return new PropertyIsBetweenType();
    }

    /**
     * Create an instance of {@link BinarySpatialOpType }
     * 
     * @return
     *     the new instance of {@link BinarySpatialOpType }
     */
    public BinarySpatialOpType createBinarySpatialOpType() {
        return new BinarySpatialOpType();
    }

    /**
     * Create an instance of {@link DistanceBufferType }
     * 
     * @return
     *     the new instance of {@link DistanceBufferType }
     */
    public DistanceBufferType createDistanceBufferType() {
        return new DistanceBufferType();
    }

    /**
     * Create an instance of {@link BBOXType }
     * 
     * @return
     *     the new instance of {@link BBOXType }
     */
    public BBOXType createBBOXType() {
        return new BBOXType();
    }

    /**
     * Create an instance of {@link BinaryLogicOpType }
     * 
     * @return
     *     the new instance of {@link BinaryLogicOpType }
     */
    public BinaryLogicOpType createBinaryLogicOpType() {
        return new BinaryLogicOpType();
    }

    /**
     * Create an instance of {@link UnaryLogicOpType }
     * 
     * @return
     *     the new instance of {@link UnaryLogicOpType }
     */
    public UnaryLogicOpType createUnaryLogicOpType() {
        return new UnaryLogicOpType();
    }

    /**
     * Create an instance of {@link LowerBoundaryType }
     * 
     * @return
     *     the new instance of {@link LowerBoundaryType }
     */
    public LowerBoundaryType createLowerBoundaryType() {
        return new LowerBoundaryType();
    }

    /**
     * Create an instance of {@link UpperBoundaryType }
     * 
     * @return
     *     the new instance of {@link UpperBoundaryType }
     */
    public UpperBoundaryType createUpperBoundaryType() {
        return new UpperBoundaryType();
    }

    /**
     * Create an instance of {@link DistanceType }
     * 
     * @return
     *     the new instance of {@link DistanceType }
     */
    public DistanceType createDistanceType() {
        return new DistanceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Add", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<BinaryOperatorType> createAdd(BinaryOperatorType value) {
        return new JAXBElement<>(_Add_QNAME, BinaryOperatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpressionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExpressionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "expression")
    public JAXBElement<ExpressionType> createExpression(ExpressionType value) {
        return new JAXBElement<>(_Expression_QNAME, ExpressionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Sub", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<BinaryOperatorType> createSub(BinaryOperatorType value) {
        return new JAXBElement<>(_Sub_QNAME, BinaryOperatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Mul", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<BinaryOperatorType> createMul(BinaryOperatorType value) {
        return new JAXBElement<>(_Mul_QNAME, BinaryOperatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryOperatorType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Div", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<BinaryOperatorType> createDiv(BinaryOperatorType value) {
        return new JAXBElement<>(_Div_QNAME, BinaryOperatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropertyNameType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PropertyNameType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyName", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<PropertyNameType> createPropertyName(PropertyNameType value) {
        return new JAXBElement<>(_PropertyName_QNAME, PropertyNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FunctionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FunctionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Function", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<FunctionType> createFunction(FunctionType value) {
        return new JAXBElement<>(_Function_QNAME, FunctionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LiteralType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LiteralType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Literal", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "expression")
    public JAXBElement<LiteralType> createLiteral(LiteralType value) {
        return new JAXBElement<>(_Literal_QNAME, LiteralType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeatureIdType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FeatureIdType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "FeatureId")
    public JAXBElement<FeatureIdType> createFeatureId(FeatureIdType value) {
        return new JAXBElement<>(_FeatureId_QNAME, FeatureIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FilterType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Filter")
    public JAXBElement<FilterType> createFilter(FilterType value) {
        return new JAXBElement<>(_Filter_QNAME, FilterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComparisonOpsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ComparisonOpsType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "comparisonOps")
    public JAXBElement<ComparisonOpsType> createComparisonOps(ComparisonOpsType value) {
        return new JAXBElement<>(_ComparisonOps_QNAME, ComparisonOpsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsEqualTo", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<BinaryComparisonOpType> createPropertyIsEqualTo(BinaryComparisonOpType value) {
        return new JAXBElement<>(_PropertyIsEqualTo_QNAME, BinaryComparisonOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsNotEqualTo", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<BinaryComparisonOpType> createPropertyIsNotEqualTo(BinaryComparisonOpType value) {
        return new JAXBElement<>(_PropertyIsNotEqualTo_QNAME, BinaryComparisonOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsLessThan", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<BinaryComparisonOpType> createPropertyIsLessThan(BinaryComparisonOpType value) {
        return new JAXBElement<>(_PropertyIsLessThan_QNAME, BinaryComparisonOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsGreaterThan", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<BinaryComparisonOpType> createPropertyIsGreaterThan(BinaryComparisonOpType value) {
        return new JAXBElement<>(_PropertyIsGreaterThan_QNAME, BinaryComparisonOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsLessThanOrEqualTo", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<BinaryComparisonOpType> createPropertyIsLessThanOrEqualTo(BinaryComparisonOpType value) {
        return new JAXBElement<>(_PropertyIsLessThanOrEqualTo_QNAME, BinaryComparisonOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsGreaterThanOrEqualTo", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<BinaryComparisonOpType> createPropertyIsGreaterThanOrEqualTo(BinaryComparisonOpType value) {
        return new JAXBElement<>(_PropertyIsGreaterThanOrEqualTo_QNAME, BinaryComparisonOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropertyIsLikeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PropertyIsLikeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsLike", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<PropertyIsLikeType> createPropertyIsLike(PropertyIsLikeType value) {
        return new JAXBElement<>(_PropertyIsLike_QNAME, PropertyIsLikeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropertyIsNullType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PropertyIsNullType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsNull", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<PropertyIsNullType> createPropertyIsNull(PropertyIsNullType value) {
        return new JAXBElement<>(_PropertyIsNull_QNAME, PropertyIsNullType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropertyIsBetweenType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PropertyIsBetweenType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "PropertyIsBetween", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "comparisonOps")
    public JAXBElement<PropertyIsBetweenType> createPropertyIsBetween(PropertyIsBetweenType value) {
        return new JAXBElement<>(_PropertyIsBetween_QNAME, PropertyIsBetweenType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpatialOpsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SpatialOpsType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "spatialOps")
    public JAXBElement<SpatialOpsType> createSpatialOps(SpatialOpsType value) {
        return new JAXBElement<>(_SpatialOps_QNAME, SpatialOpsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Equals", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createEquals(BinarySpatialOpType value) {
        return new JAXBElement<>(_Equals_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Disjoint", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createDisjoint(BinarySpatialOpType value) {
        return new JAXBElement<>(_Disjoint_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Touches", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createTouches(BinarySpatialOpType value) {
        return new JAXBElement<>(_Touches_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Within", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createWithin(BinarySpatialOpType value) {
        return new JAXBElement<>(_Within_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Overlaps", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createOverlaps(BinarySpatialOpType value) {
        return new JAXBElement<>(_Overlaps_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Crosses", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createCrosses(BinarySpatialOpType value) {
        return new JAXBElement<>(_Crosses_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Intersects", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createIntersects(BinarySpatialOpType value) {
        return new JAXBElement<>(_Intersects_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Contains", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BinarySpatialOpType> createContains(BinarySpatialOpType value) {
        return new JAXBElement<>(_Contains_QNAME, BinarySpatialOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "DWithin", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<DistanceBufferType> createDWithin(DistanceBufferType value) {
        return new JAXBElement<>(_DWithin_QNAME, DistanceBufferType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Beyond", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<DistanceBufferType> createBeyond(DistanceBufferType value) {
        return new JAXBElement<>(_Beyond_QNAME, DistanceBufferType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BBOXType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BBOXType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "BBOX", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "spatialOps")
    public JAXBElement<BBOXType> createBBOX(BBOXType value) {
        return new JAXBElement<>(_BBOX_QNAME, BBOXType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogicOpsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LogicOpsType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "logicOps")
    public JAXBElement<LogicOpsType> createLogicOps(LogicOpsType value) {
        return new JAXBElement<>(_LogicOps_QNAME, LogicOpsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "And", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "logicOps")
    public JAXBElement<BinaryLogicOpType> createAnd(BinaryLogicOpType value) {
        return new JAXBElement<>(_And_QNAME, BinaryLogicOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Or", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "logicOps")
    public JAXBElement<BinaryLogicOpType> createOr(BinaryLogicOpType value) {
        return new JAXBElement<>(_Or_QNAME, BinaryLogicOpType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnaryLogicOpType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnaryLogicOpType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/ogc", name = "Not", substitutionHeadNamespace = "http://www.opengis.net/ogc", substitutionHeadName = "logicOps")
    public JAXBElement<UnaryLogicOpType> createNot(UnaryLogicOpType value) {
        return new JAXBElement<>(_Not_QNAME, UnaryLogicOpType.class, null, value);
    }

}
