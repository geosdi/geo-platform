package org.geosdi.geoplatform.services.builder;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWMSCapabilitesBuilder extends Serializable {

    /**
     * @param serverUrl
     * @param token
     * @param authkey
     * @return {@link List<RasterLayerDTO>}
     * @throws ResourceNotFoundFault
     */
    List<RasterLayerDTO> loadWMSCapabilities(@Nonnull(when = NEVER) String serverUrl, @Nullable String token, @Nullable String authkey) throws ResourceNotFoundFault;

    /**
     * @param serverUrl
     * @param token
     * @param authkey
     * @param headers
     * @return {@link List<RasterLayerDTO>}
     * @throws ResourceNotFoundFault
     */
    List<RasterLayerDTO> loadWMSCapabilitiesAuth(@Nonnull(when = NEVER) String serverUrl, @Nullable String token,
            @Nullable String authkey, @Nullable List<WMSHeaderParam> headers) throws ResourceNotFoundFault;
}