/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gml.api.parser.jts.coordinate;

import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.DirectPositionList;
import org.geosdi.geoplatform.gml.api.jaxb.GMLObjectFactory;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSCoordinateParser implements CoordinateParser {

    private final GMLObjectFactory gmlObjectFactory;

    /**
     * @param theObjectFactory
     */
    public JTSCoordinateParser(GMLObjectFactory theObjectFactory) {
        this.gmlObjectFactory = theObjectFactory;
    }

    /**
     * @param coordinate
     * @return {@link DirectPosition}
     */
    @Override
    public DirectPosition parseCoordinate(Coordinate coordinate) {
        checkNotNull(coordinate, "The Coordinate must not be null.");
        DirectPosition directPos = gmlObjectFactory.createDirectPositionType();
        directPos.getValue().add(coordinate.x);
        directPos.getValue().add(coordinate.y);
        if (!Double.isNaN(coordinate.z)) {
            directPos.getValue().add(coordinate.z);
        }
        return directPos;
    }

    /**
     * @param coordinates
     * @return {@link List<DirectPosition>}
     */
    @Override
    public List<DirectPosition> parseCoordinates(Coordinate[] coordinates) {
        checkNotNull(coordinates, "The Coordinate array must not be null.");
        List<DirectPosition> directPositions = new ArrayList<DirectPosition>(coordinates.length);
        for (int i = 0; i < coordinates.length; i++) {
            directPositions.add(this.parseCoordinate(coordinates[i]));
        }
        return directPositions;
    }

    /**
     * @param coordinates
     * @return {@link DirectPositionList}
     */
    @Override
    public DirectPositionList parseCoordinates(List<Coordinate> coordinates) {
        checkNotNull(coordinates, "The Coordinate List must not be null.");
        DirectPositionList directPositionList = gmlObjectFactory.createDirectPositionListType();
        for (Coordinate coordinate : coordinates) {
            directPositionList.getValue().add(coordinate.x);
            directPositionList.getValue().add(coordinate.y);
            if (!Double.isNaN(coordinate.z)) {
                directPositionList.getValue().add(coordinate.z);
            }
        }
        return directPositionList;
    }
}