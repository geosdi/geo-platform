package org.geosdi.geoplatform.gml.api.parser.base.coordinate.geojson;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.Coord;
import org.geosdi.geoplatform.gml.api.Coordinates;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.DirectPositionList;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoJsonCoordinateParser extends AbstractParser {

    /**
     * @param directPosition
     * @return {@link LngLatAlt}
     * @throws ParserException
     */
    LngLatAlt parseCoordinateAsGeoJson(@Nonnull(when = NEVER) DirectPosition directPosition) throws ParserException;

    /**
     * @param directPositionList
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) DirectPositionList directPositionList) throws ParserException;

    /**
     * @param coord
     * @return {@link LngLatAlt}
     * @throws ParserException
     */
    LngLatAlt parseCoordinateAsGeoJson(@Nonnull(when = NEVER) Coord coord) throws ParserException;

    /**
     * @param coordinates
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) Coordinates coordinates) throws ParserException;

    /**
     * @param value
     * @param decimalSeparator
     * @param cs
     * @param ts
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) String value, String decimalSeparator, String cs, String ts) throws ParserException;

    /**
     * @param value
     * @param decimalSeparator
     * @param cs
     * @return {@link LngLatAlt}
     * @throws ParserException
     */
    LngLatAlt parseCoordinateAsGeoJson(@Nonnull(when = NEVER) String value, String decimalSeparator, String cs) throws ParserException;

    /**
     * @param coordinates
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    LngLatAlt[] parseCoordinateAsGeoJson(Coordinates coordinates) throws ParserException;

    /**
     * @param positions
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) List<? extends DirectPosition> positions) throws ParserException;

    /**
     * @param value
     * @param decimalSeparator
     * @return {@link Double}
     * @throws ParserException
     */
    double parseCoordinateAsGeoJson(@Nonnull(when = NEVER) String value, String decimalSeparator) throws ParserException;
}