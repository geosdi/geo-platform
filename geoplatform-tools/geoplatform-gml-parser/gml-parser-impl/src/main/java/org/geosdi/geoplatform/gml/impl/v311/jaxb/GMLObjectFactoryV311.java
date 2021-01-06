/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.impl.v311.jaxb;

import org.geosdi.geoplatform.gml.api.*;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.xml.gml.v311.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLObjectFactoryV311 extends AbstractGMLObjectFactory {

    /**
     * @param value
     * @return {@link JAXBElement<? extends DirectPosition>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pos")
    public JAXBElement<? extends DirectPosition> createPos(DirectPosition value) {
        return new JAXBElement<DirectPositionType>(_Pos_QNAME,
                DirectPositionType.class, null, (DirectPositionType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends Point>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Point",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_GeometricPrimitive")
    public JAXBElement<? extends Point> createPoint(Point value) {
        return new JAXBElement<PointType>(_Point_QNAME, PointType.class, null,
                (PointType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends PointProperty> }
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "pointProperty")
    public JAXBElement<? extends PointProperty> createPointProperty(
            PointProperty value) {
        return new JAXBElement<PointPropertyType>(_PointProperty_QNAME, PointPropertyType.class, null,
                (PointPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends LineString>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "LineString",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_Curve")
    public JAXBElement<? extends LineString> createLineString(LineString value) {
        return new JAXBElement<LineStringType>(_LineString_QNAME, LineStringType.class, null,
                (LineStringType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends LineStringProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "lineStringProperty")
    public JAXBElement<? extends LineStringProperty> createLineStringProperty(
            LineStringProperty value) {
        return new JAXBElement<LineStringPropertyType>(_LineStringProperty_QNAME, LineStringPropertyType.class, null,
                (LineStringPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends LinearRing>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "LinearRing",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_Ring")
    public JAXBElement<? extends LinearRing> createLinearRing(LinearRing value) {
        return new JAXBElement<LinearRingType>(_LinearRing_QNAME, LinearRingType.class, null, (LinearRingType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends LinearRingProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "linearRingProperty")
    public JAXBElement<? extends LinearRingProperty> createLinearRingProperty(LinearRingProperty value) {
        return new JAXBElement<LinearRingPropertyType>(_LinearRingProperty_QNAME, LinearRingPropertyType.class, null,
                (LinearRingPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends Polygon>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Polygon",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_Surface")
    public JAXBElement<? extends Polygon> createPolygon(Polygon value) {
        return new JAXBElement<PolygonType>(_Polygon_QNAME, PolygonType.class, null, (PolygonType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends PolygonProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "polygonProperty")
    public JAXBElement<? extends PolygonProperty> createPolygonProperty(PolygonProperty value) {
        return new JAXBElement<PolygonPropertyType>(_PolygonProperty_QNAME, PolygonPropertyType.class, null,
                (PolygonPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPoint>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "MultiPoint",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiPoint> createMultiPoint(MultiPoint value) {
        return new JAXBElement<MultiPointType>(_MultiPoint_QNAME, MultiPointType.class, null, (MultiPointType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPointProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "multiPointProperty")
    public JAXBElement<? extends MultiPointProperty> createMultiPointProperty(MultiPointProperty value) {
        return new JAXBElement<MultiPointPropertyType>(_MultiPointProperty_QNAME, MultiPointPropertyType.class, null,
                (MultiPointPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiLineString>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "MultiLineString",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiLineString> createMultiLineString(MultiLineString value) {
        return new JAXBElement<MultiLineStringType>(_MultiLineString_QNAME, MultiLineStringType.class, null,
                (MultiLineStringType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiLineStringProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "multiLineStringProperty")
    public JAXBElement<? extends MultiLineStringProperty> createMultiLineStringProperty(MultiLineStringProperty value) {
        return new JAXBElement<MultiLineStringPropertyType>(_MultiLineStringProperty_QNAME, MultiLineStringPropertyType.class,
                null, (MultiLineStringPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends AbstractRingProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "exterior")
    public JAXBElement<? extends AbstractRingProperty> createExterior(AbstractRingProperty value) {
        return new JAXBElement<AbstractRingPropertyType>(_Exterior_QNAME, AbstractRingPropertyType.class, null,
                (AbstractRingPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends AbstractRingProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "interior")
    public JAXBElement<? extends AbstractRingProperty> createInterior(AbstractRingProperty value) {
        return new JAXBElement<AbstractRingPropertyType>(_Interior_QNAME, AbstractRingPropertyType.class, null,
                (AbstractRingPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPolygon>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "MultiPolygon",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiPolygon> createMultiPolygon(MultiPolygon value) {
        return new JAXBElement<MultiPolygonType>(_MultiPolygon_QNAME, MultiPolygonType.class, null,
                (MultiPolygonType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPolygonProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "multiPolygonProperty")
    public JAXBElement<? extends MultiPolygonProperty> createMultiPolygonProperty(MultiPolygonProperty value) {
        return new JAXBElement<MultiPolygonPropertyType>(_MultiPolygonProperty_QNAME, MultiPolygonPropertyType.class,
                null, (MultiPolygonPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiGeometry>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "MultiGeometry",
            substitutionHeadNamespace = "http://www.opengis.net/gml",
            substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiGeometry> createMultiGeometry(MultiGeometry value) {
        return new JAXBElement<MultiGeometryType>(_MultiGeometry_QNAME, MultiGeometryType.class, null,
                (MultiGeometryType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiGeometryProperty>}
     */
    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
            name = "multiGeometryProperty")
    public JAXBElement<? extends MultiGeometryProperty> createMultiGeometryProperty(MultiGeometryProperty value) {
        return new JAXBElement<MultiGeometryPropertyType>(_MultiGeometryProperty_QNAME, MultiGeometryPropertyType.class,
                null, (MultiGeometryPropertyType) value);
    }

    /**
     * @param value
     * @return {@link JAXBElement<? extends AbstractGeometry>}
     */
    @Override
    public JAXBElement<? extends AbstractGeometry> createGeometry(AbstractGeometry value) {
        return new JAXBElement<AbstractGeometryType>(_Geometry_QNAME, AbstractGeometryType.class, null,
                (AbstractGeometryType) value);
    }

    /**
     * @return {@link Coord}
     */
    @Override
    public Coord createCoordType() {
        return new CoordType();
    }

    /**
     * @return {@link Coordinates}
     */
    @Override
    public Coordinates createCoordinatesType() {
        return new CoordinatesType();
    }

    /**
     * @return {@link DirectPosition}
     */
    @Override
    public DirectPosition createDirectPositionType() {
        return new DirectPositionType();
    }

    /**
     * @return {@link DirectPositionList}
     */
    @Override
    public DirectPositionList createDirectPositionListType() {
        return new DirectPositionListType();
    }

    /**
     * @return {@link Point}
     */
    @Override
    public Point createPointType() {
        return new PointType();
    }

    /**
     * @return {@link PointProperty
     * }
     */
    @Override
    public PointProperty createPointPropertyType() {
        return new PointPropertyType();
    }

    /**
     * @return {@link LineString}
     */
    @Override
    public LineString createLineStringType() {
        return new LineStringType();
    }

    /**
     * @return {@link LineStringProperty}
     */
    @Override
    public LineStringProperty createLineStringPropertyType() {
        return new LineStringPropertyType();
    }

    /**
     * @return {@link LinearRing}
     */
    @Override
    public LinearRing createLinearRingType() {
        return new LinearRingType();
    }

    /**
     * @return {@link LinearRingProperty}
     */
    @Override
    public LinearRingProperty createLinearRingPropertyType() {
        return new LinearRingPropertyType();
    }

    /**
     * @return {@link Polygon}
     */
    @Override
    public Polygon createPolygonType() {
        return new PolygonType();
    }

    /**
     * @return {@link PolygonProperty}
     */
    @Override
    public PolygonProperty createPolygonPropertyType() {
        return new PolygonPropertyType();
    }

    /**
     * @return {@link MultiPoint}
     */
    @Override
    public MultiPoint createMultiPointType() {
        return new MultiPointType();
    }

    /**
     * @return {@link MultiPointProperty}
     */
    @Override
    public MultiPointProperty createMultiPointPropertyType() {
        return new MultiPointPropertyType();
    }

    /**
     * @return {@link MultiLineString}
     */
    @Override
    public MultiLineString createMultiLineStringType() {
        return new MultiLineStringType();
    }

    /**
     * @return {@link MultiLineStringProperty}
     */
    @Override
    public MultiLineStringProperty createMultiLineStringPropertyType() {
        return new MultiLineStringPropertyType();
    }

    /**
     * @return {@link AbstractRingProperty}
     */
    @Override
    public AbstractRingProperty createAbstractRingPropertyType() {
        return new AbstractRingPropertyType();
    }

    /**
     * @return {@link MultiPolygon}
     */
    @Override
    public MultiPolygon createMultiPolygonType() {
        return new MultiPolygonType();
    }

    /**
     * @return {@link MultiPolygonProperty}
     */
    @Override
    public MultiPolygonProperty createMultiPolygonPropertyType() {
        return new MultiPolygonPropertyType();
    }

    /**
     * @return {@link MultiGeometry}
     */
    @Override
    public MultiGeometry createMultiGeometryType() {
        return new MultiGeometryType();
    }

    /**
     * @return {@link MultiGeometryProperty}
     */
    @Override
    public MultiGeometryProperty createMultiGeometryPropertyType() {
        return new MultiGeometryPropertyType();
    }

    /**
     * @return {@link GeometryProperty}
     */
    @Override
    public GeometryProperty createGeometryPropertyType() {
        return new GeometryPropertyType();
    }
}