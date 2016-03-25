package org.geosdi.geoplatform.experimental.el.dao.booleansearch;

import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Immutable
public class BooleanExactSearch extends  IBooleanSearch.AbstractBooleanSearch{

    public BooleanExactSearch(String theField, String theValue, BooleanQueryType theType,MatchQueryBuilder.Operator theOperator) {
        this.value = theValue;
        this.field = theField;
        this.type = theType;
        this.operator = theOperator;
    }

    /**
     *
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery(){
        return QueryBuilders.matchQuery(field, value).operator(operator);
    }

}
