package org.geosdi.geoplatform.gui.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressDTO;
import org.geosdi.geoplatform.gui.client.service.request.GPGeocodingAddressRequestDTO;
import org.geosdi.geoplatform.gui.client.service.response.FeatureCollectionResponse;
import org.geosdi.geoplatform.gui.client.service.response.WFSAddressStore;

import javax.ws.rs.*;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface WFSGeocodingService extends RestService {

    String GEOCODING_SERVICE_URL = "http://localhost:8080/geoplatform-service/geocoding/nominatim";

    /**
     * @param callback
     */
    @POST
    @Path(GEOCODING_SERVICE_URL + "/searchAddress")
    @Produces("application/json")
    void searchAddress(GPGeocodingAddressRequestDTO gpGeocodingAddressRequestDTO, MethodCallback<FeatureCollectionResponse> callback);


}
