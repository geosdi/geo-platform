package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.coordinate;

import com.vividsolutions.jts.geom.*;
import org.geojson.LngLatAlt;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGeoJsonCoordinateWriter {

    /**
     * @param coordinate
     * @return {@link LngLatAlt}
     */
    LngLatAlt buildPointCoordinate(Coordinate coordinate);

    /**
     * @param lineString
     * @return {@link List<LngLatAlt>}
     */
    List<LngLatAlt> buildLineStringCoordinate(LineString lineString);

    /**
     * @param polygon
     * @return {@link List<LngLatAlt>}
     */
    List<LngLatAlt> buildPolygonExteriorRing(Polygon polygon);

    /**
     * @param polygon
     * @return {@link List<LngLatAlt>}
     */
    List<LngLatAlt> buildPolygonInteriorRing(Polygon polygon);

    /**
     * @param multiPoint
     * @return {@link List<LngLatAlt>}
     */
    List<LngLatAlt> buildMultiPointCoordinate(MultiPoint multiPoint);

    /**
     * @param multiLineString
     * @return {@link List<LngLatAlt>}
     */
    List<List<LngLatAlt>> buildMultiLineStringCoordinate(MultiLineString multiLineString);
}
