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
public class BooleanMultiMatchSearch extends  IBooleanSearch.AbstractBooleanSearch{

    private String[] listaField;

    public BooleanMultiMatchSearch(String theValue, BooleanQueryType theType,MatchQueryBuilder.Operator theOperator,String[] listaField) {
        this.value = theValue;
        this.type = theType;
        this.operator = theOperator;
        this.listaField = listaField;
    }

    /**
     *
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery(){ return QueryBuilders.multiMatchQuery(value, listaField).operator(this.operator); }
}
