package org.geosdi.geoplatform.experimental.el.query.rest.response;

import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "GPElasticSearchQueryStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPElasticSearchQueryStore<QUERY extends GPElasticSearchQuery>
        implements IGPElasticSearchQueryStore<QUERY> {

    private static final long serialVersionUID = -4038368034305915379L;
    //
    private Long total;
    private List<QUERY> elasticSearchQueries;

    public GPElasticSearchQueryStore() {
    }

    public GPElasticSearchQueryStore(Long total, List<QUERY> elasticSearchQueries) {
        this.total = total;
        this.elasticSearchQueries = elasticSearchQueries;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getTotal() {
        return this.total;
    }

    /**
     * @param theTotal
     */
    @Override
    public void setTotal(Long theTotal) {
        this.total = theTotal;
    }

    /**
     * @return {@link List <QUERY>}
     */
    @Override
    public List<QUERY> getElasticSearchQueries() {
        return this.elasticSearchQueries;
    }

    /**
     * @param theElasticSearchQueries
     */
    @Override
    public void setElasticSearchQueries(List<QUERY> theElasticSearchQueries) {
        this.elasticSearchQueries = theElasticSearchQueries;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " total = " + total +
                ", elasticSearchQueries = " + elasticSearchQueries +
                '}';
    }
}
