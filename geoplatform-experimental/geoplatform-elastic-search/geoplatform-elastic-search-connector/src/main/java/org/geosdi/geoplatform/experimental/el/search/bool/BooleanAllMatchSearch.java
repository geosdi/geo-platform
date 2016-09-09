package org.geosdi.geoplatform.experimental.el.search.bool;

import com.google.common.base.Preconditions;
import org.elasticsearch.index.query.QueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BooleanAllMatchSearch implements IBooleanSearch {

    private final BooleanQueryType type;

    public BooleanAllMatchSearch(BooleanQueryType theType) {
        Preconditions.checkArgument((theType != null), "The Parameter Type must not be null.");
        this.type = theType;
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return matchAllQuery();
    }

    /**
     * @return {@link BooleanQueryType}
     */
    @Override
    public BooleanQueryType getType() {
        return this.type;
    }
}

