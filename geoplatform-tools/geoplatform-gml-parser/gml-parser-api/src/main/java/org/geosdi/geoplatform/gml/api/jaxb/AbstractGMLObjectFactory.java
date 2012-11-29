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
package org.geosdi.geoplatform.gml.api.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.gml.api.AbstractRingProperty;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
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

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractGMLObjectFactory implements GMLObjectFactory {

    public final static QName _Pos_QNAME = new QName(
            "http://www.opengis.net/gml", "pos");
    public final static QName _Point_QNAME = new QName(
            "http://www.opengis.net/gml", "Point");
    public final static QName _PointProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "pointProperty");
    public final static QName _LineString_QNAME = new QName(
            "http://www.opengis.net/gml", "LineString");
    public final static QName _LineStringProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "lineStringProperty");
    public final static QName _LinearRing_QNAME = new QName(
            "http://www.opengis.net/gml", "LinearRing");
    public final static QName _LinearRingProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "linearRingProperty");
    public final static QName _Exterior_QNAME = new QName(
            "http://www.opengis.net/gml", "exterior");
    public final static QName _Interior_QNAME = new QName(
            "http://www.opengis.net/gml", "interior");
    public final static QName _Polygon_QNAME = new QName(
            "http://www.opengis.net/gml", "Polygon");
    public final static QName _PolygonProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "polygonProperty");
    public final static QName _MultiPoint_QNAME = new QName(
            "http://www.opengis.net/gml", "MultiPoint");
    public final static QName _MultiPointProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "multiPointProperty");
    public final static QName _MultiLineString_QNAME = new QName(
            "http://www.opengis.net/gml", "MultiLineString");
    public final static QName _MultiLineStringProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "multiLineStringProperty");
    public final static QName _MultiPolygon_QNAME = new QName(
            "http://www.opengis.net/gml", "MultiPolygon");
    public final static QName _MultiPolygonProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "multiPolygonProperty");
    public final static QName _MultiGeometry_QNAME = new QName(
            "http://www.opengis.net/gml", "MultiGeometry");
    public final static QName _MultiGeometryProperty_QNAME = new QName(
            "http://www.opengis.net/gml", "multiGeometryProperty");
    public final static QName _Geometry_QNAME = new QName(
            "http://www.opengis.net/gml", "_Geometry");

    public abstract JAXBElement<DirectPosition> createPos(DirectPosition value);

    public abstract JAXBElement<Point> createPoint(Point value);

    public abstract JAXBElement<PointProperty> createPointProperty(
            PointProperty value);

    public abstract JAXBElement<LineString> createLineString(LineString value);

    public abstract JAXBElement<LineStringProperty> createLineStringProperty(
            LineStringProperty value);

    public abstract JAXBElement<LinearRing> createLinearRing(LinearRing value);

    public abstract JAXBElement<LinearRingProperty> createLinearRingProperty(
            LinearRingProperty value);

    public abstract JAXBElement<Polygon> createPolygon(Polygon value);

    public abstract JAXBElement<PolygonProperty> createPolygonProperty(
            PolygonProperty value);

    public abstract JAXBElement<MultiPoint> createMultiPoint(MultiPoint value);

    public abstract JAXBElement<MultiPointProperty> createMultiPointProperty(
            MultiPointProperty value);

    public abstract JAXBElement<MultiLineString> createMultiLineString(
            MultiLineString value);

    public abstract JAXBElement<MultiLineStringProperty> createMultiLineStringProperty(
            MultiLineStringProperty value);

    public abstract JAXBElement<AbstractRingProperty> createExterior(
            AbstractRingProperty value);

    public abstract JAXBElement<AbstractRingProperty> createInterior(
            AbstractRingProperty value);

    public abstract JAXBElement<MultiPolygon> createMultiPolygon(
            MultiPolygon value);

    public abstract JAXBElement<MultiPolygonProperty> createMultiPolygonProperty(
            MultiPolygonProperty value);

    public abstract JAXBElement<MultiGeometry> createMultiGeometry(
            MultiGeometry value);

    public abstract JAXBElement<MultiGeometryProperty> createMultiGeometryProperty(
            MultiGeometryProperty value);

    public abstract JAXBElement<AbstractGeometry> createGeometry(
            AbstractGeometry value);
}
