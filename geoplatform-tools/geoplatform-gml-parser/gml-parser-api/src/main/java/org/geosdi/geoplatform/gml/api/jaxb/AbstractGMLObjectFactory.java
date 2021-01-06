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
package org.geosdi.geoplatform.gml.api.jaxb;

import org.geosdi.geoplatform.gml.api.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractGMLObjectFactory implements GMLObjectFactory {

    protected final static QName _Pos_QNAME = new QName("http://www.opengis.net/gml", "pos");
    protected final static QName _Point_QNAME = new QName("http://www.opengis.net/gml", "Point");
    protected final static QName _PointProperty_QNAME = new QName("http://www.opengis.net/gml", "pointProperty");
    protected final static QName _LineString_QNAME = new QName("http://www.opengis.net/gml", "LineString");
    protected final static QName _LineStringProperty_QNAME = new QName("http://www.opengis.net/gml", "lineStringProperty");
    protected final static QName _LinearRing_QNAME = new QName("http://www.opengis.net/gml", "LinearRing");
    protected final static QName _LinearRingProperty_QNAME = new QName("http://www.opengis.net/gml", "linearRingProperty");
    protected final static QName _Exterior_QNAME = new QName("http://www.opengis.net/gml", "exterior");
    protected final static QName _Interior_QNAME = new QName("http://www.opengis.net/gml", "interior");
    protected final static QName _Polygon_QNAME = new QName("http://www.opengis.net/gml", "Polygon");
    protected final static QName _PolygonProperty_QNAME = new QName("http://www.opengis.net/gml", "polygonProperty");
    protected final static QName _MultiPoint_QNAME = new QName("http://www.opengis.net/gml", "MultiPoint");
    protected final static QName _MultiPointProperty_QNAME = new QName("http://www.opengis.net/gml", "multiPointProperty");
    protected final static QName _MultiLineString_QNAME = new QName("http://www.opengis.net/gml", "MultiLineString");
    protected final static QName _MultiLineStringProperty_QNAME = new QName("http://www.opengis.net/gml", "multiLineStringProperty");
    protected final static QName _MultiPolygon_QNAME = new QName("http://www.opengis.net/gml", "MultiPolygon");
    protected final static QName _MultiPolygonProperty_QNAME = new QName("http://www.opengis.net/gml", "multiPolygonProperty");
    protected final static QName _MultiGeometry_QNAME = new QName("http://www.opengis.net/gml", "MultiGeometry");
    protected final static QName _MultiGeometryProperty_QNAME = new QName("http://www.opengis.net/gml", "multiGeometryProperty");
    protected final static QName _Geometry_QNAME = new QName("http://www.opengis.net/gml", "_Geometry");

    /**
     * @param value
     * @return {@link JAXBElement<? extends DirectPosition>}
     */
    public abstract JAXBElement<? extends DirectPosition> createPos(DirectPosition value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends Point>}
     */
    public abstract JAXBElement<? extends Point> createPoint(Point value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends PointProperty>}
     */
    public abstract JAXBElement<? extends PointProperty> createPointProperty(PointProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends LineString>}
     */
    public abstract JAXBElement<? extends LineString> createLineString(LineString value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends LineStringProperty>}
     */
    public abstract JAXBElement<? extends LineStringProperty> createLineStringProperty(LineStringProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends LinearRing>}
     */
    public abstract JAXBElement<? extends LinearRing> createLinearRing(LinearRing value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends LinearRingProperty>}
     */
    public abstract JAXBElement<? extends LinearRingProperty> createLinearRingProperty(LinearRingProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends Polygon>}
     */
    public abstract JAXBElement<? extends Polygon> createPolygon(Polygon value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends PolygonProperty>}
     */
    public abstract JAXBElement<? extends PolygonProperty> createPolygonProperty(PolygonProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPoint>}
     */
    public abstract JAXBElement<? extends MultiPoint> createMultiPoint(MultiPoint value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPointProperty>}
     */
    public abstract JAXBElement<? extends MultiPointProperty> createMultiPointProperty(MultiPointProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiLineString>}
     */
    public abstract JAXBElement<? extends MultiLineString> createMultiLineString(MultiLineString value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiLineStringProperty>}
     */
    public abstract JAXBElement<? extends MultiLineStringProperty> createMultiLineStringProperty(MultiLineStringProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends AbstractRingProperty>}
     */
    public abstract JAXBElement<? extends AbstractRingProperty> createExterior(AbstractRingProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends AbstractRingProperty>}
     */
    public abstract JAXBElement<? extends AbstractRingProperty> createInterior(AbstractRingProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPolygon>}
     */
    public abstract JAXBElement<? extends MultiPolygon> createMultiPolygon(MultiPolygon value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiPolygonProperty>}
     */
    public abstract JAXBElement<? extends MultiPolygonProperty> createMultiPolygonProperty(MultiPolygonProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiGeometry>}
     */
    public abstract JAXBElement<? extends MultiGeometry> createMultiGeometry(MultiGeometry value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends MultiGeometryProperty>}
     */
    public abstract JAXBElement<? extends MultiGeometryProperty> createMultiGeometryProperty(MultiGeometryProperty value);

    /**
     * @param value
     * @return {@link JAXBElement<? extends AbstractGeometry>}
     */
    public abstract JAXBElement<? extends AbstractGeometry> createGeometry(AbstractGeometry value);
}