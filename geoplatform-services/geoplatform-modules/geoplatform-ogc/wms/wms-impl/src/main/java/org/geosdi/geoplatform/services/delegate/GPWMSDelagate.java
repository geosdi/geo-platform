package org.geosdi.geoplatform.services.delegate;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geosdi.geoplatform.services.response.GPLayerTypeResponse;
import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSDelagate {

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @return {@link ServerDTO}
     * @throws Exception
     */
    ServerDTO getCapabilities(String serverUrl, RequestByID request, String token, String authkey) throws ResourceNotFoundFault;

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @param headers
     * @return {@link ServerDTO}
     * @throws Exception
     */
    ServerDTO getCapabilitiesAuth(String serverUrl, RequestByID request, String token, String authkey, List<WMSHeaderParam> headers) throws ResourceNotFoundFault;

    /**
     * @param serverUrl
     * @return {@link ServerDTO}
     * @throws Exception
     */
    ServerDTO getShortServer(String serverUrl) throws ResourceNotFoundFault;

    /**
     * @param serverURL
     * @param layerName
     * @return {@link GPLayerTypeResponse}
     * @throws Exception
     */
    GPLayerTypeResponse getLayerType(String serverURL, String layerName) throws Exception;

    /**
     * @param request
     * @return {@link WMSGetFeatureInfoResponse}
     * @throws Exception
     */
    WMSGetFeatureInfoResponse wmsGetFeatureInfo(GPWMSGetFeatureInfoRequest request) throws Exception;
}