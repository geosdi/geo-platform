package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.coordinate;

import com.vividsolutions.jts.geom.*;
import org.geojson.LngLatAlt;

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
