package org.geosdi.geoplatform.experimental.el.query.rest.service;

import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;

import javax.ws.rs.core.Response;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryService<REQUEST extends GPElasticSearchQueryExecutionRequest> {

    /**
     * @param from
     * @param size
     * @return {@link Response}
     * @throws Exception
     */
    Response findGPElasticSearchQuery(Integer from, Integer size) throws Exception;

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link Response}
     * @throws Exception
     */
    Response findGPElasticSearchQueryByCreationDate(Integer from, Integer size, Long fromDate,
            Long toDate) throws Exception;
}
