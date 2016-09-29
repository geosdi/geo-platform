package org.geosdi.geoplatform.experimental.el.query.rest.service;

import org.geosdi.geoplatform.experimental.el.query.rest.path.GPElasticSearchQueryRestPathConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Path(value = GPElasticSearchQueryRestPathConfig.GP_ELASTICH_SEARCH_QUERY_PATH)
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
public interface IGPElasticSearchQueryService {

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
