package org.geosdi.geoplatform.cxf.rs.support.geocoding.services.api;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.geosdi.geoplatform.cxf.rs.support.geocoding.path.GPCXFGeocodingRestPathConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true,
        maxAge = 1,
        allowHeaders = {
                "authorization", "content-type", "X-HTTP-Method-Override"
        },
        exposeHeaders = {
                "authorization", "content-type", "X-HTTP-Method-Override"
        }
)
@Path(value = GPCXFGeocodingRestPathConfig.GP_CXF_GEOCODING_REST_PATH)
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
public interface IGPCXFGeocodingRestService {

    /**
     * @param address
     * @param lang
     * @return {@link Response}
     * @throws Exception
     */
    @GET
    @Path(value = GPCXFGeocodingRestPathConfig.SEARCH_ADDRESS_PATH)
    Response searchAddress(@QueryParam(value = "address") String address,
            @DefaultValue(value = "en") @QueryParam(value = "lang") String lang) throws Exception;
}
