package org.geosdi.geoplatform.experimental.el.dao.booleansearch;

import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Immutable
class BooleanFieldExistsSearch extends  IBooleanSearch.AbstractBooleanSearch{

    public BooleanFieldExistsSearch(String theField) {
        this.field = theField;
    }

    /**
     *
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery(){ return QueryBuilders.existsQuery(field); }

}
