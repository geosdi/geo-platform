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
package org.geosdi.geoplatform.support.wfs.feature.geometry;

import org.locationtech.jts.geom.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GeometryBinding {

    private static final Map<String, Class> GML_GEOMETRY_BINDING = new HashMap<String, Class>();

    static {
        GML_GEOMETRY_BINDING.put("GeometryPropertyType", Geometry.class);

        GML_GEOMETRY_BINDING.put("MultiPoint", MultiPoint.class);
        GML_GEOMETRY_BINDING.put("MultiPointPropertyType", MultiPoint.class);

        GML_GEOMETRY_BINDING.put("Point", Point.class);
        GML_GEOMETRY_BINDING.put("PointPropertyType", Point.class);

        GML_GEOMETRY_BINDING.put("Curve", LineString.class);
        GML_GEOMETRY_BINDING.put("LineString", LineString.class);
        GML_GEOMETRY_BINDING.put("LineStringPropertyType", LineString.class);
        GML_GEOMETRY_BINDING.put("CurvePropertyType", LineString.class);

        GML_GEOMETRY_BINDING.put("MultiGeometry", GeometryCollection.class);
        GML_GEOMETRY_BINDING.put("MultiGeometryPropertyType", GeometryCollection.class);

        GML_GEOMETRY_BINDING.put("CompositeCurve", MultiLineString.class);
        GML_GEOMETRY_BINDING.put("CompositeCurvePropertyType", MultiLineString.class);
        GML_GEOMETRY_BINDING.put("MultiLineStringPropertyType", MultiLineString.class);
        GML_GEOMETRY_BINDING.put("MultiLineString", MultiLineString.class);

        GML_GEOMETRY_BINDING.put("Envelope", Envelope.class);
        GML_GEOMETRY_BINDING.put("EnvelopePropertyType", Envelope.class);

        GML_GEOMETRY_BINDING.put("PolyHedralSurface", MultiPolygon.class);
        GML_GEOMETRY_BINDING.put("PolyHedralSurfacePropertyType", MultiPolygon.class);
        GML_GEOMETRY_BINDING.put("MultiSurfacePropertyType", MultiPolygon.class);
        GML_GEOMETRY_BINDING.put("MultiPolygonPropertyType", MultiPolygon.class);

        GML_GEOMETRY_BINDING.put("Polygon", Polygon.class);
        GML_GEOMETRY_BINDING.put("PolygonPropertyType", Polygon.class);
        GML_GEOMETRY_BINDING.put("SurfacePropertyType", Polygon.class);

        GML_GEOMETRY_BINDING.put("Ring", LinearRing.class);
        GML_GEOMETRY_BINDING.put("RingPropertyType", LinearRing.class);
        GML_GEOMETRY_BINDING.put("LinearRing", LinearRing.class);
        GML_GEOMETRY_BINDING.put("LinearRingPropertyType", LinearRing.class);
    }

    private GeometryBinding() {
    }

    public static Class getGMLGeometry(String type) {
        Class typeClass = GML_GEOMETRY_BINDING.get(type);
        if (typeClass == null) {
            throw new IllegalArgumentException("Type \"" + type + "\" is not valid.");
        }
        return typeClass;
    }

    public static boolean isGMLGeometricType(String type) {
        return GML_GEOMETRY_BINDING.containsKey(type);
    }
}
