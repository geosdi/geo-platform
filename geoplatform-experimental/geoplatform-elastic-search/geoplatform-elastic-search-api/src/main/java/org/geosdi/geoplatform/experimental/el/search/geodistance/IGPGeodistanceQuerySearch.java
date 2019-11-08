package org.geosdi.geoplatform.experimental.el.search.geodistance;

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

    class GPGeodistanceQuerySearch extends AbstractBooleanSearch implements IGPGeodistanceQuerySearch {

        private final GeoPoint geoPoint;
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
            this.geoPoint = theGeoPoint;
            this.distance = theDistance;
            this.distanceUnit = (theDistanceUnit != null) ? theDistanceUnit : DEFAULT;
            this.geoDistance = (theGeoDistance != null) ? theGeoDistance : ARC;
        }

        /**
         * @return {@link GeoPoint}
         */
        @Override
        public GeoPoint getPoint() {
            return this.geoPoint;
        }

        /**
         * @return {@link Double}
         */
        @Override
        public double getDistance() {
            return this.distance;
        }

        /**
         * @return {@link DistanceUnit}
         */
        @Override
        public DistanceUnit getDistanceUnit() {
            return this.distanceUnit;
        }

        /**
         * @return {@link GeoDistance}
         */
        @Override
        public GeoDistance getGeoDistance() {
            return this.geoDistance;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return geoDistanceQuery(this.field).point(this.geoPoint).geoDistance(this.geoDistance).distance(this.distance, this.distanceUnit);
        }
    }
}