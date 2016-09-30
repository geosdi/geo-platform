package org.geosdi.geoplatform.experimental.el.query.rest.delegare;

import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;
import org.geosdi.geoplatform.experimental.el.query.rest.response.GPElasticSearchQueryExecutorStore;
import org.geosdi.geoplatform.experimental.el.query.rest.response.GPElasticSearchQueryStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryDelegate<QUERY extends GPElasticSearchQuery,
        QUERY_DAO extends IGPElasticSearchQueryDAO<QUERY>, REQUEST extends GPElasticSearchQueryExecutionRequest> {

    /**
     * @param from
     * @param size
     * @return {@link GPElasticSearchQueryStore<QUERY>}
     * @throws Exception
     */
    GPElasticSearchQueryStore<QUERY> findQuery(Integer from, Integer size) throws Exception;

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link GPElasticSearchQueryStore<QUERY>}
     * @throws Exception
     */
    GPElasticSearchQueryStore<QUERY> findQueryByCreationDate(Integer from, Integer size, Long fromDate,
            Long toDate) throws Exception;

    /**
     * @param request
     * @return {@link GPElasticSearchQueryExecutorStore<R>}
     * @throws Exception
     */
    <R> GPElasticSearchQueryExecutorStore<R> executeGPElasticSearchQuery(REQUEST request)
            throws Exception;

    /**
     * @param theQueryDAO
     */
    void setGPElasticSearchQueryDAO(QUERY_DAO theQueryDAO);
}
