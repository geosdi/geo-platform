package org.geosdi.geoplatform.experimental.el.search.match;

import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPMatchQuerySearch extends IBooleanSearch.AbstractBooleanSearch {

    /**
     * @param theField
     * @param theValue
     * @param theType
     */
    public GPMatchQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Object theValue, @Nonnull(when = NEVER) BooleanQueryType theType) {
        super(theField, theValue, theType);
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return matchQuery(field, value);
    }
}