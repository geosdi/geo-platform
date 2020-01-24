package org.geosdi.geoplatform.experimental.el.search.geopolygon;

import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.geoPolygonQuery;
import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.FILTER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoPolygonQuerySearch extends IBooleanSearch {

    /**
     * @return {@link List<GeoPoint>}
     */
    List<GeoPoint> getPoints();

    @Getter
    @Immutable
    class GPGeoPolygonQuerySearch extends AbstractBooleanSearch implements IGPGeoPolygonQuerySearch {

        private final List<GeoPoint> points;

        /**
         * @param theField
         * @param thePoints
         */
        public GPGeoPolygonQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) List<GeoPoint> thePoints) {
            super(theField, FILTER);
            checkArgument((thePoints != null) && !(thePoints.isEmpty()), "The Parameter points must not be null or an empty List.");
            this.points = thePoints;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return geoPolygonQuery(this.field, this.points);
        }
    }
}