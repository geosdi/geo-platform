/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gml.api.parser.base.coordinate;

import com.vividsolutions.jts.geom.Coordinate;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.geosdi.geoplatform.gml.api.Coord;
import org.geosdi.geoplatform.gml.api.Coordinates;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.DirectPositionList;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLCoordinateBaseParser implements CoordinateBaseParser {

    @Override
    public Coordinate parseCoordinate(DirectPosition directPosition) throws ParserException {
        List<Double> value = directPosition.getValue();
        int count = value.size();
        if (count == 2) {
            double x = value.get(0).doubleValue();
            double y = value.get(1).doubleValue();
            return new Coordinate(x, y);
        } else if (count == 3) {
            double x = value.get(0).doubleValue();
            double y = value.get(1).doubleValue();
            double z = value.get(2).doubleValue();
            return new Coordinate(x, y, z);

        } else {
            throw new ParserException("Direct position type "
                    + "is expected to have 2 or 3 items.");
        }
    }

    @Override
    public Coordinate[] parseCoordinates(DirectPositionList directPositionList)
            throws ParserException {
        int dimensions = directPositionList.isSetSrsDimension() ? directPositionList
                .getSrsDimension().intValue() : 2;

        if (dimensions < 2 || dimensions > 3) {
            throw new ParserException("Only two or three dimensional "
                    + "coordinates are supported.");
        }

        List<Double> values = directPositionList.getValue();
        if (values.size() % dimensions != 0) {
            throw new ParserException("The list contains a number "
                    + "of incorrect entries.");
        }

        final Coordinate[] coordinates = new Coordinate[values.size()
                / dimensions];
        for (int index = 0; index < values.size() / dimensions; index++) {
            if (dimensions == 2) {
                coordinates[index] = new Coordinate(values.get(index
                        * dimensions), values.get(index * dimensions + 1));
            } else if (dimensions == 3) {
                coordinates[index] = new Coordinate(values.get(index
                        * dimensions), values.get(index * dimensions + 1),
                        values.get(index * dimensions + 2));
            }
        }
        return coordinates;
    }

    @Override
    public Coordinate parseCoordinate(Coord coord) throws ParserException {
        if (coord.isSetX() && coord.isSetY() && !coord.isSetZ()) {
            return new Coordinate(coord.getX().doubleValue(), coord
                    .getY().doubleValue());
        } else if (coord.isSetX() && coord.isSetY()
                && coord.isSetZ()) {
            return new Coordinate(coord.getX().doubleValue(), coord
                    .getY().doubleValue(), coord.getZ().doubleValue());

        } else {
            throw new ParserException("X, Y or X, Y, Z "
                    + "values are required.");
        }
    }

    @Override
    public Coordinate[] parseCoordinates(Coordinates coordinates) throws ParserException {
        Coordinate[] coords = parseCoordinates(coordinates.getValue(),
                coordinates.getDecimal(),
                coordinates.getCs(), coordinates.getTs());
        return coords;
    }

    @Override
    public Coordinate[] parseCoordinates(String value,
            String decimalSeparator,
            String cs,
            String ts) throws ParserException {

        String tupleSeparator = ts == null ? " " : ts;

        String[] tuples = StringUtils.split(value, tupleSeparator);

        Coordinate[] coordinatesArray = new Coordinate[tuples.length];
        for (int index = 0; index < tuples.length; index++) {
            coordinatesArray[index] = parseCoordinate(tuples[index],
                    decimalSeparator, cs);
        }
        return coordinatesArray;
    }

    @Override
    public Coordinate parseCoordinate(String value,
            String decimalSeparator,
            String cs) throws ParserException {

        String coordinateSeparator = cs == null ? "," : cs;

        String[] coordinatesString = StringUtils.split(value,
                coordinateSeparator);

        double[] coordinateDouble = new double[coordinatesString.length];
        for (int index = 0; index < coordinatesString.length; index++) {
            coordinateDouble[index] = parseCoordinate(coordinatesString[index],
                    decimalSeparator);
        }
        if (coordinateDouble.length == 2) {
            return new Coordinate(coordinateDouble[0],
                    coordinateDouble[1]);
        } else if (coordinateDouble.length == 3) {
            return new Coordinate(coordinateDouble[0],
                    coordinateDouble[1], coordinateDouble[2]);

        } else {
            throw new ParserException("We must have only two or "
                    + "three coordinates.");
        }
    }

    @Override
    public double parseCoordinate(String value,
            String decimalSeparator) throws ParserException {
        
        String ds = decimalSeparator == null ? "." : decimalSeparator;

        try {

            return Double.parseDouble(value.replace(ds, "."));

        } catch (NumberFormatException ex) {
            throw new ParserException(ex);
        }
    }
}
