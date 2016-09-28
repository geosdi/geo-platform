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
    Response findGPElasticSearchQuery(@QueryParam(value = "from") @DefaultValue(value = "0") Integer from,
            @QueryParam(value = "size") @DefaultValue(value = "10") Integer size) throws Exception;

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link Response}
     * @throws Exception
     */
    Response findGPElasticSearchQueryByCreationDate(@QueryParam(value = "from") @DefaultValue(value = "0") Integer from,
            @QueryParam(value = "size") @DefaultValue(value = "10") Integer size,
            @QueryParam(value = "fromDate") Long fromDate, @QueryParam(value = "toDate") Long toDate)
            throws Exception;
}
