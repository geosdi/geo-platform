package org.geosdi.geoplatform.experimental.el.search.bool;

import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Immutable
public class BooleanPrefixSearch extends IBooleanSearch.AbstractBooleanSearch {

    public BooleanPrefixSearch(String theField, Object theValue, BooleanQueryType theType,
            MatchQueryBuilder.Operator theOperator) {
        super(theValue, theField, theType, theOperator);
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return QueryBuilders.prefixQuery(field, value.toString());
    }
}
