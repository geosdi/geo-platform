package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.coordinate;

import com.vividsolutions.jts.geom.*;
import org.geojson.LngLatAlt;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IJTSCoordinateWriter {

    /**
     * @param lngLatAlt
     * @return {@link Coordinate}
     */
    Coordinate buildJTSCoordinate(LngLatAlt lngLatAlt);

    /**
     * @param lngLatAlts
     * @return {@link Coordinate[]}
     */
    Coordinate[] buildJTSCoordinates(List<LngLatAlt> lngLatAlts);

    /**
     * @param lngLatAlts
     * @return {@link LinearRing}
     */
    LinearRing buildJTSLinearRing(List<LngLatAlt> lngLatAlts);

    /**
     * @param lists
     * @return {@link LinearRing[]}
     */
    LinearRing[] buildJTSLinearRings(List<List<LngLatAlt>> lists);

    /**
     * @param lngLatAlts
     * @return {@link Point[]}
     */
    Point[] buildJTSPoints(List<LngLatAlt> lngLatAlts);

    /**
     * @param lists
     * @return {@link LineString[]}
     */
    LineString[] buildJTSLineStrings(List<List<LngLatAlt>> lists);

    /**
     * @param lists
     * @return {@link Coordinate[]}
     */
    Coordinate[] buildJTSPolygonCoordinates(List<List<LngLatAlt>> lists);

    /**
     * @param lists
     * @return {@link Polygon[]}
     */
    Polygon[] buildJTSPolygons(List<List<List<LngLatAlt>>> lists);
}
