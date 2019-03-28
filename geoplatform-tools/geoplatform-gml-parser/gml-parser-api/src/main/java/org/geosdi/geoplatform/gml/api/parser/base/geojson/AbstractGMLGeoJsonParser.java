package org.geosdi.geoplatform.gml.api.parser.base.geojson;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.PropertyType;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractGMLGeoJsonParser<A extends AbstractGeometry, P extends PropertyType, GeoJson extends GeoJsonObject> implements GMLGeoJsonParser<A, P, GeoJson> {

    protected final AbstractGMLBaseSRSParser srsParser;

    /**
     * @param theSrsParser
     */
    protected AbstractGMLGeoJsonParser(@Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSrsParser) {
        checkNotNull(theSrsParser, "The GML SRS Parser must not be null.");
        this.srsParser = theSrsParser;
    }

    /**
     * @param gmlGeometry
     * @return {@link GeoJson}
     * @throws ParserException
     */
    @Override
    public GeoJson parseGeometryAsGeoJson(A gmlGeometry) throws ParserException {
        GeoJson geoJsonGeometry = canParseGeometryAsGeoJson(gmlGeometry);
        checkArgument(geoJsonGeometry != null, "The Parameter geoJsonGeometry must not be null.");
        geoJsonGeometry.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return geoJsonGeometry;
    }

    /**
     * @param gmlGeometry
     * @return {@link GeoJson}
     * @throws ParserException
     */
    protected abstract GeoJson canParseGeometryAsGeoJson(A gmlGeometry) throws ParserException;
}