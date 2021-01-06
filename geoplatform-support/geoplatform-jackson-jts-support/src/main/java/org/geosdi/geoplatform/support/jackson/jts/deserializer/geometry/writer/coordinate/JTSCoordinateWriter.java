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
package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.coordinate;

import org.geojson.LngLatAlt;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSCoordinateWriter implements IJTSCoordinateWriter {

    private final GeometryFactory geometryFactory;

    public JTSCoordinateWriter(GeometryFactory theGeometryFactory) {
        this.geometryFactory = theGeometryFactory;
    }

    /**
     * @param lngLatAlt
     * @return {@link Coordinate}
     */
    @Override
    public Coordinate buildJTSCoordinate(LngLatAlt lngLatAlt) {
        return new Coordinate(lngLatAlt.getLongitude(), lngLatAlt.getLatitude());
    }

    /**
     * @param lngLatAlts
     * @return {@link Coordinate[]}
     */
    @Override
    public Coordinate[] buildJTSCoordinates(List<LngLatAlt> lngLatAlts) {
        return lngLatAlts.stream().filter(lngLatAlt -> lngLatAlt != null)
                .map(lngLatAlt -> buildJTSCoordinate(lngLatAlt))
                .toArray(size -> new Coordinate[size]);
    }

    /**
     * @param lngLatAlts
     * @return {@link LinearRing}
     */
    @Override
    public LinearRing buildJTSLinearRing(List<LngLatAlt> lngLatAlts) {
        return this.geometryFactory.createLinearRing(lngLatAlts.stream()
                .filter(lngLatAlt -> lngLatAlt != null)
                .map(lngLatAlt -> buildJTSCoordinate(lngLatAlt))
                .toArray(size -> new Coordinate[size]));
    }

    /**
     * @param lists
     * @return {@link LinearRing[]}
     */
    @Override
    public LinearRing[] buildJTSLinearRings(List<List<LngLatAlt>> lists) {
        return lists.stream().map(list -> buildJTSCoordinates(list))
                .map(coordinates -> this.geometryFactory.createLinearRing(coordinates))
                .toArray(size -> new LinearRing[size]);
    }

    /**
     * @param lngLatAlts
     * @return {@link Point[]}
     */
    @Override
    public Point[] buildJTSPoints(List<LngLatAlt> lngLatAlts) {
        return lngLatAlts.stream().filter(lngLatAlt -> lngLatAlt != null)
                .map(lngLatAlt -> buildJTSCoordinate(lngLatAlt))
                .map(coordinate -> this.geometryFactory.createPoint(coordinate))
                .toArray(size -> new Point[size]);
    }

    /**
     * @param lists
     * @return {@link LineString[]}
     */
    @Override
    public LineString[] buildJTSLineStrings(List<List<LngLatAlt>> lists) {
        return lists.stream().map(list -> buildJTSCoordinates(list))
                .map(coordinates -> this.geometryFactory.createLineString(coordinates))
                .toArray(size -> new LineString[size]);
    }

    /**
     * @param lists
     * @return {@link Coordinate[]}
     */
    @Override
    public Coordinate[] buildJTSPolygonCoordinates(List<List<LngLatAlt>> lists) {
        return lists.stream().map(list -> buildJTSCoordinates(list))
                .toArray(size -> new Coordinate[size]);
    }

    /**
     * @param lists
     * @return {@link Polygon[]}
     */
    @Override
    public Polygon[] buildJTSPolygons(List<List<List<LngLatAlt>>> lists) {
        List<Polygon> polygons = new ArrayList<>(lists.size());
        for (List<List<LngLatAlt>> list : lists) {
            LinearRing shell = this.buildJTSLinearRing(list.get(0));
            LinearRing[] holes = this.buildJTSLinearRings(list.subList(1, list.size()));
            polygons.add(this.geometryFactory.createPolygon(shell, holes));
        }
        return polygons.toArray(new Polygon[polygons.size()]);
    }
}
