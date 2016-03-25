package org.geosdi.geoplatform.experimental.el.dao.booleansearch;

import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.joda.time.DateTime;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Immutable
public class DateRangeSearch extends  IBooleanSearch.AbstractBooleanSearch{

    private DateTime dateFrom;
    private DateTime dateTo;

    public DateRangeSearch(String theField,DateTime dateFrom,DateTime dateTo,BooleanQueryType theType) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.field = theField;
        this.type = theType;
    }

    /**
     *
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery(){
        return QueryBuilders.rangeQuery(field).gte(dateFrom).lte(dateTo);
    }

    @Override
    public String toString() {
        return "DateRangeSearch{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                "} " + super.toString();
    }
}
