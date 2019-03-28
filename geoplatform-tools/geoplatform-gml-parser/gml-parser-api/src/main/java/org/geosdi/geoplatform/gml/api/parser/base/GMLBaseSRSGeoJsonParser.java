package org.geosdi.geoplatform.gml.api.parser.base;

import org.geojson.Crs;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GMLBaseSRSGeoJsonParser {

    /**
     * @param gmlGeometry
     * @return {@link Crs}
     * @throws ParserException
     */
    Crs parseSRS(@Nonnull(when = NEVER) AbstractGeometry gmlGeometry) throws ParserException;
}