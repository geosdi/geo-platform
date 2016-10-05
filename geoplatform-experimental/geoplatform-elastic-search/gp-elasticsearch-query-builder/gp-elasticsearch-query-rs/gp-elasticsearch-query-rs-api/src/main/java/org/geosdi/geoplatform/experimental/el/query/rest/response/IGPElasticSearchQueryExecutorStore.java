package org.geosdi.geoplatform.experimental.el.query.rest.response;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryExecutorStore<V extends Object> extends Serializable {

    /**
     * @return {@link Long}
     */
    Long getTotal();

    /**
     * @param theTotal
     */
    void setTotal(Long theTotal);

    /**
     * @return {@link Collection<V>}
     */
    Collection<V> getExecutionResults();

    /**
     * @param theExecutionResults
     */
    void setExecutionResults(Collection<V> theExecutionResults);
}
