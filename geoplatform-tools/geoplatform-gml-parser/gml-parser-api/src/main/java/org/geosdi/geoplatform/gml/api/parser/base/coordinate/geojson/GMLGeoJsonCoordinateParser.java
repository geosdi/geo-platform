package org.geosdi.geoplatform.gml.api.parser.base.coordinate.geojson;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.Coord;
import org.geosdi.geoplatform.gml.api.Coordinates;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.DirectPositionList;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLGeoJsonCoordinateParser implements GeoJsonCoordinateParser {

    /**
     * @param directPosition
     * @return {@link LngLatAlt}
     * @throws ParserException
     */
    @Override
    public LngLatAlt parseCoordinateAsGeoJson(@Nonnull(when = NEVER) DirectPosition directPosition) throws ParserException {
        checkArgument(directPosition != null, "The Parameter directPosition must not be null.");
        List<Double> value = directPosition.getValue();
        int count = value.size();

        switch (count) {
            case 2:
                double x2 = value.get(0);
                double y2 = value.get(1);
                return new LngLatAlt(x2, y2);
            case 3:
                double x3 = value.get(0);
                double y3 = value.get(1);
                double z3 = value.get(2);
                return new LngLatAlt(x3, y3, z3);
            default:
                throw new ParserException("Direct position type is expected to have 2 or 3 items.");
        }
    }

    /**
     * @param directPositionList
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    @Override
    public LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) DirectPositionList directPositionList) throws ParserException {
        checkArgument(directPositionList != null, "The Parameter directPositionList must not be null.");
        int dimensions = directPositionList.isSetSrsDimension() ? directPositionList.getSrsDimension().intValue() : 2;

        if (dimensions < 2 || dimensions > 3) {
            throw new ParserException("Only two or three dimensional coordinates are supported.");
        }

        List<Double> values = directPositionList.getValue();
        if (values.size() % dimensions != 0) {
            throw new ParserException("The list contains a number of incorrect entries.");
        }

        LngLatAlt[] coordinates = new LngLatAlt[values.size() / dimensions];
        for (int index = 0; index < values.size() / dimensions; index++) {
            if (dimensions == 2) {
                coordinates[index] = new LngLatAlt(values.get(index * dimensions), values.get(index * dimensions + 1));
            } else if (dimensions == 3) {
                coordinates[index] = new LngLatAlt(values.get(index * dimensions), values.get(index * dimensions + 1),
                        values.get(index * dimensions + 2));
            }
        }
        return coordinates;
    }

    /**
     * @param coord
     * @return {@link LngLatAlt}
     * @throws ParserException
     */
    @Override
    public LngLatAlt parseCoordinateAsGeoJson(@Nonnull(when = NEVER) Coord coord) throws ParserException {
        checkArgument(coord != null, "The Parameter coord must not be null.");
        if (coord.isSetX() && coord.isSetY() && !coord.isSetZ()) {
            return new LngLatAlt(coord.getX().doubleValue(), coord.getY().doubleValue());
        } else if (coord.isSetX() && coord.isSetY() && coord.isSetZ()) {
            return new LngLatAlt(coord.getX().doubleValue(), coord.getY().doubleValue(), coord.getZ().doubleValue());
        } else {
            throw new ParserException("X, Y or X, Y, Z values are required.");
        }
    }

    /**
     * @param coordinates
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    @Override
    public LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) Coordinates coordinates) throws ParserException {
        checkArgument(coordinates != null, "The Parameter coordinates must not be null.");
        return parseCoordinatesAsGeoJson(coordinates.getValue(), coordinates.getDecimal(), coordinates.getCs(), coordinates.getTs());
    }

    /**
     * @param value
     * @param decimalSeparator
     * @param cs
     * @param ts
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    @Override
    public LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) String value, String decimalSeparator, String cs, String ts) throws ParserException {
        checkArgument(value != null, "The Parameter value must not be null.");
        String tupleSeparator = ts == null ? " " : ts;
        String[] tuples = value.split(tupleSeparator);
        LngLatAlt[] coordinates = new LngLatAlt[tuples.length];
        for (int index = 0; index < tuples.length; index++) {
            coordinates[index] = parseCoordinateAsGeoJson(tuples[index], decimalSeparator, cs);
        }
        return coordinates;
    }

    /**
     * @param value
     * @param decimalSeparator
     * @param cs
     * @return {@link LngLatAlt}
     * @throws ParserException
     */
    @Override
    public LngLatAlt parseCoordinateAsGeoJson(@Nonnull(when = NEVER) String value, String decimalSeparator, String cs) throws ParserException {
        checkArgument(value != null, "The Parameter value must not be null.");
        String coordinateSeparator = cs == null ? "," : cs;
        String[] coordinatesString = value.split(coordinateSeparator);
        double[] coordinateDouble = new double[coordinatesString.length];
        for (int index = 0; index < coordinatesString.length; index++) {
            coordinateDouble[index] = parseCoordinateAsGeoJson(coordinatesString[index], decimalSeparator);
        }
        switch (coordinateDouble.length) {
            case 2:
                return new LngLatAlt(coordinateDouble[0], coordinateDouble[1]);
            case 3:
                return new LngLatAlt(coordinateDouble[0], coordinateDouble[1], coordinateDouble[2]);
            default:
                throw new ParserException("We must have only two or three coordinates.");
        }
    }

    /**
     * @param coordinates
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    @Override
    public LngLatAlt[] parseCoordinateAsGeoJson(Coordinates coordinates) throws ParserException {
        checkArgument(coordinates != null, "The Parameter coordinates must not be null.");
        return this.parseCoordinatesAsGeoJson(coordinates.getValue(), coordinates.getDecimal(), coordinates.getCs(), coordinates.getTs());
    }

    /**
     * @param positions
     * @return {@link LngLatAlt[]}
     * @throws ParserException
     */
    @Override
    public LngLatAlt[] parseCoordinatesAsGeoJson(@Nonnull(when = NEVER) List<? extends DirectPosition> positions) throws ParserException {
        checkNotNull(positions, "The List of Positions must not be null.");
        List<LngLatAlt> coordinates = new ArrayList<>(positions.size());
        for (DirectPosition directPosition : positions) {
            coordinates.add(parseCoordinateAsGeoJson(directPosition));
        }
        return coordinates.toArray(new LngLatAlt[coordinates.size()]);
    }

    /**
     * @param value
     * @param decimalSeparator
     * @return {@link Double}
     * @throws ParserException
     */
    @Override
    public double parseCoordinateAsGeoJson(@Nonnull(when = NEVER) String value, String decimalSeparator) throws ParserException {
        checkArgument(value != null, "The Parameter value must not be null.");
        String ds = decimalSeparator == null ? "." : decimalSeparator;
        try {
            return Double.parseDouble(value.replace(ds, "."));
        } catch (NumberFormatException ex) {
            throw new ParserException(ex);
        }
    }
}