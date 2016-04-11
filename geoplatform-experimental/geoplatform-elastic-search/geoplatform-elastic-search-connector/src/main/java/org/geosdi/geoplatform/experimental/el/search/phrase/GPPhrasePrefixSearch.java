package org.geosdi.geoplatform.experimental.el.search.phrase;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPPhrasePrefixSearch extends GPPhraseSearch {

    public GPPhrasePrefixSearch(String theField, Object theValue, BooleanQueryType theType) {
        super(theField, theValue, theType);
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return QueryBuilders.matchQuery(field, value).type(MatchQueryBuilder.Type.PHRASE_PREFIX);
    }
}
