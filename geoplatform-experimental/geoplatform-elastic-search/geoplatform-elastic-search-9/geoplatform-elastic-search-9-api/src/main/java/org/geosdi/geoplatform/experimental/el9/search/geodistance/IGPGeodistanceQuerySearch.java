/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el9.search.geodistance;

import co.elastic.clients.elasticsearch._types.DistanceUnit;
import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;
import org.locationtech.jts.geom.Coordinate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static co.elastic.clients.elasticsearch._types.DistanceUnit.Meters;
import static co.elastic.clients.elasticsearch._types.GeoDistanceType.Arc;
import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.FILTER;

/**
 * Ricerca dei punti entro una distanza da un punto (query geo_distance), sempre in FILTER.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeodistanceQuerySearch extends IBooleanSearch {

    /**
     * @return {@link Coordinate} il centro, con x longitudine e y latitudine
     */
    Coordinate getPoint();

    /**
     * @return la distanza, nell'unità di {@link #getDistanceUnit()}
     */
    double getDistance();

    /**
     * @return {@link DistanceUnit}
     */
    DistanceUnit getDistanceUnit();

    /**
     * @return {@link GeoDistanceType} il modo di calcolare la distanza
     */
    GeoDistanceType getGeoDistance();

    /**
     * Implementazione di {@link IGPGeodistanceQuerySearch}; senza unità usa i metri e senza tipo l'arco, come
     * ElasticSearch.
     */
    @Getter
    @Immutable
    class GPGeodistanceQuerySearch extends AbstractBooleanSearch implements IGPGeodistanceQuerySearch {

        private final Coordinate point;
        private final double distance;
        private final DistanceUnit distanceUnit;
        private final GeoDistanceType geoDistance;

        /**
         * @param theField        il campo geo_point
         * @param thePoint        il centro, con x longitudine e y latitudine
         * @param theDistance     la distanza, maggiore di zero
         * @param theDistanceUnit l'unità; null vuol dire metri
         * @param theGeoDistance  il modo di calcolare la distanza; null vuol dire arco
         */
        public GPGeodistanceQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Coordinate thePoint,
                double theDistance, @Nullable DistanceUnit theDistanceUnit, @Nullable GeoDistanceType theGeoDistance) {
            super(theField, FILTER);
            checkArgument(thePoint != null, "Il parametro point non deve essere null.");
            checkArgument(theDistance > 0, "Il parametro distance deve essere maggiore di zero.");
            this.point = thePoint;
            this.distance = theDistance;
            this.distanceUnit = ((theDistanceUnit != null) ? theDistanceUnit : Meters);
            this.geoDistance = ((theGeoDistance != null) ? theGeoDistance : Arc);
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query buildQuery() {
            return Query.of(q -> q.geoDistance(g -> g.field(this.field)
                    .location(l -> l.latlon(ll -> ll.lat(this.point.getY()).lon(this.point.getX())))
                    .distance(this.distance + this.distanceUnit.jsonValue())
                    .distanceType(this.geoDistance)));
        }
    }
}
