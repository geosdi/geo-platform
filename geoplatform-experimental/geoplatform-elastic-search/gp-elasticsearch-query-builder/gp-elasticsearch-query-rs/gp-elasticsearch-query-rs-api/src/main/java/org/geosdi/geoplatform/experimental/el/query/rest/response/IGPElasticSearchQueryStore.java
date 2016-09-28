package org.geosdi.geoplatform.experimental.el.query.rest.response;

import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryStore<QUERY extends GPElasticSearchQuery> extends Serializable {

    /**
     * @return {@link Long}
     */
    Long getTotal();

    /**
     * @param theTotal
     */
    void setTotal(Long theTotal);

    /**
     * @return {@link List<QUERY>}
     */
    List<QUERY> getElasticSearchQueries();

    /**
     * @param theElasticSearchQueries
     */
    void setElasticSearchQueries(List<QUERY> theElasticSearchQueries);
}
