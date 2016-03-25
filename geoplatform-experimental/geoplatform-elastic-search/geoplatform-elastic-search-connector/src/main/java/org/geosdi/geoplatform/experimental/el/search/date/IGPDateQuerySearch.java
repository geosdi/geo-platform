package org.geosdi.geoplatform.experimental.el.search.date;

import com.google.common.base.Preconditions;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.search.IGPQuerySearch;
import org.joda.time.DateTime;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPDateQuerySearch extends IGPQuerySearch {

    /**
     * @return {@link DateTime}
     */
    DateTime getDateTo();

    /**
     * @return {@link DateTime}
     */
    DateTime getDateFrom();

    /**
     *
     */
    @Immutable
    class GPDateQuerySearch implements IGPDateQuerySearch {

        private final String field;
        private final DateTime dateTo;
        private final DateTime dateFrom;

        public GPDateQuerySearch(String theField, DateTime theDateTo, DateTime theDateFrom) {
            Preconditions.checkArgument((theField != null) && !(theField.isEmpty()), "The Parameter Field " +
                    "must not be null or an Empty String.");
            Preconditions.checkArgument((theDateTo != null), "The Parameter DateTo must not be null.");
            Preconditions.checkArgument((theDateFrom != null), "The Parameter DateFrom must not be null.");
            Preconditions.checkArgument((theDateFrom.isAfter(theDateTo)), "The Parameter DateFrom must be after DateTo.");
            this.field = theField;
            this.dateTo = theDateTo;
            this.dateFrom = theDateFrom;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getField() {
            return this.field;
        }

        /**
         * @return {@link DateTime}
         */
        @Override
        public DateTime getDateTo() {
            return this.dateTo;
        }

        /**
         * @return {@link DateTime}
         */
        @Override
        public DateTime getDateFrom() {
            return this.dateFrom;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return QueryBuilders.rangeQuery(field).gte(dateFrom).lte(dateTo);
        }
    }
}
