/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gml.impl.v311.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.AbstractRingProperty;
import org.geosdi.geoplatform.gml.api.Coord;
import org.geosdi.geoplatform.gml.api.Coordinates;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.DirectPositionList;
import org.geosdi.geoplatform.gml.api.GeometryProperty;
import org.geosdi.geoplatform.gml.api.LineString;
import org.geosdi.geoplatform.gml.api.LineStringProperty;
import org.geosdi.geoplatform.gml.api.LinearRing;
import org.geosdi.geoplatform.gml.api.LinearRingProperty;
import org.geosdi.geoplatform.gml.api.MultiGeometry;
import org.geosdi.geoplatform.gml.api.MultiGeometryProperty;
import org.geosdi.geoplatform.gml.api.MultiLineString;
import org.geosdi.geoplatform.gml.api.MultiLineStringProperty;
import org.geosdi.geoplatform.gml.api.MultiPoint;
import org.geosdi.geoplatform.gml.api.MultiPointProperty;
import org.geosdi.geoplatform.gml.api.MultiPolygon;
import org.geosdi.geoplatform.gml.api.MultiPolygonProperty;
import org.geosdi.geoplatform.gml.api.Point;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.Polygon;
import org.geosdi.geoplatform.gml.api.PolygonProperty;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.xml.gml.v311.AbstractGeometryType;
import org.geosdi.geoplatform.xml.gml.v311.AbstractRingPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.CoordType;
import org.geosdi.geoplatform.xml.gml.v311.CoordinatesType;
import org.geosdi.geoplatform.xml.gml.v311.DirectPositionListType;
import org.geosdi.geoplatform.xml.gml.v311.DirectPositionType;
import org.geosdi.geoplatform.xml.gml.v311.GeometryPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.LineStringPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.LineStringType;
import org.geosdi.geoplatform.xml.gml.v311.LinearRingPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.LinearRingType;
import org.geosdi.geoplatform.xml.gml.v311.MultiGeometryPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.MultiGeometryType;
import org.geosdi.geoplatform.xml.gml.v311.MultiLineStringPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.MultiLineStringType;
import org.geosdi.geoplatform.xml.gml.v311.MultiPointPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.MultiPointType;
import org.geosdi.geoplatform.xml.gml.v311.MultiPolygonPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.MultiPolygonType;
import org.geosdi.geoplatform.xml.gml.v311.PointPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.PointType;
import org.geosdi.geoplatform.xml.gml.v311.PolygonPropertyType;
import org.geosdi.geoplatform.xml.gml.v311.PolygonType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRegistry
public class GMLObjectFactoryV311 extends AbstractGMLObjectFactory {

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pos")
    public JAXBElement<? extends DirectPosition> createPos(DirectPosition value) {
        return new JAXBElement<DirectPositionType>(_Pos_QNAME,
                DirectPositionType.class, null, (DirectPositionType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Point",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_GeometricPrimitive")
    public JAXBElement<? extends Point> createPoint(Point value) {
        return new JAXBElement<PointType>(_Point_QNAME, PointType.class, null,
                (PointType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "pointProperty")
    public JAXBElement<? extends PointProperty> createPointProperty(
            PointProperty value) {
        return new JAXBElement<PointPropertyType>(_PointProperty_QNAME,
                PointPropertyType.class, null, (PointPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "LineString",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_Curve")
    public JAXBElement<? extends LineString> createLineString(LineString value) {
        return new JAXBElement<LineStringType>(_LineString_QNAME,
                LineStringType.class, null, (LineStringType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "lineStringProperty")
    public JAXBElement<? extends LineStringProperty> createLineStringProperty(
            LineStringProperty value) {
        return new JAXBElement<LineStringPropertyType>(
                _LineStringProperty_QNAME,
                LineStringPropertyType.class, null,
                (LineStringPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "LinearRing",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_Ring")
    public JAXBElement<? extends LinearRing> createLinearRing(LinearRing value) {
        return new JAXBElement<LinearRingType>(_LinearRing_QNAME,
                LinearRingType.class, null, (LinearRingType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "linearRingProperty")
    public JAXBElement<? extends LinearRingProperty> createLinearRingProperty(
            LinearRingProperty value) {
        return new JAXBElement<LinearRingPropertyType>(
                _LinearRingProperty_QNAME,
                LinearRingPropertyType.class, null,
                (LinearRingPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Polygon",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_Surface")
    public JAXBElement<? extends Polygon> createPolygon(Polygon value) {
        return new JAXBElement<PolygonType>(_Polygon_QNAME, PolygonType.class,
                null, (PolygonType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "polygonProperty")
    public JAXBElement<? extends PolygonProperty> createPolygonProperty(
            PolygonProperty value) {
        return new JAXBElement<PolygonPropertyType>(_PolygonProperty_QNAME,
                PolygonPropertyType.class, null, (PolygonPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "MultiPoint",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiPoint> createMultiPoint(MultiPoint value) {
        return new JAXBElement<MultiPointType>(_MultiPoint_QNAME,
                MultiPointType.class, null, (MultiPointType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "multiPointProperty")
    public JAXBElement<? extends MultiPointProperty> createMultiPointProperty(
            MultiPointProperty value) {
        return new JAXBElement<MultiPointPropertyType>(
                _MultiPointProperty_QNAME,
                MultiPointPropertyType.class, null,
                (MultiPointPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "MultiLineString",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiLineString> createMultiLineString(
            MultiLineString value) {
        return new JAXBElement<MultiLineStringType>(_MultiLineString_QNAME,
                MultiLineStringType.class, null, (MultiLineStringType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "multiLineStringProperty")
    public JAXBElement<? extends MultiLineStringProperty> createMultiLineStringProperty(
            MultiLineStringProperty value) {
        return new JAXBElement<MultiLineStringPropertyType>(
                _MultiLineStringProperty_QNAME,
                MultiLineStringPropertyType.class,
                null, (MultiLineStringPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "exterior")
    public JAXBElement<? extends AbstractRingProperty> createExterior(
            AbstractRingProperty value) {
        return new JAXBElement<AbstractRingPropertyType>(_Exterior_QNAME,
                AbstractRingPropertyType.class, null,
                (AbstractRingPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "interior")
    public JAXBElement<? extends AbstractRingProperty> createInterior(
            AbstractRingProperty value) {
        return new JAXBElement<AbstractRingPropertyType>(_Interior_QNAME,
                AbstractRingPropertyType.class, null,
                (AbstractRingPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "MultiPolygon",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiPolygon> createMultiPolygon(
            MultiPolygon value) {
        return new JAXBElement<MultiPolygonType>(_MultiPolygon_QNAME,
                MultiPolygonType.class, null, (MultiPolygonType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "multiPolygonProperty")
    public JAXBElement<? extends MultiPolygonProperty> createMultiPolygonProperty(
            MultiPolygonProperty value) {
        return new JAXBElement<MultiPolygonPropertyType>(
                _MultiPolygonProperty_QNAME, MultiPolygonPropertyType.class,
                null, (MultiPolygonPropertyType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "MultiGeometry",
                    substitutionHeadNamespace = "http://www.opengis.net/gml",
                    substitutionHeadName = "_GeometricAggregate")
    public JAXBElement<? extends MultiGeometry> createMultiGeometry(
            MultiGeometry value) {
        return new JAXBElement<MultiGeometryType>(_MultiGeometry_QNAME,
                MultiGeometryType.class, null, (MultiGeometryType) value);
    }

    @Override
    @XmlElementDecl(namespace = "http://www.opengis.net/gml",
                    name = "multiGeometryProperty")
    public JAXBElement<? extends MultiGeometryProperty> createMultiGeometryProperty(
            MultiGeometryProperty value) {
        return new JAXBElement<MultiGeometryPropertyType>(
                _MultiGeometryProperty_QNAME, MultiGeometryPropertyType.class,
                null, (MultiGeometryPropertyType) value);
    }

    @Override
    public JAXBElement<? extends AbstractGeometry> createGeometry(
            AbstractGeometry value) {
        return new JAXBElement<AbstractGeometryType>(_Geometry_QNAME,
                AbstractGeometryType.class, null, (AbstractGeometryType) value);
    }

    @Override
    public Coord createCoordType() {
        return new CoordType();
    }

    @Override
    public Coordinates createCoordinatesType() {
        return new CoordinatesType();
    }

    @Override
    public DirectPosition createDirectPositionType() {
        return new DirectPositionType();
    }

    @Override
    public DirectPositionList createDirectPositionListType() {
        return new DirectPositionListType();
    }

    @Override
    public Point createPointType() {
        return new PointType();
    }

    @Override
    public PointProperty createPointPropertyType() {
        return new PointPropertyType();
    }

    @Override
    public LineString createLineStringType() {
        return new LineStringType();
    }

    @Override
    public LineStringProperty createLineStringPropertyType() {
        return new LineStringPropertyType();
    }

    @Override
    public LinearRing createLinearRingType() {
        return new LinearRingType();
    }

    @Override
    public LinearRingProperty createLinearRingPropertyType() {
        return new LinearRingPropertyType();
    }

    @Override
    public Polygon createPolygonType() {
        return new PolygonType();
    }

    @Override
    public PolygonProperty createPolygonPropertyType() {
        return new PolygonPropertyType();
    }

    @Override
    public MultiPoint createMultiPointType() {
        return new MultiPointType();
    }

    @Override
    public MultiPointProperty createMultiPointPropertyType() {
        return new MultiPointPropertyType();
    }

    @Override
    public MultiLineString createMultiLineStringType() {
        return new MultiLineStringType();
    }

    @Override
    public MultiLineStringProperty createMultiLineStringPropertyType() {
        return new MultiLineStringPropertyType();
    }

    @Override
    public AbstractRingProperty createAbstractRingPropertyType() {
        return new AbstractRingPropertyType();
    }

    @Override
    public MultiPolygon createMultiPolygonType() {
        return new MultiPolygonType();
    }

    @Override
    public MultiPolygonProperty createMultiPolygonPropertyType() {
        return new MultiPolygonPropertyType();
    }

    @Override
    public MultiGeometry createMultiGeometryType() {
        return new MultiGeometryType();
    }

    @Override
    public MultiGeometryProperty createMultiGeometryPropertyType() {
        return new MultiGeometryPropertyType();
    }

    @Override
    public GeometryProperty createGeometryPropertyType() {
        return new GeometryPropertyType();
    }
}
