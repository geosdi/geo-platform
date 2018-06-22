/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.coordinate;

import org.geojson.LngLatAlt;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonCoordinateWriter implements IGeoJsonCoordinateWriter {

    /**
     * @param coordinate
     * @return {@link LngLatAlt}
     */
    @Override
    public LngLatAlt buildPointCoordinate(Coordinate coordinate) {
        return new LngLatAlt(coordinate.x, coordinate.y);
    }

    /**
     * @param lineString
     * @return {@link List <LngLatAlt>}
     */
    @Override
    public List<LngLatAlt> buildLineStringCoordinate(LineString lineString) {
        return Arrays.stream(lineString.getCoordinates())
                .filter(coordinate -> coordinate != null)
                .map(coordinate -> buildPointCoordinate(coordinate))
                .collect(Collectors.toList());
    }

    /**
     * @param polygon
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<LngLatAlt> buildPolygonExteriorRing(Polygon polygon) {
        return buildLineStringCoordinate(polygon.getExteriorRing());
    }

    /**
     * @param polygon
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<List<LngLatAlt>> buildPolygonInteriorRing(Polygon polygon) {
        List<List<LngLatAlt>> coordinates = new ArrayList<>();
        for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
            coordinates.add(buildLineStringCoordinate(polygon.getInteriorRingN(i)));
        }
        return coordinates;
    }

    /**
     * @param multiPoint
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<LngLatAlt> buildMultiPointCoordinate(MultiPoint multiPoint) {
        List<LngLatAlt> coordinates = new ArrayList<>();
        for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
            coordinates.add(buildPointCoordinate(multiPoint.getGeometryN(i).getCoordinate()));
        }
        return coordinates;
    }

    /**
     * @param multiLineString
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<List<LngLatAlt>> buildMultiLineStringCoordinate(MultiLineString multiLineString) {
        List<List<LngLatAlt>> coordinates = new ArrayList<>();
        for (int i = 0; i < multiLineString.getNumGeometries(); i++) {
            coordinates.add(buildLineStringCoordinate((LineString) multiLineString.getGeometryN(i)));
        }
        return coordinates;
    }
}
