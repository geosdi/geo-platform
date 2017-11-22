package org.geosdi.geoplatform.cxf.rs.support.geocoding.services.impl;

import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.geosdi.geoplatform.cxf.rs.support.geocoding.delegate.IGPCXFGeocodingDelegate;
import org.geosdi.geoplatform.cxf.rs.support.geocoding.services.api.IGPCXFGeocodingRestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Service(value = "gpCXFGeocodingRestService")
public class GPCXFGeocodingRestService implements IGPCXFGeocodingRestService {

    @Resource(name = "gpCXFGeocodingDelegate")
    private IGPCXFGeocodingDelegate gpCXFGeocodingDelegate;

    /**
     * @param address
     * @param lang
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response searchAddress(String address, String lang) throws Exception {
        return Response.ok(this.gpCXFGeocodingDelegate.searchAddress(address, lang)).build();
    }

    @OPTIONS
    @Path("{path : .*}")
    @LocalPreflight
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, X-HTTP-Method-Override")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}
