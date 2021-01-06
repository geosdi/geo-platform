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
package org.geosdi.geoplatform.gui.client.widget.map.utility;

import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.LineString;
import org.gwtopenmaps.openlayers.client.geometry.MultiLineString;
import org.gwtopenmaps.openlayers.client.geometry.MultiPolygon;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.geometry.Polygon;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GeoPlatformSRSConverter {

    /**
     * Convert Geometry Polygon SRS from Projection Source to Dest This
     * Operation is possible but remember to add PROJ4 in your project to have
     * support for conversion that you want to do.
     *
     * @param geom
     * @param source
     * @param dest
     * @return
     */
    public static String polygonWKTConverter(Geometry geom, Projection source,
            Projection dest) {

        Polygon pol = Polygon.narrowToPolygon(geom.getJSObject());
        pol.transform(source, dest);

        return pol.toString();
    }

    /**
     * Convert Geometry LineString SRS from Projection Source to Dest This
     * Operation is possible but remember to add PROJ4 in your project to have
     * support for conversion that you want to do.
     *
     * @param geom
     * @param source
     * @param dest
     * @return
     */
    public static String lineWKTConverter(Geometry geom, Projection source,
            Projection dest) {

        LineString line = LineString.narrowToLineString(geom.getJSObject());
        line.transform(source, dest);

        return line.toString();
    }

    /**
     * Convert Geometry Point SRS from Projection Source to Dest Operation is
     * possible but remember to add PROJ4 in your project to have support for
     * conversion that you want to do.
     *
     * @param geom
     * @param source
     * @param dest
     * @return
     */
    public static String pointWKTConverter(Geometry geom, Projection source,
            Projection dest) {

        Point point = Point.narrowToPoint(geom.getJSObject());
        point.transform(source, dest);

        return point.toString();
    }

    /**
     *
     * @param geom
     * @param source
     * @param dest
     * @return
     */
    public static String multiPolygonWKTConverter(Geometry geom,
            Projection source, Projection dest) {

        MultiPolygon multiPol = MultiPolygon.narrowToMultiPolygon(geom
                .getJSObject());
        multiPol.transform(source, dest);

        return multiPol.toString();
    }

    /**
     *
     * @param geom
     * @param source
     * @param dest
     * @return
     */
    public static String multiLineWKTConverter(Geometry geom,
            Projection source, Projection dest) {

        MultiLineString multiLine = MultiLineString.narrowToMultiLineString(geom
                .getJSObject());
        multiLine.transform(source, dest);

        return multiLine.toString();
    }
}
