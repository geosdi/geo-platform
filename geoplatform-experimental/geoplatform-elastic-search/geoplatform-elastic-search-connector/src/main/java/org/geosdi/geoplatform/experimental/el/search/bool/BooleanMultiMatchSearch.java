package org.geosdi.geoplatform.experimental.el.search.bool;

import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Arrays;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Immutable
public class BooleanMultiMatchSearch extends IBooleanSearch.AbstractBooleanSearch {

    private final String[] listaField;

    public BooleanMultiMatchSearch(String theValue, BooleanQueryType theType,
            MatchQueryBuilder.Operator theOperator, String[] listaField) {
        super(theValue, theType, theOperator);
        this.listaField = listaField;
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return QueryBuilders.multiMatchQuery(value, listaField).operator(this.operator);
    }

    @Override
    public String toString() {
        return "BooleanMultiMatchSearch{" +
                "listaField=" + Arrays.toString(listaField) +
                "} " + super.toString();
    }
}
