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
// Generato il: 2026.11.02 alle 02:49:13 PM CET
//

package org.geosdi.geoplatform.xml.gml.v212;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.gml.v212 package. 
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

    private static final QName _Geometry_QNAME = new QName("http://www.opengis.net/gml", "_Geometry");
    private static final QName _GeometryCollection_QNAME = new QName("http://www.opengis.net/gml", "_GeometryCollection");
    private static final QName _GeometryMember_QNAME = new QName("http://www.opengis.net/gml", "geometryMember");
    private static final QName _PointMember_QNAME = new QName("http://www.opengis.net/gml", "pointMember");
    private static final QName _LineStringMember_QNAME = new QName("http://www.opengis.net/gml", "lineStringMember");
    private static final QName _PolygonMember_QNAME = new QName("http://www.opengis.net/gml", "polygonMember");
    private static final QName _OuterBoundaryIs_QNAME = new QName("http://www.opengis.net/gml", "outerBoundaryIs");
    private static final QName _InnerBoundaryIs_QNAME = new QName("http://www.opengis.net/gml", "innerBoundaryIs");
    private static final QName _Point_QNAME = new QName("http://www.opengis.net/gml", "Point");
    private static final QName _LineString_QNAME = new QName("http://www.opengis.net/gml", "LineString");
    private static final QName _LinearRing_QNAME = new QName("http://www.opengis.net/gml", "LinearRing");
    private static final QName _Polygon_QNAME = new QName("http://www.opengis.net/gml", "Polygon");
    private static final QName _Box_QNAME = new QName("http://www.opengis.net/gml", "Box");
    private static final QName _MultiGeometry_QNAME = new QName("http://www.opengis.net/gml", "MultiGeometry");
    private static final QName _MultiPoint_QNAME = new QName("http://www.opengis.net/gml", "MultiPoint");
    private static final QName _MultiLineString_QNAME = new QName("http://www.opengis.net/gml", "MultiLineString");
    private static final QName _MultiPolygon_QNAME = new QName("http://www.opengis.net/gml", "MultiPolygon");
    private static final QName _Coord_QNAME = new QName("http://www.opengis.net/gml", "coord");
    private static final QName _Coordinates_QNAME = new QName("http://www.opengis.net/gml", "coordinates");
    private static final QName _Feature_QNAME = new QName("http://www.opengis.net/gml", "_Feature");
    private static final QName _FeatureCollection_QNAME = new QName("http://www.opengis.net/gml", "_FeatureCollection");
    private static final QName _FeatureMember_QNAME = new QName("http://www.opengis.net/gml", "featureMember");
    private static final QName _GeometryProperty_QNAME = new QName("http://www.opengis.net/gml", "geometryProperty");
    private static final QName _BoundedBy_QNAME = new QName("http://www.opengis.net/gml", "boundedBy");
    private static final QName _PointProperty_QNAME = new QName("http://www.opengis.net/gml", "pointProperty");
    private static final QName _PolygonProperty_QNAME = new QName("http://www.opengis.net/gml", "polygonProperty");
    private static final QName _LineStringProperty_QNAME = new QName("http://www.opengis.net/gml", "lineStringProperty");
    private static final QName _MultiPointProperty_QNAME = new QName("http://www.opengis.net/gml", "multiPointProperty");
    private static final QName _MultiLineStringProperty_QNAME = new QName("http://www.opengis.net/gml", "multiLineStringProperty");
    private static final QName _MultiPolygonProperty_QNAME = new QName("http://www.opengis.net/gml", "multiPolygonProperty");
    private static final QName _MultiGeometryProperty_QNAME = new QName("http://www.opengis.net/gml", "multiGeometryProperty");
    private static final QName _Location_QNAME = new QName("http://www.opengis.net/gml", "location");
    private static final QName _CenterOf_QNAME = new QName("http://www.opengis.net/gml", "centerOf");
    private static final QName _Position_QNAME = new QName("http://www.opengis.net/gml", "position");
    private static final QName _ExtentOf_QNAME = new QName("http://www.opengis.net/gml", "extentOf");
    private static final QName _Coverage_QNAME = new QName("http://www.opengis.net/gml", "coverage");
    private static final QName _EdgeOf_QNAME = new QName("http://www.opengis.net/gml", "edgeOf");
    private static final QName _CenterLineOf_QNAME = new QName("http://www.opengis.net/gml", "centerLineOf");
    private static final QName _MultiLocation_QNAME = new QName("http://www.opengis.net/gml", "multiLocation");
    private static final QName _MultiCenterOf_QNAME = new QName("http://www.opengis.net/gml", "multiCenterOf");
    private static final QName _MultiPosition_QNAME = new QName("http://www.opengis.net/gml", "multiPosition");
    private static final QName _MultiCenterLineOf_QNAME = new QName("http://www.opengis.net/gml", "multiCenterLineOf");
    private static final QName _MultiEdgeOf_QNAME = new QName("http://www.opengis.net/gml", "multiEdgeOf");
    private static final QName _MultiCoverage_QNAME = new QName("http://www.opengis.net/gml", "multiCoverage");
    private static final QName _MultiExtentOf_QNAME = new QName("http://www.opengis.net/gml", "multiExtentOf");
    private static final QName _Description_QNAME = new QName("http://www.opengis.net/gml", "description");
    private static final QName _Name_QNAME = new QName("http://www.opengis.net/gml", "name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.gml.v212
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GeometryCollectionType }
     * 
     * @return
     *     the new instance of {@link GeometryCollectionType }
     */
    public GeometryCollectionType createGeometryCollectionType() {
        return new GeometryCollectionType();
    }

    /**
     * Create an instance of {@link GeometryAssociationType }
     * 
     * @return
     *     the new instance of {@link GeometryAssociationType }
     */
    public GeometryAssociationType createGeometryAssociationType() {
        return new GeometryAssociationType();
    }

    /**
     * Create an instance of {@link PointMemberType }
     * 
     * @return
     *     the new instance of {@link PointMemberType }
     */
    public PointMemberType createPointMemberType() {
        return new PointMemberType();
    }

    /**
     * Create an instance of {@link LineStringMemberType }
     * 
     * @return
     *     the new instance of {@link LineStringMemberType }
     */
    public LineStringMemberType createLineStringMemberType() {
        return new LineStringMemberType();
    }

    /**
     * Create an instance of {@link PolygonMemberType }
     * 
     * @return
     *     the new instance of {@link PolygonMemberType }
     */
    public PolygonMemberType createPolygonMemberType() {
        return new PolygonMemberType();
    }

    /**
     * Create an instance of {@link LinearRingMemberType }
     * 
     * @return
     *     the new instance of {@link LinearRingMemberType }
     */
    public LinearRingMemberType createLinearRingMemberType() {
        return new LinearRingMemberType();
    }

    /**
     * Create an instance of {@link PointType }
     * 
     * @return
     *     the new instance of {@link PointType }
     */
    public PointType createPointType() {
        return new PointType();
    }

    /**
     * Create an instance of {@link LineStringType }
     * 
     * @return
     *     the new instance of {@link LineStringType }
     */
    public LineStringType createLineStringType() {
        return new LineStringType();
    }

    /**
     * Create an instance of {@link LinearRingType }
     * 
     * @return
     *     the new instance of {@link LinearRingType }
     */
    public LinearRingType createLinearRingType() {
        return new LinearRingType();
    }

    /**
     * Create an instance of {@link PolygonType }
     * 
     * @return
     *     the new instance of {@link PolygonType }
     */
    public PolygonType createPolygonType() {
        return new PolygonType();
    }

    /**
     * Create an instance of {@link BoxType }
     * 
     * @return
     *     the new instance of {@link BoxType }
     */
    public BoxType createBoxType() {
        return new BoxType();
    }

    /**
     * Create an instance of {@link MultiPointType }
     * 
     * @return
     *     the new instance of {@link MultiPointType }
     */
    public MultiPointType createMultiPointType() {
        return new MultiPointType();
    }

    /**
     * Create an instance of {@link MultiLineStringType }
     * 
     * @return
     *     the new instance of {@link MultiLineStringType }
     */
    public MultiLineStringType createMultiLineStringType() {
        return new MultiLineStringType();
    }

    /**
     * Create an instance of {@link MultiPolygonType }
     * 
     * @return
     *     the new instance of {@link MultiPolygonType }
     */
    public MultiPolygonType createMultiPolygonType() {
        return new MultiPolygonType();
    }

    /**
     * Create an instance of {@link CoordType }
     * 
     * @return
     *     the new instance of {@link CoordType }
     */
    public CoordType createCoordType() {
        return new CoordType();
    }

    /**
     * Create an instance of {@link CoordinatesType }
     * 
     * @return
     *     the new instance of {@link CoordinatesType }
     */
    public CoordinatesType createCoordinatesType() {
        return new CoordinatesType();
    }

    /**
     * Create an instance of {@link FeatureAssociationType }
     * 
     * @return
     *     the new instance of {@link FeatureAssociationType }
     */
    public FeatureAssociationType createFeatureAssociationType() {
        return new FeatureAssociationType();
    }

    /**
     * Create an instance of {@link BoundingShapeType }
     * 
     * @return
     *     the new instance of {@link BoundingShapeType }
     */
    public BoundingShapeType createBoundingShapeType() {
        return new BoundingShapeType();
    }

    /**
     * Create an instance of {@link PointPropertyType }
     * 
     * @return
     *     the new instance of {@link PointPropertyType }
     */
    public PointPropertyType createPointPropertyType() {
        return new PointPropertyType();
    }

    /**
     * Create an instance of {@link PolygonPropertyType }
     * 
     * @return
     *     the new instance of {@link PolygonPropertyType }
     */
    public PolygonPropertyType createPolygonPropertyType() {
        return new PolygonPropertyType();
    }

    /**
     * Create an instance of {@link LineStringPropertyType }
     * 
     * @return
     *     the new instance of {@link LineStringPropertyType }
     */
    public LineStringPropertyType createLineStringPropertyType() {
        return new LineStringPropertyType();
    }

    /**
     * Create an instance of {@link MultiPointPropertyType }
     * 
     * @return
     *     the new instance of {@link MultiPointPropertyType }
     */
    public MultiPointPropertyType createMultiPointPropertyType() {
        return new MultiPointPropertyType();
    }

    /**
     * Create an instance of {@link MultiLineStringPropertyType }
     * 
     * @return
     *     the new instance of {@link MultiLineStringPropertyType }
     */
    public MultiLineStringPropertyType createMultiLineStringPropertyType() {
        return new MultiLineStringPropertyType();
    }

    /**
     * Create an instance of {@link MultiPolygonPropertyType }
     * 
     * @return
     *     the new instance of {@link MultiPolygonPropertyType }
     */
    public MultiPolygonPropertyType createMultiPolygonPropertyType() {
        return new MultiPolygonPropertyType();
    }

    /**
     * Create an instance of {@link MultiGeometryPropertyType }
     * 
     * @return
     *     the new instance of {@link MultiGeometryPropertyType }
     */
    public MultiGeometryPropertyType createMultiGeometryPropertyType() {
        return new MultiGeometryPropertyType();
    }

    /**
     * Create an instance of {@link GeometryPropertyType }
     * 
     * @return
     *     the new instance of {@link GeometryPropertyType }
     */
    public GeometryPropertyType createGeometryPropertyType() {
        return new GeometryPropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "_Geometry")
    public JAXBElement<AbstractGeometryType> createGeometry(AbstractGeometryType value) {
        return new JAXBElement<>(_Geometry_QNAME, AbstractGeometryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometryCollectionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GeometryCollectionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "_GeometryCollection", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<GeometryCollectionType> createGeometryCollection(GeometryCollectionType value) {
        return new JAXBElement<>(_GeometryCollection_QNAME, GeometryCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometryAssociationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GeometryAssociationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geometryMember")
    public JAXBElement<GeometryAssociationType> createGeometryMember(GeometryAssociationType value) {
        return new JAXBElement<>(_GeometryMember_QNAME, GeometryAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointMemberType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PointMemberType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointMember", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "geometryMember")
    public JAXBElement<PointMemberType> createPointMember(PointMemberType value) {
        return new JAXBElement<>(_PointMember_QNAME, PointMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringMemberType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LineStringMemberType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "lineStringMember", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "geometryMember")
    public JAXBElement<LineStringMemberType> createLineStringMember(LineStringMemberType value) {
        return new JAXBElement<>(_LineStringMember_QNAME, LineStringMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonMemberType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolygonMemberType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "polygonMember", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "geometryMember")
    public JAXBElement<PolygonMemberType> createPolygonMember(PolygonMemberType value) {
        return new JAXBElement<>(_PolygonMember_QNAME, PolygonMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearRingMemberType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinearRingMemberType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "outerBoundaryIs")
    public JAXBElement<LinearRingMemberType> createOuterBoundaryIs(LinearRingMemberType value) {
        return new JAXBElement<>(_OuterBoundaryIs_QNAME, LinearRingMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearRingMemberType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinearRingMemberType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "innerBoundaryIs")
    public JAXBElement<LinearRingMemberType> createInnerBoundaryIs(LinearRingMemberType value) {
        return new JAXBElement<>(_InnerBoundaryIs_QNAME, LinearRingMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PointType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Point", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<PointType> createPoint(PointType value) {
        return new JAXBElement<>(_Point_QNAME, PointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LineStringType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LineString", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<LineStringType> createLineString(LineStringType value) {
        return new JAXBElement<>(_LineString_QNAME, LineStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LinearRing", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<LinearRingType> createLinearRing(LinearRingType value) {
        return new JAXBElement<>(_LinearRing_QNAME, LinearRingType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Polygon", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<PolygonType> createPolygon(PolygonType value) {
        return new JAXBElement<>(_Polygon_QNAME, PolygonType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoxType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoxType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Box")
    public JAXBElement<BoxType> createBox(BoxType value) {
        return new JAXBElement<>(_Box_QNAME, BoxType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometryCollectionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GeometryCollectionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiGeometry", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<GeometryCollectionType> createMultiGeometry(GeometryCollectionType value) {
        return new JAXBElement<>(_MultiGeometry_QNAME, GeometryCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPointType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiPoint", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<MultiPointType> createMultiPoint(MultiPointType value) {
        return new JAXBElement<>(_MultiPoint_QNAME, MultiPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiLineStringType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiLineStringType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiLineString", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<MultiLineStringType> createMultiLineString(MultiLineStringType value) {
        return new JAXBElement<>(_MultiLineString_QNAME, MultiLineStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPolygonType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPolygonType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiPolygon", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Geometry")
    public JAXBElement<MultiPolygonType> createMultiPolygon(MultiPolygonType value) {
        return new JAXBElement<>(_MultiPolygon_QNAME, MultiPolygonType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CoordType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coord")
    public JAXBElement<CoordType> createCoord(CoordType value) {
        return new JAXBElement<>(_Coord_QNAME, CoordType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinatesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CoordinatesType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordinates")
    public JAXBElement<CoordinatesType> createCoordinates(CoordinatesType value) {
        return new JAXBElement<>(_Coordinates_QNAME, CoordinatesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFeatureType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractFeatureType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "_Feature")
    public JAXBElement<AbstractFeatureType> createFeature(AbstractFeatureType value) {
        return new JAXBElement<>(_Feature_QNAME, AbstractFeatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFeatureCollectionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractFeatureCollectionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "_FeatureCollection", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Feature")
    public JAXBElement<AbstractFeatureCollectionType> createFeatureCollection(AbstractFeatureCollectionType value) {
        return new JAXBElement<>(_FeatureCollection_QNAME, AbstractFeatureCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeatureAssociationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FeatureAssociationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "featureMember")
    public JAXBElement<FeatureAssociationType> createFeatureMember(FeatureAssociationType value) {
        return new JAXBElement<>(_FeatureMember_QNAME, FeatureAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link AbstractGeometryProperty }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link AbstractGeometryProperty }
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "_geometryProperty")
    public AbstractGeometryProperty createAbstractGeometryProperty(GeometryAssociationType value) {
        return new AbstractGeometryProperty(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometryAssociationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GeometryAssociationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geometryProperty")
    public JAXBElement<GeometryAssociationType> createGeometryProperty(GeometryAssociationType value) {
        return new JAXBElement<>(_GeometryProperty_QNAME, GeometryAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoundingShapeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoundingShapeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "boundedBy")
    public JAXBElement<BoundingShapeType> createBoundedBy(BoundingShapeType value) {
        return new JAXBElement<>(_BoundedBy_QNAME, BoundingShapeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<PointPropertyType> createPointProperty(PointPropertyType value) {
        return new JAXBElement<>(_PointProperty_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolygonPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "polygonProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<PolygonPropertyType> createPolygonProperty(PolygonPropertyType value) {
        return new JAXBElement<>(_PolygonProperty_QNAME, PolygonPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LineStringPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "lineStringProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<LineStringPropertyType> createLineStringProperty(LineStringPropertyType value) {
        return new JAXBElement<>(_LineStringProperty_QNAME, LineStringPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiPointProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<MultiPointPropertyType> createMultiPointProperty(MultiPointPropertyType value) {
        return new JAXBElement<>(_MultiPointProperty_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiLineStringPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiLineStringPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiLineStringProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<MultiLineStringPropertyType> createMultiLineStringProperty(MultiLineStringPropertyType value) {
        return new JAXBElement<>(_MultiLineStringProperty_QNAME, MultiLineStringPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPolygonPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPolygonPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiPolygonProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<MultiPolygonPropertyType> createMultiPolygonProperty(MultiPolygonPropertyType value) {
        return new JAXBElement<>(_MultiPolygonProperty_QNAME, MultiPolygonPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiGeometryPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiGeometryPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiGeometryProperty", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_geometryProperty")
    public JAXBElement<MultiGeometryPropertyType> createMultiGeometryProperty(MultiGeometryPropertyType value) {
        return new JAXBElement<>(_MultiGeometryProperty_QNAME, MultiGeometryPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "location", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "pointProperty")
    public JAXBElement<PointPropertyType> createLocation(PointPropertyType value) {
        return new JAXBElement<>(_Location_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "centerOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "pointProperty")
    public JAXBElement<PointPropertyType> createCenterOf(PointPropertyType value) {
        return new JAXBElement<>(_CenterOf_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "position", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "pointProperty")
    public JAXBElement<PointPropertyType> createPosition(PointPropertyType value) {
        return new JAXBElement<>(_Position_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolygonPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "extentOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "polygonProperty")
    public JAXBElement<PolygonPropertyType> createExtentOf(PolygonPropertyType value) {
        return new JAXBElement<>(_ExtentOf_QNAME, PolygonPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolygonPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "polygonProperty")
    public JAXBElement<PolygonPropertyType> createCoverage(PolygonPropertyType value) {
        return new JAXBElement<>(_Coverage_QNAME, PolygonPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LineStringPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "edgeOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "lineStringProperty")
    public JAXBElement<LineStringPropertyType> createEdgeOf(LineStringPropertyType value) {
        return new JAXBElement<>(_EdgeOf_QNAME, LineStringPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LineStringPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "centerLineOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "lineStringProperty")
    public JAXBElement<LineStringPropertyType> createCenterLineOf(LineStringPropertyType value) {
        return new JAXBElement<>(_CenterLineOf_QNAME, LineStringPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiLocation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiPointProperty")
    public JAXBElement<MultiPointPropertyType> createMultiLocation(MultiPointPropertyType value) {
        return new JAXBElement<>(_MultiLocation_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCenterOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiPointProperty")
    public JAXBElement<MultiPointPropertyType> createMultiCenterOf(MultiPointPropertyType value) {
        return new JAXBElement<>(_MultiCenterOf_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiPosition", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiPointProperty")
    public JAXBElement<MultiPointPropertyType> createMultiPosition(MultiPointPropertyType value) {
        return new JAXBElement<>(_MultiPosition_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiLineStringPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiLineStringPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCenterLineOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiLineStringProperty")
    public JAXBElement<MultiLineStringPropertyType> createMultiCenterLineOf(MultiLineStringPropertyType value) {
        return new JAXBElement<>(_MultiCenterLineOf_QNAME, MultiLineStringPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiLineStringPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiLineStringPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiEdgeOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiLineStringProperty")
    public JAXBElement<MultiLineStringPropertyType> createMultiEdgeOf(MultiLineStringPropertyType value) {
        return new JAXBElement<>(_MultiEdgeOf_QNAME, MultiLineStringPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPolygonPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPolygonPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiPolygonProperty")
    public JAXBElement<MultiPolygonPropertyType> createMultiCoverage(MultiPolygonPropertyType value) {
        return new JAXBElement<>(_MultiCoverage_QNAME, MultiPolygonPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPolygonPropertyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiPolygonPropertyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiExtentOf", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "multiPolygonProperty")
    public JAXBElement<MultiPolygonPropertyType> createMultiExtentOf(MultiPolygonPropertyType value) {
        return new JAXBElement<>(_MultiExtentOf_QNAME, MultiPolygonPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<>(_Name_QNAME, String.class, null, value);
    }

}
