package org.geosdi.geoplatform.gui.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressDTO;
import org.geosdi.geoplatform.gui.client.service.response.WFSAddressStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface WFSGeocodingService extends RestService {

    String GEOCODING_SERVICE_URL = "http://localhost:8080/geoplatform-service/jsonCore/geocoding";

    /**
     * @param callback
     */
    @GET
    @Path(GEOCODING_SERVICE_URL + "/searchAddress")
    @Produces("application/json")
    void searchAddress(@QueryParam(value = "address") String address, MethodCallback<WFSAddressStore> callback);


}
