package org.geosdi.geoplatform.experimental.el.search.string;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPQueryStringSearch extends IBooleanSearch.AbstractBooleanSearch {

    /**
     * @param theField
     * @param theValue
     * @param theType
     */
    public GPQueryStringSearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Object theValue, @Nonnull(when = NEVER) BooleanQueryType theType) {
        super(theField, theValue, theType);
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return QueryBuilders.queryStringQuery(this.value.toString()).field(this.field);
    }
}
