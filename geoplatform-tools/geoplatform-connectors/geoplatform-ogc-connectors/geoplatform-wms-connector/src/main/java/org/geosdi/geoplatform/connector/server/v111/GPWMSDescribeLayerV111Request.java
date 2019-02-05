package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.server.request.GPWMSDescribeLayerRequest;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSDescribeLayerV111Request extends GPWMSDescribeLayerRequest<WMSDescribeLayerResponse> {

    /**
     * @param theLayers
     * @return {@link GPWMSDescribeLayerRequest<WMSDescribeLayerResponse>}
     */
    @Override
    GPWMSDescribeLayerRequest<WMSDescribeLayerResponse> withLayers(@Nonnull(when = When.NEVER) String... theLayers);
}