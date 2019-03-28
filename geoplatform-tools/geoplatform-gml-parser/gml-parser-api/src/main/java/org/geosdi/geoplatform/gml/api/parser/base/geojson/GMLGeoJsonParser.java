package org.geosdi.geoplatform.gml.api.parser.base.geojson;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.PropertyType;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GMLGeoJsonParser<A extends AbstractGeometry, P extends PropertyType, GeoJson extends GeoJsonObject> extends AbstractParser {

    /**
     * @param gmlGeometry
     * @return {@link GeoJson}
     * @throws ParserException
     */
    GeoJson parseGeometryAsGeoJson(A gmlGeometry) throws ParserException;

    /**
     * @param propertyType
     * @return {@link GeoJson}
     * @throws ParserException
     */
    GeoJson parseGeometryAsGeoJson(P propertyType) throws ParserException;
}