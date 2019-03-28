package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.responsibility.geojson;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.AbstractCurve;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class MultiCurveGeoJsonHandler {

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    public abstract void parseGeometryAsGeoJson(List<LineString> lines, AbstractCurve gmlGeometry) throws ParserException;

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    protected abstract void forwardParseGeometryAsGeoJson(List<LineString> lines, AbstractCurve gmlGeometry) throws ParserException;
}