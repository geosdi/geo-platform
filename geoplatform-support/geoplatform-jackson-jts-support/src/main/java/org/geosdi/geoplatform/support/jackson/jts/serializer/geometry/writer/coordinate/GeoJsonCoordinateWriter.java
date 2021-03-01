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
package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.coordinate;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.support.jackson.jts.adapter.*;
import org.geosdi.geoplatform.support.jackson.jts.adapter.coordinate.GPJTSCoordinateAdapter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonCoordinateWriter implements IGeoJsonCoordinateWriter {

    /**
     * @param coordinateAdapter
     * @return {@link LngLatAlt}
     */
    @Override
    public LngLatAlt buildPointCoordinate(@Nonnull(when = NEVER) GPJTSCoordinateAdapter coordinateAdapter) {
        checkArgument(coordinateAdapter != null, "The Parameter coordinateAdapter must not be null.");
        return new LngLatAlt(coordinateAdapter.x(), coordinateAdapter.y());
    }

    /**
     * @param lineStringAdapter
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<LngLatAlt> buildLineStringCoordinate(AbstractJTSLineStringAdapter lineStringAdapter) {
        return stream(lineStringAdapter.getCoordinates())
                .filter(Objects::nonNull)
                .map(this::buildPointCoordinate)
                .collect(toList());
    }

    /**
     * @param polygonAdapter
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<LngLatAlt> buildPolygonExteriorRing(@Nonnull(when = NEVER) JTSPolygonAdapter polygonAdapter) {
        return buildLineStringCoordinate(polygonAdapter.getExteriorRing());
    }

    /**
     * @param polygonAdapter
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<List<LngLatAlt>> buildPolygonInteriorRing(@Nonnull(when = NEVER) JTSPolygonAdapter polygonAdapter) {
        checkArgument(polygonAdapter != null, "The Parameter polygonAdapter must not be null.");
        return iterate(0, n -> n + 1)
                .limit(polygonAdapter.getNumInteriorRing())
                .boxed()
                .map(i -> buildLineStringCoordinate(polygonAdapter.getInteriorRingN(i)))
                .collect(toList());
    }

    /**
     * @param multiPointAdapter
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<LngLatAlt> buildMultiPointCoordinate(@Nonnull(when = NEVER) JTSMultiPointAdapter multiPointAdapter) {
        checkArgument(multiPointAdapter != null, "The Parameter multiPointAdapter must not be null.");
        return iterate(0, n -> n + 1)
                .limit(multiPointAdapter.getNumGeometries())
                .boxed()
                .map(i -> buildPointCoordinate(multiPointAdapter.getGeometryN(i).getCoordinate()))
                .collect(toList());
    }

    /**
     * @param multiLineStringAdapter
     * @return {@link List<LngLatAlt>}
     */
    @Override
    public List<List<LngLatAlt>> buildMultiLineStringCoordinate(@Nonnull(when = NEVER) JTSMultiLinestringAdapter multiLineStringAdapter) {
        checkArgument(multiLineStringAdapter != null, "The Parameter multiLineStringAdapter must not be null.");
        return iterate(0, n -> n + 1)
                .limit(multiLineStringAdapter.getNumGeometries())
                .boxed()
                .map(i -> buildLineStringCoordinate((JTSLineStringAdapter) multiLineStringAdapter.getGeometryN(i)))
                .collect(toList());
    }
}