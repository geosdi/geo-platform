package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.coordinate;

import com.vividsolutions.jts.geom.*;
import org.geojson.LngLatAlt;

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
