package org.geosdi.geoplatform.experimental.el.search.match;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.Operator.OR;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPMatchQuerySearch extends IBooleanSearch.AbstractBooleanSearch {

    private final Operator operator;

    /**
     * @param theField
     * @param theValue
     * @param theType
     */
    public GPMatchQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Object theValue,
            @Nonnull(when = NEVER) BooleanQueryType theType, @Nullable Operator theOperator) {
        super(theField, theValue, theType);
        this.operator = ((theOperator != null) ? theOperator : OR);
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return matchQuery(field, value).operator(this.operator);
    }
}