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
package org.geosdi.geoplatform.experimental.el.search.geodistance;

import lombok.Getter;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.common.geo.GeoDistance.ARC;
import static org.elasticsearch.common.unit.DistanceUnit.DEFAULT;
import static org.elasticsearch.index.query.QueryBuilders.geoDistanceQuery;
import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.FILTER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeodistanceQuerySearch extends IBooleanSearch {

    /**
     * @return {@link GeoPoint}
     */
    GeoPoint getPoint();

    /**
     * @return {@link Double}
     */
    double getDistance();

    /**
     * @return {@link DistanceUnit}
     */
    DistanceUnit getDistanceUnit();

    /**
     * @return {@link GeoDistance}
     */
    GeoDistance getGeoDistance();

    @Getter
    class GPGeodistanceQuerySearch extends AbstractBooleanSearch implements IGPGeodistanceQuerySearch {

        private final GeoPoint point;
        private final double distance;
        private final DistanceUnit distanceUnit;
        private final GeoDistance geoDistance;

        /**
         * @param theField
         * @param theGeoPoint
         * @param theDistance
         * @param theDistanceUnit
         * @param theGeoDistance
         */
        public GPGeodistanceQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) GeoPoint theGeoPoint,
                double theDistance, @Nullable DistanceUnit theDistanceUnit, @Nullable GeoDistance theGeoDistance) {
            super(theField, FILTER);
            checkArgument(theGeoPoint != null, "The Parameter geoPoint must not be null.");
            checkArgument(theDistance > 0, "The Parameter distance must be greather than zero.");
            this.point = theGeoPoint;
            this.distance = theDistance;
            this.distanceUnit = (theDistanceUnit != null) ? theDistanceUnit : DEFAULT;
            this.geoDistance = (theGeoDistance != null) ? theGeoDistance : ARC;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return geoDistanceQuery(this.field).point(this.point).geoDistance(this.geoDistance).distance(this.distance, this.distanceUnit);
        }
    }
}