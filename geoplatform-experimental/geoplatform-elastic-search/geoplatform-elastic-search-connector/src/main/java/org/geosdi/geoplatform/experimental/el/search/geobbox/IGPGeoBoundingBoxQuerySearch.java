package org.geosdi.geoplatform.experimental.el.search.geobbox;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.Envelope;
import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoBoundingBoxQuerySearch extends IBooleanSearch {

    /**
     * @return {@link Envelope}
     */
    Envelope getEnvelope();

    /**
     * @return {@link GeoBoundingBoxQueryBuilder}
     */
    default GeoBoundingBoxQueryBuilder buildGeoBoundingBoxQueryBuilder() {
        return QueryBuilders.geoBoundingBoxQuery("mountainNowTodayPost.surveys.location")
                .topLeft(getEnvelope().getMaxY(), getEnvelope().getMinX())
                .topRight(getEnvelope().getMaxY(), getEnvelope().getMaxX())
                .bottomLeft(getEnvelope().getMinY(), getEnvelope().getMinX())
                .bottomRight(getEnvelope().getMinY(), getEnvelope().getMaxX());
    }

    /**
     *
     */
    @Immutable
    class GPGeoBoundingBoxQuerySearch extends AbstractBooleanSearch implements IGPGeoBoundingBoxQuerySearch {

        private final Envelope envelope;

        public GPGeoBoundingBoxQuerySearch(String theField, BooleanQueryType theType, Envelope theEnvelope) {
            super(theField, theType);
            Preconditions.checkArgument(theEnvelope != null, "The Parameter Envelope must not be null.");
            this.envelope = theEnvelope;
        }

        /**
         * @return {@link Envelope}
         */
        @Override
        public Envelope getEnvelope() {
            return this.envelope;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return buildGeoBoundingBoxQueryBuilder();
        }
    }
}
