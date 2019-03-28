package org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.responsibility.geojson;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.LineStringSegment;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractCurveGeoJsonHandler {

    /**
     * @param lineStringSegment
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    public abstract LineString parseGeometryAsGeoJson(LineStringSegment lineStringSegment, CoordinateBaseParser parser) throws ParserException;

    /**
     * @param lineStringSegment
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    protected abstract LineString forwardParseGeometryAsGeoJson(LineStringSegment lineStringSegment, CoordinateBaseParser parser) throws ParserException;
}
