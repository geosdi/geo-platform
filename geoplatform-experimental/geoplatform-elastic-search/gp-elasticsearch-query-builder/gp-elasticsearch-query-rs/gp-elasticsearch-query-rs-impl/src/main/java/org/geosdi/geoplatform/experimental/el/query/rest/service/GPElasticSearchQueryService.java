package org.geosdi.geoplatform.experimental.el.query.rest.service;

import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.delegare.IGPElasticSearchQueryDelegate;
import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryService<QUERY extends GPElasticSearchQuery,
        QUERY_DAO extends IGPElasticSearchQueryDAO<QUERY>, REQUEST extends GPElasticSearchQueryExecutionRequest,
        QUERY_DELEGATE extends IGPElasticSearchQueryDelegate<QUERY, QUERY_DAO, REQUEST>>
        implements GPElasticSearchQueryServiceSupport<QUERY, QUERY_DAO, QUERY_DELEGATE, REQUEST> {

    protected QUERY_DELEGATE queryDelegate;

    /**
     * @param from
     * @param size
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response findGPElasticSearchQuery(Integer from, Integer size) throws Exception {
        return Response.ok(this.queryDelegate.findQuery(from, size)).build();
    }

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response findGPElasticSearchQueryByCreationDate(Integer from, Integer size, Long fromDate, Long toDate)
            throws Exception {
        return Response.ok(this.queryDelegate.findQueryByCreationDate(from, size, fromDate, toDate)).build();
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response executeGPElasticSearchQuery(REQUEST request) throws Exception {
        return Response.ok(this.queryDelegate.executeGPElasticSearchQuery(request)).build();
    }

    /**
     * @param theQueryDelegate
     */
    @Autowired
    @Override
    public void setQueryDelegate(QUERY_DELEGATE theQueryDelegate) {
        this.queryDelegate = theQueryDelegate;
    }
}
